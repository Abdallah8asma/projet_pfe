'use strict'
angular
		.module('gpro.stockNv', [ "ngResource" ])
		.controller(
				'StockNvController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						'$window',
						function($scope, $filter, $http, $log, downloadService, UrlCommun,UrlAtelier,$window) {
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
							$scope.produitDepotCourant = {};
							$scope.resultatRecherche = $scope.listeProduitDepot;
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

							/***************************************************
							 * Gestion Cache nv
							 */
							
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
							
							$scope.listeMagazinCache();
							
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
							
							
				            
				            $scope.downloadProduitDepotList = function(
				            		produitDepotCourant) {

								var url = UrlAtelier
										+ "/fiches/produit-depot-list?idDepot="
										+ produitDepotCourant.idDepot
										+ "&idProduit="
										+ produitDepotCourant.idProduit   ;
								          

								$log.debug("--downloadAllBonLiv URL" + url);


							//	var fileName = 'Liste des Stocks';
								var a = document.createElement('a');
								document.body.appendChild(a);
								downloadService.download(url).then(function (result) {
									var heasersFileName = result.headers(['content-disposition']).substring(17);
									var fileName = heasersFileName.split('.');
									 fileName[0] =  'Etat_du_Stock_Article_par_Magasin_' + formattedDate(new Date());
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
									
								 
								});
					
								
								// downloadService.download(url).then(
								// 		function(success) {
								// 			$log.debug('success : ' + success);
								// 		}, function(error) {
								// 			$log.debug('error : ' + error);
								// 		});
							};
							

							 function formattedDate(date) {
							      var d = new Date(date),
							        month = '' + (d.getMonth() + 1),
							        day = '' + d.getDate(),
							        year = d.getFullYear();

							      if (month.length < 2) month = '0' + month;
							      if (day.length < 2) day = '0' + day;
							      return [year, month, day].join('-');
							    }
							
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
							$scope.listeProduitDepot = function(produitDepotCourant) {
								//$http.get(UrlCommun + "/partieInteressee/all")
									$http.post(UrlAtelier + "/ProduitDepot/rechercheMulticriterePrDe",produitDepotCourant)
								
							
										.success(function(data) {
											$log.debug("listeProduitDepot : "+data.length);
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
							$scope.rechercherProduitDepot = function(
									produitDepotCourant) {
								$log.debug("recherche en cours ..");
								console.log("iddepot: "+produitDepotCourant.idDepot+"  idproduit: "+produitDepotCourant.idProduit)
								$http
										.post(
												UrlAtelier
														//+ "/partieInteressee/recherchePIMulticritere",
												+"/ProduitDepot/rechercheMulticriterePrDe",
												produitDepotCourant)
										.success(
												function(resultat) {
													//$log.debug("listeRecherchée: "+ resultat.length);
													$scope.myData = resultat.produitdepotvalues;
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
							$scope.rechercherProduitDepot($scope.produitDepotCourant);
							
							
							// ** Ajout Partie Interesse **
							$scope.AffectationPartieInteressee = function(
									partieInteressee) {
								$scope.produitDepotCourant = {};
								$scope.creationPartieInteressee.$setPristine();
								$scope.listeRepresentant = [];
								$scope.listeDocument = [];
								$scope.displayMode = "edit";
							}
							
							
							
				
							
							
						
					

							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.produitDepotCourant = {};
								$scope.rechercherProduitDepot($scope.produitDepotCourant);
								$scope.listeRepresentant = [];
								$scope.listeDocument = [];
								$scope.displayMode = "list";
							}
							
						
						//	$scope.listeProduitDepot();

							
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
															field : 'referenceProduit',
															displayName : 'Réf.Article',
						                					width:'16%'

														},
														{
															field : 'designationProduit',
															displayName : 'Désignation',
						                					width:'30%'
														},
														{
															field : 'designationMagasin',
															displayName : 'Magasin',
						                					width:'15%'
														},
														{
															field : 'quantite',
															displayName : 'Quantité',
						                					width:'12%'
														},
														
														{
															field : 'seuilMin',
															displayName : 'Seuil Min',
						                					width:'12%'
														},
														
														{
															field : 'seuilMax',
															displayName : 'Seuil Max',
						                					width:'12%'
														},
														
														 ];
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
										enableColumnResize: true,
										enableHighlighting : true,
										totalServerItems : 'totalServerItems',
										pagingOptions : $scope.pagingOptions,
										selectedItems : $scope.selectedRows,
										filterOptions : $scope.filterOptions,
									};
							/** Fin de gestion des Grids de la partie Interesse */

				
								
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/

							// Liste des CategorieCache
							$scope.listCategoriePICache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeCategoriePICache")
										.success(
												function(dataCategorieCache) {
													$log.debug("listeCategorie : "+dataCategorieCache.length);
													$scope.ListCategoriePICache = dataCategorieCache;

												});
							}
							// Liste des FamilleCache
							$scope.listFamillePICache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeFamillePICache")
										.success(
												function(dataFamilleCache) {
													$log.debug("listeFamillePICache : "+dataFamilleCache.length);
													$scope.ListFamillePICache = dataFamilleCache;
												});
							}
							// Liste des TypeCache
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
							$scope.listSitePICache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeSitePartieInteresseeCache")
										.success(
												function(dataSiteCache) {
													$log.debug("listeSitePICache : "+dataSiteCache.length);
													$scope.ListSitePICache = dataSiteCache;

												});
							}
							$scope.listCategoriePICache();
							$scope.listFamillePICache();
							$scope.listTypePICache();
							$scope.listSitePICache();
						} ])
		
				//new filter
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
//								$scope.produitDepotCourant.dateIntroduction = new Date();
//							};
//							$scope.today();
//							$scope.clear = function() {
//								$scope.produitDepotCourant.dateIntroduction = null;
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
