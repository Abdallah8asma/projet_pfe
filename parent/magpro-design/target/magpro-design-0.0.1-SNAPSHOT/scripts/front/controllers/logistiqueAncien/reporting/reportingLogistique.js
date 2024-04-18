'use strict'
var app = angular.module('gpro.reportingLogistique', []);

app
		.controller(
				'reportingLogistique',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						function($scope, $http, $filter, $log, downloadService,
								UrlCommun, UrlAtelier) {

							$log
									.debug("---reportingLogistique controller -----");

							$scope.reportingCourant = {
								"dateMin" : "",
								"dateMax" : ""
							};

							// annuler Recherche
							$scope.annulerAjout = function() {

								$scope.reportingCourant = {
									"dateMin" : "",
									"dateMax" : ""
								};

								$scope.rechercheClientForm.$setPristine();
							}

							//conversion date en String
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

							
							//Générer rapport de type Client ou de type Produit
							$scope.downloadClientProduitReporting = function(
									reportingCourant, action, typeRapport) {

								$log.info("reportingCourantChaine "
										+ JSON.stringify(reportingCourant,
												null, "  "));

								var newdateSaisieMinFormat = "";

								if (angular.isDefined(reportingCourant.dateMin)) {
									$log.debug("==dateIntroductionMin "
											+ reportingCourant.dateMin);

									if (reportingCourant.dateMin != "") {
										newdateSaisieMinFormat = formattedDate(reportingCourant.dateMin);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log
											.debug("==dateIntroductionMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular.isDefined(reportingCourant.dateMax)) {
									$log.debug("==dateIntroductionMax "
											+ reportingCourant.dateMax);

									if (reportingCourant.dateMax != "") {
										newdateSaisieMaxFormat = formattedDate(reportingCourant.dateMax);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log
											.debug("==dateIntroductionMax Undefined");
								}

								if (action != null) {
									var url;

									//Client
									url = UrlAtelier
											+ "/reportLogistique/reportingClientProduit?dateMin="
											+ newdateSaisieMinFormat
											+ "&dateMax="
											+ newdateSaisieMaxFormat
											+ "&typeRapport=" + typeRapport
											+ "&type=pdf";

									$log.info("URL " + url);
									//Generate
									downloadService.download(url).then(
											function(success) {
												$log.debug('success : '
														+ success);
											}, function(error) {
												$log.debug('error : ' + error);
											});
								}
							}
							
							
							$scope.downloadChaineEtTrameReporting = function(
									reportingCourant, action) {

								$log.info("reportingCourantChaine "
										+ JSON.stringify(reportingCourant,
												null, "  "));

								var newdateSaisieMinFormat = "";

								if (angular.isDefined(reportingCourant.dateMin)) {
									$log.debug("==dateIntroductionMin "
											+ reportingCourant.dateMin);

									if (reportingCourant.dateMin != "") {
										newdateSaisieMinFormat = formattedDate(reportingCourant.dateMin);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log.debug("==dateIntroductionMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular.isDefined(reportingCourant.dateMax)) {
									$log.debug("==dateIntroductionMax "
											+ reportingCourant.dateMax);

									if (reportingCourant.dateMax != "") {
										newdateSaisieMaxFormat = formattedDate(reportingCourant.dateMax);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log
											.debug("==dateIntroductionMax Undefined");
								}

								if (action != null) {
									var url;

									url = UrlAtelier
											+ "/reportLogistique/reportingSousFamille?dateMin="
											+ newdateSaisieMinFormat
											+ "&dateMax="
											+ newdateSaisieMaxFormat
											+ "&type=pdf";

									$log.info("URL " + url);
									//Generate
									downloadService.download(url).then(
											function(success) {
												$log.debug('success : '
														+ success);
											}, function(error) {
												$log.debug('error : ' + error);
											});
								}
							}
							
							$scope.downloadFiniFaconReporting = function(
									reportingCourant, action) {

								$log.info("reportingCourantChaine "
										+ JSON.stringify(reportingCourant,
												null, "  "));

								var newdateSaisieMinFormat = "";

								if (angular.isDefined(reportingCourant.dateMin)) {
									$log.debug("==dateIntroductionMin "
											+ reportingCourant.dateMin);

									if (reportingCourant.dateMin != "") {
										newdateSaisieMinFormat = formattedDate(reportingCourant.dateMin);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log.debug("==dateIntroductionMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular.isDefined(reportingCourant.dateMax)) {
									$log.debug("==dateIntroductionMax "
											+ reportingCourant.dateMax);

									if (reportingCourant.dateMax != "") {
										newdateSaisieMaxFormat = formattedDate(reportingCourant.dateMax);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log
											.debug("==dateIntroductionMax Undefined");
								}

								if (action != null) {
									var url;

									url = UrlAtelier
											+ "/reportLogistique/reportingFiniFacon?dateMin="
											+ newdateSaisieMinFormat
											+ "&dateMax="
											+ newdateSaisieMaxFormat
											+ "&type=pdf";

									$log.info("URL " + url);
									//Generate
									downloadService.download(url).then(
											function(success) {
												$log.debug('success : '
														+ success);
											}, function(error) {
												$log.debug('error : ' + error);
											});
								}
							}


						} ]);
