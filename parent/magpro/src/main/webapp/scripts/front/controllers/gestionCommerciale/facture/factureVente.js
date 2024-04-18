angular
	.module('gpro.factureVente', ["ngResource"])
	.controller(
		'factureVenteController',
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
				
				
				/****** calcul date echeance  ****/
				
					$scope.calculerDateEcheance = function(dateBL, modalitePayement) {

					if (modalitePayement != null && dateBL != null) {

						var modalitePmt = modalitePayement.replace(/\s+/g, " ");


						var listModalitePayement = modalitePmt.split(" ").filter(s => s);

						var modaliteParJour = 0;
						var modaliteParMois = 0;
						
						var modaliteDFM = false;
						
						var modaliteReception = false;



						for (var i = 0; i < listModalitePayement.length; i++) {

							if (listModalitePayement[i].indexOf("jour") > -1 || listModalitePayement[i].indexOf("Jour") > -1) {

								if ((i - 1) >= 0) {

									modaliteParJour = parseInt(listModalitePayement[i - 1]);



								}



							} else

								if (listModalitePayement[i].indexOf("mois") > -1 || listModalitePayement[i].indexOf("Mois") > -1) {

									if ((i - 1) >= 0) {

										modaliteParMois = parseInt(listModalitePayement[i - 1]);



									}

								}
								
								
								if (listModalitePayement[i].indexOf("DFM") > -1 || listModalitePayement[i].indexOf("dfm") > -1) {

							
						             	modaliteDFM =true ;


						        }


 		                      if (listModalitePayement[i].indexOf("réception") > -1 || listModalitePayement[i].indexOf("reception") > -1) {

							
						             	modaliteReception =true ;


						        }


						var dateFacture = angular.copy(dateBL);
					
				    //	var dateFacture = angular.copy(dateBL);
					
					dateFacture = new Date(dateFacture);

						$scope.factureVenteCourant.dateEcheance = angular.copy(dateBL);
						
						
						if(modaliteReception == false){
							
							
							  if(modaliteDFM == true){
	
	                                 dateFacture = new Date(dateFacture.getFullYear(), dateFacture.getMonth()+1, 0);

                                     $scope.factureVenteCourant.dateEcheance = dateFacture ;


                                     // $scope.factureVenteCourant.dateEcheance = $scope.modifierFormatDat($scope.factureVenteCourant.dateEcheance) ;
                                   
                                  }
							
							
						if (!isNaN(modaliteParJour) && modaliteParJour != 0) {

                                  
                              

							//console.log("modaliteParJour :" , modaliteParJour );

							$scope.factureVenteCourant.dateEcheance = dateFacture.setDate(dateFacture.getDate() + modaliteParJour);
						
						 //$scope.factureVenteCourant.dateEcheance = $scope.modifierFormatDat($scope.factureVenteCourant.dateEcheance) ;
						
						}

						if (!isNaN(modaliteParMois) && modaliteParMois != 0) {

							//console.log("modaliteParMois :" , modaliteParMois );

							$scope.factureVenteCourant.dateEcheance = dateFacture.setMonth(dateFacture.getMonth() + modaliteParMois);

 //$scope.factureVenteCourant.dateEcheance = $scope.modifierFormatDat($scope.factureVenteCourant.dateEcheance) ;
						}
							
							
							
						}




					}



                 }

				};


				$scope.getStyleRow = function (montantOuvert, montantTTC) {




					if (montantOuvert != null && montantTTC != null && montantOuvert != 0)

						return {

							"background-color": "#f18973",
							"text-align": "center",
							"margin-top": "4%"


						};





				}

				//valider BL Passager

				$scope.validerBLPassager = function (clientId, dateBL) {

					if (clientId != null && dateBL != null) {


						var element = $scope.listePartieInteresseeCache.filter(function (node) {
							return node.id == clientId;
						});


						if (element[0].passager != null && element[0].passager == true) {


							var obj = { "piId": clientId, "date": dateBL };


							$http
								.post(UrlAtelier + "/facture/validatePassager", obj)
								.success(

									function (resultat) {
										$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
									});


						}


					}

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
					
					reglement.declarer = $scope.factureVenteCourant.declarer;
					

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
                           if($scope.clientActif.fodec){
	                              $scope.validerNatureFiniByOF();
	 						}else
							{
								
								 $scope.validerNatureFiniStandard();
							}

                       

			
				}
				
				$scope.validerNatureFiniStandard = function () {
					
					
							// if($scope.idFactureVente!=null)
					FactureServices.validateFini($scope.tagReferenceBLivList, $scope.idFactureVente).then(function (resultat) {
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

					 var  factureVenteCourant = { reference: $scope.referenceBL, date: $scope.dateBL };

					$scope.tagReferenceBLivList.push($scope.sendedBL.reference);

					$scope.natureLivraison = "FINI";
					$scope.listTaxeFactureInitMethod();
					$scope.initTaxeRemoved();

					$scope.affectedgroupeClientId = '';

					if ($scope.sendedBL.groupeClientId != null)
						$scope.affectedgroupeClientId = $scope.sendedBL.groupeClientId.toString();



					// console.log("AFFECTED
					// groupeClientId",$scope.affectedgroupeClientId);
			         factureVenteCourant = {
						"date": $scope.sendedBL.date,
						"idDepot": $scope.sendedBL.idDepot,
						"modepaiementId": $scope.sendedBL.modepaiementId.toString(),
						"partieIntId": $scope.sendedBL.partieIntId.toString(),
						"typePartieInteressee": $scope.sendedBL.typePartieInteressee.toString(),
						"groupeClientId": $scope.affectedgroupeClientId,
						"declarer": $scope.sendedBL.declare,
						"identifiant":$scope.sendedBL.identifiantLivraison,
						"refCommande":$scope.sendedBL.refCommande,
					};
                   // console.log(factureVenteCourant);
                    

					// console.log("AFFECTED
					// FACTURE",$scope.factureVenteCourant);


					$scope.validerNatureFini();


					$http.get(UrlAtelier + "/facture/getCurrentReferenceByTypeFacture:Normal")
						.success(
							function (res) {

								factureVenteCourant.reference = res;
								factureVenteCourant.refAvantChangement = res;
								$scope.factureVenteCourant=factureVenteCourant;
								console.log($scope.factureVenteCourant);
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

				// remove TaxeBLiv
				$scope.removeTaxeBLiv = function (index, taxeId) {

					$scope.listTaxeFacture.splice(index, 1);

					if (angular.isNumber(taxeId)) {
						var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
						$scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
						$scope.filterTaxes();
					}

				};

				/** Mode Modification * */
				// add TaxeBLiv, /_!_\ 'produitId' DOIT etre TJR UNDEFINED dans
				// l'objet ajouté pour que cancelBLVente fonctionne correctement
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
				/***************************************************************
					* Gestion des ProduitBS
					**************************************************************/
				$scope.listReferenceBL = [];

				$scope.getAvailableRefBLByClient = function (idClient) {
					$scope.listReferenceBL = [];
					$scope.listBLDetaille = [];
					if (angular.isDefined(idClient)) {
						if (idClient != null) {


							// TODO SEARCH TYPE PI
							var element = $scope.listePartieInteresseeCache.filter(function (node) {
								return node.id == idClient;
							});

							$scope.factureVenteCourant.typePartieInteressee = element[0].typePartieInteressee;
							$scope.factureVenteCourant.groupeClientId = element[0].groupeClientId;


							if ($scope.factureVenteCourant.declarer == true) {

								$http
									.get(
										UrlAtelier
										+ "/bonlivraison/getAvailableListBonLivraisonRefByClientAndDeclare:" + idClient)
									.success(
										function (resultat) {
											$log.debug("----ResultatListBL " + resultat.length);

											angular.forEach(resultat, function (element, key) {
												$scope.listReferenceBL.push(element.referenceBL);
											});

											// $log.debug("listBL : "+resultat.list.length);
											//$log.debug("--listReferenceBL : "+JSON.stringify($scope.listReferenceBL, null, "    "));
										});

							} else {
								$http
									.get(
										UrlAtelier
										+ "/bonlivraison/getAvailableListBonLivraisonRefByClient:" + idClient)
									.success(
										function (resultat) {
											$log.debug("----ResultatListBL " + resultat.length);

											angular.forEach(resultat, function (element, key) {
												$scope.listReferenceBL.push(element.referenceBL);
											});

											// $log.debug("listBL : "+resultat.list.length);
											//$log.debug("--listReferenceBL : "+JSON.stringify($scope.listReferenceBL, null, "    "));
										});

							}

							// updated by samer le 11.03.20
							/*
							 * 
							 * $http .get( UrlAtelier +
							 * "/bonlivraison/getAvailableListBonLivraisonRefByClient:"+idClient)
							 * .success( function(resultat) {
							 * $log.debug("----ResultatListBL "+resultat.length);
							 * 
							 * angular.forEach(resultat, function(element, key){
							 * $scope.listReferenceBL.push(element.referenceBL); });
							 *  // $log.debug("listBL : "+resultat.list.length);
							 * $log.debug("--listReferenceBL :
							 * "+JSON.stringify($scope.listReferenceBL, null, " "));
							 * });
							 * 
							 */
							// Ajoute By Ghazi on 07/05/2018 // affichage de Liste
							// de BL detaillée
							/*	$http
								.get(
										UrlAtelier
										+ "/bonlivraison/getAllListBonLivraisonRefByClient:"+idClient)
										.success(
												function(resultat) {
													$log.debug("----ResultatListBL "+resultat.length);
													
													angular.forEach(resultat, function(element, key){
														$scope.listBLDetaille.push(element);
													});
		
		// $log.debug("listBL : "+resultat.list.length);
													$log.debug("--listReferenceBL : "+JSON.stringify($scope.listReferenceBL, null, "    "));
													});*/



						}

					}
				}

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

				$scope.validerBL = function () {


					// console.log("inn valider BL...");
					/** ******** Récupération idclient et idproduit ****** */
					// console.log("ClientId:
					// "+$scope.factureVenteCourant.partieIntId);


					/** **************** */

					$log.debug(" Recherche des Produits appartenants à ces Bons de Livraison ...");
					$log.debug("-- tagReferenceBLivList : " + JSON.stringify($scope.tagReferenceBLivList, null, "    "));

					// idFactureVente: si undefined -> urlValier SANS
					// idFactureVente, sinon -> idFactureVente AVEC
					// idFactureVente
					$log.debug("Valider : idFactureVente " + $scope.idFactureVente);

					$scope.validerNatureFini();

					// Type livraison fini
					// if($scope.natureLivraison == 'FINI'){
					// $scope.validerNatureFini();
					//					 
					// }else{ // $scope.natureLivraison == 'facon'
					//					 
					// $scope.validerNatureFacon();
					// }

					// if($scope.idFactureVente == ''){
					// $log.debug("Log0: idBonLiv == '' ");
					// //Url Without idBonLivraison
					// var urlValider = UrlAtelier+ "/facture/validate";
					// $log.debug("-- urlValider Sans idFactureVente : "+ urlValider );
					//
					// //Invocation du service Validate qui nous recupere la liste des RouleauxFini
					// qui ne sont PAS affectés au BonLivraison Auparavant.
					// $http
					// .post(
					// urlValider,$scope.tagReferenceBLivList)
					// .success(
					// function(resultat) {
					// //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
					// $scope.modeValider = "actif";
					// //setDateInto = dateLivraison du 1erBS
					// $scope.factureVenteCourant.date = resultat.dateLivrison;
					// //listDetFactureVentePRBL
					// $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
					// $log.debug("-- listDetFactureVentePRBL Size : "+
					// $scope.listDetFactureVentePRBL.length);
					//
					// $log.debug("-- listDetFactureVentePRBL : "+
					// JSON.stringify($scope.listDetFactureVentePRBL,null,' '));
					//
					// });
					//
					// }else{
					// $log.debug("Log1: idBonLiv = " + $scope.idFactureVente);
					//
					// //Url With idFactureVente
					// var urlValider = UrlAtelier+
					// "/facture/validate?factureVenteId="+$scope.idFactureVente;
					// $log.debug("-- urlValider Avec idFactureVente : "+ urlValider );
					//
					// //Invocation du service Validate qui nous recupere la liste des ProduitBS.
					// $http
					// .post(urlValider,$scope.tagReferenceBLivList)
					// .success(
					// function(resultat) {
					// //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
					// $scope.modeValider = "actif";
					// //setDateInto = dateLaivraison du 1erBL
					// $scope.factureVenteCourant.date = resultat.dateLivrison;
					// //listDetFactureVente
					// $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
					// $log.debug("-- listDetFactureVentePRBL :
					// "+JSON.stringify($scope.listDetFactureVentePRBL, null, " ") );
					//
					// });
					//
					// }

				}

	        $scope.validerBLByOF = function () {


					$scope.validerNatureFiniByOF();

					

				}



				$scope.selectedAllBL = [];
				// check La liste des BL
				$scope.checkAllBL = function () {

					if ($scope.selectedAllBL == true) {
						$scope.selectedAllBL = true;
					} else {
						$scope.selectedAllBL = false;
					}
					angular.forEach($scope.listBLDetaille, function (item) {

						item.checked = $scope.selectedAllBL;
						item.disable = !$scope.selectedAllBL;

						if (item.checked == true)
							$scope.tagReferenceBLivList.push($scope.listBLDetaille[i].reference);



					});
				}


				$scope.validerNatureFiniFromDetails = function () {

					$log.debug("Log1: idBonLiv = " + $scope.idFactureVente);

					for (var i = 0; i < $scope.listBLDetaille.length; i++) {
						if ($scope.listBLDetaille[i].checked == true) {
							$scope.tagReferenceBLivList.push($scope.listBLDetaille[i].reference);
							$scope.tagReferenceBLivListExterne.push($scope.listBLDetaille[i].refexterne)
						}

					}
					// console.log("TAG References :
					// "+$scope.tagReferenceBLivList.length);

					// if($scope.idFactureVente!=null)
					FactureServices.validateFini($scope.tagReferenceBLivList, $scope.idFactureVente).then(function (resultat) {
						// bouton Valider en mode : Actif :afficher le tableau
						// resultant de DetLivVene
						$scope.modeValider = "actif";
						// setDateInto = dateSortie du 1erBS
						$scope.factureVenteCourant.date = resultat.dateLivrison;


						// listDetFactureVente
						$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
						$log.debug("-- listDetFactureVentePRBL : " + JSON.stringify($scope.listDetFactureVentePRBL, null, "    "));

					}
						, function (error) {
							console.log(error.statusText);
						});
				}



				// $scope.idFactureVente="";

				$scope.validerNatureFacon = function () {

					$log.debug("Log1: idfacture = " + $scope.idFactureVente);

					FactureServices.validateFacon($scope.tagReferenceBLivList, $scope.idFactureVente).then(function (resultat) {
						// bouton Valider en mode : Actif :afficher le tableau
						// resultant de DetLivVene
						$scope.modeValider = "actif";

						$scope.factureVenteCourant.date = resultat.dateSortie;

						var listeDetFacture = resultat.listDetFactureVente;

						$scope.listDetFactureVentePRBL = [];
						angular.forEach(listeDetFacture, function (elementDetFacture, value) {

							var ligneTraitement = [];
							// Filter retourne un résultat de type []
							ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetFacture.traitementFaconId });
							elementDetFacture.designationTraitement = ligneTraitement[0].designation;
							$scope.listDetFactureVentePRBL.push(elementDetFacture);
						})


						$log.debug("-- listDetLivraisonVentePRBS Size : " + $scope.listDetFactureVentePRBL.length);

						$log.debug("-- listDetLivraisonVentePRBS : " + JSON.stringify($scope.listDetFactureVentePRBL, null, '  '));

					}
						, function (error) {
							console.log(error.statusText);
						});

				}
				/***************************************************************
					* Gestion de la table Produit: table DIRECTEMENT editable sur
					* le champ 'Remise' seulement.
					**************************************************************/
				$scope.remiseChanged = function (index) {
					if ($scope.idFactureVente == '') {

						// remiseChangedOnCreation
						$log.debug("remiseChangedOnCreation INDEX Changed " + index);
						$log.debug("listDetFactureVentePRBL Remise : " + $scope.listDetFactureVentePRBL[index].remise);

						$scope.listDetFactureVentePRBL[index].remise = $scope.listDetFactureVentePRBL[index].remise;
						$log.debug("-- listDetFactureVente After Remise Change : " + JSON.stringify($scope.listDetFactureVentePRBL, null, '  '));

					} else {

						// remiseChangedOnModification
						$log.debug("remiseChangedOnModification INDEX Changed " + index);
						$log.debug("listDetFactureVentePRBL Remise : " + $scope.listDetFactureVentePRBL[index].remise);

						if ($scope.listDetFactureVentePRBL[index].id != null) {
							$log.debug("--old--");
							$scope.factureVenteCourant.listDetFactureVente[index].remise = $scope.listDetFactureVentePRBL[index].remise;

						} else {
							$log.debug("--NEW--");
							$scope.factureVenteCourant.listDetFactureVente.push($scope.listDetFactureVentePRBL[index]);

						}

						// $scope.factureVenteCourant.listDetFactureVente[index].remise
						// = $scope.listDetFactureVentePRBL[index].remise;
						$log.debug("-- factureVenteCourant listDetFactureVente After Remise Change : " + JSON.stringify($scope.factureVenteCourant.listDetFactureVente, null, '  '));
					}

				}


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

					if ($scope.clientActif.blackMode == false) {
						factureVenteCourant.declarerecherche = "oui";

					}

					factureVenteCourant.type = "Normal";
					$http
						.post(UrlAtelier +
							"/facture/rechercheMulticritere",
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

				// Annuler Recherche
				$scope.annulerAjout = function () {
					
						$scope.produitIdFinancier ="";
				    $scope.produitIdNonFinancier ="";

					$scope.traitementEnCoursGenererAll = "false";

					$scope.closeNotif();

					$scope.refBLIsInvalid = "false";

					$scope.reglementCourante = {};

					$scope.InitializeArray();

					// $scope.traitementEnCours = "false";
					$scope.hiddenNotifSucc = "false";

					$scope.idFactureVente = '';
					$log.debug("Annuler :$scope.idFactureVente " + $scope.idFactureVente);
					// bouton Valider en mode : notActive
					$scope.modeValider = "notActif";
					// bouton PDF en mode : notActive
					$scope.modePdf = "notActive";
					// vider la liste des refBS
					$scope.tagReferenceBLivList = [];
					$scope.tagReferenceBLivListExterne = [];
					$scope.listTaxeFacture = [];
					$scope.listDetFactureVente = [];
					$scope.listDetFactureVentePRBL = [];
					$scope.listeDocumentProduit = [];

					// initialiser le design des champs
					$scope.creationFactureVenteForm.$setPristine();
					$scope.rechercheFactureVenteForm.$setPristine();
					// init de l'objet courant
					$scope.factureVenteCourant = {
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
						"type": "Normal",
						"declarerecherche" :"oui"

					};
					// init de la Grid
					// $scope.rechercherFactureVente($scope.factureVenteCourant);

					$scope.rechercherFactureVente($scope.factureVenteCourant);

					// interface en mode : list
					$scope.displayMode = "list";
					//$scope.closeNotif();
				}
				
				
					$scope.annulerAjoutRapide = function () {
					
						
					// interface en mode : list
					$scope.displayMode = "list";
					
				}

				// AffectationBLVente BonLivVente
				$scope.affectationBLVente = function (factureVente) {

                   	$scope.produitIdFinancier ="";
				    $scope.produitIdNonFinancier ="";

					$scope.refBLIsInvalid = "false";

					//$scope.traitementEnCours = "false";
					$scope.hiddenNotifSucc = "false";

					$scope.natureLivraison = "FINI";
					$scope.listTaxeFactureInitMethod();
					$scope.initTaxeRemoved();
					$scope.factureVenteCourant = {
						date: new Date(), modepaiementId: 1,
						"devise": "2",
						"declarer": true,
						"forcerCalculMontant": false,
						"dateIntroduction":new Date()

					};
					// $scope.factureVenteCourant = factureVente ? angular
					// .copy(factureVente) : {};
					$scope.factureVenteCourant.declarer = true;
					var type = true;

					$http.get(UrlAtelier + "/facture/getCurrentReferenceByTypeFactureAndDeclarer:Normal:" + type)
						.success(
							function (res) {

								$scope.factureVenteCourant.reference = res;
								$scope.factureVenteCourant.refAvantChangement = res;
							});



					// mode edit activé
					$scope.displayMode = "edit";

				}


				$scope.getCurrentReferenceByType = function (declarer) {

					var type = "";

					if (declarer == true) {

						type = true;
						$scope.listTaxeFactureInitMethod();
					}
					else {

						type = false;
						$scope.listTaxeFactureInit = [
							
					/*		{// TVA7
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



					$http
						.get(
							UrlAtelier
							+ "/facture/getCurrentReferenceByTypeFactureAndDeclarer:Normal:" + type
						)
						.success(
							function (res) {
								$scope.factureVenteCourant.reference = res;
								
								    if ($scope.modePdf != 'actif'){
														
										//creation
		
										$scope.factureVenteCourant.refAvantChangement = res;
		
									   }
								

							}
						);

				}
				// AffectationBLFaconVente BonLivVente
				$scope.affectationBLFaconVente = function (factureVente) {
					$scope.natureLivraison = "FACON";
					$scope.listTaxeFactureInitMethod();
					$scope.initTaxeRemoved();
					$scope.factureVenteCourant = {};
					$scope.factureVenteCourant = factureVente ? angular
						.copy(factureVente) : {};

					// mode edit activé
					$scope.displayMode = "edit";

				}


				// Ajout et Modification Bon de Vente
				$scope.modifierOuCreerFacture = function () {
					$log.debug("modeConsultation/Modification. ");
					// index de la ligne selectionnée dans la Grid.
					var index = this.row.rowIndex;

					// idFactureVente: va etre affecté à l'Url du service
					// Valider en cas de modification
					$scope.idFactureVente = $scope.myData[index].id;
					$log.debug("idFactureVente " + $scope.idFactureVente);

					// bouton PDF activé
					$scope.modePdf = "actif";

					// nature livraison
					$scope.natureLivraison = $scope.myData[index].natureLivraison;

					// getFActure
					$http
						.get(
							UrlAtelier
							+ "/facture/getFactureById:" + $scope.idFactureVente)
						.success(
							function (datagetFactureVente) {

								// init du champ tagReferenceBLivList,

								// recuperation des refBLiv sous le
								// format X-Y-Z
								var refBLiv = datagetFactureVente.infoLivraison.split("-");
								var refBLivExterne = datagetFactureVente.infoLivraisonExterne.split("-");
								console.log("----refBLiv---" + refBLiv);

								// affectation des references à la liste
								// sous le format X,Y,Z
								$scope.tagReferenceBLivList = refBLiv;
								$scope.tagReferenceBLivListExterne = refBLivExterne;
								console.log("----tagReferenceBLivList---" + $scope.tagReferenceBLivList);


								// Liste des TaxeLivraisonVente (pour la
								// table Taxe) & detailsLivraisonVente (
								// pour la table Produit )
								// correspendants à ce bon de vente
								$scope.listDetFactureVentePRBL = datagetFactureVente.listDetFactureVente;

								// bouton Valider en mode : Actif :afficher
								// le tableau resultant de DetLivVene
								$scope.modeValider = "actif";





							/*	if ($scope.natureLivraison == "FINI") {


									var urlValider = UrlAtelier + "/facture/validate?factureVenteId=" + $scope.idFactureVente;
									$log.error("-**- URL : " + urlValider + " RefBLiv " + $scope.tagReferenceBLivList);
									// Invocation du service Validate qui
									// nous recupere la liste des ProduitBL.
									$http
										.post(urlValider, $scope.tagReferenceBLivList)
										.success(
											function (resultat) {

												$log.debug("-%%%- resultat.listDetFactureVente : " + JSON.stringify(resultat.listDetFactureVente, null, "    "));
												// listDetFactureVente
												$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
												$log.debug("-%%%- listDetFactureVentePRBL : " + JSON.stringify($scope.listDetFactureVentePRBL, null, "    "));
												$scope.myData[index].listDetFactureVente = $scope.listDetFactureVentePRBL;
											});

								} else {
									angular.forEach(datagetFactureVente.listDetFactureVente, function (elementDetFacture, value) {

										var ligneTraitement = [];
										// Filter retourne un résultat
										// de type []
										ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetFacture.traitementFaconId });
										elementDetFacture.designationTraitement = ligneTraitement[0].designation;
										$scope.listDetFactureVentePRBL.push(elementDetFacture);
									})

									$log.debug("-%%%- listDetFactureVentePRBL : " + JSON.stringify($scope.listDetFactureVentePRBL, null, "    "));

								}*/

								$log.debug("---nature livraison ----" + $scope.natureLivraison);
								$scope.listTaxeFacture = datagetFactureVente.listTaxeFacture;
								$scope.listeDocumentProduit = datagetFactureVente.listDocFactureVente;

								// affectation du resultat à myData
								$scope.myData[index].listDetFactureVente = $scope.listDetFactureVentePRBL;
								$scope.myData[index].listTaxeFacture = $scope.listTaxeFacture;

								// Initialiser le filtre des taxe à
								// éliminer
								$scope.taxeIdRemove = [];
								for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
									$scope.taxeIdRemove.push($scope.listTaxeFacture[int].taxeId);
								}
								$scope.filterTaxes();



								/*********recuperation reglement ***/

								if (datagetFactureVente.reglementId != null) {

									$http
										.get(
											UrlAtelier +
											"/reglement/getById:" +
											datagetFactureVente.reglementId)
										.success(
											function (datagetReglement) {




												$scope.reglementCourante = datagetReglement;

												$scope.listeDocumentProduit = datagetReglement.listDocReglement;


												$scope.finalOperationsList = datagetReglement.listDetailsReglement;

												$scope.finalElementList = datagetReglement.listElementReglement;

												/*   for(var i=0;i<$scope.finalElementList.length;i++){
															$scope.finalElementList[i].isNew = false;

														 if($scope.finalElementList[i].refFacture != null){
															 $scope.finalElementList[i].checked=true;
														 }
														 else{
															 $scope.finalElementList[i].checked=false;
														 }
													 }*/

											});
								} else {
									$scope.AffectationReglement(datagetFactureVente);



								}
								/*********FIN recuperation reglement ***/








							});


/*
					var dateLivraison = null;
					if ($scope.myData[index].date !== null) {
						dateLivraison = $scope.modifierFormatDate($scope.myData[index].date);
					} else {
						dateLivraison = null;
					}



					$scope.factureVenteCourant = Object.assign($scope.myData[index], { date: dateLivraison })
						//  $scope.partieInteresseeCourante = $scope.myData[index]
						? angular.copy($scope.myData[index])
						: {};
*/



					  $scope.factureVenteCourant = $scope.myData[index] ? angular
					 		 .copy($scope.myData[index])
						 : {};

					// mode edit activé
					$scope.displayMode = "edit";



				}


				$scope.modifierFormatDate = function (dateUp) {
					const dateTimeFormat = new Intl.DateTimeFormat("en", {
						year: "numeric",
						month: "numeric",
						day: "2-digit",
					});
					var [
						{ value: month },
						,
						{ value: day },
						,
						{ value: year }
					] = dateTimeFormat.formatToParts(dateUp);
					return $scope.dateParEdition = `${year}-${month}-${day}`;

				}



				// Sauvegarder bon de Vente
				$scope.sauvegarderBonVente = function (factureVente) {

					$scope.traitementEnCours = "true";

					if (angular.isDefined(factureVente.id)) {
						$log.debug("Sauvegarder Modification : " + factureVente.reference);
						$scope.updateFactureVente(factureVente);
					} else {
						$log.debug("Sauvegarder Ajout : " + factureVente.reference);
						$scope.creerFactureVente(factureVente);
					}
					// Actualiser la Grid
					//$scope.rechercherFactureVente({});
				}

				// Mise à jour des Factures de Vente
				$scope.updateFactureVente = function (factureVente) {

					//$scope.traitementEnCours = "true";

					// Liste des TaxeLivraisonVente (pour la table Taxe) &
					// detailsLivraisonVente ( pour la table Produit )
					// correspendants à ce bon de vente
				
				/*	$log.debug("-- tagReferenceBLivList " + JSON.stringify($scope.tagReferenceBLivList));

					var urlValider = UrlAtelier + "/facture/validate?factureVenteId=" + factureVente.id;
					$log.debug("--------URL Update " + urlValider);
					$http
						.post(urlValider, $scope.tagReferenceBLivList)
						.success(
							function (resultat) { */

								$scope.traitementEnCours = "false";

								$scope.showNotif();
								// bouton Valider en mode : Actif :afficher le
								// tableau resultant de DetLivVene
								$scope.modeValider = "actif";
								// listDetFactureVente
								//$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;


                                $scope.listDetFactureVentePRBL = $scope.listDetFactureVentePRBL;


								//DEBUT UPDATE FACTURE




								factureVente.listDetFactureVente = $scope.listDetFactureVentePRBL;
								factureVente.listDocFactureVente = $scope.listeDocumentProduit;



								factureVente.listTaxeFacture = $scope.listTaxeFacture;
								// tagReferenceBLivList va contenir les referencesBS
								// selectionnées, puis cette liste va etre affectée au champ
								// : infoLivraison sous la forme de ref1-ref2-..
								$log.debug("Join  " + $scope.tagReferenceBLivList.join('-'));
								factureVente.infoLivraison = $scope.tagReferenceBLivList.join('-');
								factureVente.infoLivraisonExterne = $scope.tagReferenceBLivListExterne.join('-');
								$log.debug("Modification Facture : " + JSON.stringify(factureVente, null, "  "));

								$http.post(UrlAtelier + "/facture/update", factureVente)
									.success(
										function (factureVenteId) {

											$log.debug("success Modification ");
											for (var i = 0; i < $scope.myData.length; i++) {

												if ($scope.myData[i].id == factureVenteId) {

													$scope.myData[i] = factureVenteId;
													break;
												}
											}

											// getBonLivVente
											$http.get(UrlAtelier + "/facture/getFactureById:" + factureVenteId)
												.success(
													function (dataGetFactureVente) {

														//$scope.traitementEnCours = "false";
														// bouton Valider Off
														$scope.modeValider = "actif";
														// bouton PDF activé
														$scope.modePdf = "actif";

														// getTableaux
														$scope.listTaxeFacture = dataGetFactureVente.listTaxeFacture;
														$scope.listDetFactureVente = dataGetFactureVente.listDetFactureVente;
														$scope.listeDocumentProduit = dataGetFactureVente.listDocFactureVente;

														// Attributs de Recherche
														$scope.factureVenteCourant = dataGetFactureVente ? angular
															.copy(dataGetFactureVente)
															: {};
														$log.debug("get factureVente : " + JSON.stringify($scope.factureVenteCourant, null, "  "));

														if ($scope.natureLivraison == "FACON") {
															$scope.listDetFactureVentePRBL = [];
															angular.forEach($scope.factureVenteCourant.listDetFactureVente, function (elementDetFacture, value) {

																var ligneTraitement = [];
																// Filter retourne un
																// résultat de type []
																ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetFacture.traitementFaconId });
																elementDetFacture.designationTraitement = ligneTraitement[0].designation;
																$scope.listDetFactureVentePRBL.push(elementDetFacture);
															})
														}
														// Initialiser le filtre des taxe à
														// éliminer
														$scope.taxeIdRemove = [];
														for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
															$scope.taxeIdRemove.push($scope.listTaxeFacture[int].taxeId);
														}
														$log.debug("$scope.taxeIdRemove" + JSON.stringify($scope.taxeIdRemove, null, " "));
														$scope.filterTaxes();



														if (dataGetFactureVente.reglementId != null) {

															$http
																.get(
																	UrlAtelier +
																	"/reglement/getById:" +
																	dataGetFactureVente.reglementId)
																.success(
																	function (datagetReglement) {




																		$scope.reglementCourante = datagetReglement;

																		$scope.listeDocumentProduit = datagetReglement.listDocReglement;


																		$scope.finalOperationsList = datagetReglement.listDetailsReglement;

																		$scope.finalElementList = datagetReglement.listElementReglement;

																		/*   for(var i=0;i<$scope.finalElementList.length;i++){
																					$scope.finalElementList[i].isNew = false;

																				 if($scope.finalElementList[i].refFacture != null){
																					 $scope.finalElementList[i].checked=true;
																				 }
																				 else{
																					 $scope.finalElementList[i].checked=false;
																				 }
																			 }*/

																	});
														} else {
															$scope.AffectationReglement(dataGetFactureVente);



														}



													});
											//

											// $scope.showNotif();
										});



							//});





				}

				// Création FactureVente
				$scope.creerFactureVente = function (factureVente) {
					//$scope.traitementEnCours = "true";
					// $log.debug("-----natureLivraison --------" + $scope.natureLivraison );

					// affectation des listes à l'objet 'factureVente' pour le
					// creer
					// $log.debug("-------- listDetFactureVentePRBL :" );
					// $log.debug(JSON.stringify($scope.listDetFactureVentePRBL, null, " ") );

					// $log.debug("======== AVANT :" );
					// $log.debug("factureVente.listDetFactureVente :
					// "+JSON.stringify(factureVente.listDetFactureVente, null, " ") );

					factureVente.listDetFactureVente = $scope.listDetFactureVentePRBL;
					factureVente.listDocFactureVente = $scope.listeDocumentProduit;


					// $log.debug("======== APRES :" );
					// $log.debug("listDetFactureVentePRBL :
					// "+JSON.stringify(factureVente.listDetFactureVente, null, " ") );

					factureVente.listTaxeFacture = $scope.listTaxeFactureInit;
					// tagReferenceBLivList va contenir les referencesBS
					// selectionnées, puis cette liste va etre affectée au champ
					// : infoLivraison sous la forme de ref1-ref2-..
					// $log.debug("Join "+$scope.tagReferenceBLivList.join('-'));
					factureVente.infoLivraison = $scope.tagReferenceBLivList.join('-');
					factureVente.infoLivraisonExterne = $scope.tagReferenceBLivListExterne.join('-');


					// Type
					factureVente.type = "Normal";
					factureVente.natureLivraison = $scope.natureLivraison;

					// $log.debug("Creation factureVente : "+
					// JSON.stringify(factureVente,null," "));



					/** * SI reference facture est donnéé * */


					if (angular.isDefined(factureVente.reference) && factureVente.reference != '') {

						$log.debug("-- SI reference facture est donnéé ");

						var objRech = {
							"referenceFacture": factureVente.reference,
							"type": "Normal"
						};

						// console.log("objet Rech",objRech);
						$http.post(UrlAtelier + "/facture/rechercheMulticritere", objRech)
							.success(
								function (result) {

									if (result.nombreResultaRechercher == 0) {
										$http.post(UrlAtelier + "/facture/create", factureVente)
											.success(
												function (factureVenteId) {


													$scope.traitementEnCours = "false";

													$scope.showNotif();

													//$scope.showNotif();

													if (factureVenteId == CODE_ERROR_DUPLICATE_BL_IN_FACTURE) {

														$log.debug("CODE_ERROR_DUPLICATE_BL_IN_FACTURE");

														// $scope.traitementEnCours = "false";
														$scope.refBLIsInvalid = "true";

														return;
													}

													$log.debug("Success Creation" + factureVenteId);

													// idFactureVente :
													// valider avec idBonLiv
													$scope.idFactureVente = factureVenteId;
													$log.debug("Valider idFactureVente : " + $scope.idFactureVente);

													// getBonLivVente
													$http.get(UrlAtelier + "/facture/getFactureById:" + factureVenteId)
														.success(
															function (dataGetFactureVente) {

																//$scope.traitementEnCours = "false";

																$scope.modeValider = "actif";

																// bouton
																// PDF
																// activé
																$scope.modePdf = "actif";

																// getTableaux
																$scope.listTaxeFacture = dataGetFactureVente.listTaxeFacture;
																$scope.listeDocumentProduit = dataGetFactureVente.listDocFactureVente;

																// Attributs
																// de
																// Recherche
																$scope.factureVenteCourant = dataGetFactureVente ? angular
																	.copy(dataGetFactureVente)
																	: {};
																$log.debug("get factureVente : " + JSON.stringify($scope.factureVenteCourant, null, "  "));
																$scope.listDetFactureVentePRBL = [];

																if ($scope.natureLivraison == "FACON") {

																	angular.forEach($scope.factureVenteCourant.listDetFactureVente, function (elementDetFacture, value) {

																		var ligneTraitement = [];
																		// Filter
																		// retourne
																		// un
																		// résultat
																		// de
																		// type
																		// []
																		ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetFacture.traitementFaconId });
																		elementDetFacture.designationTraitement = ligneTraitement[0].designation;
																		$scope.listDetFactureVentePRBL.push(elementDetFacture);
																	})
																} else {
																	// init
																	// du
																	// champ
																	// tagReferenceBLivList,
																	$log.debug("-- infoLivraison " + factureVente.infoLivraison);
																	// recuperation
																	// des
																	// refBS
																	// sous
																	// le
																	// format
																	// X-Y-Z
																	var refBS = factureVente.infoLivraison.split("-");
																	var refBSExterne = factureVente.infoLivraisonExterne.split("-");
																	// affectation
																	// des
																	// references
																	// à la
																	// liste
																	// sous
																	// le
																	// format
																	// X,Y,Z
																	$scope.tagReferenceBLivList = refBS;
																	$scope.tagReferenceBLivListExterne = refBSExterne;
																	// Liste
																	// des
																	// TaxeLivraisonVente
																	// (pour
																	// la
																	// table
																	// Taxe)
																	// &
																	// detailsLivraisonVente
																	// (
																	// pour
																	// la
																	// table
																	// Produit
																	// )
																	// correspendants
																	// à ce
																	// bon
																	// de
																	// vente
																	var urlValider = UrlAtelier + "/facture/validate?factureVenteId=" + factureVenteId;
																	$log.debug("--------URL Create " + urlValider);
																	$http
																		.post(urlValider, $scope.tagReferenceBLivList)
																		.success(
																			function (resultat) {
																				// bouton
																				// Valider
																				// en
																				// mode
																				// :
																				// Actif
																				// :afficher
																				// le
																				// tableau
																				// resultant
																				// de
																				// DetLivVene
																				$scope.modeValider = "actif";
																				// listDetFactureVente
																				$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
																			});
																	$log.debug("OLD------ listDetFactureVentePRBL :");
																	$log.debug(JSON.stringify($scope.listDetFactureVentePRBL, null, "    "));
																}

																// Initialiser
																// le filtre des
																// taxe à
																// éliminer
																$scope.taxeIdRemove = [];
																for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
																	$scope.taxeIdRemove.push($scope.listTaxeFacture[int].taxeId);
																}
																$log.debug("$scope.taxeIdRemove" + JSON.stringify($scope.taxeIdRemove, null, " "));
																$scope.filterTaxes();




																//


															});


												});

									}
									else {

										$scope.traitementEnCours = "false";



										// facture existe deja


										$scope.hiddenNotifSucc = "true";

										//$scope.traitementEnCours = "false";

									}
								}
							);



					} else {

						/** * SI reference facture n'est pas donnéé * */



						$log.debug("--  SI reference facture n'est pas donnéé ");

						$http.post(UrlAtelier + "/facture/create", factureVente)
							.success(
								function (factureVenteId) {

									$scope.traitementEnCours = "false";

									$scope.showNotif();
									$log.debug("Success Creation" + factureVenteId);

									if (factureVenteId == CODE_ERROR_DUPLICATE_BL_IN_FACTURE) {

										$log.debug("CODE_ERROR_DUPLICATE_BL_IN_FACTURE");

										//$scope.traitementEnCours = "false";
										$scope.refBLIsInvalid = "true";

										return;
									}

									// idFactureVente : valider avec
									// idBonLiv
									$scope.idFactureVente = factureVenteId;
									$log.debug("Valider idFactureVente : " + $scope.idFactureVente);

									// getBonLivVente
									$http.get(UrlAtelier + "/facture/getFactureById:" + factureVenteId)
										.success(
											function (dataGetFactureVente) {

												//$scope.traitementEnCours = "false";

												$scope.modeValider = "actif";

												// bouton PDF activé
												$scope.modePdf = "actif";

												// getTableaux
												$scope.listTaxeFacture = dataGetFactureVente.listTaxeFacture;
												$scope.listeDocumentProduit = dataGetFactureVente.listDocFactureVente;

												// Attributs de Recherche
												$scope.factureVenteCourant = dataGetFactureVente ? angular
													.copy(dataGetFactureVente)
													: {};
												$log.debug("get factureVente : " + JSON.stringify($scope.factureVenteCourant, null, "  "));
												$scope.listDetFactureVentePRBL = [];

												if ($scope.natureLivraison == "FACON") {

													angular.forEach($scope.factureVenteCourant.listDetFactureVente, function (elementDetFacture, value) {

														var ligneTraitement = [];
														// Filter retourne un
														// résultat de type []
														ligneTraitement = $filter('filter')($scope.listeTraitementFacon, { id: elementDetFacture.traitementFaconId });
														elementDetFacture.designationTraitement = ligneTraitement[0].designation;
														$scope.listDetFactureVentePRBL.push(elementDetFacture);
													})
												} else {
													// init du champ
													// tagReferenceBLivList,
													$log.debug("-- infoLivraison " + factureVente.infoLivraison);
													// recuperation des
													// refBS sous le format
													// X-Y-Z
													var refBS = factureVente.infoLivraison.split("-");
													var refBSExterne = factureVente.infoLivraisonExterne.split("-");
													// affectation des
													// references à la liste
													// sous le format X,Y,Z
													$scope.tagReferenceBLivList = refBS;
													$scope.tagReferenceBLivListExterne = refBSExterne;
													// Liste des
													// TaxeLivraisonVente
													// (pour la table Taxe)
													// &
													// detailsLivraisonVente
													// ( pour la table
													// Produit )
													// correspendants à ce
													// bon de vente
													var urlValider = UrlAtelier + "/facture/validate?factureVenteId=" + factureVenteId;
													$log.debug("--------URL Create " + urlValider);
													$http
														.post(urlValider, $scope.tagReferenceBLivList)
														.success(
															function (resultat) {
																// bouton
																// Valider
																// en mode :
																// Actif
																// :afficher
																// le
																// tableau
																// resultant
																// de
																// DetLivVene
																$scope.modeValider = "actif";
																// listDetFactureVente
																$scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
															});
													$log.debug("OLD------ listDetFactureVentePRBL :");
													$log.debug(JSON.stringify($scope.listDetFactureVentePRBL, null, "    "));
												}

												// Initialiser le filtre des
												// taxe à éliminer
												$scope.taxeIdRemove = [];
												for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
													$scope.taxeIdRemove.push($scope.listTaxeFacture[int].taxeId);
												}
												$log.debug("$scope.taxeIdRemove" + JSON.stringify($scope.taxeIdRemove, null, " "));
												$scope.filterTaxes();




												//
												// $scope.showNotif();

												$scope.AffectationReglement(dataGetFactureVente);

											});


								});


					}
































				}

				// Suppression FactureVente
				$scope.supprimerFactureVente = function () {

					$scope.codeSuppression = '';
					$log.debug("deleting ..");
					// TODO: Service de suppression: à revoir. erreur: operation
					// executée mais avec msg 403!
					// var index = this.row.rowIndex;
					FactureServices.supprimerFacture($scope.myData[$scope.index].id).then(function (resultat) {
						$log.debug("Success Delete");
						$scope.myData.splice($scope.index, 1);
						$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
					}
						, function (error) {
							console.log(error.statusText);
							// TODO: Temp => jusqu'à résoudre le probleme de 403
							$scope.myData.splice($scope.index, 1);
							$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
						});
					$scope.closePopupDelete();
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


				// generer rapport apres creation d'une facture. mode :
				// Modification/Consultation
				$scope.download = function (id, typerapport,avecObservation) {
					
					if(avecObservation === undefined)
					        avecObservation =false;

					$scope.traitementEnCoursGenererAll = "true";
					$log.debug("-- id" + id);
					var url = UrlAtelier + "/reportgc/facture?id=" + id
						+ "&typerapport=" + typerapport
						+ "&avecObservation=" + avecObservation
						
						+ "&type=pdf";


					var numeroFacture = '_';
					if ($scope.factureVenteCourant.reference)
						numeroFacture = $scope.factureVenteCourant.reference + '_';
					var fileName = 'Facture_' + numeroFacture + formattedDate(new Date());
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
							console.log('llll pdf');
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						}

						$scope.traitementEnCoursGenererAll = "false";

					});


					//  downloadService.download(url).then(
					// 		 function(success) {
					// 			 $log.debug('success : ' + success);
					// 			 $scope.annulerAjout();
					// 		 }, function(error) {
					// 			 $log.debug('error : ' + error);
					// 		 });
				};

				// generer rapport apres creation d'une facture. mode :
				// Modification/Consultation
				$scope.downloadExcel = function (id, typerapport) {
					$log.debug("-- id" + id);
					var url = UrlAtelier + "/fiches/facture?id=" + id
						+ "&typerapport=" + typerapport
						+ "&type=pdf";


					var fileName = '  Liste Bon Sortie	';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
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


					//  downloadService.download(url).then(
					// 		 function(success) {
					// 			 $log.debug('success : ' + success);
					// 			 $scope.annulerAjout();
					// 		 }, function(error) {
					// 			 $log.debug('error : ' + error);
					// 		 });
				};

				// generer rapport de tous les bons de livraison. mode : List
				$scope.downloadAllFactures = function (factureVenteCourant) {

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
					if (typeof $scope.factureVenteCourant.groupeClientId === 'undefined' || $scope.factureVenteCourant.groupeClientId == null) {
						var groupeClient = "";
					}
					else {
						var groupeClient = $scope.factureVenteCourant.groupeClientId;
					}

					$log.debug("-- factureVenteCourant" + JSON.stringify(factureVenteCourant, null, "  "));

					var url;
					$log.debug("PI  " + factureVenteCourant.partieIntId);
					if (factureVenteCourant.partieIntId == null) {
						factureVenteCourant.partieIntId = "";
						$log.debug("=>PI  " + factureVenteCourant.partieIntId);
						url = UrlAtelier + "/reportgc/listfacture?referenceFacture=" + factureVenteCourant.referenceFacture
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
							+ "&groupeClientId=" + groupeClient
							+ "&devise=" + newDevise
							
								+ "&dateEcheanceDe=" + formattedDate(factureVenteCourant.dateEcheanceDe)
							+ "&dateEcheanceA=" + formattedDate(factureVenteCourant.dateEcheanceA)
							+ "&declarerecherche=" + factureVenteCourant.declarerecherche
						
							+ "&type=pdf";

					} else {
						url = UrlAtelier + "/reportgc/listfacture?referenceFacture=" + factureVenteCourant.referenceFacture
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
							+ "&groupeClientId=" + groupeClient
							+ "&devise=" + newDevise
							
								+ "&dateEcheanceDe=" + formattedDate(factureVenteCourant.dateEcheanceDe)
							+ "&dateEcheanceA=" + formattedDate(factureVenteCourant.dateEcheanceA)
							+ "&declarerecherche=" + factureVenteCourant.declarerecherche
							
						
							+ "&type=pdf";
					}
					$log.debug("-- URL" + url);

					var fileName = 'liste des factures';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');

						fileName[0] = 'liste des factures_' + formattedDate(new Date());

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


					//  downloadService.download(url).then(
					// 		 function(success) {
					// 			 $log.debug('success : ' + success);
					// 		 }, function(error) {
					// 			 $log.debug('error : ' + error);
					// 		 });
				};


				// generer rapport de tous les bons de livraison. mode : List
				$scope.downloadAllFacturesExcel = function (factureVenteCourant) {
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
					if (factureVenteCourant.partieIntId == null) {
						factureVenteCourant.partieIntId = "";
				
					} 
						if (factureVenteCourant.groupeClientId == null) {
						factureVenteCourant.groupeClientId = "";
				
					} 
						url = UrlAtelier + "/fiches/listfacture?referenceFacture=" + factureVenteCourant.referenceFacture
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
							+ "&dateEcheanceDe=" + formattedDate(factureVenteCourant.dateEcheanceDe)
							+ "&dateEcheanceA=" + formattedDate(factureVenteCourant.dateEcheanceA)
							+ "&declarerecherche=" + factureVenteCourant.declarerecherche
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


					//  downloadService.download(url).then(
					// 		 function(success) {
					// 			 $log.debug('success : ' + success);
					// 		 }, function(error) {
					// 			 $log.debug('error : ' + error);
					// 		 });
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
									field: 'reference',
									displayName: 'Réf.Fac',
									//	width : '12%'
								},
								{
									field: 'partieIntAbreviation',
									displayName: 'Client',
									//	width : '32%'
								},
								{
									field: 'infoLivraison',
									displayName: 'Réf.BL'
								},
								{
									field: 'date',
									displayName: 'Date facture',
									cellFilter: "date: 'yyyy-MM-dd'",
									// width:'8%'
								},
								
								{
									field: 'dateEcheance',
									displayName: 'Date Echeance',
									cellFilter: "date: 'yyyy-MM-dd'",
									// width:'8%'
								},
								{
									field: 'metrageTotal',
									displayName: 'Qte Totale',
									// width:'6%'
								},




								{
									field: 'montantHTaxe',
									displayName: 'Montant HT',
									cellFilter: 'prixFiltre',
									//	width: '8%'
								},
								{
									field: 'montantTaxe',
									displayName: 'Mont. Taxe',
									cellFilter: 'prixFiltre',
									//	width: '8%'
								},
								{
									field: 'montantTTC',
									displayName: 'Montant TTC',
									cellFilter: 'prixFiltre',
									//	width: '8%'
								},
								{
									field: 'montantOuvert',
									displayName: 'Montant Ouvert',
									cellTemplate: `<div ng-style="getStyleRow(row.entity.montantOuvert,row.entity.montantTTC)" style ="margin-top: 4%;" >{{row.entity.montantOuvert | number:3}}</div>`,
									cellFilter: 'prixFiltre',
									//	width: '8%'
								},


								/* 
								 {
									 field : 'etat',
									 displayName : 'Etat'
								 },*/
								// {
								// field : 'natureLivraison',
								// displayName : 'Nature livraison'
								// },
								{
									field: '',
									//width: '5%',
									cellTemplate:
										`<div class="ms-CommandButton float-right"  ng-show="!rowform.$visible" >
										<button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerFacture()">
									<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
									</button>
										<button class="ms-CommandButton-button"  ng-click="showPopupDelete(7)" permission="['Vente_Delete']">
										<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
										</button>
										</div>`,



									// '<div class="buttons" ng-show="!rowform.$visible">'
									// 	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerFacture()">	<i class="fa fa-fw fa-pencil"></i></button>'
									// 	+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(7)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
								}];
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
							factureVenteCourant.type = "Normal";

							if ($scope.clientActif.blackMode == false) {
								factureVenteCourant.declarerecherche = "oui";

							}
							if (searchText) {
								var ft = searchText.toLowerCase();


								$http
									.post(
										UrlAtelier
										+ "/facture/rechercheMulticritere", factureVenteCourant)
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
										+ "/facture/rechercheMulticritere", factureVenteCourant)
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
					data: 'myData',
					columnDefs: 'colDefs',
					enablePaging: true,
					showFooter: true,
					enableColumnResize: true,
					enableHighlighting: true,
					totalServerItems: 'totalServerItems',
					pagingOptions: $scope.pagingOptions,
					selectedItems: $scope.selectedRows,
					filterOptions: $scope.filterOptions,
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