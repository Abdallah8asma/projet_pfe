angular
	.module('gpro.bondelivraisonBS', ["ngResource"])
	.controller(
		'bonlivraisonControllerBS',
		[
			'$scope',
			'$filter',
			'$http',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'BonLivraisonServices',
			'traitementFaconServices',
			'$window',
			'$rootScope',
			function($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier, BonLivraisonServices, traitementFaconServices, $window, $rootScope) {
				$log.info("=========Vente========");

				const CODE_ERROR_EDITION_BL_HAS_FACTURE = "CODE_ERROR_EDITION_BL_HAS_FACTURE";
				const CODE_ERROR_EDITION_BL_HAS_BON_RETOUR = "CODE_ERROR_EDITION_BL_HAS_BON_RETOUR";


				$scope.tagReferenceBSList = [];

				$scope.natureLivraison = "FINI";

				$scope.bonLivraisonVenteCourant = {
					"referenceBl": '',
					"referenceBs=": '',
					"partieIntId": '',
					"dateLivraisonMin": '',
					"dateLivraisonMax": '',
					"metrageMin": '',
					"metrageMax": '',
					"prixMin": '',
					"prixMax": '',
					"natureLivraison": '',
					"avecFacture": '',
					"devise": '2'
				};

				$scope.erreurUpdateBL = "false";
				$scope.msgErreurUpdateBL = "";

				$scope.produitIdFinancier = "";
				$scope.produitIdNonFinancier = "";


				$scope.listeProduitFinancier = [];

				$scope.listeProduitNonFinancier = [];



				$scope.traitementEnCours = "false";

				$scope.msg = "";





				// Liste des produits
				$scope.listeProduitCacheFinance = function() {
					$http
						.get(
							UrlCommun
							+ "/produit/all/retour")
						.success(
							function(data) {
								console

								$scope.listeProduitFinancier = data;

							});
				}

				$scope.listeProduitCacheFinance();


				// Liste des produits
				$scope.listeProduitCacheNonFinance = function() {
					$http
						.get(
							UrlCommun
							+ "/produit/all/non-retour")
						.success(
							function(data) {
								console

								$scope.listeProduitNonFinancier = data;

							});
				}

				$scope.listeProduitCacheNonFinance();

				// REST SERVICE MAGAZINS
				$scope.listeMagazinCache = function() {
					$http
						.get(
							UrlAtelier
							+ "/magasin/depots")
						.success(
							function(dataMagazin) {


								$scope.listeMagazinCache = dataMagazin;
								$log
									.debug("listeMagazinCache : "
										+ dataMagazin.length)

							});
				}




				//Tableau de Taxe Prédefini 

				$scope.listTaxeLivraisonInitMethod = function() {
					if ($scope.natureLivraison == "FINI") {
						$scope.listTaxeLivraisonInit = [
							{//FODEC
								taxeId: 1,
								pourcentage: 1,
								montant: '',
							},
							{//TVA
								taxeId: 2,
								pourcentage: 19,
								montant: '',
							}];
					} else {
						$scope.listTaxeLivraisonInit = [
							{//TVA
								taxeId: 2,
								pourcentage: 19,
								montant: '',
							}];
					}
				}


				if ($scope.navMode == "redirection") {

					$scope.natureLivraison = "FINI";
					$scope.listTaxeLivraisonInitMethod();


					$log.debug("---livraison----origine" + $scope.origine);
					if ($scope.origine = true) {


						$scope.bonLivraisonVenteCourant.reference = "";

						$scope.bonLivraisonVenteCourant = {
							//								"reference" : $scope.referenceBs,
							"date": $scope.dateSortieBs
						};


						$log.debug("-------$scope.bonLivraisonVenteCourant" + $scope.bonLivraisonVenteCourant);
					} else {


						$scope.bonLivraisonVenteCourant = {
							"reference": $scope.referenceBs,
							"date": $scope.dateSortieBs
						};

					}


					$scope.tagReferenceBSList.push($scope.referenceBs);
					$scope.displayMode = "edit";

				} else if ($scope.navMode == 'normal') {
					$scope.bonLivraisonVenteCourant = {};
					$scope.displayMode = "list";
				}

				var data;
				// bouton pdf hide
				$scope.modePdf = "notActive";

				//mode list activé
				$scope.listeBonSortie = [];
				$scope.listDetLivraisonVente = [];
				$scope.listDetLivraisonVentePRBS = [];
				$scope.idBonLivVente = '';
				//liste des ReferencesBS

				$scope.listTaxeLivraison = [];

				//modeValider
				$scope.modeValider = "notActif";
				//init deleteValue pour cancelAddBLVente
				$scope.deleteValue = "oui";

				/**************************************************
				 *Gestion des DropListe & cache 
				 **************************************************/
				$scope.listePartieInteresseeCache = function() {
					$http
						.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function(dataPartieInteressee) {
								$scope.listePartieInteresseeCache = dataPartieInteressee;
								$log.debug("listePartieInteresseeCache : " + dataPartieInteressee.length)

							});
				}

				//TODO: Liste des Taxes A remplacer par une liste extraite de la cache 
				$scope.listeTaxes = function() {
					$http
						.get(UrlAtelier + "/taxe/getAll")
						.success(
							function(dataTaxe) {

								$scope.listeTaxes = dataTaxe;
							});
				}

				//TODO: Liste des Marches A remplacer par une liste extraite de la cache 
				/* $scope.listeMarche = function() {
					 $http
					 .get(UrlAtelier + "/marche/getAll")
					 .success(
							 function(dataMarche) {
	
								 $scope.listeMarche = dataMarche;
								 $log.debug("------Vente Js: listeMarche : "+ dataMarche.length);
							 });
				 }*/

				//TODO: Liste des modePaiement A remplacer par une liste extraite de la cache 
				$scope.listeModePaiement = function() {
					$http
						.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeModePaiementCache")
						.success(
							function(dataPaiement) {

								$scope.listeModePaiement = dataPaiement;
							});
				}

				// Liste des traitements façon (hors cache)
				$scope.getListeTraitementFacon = function() {
					traitementFaconServices.getListeTraitementFacon().then(function(listeTraitementFacon) {
						$scope.listeTraitementFacon = listeTraitementFacon;
					}, function(error) {
						console.log(error.statusText);
					});
				}


				// Liste des Devises
				$scope.ListeDevise = function() {
					$http.get(UrlCommun + '/devise/all').success(function(dataDevise) {
						$scope.ListeDevise = dataDevise;
					});
				};








				$scope.ListeDevise();
				$scope.listePartieInteresseeCache();
				$scope.listeTaxes();
				//$scope.listeMarche();
				$scope.listeModePaiement();
				$scope.getListeTraitementFacon();
				$scope.listeMagazinCache();
				/***************************************************
				 * Conversion des Ids/Designation
				 **************************************************/
				//TypeTaxe
				$scope.typeTaxeId = {

					status: ''
				};
				$scope.showTaxe = function(id) {
					$scope.typeTaxeId.status = id;
					var selected = $filter('filter')(
						$scope.listeTaxes, {
						id: $scope.typeTaxeId.status
					});
					if ($scope.typeTaxeId.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}

				}

				/**************************************************
				 *Gestion de suppression d'une ligne d'un tableau
				 *************************************************/

				// Annuler Ajout
				$scope.cancelAddBLVente = function(rowform, index, id, designation, liste) {
					$log.debug("cancelAddBLVente");
					$log.debug("* Designation" + liste[0].designation);
					$log.debug("* ID" + id);
					if (angular.isDefined(id)) {

						$log.debug("DEF ID");
						$scope.deleteValue = "non";
						rowform.$cancel();
						$log.debug("CANCEL");
					} else {
						$log.debug("ID  UNDEFINED ");
						if (designation == "") {
							$scope.deleteValue == "oui"
							$log.debug("Designation : " + designation);
							$log.debug("$scope.deleteValueOUI : " + $scope.deleteValue);
							liste.splice(index, 1);
							rowform.$cancel();
							$log.debug("DELETE")
						} else {
							$log.debug("Designation :" + designation);
							$log.debug("$scope.deleteValueNON : " + $scope.deleteValue);
							rowform.$cancel();
							$log.debug("CANCEL");
						}
					}
					$scope.deleteValue = "oui";
				}
				/**************************************************
				 *Gestion des TaxeBLiv
				 **************************************************/
				/** Mode Creation **/
				$scope.addTaxeBLivInit = function() {
					$scope.inserted = {
						taxeId: '',
						pourcentage: '',
						montant: '',
					};
					$scope.listTaxeLivraisonInit.push($scope.inserted);
				};

				// remove TaxeBLivInit
				$scope.removeTaxeBLivInit = function(index, taxeId) {
					$log.debug("---remove taxe--");
					$scope.listTaxeLivraisonInit.splice(index, 1);

					if (angular.isNumber(taxeId)) {
						var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
						$scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
						$scope.filterTaxes();
					}

				};

				// remove TaxeBLiv
				$scope.removeTaxeBLiv = function(index, taxeId) {

					$scope.listTaxeLivraison.splice(index, 1);
					if (angular.isNumber(taxeId)) {
						var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
						$scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
						$scope.filterTaxes();
					}
				};

				/** Mode Modification **/
				// add TaxeBLiv, /_!_\  'produitId' DOIT etre TJR UNDEFINED dans l'objet ajouté pour que cancelBLVente fonctionne correctement
				$scope.addTaxeBLiv = function() {
					$scope.inserted = {
						taxeId: '',
						pourcentage: '',
						montant: '',
					};
					$scope.listTaxeLivraison.push($scope.inserted);
				};

				//saveTaxeBLiv
				$scope.saveTaxeBLiv = function(data) {
					$scope.taxeIdRemove.push(data.taxeId);
					$scope.filterTaxes();
				};

				$scope.saveTaxeBLivInit = function(data) {
					$scope.taxeIdRemove.push(data.taxeId);
					$scope.filterTaxes();
				}


				$scope.initTaxeRemoved = function() {
					if ($scope.natureLivraison == "FINI") {
						$scope.taxeIdRemove = [1, 2]; //FODEC + TVA
					} else {
						$scope.taxeIdRemove = [2]; //TVA uniquement
					}
				}


				// Filtre de la dropList des taxes
				$scope.filterTaxes = function() {

					return function(item) {
						var condition = false;

						for (var k = 0; k < $scope.taxeIdRemove.length; k++) {
							if (item.id != $scope.taxeIdRemove[k]) {
								condition = true
							} else {
								condition = false;
								break;
							}
						}

						if (condition == true) {
							return true;
						}
						return false;
					};
				};





				// ajout d'un Produit
				$scope.ajoutProduit = function(produitId) {


					console.log("call ajoutProduit");
					console.log("produitId=", produitId);


					if (produitId != null) {

						var element = $scope.listeProduitFinancier.filter(e => e.id == produitId);

						if (element != null && element[0] != null) {


							$scope.produitInserree = {
								produitId: produitId,
								produitReference: element[0].reference,
								produitDesignation: element[0].designation,
								numeroOF: element[0].reference,

								quantite: 1,
								unite: '',
								prixUnitaireHT: element[0].prixUnitaire,
								choix: "1",
								ficheId: 1,
								//prixTotalHT : '',
								//	nouveau :true,
								//remise : ''
							};

							$scope.listDetLivraisonVentePRBS
								.push($scope.produitInserree);

						}




					}

				};

				// ajout d'un Produit
				$scope.ajoutProduitNonFinancier = function(produitId) {


					console.log("call ajoutProduitNonFinancier");
					console.log("produitId=", produitId);


					if (produitId != null) {

						var element = $scope.listeProduitNonFinancier.filter(e => e.id == produitId);

						if (element != null && element[0] != null) {


							$scope.produitInserree = {
								produitId: produitId,
								produitReference: element[0].reference,
								produitDesignation: element[0].designation,
								numeroOF: element[0].reference,
								quantite: 1,
								unite: '',
								prixUnitaireHT: element[0].prixUnitaire,
								choix: "1",
								ficheId: 1,
								//prixTotalHT : '',
								//	nouveau :true,
								//remise : ''
							};

							$scope.listDetLivraisonVentePRBS
								.push($scope.produitInserree);

						}




					}

				};


				// Supprimer Produit
				$scope.removeProduit = function(index) {




					$scope.listDetLivraisonVentePRBS.splice(index, 1);

					console.log("Success Delete Produit ");


				};
				/**************************************************
				 *Gestion des ProduitBS & traitementsFacon
				 **************************************************/

				//champ autoSaisie du champs: referenceBS 
				$scope.listReferenceBS = [];
				$scope.select2TaggingOptions = {
					'multiple': true,
					'simple_tags': true,
					'tags': function() {
						
							$scope.traitementEnCours = "true";
						
						if($scope.bonLivraisonVenteCourant.partieIntId != undefined && $scope.bonLivraisonVenteCourant.partieIntId != "" ){
							
								//reload de la liste des RefBS
						$http
							.get(
								UrlAtelier
								+ "/bonsortiefini/getAvailableListBonSortieFiniRefByClientId:"+$scope.bonLivraisonVenteCourant.partieIntId)
							.success(
								function(resultat) {
									$log.debug("--ResultatListBS " + resultat.length);
									$scope.listReferenceBS = resultat;
									
									$scope.traitementEnCours = "false";
									
							    	return $scope.listReferenceBS;
									//$log.debug("listBS : "+resultat.list.length);
									//$log.debug("--listReferenceBS : "+JSON.stringify($scope.listReferenceBS, null, "    "));

								});
								
						$log.debug("--OnClicklistReferenceBS : " + JSON.stringify($scope.listReferenceBS, null, "    "));
						//return $scope.listReferenceBS;s
						}
						
						return $scope.listReferenceBS;
					
					}
				};

				//bouton ValiderBS: tagReferenceBSList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : infoSortie sous la forme de ref1-ref2-.. 
				// L'API appelée dépend de la nature de livraison (Fini / Facon )

				$scope.validerBS = function() {

					$log.debug(" Recherche des Produits appartenants à ces Bons de Sortie ...");
					$log.debug("-- tagReferenceBSList : " + JSON.stringify($scope.tagReferenceBSList, null, "    "));

					//idBonLivVente: si undefined -> urlValier SANS idBonLivVente, sinon -> idBonLivVente AVEC idBonLivVente
					$log.debug("Valider : idBonLivVente " + $scope.idBonLivVente);

					//Type livraison fini
					if ($scope.natureLivraison == 'FINI') {
						$scope.validerNatureFini();

					} else { // $scope.natureLivraison == 'facon'

						$scope.validerNatureFacon();
					}
				}


				$scope.validerBSByOF = function() {

					$log.debug(" Recherche des Produits appartenants à ces Bons de Sortie ...");
					$log.debug("-- tagReferenceBSList : " + JSON.stringify($scope.tagReferenceBSList, null, "    "));

					//idBonLivVente: si undefined -> urlValier SANS idBonLivVente, sinon -> idBonLivVente AVEC idBonLivVente
					$log.debug("Valider : idBonLivVente " + $scope.idBonLivVente);

					//Type livraison fini
					if ($scope.natureLivraison == 'FINI') {
						$scope.validerNatureFiniByOF();

					}
				}

				$scope.validerNatureFini = function() {


					$scope.validerNatureFiniByOF();

					/*
					 $log.debug("Log1: idBonLiv = " + $scope.idBonLivVente);
						$scope.traitementEnCours = "true";
					 
					 BonLivraisonServices.validateFini($scope.tagReferenceBSList,$scope.idBonLivVente).then(function(resultat){
						//bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
						 $scope.modeValider = "actif";
						 //setDateInto = dateSortie du 1erBS
						 $scope.bonLivraisonVenteCourant.date = resultat.dateSortie;
						 //listDetLivraisonVentePRBS
						 $scope.listDetLivraisonVentePRBS = resultat.listDetLivraisonVente;
						 $log.debug("-- listDetLivraisonVentePRBS Size : "+ $scope.listDetLivraisonVentePRBS.length);
	
						 $log.debug("-- listDetLivraisonVentePRBS : "+ JSON.stringify($scope.listDetLivraisonVentePRBS,null,'  '));
							$scope.traitementEnCours = "false";
					 }
					 ,function(error){
						 console.log(error.statusText);
					 });*/
				}


				$scope.validerNatureFiniByOF = function() {

					$log.debug("Log1: idBonLiv = " + $scope.idBonLivVente);
					$scope.traitementEnCours = "true";

					BonLivraisonServices.validateFiniByOF($scope.tagReferenceBSList, $scope.idBonLivVente).then(function(resultat) {
						//bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
						$scope.modeValider = "actif";
						//setDateInto = dateSortie du 1erBS
						$scope.bonLivraisonVenteCourant.date = resultat.dateSortie;
						//listDetLivraisonVentePRBS
						$scope.listDetLivraisonVentePRBS = resultat.listDetLivraisonVente;
						$log.debug("-- listDetLivraisonVentePRBS Size : " + $scope.listDetLivraisonVentePRBS.length);

						$log.debug("-- listDetLivraisonVentePRBS : " + JSON.stringify($scope.listDetLivraisonVentePRBS, null, '  '));
						$scope.traitementEnCours = "false";
					}
						, function(error) {
							console.log(error.statusText);
						});
				}



				$scope.validerNatureFacon = function() {

					$log.debug("Log1: idBonLiv = " + $scope.idBonLivVente);

					BonLivraisonServices.validateFacon($scope.tagReferenceBSList, $scope.idBonLivVente).then(function(resultat) {
						//bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
						$scope.modeValider = "actif";
						//setDateInto = dateSortie du 1erBS
						$scope.bonLivraisonVenteCourant.date = resultat.dateSortie;
						//listDetLivraisonVentePRBS
						var listeDetLivraison = resultat.listDetLivraisonVente;

						$scope.listDetLivraisonVentePRBS = [];
						$log.debug("-listeDetLivraison--" + JSON.stringify(listeDetLivraison, null, " "));

						angular.forEach(listeDetLivraison, function(elementDetLivraison, value) {

							var ligneTraitement = [];
							//Filter retourne un résultat de type []						 
							ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetLivraison.traitementFaconId });
							elementDetLivraison.designationTraitement = ligneTraitement[0].designation;
							$scope.listDetLivraisonVentePRBS.push(elementDetLivraison);
						})


						$log.debug("-- listDetLivraisonVentePRBS Size : " + $scope.listDetLivraisonVentePRBS.length);

						$log.debug("-- listDetLivraisonVentePRBS : " + JSON.stringify($scope.listDetLivraisonVentePRBS, null, '  '));

					}
						, function(error) {
							console.log(error.statusText);
						});

				}

				/*************************************************
				 * Gestion de la table Produit: table DIRECTEMENT 
				 * editable sur le champ 'Remise' seulement.
				 *************************************************/
				$scope.remiseChanged = function(index) {

					$log.debug("remise changed call");

					if ($scope.idBonLivVente == '') {

						//remiseChangedOnCreation
						$log.debug("remiseChangedOnCreation INDEX Changed " + index);
						$log.debug("listDetLivraisonVentePRBS Remise : " + $scope.listDetLivraisonVentePRBS[index].remise);

						$scope.listDetLivraisonVentePRBS[index].remise = $scope.listDetLivraisonVentePRBS[index].remise;
						$log.debug("-- listDetLivraisonVente After Remise Change : " + JSON.stringify($scope.listDetLivraisonVentePRBS, null, '  '));

					} else {

						//remiseChangedOnModification
						$log.debug("remiseChangedOnModification INDEX Changed " + index);
						$log.debug("listDetLivraisonVentePRBS Remise : " + $scope.listDetLivraisonVentePRBS[index].remise);

						$log.debug("listDetLivraisonVentePRBS Remise : " + $scope.listDetLivraisonVentePRBS[index].remise);

						if ($scope.listDetLivraisonVentePRBS[index].id != null) {
							$log.debug("--old--");
							$scope.bonLivraisonVenteCourant.listDetLivraisonVente[index].remise = $scope.listDetLivraisonVentePRBS[index];

						} else {
							$log.debug("--NEW--");
							$scope.bonLivraisonVenteCourant.listDetLivraisonVente.push($scope.listDetLivraisonVentePRBS[index]);

						}

						// $scope.bonLivraisonVenteCourant.listDetLivraisonVente[index].remise = $scope.listDetLivraisonVentePRBS[index].remise;
						$log.debug("-- bonLivraisonVenteCourant listDetLivraisonVente After Remise Change : " + JSON.stringify($scope.bonLivraisonVenteCourant.listDetLivraisonVente, null, '  '));
					}

				}





				/***************************************************
				 * Gestion BonLivraison -Vente
				 **************************************************/
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13, 39, 52, 156, 468],
					pageSize: 156,
					currentPage: 1
				};

				//Recherche des Bons de Vente
				$scope.rechercherBonLivraisonVente = function(bonLivraisonVenteCourant) {


					if ($scope.clientActif.blackMode == false) {
						bonLivraisonVenteCourant.declare = "oui";

					}


					bonLivraisonVenteCourant.natureLivraison = "FINI";
					$log.debug("----Livraison--*** --bonLivraisonVenteCourant : recherche avant---" + JSON.stringify(bonLivraisonVenteCourant, null, " "));
					$http
						.post(UrlAtelier +
							"/bonlivraison/rechercheMulticritere",
							bonLivraisonVenteCourant)
						.success(
							function(resultat) {
								$log.debug("----Livraison : request : bonLivraisonVenteCourant : recherche result---" + JSON.stringify($scope.bonLivraisonVenteCourant, null, " "));
								$scope.myData = resultat.list;
								// Pagination du resultat de recherche
								// data, page, pageSize
								$scope.setPagingData(
									$scope.myData,
									$scope.pagingOptions.currentPage,
									$scope.pagingOptions.pageSize);

								$log.debug("listeBonVente : " + resultat.list.length);
								$log.debug("----resultat.list-----: recherche result---" + JSON.stringify(resultat.list, null, " "));


								$scope.rechercheBLVenteForm.$setPristine();

							});

				}

				// Annuler Recherche
				$scope.annulerAjout = function() {


					$scope.produitIdFinancier = "";
					$scope.produitIdNonFinancier = "";

					$scope.traitementEnCours = "false";

					//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
					$scope.checkboxModel = {
						rapportPrix: "oui"
					};

					$scope.idBonLivVente = '';
					$log.debug("Annuler :$scope.idBonLivVente " + $scope.idBonLivVente);
					//bouton Valider en mode : notActive
					$scope.modeValider = "notActif";
					//bouton PDF en mode : notActive
					$scope.modePdf = "notActive";
					//vider la liste des refBS 
					$scope.tagReferenceBSList = [];
					$scope.listTaxeLivraison = [];
					$scope.listDetLivraisonVente = [];
					$scope.listDetLivraisonVentePRBS = [];
					//Tableau Prédefini 
					/*$scope.listTaxeLivraisonInit = [
													{//FODEC
														taxeId: 1,
														pourcentage: 1,
														montant: '',
													},
													{//TVA
														taxeId: 2,
														pourcentage: 18,
														montant: '',
													}];*/
					//initialiser le design des champs
					$scope.creationBLVenteForm.$setPristine();
					$scope.rechercheBLVenteForm.$setPristine();
					//init de l'objet courant  
					//				 $scope.bonLivraisonVenteCourant={};
					$scope.bonLivraisonVenteCourant = {
						"referenceBl": '',
						"referenceBs": '',
						"partieIntId": '',
						"dateLivraisonMin": '',
						"dateLivraisonMax": '',
						"metrageMin": '',
						"metrageMax": '',
						"prixMin": '',
						"prixMax": '',
						"natureLivraison": "FINI",
						"avecFacture": '',
						"devise": '',
						"declare": ''

					};


					$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

					//interface en mode : list
					$scope.displayMode = "list";
				}



				$scope.annulerAjoutRapide = function() {

					$scope.displayMode = "list";
				}

				// AffectationBLVente BonLivVente
				$scope.affectationBLVente = function(bonLVente) {

					$scope.produitIdFinancier = "";
					$scope.produitIdNonFinancier = "";

					$scope.natureLivraison = "FINI";
					$scope.listTaxeLivraisonInitMethod();
					$scope.initTaxeRemoved();
					// $scope.bonLivraisonVenteCourant = {};

					var defaultIdDepot = 1;
					if ($scope.listeMagazinCache && $scope.listeMagazinCache.length > 0)
						defaultIdDepot = $scope.listeMagazinCache[0].id;


					$scope.bonLivraisonVenteCourant = {
						"date": new Date(),
						"idDepot": defaultIdDepot,
						"modepaiementId": 1,
						"declare": true,
						"devise": "2",
						"stock": false
					};



					$scope.getCurrentReferenceByType($scope.bonLivraisonVenteCourant.declare);


					/*	 $scope.bonLivraisonVenteCourant = bonLVente ? angular
								 .copy(bonLVente) : {};*/

					//mode edit activé
					$scope.displayMode = "edit";
				}








				$scope.getCurrentReferenceByType = function(declarer) {

					var type = "";

					if (declarer == true) {

						type = 'declarer';



						$scope.listTaxeLivraisonInit = [
							{//FODEC
								taxeId: 1,
								pourcentage: 1,
								montant: '',
							},
							{//TVA
								taxeId: 2,
								pourcentage: 19,
								montant: '',
							}];


					}

					else {

						type = 'non-declarer';

						$scope.listTaxeLivraisonInit = [
							{//FODEC
								taxeId: 1,
								pourcentage: 1,
								montant: '',
							},
							{//TVA
								taxeId: 2,
								pourcentage: 10,
								montant: '',
							}];


					}



					$http
						.get(
							UrlAtelier
							+ "/bonlivraison/getCurrentReferenceByType:" + type
						)
						.success(
							function(res) {
								$scope.bonLivraisonVenteCourant.reference = res;


								if ($scope.modePdf != 'actif'){
									
								//creation

								$scope.bonLivraisonVenteCourant.refAvantChangement = res;

							   }	
											

							}
						);
					
					}


