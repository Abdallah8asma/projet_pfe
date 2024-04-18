'use strict'
angular
		.module('gpro.PopupStockSortieMP', [])
		.controller(
				'PopupStockSortieCtrl',
				[
						'$scope',
						'$modal',
						'$q',
						'$http',
						'UrlAtelier',
						'UrlCommun',
						'$rootScope',
						'$filter',
						'$log',
						function($scope, $modal, $q, $http, UrlAtelier, UrlCommun,
								$rootScope, $filter,$log) {

							$rootScope.typePopUp = [];

							var tmpSelectedItems = [];
							$scope.objetRechercher = {};

							var myWsData = [];

							$scope.isLoading = false;

							// Data from webservice
							var dataProduitFromWs = function(typeEntite,
									idMagasin) {
								// $scope.objetRechercher =
								// entiteStockFourniture;
								$scope.objetRechercher = {
									typeEntite : typeEntite,
									magasin : idMagasin,
									quantite:0,
									operateurQuantite:'>',
								};

								var deferred = $q.defer();
								$scope.isLoading = true;

								console.log("---------------$scope.objetRechercher:SORTIE"
										+ JSON.stringify(
												$scope.objetRechercher, null,
												" "));
								$http
										.post(
												UrlAtelier
														+ "/entiteStock/rechercheEntiteStockMouvement",
												$scope.objetRechercher)
										.success(function(data, status) {
											$scope.isLoading = false;
											console.log("----------resultat (objetRechercher) data :SORTIE"
													+ JSON.stringify(data, null," "));
											deferred.resolve(data);
											

										})
										.error(
												function(data, status) {
													$scope.isLoading = false;
													deferred.reject('Erreur into url '+ data);
												})

								return deferred.promise;

							}

							// Decalration of modal pop up
							var openModalAfterSuccess = function(myWsData,
									currentIdTypeArticle, idMagasin) {
								var modalInstance = $modal.open({
									templateUrl : 'myModalContent.html',
									controller : ModalInstanceCtrl,
									resolve : {
										itemsToModal : function() {

											return myWsData;
										},

										currentIdTypeArticle : function() {

											return currentIdTypeArticle;
										},
										idMagasin : function() {

											return idMagasin;
										}

									}
								});

								// When we click on ok button
								modalInstance.result.then(
										function(selectedItem) {
											// $scope.whatYouSelect =
											// selectedItem;
											// console.log("whatYouSelect
											// "+JSON.stringify($scope.whatYouSelect,
											// null , " ") );
										}, function() {
											// console.log('Modal dismissed at:
											// ' + new Date());
										});

							};

							// Decalaration of modal pop up controller
							var ModalInstanceCtrl = function($scope,
									$modalInstance, itemsToModal,
									currentIdTypeArticle, idMagasin) {
								$scope.myCurrentList = itemsToModal.entiteStockMouvement;
								console.log("modal magasin" + idMagasin);
								$scope.idMagasin = idMagasin;

								// Annulation de l'ajout
								$scope.annulerAjoutPopUp = function() {

									// $scope.articleCourantrecherchePopup = {};
									// $scope.listeFamilleCacheParCategorie =
									// $filter
									// ('filter')($scope.listeFamilleCache,
									// {idTypeArticle : currentIdTypeArticle});
									// $scope.listeFamilleCacheParCategorieFiltree=$scope.listeFamilleCacheParCategorie;

									// $scope.rechercherArticle({});
								}

								$scope.addSelectedArticle = function(article) {

									console.log("article.isChecked"
											+ article.isChecked);
									if (article.isChecked) {
										tmpSelectedItems.push(article);
									} else {
										var index = tmpSelectedItems
												.indexOf(article);
										tmpSelectedItems.splice(index, 1);
									}

									console.log("tmpSelectedItems"
											+ JSON.stringify(tmpSelectedItems,
													null, " "));

								}

								$scope.objectInter = {

										idArticle : "",
										referenceArticle : "",
										designationArticle : "",
										familleArticle : "",
										prixUnitaire : "",
										prixTotal : "",
										typeArticle : "",
										type : "",
										nouveau : true

									};
								
								// ok popUp
								$scope.ok = function() {

									console.log("tmpSelectedItems"
											+ JSON.stringify(tmpSelectedItems,
													null, " "));
									$modalInstance.close(tmpSelectedItems);

									var TypeArticles = $rootScope.typePopUp;

									switch (TypeArticles) {
									case "Material":

										for (var i = 0; i < tmpSelectedItems.length; i++) {
											console
													.log("tmpSelectedItems.length "
															+ JSON
																	.stringify(
																			tmpSelectedItems,
																			null,
																			" "));

											$scope.objectInter.idArticle = tmpSelectedItems[i].id;
											$scope.objectInter.referenceArticle = tmpSelectedItems[i].reference;
											$scope.objectInter.designationArticle = tmpSelectedItems[i].designation;
											$scope.objectInter.familleArticle = tmpSelectedItems[i].famille;
											$scope.objectInter.quantiteAct = tmpSelectedItems[i].qteActuel;
											
											
											$scope.objectInter.entiteStock = tmpSelectedItems[i].entiteStockId;
											$scope.objectInter.prixUnitaire = tmpSelectedItems[i].pu;
											$scope.objectInter.prixTotal = tmpSelectedItems[i].PT;

											$scope.objectInter.typeArticle = 1;
											$scope.objectInter.idMagasin = $scope.idMagasin;

											$scope.objectInter.typeElementFicheBesoin = 1;
											$scope.objectInter.type = 1;
											$rootScope.listArticlesFournitures
													.push($scope.objectInter);

											$scope.objectInter = {

													idArticle : "",
													referenceArticle : "",
													designationArticle : "",
													familleArticle : "",
													prixUnitaire : "",
													prixTotal : "",
													typeArticle : "",
													type : "",
													nouveau : true

												};
										}

										$scope.emptyQRFourniture= true;
										 $rootScope.error=true;
										
										break;

									case "fil":

										for (var i = 0; i < tmpSelectedItems.length; i++) {

											$scope.objectInter.idArticle = tmpSelectedItems[i].id;
											$scope.objectInter.referenceArticle = tmpSelectedItems[i].reference;
											$scope.objectInter.designationArticle = tmpSelectedItems[i].designation;
											$scope.objectInter.familleArticle = tmpSelectedItems[i].famille;
											$scope.objectInter.quantiteAct = tmpSelectedItems[i].qteActuel;

											$scope.objectInter.entiteStock = tmpSelectedItems[i].entiteStockId;
											$scope.objectInter.prixUnitaire = tmpSelectedItems[i].pu;
											$scope.objectInter.prixTotal = tmpSelectedItems[i].PT;

											$scope.objectInter.typeArticle = 2;
											$scope.objectInter.type = 2;
											$scope.objectInter.idMagasin = $scope.idMagasin;

											$scope.objectInter.typeElementFicheBesoin = 2;

											console.log("$scope.objectInter"
													+ JSON.stringify(
															$scope.objectInter,
															null, " "));
											$rootScope.listArticlesTissus
													.push($scope.objectInter);

											$scope.objectInter = {

													idArticle : "",
													referenceArticle : "",
													designationArticle : "",
													familleArticle : "",
													prixUnitaire : "",
													prixTotal : "",
													typeArticle : "",
													type : "",
													nouveau : true

												};
										}

										$scope.emptyQRTissu= true;
										 $rootScope.error=true;
										
										break;

//									case "coudre":
//
//										for (var i = 0; i < tmpSelectedItems.length; i++) {
//
//											$scope.objectInter.idArticle = tmpSelectedItems[i].id;
//											$scope.objectInter.referenceArticle = tmpSelectedItems[i].reference;
//											$scope.objectInter.designationArticle = tmpSelectedItems[i].designation;
//											$scope.objectInter.familleArticle = tmpSelectedItems[i].famille;
//											$scope.objectInter.quantiteAct = tmpSelectedItems[i].qteActuel;
//
//											$scope.objectInter.entiteStock = tmpSelectedItems[i].entiteStockId;
//											$scope.objectInter.prixUnitaire = tmpSelectedItems[i].pu;
//											$scope.objectInter.prixTotal = tmpSelectedItems[i].PT;
//
//											$scope.objectInter.typeArticle = 3;
//											$scope.objectInter.type = 3;
//											$scope.objectInter.idMagasin = $scope.idMagasin;
//											$scope.objectInter.typeElementFicheBesoin = 3;
//
//											$rootScope.listArticlesFACE
//													.push($scope.objectInter);
//
//											$scope.objectInter = {
//
//													idArticle : "",
//													referenceArticle : "",
//													designationArticle : "",
//													familleArticle : "",
//													prixUnitaire : "",
//													prixTotal : "",
//													typeArticle : "",
//													type : "",
//													nouveau : true
//
//												};
//										}
//
//										break;
									}

									tmpSelectedItems.length = 0;
								};

								
								$rootScope.controlEmptyField=function(type){
									switch(type){
									
									case "fourniture" : 
										$scope.emptyQRFourniture=false;
										for (var int = 0; int < $rootScope.listArticlesFournitures.length; int++) {
											
											if((!angular.isDefined($rootScope.listArticlesFournitures[int].quantiteReelle))|| $rootScope.listArticlesFournitures[int].quantiteReelle.length==0){
												$log.debug("empty=true");
												$scope.emptyQRFourniture= true;
											}
										}
										
										$log.debug("$scope.emptyQRFourniture"+ $scope.emptyQRFourniture);
										break;
										
									case "tissu" : 
										$scope.emptyQRTissu=false;
										for (var int = 0; int < $rootScope.listArticlesTissus.length; int++) {
											if((!angular.isDefined($rootScope.listArticlesTissus[int].quantiteReelle)) || $rootScope.listArticlesTissus[int].quantiteReelle.length==0){
												$scope.emptyQRTissu= true;
											}
										}
										break;
									}
									
									
									$rootScope.error = $scope.emptyQRFourniture || $scope.emptyQRTissu;
									
									$log.debug("$rootScope.error"+ $rootScope.error);
								}

								
								$scope.cancel = function() {
									$modalInstance.dismiss('cancel');
								};
							};

							$scope.setMagasinId = function(idMagasin) {
								$scope.idMagasin = idMagasin;
							}

							// this function will be called when we click on
							// rechercher button
							$scope.open = function(typePopUp) {
								$rootScope.typePopUp = typePopUp;

								switch (typePopUp) {
								case "Material":

									// Here we have to call ws and in save the
									// response into variable named myWsData
									dataProduitFromWs(1, $scope.idMagasin)
											.then(
													function(data) {
														// promise fulfilled
														if (data) {
															myWsData = data
															 console.log("Test response " +	 JSON.stringify(myWsData, null," ") );
															// Call this
															// function when
															// sucess of web
															// service

															openModalAfterSuccess(myWsData,"1",$scope.idMagasin);

														} else {

														}
													},
													function(error) {
														// promise rejected,
														// could log the error
														// with:
														console.log('error',
																error);

													}

											);

									break;
								case "fil":
									// Here we have to call ws and in save the
									// response into variable named myWsData
									dataProduitFromWs(typePopUp, $scope.idMagasin)
											.then(
													function(data) {
														// promise fulfilled
														if (data) {
															// //console.log("LISTE
															// " +
															// JSON.stringify(
															// data, null," ")
															// );
															myWsData = data
															// console.log("
															// Test response " +
															// JSON.stringify(
															// myWsData, null,"
															// ") );
															// Call this
															// function when
															// sucess of web
															// service
															openModalAfterSuccess(
																	myWsData,
																	"2",
																	$scope.idMagasin);

														} else {

														}
													}, function(error) {
														// promise rejected,
														// could log the error
														// with:
														// //console.log('error',
														// error);

													}

											);

									break;

//								case "coudre":
//									// Here we have to call ws and in save the
//									// response into variable named myWsData
//									dataProduitFromWs(typePopUp, $scope.idMagasin)
//											.then(
//													function(data) {
//														// promise fulfilled
//														if (data) {
//															////console.log("LISTE   " + JSON.stringify(  data, null,"  ") );
//															myWsData = data
//															//console.log(" Test response " + JSON.stringify( myWsData, null,"  ") );
//															//Call this function when sucess of web service	
//															openModalAfterSuccess(
//																	myWsData,
//																	"3",
//																	$scope.idMagasin);
//														} else {
//
//														}
//													}, function(error) {
//														// promise rejected, could log the error with: //console.log('error', error);
//
//													}
//
//											);
//
//									break;
								}

							};

						} ])
