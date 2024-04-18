angular
	.module('gpro.marche', ["ngResource"])
	.controller(
		'MarcheController',
		[
			'$rootScope',
			'$scope',
			'$filter',
			'$http',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'$window',
			function ($rootScope, $scope, $filter, $http, $log,
				downloadService, UrlCommun, UrlAtelier,$window) {
				$log.info("=========Marche========");
				var data;

				$scope.displayMode = "list";
				$scope.marcheCourant = {};
				$log.debug("****Marche : listeMarche : " + $rootScope.bllisteMarche.length);
				
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
					$scope.formIsInvalid = "false";
				}

				/***************************************************
				 * Gestion des DropListe & cache
				 **************************************************/


				$scope.myData = $rootScope.bllisteMarche;

				/***************************************************
				 * Gestion BonLivraison -Vente
				 **************************************************/
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13],
					pageSize: 13,
					currentPage: 1
				};

				// Annuler Recherche
				$scope.annulerAjout = function () {

					// initialiser le design des champs
					$scope.creationMarcheForm.$setPristine();
					// init de l'objet courant
					$scope.marcheCourant = {};

					// interface en mode : list
					$scope.displayMode = "list";
				}

				$scope.listePartieInteresseeCache = function () {
					$http
						.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function (dataPartieInteressee) {
								$scope.listePartieInteresseeCache = dataPartieInteressee;
								$log.debug("listePartieInteresseeCache : " + dataPartieInteressee.length)

							});
				}

				$scope.listePartieInteresseeCache();


				// AffectationBLVente BonLivVente
				$scope.affectationMarche = function (marche) {

					$scope.marcheCourant = {};
					$scope.marcheCourant = marche ? angular
						.copy(marche) : {};

					$scope.sauvegarderBonVente(marche);

				}

				// Sauvegarder Marche
				$scope.sauvegarderBonVente = function (marche) {

					

					$scope.formIsInvalid = "false";


					if ((marche.designation == null) || (marche.designation != null && marche.designation == '')) {

						$scope.formIsInvalid = "true";
						return;

					}

					if ((marche.partieInteresseId == null) || (marche.partieInteresseId != null && marche.partieInteresseId == '')) {

						$scope.formIsInvalid = "true";
						return;

					}
					 
						$scope.traitementEnCours = "true";


					if (angular.isDefined(marche.id)) {
						$log.debug("Sauvegarder Modification : " + marche.designation);
						$scope.updateMarche(marche);
					} else {
						$log.debug("Sauvegarder Ajout : " + marche.id);
						$scope.creerMarche(marche);
					}
				}

				// Création Marche
				$scope.creerMarche = function (marche) {
					console.log("----Marche id  : " + marche.partieInteresseId);



					$http
						.post(UrlAtelier + "/marche/create",
							marche)
						.success(
							function (marcheId) {
								$scope.traitementEnCours = "false";
								
					        	$scope.showNotif();
								$log.debug("Success Creation" + marcheId);
								$scope.myData.push($scope.marcheCourant);
								$scope.annulerAjout();
							});

				}

				// Suppression Marche
				$scope.supprimerMarche = function () {
					$log.debug("deleting ..");
					// TODO: Service de suppression: à revoir.
					// erreur: operation executée mais avec msg 403!
					// var index = this.row.rowIndex;
					$http(
						{
							method: "DELETE",
							url: UrlAtelier
								+ "/marche/delete:"
								+ $scope.myData[$scope.index].id
						}).success(function () {
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
						function () {
							$scope.colDefs = [
								{
									field: 'partieIntAbreviation',
									displayName: 'Client',
								},
								{
									field: 'designation',
									displayName: 'Designation',
								},
								{
									field: '',
									cellTemplate:
										'<div class="ms-CommandButton float-right"  ng-show="!rowform.$visible"  >'

										+ '<button class="ms-CommandButton-button"  ng-click="showPopupDelete(6)">'
										+ '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>'
										+ '</button>'
										+ '	</div> ',


									// '<div class="buttons" ng-show="!rowform.$visible">'
									// 		+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(6)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
							var marcheCourant = $scope.marcheCourant;
							if (searchText) {
								var ft = searchText
									.toLowerCase();
								$http
									.get(
										UrlAtelier
										+ "/marche/getAll")
									.success(
										function (
											largeLoad) {
											data = largeLoad
												.filter(function (
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
										+ "/marche/getAll")
									.success(
										function (
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

			}]);