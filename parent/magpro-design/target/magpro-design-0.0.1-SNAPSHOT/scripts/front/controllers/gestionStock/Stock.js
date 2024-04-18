'use strict'
/**
 * Gestion Stock
 */
angular
	.module('gpro.stock', [])
	.controller(
		'StockControllerEntree',
		[
			'$scope',
			'$http',
			'$filter',
			'$log',
			'downloadService',
			'UrlAtelier',
			'UrlCommun',
			'$rootScope',
			function ($scope, $http, $filter, $log, downloadService, $route,
				UrlAtelier, UrlCommun, $rootScope) {

				var data;
				$scope.fcon = true;
				$scope.myData = [];
				$scope.stockBD = [];
				$rootScope.listArticlesFACE = [];
				$scope.displayMode = "list";
				$scope.bonMouvementStockCourante = {
					typeBonMouvement: "ENTREE"
				};
				$scope.listeBonMouvements = [];
				$scope.listeEntiteStock = {};
				$scope.listemouvementStock = [];
				// declaration variable liste cache
				$scope.listeMagasinCache = [];
				$scope.ListEmplacementCache = [];
				$scope.listeClientCache = [];
				$scope.listeFournisseurCache = [];
				$scope.ListeRaisonCache = [];
				$scope.listeSousTraitantCache = [];
				$scope.listeFamilleCache = [];
				$scope.listeSousFamilleCache = [];
				$scope.listeMatiereCache = [];
				$scope.listeMetrageCache = [];
				$scope.listeGrosseurCache = [];
				$scope.ListeMises = [];
				// declaration variable liste des Mouvements
				$rootScope.listArticlesFournituresE = [];
				$rootScope.listArticlesTissusE = [];
				$rootScope.listArticlesFACE = [];
				$scope.idSelectionnee = [];
				$rootScope.error = false;


				/***************************************************
				 * Slides Articles Entree *
				 **************************************************/
				$scope.animateArticleFourniture = function () {
					$("#articles").click(
						function () {
							$scope.openOrClose(
								'panel-articles',
								'#articles', 'hidePlus');
						});
					$("#tissuBtn").click(
						function () {
							$scope.openOrClose('tissu',
								'#tissuBtn',
								'hidePlusTissu');
						});
					$("#filBtn").click(
						function () {
							$scope.openOrClose('fil',
								'#filBtn', 'hidePlusFil');
						});
					$("#fournituresBtn").click(
						function () {
							$scope.openOrCloseInv(
								'fournitures',
								'#fournituresBtn',
								'hidePlusFourniture');
						});
				}
				$scope.openOrClose = function (id_panel, id_div,
					classe) {
					$("div[id=" + id_panel + "]").slideToggle(
						"slow");
					$(id_div).toggleClass(classe);
					if ($(id_div).hasClass(classe)) {
						$(id_div).text('+');
					} else {
						$(id_div).text('-');
					}
				}
				$scope.openOrCloseInv = function (id_panel, id_div,
					classe) {
					$("div[id=" + id_panel + "]").slideToggle(
						"slow");
					$(id_div).toggleClass(classe);
					if ($(id_div).hasClass(classe)) {
						$(id_div).text('-');
					} else {
						$(id_div).text('+');
					}
				}
				$scope.animateArticleFourniture();
				/***************************************************
				 * Gestion de Cache des Referentiels *
				 **************************************************/
				// Liste de Matieres cache
				$scope.listeMatiereCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeMatiereArticleCache")
						.success(function (data) {
							$scope.listeMatiereCache = data;

						});
				}
				// Liste de Metrage cache
				$scope.listeMetrageCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeMetrageArticleCache")
						.success(function (data) {
							$scope.listeMetrageCache = data;

						});
				}
				// Liste de Grosseur cache
				$scope.listeGrosseurCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeGrosseurArticleCache")
						.success(function (data) {
							$scope.listeGrosseurCache = data;

						});
				}
				// Liste de Famille cache
				$scope.listeFamilleCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeFamilleArticleCache")
						.success(function (data) {
							$scope.listeFamilleCache = data;

						});
				}
				// Liste de Sous Famille cache
				$scope.listeSousFamilleCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeSousFamilleArticleCache")
						.success(
							function (data) {
								$scope.listeSousFamilleCache = data;

							});
				}
				// Liste Fournisseur
				$scope.listeFournisseurCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeFournisseurCache")
						.success(
							function (dataC) {
								$scope.listeFournisseurCache = dataC;

							});
				}
				// Liste de Client cache
				$scope.listeClientCache = function () {
					$http.get(UrlAtelier

						+ "/gestionnaireCache/listeClientCache")
						.success(function (dataC) {
							$scope.listeClientCache = dataC;

						});
				}
				// Liste de Sous traitant Cache
				$scope.listeSousTraitantCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeSousTraitantCache")
						.success(
							function (dataS) {
								$scope.listeSousTraitantCache = dataS;

							});
				}
				// able or disable list partie interesse
				$('#checkboxPI').change(
					function () {
						if (this.checked) {
							$("#selectPIStockSortie").attr(
								'disabled', false);
						} else {
							$("#selectPIStockSortie").attr(
								'disabled', 'disabled');
						}
					});
				// Liste de magasinCache
				$scope.listeMagasinCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeMagasinCache")
						.success(
							function (dataMagasin) {
								$scope.listeMagasinCache = dataMagasin;

							});
				}
				// Liste de emplacementCache
				$scope.ListeEmplacementCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listEmplacementCache")
						.success(
							function (dataFamilleCache) {
								$scope.ListEmplacementCache = dataEmplacementCache;

							});
				}
				// Liste de raisonCache
				$scope.ListeRaisonCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeRaisonCache")
						.success(
							function (dataSousFamilleCache) {
								$scope.ListeRaisonCache = dataSousFamilleCache;

							});
				}
				$scope.listeMatiereCache();
				$scope.listeMetrageCache();
				$scope.listeGrosseurCache();
				$scope.listeFamilleCache();
				$scope.listeSousFamilleCache();
				$scope.listeFournisseurCache();
				$scope.listeClientCache();
				$scope.listeSousTraitantCache();
				$scope.listeMagasinCache();
				$scope.ListeRaisonCache();



				/***************************************************
				 * Gestion des Mouvements
				 **************************************************/
				// GetFamilleDesignation
				$scope.famille = {
					status: ''
				};

				// show Famille Mouvements
				$scope.showStatusFamille = function (id) {
					$scope.famille.status = id;

					var selected = $filter('filter')(
						$scope.listeFamilleCache, {
						id: $scope.famille.status
					});

					if ($scope.famille.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				}
				// show Sous Famille Mouvements
				$scope.showStatusSousFamille = function (id) {
					$scope.famille.status = id;

					var selected = $filter('filter')(
						$scope.listeSousFamilleCache, {
						id: $scope.famille.status
					});

					if ($scope.famille.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				}


				$scope.listArticlesFournitureSupprimes = {
					typeArticle: "",
					listeElementsSupprimes: []
				}

				// Supprimer Mouvements de type fourniture

				$scope.removeMvtFourniture = function (index) {

					$log.debug("element supprimes" + JSON.stringify(
						$rootScope.listArticlesFournituresE[index],
						null, " "));


					$scope.listArticlesFournitureSupprimes.typeArticle = "1";

					$scope.listArticlesFournitureSupprimes.listeElementsSupprimes
						.push({
							"entiteStockId": $rootScope.listArticlesFournituresE[index].entiteStock,
							"qteReelle": $rootScope.listArticlesFournituresE[index].quantiteReelle
						});

					$log
						.debug("listArticlesFournitureSupprimes"
							+ JSON
								.stringify(
									$scope.listArticlesFournitureSupprimes,
									null, " "));

					$rootScope.listArticlesFournituresE.splice(
						index, 1);
					$scope.idSelectionnee.splice(index, 1);

				}

				$scope.listArticlesTissuSupprimes = {
					typeArticle: "",
					listeElementsSupprimes: []
				}

				$scope.removeMvtTissu = function (index) {

					$log.debug("element supprimes" + JSON.stringify(
						$rootScope.listArticlesTissusE[index],
						null, " "));

					$scope.listArticlesTissuSupprimes.typeArticle = "2";

					$scope.listArticlesTissuSupprimes.listeElementsSupprimes
						.push({
							"entiteStockId": $rootScope.listArticlesTissusE[index].entiteStock,
							"qteReelle": $rootScope.listArticlesTissusE[index].quantiteReelle,
							"nbRouleauxReel": $rootScope.listArticlesTissusE[index].nbRouleauxReel
						});

					$log
						.debug("listArticlesTissuSupprimes"
							+ JSON
								.stringify(
									$scope.listArticlesTissuSupprimes,
									null, " "));


					$rootScope.listArticlesTissusE.splice(index, 1);
				};

				$scope.listArticlesFACESupprimes = {
					typeArticle: "",
					listeElementsSupprimes: []
				}

				$scope.removeMvtFAC = function (index) {

					$scope.listArticlesFACESupprimes.typeArticle = "3";

					$scope.listArticlesFACESupprimes.listeElementsSupprimes
						.push({
							"entiteStockId": $rootScope.listArticlesFACE[index].entiteStock,
							"conesReel": $rootScope.listArticlesFACE[index].conesReel,
							"finconesReel": $rootScope.listArticlesFACE[index].finconesReel,
							"poidsReel": $rootScope.listArticlesFACE[index].poids
						});


					$rootScope.listArticlesFACE.splice(index, 1);
				};

				// Annuler Ajout
				$scope.cancelAdd = function (rowform, index, id,
					designation, liste) {
					if (angular.isDefined(id)) {
						if (id == "") {
							if (designation == "") {
								liste.splice(index, 1);
								rowform.$cancel();
							} else {
								rowform.$cancel();
							}
						}
						rowform.$cancel();
					} else {
						if (designation == "") {
							liste.splice(index, 1);
							rowform.$cancel();
						} else {
							console
							rowform.$cancel();
						}
					}
				}

				$scope.pagingOptions = {
					pageSizes: [5, 10, 13],
					pageSize: 13,
					currentPage: 1
				};

				// Rechercher par Type Entree
				$scope.rechercherStockEntrerParType = function (type) {
					$http
						.get(
							UrlAtelier
							+ "/bonMouvementStock/getBonMouvementParType:"
							+ type)
						.success(function (resultat) {
							$scope.myData = resultat;
							$scope.displayMode = "rechercher";
						});
					//$scope.rechercheSEForm.$setPristine();
				}
				$scope.rechercherStockEntrerParType("ENTREE");
				// Rechercher Stock Entree

				$scope.rechercherStockEntrer = function (
					mouvementCourante) {

					$log.debug("--getBonMouvementParType  mouvementCourante--" + JSON.stringify(mouvementCourante, null, " "));
					$http
						.post(
							UrlAtelier
							+ "/bonMouvementStock/rechercheParCritere",
							mouvementCourante)
						.success(
							function (resultat) {
								$scope.myData = resultat.bonMouvementStock;

								//data, page,pageSize
								$scope
									.setPagingData(
										$scope.myData,
										$scope.pagingOptions.currentPage,
										$scope.pagingOptions.pageSize);
								$log
									.debug("recherche "
										+ JSON
											.stringify(
												resultat.bonMouvementStock,
												null,
												"  "));
								$scope.displayMode = "rechercher";
							});

					$log.debug('test');
				}

				$log.debug("idSelectionnee : "
					+ $scope.idSelectionnee);
				// ** Ajout Article
				$scope.AffectationArticle = function (article) {
					$scope.articleCourante = article ? angular
						.copy(article) : {};
					$scope.articleCourante = {};
					$scope.creationArticleForm.$setPristine();
					$scope.displayMode = "edit";
				}
				// ** Ajout mouvementStock
				$scope.AffectationMouvement = function (
					mouvementStock) {
					$scope.bonMouvementStockCourante = mouvementStock ? angular
						.copy(mouvementStock)
						: {};
					$scope.mouvementCourante = {};
					$scope.creationSEForm.$setPristine();

					$scope.displayMode = "edit";
				}
				// Ajout et Modification mouvementStock
				$scope.modifierOuCreerBonMouvementStock = function () {
					var index = this.row.rowIndex;
					// getmouvementStock
					$http
						.get(
							UrlAtelier
							+ "/bonMouvementStock/getId:"
							+ $scope.myData[index].id)
						.success(
							function (data) {

								console
									.log("getById : data"
										+ JSON
											.stringify(
												data,
												null,
												" "));

								classifierListMouvement(data.mouvements);
							});
					$scope.bonMouvementStockCourante = $scope.myData[index] ? angular
						.copy($scope.myData[index])
						: {};
					$scope.displayMode = "edit";
					$scope.openOrClose('panel-articles',
						'#articles', 'hidePlus');
					$scope.openOrClose('tissu', '#tissuBtn',
						'hidePlusTissu');
					$scope.openOrClose('fil', '#filBtn',
						'hidePlusFil');

				}

				//Classification des ListMouvement selon le Type
				var classifierListMouvement = function (
					listElementMouvement) {
					// subListes de ListMouvement: 1:"fourniture", 2:"tissu", 3: "fil à coudre" 
					angular
						.forEach(
							listElementMouvement,
							function (elementMouvement, key) {

								var typeArticle = elementMouvement.typeArticle;
								$log
									.debug("--------- typeArticle "
										+ typeArticle);
								if (typeArticle == 1) {
									$rootScope.listArticlesFournituresE
										.push(elementMouvement);
								} else if (typeArticle == 2) {
									$rootScope.listArticlesTissusE
										.push(elementMouvement);
								} else if (typeArticle == 3) {
									$rootScope.listArticlesFACE
										.push(elementMouvement);
								}

							});

					console
						.log("listArticlesFournituresE : "
							+ JSON
								.stringify(
									$rootScope.listArticlesFournituresE,
									null, " "));
					console.log("listArticlesTissusE : "
						+ JSON.stringify(
							$rootScope.listArticlesTissusE,
							null, " "));
					console.log("listArticlesFACE : "
						+ $rootScope.listArticlesFACE.length);

				}

				// Sauvegarder Entrer Stock
				$scope.sauvegarderAjout = function (mouvementStock) {
					$log.debug("sauvegarder Ajout");

					if (angular.isDefined(mouvementStock.id)) {
						$log.debug("Sauvegarder Modification"
							+ mouvementStock);
						$scope.updateBonMouvement(mouvementStock);
					} else {
						console
							.log("Sauvegarder Ajout B.Mouvement ");
						$scope
							.creerBonMouvementStock(mouvementStock);
					}
				}

				// Mise à jour des mouvementStocks
				$scope.updateBonMouvement = function (
					bonMouvementStock) {
					var tmpMouvementStock = [];
					for (var i = 0; i < $rootScope.listArticlesFournituresE.length; i++) {
						$rootScope.listArticlesFournituresE[i].type = "ENTREE";
						tmpMouvementStock
							.push($rootScope.listArticlesFournituresE[i]);
					}
					for (var i = 0; i < $rootScope.listArticlesTissusE.length; i++) {
						$rootScope.listArticlesTissusE[i].type = "ENTREE";
						tmpMouvementStock
							.push($rootScope.listArticlesTissusE[i]);
					}
					for (var i = 0; i < $rootScope.listArticlesFACE.length; i++) {
						$rootScope.listArticlesFACE[i].type = "ENTREE";
						tmpMouvementStock
							.push($rootScope.listArticlesFACE[i]);
					}
					$log.debug("tmpMouvementStock : "
						+ tmpMouvementStock);
					bonMouvementStock.mouvements = tmpMouvementStock;
					bonMouvementStock.type = "ENTREE";


					/******* Attacher les elements supprimés *******/

					bonMouvementStock.listeMouvementsSupprimes = [];

					if ($scope.listArticlesFournitureSupprimes.listeElementsSupprimes.length > 0) {
						bonMouvementStock.listeMouvementsSupprimes
							.push($scope.listArticlesFournitureSupprimes);
					}

					if ($scope.listArticlesTissuSupprimes.listeElementsSupprimes.length > 0) {
						bonMouvementStock.listeMouvementsSupprimes
							.push($scope.listArticlesTissuSupprimes);
					}

					if ($scope.listArticlesFACESupprimes.listeElementsSupprimes.length > 0) {
						bonMouvementStock.listeMouvementsSupprimes
							.push($scope.listArticlesFACESupprimes);
					}

					$scope.listArticlesFournitureSupprimes = {
						typeArticle: "",
						listeElementsSupprimes: []
					}
					$scope.listArticlesTissuSupprimes = {
						typeArticle: "",
						listeElementsSupprimes: []
					}
					$scope.listArticlesFACESupprimes = {
						typeArticle: "",
						listeElementsSupprimes: []
					}

					console.log("Objet à Modifier : "
						+ JSON.stringify(bonMouvementStock,
							null, "  "));

					$http
						.post(
							UrlAtelier
							+ "/bonMouvementStock/modifierBonMouvementStock",
							bonMouvementStock)
						.success(
							function (mouvementStockModifiee) {

								for (var i = 0; i < $scope.myData.length; i++) {
									if ($scope.myData[i].id == mouvementStockModifiee.id) {
										$scope.myData[i] = mouvementStockModifiee;

										break;
									}
								}
								$scope.displayMode = "list";
								$scope.annulerAjout();
							});
				}

				$scope.creerBonMouvementStock = function (
					mouvementStock) {
					var tmpMouvementStock = [];

					for (var i = 0; i < $rootScope.listArticlesFournituresE.length; i++) {
						$rootScope.listArticlesFournituresE[i].type = "ENTREE";
						tmpMouvementStock
							.push($rootScope.listArticlesFournituresE[i]);
					}
					for (var i = 0; i < $rootScope.listArticlesTissusE.length; i++) {
						$rootScope.listArticlesTissusE[i].type = "ENTREE";
						tmpMouvementStock
							.push($rootScope.listArticlesTissusE[i]);
					}
					for (var i = 0; i < $rootScope.listArticlesFACE.length; i++) {
						$rootScope.listArticlesFACE[i].type = "ENTREE";
						tmpMouvementStock
							.push($rootScope.listArticlesFACE[i]);
					}

					mouvementStock.mouvements = tmpMouvementStock;
					mouvementStock.type = "ENTREE";
					console.log("Objet à Creer : "
						+ JSON.stringify(mouvementStock, null,
							"  "));

					$http
						.post(
							UrlAtelier
							+ "/bonMouvementStock/creerBonMouvementStock",
							mouvementStock)
						.success(function (id) {
							//Todo : getById
							$scope.annulerAjout();

							$scope.displayMode = "list";

						});

				}

				$scope.myClick = function () {
					$log.debug($scope.fcon);
				}

				// Annulation de l'ajout
				$scope.annulerAjout = function () {
					$log.debug("Annulation en cours ..")
					$scope.myClick();
					$scope.bonMouvementStockCourante = {};
					$scope.rechercheSEForm.$setPristine();
					$scope.listemouvementStock = [];
					$rootScope.listArticlesFournituresE = [];
					$rootScope.listArticlesTissusE = [];
					$rootScope.listArticlesFACE = [];
					$scope.displayMode = "list";
					$scope.openOrClose('panel-articles',
						'#articles', 'hidePlus');
					$scope.openOrClose('tissu', '#tissuBtn',
						'hidePlusTissu');
					$scope.openOrClose('fil', '#filBtn',
						'hidePlusFil');
					//$log.debug(listArticlesFACE[$index].fcon);
					$scope.rechercherStockEntrer({
						typeBonMouvement: "ENTREE"
					});
				}
				// Suppression bon mouvement
				$scope.supprimerMouvementStock = function () {
					//var index = this.row.rowIndex;
					//$scope.index = this.row.rowIndex;
					$log.debug("DELETE .."
						+ $scope.myData[$scope.index].id);
					$http(
						{
							method: "DELETE",
							url: UrlAtelier
								+ "/bonMouvementStock/supprimerBonMouvementStock:"
								+ $scope.myData[$scope.index].id
						}).success(function () {
							//Pas d'entree au niveau success delete 403 erreur
							$log.debug("success delete");
							$scope.myData.splice($scope.index, 1);

							$scope.closePopupDelete();
							$scope.annulerAjout();

						})
						.error(function () {
							$scope.myData.splice($scope.index, 1);
						});
					$scope.closePopupDelete();
					$scope.annulerAjout();

				};

				/*** PDF ***/
				//generer rapport apres creation d'un bon de d'Entree. mode : Modification/Consultation
				$scope.downloadBonEntree = function (id) {
					//$log.debug("-- id"+id);
					var url = UrlAtelier
						+ "/reportgs/bonMouvementStockEntreeSortieById?id="
						+ id + "&type=pdf";
					downloadService.download(url).then(
						function (success) {
							$log.debug('success : ' + success);
							//$scope.annulerAjout();
						}, function (error) {
							$log.debug('error : ' + error);
						});
				};

				// $scope.listeMouvement();
				$scope.typeAlert = 3;

				$scope
					.$watch(
						'myData',
						function () {
							$scope.colDefs = [
								{
									field: 'numero',
									displayName: 'N° BE'
								},
								{
									field: 'date | date : "dd/MM/yyyy"',
									displayName: 'Date d\'entree'
								},
								{
									field: 'raisonMouvementDesignation',
									displayName: ' Raison d\'entree'
								},

								{
									field: 'valeur',
									displayName: 'Valeur BE'
								},
								{
									field: 'etat',
									displayName: 'Etat'
								},
								{
									field: '',
									cellTemplate: '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerBonMouvementStock()">	<i class="fa fa-fw fa-pencil"></i></button>	<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(14)"><i class="fa fa-fw fa-trash-o"></i></button></div>'
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
							var BonMvtCourant = {
								typeBonMouvement: "ENTREE"
							};
							if (searchText) {
								var ft = searchText
									.toLowerCase();
								$http
									.post(
										UrlAtelier
										+ "/bonMouvementStock/rechercheParCritere",
										BonMvtCourant)
									.success(
										function (
											largeLoad) {
											data = largeLoad.bonMouvementStock
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
									.post(
										UrlAtelier
										+ "/bonMouvementStock/rechercheParCritere",
										BonMvtCourant)
									.success(
										function (
											largeLoad) {
											$scope
												.setPagingData(
													largeLoad.bonMouvementStock,
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
					enableHighlighting: true,
					totalServerItems: 'totalServerItems',
					pagingOptions: $scope.pagingOptions,
					selectedItems: $scope.selectedRows,
					filterOptions: $scope.filterOptions,
				};

			}])
	/**
	 * ***************SORTIE*************************	 */

	.controller(
		'StockControllerSortie',
		[
			'$scope',
			'$http',
			'$filter',
			'$log',
			'downloadService',
			'UrlAtelier',
			'UrlCommun',
			'$rootScope',
			function ($scope, $http, $filter, $log, downloadService,
				UrlAtelier, UrlCommun, $rootScope) {
				var data;

				$scope.myData = [];
				$scope.stockBD = [];
				$scope.displayMode = "list";
				$scope.mouvementStockCourante = {
					typeBonMouvement: "SORTIE"
				};
				$scope.bonMouvementStockCourante = {};
				$scope.MvtInserree = {

					referenceArticle: '',
					designationArticle: '',
					quantite: '',
					prixTotal: '',
					emplacement: ''
				};
				$scope.listeBonMouvements = {};
				$scope.listeEntiteStock = {};
				$scope.listemouvementStock = [];
				// declaration variable liste cache
				$scope.listeMagasinCache = [];
				$scope.ListEmplacementCache = [];
				$scope.listeClientCache = [];
				$scope.ListeRaisonCache = [];
				$scope.listeSousTraitantCache = [];
				$scope.listeFamilleCache = [];
				$scope.listeSousFamilleCache = [];
				$scope.listeMatiereCache = [];
				$scope.listeMetrageCache = [];
				$scope.listeGrosseurCache = [];
				// declaration variable liste des Mouvements
				$rootScope.listArticlesFournitures = [];
				$rootScope.listArticlesTissus = [];
				$rootScope.listArticlesFACE = [];
				$scope.idSelectionnee = [];
				$rootScope.error = false;



				/***************************************************
				 * Slides Articles Sortie *
				 **************************************************/
				$scope.animateArticleFourniture = function () {
					$("#articlesS").click(
						function () {
							$scope.openOrClose(
								'panel-articlesS',
								'#articlesS', 'hidePlus');
						});
					$("#tissuBtnS").click(
						function () {
							$scope.openOrClose('tissuS',
								'#tissuBtnS',
								'hidePlusTissu');
						});
					$("#filBtnS").click(
						function () {
							$scope.openOrClose('filS',
								'#filBtnS', 'hidePlusFil');
						});
					$("#fournituresBtnS").click(
						function () {
							$scope.openOrCloseInv(
								'fournituresS',
								'#fournituresBtnS',
								'hidePlusFourniture');
						});
				}
				$scope.openOrClose = function (id_panel, id_div,
					classe) {
					$("div[id=" + id_panel + "]")
						.fadeToggle("slow");
					$(id_div).toggleClass(classe);
					if ($(id_div).hasClass(classe)) {
						$(id_div).text('+');
					} else {
						$(id_div).text('-');
					}
				}
				$scope.openOrCloseInv = function (id_panel, id_div,
					classe) {
					$("div[id=" + id_panel + "]")
						.fadeToggle("slow");
					$(id_div).toggleClass(classe);
					if ($(id_div).hasClass(classe)) {
						$(id_div).text('-');
					} else {
						$(id_div).text('+');
					}
				}
				$scope.animateArticleFourniture();
				/***************************************************
				 * Gestion de Cache des Referentiels *
				 **************************************************/
				// Liste de Matieres cache
				$scope.listeMatiereCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeMatiereArticleCache")
						.success(function (data) {
							$scope.listeMatiereCache = data;

						});
				}

				// Liste de Metrage cache
				$scope.listeMetrageCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeMetrageArticleCache")
						.success(function (data) {
							$scope.listeMetrageCache = data;

						});
				}
				// Liste de Grosseur cache
				$scope.listeGrosseurCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeGrosseurArticleCache")
						.success(function (data) {
							$scope.listeGrosseurCache = data;

						});
				}
				// Liste de Famille cache
				$scope.listeFamilleCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeFamilleArticleCache")
						.success(function (data) {
							$scope.listeFamilleCache = data;

						});
				}
				// Liste de Sous Famille cache
				$scope.listeSousFamilleCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeSousFamilleArticleCache")
						.success(
							function (data) {
								$scope.listeSousFamilleCache = data;

							});
				}
				// Liste de Client cache

				// Liste de Client cache
				$scope.listeClientCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeClientCache")
						.success(function (dataC) {
							$scope.listeClientCache = dataC;

						});
				}

				// Liste de Sous traitant Cache
				$scope.listeSousTraitantCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeSousTraitantCache")
						.success(
							function (dataS) {
								$scope.listeSousTraitantCache = dataS;

							});
				}
				// able or disable list partie interesse
				$('#checkboxPI').change(
					function () {
						if (this.checked) {
							$("#selectPIStockSortie").attr(
								'disabled', false);
						} else {
							$("#selectPIStockSortie").attr(
								'disabled', 'disabled');
						}
					});
				// Liste de magasinCache
				$scope.listeMagasinCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeMagasinCache")
						.success(
							function (dataMagasin) {
								$scope.listeMagasinCache = dataMagasin;

							});
				}
				// Liste de emplacementCache
				$scope.ListeEmplacementCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeEmplacementCache")
						.success(
							function (dataFamilleCache) {
								$scope.ListEmplacementCache = dataEmplacementCache;

							});
				}
				// Liste de raisonCache
				$scope.ListeRaisonCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireCache/listeRaisonCache")
						.success(
							function (dataSousFamilleCache) {
								$scope.ListeRaisonCache = dataSousFamilleCache;

							});
				}

				// Liste des mises
				$scope.ListeMises = function () {
					$http
						.get(
							UrlAtelier
							+ "/mise/all")
						.success(
							function (dataMises) {
								$scope.ListeMises = dataMises;

							});
				}
				$scope.listeMatiereCache();
				$scope.listeMetrageCache();
				$scope.listeGrosseurCache();
				$scope.listeFamilleCache();
				$scope.listeSousFamilleCache();
				$scope.listeClientCache();
				$scope.listeSousTraitantCache();
				$scope.listeMagasinCache();
				$scope.ListeRaisonCache();
				$scope.ListeMises();

				/***************************************************
				 * Gestion des Bon mouvements *
				 **************************************************/
				// Rechercher par Type Sorie
				/*$scope.rechercherStockEntrerParType = function(type) {
					$log.debug("recherche en cours");
					$scope.myData = [];
					$http
							.get(
									UrlAtelier
											+ "/bonMouvementStock/getBonMouvementParType:"
											+ type)
							.success(
									function(resultat) {
										$scope.myData = resultat;
										$log
												.debug("listeBonMouvementEntree  :"
														+ $scope.myData.length);
										$scope.displayMode = "rechercher";
									});
					//$scope.rechercheSSForm.$setPristine();
				}
				*/
				//$scope.rechercherStockEntrerParType("SORTIE");

				// Annuler Ajout
				$scope.cancelAdd = function (rowform, index, id,
					designation, liste) {
					$log.debug("* Designation "
						+ liste[0].designation);
					if (angular.isDefined(id)) {
						$log.debug("DEF ID " + id);
						if (id == "") {
							if (designation == "") {
								// $log.debug("IdDELETE"
								// +designation);
								liste.splice(index, 1);
								rowform.$cancel();
							} else {
								$log.debug("IdNOT DELETE "
									+ designation);
								rowform.$cancel();
							}
						}
						rowform.$cancel();
					} else {
						$log.debug("UND ID " + id);
						if (designation == "") {
							$log.debug("DELETE" + designation);
							liste.splice(index, 1);
							rowform.$cancel();
						} else {
							$log.debug("NOT DELETE " + designation);
							rowform.$cancel();
						}
					}
				}

				$scope.rechercherStockSortie = function (
					mouvementCourante) {

					$log.debug("----------mouvementCourante" + JSON.stringify(mouvementCourante, null, " "));

					$http
						.post(
							UrlAtelier
							+ "/bonMouvementStock/rechercheParCritere",
							mouvementCourante)
						.success(
							function (resultat) {
								$log.debug("----------resultat----" + JSON.stringify(resultat, null, " "));

								$scope.myData = resultat.bonMouvementStock;
								$log.debug("----------$scope.myData----" + JSON.stringify($scope.myData, null, " "));

								//data, page,pageSize
								$scope
									.setPagingData(
										$scope.myData,
										$scope.pagingOptions.currentPage,
										$scope.pagingOptions.pageSize);
								$scope.displayMode = "rechercher";

							});

				}

				// ** Ajout mouvementStock
				$scope.AffectationMouvement = function (
					mouvementStock) {
					$scope.mouvementStockCourante = mouvementStock ? angular
						.copy(mouvementStock)
						: {};
					$scope.mouvementCourante = {};
					$scope.rechercheSSForm.$setPristine();
					$scope.displayMode = "edit";

				}
				// Ajout et Modification mouvementStock
				$scope.modifierOuCreerBonMouvementStock = function () {
					// vider les Listes
					$scope.listeArticles = [];
					$rootScope.listArticlesFournitures = [];
					$rootScope.listArticlesTissus = [];
					$rootScope.listArticlesFACE = [];

					var index = this.row.rowIndex;
					// getmouvementStock
					$http
						.get(
							UrlAtelier
							+ "/bonMouvementStock/getId:"
							+ $scope.myData[index].id)
						.success(
							function (data) {
								classifierListMouvement(data.mouvements);
							});
					$scope.bonMouvementStockCourante = $scope.myData[index] ? angular
						.copy($scope.myData[index])
						: {};
					$scope.displayMode = "edit";
					$scope.openOrClose('panel-articlesS',
						'#articlesS', 'hidePlus');
					$scope.openOrClose('tissuS', '#tissuBtnS',
						'hidePlusTissu');
					$scope.openOrClose('filS', '#filBtnS',
						'hidePlusFil');
				}

				//Classification des ListMouvement selon le Type
				var classifierListMouvement = function (
					listElementMouvement) {
					// subListes de ListMouvement: 1:"fourniture", 2:"tissu", 3: "fil à coudre" 
					angular
						.forEach(
							listElementMouvement,
							function (elementMouvement, key) {

								var typeArticle = elementMouvement.typeArticle;
								$log
									.debug("--------- typeArticle "
										+ typeArticle);
								if (typeArticle == 1) {
									$rootScope.listArticlesFournitures
										.push(elementMouvement);
								} else if (typeArticle == 2) {
									$rootScope.listArticlesTissus
										.push(elementMouvement);
								} else if (typeArticle == 3) {
									$rootScope.listArticlesFACE
										.push(elementMouvement);
								}

							});

					console
						.log("listArticlesFournitures : "
							+ JSON
								.stringify(
									$rootScope.listArticlesFournitures,
									null, " "));
					console.log("listArticlesTissus : "
						+ JSON.stringify(
							$rootScope.listArticlesTissus,
							null, " "));
					console.log("listArticlesFACE : "
						+ $rootScope.listArticlesFACE.length);

				}

				// Sauvegarder Entrer Stock
				$scope.sauvegarderAjout = function (mouvementStock) {

					if (angular.isDefined(mouvementStock.id)) {
						$log.debug("Modification"
							+ mouvementStock.numero);
						$scope.updateBonMouvement(mouvementStock);
					} else {
						$log.debug("Sauvegarder: Creation"
							+ mouvementStock.numero);
						$scope
							.creerBonMouvementStock(mouvementStock);
					}
				}
				// Mise à jour des mouvementStocks
				$scope.updateBonMouvement = function (
					bonMouvementStock) {
					var tmpListeArticles = [];
					for (var i = 0; i < $rootScope.listArticlesFournitures.length; i++) {
						$rootScope.listArticlesFournitures[i].type = "SORTIE";
						tmpListeArticles
							.push($rootScope.listArticlesFournitures[i]);
					}
					for (var i = 0; i < $rootScope.listArticlesTissus.length; i++) {
						$rootScope.listArticlesTissus[i].type = "SORTIE";
						tmpListeArticles
							.push($rootScope.listArticlesTissus[i]);
					}
					for (var i = 0; i < $rootScope.listArticlesFACE.length; i++) {
						$rootScope.listArticlesFACE[i].type = "SORTIE";
						tmpListeArticles
							.push($rootScope.listArticlesFACE[i]);
					}
					$log.debug("----tmpListeArticles = "
						+ JSON.stringify(tmpListeArticles,
							null, "  "));
					bonMouvementStock.mouvements = tmpListeArticles;
					bonMouvementStock.type = "SORTIE";

					/******* Attacher les elements supprimés *******/

					bonMouvementStock.listeMouvementsSupprimes = [];

					bonMouvementStock.listeMouvementsSupprimes
						.push($scope.listArticlesFournitureSupprimes);

					bonMouvementStock.listeMouvementsSupprimes
						.push($scope.listArticlesTissuSupprimes);

					bonMouvementStock.listeMouvementsSupprimes
						.push($scope.listArticlesFACESupprimes);

					$log.debug("Objet Modifié : "
						+ JSON.stringify(bonMouvementStock,
							null, "  "));
					$http
						.post(
							UrlAtelier
							+ "/bonMouvementStock/modifierBonMouvementStock",
							bonMouvementStock)
						.success(
							function (mouvementStockModifiee) {

								$scope.rechercheSSForm
									.$setPristine();

								$scope.creationSSForm
									.$setPristine();
								$scope.displayMode = "list";
								$scope.annulerAjoutS();
							});

				}
				// Création mouvementStock
				$scope.listeArticles = [];

				$scope.creerBonMouvementStock = function (
					bonMouvementStock) {
					var tmpListeArticles = [];
					for (var i = 0; i < $rootScope.listArticlesFournitures.length; i++) {
						$rootScope.listArticlesFournitures[i].type = "SORTIE";
						tmpListeArticles
							.push($rootScope.listArticlesFournitures[i]);
					}
					for (var i = 0; i < $rootScope.listArticlesTissus.length; i++) {
						$rootScope.listArticlesTissus[i].type = "SORTIE";
						tmpListeArticles
							.push($rootScope.listArticlesTissus[i]);
					}
					for (var i = 0; i < $scope.listArticlesFACE.length; i++) {
						$rootScope.listArticlesFACE[i].type = "SORTIE";
						tmpListeArticles
							.push($rootScope.listArticlesFACE[i]);
					}

					bonMouvementStock.mouvements = tmpListeArticles;
					bonMouvementStock.type = "SORTIE";
					$log.debug("Objet Créé : "
						+ JSON.stringify(bonMouvementStock,
							null, "  "));

					$http
						.post(
							UrlAtelier
							+ "/bonMouvementStock/creerBonMouvementStock",
							bonMouvementStock)
						.success(
							function (id) {

								$scope.rechercheSSForm
									.$setPristine();

								$scope.creationSSForm
									.$setPristine();

								$scope.annulerAjoutS();

								$scope.displayMode = "list";

							});

				}

				// Annulation de l'ajout
				$scope.annulerAjoutS = function () {

					$log.debug('annulationn..')
					$scope.mouvementStockCourante = {};
					$scope.bonMouvementStockCourante = {};

					//$scope.creationSSForm.$setPristine();
					$scope.rechercheSSForm.$setPristine();
					$scope.listeArticles = [];
					$rootScope.listArticlesFournitures = [];
					$rootScope.listArticlesTissus = [];
					$rootScope.listArticlesFACE = [];

					$scope.displayMode = "list";
					$scope.openOrClose('panel-articlesS',
						'#articlesS', 'hidePlus');
					$scope.openOrClose('tissuS', '#tissuBtnS',
						'hidePlusTissu');
					$scope.openOrClose('filS', '#filBtnS',
						'hidePlusFil');

					$scope.rechercherStockSortie({
						typeBonMouvement: "SORTIE"
					});
					$scope.displayMode = "List";

				}

				//							// Suppression bon mouvement
				//							$scope.supprimerMouvementStock = function() {
				//								//var index = this.row.rowIndex;
				//								$http(
				//										{
				//											method : "DELETE",
				//											url : UrlAtelier
				//													+ "/bonMouvementStock/supprimerBonMouvementStock:"
				//													+ $scope.[index].id
				//										}).success(function() {
				//
				//											$log.debug("success delete");
				//											$scope.myData.splice(index, 1);
				//										 
				//											
				//
				//								});
				// Suppression bon mouvement9
				$scope.supprimerMouvementStock = function () {
					//var index = this.row.rowIndex;
					//$scope.index = this.row.rowIndex;
					$log.debug("DELETE .."
						+ $scope.myData[$scope.index].id);
					$http(
						{
							method: "DELETE",
							url: UrlAtelier
								+ "/bonMouvementStock/supprimerBonMouvementStock:"
								+ $scope.myData[$scope.index].id
						}).success(function () {
							//Pas d'entree au niveau success delete 403 erreur
							$log.debug("success delete");
							$scope.myData.splice($scope.index, 1);

						})
						.error(function () {
							$scope.myData.splice($scope.index, 1);
						});
					;
					$scope.annulerAjoutS();
					$scope.closePopupDelete();
				};
				// $scope.listeMouvement();
				/***************************************************
				 * Fin de Gestion des Bon mouvements *
				 **************************************************/
				/***************************************************
				 * Gestion des Mouvements
				 **************************************************/



				$scope.listArticlesFournitureSupprimes = {
					typeArticle: "",
					listeElementsSupprimes: []
				}

				// Supprimer Mouvements
				$scope.removeMvtFourniture = function (index) {
					//								for (var i = 0; i < $scope.listeArticles.length; i++) {
					//									console
					//											.log("ID entite Stock in Article List : "
					//													+ $scope.listeArticles[i].entiteStock);
					//									console
					//											.log("$rootScope.listArticlesFournitures[index].entiteStock : "
					//													+ $rootScope.listArticlesFournitures[index].entiteStock);
					//									$log.debug("$scope.listeArticles : "
					//											+ $scope.listeArticles);
					//									if ($scope.listeArticles[i].entiteStock == $rootScope.listArticlesFournitures[index].entiteStock)
					//										$scope.listeArticles.splice(i, 1);
					//								}
					//								

					$scope.listArticlesFournitureSupprimes.typeArticle = "1";

					$scope.listArticlesFournitureSupprimes.listeElementsSupprimes
						.push({
							"entiteStockId": $rootScope.listArticlesFournitures[index].entiteStock,
							"qteReelle": $rootScope.listArticlesFournitures[index].quantiteReelle
						});


					$rootScope.listArticlesFournitures.splice(
						index, 1);
					$log.debug("$scope.listeArticles : "
						+ $scope.listeArticles);
				};


				$scope.listArticlesTissuSupprimes = {
					typeArticle: "",
					listeElementsSupprimes: []
				}


				$scope.removeMvtTissu = function (index) {

					$scope.listArticlesTissuSupprimes.typeArticle = "2";

					$scope.listArticlesTissuSupprimes.listeElementsSupprimes
						.push({
							"entiteStockId": $rootScope.listArticlesTissus[index].entiteStock,
							"qteReelle": $rootScope.listArticlesTissus[index].quantiteReelle,
							"nbRouleauxReel": $rootScope.listArticlesTissus[index].nbRouleauxReel
						});

					$rootScope.listArticlesTissus.splice(index, 1);
				};


				$scope.listArticlesFACESupprimes = {
					typeArticle: "",
					listeElementsSupprimes: []
				}


				$scope.removeMvtFAC = function (index) {


					$scope.listArticlesFACESupprimes.typeArticle = "3";

					$scope.listArticlesFACESupprimes.listeElementsSupprimes
						.push({
							"entiteStockId": $rootScope.listArticlesFACE[index].entiteStock,
							"conesReel": $rootScope.listArticlesFACE[index].conesReel,
							"finconesReel": $rootScope.listArticlesFACE[index].finconesReel,
							"poidsReel": $rootScope.listArticlesFACE[index].poids
						});

					$rootScope.listArticlesFACE.splice(index, 1);
				};
				/** Fin de gestion des Mouvements */

				//generer rapport apres creation d'un bon de d'Sortie. mode : Modification/Consultation
				$scope.downloadBonSortie = function (id) {
					//$log.debug("-- id"+id);
					var url = UrlAtelier
						+ "/reportgs/bonMouvementStockEntreeSortieById?id="
						+ id + "&type=pdf";
					downloadService.download(url).then(
						function (success) {
							$log.debug('success : ' + success);
							//$scope.annulerAjout();
						}, function (error) {
							$log.debug('error : ' + error);
						});
				};

				/*
				 * ******* gestion gridStock*******
				 */
				$scope.typeAlert = 3;
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13],
					pageSize: 13,
					currentPage: 1
				};

				$scope
					.$watch(
						'myData',
						function () {
							$scope.colDefs = [
								{
									field: 'numero',
									displayName: 'N° BS'
								},
								{
									field: 'date | date : "dd/MM/yyyy"',
									displayName: 'Date de sortie'
								},
								{
									field: 'raisonMouvementDesignation',
									displayName: ' Raison de sortie'
								},

								{
									field: 'valeur',
									displayName: 'Valeur BS'
								},
								{
									field: 'etat',
									displayName: 'Etat'
								},
								{
									field: 'origineFSuiveuse',
									displayName: 'OrigineFS',
									type: 'boolean',
									width: 80,
									cellTemplate: '<input type="checkbox" style = "margin:10%"; ng-model="row.entity.origineFSuiveuse" disabled>'
								},
								{
									field: '',
									width: 80,
									cellTemplate: '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerBonMouvementStock()"> <i class="fa fa-fw fa-pencil"></i></button>	<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(15)" ng-disabled="row.entity.origineFSuiveuse" ><i class="fa fa-fw fa-trash-o"></i></button></div>'
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
							var BonMvtCourant = {
								typeBonMouvement: "SORTIE"
							};
							if (searchText) {
								var ft = searchText
									.toLowerCase();
								$http
									.post(
										UrlAtelier
										+ "/bonMouvementStock/rechercheParCritere", BonMvtCourant
									)
									.success(
										function (
											largeLoad) {
											data = largeLoad.bonMouvementStock
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
													data.bonMouvementStock,
													page,
													pageSize);
										});

							} else {

								//$log.debug("BonMvtCourant"+ JSON.stringify(BonMvtCourant,null," "));

								$http
									.post(
										UrlAtelier
										+ "/bonMouvementStock/rechercheParCritere", BonMvtCourant
									)
									.success(
										function (
											largeLoad) {

											$log.debug("------largeLoad----" + JSON.stringify(largeLoad, null, " "));
											$scope
												.setPagingData(
													largeLoad.bonMouvementStock,
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

				$scope.gridOptionsS = {
					data: 'myData',
					columnDefs: 'colDefs',
					enablePaging: true,
					showFooter: true,
					enableHighlighting: true,
					enableColumnResize: true,
					totalServerItems: 'totalServerItems',
					pagingOptions: $scope.pagingOptions,
					selectedItems: $scope.selectedRows,
					filterOptions: $scope.filterOptions,
				};

				/***************************************************
				 * Gestion des Grids Stock
				 * ************************************************
				 * $scope.typeAlert = 3; $scope.filterOptions = {
				 * filterText : "", useExternalFilter : true };
				 * $scope.totalServerItems = 0; $scope.pagingOptions = {
				 * pageSizes : [ 5, 10, 20 ], pageSize : 14,
				 * currentPage : 1 }; $scope.setPagingData =
				 * function(data, page, pageSize) { var pagedData =
				 * data.slice((page - 1) pageSize, page * pageSize);
				 * $scope.myData = pagedData;
				 * $scope.totalServerItems = data.length; if
				 * (!$scope.$$phase) { $scope.$apply(); } };
				 * $scope.getPagedDataAsync = function(pageSize,
				 * page, searchText) { setTimeout(function() {
				 * 
				 * if (searchText) { var ft =
				 * searchText.toLowerCase();
				 * 
				 * data = $scope.myData .filter(function(item) {
				 * return JSON.stringify(item) .toLowerCase()
				 * .indexOf(ft) != -1; });
				 * $scope.setPagingData(data, page, pageSize); }
				 * else {
				 * 
				 * $scope.setPagingData($scope.myData, page,
				 * pageSize); } }, 100); };
				 * $scope.getPagedDataAsync(
				 * $scope.pagingOptions.pageSize,
				 * $scope.pagingOptions.currentPage);
				 * 
				 * $scope .$watch( 'pagingOptions', function(newVal,
				 * oldVal) { if (newVal !== oldVal &&
				 * newVal.currentPage !== oldVal.currentPage) {
				 * $scope .getPagedDataAsync(
				 * $scope.pagingOptions.pageSize,
				 * $scope.pagingOptions.currentPage,
				 * $scope.filterOptions.filterText); } }, true);
				 * $scope.$watch('filterOptions', function(newVal,
				 * oldVal) { if (newVal !== oldVal) {
				 * $scope.getPagedDataAsync(
				 * $scope.pagingOptions.pageSize,
				 * $scope.pagingOptions.currentPage,
				 * $scope.filterOptions.filterText); } }, true);
				 * 
				 * $scope.gridOptionsS = { dataselected :
				 * 'myDataselected', selectedItems : [],
				 * 
				 * data : 'myData', columnDefs : [ { field :
				 * 'numero', displayName : 'N° BS' }, { field :
				 * 'date | date : "dd/MM/yyyy"', displayName : 'Date
				 * Entrée' }, { field :
				 * 'raisonMouvementDesignation', displayName :
				 * 'Raison d\'entrée' }, { field : 'valeur',
				 * displayName : 'Valeur BS' }, { field : 'etat',
				 * displayName : 'Etat' }, { field : '',
				 * cellTemplate : '<div class="buttons pull-right"><button
				 * data-nodrag class="btn btn-default btn-xs"
				 * ng-click="modifierOuCreerBonMouvementStock()"> <i
				 * class="fa fa-fw fa-pencil"></i></button>
				 * <button data-nodrag class="btn btn-default
				 * btn-xs" ng-click="supprimerMouvementStock()"><i
				 * class="fa fa-fw fa-trash-o"></i></button></div>' } ],
				 * showSelectionCheckbox : true,
				 * beforeSelectionChange :
				 * $scope.beforeVehicleSelectionChange,
				 * checkboxHeaderTemplate : '<input
				 * class="ngSelectionHeader" type="checkbox"
				 * ng-show="multiSelect" ng-model="allSelected"
				 * ng-change="toggleSelectAll(allSelected,
				 * true)"/>', enablePaging : true, showFooter :
				 * true, totalServerItems : 'totalServerItems',
				 * pagingOptions : $scope.pagingOptions,
				 * filterOptions : $scope.filterOptions, }; /** Fin
				 * de gestion des Grids Stock
				 */
			}])
	.controller(
		'DateEntreeIntro',
		[
			'$scope',
			function ($scope) {

				$scope.maxToDay = new Date();
				// $scope.bonMouvementStockCourante = {};
				$scope.today = function () {
					$scope.bonMouvementStockCourante.date = new Date();
				};
				// $scope.today();

				$scope.clear = function () {
					$scope.bonMouvementStockCourante.date = null;
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

				$scope.initDate = new Date('2016-15-20');
				$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd',
					'dd.MM.yyyy', 'shortDate'];
				$scope.format = $scope.formats[0];
			}])
