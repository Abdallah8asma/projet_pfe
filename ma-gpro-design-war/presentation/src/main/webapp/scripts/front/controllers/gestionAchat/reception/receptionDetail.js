'use strict'
angular
	.module('gpro.gcReceptionDetailAchat', [])
	.controller(
		'gcReceptionDetailAchatCtrl',
		[
			'$scope',
			'$http',
			'$filter',
			'$rootScope',
			'$log',
			'downloadService',
			'UrlAtelier',
			'UrlCommun',
			'$window',
			function ($scope, $http, $filter, $rootScope, $log,
				downloadService, UrlAtelier, UrlCommun,$window) {

				$log.info("----------- Achat BC ----------");
				// ** Variables Recherche
				$scope.listProduitReceptions = [];
				$scope.ListClientReceptionAchatCache = [];

				// **Variables Modif/Ajout
				// init objetCourant
				$scope.receptionAchatCourante = {
					"reference": "",
					"partieInteresseId": "",
					"idProduitParRef": "",
					"idProduitParDesignation": "",
					"dateIntroductionDu": "",
					"dateIntroductionA": "",
					"dateLivraisonDu": "",
					"dateLivraisonA": "",
					"quantiteDu": "",
					"quantiteA": "",
					"coutDu": "",
					"coutA": ""
				};
				$scope.listeProduitCache = [];
				$scope.listeDocumentReceptionAchat = [];
				// **Variable Grid
				$scope.myDataReceptionAchat = [];
				// bouton pdf hide
				$scope.modePdf = "notActive";

				$scope.displayMode = "list";

				$scope.traitementEnCoursGenererDetail = "false";
				/***************************************************
				 * Gestion de Cache des Referentiels Gestion
				 * Commerciale *
				 **************************************************/
				$scope.ListEtatReceptionAchatCache = [];
				$scope.ListTypeReceptionAchatCache = [];
				$scope.listeSitePartieInteresseeCache = [];
				$scope.ListTypeDocumentCache = [];
				$scope.ListSousFamilleProduitCache = [];




				$scope.consulterNumSerie = function () {

					var index = this.row.rowIndex;
					$scope.numSerieFilter = ''
					$scope.selectedProduitsSerialisable = $scope.myDataReceptionAchat[index].produitsSerialisable;
					$scope.selectedProduitReceptionDetail = $scope.myDataReceptionAchat[index];


				}


				//Ajouter numero serie au liste reception

				$scope.ajouterNumSerie = function (numero, index) {

					console.log("numero = ", numero);
					console.log("index = ", index);

					if ($scope.listProduitReceptions[index].produitsSerialisable) {

						var produitSerialisable = { numSerie: numero };

						$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
					} else {
						$scope.listProduitReceptions[index].produitsSerialisable = [];
						var produitSerialisable = { numSerie: numero };
						$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
					}

				}

				$scope.isSerialisable = function (produitId) {

					var element = $scope.listeProduitCache.filter(function (node) {
						return node.id == produitId;
					});
					//	$scope.produitCourante.tva =  element[0].tva;
					if (element && element[0]) return element[0].serialisable;
					else return false;
				}

				$scope.removeNumSerie = function (index, indexPS) {

					//	console.log("index = ",index);
					//	console.log("indexPS = ",indexPS);

					$scope.listProduitReceptions[index].produitsSerialisable.splice(indexPS, 1);

				}

				$scope.getReferenceArticleAndDesignation = function (produitId) {
					var element = $scope.listeProduitCache.filter(function (node) {
						return node.id == produitId;
					});
					//	$scope.produitCourante.tva =  element[0].tva;
					if (element && element[0]) return element[0].reference + " " + element[0].designation;

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

							});
				}

				// REST SERVICE MAGAZINS
				$scope.listeMagazinCache = function () {
					$http
						.get(UrlAtelier + "/magasin/depots")
						.success(
							function (dataMagazin) {

								$scope.listeMagazinCache = dataMagazin;
								$log
									.debug("listeMagazinCache : "
										+ dataMagazin.length)

							});
				}

				// Liste des SiteeCache
				$scope.listeSitesPartieInteresseeCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeSitePartieInteresseeCache")
						.success(
							function (dataSiteCache) {
								console
									.log("listSiteCache : "
										+ dataSiteCache.length);
								$scope.listeSitePartieInteresseeCache = dataSiteCache;

							});

				}

				// Liste TypeDocumentCache
				$scope.listeTypeDocumentsCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeTypeDocumentCache")
						.success(
							function (dataTypeDocumentCache) {
								$log
									.debug("*LISTTypeDocumentCache :"
										+ dataTypeDocumentCache.length);
								console
									.log("liste type doc:recept cache:"
										+ dataTypeDocumentCache.length);
								var dataTypeDocCache2 = [];
								for (var i = 0; i < dataTypeDocumentCache.length; i++) {
									console
										.log("dataTypeDocumentCache[i]: "
											+ dataTypeDocumentCache[i].module);
									if (dataTypeDocumentCache[i].module == "RECEPTION") {
										dataTypeDocCache2
											.push(dataTypeDocumentCache[i]);
										console
											.log("moduleid:"
												+ dataTypeDocumentCache[i].id
												+ " designation: "
												+ dataTypeDocumentCache[i].designation);

									}
								}

								$scope.listeTypeDocumentsCache = dataTypeDocCache2;

							});
				}

				// Liste des SousFamilleCache
				$scope.ListSousFamillesProduitCache = function () {
					$http
						.get(
							UrlCommun
							+ "/gestionnaireCache/listeSousFamilleProduitCache")
						.success(
							function (
								dataSousFamilleProduitCache) {
								$log
									.debug("*LISTESousFamilleProduitCache :"
										+ dataSousFamilleProduitCache.length);
								$scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;

							});
				}

				// Liste des Agent
				// $scope.ListAgent = function() {
				// $http
				// .get(baseUrlGc+"/agentGc/all")
				// .success(
				// function(data) {
				// console.log("listAgent: " + data.length);
				// $scope.listAgent = data;
				//
				// AchatCache();
				// $scope.listeEtatReceptionV });
				// }

				// Liste des produits
				$scope.listeProduitCache = function () {
					$http
						.get(
							UrlAtelier
							+ "/gestionnaireLogistiqueCache/listeProduitCache")
						.success(
							function (data) {
								console
									.log("listeProduitCache "
										+ data.length);
								$scope.listeProduitCache = data;

							});
				}

				$scope.listeFournisseursCache();
				$scope.listeMagazinCache();
				// $scope.listeTypeReceptionenteCache();
				$scope.listeSitesPartieInteresseeCache();
				$scope.listeTypeDocumentsCache();
				$scope.ListSousFamillesProduitCache();
				$scope.listeProduitCache();
				// $scope.ListAgent();
				/** ********************************************* */

				/** ********************************************* */
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13],
					pageSize: 13,
					currentPage: 1
				};

				$scope.cancelAddReceptionAchat = function (rowform,
					index, id, designation) {
					if (angular.isDefined(id)) {

						$scope.deleteValue = "non";
						rowform.$cancel();
						console.log("CANCEL");
					} else {
						$scope.deleteValue == "oui"
						$scope.listProduitReceptions.splice(index,
							1);
						rowform.$cancel();
					}
					$scope.deleteValue = "oui";
				}
				// ** Ajout Bon de Reception de Achat
				$scope.AffectationAchatBC = function () {
					$scope.receptionAchatCourante = {};

					// $scope.receptionAchatCourante = reception ?
					// angular
					// .copy(reception) : {};

					$scope.displayMode = "edit";
				}

				// Annulation de l'ajout
				$scope.annulerAjout = function () {
					$scope.traitementEnCoursGenererDetail = "false";

					$scope.cnt = 0;
					// bouton pdf hide
					$scope.modePdf = "notActive";
					// vider le tab
					$scope.varPrix = [{
						index: '',
						prix: ''
					}];
					// init objetCourant
					$scope.receptionAchatCourante = {
						"reference": "",
						"partieInteresseId": "",
						"idProduitParRef": "",
						"idProduitParDesignation": "",
						"dateIntroductionDu": "",
						"dateIntroductionA": "",
						"dateLivraisonDu": "",
						"dateLivraisonA": "",
						"quantiteDu": "",
						"quantiteA": "",
						"coutDu": "",
						"coutA": "",
						"idDepot": ""
					};
					$scope.rechercherReceptionAchat({});
					$scope.listProduitReceptions = [];
					$scope.creationSSForm.$setPristine();
					$scope.rechercheSSForm.$setPristine();
					$scope.displayMode = "list";
					$scope.closeNotif();
				}

				$scope.rechercherReceptionAchat = function (
					receptionAchatCourante) {
					receptionAchatCourante.type = 'Achat';
					$http
						.post(
							UrlAtelier
							+ "/receptionAchatDetail/rechercheMulticritere",
							receptionAchatCourante)
						.success(
							function (data) {

								$scope.myDataReceptionAchat = data.listProduitReceptionAchat;
							});
				}

				/** * PDF ** */
				// conversion date en String
				function formattedDate(date) {
					var d = new Date(date), month = ''
						+ (d.getMonth() + 1), day = ''
							+ d.getDate(), year = d.getFullYear();

					if (month.length < 2)
						month = '0' + month;
					if (day.length < 2)
						day = '0' + day;
					return [year, month, day].join('-');
				}

				// generer rapport apres creation d'un bon de
				// sortie. mode : Modification/Consultation
				$scope.download = function (id, pRapportPrix) {

					// init checkbox : 'non' :rapport sans Prix /
					// 'oui' rapport avec prix
					$scope.checkboxModel = {
						rapportPrix: "oui"
					};

					console.log("-- id" + id);
					var url = UrlAtelier
						+ "/reportgc/bonReception?id=" + id
						+ "&avecPrix=" + pRapportPrix
						+ "&type=pdf";
					downloadService.download(url)
						.then(
							function (success) {
								console.log('success : '
									+ success);
								// $scope.annulerAjout();
							},
							function (error) {
								console.log('error : '
									+ error);
							});
				};

				// generer rapport de tous les bons de livraison.
				// mode : List
				$scope.downloadAllReceptionAchat = function (
					receptionAchatCourante) {

					var newdateLivBCMinFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateLivraisonDu)) {

						if (receptionAchatCourante.dateLivraisonDu != "") {
							newdateLivBCMinFormat = formattedDate(receptionAchatCourante.dateLivraisonDu);
							console
								.log("===== newdateLivBCMinFormat "
									+ newdateLivBCMinFormat);
						} else {
							console
								.log("===== newdateLivBCMinFormat is Null");
							newdateLivBCMinFormat = "";
						}
					} else {
						console.log("==dateLivraisonDu Undefined");
					}

					var newdateLivBCMaxFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateLivraisonA)) {

						if (receptionAchatCourante.dateLivraisonA != "") {
							newdateLivBCMaxFormat = formattedDate(receptionAchatCourante.dateLivraisonA);
							// console.log("=====
							// newdateLivBCMaxFormat
							// "+newdateLivBCMaxFormat);
						} else {
							console
								.log("===== newdateLivBCMaxFormat is Null");
							newdateLivBCMaxFormat = "";
						}
					} else {
						console.log("==dateLivraisonA Undefined");
					}

					var newdateIntroBCMinFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateLivraisonDu)) {

						if (receptionAchatCourante.dateLivraisonDu != "") {
							newdateIntroBCMinFormat = formattedDate(receptionAchatCourante.dateLivraisonDu);
							console
								.log("===== newdateIntroBCMinFormat "
									+ newdateIntroBCMinFormats);
						} else {
							console
								.log("===== newdateIntroBCMinFormat is Null");
							newdateIntroBCMinFormat = "";
						}
					} else {
						console.log("==dateLivraisonDu Undefined");
					}

					var newdateIntroBCMaxFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateIntroductionA)) {

						if (receptionAchatCourante.dateIntroductionA != "") {
							newdateIntroBCMaxFormat = formattedDate(receptionAchatCourante.dateIntroductionA);
							console
								.log("===== newdateIntroBCMaxFormat "
									+ newdateIntroBCMaxFormat);
						} else {
							console
								.log("===== newdateIntroBCMaxFormat is Null");
							newdateIntroBCMaxFormat = "";
						}
					} else {
						console
							.log("==dateIntroductionA Undefined");
					}

					console.log("-- receptionAchatCourante"
						+ JSON.stringify(
							receptionAchatCourante, null,
							"  "));

					var url;
					console
						.log("PI  "
							+ receptionAchatCourante.vTypePartieInteressee);
					if (receptionAchatCourante.vTypePartieInteressee == null) {
						receptionAchatCourante.vTypePartieInteressee = "";
					}

					console.log("Produit  "
						+ receptionAchatCourante.vProduit);
					if (receptionAchatCourante.vProduit == null) {
						receptionAchatCourante.vProduit = "";
					}

					url = UrlAtelier
						+ "/report/listReceptionAchat?reference="
						+ receptionAchatCourante.vReference
						+ "&partieInteressee="
						+ receptionAchatCourante.vTypePartieInteressee
						+ "&produit="
						+ receptionAchatCourante.vProduit
						+ "&dateIntroductionDu="
						+ newdateIntroBCMinFormat
						+ "&dateIntroductionA="
						+ newdateIntroBCMaxFormat
						+ "&dateLivraisonDu="
						+ newdateLivBCMinFormat
						+ "&dateLivraisonA="
						+ newdateLivBCMaxFormat
						+ "&typeReception="
						+ receptionAchatCourante.vTypeReception
						+ "&etatReception="
						+ receptionAchatCourante.vEtatReception
						+ "&quantiteDu="
						+ receptionAchatCourante.quantiteDu
						+ "&quantiteA="
						+ receptionAchatCourante.quantiteA
						+ "&coutDu="
						+ receptionAchatCourante.coutDu
						+ "&coutA="
						+ receptionAchatCourante.coutA
						+ "&type=pdf";

					console.log("-- URL" + url);
					downloadService.download(url).then(
						function (success) {
							// console.log('success : ' +
							// success);
						}, function (error) {
							// console.log('error : ' + error);
						});
				};

				// generer rapport de tous les bons de livraison.
				// mode : List
				$scope.downloadAllReceptionAchatDetail = function (
					receptionAchatCourante) {

					$scope.traitementEnCoursGenererDetail = "true";

					var newdateLivBCMinFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateLivraisonDu)) {

						if (receptionAchatCourante.dateLivraisonDu != "") {
							newdateLivBCMinFormat = formattedDate(receptionAchatCourante.dateLivraisonDu);
							console
								.log("===== newdateLivBCMinFormat "
									+ newdateLivBCMinFormat);
						} else {
							console
								.log("===== newdateLivBCMinFormat is Null");
							newdateLivBCMinFormat = "";
						}
					} else {
						console.log("==dateLivraisonDu Undefined");
					}

					var newdateLivBCMaxFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateLivraisonA)) {

						if (receptionAchatCourante.dateLivraisonA != "") {
							newdateLivBCMaxFormat = formattedDate(receptionAchatCourante.dateLivraisonA);
							// console.log("=====
							// newdateLivBCMaxFormat
							// "+newdateLivBCMaxFormat);
						} else {
							console
								.log("===== newdateLivBCMaxFormat is Null");
							newdateLivBCMaxFormat = "";
						}
					} else {
						console.log("==dateLivraisonA Undefined");
					}

					var newdateIntroBCMinFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateLivraisonDu)) {

						if (receptionAchatCourante.dateLivraisonDu != "") {
							newdateIntroBCMinFormat = formattedDate(receptionAchatCourante.dateLivraisonDu);
							console
								.log("===== newdateIntroBCMinFormat "
									+ newdateIntroBCMinFormats);
						} else {
							console
								.log("===== newdateIntroBCMinFormat is Null");
							newdateIntroBCMinFormat = "";
						}
					} else {
						console.log("==dateLivraisonDu Undefined");
					}

					var newdateIntroBCMaxFormat = "";
					if (angular
						.isDefined(receptionAchatCourante.dateIntroductionA)) {

						if (receptionAchatCourante.dateIntroductionA != "") {
							newdateIntroBCMaxFormat = formattedDate(receptionAchatCourante.dateIntroductionA);

						} else {

							newdateIntroBCMaxFormat = "";
						}
					} else {

					}


					var url;



					console.log("Produit  "
						+ receptionAchatCourante.vProduit);
					if (receptionAchatCourante.vProduit == null) {
						receptionAchatCourante.vProduit = "";
					}

					url = UrlAtelier
						+ "/fichesAchat/reception-achat-detail-list?reference="
						+ receptionAchatCourante.reference
						+ "&partieInteresseId="
						+ receptionAchatCourante.partieInteresseId
						+ "&produitId="
						+ receptionAchatCourante.produitId
						+ "&dateIntroductionDu="
						+ newdateIntroBCMinFormat
						+ "&dateIntroductionA="
						+ newdateIntroBCMaxFormat

						+ "&idDepot="
						+ receptionAchatCourante.idDepot
						+ "&quantiteDu="
						+ receptionAchatCourante.quantiteDu
						+ "&quantiteA="
						+ receptionAchatCourante.quantiteA
						+ "&coutDu="
						+ receptionAchatCourante.coutDu
						+ "&coutA="
						+ receptionAchatCourante.coutA;


					console.log("-- URL" + url);


					console.log('iciiiiiiii stock')

					var fileName = '  Liste Bon Sortie	'		;					
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(22);
						var fileName = heasersFileName.split('.');

						 fileName[0] =  'Liste_Reception_Achat_Detail_' + formattedDate(new Date());

					var typeFile = result.headers(['content-type']);
					var file = new Blob([result.data], {type: typeFile});
					var fileURL = window.URL.createObjectURL(file);
					if(typeFile == 'application/vnd.ms-excel'){
						console.log('llll excel');
						a.href = fileURL;
						 a.download = fileName[0];
						//$window.open(fileURL)
						 a.click();
	
					}else{
						console.log('llll pdf');
						a.href = fileURL;
						a.download = fileName[0];
					 $window.open(fileURL)
						a.click();
	
					}
						
					$scope.traitementEnCoursGenererDetail = "false";

					});
	

					// downloadService.download(url).then(
					// 	function (success) {
					// 		// console.log('success : ' +
					// 		// success);
					// 	}, function (error) {
					// 		// console.log('error : ' + error);
					// 	});
				};

				/***************************************************
				 * Notifications
				 **************************************************/

				$scope.hiddenNotif = "true";

				$scope.showNotif = function () {
					$scope.hiddenNotif = "false";
				}

				$scope.closeNotif = function () {
					$scope.hiddenNotif = "true";
				}

				/***************************************************
				 * Gestion des Bon de reception
				 **************************************************/

				$scope.isLoading = false;

				// Ajout et Modification ReceptionAchat
				$scope.modifierOuCreerReceptionAchat = function () {

					$scope.isLoading = true;

					var index = this.row.rowIndex;
					// getReceptionAchat
					$http
						.get(
							UrlAtelier
							+ "/receptionAchatDetail/getById:"
							+ $scope.myDataReceptionAchat[index].id)
						.success(
							function (datagetReceptionAchat) {

								$log
									.debug("getById : "
										+ $scope.myDataReceptionAchat[index].id
										+ " \n  "
										+ JSON
											.stringify(
												datagetReceptionAchat,
												null,
												"  "));
								// produitReception
								$scope.listProduitReceptions = datagetReceptionAchat.listProduitReceptions;

								// disable update de la
								// dropList 'Produit'
								angular
									.forEach(
										$scope.listProduitReceptions,
										function (
											produitReception,
											key) {
											produitReception.checked = true;

											// $scope.updateProduitCommandDetails(produitReception);

											$scope.productFilter = [];
											$scope.sousFamilleFilter = [];

											$scope
												.getProductFilter(produitReception.produitId);

											if ($scope.productFilter.length > 0) {
												produitReception.designation = $scope.productFilter[0].designation;

												$scope
													.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);

												if ($scope.sousFamilleFilter.length > 0) {
													produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
												}
											}
										});

								// bouton PDF activé
								$scope.modePdf = "actif";
								$scope.isLoading = false;

								// document
								// $scope.listeDocumentReceptionAchat
								// =
								// datagetReceptionAchat.documentReceptionAchats;

								$scope.myDataReceptionAchat[index].listProduitReceptions = $scope.listProduitReceptions;
								// $scope.myDataReceptionAchat[index].documentReceptionAchats
								// =
								// $scope.listeDocumentReceptionAchat;
							});

					$scope.receptionAchatCourante = $scope.myDataReceptionAchat[index] ? angular
						.copy($scope.myDataReceptionAchat[index])
						: {};
					$scope.displayMode = "edit";
				}

				// Sauvegarder ReceptionAchat
				$scope.sauvegarderAjout = function (reception) {

					if (angular.isDefined(reception.id)) {
						$log.debug("Sauvegarder Modification"
							+ reception.id);
						$scope.updateReceptionAchat(reception);
					} else {
						$log.debug("Sauvegarder Ajout"
							+ reception.reference);
						$scope.creerReceptionAchat(reception);
					}
					// refresh de la liste
					// $scope.rechercherReceptionAchat({});
				}

				// Création d'un Bon de reception de Achat
				$scope.creerReceptionAchat = function (reception) {
					// produitReception
					reception.listProduitReceptions = $scope.listProduitReceptions;

					// DocumentProduitReception
					// reception.documentReceptionAchats =
					// $scope.listeDocumentReceptionAchat ;

					$log
						.debug("-- Create "
							+ JSON.stringify(reception,
								null, "  "));

					$http
						.post(
							UrlAtelier
							+ "/receptionAchatDetail/create",
							reception)
						.success(
							function (newReceptionId) {
								$log
									.debug("success creation : "
										+ newReceptionId);
								//
								// getReceptionAchat
								$http
									.get(
										UrlAtelier
										+ "/receptionAchatDetail/getById:"
										+ newReceptionId)
									.success(
										function (
											datagetReceptionAchat) {

											// produitReception
											$scope.listProduitReceptions = datagetReceptionAchat.listProduitReceptions;

											// set
											// content
											// of
											// reference
											// produit
											// et
											// sousFamille
											// designation

											// disable
											// update
											// de la
											// dropList
											// 'Produit'
											angular
												.forEach(
													$scope.listProduitReceptions,
													function (
														produitReception,
														key) {
														produitReception.checked = true;

														// $scope.updateProduitCommandDetails(produitReception);

														$scope.productFilter = [];
														$scope.sousFamilleFilter = [];

														$scope
															.getProductFilter(produitReception.produitId);

														if ($scope.productFilter.length > 0) {
															produitReception.designation = $scope.productFilter[0].designation;
															produitReception.prixUnitaire = $scope.productFilter[0].prixUnitaire;

															$scope
																.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);

															if ($scope.sousFamilleFilter.length > 0) {
																produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
															}
														}

														$scope
															.showNotif();
													});

											// bouton
											// PDF
											// activé
											$scope.modePdf = "actif";

											// document
											// $scope.listeDocumentReceptionAchat
											// =
											// datagetReceptionAchat.documentReceptionAchats;

											$scope.receptionAchatCourante = datagetReceptionAchat ? angular
												.copy(datagetReceptionAchat)
												: {};

										});
							});

				}

				// Mise à jour des Bons de Receptions de Achat
				$scope.updateReceptionAchat = function (reception) {
					// produitReception
					reception.listProduitReceptions = $scope.listProduitReceptions;

					// document
					// reception.documentReceptionAchats =
					// $scope.listeDocumentReceptionAchat ;

					$log
						.debug("Update "
							+ JSON.stringify(reception,
								null, "  "));

					$http
						.post(
							UrlAtelier
							+ "/receptionAchatDetail/update",
							reception)
						.success(
							function (receptionModifiee) {
								// for (var i = 0; i <
								// $scope.myDataReceptionAchat.length;
								// i++) {
								//
								// if
								// ($scope.myDataReceptionAchat[i].id
								// == receptionModifiee.id)
								// {
								// $scope.myDataReceptionAchat[i]
								// = receptionModifiee;
								// break;
								// }
								// }

								$log
									.debug("success Modification "
										+ receptionModifiee);

								// getReceptionAchat
								$http
									.get(
										UrlAtelier
										+ "/receptionAchatDetail/getById:"
										+ receptionModifiee)
									.success(
										function (
											datagetReceptionAchat) {
											// bouton
											// PDF
											// activé
											$scope.modePdf = "actif";

											// produitReception
											$scope.listProduitReceptions = datagetReceptionAchat.listProduitReceptions;

											// disable
											// update
											// de la
											// dropList
											// 'Produit'
											angular
												.forEach(
													$scope.listProduitReceptions,
													function (
														produitReception,
														key) {
														produitReception.checked = true;

														// $scope.updateProduitCommandDetails(produitReception);
														$scope.productFilter = [];
														$scope.sousFamilleFilter = [];

														$scope
															.getProductFilter(produitReception.produitId);

														if ($scope.productFilter.length > 0) {
															produitReception.designation = $scope.productFilter[0].designation;
															produitReception.prixUnitaire = $scope.productFilter[0].prixUnitaire;

															$scope
																.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);

															if ($scope.sousFamilleFilter.length > 0) {
																produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
															}
														}
													});

											// document
											// $scope.listeDocumentReceptionAchat
											// =
											// datagetReceptionAchat.documentReceptionAchats;

											$scope.receptionAchatCourante = datagetReceptionAchat ? angular
												.copy(datagetReceptionAchat)
												: {};

											$scope
												.showNotif();

										});

							});
				}

				// suppression BonReception
				$scope.supprimerReceptionAchat = function () {
					// var index = this.row.rowIndex;
					$http(
						{
							method: "DELETE",
							url: UrlAtelier
								+ "/ReceptionAchat/delete:"
								+ $scope.myDataReceptionAchat[$scope.index].id
						}).success(
							function () {
								$log.debug("Success Delete");
								$scope.myDataReceptionAchat.splice(
									$scope.index, 1);
							}).error(
								function () {
									$log.debug("Erreur");
									$scope.myDataReceptionAchat.splice(
										$scope.index, 1);
								});
					$scope.closePopupDelete();
					$scope.rechercherReceptionAchat({});
				};

				$scope.updateProduitCommandDetails = function (
					produitId, produitReception) {

					$scope.productFilter = [];
					$scope.sousFamilleFilter = [];

					$scope.getProductFilter(produitId);

					if ($scope.productFilter.length > 0) {
						produitReception.designation = $scope.productFilter[0].designation;

						$scope
							.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);

						if ($scope.sousFamilleFilter.length > 0) {
							produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
						}
					}
				}

				// Produit
				$scope.produitId = {
					status: ''
				};
				// SousFamilleProduit
				$scope.sousFamilleProduitId = {
					status: ''
				};

				// showProduitDetails
				$scope.showProduitDetails = function (produitId,
					attributRecherche) {

					$scope.produitId.status = produitId;
					var selected = $filter('showProduitFilterBS')(
						$scope.listeProduitCache, {
						id: $scope.produitId.status
					});
					if ($scope.produitId.status && selected.length) {
						if (attributRecherche == 'reference') {
							return selected[0].reference;
						} else if (attributRecherche == 'tissu') {
							return selected[0].designation;
						}
						if (attributRecherche == 'prix') {
							return selected[0].prixUnitaire;
						} else if (attributRecherche == 'type') {
							// conversion de sousFamilleId par son
							// designation
							$scope.sousFamilleProduitId.status = selected[0].sousFamilleId;
							var selected2 = $filter(
								'filterSousFamilleBS')
								(
									$scope.ListSousFamilleProduitCache,
									{
										id: $scope.sousFamilleProduitId.status
									});
							if ($scope.sousFamilleProduitId.status
								&& selected2.length) {
								return selected2[0].designation;
							} else {
								return "--";
							}
						}

					}
				}

				$scope.showBtnCalender = true;
				// show bottons Calender
				$scope.showBC = function () {
					$scope.showBtnCalender = true;
				}

				$scope.type = {
					status: ''
				};

				$scope.famille = {
					status: ''
				};

				$scope.varPrix = [{
					index: '',
					prix: ''
				}];

				$scope.changePrix = function (event, item, index) {

				}

				$scope.getProductFilter = function (idProduit) {

					console.log("idProduit" + idProduit);
					$scope.productFilter = $filter('filter')(
						$scope.listeProduitCache, {
						id: idProduit
					});

					console.log("productFilter"
						+ JSON.stringify($scope.productFilter,
							null, " "));

				}

				$scope.getSousFamilleFilter = function (
					sousFamilleId) {
					$scope.sousFamilleFilter = $filter('filter')(
						$scope.ListSousFamilleProduitCache, {
						id: sousFamilleId
					});
				}

				$scope.clickProduit = function (idProduit, index) {

					console.log("idProduit clickProduit"
						+ idProduit);

					$scope.updateProduitCommandDetails(idProduit,
						$scope.listProduitReceptions[index]);

				}

				// ajout d'un Produit
				$scope.ajoutProduit = function () {

					$scope.produitInserree = {
						produitId: '',
						quantite: '',
						prix: '',
						receptionAchatId: '',
						'checked': false

					};

					$scope.listProduitReceptions
						.push($scope.produitInserree);

				};

				// Enregistrer Produit
				$scope.saveProduit = function (dataProduit, id) {
					$scope.deleteValue = "non";

					angular.extend(dataProduit, {
						id: id
					});
					$scope.showBtnCalender = false;
				};

				// Supprimer Produit
				$scope.removeProduit = function (index) {
					$scope.listProduitReceptions.splice(index, 1);
					console.log("Success Delete Produit ");
				};

				/** Fin de gestion des Produit */

				/***************************************************
				 * Gestion des DocumentReceptionAchat
				 **************************************************/
				$scope.name = "BCV";

				$scope.listeDocumentReceptionAchat = [];

				// GetId.designation
				$scope.doc = {

					status: ''
				};
				$scope.showStatus = function (id) {

					$scope.doc.status = id;
					var selected = $filter('filter')(
						$scope.ListTypeDocumentCache, {
						id: $scope.doc.status
					});
					if ($scope.doc.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				};

				// ajout d'un DocumentReceptionAchat
				$scope.ajoutDocumentReceptionAchat = function () {

					$scope.documentReceptionAchatInserree = {

						typeDocumentId: '',
						uidDocument: '',
						path: ''

					};
					$scope.listeDocumentReceptionAchat
						.push($scope.documentReceptionAchatInserree);

				};

				// Enregistrer DocumentReceptionAchat
				$scope.saveDocumentReceptionAchat = function (
					dataDocumentReceptionAchat, id) {
					console.log("**SAVE DOC "
						+ dataDocumentReceptionAchat);
					$scope.deleteValue = "non";
					angular.extend(dataDocumentReceptionAchat, {
						id: id
					});
				};

				// Supprimer DocumentReceptionAchat
				$scope.removeDocumentReceptionAchat = function (
					index) {
					$scope.listeDocumentReceptionAchat.splice(
						index, 1);
				};

				// Download Document
				$scope.download = function (uuid) {
					var url = UrlCommun
						+ "/gestionnaireDocument/document/"
						+ uuid;

					downloadService.download(url).then(
						function (success) {
							$log.debug('success : ' + success);
						}, function (error) {
							$log.debug('error : ' + error);
						});
				};
				/** Fin de gestion des DocumentProduit */
				/** Fin de gestion des DocumentReceptionAchat */
				/** Fin de gestion des DocumentReceptionAchat */
				/***************************************************
				 * Gestion de la Grid Bon de Reception de Achat
				 **************************************************/
				$scope.typeAlert = 3;

				$scope.colDefs = [];
				$scope
					.$watch(
						'myDataReceptionAchat',
						function () {
							$scope.colDefs = [
								/*{
									field : '',
									headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
									cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
									width : '3%'
								},*/
								{
									field: 'receptionAchatValue.reference',
									displayName: 'Réf.B Réception',
									//width : '10%'
								},
								{
									field: 'receptionAchatValue.partieIntersseAbbreviation',
									displayName: 'Fournisseur',
									//	width : '15%'
								},
								{
									field: 'receptionAchatValue.dateIntroduction',
									displayName: 'Date Intro',
									cellFilter: 'date:\'dd-MM-yyyy\'',
									//		width : '7%'
								},
								{
									field: 'receptionAchatValue.dateLivraison',
									displayName: 'Date Réception',
									cellFilter: 'date:\'dd-MM-yyyy\'',
									//	width : '7%'
								},
								{
									field: 'produitValue.reference',
									displayName: 'Réf. Article',
									//	width : '10%'
								},
								{
									field: 'produitValue.designation',
									displayName: 'Dés. Article',
									//	width : '28%'
								},

								{
									field: 'quantite',
									displayName: 'Quantite',
									//	width : '10%'
								},
								{
									field: 'prixUnitaire',
									displayName: 'Prix Unitaire',
									//	width : '10%'
								},

								{

									//width :'3%',
									cellTemplate:




										'<div class="buttons pull-right" ng-if="row.entity.serialisable == true"><button data-nodrag class="fa fa-barcode" ng-click="consulterNumSerie()" data-target="#SeriesModalConsult" data-toggle="modal"></button></div>'
								}/*,
														{
															field : '',
															width : '5%',
															cellTemplate : '<div class="buttons">'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerReceptionAchat()"><i class="fa fa-fw fa-pencil"></i></button>'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(12)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
														} */];
						});

				$scope.filterOptions = {
					filterText: "",
					useExternalFilter: true
				};

				$scope.totalServerItems = 0;

				$scope.setPagingData = function (data, page,
					pageSize) {

					var pagedData = data.slice((page - 1)
						* pageSize, page * pageSize);
					$scope.myDataReceptionAchat = pagedData;
					$scope.totalServerItems = data.length;
					if (!$scope.$$phase) {
						$scope.$apply();
					}
					$scope.isLoading = false;
				};

				$scope.getPagedDataAsync = function (pageSize, page,
					searchText) {
					setTimeout(
						function () {
							var data;
							var receptionAchatCourante = $scope.receptionAchatCourante;
							receptionAchatCourante.type = 'Achat';
							if (searchText) {
								var ft = searchText
									.toLowerCase();
								$scope.isLoading = true;
								$http
									.post(
										UrlAtelier
										+ "/receptionAchatDetail/rechercheMulticritere",
										receptionAchatCourante)
									.success(
										function (
											largeLoad) {
											$scope.myDataReceptionAchat = largeLoad.listProduitReceptionAchat
												.filter(function (
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

								$scope.isLoading = true;

								$http
									.post(
										UrlAtelier
										+ "/receptionAchatDetail/rechercheMulticritere",
										receptionAchatCourante)
									.success(
										function (
											largeLoad) {

											console
												.log("largeLoad"
													+ JSON
														.stringify(
															largeLoad,
															null,
															" "));
											$scope
												.setPagingData(
													largeLoad.listProduitReceptionAchat,
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
						function (newVal, oldVal) {
							if (newVal !== oldVal
								&& newVal.currentPage !== oldVal.currentPage) {
								$scope
									.getPagedDataAsync(
										$scope.pagingOptions.pageSize,
										$scope.pagingOptions.currentPage,
										$scope.filterOptions.filterText);
							}
						}, true);
				$scope.$watch('filterOptions', function (newVal,
					oldVal) {
					if (newVal !== oldVal) {
						$scope.getPagedDataAsync(
							$scope.pagingOptions.pageSize,
							$scope.pagingOptions.currentPage,
							$scope.filterOptions.filterText);
					}
				}, true);

				$scope.gridOptions = {
					data: 'myDataReceptionAchat',
					columnDefs: 'colDefs',
					enablePaging: true,
					enableColumnResize: true,
					showFooter: true,
					enableHighlighting: true,
					totalServerItems: 'totalServerItems',
					pagingOptions: $scope.pagingOptions,
					selectedItems: $scope.selectedRows,
					filterOptions: $scope.filterOptions,
				};

				/** Fin de gestion des Grids Achat BC */
			}]);
/*
 * .filter('showProduitFilterBS', function() { return function(listeProduit, id) {
 * var listeProduitFiltre = []; console.log("IdProduitt= "+id.id);
 * console.log("listeProduit "+ JSON.stringify(listeProduit, null, " "));
 * angular.forEach(listeProduit, function(produit, key){
 *
 * if(produit.id == id.id){ console.log(produit.id +" == "+ id.id);
 * listeProduitFiltre.push(produit); }
 *
 * }); // console.log(" ** listeProduitFiltre "+
 * JSON.stringify(listeProduitFiltre, null, " ")); return listeProduitFiltre; }; })
 *
 * .filter('filterSousFamilleBS', function() { return function(listeSousFamille,
 * id) { var listeSousFamilleFiltre = []; // console.log("IdSousFamille=
 * "+id.id); // console.log("listeSousFamille "+
 * JSON.stringify(listeSousFamille, null, " "));
 * angular.forEach(listeSousFamille, function(sousFamille, key){
 *
 * if(sousFamille.id == id.id){ console.log(sousFamille.id +" == "+ id.id);
 * listeSousFamilleFiltre.push(sousFamille); }
 *
 * }); // console.log(" ** listeSousFamilleFiltre "+
 * JSON.stringify(listeSousFamilleFiltre, null, " ")); return
 * listeSousFamilleFiltre; }; });
 */
