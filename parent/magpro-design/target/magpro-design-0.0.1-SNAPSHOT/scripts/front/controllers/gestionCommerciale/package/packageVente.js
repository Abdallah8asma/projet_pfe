'use strict'
angular
		.module('gpro.packageVente', [])
		.controller(
				'packageVenteController',
				[
						'$scope',
						'$http',
						'$filter',
						'$rootScope',
						'$log',
						'downloadService',
						'UrlAtelier',
						'UrlCommun',
						function($scope, $http, $filter,$rootScope, $log, downloadService, UrlAtelier,UrlCommun) {
							
							$log.info("----------- Vente BC ----------");
							//** Variables Recherche
							$scope.listDetPackage = [];
							$scope.ListClientCommandeVenteCache = [];
							
							
							
							$scope.packageVenteCourante =   {
									  "reference": "",
									  "nom": "",
									  "clientId": "",
									  "groupeId": "",
									  "dateDebut": "",
									  "dateFin": ""
									 
									};
							
							
						
							  $scope.myStyleBtn = {
										
					                    "background-color" : "yellow"
					             
					                };
							  
				
				
							$scope.listeProduitCache = [];
							$scope.listeDocumentCommandeVente = [];
							//**Variable Grid
							$scope.myDataCommandeVente = [];
							//bouton pdf hide
              				$scope.modePdf = "notActive";
							
							$scope.displayMode = "list";
							
							$scope.hiddenNotif ="true";
							
							$scope.traitementEnCours = "false";
							
							/**************************************
							 * Notification *
							 **************************************/
							$scope.showNotif = function(){
								$scope.hiddenNotif ="false";
							}
													
							$scope.closeNotif = function(){
								$scope.hiddenNotif ="true";
							}
							
							/*********************************************************
							 * Gestion de Cache des Referentiels Gestion Commerciale *
							 *********************************************************/
							$scope.ListEtatCommandeVenteCache = [];
							$scope.ListTypeCommandeVenteCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.ListTypeDocumentCache = [];
							$scope.ListSousFamilleProduitCache = [];
							
							$scope.listGroupeClient = [];
							
							// Liste des PartieInteressee (avec FamilleId=1)
							$scope.listeClientCache = function() {
								$http
										.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPartieInteressee) {

													$scope.listeClientCache = dataPartieInteressee;
												});
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
							


							
							// Liste des produits
							$scope.listeProduitCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeProduitCache").success(
										function(data) {
											console.log("listeProduitCache "+data.length);
											$scope.listeProduitCache = data;

										});
							}
							
							$scope.listeClientCache();
									
					
							$scope.ListSousFamillesProduitCache();
							$scope.listeProduitCache();

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
						    				  $scope.listDetPackage.splice(index, 1);
									    	  rowform.$cancel();
						    		}
						    		  $scope.deleteValue="oui";
						    }
							// ** Ajout Bon de Commande de Vente
							$scope.AffectationVenteBC = function() {
								
							

								$scope.packageVenteCourante={dateIntroduction : new Date()};
								
							
								
//								$scope.packageVenteCourante = commande ? angular
//										.copy(commande) : {};

								$scope.displayMode = "edit";
							}
							
							
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.closeNotif();
								
								$scope.reglementCourante = {};
								
								
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
              					//vider le tab
              			
              					//init objetCourant
              					$scope.packageVenteCourante =   {
										  "reference": "",
										  "nom": "",
										  "clientId": "",
										  "groupeId": "",
										  "dateDebut": "",
										  "dateFin": ""
										 
										};
								$scope.rechercherCommandeVente({});
								$scope.listDetPackage = [];
								$scope.listTaxeLivraison = [];
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
								$scope.closeNotif();
							}
							
							$scope.rechercherCommandeVente = function(packageVenteCourante){
							
								
								$http
								.post(
										UrlCommun+ "/package/rechercheMulticritere",
										packageVenteCourante)
								.success(
										function(data) {
											
											$scope.myDataCommandeVente = data.list;
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
              				$scope.download = function(id,pRapportPrix,type) {
              					
              				//init checkbox : 'non' :rapport sans Prix / 'oui' rapport avec prix
              				 	 $scope.checkboxModel= {
              				       rapportPrix : "oui"
              				     };

              				 	 
				
								
										var url = UrlAtelier+"/reportgc/bonCommande?id=" + id
															+ "&avecPrix="+pRapportPrix
															+ "&typerapport="+type
															+"&type=pdf";
										downloadService.download(url).then(
														function(success) {
															console.log('success : '+ success);
															
														},
														function(error) {
															console.log('error : '+ error);
														});
								
								
								
							};
        						
        					//generer rapport de tous les bons de livraison. mode : List 
							$scope.downloadAllCommandeVente = function(packageVenteCourante) {

								var newdateLivBCMinFormat="";
								if(angular.isDefined(packageVenteCourante.dateLivraisonDu)){
									
									if(packageVenteCourante.dateLivraisonDu != ""){
										newdateLivBCMinFormat = formattedDate(packageVenteCourante.dateLivraisonDu);
										console.log("===== newdateLivBCMinFormat "+newdateLivBCMinFormat);
									}else{
										console.log("===== newdateLivBCMinFormat is Null");
										newdateLivBCMinFormat = "";
									}
								}else{
									console.log("==dateLivraisonDu Undefined");
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(packageVenteCourante.dateLivraisonA)){
									
									if(packageVenteCourante.dateLivraisonA != ""){
										newdateLivBCMaxFormat = formattedDate(packageVenteCourante.dateLivraisonA);
									//	console.log("===== newdateLivBCMaxFormat "+newdateLivBCMaxFormat);
									}else{
										console.log("===== newdateLivBCMaxFormat is Null");
										newdateLivBCMaxFormat = "";
									}
								}else{
									console.log("==dateLivraisonA Undefined");
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(packageVenteCourante.dateLivraisonDu)){
									
									if(packageVenteCourante.dateLivraisonDu != ""){
										newdateIntroBCMinFormat = formattedDate(packageVenteCourante.dateLivraisonDu);
										console.log("===== newdateIntroBCMinFormat "+newdateIntroBCMinFormats);
									}else{
										console.log("===== newdateIntroBCMinFormat is Null");
										newdateIntroBCMinFormat = "";
									}
								}else{
									console.log("==dateLivraisonDu Undefined");
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(packageVenteCourante.dateIntroductionA)){
									
									if(packageVenteCourante.dateIntroductionA != ""){
										newdateIntroBCMaxFormat = formattedDate(packageVenteCourante.dateIntroductionA);
										console.log("===== newdateIntroBCMaxFormat "+newdateIntroBCMaxFormat);
									}else{
										console.log("===== newdateIntroBCMaxFormat is Null");
										newdateIntroBCMaxFormat = "";
									}
								}else{
									console.log("==dateIntroductionA Undefined");
								}


								console.log("-- packageVenteCourante" + JSON.stringify(packageVenteCourante, null, "  ") );

								var url;
								console.log("PI  "+packageVenteCourante.vTypePartieInteressee );
								 	if(packageVenteCourante.vTypePartieInteressee == null){
								 	packageVenteCourante.vTypePartieInteressee = "";
								 }

								console.log("Produit  "+packageVenteCourante.vProduit );
								 	if(packageVenteCourante.vProduit == null){
								 	packageVenteCourante.vProduit = "";
								 }

			        			url = UrlAtelier + "/report/listCommandeVente?reference=" + packageVenteCourante.vReference
								 					 + "&partieInteressee=" + packageVenteCourante.vTypePartieInteressee
								 					 + "&produit="+packageVenteCourante.vProduit
													 + "&dateIntroductionDu="+newdateIntroBCMinFormat
													 + "&dateIntroductionA="+newdateIntroBCMaxFormat
													 + "&dateLivraisonDu="+newdateLivBCMinFormat
													 + "&dateLivraisonA="+newdateLivBCMaxFormat
													 + "&typeCommande="+packageVenteCourante.vTypeCommande
													 + "&etatCommande="+packageVenteCourante.vEtatCommande
													 + "&quantiteDu="+packageVenteCourante.quantiteDu
													 + "&quantiteA="+packageVenteCourante.quantiteA
													 + "&coutDu="+packageVenteCourante.coutDu
													 + "&coutA="+packageVenteCourante.coutA
													 + "&type=pdf";
				                  
				                 console.log("-- URL" + url );
								 downloadService.download(url).then(
										 function(success) {
											// console.log('success : ' + success);
										 }, function(error) {
											// console.log('error : ' + error);
										 });
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
												UrlCommun
														+ "/package/getId:"+
														 $scope.myDataCommandeVente[index].id)
										.success(
												function(datagetCommandeVente) {

													$log.debug("getById : "+ $scope.myDataCommandeVente[index].id +" \n  "+JSON.stringify(datagetCommandeVente,null,"  ") );
													//produitCommande
													$scope.listDetPackage = datagetCommandeVente.listDetPackage;
													

													//disable update de la dropList 'Produit' 
					 								angular.forEach($scope.listDetPackage, function(produitCommande, key){
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
					 						
					 							
					 								$scope.myDataCommandeVente[index].listDetPackage = $scope.listDetPackage;
//												

										
													
													
												});


												var dateDebut = null;

												var dateFin = null;
												if ($scope.myDataCommandeVente[index].dateDebut !== null) {
													dateDebut = $scope.modifierFormatDate($scope.myDataCommandeVente[index].dateDebut);
												} else {
													dateDebut = null;
												}
							
							
												if ($scope.myDataCommandeVente[index].dateFin !== null) {
													dateFin = $scope.modifierFormatDate($scope.myDataCommandeVente[index].dateFin);
												} else {
													dateFin = null;
												}
							
							
												$scope.packageVenteCourante = Object.assign($scope.myDataCommandeVente[index], { dateDebut: dateDebut }, { dateFin: dateFin })
													//  $scope.partieInteresseeCourante = $scope.myData[index]
													? angular.copy($scope.myDataCommandeVente[index])
													: {}; 
							


								// $scope.packageVenteCourante = $scope.myDataCommandeVente[index] ? angular
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
								commande.listDetPackage = $scope.listDetPackage;
			
								
								$http.post(UrlCommun + "/package/creerPackage",	commande)
									 .success(
										function(newCommandeId) {
											$scope.traitementEnCours = "false";
											
								        	$scope.showNotif();
											$log.debug("success creation : "+newCommandeId);
											//
											//getCommandeVente 
											$http.get(
													UrlCommun
																+ "/package/getId:"+newCommandeId)
												.success(
														function(datagetCommandeVente) {
															
															//produitCommande
															$scope.listDetPackage = datagetCommandeVente.listDetPackage;

												
															
															//disable update de la dropList 'Produit' 
							 								angular.forEach($scope.listDetPackage, function(produitCommande, key){
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

															$scope.packageVenteCourante = datagetCommandeVente ? angular
																  .copy(datagetCommandeVente)
																	: {};
																	
																	
													

														});
										});
								
								
								
							}
							
							
							// Mise à jour des Bons de Commandes de Vente
							$scope.updateCommandeVente= function(commande) {
								//produitCommande
								commande.listDetPackage = $scope.listDetPackage;
							
								
								
								$http
										.post(
												UrlCommun
														+ "/package/modifierPackage",
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
        													UrlCommun
																		+ "/package/getId:"+commandeModifiee)
														.success(
																function(datagetCommandeVente) {
							          								// bouton PDF activé
								 									$scope.modePdf = "actif";
																	
																	//produitCommande
																	$scope.listDetPackage = datagetCommandeVente.listDetPackage;
																	
																	
																	
																	//disable update de la dropList 'Produit' 
									 								angular.forEach($scope.listDetPackage, function(produitCommande, key){
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

																	$scope.packageVenteCourante = datagetCommandeVente ? angular
																		  .copy(datagetCommandeVente)
																			: {};
																			
																	$scope.showNotif();
																	
																
																
															
																	
																	
																

																});

												});
							}
							
							
							// Suppression Package vente
							$scope.supprimerPackageVente = function() {
								$log.debug("DELETE .."
										+ $scope.myDataCommandeVente[$scope.index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/package/supprimerPackage:"
													+ $scope.myDataCommandeVente[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
											$scope.myDataCommandeVente.splice($scope.index, 1);
											
										
								});
								$scope.closePopupDelete();
								//$scope.rechercherPartieInteressee({});
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
						        	
				            		$scope.updateProduitCommandDetails($scope.listDetPackage[index]);
						        	
				            		$log.debug("idProduit---" + idProduit);
				            		
				            		$scope.productFilter=[];
				            		$scope.sousFamilleFilter=[];
				            		
				            		$scope.getProductFilter(idProduit);
						        	 
						        	if ($scope.productFilter.length >0){
						        		$scope.listDetPackage[index].designation=$scope.productFilter[0].designation;
						        		$scope.listDetPackage[index].prixUnitaire=$scope.productFilter[0].prixUnitaire;
						        		
						        		 $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
							        	
						        		if($scope.sousFamilleFilter.length>0){
						        			$scope.listDetPackage[index].sousFamille=$scope.sousFamilleFilter[0].designation;
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
								
								$scope.listDetPackage
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
								$scope.listDetPackage.splice(
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
											displayName : 'Réf',
											width:'12%'
										},
										
										{
											field : 'nom',
											displayName : 'Nom',
											width:'20%'
										},
										{
											field : 'clientAbreviation',
											displayName : 'Client',
											width:'20%'
										},
										
										{
											field : 'groupeAbreviation',
											displayName : 'Groupe',
											width:'21%'
										},
										
										{
											field : 'dateDebut',
											displayName : 'Date Debut',
											cellFilter: 'date:\'dd-MM-yyyy\'',
											width:'10%'
										},
										{
											field : 'dateFin',
											displayName : 'Date Fin',
											cellFilter: 'date:\'dd-MM-yyyy\'',
											width:'10%'
										},
										{
											field : '',
											width:'7%',
											cellTemplate : 
											
											
											'<div class="ms-CommandButton float-right" >' +
											 '<button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerCommandeVente()">' +
											 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
											 '</button>' +
											 '<button class="ms-CommandButton-button"  ng-click="showPopupDelete(21)">' +
											 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
											 '</button>' +
											 '	</div> ',
											
											
											// '<div class="buttons">'
											// 	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerCommandeVente()"><i class="fa fa-fw fa-pencil"></i></button>'
											// 	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(21)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
												var packageVenteCourante = $scope.packageVenteCourante;
										
												if (searchText) {
													var ft = searchText
															.toLowerCase();
													$scope.isLoading=true;
													$http
															.post(
																	UrlCommun+ "/package/rechercheMulticritere",
																	packageVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		$scope.myDataCommandeVente = largeLoad.list
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
																	UrlCommun+ "/package/rechercheMulticritere",
																	packageVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		
																		console.log("largeLoad" + JSON.stringify(largeLoad, null, " ") );
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
			
