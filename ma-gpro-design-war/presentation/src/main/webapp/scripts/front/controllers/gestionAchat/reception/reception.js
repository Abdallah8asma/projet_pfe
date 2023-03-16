'use strict'
angular
	.module('gpro.gcReceptionAchat', [])
	.controller(
		'gcReceptionAchatCtrl',
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

				$scope.responseMsgDeleteReceptionAchat = "";

				$scope.refBRIsInvalid = "false";
			
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
				
				
				$scope.hiddenNotif ="true";
				$scope.Stock ="true";
				
				$scope.traitementEnCours = "false";
				$scope.traitementEnCoursGenererAll = "false";
				$scope.traitementEnCoursGenererReception = "false";
				
				$scope.numSerieAjout = "";
				$scope.numSerieEdit = "";
				$scope.referenceArticle="";
				
				
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

				/**************************************
				 * Notification *
				 **************************************/
				$scope.showNotif = function(){
					$scope.hiddenNotif ="false";
				}
										
				$scope.closeNotif = function(){
					$scope.hiddenNotif ="true";
				}
				$scope.showStock = function(){
					$scope.Stock ="false";
				}
										
				$scope.closeStock = function(){
					$scope.Stock ="true";
				}
				

				/***************************************************
				 * Gestion de Cache des Referentiels Gestion
				 * Commerciale *
				 **************************************************/
				$scope.ListEtatReceptionAchatCache = [];
				$scope.ListTypeReceptionAchatCache = [];
				$scope.listeSitePartieInteresseeCache = [];
				$scope.ListTypeDocumentCache = [];
				$scope.ListSousFamilleProduitCache = [];
				
				
				   // Liste des Taxes
  	 $scope.ListeTaxe = function () {
        $http.get(UrlAtelier + '/taxe/getTVA').success(function (dataTaxe) {
          $scope.ListeTaxe = dataTaxe;
        });
      };

      $scope.ListeTaxe();  

				
				$scope.keyPress = function (keyCode, code) {

					if (keyCode == '13') {
						
						
						var element = $scope.listeProduitCache.filter(function(node) {
					        return node.reference==code;
					    });
				
						if(element != null && element[0] != null){
							
							var idScanndProduct = element[0].id;
							

							
							// TODO get ID PRODUIT BY REFRENCE
							
							$scope.produitInserree = {
								    produitId :idScanndProduct,
									produitDesignation : '',
									produitReference : '',
									quantite : '',
									unite : '',
									prixUnitaireHT : '',
									prixTotalHT : '',
									nouveau :true,
									remise : ''
								};

							if ($scope.listProduitReceptions.filter(e => e.produitId === idScanndProduct).length == 0){
								
								$scope.listProduitReceptions
								.push($scope.produitInserree);
						
						
						        $scope.clickProduit(idScanndProduct,$scope.listProduitReceptions.length-1);
						
						       
								
							}
							 $scope.codeBarre = "";
								
							
						}else
							{
							
							alert("N° Article inexistant !!")
							
							$scope.codeBarre = "";
							}
					
					}
					
				}
				

				$scope.validerBC = function(){
					
			
					
					 			
					$http.post(
							UrlAtelier
							+ "/receptionAchat/validateCommandes?receptionAchatId="+"", $scope.tagReferenceBLivList)						
						.success(
							function (resultat) {

								 $scope.modeValider = "actif";
									
								 $scope.receptionAchatCourante.partieIntId = resultat.partieIntId;
								 $scope.receptionAchatCourante.dateLivraison = resultat.dateLivraison;
								 $scope.receptionAchatCourante.dateIntroduction = resultat.dateLivraison;
								 
								 $scope.listProduitReceptions = [];
								 
									angular.forEach(resultat.listDetLivraisonVente, function (element, key) {
										
										
											$scope
												.getProductFilter(element.produitId);
												
										
										element.referenceArticle= $scope.productFilter[0].ref;
										
										
										
										
										element.designation = element.produitDesignation;
										
										 element.prixUnitaire = element.prixUnitaireHT ;
									
									
									
									      	

											if ($scope.productFilter.length > 0) {


												element.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;
												
	                                           }
									
									
									
									
										 $scope.listProduitReceptions.push(element);


									});
									
							});
					
					
				}


				$scope.checkIfEnterKeyWasPressed = function ($event, numSerie, index) {
					var keyCode = $event.which || $event.keyCode;
					if (keyCode === 13) {
						// Do that thing you finally wanted to do 

						$scope.ajouterNumSerie(numSerie, index);


					}
				}



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

						if ($scope.listProduitReceptions[index].produitsSerialisable.filter(e => e.numSerie === numero).length == 0){
							$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
							
							$scope.numSerieAjout = "";
							document.getElementById("numSerieAjout").value = "";
							
							$scope.numSerieEdit = "";
							document.getElementById("numSerieEdit").value = "";
						}
							
					} else {
						$scope.listProduitReceptions[index].produitsSerialisable = [];
						var produitSerialisable = { numSerie: numero };
						$scope.listProduitReceptions[index].produitsSerialisable.push(produitSerialisable);
						
						$scope.numSerieAjout = "";
						document.getElementById("numSerieAjout").value = "";
						
						$scope.numSerieEdit = "";
						document.getElementById("numSerieEdit").value = "";
					}

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

				// // Liste TypeDocumentCache
				// $scope.listeTypeDocumentsCache = function () {
				// 	$http
				// 		.get(
				// 			UrlCommun
				// 			+ "/gestionnaireCache/listeTypeDocumentCache")
				// 		.success(
				// 			function (dataTypeDocumentCache) {
				// 				$log
				// 					.debug("*LISTTypeDocumentCache :"
				// 						+ dataTypeDocumentCache.length);
				// 				console
				// 					.log("liste type doc:recept cache:"
				// 						+ dataTypeDocumentCache.length);
				// 				var dataTypeDocCache2 = [];
				// 				for (var i = 0; i < dataTypeDocumentCache.length; i++) {
				// 					console
				// 						.log("dataTypeDocumentCache[i]: "
				// 							+ dataTypeDocumentCache[i].module);
				// 					if (dataTypeDocumentCache[i].module == "ACHAT_RECEPTION") {
				// 						dataTypeDocCache2
				// 							.push(dataTypeDocumentCache[i]);
				// 						console
				// 							.log("moduleid:"
				// 								+ dataTypeDocumentCache[i].id
				// 								+ " designation: "
				// 								+ dataTypeDocumentCache[i].designation);

				// 					}
				// 				}

				// 				$scope.listeTypeDocumentsCache = dataTypeDocCache2;

				// 			});
				// }

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
							UrlCommun
							+ "/article/all")

							//+ "/gestionnaireLogistiqueCache/listeProduitCache")

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
			//	$scope.listeTypeDocumentsCache();
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
					
					$scope.tagReferenceBLivList = [];
					
					$scope.receptionAchatCourante = {
						"dateIntroduction": new Date(),
						"dateLivraison": new Date(),
						"facture" : true
					
					};
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


				$scope.getCurrentReferenceMensuelleByTypeAndDate($scope.receptionAchatCourante.facture,$scope.receptionAchatCourante.dateIntroduction);





					$scope.displayMode = "edit";
				}
				
				
				$scope.getCurrentReferenceMensuelleByTypeAndDate  = function (declarer,dateIntro) {
					
					var type ="";
					
					if(declarer == true)
					 type = 'declarer';
				   else
	                 type = 'non-declarer';
				
					
							$http
						.get(
							UrlAtelier
							+ "/receptionAchat/getCurrentReferenceMensuelByTypeAndDate:"+type + ":"+formattedDate(dateIntro)
						)
						.success(
							function (data) {
								$scope.receptionAchatCourante.reference = data;
								$scope.receptionAchatCourante.refAvantChangement = data;

							}
						);
					
					}

				// Annulation de l'ajout
				$scope.annulerAjout = function () {
					
					$scope.traitementEnCours = "false";
					$scope.traitementEnCoursGenererAll = "false";
					$scope.traitementEnCoursGenererReception = "false";
					

					$scope.closeNotif();
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
						"creerBonMouvementStock": "",
						"idProduitParDesignation": "",
						"dateIntroductionDu": "",
						"dateIntroductionA": "",
						"dateLivraisonDu": "",
						"dateLivraisonA": "",
						"quantiteDu": "",
						"quantiteA": "",
						"coutDu": "",
						"coutA": "",
						"idDepot": "",
						"facture": ""
					};





					$scope.rechercherReceptionAchat({});
					$scope.listProduitReceptions = []; 
					$scope.listeDocumentReceptionAchat = [];
					$scope.creationSSForm.$setPristine();
					$scope.rechercheSSForm.$setPristine();
					$scope.displayMode = "list";
					$scope.closeNotif();
				}
				
				
				
				
					// Annulation de l'ajout
				$scope.annulerAjoutRapide = function () {
			
					$scope.displayMode = "list";
				
				}

				$scope.rechercherReceptionAchat = function (
					receptionAchatCourante) {
						if($scope.clientActif.blackMode==false){
							receptionAchatCourante.facture="oui";
						}
					
					receptionAchatCourante.type = 'Achat';
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
				$scope.downloadReport = function (id, pRapportPrix,typeRapport) {
					$scope.traitementEnCoursGenererReception = "true";
					

					// init checkbox : 'non' :rapport sans Prix /
					// 'oui' rapport avec prix
					$scope.checkboxModel = {
						rapportPrix: "oui"
					};

					console.log("-- id" + id);
					var url = UrlAtelier
						+ "/reportAchat/bonReceptionAchat?id=" + id
						+ "&avecPrix=" + pRapportPrix
						+ "&typeRapport=" + typeRapport
						+ "&type=pdf";


			

						var fileName = '  Liste Bon Sortie	'		;					
						var a = document.createElement('a');
						document.body.appendChild(a);
						downloadService.download(url).then(function (result) {
							var heasersFileName = result.headers(['content-disposition']).substring(22);
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
						$scope.traitementEnCoursGenererReception = "false";
						
						 
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



						console.log('iciiiiiiii stock')

						var fileName = '  Liste Bon Sortie	'		;					
						var a = document.createElement('a');
						document.body.appendChild(a);
						downloadService.download(url).then(function (result) {
							var heasersFileName = result.headers(['content-disposition']).substring(22);
							var fileName = heasersFileName.split('.');
							 
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
							
						 
						});
		
			


					console.log("-- URL" + url);
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
					
					$scope.traitementEnCoursGenererAll = "true";
					

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
						+ "/fichesAchat/listReceptionAchat?reference="
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



					console.log('iciiiiiiii stock')

					var fileName = '  Liste Bon Sortie	'		;					
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function (result) {
						var heasersFileName = result.headers(['content-disposition']).substring(23);
						
						
						
						var fileName = heasersFileName.split('.');
						
						fileName[0] =  'Reception-Achat_' + formattedDate(new Date());
						
					

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
					$scope.traitementEnCoursGenererAll = "false";
					
					 
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




	$scope.dupliquerBR = function (refBR) {

					$scope.isLoading = true;

				
					// getReceptionAchatByNumero
					$http
						.get(
							UrlAtelier
							+ "/receptionAchat/dupliquerByReference?reference="
							+ refBR)
						.success(
							function (datagetReceptionAchat) {
								
								$scope.receptionAchatCourante.partieIntersseId = datagetReceptionAchat.partieIntersseId ; 
								
								$scope.receptionAchatCourante.idDepot = datagetReceptionAchat.idDepot ; 

								$scope.listProduitReceptions = datagetReceptionAchat.listProduitReceptions;
								
									angular
									.forEach(
										$scope.listProduitReceptions,
										function (
											produitReception,
											key) {
												
													$scope
												.getProductFilter(produitReception.produitId);

											if ($scope.productFilter.length > 0) {


												produitReception.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;
												
	                                           }
																			
								
								});
								
								
								$scope.isLoading = false;
								
								
								});
								
								
					}

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
								$scope.listeDocumentReceptionAchat = datagetReceptionAchat.listDocReceptionAchat;

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


												produitReception.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;
												

												//produitReception.designation = $scope.productFilter[0].designation;
												//produitReception.referenceArticle = $scope.productFilter[0].reference;

												//$scope
													//.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);

											/*	if ($scope.sousFamilleFilter.length > 0) {
													produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
												}*/
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


							var dateIntroduction = null;
							var dateLivraison = null;
							if ($scope.myDataReceptionAchat[index].dateIntroduction !== null) {
								dateIntroduction = $scope.modifierFormatDate($scope.myDataReceptionAchat[index].dateIntroduction);
							} else {
								dateIntroduction = null;
							}
				
				
							if ($scope.myDataReceptionAchat[index].dateLivraison !== null) {
								dateLivraison = $scope.modifierFormatDate($scope.myDataReceptionAchat[index].dateLivraison);
							} else {
								dateLivraison = null;
							}


							$scope.receptionAchatCourante = Object.assign($scope.myDataReceptionAchat[index], { dateIntroduction: dateIntroduction } ,{ dateLivraison: dateLivraison })
							//  $scope.partieInteresseeCourante = $scope.myData[index]
							? angular.copy($scope.myDataReceptionAchat[index])
							: {};


					// $scope.receptionAchatCourante = $scope.myDataReceptionAchat[index] ? angular
					// 	.copy($scope.myDataReceptionAchat[index])
					// 	: {};
					$scope.displayMode = "edit";
				}

				$scope.modifierFormatDate = function (dateUp) {
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
					
					$scope.traitementEnCours = "true";



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
					

					
					reception.type = 'Achat';

					reception.refCommande = $scope.tagReferenceBLivList.join('-');


					// produitReception
					reception.listProduitReceptions = $scope.listProduitReceptions;

					reception.listTaxeReceptionAchat = $scope.listTaxeReceptionAchat;
					reception.listDocReceptionAchat = $scope.listeDocumentReceptionAchat ;

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
							+ "/receptionAchat/create",
							reception)
						.success(
							function (newReceptionId) {
								
								$scope.traitementEnCours = "false";
					        	$scope.showNotif();
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
											$scope.listeDocumentReceptionAchat = datagetReceptionAchat.listDocReceptionAchat ;

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

															produitReception.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;
												
															//produitReception.designation = $scope.productFilter[0].designation;

															//produitReception.referenceArticle = $scope.productFilter[0].reference;
															//produitReception.prixUnitaire = $scope.productFilter[0].prixUnitaire;

															//$scope
															//	.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);

														//	if ($scope.sousFamilleFilter.length > 0) {
														//		produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
														//	}
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
											
																if (datagetReceptionAchat.dateIntroduction !== null) {
							  	datagetReceptionAchat.dateIntroduction = $scope.modifierFormatDate(datagetReceptionAchat.dateIntroduction);
							     }
		
							if (datagetReceptionAchat.dateLivraison !== null) {
							  	datagetReceptionAchat.dateLivraison = $scope.modifierFormatDate(datagetReceptionAchat.dateLivraison);
							     }

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
					reception.listDocReceptionAchat = $scope.listeDocumentReceptionAchat ;

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
								$scope.traitementEnCours = "false";
					        	$scope.showNotif();
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
											$scope.listeDocumentReceptionAchat = datagetReceptionAchat.listDocReceptionAchat ;

											$scope.listSuppDetReceptionAchat = [];
											
											
												if (datagetReceptionAchat.dateIntroduction !== null) {
							  	datagetReceptionAchat.dateIntroduction = $scope.modifierFormatDate(datagetReceptionAchat.dateIntroduction);
							     }
		
							if (datagetReceptionAchat.dateLivraison !== null) {
							  	datagetReceptionAchat.dateLivraison = $scope.modifierFormatDate(datagetReceptionAchat.dateLivraison);
							     }
											
											

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



															produitReception.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;
												

															//produitReception.designation = $scope.productFilter[0].designation;
															//produitReception.referenceArticle = $scope.productFilter[0].reference;
															//produitReception.prixUnitaire = $scope.productFilter[0].prixUnitaire;

															//$scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);


															/*if ($scope.sousFamilleFilter.length > 0) {
																produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
															}*/
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

						console.log("call updateProduitCommandDetails");

					$scope.productFilter = [];
					$scope.sousFamilleFilter = [];

					$scope.getProductFilter(produitId);

					if ($scope.productFilter.length > 0) {

                        console.log("$scope.productFilter.length > 0");
                        

						produitReception.designation = $scope.productFilter[0].designation;
						produitReception.referenceArticle= $scope.productFilter[0].ref;
					//	$scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);
							
					produitReception.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;
						produitReception.prixUnitaire = $scope.productFilter[0].pu;
						
						produitReception.taxeId = $scope.productFilter[0].idTaxe;


					

					//	if ($scope.sousFamilleFilter.length > 0) {
					//		produitReception.sousFamille = $scope.sousFamilleFilter[0].designation;
					//	}
						
						
						 /***Debut get Prix Speciale Fournisseur  ***/
				    	
						
					    if( produitId != null 
							&& produitId != "" 	
						    &&  $scope.receptionAchatCourante.partieIntersseId != null 
							&& $scope.receptionAchatCourante.partieIntersseId != "" ){
						
						
						  var prixFournisseurRech = { 
					        		
				                   "idClient" : $scope.receptionAchatCourante.partieIntersseId,
				                    "idProduit" :produitId
				                    
			                      }


							$http.post(UrlCommun + "/prixClient/PrixClientProduit",prixFournisseurRech
									).success(
									function(resultat) {
										
										
										if(resultat != "" && resultat.prixvente>0){
											
											
											produitReception.prixUnitaire =resultat.prixvente;
										 
											
										}
										
							
									});
						
						
					         }
					    
					    /*** Fin get Prix Speciale Fournisseur  ***/
						
						
						
						
						
						
						
						
						
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
/* 
				$scope.getProductFilter = function (idProduit) {

					//console.log("idProduit" + idProduit);
					$scope.productFilter = $filter('filter')(
						$scope.listeProduitCache, {
						id: idProduit
					});

					console.log("productFilter"
							+ JSON.stringify($scope.productFilter,
									null, " "));

				} */

				
				$scope.getProductFilter = function (idProduit) {

				
					$scope.productFilter =$scope.listeProduitCache.filter(e => e.id == idProduit);
					
				

				}

				$scope.getSousFamilleFilter = function (
					sousFamilleId) {
					$scope.sousFamilleFilter = $scope.ListSousFamilleProduitCache.filter(e => e.id == sousFamilleId);
					
			
				}


				/* 
				$scope.getSousFamilleFilter = function (
					sousFamilleId) {
					$scope.sousFamilleFilter = $filter('filter')(
						$scope.ListSousFamilleProduitCache, {
						id: sousFamilleId
					});
				} */

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
						quantite: 1,
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
							$scope.name = "ACHAT_RECEPTION";


							$scope.getListeTypeDocumentsCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeTypeDocumentCache")
										.success(
												function(dataTypeDocumentCache) {
											
			
																 $scope.listeTypeDocumentsCache = dataTypeDocumentCache.filter(e => e.module === 'ACHAT_RECEPTION');;
											
			
												}); 
							}
							$scope.getListeTypeDocumentsCache();

								
							$scope.listeDocumentReceptionAchat = [];

							// GetId.designation
							$scope.doc = {

								status : ''
							};
							/*$scope.showStatus = function(id) {

								$scope.doc.status = id;
								var selected = $filter('filter')(
										$scope.ListTypeDocumentCache, {
											id : $scope.doc.status
										});
								if ($scope.doc.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							};*/
							
							
							$scope.showStatus = function(id) {

								/*$scope.type.status = id;
								var selected = $filter('filterProduit')(
										$scope.ListTypeDocumentCache, {
											id : $scope.type.status
										});
								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}*/
								 var designation="";
								 //console.log("show status length: "+$scope.listeTypeDocumentsCache.length);
						    	   for(var i=0;i<$scope.listeTypeDocumentsCache.length;i++)
					    		   {
						    		   //console.log("id: "+id+"----listeTypeDocumentCache[i]="+ $scope.listeTypeDocumentsCache[i].id);
						    		   //console.log("---module: "+$scope.listeTypeDocumentCache[i].module);
						    		   if($scope.listeTypeDocumentsCache[i].id==id ){
						    			   
						    			   designation= $scope.listeTypeDocumentsCache[i].designation;
						    			   //console.log("designation type doc :" +designation);
						    			   return designation;
						    		   }
						    		   
					    		   }
							};

							// ajout d'un DocumentReceptionAchat
							$scope.ajoutDocumentReceptionAchat = function() {

								$scope.documentReceptionAchatInserree = {

									typeDocumentId : '',
									uidDocument : '',
									path : ''

								};
								$scope.listeDocumentReceptionAchat
										.push($scope.documentReceptionAchatInserree);

							};
							$scope.creerBonMouvementStock = function(receptionAchatCourante
								) {
									$scope.traitementEnCours = "true";
									let  bonMvtEntree= {
												
										'date':$scope.receptionAchatCourante.dateIntroduction,
										'partieintId':$scope.receptionAchatCourante.partieIntersseId,
										'type':"ENTREE",
										'idMagasin':$scope.receptionAchatCourante.idDepot,
										'bonMouvementEntreeId':$scope.receptionAchatCourante.bonMouvementEntreeId,
										'referenceBonReception':$scope.receptionAchatCourante.reference,
										'mouvements':[]
 
									  };
									for (var i = 0; i < $scope.listProduitReceptions.length; i++) {


										let mvt = {} ;
										
										mvt.idArticle=$scope.listProduitReceptions[i].produitId;
										mvt.quantiteReelle=$scope.listProduitReceptions[i].quantite;
										mvt.prixUnitaire=$scope.listProduitReceptions[i].prixUnitaire;
										mvt.type="ENTREE";

										bonMvtEntree.mouvements.push(mvt);
												
												
									}

	
								
					
					
							$http
									.post(
											UrlAtelier
													+ "/bonMouvementStock/creerBonMouvementStock",
													bonMvtEntree)
									.success(function(data) {
										$scope.traitementEnCours = "false";
					        	         $scope.showStock();
                                        $scope.receptionAchatCourante.bonMouvementEntreeId=data;
										$scope.updateReceptionAchat(receptionAchatCourante);

									});
			
						}

							// Enregistrer DocumentReceptionAchat
							$scope.saveDocumentReceptionAchat = function(
									dataDocumentReceptionAchat, id) {
								console.log("**SAVE DOC "
										+ dataDocumentReceptionAchat);
								$scope.deleteValue = "non";
								angular.extend(dataDocumentReceptionAchat, {
									id : id
								});
							};

							// Supprimer DocumentReceptionAchat
							$scope.removeDocumentReceptionAchat = function(
									index) {
								$scope.listeDocumentReceptionAchat.splice(
										index, 1);
							};

							// Download Document
							$scope.download = function(uuid) {
								var url = UrlCommun
										+ "/gestionnaireDocument/document/"
										+ uuid;

								downloadService.download(url).then(
										function(success) {
											$log.debug('success : ' + success);
										}, function(error) {
											$log.debug('error : ' + error);
										});
							};
							
							/** Fin de gestion des DocumentProduit */
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
									splayName: 'Réf.B Réception',
										width : '30%'
								},
								{
									field: 'partieIntersseAbbreviation',
									displayName: 'Fournisseur',
										width : '30%'
								},
								{
									field: 'dateIntroduction',
									displayName: 'Date Réception',
									cellFilter: 'date:\'dd-MM-yyyy\'',
										width : '10%'
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
										width : '10%'
								},


								{
									field: 'prixTotal',
									displayName: 'Montant HT',
									cellFilter: 'prixFiltre',
										width:'5%'
								},
								{
									field: 'montantTaxe',
									displayName: 'Mont. Taxe',
									cellFilter: 'prixFiltre',
										width:'5%'
								},
								{
									field: 'montantTTC',
									displayName: 'Montant TTC',
									cellFilter: 'prixFiltre',
										width:'5%'
								},

								{
									field: '',
										width : '5%',
									cellTemplate:
									`<div class="ms-CommandButton float-right"   >
										<button class="ms-CommandButton-button ms-CommandButton-Gpro"  ng-click="modifierOuCreerReceptionAchat()">
										<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
										</button>
										<button class="ms-CommandButton-button"  ng-click="showPopupDelete(17)" permission="['Achat_Delete']">
										<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
										</button>
										</div> `,


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
							receptionAchatCourante.type = 'Achat';
							if($scope.clientActif.blackMode==false){
								receptionAchatCourante.declare="oui";
			
							 }
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
					//enableColumnResize: true,
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