// AffectationBLFaconVente BonLivVente
$scope.affectationBLFaconVente = function(bonLVente) {
	//				 $scope.natureLivraison ="FACON";
	$scope.listTaxeLivraisonInitMethod();
	$scope.initTaxeRemoved();
	$scope.bonLivraisonVenteCourant = {};
	$scope.bonLivraisonVenteCourant = bonLVente ? angular
		.copy(bonLVente) : {};

	//mode edit activé
	$scope.displayMode = "edit";

}


// Ajout et Modification Bon de Vente
$scope.modifierOuCreerBonLVente = function() {

	$log.debug("modeConsultation/Modification. ");
	//index de la ligne selectionnée dans la Grid.
	var index = this.row.rowIndex;

	//idBonLivVente: va etre affecté à l'Url du service Valider en cas de modification 
	$scope.idBonLivVente = $scope.myData[index].id;
	$log.debug("idBonLivVente " + $scope.idBonLivVente);

	// bouton PDF activé
	$scope.modePdf = "actif";

	//nature livraison
	$scope.natureLivraison = $scope.myData[index].natureLivraison;

	// getBonVente
	$http
		.get(
			UrlAtelier
			+ "/bonlivraison/getBonLivraisonById:" + $scope.myData[index].id)
		.success(
			function(datagetBonVente) {

				$log.debug("-- datagetBonVente--- " + JSON.stringify(datagetBonVente, null, " "));

				//init du champ tagReferenceBSList, 
				$log.debug("-- infoSortie " + datagetBonVente.infoSortie);
				$log.debug("-- infoSortie Length " + datagetBonVente.infoSortie.length);
				//recuperation des refBS sous le format X-Y-Z
				var refBS = datagetBonVente.infoSortie.split("-");
				//affectation des references à la liste sous le format X,Y,Z

				$scope.tagReferenceBSList = refBS;


				$scope.listDetLivraisonVentePRBS = [];


				$scope.listDetLivraisonVentePRBS = datagetBonVente.listDetLivraisonVente;

				///bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
				$scope.modeValider = "actif";
				if ($scope.natureLivraison == "FINI") {



					/* BonLivraisonServices.validateFini($scope.tagReferenceBSList,datagetBonVente.id).then(function(resultat){
							
						 //listDetLivraisonVente
						 $scope.listDetLivraisonVentePRBS = resultat.listDetLivraisonVente;
						 
						 $log.debug("-%%%- listDetLivraisonVentePRBS : "+JSON.stringify($scope.listDetLivraisonVentePRBS, null, "    ") );
						 $scope.myData[index].listDetLivraisonVente = $scope.listDetLivraisonVentePRBS;
						 }
						 ,function(error){
							 console.log(error.statusText);
						 });*/
				}
				// Pour type façon pas besoin d'appeler validerFacon. Pas de nécessité de calcul
				// Les champs de calcul (prix) sont introduit dans detailLivraison
				else {
					angular.forEach(datagetBonVente.listDetLivraisonVente, function(elementDetLivraison, value) {
						$log.debug("---elementDetLivraison-- : " + JSON.stringify(elementDetLivraison, null, "    "));
						var ligneTraitement = [];
						//Filter retourne un résultat de type []						 
						ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetLivraison.traitementFaconId });
						elementDetLivraison.designationTraitement = ligneTraitement[0].designation;
						$scope.listDetLivraisonVentePRBS.push(elementDetLivraison);
					})

					$log.debug("-%%%- listDetLivraisonVentePRBS : " + JSON.stringify($scope.listDetLivraisonVentePRBS, null, "    "));
				}

				$scope.listTaxeLivraison = datagetBonVente.listTaxeLivraison;
				//affectation du resultat à myData
				$scope.myData[index].listDetLivraisonVente = $scope.listDetLivraisonVentePRBS;
				$scope.myData[index].listTaxeLivraison = $scope.listTaxeLivraison;

				//Initialiser le filtre des taxe à éliminer
				$scope.taxeIdRemove = [];
				for (var int = 0; int < $scope.listTaxeLivraison.length; int++) {
					$scope.taxeIdRemove.push($scope.listTaxeLivraison[int].taxeId);
				}
				$scope.filterTaxes();

				$log.debug("getBLId : " + $scope.myData[index].id + " : " + JSON.stringify($scope.myData[index], null, "  "));
			});




	$scope.bonLivraisonVenteCourant = $scope.myData[index] ? angular
		.copy($scope.myData[index])
		: {};


	// mode edit activé	
	$scope.displayMode = "edit";

}

