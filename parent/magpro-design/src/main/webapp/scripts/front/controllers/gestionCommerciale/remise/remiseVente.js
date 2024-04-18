'use strict'

angular
	.module('gpro.remiseVente', ["ngResource"])
	.controller(
		'remiseVenteController',
		[
			'$scope',
			'$filter',
			'$http',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			function ($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier) {
				// Déclaration des variables globales utilisés
				$log.info("=============remiseVenteController===============");
				var data;
				$scope.displayMode = "list";
				$scope.remiseVenteCourante = {};

				$scope.hiddenNotifSucc = "false";


				/**  get liste produit  ***/
				$scope.ListProduit = [];

				// Liste des Produit
				$scope.listeProduit = function () {
					$http
						.get(
							UrlCommun + "/produit/all")
						.success(
							function (dataProduit) {
								$scope.ListProduit = dataProduit;
							});
				}
				$scope.listeProduit();
				/**  Fin getliste produit  ***/

				//Variale de Pagination de la grid
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13],
					pageSize: 13,
					currentPage: 1
				};




				//Init data list
				$scope.initMyData = [];

				$scope.hiddenNotif = "true";

				$scope.traitementEnCours = "false";

				/**************************************
				 * Notification *
				 **************************************/
				$scope.showNotif = function () {
					$scope.hiddenNotif = "false";
				}

				$scope.closeNotif = function () {
					$scope.hiddenNotif = "true";
				}


				// Rechercher produit
				$scope.rechercheProduit = function (produitCourante) {
					$log.debug("recherche en cours ..");
					$http
						.post(
							UrlCommun
							+ "/remise/rechercheMulticritere",
							produitCourante)
						.success(
							function (resultat) {

								$scope.myData = resultat;
								$scope.initMyData = $scope.myData;

								//data, page,pageSize
								$scope.setPagingData($scope.myData, $scope.pagingOptions.currentPage,
									$scope.pagingOptions.pageSize
								);

								$scope.rechercheProduitForm
									.$setPristine();
								$scope.displayMode = "rechercher";
							});
				}

				//Initialiser la liste des produits (ng-grid)
				$scope.rechercheProduit({});

				// Annulation de l'ajout
				$scope.annulerAjout = function () {

					//$scope.closeNotif();
					$scope.cnt = 0;
					$scope.remiseVenteCourante = {
						"dateDebut": "",
						"dateFin": "",
						"remise": "",
						"produitId": "",
						"boutiqueId": "",
					};

					$scope.rechercheProduitForm.$setPristine();
					$scope.listeDocumentProduit = [];
					//	$scope.rechercheProduit({});
					$scope.displayMode = "list";
					$scope.hiddenNotifSucc = "false";
				}
				// ** Ajout Produit
				$scope.AffectationProduit = function (Produit) {

					$scope.hiddenNotifSucc = "false";

					$scope.remiseVenteCourante = Produit ? angular
						.copy(Produit) : {};


					$scope.displayMode = "edit";
					$scope.remiseVenteCourante.quantite = 0;
					$scope.remiseVenteCourante.dateIntroduction = new Date();
					$scope.qte = false;
				}


				// Ajout et Modification Produit
				$scope.modifierOuCreerProduit = function () {
					var index = this.row.rowIndex;
					// getProduit
					$http
						.get(
							UrlCommun
							+ "/remise/getId:"
							+ $scope.myData[index].id)
						.success(
							function (datagetProduit) {

								$scope.creationProduitForm.$setPristine();


							});



					var dateDebut = null;
					var dateFin = null;
					if ($scope.myData[index].dateDebut !== null) {
						dateDebut = $scope.modifierFormatDate($scope.myData[index].dateDebut);
					} else {
						dateDebut = null;
					}


					if ($scope.myData[index].dateFin !== null) {
						dateFin = $scope.modifierFormatDate($scope.myData[index].dateFin);
					} else {
						dateFin = null;
					}


					$scope.remiseVenteCourante = Object.assign($scope.myData[index], { dateDebut: dateDebut }, { dateFin: dateFin })
						//  $scope.partieInteresseeCourante = $scope.myData[index]
						? angular.copy($scope.myData[index])
						: {}; 


					// $scope.remiseVenteCourante = $scope.myData[index] ? angular
					// 		.copy($scope.myData[index])
					// 		: {};
					$scope.displayMode = "edit";
					$scope.qte = true;
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

				// Sauvegarder Produit
				$scope.sauvegarderAjout = function (Produit) {


					$scope.traitementEnCours = "true";
					$log.debug("Sauvegarder Modification"
						+ Produit);
					if (angular.isDefined(Produit.id)) {
						$scope.updateProduit(Produit);
					} else {
						$log.debug("Sauvegarder Ajout"
							+ Produit.SiteId);
						$scope.creerProduit(Produit);
					}

				}

				// Mise à jour des Produits
				$scope.updateProduit = function (produit) {
					produit.documentProduits = $scope.listeDocumentProduit;

					$http
						.post(
							UrlCommun
							+ "/remise/modifierRemise",
							produit)
						.success(
							function (ProduitModifiee) {
								$scope.traitementEnCours = "false";

								$scope.showNotif();
								//$log.debug("Success Modification "+ newProduit.designation);

								//TODO :getById
								$scope.annulerAjout();
								$scope.rechercheProduit({});
							});
				}

				// Création Produit
				$scope.creerProduit = function (produit) {

					$http.post(UrlCommun + "/remise/creerRemise",
						produit).success(
							function (newProduit) {
								$scope.traitementEnCours = "false";

								$scope.showNotif();
								//	$log.debug("Success Creation"+ newProduit.designation);

								//TODO :getById
								$scope.annulerAjout();

								$scope.rechercheProduit({});
							});


				};


				// Suppression Remise vente
				$scope.supprimerRemiseVente = function () {
					$log.debug("DELETE .."
						+ $scope.myData[$scope.index].id);
					$http(
						{
							method: "DELETE",
							url: UrlCommun
								+ "/remise/supprimerRemise:"
								+ $scope.myData[$scope.index].id
						}).success(function () {
							$log.debug("Success Delete");
							$scope.myData.splice($scope.index, 1);
							$scope.rechercheProduit({});

						});
					$scope.closePopupDelete();
					//$scope.rechercherPartieInteressee({});
				}


				/** Fin de gestion des DocumentProduit */

				/***************************************************
				 * Gestion des Grids de recherche
				 **************************************************/
				$scope.typeAlert = 3;
				$scope.colDefs = [];
				$scope.$watch(
					'myData',
					function () {
						$scope.colDefs = [
							{
								field: 'referenceProduit',
								displayName: 'Réf.Article',
								width: '20%'
							},
							{
								field: 'designationProduit',
								displayName: 'Désignation',
								width: '35%'
							},

							{
								field: 'remise',
								displayName: 'Remise'

							},

							{
								field: 'dateDebut',
								displayName: 'Date Debut',
								cellFilter: 'date:\'dd-MM-yyyy\'',
								width: '15%'
							},

							{
								field: 'dateFin',
								displayName: 'Date Fin',
								cellFilter: 'date:\'dd-MM-yyyy\'',
								width: '15%'
							},
							{
								field: '',
								cellTemplate: `<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
											<button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerProduit()">
											<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
											</button>
											<button class="ms-CommandButton-button"  ng-click="showPopupDelete(20)" permission="['Vente_Delete']">
											 <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
											 </button>
											</div> `,







								//  '<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button> <button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(20)"> <i class="fa fa-fw fa-trash-o"></i></button></div>'

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
							if (searchText) {
								var ft = searchText
									.toLowerCase();

								var data = $scope.initMyData.filter(function (item) {
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

				/** Fin de gestion des Grids de la partie Interesse */




				/** Fin de gestion des Grids de la produit */
				$scope.editFicheBesoin = function () {
					var index = this.row.rowIndex;
					window.location.href = "#/ficheBesoin?idProduit=" + $scope.myData[index].id;
				};
			}])
	.filter('filterProduit', function () {
		return function (liste, id) {
			var listeFiltre = [];
			// $log.debug("IdSousFamille=  "+id.id);
			// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
			angular.forEach(listeSousFamille, function (sousFamille, key) {

				if (liste.id == id.id) {
					//$log.debug(liste.id +" == "+ id.id);
					listeFiltre.push(liste);
				}

			});
			// $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
			return listeFiltre;
		};
	})
	.controller(
		'DateIntroCtrl',
		[
			'$scope',
			function ($scope) {
				$scope.maxToDay = new Date();
				// date piker defit
				//							$scope.today = function() {
				//								$scope.articleCourante.dateIntroduction = new Date();
				//							};
				//							$scope.today();
				$scope.clear = function () {
					$scope.articleCourante.dateIntroduction = null;
				};
				// Disable weekend selection
				$scope.disabled = function (date, mode) {
					return (mode === 'day' && (date.getDay() === 0 || date
						.getDay() === 6));
				};
				$scope.toggleMin = function () {
					$scope.minDate = $scope.minDate ? null
						: new Date();
				};
				$scope.toggleMin();
				$scope.open = function ($event) {
					$event.preventDefault();
					$event.stopPropagation();
					$scope.opened = true;
				};
				$scope.dateOptions = {
					formatYear: 'yy',
					startingDay: 1
				};
				$scope.initDate = new Date('20/08/2015');
				$scope.formats = ['dd-MMMM-yyyy', 'dd/MM/yyyy', 'dd.MM.yyyy', 'shortDate'];
				$scope.format = $scope.formats[0];

			}])
