'use strict'
angular
		.module('gpro.gcReception', [])
		.controller(
				'gcReceptionCtrl',
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
						function($scope, $http, $filter,$rootScope, $log, downloadService, UrlAtelier,UrlCommun,$window) {
							
							$log.info("----------- Vente BC ----------");
							//** Variables Recherche
							$scope.listProduitReceptions = [];
							$scope.ListClientReceptionVenteCache = [];
							
							//**Variables Modif/Ajout
							//init objetCourant
							$scope.receptionVenteCourante =   {
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
													  "idDepot":""
													};
							$scope.listeProduitCache = [];
							$scope.listeDocumentReceptionVente = [];
							//**Variable Grid
							$scope.myDataReceptionVente = [];
							//bouton pdf hide
              				$scope.modePdf = "notActive";
							
							$scope.displayMode = "list";
							
							/*********************************************************
							 * Gestion de Cache des Referentiels Gestion Commerciale *
							 *********************************************************/
							$scope.ListEtatReceptionVenteCache = [];
							$scope.ListTypeReceptionVenteCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.ListTypeDocumentCache = [];
							$scope.ListSousFamilleProduitCache = [];
							
							// Liste des PartieInteressee (avec FamilleId=1)
							$scope.listeFournisseursCache = function() {
								$http
										.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPartieInteressee) {

													$scope.listePartieInteressee = $filter ('filter') (dataPartieInteressee , {famillePartieInteressee : 2});

												});
							}
							
							
							
							//REST SERVICE MAGAZINS
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
//							$scope.listeEtatReceptionV	});
//							}

							

							// Liste des produits
							$scope.listeProduitCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeProduitCache").success(
										function(data) {
											console.log("listeProduitCache "+data.length);
											$scope.listeProduitCache = data;

										});
							}
							
							$scope.listeFournisseursCache();
							$scope.listeMagazinCache();
