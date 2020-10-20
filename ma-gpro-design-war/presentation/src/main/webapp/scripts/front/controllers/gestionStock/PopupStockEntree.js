'use strict'
angular
		.module('gpro.PopupStockEntree', [])
		.controller(
				'PopupStockEntreeCtrl',
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
						function($scope,
								$modal,
								$q,
								$http,
								UrlAtelier, 
								UrlCommun,
								$rootScope,
								$filter,
								$log) {

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
									idMAgasin : idMagasin
								};

								console.log("------$scope.objetRechercher"+ JSON.stringify($scope.objetRechercher,null, " "));
								var deferred = $q.defer();
								$scope.isLoading = true;

								$http
										.post(
												UrlAtelier
														+ "/articleEntree/rechercheArticleEntreeMultiCritere",
												$scope.objetRechercher)
										.success(function(data, status) {
											
											$scope.isLoading = false;
											deferred.resolve(data);

										})
										.error(
												function(data, status) {
													$scope.isLoading = false;
													deferred
															.reject('Erreur into url '
																	+ data);
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
								$scope.myCurrentList = itemsToModal.articleEntree;
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
									pu : "",
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
									case "fourniture":

										for (var i = 0; i < tmpSelectedItems.length; i++) {
											console
													.log("tmpSelectedItems.length "
															+ JSON
																	.stringify(
																			tmpSelectedItems,
																			null,
																			" "));

											$scope.objectInter.idArticle = tmpSelectedItems[i].id;
											$scope.objectInter.referenceArticle = tmpSelectedItems[i].ref;
											$scope.objectInter.designationArticle = tmpSelectedItems[i].designation;
											$scope.objectInter.familleArticle = tmpSelectedItems[i].familleArticleDesignation;

											$scope.objectInter.prixUnitaire = tmpSelectedItems[i].pu;
											$scope.objectInter.prixTotal = tmpSelectedItems[i].prixTotal;

											$scope.objectInter.typeArticle = 1;
											$scope.objectInter.idMagasin = $scope.idMagasin;

											$scope.objectInter.typeElementFicheBesoin = 1;
											$scope.objectInter.type = 1;
											$rootScope.listArticlesFournituresE
													.push($scope.objectInter);

											$scope.objectInter = {

												idArticle : "",
												referenceArticle : "",
												designationArticle : "",
												familleArticle : "",
												pu : "",
												prixTotal : "",
												typeArticle : "",
												type : "",
												nouveau : true

											};
										}

										$scope.emptyQRFourniture= true;
										 
										break;
										
									case "tissu":

										for (var i = 0; i < tmpSelectedItems.length; i++) {

											$scope.objectInter.idArticle = tmpSelectedItems[i].id;
											$scope.objectInter.referenceArticle = tmpSelectedItems[i].ref;
											$scope.objectInter.designationArticle = tmpSelectedItems[i].designation;
											$scope.objectInter.familleArticle = tmpSelectedItems[i].familleArticleDesignation;

											$scope.objectInter.prixUnitaire = tmpSelectedItems[i].pu;
											$scope.objectInter.prixTotal = tmpSelectedItems[i].prixTotal;

											$scope.objectInter.typeArticle = 2;
											$scope.objectInter.type = 2;
											$scope.objectInter.idMagasin = $scope.idMagasin;

											$scope.objectInter.typeElementFicheBesoin = 2;

											console.log("$scope.objectInter"
													+ JSON.stringify(
															$scope.objectInter,
															null, " "));
											$rootScope.listArticlesTissusE
													.push($scope.objectInter);

											$scope.objectInter = {

												idArticle : "",
												referenceArticle : "",
												designationArticle : "",
												familleArticle : "",
												pu : "",
												prixTotal : "",
												typeArticle : "",
												type : "",
												nouveau : true

											};
										}
										
										$scope.emptyQRTissu= true;
										
										break;

//									case "coudre":
//
//										for (var i = 0; i < tmpSelectedItems.length; i++) {
//
//											$scope.objectInter.idArticle = tmpSelectedItems[i].id;
//											$scope.objectInter.referenceArticle = tmpSelectedItems[i].ref;
//											$scope.objectInter.designationArticle = tmpSelectedItems[i].designation;
//											$scope.objectInter.familleArticle = tmpSelectedItems[i].familleArticleDesignation;
//											
//											$scope.objectInter.prixUnitaire = tmpSelectedItems[i].pu;
//											$scope.objectInter.prixTotal = tmpSelectedItems[i].prixTotal;
//
//											$scope.objectInter.typeArticle = 3;
//											$scope.objectInter.type = 3;
//											$scope.objectInter.typeElementFicheBesoin = 3;
//											$scope.objectInter.idMagasin = $scope.idMagasin;
//
//											$rootScope.listArticlesFACE
//													.push($scope.objectInter);
//
//											$scope.objectInter = {
//
//												idArticle : "",
//												referenceArticle : "",
//												designationArticle : "",
//												familleArticle : "",
//												pu : "",
//												prixTotal : "",
//												typeArticle : "",
//												type : "",
//												nouveau : true
//
//											};
//										}
//										
//										$scope.emptyQRCoudre= true;
//										
//										break;
									}
									
									 $rootScope.error=true;
									tmpSelectedItems.length = 0;
								};
								
								
								$rootScope.controlEmptyField=function(type){
									switch(type){
									
									case "fourniture" : 
										$scope.emptyQRFourniture=false;
										for (var int = 0; int < $rootScope.listArticlesFournituresE.length; int++) {
											
											if((!angular.isDefined($rootScope.listArticlesFournituresE[int].quantiteReelle))|| $rootScope.listArticlesFournituresE[int].quantiteReelle.length==0){
												$scope.emptyQRFourniture= true;
											}
										}
										break;
										
									case "tissu" : 
										$scope.emptyQRTissu=false;
										for (var int = 0; int < $rootScope.listArticlesTissusE.length; int++) {
											if((!angular.isDefined($rootScope.listArticlesTissusE[int].quantiteReelle)) || $rootScope.listArticlesTissusE[int].quantiteReelle.length==0){
												$scope.emptyQRTissu= true;
											}
										}
										break;
									
									case "coudre" : 
										$scope.emptyQRCoudre=false;
										for (var int = 0; int < $rootScope.listArticlesFACE.length; int++) {
											if(((!angular.isDefined($rootScope.listArticlesFACE[int].conesReel)) || $rootScope.listArticlesFACE[int].conesReel.length==0)  && ((!angular.isDefined($rootScope.listArticlesFACE[int].finconesReel)) || $rootScope.listArticlesFACE[int].finconesReel.length==0)){
												$scope.emptyQRCoudre= true;
											}
										}
										break;
									}
									
									
									$rootScope.error = $scope.emptyQRFourniture || $scope.emptyQRTissu || $scope.emptyQRCoudre;
								}

								$scope.cancel = function() {
									$modalInstance.dismiss('cancel');
								};
							};

							// this function will be called when we click on
							// rechercher button
							$scope.open = function(typePopUp) {
								$rootScope.typePopUp = typePopUp;

								switch (typePopUp) {
								case "fourniture":

									// Here we have to call ws and in save the
									// response into variable named myWsData
									dataProduitFromWs(1, $scope.idMagasin)
											.then(
													function(data) {
														// promise fulfilled
														if (data) {
															myWsData = data
															// //console.log("
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
																	"1",
																	$scope.idMagasin);

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
								case "tissu":
									// Here we have to call ws and in save the
									// response into variable named myWsData
									dataProduitFromWs(2, $scope.idMagasin)
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
//									dataProduitFromWs(3, $scope.idMagasin)
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