// Sauvegarder bon de Vente
$scope.sauvegarderBonVente = function(bonVente) {

	if (angular.isDefined(bonVente.id)) {
		$log.debug("Sauvegarder Modification : " + bonVente.reference);
		$scope.updateBonVente(bonVente);
	} else {
		$log.debug("Sauvegarder Ajout : " + bonVente.reference);
		$scope.creerBonVente(bonVente);
	}


}

// Mise à jour des Bons de Vente
$scope.updateBonVente = function(bonLVente) {

	$scope.traitementEnCours = "true";


	//Liste des TaxeLivraisonVente (pour la table Taxe) & detailsLivraisonVente ( pour la table Produit ) correspendants à ce bon de vente
	$log.debug("-- tagReferenceBSList " + JSON.stringify($scope.tagReferenceBSList));

	var urlValider = UrlAtelier + "/bonlivraison/validate?livraisonVenteId=" + bonLVente.id;
	$log.debug("--------URL " + urlValider);

	/*	 $http
		 .post(urlValider,$scope.tagReferenceBSList)
		 .success(
				 function(resultat) {
					 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
					 $scope.modeValider = "actif";
					 //listDetLivraisonVente
					 $scope.listDetLivraisonVentePRBS = resultat.listDetLivraisonVente;
					 $scope.traitementEnCours = "false";
				 });
			*/
	$log.debug("Update------ listDetLivraisonVentePRBS :");
	$log.debug(JSON.stringify($scope.listDetLivraisonVentePRBS, null, "    "));


	$log.debug("======== AVANT :");
	$log.debug("bonLVente.listDetLivraisonVente : " + JSON.stringify(bonLVente.listDetLivraisonVente, null, "    "));

	bonLVente.listDetLivraisonVente = $scope.listDetLivraisonVentePRBS;

	$log.debug("======== APRES :");
	$log.debug("listDetLivraisonVentePRBS : " + JSON.stringify(bonLVente.listDetLivraisonVente, null, "    "));


	bonLVente.listTaxeLivraison = $scope.listTaxeLivraison;

	//tagReferenceBSList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : infoSortie sous la forme de ref1-ref2-.. 
	$log.debug("Join  " + $scope.tagReferenceBSList.join('-'));
	bonLVente.infoSortie = $scope.tagReferenceBSList.join('-');

	$log.debug("Modification bonLVente : " + JSON.stringify(bonLVente, null, "  "));

	$scope.traitementEnCours = "true";

	$http.post(UrlAtelier + "/bonlivraison/updateBonLivraison", bonLVente)
		.success(
			function(bonLVenteId) {



				/** Debut contraintes modification **/
				if (bonLVenteId == CODE_ERROR_EDITION_BL_HAS_FACTURE) {
					$scope.msgErreurUpdateBL = "Une facture existe avec cette BL";
					$scope.erreurUpdateBL = "true";
					$scope.traitementEnCours = "false";

					return;
				}

				if (bonLVenteId == CODE_ERROR_EDITION_BL_HAS_BON_RETOUR) {
					$scope.msgErreurUpdateBL = "Un Bon de retour exist avec cette BL";
					$scope.erreurUpdateBL = "true";
					$scope.traitementEnCours = "false";
					return;
				}





				for (var i = 0; i < $scope.myData.length; i++) {

					if ($scope.myData[i].id == bonLVenteId) {

						$scope.myData[i] = bonLVenteId;
						break;
					}
				}


				//getBonLivVente 
				$http.get(UrlAtelier + "/bonlivraison/getBonLivraisonById:" + bonLVenteId)
					.success(
						function(dataGetBonLivVente) {

							// bouton PDF activé
							$scope.modePdf = "actif";
							$scope.modeValider = "actif";

							//getTableaux
							$scope.listTaxeLivraison = dataGetBonLivVente.listTaxeLivraison;
							$scope.listDetLivraisonVente = dataGetBonLivVente.listDetLivraisonVente;
							// Attributs de Recherche
							$scope.bonLivraisonVenteCourant = dataGetBonLivVente ? angular
								.copy(dataGetBonLivVente)
								: {};
							$log.debug("get bonLVente : " + JSON.stringify($scope.bonLivraisonVenteCourant, null, "  "));


							if ($scope.natureLivraison == "FACON") {
								$scope.listDetLivraisonVentePRBS = [];
								angular.forEach($scope.bonLivraisonVenteCourant.listDetLivraisonVente, function(elementDetLivraison, value) {

									var ligneTraitement = [];
									//Filter retourne un résultat de type []						 
									ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetLivraison.traitementFaconId });
									elementDetLivraison.designationTraitement = ligneTraitement[0].designation;
									$scope.listDetLivraisonVentePRBS.push(elementDetLivraison);
								})
							}

							//Initialiser le filtre des taxe à éliminer
							$scope.taxeIdRemove = [];
							for (var int = 0; int < $scope.listTaxeLivraison.length; int++) {
								$scope.taxeIdRemove.push($scope.listTaxeLivraison[int].taxeId);
							}
							$scope.filterTaxes();
							$scope.traitementEnCours = "false";
						});
			});

}

