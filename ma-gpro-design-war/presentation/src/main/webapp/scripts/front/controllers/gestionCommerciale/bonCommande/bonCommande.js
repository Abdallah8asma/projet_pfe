'use strict'
angular
		.module('gpro.gcVenteBC', [])
		.controller(
				'VenteBCCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$rootScope',
						'$log',
						'downloadService',
						'UrlAtelier',
						'UrlCommun',
						'$window',
						function($scope, $http, $filter,$rootScope, $log, downloadService, UrlAtelier,UrlCommun ,$window) {
							
							$log.info("----------- Vente BC ----------");
							//** Variables Recherche
							$scope.listProduitCommandes = [];
							$scope.ListClientCommandeVenteCache = [];

							$scope.ListeDevise = [];
							
							$scope.hiddenNotif ="true";
							
							$scope.traitementEnCours = "false";
							$scope.traitementEnCoursCommande = "false";
							
							$scope.traitementEnCoursAllProduit = "false";	
							
							/**************************************
							 * Notification *
							 **************************************/
							$scope.showNotif = function(){
								$scope.hiddenNotif ="false";
							}
													
							$scope.closeNotif = function(){
								$scope.hiddenNotif ="true";
							}
							
							
							
							//**Variables Modif/Ajout
							//init objetCourant
							$scope.commandeVenteCourante =   { 
													  "reference": "",
													  "partieInteresseId": "",
													  "idProduitParRef": "",
													  "idProduitParDesignation": "",
													  "dateIntroductionDu": "",
													  "dateIntroductionA": "",
													  "dateLivraisonDu": "",
													  "dateLivraisonA": "",
													  "quantiteDu": "",
													  "quantiteA": "",
													  "coutDu": "",
													  "coutA": "",
													   "type" :"",
													   "devise":"2"
													};
							
							
							
							/************************************  DEBUT REGLEMENT ****************************/
							
							$scope.isCollapsedDetailReglement = true;
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
					                	  
					                	reglement.ajoutSpecial = "REGLEMENT_AJOUT_SPECIAL_BC";
					                	
					                	reglement.refBC = $scope.commandeVenteCourante.reference;
					                   
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
					                    
					                    reglement.listElementReglement = $scope.finalBLList;
					                   
					                    
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
							
							
				
					        
					        $scope.AffectationReglement = function(bc) {
					        	

				                   
			                    $('.elementReglement').hide();
			                      $('.BL_FACT_Reglement').show();
			                      
			                      $scope.disableClient =false;
			                      $scope.disableValider = false;
			                      
			                     $scope.reglementCourante = {
			                    		 
			                    		 "date":new Date(),
			                    		 "partieIntId":bc.partieInteresseId,
			                    		 "idDepot":bc.idDepot,
			                    		
			                    		 "refBC":bc.reference
			                    		 
			                    		 
			                     };
			                     
			                     
			                     $scope.finalBLList = [];
			                     
			                     
			                  /*    var blObj = {
			                    		 "refBL":bc.reference,
			                    		  "dateEcheance":bc.date,
			                    		  "montant":bc.montantTTC,
			                    		  "montantDemande":bc.montantTTC
			                     }
			                     
			                     $scope.finalBLList.push(blObj);
			                     */
			                     
			                     
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
				                      montant: $scope.commandeVenteCourante.montantTTC,
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
							
							
							
							// Tableau de Taxe Prédefini

							$scope.listTaxeLivraisonInitMethod = function() {
								//if ($scope.natureLivraison == "FINI") {
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
								/*} else {
									$scope.listTaxeLivraisonInit = [ {// TVA
										taxeId : 2,
										pourcentage : 19,
										montant : '',
									} ];
								}*/
							}
							$scope.listeProduitCache = [];
							$scope.listeDocumentCommandeVente = [];
							//**Variable Grid
							$scope.myDataCommandeVente = [];
							//bouton pdf hide
              				$scope.modePdf = "notActive";
							
							$scope.displayMode = "list";
							
							/*********************************************************
							 * Gestion de Cache des Referentiels Gestion Commerciale *
							 *********************************************************/
							$scope.ListEtatCommandeVenteCache = [];
							$scope.ListTypeCommandeVenteCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.ListTypeDocumentCache = [];
							$scope.ListSousFamilleProduitCache = [];
							
							// Liste des PartieInteressee (avec FamilleId=1)
							$scope.listeClientCache = function() {
								$http
										.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPartieInteressee) {

													$scope.listeClientCache = dataPartieInteressee;
												});
							}
							
							// Liste des SiteeCache
							$scope.listeSitesPartieInteresseeCache = function() {
								$http
										.get(UrlCommun+"/gestionnaireCache/listeSitePartieInteresseeCache")
										.success(
												function(dataSiteCache) {
													console.log("listSiteCache : "+ dataSiteCache.length);
													$scope.listeSitePartieInteresseeCache = dataSiteCache;

												});
							
							}
						
						// Liste TypeDocumentCache
//							$scope.listeTypeDocumentsCache = function() {
//								$http
//										.get(baseUrl+"/gestionnaireCache/listeTypeDocumentCache")
//										.success(
//												function(dataTypeDocumentCache) {
//													console.log("listTypeDocumentCache : " + dataTypeDocumentCache.length);
//													$scope.ListTypeDocumentCache = dataTypeDocumentCache;
//												});
//							}
//							
							// Liste des SousFamilleCache
							$scope.ListSousFamillesProduitCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeSousFamilleProduitCache")
										.success(
												function(
														dataSousFamilleProduitCache) {
													$log.debug("*LISTESousFamilleProduitCache :"
																	+ dataSousFamilleProduitCache.length);
													$scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;

												});
							}
							
							// Liste des Agent
