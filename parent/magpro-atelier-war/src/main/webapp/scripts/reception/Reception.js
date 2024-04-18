'use strict'
angular
		.module('gpro.reception', [ "ngResource" ])
		.controller(
				'ReceptionController',
				[
						'$scope',
						'$filter',
						'$http',
						'$interval',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						'$log',
						function($scope, $filter, $http, $interval,
								downloadService, UrlCommun, UrlAtelier,$log) {
							// Déclaration des variables globales utilisées
							var data;
							// Dropdown
							$scope.listeTraitementCaches = [];
							$scope.listeClient = [];
							$scope.listeTissu = [];

							// modePdf desactivé par defaut
							$scope.modePdf = "false";
							// Affichage de la sousFamille d'un produit lors du
							// clique sur un produit
							$scope.remplir = false;
							$scope.resultatRechercheProduit = {};
							
							$scope.indexBonReception = 0;
							$scope.metrageTrouve = false;
							$scope.date = new Date();
							// $scope.listeBonReceptions = [];
							$scope.listeDetails = [];
							$scope.listeTraitements = [];
							$scope.lengthRow = 0;
							$scope.traitementIdRemove = [];
							$scope.ajout =false;
							
							$scope.bonReceptionCourantRecherche = {};
							$scope.bonReceptionCourant = {};

							/***************************************************
							 * Invocation des services pour les listes
							 * déroulantes
							 **************************************************/
							// Liste Traitement
							$scope.listeTraitementCaches = function() {
								$http
										.get(UrlAtelier + "/traitement/all")
										.success(
												function(data) {
													$log.debug("Traitement : "
															+ data.length);
													$scope.listeTraitementCaches = data;

												});
							}

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
							// Liste Tissu
							$scope.listeTissu = function() {
								$http.get(UrlCommun + "/produit/all").success(
										function(data) {
											$log.debug("Tissu : "
													+ data.length);
											$scope.listeTissu = data;
										});
							}
							$scope.listeTissu();
							$scope.listeClient();
							$scope.listeTraitementCaches();
							
							/***************************************************
							 * Remplissage du TypeProduit lors d'une selection
							 * d'un Tissu
							 **************************************************/
							
							$scope.remplirTypeProduit = function(idProduit) {
								
								$scope.remplir = true;
								// getProduit
								if(idProduit!= null){
									$http
									.get(
											UrlCommun
													+ "/produit/getId:"+ idProduit)
									.success(
											function(datagetProduit) {
												$scope.resultatRechercheProduit = datagetProduit;
												$log.debug("****TissuId : " + datagetProduit.id);
												$log.debug("****SousFamilleId : " + datagetProduit.sousFamilleId);
												$log.debug("****SousFamilleDesignation : " + datagetProduit.sousFamilleDesignation);

											});
								}else{
									$log.debug("idProduit is null");
								}

							}
							
							/***************************************************
							 * Gestion des Bons de Reception
							 **************************************************/
							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 ],
								pageSize : 10,
								currentPage : 1
							};							

							// Lister les Bons de Reception
							$scope.rechercherBonReception = function(
									bonReceptionCourantRecherche) {
								$http
										.post(
												UrlAtelier
														+ "/bonreception/rechercheBonReceptionMulticritere",
												bonReceptionCourantRecherche)
										.success(
												function(resultat) {
													
													$scope.myData = resultat.listeElementRechecheBonReceptionValeur;
													
													// data, page,pageSize
													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																					   $scope.pagingOptions.pageSize										
																					   );
													
													
													console
															.log("listeBonReception : "
																	+ resultat.listeElementRechecheBonReceptionValeur.length);
													$scope.rechercheReceptionFormCritere
															.$setPristine();
												});

							}

							// annuler Ajout
							$scope.annulerAjout = function() {
								$scope.addBloc = false;
								$scope.modePdf = "notActif";
								$scope.metrageTrouve = false;
								$scope.bonReceptionCourantRecherche = {};
								$scope.bonReceptionCourant = {};
								$scope.resultatRechercheProduit.sousFamilleDesignation = "";
								$scope.listeDetails = [];
								$scope.listeTraitements = [];
								$scope.traitementIdRemove = [];
								$scope.rechercherBonReception({});
								$scope.creationBonReception.$setPristine();
								$scope.rechercheReceptionFormCritere
										.$setPristine();
							}

							// Ajout BonReception
							$scope.AffectationBonReception = function(
									bonReception) {
								$scope.bonReceptionCourant = {};
								$scope.creationBonReception.$setPristine();
							}

							
							// Ajout et Modification d'un bon de reception
							$scope.modifierOuCreerBonReception = function() {
								// mode Modification
								$scope.modeCreation = false;
								$scope.modePdf = "actif";
								
								$scope.traitementIdRemove = [];
								$scope.listeTraitements = [];
								$scope.dropList = "ON";
								
								var index = this.row.rowIndex;
								$log.debug("INDEX :" + index +" ID :"+$scope.myData[index].idBonReception);

								// getBonReception
								$http
										.get(UrlAtelier + "/bonreception/getBonReceptionId:"
														+ $scope.myData[index].idBonReception)
										.success(
												function(dataGetBonReception) {
													
													// getProduit correspandant à ce bon de reception
													$http
													.get(UrlCommun+ "/produit/getId:"+ dataGetBonReception.produit)
													.success(
															function(datagetProduit) {
																$scope.resultatRechercheProduit = datagetProduit;
																$log.debug("****TissuId : " + datagetProduit.id);
																$log.debug("****SousFamilleId : " + datagetProduit.sousFamilleId);
																$log.debug("****SousFamilleDesignation : " + datagetProduit.sousFamilleDesignation);

															});
													$scope.metrageTrouve = true;
													
													$scope.indexBonReception = $scope.myData[index].idBonReception;

													// listeDetailsBC
													$scope.listeDetails = dataGetBonReception.listeRouleauxEcru;
													// ListeTraitementsBC
													$scope.listeTraitements = dataGetBonReception.listeTraitements;
													//liste des traitementId deja affectés
													if($scope.listeTraitements != null){
														angular.forEach($scope.listeTraitements, function(traitement, key){
															console.info("traitement "+traitement.traitementId);
															$scope.traitementIdRemove.push(traitement.traitementId);
														});
													}
													
													/*if(dataGetBonReception.listeTraitements[0] != null){
														$scope.traitementIdRemove.push(dataGetBonReception.listeTraitements[0].traitementId);
														for(var i=0; i<dataGetBonReception.listeTraitements.length; i++){
															if(dataGetBonReception.listeTraitements[0].traitementId ==dataGetBonReception.listeTraitements[i].traitementId){
																$log.debug("Equal 0 0");
																
															} else{
																$log.debug("NOT Equal 0 0");
																$scope.traitementIdRemove.push(dataGetBonReception.listeTraitements[i].traitementId);
																
															}
														}
													}*/
													
													// Affectation des listes trouvées à notre Objet
													$scope.myData[index].listeRouleauxEcru = $scope.listeDetails;
													$scope.myData[index].listeTraitements = $scope.listeTraitements;

													$log.debug("listeRouleauxEcru : "+ $scope.listeDetails.length);
													$log.debug("listeTraitements : "+ $scope.listeTraitements.length);
													
													// Attributs de Recherche
													$scope.bonReceptionCourant = dataGetBonReception ? angular
															.copy(dataGetBonReception): {};
													
												});

							}

							//TODO : code à revoir
							// Filtre de la dropList de Traitements
							$scope.filterTraitement = function () {
								
							    return function (item) {
							    	
							    	
							    	 var condition = false;
							    	 for(var k=0; k<$scope.traitementIdRemove.length; k++){
							    		if(item.id != $scope.traitementIdRemove[k]){
							    			condition = true
								    	}else{
								    		condition = false;
								    		break;
								    	}
							    	}
							    	
							        if (condition==true){
							            return true;
							        }
							        return false;
							        
							    };
							};
							
							
							$scope.oldPhase = 0;
							$scope.changeTraitement = function( phaseId,index){ 
								$log.error("///////////// changeTraitement"+ phaseId+" --- "+index);
								/** Affectation */
								//$log.warn("-- Before oldPhase : "+JSON.stringify($scope.oldPhase, null, "    ") );
								$scope.oldPhase = phaseId;
								//$log.warn("-- After oldPhase : "+JSON.stringify($scope.oldPhase, null, "    ") );
								
								$scope.ajout = false;
								
							};
																					
							$scope.addBloc = false;
							$scope.newPhase = 0;
							$scope.updateFct = false;
							$scope.refreshTraitement = function(newPhase){
								//$log.warn("-- refreshTraitement : "+newPhase); 
								
								$scope.newPhase = newPhase;
								$scope.updateFct = true;
							
								if($scope.updateFct == true){
									if($scope.newPhase == undefined){
										//$log.warn(" newPhase Undefined");
										var index = $scope.traitementIdRemove.length;
										//$log.warn("-- BEFORE traitementIdRemove "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
										$scope.traitementIdRemove.splice(index,1);
										//$log.warn("-- AFTER traitementIdRemove "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
									}else{
										//$log.warn(" newPhase Defined .. : ");
										if($scope.newPhase == null){
											//$log.warn("newPhase NULL ");
										}else{
											//$log.warn("newPhase : "+newPhase);
											if($scope.ajout == false){
												//$log.error(" Ajout = False");
												if($scope.addBloc == false){
													//$log.info(" addBloc = False");
												
													/** Splice **/
													//$log.warn("===BEFORE SPLICE "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													$scope.traitementIdRemove.splice(0,1);
													//$log.warn("=== AFTER SPLICE "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													/** Push **/
													//$log.warn("=== BEFORE PUSH "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													$scope.traitementIdRemove.push($scope.newPhase);
													//$log.warn("=== AFTER PUSH "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													
													$scope.addBloc = "others";
													
												}else if($scope.addBloc == true){
													//$log.info(" addBloc = True");
													
													/** Splice **/
													//$log.warn("-- BEFORE SPLICE "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													$scope.traitementIdRemove.splice($scope.traitementIdRemove.indexOf(""),1);
													//$log.warn("-- AFTER SPLICE "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													/** newOld **/
													//$log.info(" $scope.oldNewPhase "+$scope.oldPhase  );
													$scope.newPhase = $scope.oldPhase ;
													/** Push **/
													//$log.warn("-- BEFORE PUSH "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													$scope.traitementIdRemove.push($scope.newPhase);
													//$log.warn("-- AFTER PUSH "+JSON.stringify($scope.traitementIdRemove, null ,"  ") );
													
													$scope.addBloc = "others";
													
												}
												
												
											}else{
												$log.error(" Ajout = TRUE");
												$scope.addBloc = true ;
												$scope.newPhase = "";
												//$log.warn("***** $scope.newPhase "+$scope.newPhase);
												//$log.warn("*****BEFORE PUSH  $scope.traitementIdRemove "+JSON.stringify($scope.traitementIdRemove,null,"  ") );
												
												if($scope.traitementIdRemove.indexOf($scope.newPhase) == -1){
														$scope.phaseIdRemove.push($scope.newPhase);
													//	$log.warn("*****After PUSH  $scope.traitementIdRemove "+JSON.stringify($scope.traitementIdRemove,null,"  ") );
													
												}else{
													$log.error( $scope.newPhase + " Exist " );
													//$scope.ajout = false;
												}
												
											}
												
										}
									}
									
									$scope.updateFct = false;
								}else{
									//$log.debug("On Passe");
								}
								$scope.updateFct = false;
							}
							// Sauvegarder bon de reception
							$scope.sauvegarderAjout = function(bonReception) {

								if (angular.isDefined(bonReception.id)) {
									$log.debug("Modif. Operation ..");
									$scope.modifierBonReception(bonReception);
								} else {
									$log.debug("Add. Operation ..");
									$scope.creerBonReception(bonReception);
								}
								
								$scope.bonReceptionCourant = {};
								
								$scope.rechercherBonReception($scope.bonReceptionCourant);
							}

							// Création Bon Reception
							$scope.creerBonReception = function(bonReception) {
								bonReception.listeRouleauxEcru = $scope.listeDetails;
								bonReception.listeTraitements = $scope.listeTraitements;
								bonReception.dateIntroduction = $scope.date;
								// bonReception.dateLivraison = $scope.date;
								$http
									.post(UrlAtelier + "/bonreception/creerOrdreBonReception", bonReception)
									.success(
											function(idBon) {
												$log.debug("Success Creation ");
												// getBonReception
												$http.get(UrlAtelier + "/bonreception/getBonReceptionId:"
																	 + idBon)
													  .success(
															  function(dataGetBonReception) {
																//bouton Pdf activé
																$scope.modePdf = "actif";
																// Attributs de Recherche
																$scope.bonReceptionCourant = dataGetBonReception ? angular
																			.copy(dataGetBonReception): {};
															  });
																													
											});
							}

							// modifier bon de reception
							$scope.modifierBonReception = function(bonReception) {
								bonReception.listeRouleauxEcru = $scope.listeDetails;
								bonReception.listeTraitements = $scope.listeTraitements;

								$http
									.post(UrlAtelier+ "/bonreception/modifieBonReception", bonReception)
									.success(
											function(BonModifiee) {
													for (var i = 0; i < $scope.myData.length; i++) {
														if ($scope.myData[i].idBonReception == BonModifiee.idBonReception) {
															$scope.myData[i] = BonModifiee;
															break;
														}
													}
													// getBonReception
													$http.get(UrlAtelier + "/bonreception/getBonReceptionId:"
																		 + BonModifiee)
														  .success(
																  function(dataGetBonReception) {
																	//bouton Pdf activé
																	$scope.modePdf = "actif";
																	// Attributs de Recherche
																	$scope.bonReceptionCourant = dataGetBonReception ? angular
																				.copy(dataGetBonReception): {};
																  });
													//$scope.annulerAjout();
												});
							}
							
							// Suppression d'un bon de reception
							$scope.supprimerBonReception = function() {
								$log.debug("DELETE l'index : " + $scope.index);
								$http(
										{
											method : "DELETE",
											url : UrlAtelier + "/bonreception/supprimerBonReception:"+ $scope.myData[$scope.index].idBonReception
										}).success(function() {
									$log.debug("Success Delete");
									$scope.myData.splice($scope.index, 1);
								});
								$scope.myData.splice($scope.index, 1);
								$scope.closePopupDelete();
								$scope.annulerAjout();
							}

							/** ** Traitement ** */
							$scope.dropList = "ON";
							$scope.testNullTraitement = function(traitementId){
								if(angular.isDefined(traitementId)){
									$scope.dropList = "OFF";
									//return traitementId;
								}else{
									$scope.dropList = "ON";
									//return "NULL";
								}
							}
							
							// GetId.designation Traitement
							$scope.traitement = {

								status : ''
							};

							$scope.showTraitement = function(id, traitementId) {
							if (angular.isDefined(id)) {
								/** id Def * */
								if (angular.isDefined(traitementId)) {
									/** traitementId Def * */
									$scope.traitement.status = traitementId;
									var selected = $filter('filter')(
											$scope.listeTraitementCaches, {
												id : $scope.traitement.status
											});
									if ($scope.traitement.status && selected.length) {
										return selected[0].designation;
									} else {

										return "***";
									}
								} else {
									/** traitementId NOT Def * */
									$scope.traitement.status = id;
									var selected = $filter('filter')(
											$scope.listeTraitementCaches, {
												id : $scope.traitement.status
											});
									if ($scope.traitement.status && selected.length) {
										return selected[0].designation;
									} else {

										return "---";
									}
								}
								
							}else{
								/** Id NotDef * */
								if (angular.isDefined(traitementId)) {
									
									$scope.traitement.status = traitementId;
									var selected = $filter('filter')(
											$scope.listeTraitementCaches, {
												id : $scope.traitement.status
											});
									if ($scope.traitement.status && selected.length) {
										return selected[0].designation;
									} else {

										return "xxx";
									}
								} else {
									$scope.traitement.status = traitementId;
									var selected = $filter('filter')(
											$scope.listeTraitementCaches, {
												id : $scope.traitement.status
											});
									if ($scope.traitement.status && selected.length) {
										return selected[0].designation;
									} else {

										return "%%%";
									}
								}
								
							}

							};

							// ajouter un Traitement
							$scope.plusTraitement = function() {
								$scope.ajout = true;
								$scope.traitementIdRemove.push("");
								$scope.traitementObject = {};
								$scope.listeTraitements.push($scope.traitementObject);
							}

							// supprimer un Traitement
							$scope.removeTraitement = function(index) {
								//TODO
								// $scope.bonReceptionCourant.nombreRouleau--;
								
								//remove traitementId de la liste des traitements utilisés, pour qu'il soit reutilisable 
								$scope.traitementIdRemove.splice($scope.traitementIdRemove.indexOf($scope.traitementIdRemove[index]), 1);
								
								$scope.listeTraitements.splice(index, 1);
							}
							/** ** Rouleau ** */
							// rows Details Length
							/*
							$scope.rowsDetailsLength = function() {
								$scope.listeDetails = [];
								if ($scope.bonReceptionCourant.nombreRouleau != '') {
									$scope.lengthRow = $scope.bonReceptionCourant.nombreRouleau;
									$scope.listeDetails[0] = {
										ref : "ref"
									};
									for (var i = 1; i < $scope.lengthRow; i++) {
										$scope.listeDetails[i] = {
											ref : "ref"
										};
									}
									$log.debug("lengthRow = "
											+ $scope.lengthRow);
								}
							}
							*/
							
							// ajouter un rouleau
							$scope.plusDetail = function() {
								$scope.bonReceptionCourant.nombreRouleau++;

								$scope.rouleauObject = {};
								$scope.listeDetails.push($scope.rouleauObject);
							}

							// supprimer un rouleau
							$scope.removeRouleau = function(index) {
								$scope.bonReceptionCourant.nombreRouleau--;

								$scope.listeDetails.splice(index, 1);
							}

							/** ** PDF ** */
							$scope.download = function(id) {
								$log.debug("-- id"+id);
								var url = UrlAtelier+"/report/generer?id=" + id+"&type=pdf";
								downloadService.download(url)
										.then(
												function(success) {
													$log.debug('success : '+ success);
												},
												function(error) {
													$log.debug('error : '+ error);
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
							$scope.$watch(
											$scope.myData,
											function() {
												$scope.colDefs = [
														{
															field : 'dateIntroduction',
															displayName : 'Date',
															cellFilter: 'date:"dd-MM-yyyy"',
														},
														{
															field : 'referenceBR',
															displayName : 'Ref',
															cellFilter: 'substringFilterReception'
														},
														{
															field : 'abreviationClientDesignation',
															displayName : 'Client'
														},
														{
															field : 'referenceProduit ',
															displayName : 'Tissu'
														},
														{
															field : 'metr.A',
															displayName : ' Metrage/Poids'
														},
														{
															field : '',
															cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerBonReception()">	<i class="fa fa-fw fa-pencil"></i></button>'
																	+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(0)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
														} ];
											});
							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};

							$scope.totalServerItems = 0;

							/* this function is used to set data will be showed */
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
											var bonReceptionCourantRecherche = $scope.bonReceptionCourantRecherche;
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																UrlAtelier
																		+ "/bonreception/rechercheBonReceptionMulticritere",
																bonReceptionCourantRecherche)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.listeElementRechecheBonReceptionValeur
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
																		+ "/bonreception/rechercheBonReceptionMulticritere",
																bonReceptionCourantRecherche)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.listeElementRechecheBonReceptionValeur,
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
								/*sortInfo: {
								      fields: ['idBonReception'],
								      directions: ['desc']
								    },
								enableSorting: true,*/
								enablePaging : true,
								showFooter : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};

							/** Fin de gestion des Grids de la partie Interesse */
						} ])
	
		//Filter reference 
		.filter('substringFilterReception', function() {
		  return function(text) {
			  if(text != null){
				  if(text.length >6){
					  text = text.substring(5);
					  return text;
				  }else{
					  return "-";
				  }
			  }else{
				  return "--";
			  }
		  };
		})		
		// DatePicker
		// TODO: à transformer en Directive
		.controller(
				'DatepickerDController',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
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

		// HorlogeController
		// TODO : la transformer en Directive
		.controller('TimeCtrl', function($scope, $interval) {
			var tick = function() {
				$scope.clock = Date.now();
			}
			tick();
			$interval(tick, 1000);
		});