// Création BonVente
$scope.creerBonVente = function(bonLVente) {

	$scope.traitementEnCours = "true";
	//affectation des listes à l'objet 'bonLVente' pour le creer
	$log.debug("-------- listDetLivraisonVentePRBS :");
	$log.debug(JSON.stringify($scope.listDetLivraisonVentePRBS, null, "    "));

	$log.debug("======== AVANT :");
	$log.debug("bonLVente.listDetLivraisonVente : " + JSON.stringify(bonLVente.listDetLivraisonVente, null, "    "));

	bonLVente.listDetLivraisonVente = $scope.listDetLivraisonVentePRBS;

	$log.debug("======== APRES :");
	$log.debug("listDetLivraisonVentePRBS : " + JSON.stringify(bonLVente.listDetLivraisonVente, null, "    "));

	bonLVente.listTaxeLivraison = $scope.listTaxeLivraisonInit;
	//tagReferenceBSList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : infoSortie sous la forme de ref1-ref2-.. 
	$log.debug("Join  " + $scope.tagReferenceBSList.join('-'));
	bonLVente.infoSortie = $scope.tagReferenceBSList.join('-');

	bonLVente.natureLivraison = $scope.natureLivraison;

	$log.debug("Creation bonLVente : " + JSON.stringify(bonLVente, null, "  "));

	$http.post(UrlAtelier + "/bonlivraison/createBonLivraison", bonLVente)
		.success(
			function(bonLVenteId) {
				
				
				$log.debug("Success Creation" + bonLVenteId);
				
				
				if(bonLVenteId == "CODE_ERROR_CREATION_BL_BS_EXSIT"){
					
							    alert("Bon de sortie existe déja !!");
                                  $scope.traitementEnCours = "false";

	                  return ;
				}
		

				//idBonLivVente : valider avec idBonLiv
				$scope.idBonLivVente = bonLVenteId;
				$log.debug("Valider idBonLivVente : " + $scope.idBonLivVente);

				//getBonLivVente 
				$http.get(UrlAtelier + "/bonlivraison/getBonLivraisonById:" + bonLVenteId)
					.success(
						function(dataGetBonLivVente) {

							//bouton Valider Off
							$scope.modeValider = "actif";
							// bouton PDF activé
							$scope.modePdf = "actif";

							//getTableaux
							$scope.listTaxeLivraison = dataGetBonLivVente.listTaxeLivraison;
							$scope.listDetLivraisonVente = dataGetBonLivVente.listDetLivraisonVente;
							// Attributs de Recherche
							$scope.bonLivraisonVenteCourant = dataGetBonLivVente ? angular
								.copy(dataGetBonLivVente)
								: {};
							$log.debug("get bonLVente : " + JSON.stringify($scope.bonLivraisonVenteCourant, null, "  "));

							$scope.listDetLivraisonVentePRBS = [];
							$log.debug("source : $scope.bonLivraisonVenteCourant.listDetLivraisonVente : " + JSON.stringify($scope.bonLivraisonVenteCourant.listDetLivraisonVente, null, "    "));

							if ($scope.natureLivraison == "FACON") {
								angular.forEach($scope.bonLivraisonVenteCourant.listDetLivraisonVente, function(elementDetLivraison, value) {
									var ligneTraitement = [];
									//Filter retourne un résultat de type []						 
									ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetLivraison.traitementFaconId });
									elementDetLivraison.designationTraitement = ligneTraitement[0].designation;

									$log.debug("elementDetLivraison : " + JSON.stringify(elementDetLivraison, null, "    "));
									$log.debug("en cours :listDetLivraisonVentePRBS apres remplissage designation : " + JSON.stringify($scope.listDetLivraisonVentePRBS, null, "    "));
									$scope.listDetLivraisonVentePRBS.push(elementDetLivraison);
								})
							} else {

								//init du champ tagReferenceBSList, 
								$log.debug("-- infoSortie " + bonLVente.infoSortie);
								//$log.debug("-- infoSortie Length " + datagetBonVente.infoSortie.length);
								//recuperation des refBS sous le format X-Y-Z
								var refBS = bonLVente.infoSortie.split("-");
								//affectation des references à la liste sous le format X,Y,Z
								$scope.tagReferenceBSList = refBS;

								//Liste des TaxeLivraisonVente (pour la table Taxe) & detailsLivraisonVente ( pour la table Produit ) correspendants à ce bon de vente
								var urlValider = UrlAtelier + "/bonlivraison/validate?livraisonVenteId=" + bonLVenteId;
								$log.debug("--------URL " + urlValider);
								$log.debug("--------contenu----- " + JSON.stringify($scope.tagReferenceBSList, null, " "));
								/* $http
								 .post(urlValider,$scope.tagReferenceBSList)
								 .success(
										 function(resultat) {
											 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
											 $scope.modeValider = "actif";
											 //listDetLivraisonVente
											 $scope.listDetLivraisonVentePRBS = resultat.listDetLivraisonVente;
										 });
									*/
								$log.debug("OLD------ listDetLivraisonVentePRBS :");
								$log.debug(JSON.stringify($scope.listDetLivraisonVentePRBS, null, "    "));
							}


							$scope.listDetLivraisonVentePRBS = dataGetBonLivVente.listDetLivraisonVente;

							//Initialiser le filtre des taxe à éliminer
							$scope.taxeIdRemove = [];
							for (var int = 0; int < $scope.listTaxeLivraison.length; int++) {
								$scope.taxeIdRemove.push($scope.listTaxeLivraison[int].taxeId);
							}
							$scope.filterTaxes();
						});

				$scope.traitementEnCours = "false";

			});

}

