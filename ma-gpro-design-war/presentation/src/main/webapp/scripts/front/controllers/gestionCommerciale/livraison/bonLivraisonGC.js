angular
		.module('gpro.bondelivraisonGC', [ "ngResource" ])
		.controller(
				'bonlivraisonControllerGC',
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
						function($scope, $filter, $http, $log, downloadService,
								UrlCommun, UrlAtelier, BonLivraisonServices,
								traitementFaconServices,$window) {
							$log.info("=========Vente========");
							
							const CODE_ERROR_EDITION_BL_HAS_FACTURE = "CODE_ERROR_EDITION_BL_HAS_FACTURE";
							const CODE_ERROR_EDITION_BL_HAS_BON_RETOUR = "CODE_ERROR_EDITION_BL_HAS_BON_RETOUR";
							
							$scope.isCollapsedClientPassager = false;
							$scope.isCollapsedDetailReglement = true;
							$scope.isCollapsedMontants = false;
							

							$scope.tagReferenceBSList = [];
							$scope.listeProduitCach = [];
							$scope.natureLivraison = "FINI";
							$scope.listeUniteArticle = [];
							
							 $scope.traitementEnCours = "false";
							 
							 $scope.hiddenNotifSucc = "false";  
							
							$scope.tagReferenceBLivList = [];
							
				
							 $scope.refBLIsInvalid = "false"; 
							 
							 $scope.erreurUpdateBL = "false"; 
							 $scope.msgErreurUpdateBL = ""; 
							 
							 $scope.hiddenNotif ="true";
								
								$scope.traitementEnCours = "false";
								$scope.traitementEnCoursGenererAll = "false";
								$scope.traitementEnCoursGenererLivraison = "false";
								
								/**************************************
								 * Notification *
								 **************************************/
								$scope.showNotif = function(){
									$scope.hiddenNotif ="false";
								}
														
								$scope.closeNotif = function(){
									$scope.hiddenNotif ="true";
								}
								
							 
						
								$scope.checkboxModel = {
										rapportPrix : "oui"
									};
								
								$scope.checkboxModelRech = {
										stock :$scope.clientActif.hasStock?"oui":"non"
									};
								
								
							$scope.bonLivraisonVenteCourant = {
								"referenceBl" : '',
								"referenceBs=" : '',
								"partieIntId" : '',
								"dateLivraisonMin" : '',
								"dateLivraisonMax" : '',
								"metrageMin" : '',
								"metrageMax" : '',
								"prixMin" : '',
								"prixMax" : '',
								"natureLivraison" : '',
								"avecFacture" : '',
								"idDepot" :'',
								"declare": true,
							};
							
							
							
							
							
							
							
							$scope.validerBC = function(){
								
								BonLivraisonServices.validateBC($scope.tagReferenceBLivList,'')
								.then(function(resultat){
									
										 $scope.modeValider = "actif";
								
										 $scope.bonLivraisonVenteCourant.partieIntId = resultat.partieIntId;
										 $scope.bonLivraisonVenteCourant.dateLivraison = resultat.dateLivraison;
										
										 // listDetFactureVente
										 $scope.listDetLivraisonVente = resultat.listDetLivraisonVente;
								
									 }
									 ,function(error){
										 console.log(error.statusText);
									 });
								
							}
							
							
							/************************************  DEBUT REGLEMENT ****************************/
							
							
							  $scope.myStyleBtn = {
										
					                    "background-color" : "yellow"
					             
					                };
							  
							     // Liste type reglement
			                  $scope.listTypes = function() {
			                    
			            
			                      $http
			                          .get(UrlAtelier + "/gestionnaireLogistiqueCache/listeTypeReglementCache")
			                          .success(
			                              function(dataProduit) {
			                                
			                                  $scope.listTypes = dataProduit;
			                              });
			                  }
			                  $scope.listTypes();
							
					        // Mise à jour des Reglements
					        $scope.updateReglement= function(reglement) {
					        	
					           reglement.listDocReglement= $scope.listeDocumentProduit;
					           reglement.listDetailsReglement= $scope.finalOperationsList;
					           reglement.listElementReglement =$scope.finalElementList;
					           $scope.test = reglement.listElementReglement;
					           
					           console.log("update:magazin: "+reglement.idDepot);
					                        
					               $http
					                   .put(
					                       UrlAtelier +
					                       "/reglement/update",
					                       reglement)
					                   .success(
					                       function(reglementModifiee) {
					                         
					                       
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
					                  $scope.creerReglement = function(reglement) {
					                	  
					                	  reglement.ajoutSpecial = "REGLEMENT_AJOUT_SPECIAL_BL";
					                   
					                	reglement.listDocReglement= $scope.listeDocumentProduit;
					                    reglement.listDetailsReglement= $scope.finalOperationsList;
					                    
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
					                    
					                    reglement.listElementReglement = $scope. finalBLList;
					                   
					                    
					                      $http
					                          .post(
					                              UrlAtelier +
					                              "/reglement/create",
					                              reglement)
					                          .success(
					                              function(newreglement) {

					                                  // TODO getId
					                            
					                            	  $scope.reglementCourante.id = newreglement;
					                                  //$scope.annulerAjout();
					                                  
					                                  
					                              });

					                  }
							
							
				
					        
					        $scope.AffectationReglement = function(bl) {
					        	

			                	
				                   
			                    $('.elementReglement').hide();
			                      $('.BL_FACT_Reglement').show();
			                      
			                      $scope.disableClient =false;
			                      $scope.disableValider = false;
			                      
			                     $scope.reglementCourante = {
			                    		 
			                    		 "date":new Date(),
			                    		 "partieIntId":bl.partieIntId,
			                    		 "idDepot":bl.idDepot,
			                    		 "clientPassager":bl.transporteur
			                    		 
			                    		 
			                     };
			                     
			                     
			                     $scope.finalBLList = [];
			                     
			                     
			                     var blObj = {
			                    		 "refBL":bl.reference,
			                    		  "dateEcheance":bl.date,
			                    		  "montant":bl.montantTTC,
			                    		  "montantDemande":bl.montantTTC
			                     }
			                     
			                     $scope.finalBLList.push(blObj);
			                     
			                     
			                     //$scope.creationReglementForm.$setPristine();
			                     
			                     $scope.finalOperationsList = []; 
			                     for(i=1; i<2; i++){
			                        $scope.addElement(i);
			                       } 
			                     
			                
			                    
			                     //$scope.DisableMontantRegCol();
			                     
			                     //$scope.displayMode = "edit";
			                     
			             		//if($scope.clientActif.disableVente){
									
									$http
									.get(
											UrlAtelier
													+ "/reglement/getCurrentReference"
													)
									.success(
											function(res) {
												
												$scope.reglementCourante.reference = res;
												$scope.reglementCourante.refAvantChangement = res;
											});
									
									
								//}

			                 }
							
					          // Sauvegarder Reglement
					          $scope.sauvegarderAjoutReglement = function(reglement) {
					            if (angular.isDefined(reglement.id)) {
					              
					              $scope.updateReglement(reglement);
					            } else {
					              $scope.creerReglement(reglement);
					              
					            }
					          }
							
						 
				              
				             
				              //liste des Factures
				              $scope.addFacturesElement = function(_ordre) {
				                   
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
				              $scope.addElement = function(_ordre) {
								  
								  var newOrdre = 1;
								  
								  if($scope.finalOperationsList.length == 0) 
									  newOrdre = 1;
								  else newOrdre =  parseInt($scope.finalOperationsList[$scope.finalOperationsList.length - 1].ordre) + 1;
								  
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
				                      typeReglementId : 2,    
				                      numPiece :'',
				                      banque: '',
				                      montant: $scope.bonLivraisonVenteCourant.montantTTC,
				                      dateEmission : new Date(),
				                      dateEcheance : ''
				                  };
				                     
				                  if ($scope.finalOperationsList
				                      .indexOf(tmpElement) == -1) {
				                      $scope.finalOperationsList.push(tmpElement);

				                       var t = parseInt(_ordre)+1;
				                      _ordre =t;
				                     


				                      
				                  }
				                 
				              };
				              
				              
				              // Supprimer Detail
				              $scope.removeDetailRegelement = function(index) {
				                $scope.finalOperationsList.splice(index, 1);
				              };
							
					          $scope.InitializeArray = function() {
			                    
			                       //initialisation des arrays
			                     $scope.finalOperationsList = []; 
			                     for(i=1; i<2; i++){
			                        $scope.addElement(i);
			                       } 
			                      $scope.finalBLList = [];
			                      $scope.finalFacturesList = [];
			                      
			                      $scope.selectedAllBL = [];
			                      for(i=1; i<11; i++){
			                        //$scope.addElement(i);
			                        //$scope.addBLElement(i);
			                        $scope.addFacturesElement(i);
			                      } 
			                       
			                  }
					          
					          $scope.InitializeArray();
							
							
					          /**FIN******************************** FIN REGLEMENT ****************************/
							

							
							  $scope.getAbreviationPIbyId = function (fournisseurId){
								  
									 
								  if(fournisseurId){

										var element = $scope.listePartieInteresseeCache.filter(e => e.id === fournisseurId);
							
										if(element && element[0])
											return element[0].raisonSociale;
										
								  }
		
									
							  }
							  
	                        $scope.isValidSauvegarde = function(){
								
								let resultIsValidSauvegarde = true;
								
								//mode creation
								if($scope.modePdf != 'actif'){
									
									angular.forEach($scope.listDetLivraisonVente, function(element, key){
										
										
										if($scope.isSerialisable(element.produitId)){
											if(element.produitsSerialisable == null)
												
												resultIsValidSauvegarde = false;
											
											else
												
								      	if (element.produitsSerialisable.filter(e => e.checked === true).length !== element.quantite)	
										    resultIsValidSauvegarde = false;
											
										}
										
									
										
									});
									
								}
						
								
								return resultIsValidSauvegarde;
							}	
	                        
	                        
							
                     	  $scope.nbrSelectedProduitsSerialisable = function(produitSerialisableList) {
								
                     		  
                     		  var qte = 0;
								angular.forEach(produitSerialisableList, function(element, key){
								
									if(element.checked == true)	qte += 1;
									
								});
								
								
								return qte;
								
							}
							
							// Liste des listGroupeClient
							$scope.listGroupeClient = function() {
								$http
										.get(
												UrlCommun
														+ "/groupeClient/all")
										.success(
												function(dataCategorieCache) {
												// $log.debug("listeCategorie :
												// "+dataCategorieCache.length);
													$scope.listGroupeClient = dataCategorieCache;

												});
							}
							$scope.listGroupeClient();
							
							$scope.keyPress = function (keyCode, code) {

								if (keyCode == '13') {
									
									
									var element = $scope.listeProduitCache.filter(function(node) {
								        return node.reference==code;
								    });
							
									if(element != null && element[0] != null){
										
										var idScanndProduct = element[0].id;
										

										
										// TODO get ID PRODUIT BY REFRENCE
										
										$scope.produitInserree = {
											    produitId :idScanndProduct,
												produitDesignation : '',
												produitReference : '',
												quantite : '',
												unite : '',
												prixUnitaireHT : '',
												prixTotalHT : '',
												nouveau :true,
												remise : ''
											};

											$scope.listDetLivraisonVente
													.push($scope.produitInserree);
											
											
											$scope.clickProduit(idScanndProduct,$scope.listDetLivraisonVente.length-1,true);
										
									}
								
								}
								
							}
							
							
							$scope.numSerieIsScanned = function (keyCode, numero) {

								if (keyCode == '13') {

									
									var produitCouranteRech = {
							                
							                   "numSerie" :numero,
							                   "critereSpeciale":"produit-non-vendue"
							                   };
									
									
									$http.post(
											UrlCommun
													+ "/produitSerialisable/rechercheProduitSerialisableMulticritere",
													produitCouranteRech)
									.success(
											function(resultat) {
										

												if(resultat.produitSerialisableValues.length > 0){
													
													var produitSerialisable = resultat.produitSerialisableValues[0];
													produitSerialisable.dateFinGarantie = new Date(new Date().setFullYear(new Date().getFullYear() + 1));
													produitSerialisable.checked = true;
													
													
													var element = $scope.listDetLivraisonVente.filter(function(node) {
												        return node.produitId==produitSerialisable.produitId;
												    });
													
													if(element != null && element[0] != null){
														
		
														
														if (element[0].produitsSerialisable.filter(e => e.numSerie === produitSerialisable.numSerie).length == 0){
														
															element[0].produitsSerialisable.push(produitSerialisable);   
															element[0].quantite ++ ;   
															$scope.serieNumber = "";
															$scope.codeBarre ="";
														}else
															{
															 alert("numero de serie existe déjà !!");
																$scope.serieNumber = "";
																$scope.codeBarre ="";
															}
														
														
														
														
													
														
													}
													else
														{
														

														
														$scope.produitInserree = {
															    produitId :produitSerialisable.produitId,
																produitDesignation : '',
																produitReference : '',
																quantite : '',
																unite : '',
																prixUnitaireHT : '',
																prixTotalHT : '',
																nouveau :true,
																remise : ''
															};

															$scope.listDetLivraisonVente
																	.push($scope.produitInserree);
															
															
															$scope.clickProduit(produitSerialisable.produitId,$scope.listDetLivraisonVente.length-1,false);
														
															$scope.listDetLivraisonVente[$scope.listDetLivraisonVente.length-1].produitsSerialisable= [];
															
															$scope.listDetLivraisonVente[$scope.listDetLivraisonVente.length-1].produitsSerialisable.push(produitSerialisable);   
														
															$scope.listDetLivraisonVente[$scope.listDetLivraisonVente.length-1].quantite = 1;
															
															
															$scope.serieNumber = "";
															$scope.codeBarre ="";
														
														}
													
													
											
												}
												
												
										
											});
									
									
								
								}
								
							}
							
							$scope.listTypePICache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeTypePICache")
										.success(
												function(dataTypeCache) {
													$log.debug("listeTypePICache : "+dataTypeCache.length);
													$scope.ListTypePICache = dataTypeCache;

												});
							}
							$scope.listTypePICache();
							
		// Ajouter numero serie au liste reception
							
							$scope.ajouterNumSerieWS = function (numero,index,produitId){
								
								index = $scope.listDetLivraisonVente.length - index -1;
								
								//console.log("numero = ",numero);
								//console.log("index = ",index);
								
								
								
								var produitCouranteRech = {
										                   "produitId":produitId,
										                   "numSerie" :numero,
										                   "critereSpeciale":"produit-non-vendue"
										                   };
								$http.post(
										UrlCommun
												+ "/produitSerialisable/rechercheProduitSerialisableMulticritere",
												produitCouranteRech)
								.success(
										function(resultat) {
									

											if(resultat.produitSerialisableValues.length > 0){
												
												var produitSerialisable = resultat.produitSerialisableValues[0];
												
												if($scope.listDetLivraisonVente[index].produitsSerialisable){
													
													if ($scope.listDetLivraisonVente[index].produitsSerialisable.filter(e => e.numSerie === numero).length == 0){
														
														$scope.listDetLivraisonVente[index].produitsSerialisable.push(produitSerialisable); 
														$scope.numSerie = "";
													}	
														
													
												}else
													{
													$scope.listDetLivraisonVente[index].produitsSerialisable= [];
												
													$scope.listDetLivraisonVente[index].produitsSerialisable.push(produitSerialisable);
													}
												
												
												
											}
											
											
									
										});
								
								
								
							}
							
							$scope.isSerialisable = function(produitId){
								
								var element = $scope.listeProduitCache.filter(function(node) {
							        return node.id==produitId;
							    });
							// $scope.produitCourante.tva = element[0].tva;
								if(element && element[0]) return element[0].serialisable;
								else return false;
							}
							
							$scope.removeNumSerie = function(index,indexPS){
								
							// console.log("index = ",index);
							// console.log("indexPS = ",indexPS);
								
								$scope.listDetLivraisonVente[index].produitsSerialisable.splice(indexPS, 1);
								
							} 
							
							$scope.getReferenceArticleAndDesignation = function(produitId){
								var element = $scope.listeProduitCache.filter(function(node) {
							        return node.id==produitId;
							    });
							// $scope.produitCourante.tva = element[0].tva;
								if(element && element[0]) return element[0].reference + " "+element[0].designation;
								
							}

							// Tableau de Taxe Prédefini

							$scope.listTaxeLivraisonInitMethod = function() {
								if ($scope.natureLivraison == "FINI") {
									$scope.listTaxeLivraisonInit = [
									/*
									 * {//FODEC taxeId: 1, pourcentage: 1,
									 * montant: '', },
									 */
									{// TVA19
										taxeId : 2,
										pourcentage : 19,
										montant : '',
									},
									{// TVA7
										taxeId : 4,
										pourcentage : 7,
										montant : '',
									},
									{// TVA13
										taxeId : 5,
										pourcentage : 13,
										montant : '',
									}];
								} else {
									$scope.listTaxeLivraisonInit = [ {// TVA
										taxeId : 2,
										pourcentage : 19,
										montant : '',
									} ];
								}
							}

							if ($scope.navMode == "redirection") {

								$scope.natureLivraison = "FINI";
								$scope.listTaxeLivraisonInitMethod();

								$log.debug("---livraison----origine"
										+ $scope.origine);
								if ($scope.origine = true) {

									$scope.bonLivraisonVenteCourant.reference = "";

									$scope.bonLivraisonVenteCourant = {
										// "reference" : $scope.referenceBs,
										"date" : $scope.dateSortieBs
									};

									$log
											.debug("-------$scope.bonLivraisonVenteCourant"
													+ $scope.bonLivraisonVenteCourant);
								} else {

									$scope.bonLivraisonVenteCourant = {
										"reference" : $scope.referenceBs,
										"date" : $scope.dateSortieBs
									};

								}

								$scope.tagReferenceBSList
										.push($scope.referenceBs);
								$scope.displayMode = "edit";

							} else if ($scope.navMode == 'normal') {
								// $scope.bonLivraisonVenteCourant = {};
								
								
								$scope.bonLivraisonVenteCourant = {
										"referenceBl" : '',
										"referenceBs=" : '',
										"partieIntId" : '',
										"dateLivraisonMin" : '',
										"dateLivraisonMax" : '',
										"metrageMin" : '',
										"metrageMax" : '',
										"prixMin" : '',
										"prixMax" : '',
										"natureLivraison" : '',
										"avecFacture" : '',
										"idDepot" :''
									};
								
								if($scope.clientActif.hasStock)
									$scope.checkboxModel.stock="oui";
								else
									$scope.checkboxModel.stock="non";
								

								
								$scope.displayMode = "list";
							}

							var data;
							// bouton pdf hide
							$scope.modePdf = "notActive";

							// mode list activé
							$scope.listeBonSortie = [];
							$scope.listDetLivraisonVente = [];
							$scope.listDetLivraisonVentePRBS = [];
							$scope.idBonLivVente = '';
							// liste des ReferencesBS

							$scope.listTaxeLivraison = [];
							$scope.listeDocumentProduit = [];
															

							// modeValider
							$scope.modeValider = "notActif";
							// init deleteValue pour cancelAddBLVente
							$scope.deleteValue = "oui";

							/***************************************************
							 * Gestion des DropListe & cache
							 **************************************************/
							$scope.listePartieInteresseeCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPartieInteressee) {
													$scope.listePartieInteresseeCache = dataPartieInteressee;
													$log
															.debug("listePartieInteresseeCache : "
																	+ dataPartieInteressee.length)

												});
							}
							
							
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
							

							// TODO: Liste des Taxes A remplacer par une liste
							// extraite de la cache
							$scope.listeTaxes = function() {
								$http.get(UrlAtelier + "/taxe/getAll").success(
										function(dataTaxe) {

											$scope.listeTaxes = dataTaxe;
											console.log("===> get taxe");
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

							// Liste des unités :uniteArticle
							$scope.listeUniteArticle = function() {
								$http
										.get(UrlCommun + "/uniteArticle/all")
										.success(
												function(data) {
													console
															.log("listeProduitCache "
																	+ data.length+" "+data);
													$scope.listeUniteArticle = data;

												});
							}

							$scope.listeUniteArticle();

							// TODO: Liste des Marches A remplacer par une liste
							// extraite de la cache
							
// $scope.listeMarche = function(idClient) { $http
// .get(UrlAtelier + "/marche/getAll") .success(
// function(dataMarche) {
//							  
// $scope.listeMarche = dataMarche;
// $log.debug("------Vente Js: listeMarche : "+
// dataMarche.length); }); }
							 

							// TODO: Liste des modePaiement A remplacer par une
							// liste extraite de la cache
							
							  
							  
							  
// $scope.listeMarche = function (idClient) {
// $scope.listeMarche = [];
//
// if(angular.isDefined(idClient)){
// if(idClient != null){
// $http
// .get(
// UrlAtelier
// + "/marche/getListById:"+idClient)
// .success(
// function(resultat) {
// //$log.debug("----ResultatListBC "+resultat.length);
//																
// angular.forEach(resultat, function(element, key){
// //console.log("==>elemet: "+element.reference);
// $scope.listeMarche.push(element);
//																	
//																	
// });
//																
// //console.log("listeRefCommande: "+listReferenceBC.lenght);
//
// // $log.debug("listBL : "+resultat.list.length);
// //console.log("--listReferenceBC : "+JSON.stringify($scope.listReferenceBC,
// null, " "));
// });
// }
//									 	
// }
// }
							  
							  

							  
							  $scope.listeModePaiement = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireLogistiqueCache/listeModePaiementCache")
										.success(
												function(dataPaiement) {

													$scope.listeModePaiement = dataPaiement;
												});
							}

							// Liste des traitements façon (hors cache)
							$scope.getListeTraitementFacon = function() {
								traitementFaconServices
										.getListeTraitementFacon()
										.then(
												function(listeTraitementFacon) {
													$scope.listeTraitementFacon = listeTraitementFacon;
												},
												function(error) {
													console
															.log(error.statusText);
												});
							}
							
// /// Ajout de Camion,chauffeur et Remorque 26042018
							
							
							$scope.listeCamion = function() {
								$http.get(UrlAtelier + "/engin/getAll").success(
										function(dataTaxe) {

											$scope.listeCamion = dataTaxe;
											console.log("===> get taxe");
										});
							}
							
							$scope.listeChauffeur = function() {
								$http.get(UrlAtelier + "/personnel/getAll").success(
										function(dataTaxe) {

											$scope.listeChauffeur = dataTaxe;
											console.log("===> get taxe");
										});
							}
							
							
							$scope.listeRemorque = function() {
								$http.get(UrlAtelier + "/remorque/getAll").success(
										function(dataTaxe) {

											$scope.listeRemorque = dataTaxe;
											console.log("===> get taxe");
										});
							}
							

							$scope.listePartieInteresseeCache();
							$scope.listeMagazinCache();
							$scope.listeTaxes();
							// $scope.listeMarche();
							$scope.listeModePaiement();
							$scope.getListeTraitementFacon();
							$scope.listeCamion(); 
							$scope.listeChauffeur();
							$scope.listeRemorque();
							
							// /// Ajout de Camion,chauffeur et Remorque
							// 26042018
							
							
							$scope.listeCamion = function() {
								$http.get(UrlAtelier + "/engin/getAll").success(
										function(dataTaxe) {

											$scope.listeCamion = dataTaxe;
											console.log("===> get taxe");
										});
							}
							
							$scope.listeChauffeur = function() {
								$http.get(UrlAtelier + "/personnel/getAll").success(
										function(dataTaxe) {

											$scope.listeChauffeur = dataTaxe;
											console.log("===> get taxe");
										});
							}
							
							
							$scope.listeRemorque = function() {
								$http.get(UrlAtelier + "/remorque/getAll").success(
										function(dataTaxe) {

											$scope.listeRemorque = dataTaxe;
											console.log("===> get taxe");
										});
							}
							
							
							
						/** DEBUT***************** DOCUMENTS LIVRAISON  *******************/
							
								$scope.name = "BL";
								

								$scope.getListeTypeDocumentsCache = function() {
									$http
											.get(
													UrlCommun
															+ "/gestionnaireCache/listeTypeDocumentCache")
											.success(
													function(dataTypeDocumentCache) {
												
				
															
													
							          $scope.listeTypeDocumentsCache = dataTypeDocumentCache.filter(e => e.module === 'VENTE_LIVRAISON');
							
				
													});
								}
								$scope.getListeTypeDocumentsCache();

							

								$scope.showStatus = function (id) { 
									var designation = '';
									for (var i = 0; i < $scope.listeTypeDocumentCache.length; i++) {
										console.log(
											'listeTypeDocumentCache[i]=' + $scope.listeTypeDocumentCache[i].id
										);
										if (
											$scope.listeTypeDocumentCache[i].id == id &&
											$scope.listeTypeDocumentCache[i].module == 'VENTE_LIVRAISON'
										) {
											designation = $scope.listeTypeDocumentCache[i].designation;
											//console.log("designation type doc :" +designation);
											return designation;
										}
										/*else{
												return "vide";
											}*/
									}
					
								};



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
        var designation = '';
        console.log(
          'show status length: ' + $scope.listeTypeDocumentsCache.length
        );
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
							$scope.ajoutDocumentProduit = function() {

								$scope.DocumentProduitInserree = {
									typeDocumentId : '',
									uidDocument : '',
									path : ''

								};
								$scope.listeDocumentProduit
										.push($scope.DocumentProduitInserree);

							};

							// Enregistrer DocumentProduit
							$scope.saveDocumentProduit = function(
									dataDocumentProduit, id) {
								$scope.deleteValue = "non";
								$log.debug("$scope.deleteValue :"
										+ $scope.deleteValue);
								angular.extend(dataDocumentProduit, {
									id : id
								});
							};

							// Supprimer DocumentProduit
							$scope.removeDocumentProduit = function(index) {
								$scope.listeDocumentProduit.splice(index, 1);
							};
							
							
						
							/**FIN ***************** DOCUMENTS LIVRAISON  *******************/
							
							
							
							
							/***************************************************
							 * Conversion des Ids/Designation
							 **************************************************/
							// TypeTaxe
							$scope.typeTaxeId = {

								status : ''
							};
							$scope.showTaxe = function(id) {
								$scope.typeTaxeId.status = id;
								var selected = $filter('filter')(
										$scope.listeTaxes, {
											id : $scope.typeTaxeId.status
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
							$scope.cancelAddBLVente = function(rowform, index,
									id, designation, liste) {
								$log.debug("cancelAddBLVente");
								$log.debug("* Designation"
										+ liste[0].designation);
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
								
								// rechargement des produits
								$scope.listeProduitCache();
							}
							/***************************************************
							 * Gestion des TaxeBLiv
							 **************************************************/
							/** Mode Creation * */
							$scope.addTaxeBLivInit = function() {
								$scope.inserted = {
									taxeId : '',
									pourcentage : '',
									montant : '',
								};
								$scope.listTaxeLivraisonInit
										.push($scope.inserted);
							};

							// remove TaxeBLivInit
							$scope.removeTaxeBLivInit = function(index, taxeId) {
								$scope.listTaxeLivraisonInit.splice(index, 1);

								if (angular.isNumber(taxeId)) {
									var indexTaxeRemoved = $scope.taxeIdRemove
											.indexOf(taxeId);
									$scope.taxeIdRemove.splice(
											indexTaxeRemoved, 1);
									$scope.filterTaxes();
								}

							};

							// remove TaxeBLiv
							$scope.removeTaxeBLiv = function(index, taxeId) {

								$scope.listTaxeLivraison.splice(index, 1);
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
							// cancelBLVente fonctionne correctement
// $scope.addTaxeBLiv = function() {
// $scope.filterTaxes();
// $scope.inserted = {
// taxeId : '',
// pourcentage : '',
// montant : '',
// };
//								
// $scope.listTaxeLivraison.push($scope.inserted);
// };
							$scope.addTaxeBLiv= function() {
								$scope.inserted = {
									taxeId : '',
									pourcentage : '',
									montant : '',
								};
								$scope.listTaxeLivraison
										.push($scope.inserted);
							};

							// saveTaxeBLiv
							$scope.saveTaxeBLiv = function(data) {
								$scope.taxeIdRemove.push(data.taxeId);
								$scope.filterTaxes();
							};

							$scope.saveTaxeBLivInit = function(data) {
								$scope.taxeIdRemove.push(data.taxeId);
								$scope.filterTaxes();
							}

							$scope.initTaxeRemoved = function() {
								/*
								 * if($scope.natureLivraison == "FINI"){
								 * $scope.taxeIdRemove = [1,2]; //FODEC + TVA
								 * }else{ $scope.taxeIdRemove = [2]; //TVA
								 * uniquement }
								 */

								$scope.taxeIdRemove = [ 2 ]; // TVA
																// uniquement

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

							/**
							 * **********************Gestion des
							 * produits****************
							 */
							// ajout d'un Produit
							$scope.ajoutProduit = function() {
								$scope.produitInserree = {
									// produitId :'',
									produitDesignation : '',
									produitReference : '',
									quantite : '',
									unite : '',
									prixUnitaireHT : '',
									prixTotalHT : '',
									nouveau :true,
									remise : ''
								};

								$scope.listDetLivraisonVente
										.push($scope.produitInserree);

							};
							
							
							$scope.ajoutProduitAll = function() {

								
								angular.forEach($scope.listeProduitCache, function(element, key){
									
									console.log("Key =",key);
									console.log("element =",element);
									
									$scope.produitInserree = {
											produitId :element.id,
											produitDesignation : '',
											produitReference : '',
											quantite : '',
											unite : '',
											prixUnitaireHT : '',
											prixTotalHT : '',
											nouveau :true,
											remise : ''
										};
								
									$scope.listDetLivraisonVente.push($scope.produitInserree);
				
									
									$scope.clickProduit(element.id, key,true);

								});
								
							

							};

							// Enregistrer Produit
							$scope.saveProduit = function(dataProduit, id) {
								$scope.deleteValue = "non";
								console.log("==>dataProduit"+ JSON.stringify(dataProduit, null," "));

								console.log("$scope.getProductFilter"
										+ JSON.stringify($scope.ProductFilter,
												null, " "));

								angular.extend(dataProduit, {
									id : id
								});
								$scope.showBtnCalender = false;
							};

							// Supprimer Produit
							$scope.removeProduit = function(index) {
								
								// console.log("index = ",index);
								
							var	nindex = $scope.listDetLivraisonVente.length - index -1;
							// console.log("nindex = ",nindex);
								
							if($scope.modePdf == "actif"){
								$scope.listSuppDetLivraisonVente.push($scope.listDetLivraisonVente[nindex]);
							}
							
								
								$scope.listDetLivraisonVente.splice(nindex, 1);
								
								console.log("Success Delete Produit ");
								
								
							};

							$scope.produitRech={
								id : ''
							};
							
							$scope.getProductFilter = function(idProduit) {
								$scope.productFilter = [];
								$scope.productFilter2 =[];
								
								$scope.produitRech.id=idProduit;
								// console.log("produit id"+idProduit);
								// console.log("produit id
								// rec"+$scope.produitRech.id);
								
								
								
								$scope.productFilter = $filter('filter')(
										
										
										$scope.listeProduitCache, {
											// idProduit :parseInt(id)
											id : idProduit
											
											
										});
								
								// console.log("length liste filtre
								// produit"+$scope.productFilter.length);
								
								for(var i=0;i<$scope.productFilter.length;i++){
								
									
									if($scope.productFilter[i].id==idProduit){
										// console.log("resultat filter:
										// "+$scope.productFilter[i].reference);
										$scope.productFilter2=$scope.productFilter[i];
									}
								}
                                 
							}
							$scope.PrixClientValue = {
									"idClient" : '',
									"idProduit=" : '',
									
								};
							
							
							 /***********************************************
								 * Gestion des ProduitBC
								 **********************************************/
							  $scope.listReferenceBC = [];

							  $scope.getAvailableRefBCByClient = function (idClient) {
								$scope.listReferenceBC = [];
								$scope.listeMarche=[];

								if(angular.isDefined(idClient)){
									if(idClient != null){
										
										
										// TODO SEARCH TYPE PI
										var element = $scope.listePartieInteresseeCache.filter(function(node) {
									        return node.id==idClient;
									    });
								
										$scope.bonLivraisonVenteCourant.typePartieInteressee = element[0].typePartieInteressee;
										
										$scope.bonLivraisonVenteCourant.groupeClientId = element[0].groupeClientId;
										
										$http
										.get(
												UrlAtelier
												+ "/commandeVente/getAvailableListBonCommandeRefByClient:"+idClient)
												.success(
														function(resultat) {
															$log.debug("----ResultatListBC "+resultat.length);
															
															angular.forEach(resultat, function(element, key){
																console.log("==>elemet: "+element.reference);
																$scope.listReferenceBC.push(element.reference);
																
																
															});
															
															// console.log("listeRefCommande:
															// "+listReferenceBC.lenght);

// $log.debug("listBL : "+resultat.list.length);
															// console.log("--listReferenceBC
															// :
															// "+JSON.stringify($scope.listReferenceBC,
															// null, " "));
							         			});
										}
									
									if(idClient != null){
										console.log("enter: to List ");
										$http
										.get(
												UrlAtelier
												+ "/marche/getListById:"+idClient)
												.success(
														function(resultat) {
															// $log.debug("----ResultatListBC
															// "+resultat.length);
															
															angular.forEach(resultat, function(element, key){
																// console.log("==>elemet:
																// "+element.reference);
																$scope.listeMarche.push(element);
																
																
															});
															
															// console.log("listeRefCommande:
															// "+listReferenceBC.lenght);

// $log.debug("listBL : "+resultat.list.length);
															// console.log("--listReferenceBC
															// :
															// "+JSON.stringify($scope.listReferenceBC,
															// null, " "));
							         			});
										}
								 	
									}
						        }
						        
							// champ autoSaisie du champs: referenceBC
							$scope.select2TaggingOptions = {
									 'multiple': true,
									 'simple_tags': true,
									 'tags': function () {
											 // reload de la liste des RefBC
											 	$scope.listNewReferenceBC = [];
											 	
											 	$scope.listNewReferenceBC = $scope.listReferenceBC;

												$log.debug("----OnClicklistNewReferenceBC : "+JSON.stringify($scope.listNewReferenceBC, null, "    "));
												console.log("----OnClicklistNewReferenceBC : "+JSON.stringify($scope.listNewReferenceBC, null, "    "));

												return $scope.listNewReferenceBC;
										    }
									  
							 };
							/** ******************************** */
							$scope.clickProduit = function(idProduit, index,whithGetProduitSerialisable) {
								index = $scope.listDetLivraisonVente.length - index -1;
								
								
								
								// var ObjetPrixClient
								
								// get idClient et get idProduit
								
								var idClient=$scope.bonLivraisonVenteCourant.partieIntId;
								// console.log("###### PI click prod:
								// "+idClient);
								// console.log("idProduit---" +
								// idProduit+"index: "+index);
								
								$scope.PrixClientValue.idClient=idClient;
								$scope.PrixClientValue.idProduit=idProduit;
								
								// console.log("=====> client id:
								// "+$scope.PrixClientValue.idClient);
									
										// fonction load PrixClient Par Produit
										$scope.listePrixClientProduit = function() {
											$http.post(UrlCommun + "/prixClient/PrixClientProduit",$scope.PrixClientValue
													).success(
													function(resultat) {
														
															// console.log("resultat
															// id:
															// "+resultat.id);
														$scope.listePrixClientProduit = resultat;
														
														// console.log("$scope.listePrixClientProduit:
														// "+$scope.listePrixClientProduit.prixvente);
														// test sur prix
														if($scope.listePrixClientProduit!=null &&  $scope.listePrixClientProduit.prixvente>0 && $scope.PrixClientValue.idClient!="" && $scope.PrixClientValue.idClient!=undefined)
														{
															$scope.listDetLivraisonVente[index].prixUnitaireHT=$scope.listePrixClientProduit.prixvente;
															$scope.listDetLivraisonVente[index].prixTTC=($scope.listDetLivraisonVente[index].prixUnitaireHT)*(1+$scope.listDetLivraisonVente[index].tauxTVA/100);
															
															
															// console.log("prix
															// vente si >0
															// :"+$scope.listePrixClientProduit.prixvente);
														}else
														{
															$scope.listDetLivraisonVente[index].prixUnitaireHT = $scope.productFilter2.prixUnitaire;
															$scope.listDetLivraisonVente[index].prixTTC=($scope.listDetLivraisonVente[index].prixUnitaireHT)*(1+$scope.listDetLivraisonVente[index].tauxTVA/100);
															// console.log("prix
															// vente standard :
															// "+$scope.productFilter2.prixUnitaire);
														}
													});
										}
								
									
								
								
								$scope.listePrixClientProduit();
                                
								$scope.productFilter = [];

								$scope.getProductFilter(idProduit);

										
								// nouveau affectation des vari
								// console.log("--> click prod:
								// "+$scope.productFilter2.designation+" -- qte:
								// "+$scope.productFilter2.unite);
								$scope.listDetLivraisonVente[index].produitDesignation = $scope.productFilter2.designation;
								$scope.listDetLivraisonVente[index].quantiteStock = $scope.productFilter2.quantite;
								$scope.listDetLivraisonVente[index].unite = $scope.productFilter2.unite;
								$scope.listDetLivraisonVente[index].tauxTVA = $scope.productFilter2.tauxTVA;
								$scope.listDetLivraisonVente[index].uniteSupplementaire=$scope.productFilter2.uniteSupplementaire;
								// console.log("########## prix vente:
								// "+$scope.listePrixClientProduit.prixvente);
								// test sur prix
								if($scope.listePrixClientProduit!=null &&  $scope.listePrixClientProduit.prixvente>0)
								{
									$scope.listDetLivraisonVente[index].prixUnitaireHT=$scope.listePrixClientProduit.prixvente;
									$scope.listDetLivraisonVente[index].prixTTC=($scope.listDetLivraisonVente[index].prixUnitaireHT)*(1+$scope.listDetLivraisonVente[index].tauxTVA/100);
									
									// console.log("prix vente si >0
									// :"+$scope.listePrixClientProduit.prixvente);
								}else
								{
									$scope.listDetLivraisonVente[index].prixUnitaireHT = $scope.productFilter2.prixUnitaire;
									$scope.listDetLivraisonVente[index].prixTTC=($scope.listDetLivraisonVente[index].prixUnitaireHT)*(1+$scope.listDetLivraisonVente[index].tauxTVA/100);
									
									// console.log("prix vente standard :
									// "+$scope.productFilter2.prixUnitaire);
								}
								$scope.listDetLivraisonVente[index].errorQte = false;
								if($scope.isSerialisable(idProduit)){
									if(!$scope.listDetLivraisonVente[index].produitsSerialisable || ($scope.listDetLivraisonVente[index].produitsSerialisable.length == 0))	{
										
										var produitCouranteRech = {produitId :idProduit,critereSpeciale:"produit-non-vendue"};
										
										
										if(whithGetProduitSerialisable == true){
											
											$http.post(
													UrlCommun
															+ "/produitSerialisable/rechercheProduitSerialisableMulticritere",
															produitCouranteRech)
											.success(
													function(resultat) {
												

														$scope.listDetLivraisonVente[index].produitsSerialisable = resultat.produitSerialisableValues;
												
													});
											
										}
								
										
									}
									
								}else
									{
									     $scope.listDetLivraisonVente[index].produitsSerialisable = null;
									}
					
								

							}

							$scope.checkQte = function(qteStock, qte, index) {

								if (qte > qteStock) {
									$scope.listDetLivraisonVente[index].errorQte = true;
								} else {
									$scope.listDetLivraisonVente[index].errorQte = false;
								}
								// calcul qte*prixunitaire//
								
							 $scope.listDetLivraisonVente[index].prixTotalHT=$scope.productFilter[0].prixUnitaire*$scope.qte;

							}
							// $scope.updateProduitCommandDetails= function
							// (produitCommande){
							//					
							// $scope.productFilter=[];
							// $scope.sousFamilleFilter=[];
							//            		
							// $scope.getProductFilter(produitCommande.id);
							//		        	 
							// if ($scope.productFilter.length >0){
							// produitCommande.produitDesignation=$scope.productFilter[0].designation;
							// produitCommande.prixUnitaireHT=$scope.productFilter[0].prixUnitaire;
							//		        		
							// }
							// }

							/** Fin de gestion des Produit */

							/** ****fin Gestion des produits****** */
							/***************************************************
							 * Gestion des ProduitBS & traitementsFacon
							 **************************************************/

							// champ autoSaisie du champs: referenceBS
							$scope.listReferenceBS = [];
// $scope.select2TaggingOptions = {
// 'multiple' : true,
// 'simple_tags' : true,
// 'tags' : function() {
// // reload de la liste des RefBS
// $http
// .get(
// UrlAtelier
// + "/bonsortiefini/getAvailableListBonSortieFiniRef")
// .success(
// function(resultat) {
// $log
// .debug("--ResultatListBS "
// + resultat.length);
// $scope.listReferenceBS = resultat;
// // $log.debug("listBS :
// // "+resultat.list.length);
// // $log.debug("--listReferenceBS
// // :
// // "+JSON.stringify($scope.listReferenceBS,
// // null, " "));
//
// });
// $log.debug("--OnClicklistReferenceBS : "
// + JSON.stringify(
// $scope.listReferenceBS,
// null, " "));
// return $scope.listReferenceBS;
// }
// };

							// bouton ValiderBS: tagReferenceBSList va contenir
							// les referencesBS selectionnées, puis cette liste
							// va etre affectée au champ : infoSortie sous la
							// forme de ref1-ref2-..
							// L'API appelée dépend de la nature de livraison
							// (Fini / Facon )

							$scope.validerBS = function() {

								$log
										.debug(" Recherche des Produits appartenants à ces Bons de Sortie ...");
								$log.debug("-- tagReferenceBSList : "
										+ JSON.stringify(
												$scope.tagReferenceBSList,
												null, "    "));

								// idBonLivVente: si undefined -> urlValier SANS
								// idBonLivVente, sinon -> idBonLivVente AVEC
								// idBonLivVente
								$log.debug("Valider : idBonLivVente "
										+ $scope.idBonLivVente);

								// Type livraison fini
								if ($scope.natureLivraison == 'FINI') {
									$scope.validerNatureFini();

								} else { // $scope.natureLivraison == 'facon'

									$scope.validerNatureFacon();
								}
							}

							$scope.validerNatureFini = function() {

								$log.debug("Log1: idBonLiv = "
										+ $scope.idBonLivVente);

								BonLivraisonServices
										.validateFini(
												$scope.tagReferenceBSList,
												$scope.idBonLivVente)
										.then(
												function(resultat) {
													// bouton Valider en mode :
													// Actif :afficher le
													// tableau resultant de
													// DetLivVene
													$scope.modeValider = "actif";
													// setDateInto = dateSortie
													// du 1erBS
													$scope.bonLivraisonVenteCourant.date = resultat.dateSortie;
													// listDetLivraisonVentePRBS
													$scope.listDetLivraisonVentePRBS = resultat.listDetLivraisonVente;
													/*
													 * $log .debug("--
													 * listDetLivraisonVentePRBS
													 * Size : " +
													 * $scope.listDetLivraisonVentePRBS.length);
													 * 
													 * $log .debug("--
													 * listDetLivraisonVentePRBS : " +
													 * JSON .stringify(
													 * $scope.listDetLivraisonVentePRBS,
													 * null, ' '));
													 */

												},
												function(error) {
													console
															.log(error.statusText);
												});
							}

							$scope.validerNatureFacon = function() {

								$log.debug("Log1: idBonLiv = "
										+ $scope.idBonLivVente);

								BonLivraisonServices
										.validateFacon(
												$scope.tagReferenceBSList,
												$scope.idBonLivVente)
										.then(
												function(resultat) {
													// bouton Valider en mode :
													// Actif :afficher le
													// tableau resultant de
													// DetLivVene
													$scope.modeValider = "actif";
													// setDateInto = dateSortie
													// du 1erBS
													$scope.bonLivraisonVenteCourant.date = resultat.dateSortie;
													// listDetLivraisonVentePRBS
													var listeDetLivraison = resultat.listDetLivraisonVente;

													$scope.listDetLivraisonVentePRBS = [];
													$log
															.debug("-listeDetLivraison--"
																	+ JSON
																			.stringify(
																					listeDetLivraison,
																					null,
																					" "));

													angular
															.forEach(
																	listeDetLivraison,
																	function(
																			elementDetLivraison,
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
																							id : elementDetLivraison.traitementFaconId
																						});
																		elementDetLivraison.designationTraitement = ligneTraitement[0].designation;
																		$scope.listDetLivraisonVentePRBS
																				.push(elementDetLivraison);
																	})

												/*
												 * $log .debug("--
												 * listDetLivraisonVentePRBS
												 * Size : " +
												 * $scope.listDetLivraisonVentePRBS.length);
												 * 
												 * $log .debug("--
												 * listDetLivraisonVentePRBS : " +
												 * JSON .stringify(
												 * $scope.listDetLivraisonVentePRBS,
												 * null, ' '));
												 */

												},
												function(error) {
													console
															.log(error.statusText);
												});

							}
							
				            $scope.calculBonVente = function() {
								 
			            		var prixTTC=0;
			    			 angular.forEach($scope.listDetLivraisonVente, function(listDetLivraisonVente,value){
			    				prixTTC+=(listDetLivraisonVente.prixTTC*listDetLivraisonVente.quantite);
			    			
			    			 })
			    			$scope.bonLivraisonVenteCourant.montantTTC =prixTTC;
							 
						 }		
							
							
							
							
							
							
							

							/***************************************************
							 * Gestion de la table Produit: table DIRECTEMENT
							 * editable sur le champ 'Remise' seulement.
							 **************************************************/
							$scope.remiseChanged = function(index) {

								$log.debug("remise changed call");

								if ($scope.idBonLivVente == '') {

									// remiseChangedOnCreation
									$log
											.debug("remiseChangedOnCreation INDEX Changed "
													+ index);
									$log
											.debug("listDetLivraisonVentePRBS Remise : "
													+ $scope.listDetLivraisonVentePRBS[index].remise);

									$scope.listDetLivraisonVentePRBS[index].remise = $scope.listDetLivraisonVentePRBS[index].remise;
									$log
											.debug("-- listDetLivraisonVente After Remise Change : "
													+ JSON
															.stringify(
																	$scope.listDetLivraisonVentePRBS,
																	null, '  '));

								} else {

									// remiseChangedOnModification
									$log
											.debug("remiseChangedOnModification INDEX Changed "
													+ index);
									$log
											.debug("listDetLivraisonVentePRBS Remise : "
													+ $scope.listDetLivraisonVentePRBS[index].remise);

									$log
											.debug("listDetLivraisonVentePRBS Remise : "
													+ $scope.listDetLivraisonVentePRBS[index].remise);

									if ($scope.listDetLivraisonVentePRBS[index].id != null) {
										$log.debug("--old--");
										$scope.bonLivraisonVenteCourant.listDetLivraisonVente[index].remise = $scope.listDetLivraisonVentePRBS[index];

									} else {
										$log.debug("--NEW--");
										$scope.bonLivraisonVenteCourant.listDetLivraisonVente
												.push($scope.listDetLivraisonVentePRBS[index]);

									}

									// $scope.bonLivraisonVenteCourant.listDetLivraisonVente[index].remise
									// =
									// $scope.listDetLivraisonVentePRBS[index].remise;
									$log
											.debug("-- bonLivraisonVenteCourant listDetLivraisonVente After Remise Change : "
													+ JSON
															.stringify(
																	$scope.bonLivraisonVenteCourant.listDetLivraisonVente,
																	null, '  '));
								}

							}

							/***************************************************
							 * Gestion BonLivraison -Vente
							 **************************************************/
							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 ,52,130 ],
								pageSize : 52,
								currentPage : 1
							};

							// Recherche des Bons de Vente
							$scope.rechercherBonLivraisonVente = function(
									bonLivraisonVenteCourant) {
								
								// console.log("===> recherche stock:
								// "+$scope.checkboxModelRech.stock);
								if($scope.checkboxModelRech.stock=="oui"){
									bonLivraisonVenteCourant.stock=true;
								}else
									bonLivraisonVenteCourant.stock=false;
								
								bonLivraisonVenteCourant.natureLivraison = "FINI";
								
							/*
							 * console.log("========>Rech:"+bonLivraisonVenteCourant.idDepot);
							 * $log .debug("----Livraison--***
							 * --bonLivraisonVenteCourant : recherche avant---" +
							 * JSON .stringify( bonLivraisonVenteCourant, null, "
							 * "));
							 * 
							 */
						
							if ($scope.clientActif.blackMode == false) {
						bonLivraisonVenteCourant.declare = "oui";

					}
								$http
										.post(
												UrlAtelier
														+ "/bonlivraison/rechercheMulticritere",
												bonLivraisonVenteCourant)
										.success(
												function(resultat) {
													/*
													 * $log
													 * .debug("----Livraison :
													 * request :
													 * bonLivraisonVenteCourant :
													 * recherche result---" +
													 * JSON .stringify(
													 * $scope.bonLivraisonVenteCourant,
													 * null, " "));
													 */
													$scope.myData = resultat.list;
													// Pagination du resultat de
													// recherche
													// data, page, pageSize
													$scope
															.setPagingData(
																	$scope.myData,
																	$scope.pagingOptions.currentPage,
																	$scope.pagingOptions.pageSize);

												/*
												 * $log .debug("listeBonVente : " +
												 * resultat.list.length); $log
												 * .debug("----resultat.list-----:
												 * recherche result---" + JSON
												 * .stringify( resultat.list,
												 * null, " "));
												 */

													$scope.rechercheBLVenteForm
															.$setPristine();

												/*
												 * $scope.bonLivraisonVenteCourant = {
												 * "referenceBl" : '',
												 * "referenceBs=" : '',
												 * "partieIntId" : '',
												 * "dateLivraisonMin" : '',
												 * "dateLivraisonMax" : '',
												 * "metrageMin" : '',
												 * "metrageMax" : '', "prixMin" :
												 * '', "prixMax" : '',
												 * "natureLivraison" : "FINI",
												 * "avecFacture" : '',
												 * "idDepot":''
												 *  }; $log
												 * .debug("----$scope.bonLivraisonVenteCourant :
												 * rechrche fin---" + JSON
												 * .stringify(
												 * $scope.bonLivraisonVenteCourant,
												 * null, " "));
												 * 
												 */
													
												});

							}

							// Annuler Recherche
							$scope.annulerAjout = function() {
								
									$scope.isCollapsedClientPassager = false;
							$scope.isCollapsedDetailReglement = true;
							$scope.isCollapsedMontants = false;
								

								$scope.traitementEnCours = "false";
								$scope.traitementEnCoursGenererAll = "false";
								$scope.traitementEnCoursGenererLivraison = "false";
								$scope.closeNotif();
								
								$scope.erreurUpdateBL = "false"; 
								 $scope.msgErreurUpdateBL = ""; 
								
								$scope.reglementCourante = {};
								
								 $scope.InitializeArray();
								
								 $scope.traitementEnCours = "false";
								 
								 $scope.hiddenNotifSucc = "false"; 

								// init checkbox : 'non' :rapport sans Prix /
								// 'oui' rapport avec prix
								
								
								$scope.checkboxModel = {
										stock : $scope.clientActif.hasStock?"oui":"non",
										rapportPrix : "oui"
									};
								
								$scope.checkboxModelRech = {
										stock :$scope.clientActif.hasStock?"oui":"non"
									};
								
								

								$scope.idBonLivVente = '';
								$log.debug("Annuler :$scope.idBonLivVente "
										+ $scope.idBonLivVente);
								// bouton Valider en mode : notActive
								$scope.modeValider = "notActif";
								// bouton PDF en mode : notActive
								$scope.modePdf = "notActive";
								// vider la liste des refBS
								$scope.tagReferenceBSList = [];
								$scope.tagReferenceBLivList = [];
								$scope.listTaxeLivraison = [];
								
								$scope.listDetLivraisonVente = [];
								$scope.listDetLivraisonVentePRBS = [];
								$scope.listeDocumentProduit = [];
								// Tableau Prédefini
								/*
								 * $scope.listTaxeLivraisonInit = [ {//FODEC
								 * taxeId: 1, pourcentage: 1, montant: '', },
								 * {//TVA taxeId: 2, pourcentage: 18, montant:
								 * '', }];
								 */
								// initialiser le design des champs
								$scope.creationBLVenteForm.$setPristine();
								$scope.rechercheBLVenteForm.$setPristine();
								// init de l'objet courant
								// $scope.bonLivraisonVenteCourant={};
								
								
								
								$scope.bonLivraisonVenteCourant = {
									"referenceBl" : '',
									"referenceBs" : '',
									"partieIntId" : '',
									"dateLivraisonMin" : '',
									"dateLivraisonMax" : '',
									"metrageMin" : '',
									"metrageMax" : '',
									"prixMin" : '',
									"prixMax" : '',
									"natureLivraison" : "FINI",
									"avecFacture" : '',
									"idDepot":''
								};

								$scope.getPagedDataAsync(
										$scope.pagingOptions.pageSize,
										$scope.pagingOptions.currentPage);

								// interface en mode : list
								$scope.displayMode = "list";
								
								
								
							}

							// AffectationBLVente BonLivVente
							$scope.affectationBLVente = function(bonLVente) {
								
								 $scope.hiddenNotifSucc = "false"; 
								
								 $scope.traitementEnCours = "false";
								
								$scope.natureLivraison = "FINI";
								$scope.listTaxeLivraisonInitMethod();
								$scope.initTaxeRemoved();
								
								
								var defaultIdDepot = 1;
								if($scope.listeMagazinCache && $scope.listeMagazinCache.length >0)
									defaultIdDepot = $scope.listeMagazinCache[0].id;
								
								
								$scope.bonLivraisonVenteCourant = {"date" : new Date(),
										                        "idDepot" : defaultIdDepot,
										                          "modepaiementId" : 1,
										                          "declare":true };
								
								if($scope.clientActif.hasStock)
									$scope.checkboxModel.stock="oui";
								else
									$scope.checkboxModel.stock="non";
							/*
							 * $scope.bonLivraisonVenteCourant = bonLVente ?
							 * angular .copy(bonLVente) : {};
							 */
								if($scope.clientActif.disableVente){
									
									$http
									.get(
											UrlAtelier
													+ "/bonlivraison/getCurrentReference"
													)
									.success(
											function(res) {
												
												$scope.bonLivraisonVenteCourant.reference = res;
												$scope.bonLivraisonVenteCourant.refAvantChangement = res;
											});
									
									
								}
								
								
								// mode edit activé
								$scope.displayMode = "edit";
								
								
								 // Reload Liste des produits
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
								
							}

							// AffectationBLFaconVente BonLivVente
							$scope.affectationBLFaconVente = function(bonLVente) {
								// $scope.natureLivraison ="FACON";
								$scope.listTaxeLivraisonInitMethod();
								$scope.initTaxeRemoved();
								$scope.bonLivraisonVenteCourant = {};
								$scope.bonLivraisonVenteCourant = bonLVente ? angular
										.copy(bonLVente)
										: {};

								// mode edit activé
								$scope.displayMode = "edit";

							}

							// Ajout et Modification Bon de Vente
							$scope.modifierOuCreerBonLVente = function() {

								//$log.debug("modeConsultation/Modification. ");
								
								
								
								// index de la ligne selectionnée dans la Grid.
								var index = this.row.rowIndex;

								// idBonLivVente: va etre affecté à l'Url du
								// service Valider en cas de modification
								$scope.idBonLivVente = $scope.myData[index].id;
								$log.debug("idBonLivVente "
										+ $scope.idBonLivVente);

								// bouton PDF activé
								$scope.modePdf = "actif";

								// nature livraison
								$scope.natureLivraison = $scope.myData[index].natureLivraison;

								// getBonVente
								$http
										.get(
												UrlAtelier
														+ "/bonlivraison/getBonLivraisonById:"
														+ $scope.myData[index].id)
										.success(
												function(datagetBonVente) {
													
													 // recuperation des
														// refBLiv sous le
														// format X-Y-Z
													 var refBC = datagetBonVente.refCommande.split("-");
													 
													 //console.log("----refBLiv---"+refBC);

													 // affectation des
														// references à la liste
														// sous le format X,Y,Z
													 $scope.tagReferenceBLivList = refBC;

													 

													/*$log
															.debug("-- datagetBonVente--- "
																	+ JSON
																			.stringify(
																					datagetBonVente,
																					null,
																					" "));*/

													// init du champ
													// tagReferenceBSList,
												//	$log.debug("-- infoSortie "	+ datagetBonVente.infoSortie);
													//$log.debug("-- infoSortie Length "+ datagetBonVente.infoSortie.length);
												
													$scope.listDetLivraisonVente = [];
													$scope.listSuppDetLivraisonVente = [];

													if ($scope.natureLivraison == "FINI") {
														// BonLivraisonServices.validateFini($scope.tagReferenceBSList,datagetBonVente.id).then(function(resultat){

														// listDetLivraisonVente
														$scope.listDetLivraisonVente = datagetBonVente.listDetLivraisonVente;
														//console.log("datagetBonVente.listDetLivraisonVente:  "+datagetBonVente.listDetLivraisonVente);

														/*$log
																.debug("-%%%- listDetLivraisonVentePRBS : "
																		+ JSON
																				.stringify(
																						$scope.listDetLivraisonVente,
																						null,
																						"    "));*/
														$scope.myData[index].listDetLivraisonVente = $scope.listDetLivraisonVente;
														// }
														// ,function(error){
														// console.log(error.statusText);
														// });
													}
													// Pour type façon pas
													// besoin d'appeler
													// validerFacon. Pas de
													// nécessité de calcul
													// Les champs de calcul
													// (prix) sont introduit
													// dans detailLivraison
													else {
														angular
																.forEach(
																		datagetBonVente.listDetLivraisonVente,
																		function(
																				elementDetLivraison,
																				value) {
																			/*$log
																					.debug("---elementDetLivraison-- : "
																							+ JSON
																									.stringify(
																											elementDetLivraison,
																											null,
																											"    "));*/
																			
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
																								id : elementDetLivraison.traitementFaconId
																							});
																			elementDetLivraison.designationTraitement = ligneTraitement[0].designation;
																			$scope.listDetLivraisonVente
																					.push(elementDetLivraison);
																		})

														/*$log
																.debug("-%%%- listDetLivraisonVentePRBS : "
																		+ JSON
																				.stringify(
																						$scope.listDetLivraisonVente,
																						null,
																						"    "));*/
													}

													$scope.listTaxeLivraison = datagetBonVente.listTaxeLivraison;
													$scope.listeDocumentProduit = datagetBonVente.listDocLivraisonVente;
													// affectation du resultat à
													// myData
													$scope.myData[index].listDetLivraisonVente = $scope.listDetLivraisonVente;
													$scope.myData[index].listTaxeLivraison = $scope.listTaxeLivraison;
													
													
													$scope.bonLivraisonVenteCourant.transporteur = datagetBonVente.transporteur;
													$scope.bonLivraisonVenteCourant.numTelPassager = datagetBonVente.numTelPassager;
													$scope.bonLivraisonVenteCourant.emailPassager = datagetBonVente.emailPassager;
													$scope.bonLivraisonVenteCourant.adressePassager = datagetBonVente.adressePassager;
													
													/*
													 * //TODO SEARCH TYPE PI //
													 * console.log("partieIntId
													 * ------------------------------------------------------------------",datagetBonVente.partieIntId);
													 * var element =
													 * $scope.listePartieInteresseeCache.filter(function(node) {
													 * return
													 * node.id==datagetBonVente.partieIntId;
													 * }); //
													 * console.log("partieIntId
													 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++",
													 * element[0].typePartieInteressee);
													 * 
													 * $scope.myData[index].typePartieInteressee =
													 * element[0].typePartieInteressee;
													 * 
													 */		
												
													// Initialiser le filtre des
													// taxe à éliminer
													$scope.taxeIdRemove = [];
													for (var int = 0; int < $scope.listTaxeLivraison.length; int++) {
														// $scope.taxeIdRemove.push($scope.listTaxeLivraison[int].taxeId);

														// Temporary remove
														// FODEC from list
														if ($scope.listTaxeLivraison[int].taxeId == 1) {
															console
																	.log("index"
																			+ $scope.listTaxeLivraison
																					.indexOf($scope.listTaxeLivraison[int]));
															$scope.listTaxeLivraison
																	.splice(
																			$scope.listTaxeLivraison
																					.indexOf($scope.listTaxeLivraison[int]),
																			1);
														}

													}
													$scope.filterTaxes();

													/*$log
															.debug("getBLId : "
																	+ $scope.myData[index].id
																	+ " : "
																	+ JSON
																			.stringify(
																					$scope.myData[index],
																					null,
																					"  "));*/
													
													
													if(datagetBonVente.reglementId != null){
														
												         $http
								                          .get(
								                              UrlAtelier +
								                              "/reglement/getById:" +
								                              datagetBonVente.reglementId)
								                          .success(
								                              function(datagetReglement) {
								                        
								              
								                              
								                            	  
								                              $scope.reglementCourante = datagetReglement;
								                      
								                              $scope.listeDocumentProduit = datagetReglement.listDocReglement;
								                          
								                             
								                              $scope.finalOperationsList=datagetReglement.listDetailsReglement;
								                              
								                              $scope.finalElementList=datagetReglement.listElementReglement;
								                                 
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
													}else
														
														{
														$scope.AffectationReglement(datagetBonVente);
														  
														
														
														}
													
													
													
													
													
													
													
												});


											
												var dateLivraison= null;

												


												
												if ($scope.myData[index].date !== null) {
													dateLivraison = $scope.modifierFormatDate($scope.myData[index].date);
												} else {
													dateLivraison = null;
												}
							
							
							 
												$scope.bonLivraisonVenteCourant = Object.assign($scope.myData[index],{date:dateLivraison})
													//  $scope.partieInteresseeCourante = $scope.myData[index]
													? angular.copy($scope.myData[index])
													: {};

								// $scope.bonLivraisonVenteCourant = $scope.myData[index] ? angular
								// 		.copy($scope.myData[index])
								// 		: {};

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
							$scope.sauvegarderBonVente = function(bonVente) {
								

								$scope.traitementEnCours = "true";
								
								$scope.refBLIsInvalid = "false"; 
								
								
								if(bonVente.reference){
									if(bonVente.reference.indexOf("-")!= -1) {
										$scope.refBLIsInvalid = "true"; 
										return;
									}
								}
								
								
								
								
								// bonVente.stock=$scope.checkboxModel.stock;
								//console.log(" sauv etat stock: "+$scope.checkboxModel.stock);
								if($scope.checkboxModel.stock=="oui")
								{
									bonVente.stock=true;
								}else bonVente.stock=false;
								
								
								if($scope.checkboxModel.description=="oui")
								{
									bonVente.description=true;
								}else bonVente.description=false;
								

								if (angular.isDefined(bonVente.id)) {
									$log.debug("Sauvegarder Modification : "
											+ bonVente.reference);
									$scope.updateBonVente(bonVente);
								} else {
									$log.debug("Sauvegarder Ajout : "
											+ bonVente.reference);
									$scope.creerBonVente(bonVente);
								}

							}

							// Mise à jour des Bons de Vente
							$scope.updateBonVente = function(bonLVente) {
								//$scope.traitementEnCours = "true";
								
								/*
								var urlValider = UrlAtelier
										+ "/bonlivraison/validate?livraisonVenteId="+ bonLVente.id;
										
										$http.post(urlValider,
												$scope.tagReferenceBSList)
										.success(
												function(resultat) {
													// bouton Valider en mode :
													// Actif :afficher le
													// tableau resultant de
													// DetLivVene
													$scope.modeValider = "actif";
													// listDetLivraisonVente
													$scope.listDetLivraisonVente = resultat.listDetLivraisonVente;
												});*/
						

								bonLVente.listDetLivraisonVente = $scope.listDetLivraisonVente;
								bonLVente.listTaxeLivraison = $scope.listTaxeLivraison;
								bonLVente.listSuppDetLivraisonVente = $scope.listSuppDetLivraisonVente;
								bonLVente.listDocLivraisonVente = $scope.listeDocumentProduit;
								

							//	$log.debug("======== APRES :");
							/*	$log
										.debug("listDetLivraisonVentePRBS : "
												+ JSON
														.stringify(
																bonLVente.listDetLivraisonVente,
																null, "    "));*/
								
								//commente par samer le 11.05.20
								//bonLVente.listTaxeLivraison = $scope.listTaxeLivraisonInit;
								bonLVente.refCommande = $scope.tagReferenceBLivList.join('-');

								// tagReferenceBSList va contenir les
								// referencesBS selectionnées, puis cette liste
								// va etre affectée au champ : infoSortie sous
								// la forme de ref1-ref2-..
								$log.debug("Join  "	+ $scope.tagReferenceBSList.join('-'));
									
								bonLVente.infoSortie = $scope.tagReferenceBSList
										.join('-');

								/*$log
										.debug("Modification bonLVente : "
												+ JSON.stringify(bonLVente,
														null, "  "));*/

								$http
										.post(
												UrlAtelier
														+ "/bonlivraison/updateBonLivraison",
												bonLVente)
										.success(
												function(bonLVenteId) {
													
													$scope.traitementEnCours = "false";
													
										        	$scope.showNotif();
													
													/** Debut contraintes modification **/
													if(bonLVenteId == CODE_ERROR_EDITION_BL_HAS_FACTURE){
														$scope.msgErreurUpdateBL = "Une facture existe avec cette BL"; 
														 $scope.erreurUpdateBL = "true"; 
														// $scope.traitementEnCours = "false";
														 
														return;
													}
														
												    if(bonLVenteId == CODE_ERROR_EDITION_BL_HAS_BON_RETOUR){
												    	$scope.msgErreurUpdateBL = "Un Bon de retour exist avec cette BL"; 
												    	 $scope.erreurUpdateBL = "true"; 
												    	// $scope.traitementEnCours = "false";
												    	return;
													}
												    
												    
												    console.log("Apres Contrainnte modification");
												    
												    /** Fin contraintes modification **/ 

													for (var i = 0; i < $scope.myData.length; i++) {

														if ($scope.myData[i].id == bonLVenteId) {

															$scope.myData[i] = bonLVenteId;
															break;
														}
													}

													// getBonLivVente
													$http
															.get(
																	UrlAtelier
																			+ "/bonlivraison/getBonLivraisonById:"
																			+ bonLVenteId)
															.success(
																	function(
																			dataGetBonLivVente) {
																		
																		$scope.traitementEnCours = "false";

																		// bouton
																		// PDF
																		// activé
																		$scope.modePdf = "actif";
																		$scope.modeValider = "actif";

																		// getTableaux
																		$scope.listTaxeLivraison = dataGetBonLivVente.listTaxeLivraison;
																		$scope.listDetLivraisonVente = dataGetBonLivVente.listDetLivraisonVente;
																		$scope.listeDocumentProduit = dataGetBonLivVente.listDocLivraisonVente;
																		
																		$scope.listSuppDetLivraisonVente = [];
																		// Attributs
																		// de
																		// Recherche
																		$scope.bonLivraisonVenteCourant = dataGetBonLivVente ? angular
																				.copy(dataGetBonLivVente)
																				: {};
																				
																				$scope.bonLivraisonVenteCourant.transporteur = dataGetBonLivVente.transporteur;
																				$scope.bonLivraisonVenteCourant.numTelPassager = dataGetBonLivVente.numTelPassager;
																				$scope.bonLivraisonVenteCourant.emailPassager = dataGetBonLivVente.emailPassager;
																				$scope.bonLivraisonVenteCourant.adressePassager = dataGetBonLivVente.adressePassager;
																		/*$log
																				.debug("get bonLVente : "
																						+ JSON
																								.stringify(
																										$scope.bonLivraisonVenteCourant,
																										null,
																										"  "));*/

																		if ($scope.natureLivraison == "FACON") {
																			$scope.listDetLivraisonVente = [];
																			angular
																					.forEach(
																							$scope.bonLivraisonVenteCourant.listDetLivraisonVente,
																							function(
																									elementDetLivraison,
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
																													id : elementDetLivraison.traitementFaconId
																												});
																								elementDetLivraison.designationTraitement = ligneTraitement[0].designation;
																								$scope.listDetLivraisonVente
																										.push(elementDetLivraison);
																							})
																		}

																		// Initialiser
																		// le
																		// filtre
																		// des
																		// taxe
																		// à
																		// éliminer
																		$scope.taxeIdRemove = [];
																		for (var int = 0; int < $scope.listTaxeLivraison.length; int++) {
																			$scope.taxeIdRemove
																					.push($scope.listTaxeLivraison[int].taxeId);
																		}
																		$scope.filterTaxes();
																		
																		
																		
																		if(dataGetBonLivVente.reglementId != null){
																			
																	         $http
													                          .get(
													                              UrlAtelier +
													                              "/reglement/getById:" +
													                              dataGetBonLivVente.reglementId)
													                          .success(
													                              function(datagetReglement) {
													                        
													              
													                              
													                            	  
													                              $scope.reglementCourante = datagetReglement;
													                      
													                              $scope.listeDocumentProduit = datagetReglement.listDocReglement;
													                          
													                             
													                              $scope.finalOperationsList=datagetReglement.listDetailsReglement;
													                              
													                              $scope.finalElementList=datagetReglement.listElementReglement;
													                                 
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
																		}else
																			
																			{
																			$scope.AffectationReglement(dataGetBonLivVente);
																			  
																			
																			
																			}

																	});
												});

							}
							
							$scope.closeNotifS = function (){
								 $scope.hiddenNotifSucc = "false";
							}

							// Création BonVente
							$scope.creerBonVente = function(bonLVente) {
								
								//$scope.traitementEnCours = "true";
								
								 $scope.hiddenNotifSucc = "false";    
								

								
								bonLVente.refCommande = $scope.tagReferenceBLivList.join('-');

								bonLVente.listDetLivraisonVente = $scope.listDetLivraisonVente;



								bonLVente.listTaxeLivraison = $scope.listTaxeLivraisonInit;
								
								bonLVente.listDocLivraisonVente = $scope.listeDocumentProduit;
								// tagReferenceBSList va contenir les
								// referencesBS selectionnées, puis cette liste
								// va etre affectée au champ : infoSortie sous
								// la forme de ref1-ref2-..
							// $log.debug("Join " +
							// $scope.tagReferenceBSList.join('-'));
									
								bonLVente.infoSortie = $scope.tagReferenceBSList
										.join('-');

								bonLVente.natureLivraison = $scope.natureLivraison;

								
								
							/*	$http.post(UrlAtelier + "/bonlivraison/rechercheMulticritere", {"referenceBl":bonLVente.reference})
								.success(
									function (result) {
										
									
										
										
									}
									);
								
								*/
							
									
									$http
									.post(
											UrlAtelier
													+ "/bonlivraison/createBonLivraison",
											bonLVente)
									.success(
											function(bonLVenteId) {
											

												$scope.idBonLivVente = bonLVenteId;
											

												// getBonLivVente
												$http
														.get(
																UrlAtelier
																		+ "/bonlivraison/getBonLivraisonById:"
																		+ bonLVenteId)
														.success(
																function(
																		dataGetBonLivVente) {
																	
																	
																	$scope.traitementEnCours = "false";
																	
														        	$scope.showNotif();

																	// bouton
																	// Valider
																	// Off
																	$scope.modeValider = "actif";
																	// bouton
																	// PDF
																	// activé
																	$scope.modePdf = "actif";

																	// getTableaux
																	$scope.listTaxeLivraison = dataGetBonLivVente.listTaxeLivraison;
																	$scope.listDetLivraisonVente = dataGetBonLivVente.listDetLivraisonVente;
																	$scope.listeDocumentProduit = dataGetBonLivVente.listDocLivraisonVente;
																	// Attributs
																	// de
																	// Recherche
																	$scope.bonLivraisonVenteCourant = dataGetBonLivVente ? angular
																			.copy(dataGetBonLivVente)
																			: {};
																			
																			$scope.bonLivraisonVenteCourant.transporteur = dataGetBonLivVente.transporteur;
																			$scope.bonLivraisonVenteCourant.numTelPassager = dataGetBonLivVente.numTelPassager;
																			$scope.bonLivraisonVenteCourant.emailPassager = dataGetBonLivVente.emailPassager;
																			$scope.bonLivraisonVenteCourant.adressePassager = dataGetBonLivVente.adressePassager;
																				
																
																	$scope.listDetLivraisonVentePRBS = [];
																

																	if ($scope.natureLivraison == "FACON") {
																		angular
																				.forEach(
																						$scope.bonLivraisonVenteCourant.listDetLivraisonVente,
																						function(
																								elementDetLivraison,
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
																												id : elementDetLivraison.traitementFaconId
																											});
																							elementDetLivraison.designationTraitement = ligneTraitement[0].designation;

																							$log
																									.debug("elementDetLivraison : "
																											+ JSON
																													.stringify(
																															elementDetLivraison,
																															null,
																															"    "));
																							$log
																									.debug("en cours :listDetLivraisonVentePRBS apres remplissage designation : "
																											+ JSON
																													.stringify(
																															$scope.listDetLivraisonVentePRBS,
																															null,
																															"    "));
																							$scope.listDetLivraisonVente
																									.push(elementDetLivraison);
																						})
																	} else {

																		// init
																		// du
																		// champ
																		// tagReferenceBSList,
																		$log
																				.debug("-- infoSortie "
																						+ bonLVente.infoSortie);
																	
																		$log
																				.debug("OLD------ listDetLivraisonVentePRBS :");
																		$log
																				.debug(JSON
																						.stringify(
																								$scope.listDetLivraisonVente,
																								null,
																								"    "));
																	}

																	// Initialiser
																	// le
																	// filtre
																	// des
																	// taxe
																	// à
																	// éliminer
																	$scope.taxeIdRemove = [];
																	for (var int = 0; int < $scope.listTaxeLivraison.length; int++) {
																		$scope.taxeIdRemove
																				.push($scope.listTaxeLivraison[int].taxeId);
																	}
																	$scope.filterTaxes();
																	
																	 // exixte deja
																	
																	 $scope.hiddenNotifSucc = "true";    
																	 
																	 $scope.traitementEnCours = "false";
																	
																	$scope.AffectationReglement(dataGetBonLivVente);
																	
																	
																	
																});
											});
								
			
								

							}

							// Suppression BonVente
							$scope.supprimerBonVente = function() {
								$log.debug("deleting ..");
								// TODO: Service de suppression: à revoir.
								// erreur: operation executée mais avec msg 403!
								// var index = this.row.rowIndex;

								BonLivraisonServices
										.supprimerLivraison(
												$scope.myData[$scope.index].id)
										.then(
												function(resultat) {
													$log
															.debug("Success Delete");
													$scope.myData.splice(
															$scope.index, 1);
													$scope
															.getPagedDataAsync(
																	$scope.pagingOptions.pageSize,
																	$scope.pagingOptions.currentPage);
												},
												function(error) {
													console
															.log(error.statusText);
													// TODO: Temp => jusqu'à
													// résoudre le probleme de
													// 403
													$scope.myData.splice(
															$scope.index, 1);
													$scope
															.getPagedDataAsync(
																	$scope.pagingOptions.pageSize,
																	$scope.pagingOptions.currentPage);
												});

								$scope.closePopupDelete();
							}

							/** * PDF ** */
							// generer rapport apres creation d'un bon de
							// Livraison. mode : Modification/Consultation
							$scope.download = function(id, pRapportPrix,typerapport) {
								// init checkbox : 'non' :rapport sans Prix /
								// 'oui' rapport avec prix
								/*$scope.checkboxModel = {
									rapportPrix : "oui"
								};*/
								$scope.traitementEnCoursGenererLivraison="true";
								

								$log.debug("-- id" + id + pRapportPrix);
								console.log("type BL:"+typerapport);
								var url = UrlAtelier
										+ "/reportgc/bonlivraison?id=" + id
										+ "&avecPrix=" + $scope.checkboxModel.rapportPrix
										+"&typerapport=" +typerapport
										+ "&type=pdf";

											
										var a = document.createElement('a');
										document.body.appendChild(a);
										downloadService.download(url).then(function (result) {
											var heasersFileName = result.headers(['content-disposition']).substring(17);
										var fileName = heasersFileName.split('.');
									var typeFile = result.headers(['content-type']);
									var file = new Blob([result.data], {type: typeFile});
									var fileURL = window.URL.createObjectURL(file);
									if(typeFile == 'application/vnd.ms-excel'){
			
									 // a.href = fileURL;
										 a.download = fileName[0];
										$window.open(fileURL)
										 a.click();
					
									}else{
								
										a.href = fileURL;
										a.download = fileName[0];
									 $window.open(fileURL)
										a.click();
					
									}
										
									$scope.traitementEnCoursGenererLivraison="false";

									});

								// downloadService.download(url).then(
								// 		function(success) {
								// 			$log.debug('success : ' + success);
								// 			// $scope.annulerAjout();
								// 		}, function(error) {
								// 			$log.debug('error : ' + error);
								// 		});
							};
							
							
							$scope.downloadExcel = function(id, pRapportPrix,typerapport) {
								// init checkbox : 'non' :rapport sans Prix /
								// 'oui' rapport avec prix
								$scope.checkboxModel = {
									rapportPrix : "oui"
								};

								$log.debug("-- id" + id + pRapportPrix);
								console.log("type BL:"+typerapport);
								var url = UrlAtelier
										+ "/fiches/bonlivraison?id=" + id
										+ "&avecPrix=" + pRapportPrix
										+"&typerapport=" +typerapport
										+ "&type=pdf";


											
										var a = document.createElement('a');
										document.body.appendChild(a);
										downloadService.download(url).then(function (result) {
											var heasersFileName = result.headers(['content-disposition']).substring(17);
											var fileName = heasersFileName.split('.');
										var typeFile = result.headers(['content-type']);
										var file = new Blob([result.data], {type: typeFile});
										var fileURL = window.URL.createObjectURL(file);
										if(typeFile == 'application/vnd.ms-excel'){
				
										 // a.href = fileURL;
											 a.download = fileName[0];
											$window.open(fileURL)
											 a.click();
						
										}else{
									
											a.href = fileURL;
											a.download = fileName[0];
										 $window.open(fileURL)
											a.click();
						
										}
											
										 
										});

								// downloadService.download(url).then(
								// 		function(success) {
								// 			$log.debug('success : ' + success);
								// 			// $scope.annulerAjout();
								// 		}, function(error) {
								// 			$log.debug('error : ' + error);
								// 		});
							};

							// generer rapport de tous les bons de livraison.
							// mode : List

							// conversion date en String
							function formattedDate(date) {
								var d = new Date(date), month = ''
										+ (d.getMonth() + 1), day = ''
										+ d.getDate(), year = d.getFullYear();

								if (month.length < 2)
									month = '0' + month;
								if (day.length < 2)
									day = '0' + day;
								return [ year, month, day ].join('-');
							}

							// download service

							$scope.downloadAllBonLiv = function(
									bonLivraisonVenteCourant) {
								$scope.traitementEnCoursGenererAll = "true";

								bonLivraisonVenteCourant.referenceBs='';
								if($scope.checkboxModelRech.stock=="oui"){
									bonLivraisonVenteCourant.stock=true;
								}else
									bonLivraisonVenteCourant.stock=false
									

									console.log("etat stock generer: "+bonLivraisonVenteCourant.stock);
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
								if(angular.isDefined(bonLivraisonVenteCourant.idDepot)){
									newIdDepot = bonLivraisonVenteCourant.idDepot;
									
								}
								
								
								var newPartieIntId = '';
								if(angular.isDefined(bonLivraisonVenteCourant.partieIntId)){
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
										+ "&stock="+bonLivraisonVenteCourant.stock
										+ "&idDepot="+newIdDepot
									    + "&groupeClientId="+bonLivraisonVenteCourant.groupeClientId
										+ "&type=pdf";

								$log.debug("--downloadAllBonLiv URL" + url);


							
								var a = document.createElement('a');
								document.body.appendChild(a);
								downloadService.download(url).then(function (result) {
									var heasersFileName = result.headers(['content-disposition']).substring(17);
									var fileName = heasersFileName.split('.');
								var typeFile = result.headers(['content-type']);
								var file = new Blob([result.data], {type: typeFile});
								var fileURL = window.URL.createObjectURL(file);
								if(typeFile == 'application/vnd.ms-excel'){
		
								 // a.href = fileURL;
									 a.download = fileName[0];
									$window.open(fileURL)
									 a.click();
				
								}else{
							
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
							
							
							// download service

							$scope.downloadAllBonLivExcel = function(
									bonLivraisonVenteCourant) {
								$scope.traitementEnCoursGenererAll = "true";

								bonLivraisonVenteCourant.referenceBs='';
								if($scope.checkboxModelRech.stock=="oui"){
									bonLivraisonVenteCourant.stock=true;
								}else
									bonLivraisonVenteCourant.stock=false
									

									console.log("etat stock generer: "+bonLivraisonVenteCourant.stock);
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
								if(angular.isDefined(bonLivraisonVenteCourant.idDepot)){
									newIdDepot = bonLivraisonVenteCourant.idDepot;
									
								}
								
								
								var newPartieIntId = '';
								if(angular.isDefined(bonLivraisonVenteCourant.partieIntId)){
									newPartieIntId = bonLivraisonVenteCourant.partieIntId;
									
								}
								
							// console.log("===============>download:
							// idDepot:"+bonLivraisonVenteCourant.idDepot);
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
										+ "&natureLivraison="
										+ bonLivraisonVenteCourant.natureLivraison
										+ "&avecFacture="
										+ bonLivraisonVenteCourant.avecFacture
										+ "&stock="+bonLivraisonVenteCourant.stock
										+ "&idDepot="+newIdDepot
										+ "&groupeClientId="+bonLivraisonVenteCourant.groupeClientId
										+ "&type=pdf";

								$log.debug("--downloadAllBonLiv URL" + url);

								
								var a = document.createElement('a');
								document.body.appendChild(a);
								downloadService.download(url).then(function (result) {
									var heasersFileName = result.headers(['content-disposition']).substring(17);
									var fileName = heasersFileName.split('.');

									 fileName[0] =  'Liste_des_Bons_de_Livraisons_' + formattedDate(new Date());

								var typeFile = result.headers(['content-type']);
								var file = new Blob([result.data], {type: typeFile});
								var fileURL = window.URL.createObjectURL(file);
								if(typeFile == 'application/vnd.ms-excel'){
		
									a.href = fileURL;
									 a.download = fileName[0];
									//$window.open(fileURL)
									 a.click();
				
								}else{
							
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

							/***************************************************
							 * Gestion des Grids de recherche
							 **************************************************/
							$scope.colDefs = [];
							$scope
									.$watch(
											'myData',
											function() {
												$scope.colDefs = [


													/*
													 * { field :
													 * 'typePartieInteressee',
													 * displayName :
													 * 'typePartieInteressee', width :
													 * '15%' },
													 */
													{
														field: 'reference',
														displayName: 'Réf.BL',
														//	width : '15%'
													},
													/*
													 * { field :
													 * 'refexterne',
													 * displayName :
													 * 'Réf.Externe', width :
													 * '15%' },
													 */
													{
														field: 'partieIntAbreviation',
														displayName: 'Client',
														//	width : '36%'
													},
													// {
													// field : 'infoSortie',
													// displayName :
													// 'Réf.BS',
													// width:'10%'
													// },
													{
														field: 'date',
														displayName: 'Date Liv.',
														cellFilter: "date: 'yyyy-MM-dd'",
														//width : '7%'
													},
													{
														field: 'depot',
														displayName: 'Magasin',
														//	width : '10%'
													},

													{
														field: 'montantHTaxe',
														displayName: 'Montant HT',
														cellFilter: 'prixFiltre',
														//width:'8%'
													},
													{
														field: 'montantTaxe',
														displayName: 'Mont. Taxe',
														cellFilter: 'prixFiltre',
														//	width:'8%'
													},
													{
														field: 'montantTTC',
														displayName: 'Montant TTC',
														cellFilter: 'prixFiltre',
														//	width:'8%'
													},
													/*{
														field : 'montantTTC',
														displayName : 'Prix Totale',
														cellFilter : 'prixFiltre',
														width : '10%'
													},*/


													// {
													// field :
													// 'natureLivraison',
													// displayName : 'Nature
													// livraison',
													// width:'10%'
													// },
													{
														field: '',
														//	width : '5%',
														cellTemplate:


														`<div class="ms-CommandButton float-right"  ng-show="!rowform.$visible" >
															<button class="ms-CommandButton-button ms-CommandButton-Gpro  " ng-click="modifierOuCreerBonLVente()">
															<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
															</button>
															<button class="ms-CommandButton-button"  ng-click="showPopupDelete(5)" permission="['Vente_Delete']">
														<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
															</button>
																</div> `,

														// '<div class="buttons" >'
														// 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerBonLVente()">	<i class="fa fa-fw fa-pencil"></i></button>'
														// 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(5)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
													}];
											});

							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
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
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																UrlAtelier
																		+ "/bonlivraison/rechercheMulticritere",
																bonLivraisonVenteCourant)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.list
																			.filter(function(
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
												bonLivraisonVenteCourant.natureLivraison = "FINI";
												$http
														.post(
																UrlAtelier
																		+ "/bonlivraison/rechercheMulticritere",
																bonLivraisonVenteCourant)
														.success(
																function(
																		largeLoad) {
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
								data : 'myData',
								columnDefs : 'colDefs',
								enablePaging : true,
								showFooter : true,
								enableColumnResize: true,
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};
							/** Fin de gestion des Grids de la BonVente */

						} ]);

app.filter('reverse', function() {
	  return function(items) {
	    return items.slice().reverse();
	  };
	});
