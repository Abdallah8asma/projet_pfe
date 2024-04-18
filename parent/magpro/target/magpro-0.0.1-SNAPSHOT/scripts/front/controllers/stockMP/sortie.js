'use strict'
angular
		.module('gpro.stockSortieMP', [])
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
					'$window',
					function($scope, $http, $filter, $log, downloadService,
							UrlAtelier, UrlCommun, $rootScope,$window ) {
						var data;

						$scope.myData = [];
						$scope.stockBD = [];
						$scope.displayMode = "list";
						$scope.mouvementStockCourante = {
							typeBonMouvement : "SORTIE"
						};
						$scope.bonMouvementStockCourante = {};
						$scope.MvtInserree = {

							referenceArticle : '',
							designationArticle : '',
							quantite : '',
							prixTotal : '',
							emplacement : ''
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
						$rootScope.error=false;




								////////////////////document/////////////
								$scope.listeDocument = [];
							
								$scope.listeDocumentProduit = [];
						
						
						/***************************************************
						 * Slides Articles Sortie *
						 **************************************************/
						$scope.animateArticleFourniture = function() {
							$("#articlesS").click(
									function() {
										$scope.openOrClose(
												'panel-articlesS',
												'#articlesS', 'hidePlus');
									});
							$("#tissuBtnS").click(
									function() {
										$scope.openOrClose('tissuS',
												'#tissuBtnS',
												'hidePlusTissu');
									});
							$("#filBtnS").click(
									function() {
										$scope.openOrClose('filS',
												'#filBtnS', 'hidePlusFil');
									});
							$("#fournituresBtnS").click(
									function() {
										$scope.openOrCloseInv(
												'fournituresS',
												'#fournituresBtnS',
												'hidePlusFourniture');
									});
						}
						$scope.openOrClose = function(id_panel, id_div,
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
						$scope.openOrCloseInv = function(id_panel, id_div,
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
						$scope.listeMatiereCache = function() {
							$http
									.get(
											UrlCommun
													+ "/gestionnaireCache/listeMatiereArticleCache")
									.success(function(data) {
										$scope.listeMatiereCache = data;

									});
						}

						// Liste de Metrage cache
						$scope.listeMetrageCache = function() {
							$http
									.get(
											UrlCommun
													+ "/gestionnaireCache/listeMetrageArticleCache")
									.success(function(data) {
										$scope.listeMetrageCache = data;

									});
						}
						// Liste de Grosseur cache
						$scope.listeGrosseurCache = function() {
							$http
									.get(
											UrlCommun
													+ "/gestionnaireCache/listeGrosseurArticleCache")
									.success(function(data) {
										$scope.listeGrosseurCache = data;

									});
						}
						// Liste de Famille cache
						$scope.listeFamilleCache = function() {
							$http
									.get(
											UrlCommun
													+ "/gestionnaireCache/listeFamilleArticleCache")
									.success(function(data) {
										$scope.listeFamilleCache = data;

									});
						}
						// Liste de Sous Famille cache
						$scope.listeSousFamilleCache = function() {
							$http
									.get(
											UrlCommun
													+ "/gestionnaireCache/listeSousFamilleArticleCache")
									.success(
											function(data) {
												$scope.listeSousFamilleCache = data;

											});
						}
						// Liste de Client cache

						// Liste de Client cache
						$scope.listeClientCache = function() {
							$http
									.get(
											UrlAtelier
													+ "/gestionnaireCache/listeClientCache")
									.success(function(dataC) {
										$scope.listeClientCache = dataC;

									});
						}

						// Liste de Sous traitant Cache
						$scope.listeSousTraitantCache = function() {
							$http
									.get(
											UrlAtelier
													+ "/gestionnaireCache/listeSousTraitantCache")
									.success(
											function(dataS) {
												$scope.listeSousTraitantCache = dataS;

											});
						}
						// able or disable list partie interesse
						$('#checkboxPI').change(
								function() {
									if (this.checked) {
										$("#selectPIStockSortie").attr(
												'disabled', false);
									} else {
										$("#selectPIStockSortie").attr(
												'disabled', 'disabled');
									}
								});
						// Liste de magasinCache
						$scope.listeMagasinCache = function() {
							$http
									.get(
											UrlAtelier
													+ "/magasin/depots")
									.success(
											function(dataMagasin) {
												$scope.listeMagasinCache = dataMagasin;

											});
						}
						// Liste de emplacementCache
						$scope.ListeEmplacementCache = function() {
							$http
									.get(
											UrlAtelier
													+ "/gestionnaireCache/listeEmplacementCache")
									.success(
											function(dataFamilleCache) {
												$scope.ListEmplacementCache = dataEmplacementCache;

											});
						}
						// Liste de raisonCache
						$scope.ListeRaisonCache = function() {
							$http
									.get(
											UrlAtelier
													+ "/gestionnaireCache/listeRaisonCache")
									.success(
											function(dataSousFamilleCache) {
												$scope.ListeRaisonCache = dataSousFamilleCache;

											});
						}
						
						// Liste des mises
						$scope.ListeMises = function() {
							$http
									.get(
											UrlAtelier
													+ "/mise/all")
									.success(
											function(dataMises) {
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
						$scope.cancelAdd = function(rowform, index, id,
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

						$scope.rechercherStockSortie = function(
								mouvementCourante) {

									mouvementCourante.typeBonMouvement = 'SORTIE';
							
							$log.debug("----------mouvementCourante"+ JSON.stringify(mouvementCourante,null," "));
							
							$http
									.post(
											UrlAtelier
													+ "/bonMouvementStock/rechercheParCritere",
											mouvementCourante)
									.success(
											function(resultat) {
												$log.debug("----------resultat----"+ JSON.stringify(resultat,null," "));

												$scope.myData = resultat.bonMouvementStock;
												$log.debug("----------$scope.myData----"+ JSON.stringify($scope.myData,null," "));

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
						$scope.AffectationMouvement = function(
								mouvementStock) {
							$scope.mouvementStockCourante = mouvementStock ? angular
									.copy(mouvementStock)
									: {};
							$scope.mouvementCourante = {};
							$scope.rechercheSSForm.$setPristine();
							$scope.displayMode = "edit";

						}
						// Ajout et Modification mouvementStock
						$scope.modifierOuCreerBonMouvementStock = function(id) {
							// vider les Listes
							$scope.listeArticles = [];
							$rootScope.listArticlesFournitures = [];
							$rootScope.listArticlesTissus = [];
							$rootScope.listArticlesFACE = [];

						//	var index = this.row.rowIndex;
							// getmouvementStock
							$http
									.get(
											UrlAtelier
													+ "/bonMouvementStock/getId:"
													//+ $scope.myData[index].id
													+id)
									.success(
											function(data) {
												$scope.bonMouvementStockCourante =data;
												classifierListMouvement(data.mouvements);
										
											});
					/* 		$scope.bonMouvementStockCourante = $scope.myData[index] ? angular
									.copy($scope.myData[index])
									: {}; */
							
							$scope.displayMode = "edit";
							$scope.openOrClose('panel-articlesS',
									'#articlesS', 'hidePlus');
							$scope.openOrClose('tissuS', '#tissuBtnS',
									'hidePlusTissu');
							$scope.openOrClose('filS', '#filBtnS',
									'hidePlusFil');
						}

						//Classification des ListMouvement selon le Type
						var classifierListMouvement = function(
								listElementMouvement) {
							// subListes de ListMouvement: 1:"fourniture", 2:"tissu", 3: "fil à coudre" 
							angular
									.forEach(
											listElementMouvement,
											function(elementMouvement, key) {

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
						$scope.sauvegarderAjout = function(mouvementStock) {

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
						$scope.updateBonMouvement = function(
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
							
							bonMouvementStock.listeMouvementsSupprimes=[];
							
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
											function(mouvementStockModifiee) {

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

						$scope.creerBonMouvementStock = function(
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
											function(id) {

												$scope.rechercheSSForm
														.$setPristine();

												$scope.creationSSForm
														.$setPristine();

												$scope.annulerAjoutS();

												$scope.displayMode = "list";

											});

						}

						// Annulation de l'ajout
						$scope.annulerAjoutS = function() {

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
								typeBonMouvement : "SORTIE"
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
						$scope.supprimerMouvementStock = function() {
							//var index = this.row.rowIndex;
							//$scope.index = this.row.rowIndex;
							$log.debug("DELETE .."
									+ $scope.myData[$scope.index].id);
							$http(
									{
										method : "DELETE",
										url : UrlAtelier
												+ "/bonMouvementStock/supprimerBonMouvementStock:"
												+ $scope.myData[$scope.index].id
									}).success(function() {
								//Pas d'entree au niveau success delete 403 erreur
								$log.debug("success delete");
								$scope.myData.splice($scope.index, 1);

							})
							.error(function(){
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
								typeArticle : "",
								listeElementsSupprimes : []
							}
						
						// Supprimer Mouvements
						$scope.removeMvtFourniture = function(index) {
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
											"entiteStockId" : $rootScope.listArticlesFournitures[index].entiteStock,
											"qteReelle" : $rootScope.listArticlesFournitures[index].quantiteReelle
										});
						
							
							$rootScope.listArticlesFournitures.splice(
									index, 1);
							$log.debug("$scope.listeArticles : "
									+ $scope.listeArticles);
						};
						
						
						$scope.listArticlesTissuSupprimes = {
								typeArticle : "",
								listeElementsSupprimes : []
							}

						
						$scope.removeMvtTissu = function(index) {
							
							$scope.listArticlesTissuSupprimes.typeArticle = "2";
							
							$scope.listArticlesTissuSupprimes.listeElementsSupprimes
									.push({
										"entiteStockId" : $rootScope.listArticlesTissus[index].entiteStock,
										"qteReelle" : $rootScope.listArticlesTissus[index].quantiteReelle,
										"nbRouleauxReel": $rootScope.listArticlesTissus[index].nbRouleauxReel
									});
							
							$rootScope.listArticlesTissus.splice(index, 1);
						};
						
						
						$scope.listArticlesFACESupprimes = {
								typeArticle : "",
								listeElementsSupprimes : []
							}

						
						$scope.removeMvtFAC = function(index) {
							

							$scope.listArticlesFACESupprimes.typeArticle = "3";
							
							$scope.listArticlesFACESupprimes.listeElementsSupprimes
									.push({
										"entiteStockId" : $rootScope.listArticlesFACE[index].entiteStock,
										"conesReel" : $rootScope.listArticlesFACE[index].conesReel,
										"finconesReel": $rootScope.listArticlesFACE[index].finconesReel,
										"poidsReel": $rootScope.listArticlesFACE[index].poids
									});
							
							$rootScope.listArticlesFACE.splice(index, 1);
						};
						/** Fin de gestion des Mouvements */

						//generer rapport apres creation d'un bon de d'Sortie. mode : Modification/Consultation
						$scope.downloadBonSortie = function(id) {
							
							var url = UrlAtelier
									+ "/reportgs/bonMouvementStockEntreeSortieById?id="
									+ id + "&type=pdf";

									var a = document.createElement('a');
										document.body.appendChild(a);
										downloadService.download(url).then(function (result) {
											var heasersFileName = result.headers(['content-disposition']).substring(17);
										var fileName = heasersFileName.split('.');
									var typeFile = result.headers(['content-type']);
									var file = new Blob([result.data], {type: typeFile});
									var fileURL = window.URL.createObjectURL(file);
				
				
									a.download = fileName[0];
									$window.open(fileURL)
									 a.click();
				
										
									$scope.traitementEnCoursGenererLivraison="false";
										});
						};


						
						                 /*** Excel ***/
						$scope.downloadBonSortieExcel = function(id) {
							
							var url = UrlAtelier
									+ "/fichesGS/sortiestock?id="
									+ id + "&type=xls";

									var a = document.createElement('a');
										document.body.appendChild(a);
										downloadService.download(url).then(function (result) {
											var heasersFileName = result.headers(['content-disposition']).substring(17);
										var fileName = heasersFileName.split('.');
									var typeFile = result.headers(['content-type']);
									var file = new Blob([result.data], {type: typeFile});
									var fileURL = window.URL.createObjectURL(file);
				
				
									a.download = fileName[0];
									$window.open(fileURL)
									 a.click();
				
										
									
										});
						};


						/*
						 * ******* gestion gridStock*******
						 */
						$scope.typeAlert = 3;
						$scope.pagingOptions = {
							pageSizes : [ 5, 10, 13 ],
							pageSize : 13,
							currentPage : 1
						};



						

						$scope
								.$watch(
										'myData',
										function() {
											$scope.colDefs = [
												{
													field : 'numero',
													displayName : 'N°'
												},
												{
													field : 'date | date : "dd/MM/yyyy"',
													displayName : 'Date Sortie'
												},
												{
													field : 'raisonMouvementDesignation',
													displayName : ' Raison Sortie '
												},

												{
													field : 'numOF',
													displayName : 'Of'
												},
												{
													field : 'etat',
													displayName : 'Etat'
												},
												{   field: 'origineFSuiveuse',
													displayName: 'PDA Origin',
													type: 'boolean',
													width: 90,
													cellTemplate: '<input type="checkbox" style = "margin:10%"; ng-model="row.entity.origineFSuiveuse" disabled>'},
													{
														field : '',
														width: 80,
														cellTemplate :
														
														`<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
														<button class="ms-CommandButton-button ms-CommandButton-Gpro  " ng-click="modifierOuCreerBonMouvementStock(row.entity.id)">
														<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
														</button>
														<button class="ms-CommandButton-button"  ng-click="showPopupDelete(15)" permission="['StockMP_Sortie_Delete']">
														<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
													   </button>
															</div> `,
														
														
														
														
														
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
										var BonMvtCourant = {
											typeBonMouvement : "SORTIE"
										};
										if (searchText) {
											var ft = searchText
													.toLowerCase();
											$http
											.post(
													UrlAtelier
															+ "/bonMouvementStock/rechercheParCritere",  BonMvtCourant 
													)
													.success(
															function(
																	largeLoad) {
																data = largeLoad.bonMouvementStock
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
																				data.bonMouvementStock,
																				page,
																				pageSize);
															});

										} else {
											
											//$log.debug("BonMvtCourant"+ JSON.stringify(BonMvtCourant,null," "));
											
											$http
													.post(
															UrlAtelier
																	+ "/bonMouvementStock/rechercheParCritere",  BonMvtCourant
															)
													.success(
															function(
																	largeLoad) {
																
																$log.debug("------largeLoad----"+ JSON.stringify(largeLoad,null," "));
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

						$scope.gridOptionsS = {
							data : 'myData',
							columnDefs : 'colDefs',
							enablePaging : true,
							showFooter : true,
							enableHighlighting : true,
							enableColumnResize	: true,
							totalServerItems : 'totalServerItems',
							pagingOptions : $scope.pagingOptions,
							selectedItems : $scope.selectedRows,
							filterOptions : $scope.filterOptions,
						};

		
							//////////////////////////////////////document/////////////////////

				
						  // ajout d'un DocumentProduit
						  $scope.ajoutDocumentProduit = function () {
							$scope.DocumentProduitInserree = {
							  typeDocumentId: '',
							  uidDocument: '',
							  path: '',
							};
							$scope.listeDocumentProduit.push($scope.DocumentProduitInserree);
						  };
					
						  // Enregistrer DocumentProduit
						  $scope.saveDocumentProduit = function (dataDocumentProduit, id) {
							$scope.deleteValue = 'non';
							$log.debug('$scope.deleteValue :' + $scope.deleteValue);
							angular.extend(dataDocumentProduit, {
							  id: id,
							});
						  };
					
						  // Supprimer DocumentProduit
						  $scope.removeDocumentProduit = function (index) {
							$scope.listeDocumentProduit.splice(index, 1);
						  };
					
						  //Download Document
						  $scope.download = function (uuid) {
							var url = UrlCommun + '/gestionnaireDocument/document/' + uuid;
					
							downloadService.download(url).then(
							  function (success) {
								$log.debug('success : ' + success);
							  },
							  function (error) {
								$log.debug('error : ' + error);
							  }
							);
						  };




					} ])
	.controller(
			'DateEntreeIntro',
			[
					'$scope',
					function($scope) {

						$scope.maxToDay = new Date();
						// $scope.bonMouvementStockCourante = {};
						$scope.today = function() {
							$scope.bonMouvementStockCourante.date = new Date();
						};
						// $scope.today();

						$scope.clear = function() {
							$scope.bonMouvementStockCourante.date = null;
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

						$scope.initDate = new Date('2016-15-20');
						$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
								'dd.MM.yyyy', 'shortDate' ];
						$scope.format = $scope.formats[0];
					} ])