// Suppression BonVente
$scope.supprimerBonVente = function() {
	$log.debug("deleting ..");
	//TODO: Service de suppression: à revoir. erreur: operation executée mais avec msg  403!
	// var index = this.row.rowIndex;

	BonLivraisonServices.supprimerLivraison($scope.myData[$scope.index].id).then(function(resultat) {
		$log.debug("Success Delete");
		$scope.myData.splice($scope.index, 1);
		$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	}
		, function(error) {
			console.log(error.statusText);
			//TODO: Temp => jusqu'à résoudre le probleme de 403
			$scope.myData.splice($scope.index, 1);
			$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
		});

	$scope.closePopupDelete();
}

/*** PDF ***/
//generer rapport apres creation d'un bon de Livraison. mode : Modification/Consultation 
$scope.download = function(id, pRapportPrix, typerapport) {
	//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
	/* 	 $scope.checkboxModel= {
		  rapportPrix : "oui"
		}; */

	$log.debug("-- id" + id + pRapportPrix);
	var url = UrlAtelier + "/reportgc/bonlivraison?id=" + id
		+ "&avecPrix=" + pRapportPrix
		+ "&typerapport=" + typerapport
		+ "&type=pdf";


	var a = document.createElement('a');
	document.body.appendChild(a);
	downloadService.download(url).then(function(result) {
		var heasersFileName = result.headers(['content-disposition']).substring(17);
		var fileName = heasersFileName.split('.');
		var typeFile = result.headers(['content-type']);
		var file = new Blob([result.data], { type: typeFile });
		var fileURL = window.URL.createObjectURL(file);
		if (typeFile == 'application/vnd.ms-excel') {

			// a.href = fileURL;
			a.download = fileName[0];
			$window.open(fileURL)
			a.click();

		} else {

			a.href = fileURL;
			a.download = fileName[0];
			$window.open(fileURL)
			a.click();

		}

		$scope.traitementEnCoursGenererLivraison = "false";

	});
};


