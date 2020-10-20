'use strict'
angular
		.module('gpro.manageBoutique', [ "ngResource" ])
		.controller(
				'manageBoutiqueController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'UrlCommun',
						 'UrlAtelier',
						function($scope, $filter, $http, $log, downloadService, UrlCommun,UrlAtelier) {
							// Déclaration des variables globales utilisées
							$log.info("=============PartieInteressee===============");
							var data;
							$scope.displayMode = "list";
							$scope.displayMenu = "pi";
							$scope.listeRepresentant = [];
							$scope.listeDocument = [];
							$scope.ListCategoriePICache = [];
							$scope.ListTypePICache = [];
							$scope.ListFamillePICache = [];
							$scope.ListSitePICache = [];
							$scope.listeRegionCache = [];
							$scope.ListActifPI = [{actif:true,designation:'oui'},{actif:false,designation:'non'}];
							$scope.partieInteresseeCourante = {};
							$scope.resultatRecherche = $scope.listePartieInteressee;
							/***************************************************
							 * Gestion de la Menu PI
							 **************************************************/
							$scope.changeTaPartieInteresse = function() {
								$scope.displayMenu = "pi";
							}
							$scope.changeTaElementBase = function() {
								$scope.displayMenu = "eb";
							}
							$scope.changeTaGestionCommerciale = function() {
								$scope.displayMenu = "gc";
							}
							
							 $scope.societes = [];
							 
							 
							 $scope.listeSocietes = function() {
									$http
									.get(
									   UrlCommun      
											+ "/societe/all"
											)
											.success(
													function(data) {
														$scope.societes=data;
													});
										
								}
							 
							 
							 $scope.listeSocietes();
							 
							
							 $scope.downloadAllExcel = function(partieInteresseeCourante) {
								 	
									var url;
								
					       			url = UrlCommun + "/fiches/listpi?vReference=" + partieInteresseeCourante.vReference
										 					 + "&vRaisonSociale=" + partieInteresseeCourante.vRaisonSociale
															 + "&vCategoriePartieInteressee="+partieInteresseeCourante.vCategoriePartieInteressee
															 + "&groupeClientId="+partieInteresseeCourante.groupeClientId
															 
															 + "&vTypePartieInteressee="+partieInteresseeCourante.vTypePartieInteressee
															 + "&actif="+partieInteresseeCourante.actif
															 + "&vFamillePartieInteressee=1"
															 
															
															;
										
					                   $log.debug("-- URL--- :" + url );
									 downloadService.download(url).then(
											 function(success) {
												// $log.debug('success : ' + success);
											 }, function(error) {
												// $log.debug('error : ' + error);
											 });
								 };
								 
								 
							// Liste des CategorieCache
							$scope.listGroupeClient = function() {
								$http
										.get(
												UrlCommun
														+ "/groupeClient/all")
										.success(
												function(dataCategorieCache) {
												//	$log.debug("listeCategorie : "+dataCategorieCache.length);
													$scope.listGroupeClient = dataCategorieCache;

												});
							}
							$scope.listGroupeClient();

							/***************************************************
							 * Gestion Cache
							 */
							// Liste des DeviseCache
							$scope.ListDeviseCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeDeviseCache")
										.success(
												function(dataDeviseCache) {
													$log.debug("listeDeviseCache :"
																	+ dataDeviseCache.length);
													$scope.ListDeviseCache = dataDeviseCache;
												});
							}
							// Liste des TypeDocs
						       $scope.listeTypeDocumentCache = function() {
						        $http
						          .get(
						            UrlCommun
						              + "/gestionnaireCache/listeTypeDocumentCache")
						          .success(
						            function(dataTypeDocCache) {
						         
						             
						             $scope.listeTypeDocumentCache = dataTypeDocCache.filter(e => e.module === 'PI_CLIENT');
										
						            });
						       }
						       // Liste des regions
						       $scope.listeRegionCache = function() {
						       	//TODO cache
						        $http
						          .get(
						            UrlCommun
						              + "/region/getAll")
						          .success(
						            function(dataRegionCache) {
						             $log.debug("listeRegionCache : "
						                 + dataRegionCache.length);
						             $scope.listeRegionCache = dataRegionCache;
						            });
						       }

						       
						       $scope.listeRegionCache();
						       $scope.ListDeviseCache();
						       $scope.listeTypeDocumentCache();
							/***************************************************
							 * Gestion de la PI
							 **************************************************/
						   	$scope.deleteValue="oui";
							  //Annuler Ajout
						    $scope.cancelAddPartieInteressee = function(rowform, index, id, designation, liste){
						    	//$log.debug("* Designation "+liste[0].designation);
						    		  if (angular.isDefined(id)) {
						    			 
						    				//  $log.debug("DEF ID");
						    				  $scope.deleteValue="non";
						    				  rowform.$cancel();
						    				//  $log.debug("CANCEL");
						    		  }else{	
						    			 // $log.debug("UND ID  "+id);
						    			  if(designation ==""){
						    				  $scope.deleteValue=="oui"
						    				$log.debug.log("Designation : "+designation);
						    				  $log.debug("$scope.deleteValueOUI : "+$scope.deleteValue);
						    				  liste.splice(index, 1);
									    	  rowform.$cancel();
						    				  $log.debug("DELETE")
						    			  }else{
						    				  $log.debug("Designation :"+designation);
						    				  $log.debug("$scope.deleteValueNON : "+$scope.deleteValue);
						    				  rowform.$cancel();
						    				  $log.debug("CANCEL");
						    			  }
						    		}
						    		  $scope.deleteValue="oui";
						    }
						    
						    //Variale de Pagination de la grid
						    $scope.pagingOptions = {
									pageSizes : [ 5, 10, 13 ],
									pageSize : 13,
									currentPage : 1
								};
						    
							// Lister les parties interessées
							$scope.listePartieInteressee = function() {
								$http.get(UrlCommun + "/boutique/all")
										.success(function(data) {
											$log.debug("listePartieInteressee : "+data.length);
											$scope.partieInteresssees = data;
											$scope.myData = data;
											//data, page,pageSize
											$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																			   $scope.pagingOptions.pageSize										
																			   );
											
											
											
											
										});
							}
							
							//Init data list
							$scope.initMyData = [];
							
							
							// Rechercher PI
							$scope.rechercherPartieInteressee = function(
									partieInteresseeCourante) {
							
								$log.debug("recherche en cours ..");
								$http
										.post(
												UrlCommun
														+ "/boutique/rechercheMulticritere",
												partieInteresseeCourante)
										.success(
												function(resultat) {
												
													$scope.myData = resultat;
													$scope.initMyData = $scope.myData;
													
													//data, page,pageSize
													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																					   $scope.pagingOptions.pageSize										
																					   );
													
													$scope.displayMode = "rechercher";
													$scope.recherchePartieIntereseeFormCritere
															.$setPristine();
												});

							}
							
							//Init liste PI (ng-grid)
							$scope.rechercherPartieInteressee($scope.partieInteresseeCourante);
							
							
							// ** Ajout Partie Interesse **
							$scope.AffectationPartieInteressee = function(
									partieInteressee) {
								$scope.partieInteresseeCourante = {"dateIntroduction":new Date(),
										                            "actif":true,
										                            "passager":false
										                           };
								$scope.creationPartieInteressee.$setPristine();
								$scope.listeRepresentant = [];
								$scope.listeDocument = [];
								
							
								
								$scope.displayMode = "edit";
							}
							
							
							
							
							// Ajout et Modification Partie Interessee
							$scope.modifierOuCreerPartieInteressee = function() {
								var index = this.row.rowIndex;
								// getArticle
								$http
										.get(
												UrlCommun
														+ "/boutique/getId:"
														+ $scope.myData[index].id)
										.success(
												function(datagetPI) {
													
													$scope.partieInteresseeCourante = datagetPI;
												});
								$scope.partieInteresseeCourante = $scope.myData[index] ? angular
										.copy($scope.myData[index])
										: {};

								$scope.displayMode = "edit";
							}
							
							
							
							// Sauvegarder PI
							$scope.sauvegarderAjout = function(partieInteressee) {
								if (angular.isDefined(partieInteressee.id)) {
									$scope
											.updatePartieInteressee(partieInteressee);
								} else {
									$scope
											.creerPartieInteressee(partieInteressee);
								}
							
							}
							// Mise à jour de la partie interessée
							$scope.updatePartieInteressee = function(
									partieInteressee) {
							
								$http
										.post(
												UrlCommun
														+ "/boutique/modifierBoutique",
												partieInteressee)
										.success(
												function(
														partieInteresseeModifiee) {
											
													$scope.rechercherPartieInteressee({});
												
													$scope.displayMode = "list";
												});
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.creationPartieInteressee.$setPristine();
								$scope.partieInteresseeCourante = {};
							}
							// Création PI
							$scope.creerPartieInteressee = function(
									partieInteressee) {
					
								$http
										.post(
												UrlCommun
														+ "/boutique/creerBoutique",
												partieInteressee)
										.success(
												function(newPartieInteressee) {
												
													$scope.displayMode = "list";
													$scope.rechercherPartieInteressee({});
												
															
													
												});
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.creationPartieInteressee.$setPristine();
								$scope.partieInteresseeCourante = {};
							}

							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.partieInteresseeCourante = {};
								$scope.rechercherPartieInteressee($scope.partieInteresseeCourante);
								$scope.listeRepresentant = [];
								$scope.listeDocument = [];
								$scope.displayMode = "list";
							}
							
							// Suppression PI
							$scope.supprimerPartieInteressee = function() {
								$log.debug("DELETE .."
										+ $scope.myData[$scope.index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/boutique/supprimerBoutique:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
											$scope.myData.splice($scope.index, 1);
											
										
								});
								$scope.closePopupDelete();
								$scope.rechercherPartieInteressee({});
							}
						//	$scope.listePartieInteressee();

							/** Fin de gestion de la partie Interessée */

							/***************************************************
							 * Gestion des representants
							 **************************************************/
							// ajout d'un Representant
							$scope.ajoutRepresentant = function() {
								$scope.representantInserree = {
									nom : '',
									fonction : null,
									email : null
								};
								$scope.listeRepresentant
										.push($scope.representantInserree);

							};

							// Enregistrer Representant
							$scope.saveRepresentant = function(data, id) {
								$scope.deleteValue = "non";
								var regExpValidEmail = new RegExp(
										"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$",
										"gi");
								// $scope.Representant not updated yet
								if (!data.email
										|| !data.email.match(regExpValidEmail)) {
									$scope.showPopupDelete(2);
									$scope.rowform.$cancel();
									return;
								} else {
									angular.extend(data, {
										id : id
									});
								}
							};

							// Supprimer representant
							$scope.removeRepresentant = function(index) {
								$scope.listeRepresentant.splice(index, 1);
							};
							/** Fin de gestion des representants */
							

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
															field : 'reference',
															displayName : 'Réf.',
						                					width:'10%'

														},
														{
															field : 'abreviation',
															displayName : 'Abreviation',
						                					width:'20%'
														},
														{
															field : 'raisonSociale',
															displayName : 'Raison Sociale',
						                					width:'20%'
														},
													
														{
															field : 'telephone',
															displayName : 'Téléphone',
						                					width:'10%'
														},
														{
															field : 'fax',
															displayName : 'Fax',
						                					width:'10%'
														},
														
														{
															field : 'email',
															displayName : 'Email',
						                					width:'13%'
														},
														{
															field : 'adresse',
															displayName : 'Adresse',
						                					width:'14%'
														},
													     {
												              field: '',
												              //	width: '10%',
												              fontfamily: 'Poppins, Helvetica, sans-serif',
												              cellTemplate:
												              `<div class="ms-CommandButton float-right">
												               <button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerPartieInteressee()">
												               <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
												               </button>
												               
												                	</div> `,

												              // <div class="buttons" ng-show="!rowform.$visible">'
												              // + '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerPartieInteressee()">	<i class="fa fa-fw fa-pencil"></i></button>'
												              // + '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(1)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
												            } ];
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
													if (searchText) {
														var ft = searchText
																.toLowerCase();

														var data  = $scope.initMyData.filter(function(item) {
									                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
														});
														
									                        $scope
															.setPagingData(
																	data,
																	page,
																	pageSize);
									                        
													} else {
														
														$scope
														.setPagingData(
																$scope.initMyData,
																page,
																pageSize);
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
										enableHighlighting : true,
										totalServerItems : 'totalServerItems',
										pagingOptions : $scope.pagingOptions,
										selectedItems : $scope.selectedRows,
										filterOptions : $scope.filterOptions,
									};
							/** Fin de gestion des Grids de la partie Interesse */

							/***************************************************
							 * Gestion des Documents
							 **************************************************/
							/** ***Liste desVariables ****/
						       $scope.listeDocument = [];
						       $scope.listeTypeDoc = [];
                               $scope.name="CLIENT";
						       // GetId.designation
						       $scope.type = {

						        status : ''
						       };
						       $scope.showStatus = function(id) {
						    	   
						    	   var designation="";
						    	   for(var i=0;i<$scope.listeTypeDocumentCache.length;i++)
					    		   {
						    		   console.log("listeTypeDocumentCache[i]="+ $scope.listeTypeDocumentCache[i].id);
						    		   if( $scope.listeTypeDocumentCache[i].id==id &&  $scope.listeTypeDocumentCache[i].module=="PI_CLIENT"){
						    			   designation= $scope.listeTypeDocumentCache[i].designation;
						    			   //console.log("designation type doc :" +designation);
						    			   return designation;
						    		   }
						    		   /*else{
						    			   return "vide";
						    		   }*/
						    		   
					    		   }
						    	   
						    	   
						    	   /*$scope.type.status = id;
							       console.log(" $scope.listeTypeDocumentCache: "+ $scope.listeTypeDocumentCache);
							       var selected = $filter('filterProduit')(
							          $scope.listeTypeDocumentCache, {
							           id : $scope.type.status
							          });
							        if ($scope.type.status && selected.length) {
							         return selected[0].designation;
							        }else {
							                   return "Not Set";
							                }
						    	   */
						       /*$scope.type.status = id;
						       console.log(" $scope.listeTypeDocumentCache: "+ $scope.listeTypeDocumentCache);
						       var selected = $filter('filterProduit')(
						          $scope.listeTypeDocumentCache, {
						           id : $scope.type.status
						          });
						        if ($scope.type.status && selected.length) {
						         return selected[0].designation;
						        }else {
						                   return "Not Set";
						                }
						          */      
						                
						       };           
						       // ajout d'un Document Partie Interesee
						       $scope.ajoutDocPI = function() {
						        $scope.DocPartieInteresseInserree = {
						         typeDocument : '',
						         vUUIDDocument : '',
						         path:''
						        };
						        $scope.listeDocument
						          .push($scope.DocPartieInteresseInserree);
						       };
						       
						       
						       // Enregistrer Document Partie Interesee
						       $scope.saveDocPI = function(dataDocPI, id) {
						    	   $scope.deleteValue = "non";
						        angular.extend(dataDocPI, {
						         id : id
						        });
						        console
						          .log("Success Save Document in Partie Interessee "
						            + dataDocPI.id);
						       };
						    // Supprimer Document
								$scope.removeDocPi = function(index) {
									$scope.listeDocument.splice(index, 1);
								};
								
							//Download Document
								$scope.download = function(uuid) {
									var url = UrlCommun+"/gestionnaireDocument/document/" + uuid;
									
								    downloadService.download(url)
								        .then(function(success) {
								            $log.debug('success : ' + success);
								        }, function(error) {
								            $log.debug('error : ' + error);
								        });
								};
								
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/

						} ])
	
						.filter('filterProduit', function() {
				  return function(liste, id) {
					 var listeFiltre = [];
					
					 angular.forEach(liste, function(elementListe, key){
						
						if(elementListe.id == id.id){
							
							listeFiltre.push(elementListe);
						}
							
					 });
					
					 return listeFiltre;
				  };
				})
		.controller(
				'DatepickerDController',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
//							// date piker defit
//							$scope.today = function() {
//								$scope.partieInteresseeCourante.dateIntroduction = new Date();
//							};
//							$scope.today();
//							$scope.clear = function() {
//								$scope.partieInteresseeCourante.dateIntroduction = null;
//							};
							// Disable weekend selection
							$scope.disabled = function(date, mode) {
								return (mode === 'day' && (date.getDay() === 0 || date
										.getDay() === 6));
							};
							$scope.toggleMin = function() {
								$scope.minDate = $scope.minDate ? null
										: new Date();
							};
							$scope.toggleMin();
							$scope.open = function($event) {
								$event.preventDefault();
								$event.stopPropagation();
								$scope.opened = true;
							};
							$scope.dateOptions = {
								formatYear : 'yy',
								startingDay : 1
							};
							$scope.initDate = new Date('20-08-2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ]);
