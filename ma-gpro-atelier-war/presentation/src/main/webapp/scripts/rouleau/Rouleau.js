'use strict'
angular
		.module('gpro.rouleau', [ "ngResource" ])
		.controller(
				'RouleauController',
				[
						'$scope',
						'$filter',
						'$http',
						'$interval',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						'$window',
						function($scope, $filter, $http, $interval,$log,
								downloadService, UrlCommun, UrlAtelier,$window) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "list";
							$scope.displayMenu = "pi";
							// Dropdown
							$scope.listeClient = [];
							$scope.listeTissu = [];
							
							// mode creation
							$scope.creation = true;
							// modePdf desactivé par defaut
							$scope.modePdf = "notActive";
							
							//afficher les attributs d'un BonDeReception
							$scope.remplir = false;
							//RechercheMise
							$scope.resultatRechercheMise = [];

							$scope.date = new Date();

							$scope.rouleauCourant = {};
							$scope.rouleauCourantRecherche = {};
							$scope.resultatRecherche = $scope.listeRouleau;
							
							
							$scope.checkList = false;
							
							$scope.enCoursDelete = false;
							
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

							/**************************************************
							 * Remplir rouleau lors du clique sur une Mise 
							 **************************************************/
							$scope.clientId = {

									status : ''
								};
							
							
						
							
							$scope.remplirRouleauMise = function(idMise) {
								$scope.remplir = true;
								$log.debug("IdMise "+idMise);
								console.log("IdMise "+idMise)
								
								// getBonReception
								if(idMise!= null){
									$http
									.get(
											UrlAtelier
													+ "/mise/getMiseId:"+ idMise)
									.success(
											function(datagetMise) {
												$scope.resultatRechercheMise = datagetMise;
												//referenceMise 
												$log.debug("---Mise Reference "+datagetMise.reference);
												console.log("---Mise Reference "+datagetMise.reference)
												$scope.resultatRechercheMise.referenceMise = datagetMise.reference;
												//PoidFini
												console.log("---Mise poid fini: "+datagetMise.poidFini);
												$scope.resultatRechercheMise.poidFini = datagetMise.poidFini;
												//refBonReception
												$scope.resultatRechercheMise.refBonreception = datagetMise.refBonreception;
												//miseFini
												$log.debug("---MiseFini "+datagetMise.fini);
												$scope.resultatRechercheMise.fini = datagetMise.fini;
												//Produit
												$log.debug("---TissuId : "+ datagetMise.produitId);
												//Client
												$log.debug("---ClientId : "+ datagetMise.partieintId);
												
												/**conversion Produit & sousFamilleProduit **/
											    /*Methode 1: utilise le service getId pour convertir le produit avec l'Id x à sa propre reference
												 */
												if(datagetMise.produitId!= null){
													$http
													.get(
															UrlCommun
																	+ "/produit/getId:"+ datagetMise.produitId)
													.success(
															function(datagetProduit) {
																$log.debug(" --TissuId : " + datagetProduit.id);
																$log.debug(" --TissuReference : " + datagetProduit.reference);
																$log.debug(" --SousFamilleId : " + datagetProduit.sousFamilleId);
																$log.debug(" --SousFamilleDesignation : " + datagetProduit.sousFamilleDesignation);

																//affectation de sousFamilleProduit et referenceProduit au rouleau selectionné
																$scope.resultatRechercheMise.produitId = datagetProduit.id;
																$scope.resultatRechercheMise.reference = datagetProduit.reference;
																$scope.resultatRechercheMise.sousFamille = datagetProduit.sousFamilleDesignation
																$scope.resultatRechercheMise.composition = datagetProduit.composition;
																
																$scope.resultatRechercheMise.designationProduit = datagetProduit.designation;
															});
												}else{
													$log.debug(" -- aucun Produit n'est affecté à ce Rouleau !");
												}
												
												/** conversion Client **/
												/* Methode2: utilise le Filtre de Angular pour faire la conversion des Id en leurs propre designations
												 * tout en recuperant la liste des clients de la BD. (TODO recuperation de la cache des listes clients et Produit)
												 */
												if(datagetMise.partieintId != null){
													$scope.clientId.status = datagetMise.partieintId;
													var selected = $filter('showClientFilter')($scope.listeClient,{id : $scope.clientId.status});
															
													if ($scope.clientId.status && selected.length) {
														$scope.resultatRechercheMise.partieintId = selected[0].id;
														$scope.resultatRechercheMise.clientAbreviation = selected[0].abreviation;
														$log.debug(" --ClientAbreviation : "
																				+ $scope.resultatRechercheMise.clientAbreviation);
													} else {
														$log.debug("NotSet Client");
													}
												}else{
													$log.debug(" -- aucun Client n'est affecté à ce Rouleau !");
												}

											});
								}else{
									$log.debug("idMise is null");
								}

							}
							
							
							
							
										$scope.remplirRouleauMiseByReference = function(idMise) {
											
								$scope.clientId.status = "";
											
											
								$scope.remplir = true;
								$log.debug("IdMise "+idMise);
								console.log("IdMise "+idMise)
								
								// getBonReception
								if(idMise!= null){
									$http
									.get(
											UrlAtelier
													+ "/mise/rechercheMiseParReference:"+ idMise)
									.success(
											function(datagetMise) {
												$scope.resultatRechercheMise = datagetMise;
												//referenceMise 
												$log.debug("---Mise Reference "+datagetMise.reference);
												console.log("---Mise Reference "+datagetMise.reference)
												$scope.resultatRechercheMise.referenceMise = datagetMise.reference;
												//PoidFini
												console.log("---Mise poid fini: "+datagetMise.poidFini);
												$scope.resultatRechercheMise.poidFini = datagetMise.poidFini;
												//refBonReception
												$scope.resultatRechercheMise.refBonreception = datagetMise.refBonreception;
												//miseFini
												$log.debug("---MiseFini "+datagetMise.fini);
												$scope.resultatRechercheMise.fini = datagetMise.fini;
												//Produit
												$log.debug("---TissuId : "+ datagetMise.produitId);
												//Client
												$log.debug("---ClientId : "+ datagetMise.partieintId);
												
												/**conversion Produit & sousFamilleProduit **/
											    /*Methode 1: utilise le service getId pour convertir le produit avec l'Id x à sa propre reference
												 */
												if(datagetMise.produitId!= null){
													$http
													.get(
															UrlCommun
																	+ "/produit/getId:"+ datagetMise.produitId)
													.success(
															function(datagetProduit) {
																$log.debug(" --TissuId : " + datagetProduit.id);
																$log.debug(" --TissuReference : " + datagetProduit.reference);
																$log.debug(" --SousFamilleId : " + datagetProduit.sousFamilleId);
																$log.debug(" --SousFamilleDesignation : " + datagetProduit.sousFamilleDesignation);

																//affectation de sousFamilleProduit et referenceProduit au rouleau selectionné
																$scope.resultatRechercheMise.produitId = datagetProduit.id;
																$scope.resultatRechercheMise.reference = datagetProduit.reference;
																$scope.resultatRechercheMise.sousFamille = datagetProduit.sousFamilleDesignation
																$scope.resultatRechercheMise.composition = datagetProduit.composition;
																
																$scope.resultatRechercheMise.designationProduit = datagetProduit.designation;
															});
												}else{
													$log.debug(" -- aucun Produit n'est affecté à ce Rouleau !");
												}
												
												/** conversion Client **/
												/* Methode2: utilise le Filtre de Angular pour faire la conversion des Id en leurs propre designations
												 * tout en recuperant la liste des clients de la BD. (TODO recuperation de la cache des listes clients et Produit)
												 */
												if(datagetMise.partieintId != null){
													$scope.clientId.status = datagetMise.partieintId;
													var selected = $filter('showClientFilter')($scope.listeClient,{id : $scope.clientId.status});
															
													if ($scope.clientId.status && selected.length) {
														$scope.resultatRechercheMise.partieintId = selected[0].id;
														$scope.resultatRechercheMise.clientAbreviation = selected[0].abreviation;
														$log.debug(" --ClientAbreviation : "
																				+ $scope.resultatRechercheMise.clientAbreviation);
													} else {
														$log.debug("NotSet Client");
													}
												}else{
													$log.debug(" -- aucun Client n'est affecté à ce Rouleau !");
												}

											});
								}else{
									$log.debug("idMise is null");
								}

							}
							
								$scope.cleanChamps = function() {
									

								$scope.clientId.status = {};
								$scope.resultatRechercheMise = [];
								$scope.id = {};
								
								$scope.rouleauCourant = {};
								$scope.rouleauCourantRecherche = {};
							
									
									
									}
							
							
							
							/***************************************************
							 * Invocation des services pour les listes
							 * déroulantes
							 **************************************************/

							// Liste des Mises
							$scope.listeMise = function() {
								var mise ={};
								console.log("inn liste mise..");
								$http
								.post(
										UrlAtelier
												+ "/mise/rechercheMiseMulticritere",mise)
								.success(
										function(resultat) {
											$log.debug("listeMise : "+resultat.listeElementRechecheMiseValeur.length);
											$scope.listeMise = resultat.listeElementRechecheMiseValeur;
											
											console.log("listeMise: "+resultat.listeElementRechecheMiseValeur.length);

												})
												
								.error(function(resultat){
									console.log("erreur rech mise");
									});
							}
							
							
							
							
							//ListeQualité
          					$scope.listeQualite = function() {
          						$http.get(UrlAtelier + "/choixRouleau/all").success(
          								function(data) {
          									$scope.listeQualite = data;
          									$log.debug("listeQualité : "+data.length);

          								});
          					}
							
							// ListeEntrepot
							$scope.listeEntrepot = function() {
								var objetVide = {};
								$http.post(UrlAtelier + "/entrepot/rechercheMulticritere",objetVide).success(
										function(data) {
											$log.debug("Entrepot : "
													+ data.list.length);
											$scope.listeEntrepot = data.list;
										});
							}
							
							// Liste Client
							$scope.listeClient = function() {
								$http.get(UrlCommun + "/partieInteressee/all")
										.success(
												function(data) {
													console.log("Client : "
															+ data.length);
													console.log("Client 0 "+data[0].abreviation);
													$scope.listeClient = data;

												});
							}
							// Liste Tissu
							$scope.listeTissu = function() {
								$http.get(UrlCommun + "/produit/all").success(
										function(data) {
											$log.debug("Tissu : "
													+ data.length);
											console.log("Tissu length: "+ data.length)
											$scope.listeTissu = data;
										});
							}
							
							$scope.listeQualite();
							//$scope.listeMise();
							$scope.listeEntrepot();
							$scope.listeTissu();
							$scope.listeClient();
							
							/***************************************************
							 * Gestion des rouleaux
							 **************************************************/
							
							$scope.pagingOptions = {
									pageSizes : [ 5, 10, 13 ,26,39,130,195,260,520,1040 ],
									pageSize : 1040,
									currentPage : 1
								};
							
							// Lister les Bons de Reception
							$scope.rechercherRouleau = function(
									rouleauCourantRecherche) {
								$log.debug("recherche en cours ..");
								$http
										.post(
												UrlAtelier
														+ "/rouleaufini/rechercheMulticritere",
												rouleauCourantRecherche)
										.success(
												function(resultat) {
													
													$scope.myData = resultat.list;
													console.log("resultat.list: ")
													//Pagination du resultat de recherche
													//data, page,pageSize
													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																					   $scope.pagingOptions.pageSize										
																					   );
													
													$log.debug("listeRouleau : "
																	+ resultat.list.length);
													$scope.recherchePartieIntereseeFormCritere
															.$setPristine();
												});

							}

							
							// Lister les Bons de Reception
							$scope.rechercherRouleauByInfoModif = function(
									rouleauCourant) {
								$log.debug("recherche en cours par InfoModif .."+rouleauCourant.infoModif);
								$http(
										{
											method : "GET",
											url : UrlAtelier
													+ "/rouleaufini/getRouleauFiniByInfoModif:"+
													+ rouleauCourant.infoModif
										}).success(function(resultat) {
											
											$scope.myData = resultat.list;
											//Pagination du resultat de recherche
											//data, page,pageSize
											$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																			   $scope.pagingOptions.pageSize										
																			   );
											
											$log.debug("listeRouleauByInfoModif : "
															+ resultat.list.length);
											$scope.recherchePartieIntereseeFormCritere
													.$setPristine();
											
								});
								
								/*$http
										.get(
												UrlAtelier
														+ "/rouleaufini/getRouleauFiniByInfoModif:"+
														rouleauCourant.infoModif)
										.success(
												function(resultat) {
													$scope.myData = resultat.list;
													//Pagination du resultat de recherche
													//data, page,pageSize
													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																					   $scope.pagingOptions.pageSize										
																					   );
													
													$log.debug("listeRouleauByInfoModif : "
																	+ resultat.list.length);
													$scope.recherchePartieIntereseeFormCritere
															.$setPristine();
												});*/

							}
							
							
								$scope.checkAllItemList = function(checkList) {
									
										 angular.forEach($scope.myData, function(element, key){
				
				                                      element.checked = checkList;
					
			                              });
								
								
							     }
		
	                    	   $scope.deleteMultipleColis = function() {
		
		    /* var codeSuppressionColis = prompt("Entrer code de suppression", "");

               if (codeSuppressionColis != null && codeSuppressionColis != '123900') {
                    return ;
                }*/

	$scope.closePopupDelete();
		
		
		                       $scope.enCoursDelete = true;
	     
		
		                        var deletedColisList = [];
									
										 angular.forEach($scope.myData, function(element, key){
				
				                                      if(element.checked)
                                                              deletedColisList.push(element.id) ; 
				                                       
					
			                              });


                                   $http
									.post(
												UrlAtelier
														+ "/rouleaufini/deleteRouleauFiniMultiple",
												deletedColisList)
										.success(
												function(resp) {
													
													$scope.enCoursDelete = false;
													
												    $scope.rechercherRouleau({});
													
													alert("Liste des colis supprimer avec succès") ;
													
													});
                                       
								
								
							     }
							// annuler Ajout
							$scope.annulerAjout = function() {
									
								
								$scope.referenceMise = "";
								
								$scope.enCoursDelete = false;
								$scope.checkList = false;
								$scope.creation = true;
								$scope.remplir = false;
								$scope.modePdf = "notActive";
								$scope.clientId.status = {};
								$scope.resultatRechercheMise = [];
								$scope.id = {};
								$scope.metrageTrouve = false;
								$scope.rouleauCourant = {};
								$scope.rouleauCourantRecherche = {};
								$scope.rouleauCourant.laize = 150;
								$scope.rouleauCourant.choix = 1;
								$scope.listeDetails = [];
								$scope.listeTraitements = [];
								$scope.rechercherRouleau({});
								$scope.creationRouleau.$setPristine();
								$scope.recherchePartieIntereseeFormCritere
										.$setPristine();
								$scope.displayMode = "list";
							}

							// Ajout Partie Interesse **
							$scope.AffectationRouleau = function(
									rouleau) {
								$scope.rouleauCourant = {};
								$scope.creationRouleau.$setPristine();
								$scope.displayMode = "list";
							}

							
							// Ajout et Modification d'un bon de reception
							$scope.modifierOuCreerRouleau = function() {
								$scope.creation = false;
								$scope.modePdf = "actif";
								var index = this.row.rowIndex;
								$log.debug("INDEX :" + index +" ID :"+$scope.myData[index].id);

								// getRouleau
								$http
										.get(
												UrlAtelier
														+ "/rouleaufini/getRouleauFiniById:"
														+ $scope.myData[index].id)
										.success(
												function(dataGetRouleau) {
												 
													//Mise
													$log.debug("***Mise Reference : "+dataGetRouleau.referenceMise);
													$scope.resultatRechercheMise.referenceMise = dataGetRouleau.referenceMise;
													
													
													//Produit
													$log.debug("***TissuId : " + dataGetRouleau.produitId );
													$scope.resultatRechercheMise.produitId = dataGetRouleau.produitId;
													//Client
													$log.debug("***ClientId : " + dataGetRouleau.partieInteresseeId);
													//$scope.clientId.status = datagetMise.partieintId;
													$scope.resultatRechercheMise.partieInteresseeId = dataGetRouleau.partieInteresseeId;
													
													/**conversion Produit & sousFamilleProduit **/
												    /*Methode 1: utilise le service getId pour convertir le produit avec l'Id x à sa propre reference
													 */
													if(dataGetRouleau.produitId!= null){
														$http
														.get(
																UrlCommun
																		+ "/produit/getId:"+ dataGetRouleau.produitId)
														.success(
																function(datagetProduit) {
																	$log.debug(" **TissuId : " + datagetProduit.id);
																	$log.debug(" **TissuReference : " + datagetProduit.reference);
																	$log.debug(" **SousFamilleId : " + datagetProduit.sousFamilleId);
																	$log.debug(" **SousFamilleDesignation : " + datagetProduit.sousFamilleDesignation);
																	$log.debug(" **CompositionProduit : " + datagetProduit.composition);
																	
																	//console.log("dataGetRouleau.produitId:"+dataGetRouleau.reference);

																	//affectation de sousFamilleProduit et referenceProduit au BonReceptionselectionné
																	$scope.resultatRechercheMise.produit = datagetProduit.id;
																	$scope.resultatRechercheMise.reference = datagetProduit.reference;
																	$scope.resultatRechercheMise.sousFamille = datagetProduit.sousFamilleDesignation;
																	$scope.resultatRechercheMise.composition = datagetProduit.composition;
																	$scope.resultatRechercheMise.designationProduit = datagetProduit.designation;
																	
																});
													}else{
														$log.debug("aucun Produit n'est affecté à ce Rouleau");
													}
													
													
													
												
													
													/** conversion Client **/
													/* Methode2: utilise le Filtre de Angular pour faire la conversion des Id en leurs propre designations
													 * tout en recuperant la liste de les clients de la BD. (TODO recuperation de la cache des listes clients et Produit)
													 */
													if(dataGetRouleau.partieInteresseeId != null){
														$scope.clientId.status = dataGetRouleau.partieInteresseeId;
														//$log.debug("////////// Client ID = "+dataGetRouleau.partieInteresseeId+"$scope.clientId.status "+$scope.clientId.status);
														var selected = $filter('showClientFilter')
																		($scope.listeClient,{id : $scope.clientId.status});
														//$log.debug("/// Selected  "+JSON.stringify(selected, null, "    "));
														if ($scope.clientId.status && selected.length) {
															$log.debug("///////// selected[0].id "+selected[0].id +"///// abreviation : "+selected[0].abreviation);
															$scope.resultatRechercheMise.partieInteressee = selected[0].id;
															$scope.resultatRechercheMise.clientAbreviation = selected[0].abreviation;
															$log.debug(" **ClientAbreviation : " + $scope.resultatRechercheMise.clientAbreviation);
														} else {
															$log.debug("NotSet Client");
														}	
													}else{
														$log.debug("aucun Client n'est affecté à ce Rouleau");
													}
													
													// Attributs de Recherche
													$scope.rouleauCourant = dataGetRouleau ? angular
															.copy(dataGetRouleau)
															: {};
													$scope.displayMode = "list";
												});

							}

							// Sauvegarder bon de reception
							$scope.sauvegarderAjout = function(rouleau) {

								if (angular.isDefined(rouleau.id)) {
									$log.debug("Sauvegarder Modification"
											+ rouleau);
									$scope
											.modifierRouleau(rouleau);
								} else {
									$log.debug("**Sauvegarder Ajout"
											+ rouleau.id);
									$scope.creerRouleau(rouleau);
								}
								$scope.rouleauCourant = {};
								$scope.rechercherRouleau($scope.rouleauCourant);
							}

							// Création Bon Reception
							$scope.creerRouleau = function(rouleau) {
								//Affectation du attributs recherchés par la le BonReception
								
							//	$log.debug("--FiniMise "+$scope.resultatRechercheMise.fini);
								rouleau.fini = $scope.resultatRechercheMise.fini;
								
							//	$log.debug("--ReferenceMise "+$scope.resultatRechercheMise.referenceMise);
								rouleau.referenceMise = $scope.resultatRechercheMise.referenceMise;
								
							//	$log.debug("--TissuId "+ $scope.resultatRechercheMise.produitId);
								rouleau.produitId = $scope.resultatRechercheMise.produitId;
								
							//	$log.debug("--ClientId "+ $scope.clientId.status);
								rouleau.partieInteresseeId = $scope.clientId.status;


								rouleau.ids = null;
								
								$http
									.post(
												UrlAtelier
														+ "/rouleaufini/createRouleauFini",
												rouleau)
										.success(
												function(idBon) {
													//$log.debug("Success Creation IdBon : "+idBon);


												//	var listIdRouleau = idBon.split(",");


													if(idBon.indexOf(",") === -1){

														console.log("creation une rouleau");


												

	// getRouleau
													$http.get(UrlAtelier + "/rouleaufini/getRouleauFiniById:"
																		 + idBon)
															.success(
																	function(dataGetRouleau) {
																		//afficher le bouton de generation des etiquettes
																		$scope.modePdf = "actif";
																		// Attributs de Recherche
																		$scope.rouleauCourant = dataGetRouleau ? angular
																				.copy(dataGetRouleau)
																				: {};
																				//added by wassim 20/02
																		//$scope.annulerAjout();
																	});


													}else

													{

														console.log("creation plusieurs rouleau");

														rouleau.ids =  idBon; 
														$scope.rouleauCourant.ids =  idBon;

													}

												

																	 
												});

							}

							// modifier bon de reception
							$scope.modifierRouleau = function(rouleau) {

								rouleau.ids = null;
								$http
										.post(
												UrlAtelier
														+ "/rouleaufini/updateRouleauFini",
														rouleau)
										.success(
												function(rouleauModifiee) {

													for (var i = 0; i < $scope.myData.length; i++) {

														if ($scope.myData[i].id == rouleauModifiee.id) {

															$scope.myData[i] = rouleauModifiee;
															break;
														}
													}
													// getRouleau
													$http.get(UrlAtelier + "/rouleaufini/getRouleauFiniById:"
																		 + rouleauModifiee)
															.success(
																	function(dataGetRouleau) {
																		//afficher le bouton de generation des etiquettes
																		$scope.modePdf = "actif";
																		// Attributs de Recherche
																		$scope.rouleauCourant = dataGetRouleau ? angular
																				.copy(dataGetRouleau)
																				: {};
																				
																				$scope.annulerAjout();
																	});
													

												});

							}
							
							// Suppression d'un bon de reception
							$scope.supprimerRouleau = function() {
								//$log.debug("DELETE l'index : " + $scope.myData[$scope.index].id);
								//console.log("DELETE l'index : " + $scope.myData[$scope.index].id);
								
								//console.log(UrlAtelier+ "/rouleaufini/deleteRouleauFini:"+$scope.myData[$scope.index].id);
								
								
								$http(
										{
											method : "DELETE",
											url : UrlAtelier
													+ "/rouleaufini/deleteRouleauFini:"+
													+ $scope.myData[$scope.index].id
										}).success(function() {
									$log.debug("Success Delete");
									$scope.myData.splice($scope.index, 1);
								});
								$scope.myData.splice($scope.index, 1);
								$scope.closePopupDelete();
								$scope.annulerAjout();
								
									}
							
							

							/**** Etiquette PDF ** */
							$scope.download = function(id) {
								$log.debug("-- id"+id);
								
								var url = UrlAtelier+"/report/etiquetteRouleau?id="+ id +"&type=pdf";
							/*	downloadService.download(url)
										.then(
												function(success) {
													$log.debug('success : '
															+ success);
													$scope.annulerAjout();
												},
												function(error) {
													$log.debug('error : '
															+ error);
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
												$scope.traitementEnCoursGenererAll = "false";
				
												 
												});


							};



								/**** Etiquette PDF ** */
								$scope.downloadListRouleau = function(rechrechRouleau) {
									//$log.debug("-- id"+id);
									
									var url = UrlAtelier+"/report/list-etiquette-rouleau?reference="+ rechrechRouleau.reference
									
									+ "&refMise="+rechrechRouleau.refMise  

									+ "&produitId="+rechrechRouleau.produitId  

									+ "&numberOfBox="+rechrechRouleau.numberOfBox 
									

									+ "&metrage="+rechrechRouleau.metrage  


									+ "&ids="+rechrechRouleau.ids   


									+ "&type=pdf";
								/*	downloadService.download(url)
											.then(
													function(success) {
														$log.debug('success : '
																+ success);
														$scope.annulerAjout();
													},
													function(error) {
														$log.debug('error : '
																+ error);
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
													$scope.traitementEnCoursGenererAll = "false";
					
													 
													});
	
	
								};











								$scope.downloadListRouleauParIds = function(ids) {
									
									
									var url = UrlAtelier+"/report/list-etiquette-rouleau?reference="+ ""
									
									+ "&refMise="+""  

									+ "&produitId="+""  

									+ "&numberOfBox="+""
									

									+ "&metrage="+""


									+ "&ids="+ids   


									+ "&type=pdf";
								/*	downloadService.download(url)
											.then(
													function(success) {
														$log.debug('success : '
																+ success);
														$scope.annulerAjout();
													},
													function(error) {
														$log.debug('error : '
																+ error);
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
													$scope.traitementEnCoursGenererAll = "false";
					
													 
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
											'myData',
											function() {
												$scope.colDefs = [
													
													{
								                     field: 'checked',
								                     displayName: '',
								                     cellTemplate: '<input type="checkbox" ng-model="row.entity.checked"  style="margin: 8px;"/>',
								                     width: '5%'
							                         },
														{
															field : 'reference',
															displayName : 'Ref',
															cellFilter: 'substringFilterRouleau'
														},
														{
															field : 'partieInteresseeIdDesignation',
															displayName : 'Client'
														},
														{
															field : 'produitReference ',
															displayName : 'Produit'
														},
														{
															field : 'referenceMise ',
															displayName : 'O.F'
														},
														{
															field : 'metrage',
															displayName : ' Quantite'
														},
														{
															field : '',
															cellTemplate : `<div class="buttons" ng-show="!rowform.$visible">
																	<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerRouleau()">	<i class="fa fa-fw fa-pencil"></i></button>
																	<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(2)" permission="['Production_Production_Delete']">	<i class="fa fa-fw fa-trash-o"></i></button></div>`
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
											var rouleauCourantRecherche = $scope.rouleauCourantRecherche;
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																UrlAtelier
																		+ "/rouleaufini/rechercheMulticritere",
																rouleauCourantRecherche)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.list
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
																		+ "/rouleaufini/rechercheMulticritere",
																rouleauCourantRecherche)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.list,
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

							/** Fin de gestion des Grids des Rouleaux */
						} ])
		//Filter reference Grid 
		.filter('substringFilterRouleau', function() {
		  return function(text) {
			  
			 
			  if(text != null){
				  if(text.length >6){
					  //text = text.substring(1);
					  return text;
				  }else{
					  return "-";
				  }
			  }else{
				  return "--";
			  }
			  
		  };
		})	
		.filter('showClientFilter', function() {
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
							// $scope.rouleauCourant.dateIntroduction
							// = new Date();
							// };
							// $scope.today();
							// $scope.clear = function() {
							// $scope.rouleauCourant.dateIntroduction
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
