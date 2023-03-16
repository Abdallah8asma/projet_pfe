'use strict'
/**
 * Gestion Stock
 */
angular
		.module('gpro.stockEntreeMP', [])
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
						'$window', 
						function($scope, $http, $filter, $log, downloadService,
								UrlAtelier, UrlCommun, $rootScope,$window ) {
							var data;
							$scope.fcon = true;
							$scope.myData = [];
							$scope.stockBD = [];
							$rootScope.listArticlesFACE = [];
							$scope.displayMode = "list";
							$scope.bonMouvementStockCourante = {
								typeBonMouvement : "ENTREE"
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
							$scope.ListeMises=[];
							// declaration variable liste des Mouvements
							$rootScope.listArticlesFournituresE = [];
							$rootScope.listArticlesTissusE = [];
							$rootScope.listArticlesFACE = [];
							$scope.idSelectionnee = [];
							$rootScope.error=false;



							////////////////////document/////////////
							$scope.listeDocument = [];
							
							$scope.listeDocumentProduit = [];
							/***************************************************
							 * Slides Articles Entree *
							 **************************************************/
							$scope.animateArticleFourniture = function() {
								$("#articles").click(
										function() {
											$scope.openOrClose(
													'panel-articles',
													'#articles', 'hidePlus');
										});
								$("#tissuBtn").click(
										function() {
											$scope.openOrClose('tissu',
													'#tissuBtn',
													'hidePlusTissu');
										});
								$("#filBtn").click(
										function() {
											$scope.openOrClose('fil',
													'#filBtn', 'hidePlusFil');
										});
								$("#fournituresBtn").click(
										function() {
											$scope.openOrCloseInv(
													'fournitures',
													'#fournituresBtn',
													'hidePlusFourniture');
										});
							}
							$scope.openOrClose = function(id_panel, id_div,
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
							$scope.openOrCloseInv = function(id_panel, id_div,
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
							// Liste Fournisseur
							$scope.listeFournisseurCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireCache/listeFournisseurCache")
										.success(
												function(dataC) {
													$scope.listeFournisseurCache = dataC;

												});
							}
							// Liste de Client cache
							$scope.listeClientCache = function() {
								$http.get(UrlAtelier

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
														+ "/gestionnaireCache/listEmplacementCache")
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

									// Liste des PartieInteressee (avec FamilleId=1)
				$scope.listeFournisseursCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function (dataPartieInteressee) {

								$scope.listePartieInteressee = $filter(
									'filter')
									(
										dataPartieInteressee,
										{
											famillePartieInteressee: 2
										});

								$scope.listePartieInteresseeClient = $filter(
									'filter')
									(
										dataPartieInteressee,
										{
											famillePartieInteressee: 1
										});


							});
				}


	                       $scope.listeFournisseursCache();
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

								status : ''
							};

							// show Famille Mouvements
							$scope.showStatusFamille = function(id) {
								$scope.famille.status = id;

								var selected = $filter('filter')(
										$scope.listeFamilleCache, {
											id : $scope.famille.status
										});

								if ($scope.famille.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							}
							// show Sous Famille Mouvements
							$scope.showStatusSousFamille = function(id) {
								$scope.famille.status = id;

								var selected = $filter('filter')(
										$scope.listeSousFamilleCache, {
											id : $scope.famille.status
										});

								if ($scope.famille.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							}

							
							$scope.listArticlesFournitureSupprimes = {
									typeArticle : "",
									listeElementsSupprimes : []
								}
							
							// Supprimer Mouvements de type fourniture

							$scope.removeMvtFourniture = function(index) {

								$log.debug("element supprimes"	+ JSON.stringify(
																$rootScope.listArticlesFournituresE[index],
																null, " "));

								
								$scope.listArticlesFournitureSupprimes.typeArticle = "1";
								
								$scope.listArticlesFournitureSupprimes.listeElementsSupprimes
										.push({
											"entiteStockId" : $rootScope.listArticlesFournituresE[index].entiteStock,
											"qteReelle" : $rootScope.listArticlesFournituresE[index].quantiteReelle
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
									typeArticle : "",
									listeElementsSupprimes : []
								}

							$scope.removeMvtTissu = function(index) {
								
								$log.debug("element supprimes"	+ JSON.stringify(
										$rootScope.listArticlesTissusE[index],
										null, " "));

								$scope.listArticlesTissuSupprimes.typeArticle = "2";
								
								$scope.listArticlesTissuSupprimes.listeElementsSupprimes
										.push({
											"entiteStockId" : $rootScope.listArticlesTissusE[index].entiteStock,
											"qteReelle" : $rootScope.listArticlesTissusE[index].quantiteReelle,
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

							// Annuler Ajout
							$scope.cancelAdd = function(rowform, index, id,
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
								pageSizes : [ 5, 10, 13 ],
								pageSize : 13,
								currentPage : 1
							};

							// Rechercher par Type Entree
							$scope.rechercherStockEntrerParType = function(type) {
								$http
										.get(
												UrlAtelier
														+ "/bonMouvementStock/getBonMouvementParType:"
														+ type)
										.success(function(resultat) {
											$scope.myData = resultat;
											$scope.displayMode = "rechercher";
										});
								//$scope.rechercheSEForm.$setPristine();
							}
							$scope.rechercherStockEntrerParType("ENTREE");
							// Rechercher Stock Entree

							$scope.rechercherStockEntrer = function(
									mouvementCourante) {
								
								$log.debug("--getBonMouvementParType  mouvementCourante--"+JSON.stringify(mouvementCourante,null," "));
								$http
										.post(
												UrlAtelier
														+ "/bonMouvementStock/rechercheParCritere",
												mouvementCourante)
										.success(
												function(resultat) {
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

								$log.debug('test123');
							}

							$log.debug("idSelectionnee : "
									+ $scope.idSelectionnee);
							// ** Ajout Article
							$scope.AffectationArticle = function(article) {
								$scope.articleCourante = article ? angular
										.copy(article) : {};
								$scope.articleCourante = {};
								$scope.creationArticleForm.$setPristine();
								$scope.displayMode = "edit";
							}
							// ** Ajout mouvementStock
							$scope.AffectationMouvement = function(
									mouvementStock) {
								$scope.bonMouvementStockCourante = mouvementStock ? angular
										.copy(mouvementStock)
										: {};
								$scope.mouvementCourante = {};
								$scope.creationSEForm.$setPristine();

								$scope.displayMode = "edit";
							}
							// Ajout et Modification mouvementStock
							$scope.modifierOuCreerBonMouvementStock = function(id) {
							//	var index = this.row.rowIndex;
								// getmouvementStock
								$http
										.get(
												UrlAtelier
														+ "/bonMouvementStock/getId:"
														//+ $scope.myData[index].id)
															+ id)
										.success(
												function(data) {
													$scope.bonMouvementStockCourante =data;

													console
															.log("getById : data"
																	+ JSON
																			.stringify(
																					data,
																					null,
																					" "));

													classifierListMouvement(data.mouvements);
												});
								//$scope.bonMouvementStockCourante = $scope.myData[index] ? angular
									//	.copy($scope.myData[index])
										//: {};
								$scope.displayMode = "edit";
								$scope.openOrClose('panel-articles',
										'#articles', 'hidePlus');
								$scope.openOrClose('tissu', '#tissuBtn',
										'hidePlusTissu');
								$scope.openOrClose('fil', '#filBtn',
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
							$scope.sauvegarderAjout = function(mouvementStock) {
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
							$scope.updateBonMouvement = function(
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
								
								bonMouvementStock.listeMouvementsSupprimes=[];
								
								if($scope.listArticlesFournitureSupprimes.listeElementsSupprimes.length>0){
									bonMouvementStock.listeMouvementsSupprimes
									.push($scope.listArticlesFournitureSupprimes);
								}
								
								if($scope.listArticlesTissuSupprimes.listeElementsSupprimes.length>0){
									bonMouvementStock.listeMouvementsSupprimes
									.push($scope.listArticlesTissuSupprimes);
								}
								
								if($scope.listArticlesFACESupprimes.listeElementsSupprimes.length>0){
									bonMouvementStock.listeMouvementsSupprimes
									.push($scope.listArticlesFACESupprimes);
								}

								$scope.listArticlesFournitureSupprimes = {
										typeArticle : "",
										listeElementsSupprimes : []
									}
								$scope.listArticlesTissuSupprimes = {
										typeArticle : "",
										listeElementsSupprimes : []
									}
								$scope.listArticlesFACESupprimes = {
										typeArticle : "",
										listeElementsSupprimes : []
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
												function(mouvementStockModifiee) {

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

							$scope.creerBonMouvementStock = function(
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
										.success(function(id) {
											//Todo : getById
											$scope.annulerAjout();

											$scope.displayMode = "list";

										});

							}

							$scope.myClick = function() {
								$log.debug($scope.fcon);
							}

							// Annulation de l'ajout
							$scope.annulerAjout = function() {
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
									typeBonMouvement : "ENTREE"
								});
							}
							// Suppression bon mouvement
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

									$scope.closePopupDelete();
									$scope.annulerAjout();

								})
								.error(function(){
									$scope.myData.splice($scope.index, 1);
								});
								$scope.closePopupDelete();
								$scope.annulerAjout();

							};

							/*** PDF ***/
							//generer rapport apres creation d'un bon de d'Entree. mode : Modification/Consultation
							$scope.downloadBonEntree = function(id) {


								$scope.traitementEnCoursGenererLivraison="true";
								//$log.debug("-- id"+id);
								var url = UrlAtelier
										+ "/reportgs/bonMouvementStockEntreeSortieById?id="
										+ id + "&type=pdf";
							/*	downloadService.download(url).then(
										function(success) {
											$log.debug('success : ' + success);
											//$scope.annulerAjout();
										}, function(error) {
											$log.debug('error : ' + error);
										});*/


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



							   /*** PDF ***/
			   $scope.downloadBarCode = function(etatCourant) {

				$scope.traitementEnCoursGenererLivraison="true";
								 	
							
				var url;
				//$log.debug("PI  "+produitCourant.partieInteressee );
				
			
				
				//$log.debug("-- produitCourant After" + JSON.stringify(produitCourant, null, "  ") );
				   url = UrlAtelier + "/reportgs/listEtatStockBarCodeFromBE?id="+etatCourant.id 
									
										 + "&type=pdf";

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



			   }

                        /*** Excel ***/
			   $scope.downloadBonEntreeExcel = function(id) {
							
				var url = UrlAtelier
						+ "/fichesGS/entreestock?id="
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


							// $scope.listeMouvement();
							$scope.typeAlert = 3;

							$scope
									.$watch(
											'myData',
											function() {
												$scope.colDefs = [
													{
														field : 'id',
														displayName : 'Id'
													},
														{
															field : 'numero',
															displayName : 'N°'
														},
														{
															field : 'date | date : "dd/MM/yyyy"',
															displayName : 'Date Entree'
														},
														{
															field : 'raisonMouvementDesignation',
															displayName : ' Raison Entree'
														},

														{
															field : 'valeur',
															displayName : 'Valeur'
														},
														{
															field : 'etat',
															displayName : 'Etat'
														},
														{
															field : 'referenceBonReception',
															displayName : 'Réf.B Réception'
														},
														
														{
															field : '',
															width: 80,
															
															cellTemplate : 
															
															`<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
															<button class="ms-CommandButton-button ms-CommandButton-Gpro  " ng-click="modifierOuCreerBonMouvementStock(row.entity.id)">
															<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
															</button>
															<button class="ms-CommandButton-button"  ng-click="showPopupDelete(15)" permission="['StockMP_Entree_Delete']">
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
												typeBonMouvement : "ENTREE"
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
																function(
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