//							$scope.ListAgent = function() {
//								$http
//										.get(baseUrlGc+"/agentGc/all")
//										.success(
//												function(data) {
//													console.log("listAgent: " + data.length);
//													$scope.listAgent = data;
//
//											VenteCache();
//							$scope.listeEtatCommandeV	});
//							}

							
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
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeProduitCache").success(
										function(data) {
											console.log("listeProduitCache "+data.length);
											$scope.listeProduitCache = data;

										});
							}



							     // Liste des Devises
                     $scope.ListeDevise = function () {
                       $http.get(UrlCommun + '/devise/all').success(function (dataDevise) {
                        $scope.ListeDevise = dataDevise;
                                });
                            };
							

							
							$scope.ListeDevise();

							$scope.listeClientCache();
							$scope.listeTaxes();
//							$scope.listeTypeCommandeenteCache();
							$scope.listeSitesPartieInteresseeCache();
//							$scope.listeTypeDocumentsCache();
							$scope.ListSousFamillesProduitCache();
							$scope.listeProduitCache();
//							$scope.ListAgent();
							/************************************************/
							
							/************************************************/
							$scope.pagingOptions = {
										pageSizes : [ 5, 10, 13 ],
										pageSize : 13,
										currentPage : 1
									};

							$scope.cancelAddCommandeVente = function(rowform, index, id, designation){
						    		  if (angular.isDefined(id)) {
						    			 
						    				  $scope.deleteValue="non";
						    				  rowform.$cancel();
						    				  console.log("CANCEL");
						    		  }else{	
						    				  $scope.deleteValue=="oui"
						    				  $scope.listProduitCommandes.splice(index, 1);
									    	  rowform.$cancel();
						    		}
						    		  $scope.deleteValue="oui";
						    }
							// ** Ajout Bon de Commande de Vente
							$scope.AffectationVenteBC = function() {
								
								$scope.listTaxeLivraisonInitMethod();
								$scope.initTaxeRemoved();

								$scope.commandeVenteCourante={dateIntroduction : new Date(),
									"devise":"2"
								
								};
								
								$http
								.get(
										UrlAtelier
												+ "/commandeVente/getCurrentReferenceByType:Commande"
												)
								.success(
										function(res) {
											
											 
											$scope.commandeVenteCourante.reference = res;
											$scope.commandeVenteCourante.refAvantChangement = res;
										});
								
//								$scope.commandeVenteCourante = commande ? angular
//										.copy(commande) : {};

								$scope.displayMode = "edit";
							}
							
							
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.traitementEnCoursCommande = "false";

								$scope.traitementEnCours = "false";	
								$scope.traitementEnCoursAllProduit = "false";	

								$scope.closeNotif();
								
								$scope.reglementCourante = {};
								
								 $scope.InitializeArray();
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
              					//vider le tab
              					$scope.varPrix = [{index:'', prix:''}];
              					//init objetCourant
              					$scope.commandeVenteCourante =   {
										  "reference": "",
										  "partieInteresseId": "",
										  "idProduitParRef": "",
										  "idProduitParDesignation": "",
										  "dateIntroductionDu": "",
										  "dateIntroductionA": "",
										  "dateLivraisonDu": "",
										  "dateLivraisonA": "",
										  "quantiteDu": "",
										  "quantiteA": "",
										  "coutDu": "",
										  "coutA": ""
										};
								$scope.rechercherCommandeVente({});
								$scope.listProduitCommandes = [];
								$scope.listTaxeLivraison = [];
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
								$scope.closeNotif();
							}
							
							$scope.rechercherCommandeVente = function(commandeVenteCourante){
								
								commandeVenteCourante.type = "Commande";
								
								$http
								.post(
										UrlAtelier+ "/commandeVente/rechercheMulticritere",
										commandeVenteCourante)
								.success(
										function(data) {
											
											$scope.myDataCommandeVente = data.listCommandeVente;
										});
							}

							/*** PDF ***/
							//conversion date en String
							function formattedDate(date) {
							    var d = new Date(date),
							        month = '' + (d.getMonth() + 1),
							        day = '' + d.getDate(),
							        year = d.getFullYear();

							    if (month.length < 2) month = '0' + month;
							    if (day.length < 2) day = '0' + day;
							    return [year,month,day].join('-');
							}

                      		//generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
              				$scope.download = function(id,pRapportPrix,type,avecEntete,numrapport) {
    							$scope.traitementEnCoursCommande = "true";

              					
              				//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
              				 	 $scope.checkboxModel= {
              				       rapportPrix : "oui"
              				     };

              				 	 
							
								
								
									console.log("inn commande");
								
										var url = UrlAtelier+"/reportgc/bonCommande?id=" + id
															+ "&avecPrix="+pRapportPrix
															+ "&typerapport="+type
															+ "&avecEntete="+avecEntete
															+"&type=pdf"
															+ "&numrapport="+numrapport;


														

															
															var a = document.createElement('a');
															document.body.appendChild(a);
															downloadService.download(url).then(function (result) {
																console.log(result.headers(['content-disposition']))
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
																console.log(result.headers(['content-disposition']))
																a.href = fileURL;
																a.download = fileName[0];
															 $window.open(fileURL)
																a.click();
											
															}
															$scope.traitementEnCoursCommande = "false";

																
															 
															});
											

										// downloadService.download(url).then(
										// 				function(success) {
										// 					console.log('success : '+ success);
															
										// 				},
										// 				function(error) {
										// 					console.log('error : '+ error);
										// 				});
								
								
								
							};
        						
							 $scope.dowloadListCmdvente = function (commandeAchatCourante) {
							    	$scope.traitementEnCoursAllProduit = "true";
									 
									 
									$http({
										url: UrlAtelier + "/fiches/listCommandesVente",
										method: "POST",
										data: commandeAchatCourante, // this is your json
																		// data string
										headers: {
											'Content-type': 'application/json',
										},
										responseType: 'arraybuffer'
									}).success(function (data, status, headers, config) {

										 $scope.traitementEnCoursAllProduit = "false";
										var blob = new Blob([data], { type: "application/vnd.ms-excel" });


										var fileName = 'Vente-Liste_Commandes_' + formattedDate(new Date());
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
								}



									
							$scope.dowloadListCmdventeDevise = function (commandeAchatCourante) {
								$scope.traitementEnCoursAllProduit = "true";
								 
								 
								$http({
									url: UrlAtelier + "/fiches/listCommandesVenteDevise",
									method: "POST",
									data: commandeAchatCourante, // this is your json
																	// data string
									headers: {
										'Content-type': 'application/json',
									},
									responseType: 'arraybuffer'
								}).success(function (data, status, headers, config) {

									 $scope.traitementEnCoursAllProduit = "false";
									var blob = new Blob([data], { type: "application/vnd.ms-excel" });


									var fileName = 'Vente-Liste_Commandes_' + formattedDate(new Date());
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
							}


							
	
        					//generer rapport de tous les bons de livraison. mode : List 
							$scope.downloadAllCommandeVente = function(commandeVenteCourante) {
								
								$scope.traitementEnCoursAllProduit = "true";	
								 	
								var newdateLivBCMinFormat="";
								if(angular.isDefined(commandeVenteCourante.dateLivraisonDu)){
									
									if(commandeVenteCourante.dateLivraisonDu != ""){
										newdateLivBCMinFormat = formattedDate(commandeVenteCourante.dateLivraisonDu);
										console.log("===== newdateLivBCMinFormat "+newdateLivBCMinFormat);
									}else{
										console.log("===== newdateLivBCMinFormat is Null");
										newdateLivBCMinFormat = "";
									}
								}else{
									console.log("==dateLivraisonDu Undefined");
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(commandeVenteCourante.dateLivraisonA)){
									
									if(commandeVenteCourante.dateLivraisonA != ""){
										newdateLivBCMaxFormat = formattedDate(commandeVenteCourante.dateLivraisonA);
									//	console.log("===== newdateLivBCMaxFormat "+newdateLivBCMaxFormat);
									}else{
										console.log("===== newdateLivBCMaxFormat is Null");
										newdateLivBCMaxFormat = "";
									}
								}else{
									console.log("==dateLivraisonA Undefined");
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(commandeVenteCourante.dateLivraisonDu)){
									
									if(commandeVenteCourante.dateLivraisonDu != ""){
										newdateIntroBCMinFormat = formattedDate(commandeVenteCourante.dateLivraisonDu);
										console.log("===== newdateIntroBCMinFormat "+newdateIntroBCMinFormats);
									}else{
										console.log("===== newdateIntroBCMinFormat is Null");
										newdateIntroBCMinFormat = "";
									}
								}else{
									console.log("==dateLivraisonDu Undefined");
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(commandeVenteCourante.dateIntroductionA)){
									
									if(commandeVenteCourante.dateIntroductionA != ""){
										newdateIntroBCMaxFormat = formattedDate(commandeVenteCourante.dateIntroductionA);
										console.log("===== newdateIntroBCMaxFormat "+newdateIntroBCMaxFormat);
									}else{
										console.log("===== newdateIntroBCMaxFormat is Null");
										newdateIntroBCMaxFormat = "";
									}
								}else{
									console.log("==dateIntroductionA Undefined");
								}


								console.log("-- commandeVenteCourante" + JSON.stringify(commandeVenteCourante, null, "  ") );

								var url;
								console.log("PI  "+commandeVenteCourante.vTypePartieInteressee );
								 	if(commandeVenteCourante.vTypePartieInteressee == null){
								 	commandeVenteCourante.vTypePartieInteressee = "";
								 }

								console.log("Produit  "+commandeVenteCourante.vProduit );
								 	if(commandeVenteCourante.vProduit == null){
								 	commandeVenteCourante.vProduit = "";
								 }

			        			url = UrlAtelier + "/report/listCommandeVente?reference=" + commandeVenteCourante.vReference
								 					 + "&partieInteressee=" + commandeVenteCourante.vTypePartieInteressee
								 					 + "&produit="+commandeVenteCourante.vProduit
													 + "&dateIntroductionDu="+newdateIntroBCMinFormat
													 + "&dateIntroductionA="+newdateIntroBCMaxFormat
													 + "&dateLivraisonDu="+newdateLivBCMinFormat
													 + "&dateLivraisonA="+newdateLivBCMaxFormat
													 + "&typeCommande="+commandeVenteCourante.vTypeCommande
													 + "&etatCommande="+commandeVenteCourante.vEtatCommande
													 + "&quantiteDu="+commandeVenteCourante.quantiteDu
													 + "&quantiteA="+commandeVenteCourante.quantiteA
													 + "&coutDu="+commandeVenteCourante.coutDu
													 + "&coutA="+commandeVenteCourante.coutA
													 + "&type=pdf";
				                  
												 console.log("-- URL" + url );
				
												 var a = document.createElement('a');
												 document.body.appendChild(a);
												 downloadService.download(url).then(function (result) {
														$scope.traitementEnCoursAllProduit = "false";	

													var heasersFileName = result.headers(['content-disposition']).substring(17);
													var fileName = heasersFileName.split('.');
												var typeFile = result.headers(['content-type']);
												var file = new Blob([result.data], {type: typeFile});
												var fileURL = window.URL.createObjectURL(file);
												if(typeFile == 'application/vnd.ms-excel'){
													console.log('llll excel');
												 // a.href = fileURL;
													 a.download = fileName[0];
													$window.open(fileURL)
													 a.click();
								
												}else{
													console.log('llll pdf');
													a.href = fileURL;
													a.download = fileName[0];
												 $window.open(fileURL)
													a.click();
								
												}
													
												 
												});
								

								//  downloadService.download(url).then(
								// 		 function(success) {
								// 			// console.log('success : ' + success);
								// 		 }, function(error) {
								// 			// console.log('error : ' + error);
								// 		 });
							};	
						
							
							
							/***************************************************
							 * Notifications
							 **************************************************/
							
							$scope.hiddenNotif ="true";
							
							
							$scope.showNotif = function(){
								$scope.hiddenNotif ="false";
							}
							
							$scope.closeNotif = function(){
								$scope.hiddenNotif ="true";
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
							
							/***************************************************
							 * Gestion des Bon de commande
							 **************************************************/
							
							$scope.isLoading = false;
							
							// Ajout et Modification CommandeVente
							$scope.modifierOuCreerCommandeVente = function() {
								
								$scope.isLoading = true;
								
								var index = this.row.rowIndex;
								// getCommandeVente
								$http
										.get(
												UrlAtelier
														+ "/commandeVente/getById:"+
														 $scope.myDataCommandeVente[index].id)
										.success(
												function(datagetCommandeVente) {

													$log.debug("getById : "+ $scope.myDataCommandeVente[index].id +" \n  "+JSON.stringify(datagetCommandeVente,null,"  ") );
													//produitCommande
													$scope.listProduitCommandes = datagetCommandeVente.listProduitCommandes;
													

													//disable update de la dropList 'Produit' 
					 								angular.forEach($scope.listProduitCommandes, function(produitCommande, key){
									            		produitCommande.checked = true;

//									            		$scope.updateProduitCommandDetails(produitCommande);
									            		
									            		$scope.productFilter=[];
									            		$scope.sousFamilleFilter=[];
									            		
									            		$scope.getProductFilter(produitCommande.produitId);
											        	 
											        	if ($scope.productFilter.length >0){
											        		produitCommande.designation=$scope.productFilter[0].designation;
											        		//produitCommande.prixUnitaire=$scope.productFilter[0].prixUnitaire;
											        		
											        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
												        	
											        		if($scope.sousFamilleFilter.length>0){
											        			produitCommande.sousFamille=$scope.sousFamilleFilter[0].designation;
											        		}
											        	}
													});

					 							// bouton PDF activé
					              					$scope.modePdf = "actif";
					 								$scope.isLoading=false;
					 								
													//document
//													$scope.listeDocumentCommandeVente = datagetCommandeVente.documentCommandeVentes;
					 								/**** Added on 30 03 2018 ****/
					 								$scope.listProduitTaxes = datagetCommandeVente.listProduitTaxes;
					 								console.log("edit: liste taxe size: "+datagetCommandeVente.listProduitTaxes.length);
					 								$scope.myDataCommandeVente[index].listProduitTaxes = $scope.listProduitTaxes;
					 								/****************************/
													$scope.myDataCommandeVente[index].listProduitCommandes = $scope.listProduitCommandes;
//													$scope.myDataCommandeVente[index].documentCommandeVentes = $scope.listeDocumentCommandeVente;
													
													
													
													/*********** Added on 30 03 2018 ******/
													// Initialiser le filtre des
													// taxe à éliminer
													$scope.taxeIdRemove = [];
													for (var int = 0; int < $scope.listProduitTaxes.length; int++) {
														// $scope.taxeIdRemove.push($scope.listTaxeLivraison[int].taxeId);

														// Temporary remove
														// FODEC from list
														if ($scope.listProduitTaxes[int].taxeId == 1) {
															console
																	.log("index"
																			+ $scope.listProduitTaxes
																					.indexOf($scope.listProduitTaxes[int]));
															$scope.listProduitTaxes
																	.splice(
																			$scope.listProduitTaxes
																					.indexOf($scope.listProduitTaxes[int]),
																			1);
														}

													}
													$scope.filterTaxes();

													
													/******/
													
													if(datagetCommandeVente.reglementId != null){
														
												         $http
								                          .get(
								                              UrlAtelier +
								                              "/reglement/getById:" +
								                              datagetCommandeVente.reglementId)
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
														$scope.AffectationReglement(datagetCommandeVente);
														  
														
														
														}
													
													
												});



												var dateCommande = null;
												var dateLivraison= null;

												if ($scope.myDataCommandeVente[index].dateIntroduction !== null) {
													dateCommande = $scope.modifierFormatDate($scope.myDataCommandeVente[index].dateIntroduction);
												} else {
													dateCommande = null;
												}


												
												if ($scope.myDataCommandeVente[index].dateLivraison !== null) {
													dateLivraison = $scope.modifierFormatDate($scope.myDataCommandeVente[index].dateLivraison);
												} else {
													dateLivraison = null;
												}
							
							
							 
												$scope.commandeVenteCourante = Object.assign($scope.myDataCommandeVente[index], { dateIntroduction: dateCommande },{dateLivraison:dateLivraison})
													//  $scope.partieInteresseeCourante = $scope.myData[index]
													? angular.copy($scope.myDataCommandeVente[index])
													: {};



								// $scope.commandeVenteCourante = $scope.myDataCommandeVente[index] ? angular
								// 		.copy($scope.myDataCommandeVente[index])
								// 		: {};
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

							
							// Sauvegarder CommandeVente
							$scope.sauvegarderAjout = function(commande) {

								$scope.traitementEnCours = "true";
								if (angular.isDefined(commande.id)) {
									$log.debug("Sauvegarder Modification"+commande.id);
									$scope.updateCommandeVente(commande);
								} else {
									$log.debug("Sauvegarder Ajout" + commande.reference);
									$scope.creerCommandeVente(commande);
								}
								//refresh de la liste
								//$scope.rechercherCommandeVente({});
							}
    							 
							// Création d'un Bon de commande de vente
							$scope.creerCommandeVente = function(commande) {
								//produitCommande
								commande.listProduitCommandes = $scope.listProduitCommandes;
								
								/***** added on 30 03 2018 *****/
								commande.listProduitTaxes = $scope.listTaxeLivraisonInit;
								console.log("create liste taxe: "+commande.listProduitTaxes.length)
								
								/************/

								//DocumentProduitCommande
//								commande.documentCommandeVentes = $scope.listeDocumentCommandeVente ;
								
								$log.debug("-- Create "+JSON.stringify(commande,null,"  "));
								
								commande.type = "Commande";
								
								$http.post(UrlAtelier + "/commandeVente/create",	commande)
									 .success(
										function(newCommandeId) {
											$scope.traitementEnCours = "false";
											
								        	$scope.showNotif();
											$log.debug("success creation : "+newCommandeId);
											//
											//getCommandeVente 
											$http.get(
													UrlAtelier
																+ "/commandeVente/getById:"+newCommandeId)
												.success(
														function(datagetCommandeVente) {
															
															//produitCommande
															$scope.listProduitCommandes = datagetCommandeVente.listProduitCommandes;

															// getTableaux
															$scope.listProduitTaxes = datagetCommandeVente.listProduitTaxes;
															
															
															
															//disable update de la dropList 'Produit' 
							 								angular.forEach($scope.listProduitCommandes, function(produitCommande, key){
											            		produitCommande.checked = true;
											            		
//											            		$scope.updateProduitCommandDetails(produitCommande);
											            		
											            		$scope.productFilter=[];
											            		$scope.sousFamilleFilter=[];
											            		
											            		$scope.getProductFilter(produitCommande.produitId);
													        	 
													        	if ($scope.productFilter.length >0){
													        		produitCommande.designation=$scope.productFilter[0].designation;
													        		produitCommande.prixUnitaire=$scope.productFilter[0].prixUnitaire;
													        		
													        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
														        	
													        		if($scope.sousFamilleFilter.length>0){
													        			produitCommande.sousFamille=$scope.sousFamilleFilter[0].designation;
													        		}
													        	}
													        	
											            		$scope.showNotif();
															});

							 							// bouton PDF activé
						 									$scope.modePdf = "actif";
						 									
															//document
//															$scope.listeDocumentCommandeVente = datagetCommandeVente.documentCommandeVentes;

															$scope.commandeVenteCourante = datagetCommandeVente ? angular
																  .copy(datagetCommandeVente)
																	: {};
																	
																	
																	
															$scope.taxeIdRemove = [];
															for (var int = 0; int < $scope.listProduitTaxes.length; int++) {
																$scope.taxeIdRemove
																		.push($scope.listProduitTaxes[int].taxeId);
															}
															$scope.filterTaxes();
															
															$scope.AffectationReglement(datagetCommandeVente);

														});
										});
								
								console.log("commande liste taxe: "+commande.listProduitTaxes.length);
								
							}
							
							
							// Mise à jour des Bons de Commandes de Vente
							$scope.updateCommandeVente= function(commande) {
								//produitCommande
								commande.listProduitCommandes = $scope.listProduitCommandes;
								/********** added on 30 03 2018 ********/
								commande.listTaxeLivraison = $scope.listTaxeLivraisonInit;
								
								//console.log("liste taxe: "+commande.listTaxeLivraison.length);
								/******************/
								

								//document
//								commande.documentCommandeVentes = $scope.listeDocumentCommandeVente ;
								
								$log.debug("Update "+JSON.stringify(commande,null,"  "));
								
								$http
										.post(
												UrlAtelier
														+ "/commandeVente/update",
												commande)
										.success(
												function(commandeModifiee) {
													$scope.traitementEnCours = "false";
													
										        	$scope.showNotif();
//													for (var i = 0; i < $scope.myDataCommandeVente.length; i++) {
//
//														if ($scope.myDataCommandeVente[i].id == commandeModifiee.id) {
//															$scope.myDataCommandeVente[i] = commandeModifiee;
//															break;
//														}
//													}
													
													$log.debug("success Modification "+commandeModifiee);
													
													//getCommandeVente 
        											$http.get(
        													UrlAtelier
																		+ "/commandeVente/getById:"+commandeModifiee)
														.success(
																function(datagetCommandeVente) {
							          								// bouton PDF activé
								 									$scope.modePdf = "actif";
																	
																	//produitCommande
																	$scope.listProduitCommandes = datagetCommandeVente.listProduitCommandes;
																	
																	
																	
																	//disable update de la dropList 'Produit' 
									 								angular.forEach($scope.listProduitCommandes, function(produitCommande, key){
													            		produitCommande.checked = true;
													            		
//													            		$scope.updateProduitCommandDetails(produitCommande);
													            		$scope.productFilter=[];
													            		$scope.sousFamilleFilter=[];
													            		
													            		
													            		$scope.getProductFilter(produitCommande.produitId);
															        	 
															        	if ($scope.productFilter.length >0){
															        		produitCommande.designation=$scope.productFilter[0].designation;
															        		produitCommande.prixUnitaire=$scope.productFilter[0].prixUnitaire;
															        		
															        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
																        	
															        		if($scope.sousFamilleFilter.length>0){
															        			produitCommande.sousFamille=$scope.sousFamilleFilter[0].designation;
															        		}
															        	}
																	});

																	//document
//																	$scope.listeDocumentCommandeVente = datagetCommandeVente.documentCommandeVentes;

																	$scope.commandeVenteCourante = datagetCommandeVente ? angular
																		  .copy(datagetCommandeVente)
																			: {};
																			
																	$scope.showNotif();
																	
																	$scope.taxeIdRemove = [];
																	for (var int = 0; int < $scope.listProduitTaxes.length; int++) {
																		$scope.taxeIdRemove
																				.push($scope.listProduitTaxes[int].taxeId);
																	}
																	$scope.filterTaxes();
																	
																	
																	if(datagetCommandeVente.reglementId != null){
																		
																         $http
												                          .get(
												                              UrlAtelier +
												                              "/reglement/getById:" +
												                              datagetCommandeVente.reglementId)
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
																		$scope.AffectationReglement(datagetCommandeVente);
																		  
																		
																		
																		}

																});

												});
							}
							
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

							//suppression BonCommande
							$scope.supprimerCommandeVente = function() {
								//var index = this.row.rowIndex;
								$http(
										{
											method : "DELETE",
											url :UrlAtelier + "/commandeVente/delete:"
													+ $scope.myDataCommandeVente[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
              								$scope.myDataCommandeVente.splice($scope.index, 1);
										})
										.error(function(){
											$log.debug("Erreur");
											$scope.myDataCommandeVente.splice($scope.index, 1);
										});
								$scope.closePopupDelete();
								$scope.rechercherCommandeVente({});
							};
							
							$scope.updateProduitCommandDetails= function (produitCommande){
								
								$scope.productFilter=[];
			            		$scope.sousFamilleFilter=[];
			            		
			            		$scope.getProductFilter(produitCommande.id);
					        	 
					        	if ($scope.productFilter.length >0){
					        		produitCommande.designation=$scope.productFilter[0].designation;
					        		produitCommande.prixUnitaire=$scope.productFilter[0].prixUnitaire;
					        		
					        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
						        	
					        		if($scope.sousFamilleFilter.length>0){
					        			produitCommande.sousFamille=$scope.sousFamilleFilter[0].designation;
					        		}
					        	}
							}
							
							
              					// Produit 
              					$scope.produitId = {
            						status : ''
              					};
              					//SousFamilleProduit
              					$scope.sousFamilleProduitId = {
              						status : ''
                  				};

							 // showProduitDetails
              					$scope.showProduitDetails = function(produitId, attributRecherche) {
              						
              						$scope.produitId.status = produitId;
              						var selected = $filter('showProduitFilterBS')(
              								$scope.listeProduitCache, {
              									id : $scope.produitId.status
              								});
              						if ($scope.produitId.status && selected.length) {
              							if(attributRecherche == 'reference'){
              								return selected[0].reference;
              							}
              							else if(attributRecherche == 'tissu'){
              								return selected[0].designation;
              							}
              							if(attributRecherche == 'prix'){
              								return selected[0].prixUnitaire;
              							}
              							else if(attributRecherche == 'type'){
              								//conversion de sousFamilleId par son designation
              								$scope.sousFamilleProduitId.status = selected[0].sousFamilleId;
                      						var selected2= $filter('filterSousFamilleBS')(
                      								$scope.ListSousFamilleProduitCache , {
                      									id : $scope.sousFamilleProduitId.status
                      								});
                      						if ($scope.sousFamilleProduitId.status && selected2.length) {
                      							return selected2[0].designation;
                      						}else{
                      							return "--";
                      						}
              							}
              							
              						} 
              					}

							$scope.showBtnCalender = true;
							// show bottons Calender
							$scope.showBC = function() {
								$scope.showBtnCalender = true;
							}
							
						      
								$scope.type = {
									status : ''
								};

								$scope.famille = {
									status : ''
								};

								$scope.varPrix = [{index:'', prix:''}];

						       $scope.changePrix = function (event, item, index) {

    							 }

						       
						       $scope.getProductFilter = function(idProduit){
						    	   $scope.productFilter = $filter('filter')(
									          $scope.listeProduitCache, {
									           id : idProduit
									          });
						        	
						       }
						       
						       $scope.getSousFamilleFilter= function(sousFamilleId){
						    	   $scope.sousFamilleFilter = $filter('filter')(
					        				$scope.ListSousFamilleProduitCache, {
									           id : sousFamilleId
									          });
						       }
    							 
						        $scope.clickProduit = function( idProduit, index){
						        	
				            		$scope.updateProduitCommandDetails($scope.listProduitCommandes[index]);
						        	
				            		$log.debug("idProduit---" + idProduit);
				            		
				            		$scope.productFilter=[];
				            		$scope.sousFamilleFilter=[];
				            		
				            		$scope.getProductFilter(idProduit);
						        	 
						        	if ($scope.productFilter.length >0){
						        		$scope.listProduitCommandes[index].designation=$scope.productFilter[0].designation;
						        		$scope.listProduitCommandes[index].prixUnitaire=$scope.productFilter[0].prixUnitaire;
						        		
						        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
							        	
						        		if($scope.sousFamilleFilter.length>0){
						        			$scope.listProduitCommandes[index].sousFamille=$scope.sousFamilleFilter[0].designation;
						        		}
						        	}
						        	
						        	}
						        	
    							 
						    // ajout d'un Produit
							$scope.ajoutProduit = function() {
								console.log("----------------- New Line ------------------");
								console.log("New Line ");
								

								$scope.produitInserree = {
										produitId :'',
										quantite:'',
										prix:'',
										commandeVenteId:'',
										'checked' : false

								};
								
								$scope.listProduitCommandes
										.push($scope.produitInserree);
								
							};
							
							// Enregistrer Produit
							$scope.saveProduit = function(
									dataProduit, id) {
								$scope.deleteValue = "non";
								
								angular.extend(dataProduit, {
									id : id
								});
								$scope.showBtnCalender = false;
							};

							// Supprimer Produit
							$scope.removeProduit = function(
									index) {
								$scope.listProduitCommandes.splice(
										index, 1);
								console.log("Success Delete Produit ");
							};

							/** Fin de gestion des Produit */
							

							/***************************************************
							 * Gestion des DocumentCommandeVente
							 **************************************************/
							 $scope.name="BCV";
							 
							 $scope.listeDocumentCommandeVente = [];
							 
								// GetId.designation
								$scope.doc = {

									status : ''
								};
								$scope.showStatus = function(id) {

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
								};
								
								// ajout d'un DocumentCommandeVente
								$scope.ajoutDocumentCommandeVente = function() {

									$scope.documentCommandeVenteInserree = {
										typeDocumentId:'',
										uidDocument : '',
										path : ''

									};
									$scope.listeDocumentCommandeVente
											.push($scope.documentCommandeVenteInserree);

								};

								// Enregistrer DocumentCommandeVente
								$scope.saveDocumentCommandeVente = function(										
										dataDocumentCommandeVente, id) {
									console.log("**SAVE DOC "+dataDocumentCommandeVente);
									$scope.deleteValue = "non";
									angular.extend(dataDocumentCommandeVente, {
										id : id
									});
								};

								// Supprimer DocumentCommandeVente
								$scope.removeDocumentCommandeVente = function(index) {
									$scope.listeDocumentCommandeVente.splice(index, 1);
								};
								/** Fin de gestion des DocumentCommandeVente */
							/** Fin de gestion des DocumentCommandeVente */
							/***************************************************
							 * Gestion de la Grid Bon de Commande de Vente 
							 **************************************************/
							$scope.typeAlert = 3;
								
							$scope.colDefs = [];
							$scope.$watch(
								'myDataCommandeVente',
									function() {
									$scope.colDefs = [
										/*	{
												field : '',
												headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
												cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
												width:'3%'
											},*/
											{
												field : 'reference',
												displayName : 'Réf.BC',
											//	width:'12%'
											},
											{
												field : 'partieIntersseAbbreviation',
												displayName : 'Client',
											//	width:'23%'
											},
											{
												field : 'dateIntroduction',
												displayName : 'Date Cmd.',
												cellFilter: 'date:\'dd-MM-yyyy\'',
											//	width:'10%'
											},
											{
												field : 'dateLivraison',
												displayName : 'Date Liv.',
												cellFilter: 'date:\'dd-MM-yyyy\'',
											//	width:'10%'
											},
											{
												field : 'quantite',
												displayName : 'Quantite',
											//	width:'10%'
											},
											{
												field : 'prixTotal',
												displayName : 'Montant HT',
										//		width:'10%'
											},
											{
												field : 'montantTaxe',
												displayName : 'Montant Taxe',
											//	width:'10%'
											},
											{
												field : 'montantTTC',
												displayName : 'Montant TTC',
											//	width:'10%'
											},
											{
												field : '',
											//	width:'5%',
												cellTemplate : 
												
												`<div class="ms-CommandButton float-right"   >
												<button class="ms-CommandButton-button ms-CommandButton-Gpro "  ng-click="modifierOuCreerCommandeVente()">
											<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
											</button>
												<button class="ms-CommandButton-button"  ng-click="showPopupDelete(12)" permission="['Vente_Delete']">
												<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
											</button>
												</div> `,  
												
												
												// '<div class="buttons">'
												// 	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerCommandeVente()"><i class="fa fa-fw fa-pencil"></i></button>'
												// 	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(12)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
									$scope.myDataCommandeVente = pagedData;
									$scope.totalServerItems = data.length;
									if (!$scope.$$phase) {
										$scope.$apply();
									}
									$scope.isLoading=false;
								};

								$scope.getPagedDataAsync = function(pageSize, page,
										searchText) {
									setTimeout(
											function() {
												var data;
												var commandeVenteCourante = $scope.commandeVenteCourante;
												commandeVenteCourante.type = "Commande";
												if (searchText) {
													var ft = searchText
															.toLowerCase();
													$scope.isLoading=true;
													$http
															.post(
																	UrlAtelier+ "/commandeVente/rechercheMulticritere",
																	commandeVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		$scope.myDataCommandeVente = largeLoad.commandeVenteValues
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
													
													$scope.isLoading=true;
													
													$http
															.post(
																	UrlAtelier+ "/commandeVente/rechercheMulticritere",
																	commandeVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		
																		console.log("largeLoad" + JSON.stringify(largeLoad, null, " ") );
																		$scope
																				.setPagingData(
																						largeLoad.listCommandeVente,
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
									data : 'myDataCommandeVente',
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

							
							/** Fin de gestion des Grids Vente BC */
						} ]);
				/*.filter('showProduitFilterBS', function() {
				  return function(listeProduit, id) {
					 var listeProduitFiltre = [];
					console.log("IdProduitt=  "+id.id);
					console.log("listeProduit "+ JSON.stringify(listeProduit, null, "    "));
					 angular.forEach(listeProduit, function(produit, key){
						
						if(produit.id == id.id){
							console.log(produit.id +" == "+ id.id);
							listeProduitFiltre.push(produit);
						}
							
					 });
					// console.log(" ** listeProduitFiltre "+ JSON.stringify(listeProduitFiltre, null, "    "));
					 return listeProduitFiltre;
				  };
				})

				.filter('filterSousFamilleBS', function() {
				  return function(listeSousFamille, id) {
					 var listeSousFamilleFiltre = [];
					// console.log("IdSousFamille=  "+id.id);
					// console.log("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
					 angular.forEach(listeSousFamille, function(sousFamille, key){
						
						if(sousFamille.id == id.id){
							console.log(sousFamille.id +" == "+ id.id);
							listeSousFamilleFiltre.push(sousFamille);
						}
							
					 });
					// console.log(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
					 return listeSousFamilleFiltre;
				  };
				});*/