$scope.downloadFacture = function(id) {
	//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
	$scope.checkboxModel = {
		rapportPrix: "oui"
	};

	//	 $log.debug("-- id" + id + pRapportPrix);
	var url = UrlAtelier + "/fiches/facture?id=" + id;

	downloadService.download(url).then(
		function(success) {
			$log.debug('success : ' + success);
			//$scope.annulerAjout();
		}, function(error) {
			$log.debug('error : ' + error);
		});
};


$scope.downloadBL = function(id) {
	//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
	$scope.checkboxModel = {
		rapportPrix: "oui"
	};

	//	 $log.debug("-- id" + id + pRapportPrix);
	var url = UrlAtelier + "/fiches/bonlivraisonExc?refBonSortieList=" + $scope.tagReferenceBSList
		+ "&idBonLivVente=" + $scope.idBonLivVente;



	downloadService.download(url).then(
		function(success) {
			$log.debug('success : ' + success);
			//$scope.annulerAjout();
		}, function(error) {
			$log.debug('error : ' + error);
		});
};


$scope.downloadBLWithoutOF = function(id) {
	//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
	$scope.checkboxModel = {
		rapportPrix: "oui"
	};

	//	 $log.debug("-- id" + id + pRapportPrix);
	var url = UrlAtelier + "/fiches/bonlivraisonExc-without-of?refBonSortieList=" + $scope.tagReferenceBSList
		+ "&idBonLivVente=" + $scope.idBonLivVente;



	downloadService.download(url).then(
		function(success) {
			$log.debug('success : ' + success);
			//$scope.annulerAjout();
		}, function(error) {
			$log.debug('error : ' + error);
		});
};


$scope.downloadBLbyArticle = function(id) {
	//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
	$scope.checkboxModel = {
		rapportPrix: "oui"
	};

	//	 $log.debug("-- id" + id + pRapportPrix);
	var url = UrlAtelier + "/fiches/bonlivraisonExc-by-article?refBonSortieList=" + $scope.tagReferenceBSList
		+ "&idBonLivVente=" + $scope.idBonLivVente;



	downloadService.download(url).then(
		function(success) {
			$log.debug('success : ' + success);
			//$scope.annulerAjout();
		}, function(error) {
			$log.debug('error : ' + error);
		});
};

$scope.downloadBLbyArticleThermo = function(id) {
	//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
	$scope.checkboxModel = {
		rapportPrix: "oui"
	};

	//	 $log.debug("-- id" + id + pRapportPrix);
	var url = UrlAtelier + "/fiches/bonlivraisonExc-by-article-th?refBonSortieList=" + $scope.tagReferenceBSList
		+ "&idBonLivVente=" + $scope.idBonLivVente;



	downloadService.download(url).then(
		function(success) {
			$log.debug('success : ' + success);
			//$scope.annulerAjout();
		}, function(error) {
			$log.debug('error : ' + error);
		});
};

//generer rapport de tous les bons de livraison. mode : List 

//conversion date en String
function formattedDate(date) {
	var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;
	return [year, month, day].join('-');
}

//download service

