angular
	.module('gpao.detailsFactureVente', ["ngResource"])
	.controller(
		'detailsFactureVenteController',
		[
			'$scope',
			'$filter',
			'$http',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'FactureServices',
			'traitementFaconServices', '$window',
			function ($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier, FactureServices, traitementFaconServices, $window) {

				const CODE_ERROR_DUPLICATE_BL_IN_FACTURE = "CODE_ERROR_DUPLICATE_BL_IN_FACTURE";

				$scope.refBLIsInvalid = "false";

				$log.info("=========Facture========");
				var data;
				$scope.displayMode = "list";
				// bouton pdf hide
				$scope.modePdf = "notActive";
				$scope.factureVenteCourant = { "type": "Normal" , "declarerecherche" :"oui" };
				// mode list activé
				$scope.displayMode = "list";
				$scope.listeBonSortie = [];
				$scope.listDetFactureVente = [];
				$scope.listDetFactureVentePRBL = [];
				$scope.idFactureVente = '';
				// liste des ReferencesBS
				$scope.tagReferenceBLivList = [];
				$scope.tagReferenceBLivListExterne = [];
				$scope.listTaxeFacture = [];
				$scope.listBLDetaille = [];
				// Tableau de Taxe Prédefini
				$scope.ListeDevise = [];

				$scope.listeProduitFinancier = [];
				$scope.listeProduitNonFinancier = [];

				$scope.isCollapsed = true;

				
				$scope.produitIdFinancier ="";
				$scope.produitIdNonFinancier ="";


				$scope.hiddenNotifSucc = "false";

				$scope.closeNotifS = function () {
					$scope.hiddenNotifSucc = "false";
				}
				
				
			

				

			

				// Liste des listGroupeClient
				$scope.listGroupeClient = function () {
					$http
						.get(
							UrlCommun
							+ "/groupeClient/all")
						.success(
							function (dataCategorieCache) {
								// $log.debug("listeCategorie :
								// "+dataCategorieCache.length);
								$scope.listGroupeClient = dataCategorieCache;

							});
				}
				$scope.listGroupeClient();


				/************************************  DEBUT REGLEMENT ****************************/
				$scope.isCollapsedDetailReglement = true;

				$scope.myStyleBtn = {

					"background-color": "yellow"

				};

				// Liste type reglement
				$scope.listTypes = function () {


					$http
						.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeTypeReglementCache")
						.success(
							function (dataProduit) {

								$scope.listTypes = dataProduit;
							});
				}
				$scope.listTypes();

				// Liste des Devises
				$scope.ListeDevise = function () {
					$http.get(UrlCommun + '/devise/all').success(function (dataDevise) {
						$scope.ListeDevise = dataDevise;
					});
				};



				$scope.ListeDevise();

				// Mise à jour des Reglements
				$scope.updateReglement = function (reglement) {

					reglement.listDocReglement = $scope.listeDocumentProduit;
					reglement.listDetailsReglement = $scope.finalOperationsList;
					reglement.listElementReglement = $scope.finalElementList;
					$scope.test = reglement.listElementReglement;

					console.log("update:magazin: " + reglement.idDepot);

					$http
						.put(
							UrlAtelier +
							"/reglement/update",
							reglement)
						.success(
							function (reglementModifiee) {


								// TODO Code à revoir
								/*    for (var i = 0; i < $scope.myData.length; i++) {

												if ($scope.myData[i].id == reglementModifiee) {
														$scope.myData[i] = reglementModifiee;
													  
														break;
												}
										}*/

								// $scope.annulerAjout();




							});

				}

				// Création Reglement
				$scope.creerReglement = function (reglement) {

					reglement.ajoutSpecial = "REGLEMENT_AJOUT_SPECIAL_FACTURE";

					reglement.listDocReglement = $scope.listeDocumentProduit;
					reglement.listDetailsReglement = $scope.finalOperationsList;

					var tmplistElementReglement = [];
					//Facture
					/*  angular.forEach($scope.finalFacturesList, function(elementFact, key){
							var tmp = {};

							tmp.id = null;
							tmp.montant = elementFact.montantFacture;
							tmp.refFacture = elementFact.numFacture;
							tmp.montantDemande = elementFact.montantRegle;
							tmp.dateEcheance = elementFact.date;
							tmp.reglementId = elementFact.reglementId;

							tmplistElementReglement.push(tmp);
						  
						});*/
					//BL

					/*  angular.forEach($scope.finalBLList, function(elementBl, key){
						var tmp = {};
					  
						tmp.id = null;
						tmp.montant = elementBl.montantBL;
						tmp.refBL = elementBl.numBL;
						tmp.montantDemande = elementBl.montantRegle;
						tmp.dateEcheance = elementBl.date;
						tmp.reglementId = elementBl.reglementId;

						tmplistElementReglement.push(tmp);
					});
			  
		  
				 reglement.listElementReglement  = tmplistElementReglement;
		 
			 */

					reglement.listElementReglement = $scope.finalBLList;


					$http
						.post(
							UrlAtelier +
							"/reglement/create",
							reglement)
						.success(
							function (newreglement) {

								// TODO getId

								$scope.reglementCourante.id = newreglement;
								//$scope.annulerAjout();


							});

				}




				$scope.AffectationReglement = function (fac) {




					$('.elementReglement').hide();
					$('.BL_FACT_Reglement').show();

					$scope.disableClient = false;
					$scope.disableValider = false;

					$scope.reglementCourante = {

						"date": new Date(),
						"partieIntId": fac.partieIntId,
						"idDepot": fac.idDepot,
						"clientPassager": fac.transporteur


					};


					$scope.finalBLList = [];


					var blObj = {
						"refFacture": fac.reference,
						"dateEcheance": fac.date,
						"montant": fac.montantTTC,
						"montantDemande": fac.montantTTC
					}

					$scope.finalBLList.push(blObj);


					//$scope.creationReglementForm.$setPristine();

					$scope.finalOperationsList = [];
					for (i = 1; i < 2; i++) {
						$scope.addElement(i);
					}



					//$scope.DisableMontantRegCol();

					//$scope.displayMode = "edit";

					//if($scope.clientActif.disableVente){
						
						var dateIntroReglement = new Date();

				$http
        	            		.get(
        	            				UrlAtelier
        	            						+ "/reglement/getCurrentReferenceByDate:"+formattedDate(dateIntroReglement)
        	            						)
        	            		.success(
        	            				function(res) {
        	            					
        	            					$scope.reglementCourante.reference = res;
        	            					$scope.reglementCourante.refAvantChangement = res;
        	            				});


					//}

				}

				// Sauvegarder Reglement
				$scope.sauvegarderAjoutReglement = function (reglement) {
					if (angular.isDefined(reglement.id)) {

						$scope.updateReglement(reglement);
					} else {
						$scope.creerReglement(reglement);

					}
				}
 



				//liste des Factures
				$scope.addFacturesElement = function (_ordre) {

					/*
														var tmpElement = {
																ordre: (_ordre === undefined) ? (parseInt($scope.finalFacturesList[$scope.finalFacturesList.length - 1].ordre) + 1) :
																		_ordre,
																code : '',
																designation : '',
																temps: 0,
																pdh: 0,
																sectionId : null,
																machineId : null,
																observations: '',
																operationId : null,
																disable: true,
																comptage : false,
																checked : false
														};
															 
														if ($scope.finalFacturesList
																.indexOf(tmpElement) == -1) {
																$scope.finalFacturesList.push(tmpElement);
				
																 var t = parseInt(_ordre)+1;
																_ordre =t;
															  
														}
													  
													  
														*/

				};


				// used to add new element into list of table
				$scope.addElement = function (_ordre) {

					var newOrdre = 1;

					if ($scope.finalOperationsList.length == 0)
						newOrdre = 1;
					else newOrdre = parseInt($scope.finalOperationsList[$scope.finalOperationsList.length - 1].ordre) + 1;

					/* 
									 var tmpElement = {
											 ordre: (_ordre === undefined) ? (parseInt($scope.finalOperationsList[$scope.finalOperationsList.length - 1].ordre) + 1) :
													 _ordre,
											 typeReglementId : '',    
											 numPiece :'',
											 banque: '',
											 montant: 0,
											 dateEmission : '',
											 dateEcheance : ''
									 };*/

					var tmpElement = {
						ordre: (_ordre === undefined) ? newOrdre :
							_ordre,
						typeReglementId: 2,
						numPiece: '',
						banque: '',
						montant: $scope.factureVenteCourant.montantTTC,
						dateEmission: new Date(),
						dateEcheance: ''
					};

					if ($scope.finalOperationsList
						.indexOf(tmpElement) == -1) {
						$scope.finalOperationsList.push(tmpElement);

						var t = parseInt(_ordre) + 1;
						_ordre = t;




					}

				};


				// Supprimer Detail
				$scope.removeDetailRegelement = function (index) {
					$scope.finalOperationsList.splice(index, 1);
				};

				$scope.InitializeArray = function () {

					//initialisation des arrays
					$scope.finalOperationsList = [];
					for (i = 1; i < 2; i++) {
						$scope.addElement(i);
					}
					$scope.finalBLList = [];
					$scope.finalFacturesList = [];

					$scope.selectedAllBL = [];
					for (i = 1; i < 11; i++) {
						//$scope.addElement(i);
						//$scope.addBLElement(i);
						$scope.addFacturesElement(i);
					}

				}

				$scope.InitializeArray();


				/**FIN******************************** FIN REGLEMENT ****************************/


				$scope.listTaxeFactureInitMethod = function () {
					
					
					$scope.listTaxeFactureInit = [

						
					/*	{// TVA7
							taxeId: 4,
							pourcentage: 7,
							montant: '',
						},
						{// TVA13
							taxeId: 5,
							pourcentage: 13,
							montant: '',
						},*/
						{// TIMBRE
							taxeId: 3,
							pourcentage: '',
							montant: 1,
						}

					];
					
			
					
			
     	       if($scope.clientActif.tva19 == true){
	
					let taxeTva19 = 	{// TVA
							taxeId: 2,
							pourcentage: 19,
							montant: '',
						};

     	            $scope.listTaxeFactureInit.unshift(taxeTva19);


				}
				
				
						if($scope.clientActif.fodec == true){
					
					let taxeFodec = 	{
						   //FODEC 
							taxeId: 1,
							pourcentage: 1,
							montant: '',
						} ;

                    	$scope.listTaxeFactureInit.unshift(taxeFodec);
						
					}
					
				}

				$scope.initTaxeRemoved = function () {
					if ($scope.natureLivraison == "FINI") {
						// $scope.taxeIdRemove = [1,2,3]; //FODEC + TVA +TIMBRE
						$scope.taxeIdRemove = [2, 3];
					} else {
						$scope.taxeIdRemove = [2, 3]; // TVA + TIMBRE
					}
				}

				$scope.validerNatureFini = function () {

					// $log.debug("Log1: idBonLiv = " + $scope.idFactureVente);


$scope.validerNatureFiniByOF();

					// if($scope.idFactureVente!=null)
				/*	FactureServices.validateFini($scope.tagReferenceBLivList, $scope.idFactureVente).then(function (resultat) {
						// bouton Valider en mode : Actif :afficher le tableau
						// resultant de DetLivVene
						$scope.modeValider = "actif";
						// setDateInto = dateSortie du 1erBS
						// $scope.factureVenteCourant.date = resultat.dateLivrison;
						$scope.factureVenteCourant.idMarche = resultat.idMarche;
						$scope.factureVenteCourant.idDepot = resultat.idDepot;

						// listDetFactureVente
						$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
						$log.debug("-- listDetFactureVentePRBL : " + JSON.stringify($scope.listDetFactureVentePRBL, null, "    "));

					}
						, function (error) {
							console.log(error.statusText);
						});*/
				}
				
					$scope.validerNatureFiniByOF = function () {

					// $log.debug("Log1: idBonLiv = " + $scope.idFactureVente);




					// if($scope.idFactureVente!=null)
					FactureServices.validateFiniByOF($scope.tagReferenceBLivList, $scope.idFactureVente).then(function (resultat) {
						// bouton Valider en mode : Actif :afficher le tableau
						// resultant de DetLivVene
						$scope.modeValider = "actif";
						// setDateInto = dateSortie du 1erBS
						// $scope.factureVenteCourant.date = resultat.dateLivrison;
						$scope.factureVenteCourant.idMarche = resultat.idMarche;
						$scope.factureVenteCourant.idDepot = resultat.idDepot;

						// listDetFactureVente
						$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
						$log.debug("-- listDetFactureVentePRBL : " + JSON.stringify($scope.listDetFactureVentePRBL, null, "    "));

					}
						, function (error) {
							console.log(error.statusText);
						});
				}

				if ($scope.navMode == "redirection") {
					// console.log("FROM FACTURE redirection");
					// console.log("RECIEVED BL",$scope.sendedBL);

					$scope.factureVenteCourant = { reference: $scope.referenceBL, date: $scope.dateBL };

					$scope.tagReferenceBLivList.push($scope.sendedBL.reference);

					$scope.natureLivraison = "FINI";
					$scope.listTaxeFactureInitMethod();
					$scope.initTaxeRemoved();

					$scope.affectedgroupeClientId = '';

					if ($scope.sendedBL.groupeClientId != null)
						$scope.affectedgroupeClientId = $scope.sendedBL.groupeClientId.toString();



					// console.log("AFFECTED
					// groupeClientId",$scope.affectedgroupeClientId);
					$scope.factureVenteCourant = {
						"date": $scope.sendedBL.date,
						"idDepot": $scope.sendedBL.idDepot,
						"modepaiementId": $scope.sendedBL.modepaiementId.toString(),
						"partieIntId": $scope.sendedBL.partieIntId.toString(),
						"typePartieInteressee": $scope.sendedBL.typePartieInteressee.toString(),
						"groupeClientId": $scope.affectedgroupeClientId
					};



					// console.log("AFFECTED
					// FACTURE",$scope.factureVenteCourant);


					$scope.validerNatureFini();


					$http.get(UrlAtelier + "/facture/getCurrentReferenceByTypeFacture:Normal")
						.success(
							function (res) {

								$scope.factureVenteCourant.reference = res;
								$scope.factureVenteCourant.refAvantChangement = res;
							});


					$scope.displayMode = "edit";

					// affectationBLVente();

				}
				else {



				}

				$scope.listTypePICache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeTypePICache")
						.success(
							function (dataTypeCache) {
								$log.debug("listeTypePICache : " + dataTypeCache.length);
								$scope.ListTypePICache = dataTypeCache;

							});
				}
				$scope.listTypePICache();


				// modeValider
				$scope.modeValider = "notActif";
				// init deleteValue pour cancelAddBLVente
				$scope.deleteValue = "oui";

				/***************************************************************
					* Gestion des DropListe & cache
					**************************************************************/
				$scope.listePartieInteresseeCache = function () {
					$http
						.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function (dataPartieInteressee) {
								$scope.listePartieInteresseeCache = dataPartieInteressee;
								$log.debug("listePartieInteresseeCache : " + dataPartieInteressee.length)

							});
				}

				// TODO: Liste des Taxes A remplacer par une liste extraite de
				// la cache
				$scope.listeTaxes = function () {
					$http
						.get(UrlAtelier + "/taxe/getAll")
						.success(
							function (dataTaxe) {

								$scope.listeTaxes = dataTaxe;
							});
				}

				// TODO: Liste des Marches A remplacer par une liste extraite de
				// la cache
				/*
				 * $scope.listeMarche = function() { $http .get(UrlAtelier +
				 * "/marche/getAll") .success( function(dataMarche) {
				 * 
				 * $scope.listeMarche = dataMarche; $log.debug("------Vente Js:
				 * listeMarche : "+ dataMarche.length); }); }
				 */

				// TODO: Liste des modePaiement A remplacer par une liste
				// extraite de la cache
				$scope.listeModePaiement = function () {
					$http
						.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeModePaiementCache")
						.success(
							function (dataPaiement) {

								$scope.listeModePaiement = dataPaiement;
							});
				}

				// Liste des traitements façon (hors cache)
				$scope.getListeTraitementFacon = function () {
					traitementFaconServices.getListeTraitementFacon().then(function (listeTraitementFacon) {
						$scope.listeTraitementFacon = listeTraitementFacon;
					}, function (error) {
						console.log(error.statusText);
					});
				}

						// Liste des produits
						$scope.listeProduitCacheFinance = function () {
							$http
								.get(
									UrlCommun
									+ "/produit/all/retour")
								.success(
									function (data) {
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

				$scope.listePartieInteresseeCache();
				$scope.listeTaxes();
				// $scope.listeMarche();
				$scope.listeModePaiement();
				$scope.getListeTraitementFacon();
				/***************************************************************
					* Conversion des Ids/Designation
					**************************************************************/
				// TypeTaxe
				$scope.typeTaxeId = {

					status: ''
				};
				$scope.showTaxe = function (id) {
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

				/***************************************************************
					* Gestion de suppression d'une ligne d'un tableau
					**************************************************************/

				// Annuler Ajout
				$scope.cancelAddBLVente = function (rowform, index, id, designation, liste) {
					if (angular.isDefined(id)) {

						$log.debug("DEF ID" + id);
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
				/***************************************************************
					* Gestion des TaxeBLiv
					**************************************************************/
				/** Mode Creation * */
				$scope.addTaxeBLivInit = function () {
					$scope.inserted = {
						taxeId: '',
						pourcentage: '',
						montant: '',
					};
					$scope.listTaxeFactureInit.push($scope.inserted);
				};

				// remove TaxeBLivInit
				$scope.removeTaxeBLivInit = function (index, taxeId) {
					$scope.listTaxeFactureInit.splice(index, 1);

					if (angular.isNumber(taxeId)) {
						var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
						$scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
						$scope.filterTaxes();
					}

				};

				
				

				

				

				
				
				// champ autoSaisie du champs: referenceBS
				$scope.select2TaggingOptions = {
					'multiple': true,
					'simple_tags': true,
					'tags': function () {
						// reload de la liste des RefBS
						$scope.listNewReferenceBL = [];
						$scope.listNewReferenceBL = $scope.listReferenceBL;

						//$log.debug("----OnClicklistNewReferenceBL : "+JSON.stringify($scope.listNewReferenceBL, null, "    "));
						//console.log("----OnClicklistNewReferenceBL : "+JSON.stringify($scope.listNewReferenceBL, null, "    "));

						return $scope.listNewReferenceBL;

					}

				};

				// bouton ValiderBL: tagReferenceBLivList va contenir les
				// referencesBL selectionnées, puis cette liste va etre affectée
				// au champ : debugLivraison sous la forme de ref1-ref2-..

				
	      



				

				$scope.hiddenNotif = "true";

				$scope.traitementEnCours = "false";
				$scope.traitementEnCoursGenererAll = "false";
				//$scope.traitementEnCoursGenererFacture = "false";

				/**************************************
				 * Notification *
				 **************************************/
				$scope.showNotif = function () {
					$scope.hiddenNotif = "false";
				}

				$scope.closeNotif = function () {
					$scope.hiddenNotif = "true";
				}


				/***************************************************************
					* Gestion facture -Vente
					**************************************************************/
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13, 52, 130],
					pageSize: 52,
					currentPage: 1
				};

				// Recherche des Bons de Vente
				$scope.rechercherFactureVente = function (factureVenteCourant) {

					
					$http
						.post(UrlAtelier +
							"/detfacturevente/rechercheMulticritere",
							factureVenteCourant)
						.success(
							function (resultat) {
								$scope.myData = resultat.list;
								// Pagination du resultat de recherche
								// data, page, pageSize
								$scope.setPagingData(
									$scope.myData,
									$scope.pagingOptions.currentPage,
									$scope.pagingOptions.pageSize);

								$log.info("========listeFacture : " + resultat.list.length);

								$scope.rechercheFactureVenteForm.$setPristine();
							});

				}
                // Liste des produits
							$scope.listeProduitCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireLogistiqueCache/listeProduitCache")
										.success(
												function(data) {
													console
															.log("listeProduitCache "
																	+ data.length);
													$scope.listeProduitCache = data;

												});
							}

							$scope.listeProduitCache();

				// Annuler Recherche
				$scope.annulerAjout = function () {
					
                    $scope.cnt=0;
					// init de l'objet courant
					$scope.factureVenteCourant = 
                    {
						"reference": "",
						"referenceFacture": "",
						"partieIntId": null,
						"referenceBl": "",
                        "dateFactureMin":"",
                        "dateFactureMax":"",
                        "prixMin":"",
                        "prixMax":"",
                        "quantiteDe":"",
                        "quantiteA":"",
						"produitId":"",
						
						
                        

					};
					$scope.rechercherFactureVente({});
							$scope.rechercheFactureVenteForm.$setPristine();
							$scope.displayMode = "list";

					

				
				}
				
				
				/** * PDF ** */
				// conversion date en String
				function formattedDate(date) {
					
					if(date === undefined) return "";
					if(date == null) return "";
					
					
					var d = new Date(date),
						month = '' + (d.getMonth() + 1),
						day = '' + d.getDate(),
						year = d.getFullYear();

					if (month.length < 2) month = '0' + month;
					if (day.length < 2) day = '0' + day;
					return [year, month, day].join('-');
				}


				

				

			

				// generer rapport de tous les bons de livraison. mode : List
				$scope.downloadAllFacturesExcel = function (factureVenteCourant) 
				{
					$scope.chargementEnCours = "true";
							
							
								   $http({
									   url: UrlAtelier + "/fiches/listDetfacturevente",
									   method: "POST",
									   data: factureVenteCourant,
									    // this is your json
																	   // data string
									   headers: {
										   'Content-type': 'application/json',
									   },
									   responseType: 'arraybuffer'
								   }).success(function (data, status, headers, config) {
									   
										$scope.chargementEnCours = "false";
										
										

									   var blob = new Blob([data], { type: "application/vnd.ms-excel" });


									   var fileName = 'Det_listes_Factues_Ventes' + formattedDate(new Date());
									   var link = document.createElement('a');
									   link.href = window.URL.createObjectURL(blob);
									   link.download = fileName;
									   link.click();
									   window.URL.revokeObjectURL(link.href);
									 


									   // var objectUrl = URL.createObjectURL(blob);
									   // window.open(objectUrl);
								   }).error(function (data, status, headers, config) {
									   // upload failed
								   });
					


					
				};




				$scope.downloadAllFacturesExcelDevise = function (factureVenteCourant) {
					$scope.traitementEnCoursGenererAll = "true";

					var newdateFacMinFormat = "";
					if (angular.isDefined(factureVenteCourant.dateFactureMin)) {
						$log.debug("==dateFactureMin " + factureVenteCourant.dateFactureMin);

						if (factureVenteCourant.dateFactureMin != "") {
							newdateFacMinFormat = formattedDate(factureVenteCourant.dateFactureMin);
							$log.debug("===== newdateFacMinFormat " + newdateFacMinFormat);
						} else {
							$log.debug("===== newdateFacMinFormat is Null");
							newdateLivMinFormat = "";
						}
					} else {
						$log.error("==dateFactureMin Undefined");
					}

					var newdateFacMaxFormat = "";
					if (angular.isDefined(factureVenteCourant.dateFactureMax)) {
						$log.debug("==dateFactureMax " + factureVenteCourant.dateFactureMax);

						if (factureVenteCourant.dateFactureMax != "") {
							newdateFacMaxFormat = formattedDate(factureVenteCourant.dateFactureMax);
							$log.debug("===== newdateFacMaxFormat " + newdateFacMaxFormat);
						} else {
							$log.debug("===== newdateFacMaxFormat is Null");
							newdateFacMaxFormat = "";
						}
					} else {
						$log.debug("==dateFactureMax Undefined");
					}
					if (typeof $scope.factureVenteCourant.devise === 'undefined' || $scope.factureVenteCourant.devise == null) {
						var newDevise = "";
					}
					else {
						var newDevise = $scope.factureVenteCourant.devise;
					}
					$log.debug("-- factureVenteCourant" + JSON.stringify(factureVenteCourant, null, "  "));

					var url;
					$log.debug("PI  " + factureVenteCourant.partieIntId);

					url = UrlAtelier + "/fiches/listfactureDevise?referenceFacture=" + factureVenteCourant.referenceFacture
						+ "&typeFacture=Normal"
						+ "&referenceBl=" + factureVenteCourant.referenceBl
						+ "&partieIntId=" + factureVenteCourant.partieIntId
						+ "&dateFactureMin=" + newdateFacMinFormat
						+ "&dateFactureMax=" + newdateFacMaxFormat
						+ "&metrageMin=" + factureVenteCourant.metrageMin
						+ "&metrageMax=" + factureVenteCourant.metrageMax
						+ "&prixMin=" + factureVenteCourant.prixMin
						+ "&prixMax=" + factureVenteCourant.prixMax
						+ "&natureLivraison=" + factureVenteCourant.natureLivraison
						+ "&groupeClientId=" + factureVenteCourant.groupeClientId
						+ "&devise=" + newDevise
						
						+ "&type=pdf";


					$log.debug("-- URL" + url);

					var fileName = 'Liste facture';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');


						fileName[0] = 'Liste des Factures_' + formattedDate(new Date());

						var typeFile = result.headers(['content-type']);
						var file = new Blob([result.data], { type: typeFile });
						var fileURL = window.URL.createObjectURL(file);
						if (typeFile == 'application/vnd.ms-excel') {
							console.log('llll excel');
							a.href = fileURL;
							a.download = fileName[0];
							//$window.open(fileURL)
							a.click();

						} else {
							console.log('llll pdf');
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						}
						$scope.traitementEnCoursGenererAll = "false";


					});


				};



				$scope.downloadRecapExcel = function (factureVenteCourant) {

					$scope.traitementEnCoursGenererAll = "true";
					var newdateFacMinFormat = "";
					if (angular.isDefined(factureVenteCourant.dateFactureMin)) {
						$log.debug("==dateFactureMin " + factureVenteCourant.dateFactureMin);

						if (factureVenteCourant.dateFactureMin != "") {
							newdateFacMinFormat = formattedDate(factureVenteCourant.dateFactureMin);
							$log.debug("===== newdateFacMinFormat " + newdateFacMinFormat);
						} else {
							$log.debug("===== newdateFacMinFormat is Null");
							newdateLivMinFormat = "";
						}
					} else {
						$log.error("==dateFactureMin Undefined");
					}

					var newdateFacMaxFormat = "";
					if (angular.isDefined(factureVenteCourant.dateFactureMax)) {
						$log.debug("==dateFactureMax " + factureVenteCourant.dateFactureMax);

						if (factureVenteCourant.dateFactureMax != "") {
							newdateFacMaxFormat = formattedDate(factureVenteCourant.dateFactureMax);
							$log.debug("===== newdateFacMaxFormat " + newdateFacMaxFormat);
						} else {
							$log.debug("===== newdateFacMaxFormat is Null");
							newdateFacMaxFormat = "";
						}
					} else {
						$log.debug("==dateFactureMax Undefined");
					}

					$log.debug("-- factureVenteCourant" + JSON.stringify(factureVenteCourant, null, "  "));

					var url;
					$log.debug("PI  " + factureVenteCourant.partieIntId);
					if (factureVenteCourant.partieIntId == null) {
						factureVenteCourant.partieIntId = "";
						$log.debug("=>PI  " + factureVenteCourant.partieIntId);
						url = UrlAtelier + "/fiches/factureRecap?referenceFacture=" + factureVenteCourant.referenceFacture
							+ "&typeFacture=Normal"
							+ "&referenceBl=" + factureVenteCourant.referenceBl
							+ "&partieIntId=" + factureVenteCourant.partieIntId
							+ "&dateFactureMin=" + newdateFacMinFormat
							+ "&dateFactureMax=" + newdateFacMaxFormat
							+ "&metrageMin=" + factureVenteCourant.metrageMin
							+ "&metrageMax=" + factureVenteCourant.metrageMax
							+ "&prixMin=" + factureVenteCourant.prixMin
							+ "&prixMax=" + factureVenteCourant.prixMax
							+ "&natureLivraison=" + factureVenteCourant.natureLivraison
							+ "&groupeClientId=" + factureVenteCourant.groupeClientId
							+ "&type=pdf";


					} else {
						url = UrlAtelier + "/fiches/factureRecap?referenceFacture=" + factureVenteCourant.referenceFacture
							+ "&typeFacture=Normal"
							+ "&referenceBl=" + factureVenteCourant.referenceBl
							+ "&partieIntId=" + factureVenteCourant.partieIntId
							+ "&dateFactureMin=" + newdateFacMinFormat
							+ "&dateFactureMax=" + newdateFacMaxFormat
							+ "&metrageMin=" + factureVenteCourant.metrageMin
							+ "&metrageMax=" + factureVenteCourant.metrageMax
							+ "&prixMin=" + factureVenteCourant.prixMin
							+ "&prixMax=" + factureVenteCourant.prixMax
							+ "&natureLivraison=" + factureVenteCourant.natureLivraison
							+ "&groupeClientId=" + factureVenteCourant.groupeClientId
							+ "&type=pdf";
					}
					$log.debug("-- URL" + url);



					var fileName = 'Liste facture Recap';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
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



					/* downloadService.download(url).then(
							 function(success) {
								 $log.debug('success : ' + success);
							 }, function(error) {
								 $log.debug('error : ' + error);
							 });*/
				};


				/** DEBUT***************** DOCUMENTS FACTURE  *******************/

				$scope.listeDocumentProduit = [];
				$scope.name = "FACTURE";

				//$scope.listeTypeDocumentsCache = $scope.listeTypeDocumentsCacheALL.filter(e => e.module === 'VENTE_FACTURE');

				$scope.getListeTypeDocumentsCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeTypeDocumentCache")
						.success(
							function (dataTypeDocumentCache) {




								$scope.listeTypeDocumentsCache = dataTypeDocumentCache.filter(e => e.module === 'VENTE_FACTURE');


							});
				}
				$scope.getListeTypeDocumentsCache();



				$scope.showStatus = function (id) {

					/*$scope.type.status = id;
					var selected = $filter('filterProduit')(
							$scope.ListTypeDocumentCache, {
								id : $scope.type.status
							});
					if ($scope.type.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}*/
					var designation = "";
					//console.log("show status length: "+$scope.listeTypeDocumentsCache.length);
					for (var i = 0; i < $scope.listeTypeDocumentsCache.length; i++) {
						//console.log("id: "+id+"----listeTypeDocumentCache[i]="+ $scope.listeTypeDocumentsCache[i].id);
						//console.log("---module: "+$scope.listeTypeDocumentCache[i].module);
						if ($scope.listeTypeDocumentsCache[i].id == id) {

							designation = $scope.listeTypeDocumentsCache[i].designation;
							//console.log("designation type doc :" +designation);
							return designation;
						}

					}
				};

				// ajout d'un DocumentProduit
				$scope.ajoutDocumentProduit = function () {

					$scope.DocumentProduitInserree = {
						typeDocumentId: '',
						uidDocument: '',
						path: ''

					};
					$scope.listeDocumentProduit
						.push($scope.DocumentProduitInserree);

				};

				// Enregistrer DocumentProduit
				$scope.saveDocumentProduit = function (
					dataDocumentProduit, id) {
					$scope.deleteValue = "non";
					$log.debug("$scope.deleteValue :"
						+ $scope.deleteValue);
					angular.extend(dataDocumentProduit, {
						id: id
					});
				};

				// Supprimer DocumentProduit
				$scope.removeDocumentProduit = function (index) {
					$scope.listeDocumentProduit.splice(index, 1);
				};



				/**FIN *****************   *******************/


		
	 	// ajout d'un Produit
									 $scope.ajoutProduit = function(produitId) {


										//console.log("call ajoutProduit");
										//console.log("produitId=",produitId);
									

										if(produitId!=null){
		
											var element = $scope.listeProduitFinancier.filter(e => e.id == produitId);
								
											if(element != null && element[0] != null){
		
		
												$scope.produitInserree = {
													produitId :produitId,
													produitReference : element[0].reference,
													produitDesignation : element[0].designation,
												
													quantite : 1,
													unite : '',
													choix: "1",
													ficheId : 1,
													prixUnitaireHT : element[0].prixUnitaire
													//prixTotalHT : '',
												//	nouveau :true,
													//remise : ''
												};
											
												$scope.listDetFactureVentePRBL
											.push($scope.produitInserree);
		
											}
										
										
		
											
									  }
									
									};
									
									
											 $scope.ajoutProduitNonFinancier = function(produitId) {


										//console.log("call ajoutProduit");
										//console.log("produitId=",produitId);
									

										if(produitId!=null){
		
											var element = $scope.listeProduitNonFinancier.filter(e => e.id == produitId);
								
											if(element != null && element[0] != null){
		
		
												$scope.produitInserree = {
													produitId :produitId,
													produitReference : element[0].reference,
													produitDesignation : element[0].designation,
										
													quantite : 1,
													unite : '',
													choix: "1",
													ficheId : 1,
													prixUnitaireHT : element[0].prixUnitaire
													//prixTotalHT : '',
												//	nouveau :true,
													//remise : ''
												};
											
												$scope.listDetFactureVentePRBL
											.push($scope.produitInserree);
		
											}
										
										
		
											
									  }
									
									};

				// Supprimer Produit
				$scope.removeProduit = function (index) {




					$scope.listDetFactureVentePRBL.splice(index, 1);

					console.log("Success Delete Produit ");


				};



				/***************************************************************
					* Gestion des Grids de recherche
					**************************************************************/
				$scope.colDefs = [];
				$scope
					.$watch(
						'myData',
						function () {
							$scope.colDefs = [

								{
									field: 'referenceFacture',
									displayName: 'Réf.Fac',
										width : '8%'
								},
								{
									field: 'clientAbreviation',
									displayName: 'Client',
										width : '17%'
								},
								{
									field: 'referenceBl',
									displayName: 'Réf.BL',
									width : '8%'
								},
								{
									field: 'commandeReference',
									displayName: 'Réf.Bc',
									width : '8%'
								},
								
								{
									field: 'dateFacture',
									displayName: 'Date facture',
									cellFilter: "date: 'yyyy-MM-dd'",
									 width:'8%'
								},
								
								{
									field: 'dateEcheance',
									displayName: 'Date Echeance',
									cellFilter: "date: 'yyyy-MM-dd'",
									 width:'8%'
								},
								{
									field : 'produitReference',
									displayName : 'Ref.Produit',
									width : '8%'
								},
								{
									field : 'produitDesignation',
									displayName : 'Designation',
									width : '15%'
								},
								{
									field: 'prixUnitaireHT',
									displayName: 'prixUnitaireHT',
									 width:'6%'
								},
                                {
									field: 'quantite',
									displayName: 'Quantite',
									 width:'6%'
								},

                                {
									field: 'prixTotalHT',
									displayName: 'prixTotalHT',
									 width:'6%'
								},


								
								
                            ];
						});

				$scope.filterOptions = {
					filterText: "",
					useExternalFilter: true
				};

				$scope.totalServerItems = 0;

				$scope.setPagingData = function (data, page,
					pageSize) {
					var pagedData = data.slice((page - 1)
						* pageSize, page * pageSize);
					$scope.myData = pagedData;
					$scope.totalServerItems = data.length;
					if (!$scope.$$phase) {
						$scope.$apply();
					}
				};

				$scope.getPagedDataAsync = function (pageSize, page,
					searchText) {
					setTimeout(
						function () {
							var data;
							var factureVenteCourant = $scope.factureVenteCourant;
							//factureVenteCourant.type = "Normal";

							if (searchText) {
								var ft = searchText.toLowerCase();


								$http
									.post(
										UrlAtelier
										+ "/detfacturevente/rechercheMulticritere", factureVenteCourant)
									.success(
										function (
											largeLoad) {
											data = largeLoad.list
												.filter(function (item) {
													return JSON.stringify(item)
														.toLowerCase()
														.indexOf(ft) != -1;
												});
											$scope.setPagingData(data, page, pageSize);
										});

							} else {

								$http
									.post(
										UrlAtelier
										+ "/detfacturevente/rechercheMulticritere", factureVenteCourant)
									.success(
										function (largeLoad) {
											$log.info("========Grid listeFacture : " + largeLoad.list.length);
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
						function (newVal, oldVal) {
							if (newVal !== oldVal
								&& newVal.currentPage !== oldVal.currentPage) {
								$scope
									.getPagedDataAsync(
										$scope.pagingOptions.pageSize,
										$scope.pagingOptions.currentPage,
										$scope.filterOptions.filterText);
							}
						}, true);
				$scope.$watch('filterOptions', function (newVal,
					oldVal) {
					if (newVal !== oldVal) {
						$scope.getPagedDataAsync(
							$scope.pagingOptions.pageSize,
							$scope.pagingOptions.currentPage,
							$scope.filterOptions.filterText);
					}
				}, true);

				$scope.gridOptions = {
					data : 'myData',
					 columnDefs : 'colDefs',
					 enablePaging : true,
					 showFooter : true,
					 enableHighlighting : true,
					 totalServerItems : 'totalServerItems',
					 pagingOptions : $scope.pagingOptions,
					 selectedItems : $scope.selectedRows,
					 filterOptions : $scope.filterOptions,
				};
				/** Fin de gestion des Grids de la BonVente */

			}])
	// Filtre sur le champ prix du tableau ProduitBS: retourne les 3
	// chiffres apres le point .
	.filter('prixFiltre', function () {
		return function (prix) {
			if (prix) {
				prix = prix.toString();
				// $log.debug("Prix "+prix);
				if (prix.indexOf(".") == -1) {
					return prix;
				} else {
					var index = prix.indexOf(".");
					// $log.debug("index . "+index);
					return prix.substring(0, index + 4);
				}
			}
		};
	});