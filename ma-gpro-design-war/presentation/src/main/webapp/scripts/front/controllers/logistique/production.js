'use strict'

angular
		.module('atelier.rouleau', [ "ngResource" ])
		.controller(
				'rouleauController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						function($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier) {
							$log.info("=========Rouleau========");
							// Déclaration des variables globales utilisés
							var data;
							$scope.displayMode = "list";
							$scope.rouleauCourant = {};
							$scope.listUser = [];
							
							var d =  new Date();
                            d.setHours(0,0,0,0);

						//	$scope.rouleauCourant.dateProductionA = new Date();
							//$scope.rouleauCourant.dateProductionDe = d;
							/********************************
							 * Gestion des listes deroulantes*
							 * à changer par Cache
							 ********************************/
							//listeSortie
          					$scope.listeSortie = [{id:1,designation:"non"},{id:2,designation:"oui"},{id:3,designation:"tous"}];
          					
          					//listeOrigine
							$scope.listeOrigine = [{id:1,designation:"Non"},{id:2,designation:"Oui"},{id:3,designation:"Tous"}];
          					
							
							
							  var toUTCDate = function(date){
								    var _utc = new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(),  date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
								    return _utc;
								  };

								  var millisToUTCDate = function(millis){
								    return toUTCDate(new Date(millis));
								  };

								    $scope.toUTCDate = toUTCDate;
								    $scope.millisToUTCDate = millisToUTCDate;
							
							// Liste des Mises
							$scope.getListeUsers = function() {
								var mise ={};
								$http
								.post(
										UrlCommun
												+ "/user/rechercheMulticritere",mise)
								.success(
										function(resultat) {
							
											$scope.listUser = resultat.list;

												});
							}
							$scope.getListeUsers();
							
						
							 $scope.downloadProdUser= function (objectCourant)
							 {
									var url;
									
							

									url = UrlAtelier + "/fiches/production-user?numMise=" + objectCourant.numMise
										 					 + "&idProduitReference=" + objectCourant.idProduitReference
															 + "&produitId="+objectCourant.produitId
															 + "&username="+objectCourant.username 
															 + "&dateProductionDe="+objectCourant.dateProductionDe
															 + "&dateProductionA="+objectCourant.dateProductionA
															 + "&typeOf="+objectCourant.typeOf
													 ;
														
										
					                   $log.debug("-- URL--- :" + url );
									 downloadService.download(url).then(
											 function(success) {
												// $log.debug('success : ' + success);
											 }, function(error) {
												// $log.debug('error : ' + error);
											 });
							 }
							 
							 
							 $scope.downloadProdUserPrice= function (objectCourant)
							 {
									var url;
									
							

									url = UrlAtelier + "/fiches/production-user-price?numMise=" + objectCourant.numMise
										 					 + "&idProduitReference=" + objectCourant.idProduitReference
															 + "&produitId="+objectCourant.produitId
															 + "&username="+objectCourant.username 
															 + "&dateProductionDe="+objectCourant.dateProductionDe
															 + "&dateProductionA="+objectCourant.dateProductionA
															 + "&typeOf="+objectCourant.typeOf
													 ;
														
										
					                   $log.debug("-- URL--- :" + url );
									 downloadService.download(url).then(
											 function(success) {
												// $log.debug('success : ' + success);
											 }, function(error) {
												// $log.debug('error : ' + error);
											 });
							 }
							
							
							
							// Liste des Mises
							$scope.listeMise = function() {
								var mise ={};
								$http
								.post(
										UrlAtelier
												+ "/mise/rechercheMiseMulticritere",mise)
								.success(
										function(resultat) {
											$log.debug("listeMise : "+resultat.listeElementRechecheMiseValeur.length);
											$scope.listeMise = resultat.listeElementRechecheMiseValeur;

												});
							}
							
							//listeChoixRouleauCache
          					$scope.listeChoixRouleauCache = function() {
          						$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeChoixRouleauCache").success(
          								function(data) {
          									$scope.listeChoixRouleauCache = data;
          									$log.debug("listeChoixRouleauCache : "+data.length);

          								});
          					}
          					
							// Liste des produits
							$scope.listeProduitCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeProduitCache").success(
										function(data) {
											$log.debug("listeProduitCache "+data.length);
											$scope.listeProduitCache = data;

										});
							}

							// listeEntrepotCache
							$scope.listeEntrepotCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeEntrepotCache").success(
										function(data) {
											$log.debug("listeEntrepotCache "+data.length);
											$scope.listeEntrepotCache = data;

										});
							}
													
							// Liste des PartieInteressee (avec FamilleId=1)
							$scope.listeClientCache = function() {
								$http
										.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPartieInteressee) {

													$scope.listeClientCache = dataPartieInteressee;
												});
							}
							$scope.listeChoixRouleauCache();
							//$scope.listeMise();
							$scope.listeProduitCache();
							$scope.listeClientCache();
							$scope.listeEntrepotCache();

							
							/***************************************************
							 * Gestion des Rouleaux
							 **************************************************/
							
						    $scope.pagingOptions = {
									pageSizes : [ 5, 10, 13 , 1040 ],
									pageSize : 1040,
									currentPage : 1
								};

								// Lister les Rouleaux
								$scope.rechercherRouleau = function(rouleauCourant) {
									$log.debug("---rouleauCourant:  "+JSON.stringify(rouleauCourant, null, "  "));
									$http.post(
													UrlAtelier
															+ "/rouleaufini/rechercheMulticritereStandard",
													rouleauCourant)
											.success(
													function(resultat) {
														$scope.myData = resultat.list;
														// Pagination du resultat de
														// recherche
														// data, page,pageSize
														$scope
																.setPagingData(
																		$scope.myData,
																		$scope.pagingOptions.currentPage,
																		$scope.pagingOptions.pageSize);

														$log.debug("listeRouleau : "+ resultat.list.length);
														
														$scope.rechercheRouleauForm.$setPristine();
													});

								}
							

								// annuler Recherche
								$scope.annulerAjout = function() {
									$scope.rouleauCourant = {};
									$scope.rouleauCourant.inBonDeSortie="non";
									$scope.rechercherRouleau($scope.rouleauCourant);
									$scope.rechercheRouleauForm.$setPristine();
							
							
								}
								
							/***************************************************
							 * Gestion des Grids de recherche
							 **************************************************/
							 $scope.colDefs = [];
							$scope.$watch('myData',function() {
									$scope.colDefs = [
                                        	{
															field : 'dateIntroduction',
															displayName : 'Date Introduction',
															cellFilter : 'date:"dd-MM-yyyy HH:mm:ss" : timezone ',
															width : '12%'
											},
									
									
										{
											field : 'reference',
											displayName : 'Réf',
						                	width:'8%'
										},
										{
											field : 'referenceMise',
											displayName : 'Of',
						                	width:'8%'
										},
										{
											field : 'produitIdSousFamille',
											displayName : 'Type O.F',
						                	width:'10%'
										},
										{
											field : 'produitReference',
											displayName : 'Article',
						                	width:'8%'
										},
										
										{
											field : 'produitIdDesignation',
											displayName : 'Designation',
						                	width:'20%'
										},
									/* 	{
											field : 'partieInteresseeIdDesignation',
											displayName : 'Client',
						                	width:'15%'
										}, */
										
										
							/* 			{
											field : 'produitIdComposition',
											displayName : 'Composition',
						                	width:'10%'
										},
									
										{
											field : 'poids',
											displayName : 'Poids',
						                	width:'5%'
										}, */
										{
											field : 'metrage',
											displayName : 'Qte',
						                	width:'5%'
										},
										{
											field : 'responsable',
											displayName : 'Resp. Prod.',
						                	width:'10%'
										},
										{
											field : 'responsableExpedition',
											displayName : 'Resp. Exp',
						                	width:'10%'
										},
								/* 	
										{
											field : 'entrepotDesignation',
											displayName : 'Entrepot',
						                	width:'10%'
										},
									 */
										{
											field : '',
						                	width:'5%',
											cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
														+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
														var data;
														var rouleauCourant = $scope.rouleauCourant;
														if (searchText) {
															var ft = searchText.toLowerCase();
															$http
																	.post(
																			UrlAtelier
																			+ "/rouleaufini/rechercheMulticritereStandard",rouleauCourant)
																	.success(
																			function(
																					largeLoad) {
																				$log.debug("---success "+JSON.stringify(largeLoad.list, null, "  "));
																				data = largeLoad.list
																						.filter(function(item) {
																							return JSON.stringify(item)
																									.toLowerCase()
																									.indexOf(ft) != -1;
																						});
																				$scope.setPagingData( data,	page, pageSize);
																			});

														} else {
															
															$http
																.post(
																	UrlAtelier
																			+ "/rouleaufini/rechercheMulticritereStandard",
																				rouleauCourant)
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
							/** Fin de gestion des Grids de la produit */
							
						} ])
		.controller(
				'DateIntroCtrl',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
							// date piker defit
//							$scope.today = function() {
//								$scope.articleCourante.dateIntroduction = new Date();
//							};
//							$scope.today();
							$scope.clear = function() {
								$scope.articleCourante.dateIntroduction = null;
							};
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
							$scope.initDate = new Date('20/08/2015');
							 $scope.formats = ['dd-MMMM-yyyy', 'dd/MM/yyyy', 'dd.MM.yyyy', 'shortDate'];
							    $scope.format = $scope.formats[0];

						} ])
