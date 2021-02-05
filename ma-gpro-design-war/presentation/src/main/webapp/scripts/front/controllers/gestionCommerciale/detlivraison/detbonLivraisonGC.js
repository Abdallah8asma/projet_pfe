angular
		.module('gpro.detbondelivraisonGC', [ "ngResource" ])
		.controller(
				'detbonlivraisonControllerGC',
				[
						'$scope',

						'$filter',
						'$http',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						'BonLivraisonServices',
						'traitementFaconServices',
						'$window',
						function($scope, $filter, $http, $log, downloadService,
								UrlCommun, UrlAtelier, BonLivraisonServices,
								traitementFaconServices,$window) {
							$log.info("=========Vente========");

							$scope.tagReferenceBSList = [];
							$scope.listeProduitCach = [];
							$scope.natureLivraison = "FINI";
							$scope.listeUniteArticle = [];
							
							$scope.traitementEnCoursGenererDet = "false";	

													
							$scope.detbonLivraisonVenteCourant = {
									
									"partieIntId" : '',
									"dateDe" : '',
									"dateA" : '',
									"qteDe" : '',
									"qteA" : '',
									"prixDe" : '',
									"prixA" : '',
									"idDepot":'',
									"idProduit":''
									
								};

					

							if ($scope.navMode == "redirection") {

								$scope.natureLivraison = "FINI";
								$scope.listTaxeLivraisonInitMethod();

								$log.debug("---livraison----origine"
										+ $scope.origine);
								if ($scope.origine = true) {

									$scope.detdetbonLivraisonVenteCourant.reference = "";

									$scope.detdetbonLivraisonVenteCourant = {
										// "reference" : $scope.referenceBs,
										"date" : $scope.dateSortieBs
									};

									$log
											.debug("-------$scope.detdetbonLivraisonVenteCourant"
													+ $scope.detdetbonLivraisonVenteCourant);
								} else {

									$scope.detdetbonLivraisonVenteCourant = {
										"reference" : $scope.referenceBs,
										"date" : $scope.dateSortieBs
									};

								}

								$scope.tagReferenceBSList
										.push($scope.referenceBs);
								$scope.displayMode = "edit";

							} else if ($scope.navMode == 'normal') {
								$scope.detdetbonLivraisonVenteCourant = {};
								$scope.displayMode = "list";
							}

							var data;
							// bouton pdf hide
							$scope.modePdf = "notActive";

							// mode list activé
							$scope.listeBonSortie = [];
							$scope.listDetLivraisonVente = [];
							$scope.listDetLivraisonVentePRBS = [];
							$scope.idBonLivVente = '';
							// liste des ReferencesBS

							$scope.listTaxeLivraison = [];
															

							// modeValider
							$scope.modeValider = "notActif";
							// init deleteValue pour cancelAddBLVente
							$scope.deleteValue = "oui";
							$scope.ListeMises = [];
							/***************************************************
							 * Gestion des DropListe & cache
							 **************************************************/
							$scope.listePartieInteresseeCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listePartieInteresseeCache")
										.success(
												function(dataPartieInteressee) {
													$scope.listePartieInteresseeCache = dataPartieInteressee;
													$log
															.debug("listePartieInteresseeCache : "
																	+ dataPartieInteressee.length)

												});
							}
							
							
							//REST SERVICE MAGAZINS
							$scope.listeMagazinCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/magasin/depots")
										.success(
												function(dataMagazin) {
													
													
													$scope.listeMagazinCache = dataMagazin;
													$log
															.debug("listeMagazinCache : "
																	+ dataMagazin.length)

												});
							}
							

							// TODO: Liste des Taxes A remplacer par une liste
							// extraite de la cache
							$scope.listeTaxes = function() {
								$http.get(UrlAtelier + "/taxe/getAll").success(
										function(dataTaxe) {

											$scope.listeTaxes = dataTaxe;
											console.log("===> get taxe");
										});
							}

							// Liste des produits
							$scope.listeProduitCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireLogistiqueCache/listeProduitCache")
										.success(
												function(data) {
													console
															.log("listeProduitCache "
																	+ data.length);
													$scope.listeProduitCache = data;

												});
							}

							$scope.listeProduitCache();

							// Liste des unités :uniteArticle
							$scope.listeUniteArticle = function() {
								$http
										.get(UrlCommun + "/uniteArticle/all")
										.success(
												function(data) {
													console
															.log("listeProduitCache "
																	+ data.length+" "+data);
													$scope.listeUniteArticle = data;

												});
							}

							$scope.listeUniteArticle();

							// TODO: Liste des Marches A remplacer par une liste
							// extraite de la cache
							/*
							 * $scope.listeMarche = function() { $http
							 * .get(UrlAtelier + "/marche/getAll") .success(
							 * function(dataMarche) {
							 * 
							 * $scope.listeMarche = dataMarche;
							 * $log.debug("------Vente Js: listeMarche : "+
							 * dataMarche.length); }); }
							 */

							// TODO: Liste des modePaiement A remplacer par une
							// liste extraite de la cache
							$scope.listeModePaiement = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireLogistiqueCache/listeModePaiementCache")
										.success(
												function(dataPaiement) {

													$scope.listeModePaiement = dataPaiement;
												});
							}

							// Liste des traitements façon (hors cache)
							$scope.getListeTraitementFacon = function() {
								traitementFaconServices
										.getListeTraitementFacon()
										.then(
												function(listeTraitementFacon) {
													$scope.listeTraitementFacon = listeTraitementFacon;
												},
												function(error) {
													console
															.log(error.statusText);
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

							$scope.listePartieInteresseeCache();
							$scope.listeMagazinCache();
							$scope.listeTaxes();
							// $scope.listeMarche();
							$scope.listeModePaiement();
							$scope.getListeTraitementFacon();

							$scope.ListeMises();
							// Liste des listGroupeClient
							$scope.listGroupeClient = function() {
								$http
										.get(
												UrlCommun
														+ "/groupeClient/all")
										.success(
												function(dataCategorieCache) {
												// $log.debug("listeCategorie :
												// "+dataCategorieCache.length);
													$scope.listGroupeClient = dataCategorieCache;

												});
							}
							$scope.listGroupeClient();

							/***************************************************
							 * Conversion des Ids/Designation
							 **************************************************/
							// TypeTaxe
							$scope.typeTaxeId = {

								status : ''
							};
							$scope.showTaxe = function(id) {
								$scope.typeTaxeId.status = id;
								var selected = $filter('filter')(
										$scope.listeTaxes, {
											id : $scope.typeTaxeId.status
										});
								if ($scope.typeTaxeId.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}

							}

							/***************************************************
							 * Gestion de suppression d'une ligne d'un tableau
							 **************************************************/

							// Annuler Ajout
							$scope.cancelAddBLVente = function(rowform, index,
									id, designation, liste) {
								$log.debug("cancelAddBLVente");
								$log.debug("* Designation"
										+ liste[0].designation);
								$log.debug("* ID" + id);
								if (angular.isDefined(id)) {

									$log.debug("DEF ID");
									$scope.deleteValue = "non";
									rowform.$cancel();
									$log.debug("CANCEL");
								} else {
									$log.debug("ID  UNDEFINED ");
									if (designation == "") {
										$scope.deleteValue == "oui"
										$log.debug("Designation : "
												+ designation);
										$log.debug("$scope.deleteValueOUI : "
												+ $scope.deleteValue);
										liste.splice(index, 1);
										rowform.$cancel();
										$log.debug("DELETE")
									} else {
										$log.debug("Designation :"
												+ designation);
										$log.debug("$scope.deleteValueNON : "
												+ $scope.deleteValue);
										rowform.$cancel();
										$log.debug("CANCEL");
									}
								}
								$scope.deleteValue = "oui";
								
								//rechargement des produits
								$scope.listeProduitCache();
							}
						

						



							$scope.produitRech={
								id : ''
							};
							
							$scope.getProductFilter = function(idProduit) {
								$scope.productFilter = [];
								$scope.productFilter2 =[];
								
								$scope.produitRech.id=idProduit;
								//console.log("produit id"+idProduit);
								//console.log("produit id rec"+$scope.produitRech.id);
								
								
								
								$scope.productFilter = $filter('filter')(
										
										
										$scope.listeProduitCache, {
											// idProduit :parseInt(id)
											id : idProduit
											
											
										});
								
								//console.log("length liste filtre produit"+$scope.productFilter.length);
								
								for(var i=0;i<$scope.productFilter.length;i++){
								
									
									if($scope.productFilter[i].id==idProduit){
										//console.log("resultat filter: "+$scope.productFilter[i].reference);
										$scope.productFilter2=$scope.productFilter[i];
									}
								}
                                 
							}
							$scope.PrixClientValue = {
									"idClient" : '',
									"idProduit=" : '',
									
								};
							$scope.clickProduit = function(idProduit, index) {
								
								
								
								//var ObjetPrixClient								
								
								//get idClient et get idProduit
								
								var idClient=$scope.detdetbonLivraisonVenteCourant.partieIntId;
								//console.log("###### PI click prod: "+idClient);
								//console.log("idProduit---" + idProduit+"index: "+index);
								
								$scope.PrixClientValue.idClient=idClient;
								$scope.PrixClientValue.idProduit=idProduit;
								
								//console.log("=====> client id: "+$scope.PrixClientValue.idClient);
									
										//fonction load PrixClient Par Produit
										$scope.listePrixClientProduit = function() {
											$http.post(UrlCommun + "/prixClient/PrixClientProduit",$scope.PrixClientValue
													).success(
													function(resultat) {
														
															//console.log("resultat id: "+resultat.id);
														$scope.listePrixClientProduit = resultat;
														
														//console.log("$scope.listePrixClientProduit: "+$scope.listePrixClientProduit.prixvente);
														//test sur prix
														if($scope.listePrixClientProduit!=null &&  $scope.listePrixClientProduit.prixvente>0 && $scope.PrixClientValue.idClient!="" && $scope.PrixClientValue.idClient!=undefined)
														{
															$scope.listDetLivraisonVente[index].prixUnitaireHT=$scope.listePrixClientProduit.prixvente;
															//console.log("prix vente si >0 :"+$scope.listePrixClientProduit.prixvente);
														}else
														{
															$scope.listDetLivraisonVente[index].prixUnitaireHT = $scope.productFilter2.prixUnitaire;
															//console.log("prix vente standard : "+$scope.productFilter2.prixUnitaire);
														}
													});
										}
								
									
								
								
								$scope.listePrixClientProduit();
                                
								$scope.productFilter = [];

								$scope.getProductFilter(idProduit);

										
								//nouveau affectation des vari
								console.log("--> click prod: "+$scope.productFilter2.designation+" -- qte: "+$scope.productFilter2.unite);
								$scope.listDetLivraisonVente[index].produitDesignation = $scope.productFilter2.designation;
								$scope.listDetLivraisonVente[index].quantiteStock = $scope.productFilter2.quantite;
								$scope.listDetLivraisonVente[index].unite = $scope.productFilter2.unite;
								
								
								console.log("########## prix vente: "+$scope.listePrixClientProduit.prixvente);
								//test sur prix
								if($scope.listePrixClientProduit!=null &&  $scope.listePrixClientProduit.prixvente>0)
								{
									$scope.listDetLivraisonVente[index].prixUnitaireHT=$scope.listePrixClientProduit.prixvente;
									console.log("prix vente si >0 :"+$scope.listePrixClientProduit.prixvente);
								}else
								{
									$scope.listDetLivraisonVente[index].prixUnitaireHT = $scope.productFilter2.prixUnitaire;
									console.log("prix vente standard : "+$scope.productFilter2.prixUnitaire);
								}
								$scope.listDetLivraisonVente[index].errorQte = false;
								

							}

							$scope.checkQte = function(qteStock, qte, index) {

								if (qte > qteStock) {
									$scope.listDetLivraisonVente[index].errorQte = true;
								} else {
									$scope.listDetLivraisonVente[index].errorQte = false;
								}
								//calcul qte*prixunitaire//
								
							 $scope.listDetLivraisonVente[index].prixTotalHT=$scope.productFilter[0].prixUnitaire*$scope.qte;

							}
						

							/** Fin de gestion des Produit */

							


							/***************************************************
							 * Gestion BonLivraison -Vente
							 **************************************************/
							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 ],
								pageSize : 13,
								currentPage : 1
							};

							// Recherche des Bons de Vente
							$scope.rechercherBonLivraisonVente = function(
									detbonLivraisonVenteCourant) {
								
							
								
								console.log("========>Rech:"+detbonLivraisonVenteCourant.idDepot);
								$log
										.debug("----Livraison--*** --detbonLivraisonVenteCourant : recherche avant---"
												+ JSON
														.stringify(
																detbonLivraisonVenteCourant,
																null, " "));
								$http
										.post(
												UrlAtelier
														//+ "/bonlivraison/rechercheMulticritere",
														+ "/detlivraisonvente/rechercheMulticritereDetLivraison",
												detbonLivraisonVenteCourant)
										.success(
												function(resultat) {
													$log
															.debug("----Livraison : request : detbonLivraisonVenteCourant : recherche result---"
																	+ JSON
																			.stringify(
																					$scope.detbonLivraisonVenteCourant,
																					null,
																					" "));
													$scope.myData = resultat.listDetailLivraison;
													
													//console.log("myData: "+$scope.myData.length);
													// Pagination du resultat de
													// recherche
													// data, page, pageSize
													$scope
															.setPagingData(
																	$scope.myData,
																	$scope.pagingOptions.currentPage,
																	$scope.pagingOptions.pageSize);

													//$log	.debug("listeBonVente : "+ resultat.list.length);
													$log
															.debug("----resultat.list-----: recherche result---"
																	+ JSON
																			.stringify(
																					resultat.list,
																					null,
																					" "));

													$scope.rechercheBLVenteForm
															.$setPristine();

												/*	$scope.detbonLivraisonVenteCourant = {
														
														"partieIntId" : '',
														"dateDe" : '',
														"dateA" : '',
														"qteDe" : '',
														"qteA" : '',
														"prixDe" : '',
														"prixA" : '',
														"idDepot":'',
														"idProduit":''
														
													};*/
													$log
															.debug("----$scope.detbonLivraisonVenteCourant : rechrche fin---"
																	+ JSON
																			.stringify(
																					$scope.detbonLivraisonVenteCourant,
																					null,
																					" "));
												});

							}

							// Annuler Recherche
							$scope.annulerAjout = function() {
								
								$scope.traitementEnCoursGenererDet = "false";	


								// init checkbox : 'non' :rapport sans Prix /
								// 'oui' rapport avec prix
								$scope.checkboxModel = {
									rapportPrix : "oui"
								};
								
								$scope.checkboxModel = {
										stock : "oui"
									};
								
								$scope.checkboxModelRech = {
										stock : "oui"
									};

								$scope.idBonLivVente = '';
								$log.debug("Annuler :$scope.idBonLivVente "
										+ $scope.idBonLivVente);
								// bouton Valider en mode : notActive
								$scope.modeValider = "notActif";
								// bouton PDF en mode : notActive
								$scope.modePdf = "notActive";
								// vider la liste des refBS
								$scope.tagReferenceBSList = [];
								$scope.listTaxeLivraison = [];
								
								$scope.listDetLivraisonVente = [];
								$scope.listDetLivraisonVentePRBS = [];
								// Tableau Prédefini
								/*
								 * $scope.listTaxeLivraisonInit = [ {//FODEC
								 * taxeId: 1, pourcentage: 1, montant: '', },
								 * {//TVA taxeId: 2, pourcentage: 18, montant:
								 * '', }];
								 */
								// initialiser le design des champs
								$scope.creationBLVenteForm.$setPristine();
								$scope.rechercheBLVenteForm.$setPristine();
								// init de l'objet courant
								// $scope.detbonLivraisonVenteCourant={};
								
								
								
								$scope.detbonLivraisonVenteCourant = {
										
										"partieIntId" : '',
										"dateDe" : '',
										"dateA" : '',
										"qteDe" : '',
										"qteA" : '',
										"prixDe" : '',
										"prixA" : '',
										"idDepot":'',
										"idProduit":''
										
									};

								$scope.getPagedDataAsync(
										$scope.pagingOptions.pageSize,
										$scope.pagingOptions.currentPage);

								// interface en mode : list
								$scope.displayMode = "list";
								
								
								
							}

					
							
							

					

							/** * PDF ** */
							// generer rapport apres creation d'un bon de
							// Livraison. mode : Modification/Consultation
							$scope.download = function(id, pRapportPrix,typerapport) {
								// init checkbox : 'non' :rapport sans Prix /
								// 'oui' rapport avec prix
								$scope.checkboxModel = {
									rapportPrix : "oui"
								};

								$log.debug("-- id" + id + pRapportPrix);
								console.log("type BL:"+typerapport);
								var url = UrlAtelier
										+ "/reportgc/bonlivraison?id=" + id
										+ "&avecPrix=" + pRapportPrix
										+"&typerapport=" +typerapport
										+ "&type=pdf";


								
										var a = document.createElement('a');
										document.body.appendChild(a);
										downloadService.download(url).then(function (result) {
											var heasersFileName = result.headers(['content-disposition']).substring(17);
											var fileName = heasersFileName.split('.');
										var typeFile = result.headers(['content-type']);
										var file = new Blob([result.data], {type: typeFile});
										var fileURL = window.URL.createObjectURL(file);
										if(typeFile == 'application/vnd.ms-excel'){
											console.log('llll excel');
										 // a.href = fileURL;
											 a.download = fileName[0];
											$window.open(fileURL)
											 a.click();
						
										}else{
											console.log('llll pdf');
											a.href = fileURL;
											a.download = fileName[0];
										 $window.open(fileURL)
											a.click();
						
										}
											
										 
										});
						
								// downloadService.download(url).then(
								// 		function(success) {
								// 			$log.debug('success : ' + success);
								// 			// $scope.annulerAjout();
								// 		}, function(error) {
								// 			$log.debug('error : ' + error);
								// 		});
							};

							// generer rapport de tous les bons de livraison.
							// mode : List

							// conversion date en String
							function formattedDate(date) {
								var d = new Date(date), month = ''
										+ (d.getMonth() + 1), day = ''
										+ d.getDate(), year = d.getFullYear();

								if (month.length < 2)
									month = '0' + month;
								if (day.length < 2)
									day = '0' + day;
								return [ year, month, day ].join('-');
							}

							// download service

							$scope.downloadAllBonLiv = function(
									detbonLivraisonVenteCourant) {
								$scope.traitementEnCoursGenererDet = "true";	

								detbonLivraisonVenteCourant.referenceBs='';
								if($scope.checkboxModelRech.stock=="oui"){
									detbonLivraisonVenteCourant.stock=true;
								}else
									detbonLivraisonVenteCourant.stock=false
									

									console.log("etat stock generer: "+detbonLivraisonVenteCourant.stock);
								console
										.log("---Objet recherche : detbonLivraisonVenteCourant----"
												+ JSON
														.stringify(
																detbonLivraisonVenteCourant,
																null, " "));
								var newdateLivMinFormat = "";
								if (angular
										.isDefined(detbonLivraisonVenteCourant.dateLivraisonMin)) {
									$log
											.debug("==dateLivraisonMin "
													+ detbonLivraisonVenteCourant.dateLivraisonMin);

									if (detbonLivraisonVenteCourant.dateLivraisonMin != "") {
										newdateLivMinFormat = formattedDate(detbonLivraisonVenteCourant.dateLivraisonMin);
										$log.debug("===== newdateLivMinFormat "
												+ newdateLivMinFormat);
									} else {
										$log
												.debug("===== newdateLivMinFormat is Null");
										newdateLivMinFormat = "";
									}
								} else {
									$log.debug("==dateLivraisonMin Undefined");
								}

								var newdateLivMaxFormat = "";
								if (angular
										.isDefined(detbonLivraisonVenteCourant.dateLivraisonMax)) {
									$log
											.debug("==dateLivraisonMax "
													+ detbonLivraisonVenteCourant.dateLivraisonMax);

									if (detbonLivraisonVenteCourant.dateLivraisonMax != "") {
										newdateLivMaxFormat = formattedDate(detbonLivraisonVenteCourant.dateLivraisonMax);
										$log.debug("===== newdateLivMaxFormat "
												+ newdateLivMaxFormat);
									} else {
										$log
												.debug("===== newdateLivMaxFormat is Null");
										newdateLivMaxFormat = "";
									}
								} else {
									$log.debug("==dateLivraisonMax Undefined");
								}

								$log.debug("-- detbonLivraisonVenteCourant"
										+ JSON.stringify(
												detbonLivraisonVenteCourant, null,
												"  "));
								
								console.log("===============>download: idDepot:"+detbonLivraisonVenteCourant.idDepot);
								var url = UrlAtelier
										+ "/reportgc/listbonlivraison?referenceBl="
										+ detbonLivraisonVenteCourant.referenceBl
										+ "&referenceBs="
										+ detbonLivraisonVenteCourant.referenceBs
										+ "&partieIntId="
										+ detbonLivraisonVenteCourant.partieIntId
										+ "&dateLivraisonMin="
										+ newdateLivMinFormat
										+ "&dateLivraisonMax="
										+ newdateLivMaxFormat
										+ "&metrageMin="
										+ detbonLivraisonVenteCourant.qteDe
										+ "&metrageMax="
										+ detbonLivraisonVenteCourant.qteA
										+ "&prixMin="
										+ detbonLivraisonVenteCourant.prixDe
										+ "&prixMax="
										+ detbonLivraisonVenteCourant.prixA
										+ "&natureLivraison="
										+ detbonLivraisonVenteCourant.natureLivraison
										+ "&avecFacture="
										+ detbonLivraisonVenteCourant.avecFacture
										+ "&stock="+detbonLivraisonVenteCourant.stock
										+ "&idDepot="+detbonLivraisonVenteCourant.idDepot
										+ "&groupeClientId="+detbonLivraisonVenteCourant.groupeClientId
										+ "&numof="+detbonLivraisonVenteCourant.numOF
										+ "&type=pdf";

								$log.debug("--downloadAllBonLiv URL" + url);



										
								var a = document.createElement('a');
								document.body.appendChild(a);
								downloadService.download(url).then(function (result) {
									var heasersFileName = result.headers(['content-disposition']).substring(17);
									var fileName = heasersFileName.split('.');
								var typeFile = result.headers(['content-type']);
								var file = new Blob([result.data], {type: typeFile});
								var fileURL = window.URL.createObjectURL(file);
								if(typeFile == 'application/vnd.ms-excel'){
									a.href = fileURL;
									 a.download = fileName[0];
									//$window.open(fileURL)
									 a.click();
				
								}else{
									a.href = fileURL;
									a.download = fileName[0];
								 $window.open(fileURL)
									a.click();
				
								}
									
								$scope.traitementEnCoursGenererDet = "false";	

								});
				

								// downloadService.download(url).then(
								// 		function(success) {
								// 			$log.debug('success : ' + success);
								// 		}, function(error) {
								// 			$log.debug('error : ' + error);
								// 		});
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
															field : 'partieInteressee',
															displayName : 'Client',
															width : '20%'
														},
														{
															field : 'referenceBL',
															displayName : 'Ref.BL',
															width : '8%'
														},
														{
															field : 'date',
															displayName : 'Date',
															cellFilter : "date: 'yyyy-MM-dd'",
															width : '7%'
														},
														{
															field : 'depotDesignation',
															displayName : 'Magasin',
															width : '6%'
														},
														
														{
															field : 'produitReference',
															displayName : 'Ref.Produit',
															width : '10%'
														},
														{
															field : 'produitDesignation',
															displayName : 'Designation',
															width : '24%'
														},
														{
															field : 'quantite',
															displayName : 'Quantité',
															width : '8%'
														},
														{
															field : 'prixUnitaireHT',
															displayName : 'P.Unitaire',
															width : '8%'
														},
														{
															field : 'prixTotalHT',
															displayName : 'P.Total',
															width : '8%'
														},
														
														
														
														];
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
											var detbonLivraisonVenteCourant = $scope.detbonLivraisonVenteCourant;
											//detbonLivraisonVenteCourant.natureLivraison = "FINI";

											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																UrlAtelier
																		//+ "/bonlivraison/rechercheMulticritere",
																+ "/detlivraisonvente/rechercheMulticritereDetLivraison",
																detbonLivraisonVenteCourant)
														.success(
																function(largeLoad) {
																	data = largeLoad.listDetailLivraison
																	
																	
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
												//detbonLivraisonVenteCourant.natureLivraison = "FINI";
												$http
														.post(
																UrlAtelier
																		//+ "/bonlivraison/rechercheMulticritere",
																+ "/detlivraisonvente/rechercheMulticritereDetLivraison",
																detbonLivraisonVenteCourant)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.listDetailLivraison,
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
								enableColumnResize: true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};
							/** Fin de gestion des Grids de la BonVente */

						} ]);
