angular
		.module('gpro.engin', [ "ngResource" ])
		.controller(
				'EnginController',
				[
						'$rootScope',
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						function($rootScope, $scope, $filter, $http, $log,
								downloadService, UrlCommun, UrlAtelier) {
							 $log.info("=========Marche========");
							var data;

							$scope.displayMode = "list";
							$scope.marcheCourant = {};
						//	$log.debug("****Marche : listeMarche : "+ $rootScope.bllisteMarche.length);

							/***************************************************
							 * Gestion des DropListe & cache
							 **************************************************/


							$scope.myData = $rootScope.bllisteMarche;

							/***************************************************
							 * Gestion BonLivraison -Vente
							 **************************************************/
							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 ],
								pageSize : 13,
								currentPage : 1
							};

							// Annuler Recherche
							$scope.annulerAjout = function() {

								// initialiser le design des champs
								$scope.creationMarcheForm.$setPristine();
								// init de l'objet courant
								$scope.marcheCourant = {};

								// interface en mode : list
								$scope.displayMode = "list";
							}

							 $scope.listePartieInteresseeCache = function() {
								 $http
								 .get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
								 .success(
										 function(dataPartieInteressee) {
											 $scope.listePartieInteresseeCache = dataPartieInteressee;
											 $log.debug("listePartieInteresseeCache : "+dataPartieInteressee.length)

										 });
							 }
							
							 $scope.listePartieInteresseeCache();
							
							
							// AffectationBLVente BonLivVente
							$scope.affectationMarche = function(marche) {

								$scope.marcheCourant = {};
								$scope.marcheCourant = marche ? angular
										.copy(marche) : {};

								$scope.sauvegarderBonVente(marche);

							}

							// Sauvegarder Marche
							$scope.sauvegarderBonVente = function(marche) {

								if (angular.isDefined(marche.id)) {
									$log.debug("Sauvegarder Modification : "+ marche.immatriculation);
									$scope.updateMarche(marche);
								} else {
									$log.debug("Sauvegarder Ajout : "+ marche.id);
									$scope.creerMarche(marche);
								}
							}

							// Création Marche
							$scope.creerMarche = function(marche) {
								console.log("----Marche id  : "+marche.immatriculation);

								
								
								$http
										.post(UrlAtelier + "/engin/create",
												marche)
										.success(
												function(marcheId) {
													$log.debug("Success Creation"+ marcheId);
													$scope.myData.push($scope.marcheCourant);
													$scope.annulerAjout();
												});
								
							}

							// Suppression Marche
							$scope.supprimerMarche = function() {
								$log.debug("deleting ..");
								// TODO: Service de suppression: à revoir.
								// erreur: operation executée mais avec msg 403!
								// var index = this.row.rowIndex;
								$http(
										{
											method : "DELETE",
											url : UrlAtelier
													+ "/engin/delete:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
									$log.debug("Success Delete");
									$scope.myData.splice($scope.index, 1);
									// $scope.closePopupDelete();
									// $scope.rechercherBonLivraisonVente({});
								});
								$scope.closePopupDelete();
								
								$scope.myData.splice($scope.index, 1);
								//$scope.listeMarcheFct();
							}
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
																	field : 'marque',
																	displayName : 'Marque',
																},
														{
															field : 'type',
															displayName : 'Type',
														},
														{
															field : 'immatriculation',
															displayName : 'Immatriculation',
														},
														{
															field : '',
															cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
																	+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(6)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
											var marcheCourant = $scope.marcheCourant;
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.get(
																UrlAtelier
																		+ "/engin/getAll")
														.success(
																function(
																		largeLoad) {
																	data = largeLoad
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

												$http
														.get(
																UrlAtelier
																		+ "/engin/getAll")
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad,
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
							/** Fin de gestion des Grids de la BonVente */

						} ]);