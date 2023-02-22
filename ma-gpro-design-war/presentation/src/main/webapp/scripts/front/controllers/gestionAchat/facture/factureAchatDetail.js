angular
	.module('gpro.detFactureAchat', ["ngResource"])
	.controller(
		'factureAchatDetailController',
		[
			'$scope',
			'$filter',
			'$http',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'FactureAchatServices',
			'traitementFaconServices',
			'$window',
			function ($scope, $filter, $http, $log, downloadService,
				UrlCommun, UrlAtelier, FactureAchatServices,
				traitementFaconServices, $window) {
				$log.info("=========Facture========");
				var data;
				$scope.displayMode = "list";
				// bouton pdf hide
				$scope.modePdf = "notActive";
				$scope.factureAchatCourant = {
					"type": "Normal"
				};
				// mode list activé
				$scope.displayMode = "list";
				$scope.listeBonSortie = [];
				$scope.listDetFactureAchat = [];
				$scope.listDetFactureAchatPRBL = [];
				$scope.idFactureAchat = '';
				// liste des ReferencesBS
				$scope.tagReferenceBLivList = [];
				$scope.tagReferenceBLivListExterne = [];
				$scope.listTaxeFacture = [];
				$scope.listBLDetaille = [];
				// Tableau de Taxe Prédefini

				$scope.hiddenNotif = "true";

				$scope.traitementEnCours = "false";
				$scope.traitementEnCoursGenererFacture = "false";
				$scope.traitementEnCoursGenererAll = "false";







				/**************************************
				 * Notification *
				 **************************************/
			

				

				
				
				
				
				// Liste des unités :uniteArticle
							$scope.listeUniteArticle = function() {
								$http
										.get(UrlCommun + "/article/all")
										.success(
												function(data) {
													console
															.log("listeProduitCache "
																	+ data.length+" "+data);
													$scope.listeUniteArticle = data;

												});
							}

							$scope.listeUniteArticle();
				
								   // Liste des Taxes
  

   

				
				$scope.listePartieInteresseeCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function (dataPartieInteressee) {
								$scope.listePartieInteresseeCache = dataPartieInteressee;
								$log
									.debug("listePartieInteresseeCache : "
										+ dataPartieInteressee.length)

							});
				}

				// TODO: Liste des Taxes A remplacer par une liste
				// extraite de la cache
				$scope.listeTaxes = function () {
					$http.get(UrlAtelier + "/taxe/getAll").success(
						function (dataTaxe) {

							$scope.listeTaxes = dataTaxe;
						});
				}

				
				$scope.listeModePaiement = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireLogistiqueCache/listeModePaiementCache")
						.success(
							function (dataPaiement) {

								$scope.listeModePaiement = dataPaiement;
							});
				}

				// Liste des traitements façon (hors cache)
				$scope.getListeTraitementFacon = function () {
					traitementFaconServices
						.getListeTraitementFacon()
						.then(
							function (listeTraitementFacon) {
								$scope.listeTraitementFacon = listeTraitementFacon;
							},
							function (error) {
								console
									.log(error.statusText);
							});
				}

				$scope.listePartieInteresseeCache();
				$scope.listeTaxes();
				// $scope.listeMarche();
				$scope.listeModePaiement();
				$scope.getListeTraitementFacon();
				/***************************************************
				 * Conversion des Ids/Designation
				 **************************************************/
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

				/***************************************************
				 * Gestion de suppression d'une ligne d'un tableau
				 **************************************************/

				// Annuler Ajout
				$scope.cancelAddBLAchat = function (rowform, index,
					id, designation, liste) {
					if (angular.isDefined(id)) {

						$log.debug("DEF ID" + id);
						$scope.deleteValue = "non";
						rowform.$cancel();
						$log.debug("CANCEL");
					} else {
						$log.debug("ID  UNDEFINED ");
						if (designation == "") {
							$scope.deleteValue == "oui"
							$log.debug("Designation : "
								+ designation);
							$log.debug("$scope.deleteValueOUI : "
								+ $scope.deleteValue);
							liste.splice(index, 1);
							rowform.$cancel();
							$log.debug("DELETE")
						} else {
							$log.debug("Designation :"
								+ designation);
							$log.debug("$scope.deleteValueNON : "
								+ $scope.deleteValue);
							rowform.$cancel();
							$log.debug("CANCEL");
						}
					}
					$scope.deleteValue = "oui";
				}
				/***************************************************
				 * Gestion des TaxeBLiv
				 **************************************************/
				/** Mode Creation * */
				$scope.addTaxeBLivInit = function () {
					$scope.inserted = {
						taxeId: '',
						pourcentage: '',
						montant: '',
					};
					$scope.listTaxeFactureInit
						.push($scope.inserted);
				};

				// remove TaxeBLivInit
				$scope.removeTaxeBLivInit = function (index, taxeId) {
					$scope.listTaxeFactureInit.splice(index, 1);

					if (angular.isNumber(taxeId)) {
						var indexTaxeRemoved = $scope.taxeIdRemove
							.indexOf(taxeId);
						$scope.taxeIdRemove.splice(
							indexTaxeRemoved, 1);
						$scope.filterTaxes();
					}

				};

				// remove TaxeBLiv
				$scope.removeTaxeBLiv = function (index, taxeId) {

					$scope.listTaxeFacture.splice(index, 1);

					if (angular.isNumber(taxeId)) {
						var indexTaxeRemoved = $scope.taxeIdRemove
							.indexOf(taxeId);
						$scope.taxeIdRemove.splice(
							indexTaxeRemoved, 1);
						$scope.filterTaxes();
					}

				};

				/** Mode Modification * */
				// add TaxeBLiv, /_!_\ 'produitId' DOIT etre TJR
				// UNDEFINED dans l'objet ajouté pour que
				// cancelBLAchat fonctionne correctement
				$scope.addTaxeBLiv = function () {
					$scope.inserted = {
						taxeId: '',
						pourcentage: '',
						montant: '',
					};
					$scope.listTaxeFacture.push($scope.inserted);
				};

				// saveTaxeBLiv
				$scope.saveTaxeBLiv = function (data) {
					$scope.taxeIdRemove.push(data.taxeId);
					$scope.filterTaxes();
				};

				$scope.saveTaxeBLivInit = function (data) {
					$scope.taxeIdRemove.push(data.taxeId);
					$scope.filterTaxes();
				}

				// Filtre de la dropList des taxes
				$scope.filterTaxes = function () {
					return function (item) {
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
				
				// champ autoSaisie du champs: referenceBS
				$scope.select2TaggingOptions = {
					'multiple': true,
					'simple_tags': true,
					'tags': function () {
						// reload de la liste des RefBS
						$scope.listNewReferenceBL = [];
						$scope.listNewReferenceBL = $scope.listReferenceBL;

						$log
							.debug("----OnClicklistNewReferenceBL : "
								+ JSON
									.stringify(
										$scope.listNewReferenceBL,
										null,
										"    "));
						console
							.log("----OnClicklistNewReferenceBL : "
								+ JSON
									.stringify(
										$scope.listNewReferenceBL,
										null,
										"    "));

						return $scope.listNewReferenceBL;

					}

				};

			

				
				
				
					

				if ($scope.navMode == "redirection") {
					console.log("FROM FACTURE Achat redirection");
					console.log("Sended PI ", $scope.sendedBR.partieIntersseId);
						console.log("Sended PI ", $scope.sendedBR.partieIntersseId);




					$scope.affectedgroupeClientId = '';

					if ($scope.sendedBR.groupeClientId != null)
						$scope.affectedgroupeClientId = $scope.sendedBR.groupeClientId.toString();

					$scope.factureAchatCourant = {
						"date": $scope.sendedBR.dateLivraison,
						"partieIntId": $scope.sendedBR.partieIntersseId.toString(),
						"groupeClientId": $scope.affectedgroupeClientId,
						"idDepot": $scope.sendedBR.idDepot,
						"declarer":$scope.sendedBR.facture,
						"refexterne":$scope.sendedBR.refexterne
					};



					$scope.tagReferenceBLivList.push($scope.sendedBR.reference);

					$scope.natureLivraison = "FINI";
					$scope.listTaxeFactureInitMethod();
					$scope.initTaxeRemoved();

					console.log("Avant  validerNatureFini");

					$scope.validerNatureFini();


					console.log("Apres  validerNatureFini");
					
					
					$scope.getCurrentReferenceByTypeAndDeclareAndDate('Normal',$scope.factureAchatCourant.declarer,$scope.factureAchatCourant.date); 

								/*	if ($scope.factureAchatCourant.date != null) {
							  	$scope.factureAchatCourant.date = $scope.modifierFormatDate($scope.factureAchatCourant.date);
							     }*/



					/*$http.get(UrlAtelier + "/factureAchat/getCurrentReferenceMensuel:Normal")
						.success(
							function (res) {

								$scope.factureAchatCourant.reference = res;
								$scope.factureAchatCourant.refAvantChangement = res;
							});*/


					$scope.displayMode = "edit";

					$scope.navMode = "Normal";

					$scope.sendedBR = {};

				}

				$scope.selectedAllBL = [];
				// check La liste des BL
				$scope.checkAllBL = function () {

					if ($scope.selectedAllBL == true) {
						$scope.selectedAllBL = true;
					} else {
						$scope.selectedAllBL = false;
					}
					angular
						.forEach(
							$scope.listBLDetaille,
							function (item) {

								item.checked = $scope.selectedAllBL;
								item.disable = !$scope.selectedAllBL;

								if (item.checked == true)
									$scope.tagReferenceBLivList
										.push($scope.listBLDetaille[i].reference);

							});
				}
				// Liste des produits
				$scope.listeProduitCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireLogistiqueCache/listeProduitCache")
						.success(
							function (data) {
								console
									.log("listeProduitCache "
										+ data.length);
								$scope.listeProduitCache = data;

							});
				}
				$scope.listeProduitCache();


				$scope.validerNatureFiniFromDetails = function () {

					$log.debug("Log1: idBonLiv = "
						+ $scope.idFactureAchat);

					for (var i = 0; i < $scope.listBLDetaille.length; i++) {
						if ($scope.listBLDetaille[i].checked == true) {
							$scope.tagReferenceBLivList
								.push($scope.listBLDetaille[i].reference);
							$scope.tagReferenceBLivListExterne
								.push($scope.listBLDetaille[i].refexterne)
						}

					}
					console.log("TAG References    :    "
						+ $scope.tagReferenceBLivList.length);

					// if($scope.idFactureAchat!=null)
					FactureAchatServices
						.validateFini(
							$scope.tagReferenceBLivList,
							$scope.idFactureAchat)
						.then(
							function (resultat) {
								// bouton Valider en mode :
								// Actif :afficher le
								// tableau resultant de
								// DetLivVene
								$scope.modeValider = "actif";
								// setDateInto = dateSortie
								// du 1erBS
								$scope.factureAchatCourant.date = resultat.dateLivrison;

								// listDetFactureAchat
								$scope.listDetFactureAchatPRBL = resultat.listDetFactureAchat;
								$log
									.debug("-- listDetFactureAchatPRBL : "
										+ JSON
											.stringify(
												$scope.listDetFactureAchatPRBL,
												null,
												"    "));

							},
							function (error) {
								console
									.log(error.statusText);
							});
				}

				// $scope.idFactureAchat="";

				$scope.validerNatureFacon = function () {

					$log.debug("Log1: idfacture = "
						+ $scope.idFactureAchat);

					FactureAchatServices
						.validateFacon(
							$scope.tagReferenceBLivList,
							$scope.idFactureAchat)
						.then(
							function (resultat) {
								// bouton Valider en mode :
								// Actif :afficher le
								// tableau resultant de
								// DetLivVene
								$scope.modeValider = "actif";

								$scope.factureAchatCourant.date = resultat.dateSortie;

								var listeDetFacture = resultat.listDetFactureAchat;

								$scope.listDetFactureAchatPRBL = [];
								angular
									.forEach(
										listeDetFacture,
										function (
											elementDetFacture,
											value) {

											var ligneTraitement = [];
											// Filter
											// retourne
											// un
											// résultat
											// de
											// type
											// []
											ligneTraitement = $filter(
												'filter')
												(
													$scope.listeTraitementFacon,
													{
														id: elementDetFacture.traitementFaconId
													});
											elementDetFacture.designationTraitement = ligneTraitement[0].designation;
											$scope.listDetFactureAchatPRBL
												.push(elementDetFacture);
										})

								$log
									.debug("-- listDetLivraisonAchatPRBS Size : "
										+ $scope.listDetFactureAchatPRBL.length);

								$log
									.debug("-- listDetLivraisonAchatPRBS : "
										+ JSON
											.stringify(
												$scope.listDetFactureAchatPRBL,
												null,
												'  '));

							},
							function (error) {
								console
									.log(error.statusText);
							});

				}
				/***************************************************
				 * Gestion de la table Produit: table DIRECTEMENT
				 * editable sur le champ 'Remise' seulement.
				 **************************************************/
				$scope.remiseChanged = function (index) {
					if ($scope.idFactureAchat == '') {

						// remiseChangedOnCreation
						$log
							.debug("remiseChangedOnCreation INDEX Changed "
								+ index);
						$log
							.debug("listDetFactureAchatPRBL Remise : "
								+ $scope.listDetFactureAchatPRBL[index].remise);

						$scope.listDetFactureAchatPRBL[index].remise = $scope.listDetFactureAchatPRBL[index].remise;
						$log
							.debug("-- listDetFactureAchat After Remise Change : "
								+ JSON
									.stringify(
										$scope.listDetFactureAchatPRBL,
										null, '  '));

					} else {

						// remiseChangedOnModification
						$log
							.debug("remiseChangedOnModification INDEX Changed "
								+ index);
						$log
							.debug("listDetFactureAchatPRBL Remise : "
								+ $scope.listDetFactureAchatPRBL[index].remise);

						if ($scope.listDetFactureAchatPRBL[index].id != null) {
							$log.debug("--old--");
							$scope.factureAchatCourant.listDetFactureAchat[index].remise = $scope.listDetFactureAchatPRBL[index].remise;

						} else {
							$log.debug("--NEW--");
							$scope.factureAchatCourant.listDetFactureAchat
								.push($scope.listDetFactureAchatPRBL[index]);

						}

						// $scope.factureAchatCourant.listDetFactureAchat[index].remise
						// =
						// $scope.listDetFactureAchatPRBL[index].remise;
						$log
							.debug("-- factureAchatCourant listDetFactureAchat After Remise Change : "
								+ JSON
									.stringify(
										$scope.factureAchatCourant.listDetFactureAchat,
										null, '  '));
					}

				}

				/***************************************************
				 * Notifications
				 **************************************************/

				$scope.hiddenNotif = "true";

				$scope.showNotif = function () {
					$scope.hiddenNotif = "false";
				}

				$scope.closeNotif = function () {
					$scope.hiddenNotif = "true";
				}

				/***************************************************
				 * Gestion facture -Achat
				 **************************************************/
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13],
					pageSize: 13,
					currentPage: 1
				};

				// Recherche des Bons de Achat
				$scope.rechercherFactureAchat = function (
					factureAchatCourant) {
								
						
					$http
						.post(
							UrlAtelier
							+ "/detfactureAchat/rechercheMulticritere",
							factureAchatCourant)
						.success(
							function (resultat) {
								$scope.myData = resultat.list;
								// Pagination du resultat de
								// recherche
								// data, page, pageSize
								$scope
									.setPagingData(
										$scope.myData,
										$scope.pagingOptions.currentPage,
										$scope.pagingOptions.pageSize);

								

								$scope.rechercheFactureAchatForm
									.$setPristine();
							});

				}

				// Annuler Recherche
				$scope.annulerAjout = function () {
					$scope.cnt=0;
					// init de l'objet courant
					$scope.factureAchatCourant = {
						"reference": "",
						"referenceFacture": "",
						"partieIntId": null,
						"referenceBl": "",
						"dateFactureMin": "",
						"dateFactureMax": "",
						"prixMin": "",
						"prixMax": "",
						"metrageMin": "",
						"metrageMax": "",
						"produitId":"",
						"ReferenceBonReception":""
					};
					$scope.rechercherFactureAchat({});
					$scope.rechercheFactureAchatForm.$setPristine();
					$scope.displayMode = "list";
				}


	// Annuler Recherche
				$scope.annulerAjoutRapide = function () {

				
					$scope.displayMode = "list";
					
				}
				// AffectationBLAchat BonLivAchat
				$scope.affectationBLAchat = function (factureAchat) {
					$scope.listDocFactureAchat = [];
					$scope.natureLivraison = "FINI";

					$scope.listTaxeFactureInitMethod();
					$scope.initTaxeRemoved();
					$scope.factureAchatCourant = {};
					$scope.factureAchatCourant = factureAchat ? angular
						.copy(factureAchat)
						: {};
						$scope.factureAchatCourant.declarer=true;
						$scope.factureAchatCourant.date=new Date();
						$scope.factureAchatCourant.forcerCalculMontant=false;
						
						
						var type =true;
						
						
						$scope.getCurrentReferenceByTypeAndDeclareAndDate('Normal',$scope.factureAchatCourant.declarer,$scope.factureAchatCourant.date);
					
					
				


					// mode edit activé
					$scope.displayMode = "edit";

				}

	
				// AffectationBLFaconAchat BonLivAchat
				$scope.affectationBLFaconAchat = function (
					factureAchat) {
					$scope.listDocFactureAchat = [];
					$scope.natureLivraison = "FACON";
					$scope.listTaxeFactureInitMethod();
					$scope.initTaxeRemoved();
					$scope.factureAchatCourant = {};
					$scope.factureAchatCourant = factureAchat ? angular
						.copy(factureAchat)
						: {};

					// mode edit activé
					$scope.displayMode = "edit";

				}

				// Ajout et Modification Bon de Achat
				$scope.modifierOuCreerFacture = function () {
					$log.debug("modeConsultation/Modification. ");
					// index de la ligne selectionnée dans la Grid.
					var index = this.row.rowIndex;

					// idFactureAchat: va etre affecté à l'Url du
					// service Valider en cas de modification
					$scope.idFactureAchat = $scope.myData[index].id;
					$log.debug("idFactureAchat "
						+ $scope.idFactureAchat);

					// bouton PDF activé
					$scope.modePdf = "actif";

					// nature livraison
					$scope.natureLivraison = $scope.myData[index].natureLivraison;

					// getFActure
					$http
						.get(
							UrlAtelier
							+ "/factureAchat/getFactureById:"
							+ $scope.idFactureAchat)
						.success(
							function (datagetFactureAchat) {

								// init du champ
								// tagReferenceBLivList,

								// recuperation des refBLiv
								// sous le format X-Y-Z
								var refBLiv = datagetFactureAchat.infoLivraison
									.split("-");
								var refBLivExterne = datagetFactureAchat.infoLivraisonExterne
									.split("-");
								console
									.log("----refBLiv---"
										+ refBLiv);

								// affectation des
								// references à la liste
								// sous le format X,Y,Z
								$scope.tagReferenceBLivList = refBLiv;
								$scope.tagReferenceBLivListExterne = refBLivExterne;
								console
									.log("----tagReferenceBLivList---"
										+ $scope.tagReferenceBLivList);

								// Liste des
								// TaxeLivraisonAchat (pour
								// la table Taxe) &
								// detailsLivraisonAchat (
								// pour la table Produit )
								// correspendants à ce bon
								// de Achat
								$scope.listDetFactureAchatPRBL = [];
								
								
								if(datagetFactureAchat.listDetFactureAchat != null)
								     $scope.listDetFactureAchatPRBL = datagetFactureAchat.listDetFactureAchat ;

								// bouton Valider en mode :
								// Actif :afficher le
								// tableau resultant de
								// DetLivVene
								$scope.modeValider = "actif";

							

							
								$scope.listTaxeFacture = datagetFactureAchat.listTaxeFacture;
								$scope.listDocFactureAchat = datagetFactureAchat.listDocFactureAchat;

								// affectation du resultat à
								// myData
								$scope.myData[index].listDetFactureAchat = $scope.listDetFactureAchatPRBL;
								$scope.myData[index].listTaxeFacture = $scope.listTaxeFacture;

								// Initialiser le filtre des
								// taxe à éliminer
								$scope.taxeIdRemove = [];
								for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
									$scope.taxeIdRemove
										.push($scope.listTaxeFacture[int].taxeId);
								}
								$scope.filterTaxes();

							});


					var dateIntroduction = null;

					if ($scope.myData[index].date !== null) {
						dateIntroduction = $scope.modifierFormatDate($scope.myData[index].date);
					} else {
						dateIntroduction = null;
					}



					$scope.factureAchatCourant = Object.assign($scope.myData[index], { date: dateIntroduction })
						//  $scope.partieInteresseeCourante = $scope.myData[index]
						? angular.copy($scope.myData[index])
						: {};


					// $scope.factureAchatCourant = $scope.myData[index] ? angular
					// 	.copy($scope.myData[index])
					// 	: {};

					// mode edit activé
					$scope.displayMode = "edit";

				}


			


				// Sauvegarder bon de Achat
				$scope.sauvegarderBonAchat = function (factureAchat) {
					$scope.traitementEnCours = "true";

					if (angular.isDefined(factureAchat.id)) {
						$log.debug("Sauvegarder Modification : "
							+ factureAchat.reference);
						$scope.updateFactureAchat(factureAchat);
					} else {
						$log.debug("Sauvegarder Ajout : "
							+ factureAchat.reference);
						$scope.creerFactureAchat(factureAchat);
					}
					// Actualiser la Grid
					$scope.rechercherFactureAchat({});
				}

				// Mise à jour des Factures de Achat
				

			

				
				$scope.downloadAllFacturesExcel = function (factureVenteCourant) {
					$scope.chargementEnCours = "true";
							
							
								   $http({
									   url: UrlAtelier + "/fichesAchatXlsx/listDetfactureAchat",
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
										
										

									   var blob = new Blob([data], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });

									   var fileName = 'Det_listes_Factues_Achat' + formattedDate(new Date());
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


				/***************************************************
				 * Gestion des DocumentReceptionAchat
				 **************************************************/
				$scope.name = "ACHAT_FACTURE";




				$scope.getListeTypeDocumentsCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeTypeDocumentCache")
						.success(
							function (dataTypeDocumentCache) {


								$scope.listeTypeDocumentsCache = dataTypeDocumentCache.filter(e => e.module === 'ACHAT_FACTURE');;


							});
				}
				$scope.getListeTypeDocumentsCache();

				$scope.listDocFactureAchat = [];

				// GetId.designation
				$scope.doc = {

					status: ''
				};
				/*$scope.showStatus = function(id) {

					$scope.doc.status = id;
					var selected = $filter('filter')(
							$scope.ListTypeDocumentCache, {
								id : $scope.doc.status
							});
					if ($scope.doc.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				};*/


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

				// ajout d'un DocumentReceptionAchat
				$scope.ajoutDocumentReceptionAchat = function () {

					$scope.documentFactureAchatInserree = {

						typeDocumentId: '',
						uidDocument: '',
						path: ''

					};
					$scope.listDocFactureAchat
						.push($scope.documentFactureAchatInserree);

				};

				// Enregistrer DocumentReceptionAchat
				$scope.saveDocumentReceptionAchat = function (
					dataDocumentReceptionAchat, id) {
					console.log("**SAVE DOC "
						+ dataDocumentReceptionAchat);
					$scope.deleteValue = "non";
					angular.extend(dataDocumentReceptionAchat, {
						id: id
					});
				};

				// Supprimer DocumentReceptionAchat
				$scope.removeDocumentReceptionAchat = function (
					index) {
					$scope.listDocFactureAchat.splice(
						index, 1);
				};


				/** * PDF ** */
				// conversion date en String
				function formattedDate(date) {
					var d = new Date(date), month = ''
						+ (d.getMonth() + 1), day = ''
							+ d.getDate(), year = d.getFullYear();

					if (month.length < 2)
						month = '0' + month;
					if (day.length < 2)
						day = '0' + day;
					return [year, month, day].join('-');
				}

				// generer rapport apres creation d'une facture.
				// mode : Modification/Consultation
				$scope.download = function (id, typerapport) {
					$scope.traitementEnCoursGenererFacture = "true";
					$log.debug("-- id" + id);
					var url = UrlAtelier + "/reportAchat/facture?id="
						+ id + "&typerapport=" + typerapport
						+ "&type=pdf";

					console.log('iciiiiiiii stock')

					var fileName = '  Liste Bon Sortie	';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(22);
						var fileName = heasersFileName.split('.');

						fileName[0] = 'Facture_Achat_' + formattedDate(new Date());

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
						$scope.traitementEnCoursGenererFacture = "false";


					});


					// downloadService.download(url).then(
					// 	function (success) {
					// 		$log.debug('success : ' + success);
					// 		$scope.annulerAjout();
					// 	}, function (error) {
					// 		$log.debug('error : ' + error);
					// 	});
				};

				// generer rapport de tous les bons de livraison.
				// mode : List
				$scope.downloadAllFactures = function (
					factureAchatCourant) {
					var newdateFacMinFormat = "";
					if (angular
						.isDefined(factureAchatCourant.dateFactureMin)) {
						$log
							.debug("==dateFactureMin "
								+ factureAchatCourant.dateFactureMin);

						if (factureAchatCourant.dateFactureMin != "") {
							newdateFacMinFormat = formattedDate(factureAchatCourant.dateFactureMin);
							$log.debug("===== newdateFacMinFormat "
								+ newdateFacMinFormat);
						} else {
							$log
								.debug("===== newdateFacMinFormat is Null");
							newdateLivMinFormat = "";
						}
					} else {
						$log.error("==dateFactureMin Undefined");
					}

					var newdateFacMaxFormat = "";
					if (angular
						.isDefined(factureAchatCourant.dateFactureMax)) {
						$log
							.debug("==dateFactureMax "
								+ factureAchatCourant.dateFactureMax);

						if (factureAchatCourant.dateFactureMax != "") {
							newdateFacMaxFormat = formattedDate(factureAchatCourant.dateFactureMax);
							$log.debug("===== newdateFacMaxFormat "
								+ newdateFacMaxFormat);
						} else {
							$log
								.debug("===== newdateFacMaxFormat is Null");
							newdateFacMaxFormat = "";
						}
					} else {
						$log.debug("==dateFactureMax Undefined");
					}

					$log.debug("-- factureAchatCourant"
						+ JSON.stringify(factureAchatCourant,
							null, "  "));

					var url;
					$log.debug("PI  "
						+ factureAchatCourant.partieIntId);
					if (factureAchatCourant.partieIntId == null) {
						factureAchatCourant.partieIntId = "";
						$log.debug("=>PI  "
							+ factureAchatCourant.partieIntId);
						url = UrlAtelier
							+ "/reportgc/listfacture?referenceFacture="
							+ factureAchatCourant.referenceFacture
							+ "&typeFacture=Normal"
							+ "&referenceBl="
							+ factureAchatCourant.referenceBl
							+ "&partieIntId="
							+ factureAchatCourant.partieIntId
							+ "&dateFactureMin="
							+ newdateFacMinFormat
							+ "&dateFactureMax="
							+ newdateFacMaxFormat
							+ "&metrageMin="
							+ factureAchatCourant.metrageMin
							+ "&metrageMax="
							+ factureAchatCourant.metrageMax
							+ "&prixMin="
							+ factureAchatCourant.prixMin
							+ "&prixMax="
							+ factureAchatCourant.prixMax
							+ "&natureLivraison="
							+ factureAchatCourant.natureLivraison
							+ "&type=pdf";

					} else {
						url = UrlAtelier
							+ "/reportgc/listfacture?referenceFacture="
							+ factureAchatCourant.referenceFacture
							+ "&typeFacture=Normal"
							+ "&referenceBl="
							+ factureAchatCourant.referenceBl
							+ "&partieIntId="
							+ factureAchatCourant.partieIntId
							+ "&dateFactureMin="
							+ newdateFacMinFormat
							+ "&dateFactureMax="
							+ newdateFacMaxFormat
							+ "&metrageMin="
							+ factureAchatCourant.metrageMin
							+ "&metrageMax="
							+ factureAchatCourant.metrageMax
							+ "&prixMin="
							+ factureAchatCourant.prixMin
							+ "&prixMax="
							+ factureAchatCourant.prixMax
							+ "&natureLivraison="
							+ factureAchatCourant.natureLivraison
							+ "&type=pdf";
					}



					$log.debug("-- URL" + url);


					console.log('iciiiiiiii stock')

					var fileName = '  Liste Bon Sortie	';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(22);
						var fileName = heasersFileName.split('.');
						var typeFile = result.headers(['content-type']);
						var file = new Blob([result.data], { type: typeFile });
						var fileURL = window.URL.createObjectURL(file);
						if (typeFile == 'application/vnd.ms-excel') {
							console.log('llll excel');
							// a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						} else {
							console.log('llll pdf');
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						}


					});




					// downloadService.download(url).then(
					// 	function (success) {
					// 		$log.debug('success : ' + success);
					// 	}, function (error) {
					// 		$log.debug('error : ' + error);
					// 	});
				};
				/***************************************************
				 * Gestion des Grids de recherche
				 **************************************************/
				$scope.colDefs = [];
				$scope
					.$watch(
						'myData',
						function () {
							$scope.colDefs = [

								{
									field: 'factureReference',
									displayName: 'Réf.Fac',
									width:'12%'
								},
								{
									field: 'clientAbreviation',
									displayName: 'Fournisseur',
										width:'11%'
								},
								{
									field : 'infoLivraison',
									displayName : 'Réf.BR',
									width:'14%'
								},
								{
									field : 'referenceBlExterne',
									displayName : 'Ref.Externe',
									width:'10%'
								},
								{
									field: 'dateIntroduction',
									displayName: 'Date facture',
									cellFilter: "date: 'yyyy-MM-dd'",
										width:'10%'
								},
								{
									field : 'produitReference',
									displayName : 'Réf. Article',
									width : '8%'
								},
								{
									field : 'produitDesignation',
									displayName : 'Dés. Article',
									width : '12%'
								},
								{
									field: 'prixUnitaireHT',
									displayName: 'Prix Unitaire',
									 width:'10%'
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
							var factureAchatCourant = $scope.factureAchatCourant;
								
							if($scope.clientActif.blackMode==false){
								$scope.factureAchatCourant.declarerecherche="oui";
			
							 }
							if (searchText) {
								var ft = searchText
									.toLowerCase();
								
								$http
									.post(
										UrlAtelier
										+ "/detfactureAchat/rechercheMulticritere",
										factureAchatCourant)
									.success(
										function (
											largeLoad) {
											data = largeLoad.list
												.filter(function (
													item) {
													return JSON
														.stringify(
															item)
														.toLowerCase()
														.indexOf(
															ft) != -1;
												});
											$scope
												.setPagingData(
													data,
													page,
													pageSize);
										});

							} else {

								$http
									.post(
										UrlAtelier
										+ "/detfactureAchat/rechercheMulticritere",
										factureAchatCourant)
									.success(
										function (
											largeLoad) {
											$log
												.info("========Grid listeFacture : "
													+ largeLoad.list.length);
											$scope
												.setPagingData(
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
					data: 'myData',
					columnDefs: 'colDefs',
					enablePaging: true,
					enableColumnResize: true,
					showFooter: true,
					enableHighlighting: true,
					totalServerItems: 'totalServerItems',
					pagingOptions: $scope.pagingOptions,
					selectedItems: $scope.selectedRows,
					filterOptions: $scope.filterOptions,
				};
				/** Fin de gestion des Grids de la BonAchat */

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