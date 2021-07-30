angular
	.module('gpro.gcReportingFNclient', [])
	.controller(
		'GcReportingFNclientClientCtrl',
		[
			'$scope',
			'$http',
			'$filter',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'$window',

			function($scope, $http, $filter, $log, downloadService,
				UrlCommun, UrlAtelier, $window) {

				$log.info("=========Gc Reporting Client========");

				$scope.ordreFabricationCourant = {
					"chaineId": "",
					"dateSaisieMin": "",
					"dateSaisieMax": "",
					"declarerRech": ""
				};


				if ($scope.clientActif.blackMode == false) {
					$scope.ordreFabricationCourant.declarerRech = "oui";

				}


				$scope.isEnCoursChargement = false;

				$scope.ListClientCache = [];

				// Liste des ClientCommandeVenteCache
				$scope.listeClientCache = function() {
					$http
						.get(
							UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function(dataClientCache) {
								$scope.ListClientCache = dataClientCache;
							});
				}
				$scope.listeClientCache();

				// annuler Recherche
				$scope.annulerChaineAjout = function() {


					$scope.isEnCoursChargement = false;

					$scope.modePdf = "notActive";

					$scope.ordreFabricationCourant = {
						"chaineId": "",
						"dateSaisieMin": "",
						"dateSaisieMax": "",
						"declarerRech": ""
					};


					if ($scope.clientActif.blackMode == false) {
						$scope.ordreFabricationCourant.declarerRech = "oui";

					}

					$scope.rechercheClientForm.$setPristine();
				}

				//generer rapport de tous les bons de sortie. mode : List 
				//conversion date en String
				function formattedDate(date) {
					var d = new Date(date),
						month = '' + (d.getMonth() + 1),
						day = '' + d.getDate(),
						year = d.getFullYear();

					if (month.length < 2) month = '0' + month;
					if (day.length < 2) day = '0' + day;
					return [year, month, day].join('-');
				}

				$scope.downloadClientReporting = function(feuilleCourante, action) {

					$scope.isEnCoursChargement = true;

					var newdateSaisieMinFormat = "";
					if (angular.isDefined(feuilleCourante.dateMin)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMin);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMinFormat = formattedDate(feuilleCourante.dateMin);
						} else {
							newdateSaisieMinFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}

					var newdateSaisieMaxFormat = "";
					if (angular.isDefined(feuilleCourante.dateMax)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMax);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMaxFormat = formattedDate(feuilleCourante.dateMax);
						} else {
							newdateSaisieMaxFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}


					//   $log.info("-- feuilleCourante" + JSON.stringify($scope.feuilleCourante, null, "  ") );
					$log.info("feuilleCouranteChaine " + JSON.stringify(feuilleCourante, null, "  "));

					if (action != null) {
						var url;

						if (action == 1) {
							//ficheClient
							url = UrlAtelier + "/reportgc/ficheClient?clientId=" + feuilleCourante.clientId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&typeRapport=ficheClient"
								+ "&declarerRech=" + feuilleCourante.declarerRech
								+ "&type=pdf";
						} else if (action == 2) {
							//Releve
							url = UrlAtelier + "/reportgc/ficheClient?clientId=" + feuilleCourante.clientId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&typeRapport=releveClient"
								+ "&declarerRech=" + feuilleCourante.declarerRech
								+ "&type=pdf";
						}
						else if (action == 3) {
							//Releve
							url = UrlAtelier + "/reportgc/ficheClient?clientId=" + feuilleCourante.clientId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&typeRapport=ficheFactureClient"
								+ "&declarerRech=" + feuilleCourante.declarerRech
								+ "&type=pdf";
						}

						else if (action == 4) {
							//Releve
							url = UrlAtelier + "/reportgc/ficheClient?clientId=" + feuilleCourante.clientId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&typeRapport=releveFactureClient"
								+ "&declarerRech=" + feuilleCourante.declarerRech
								+ "&type=pdf";
						}
						$log.info("URL " + url);



						//	var fileName = '  Liste Bon Sortie	'		;					
						var a = document.createElement('a');
						document.body.appendChild(a);
						downloadService.download(url).then(function(result) {

							var heasersFileName = result.headers(['content-disposition']).substring(17);
							var fileName = heasersFileName.split('.');
							var typeFile = result.headers(['content-type']);
							var file = new Blob([result.data], { type: typeFile });
							var fileURL = window.URL.createObjectURL(file);
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

							$scope.isEnCoursChargement = false;
						});


						//Generate
						// downloadService.download(url).then(
						//     function(success) {
						//          $log.debug('success : ' + success);
						//     }, function(error) {
						//          $log.debug('error : ' + error);
						//     });
					}
				}

			}
		])


	//Groupe


	.controller(
		'GcReportingFNclientGroupeCtrl',
		[
			'$scope',
			'$http',
			'$filter',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'$window',

			function($scope, $http, $filter, $log, downloadService,
				UrlCommun, UrlAtelier, $window) {


				$scope.isEnCoursChargement = false;

				$log.info("=========Gc Reporting Client========");

				$scope.ListClientCache = [];

				// Liste des ClientCommandeVenteCache
				/*	$scope.listeClientCache = function() {
						$http
								.get(
										UrlCommun+"/gestionnaireCache/listePartieInteresseeCache")
								.success(
										function(dataClientCache) {
											$scope.ListClientCache = dataClientCache;
										});
					}
					$scope.listeClientCache();
				*/


				// Liste des ClientCommandeVenteCache
				$scope.listeGroupeClient = function() {
					$http
						.get(
							UrlCommun + "/groupeClient/all")
						.success(
							function(dataClientCache) {
								$scope.ListClientCache = dataClientCache;
							});
				}
				$scope.listeGroupeClient();

				// annuler Recherche
				$scope.annulerChaineAjout = function() {

					$scope.isEnCoursChargement = false;

					$scope.modePdf = "notActive";

					$scope.ordreFabricationCourant = {
						"chaineId": "",
						"dateSaisieMin": "",
						"dateSaisieMax": ""
					};

					$scope.rechercheClientForm.$setPristine();
				}

				//generer rapport de tous les bons de sortie. mode : List 
				//conversion date en String
				function formattedDate(date) {
					var d = new Date(date),
						month = '' + (d.getMonth() + 1),
						day = '' + d.getDate(),
						year = d.getFullYear();

					if (month.length < 2) month = '0' + month;
					if (day.length < 2) day = '0' + day;
					return [year, month, day].join('-');
				}

				$scope.downloadClientReporting = function(feuilleCourante, action) {


					$scope.isEnCoursChargement = true;
					var newdateSaisieMinFormat = "";
					if (angular.isDefined(feuilleCourante.dateMin)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMin);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMinFormat = formattedDate(feuilleCourante.dateMin);
						} else {
							newdateSaisieMinFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}

					var newdateSaisieMaxFormat = "";
					if (angular.isDefined(feuilleCourante.dateMax)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMax);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMaxFormat = formattedDate(feuilleCourante.dateMax);
						} else {
							newdateSaisieMaxFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}


					//   $log.info("-- feuilleCourante" + JSON.stringify($scope.feuilleCourante, null, "  ") );
					$log.info("feuilleCouranteChaine " + JSON.stringify(feuilleCourante, null, "  "));

					if (action != null) {
						var url;

						if (action == 1) {
							//ficheClient
							url = UrlAtelier + "/reportgc/ficheGroupe?groupeClientId=" + feuilleCourante.clientId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&typeRapport=ficheGroupe"
								+ "&type=pdf";
						} else if (action == 2) {
							//Releve
							url = UrlAtelier + "/reportgc/ficheGroupe?groupeClientId=" + feuilleCourante.clientId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&typeRapport=releveGroupe"
								+ "&type=pdf";
						}
						$log.info("URL " + url);


						//	var fileName = '  Liste Bon Sortie	'		;					
						var a = document.createElement('a');
						document.body.appendChild(a);
						downloadService.download(url).then(function(result) {
							var heasersFileName = result.headers(['content-disposition']).substring(17);
							var fileName = heasersFileName.split('.');
							var typeFile = result.headers(['content-type']);
							var file = new Blob([result.data], { type: typeFile });
							var fileURL = window.URL.createObjectURL(file);
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();
							$scope.isEnCoursChargement = false;
						});


						//Generate
						// downloadService.download(url).then(
						//     function(success) {
						//          $log.debug('success : ' + success);
						//     }, function(error) {
						//          $log.debug('error : ' + error);
						//     });
					}
				}

			}
		])

	//Situation							
	.controller(
		'GcReportingFNclientSituationCtrl',
		[
			'$scope',
			'$http',
			'$filter',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'$window',

			function($scope, $http, $filter, $log, downloadService,
				UrlCommun, UrlAtelier, $window) {

				$scope.isEnCoursChargement = false;

				$scope.ListRegion = [];
				$scope.ListClientCache = [];
				$scope.ListeDevise = [];


				$scope.ListeDevise = function() {
					$http.get(UrlCommun + '/devise/all').success(function(dataDevise) {
						$scope.ListeDevise = dataDevise;
					});
				};



				$scope.ListeDevise();

				// Liste des ClientCommandeVenteCache
				$scope.listeRegionCache = function() {
					$http
						.get(
							UrlCommun + "/region/getAll")
						.success(
							function(dataRegion) {
								$scope.ListRegion = dataRegion;
							});
				}
				$scope.listeRegionCache();

				// Liste des ClientCommandeVenteCache
				$scope.listeClientCache = function() {
					$http
						.get(
							UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function(dataClientCache) {
								$scope.ListClientCache = dataClientCache;
							});
				}
				$scope.listeClientCache();

				// annuler Recherche
				$scope.annulerOFAjout = function() {

					$scope.isEnCoursChargement = false;

					$scope.modePdf = "notActive";

					$scope.ordreFabricationCourant = {
						"regionId": "",
						"soldeMin": "",
						"soldeMax": "",
						"partieIntId": "",
						"deviseId": "",
					};

					$scope.rechercheSituationForm.$setPristine();
				}

				//generer rapport de tous les bons de sortie. mode : List 
				//conversion date en String
				function formattedDate(date) {
					var d = new Date(date),
						month = '' + (d.getMonth() + 1),
						day = '' + d.getDate(),
						year = d.getFullYear();

					if (month.length < 2) month = '0' + month;
					if (day.length < 2) day = '0' + day;
					return [year, month, day].join('-');
				}

				$scope.downloadAllOrdreFabrication = function(feuilleCourante, action) {


					$scope.isEnCoursChargement = true;
					var newdateSaisieMinFormat = "";
					if (angular.isDefined(feuilleCourante.dateMin)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMin);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMinFormat = formattedDate(feuilleCourante.dateMin);
						} else {
							newdateSaisieMinFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}

					var newdateSaisieMaxFormat = "";
					if (angular.isDefined(feuilleCourante.dateMax)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMax);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMaxFormat = formattedDate(feuilleCourante.dateMax);
						} else {
							newdateSaisieMaxFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}

					$log.info("feuilleCouranteOF " + JSON.stringify(feuilleCourante, null, "  "));
					if (action != null) {
						var url;
						if (action == 1) {
							//Situation 
							url = UrlAtelier + "/reporting/situation?partieIntId=" + feuilleCourante.partieIntId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&regiontId=" + feuilleCourante.regionId
								+ "&deviseId=" + feuilleCourante.deviseId
								+ "&soldeMin=&soldeMax="
								+ "&solde=" + action
								+ "&type=pdf";
						}
						if (action == 2) {

							//Situation  solde non null
							url = UrlAtelier + "/reporting/situation?partieIntId=" + feuilleCourante.partieIntId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&regiontId=" + feuilleCourante.regionId
								+ "&deviseId=" + feuilleCourante.deviseId
								+ "&soldeMin=&soldeMax="
								+ "&solde=" + action
								+ "&type=pdf";


						}


						//	var fileName = '  Liste Bon Sortie	'		;					
						var a = document.createElement('a');
						document.body.appendChild(a);
						downloadService.download(url).then(function(result) {
							var heasersFileName = result.headers(['content-disposition']).substring(17);
							var fileName = heasersFileName.split('.');
							var typeFile = result.headers(['content-type']);
							var file = new Blob([result.data], { type: typeFile });
							var fileURL = window.URL.createObjectURL(file);
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

							$scope.isEnCoursChargement = false;
						});


						//Generate
						// downloadService.download(url).then(
						//     function(success) {
						//          $log.debug('success : ' + success);
						//     }, function(error) {
						//          $log.debug('error : ' + error);
						//     });
					}
				}

				$scope.downloadAllOrdreFabricationExcel = function(feuilleCourante, action) {

					$scope.isEnCoursChargement = true;
					var newdateSaisieMinFormat = "";
					if (angular.isDefined(feuilleCourante.dateMin)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMin);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMinFormat = formattedDate(feuilleCourante.dateMin);
						} else {
							newdateSaisieMinFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}

					var newdateSaisieMaxFormat = "";
					if (angular.isDefined(feuilleCourante.dateMax)) {
						$log.debug("==dateIntroductionMin " + feuilleCourante.dateMax);

						if (feuilleCourante.dateMin != "") {
							newdateSaisieMaxFormat = formattedDate(feuilleCourante.dateMax);
						} else {
							newdateSaisieMaxFormat = "";
						}
					} else {
						$log.debug("==dateIntroductionMin Undefined");
					}

					$log.info("feuilleCouranteOF " + JSON.stringify(feuilleCourante, null, "  "));
					if (action != null) {
						var url;
						if (action == 1) {
							//Situation 
							url = UrlAtelier + "/fiches/situation?partieIntId=" + feuilleCourante.partieIntId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&regiontId=" + feuilleCourante.regionId
								+ "&deviseId=" + feuilleCourante.deviseId
								+ "&soldeMin=&soldeMax="
								+ "&solde=" + action
								+ "&type=pdf";
						}
						if (action == 2) {

							//Situation  solde non null
							url = UrlAtelier + "/fiches/situation?partieIntId=" + feuilleCourante.partieIntId
								+ "&dateMin=" + newdateSaisieMinFormat
								+ "&dateMax=" + newdateSaisieMaxFormat
								+ "&regiontId=" + feuilleCourante.regionId
								+ "&deviseId=" + feuilleCourante.deviseId
								+ "&soldeMin=&soldeMax="
								+ "&solde=" + action
								+ "&type=pdf";


						}


						//	var fileName = '  Liste Bon Sortie	'		;					
						var a = document.createElement('a');
						document.body.appendChild(a);
						downloadService.download(url).then(function(result) {
							var heasersFileName = result.headers(['content-disposition']).substring(21);
							var fileName = heasersFileName.split('.');
							var typeFile = result.headers(['content-type']);
							var file = new Blob([result.data], { type: typeFile });
							var fileURL = window.URL.createObjectURL(file);
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();
							$scope.isEnCoursChargement = false;
						});


						//Generate
						// downloadService.download(url).then(
						//     function(success) {
						//          $log.debug('success : ' + success);
						//     }, function(error) {
						//          $log.debug('error : ' + error);
						//     });
					}
				}

			}
		])


	//BL Non paye


	.controller(
		'GcReportingFNclientBLnonPayeCtrl',
		[
			'$scope',
			'$http',
			'$filter',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'$window',

			function($scope, $http, $filter, $log, downloadService,
				UrlCommun, UrlAtelier, $window) {

				$scope.isEnCoursChargement = false;

				$log.info("=========Gc Reporting Client========");

				$scope.ListClientCache = [];

				$scope.ListeDevise = [];

				// Liste des ClientCommandeVenteCache
				/*	$scope.listeClientCache = function() {
						$http
								.get(
										UrlCommun+"/gestionnaireCache/listePartieInteresseeCache")
								.success(
										function(dataClientCache) {
											$scope.ListClientCache = dataClientCache;
										});
					}
					$scope.listeClientCache();
				*/

				$scope.ListeDevise = function() {
					$http.get(UrlCommun + '/devise/all').success(function(dataDevise) {
						$scope.ListeDevise = dataDevise;
					});
				};



				$scope.ListeDevise();

				// Liste des listGroupeClient
				$scope.listGroupeClient = function() {
					$http
						.get(
							UrlCommun
							+ "/groupeClient/all")
						.success(
							function(dataCategorieCache) {
								//	$log.debug("listeCategorie : "+dataCategorieCache.length);
								$scope.listGroupeClient = dataCategorieCache;

							});
				}
				$scope.listGroupeClient();

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

				$scope.listePartieInteresseeCache();

				// annuler Recherche
				$scope.annulerAjout = function() {

					$scope.isEnCoursChargement = false;

					$scope.modePdf = "notActive";

					$scope.bonLivraisonVenteCourant = {
					};

					$scope.rechercheClientForm.$setPristine();
				}

				//generer rapport de tous les bons de sortie. mode : List 
				//conversion date en String
				function formattedDate(date) {
					var d = new Date(date),
						month = '' + (d.getMonth() + 1),
						day = '' + d.getDate(),
						year = d.getFullYear();

					if (month.length < 2) month = '0' + month;
					if (day.length < 2) day = '0' + day;
					return [year, month, day].join('-');
				}




				$scope.downloadBLNonPaye = function(
					bonLivraisonVenteCourant) {

					$scope.isEnCoursChargement = true;


					var newdateLivMinFormat = "";
					if (angular
						.isDefined(bonLivraisonVenteCourant.dateLivraisonMin)) {
						$log
							.debug("==dateLivraisonMin "
								+ bonLivraisonVenteCourant.dateLivraisonMin);

						if (bonLivraisonVenteCourant.dateLivraisonMin != "") {
							newdateLivMinFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMin);
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
						.isDefined(bonLivraisonVenteCourant.dateLivraisonMax)) {
						$log
							.debug("==dateLivraisonMax "
								+ bonLivraisonVenteCourant.dateLivraisonMax);

						if (bonLivraisonVenteCourant.dateLivraisonMax != "") {
							newdateLivMaxFormat = formattedDate(bonLivraisonVenteCourant.dateLivraisonMax);
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

					$log.debug("-- bonLivraisonVenteCourant"
						+ JSON.stringify(
							bonLivraisonVenteCourant, null,
							"  "));

					var newIdDepot = '';
					if (angular.isDefined(bonLivraisonVenteCourant.idDepot)) {
						newIdDepot = bonLivraisonVenteCourant.idDepot;

					}


					var newPartieIntId = '';
					if (angular.isDefined(bonLivraisonVenteCourant.partieIntId)) {
						newPartieIntId = bonLivraisonVenteCourant.partieIntId;

					}

					var deviseId = '';
					if (angular.isDefined(bonLivraisonVenteCourant.deviseId)) {
						deviseId = bonLivraisonVenteCourant.deviseId;

					}

					//	console.log("===============>download: idDepot:"+bonLivraisonVenteCourant.idDepot);
					var url = UrlAtelier
						+ "/fiches/bl-non-paye?referenceBl="
						+ bonLivraisonVenteCourant.referenceBl
						+ "&referenceBs="
						+ bonLivraisonVenteCourant.referenceBs
						+ "&partieIntId="
						+ newPartieIntId
						+ "&dateLivraisonMin="
						+ newdateLivMinFormat
						+ "&dateLivraisonMax="
						+ newdateLivMaxFormat
						+ "&metrageMin="
						+ bonLivraisonVenteCourant.metrageMin
						+ "&metrageMax="
						+ bonLivraisonVenteCourant.metrageMax
						+ "&prixMin="
						+ bonLivraisonVenteCourant.prixMin
						+ "&prixMax="
						+ bonLivraisonVenteCourant.prixMax
						+ "&natureLivraison="
						+ bonLivraisonVenteCourant.natureLivraison
						+ "&avecFacture="
						+ bonLivraisonVenteCourant.avecFacture
						+ "&groupeClientId=" + bonLivraisonVenteCourant.groupeClientId
						+ "&idDepot=" + newIdDepot
						+ "&deviseId=" + deviseId
						+ "&type=pdf";

					$log.debug("--downloadAllBonLiv URL" + url);


					//	var fileName = '  Liste Bon Sortie	'		;					
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function(result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');

						fileName[0] = 'Liste des Bons de Livraisons Non Payées_' + formattedDate(new Date());
						var typeFile = result.headers(['content-type']);
						var file = new Blob([result.data], { type: typeFile });
						var fileURL = window.URL.createObjectURL(file);
						a.href = fileURL;
						a.download = fileName[0];
						//$window.open(fileURL)
						a.click();

						$scope.isEnCoursChargement = false;
					});


					// downloadService.download(url).then(
					// 		function(success) {
					// 			$log.debug('success : ' + success);
					// 		}, function(error) {
					// 			$log.debug('error : ' + error);
					// 		});
				};


			}
		])


	//Facture non paye
	.controller(
		'GcReportingFNclientFactureNonPayeCtrl',
		[
			'$scope',
			'$http',
			'$filter',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'$window',
			function($scope, $http, $filter, $log, downloadService,
				UrlCommun, UrlAtelier, $window) {


				$scope.factureVenteCourant = {
					"dateFactureMin": "",
					"dateFactureMax": "",
					"groupeClientId": "",
					"partieIntId": "",

					"prixMin": "",
					"prixMax": "",

					"metrageMin": "",
					"metrageMax": "",

					"deviseId": "",
					"declarerRech": ""
				};

				



				if ($scope.clientActif.blackMode == false) {
					$scope.factureVenteCourant.declarerRech = "oui";

				}

				$scope.factureVenteCourant.declarerRech


				$scope.isEnCoursChargement = false;


				$log.info("=========Gc Reporting Client========");
				
				

				$scope.ListClientCache = [];

				$scope.ListeDevise = [];



				$scope.ListeDevise = function() {
					$http.get(UrlCommun + '/devise/all').success(function(dataDevise) {
						$scope.ListeDevise = dataDevise;
					});
				};
				$scope.ListeDevise();
				// Liste des ClientCommandeVenteCache
				/*	$scope.listeClientCache = function() {
						$http
								.get(
										UrlCommun+"/gestionnaireCache/listePartieInteresseeCache")
								.success(
										function(dataClientCache) {
											$scope.ListClientCache = dataClientCache;
										});
					}
					$scope.listeClientCache();
				*/

				// Liste des listGroupeClient
				$scope.listGroupeClient = function() {
					$http
						.get(
							UrlCommun
							+ "/groupeClient/all")
						.success(
							function(dataCategorieCache) {
								//	$log.debug("listeCategorie : "+dataCategorieCache.length);
								$scope.listGroupeClient = dataCategorieCache;

							});
				}
				$scope.listGroupeClient();

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

				$scope.listePartieInteresseeCache();

				// annuler Recherche
				$scope.annulerAjout = function() {

					$scope.isEnCoursChargement = false;

					$scope.modePdf = "notActive";

						$scope.factureVenteCourant = {
					"dateFactureMin": "",
					"dateFactureMax": "",
					"groupeClientId": "",
					"partieIntId": "",

					"prixMin": "",
					"prixMax": "",

					"metrageMin": "",
					"metrageMax": "",

					"deviseId": "",
					"declarerRech": ""
				};

				



				if ($scope.clientActif.blackMode == false) {
					$scope.factureVenteCourant.declarerRech = "oui";

				}

					$scope.rechercheClientForm.$setPristine();
				}

				//generer rapport de tous les bons de sortie. mode : List 
				//conversion date en String
				function formattedDate(date) {
					var d = new Date(date),
						month = '' + (d.getMonth() + 1),
						day = '' + d.getDate(),
						year = d.getFullYear();

					if (month.length < 2) month = '0' + month;
					if (day.length < 2) day = '0' + day;
					return [year, month, day].join('-');
				}



				$scope.downloadFactureNonPaye = function(factureVenteCourant) {


					$scope.isEnCoursChargement = true;
					var newdateFacMinFormat = "";
					if (angular.isDefined(factureVenteCourant.dateFactureMin)) {
						$log.debug("==dateFactureMin " + factureVenteCourant.dateFactureMin);

						if (factureVenteCourant.dateFactureMin != "") {
							newdateFacMinFormat = formattedDate(factureVenteCourant.dateFactureMin);
							$log.debug("===== newdateFacMinFormat " + newdateFacMinFormat);
						} else {
							$log.debug("===== newdateFacMinFormat is Null");
							newdateLivMinFormat = "";
						}
					} else {
						$log.error("==dateFactureMin Undefined");
					}

					var newdateFacMaxFormat = "";
					if (angular.isDefined(factureVenteCourant.dateFactureMax)) {
						$log.debug("==dateFactureMax " + factureVenteCourant.dateFactureMax);

						if (factureVenteCourant.dateFactureMax != "") {
							newdateFacMaxFormat = formattedDate(factureVenteCourant.dateFactureMax);
							$log.debug("===== newdateFacMaxFormat " + newdateFacMaxFormat);
						} else {
							$log.debug("===== newdateFacMaxFormat is Null");
							newdateFacMaxFormat = "";
						}
					} else {
						$log.debug("==dateFactureMax Undefined");
					}

					$log.debug("-- factureVenteCourant" + JSON.stringify(factureVenteCourant, null, "  "));

					var url;
					$log.debug("PI  " + factureVenteCourant.partieIntId);
					if (factureVenteCourant.partieIntId == null) {
						factureVenteCourant.partieIntId = "";
						$log.debug("=>PI  " + factureVenteCourant.partieIntId);
						url = UrlAtelier + "/fiches/facture-non-paye?referenceFacture=" + factureVenteCourant.referenceFacture
							+ "&typeFacture=Normal"
							+ "&referenceBl=" + factureVenteCourant.referenceBl
							+ "&partieIntId=" + factureVenteCourant.partieIntId
							+ "&dateFactureMin=" + newdateFacMinFormat
							+ "&dateFactureMax=" + newdateFacMaxFormat
							+ "&metrageMin=" + factureVenteCourant.metrageMin
							+ "&metrageMax=" + factureVenteCourant.metrageMax
							+ "&prixMin=" + factureVenteCourant.prixMin
							+ "&prixMax=" + factureVenteCourant.prixMax
							+ "&natureLivraison=" + factureVenteCourant.natureLivraison
							+ "&groupeClientId=" + factureVenteCourant.groupeClientId
							+ "&deviseId=" + factureVenteCourant.deviseId
							+ "&declarerRech=" + factureVenteCourant.declarerRech
							+ "&type=pdf";

					} else {
						url = UrlAtelier + "/fiches/facture-non-paye?referenceFacture=" + factureVenteCourant.referenceFacture
							+ "&typeFacture=Normal"
							+ "&referenceBl=" + factureVenteCourant.referenceBl
							+ "&partieIntId=" + factureVenteCourant.partieIntId
							+ "&dateFactureMin=" + newdateFacMinFormat
							+ "&dateFactureMax=" + newdateFacMaxFormat
							+ "&metrageMin=" + factureVenteCourant.metrageMin
							+ "&metrageMax=" + factureVenteCourant.metrageMax
							+ "&prixMin=" + factureVenteCourant.prixMin
							+ "&prixMax=" + factureVenteCourant.prixMax
							+ "&natureLivraison=" + factureVenteCourant.natureLivraison
							+ "&groupeClientId=" + factureVenteCourant.groupeClientId
							+ "&deviseId=" + factureVenteCourant.deviseId
							+ "&declarerRech=" + factureVenteCourant.declarerRech
							+ "&type=pdf";
					}
					$log.debug("-- URL" + url);


					// var fileName = '  Liste Bon Sortie	'		;					
					var a = document.createElement('a');
					document.body.appendChild(a);
					downloadService.download(url).then(function(result) {
						var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');

						fileName[0] = 'Liste des Factures Non Payées_' + formattedDate(new Date());
						var typeFile = result.headers(['content-type']);
						var file = new Blob([result.data], { type: typeFile });
						var fileURL = window.URL.createObjectURL(file);
						a.href = fileURL;
						a.download = fileName[0];
						//$window.open(fileURL)
						a.click();

						$scope.isEnCoursChargement = false;
					});


					//  downloadService.download(url).then(
					// 		 function(success) {
					// 			 $log.debug('success : ' + success);
					// 		 }, function(error) {
					// 			 $log.debug('error : ' + error);
					// 		 });
				};

			}
		]);