$scope.downloadAllBonLiv = function(
	bonLivraisonVenteCourant) {
	$scope.traitementEnCoursGenererAll = "true";

	bonLivraisonVenteCourant.referenceBs = '';
	/*if ($scope.checkboxModelRech.stock == "oui") {
		bonLivraisonVenteCourant.stock = true;
	} else
		bonLivraisonVenteCourant.stock = false

*/
	//console.log("etat stock generer: " + bonLivraisonVenteCourant.stock);
	console
		.log("---Objet recherche : bonLivraisonVenteCourant----"
			+ JSON
				.stringify(
					bonLivraisonVenteCourant,
					null, " "));
	var newdateLivMinFormat = "";
	if (angular
		.isDefined(bonLivraisonVenteCourant.dateLivraisonMin)) {
		$log
			.debug("==dateLivraisonMin "
				+ bonLivraisonVenteCourant.dateLivraisonMin);

		if (bonLivraisonVenteCourant.dateLivraisonMin != "") {
			newdateLivMinFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMin);
			$log.debug("===== newdateLivMinFormat "
				+ newdateLivMinFormat);
		} else {
			$log
				.debug("===== newdateLivMinFormat is Null");
			newdateLivMinFormat = "";
		}
	} else {
		$log.debug("==dateLivraisonMin Undefined");
	}

	var newdateLivMaxFormat = "";
	if (angular
		.isDefined(bonLivraisonVenteCourant.dateLivraisonMax)) {
		$log
			.debug("==dateLivraisonMax "
				+ bonLivraisonVenteCourant.dateLivraisonMax);

		if (bonLivraisonVenteCourant.dateLivraisonMax != "") {
			newdateLivMaxFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMax);
			$log.debug("===== newdateLivMaxFormat "
				+ newdateLivMaxFormat);
		} else {
			$log
				.debug("===== newdateLivMaxFormat is Null");
			newdateLivMaxFormat = "";
		}
	} else {
		$log.debug("==dateLivraisonMax Undefined");
	}

	$log.debug("-- bonLivraisonVenteCourant"
		+ JSON.stringify(
			bonLivraisonVenteCourant, null,
			"  "));

	var newIdDepot = '';
	if (angular.isDefined(bonLivraisonVenteCourant.idDepot)) {
		newIdDepot = bonLivraisonVenteCourant.idDepot;

	}


	var newPartieIntId = '';
	if (angular.isDefined(bonLivraisonVenteCourant.partieIntId)) {
		newPartieIntId = bonLivraisonVenteCourant.partieIntId;

	}


	// console.log("===============>download:
	// idDepot:"+bonLivraisonVenteCourant.idDepot);
	var url = UrlAtelier
		+ "/reportgc/listbonlivraison?referenceBl="
		+ bonLivraisonVenteCourant.referenceBl
		+ "&referenceBs="
		+ bonLivraisonVenteCourant.referenceBs
		+ "&partieIntId="
		+ newPartieIntId
		+ "&dateLivraisonMin="
		+ newdateLivMinFormat
		+ "&dateLivraisonMax="
		+ newdateLivMaxFormat
		+ "&metrageMin="
		+ bonLivraisonVenteCourant.metrageMin
		+ "&metrageMax="
		+ bonLivraisonVenteCourant.metrageMax
		+ "&prixMin="
		+ bonLivraisonVenteCourant.prixMin
		+ "&prixMax="
		+ bonLivraisonVenteCourant.prixMax
		+ "&natureLivraison="
		+ bonLivraisonVenteCourant.natureLivraison
		+ "&avecFacture="
		+ bonLivraisonVenteCourant.avecFacture
		+ "&stock=" + ""
		+ "&idDepot=" + newIdDepot
		+ "&groupeClientId=" + bonLivraisonVenteCourant.groupeClientId
		+ "&declare=" + bonLivraisonVenteCourant.declare
		+ "&type=pdf";

	$log.debug("--downloadAllBonLiv URL" + url);



	var a = document.createElement('a');
	document.body.appendChild(a);
	downloadService.download(url).then(function(result) {
		var heasersFileName = result.headers(['content-disposition']).substring(17);
		var fileName = heasersFileName.split('.');
		var typeFile = result.headers(['content-type']);
		var file = new Blob([result.data], { type: typeFile });
		var fileURL = window.URL.createObjectURL(file);
		if (typeFile == 'application/vnd.ms-excel') {

			// a.href = fileURL;
			a.download = fileName[0];
			$window.open(fileURL)
			a.click();

		} else {

			a.href = fileURL;
			a.download = fileName[0];
			$window.open(fileURL)
			a.click();

		}
		$scope.traitementEnCoursGenererAll = "false";


	});

	// downloadService.download(url).then(
	// 		function(success) {
	// 			$log.debug('success : ' + success);
	// 		}, function(error) {
	// 			$log.debug('error : ' + error);
	// 		});
};












$scope.downloadAllBonLivExcel = function(
	bonLivraisonVenteCourant) {
	//	$scope.traitementEnCoursGenererAll = "true";

	//	bonLivraisonVenteCourant.referenceBs='';
	/* 		if($scope.checkboxModelRech.stock=="oui"){
				bonLivraisonVenteCourant.stock=true;
			}else
				bonLivraisonVenteCourant.stock=false */



	var newdateLivMinFormat = "";
	if (angular
		.isDefined(bonLivraisonVenteCourant.dateLivraisonMin)) {
		$log
			.debug("==dateLivraisonMin "
				+ bonLivraisonVenteCourant.dateLivraisonMin);

		if (bonLivraisonVenteCourant.dateLivraisonMin != "") {
			newdateLivMinFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMin);
			$log.debug("===== newdateLivMinFormat "
				+ newdateLivMinFormat);
		} else {
			$log
				.debug("===== newdateLivMinFormat is Null");
			newdateLivMinFormat = "";
		}
	} else {
		$log.debug("==dateLivraisonMin Undefined");
	}

	var newdateLivMaxFormat = "";
	if (angular
		.isDefined(bonLivraisonVenteCourant.dateLivraisonMax)) {
		$log
			.debug("==dateLivraisonMax "
				+ bonLivraisonVenteCourant.dateLivraisonMax);

		if (bonLivraisonVenteCourant.dateLivraisonMax != "") {
			newdateLivMaxFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMax);
			$log.debug("===== newdateLivMaxFormat "
				+ newdateLivMaxFormat);
		} else {
			$log
				.debug("===== newdateLivMaxFormat is Null");
			newdateLivMaxFormat = "";
		}
	} else {
		$log.debug("==dateLivraisonMax Undefined");
	}

	$log.debug("-- bonLivraisonVenteCourant"
		+ JSON.stringify(
			bonLivraisonVenteCourant, null,
			"  "));

	/* 	var newIdDepot = '';
		if(angular.isDefined(bonLivraisonVenteCourant.idDepot)){
			newIdDepot = bonLivraisonVenteCourant.idDepot;
			
		}
		 */

	var newPartieIntId = '';
	if (angular.isDefined(bonLivraisonVenteCourant.partieIntId)) {
		newPartieIntId = bonLivraisonVenteCourant.partieIntId;

	}

	if (typeof $scope.bonLivraisonVenteCourant.devise === 'undefined' || $scope.bonLivraisonVenteCourant.devise == null) {
		var newDevise = "";
	}
	else {
		var newDevise = $scope.bonLivraisonVenteCourant.devise;
	}

	var url = UrlAtelier
		+ "/fiches/listbonlivraison?referenceBl="
		+ bonLivraisonVenteCourant.referenceBl
		+ "&referenceBs="
		+ bonLivraisonVenteCourant.referenceBs
		+ "&partieIntId="
		+ newPartieIntId
		+ "&dateLivraisonMin="
		+ newdateLivMinFormat
		+ "&dateLivraisonMax="
		+ newdateLivMaxFormat
		+ "&metrageMin="
		+ bonLivraisonVenteCourant.metrageMin
		+ "&metrageMax="
		+ bonLivraisonVenteCourant.metrageMax
		+ "&prixMin="
		+ bonLivraisonVenteCourant.prixMin
		+ "&prixMax="
		+ bonLivraisonVenteCourant.prixMax
		//+ "&natureLivraison="+ bonLivraisonVenteCourant.natureLivraison
		+ "&avecFacture="+ bonLivraisonVenteCourant.avecFacture
        + "&devise=" + newDevise
        + "&declare=" + bonLivraisonVenteCourant.declare
		+ "&type=pdf";

	$log.debug("--downloadAllBonLiv URL" + url);


	var a = document.createElement('a');
	document.body.appendChild(a);
	downloadService.download(url).then(function(result) {
		var heasersFileName = result.headers(['content-disposition']).substring(17);
		var fileName = heasersFileName.split('.');

		fileName[0] = 'Liste_des_Bons_de_Livraisons_' + formattedDate(new Date());

		var typeFile = result.headers(['content-type']);
		var file = new Blob([result.data], { type: typeFile });
		var fileURL = window.URL.createObjectURL(file);
		if (typeFile == 'application/vnd.ms-excel') {

			a.href = fileURL;
			a.download = fileName[0];
			//$window.open(fileURL)
			a.click();

		} else {

			a.href = fileURL;
			a.download = fileName[0];
			$window.open(fileURL)
			a.click();

		}
		//$scope.traitementEnCoursGenererAll = "false";


	});


};


