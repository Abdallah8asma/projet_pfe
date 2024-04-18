'use strict'
var app = angular.module('gpro.FinanceReporting', []);

//Client
app.controller(
	'FnReportingClientCtrl',
	[
		'$scope',
		'$http',
		'$filter',
		'$log',
		'downloadService',
		'UrlCommun',
		'UrlAtelier',
		'$window',
		function ($scope, $http, $filter, $log, downloadService,
			UrlCommun, UrlAtelier,$window) {
			
			$scope.traitementEnCoursCl= "false";	
				

			$scope.ListClientCache = [];

			// Liste des ClientCommandeVenteCache
			$scope.listeClientCache = function () {
				$http
					.get(
						UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
					.success(
						function (dataClientCache) {
							$scope.ListClientCache = dataClientCache;
						});
			}
			$scope.listeClientCache();

			// annuler Recherche
			$scope.annulerClientAjout = function () {

				$scope.fnReportingCourant = {
					"clientId": "",
					"dateMin": "",
					"dateMax": ""
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

			$scope.downloadClientReporting = function (fnReportingCourant, action) {
				$scope.traitementEnCoursCl= "true";	
				var newdateSaisieMinFormat = "";
				if (angular.isDefined(fnReportingCourant.dateMin)) {
					$log.debug("==dateIntroductionMin " + fnReportingCourant.dateMin);

					if (fnReportingCourant.dateMin != "") {
						newdateSaisieMinFormat = formattedDate(fnReportingCourant.dateMin);
					} else {
						newdateSaisieMinFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				var newdateSaisieMaxFormat = "";
				if (angular.isDefined(fnReportingCourant.dateMax)) {
					$log.debug("==dateIntroductionMin " + fnReportingCourant.dateMax);

					if (fnReportingCourant.dateMin != "") {
						newdateSaisieMaxFormat = formattedDate(fnReportingCourant.dateMax);
					} else {
						newdateSaisieMaxFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}


				//   $log.info("-- fnReportingCourant" + JSON.stringify($scope.fnReportingCourant, null, "  ") );
				$log.info("fnReportingCourantChaine " + JSON.stringify(fnReportingCourant, null, "  "));

				if (action != null) {
					var url;

					//Client
					url = UrlAtelier + "/reportFn/clientGrByProduitOrClient?partieIntId=" + fnReportingCourant.clientId
						+ "&produitId="
						+ "&dateMin=" + newdateSaisieMinFormat
						+ "&dateMax=" + newdateSaisieMaxFormat
						+ "&typeRapport=Client"
						+ "&type=pdf";

					$log.info("URL " + url);

		 
							
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
					$scope.traitementEnCoursCl= "false";	
						
					 
					});


					//Generate
					// downloadService.download(url).then(
					// 	function (success) {
					// 		$log.debug('success : ' + success);
					// 	}, function (error) {
					// 		$log.debug('error : ' + error);
					// 	});
				}
			}

		}
	]);

//Produit
app.controller(
	'FnReportingProduitCtrl',
	[
		'$scope',
		'$http',
		'$filter',
		'$log',
		'downloadService',
		'UrlCommun',
		'UrlAtelier',
		'$window',
		function ($scope, $http, $filter, $log, downloadService,
			UrlCommun, UrlAtelier,$window) {

			$scope.ListProduit = [];
			$scope.traitementEnCoursAr= "false";	
			

			// Liste des Produit
			$scope.listeProduit = function () {
				$http
					.get(
						UrlCommun + "/produit/all")
					.success(
						function (dataProduit) {
							$scope.ListProduit = dataProduit;
						});
			}
			$scope.listeProduit();

			// annuler Recherche
			$scope.annulerProduitAjout = function () {

				$scope.fnReportingCourant = {
					"produitId": "",
					"dateMin": "",
					"dateMax": ""
				};

				$scope.rechercheProduitForm.$setPristine();
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

			$scope.downloadProduitReporting = function (fnReportingCourant, action) {
				$scope.traitementEnCoursAr= "true";	
				var newdateSaisieMinFormat = "";
				if (angular.isDefined(fnReportingCourant.dateMin)) {

					if (fnReportingCourant.dateMin != "") {
						newdateSaisieMinFormat = formattedDate(fnReportingCourant.dateMin);
					} else {
						newdateSaisieMinFormat = "";
					}
				}

				var newdateSaisieMaxFormat = "";
				if (angular.isDefined(fnReportingCourant.dateMax)) {

					if (fnReportingCourant.dateMin != "") {
						newdateSaisieMaxFormat = formattedDate(fnReportingCourant.dateMax);
					} else {
						newdateSaisieMaxFormat = "";
					}
				}

				$log.info("fnReportingCourantChaine " + JSON.stringify(fnReportingCourant, null, "  "));

				var url;
				//Produit
				url = UrlAtelier + "/reportFn/clientGrByProduitOrClient?partieIntId="
					+ "&produitId=" + fnReportingCourant.produitId
					+ "&dateMin=" + newdateSaisieMinFormat
					+ "&dateMax=" + newdateSaisieMaxFormat
					+ "&typeRapport=Produit"
					+ "&type=pdf";

				$log.info("URL " + url);

		 
			
			
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
					
				$scope.traitementEnCoursAr= "false";	
				});

				//Generate
				// downloadService.download(url).then(
				// 	function (success) {
				// 		$log.debug('success : ' + success);
				// 	}, function (error) {
				// 		$log.debug('error : ' + error);
				// 	});
			}


		}
	]);

//CA & Exp							
app.controller(
	'GcReportingCA_ExpCtrl',
	[
		'$scope',
		'$http',
		'$filter',
		'$log',
		'downloadService',
		'UrlCommun',
		'UrlAtelier',
		'$window',
		function ($scope, $http, $filter, $log, downloadService,
			UrlCommun, UrlAtelier,$window) {

			$scope.ListRegion = [];
			$scope.traitementEnCoursCA= "false";

			// Liste des Regions
			$scope.listeRegionCache = function () {
				$http
					.get(
						UrlCommun + "/region/getAll")
					.success(
						function (dataRegion) {
							$scope.ListRegion = dataRegion;
						});
			}
			$scope.listeRegionCache();

			// annuler Recherche
			$scope.annulerAjout = function () {

				$scope.fnReportingCourant = {
					"regionId": "",
					"dateMin": "",
					"dateMax": ""
				};

				$scope.rechercheCaEtExpForm.$setPristine();
			}

			//generer rapport mode : List 
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

			$scope.downloadFnReportingCA_Exp = function (fnReportingCourant, action) {
				$scope.traitementEnCoursCA= "true";	
				var newdateSaisieMinFormat = "";
				if (angular.isDefined(fnReportingCourant.dateMin)) {
					$log.debug("==dateIntroductionMin " + fnReportingCourant.dateMin);

					if (fnReportingCourant.dateMin != "") {
						newdateSaisieMinFormat = formattedDate(fnReportingCourant.dateMin);
					} else {
						newdateSaisieMinFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				var newdateSaisieMaxFormat = "";
				if (angular.isDefined(fnReportingCourant.dateMax)) {
					$log.debug("==dateIntroductionMin " + fnReportingCourant.dateMax);

					if (fnReportingCourant.dateMin != "") {
						newdateSaisieMaxFormat = formattedDate(fnReportingCourant.dateMax);
					} else {
						newdateSaisieMaxFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				$log.info("fnReportingCourantOF " + JSON.stringify(fnReportingCourant, null, "  "));
				if (action != null) {
					var url;
					if (action == 1) {
						//CA 
						url = UrlAtelier + "/reportFn/chiffreAffaireGpByClient?regiontId=" + fnReportingCourant.regionId
							+ "&dateMin=" + newdateSaisieMinFormat
							+ "&dateMax=" + newdateSaisieMaxFormat
							+ "&type=pdf";
					} else if (action == 2) {
						//Exp 
						url = UrlAtelier + "/reportFn/expeditionEchantillonGpByClient?regiontId=" + fnReportingCourant.regionId
							+ "&dateMin=" + newdateSaisieMinFormat
							+ "&dateMax=" + newdateSaisieMaxFormat
							+ "&type=pdf";
					}

					//Generate


		 
							
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
					$scope.traitementEnCoursCA= "false";	
					 
					});


					// downloadService.download(url).then(
					// 	function (success) {
					// 		$log.debug('success : ' + success);
					// 	}, function (error) {
					// 		$log.debug('error : ' + error);
					// 	});
				}
			}

		}
	]);
