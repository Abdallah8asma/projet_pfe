'use strict'

angular
		.module('atelier.suivieOF', [ "ngResource", "ngCsvImport" ])
		.controller(
				'suivieOFController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'$parse',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						'$window',
						function($scope, $filter, $http, $log, $parse,
								downloadService, UrlCommun, UrlAtelier,$window) {

						
							
							    var vm = this;
							  
						    vm.date = new Date();
						    vm.options = '{format:"DD.MM.YYYY HH:mm"}'
								
								
							$log
									.info("=========  Inventaire controller ========");

							// Déclaration des variables globales utilisés
							$scope.today = new Date();
							var data;
							$scope.displayMode = "list";
							//bouton pdf hide
							$scope.modePdf = "notActive";
							$scope.bonInventaireCourant = null;
							//init modification Metrage du rouleau
							$scope.isModifie = false;
							$scope.listeRouleauFini = [];
							$scope.listSousFamilleProduitCache = [];
							$scope.listeProduit = [];
							$scope.listeRefMiseREF = [];
							$scope.listeCodeBarreFromMise = [];
							$scope.nbRouleauFiniRestant = 0;
							$scope.refrenceMise = '';
							$scope.listeMachines=[];
						    $scope.listeFilterMachines= [];
							$scope.listeFiltredProduit = [];
							
							//init urlValider
							$scope.urlValider = "";
							//Pavet SolderMise ne s'affiche pas au demarrage de la page
							$scope.isCollapsed = true;
							/********************************
							 * Gestion des listes deroulantes*
							 * à changer par Cache
							 ********************************/
							$scope.changeProduits = function(id){
								
								var element = $scope.listeProduit.filter(function(node) {
							        return node.id==id;
							    });
								$scope.miseCourant.partieintId =  element[0].partieIntersseId;
								$scope.miseCourant.partieIntersseDesignation =  element[0].partieIntersseDesignation;
								$scope.miseCourant.destinationProduit =  element[0].designation;
								$scope.miseCourant.produitId = element[0].id;
								$scope.miseCourant.referenceProduit = element[0].reference ;
								$scope.miseCourant.poidFini = element[0].qtyBox;
							}
							// Liste des PartieInteressee (avec FamilleId=1)
							$scope.listeClientCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPartieInteressee) {

													$scope.listeClientCache = dataPartieInteressee;
												});
							}

							// Liste des Produits
							$scope.listeProduit = function() {
								$http
										.get(UrlCommun + "/produit/all")
										.success(
												function(data) {
													$log
															.debug("listeProduitCache "
																	+ data.length);
													$scope.listeProduit = data;
													// Liste SousFamilleProduitCache
													$http
															.get(
																	UrlCommun
																			+ "/gestionnaireCache/listeSousFamilleProduitCache")
															.success(
																	function(
																			dataSousFamilleProduitCache) {
																		$log
																				.debug(" listeSousFamilleProduitCache : "
																						+ dataSousFamilleProduitCache.length);
																		$scope.listSousFamilleProduitCache = dataSousFamilleProduitCache;

																	});

												});
							}

							
							
							// Liste Machines
                            $scope.listeMachines = function() {
                              $http
                                  .get(UrlAtelier + "/gestionnaireLogistiqueCache/listeMachineCache")
                                  .success(
                                      function(data) {
                                        //$log("Machines : " + data.length);
                                        $scope.listeMachines = data;

                                      });
                            }
							
							
							
									$scope.onChangeOfType= function (typeOF) {
					//$scope.listeMachines 
					console.log('AppelSam : filterMachine : ', typeOF);
					var items = $scope.listeMachines;
					var filtered = [];
			
					
					if (typeOF === undefined || typeOF == null) {
						return items;
					}
					angular.forEach(items, function (item) {
						if (typeOF == item.version) {
							filtered.push(item);
						}
					});
					$scope.listeFilterMachines = filtered;

					console.log('liste filtred machine : ',filtered);
				

					var itemsProduits = $scope.listeProduit;
					var filtredProduct=[];

					var ofType=typeOF;

					if(ofType=='Injection') ofType='INJECTED';
					if(ofType=='Tempographie') ofType='PAINTED';
					if(ofType=='Assemblage') ofType='Assembled';
					if(ofType=='Spread') ofType='SPRAYED';

					console.log('ofType::: ',ofType);

					angular.forEach(itemsProduits, function (item) {
						if (ofType == item.sousFamilleDesignation) {
							filtredProduct.push(item);
						}
					});

                   $scope.listeFiltredProduit=filtredProduct;

				
					return $scope.listeFilterMachines;

				};
							
							
							
							
                        	$scope.listeMachines();
							$scope.listeProduit();
							$scope.listeClientCache();
							$scope.listCode = [];
							$scope.nbrColis = 0;
							$scope.modeValider = false;
							// Ajout Partie Interesse **
							$scope.affectationMise = function(mise) {
								$scope.miseCourante = {};
								$scope.displayMode = "edit";
								$scope.modePdf = "actif";
								
							}
							//Choix

							/***************************************************
							 * Gestion des Rouleaux
							 **************************************************/

							// Sauvegarder bon de reception
							$scope.sauvegarderAjout = function(mise) {
								console.log("mise avant envoie :",mise);


								if (angular.isDefined(mise.id)) {
									$log.debug("Sauvegarder Modification"
											+ mise);
									$scope.modifierMise(mise);
								} else {
									console
											.log("**Sauvegarder Ajout"
													+ mise.id);

									$scope.creerMise(mise);
								}
								$scope.miseCourante = {};

								$scope.checkboxModel = {
									isStitClient : false
								};
								$scope.rechercherMise($scope.miseCourante);
							}

							// Création Mise
							$scope.creerMise = function(mise) {

								mise.dateIntroduction = new Date();
								$http
										.post(UrlAtelier + "/mise/creerMise",
												mise).success(function(idMise) {
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
								var index = this.row.rowIndex
								$http(
										{
											method : "DELETE",
											url : UrlAtelier
													+ "/mise/supprimerMise:"
													+ +$scope.myData[index].idMise
										}).success(function() {
									$log.debug("Success Delete");
									$scope.myData.splice($scope.index, 1);
								});
								$scope.myData.splice($scope.index, 1);
								$scope.closePopupDelete();
								$scope.annulerAjout();
							}
							
							
							
							 $scope.downloadFicheOf = function(objectCourant) {
								var url;

								url = UrlAtelier + "/report/mise?reference=" + objectCourant.reference
									 					 + "&referenceProduit=" + objectCourant.referenceProduit
														 + "&destinationProduit="+objectCourant.destinationProduit
														 + "&partieintId="+objectCourant.partieintId
														 + "&partieIntersseDesignation="+objectCourant.partieIntersseDesignation
														 + "&dateIntroduction="+objectCourant.dateIntroduction
														 + "&dateFin="+objectCourant.dateFin
														 + "&quantite="+objectCourant.quantite
														 + "&statut="+objectCourant.statut
														 + "&observations="+objectCourant.observations
														 + "&id="+objectCourant.id
													 
														 + "&type=pdf";
									
				                   $log.debug("-- URL--- :" + url );
								/* downloadService.download(url).then(
										 function(success) {
											// $log.debug('success : ' + success);
										 }, function(error) {
											// $log.debug('error : ' + error);
										 });*/



										 var a = document.createElement('a');
										 document.body.appendChild(a);
										 downloadService.download(url).then(function (result) {
											 var heasersFileName = result.headers(['content-disposition']).substring(17);
										 var fileName = heasersFileName.split('.');
									 var typeFile = result.headers(['content-type']);
									 var file = new Blob([result.data], {type: typeFile});
									 var fileURL = window.URL.createObjectURL(file);
									 if(typeFile == 'application/vnd.ms-excel'){
			 
									  // a.href = fileURL;
										  a.download = fileName[0];
										 $window.open(fileURL)
										  a.click();
					 
									 }else{
								 
										 a.href = fileURL;
										 a.download = fileName[0];
									  $window.open(fileURL)
										 a.click();
					 
									 }
										 
									 $scope.traitementEnCoursGenererLivraison="false";
 
									 });


							 };
							 
							 $scope.generateReport= function (objectCourant)
							 {
									var url;

									url = UrlAtelier + "/fichesLogistique/suivi-of?state=" + objectCourant.statut
										 					 + "&type=" + objectCourant.type
															 + "&num="+objectCourant.referenceOF
															
															 
															 + "&dateLivDe="+objectCourant.dateLivraisonDE
															 + "&dateLivA="+objectCourant.dateLivraisonA
															 
															 + "&dateIntroDe="+objectCourant.dateIntroductionDE
															 + "&dateIntroA="+objectCourant.dateIntroductionA
															 
															 + "&dateDebutProdDe="+objectCourant.dateDebutProductionDe
															 + "&dateDebutProdA="+objectCourant.dateDebutProductionA
															 
															 + "&dateFinProdDe="+objectCourant.dateFinProductionDe
															 + "&dateFinProdA="+objectCourant.dateFinProductionA
															 
															 + "&etatProduced="+objectCourant.etatProduced
															 + "&etatShipped="+objectCourant.etatShipped 
															 + "&machine="+objectCourant.machine 
														
															     + "&client="+objectCourant.client 
									                         + "&produitId="+objectCourant.produitId ;
														
										
					                   $log.debug("-- URL--- :" + url );
								/*	 downloadService.download(url).then(
											 function(success) {
												// $log.debug('success : ' + success);
											 }, function(error) {
												// $log.debug('error : ' + error);
											 });*/



											 var a = document.createElement('a');
											 document.body.appendChild(a);
											 downloadService.download(url).then(function (result) {
												 var heasersFileName = result.headers(['content-disposition']).substring(17);
											 var fileName = heasersFileName.split('.');
										 var typeFile = result.headers(['content-type']);
										 var file = new Blob([result.data], {type: typeFile});
										 var fileURL = window.URL.createObjectURL(file);
										 if(typeFile == 'application/vnd.ms-excel'){
				 
										  // a.href = fileURL;
											  a.download = fileName[0];
											 $window.open(fileURL)
											  a.click();
						 
										 }else{
									 
											 a.href = fileURL;
											 a.download = fileName[0];
										  $window.open(fileURL)
											 a.click();
						 
										 }
											 
										 $scope.traitementEnCoursGenererLivraison="false";
	 
										 });
							 }

							/** Fin de gestion des Rouleaux */
							/***************************************************
							 * Gestion des Bons de Sortie
							 **************************************************/

							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 , 65 , 113 ],
								pageSize : 65,
								currentPage : 1
							};

							// Lister les inventaires
							$scope.rechercherMise = function(
									miseCouranteRecherche) {
								//console.log(miseCouranteRecherche);
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

													

													$scope.rechercheMiseForm
															.$setPristine();
												});

							}

							// Ajout et Modification d'un bon de reception
							$scope.modifierOuCreerMise = function() {
								$scope.listeFilterMachines=$scope.listeMachines;
					            $scope.listeFiltredProduit=$scope.listeProduit ;
				
								// mode Modification/Condltation Actif
								$scope.displayMode = "edit";
								$scope.modePdf = "actif";
								
								var index = this.row.rowIndex;
								$log.debug("Id Mise : " + $scope.myData[index].idMise);

								// getMise
								$http
										.get(UrlAtelier + "/mise/getMiseId:"
												+ $scope.myData[index].idMise)
										.success(function(dataGetMise) {
											
											console.log('DataGetMise ::::::::::',dataGetMise);
													
													$scope.miseCourant = dataGetMise;
											
													
													//$scope.dateIntro=
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

																	
																	$scope.miseCourant.partieintId =  datagetProduit.partieIntersseId;
																	$scope.miseCourant.partieIntersseDesignation =  datagetProduit.partieIntersseDesignation;
																	$scope.miseCourant.destinationProduit =  datagetProduit.designation;
																	$scope.miseCourant.produitId = datagetProduit.id;
																	
																//	$scope.miseCourant.quantiteByBox = datagetProduit.qtyBox;
																});
													}else{
														$log.debug("aucun Produit n'est affecté à ce BonReception");
													}
													
													$scope.miseCourante = dataGetMise ? angular
															.copy(dataGetMise)
															: {};
												});

							}
							// annuler Recherche
							$scope.annulerAjout = function() {
								$scope.modePdf = "notActive";
								$scope.miseCourant = {
									"reference" : "",
									"type" : "",
									"partieInt" : null,
									"dateSortieMin" : "",
									"dateSortieMax" : "",
									 "reference": "",
									 "metrage": "",
									 "poids": "",
									"refBonreception": "",
									"dateIntroduction": "",
									" observations": "",
									"codeBarre": "",
									"produitId": "",
									"partieintId": "",
									 "poidFini": "",
									"quantite": "",
									 "destinationProduit": "",
									 "statut": "",
									"dateFin": "",
								};

								$scope.nbrColis = 0;
								$scope.listCode = [];
								$scope.modeValider = false;
								$scope.listeRouleauFini = [];
								$scope.codeBarre = "";
								$scope.idbonInventaire = '';
								//Pavet SolderMise ne s'affiche pas au demarrage de la page
								$scope.isCollapsed = true;
								$scope.rechercherMise({});
								$scope.rechercheMiseForm
										.$setPristine();
								$scope.creationMise.$setPristine();
								$scope.displayMode = "list";
								$scope.displayView = "view1";
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
												                  
                                                /*       {
                                                    	   	field : 'typeOF',
                                                    	   	displayName : 'type',
                                                    	   	width : '8%'
                                                       },
                                                            {
                                                    	   	field : 'machine',
                                                    	   	displayName : 'Machine',
                                                    	   	width : '4%'
                                                       },*/
												                  
														{
															field : 'reference',
															displayName : 'NUMERO',
															width : '7%'
														},
														{
															field : 'statut',
															displayName : 'Statut',
															width : '7%'
														},
														{
															field : 'referenceProduit',
															displayName : 'Reference',
															width : '6%'
														},
														{
															field : 'destinationProduit',
															displayName : 'Description',
															width : '14%'
														},
														{
															field : 'dateDebutProduction',
															displayName : 'D.Deb. Prod.',
															cellFilter : 'date:"dd-MM-yyyy HH:mm:ss"',
															width : '10%'
														},
														{
															field : 'dateFinProduction',
															displayName : 'D.Fin Prod.',
															cellFilter : 'date:"dd-MM-yyyy HH:mm:ss"',
															width : '10%'
														},
														{
															field : 'dateFin',
															displayName : 'Date Fin Calc.',
															cellFilter : 'date:"dd-MM-yyyy HH:mm:ss"',
															width : '10%'
														},
														{
															field : 'quantite',
															displayName : 'Qte',
															width : '6%'
														},
														{
															field : 'qteProduite',
															displayName : 'Qte Prod.',
															width : '6%'
														},
														{
															field : 'qteExpedition',
															displayName : 'Qte Exp.',
															width : '6%'
														},
														{
															field : 'nbrColis',
															displayName : 'Packages',
															width : '6%'
														},
														{
															field : 'nbrColisExpedition',
															displayName : 'Nbr Col. Exp.',
															width : '6%'
														},	{
															field: '',
															//width: '5%',
															cellTemplate:
																`<div class="ms-CommandButton float-right"  ng-show="!rowform.$visible" >
																<button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerMise()">
															<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
															</button>
																<button class="ms-CommandButton-button"  ng-click="supprimerMise()" permission="['Production_OF_Delete']">
																<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
																</button>
																</div>`,
						
						                                } ];
											});

							$scope.totalServerItems = 0;

							/***************************************************
							 * Gestion des Grids de recherche
							 **************************************************/
							$scope.colDefs = [];

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
											var miseCouranteRecherche = $scope.miseCourant;
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
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};

							/** Fin de gestion des Grids de la bonInventaire */

						} ])
		.filter('showProduitFilterBS', function() {
			return function(listeProduit, id) {
				var listeProduitFiltre = [];
				// $log.debug("IdProduitt=  "+id.id);
				// $log.debug("listeProduit "+ JSON.stringify(listeProduit, null, "    "));
				angular.forEach(listeProduit, function(produit, key) {

					if (produit.id == id.id) {
						//$log.debug(produit.id +" == "+ id.id);
						listeProduitFiltre.push(produit);
					}

				});
				// $log.debug(" ** listeProduitFiltre "+ JSON.stringify(listeProduitFiltre, null, "    "));
				return listeProduitFiltre;
			};
		})
		.filter('filterSousFamilleBS', function() {
			return function(listeSousFamille, id) {
				var listeSousFamilleFiltre = [];
				// $log.debug("IdSousFamille=  "+id.id);
				// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
				angular.forEach(listeSousFamille, function(sousFamille, key) {

					if (sousFamille.id == id.id) {
						//$log.debug(sousFamille.id +" == "+ id.id);
						listeSousFamilleFiltre.push(sousFamille);
					}

				});
				// $log.debug(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
				return listeSousFamilleFiltre;
			};
		})
		.directive(
				'datepickerLocalDate',
				[
						'$parse',
						function($parse) {
							var directive = {
								restrict : 'A',
								require : [ 'ngModel' ],
								link : link
							};
							return directive;

							function link(scope, element, attr, ctrls) {
								var ngModelController = ctrls[0];

								// called with a JavaScript Date object when picked from the datepicker
								ngModelController.$parsers.push(function(
										viewValue) {
									// undo the timezone adjustment we did during the formatting
									viewValue.setMinutes(viewValue.getMinutes()
											- viewValue.getTimezoneOffset());
									// we just want a local date in ISO format
									return viewValue.toISOString().substring(0,
											10);
								});

								// called with a 'yyyy-mm-dd' string to format
								ngModelController.$formatters.push(function(
										modelValue) {
									if (!modelValue) {
										return undefined;
									}
									// date constructor will apply timezone deviations from UTC (i.e. if locale is behind UTC 'dt' will be one day behind)
									var dt = new Date(modelValue);
									// 'undo' the timezone offset again (so we end up on the original date again)
									dt.setMinutes(dt.getMinutes()
											+ dt.getTimezoneOffset());
									return dt;
								});
							}
						} ])

		.controller(
				'DateIntroCtrl',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
							// date piker defit
							//              					$scope.today = function() {
							//              						$scope.articleCourante.dateIntroduction = new Date();
							//              					};
							//              					$scope.today();
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
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])
