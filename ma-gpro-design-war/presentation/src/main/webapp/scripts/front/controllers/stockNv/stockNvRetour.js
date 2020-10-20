'use strict'
angular
	.module('gpro.stockNvRetour', [])
	.controller(
		'stockNvRetourController',
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
				downloadService, UrlAtelier, UrlCommun, $window) {

				$log.info("----------- Achat BC ----------");
				// ** Variables Recherche
				$scope.listProduitReceptions = [];
				$scope.ListClientReceptionAchatCache = [];

				$scope.responseMsgDeleteReceptionAchat = "";

				$scope.refBRIsInvalid = "false";

				$scope.myStyleBtnValiderAvoir = {

					"background-color": "green"

				};

				$scope.validerAvoirRetourSucess = false;
				$scope.validerBLSucess = false;

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
					"coutA": "",
					"idDepot": ""
				};

				$scope.listePartieInteresseeClient = [];

				$scope.listTaxeReceptionAchat = [
					/*
					 * {//FODEC taxeId: 1, pourcentage: 1,
					 * montant: '', },
					 */
					{// TVA19
						taxeId: 2,
						pourcentage: 19,
						montant: '',
					},
					{// TVA7
						taxeId: 4,
						pourcentage: 7,
						montant: '',
					},
					{// TVA13
						taxeId: 5,
						pourcentage: 13,
						montant: '',
					}];
				$scope.listeProduitCache = [];
				$scope.listeDocumentReceptionAchat = [];
				// **Variable Grid
				$scope.myDataReceptionAchat = [];
				// bouton pdf hide
				$scope.modePdf = "notActive";

				$scope.displayMode = "list";

				/***************************************************
				 * Gestion de Cache des Referentiels Gestion
				 * Commerciale *
				 **************************************************/
				$scope.ListEtatReceptionAchatCache = [];
				$scope.ListTypeReceptionAchatCache = [];
				$scope.listeSitePartieInteresseeCache = [];
				$scope.ListTypeDocumentCache = [];
				$scope.ListSousFamilleProduitCache = [];


				// champ autoSaisie du champs: referenceBC
				$scope.select2TaggingOptions = {
					'multiple': true,
					'simple_tags': true,
					'tags': function () {
						// reload de la liste des RefBC
						$scope.listNewReferenceBC = [];

						$scope.listNewReferenceBC = $scope.listReferenceBC;

						$log.debug("----OnClicklistNewReferenceBC : " + JSON.stringify($scope.listNewReferenceBC, null, "    "));
						console.log("----OnClicklistNewReferenceBC : " + JSON.stringify($scope.listNewReferenceBC, null, "    "));

						return $scope.listNewReferenceBC;
					}

				};


				/**  valider reference Avoir Retour  **/


				$scope.validerAvoirRetour = function (ref) {

					$scope.listProduitReceptions = [];

					$scope.refAvoirRetourIsInvalid = false;
					$scope.isEnCoursValider = true;

					$http
						.get(
							UrlAtelier
							+ "/receptionAchat/validerFactureAvoirRetour:" + ref)
						.success(
							function (receptionAchat) {

								if (receptionAchat.refAvoirRetour == null) {

									$scope.refAvoirRetourIsInvalid = true;

								} else {
									$scope.validerAvoirRetourSucess = true;
									$scope.receptionAchatCourante.dateLivraison = receptionAchat.dateLivraison;
									$scope.listProduitReceptions = receptionAchat.listProduitReceptions;
									$scope.receptionAchatCourante.partieIntersseId = receptionAchat.partieIntersseId;
									$scope.receptionAchatCourante.idDepot = receptionAchat.idDepot;
									$scope.receptionAchatCourante.refFacture = receptionAchat.refFacture;

								}


								$scope.isEnCoursValider = false;

							});

				}

				/**  valider reference BL  **/


				$scope.validerBL = function (ref) {

					$scope.listProduitReceptions = [];

					$scope.refAvoirRetourIsInvalid = false;
					$scope.isEnCoursValider = true;

					$http
						.get(
							UrlAtelier
							+ "/receptionAchat/validerByRefBL:" + ref)
						.success(
							function (receptionAchat) {

								if (receptionAchat.refBL == null) {

									$scope.refBLIsInvalid = true;

								} else {
									$scope.validerBLSucess = true;
									$scope.receptionAchatCourante.dateLivraison = receptionAchat.dateLivraison;
									$scope.listProduitReceptions = receptionAchat.listProduitReceptions;
									$scope.receptionAchatCourante.partieIntersseId = receptionAchat.partieIntersseId;
									$scope.receptionAchatCourante.idDepot = receptionAchat.idDepot;
								}


								$scope.isEnCoursValider = false;

							});

				}

				//get Available commande

				$scope.listReferenceBC = [];

				$scope.getAvailableRefBCByFournisseur = function (idClient) {
					$scope.listReferenceBC = [];
					$scope.listeMarche = [];

					if (angular.isDefined(idClient)) {
						if (idClient != null) {


							// TODO SEARCH TYPE PI
							/*	var element = $scope.listePartieInteresseeCache.filter(function(node) {
											return node.id==idClient;
									});
						
								$scope.bonLivraisonVenteCourant.typePartieInteressee = element[0].typePartieInteressee;
								
								$scope.bonLivraisonVenteCourant.groupeClientId = element[0].groupeClientId;
								
								*/

							$http
								.get(
									UrlAtelier
									+ "/commandeAchat/getAvailableListBonCommandeRefByFournisseur:" + idClient)
								.success(
									function (resultat) {
										//$log.debug("----ResultatListBC "+resultat.length);

										angular.forEach(resultat, function (element, key) {
											//console.log("==>elemet: "+element.reference);
											$scope.listReferenceBC.push(element.reference);


										});


									});
						}



					}
				}


				$scope.articleSerialisableHasBL = function (produitsSerialisable) {

					let resultIs = false;


					if (produitsSerialisable) {


						angular.forEach(produitsSerialisable, function (element, key) {



							if (element.numBonLivraison != null) {

								resultIs = true;
							}



						});



					}

					return resultIs;
				}

				$scope.getAbreviationPIbyId = function (clientId) {


					if (clientId) {

						var element = $scope.listePartieInteresseeClient.filter(e => e.id === clientId);

						if (element && element[0])
							return element[0].raisonSociale;

					}


				}


				//Ajouter numero serie au liste reception

				$scope.ajouterNumSerie = function (numero, index) {

					//console.log("numero = ",numero);
					//console.log("index = ",index);

					if ($scope.listProduitReceptions[index].produitsSerialisable) {

						var produitSerialisable = { numSerie: numero };

						//if (!$scope.listProduitReceptions[index].produitsSerialisable.some(e => e.numSerie === numero))	

						if ($scope.listProduitReceptions[index].produitsSerialisable.filter(e => e.numSerie === numero).length == 0)
							$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
					} else {
						$scope.listProduitReceptions[index].produitsSerialisable = [];
						var produitSerialisable = { numSerie: numero };
						$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
					}

				}
				//Ajouter numero serie au liste reception appel WS	
				$scope.ajouterNumSerieWS = function (numero, index) {



					var produitCouranteRech = {
						"numSerie": numero,
						"critereSpeciale": "produit-non-vendue"
					};
					$http.post(
						UrlCommun
						+ "/produitSerialisable/rechercheProduitSerialisableMulticritere",
						produitCouranteRech)
						.success(
							function (resultat) {


								if (resultat.produitSerialisableValues.length > 0) {

									var produitSerialisable = resultat.produitSerialisableValues[0];

									if ($scope.listProduitReceptions[index].produitsSerialisable) {

										if ($scope.listProduitReceptions[index].produitsSerialisable.filter(e => e.numSerie === numero).length == 0) {

											$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
											$scope.numSerie = "";
										}


									} else {
										$scope.listProduitReceptions[index].produitsSerialisable = [];

										$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
									}



								}



							});



				}

				$scope.isValidSauvegarde = function () {

					let resultIsValidSauvegarde = true;
					angular.forEach($scope.listProduitReceptions, function (element, key) {


						if ($scope.isSerialisable(element.produitId)) {
							if (element.produitsSerialisable == null) {

								resultIsValidSauvegarde = false;
							}
							else
								if (element.quantite !== element.produitsSerialisable.length)
									resultIsValidSauvegarde = false;
						}


					});

					return resultIsValidSauvegarde;
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

								$scope.listePartieInteresseeClient = $filter(
									'filter')
									(
										dataPartieInteressee,
										{
											famillePartieInteressee: 1
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
					$scope.validerAvoirRetourSucess = false;
					$scope.receptionAchatCourante = { "dateIntroduction": new Date() };
					$scope.listTaxeReceptionAchat = [
						/*
						 * {//FODEC taxeId: 1, pourcentage: 1,
						 * montant: '', },
						 */
						{// TVA19
							taxeId: 2,
							pourcentage: 19,
							montant: '',
						},
						{// TVA7
							taxeId: 4,
							pourcentage: 7,
							montant: '',
						},
						{// TVA13
							taxeId: 5,
							pourcentage: 13,
							montant: '',
						}];

					$scope.receptionAchatCourante.idDepot = $scope.listeMagazinCache.length > 0 ? $scope.listeMagazinCache[0].id : '';


					// $scope.receptionAchatCourante = reception ?
					// angular
					// .copy(reception) : {};


					$http
						.get(
							UrlAtelier
							+ "/receptionAchat/getCurrentReference"
						)
						.success(
							function (data) {
								$scope.receptionAchatCourante.reference = data;
								$scope.receptionAchatCourante.refAvantChangement = data;

							}
						);





					$scope.displayMode = "edit";
				}

				// Annulation de l'ajout
				$scope.annulerAjout = function () {
					$scope.validerAvoirRetourSucess = false;
					$scope.validerBLSucess = false;
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




					$scope.isEnCoursValider = false;
					$scope.rechercherReceptionAchat({});
					$scope.listProduitReceptions = [];
					$scope.creationSSForm.$setPristine();
					$scope.rechercheSSForm.$setPristine();
					$scope.displayMode = "list";
					$scope.closeNotif();
				}

				$scope.rechercherReceptionAchat = function (
					receptionAchatCourante) {

					receptionAchatCourante.type = 'Retour';
					$http
						.post(
							UrlAtelier
							+ "/receptionAchat/rechercheMulticritere",
							receptionAchatCourante)
						.success(
							function (data) {

								$scope.myDataReceptionAchat = data.listReceptionAchat;
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
				$scope.downloadReport = function (id, pRapportPrix) {

					// init checkbox : 'non' :rapport sans Prix /
					// 'oui' rapport avec prix
					$scope.checkboxModel = {
						rapportPrix: "oui"
					};

					console.log("-- id" + id);
					var url = UrlAtelier
						+ "/reportAchat/bonReceptionAchat?id=" + id
						+ "&avecPrix=" + pRapportPrix
						+ "&type=pdf"
						;


					//var fileName = 'Liste Stock Retour ';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');
						var typeFile = result.headers(['content-type']);
						var file = new Blob([result.data], { type: typeFile });
						var fileURL = window.URL.createObjectURL(file);
						if (typeFile == 'application/vnd.ms-excel') {

							// a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						} else {

							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						}


					});



					// downloadService.download(url)
					// 	.then(
					// 		function (success) {
					// 			console.log('success : '
					// 				+ success);
					// 			// $scope.annulerAjout();
					// 		},
					// 		function (error) {
					// 			console.log('error : '
					// 				+ error);
					// 		});
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
						+ "/report/listReceptionRetour?reference="
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





					//	var fileName = 'Liste Stock Retour ';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');



						var typeFile = result.headers(['content-type']);
						var file = new Blob([result.data], { type: typeFile });
						var fileURL = window.URL.createObjectURL(file);
						if (typeFile == 'application/vnd.ms-excel') {

							// a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						} else {

							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						}


					});
					// downloadService.download(url).then(
					// 	function (success) {
					// 		// console.log('success : ' +
					// 		// success);
					// 	}, function (error) {
					// 		// console.log('error : ' + error);
					// 	});
				};



				$scope.downloadAllReceptionAchatExcel = function (
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


					var newIdDepot = "";
					if (angular
						.isDefined(receptionAchatCourante.idDepot)) {
						newIdDepot = receptionAchatCourante.idDepot;
					}



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
						+ "/fichesAchat/listReceptionRetour?reference="
						+ receptionAchatCourante.reference
						+ "&partieInteresseId="
						+ receptionAchatCourante.partieInteresseId
						+ "&dateIntroductionDu="
						+ newdateIntroBCMinFormat
						+ "&dateIntroductionA="
						+ newdateIntroBCMaxFormat
						+ "&idDepot="
						+ newIdDepot

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




					//	var fileName = 'Liste Stock Retour ';
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');
						fileName[0] = 'Liste_Stock_Retour_' + formattedDate(new Date());
						var typeFile = result.headers(['content-type']);
						var file = new Blob([result.data], { type: typeFile });
						var fileURL = window.URL.createObjectURL(file);
						if (typeFile == 'application/vnd.ms-excel') {
							a.href = fileURL;
							a.download = fileName[0];
							//$window.open(fileURL)
							a.click();

						} else {

							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						}
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
							+ "/receptionAchat/getById:"
							+ $scope.myDataReceptionAchat[index].id)
						.success(
							function (datagetReceptionAchat) {

								if (datagetReceptionAchat.refCommande) {

									var refBC = datagetReceptionAchat.refCommande.split("-");
									$scope.tagReferenceBLivList = refBC;
								}


								/*$log
										.debug("getById : "
												+ $scope.myDataReceptionAchat[index].id
												+ " \n  "
												+ JSON
														.stringify(
																datagetReceptionAchat,
																null,
																"  "));*/
								// produitReception
								$scope.listProduitReceptions = datagetReceptionAchat.listProduitReceptions;

								$scope.listSuppDetReceptionAchat = [];

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

					var dateLivraison = null;
					var dateIntroduction = null;
					if ($scope.myDataReceptionAchat[index].dateLivraison !== null) {
						dateLivraison = $scope.modifierFormatDateStockRetour($scope.myDataReceptionAchat[index].dateLivraison);
					} else {
						dateLivraison = null;
					}


					if ($scope.myDataReceptionAchat[index].dateIntroduction !== null) {
						dateIntroduction = $scope.modifierFormatDateStockRetour($scope.myDataReceptionAchat[index].dateIntroduction);
					} else {
						dateIntroduction = null;
					}



					$scope.receptionAchatCourante = Object.assign($scope.myDataReceptionAchat[index], { dateLivraison: dateLivraison }, { dateIntroduction: dateIntroduction })
						//  $scope.partieInteresseeCourante = $scope.myData[index]
						? angular.copy($scope.myDataReceptionAchat[index])
						: {};

					/*	$scope.receptionAchatCourante = $scope.myDataReceptionAchat[index] ? angular
							.copy($scope.myDataReceptionAchat[index])
							: {};*/
					$scope.displayMode = "edit";
				}



				$scope.modifierFormatDateStockRetour = function (dateUp) {
					const dateTimeFormat = new Intl.DateTimeFormat("en", {
						year: "numeric",
						month: "numeric",
						day: "2-digit",
					});
					var [
						{ value: month },
						,
						{ value: day },
						,
						{ value: year }
					] = dateTimeFormat.formatToParts(dateUp);
					return $scope.dateParEdition = `${year}-${month}-${day}`;

				}

				// Sauvegarder ReceptionAchat
				$scope.sauvegarderAjout = function (reception) {


					$scope.refBRIsInvalid = "false";


					if (reception.reference) {
						if (reception.reference.indexOf("-") != -1) {
							$scope.refBRIsInvalid = "true";
							return;
						}
					}


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
					reception.type = 'Retour';

					//reception.refCommande = $scope.tagReferenceBLivList.join('-');


					// produitReception
					reception.listProduitReceptions = $scope.listProduitReceptions;

					reception.listTaxeReceptionAchat = $scope.listTaxeReceptionAchat;

					// DocumentProduitReception
					// reception.documentReceptionAchats =
					// $scope.listeDocumentReceptionAchat ;

					/*$log
							.debug("-- Create "
									+ JSON.stringify(reception,
											null, "  "));*/

					$http
						.post(
							UrlAtelier
							+ "/receptionAchat/create",
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
										+ "/receptionAchat/getById:"
										+ newReceptionId)
									.success(
										function (
											datagetReceptionAchat) {

											// produitReception
											$scope.listProduitReceptions = datagetReceptionAchat.listProduitReceptions;

											$scope.listTaxeReceptionAchat = datagetReceptionAchat.listTaxeReceptionAchat;

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
															//produitReception.prixUnitaire = $scope.productFilter[0].prixUnitaire;

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

					reception.refCommande = $scope.tagReferenceBLivList.join('-');

					// produitReception
					reception.listProduitReceptions = $scope.listProduitReceptions;

					reception.listSuppDetReceptionAchat = $scope.listSuppDetReceptionAchat;

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
							+ "/receptionAchat/update",
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
										+ "/receptionAchat/getById:"
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

											$scope.listSuppDetReceptionAchat = [];

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
															//produitReception.prixUnitaire = $scope.productFilter[0].prixUnitaire;

															$scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);


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

					$scope.responseMsgDeleteReceptionAchat = "";

					// var index = this.row.rowIndex;
					$http(
						{
							method: "DELETE",
							url: UrlAtelier
								+ "/receptionAchat/delete:"
								+ $scope.myDataReceptionAchat[$scope.index].id
						}).success(
							function (response) {
								$log.debug("Success Delete", response);

								if (response.msg == "OK") {

									$scope.closePopupDelete();
									$scope.responseMsgDeleteReceptionAchat = "";

									$scope.rechercherReceptionAchat({});

									$scope.myDataReceptionAchat.splice(
										$scope.index, 1);
								}

								else {

									$scope.responseMsgDeleteReceptionAchat = "Une ou plus. BL exsite avec cette réception";

								}






							}).error(
								function () {
									$log.debug("Erreur");
									$scope.myDataReceptionAchat.splice(
										$scope.index, 1);
								});

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

						produitReception.prixUnitaire = $scope.productFilter[0].prixAchat;

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

					//console.log("idProduit" + idProduit);
					$scope.productFilter = $filter('filter')(
						$scope.listeProduitCache, {
						id: idProduit
					});

					/*console.log("productFilter"
							+ JSON.stringify($scope.productFilter,
									null, " "));*/

				}

				$scope.getSousFamilleFilter = function (
					sousFamilleId) {
					$scope.sousFamilleFilter = $filter('filter')(
						$scope.ListSousFamilleProduitCache, {
						id: sousFamilleId
					});
				}

				$scope.clickProduit = function (idProduit, index) {

					/*console.log("idProduit clickProduit"
							+ idProduit);*/

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
					$scope.listSuppDetReceptionAchat.push($scope.listProduitReceptions[index]);
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
								/*	{
										field : '',
										headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
										cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
										width : '3%'
									},*/
								{
									field: 'reference',
									displayName: 'Réf.Retour',
									//	width : '10%'
								},
								{
									field: 'dateIntroduction',
									displayName: 'Date Retour',
									cellFilter: 'date:\'dd-MM-yyyy\'',
									//	width : '7%'
								},
								{
									field: 'partieIntersseAbbreviation',
									displayName: 'Client',
									//		width : '20%'
								},

								{
									field: 'dateLivraison',
									displayName: 'Date',
									cellFilter: 'date:\'dd-MM-yyyy\'',
									//	width : '7%'
								},
								{
									field: 'refAvoirRetour',
									displayName: 'Réf.Avoir',
									//	width : '10%'
								},
								{
									field: 'refFacture',
									displayName: 'Réf.Facture',
									//	width : '10%'
								},
								{
									field: 'refBL',
									displayName: 'Réf.BL',
									//	width : '10%'
								},


								// {
								// field :
								// 'dateLivraison',
								// displayName : 'Date
								// Liv.',
								// cellFilter:
								// 'date:\'dd-MM-yyyy\'',
								// width:'15%'
								// },
								{
									field: 'quantite',
									displayName: 'Quantite',
									//width : '10%'
								},


								{
									field: 'prixTotal',
									displayName: 'Montant HT',
									cellFilter: 'prixFiltre',
									//	width:'10%'
								},
								/*{
									field : 'montantTaxe',
									displayName : 'Mont. Taxe',
									cellFilter : 'prixFiltre',
									width:'10%'
								},
								{
									field : 'montantTTC',
									displayName : 'Montant TTC',
									cellFilter : 'prixFiltre',
									width:'10%'
								},*/

								{
									field: '',
									fontfamily: 'Poppins, Helvetica, sans-serif',
									//	width : '5%',
									cellTemplate: `<div class="ms-CommandButton float-right">
								<button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerReceptionAchat()">
									<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
									</button>
									<button class="ms-CommandButton-button"  ng-click="showPopupDelete(17)" permission="['stock_Delete']">
								<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
									</button>
										</div> `








									// '<div class="buttons">'
									// 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerReceptionAchat()"><i class="fa fa-fw fa-pencil"></i></button>'
									// 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(17)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
								}];
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
							receptionAchatCourante.type = 'Retour';
							if (searchText) {
								var ft = searchText
									.toLowerCase();
								$scope.isLoading = true;
								$http
									.post(
										UrlAtelier
										+ "/receptionAchat/rechercheMulticritere",
										receptionAchatCourante)
									.success(
										function (
											largeLoad) {
											$scope.myDataReceptionAchat = largeLoad.receptionAchatValues
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
										+ "/receptionAchat/rechercheMulticritere",
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
													largeLoad.listReceptionAchat,
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
					showFooter: true,
					enableColumnResize: true,
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
