'use strict'
angular
		.module('gpro.partieInteressee', [ "ngResource" ])
		.controller(
				'PartieInteresseeController',
				[
						'$scope',
						'$filter',
						'$http',
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "list";
							$scope.displayMenu = "pi";
							$scope.listeRepresentant = [];
							$scope.listeDocument = [];
							$scope.ListCategoriePICache = [];
							$scope.ListTypePICache = [];
							$scope.ListFamillePICache = [];
							$scope.ListSitePICache = [];
							$scope.ListActifPI = [{actif:true,designation:'oui'},{actif:false,designation:'non'}];
							$scope.partieInteresseeCourante = {};
							$scope.resultatRecherche = $scope.listePartieInteressee;
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

							/***************************************************
							 * Gestion Cache
							 */
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
							// Liste des TypeDocs
						       $scope.listeTypeDocumentCache = function() {
						        $http
						          .get(
						            baseUrl
						              + "/gestionnaireCache/listeTypeDocumentCache")
						          .success(
						            function(dataTypeDocCache) {
						             console
						               .log("*listeTypeDocumentCache :"
						                 + dataTypeDocCache);
						             $scope.listeTypeDocumentCache = dataTypeDocCache;
						            });
						       }
						       $scope.ListDeviseCache();
						       $scope.listeTypeDocumentCache();
							/***************************************************
							 * Gestion de la PI
							 **************************************************/
						   	$scope.deleteValue="oui";
							  //Annuler Ajout
						    $scope.cancelAddPartieInteressee = function(rowform, index, id, designation, liste){
						    	//console.log("* Designation "+liste[0].designation);
						    		  if (angular.isDefined(id)) {
						    			 
						    				  console.log("DEF ID");
						    				  $scope.deleteValue="non";
						    				  rowform.$cancel();
						    				  console.log("CANCEL");
						    		  }else{	
						    			  console.log("UND ID  "+id);
						    			  if(designation ==""){
						    				  $scope.deleteValue=="oui"
						    				  console.log("Designation : "+designation);
						    				  console.log("$scope.deleteValueOUI : "+$scope.deleteValue);
						    				  liste.splice(index, 1);
									    	  rowform.$cancel();
						    				  console.log("DELETE")
						    			  }else{
						    				  console.log("Designation :"+designation);
						    				  console.log("$scope.deleteValueNON : "+$scope.deleteValue);
						    				  rowform.$cancel();
						    				  console.log("CANCEL");
						    			  }
						    		}
						    		  $scope.deleteValue="oui";
						    }
						    
						
							// Lister les parties interessées
							$scope.listePartieInteressee = function() {
								$http.get(baseUrl + "/partieInteressee/all")
										.success(function(data) {
											$scope.partieInteresssees = data;
											$scope.myData = data;
										});
							}
							// Rechercher PI
							$scope.rechercherPartieInteressee = function(
									partieInteresseeCourante) {
								$http
										.post(
												baseUrl
														+ "/partieInteressee/recherchePIMulticritere",
												partieInteresseeCourante)
										.success(
												function(resultat) {
													$scope.myData = resultat.partieInteresseValues;
													$scope.displayMode = "rechercher";
													$scope.recherchePartieIntereseeFormCritere
															.$setPristine();
												});

							}
							// ** Ajout Partie Interesse **
							$scope.AffectationPartieInteressee = function(
									partieInteressee) {
								$scope.partieInteresseeCourante = {};
								$scope.creationPartieInteressee.$setPristine();
								$scope.listeRepresentant = [];
								$scope.listeDocument = [];
								$scope.displayMode = "edit";
							}
							// Ajout et Modification Partie Interessee
							$scope.modifierOuCreerPartieInteressee = function() {
								var index = this.row.rowIndex;
								// getArticle
								$http
										.get(
												baseUrl
														+ "/partieInteressee/getId:"
														+ $scope.myData[index].id)
										.success(
												function(datagetPI) {
													$scope.listeRepresentant = datagetPI.representants;
													$scope.listeDocument = datagetPI.documents;
													$scope.myData[index].representants = $scope.listeRepresentant;
													$scope.myData[index].documents = $scope.listeDocument;
													$scope.partieInteresseeCourante.actif=datagetPI.actif.toString();
//													if(datagetPI.actif == true){
//														$scope.partieInteresseeCourante.actif='oui';
//														$scope.ListActifPI.actif=true; 
//													}else{
//														$scope.partieInteresseeCourante.actif='non';
//														$scope.ListActifPI.actif=false;
//													}
													console.log("$scope.myData[index].actif : "+$scope.myData[index].actif);
												});
								$scope.partieInteresseeCourante = $scope.myData[index] ? angular
										.copy($scope.myData[index])
										: {};

								$scope.displayMode = "edit";
							}
							// Sauvegarder PI
							$scope.sauvegarderAjout = function(partieInteressee) {
								if (angular.isDefined(partieInteressee.id)) {
									$scope
											.updatePartieInteressee(partieInteressee);
								} else {
									$scope
											.creerPartieInteressee(partieInteressee);
								}
								$scope.rechercherPartieInteressee({});
							}
							// Mise à jour de la partie interessée
							$scope.updatePartieInteressee = function(
									partieInteressee) {
								partieInteressee.representants = $scope.listeRepresentant;
								partieInteressee.documents = $scope.listeDocument;
								$http
										.post(
												baseUrl
														+ "/partieInteressee/modifierPartieInteressee",
												partieInteressee)
										.success(
												function(
														partieInteresseeModifiee) {
													for (var i = 0; i < $scope.listePartieInteressee.length; i++) {
														if ($scope.myData[i].id == partieInteresseeModifiee.id) {
															$scope.myData[i] = partieInteresseeModifiee;
															break;
														}
													}
													$scope.displayMode = "list";
												});
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.creationPartieInteressee.$setPristine();
								$scope.partieInteresseeCourante = {};
							}
							// Création PI
							$scope.creerPartieInteressee = function(
									partieInteressee) {
								partieInteressee.representants = $scope.listeRepresentant;
								partieInteressee.documents = $scope.listeDocument;
								$http
										.post(
												baseUrl
														+ "/partieInteressee/creerPartieInteressee",
												partieInteressee)
										.success(
												function(newPartieInteressee) {
													$scope.myData
															.push(newPartieInteressee);
													$scope.displayMode = "list";
												});
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.creationPartieInteressee.$setPristine();
								$scope.partieInteresseeCourante = {};
							}

							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.partieInteresseeCourante = {};
								$scope
										.rechercherPartieInteressee($scope.partieInteresseeCourante);
								$scope.listeRepresentant = [];
								$scope.listeDocument = [];
								$scope.displayMode = "list";
							}
							// Suppression PI
							$scope.supprimerPartieInteressee = function() {
								console.log("DELETE .."
										+ $scope.myData[$scope.index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/partieInteressee/supprimerPartieInteressee:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
								});
								$scope.closePopupDelete();
								$scope.listePartieInteressee();
							}
							$scope.listePartieInteressee();

							/** Fin de gestion de la partie Interessée */

							/***************************************************
							 * Gestion des representants
							 **************************************************/
							// ajout d'un Representant
							$scope.ajoutRepresentant = function() {
								$scope.representantInserree = {
									nom : '',
									fonction : null,
									email : null
								};
								$scope.listeRepresentant
										.push($scope.representantInserree);

							};

							// Enregistrer Representant
							$scope.saveRepresentant = function(data, id) {
								$scope.deleteValue = "non";
								var regExpValidEmail = new RegExp(
										"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$",
										"gi");
								// $scope.Representant not updated yet
								if (!data.email
										|| !data.email.match(regExpValidEmail)) {
									$scope.showPopupDelete(2);
									$scope.rowform.$cancel();
									return;
								} else {
									angular.extend(data, {
										id : id
									});
								}
							};

							// Supprimer representant
							$scope.removeRepresentant = function(index) {
								$scope.listeRepresentant.splice(index, 1);
							};
							/** Fin de gestion des representants */

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
															displayName : 'Réf.PI',
														},
														{
															field : 'raisonSociale',
															displayName : 'Raison Sociale'
														},
														{
															field : 'famillePartieInteresseeDesignation',
															displayName : 'Famille PI'
														},
														{
															field : 'categoriePartieInteresseeDesignation',
															displayName : 'Catégorie PI'
														},
														{
															field : 'typePartieInteresseeDesignation',
															displayName : 'Type PI'
														},
														{
															field : 'telephone',
															displayName : 'Téléphone'
														},
														{
															field : '',
															cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerPartieInteressee()">	<i class="fa fa-fw fa-pencil"></i></button>'
																	+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(1)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
														} ];
											});
							    $scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};

							$scope.totalServerItems = 0;
							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 20 ],
								pageSize : 3,
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
								setTimeout(
										function() {
											var data;
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.get(
																baseUrl
																		+ "/partieInteressee/all")
														.success(
																function(
																		largeLoad) {
																	data = largeLoad
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
														.get(baseUrl
																+ "/partieInteressee/all")
												.success(function(largeLoad) {
													$scope.setPagingData(
															largeLoad, page,
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

							/***************************************************
							 * Gestion des Documents
							 **************************************************/
							/** ***Liste desVariables ****/
						       $scope.listeDocument = [];
						       $scope.listeTypeDoc = [];
                               $scope.name="PI";
						       // GetId.designation
						       $scope.type = {

						        status : ''
						       };
						       $scope.showStatus = function(id) {

						        $scope.type.status = id;
						        var selected = $filter('filter')(
						          $scope.listeTypeDocumentCache, {
						           id : $scope.type.status
						          });
						        if ($scope.type.status && selected.length) {
						         return selected[0].designation;
						        }else {
						                   return "Not Set";
						                  }
						       };           
						       // ajout d'un Document Partie Interesee
						       $scope.ajoutDocPI = function() {
						        $scope.DocPartieInteresseInserree = {
						         typeDocument : '',
						         vUUIDDocument : '',
						         path:''
						        };
						        $scope.listeDocument
						          .push($scope.DocPartieInteresseInserree);
						       };
						       // Enregistrer Document Partie Interesee
						       $scope.saveDocPI = function(dataDocPI, id) {
						    	   $scope.deleteValue = "non";
						        angular.extend(dataDocPI, {
						         id : id
						        });
						        console
						          .log("Success Save Document in Partie Interessee "
						            + dataDocPI.id);
						       };
						    // Supprimer representant
								$scope.removeDocPi = function(index) {
									$scope.listeDocument.splice(index, 1);
								};
								
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/

							// Liste des CategorieCache
							$scope.listCategoriePICache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeCategoriePICache")
										.success(
												function(dataCategorieCache) {
													$scope.ListCategoriePICache = dataCategorieCache;

												});
							}
							// Liste des FamilleCache
							$scope.listFamillePICache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeFamillePICache")
										.success(
												function(dataFamilleCache) {
													$scope.ListFamillePICache = dataFamilleCache;
												});
							}
							// Liste des TypeCache
							$scope.listTypePICache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeTypePICache")
										.success(
												function(dataTypeCache) {
													$scope.ListTypePICache = dataTypeCache;

												});
							}
							$scope.listSitePICache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSitePartieInteresseeCache")
										.success(
												function(dataSiteCache) {
													$scope.ListSitePICache = dataSiteCache;

												});
							}
							$scope.listCategoriePICache();
							$scope.listFamillePICache();
							$scope.listTypePICache();
							$scope.listSitePICache();
						} ])
		.controller(
				'DatepickerDController',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
//							// date piker defit
//							$scope.today = function() {
//								$scope.partieInteresseeCourante.dateIntroduction = new Date();
//							};
//							$scope.today();
//							$scope.clear = function() {
//								$scope.partieInteresseeCourante.dateIntroduction = null;
//							};
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

						} ]);
