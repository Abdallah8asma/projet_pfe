'use strict'

angular
		.module('gpro.produit', [ "ngResource" ])
		.controller(
				'produitController',
				[
						'$scope',
						'$filter',
						'$http',
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {
							// Déclaration des variables globales utilisés
							var data;
							$scope.displayMode = "list";
							$scope.produitCourante = null;
							$scope.listePartieInteressee = [];
							$scope.listeDocuments = [];
							$scope.listeDocumentProduit = [];
							$scope.resultatRecherche = $scope.listeProduit;
							$scope.ListFamilleProduitCache = [];
							$scope.ListSousFamilleProduitCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.listeDeviseCache = [];
							$scope.ListeDevise = [];
							$scope.prixFab = 0;
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/
							// Liste des FamilleCache
							$scope.ListFamillesProduitCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeFamilleProduitCache")
										.success(
												function(
														dataFamilleProduitCache) {
													console
															.log("*LISTEFamilleProduitCache :"
																	+ dataFamilleProduitCache);
													$scope.ListFamilleProduitCache = dataFamilleProduitCache;

												});
							}

							// Liste des SousFamilleCache
							$scope.ListSousFamillesProduitCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSousFamilleProduitCache")
										.success(
												function(
														dataSousFamilleProduitCache) {
													console
															.log("*LISTESousFamilleProduitCache :"
																	+ dataSousFamilleProduitCache);
													$scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;

												});
							}

							// Liste des SiteeCache
							$scope.listeSitesPartieInteresseeCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSitePartieInteresseeCache")
										.success(
												function(dataSiteCache) {
													console
															.log("*LISTESiteCache :"
																	+ dataSiteCache);
													$scope.listeSitePartieInteresseeCache = dataSiteCache;

												});
							}

							
							// Liste des DeviseCache
							$scope.ListDeviseCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeDeviseCache")
										.success(
												function(dataDeviseCache) {
													console
															.log("*LISTEDeviseCache :"
																	+ dataDeviseCache);
													$scope.ListDeviseCache = dataDeviseCache;

												});
							}
							

							
							// Liste des PICache
							$scope.listePartieInteresseeCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPICache) {
													console
															.log("*LISTEPartieInteresseeCache :"
																	+ dataPICache);
													$scope.listePartieInteresseeCache = dataPICache;

												});
							}

							// Liste TypeDocumentCache
							$scope.listeTypeDocumentsCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeTypeDocumentCache")
										.success(
												function(dataTypeDocumentCache) {
													console
															.log("*LISTTypeDocumentCache :"
																	+ dataTypeDocumentCache);
													$scope.ListTypeDocumentCache = dataTypeDocumentCache;

												});
							}
							$scope.ListFamillesProduitCache();
							$scope.ListSousFamillesProduitCache();
							$scope.listeSitesPartieInteresseeCache();
							$scope.ListDeviseCache();
							$scope.listePartieInteresseeCache();
							$scope.listeTypeDocumentsCache();

							/***************************************************
							 * Gestion des Produits
							 **************************************************/
							$scope.deleteValue = "oui";
							// Annuler Ajout
							$scope.cancelAddProduit = function(rowform, index,
									id, designation, liste) {

								// console.log("* Designation
								// "+liste[0].designation);
								if (angular.isDefined(id)) {

									console.log("DEF ID");
									$scope.deleteValue = "non";
									rowform.$cancel();
									console.log("CANCEL");
								} else {
									console.log("UND ID  " + id);
									if (designation == "") {
										$scope.deleteValue == "oui"
										console.log("Designation : "
												+ designation);
										console.log("$scope.deleteValueOUI : "
												+ $scope.deleteValue);
										liste.splice(index, 1);
										rowform.$cancel();
										console.log("DELETE")
									} else {
										console.log("Designation :"
												+ designation);
										console.log("$scope.deleteValueNON : "
												+ $scope.deleteValue);
										rowform.$cancel();
										console.log("CANCEL");
									}
								}
								$scope.deleteValue = "oui";
							}

							// Liste des produits
							$scope.listeProduit = function() {
								$http.get(baseUrl + "/produit/all").success(
										function(data) {
											$scope.myData = data;

										});
							}

							// Liste des Devises
							$scope.ListeDevise = function() {
								$http.get(baseUrl + "/devise/all").success(
										function(dataDevise) {

											$scope.ListeDevise = dataDevise;
										});
							}

							// Liste des PartieInteressee
							$scope.listePartieInteressee = function() {
								$http
										.get(baseUrl + "/partieInteressee/all")
										.success(
												function(dataPartieInteressee) {

													$scope.listePartieInteressee = dataPartieInteressee;
												});
							}
							// Rechercher produit
							$scope.rechercheProduit = function(produitCourante) {
								$http
										.post(
												baseUrl
														+ "/produit/rechercheProduitMulticritere",
												produitCourante)
										.success(
												function(resultat) {
													$scope.myData = resultat.produitValues;
													$scope.rechercheProduitForm
															.$setPristine();
													$scope.displayMode = "rechercher";
												});
							}
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.cnt = 0;
								$scope.produitCourante = {};
								$scope.rechercheProduitForm.$setPristine();
								$scope.listeDocumentProduit = [];
								$scope.listeProduit();
								$scope.displayMode = "list";
							}
							// ** Ajout Produit
							$scope.AffectationProduit = function(Produit) {
								$scope.produitCourante = {};
								$scope.produitCourante = Produit ? angular
										.copy(Produit) : {};
								$scope.displayMode = "edit";
							}
							

							// Ajout et Modification Produit
							$scope.modifierOuCreerProduit = function(index) {
								var index = this.row.rowIndex;
								// getProduit
								$http
										.get(
												baseUrl
														+ "/produit/getId:"
														+ $scope.myData[index].id)
										.success(
												function(datagetProduit) {

													$scope.listeDocumentProduit = datagetProduit.documentProduits;

													$scope.creationProduitForm.$setPristine();
													$scope.myData[index].documentProduits = $scope.listeDocumentProduit;

												});

								$scope.produitCourante = $scope.myData[index] ? angular
										.copy($scope.myData[index])
										: {};
								$scope.displayMode = "edit";
							}

							// Sauvegarder Produit
							$scope.sauvegarderAjout = function(Produit) {
								console.log("Sauvegarder Modification"
										+ Produit);
								if (angular.isDefined(Produit.id)) {
									$scope.updateProduit(Produit);
								} else {
									console.log("**Sauvegarder Ajout"
											+ Produit.SiteId);
									$scope.creerProduit(Produit);
								}
							}

							// Mise à jour des Produits
							$scope.updateProduit = function(produit) {
								produit.documentProduits = $scope.listeDocumentProduit;

								$http
										.post(
												baseUrl
														+ "/produit/modifierProduit",
												produit)
										.success(
												function(ProduitModifiee) {

													for (var i = 0; i < $scope.listeProduit.length; i++) {

														if ($scope.myData[i].id == ProduitModifiee.id) {

															$scope.myData[i] = ProduitModifiee;
															break;
														}
													}
													// $scope.listeProduit();
													$scope.produitCourante = {};
													$scope.rechercheProduitForm.$setPristine();
													$scope.displayMode = "list";
												});
							}

							// Création Produit
							$scope.creerProduit = function(produit) {
								produit.documentProduits = $scope.listeDocumentProduit;

								$http.post(baseUrl + "/produit/creerProduit",
										produit).success(
										function(newProduit) {
											console.log("Success Creation"+ newProduit.designation);
											$scope.myData.push(newProduit);
											$scope.ProduitCourante = {};
											$scope.listeProduit();
											$scope.displayMode = "list";
											$scope.rechercheProduitForm.$setPristine();
										});
							}

							// Suppression produit
							$scope.supprimerProduit = function() {
								console.log("INDEX" + $scope.index);
								console.log("**OBJET :"
										+ $scope.myData[$scope.index]);
								console.log("DELETE .."
										+ $scope.myData[$scope.index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/produit/supprimerProduit:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
									console.log("Success Delete");
									$scope.myData.splice($scope.index, 1);
									$scope.$apply();
								});
							}

							$scope.listeProduit();
							$scope.listePartieInteressee();
							$scope.ListeDevise();

							/** Fin de gestion des Produits */

							/***************************************************
							 * Gestion des DocumentProduit
							 **************************************************/
							$scope.listeDocumentProduit = [];
							$scope.name = "P";
							// GetId.designation
							$scope.type = {

								status : ''
							};
							$scope.showStatus = function(id) {

								$scope.type.status = id;
								var selected = $filter('filter')(
										$scope.ListTypeDocumentCache, {
											id : $scope.type.status
										});
								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							};
							// ajout d'un DocumentProduit
							$scope.ajoutDocumentProduit = function() {

								$scope.DocumentProduitInserree = {
									typeDocumentId : '',
									uidDocument : '',
									path : ''

								};
								$scope.listeDocumentProduit
										.push($scope.DocumentProduitInserree);

							};

							// Enregistrer DocumentProduit
							$scope.saveDocumentProduit = function(
									dataDocumentProduit, id) {
								$scope.deleteValue = "non";
								console.log("$scope.deleteValue :"
										+ $scope.deleteValue);
								angular.extend(dataDocumentProduit, {
									id : id
								});
							};

							// Supprimer DocumentProduit
							$scope.removeDocumentProduit = function(index) {
								$scope.listeDocumentProduit.splice(index, 1);
							};
							/** Fin de gestion des DocumentProduit */

							/***************************************************
							 * Gestion des Grids de recherche
							 **************************************************/
							$scope.typeAlert = 3;
							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};
							$scope.totalServerItems = 0;
							$scope.pagingOptions = {
								pageSizes : [ 25, 50, 100 ],
								pageSize : 25,
								currentPage : 1
							};
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
								setTimeout(function() {

									if (searchText) {
										var ft = searchText.toLowerCase();

										data = $scope.myData
												.filter(function(item) {
													return JSON.stringify(item)
															.toLowerCase()
															.indexOf(ft) != -1;
												});
										$scope.setPagingData(data, page,
												pageSize);
									} else {

										$scope.setPagingData($scope.myData,
												page, pageSize);

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
								dataselected : 'myDataselected',
								selectedItems : [],

								data : 'myData',
								columnDefs : [
										{
											field : 'reference',
											displayName : 'Réf.Produit'
										},
										{
											field : 'designation',
											displayName : 'Désignation'
										},
										{
											field : 'familleDesignation',
											displayName : 'Famille'
										},
										{
											field : 'sousFamilleDesignation',
											displayName : 'Sous Famille'
										},
										{
											field : 'partieIntersseDesignation',
											displayName : 'PI'
										},
										{
											field : 'prixUnitaire',
											displayName : 'Prix'
										},
										{
											field : 'siteEntiteDesignation',
											displayName : 'Site'
										},
										{
											field : 'etat',
											displayName : 'Etat'
										},
										{
											field : '',
											 cellTemplate : '<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button> <button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete('
									             + $scope.typeAlert
									             + ')"> <i class="fa fa-fw fa-trash-o"></i></button>'
									             +'</div>'
										} ],
								enablePaging : true,
								showFooter : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								filterOptions : $scope.filterOptions,

							};
							/** Fin de gestion des Grids de la produit */
							$scope.editFicheBesoin = function() {
						        var index = this.row.rowIndex;
						        window.location.href = "#/ficheBesoin?idProduit="+$scope.myData[index].id;
						       };
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
