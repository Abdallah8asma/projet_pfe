
'use strict'

angular
    .module('gpro.elementReglement', [])
    .controller(
        'ElementReglementController', [
        '$scope',
        '$http',
        '$log',
        '$filter',
        'downloadService',
        'UrlAtelier',
        'UrlCommun',
        '$rootScope',
        '$route',
        'initCommercialService',
        '$window',

        function ($scope, $http, $log, $filter,
            downloadService, UrlAtelier, UrlCommun,$window,
            $rootScope, $route, initCommercialService) {
            $log.info("=========Reglement========");

            /**N.B la rechercheMc pour element reglement peut etre changer 
             * par detail reglement au niveau de la partie back-end "/elementReglement/rechercheMulticritere"**/



            // Déclaration des variables globales utilisées
            /** ***Liste des Variables **** */
            var data;
            $scope.displayMode = "list";
            $scope.BLError = false;
            $scope.FacturesError = false;
            $scope.test = [];

            $scope.listRefFacture = [{ "id": 1, "numFacture": "fact1" }, { "id": 2, "refFacture": "fact2" }];
            $scope.listRefBL = [{ "id": 1, "numFacture": "Bl1" }, { "id": 2, "refBL": "Bl2" }];


            //bouton pdf show
            $scope.modePdf = "notActive";
            $scope.idClient = null;


            //REST SERVICE MAGAZINS
            $scope.listeMagazinCache = function () {
                $http
                    .get(UrlAtelier + "/magasin/pdv")
                    .success(
                        function (dataMagazin) {

                            $scope.listeMagazinCache = dataMagazin;
                            $log.debug("listeMagazinCache : " + dataMagazin.length);
                            console.log("listeMagazinCache : " + dataMagazin.length)

                        });
            }
            $scope.listeMagazinCache();

            $scope.listePartieInteresseeCache = function () {
                $http
                    .get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
                    .success(
                        function (dataPartieInteressee) {
                            $scope.listePartieInteresseeCache = dataPartieInteressee;
                            $log.debug("listePartieInteresseeCache : " + dataPartieInteressee.length)
                            console.log("listePartieInteresseeCache : " + dataPartieInteressee.length);

                        });
            }
            $scope.listePartieInteresseeCache();


            // Liste des listGroupeClient
            $scope.listGroupeClient = function () {
                $http
                    .get(
                        UrlCommun
                        + "/groupeClient/all")
                    .success(
                        function (dataCategorieCache) {
                            //	$log.debug("listeCategorie : "+dataCategorieCache.length);
                            $scope.listGroupeClient = dataCategorieCache;

                        });
            }
            $scope.listGroupeClient();


            $scope.CheckWithShift = function () {
                var lastChecked = null;

                $(document).ready(function () {
                    var $chkboxes = $('.chkbox');
                    $chkboxes.click(function (e) {
                        if (!lastChecked) {
                            lastChecked = this;
                            return;
                        }

                        if (e.shiftKey) {
                            var start = $chkboxes.index(this);
                            var end = $chkboxes.index(lastChecked);

                            $chkboxes.slice(Math.min(start, end), Math.max(start, end) + 1).prop('checked', lastChecked.checked);

                        }

                        lastChecked = this;
                    });
                });

            }

            // Rechercher Reglements
            $scope.rechercheReglement = function (reglementCourante) {


                $http
                    .post(
                        UrlAtelier +
                        "/elementReglement/rechercheMulticritere",
                        reglementCourante)

                    .success(
                        function (resultat) {

                            $scope.myData = resultat.list;

                            //  console.log("rechh reglement: "+resultat.list.length);

                            $scope
                                .setPagingData(
                                    $scope.myData,
                                    $scope.pagingOptions.currentPage,
                                    $scope.pagingOptions.pageSize);

                            $scope.rechercheReglementForm
                                .$setPristine();
                            $scope.displayMode = "rechercher";
                            $scope.displayAlert = "sleep";
                        });

            }


            $scope.AffectationReglement = function (reglementCourante) {



                $('.elementReglement').hide();
                $('.BL_FACT_Reglement').show();

                $scope.disableClient = false;
                $scope.disableValider = false;

                $scope.reglementCourante = {};
                $scope.creationReglementForm.$setPristine();

                $scope.finalOperationsList = [];
                for (i = 1; i < 2; i++) {
                    $scope.addElement(i);
                }

                $scope.DisableMontantRegCol();

                $scope.displayMode = "edit";

            }

            // Annulation de l'ajout
            $scope.annulerAjout = function () {


                $scope.reglementCourante = {};

                $scope.InitializeArray();


                //                        $scope.finalBLList = [];
                //                        $scope.finalFacturesList = [];
                //                        $scope.finalOperationsList=[];
                //                        $scope.finalElementList = [];

                $scope.creationReglementForm.$setPristine();
                $scope.rechercheReglementForm.$setPristine();
                $scope.rechercheReglement({});
                //bouton pdf show
                $scope.modePdf = "NotActif";
                $scope.displayMode = "list";
            }

            // declaration variable
            $scope.displayAlert = "sleep";

            /***************************************************
             * Suppression de reglement *
             **************************************************/
            $scope.deleteValue = "oui";
            // Annuler Ajout
            $scope.cancelAddReglement = function (rowform, index,
                id, designation, liste) {
                // console.log("* Designation
                // "+liste[0].designation);
                if (angular.isDefined(id)) {

                    //console.log("DEF ID");

                    $scope.deleteValue = "non";
                    rowform.$cancel();
                    //console.log("CANCEL");

                } else {
                    //console.log("UND ID  " + id);

                    if (designation == "") {
                        $scope.deleteValue == "oui"
                        //                              console.log("Designation : " +
                        //                                  designation);
                        //                              console.log("$scope.deleteValueOUI : " +
                        //                                  $scope.deleteValue);
                        liste.splice(index, 1);
                        rowform.$cancel();
                        // console.log("DELETE");

                    } else {
                        //                              console.log("Designation :" +
                        //                                  designation);
                        //                              console.log("$scope.deleteValueNON : " +
                        //                                  $scope.deleteValue);
                        rowform.$cancel();
                        //console.log("CANCEL");

                    }
                }
                $scope.deleteValue = "oui";
            }

            // declaration variable
            $scope.displayAlert = "sleep";

            // Liste des Clients
            $scope.listClients = function () {
                $http
                    .get(UrlCommun + "/partieInteressee/all")
                    .success(
                        function (dataProduit) {

                            $scope.listClients = dataProduit;


                        });
            }
            $scope.listClients();
            $scope.rechercheReglement({});

            // Liste des Clients
            $scope.listTypes = function () {


                $http
                    .get(UrlAtelier + "/gestionnaireLogistiqueCache/listeTypeReglementCache")
                    .success(
                        function (dataProduit) {

                            $scope.listTypes = dataProduit;
                        });
            }
            $scope.listTypes();

            //methode appelé lors de check le checkbox Montant LB
            $scope.changeMontantLBL = function (bool, index) {

                $scope.CheckWithShift();

                if (bool == true) {
                    $scope.finalBLList[index].disable = false;
                    $scope.finalBLList[index].montantRegle = $scope.finalBLList[index].montantBL;
                }
                else {

                    $scope.finalBLList[index].disable = true;
                    $scope.finalBLList[index].montantRegle = 0;
                }
            }

            //methode appelé lors de check le checkbox Montant LB
            $scope.changeMontantLBLById = function (bool, id) {

                var index = $scope.finalBLList.findIndex(record => record.id === id);

                $scope.CheckWithShift();

                if (bool == true) {
                    $scope.finalBLList[index].disable = false;
                    $scope.finalBLList[index].montantRegle = $scope.finalBLList[index].montantBL;
                }
                else {

                    $scope.finalBLList[index].disable = true;
                    $scope.finalBLList[index].montantRegle = 0;
                }
            }

            //methode appelé lors de check le checkbox Montant Facture
            $scope.changeMontantLFactures = function (bool, index) {

                $scope.CheckWithShift();
                if (bool == true) {
                    $scope.finalFacturesList[index].disable = false;
                    $scope.finalFacturesList[index].montantRegle = $scope.finalFacturesList[index].montantFacture;

                }
                else {
                    $scope.finalFacturesList[index].disable = true;
                    $scope.finalFacturesList[index].montantRegle = 0;

                }
            }


            //methode appelé lors de check le checkbox Montant Facture
            $scope.changeMontantLFacturesById = function (bool, id) {




                var index = $scope.finalFacturesList.findIndex(record => record.id === id);


                $scope.CheckWithShift();
                if (bool == true) {
                    $scope.finalFacturesList[index].disable = false;
                    $scope.finalFacturesList[index].montantRegle = $scope.finalFacturesList[index].montantFacture;

                }
                else {
                    $scope.finalFacturesList[index].disable = true;
                    $scope.finalFacturesList[index].montantRegle = 0;

                }
            }

            // Modification Reglement
            $scope.modifierOuCreerReglement = function () {

                var index = this.row.rowIndex;

                $('.BL_FACT_Reglement').hide();
                $('.elementReglement').show();


                $scope.disableClient = true;
                $scope.disableValider = true;
                //bouton pdf hide
                $scope.modePdf = "actif";


                // get Reglement
                $http
                    .get(
                        UrlAtelier +
                        "/elementReglement/getById:" +
                        $scope.myData[index].id)
                    .success(
                        function (datagetReglement) {

                            $scope.idClient = datagetReglement.partieIntId;
                            // $scope.Valider(datagetReglement.partieIntId);

                            console.log("reglement update: " + datagetReglement.idDepot);
                            $scope.reglementCourante.idDepot = datagetReglement.idDepot;
                            $scope.finalOperationsList = datagetReglement.listDetailsReglement;
                            $scope.finalElementList = datagetReglement.listElementReglement;

                            for (var i = 0; i < $scope.finalElementList.length; i++) {
                                $scope.finalElementList[i].isNew = false;

                                if ($scope.finalElementList[i].refFacture != null) {
                                    $scope.finalElementList[i].checked = true;
                                }
                                else {
                                    $scope.finalElementList[i].checked = false;
                                }
                            }

                        });

                $scope.reglementCourante = $scope.myData[index] ? angular
                    .copy($scope.myData[index])
                    : {};

                $scope.displayMode = "edit";
            }


            // Sauvegarder Reglement
            $scope.sauvegarderAjout = function (reglement) {
                if (angular.isDefined(reglement.id)) {

                    $scope.updateReglement(reglement);
                } else {
                    $scope.creerReglement(reglement);

                }
            }

            /******************************************************************************/
            // Mise à jour des Reglements
            $scope.updateReglement = function (reglement) {
                reglement.listDetailsReglement = $scope.finalOperationsList;
                reglement.listElementReglement = $scope.finalElementList;
                $scope.test = reglement.listElementReglement;

                console.log("update:magazin: " + reglement.idDepot);

                $http
                    .put(
                        UrlAtelier +
                        "/elementReglement/update",
                        reglement)
                    .success(
                        function (reglementModifiee) {


                            // TODO Code à revoir
                            for (var i = 0; i < $scope.myData.length; i++) {

                                if ($scope.myData[i].id == reglementModifiee) {
                                    $scope.myData[i] = reglementModifiee;

                                    break;
                                }
                            }

                            $scope.annulerAjout();
                        });

            }

            // Création Reglement
            $scope.creerReglement = function (reglement) {

                reglement.listDetailsReglement = $scope.finalOperationsList;

                var tmplistElementReglement = [];
                //Facture
                angular.forEach($scope.finalFacturesList, function (elementFact, key) {
                    var tmp = {};

                    tmp.id = null;
                    tmp.montant = elementFact.montantFacture;
                    tmp.refFacture = elementFact.numFacture;
                    tmp.montantDemande = elementFact.montantRegle;
                    tmp.dateEcheance = elementFact.date;
                    tmp.reglementId = elementFact.reglementId;

                    tmplistElementReglement.push(tmp);

                });
                //BL
                angular.forEach($scope.finalBLList, function (elementBl, key) {
                    var tmp = {};

                    tmp.id = null;
                    tmp.montant = elementBl.montantBL;
                    tmp.refBL = elementBl.numBL;
                    tmp.montantDemande = elementBl.montantRegle;
                    tmp.dateEcheance = elementBl.date;
                    tmp.reglementId = elementBl.reglementId;

                    tmplistElementReglement.push(tmp);
                });

                reglement.listElementReglement = tmplistElementReglement;

                $http
                    .post(
                        UrlAtelier +
                        "/elementReglement/create",
                        reglement)
                    .success(
                        function (newreglement) {

                            // TODO getId


                            $scope.annulerAjout();


                        });

            }

            $scope.InitializeArray = function () {

                //initialisation des arrays
                $scope.finalOperationsList = [];
                for (i = 1; i < 2; i++) {
                    $scope.addElement(i);
                }
                $scope.finalBLList = [];
                $scope.selectedAllBL = [];
                for (i = 1; i < 11; i++) {
                    //$scope.addElement(i);
                    $scope.addBLElement(i);
                    $scope.addFacturesElement(i);
                }

            }

            // Suppression Reglement
            $scope.supprimerReglement = function () {

                $http({
                    method: "DELETE",
                    url: UrlAtelier +
                        "/elementReglement/delete:" +
                        $scope.myData[$scope.index].id
                }).success(function () {
                    $scope.myData.splice($scope.index, 1);
                    $scope.closePopupDelete();
                    $scope.rechercheReglement({});

                });


            };

            // Supprimer Detail
            $scope.removeDetailRegelement = function (index) {
                $scope.finalOperationsList.splice(index, 1);
            };

            /*** PDF ***/
            //boutton Generer
            //                   $scope.Generate = function(idreg) {

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


            //                   }
            //generer rapport de tous les Reglement : List
            
            
            
            
            
            
    		$scope.downloadAllReglements = function (
    				bonInventaireCourante) {

    				$http({
    					url: UrlAtelier + "/fiches/listElementReglement",
    					method: "POST",
    					data: bonInventaireCourante, // this is your json
    													// data string
    					headers: {
    						'Content-type': 'application/json',
    					},
    					responseType: 'arraybuffer'
    				}).success(function (data, status, headers, config) {

    					var blob = new Blob([data], { type: "application/vnd.ms-excel" });


    					var fileName = 'detail_reglement_vente' + formattedDate(new Date());
    					var link = document.createElement('a');
    					link.href = window.URL.createObjectURL(blob);
    					link.download = fileName;
    					link.click();
    					window.URL.revokeObjectURL(link.href);


    					// var objectUrl = URL.createObjectURL(blob);
    					// window.open(objectUrl);
    				}).error(function (data, status, headers, config) {
    					// upload failed
    				});


    			};
            
    			// conversion date en String
  			  function formattedDate(date) {
  				    var d = new Date(date),
  				        month = '' + (d.getMonth() + 1),
  				        day = '' + d.getDate(),
  				        year = d.getFullYear();

  				    if (month.length < 2) month = '0' + month;
  				    if (day.length < 2) day = '0' + day;
  				    return [year,month,day].join('-');
  				}
            
            
     /*       $scope.downloadAllReglements = function (reglementCourante) {

                if (typeof reglementCourante.dateReglementMin === 'undefined') {
                    var dateMin = "";
                    console.log("--------undfined -------");
                }
                else {
                    var longDateMin = Date.parse(reglementCourante.dateReglementMin);
                    var varDateMin = new Date(longDateMin);
                    var dateMin = varDateMin.getFullYear() + '-' + (varDateMin.getMonth() + 1) + '-' + varDateMin.getDate();
                    console.log("--------dateMin -------" + dateMin);
                }

                console.log("--------dateMin 2-------" + dateMin);

                if (typeof reglementCourante.dateReglementMax === 'undefined') {
                    var dateMax = "";
                }
                else {
                    var longDateMax = Date.parse(reglementCourante.dateReglementMax);
                    var varDateMax = new Date(longDateMax);
                    var dateMax = varDateMax.getFullYear() + '-' + (varDateMax.getMonth() + 1) + '-' + varDateMax.getDate();
                }

                var url;

                if ($scope.reglementCourante.partieIntId == null) {
                    $scope.reglementCourante.partieIntId = '';
                }

                console.log("------- reglementCourante av url --------- " + JSON.stringify(reglementCourante, null, "  "));

                url = UrlAtelier + "/reportgc/elementReglementList?partieIntId=" + reglementCourante.partieIntId
                    + "&dateReglementDu=" + dateMin
                    + "&dateReglementAu=" + dateMax
                    + "&montantMin=" + reglementCourante.montantMin
                    + "&montantMax=" + reglementCourante.montantMax
                    + "&idDepot=" + reglementCourante.idDepot
                    + "&type=pdf";

                console.log("-- URL--- :" + url);

     			
                var a = document.createElement('a');
                document.body.appendChild(a);
                downloadService.download(url).then(function (result) {
                    var heasersFileName = result.headers(['content-disposition']).substring(17);
                    var fileName = heasersFileName.split('.');
                var typeFile = result.headers(['content-type']);
                    var file = new Blob([result.data], {type: typeFile});
                     var fileURL = window.URL.createObjectURL(file);
                     a.href = fileURL;
                     a.download = fileName[0];
                    $window.open(fileURL)
                     a.click();
                });


                // downloadService.download(url).then(
                //     function (success) {
                //         //$log.debug('success : ' + success);
                //     }, function (error) {
                //         //$log.debug('error : ' + error);
                //     });
            };*/



            //downloadReglement
            $scope.downloadReglement = function (reglementCourante) {
                console.log("------- reglementCourante ** ** " + JSON.stringify(reglementCourante, null, "  "));


                var url;


                url = UrlAtelier + "/reportgc/elementReglement?id=" + reglementCourante.id + "&type=pdf";

                console.log("------- URL---------- :" + url);

                var fileName = '  Liste Bon Sortie	'		;					
                var a = document.createElement('a');
                document.body.appendChild(a);
                downloadService.download(url).then(function (result) {
                    var heasersFileName = result.headers(['content-disposition']).substring(22);
                    var fileName = heasersFileName.split('.');
                var typeFile = result.headers(['content-type']);
                    var file = new Blob([result.data], {type: typeFile});
                     var fileURL = window.URL.createObjectURL(file);
                     a.href = fileURL;
                     a.download = fileName[0];
                    $window.open(fileURL)
                     a.click();
                });


                // downloadService.download(url).then(
                //     function (success) {
                //         //$log.debug('success : ' + success);
                //     }, function (error) {
                //         //$log.debug('error : ' + error);
                //     });
            };





            $scope.pagingOptions = {
                pageSizes: [5, 10, 20],
                pageSize: 20,
                currentPage: 1
            };
            $scope.colDefs = [];
            $scope
                .$watch(
                    'myData',
                    function () {

                   
     if ((angular.isDefined($scope.reglementCourante.numPiece) && $scope.reglementCourante.numPiece != '') 
                              ||

(angular.isDefined($scope.reglementCourante.referenceDetailReglement) && $scope.reglementCourante.referenceDetailReglement != '')

                    ) {


                            $scope.colDefs = [
	
	                             {
                                    field: 'referenceDetReglement',
                                    displayName: 'Ref.Det. Reg',
                                    //width: '10%'
                                },

                                {
                                    field: 'reference',
                                    displayName: 'Ref.Reg',
                                    //  width: '10%'
                                },
                                {
                                    field: 'groupeClientDesignation',
                                    displayName: 'Groupe',
                                    //   width: '10%'
                                },
                                {
                                    field: 'partieIntAbreviation',
                                    displayName: 'Client',
                                    //  width: '20%'
                                },
                                {
                                    field: 'montantTotal',
                                    displayName: 'Mont.Totale',
                                    cellFilter: 'number : 3'
                                    //  width: '8%'
                                },
                                {
                                    field: 'date',
                                    displayName: 'Date',
                                    cellFilter: 'date:\'dd-MM-yyyy\'',
                                    //   width: '7%'
                                },



                                {
                                    field: 'montant',
                                    displayName: 'Montant',
                                    cellFilter: 'number : 3'
                                    //  width: '8%'
                                },
                                {
                                    field: 'refFacture',
                                    displayName: 'Ref.Facture',
                                    //  width: '10%'
                                },


                                {
                                    field: 'numPiece',
                                    displayName: 'Num.Piece',
                                    //  width: '8%'
                                },

                                {
                                    field: 'dateEcheance',
                                    displayName: 'D.Echeance',
                                    cellFilter: 'date:\'dd-MM-yyyy\'',
                                    // width: '7%'
                                },

                                {
                                    field: 'dateEmission',
                                    displayName: 'D.Emission',
                                    cellFilter: 'date:\'dd-MM-yyyy\'',
                                    //   width: '7%'
                                }/*,
                                         
                                         {
                                             field: '',
                                             width:'3%',
                                             cellTemplate: '<div class="buttons TableHeaderalignment" ng-show="!rowform.$visible" >' +
                                                 '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerReglement()"><i class="fa fa-fw fa-pencil"></i></button>' +
                                                 '</div>'
                                         }*/
                            ];
                        }
                        else {
                            $scope.colDefs = [

                                {
                                    field: 'reference',
                                    displayName: 'Ref.Reg',
                                    width: '8%'
                                },
                                {
                                    field: 'groupeClientDesignation',
                                    displayName: 'Groupe',
                                    width: '10%'
                                },
                                {
                                    field: 'partieIntAbreviation',
                                    displayName: 'Client',
                                    width: '20%'
                                },
                                {
                                    field: 'montantTotal',
                                    displayName: 'Mont.Totale',
                                    cellFilter: 'number : 3',
                                    width: '8%'
                                },
                                {
                                    field: 'date',
                                    displayName: 'Date',
                                    cellFilter: 'date:\'dd-MM-yyyy\'',
                                    width: '7%'
                                },



                                {
                                    field: 'montant',
                                    displayName: 'Montant',
                                    cellFilter: 'number : 3',
                                    width: '8%'
                                },
                                {
                                    field: 'refFacture',
                                    displayName: 'Ref.Facture',
                                    width: '10%'
                                },
                                {
                                    field: 'refBL',
                                    displayName: 'Ref.BL',
                                    width: '10%'
                                },


                                {
                                    field: 'montantDemande',
                                    displayName: 'Mont.Demande',
                                    cellFilter: 'number : 3',
                                    width: '8%'
                                },

                                {
                                    field: 'dateEcheance',
                                    displayName: 'D.Echeance',
                                    cellFilter: 'date:\'dd-MM-yyyy\'',
                                    width: '7%'
                                }/*,
                                         
                                         {
                                             field: '',
                                             width:'3%',
                                             cellTemplate: '<div class="buttons TableHeaderalignment" ng-show="!rowform.$visible" >' +
                                                 '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerReglement()"><i class="fa fa-fw fa-pencil"></i></button>' +
                                                 '</div>'
                                         }*/
                            ];

                        }

                    });

            $scope.typeAlert = 0;
            $scope.filterOptions = {
                filterText: "",
                useExternalFilter: true
            };
            $scope.totalServerItems = 0;

            $scope.setPagingData = function (data, page,
                pageSize) {
                var pagedData = data.slice((page - 1) *
                    pageSize, page * pageSize);
                $scope.myData = pagedData;
                $scope.totalServerItems = data.length;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            };

            $scope.getPagedDataAsync = function (pageSize, page,
                searchText) {
                setTimeout(
                    function () {
                        var data;
                        var reglementCourante = $scope.reglementCourante;
                        if (searchText) {
                            var ft = searchText
                                .toLowerCase();
                            $http
                                .post(
                                    UrlAtelier +
                                    "/elementReglement/rechercheMulticritere",
                                    reglementCourante)
                                .success(
                                    function (
                                        largeLoad) {
                                        data = largeLoad.list
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
                            $http
                                .post(
                                    UrlAtelier +
                                    "/elementReglement/rechercheMulticritere",
                                    reglementCourante)
                                .success(
                                    function (
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
                    function (newVal, oldVal) {
                        if (newVal !== oldVal &&
                            newVal.currentPage !== oldVal.currentPage) {
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



            var operationsListWS = [];
            $scope.selectedRef = '';

            // first function to call
            var _init = function () {

                $scope.finalOperationsList = [];
                $scope.finalBLList = [];
                $scope.finalFacturesList = [];
                for (i = 1; i < 11; i++) {
                    $scope.addElement(i);
                    $scope.addBLElement(i);
                    $scope.addFacturesElement(i);
                }
            };

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

            /** ********************************************* */

            //check La liste des BL
            $scope.checkAllBL = function () {

                if ($scope.selectedAllBL == true) {
                    $scope.selectedAllBL = true;
                } else {
                    $scope.selectedAllBL = false;
                }
                angular.forEach($scope.finalBLList, function (item) {

                    item.checked = $scope.selectedAllBL;
                    item.disable = !$scope.selectedAllBL;
                    item.montantRegle = item.montantBL

                });
            }

            //check La liste des Factures
            $scope.checkAllFactures = function () {

                if ($scope.selectedAllFactures == true) {
                    $scope.selectedAllFactures = true;
                } else {
                    $scope.selectedAllFactures = false;
                }
                angular.forEach($scope.finalFacturesList, function (item) {

                    item.checked = $scope.selectedAllFactures;
                    item.disable = !$scope.selectedAllFactures;
                    item.montantRegle = item.montantFacture;
                });
            }






            //disable la colonne Montant reglé
            $scope.DisableMontantRegCol = function () {

                for (var i = 0; i < $scope.finalBLList.length; i++) {
                    $scope.finalBLList[i].checked = false;
                    $scope.finalBLList[i].disable = true;

                }

                for (var i = 0; i < $scope.finalFacturesList.length; i++) {
                    $scope.finalFacturesList[i].checked = false;
                    $scope.finalFacturesList[i].disable = true;

                }

            }


            // used to add new element into list of table
            $scope.addElement = function (_ordre) {
                var tmpElement = {
                    ordre: (_ordre === undefined) ? (parseInt($scope.finalOperationsList[$scope.finalOperationsList.length - 1].ordre) + 1) :
                        _ordre,
                    typeReglementId: '',
                    numPiece: '',
                    banque: '',
                    montant: 0,
                    dateEmission: '',
                    dateEcheance: ''
                };

                if ($scope.finalOperationsList
                    .indexOf(tmpElement) == -1) {
                    $scope.finalOperationsList.push(tmpElement);

                    var t = parseInt(_ordre) + 1;
                    _ordre = t;




                }

            };
            var dataFacture = [];

            $scope.addElementReglement = function (_ordre) {

                var tmpElement = {
                    refFacture: null,
                    refBL: null,
                    montantDemande: 0,
                    dateEcheance: 0,
                    reglementId: null,
                    checked: false,
                    isNew: true
                };

                if ($scope.finalElementList
                    .indexOf(tmpElement) == -1) {
                    $scope.finalElementList.push(tmpElement);

                }

                initCommercialService.getRefFactureList($scope.idClient)
                    .then(
                        function (success) {
                            $scope.b = success;
                            $log.debug(">>>>> Click  B=" + JSON.stringify($scope.b, null, "  "));

                            $scope.listRefFacture = $scope.b;

                            $log.debug(">>>>> Click ....listRefFacture=" + JSON.stringify($scope.listRefFacture, null, "  "));

                            $log.debug('*** RefFactureList *** : ' + JSON.stringify(success, null, "  "));
                        },
                        function (error) {
                            $log.error('****ErrorRefFactureList : ' + error);
                        });
                initCommercialService.getRefBLList($scope.idClient)
                    .then(
                        function (success) {
                            $scope.bL = success;
                            $log.debug(">>>>> Click  B=" + JSON.stringify($scope.bL, null, "  "));

                            $scope.listRefBL = $scope.bL;

                            $log.debug(">>>>> Click ....listRefBL=" + JSON.stringify($scope.listRefBL, null, "  "));

                            $log.debug('*** listRefBL *** : ' + JSON.stringify(success, null, "  "));
                        },
                        function (error) {
                            $log.error('****ErrorRefBLList : ' + error);
                        });

            };

            // used to delete an element from the list
            $scope.deleteElement = function (item, indexLine) {

                if ($scope.finalElementList.length > 1) {
                    // delete line from final list
                    $scope.finalElementList.splice(
                        indexLine, 1);

                }
            };

            //liste des BL
            $scope.addBLElement = function (_ordre) {


                var tmpElement = {
                    ordre: (_ordre === undefined) ? (parseInt($scope.finalBLList[$scope.finalBLList.length - 1].ordre) + 1) :
                        _ordre,
                    code: '',
                    designation: '',
                    temps: 0,
                    pdh: 0,
                    sectionId: null,
                    machineId: null,
                    observations: '',
                    operationId: null,
                    disable: true,
                    comptage: false,
                    checked: false
                };

                if ($scope.finalBLList
                    .indexOf(tmpElement) == -1) {
                    $scope.finalBLList.push(tmpElement);

                    var t = parseInt(_ordre) + 1;
                    _ordre = t;




                }

            };


            //liste des Factures
            $scope.addFacturesElement = function (_ordre) {


                var tmpElement = {
                    ordre: (_ordre === undefined) ? (parseInt($scope.finalFacturesList[$scope.finalFacturesList.length - 1].ordre) + 1) :
                        _ordre,
                    code: '',
                    designation: '',
                    temps: 0,
                    pdh: 0,
                    sectionId: null,
                    machineId: null,
                    observations: '',
                    operationId: null,
                    disable: true,
                    comptage: false,
                    checked: false
                };

                if ($scope.finalFacturesList
                    .indexOf(tmpElement) == -1) {
                    $scope.finalFacturesList.push(tmpElement);

                    var t = parseInt(_ordre) + 1;
                    _ordre = t;

                }

            };

            _init();

        }
    ])
    //end controller

    ;