$scope.downloadAllBonLivExcelDevise = function(
	bonLivraisonVenteCourant) {
	//	$scope.traitementEnCoursGenererAll = "true";

	//	bonLivraisonVenteCourant.referenceBs='';
	/* 		if($scope.checkboxModelRech.stock=="oui"){
				bonLivraisonVenteCourant.stock=true;
			}else
				bonLivraisonVenteCourant.stock=false */



	var newdateLivMinFormat = "";
	if (angular
		.isDefined(bonLivraisonVenteCourant.dateLivraisonMin)) {
		$log
			.debug("==dateLivraisonMin "
				+ bonLivraisonVenteCourant.dateLivraisonMin);

		if (bonLivraisonVenteCourant.dateLivraisonMin != "") {
			newdateLivMinFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMin);
			$log.debug("===== newdateLivMinFormat "
				+ newdateLivMinFormat);
		} else {
			$log
				.debug("===== newdateLivMinFormat is Null");
			newdateLivMinFormat = "";
		}
	} else {
		$log.debug("==dateLivraisonMin Undefined");
	}

	var newdateLivMaxFormat = "";
	if (angular
		.isDefined(bonLivraisonVenteCourant.dateLivraisonMax)) {
		$log
			.debug("==dateLivraisonMax "
				+ bonLivraisonVenteCourant.dateLivraisonMax);

		if (bonLivraisonVenteCourant.dateLivraisonMax != "") {
			newdateLivMaxFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMax);
			$log.debug("===== newdateLivMaxFormat "
				+ newdateLivMaxFormat);
		} else {
			$log
				.debug("===== newdateLivMaxFormat is Null");
			newdateLivMaxFormat = "";
		}
	} else {
		$log.debug("==dateLivraisonMax Undefined");
	}

	$log.debug("-- bonLivraisonVenteCourant"
		+ JSON.stringify(
			bonLivraisonVenteCourant, null,
			"  "));

	/* 	var newIdDepot = '';
		if(angular.isDefined(bonLivraisonVenteCourant.idDepot)){
			newIdDepot = bonLivraisonVenteCourant.idDepot;
			
		}
		 */

	var newPartieIntId = '';
	if (angular.isDefined(bonLivraisonVenteCourant.partieIntId)) {
		newPartieIntId = bonLivraisonVenteCourant.partieIntId;

	}


	if (typeof $scope.bonLivraisonVenteCourant.devise === 'undefined' || $scope.bonLivraisonVenteCourant.devise == null) {
		var newDevise = "";
	}
	else {
		var newDevise = $scope.bonLivraisonVenteCourant.devise;
	}

	var url = UrlAtelier
		+ "/fiches/listbonlivraisonDevise?referenceBl="
		+ bonLivraisonVenteCourant.referenceBl
		+ "&referenceBs="
		+ bonLivraisonVenteCourant.referenceBs
		+ "&partieIntId="
		+ newPartieIntId
		+ "&dateLivraisonMin="
		+ newdateLivMinFormat
		+ "&dateLivraisonMax="
		+ newdateLivMaxFormat
		+ "&metrageMin="
		+ bonLivraisonVenteCourant.metrageMin
		+ "&metrageMax="
		+ bonLivraisonVenteCourant.metrageMax
		+ "&prixMin="
		+ bonLivraisonVenteCourant.prixMin
		+ "&prixMax="
		+ bonLivraisonVenteCourant.prixMax
		//+ "&natureLivraison="+ bonLivraisonVenteCourant.natureLivraison
		+ "&avecFacture="
		+ bonLivraisonVenteCourant.avecFacture
		//+ "&stock="+""
		//+ "&idDepot="+newIdDepot
		//	+ "&groupeClientId="+""
		+ "&devise=" + newDevise
		
		+ "&declare=" + bonLivraisonVenteCourant.declare
		
		+ "&type=pdf";

	$log.debug("--downloadAllBonLiv URL" + url);


	var a = document.createElement('a');
	document.body.appendChild(a);
	downloadService.download(url).then(function(result) {
		var heasersFileName = result.headers(['content-disposition']).substring(17);
		var fileName = heasersFileName.split('.');

		fileName[0] = 'Liste_des_Bons_de_Livraisons_' + formattedDate(new Date());

		var typeFile = result.headers(['content-type']);
		var file = new Blob([result.data], { type: typeFile });
		var fileURL = window.URL.createObjectURL(file);
		if (typeFile == 'application/vnd.ms-excel') {

			a.href = fileURL;
			a.download = fileName[0];
			//$window.open(fileURL)
			a.click();

		} else {

			a.href = fileURL;
			a.download = fileName[0];
			$window.open(fileURL)
			a.click();

		}
		//$scope.traitementEnCoursGenererAll = "false";


	});


};






/***************************************************
 * Gestion des Grids de recherche
 **************************************************/
$scope.colDefs = [];
$scope
	.$watch(
		'myData',
		function() {
			$scope.colDefs = [

				{
					field: 'reference',
					displayName: 'Reference',
					width: '10%'
				},

				{
					field: 'referenceBlManuel',
					displayName: 'Ref. BL Manuel',
					width: '10%'
				},
				{
					field: 'partieIntAbreviation',
					displayName: 'Client',
					width: '30%'
				},
				{
					field: 'infoSortie',
					displayName: 'Ref Sortie .',
					width: '10%'
				},
				{
					field: 'date',
					displayName: 'Date livraison',
					cellFilter: "date: 'yyyy-MM-dd'",
					width: '10%'
				},
				{
					field: 'metrageTotal',
					displayName: 'Quantite Total',
					width: '10%'
				},
				{
					field: 'montantTTC',
					displayName: 'prix Total ',
					cellFilter: 'prixFiltre',
					width: '10%'
				},

				{
					field: '',
					//width:'10%',
					cellTemplate:
						`<div class="ms-CommandButton float-right"  ng-show="!rowform.$visible" >
											   <button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerBonLVente()">
										   <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
										   </button>
											   <button class="ms-CommandButton-button"  ng-click="showPopupDelete(5)" permission="['Production_Expedition_Delete']">
											   <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
											   </button>
											   </div>`,
					width: '7%',


				}];
		});

$scope.filterOptions = {
	filterText: "",
	useExternalFilter: true
};

$scope.totalServerItems = 0;

$scope.setPagingData = function(data, page,
	pageSize) {
	var pagedData = data.slice((page - 1)
		* pageSize, page * pageSize);
	$scope.myData = pagedData;
	$scope.totalServerItems = data.length;
	if (!$scope.$$phase) {
		$scope.$apply();
	}
};

$scope.getPagedDataAsync = function(pageSize, page,
	searchText) {

	setTimeout(
		function() {
			var data;
			var bonLivraisonVenteCourant = $scope.bonLivraisonVenteCourant;
			bonLivraisonVenteCourant.natureLivraison = "FINI";
			if ($scope.clientActif.blackMode == false) {
				bonLivraisonVenteCourant.declare = "oui";

			}
			if (searchText) {
				var ft = searchText.toLowerCase();
				$http
					.post(
						UrlAtelier
						+ "/bonlivraison/rechercheMulticritere", bonLivraisonVenteCourant)
					.success(
						function(
							largeLoad) {
							data = largeLoad.list
								.filter(function(item) {
									return JSON.stringify(item)
										.toLowerCase()
										.indexOf(ft) != -1;
								});
							$scope.setPagingData(data, page, pageSize);
						});

			} else {
				bonLivraisonVenteCourant.natureLivraison = "FINI";

				$http
					.post(
						UrlAtelier
						+ "/bonlivraison/rechercheMulticritere", bonLivraisonVenteCourant)
					.success(
						function(largeLoad) {
							$scope.setPagingData(
								largeLoad.list,
								page,
								pageSize);
						});
			}
		}, 100);
};

$scope.getPagedDataAsync(
	$scope.pagingOptions.pageSize,
	$scope.pagingOptions.currentPage);

$scope
	.$watch(
		'pagingOptions',
		function(newVal, oldVal) {
			if (newVal !== oldVal
				&& newVal.currentPage !== oldVal.currentPage) {
				$scope
					.getPagedDataAsync(
						$scope.pagingOptions.pageSize,
						$scope.pagingOptions.currentPage,
						$scope.filterOptions.filterText);
			}
		}, true);
$scope.$watch('filterOptions', function(newVal,
	oldVal) {
	if (newVal !== oldVal) {
		$scope.getPagedDataAsync(
			$scope.pagingOptions.pageSize,
			$scope.pagingOptions.currentPage,
			$scope.filterOptions.filterText);
	}
}, true);

$scope.gridOptions = {
	data: 'myData',
	columnDefs: 'colDefs',
	enablePaging: true,
	showFooter: true,
	enableHighlighting: true,
	enableColumnResize: true,
	totalServerItems: 'totalServerItems',
	pagingOptions: $scope.pagingOptions,
	selectedItems: $scope.selectedRows,
	filterOptions: $scope.filterOptions,
};
			 /** Fin de gestion des Grids de la BonVente */
			 
			 


		 } ]);

