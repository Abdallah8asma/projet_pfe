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
							$scope.rouleauCourant = null;
							
							/********************************
							 * Gestion des listes deroulantes*
							 * à changer par Cache
							 ********************************/
							//listeSortie
          					$scope.listeSortie = [{id:1,designation:"Non"},{id:2,designation:"Oui"},{id:3,designation:"Tous"}];
          					
          					//listeOrigine
							$scope.listeOrigine = [{id:1,designation:"Non"},{id:2,designation:"Oui"},{id:3,designation:"Tous"}];
          					
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
							$scope.listeMise();
							$scope.listeProduitCache();
							$scope.listeClientCache();
							$scope.listeEntrepotCache();

							
							/***************************************************
							 * Gestion des Rouleaux
							 **************************************************/
							
						    $scope.pagingOptions = {
									pageSizes : [ 5, 10, 13 ],
									pageSize : 13,
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
											field : 'reference',
											displayName : 'Réf.Roul',
						                	width:'5%'
										},
										{
											field : 'referenceMise',
											displayName : 'N°.Mise :',
						                	width:'5%'
										},
										{
											field : 'partieInteresseeIdDesignation',
											displayName : 'Client',
						                	width:'15%'
										},
										{
											field : 'produitReference',
											displayName : 'Tissu',
						                	width:'10%'
										},
										{
											field : 'produitIdSousFamille',
											displayName : 'Type',
						                	width:'10%'
										},
										{
											field : 'produitIdComposition',
											displayName : 'Composition',
						                	width:'10%'
										},
										{
											field : 'laize',
											displayName : 'Laize',
						                	width:'5%'
										},
										{
											field : 'poids',
											displayName : 'Poids',
						                	width:'5%'
										},
										{
											field : 'metrage',
											displayName : 'Metrage',
						                	width:'5%'
										},
										{
											field : 'choixDesignation',
											displayName : 'Cr. Qualité',
						                	width:'5%'
										},
										{
											field : 'entrepotDesignation',
											displayName : 'Entrepot',
						                	width:'10%'
										},
										{
											field : 'emplacement',
											displayName : 'Emplacement',
						                	width:'10%'
										},
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