//							$scope.listeTypeReceptionenteCache();
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

							$scope.cancelAddReceptionVente = function(rowform, index, id, designation){
						    		  if (angular.isDefined(id)) {
						    			 
						    				  $scope.deleteValue="non";
						    				  rowform.$cancel();
						    				  console.log("CANCEL");
						    		  }else{	
						    				  $scope.deleteValue=="oui"
						    				  $scope.listProduitReceptions.splice(index, 1);
									    	  rowform.$cancel();
						    		}
						    		  $scope.deleteValue="oui";
						    }
							// ** Ajout Bon de Reception de Vente
							$scope.AffectationVenteBC = function() {
								$scope.receptionVenteCourante={};
								
//								$scope.receptionVenteCourante = reception ? angular
//										.copy(reception) : {};

								$scope.displayMode = "edit";
							}
							
							
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
              					//vider le tab
              					$scope.varPrix = [{index:'', prix:''}];
              					//init objetCourant
              					$scope.receptionVenteCourante =   {
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
								$scope.rechercherReceptionVente({});
								$scope.listProduitReceptions = [];
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
								$scope.closeNotif();
							}
							
							$scope.rechercherReceptionVente = function(receptionVenteCourante){
								$http
								.post(
										UrlAtelier+ "/receptionVente/rechercheMulticritere",
										receptionVenteCourante)
								.success(
										function(data) {
											
											$scope.myDataReceptionVente = data.listReceptionVente;
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
              				$scope.download = function(id,pRapportPrix) {
              					
              				//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
              				 	 $scope.checkboxModel= {
              				       rapportPrix : "oui"
              				     };

              				 	 
								console.log("-- id"+id);
								var url = UrlAtelier+"/reportgc/bonReception?id=" + id
													+ "&avecPrix="+pRapportPrix
													+"&type=pdf";


		
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

								// downloadService.download(url)
								// 		.then(
								// 				function(success) {
								// 					console.log('success : '+ success);
								// 					//$scope.annulerAjout();
								// 				},
								// 				function(error) {
								// 					console.log('error : '+ error);
								// 				});
							};
        						
        					//generer rapport de tous les bons de livraison. mode : List 
							$scope.downloadAllReceptionVente = function(receptionVenteCourante) {

								var newdateLivBCMinFormat="";
								if(angular.isDefined(receptionVenteCourante.dateLivraisonDu)){
									
									if(receptionVenteCourante.dateLivraisonDu != ""){
										newdateLivBCMinFormat = formattedDate(receptionVenteCourante.dateLivraisonDu);
										console.log("===== newdateLivBCMinFormat "+newdateLivBCMinFormat);
									}else{
										console.log("===== newdateLivBCMinFormat is Null");
										newdateLivBCMinFormat = "";
									}
								}else{
									console.log("==dateLivraisonDu Undefined");
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(receptionVenteCourante.dateLivraisonA)){
									
									if(receptionVenteCourante.dateLivraisonA != ""){
										newdateLivBCMaxFormat = formattedDate(receptionVenteCourante.dateLivraisonA);
									//	console.log("===== newdateLivBCMaxFormat "+newdateLivBCMaxFormat);
									}else{
										console.log("===== newdateLivBCMaxFormat is Null");
										newdateLivBCMaxFormat = "";
									}
								}else{
									console.log("==dateLivraisonA Undefined");
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(receptionVenteCourante.dateLivraisonDu)){
									
									if(receptionVenteCourante.dateLivraisonDu != ""){
										newdateIntroBCMinFormat = formattedDate(receptionVenteCourante.dateLivraisonDu);
										console.log("===== newdateIntroBCMinFormat "+newdateIntroBCMinFormats);
									}else{
										console.log("===== newdateIntroBCMinFormat is Null");
										newdateIntroBCMinFormat = "";
									}
								}else{
									console.log("==dateLivraisonDu Undefined");
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(receptionVenteCourante.dateIntroductionA)){
									
									if(receptionVenteCourante.dateIntroductionA != ""){
										newdateIntroBCMaxFormat = formattedDate(receptionVenteCourante.dateIntroductionA);
										console.log("===== newdateIntroBCMaxFormat "+newdateIntroBCMaxFormat);
									}else{
										console.log("===== newdateIntroBCMaxFormat is Null");
										newdateIntroBCMaxFormat = "";
									}
								}else{
									console.log("==dateIntroductionA Undefined");
								}


								console.log("-- receptionVenteCourante" + JSON.stringify(receptionVenteCourante, null, "  ") );

								var url;
								console.log("PI  "+receptionVenteCourante.vTypePartieInteressee );
								 	if(receptionVenteCourante.vTypePartieInteressee == null){
								 	receptionVenteCourante.vTypePartieInteressee = "";
								 }

								console.log("Produit  "+receptionVenteCourante.vProduit );
								 	if(receptionVenteCourante.vProduit == null){
								 	receptionVenteCourante.vProduit = "";
								 }

			        			url = UrlAtelier + "/report/listReceptionVente?reference=" + receptionVenteCourante.vReference
								 					 + "&partieInteressee=" + receptionVenteCourante.vTypePartieInteressee
								 					 + "&produit="+receptionVenteCourante.vProduit
													 + "&dateIntroductionDu="+newdateIntroBCMinFormat
													 + "&dateIntroductionA="+newdateIntroBCMaxFormat
													 + "&dateLivraisonDu="+newdateLivBCMinFormat
													 + "&dateLivraisonA="+newdateLivBCMaxFormat
													 + "&typeReception="+receptionVenteCourante.vTypeReception
													 + "&etatReception="+receptionVenteCourante.vEtatReception
													 + "&quantiteDu="+receptionVenteCourante.quantiteDu
													 + "&quantiteA="+receptionVenteCourante.quantiteA
													 + "&coutDu="+receptionVenteCourante.coutDu
													 + "&coutA="+receptionVenteCourante.coutA
													 + "&idDepot="+receptionVenteCourante.idDepot
													 + "&type=pdf";
				                  
												 console.log("-- URL" + url );
												 


												
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
							 * Gestion des Bon de reception
							 **************************************************/
							
							$scope.isLoading = false;
							
							// Ajout et Modification ReceptionVente
							$scope.modifierOuCreerReceptionVente = function() {
								
								$scope.isLoading = true;
								
								var index = this.row.rowIndex;
								// getReceptionVente
								$http
										.get(
												UrlAtelier
														+ "/receptionVente/getById:"+
														 $scope.myDataReceptionVente[index].id)
										.success(
												function(datagetReceptionVente) {

													$log.debug("getById : "+ $scope.myDataReceptionVente[index].id +" \n  "+JSON.stringify(datagetReceptionVente,null,"  ") );
													//produitReception
													$scope.listProduitReceptions = datagetReceptionVente.listProduitReceptions;
													

													//disable update de la dropList 'Produit' 
					 								angular.forEach($scope.listProduitReceptions, function(produitReception, key){
									            		produitReception.checked = true;

//									            		$scope.updateProduitCommandDetails(produitReception);
									            		
									            		$scope.productFilter=[];
									            		$scope.sousFamilleFilter=[];
									            		
									            		$scope.getProductFilter(produitReception.produitId);
											        	 
											        	if ($scope.productFilter.length >0){
											        		produitReception.designation=$scope.productFilter[0].designation;
											        		
											        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
												        	
											        		if($scope.sousFamilleFilter.length>0){
											        			produitReception.sousFamille=$scope.sousFamilleFilter[0].designation;
											        		}
											        	}
													});

					 							// bouton PDF activé
					              					$scope.modePdf = "actif";
					 								$scope.isLoading=false;
					 								
													//document
//													$scope.listeDocumentReceptionVente = datagetReceptionVente.documentReceptionVentes;


													$scope.myDataReceptionVente[index].listProduitReceptions = $scope.listProduitReceptions;
//													$scope.myDataReceptionVente[index].documentReceptionVentes = $scope.listeDocumentReceptionVente;
												});

								$scope.receptionVenteCourante = $scope.myDataReceptionVente[index] ? angular
										.copy($scope.myDataReceptionVente[index])
										: {};
								$scope.displayMode = "edit";
							}

							
							// Sauvegarder ReceptionVente
							$scope.sauvegarderAjout = function(reception) {

								if (angular.isDefined(reception.id)) {
									$log.debug("Sauvegarder Modification"+reception.id);
									$scope.updateReceptionVente(reception);
								} else {
									$log.debug("Sauvegarder Ajout" + reception.reference);
									$scope.creerReceptionVente(reception);
								}
								//refresh de la liste
								//$scope.rechercherReceptionVente({});
							}
    							 
							// Création d'un Bon de reception de vente
							$scope.creerReceptionVente = function(reception) {
								//produitReception
								reception.listProduitReceptions = $scope.listProduitReceptions;

								//DocumentProduitReception
//								reception.documentReceptionVentes = $scope.listeDocumentReceptionVente ;
								
								$log.debug("-- Create "+JSON.stringify(reception,null,"  "));
								
								$http.post(UrlAtelier + "/receptionVente/create",	reception)
									 .success(
										function(newReceptionId) {
											$log.debug("success creation : "+newReceptionId);
											//
											//getReceptionVente 
											$http.get(
													UrlAtelier
																+ "/receptionVente/getById:"+newReceptionId)
												.success(
														function(datagetReceptionVente) {
															
															//produitReception
															$scope.listProduitReceptions = datagetReceptionVente.listProduitReceptions;
															
															//set content of reference produit et sousFamille designation
															
															
															//disable update de la dropList 'Produit' 
							 								angular.forEach($scope.listProduitReceptions, function(produitReception, key){
											            		produitReception.checked = true;
											            		
//											            		$scope.updateProduitCommandDetails(produitReception);
											            		
											            		$scope.productFilter=[];
											            		$scope.sousFamilleFilter=[];
											            		
											            		$scope.getProductFilter(produitReception.produitId);
													        	 
													        	if ($scope.productFilter.length >0){
													        		produitReception.designation=$scope.productFilter[0].designation;
													        		produitReception.prixUnitaire=$scope.productFilter[0].prixUnitaire;
													        		
													        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
														        	
													        		if($scope.sousFamilleFilter.length>0){
													        			produitReception.sousFamille=$scope.sousFamilleFilter[0].designation;
													        		}
													        	}
													        	
											            		$scope.showNotif();
															});

							 							// bouton PDF activé
						 									$scope.modePdf = "actif";
						 									
															//document
//															$scope.listeDocumentReceptionVente = datagetReceptionVente.documentReceptionVentes;

															$scope.receptionVenteCourante = datagetReceptionVente ? angular
																  .copy(datagetReceptionVente)
																	: {};

														});
										});
								
							}
							
							
							// Mise à jour des Bons de Receptions de Vente
							$scope.updateReceptionVente= function(reception) {
								//produitReception
								reception.listProduitReceptions = $scope.listProduitReceptions;

								//document
//								reception.documentReceptionVentes = $scope.listeDocumentReceptionVente ;
								
								$log.debug("Update "+JSON.stringify(reception,null,"  "));
								
								$http
										.post(
												UrlAtelier
														+ "/receptionVente/update",
												reception)
										.success(
												function(receptionModifiee) {
//													for (var i = 0; i < $scope.myDataReceptionVente.length; i++) {
//
//														if ($scope.myDataReceptionVente[i].id == receptionModifiee.id) {
//															$scope.myDataReceptionVente[i] = receptionModifiee;
//															break;
//														}
//													}
													
													$log.debug("success Modification "+receptionModifiee);
													
													//getReceptionVente 
        											$http.get(
        													UrlAtelier
																		+ "/receptionVente/getById:"+receptionModifiee)
														.success(
																function(datagetReceptionVente) {
							          								// bouton PDF activé
								 									$scope.modePdf = "actif";
																	
																	//produitReception
																	$scope.listProduitReceptions = datagetReceptionVente.listProduitReceptions;
																	
																	//disable update de la dropList 'Produit' 
									 								angular.forEach($scope.listProduitReceptions, function(produitReception, key){
													            		produitReception.checked = true;
													            		
//													            		$scope.updateProduitCommandDetails(produitReception);
													            		$scope.productFilter=[];
													            		$scope.sousFamilleFilter=[];
													            		
													            		$scope.getProductFilter(produitReception.produitId);
															        	 
															        	if ($scope.productFilter.length >0){
															        		produitReception.designation=$scope.productFilter[0].designation;
															        		produitReception.prixUnitaire=$scope.productFilter[0].prixUnitaire;
															        		
															        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
																        	
															        		if($scope.sousFamilleFilter.length>0){
															        			produitReception.sousFamille=$scope.sousFamilleFilter[0].designation;
															        		}
															        	}
																	});

																	//document
//																	$scope.listeDocumentReceptionVente = datagetReceptionVente.documentReceptionVentes;

																	$scope.receptionVenteCourante = datagetReceptionVente ? angular
																		  .copy(datagetReceptionVente)
																			: {};
																			
																	$scope.showNotif();

																});

												});
							}

							//suppression BonReception
							$scope.supprimerReceptionVente = function() {
								//var index = this.row.rowIndex;
								$http(
										{
											method : "DELETE",
											url :UrlAtelier + "/ReceptionVente/delete:"
													+ $scope.myDataReceptionVente[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
              								$scope.myDataReceptionVente.splice($scope.index, 1);
										})
										.error(function(){
											$log.debug("Erreur");
											$scope.myDataReceptionVente.splice($scope.index, 1);
										});
								$scope.closePopupDelete();
								$scope.rechercherReceptionVente({});
							};
							
							$scope.updateProduitCommandDetails= function (produitId, produitReception){
								
								$scope.productFilter=[];
			            		$scope.sousFamilleFilter=[];
			            		
			            		$scope.getProductFilter(produitId);
					        	 
					        	if ($scope.productFilter.length >0){
					        		produitReception.designation=$scope.productFilter[0].designation;
					        		
					        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
						        	
					        		if($scope.sousFamilleFilter.length>0){
					        			produitReception.sousFamille=$scope.sousFamilleFilter[0].designation;
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
						    	   
						    	   console.log("idProduit" + idProduit);
						    	   $scope.productFilter = $filter('filter')(
									          $scope.listeProduitCache, {
									           id : idProduit
									          });
						    	   
						    	   console.log("productFilter" + JSON.stringify($scope.productFilter,null," "));

						        	
						       }
						       
						       $scope.getSousFamilleFilter= function(sousFamilleId){
						    	   $scope.sousFamilleFilter = $filter('filter')(
					        				$scope.ListSousFamilleProduitCache, {
									           id : sousFamilleId
									          });
						       }
    							 
						        $scope.clickProduit = function( idProduit, index){
						        	
							    	   console.log("idProduit clickProduit" + idProduit);
							    	   
				            		$scope.updateProduitCommandDetails(idProduit,$scope.listProduitReceptions[index]);
						       					        	
						        	
						        	}
						        	
    							 
						    // ajout d'un Produit
							$scope.ajoutProduit = function() {

								$scope.produitInserree = {
										produitId :'',
										quantite:'',
										prix:'',
										receptionVenteId:'',
										'checked' : false

								};
								
								$scope.listProduitReceptions
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
								$scope.listProduitReceptions.splice(
										index, 1);
								console.log("Success Delete Produit ");
							};

							/** Fin de gestion des Produit */
							

							/***************************************************
							 * Gestion des DocumentReceptionVente
							 **************************************************/
							 $scope.name="BCV";
							 
							 $scope.listeDocumentReceptionVente = [];
							 
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
								
								// ajout d'un DocumentReceptionVente
								$scope.ajoutDocumentReceptionVente = function() {

									$scope.documentReceptionVenteInserree = {
										//typeDocument:'',
										 //vUUIDDocument : '',
											typeDocumentId : '',
											uidDocument : '',
										path : ''

									};
									$scope.listeDocumentReceptionVente
											.push($scope.documentReceptionVenteInserree);

								};

								// Enregistrer DocumentReceptionVente
								$scope.saveDocumentReceptionVente = function(										
										dataDocumentReceptionVente, id) {
									console.log("**SAVE DOC "+dataDocumentReceptionVente);
									$scope.deleteValue = "non";
									angular.extend(dataDocumentReceptionVente, {
										id : id
									});
								};

								// Supprimer DocumentReceptionVente
								$scope.removeDocumentReceptionVente = function(index) {
									$scope.listeDocumentReceptionVente.splice(index, 1);
								};
								/** Fin de gestion des DocumentReceptionVente */
							/** Fin de gestion des DocumentReceptionVente */
							/***************************************************
							 * Gestion de la Grid Bon de Reception de Vente 
							 **************************************************/
							$scope.typeAlert = 3;
								
							$scope.colDefs = [];
							$scope.$watch(
								'myDataReceptionVente',
									function() {
										$scope.colDefs = [
										{
											field : '',
											headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
											cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
										//	width:'3%'
										},
										{
											field : 'reference',
											displayName : 'Réf.B Réception',
										//	width:'20%'
										},
										{
											field : 'partieIntersseAbbreviation',
											displayName : 'Fournisseur',
										//	width:'25%'
										},
										{
											field : 'dateIntroduction',
											displayName : 'Date Réception',
											cellFilter: 'date:\'dd-MM-yyyy\'',
										//	width:'15%'
										},
//										{
//											field : 'dateLivraison',
//											displayName : 'Date Liv.',
//											cellFilter: 'date:\'dd-MM-yyyy\'',
//											width:'15%'
//										},
										{
											field : 'quantite',
											displayName : 'Quantite',
										//	width:'15%'
										},
										{
											field : 'prixTotal',
											displayName : 'Cout',
										//	width:'15%'
										},
										{
											field : '',
										//	width:'5%',
											cellTemplate :  
											
											
											`<div class="ms-CommandButton float-right"  >
									<button class="ms-CommandButton-button ms-CommandButton-Gpro "  ng-click="modifierOuCreerReceptionVente()">
									<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
									</button>
									<button class="ms-CommandButton-button" ng-click="showPopupDelete(12)" permission="['Vente_Delete']">
										<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
									</button>
										</div> `,
											
											
											
											// '<div class="buttons">'
											// 	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerReceptionVente()"><i class="fa fa-fw fa-pencil"></i></button>'
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
									$scope.myDataReceptionVente = pagedData;
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
												var receptionVenteCourante = $scope.receptionVenteCourante;
												if (searchText) {
													var ft = searchText
															.toLowerCase();
													$scope.isLoading=true;
													$http
															.post(
																	UrlAtelier+ "/receptionVente/rechercheMulticritere",
																	receptionVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		$scope.myDataReceptionVente = largeLoad.receptionVenteValues
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
																	UrlAtelier+ "/receptionVente/rechercheMulticritere",
																	receptionVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		
																		console.log("largeLoad" + JSON.stringify(largeLoad, null, " ") );
																		$scope
																				.setPagingData(
																						largeLoad.listReceptionVente,
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
									data : 'myDataReceptionVente',
									columnDefs : 'colDefs',
									enablePaging : true,
									enableColumnResize: true,
									showFooter : true,
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
