
'use strict'

angular
	.module('gpro.rapprochement', [])
	.controller(
		'rapprochementController', [
		'$scope',
		'$http',
		'$log',
		'$filter',
		'downloadService',
		'UrlAtelier',
		'UrlCommun',
		'$rootScope',
		'$route',
		'$window',
		'$timeout',
		function($scope, $http, $log, $filter,
			downloadService, UrlAtelier, UrlCommun,
			$rootScope, $route, $window,$timeout) {
			$log.info("=========echeancier========");

			// Déclaration des variables globales utilisées
			/** ***Liste des Variables **** */
			var data;

			$scope.displayMode = "list";
			
			              $scope.echeancierCourant = {
                          "reference":"",
                          "partieIntId":"",
                          "typeReglementId":"",
                          "dateReglementDu":"",
                          "dateReglementAu":"",
                          "dateEmissionDu":"",
                          "dateEmissionAu":"",
                          "dateEcheanceDu":"",
                          "dateEcheanceAu":"",
                          "numPiece":"",
                          "espece":"",
                          "regle":"",
                          "declarerRech":""
                        };


               	 if ($scope.clientActif.blackMode == false) {
						 $scope.echeancierCourant.declarerRech = "oui";

					}

			$scope.listATerme = [{ "valeur": true, "designation": "Oui" }, { "valeur": false, "designation": "Non" }];
			// Liste des listeRegion
			$scope.listeRegionCache = function() {
				//TODO cache
				$http
					.get(
						UrlCommun
						+ "/region/getAll")
					.success(
						function(dataRegionCache) {
							$log.debug("listeRegionCache : "
								+ dataRegionCache.length);
							$scope.listeRegionCache = dataRegionCache;
						});
			}


			$scope.listeRegionCache();


			$scope.getListeBanquePI = function() {
				$http.get(UrlCommun + "/banquePI/all").success(function(data) {
					$log.debug("listeCathegorie : " + data.length);
					$scope.listeBanque = data;
				});
			}

			$scope.getListeBanquePI();


			// Liste des listGroupeClient
			$scope.listGroupeClient = function() {
				console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				$http
					.get(
						UrlCommun
						+ "/groupeClient/all")
					.success(
						function(dataCategorieCache) {
							//console.log("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
							//	$log.debug("listeCategorie : "+dataCategorieCache.length);
							$scope.listGroupeClient = dataCategorieCache;

						});
			}
			$scope.listGroupeClient();

			// Rechercher echeanciers
			$scope.rechercheecheancier = function(echeancierCourant) {
				//                	var echeancierCourant = {
				//					                		  "partieIntId": "",
				//					                		  "typeReglementId": "",
				//					                		  "dateReglementDu":"",
				//					                		  "dateReglementAu":""};

				$http
					.post(
						UrlAtelier +
						"/echeancierClient/rechercheMulticritere",
						echeancierCourant)

					.success(
						function(resultat) {
							$scope.myData = resultat.list;

							$scope
								.setPagingData(
									$scope.myData,
									$scope.pagingOptions.currentPage,
									$scope.pagingOptions.pageSize);

							$scope.rechercheecheancierForm
								.$setPristine();
							$scope.displayMode = "rechercher";
							$scope.displayAlert = "sleep";
						});

			}

			// Annulation de l'ajout
			$scope.annulerAjout = function() {
				$scope.echeancierCourant = {
					"reference": "",
					"partieIntId": "",
					"typeReglementId": "",
					"dateReglementDu": "",
					"dateReglementAu": "",
					"dateEmissionDu": "",
					"dateEmissionAu": "",
					"dateEcheanceDu": "",
					"dateEcheanceAu": "",
					"numPiece": "",
					"espece": "",
					"regle": "",
					 "declarerRech":""
				};
				
				
					 if ($scope.clientActif.blackMode == false) {
						 $scope.echeancierCourant.declarerRech = "oui";

					}
				
				$scope.rechercheecheancierForm.$setPristine();
				$scope.rechercheecheancier($scope.echeancierCourant);
				$scope.displayMode = "list";
			}
			
		

			// declaration variable
			$scope.displayAlert = "sleep";

			//listeEspece
			$scope.listeEspece = [{ value: 1, designation: "espece" }, { value: 2, designation: "non espece" }];

			// Liste des refReglement
			$scope.listrefReglement = function() {


				$http
					.get(UrlAtelier + "/reglement/listeRefReglement")
					.success(
						function(dataProduit) {

							$scope.listrefReglement = dataProduit;
						});
			}
			$scope.listrefReglement();


			// Liste des Clients
			$scope.listClients = function() {


				$http
					.get(UrlCommun + "/partieInteressee/all")
					.success(
						function(dataProduit) {

							$scope.listClients = dataProduit;
						});
			}
			$scope.listClients();

			// Liste des Types
			$scope.listTypes = function() {


				$http
					.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeTypeReglementCache")
					.success(
						function(dataProduit) {

							$scope.listTypes = dataProduit;
						});
			}
			$scope.listTypes();


			//$scope.rechercheecheancier($scope.echeancierCourant);

			//boutton Generer
			$scope.Generate = function(idreg) {
				//                       var res = $scope.array.toString();
				//                       var resP = $scope.arrayP.toString();
				//                                      
				//                       var url = UrlAtelier+"/report/ficheSuiveuse?ordreFabricationId="+idreg+"&paquetsList="+resP+"&operationsList="+res+"&type=pdf";
				//              downloadService.download(url)
				//                  .then(
				//                      function(success) {
				//                        $log.debug('success : '+ success);
				//                        //$scope.annulerAjout();
				//                      },
				//                      function(error) {
				//                        $log.debug('error : '+ error);
				//                      });
				//              

			}

			function formattedDate(date) {
				
					if(!angular.isDefined(date))
						 return "";
					
						
						if(date == null)
						 return "";
					
						if(date == "")
						 return "";
					
					
				var d = new Date(date),
					month = '' + (d.getMonth() + 1),
					day = '' + d.getDate(),
					year = d.getFullYear();

				if (month.length < 2) month = '0' + month;
				if (day.length < 2) day = '0' + day;
				return [year, month, day].join('-');
			}
			//generer rapport de tous les Ordre de Fabrication. mode : List
			$scope.downloadAllecheanciers = function(echeancierCourant, typeRapport) {
				var avecTerme;
				var nomRapport;
				var url;
				var newdateSaisieMinFormat = "";
				if (angular.isDefined(echeancierCourant.dateReglementDu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateReglementDu);

					if (echeancierCourant.dateReglementDu != "") {
						newdateSaisieMinFormat = formattedDate(echeancierCourant.dateReglementDu);
					} else {
						newdateSaisieMinFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				var newdateSaisieMaxFormat = "";
				if (angular.isDefined(echeancierCourant.dateReglementAu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateReglementAu);

					if (echeancierCourant.dateReglementAu != "") {
						newdateSaisieMaxFormat = formattedDate(echeancierCourant.dateReglementAu);
					} else {
						newdateSaisieMaxFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				//
				var newdateEchecMinFormat = "";
				if (angular.isDefined(echeancierCourant.dateEcheanceDu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateEcheanceDu);

					if (echeancierCourant.dateEcheanceDu != "") {
						newdateEchecMinFormat = formattedDate(echeancierCourant.dateEcheanceDu);
					} else {
						newdateEchecMinFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				var newdateEchecMaxFormat = "";
				if (angular.isDefined(echeancierCourant.dateEcheanceAu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateEcheanceAu);

					if (echeancierCourant.dateEcheanceAu != "") {
						newdateEchecMaxFormat = formattedDate(echeancierCourant.dateEcheanceAu);
					} else {
						newdateEchecMaxFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}
				if (typeRapport == 0)//Paiement
				{
					avecTerme = echeancierCourant.avecTerme;
					nomRapport = "Paiement Client";
				}
				else if (typeRapport == 1)//Echeancier
				{
					avecTerme = true;
					nomRapport = "Echeancier Client"
				}

				//$log.info("------- echeancierCourant " + JSON.stringify(echeancierCourant, null, "  ") );
				url = UrlAtelier + "/reportgc/listEcheance?reference=" + echeancierCourant.reference
					+ "&referenceDetReglement=" + echeancierCourant.referenceDetReglement
					+ "&partieIntId=" + echeancierCourant.partieIntId
					+ "&dateReglementDu=" + newdateSaisieMinFormat
					+ "&dateReglementAu=" + newdateSaisieMaxFormat
					+ "&dateEcheanceDu=" + newdateEchecMinFormat
					+ "&dateEcheanceAu=" + newdateEchecMaxFormat
				     + "&dateDepotBanqueDe="+formattedDate(echeancierCourant.dateDepotBanqueDe)
                      + "&dateDepotBanqueA="+formattedDate(echeancierCourant.dateDepotBanqueA)

					
					+ "&numPiece=" + echeancierCourant.numPiece
					+ "&regle=" + echeancierCourant.regle
					+ "&typeReglementId=" + echeancierCourant.typeReglementId
					+ "&avecTerme=" + avecTerme
					+ "&nomRapport=" + nomRapport
					
			        + "&declarerRech="+echeancierCourant.declarerRech

					+ "&type=pdf";

				$log.info("------- URL " + url);



				var a = document.createElement('a');
				document.body.appendChild(a);
				downloadService.download(url).then(function(result) {
					var heasersFileName = result.headers(['content-disposition']).substring(17);
					var fileName = heasersFileName.split('.');

					fileName[0] = nomRapport + '_' + formattedDate(new Date());
					var typeFile = result.headers(['content-type']);
					var file = new Blob([result.data], { type: typeFile });
					var fileURL = window.URL.createObjectURL(file);
					a.href = fileURL;
					a.download = fileName[0];
					$window.open(fileURL)
					a.click();
				});


				//   downloadService.download(url).then(
				// 							function(success) {
				// 								$log.debug('success : '
				// 										+ success);
				// 							},
				// 							function(error) {
				// 								$log.debug('error : '
				// 										+ error);
				// 							});
			};


			//generer rapport de tous les Ordre de Fabrication. mode : List
			$scope.downloadAllecheanciersExcel = function(echeancierCourant, typeRapport) {
				var avecTerme;
				var nomRapport;
				var url;
				var newdateSaisieMinFormat = "";
				if (angular.isDefined(echeancierCourant.dateReglementDu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateReglementDu);

					if (echeancierCourant.dateReglementDu != "") {
						newdateSaisieMinFormat = formattedDate(echeancierCourant.dateReglementDu);
					} else {
						newdateSaisieMinFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				var newdateSaisieMaxFormat = "";
				if (angular.isDefined(echeancierCourant.dateReglementAu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateReglementAu);

					if (echeancierCourant.dateReglementAu != "") {
						newdateSaisieMaxFormat = formattedDate(echeancierCourant.dateReglementAu);
					} else {
						newdateSaisieMaxFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				//
				var newdateEchecMinFormat = "";
				if (angular.isDefined(echeancierCourant.dateEcheanceDu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateEcheanceDu);

					if (echeancierCourant.dateEcheanceDu != "") {
						newdateEchecMinFormat = formattedDate(echeancierCourant.dateEcheanceDu);
					} else {
						newdateEchecMinFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}

				var newdateEchecMaxFormat = "";
				if (angular.isDefined(echeancierCourant.dateEcheanceAu)) {
					$log.debug("==dateIntroductionMin " + echeancierCourant.dateEcheanceAu);

					if (echeancierCourant.dateEcheanceAu != "") {
						newdateEchecMaxFormat = formattedDate(echeancierCourant.dateEcheanceAu);
					} else {
						newdateEchecMaxFormat = "";
					}
				} else {
					$log.debug("==dateIntroductionMin Undefined");
				}
				if (typeRapport == 0)//Paiement
				{
					avecTerme = echeancierCourant.avecTerme;
					nomRapport = "Paiement Client";
				}
				else if (typeRapport == 1)//Echeancier
				{
					avecTerme = true;
					nomRapport = "Echeancier Client"
				}

				//$log.info("------- echeancierCourant " + JSON.stringify(echeancierCourant, null, "  ") );
				url = UrlAtelier + "/fiches/listEcheance?reference=" + echeancierCourant.reference
					+ "&referenceDetReglement=" + echeancierCourant.referenceDetReglement
					+ "&partieIntId=" + echeancierCourant.partieIntId
					+ "&dateReglementDu=" + newdateSaisieMinFormat
					+ "&dateReglementAu=" + newdateSaisieMaxFormat
					+ "&dateEcheanceDu=" + newdateEchecMinFormat
					+ "&dateEcheanceAu=" + newdateEchecMaxFormat
					
				    + "&dateDepotBanqueDe="+formattedDate(echeancierCourant.dateDepotBanqueDe)
                    + "&dateDepotBanqueA="+formattedDate(echeancierCourant.dateDepotBanqueA)


					+ "&numPiece=" + echeancierCourant.numPiece
					+ "&regle=" + echeancierCourant.regle
					+ "&typeReglementId=" + echeancierCourant.typeReglementId
					+ "&avecTerme=" + avecTerme
					+ "&nomRapport=" + nomRapport
			        + "&declarerRech="+echeancierCourant.declarerRech
					+ "&type=pdf";

				$log.info("------- URL " + url);



				var a = document.createElement('a');
				document.body.appendChild(a);
				downloadService.download(url).then(function(result) {
					var heasersFileName = result.headers(['content-disposition']).substring(17);
					var fileName = heasersFileName.split('.');

					fileName[0] = nomRapport + '_' + formattedDate(new Date());
					var typeFile = result.headers(['content-type']);
					var file = new Blob([result.data], { type: typeFile });
					var fileURL = window.URL.createObjectURL(file);
					a.href = fileURL;
					a.download = fileName[0];
					$window.open(fileURL)
					a.click();
				});


				//   downloadService.download(url).then(
				// 							function(success) {
				// 								$log.debug('success : '
				// 										+ success);
				// 							},
				// 							function(error) {
				// 								$log.debug('error : '
				// 										+ error);
				// 							});
			};


			$scope.updateDetailReglement = function(detailReglement) {

				console.log("detailReglement ",detailReglement);
				
				
				//console.log("isReglee ",isReglee);

				var conf = confirm("Veuillez confirmer la modification !")



				if (conf) {
					
					detailReglement.reference = detailReglement.referenceDetReglement ;

					$http
						.put(
							UrlAtelier
							+ "/detailsReglement/update",detailReglement)
						.success(
							function(data) {
								$scope.alertCreatSuc = true;
							$timeout(function () {  
						      $scope.alertCreatSuc = false;
							 }, 3000);
							});
				} else {
				   
				}

			}


			$scope.pagingOptions = {
				pageSizes: [5, 10, 20],
				pageSize: 20,
				currentPage: 1
			};
			$scope.colDefs = [];
			$scope
				.$watch(
					'myData',
					function() {
						$scope.colDefs = [

							{
								field: 'referenceDetReglement',
								displayName: 'Ref. Transact.',
								width: '10%'
							},

							{
								field: 'dateReglement',
								displayName: 'Date',
								cellFilter: 'date:\'dd-MM-yyyy\'',
								width: '10%'
							},
							{
								field: 'refReglement',
								displayName: 'Ref.Regl',
								width: '10%'
							}, {
								field: 'typeReglement',
								displayName: 'Type',
								width: '10%'
							}, {
								field: 'clientAbreviation',
								displayName: 'Client',
								width: '10%'
							}, {
								field: 'numPiece',
								displayName: 'N° Pièce',
								width: '10%'
							}, {
								field: 'banque',
								displayName: 'Banque',
								width: '10%'
							},

             

							{
								field: 'montant',
								displayName: 'Montant ',
								width: '10%'
							}, {
								field: 'dateEcheance',
								displayName: 'Échéance',
								cellFilter: 'date:\'dd-MM-yyyy\'',
								width: '10%'
							}, {
								field: 'regle',
								displayName: 'Reglé',
								cellTemplate: '<input type="checkbox" ng-model="row.entity.regle"  style="margin: 8px;"/>',
								width: '10%'
							}, {
								field: 'observation',
								displayName: 'Observations',
							    cellTemplate: '<input type="text" ng-model="row.entity.observation"/>',
								width: '10%'
							},
							{
								field: 'banqueSociete',
								displayName: 'Banque Soc.',
								cellTemplate: `  <select  ui-select2 ng-model="row.entity.banqueSociete"   data-placeholder="" class="form-control" style="border: 1px solid #e5eaee !important;" >
                                                   <option value=""></option>
                                                   <option ng-repeat="categorie in listeBanque | filter : {societe : true}" value="{{categorie.abreviation}}">{{categorie.code}}/{{categorie.abreviation}}</option>
                                                 </select> `,

								width: '10%'
							},

							{
								field: 'dateDepotBanque',
								displayName: 'D.Depot Banque',
									cellTemplate: '<input type="date"   datepicker-popup="{{format}}" ng-model="row.entity.dateDepotBanque">',
								width: '15%'
							}
							,

							{
								field: 'chargeBanque',
								displayName: 'Charge Banque',
								cellTemplate: '<input type="number" ng-model="row.entity.chargeBanque"/>',
								
								width: '10%'
							},
							{
								field: 'tvaBanque',
								displayName: 'TVA Banque',
								cellTemplate: '<input type="number" ng-model="row.entity.tvaBanque"/>',
								width: '10%'
							},
		   {
          field: '',
          width: '5%',
          cellTemplate:
            `
            <button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="updateDetailReglement(row.entity)">
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
            </button> 	 `
    
           
        }
						];
					});

			$scope.typeAlert = 0;
			$scope.filterOptions = {
				filterText: "",
				useExternalFilter: true
			};
			$scope.totalServerItems = 0;

			$scope.setPagingData = function(data, page,
				pageSize) {
				var pagedData = data.slice((page - 1) *
					pageSize, page * pageSize);
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
						var echeancierCourant = $scope.echeancierCourant;
						if (searchText) {
							var ft = searchText
								.toLowerCase();
							$http
								.post(
									UrlAtelier +
									"/echeancierClient/rechercheMulticritere",
									echeancierCourant)
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
									UrlAtelier +
									"/echeancierClient/rechercheMulticritere",
									echeancierCourant)
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
						if (newVal !== oldVal &&
							newVal.currentPage !== oldVal.currentPage) {
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



			var operationsListWS = [];
			$scope.selectedRef = '';




			$scope.gridOptions = {

				data: 'myData',
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







		}
	])
	//.controller('DatepickerDemoController', ['$scope', function ($scope) {
	//    $scope.today = function() {
	//        $scope.dt = new Date();
	//      };
	//      $scope.today();
	//
	//      $scope.clear = function () {
	//        $scope.dt = null;
	//      };
	//
	//      // Disable weekend selection
	//      $scope.disabled = function(date, mode) {
	//        return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	//      };
	//
	//      $scope.toggleMin = function() {
	//        $scope.minDate = $scope.minDate ? null : new Date();
	//      };
	//      $scope.toggleMin();
	//
	//      $scope.open = function($event) {
	//        $event.preventDefault();
	//        $event.stopPropagation();
	//
	//        $scope.opened = true;
	//      };
	//
	//      $scope.dateOptions = {
	//        formatYear: 'yy',
	//        startingDay: 1
	//      };
	//
	//      $scope.initDate = new Date('2016-15-20');
	//      $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	//      $scope.format = $scope.formats[0];
	//    }])
	;