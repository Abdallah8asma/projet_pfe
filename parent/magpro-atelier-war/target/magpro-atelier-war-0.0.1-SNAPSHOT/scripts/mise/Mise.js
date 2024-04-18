'use strict'
angular
		.module('gpro.mise', [ "ngResource" ])
		.controller(
				'MiseController',
				[
						'$scope',
						'$filter',
						'$http',
						'$interval',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						function($scope, $filter, $http, $interval, $log,
								downloadService, UrlCommun, UrlAtelier) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "list";
							$scope.displayMenu = "pi";
							// Dropdown
							$scope.listeClient = [];
							$scope.listeTissu = [];
							$scope.resultatRechercheBonReception = [];
							$scope.listeBonReceptionDrop = [];

							// mode creation
							$scope.creation = true;
							// modePdf desactivé par defaut
							$scope.modePdf = "false";
							//afficher les attributs d'un BonDeReception
							$scope.remplir = false;

							$scope.date = new Date();
							
							$scope.miseCouranteRecherche = {};
							$scope.miseCourante = {};

							/***************************************************
							 * Gestion de la Menu PI
							 **************************************************/
							$scope.changeTaPartieInteresse = function() {
								$scope.displayMenu = "pi";
							}
							$scope.changeTaElementBase = function() {
								$scope.displayMenu = "eb";
							}
							$scope.changeTaGestionCommerciale = function() {
								$scope.displayMenu = "gc";
							}
							
						 	 $scope.checkboxModel= {
						 			 isStitClient : false
						 	 };

							/**************************************************
							 * gestion de la cache
							 **************************************************/
							// Liste Client
							$scope.listeClient = function() {
								$http.get(UrlCommun + "/partieInteressee/all")
										.success(
												function(data) {
													$log.debug("Client : "
															+ data.length);
													$scope.listeClient = data;

												});
							}
							
							$scope.listeClient();
							
						
							
							/**************************************************
							 * gestion de la cache
							 **************************************************/
							// Liste Client
							$scope.listeProduit = function() {
								$http.get(UrlCommun + "/produit/all")
										.success(
												function(data) {
													$log.debug("Produit : "
															+ data.length);
													$scope.listeProduit = data;

												});
							}
							
							$scope.listeProduit();
							
							// Liste Lot
							$scope.listeLot = function() {
								$http.get(UrlAtelier + "/entiteStock/lots")
										.success(
												function(data) {
													$log.debug("Produit : "
															+ data.length);
													$scope.listeLot = data;

												});
							}
							
							$scope.listeLot();
							/*
							$scope.remplirReference = function(refBonreception) {
								$scope.remplir = true;
								$log.debug("idLot "+idLot);
								
								// getBonReception
								if(idLot!= null){
									$http.get(
											UrlAtelier
													+ "/entiteStock/getLot:"+ idLot)
									.success(
											function(datagetLot) {
												$scope.resultatRechercheMise = datagetLot;
												//referenceMise 
												$log.debug("---Mise Reference "+datagetMise.reference);
												$scope.resultatRechercheMise.referenceMise = datagetMise.reference;
												//refBonReception
												$scope.resultatRechercheMise.refBonreception = datagetMise.refBonreception;
												//miseFini
												$log.debug("---refBonReception "+datagetMise.refBonreception);
												//$scope.resultatRechercheMise.fini = datagetMise.fini;
												
												
											});
								}
							});
							*/
							
							$scope.remplirReference = function(idLot) {
								
								console.log("inn..");
								$scope.remplir = true;
								console.log("idLot "+idLot);
								if(idLot!= null)
								{
									$http.get(UrlAtelier + "/entiteStock/getLot:"+ idLot)
											.success(
													function(datagetLot) {
														$scope.resultatRechercheMise = datagetLot;
												//referenceMise 
												//console.log("---Mise Reference "+datagetMise.id);
												$scope.miseCourante.reference = datagetLot.referenceLot;
												//refBonReception
											//	$scope.resultatRechercheMise.refBonreception = datagetLot.refBonreception;
													$scope.miseCourante.poids=datagetLot.qteActuelle;	

													});
												
								}
							}
							
							/***************************************************
							 * Invocation des services pour les listes
							 * déroulantes
							 **************************************************/
							// Lister les Bons de Reception
							$scope.rechercherBonReception = function(
									bonReceptionCourant) {
								$http
										.post(
												UrlAtelier
														+ "/bonreception/rechercheBonReceptionMulticritere",
												bonReceptionCourant)
										.success(
												function(resultat) {

													$scope.listeBonReceptionDrop = resultat.listeElementRechecheBonReceptionValeur;

													console
															.log("listeBonReception : "
																	+ resultat.listeElementRechecheBonReceptionValeur.length);
												});

							}

							//$scope.rechercherBonReception({});
							
							$scope.clientId = {

									status : ''
								};
							
//							//Remplissage des champs lors du choix d'un Bon de reception
//							$scope.remplirMise = function(idBonReception) {
//								$scope.remplir = true;
//								
//								// getBonReception 
//								if(idBonReception!= null){
//									$http
//									.get(
//											UrlAtelier
//													+ "/bonreception/getBonReceptionId:"
//													+ idBonReception)
//									.success(
//											function(datagetBonReception) {
//												//referenceBonReception
//												$log.debug("refBonreception "+datagetBonReception.reference);
//												$scope.resultatRechercheBonReception.refBonreception = datagetBonReception.reference;
//												
//												// Produit & sousFamilleProduit (Methode avec getId )
//												if(datagetBonReception.produit!= null){
//													$http
//													.get(
//															UrlCommun
//																	+ "/produit/getId:"+ datagetBonReception.produit)
//													.success(
//															function(datagetProduit) {
//																$log.debug("---TissuId : " + datagetProduit.id);
//																$log.debug("---TissuReference : " + datagetProduit.reference);
//																$log.debug("---SousFamilleId : " + datagetProduit.sousFamilleId);
//																$log.debug("---SousFamilleDesignation : " + datagetProduit.sousFamilleDesignation);
//
//																//affectation de sousFamilleProduit et referenceProduit au BonReceptionselectionné
//																$scope.resultatRechercheBonReception.produit = datagetProduit.id;
//																$scope.resultatRechercheBonReception.reference = datagetProduit.reference;
//																$scope.resultatRechercheBonReception.sousFamille = datagetProduit.sousFamilleDesignation
//																$scope.resultatRechercheBonReception.composition = datagetProduit.composition;
//															});
//												}else{
//													$log.debug("idProduit is null");
//												}
//												
//												//dateLivraison
//												$log.debug("****DateLivraison : " + datagetBonReception.dateLivraison);
//												$scope.resultatRechercheBonReception.dateLivraison = datagetBonReception.dateLivraison;
//												
//												// IdClient => Designation (Methode qui utilise le 'filtre' de Angular)
//												$scope.clientId.status = datagetBonReception.partieInteressee;
//												var selected = $filter('showClientFilter')($scope.listeClient,{id : $scope.clientId.status});
//														
//												if ($scope.clientId.status && selected.length) {
//													$scope.resultatRechercheBonReception.partieInteressee = selected[0].id;
//													$scope.resultatRechercheBonReception.clientAbreviation = selected[0].abreviation;
//													$log.debug("clientAbreviation"
//																			+ $scope.resultatRechercheBonReception.clientAbreviation);
//												} else {
//													$log.debug("NotSet Client");
//												}						
//												
//												//metrageAnnoncé
//												$log.debug("****metrage : "+ datagetBonReception.metrageAnnonce);
//												$scope.resultatRechercheBonReception.metrageAnnonce = datagetBonReception.metrageAnnonce;
//												
//											});
//								}else{
//									$log.debug("idBonReception is null");
//								}
//								
//
//							}
							
							/***************************************************
							 * Gestion des mise
							 **************************************************/

							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 ],
								pageSize : 10,
								currentPage : 1
							};

							// Lister les Mises
							$scope.rechercherMise = function(miseCouranteRecherche) {
								$http
										.post(
												UrlAtelier
														+ "/mise/rechercheMiseMulticritere",
												miseCouranteRecherche)
										.success(
												function(resultat) {
													$scope.myData = resultat.listeElementRechecheMiseValeur;
													// Pagination du resultat de
													// recherche
													// data, page,pageSize
													$scope
															.setPagingData(
																	$scope.myData,
																	$scope.pagingOptions.currentPage,
																	$scope.pagingOptions.pageSize);

													console
															.log("listeMise : "
																	+ resultat.listeElementRechecheMiseValeur.length);
													$scope.rechercheMiseFormCritere
															.$setPristine();
												});

							}

							// annuler Ajout
							$scope.annulerAjout = function() {
								//modeCreation Actif
								$scope.creation = true;
								$scope.modePdf = "notActif";
								$scope.resultatRechercheBonReception = [];
								$scope.id = {};
								$scope.metrageTrouve = false;
								$scope.miseCourante = {};
								$scope.miseCouranteRecherche = {};
								$scope.listeDetails = [];
								$scope.listeTraitements = [];
								$scope.rechercherMise({});
								$scope.creationMise.$setPristine();
								$scope.rechercheMiseFormCritere.$setPristine();
								$scope.displayMode = "list";
								
							 	 $scope.checkboxModel= {
							 			 isStitClient : false
							 	 };
							}

							// Ajout Partie Interesse **
							$scope.AffectationMise = function(mise) {
								$scope.miseCourante = {};
								$scope.creationMise.$setPristine();
								$scope.displayMode = "list";
							}

							// Ajout et Modification d'un bon de reception
							$scope.modifierOuCreerMise = function() {

								// mode Modification/Condltation Actif
								$scope.creation = false;
								$scope.modePdf = "actif";
								
								var index = this.row.rowIndex;
								$log.debug("Id Mise : " + $scope.myData[index].idMise);

								// getMise
								$http
										.get(UrlAtelier + "/mise/getMiseId:"
												+ $scope.myData[index].idMise)
										.success(function(dataGetMise) {
													
													$scope.checkboxModel.isStitClient = dataGetMise.fini;
													
													//Bonreception
													$log.debug("***BR Reference : " + dataGetMise.refBonreception);
													$scope.resultatRechercheBonReception.refBonreception = dataGetMise.refBonreception;
													//dateLivraison
													/*$log.debug("****DateLivraison : " + dataGetMise.dateLivraison);
													$scope.resultatRechercheBonReception.dateLivraison = dataGetMise.dateLivraison;
													*/
													//Produit
													$log.debug("***TissuId : " + dataGetMise.produitId);
													$scope.resultatRechercheBonReception.produitId = dataGetMise.produitId;
													//Client
													$log.debug("***ClientId : " + dataGetMise.partieintId);
													$scope.resultatRechercheBonReception.partieintId = dataGetMise.partieintId;
													
													/**conversion Produit & sousFamilleProduit **/
												    /*Methode 1: utilise le service getId pour convertir le produit avec l'Id x à sa propre reference
													 */
													if(dataGetMise.produitId!= null){
														$http
														.get(
																UrlCommun
																		+ "/produit/getId:"+ dataGetMise.produitId)
														.success(
																function(datagetProduit) {
																	$log.debug(" **TissuId : " + datagetProduit.id);
																	$log.debug(" **TissuReference : " + datagetProduit.reference);
																	$log.debug(" **SousFamilleId : " + datagetProduit.sousFamilleId);
																	$log.debug(" **SousFamilleDesignation : " + datagetProduit.sousFamilleDesignation);

																	//affectation de sousFamilleProduit et referenceProduit au BonReceptionselectionné
																	$scope.resultatRechercheBonReception.produit = datagetProduit.id;
																	$scope.resultatRechercheBonReception.reference = datagetProduit.reference;
																	$scope.resultatRechercheBonReception.sousFamille = datagetProduit.sousFamilleDesignation;
																	$scope.resultatRechercheBonReception.composition = datagetProduit.composition;
																	
																});
													}else{
														$log.debug("aucun Produit n'est affecté à ce BonReception");
													}
													
													/** conversion Client **/
													/* Methode2: utilise le Filtre de Angular pour faire la conversion des Id en leurs propre designations
													 * tout en recuperant la liste de les clients de la BD. (TODO recuperation de la cache des listes clients et Produit)
													 */
													if(dataGetMise.partieintId != null){
																											
														$scope.clientId.status = dataGetMise.partieintId;
														var selected = $filter('showClientFilterMise')
																		($scope.listeClient,{id : $scope.clientId.status});
																
														if ($scope.clientId.status && selected.length) {
															$scope.resultatRechercheBonReception.partieInteressee = selected[0].id;
															$scope.resultatRechercheBonReception.clientAbreviation = selected[0].abreviation;
															$log.debug(" ** clientAbreviation : " + $scope.resultatRechercheBonReception.clientAbreviation);
														} else {
															$log.debug("NotSet Client");
														}	
													}else{
														$log.debug("aucun Client n'est affecté à ce BonReception");
													}
													
													// Attributs de Recherche
													$scope.miseCourante = dataGetMise ? angular
															.copy(dataGetMise)
															: {};
													$scope.displayMode = "list";
												});

							}
							
							

							// Sauvegarder bon de reception
							$scope.sauvegarderAjout = function(mise) {
								
								mise.fini = $scope.checkboxModel.isStitClient;
								
								if (angular.isDefined(mise.id)) {
									$log.debug("Sauvegarder Modification"+ mise);
									$scope.modifierMise(mise);
								} else {
									console
											.log("**Sauvegarder Ajout"
													+ mise.id);
									
									$scope.creerMise(mise);
								}
								$scope.miseCourante = {};
								
							 	 $scope.checkboxModel= {
							 			 isStitClient : false
							 	 };
								$scope.rechercherMise($scope.miseCourante);
							}

							// Création Mise
							$scope.creerMise = function(mise) {
								//Affectation des attributs recherchés par le BR
								//referenceBonReception
							//	mise.refBonreception =  $scope.resultatRechercheBonReception.refBonreception;
								//DateIntro
								mise.dateIntroduction = new Date();
								//Tissu
								//$log.debug("--TissuId "+ $scope.resultatRechercheBonReception.produit);
								//mise.produitId = $scope.resultatRechercheBonReception.produit;
								//Client
								$log.debug("--ClientId "+ $scope.resultatRechercheBonReception.partieInteressee);
								//mise.partieintId = $scope.resultatRechercheBonReception.partieInteressee;
								
								$http
										.post(UrlAtelier + "/mise/creerMise",
												mise)
										.success(
												function(idMise) {
													console
															.log("Success Creation : id= "
																	+ idMise);
													$scope.annulerAjout();
												});

							}

							// modifier bon de reception
							$scope.modifierMise = function(mise) {
								$http
										.post(UrlAtelier + "/mise/modifieMise",
												mise)
										.success(
												function(miseModifiee) {

													for (var i = 0; i < $scope.myData.length; i++) {

														if ($scope.myData[i].id == miseModifiee.id) {

															$scope.myData[i] = miseModifiee;
															break;
														}
													}
													
													//get mise by id
													
													
															
													$scope.annulerAjout();

												});

							}

							// Suppression d'une mise
							$scope.supprimerMise = function() {
								$log.debug("DELETE l'index : "
										+ $scope.myData[$scope.index].idMise);
								$http(
										{
											method : "DELETE",
											url : UrlAtelier
													+ "/mise/supprimerMise:"
													+ +$scope.myData[$scope.index].idMise
										}).success(function() {
									$log.debug("Success Delete");
									$scope.myData.splice($scope.index, 1);
								});
								$scope.myData.splice($scope.index, 1);
								$scope.closePopupDelete();
								$scope.annulerAjout();
							}

							/** ** PDF ** */
							$scope.download = function(id) {
								$log.debug("-- id" + id);
								// TODO URL
								var url = "";
								downloadService.download(url)
										.then(
												function(success) {
													$log.debug('success : '
															+ success);
												},
												function(error) {
													$log.debug('error : '
															+ error);
												});
							};

							// print Bon Reception methode Windows.open
							// javascript
							$scope.openWin = function() {
								var width = screen.width;
								var cssHead = '<head><meta charset="utf-8"><link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="css/style.css"/></head>';
								var tableData = cssHead
										+ "<br><img src='images/logoSTIT.png' width='150px'/><br><br><table width="
										+ width
										+ " border='0' style='font-size:30;'>"
										+ document.querySelector('#refBon').innerHTML
										+ '</table><br><br><div class="spacer"></div><table width="'
										+ width
										+ '" border="1" style="font-size:25;text-align:center">'
										+ document
												.querySelector('#tableListeMetrage').innerHTML
										+ '</table>';
								// var tableData = '<h1>STIT</h1>'
								// +'<table width="' + width
								// + '" border="1">'
								// +
								// '<tr><th>Client</th><th>Tissu</th><th>Type</th><th>Nombre
								// de Roul</th></tr>'
								// +
								// '<tr><td>Mohamed</td><td>Belaid</td><td>Type1</td><td>2</td></tr>'
								// + '</table>';
								var myWindow = window.open('', '',
										'width=auto, height=auto');
								// myWindow.innerWidth = screen.width;
								// myWindow.innerHeight = screen.height;
								// myWindow.screenX = 0;
								// myWindow.screenY = 0;

								myWindow.document.write(tableData);
								myWindow.print();

							};

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
															field : 'reference',
															displayName : 'Ref',
														},
														{
															field : 'abreviationClientDesignation',
															displayName : 'Client'
														},
														{
															field : 'referenceProduit',
															displayName : 'Ref.Produit'
														},
														{
															field : 'dateIntroduction',
															displayName : 'dateIntro',
															cellFilter: 'date:"dd-MM-yyyy"',
														},
														
														{
															field : '',
															cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerMise()">	<i class="fa fa-fw fa-pencil"></i></button>'
																	+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(1)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
											var miseCouranteRecherche = $scope.miseCouranteRecherche;
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																UrlAtelier
																		+ "/mise/rechercheMiseMulticritere",
																miseCouranteRecherche)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.listeElementRechecheMiseValeur
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
																		+ "/mise/rechercheMiseMulticritere",
																miseCouranteRecherche)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.listeElementRechecheMiseValeur,
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
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};

							/** Fin de gestion des Grids de la partie Interesse */
						} ])
		.filter('showClientFilterMise', function() {
		  return function(listeClient, id) {
			  var listeClientFiltre = [];
			// $log.debug("IdClient=  "+id.id);
			 angular.forEach(listeClient, function(client, key){
				
				if(client.id == id.id){
					listeClientFiltre.push(client);
				}
					
			 });
			// $log.debug("listeClientFiltre "+ JSON.stringify(listeClientFiltre, null, "    "));
			 return listeClientFiltre;
		  };
		})	
		.controller(
				'DatepickerDController',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
							// // date piker defit
							// $scope.today = function() {
							// $scope.miseCourante.dateIntroduction
							// = new Date();
							// };
							// $scope.today();
							// $scope.clear = function() {
							// $scope.miseCourante.dateIntroduction
							// = null;
							// };
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
							$scope.initDate = new Date('20-08-2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])

		.controller('TimeCtrl', function($scope, $interval) {
			var tick = function() {
				$scope.clock = Date.now();
			}
			tick();
			$interval(tick, 1000);
		});
