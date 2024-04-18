'use strict';

angular.module('gpro.reglementInverse', []).controller('ReglementInverseController', [
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

  function (
    $scope,
    $http,
    $log,
    $filter,
    downloadService,
    UrlAtelier,
    UrlCommun,
    $rootScope,
    $route,
    initCommercialService,
    $window
  ) {
    $log.info('=========Reglement========');
    // Déclaration des variables globales utilisées
    /** ***Liste des Variables **** */
    var data;
    $scope.displayMode = 'list';
    $scope.BLError = false;
    $scope.FacturesError = false;
    $scope.test = [];

    $scope.listRefFacture = [
      { id: 1, numFacture: 'fact1' },
      { id: 2, refFacture: 'fact2' },
    ];
    $scope.listRefBL = [
      { id: 1, numFacture: 'Bl1' },
      { id: 2, refBL: 'Bl2' },
    ];

    // bouton pdf show
    $scope.modePdf = 'notActive';
    $scope.idClient = null;
    $scope.isEnCoursValider = false;

    $scope.msgChargement = '';

    $scope.reglementCourante = {"declarerRech" : "oui" , "hasElementReglement":"" , "hasDetailReglement":"","idDepot":""};

    $scope.currentFactureOrBl = { };
	

	     $scope.getListeBanquePI = function () {
              $http.get(UrlCommun+"/banquePI/all").success(function (data) {
                $log.debug("listeCathegorie : "+data.length);
                $scope.listeBanque = data;
              });
            }

            $scope.getListeBanquePI();


    // verification entre date emission et echéance

    $scope.VerifieRegle = function (item, index) {

      if (item.dateEcheance == "") {
        $scope.finalOperationsList[index].regle = false;

      }
      else if (item.dateEcheance < item.dateEmission) {
        $scope.finalOperationsList[index].regle = true;

      }

      else {
        $scope.finalOperationsList[index].regle = false;
      }

    }




    $scope.onChangeTypeReglement = function (item, index) {
	
	//Au niveau de DetailsReglement : Si le type de Reglement est Espece ou RS alors reglé =True

      if (item.typeReglementId != null &&  item.typeReglementId != "") {
	
	    var elementsTypeReglement = $scope.listTypes.filter((e) => e.id == item.typeReglementId) ; 

          if(elementsTypeReglement != null && elementsTypeReglement.length >0){
	
	                      if(elementsTypeReglement[0].regle != null && elementsTypeReglement[0].regle == true){
		
		                              $scope.finalOperationsList[index].regle = true;
                                 	}else
                                    {
	                                  $scope.finalOperationsList[index].regle = false;
                                    }
               }
	
  }
    

    }


    $scope.getListeTaxes = function () {
      $http
        .get(UrlAtelier + "/taxe/getAll")
        .success(
          function (dataTaxe) {

            $scope.listeTaxes = dataTaxe;
          });
    }

    $scope.getListeTaxes();

    // REST SERVICE MAGAZINS
    $scope.listeMagazinCache = function () {
      $http.get(UrlAtelier + '/magasin/pdv').success(function (dataMagazin) {
        $scope.listeMagazinCache = dataMagazin;
        $log.debug('listeMagazinCache : ' + dataMagazin.length);
        console.log('listeMagazinCache : ' + dataMagazin.length);
      });
    };
    $scope.listeMagazinCache();

    $scope.listePartieInteresseeCache = function () {
      $http
        .get(UrlCommun + '/gestionnaireCache/listePartieInteresseeCache')
        .success(function (dataPartieInteressee) {
          $scope.listePartieInteresseeCache = dataPartieInteressee;
          $log.debug(
            'listePartieInteresseeCache : ' + dataPartieInteressee.length
          );
          console.log(
            'listePartieInteresseeCache : ' + dataPartieInteressee.length
          );
        });
    };
    $scope.listePartieInteresseeCache();

    // Liste des CategorieCache
    $scope.listGroupeClient = function () {
      $http
        .get(UrlCommun + '/groupeClient/all')
        .success(function (dataCategorieCache) {
          // $log.debug("listeCategorie : "+dataCategorieCache.length);
          $scope.listGroupeClient = dataCategorieCache;
        });
    };
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

            $chkboxes
              .slice(Math.min(start, end), Math.max(start, end) + 1)
              .prop('checked', lastChecked.checked);
          }

          lastChecked = this;
        });
      });
    };

    // Rechercher Reglements
    $scope.rechercheReglement = function (reglementCourante) {
      $http
        .post(
          UrlAtelier + '/reglementInverse/rechercheMulticritere',
          reglementCourante
        )

        .success(function (resultat) {
          $scope.myData = resultat.list;

          // console.log("rechh reglement: "+resultat.list.length);

          $scope.setPagingData(
            $scope.myData,
            $scope.pagingOptions.currentPage,
            $scope.pagingOptions.pageSize
          );

          $scope.rechercheReglementForm.$setPristine();
          $scope.displayMode = 'rechercher';
          $scope.displayAlert = 'sleep';
        });
    };


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

          $scope.getCurrentReferenceMensuelByDate = function(dateIntro) {
	
	
	
	$http
        	            		.get(
        	            				UrlAtelier
        	            						+ "/reglementInverse/getCurrentReferenceMensuelByDate:"+formattedDate(dateIntro)
        	            						)
        	            		.success(
        	            				function(res) {
        	            					
        	            					$scope.reglementCourante.reference = res;
        	            					$scope.reglementCourante.refAvantChangement = res;
        	            				});
	
	
	  }

    $scope.AffectationReglement = function (reglementCourante) {


 


      $('.elementReglement').hide();
      $('.BL_FACT_Reglement').show();

      $scope.disableClient = false;
      $scope.disableValider = false;

      $scope.reglementCourante = {"date":new Date() , "declarer":true};


  
             $scope.getCurrentReferenceMensuelByDate( $scope.reglementCourante.date);
      $scope.creationReglementForm.$setPristine();

      $scope.finalOperationsList = [];
      for (i = 1; i < 2; i++) {
        $scope.addElement(i);
      }

      $scope.DisableMontantRegCol();

      $scope.displayMode = 'edit';
    };

    // Annulation de l'ajout
    $scope.annulerAjout = function () {
      $scope.listeDocumentProduit = [];
      $scope.isEnCoursValider = false;

         $scope.reglementCourante = {"declarerRech" : "oui" , "hasElementReglement":"" , "hasDetailReglement":"" , "idDepot":""};

      $scope.InitializeArray();

      // $scope.finalBLList = [];
      // $scope.finalFacturesList = [];
      // $scope.finalOperationsList=[];
      // $scope.finalElementList = [];

      $scope.creationReglementForm.$setPristine();
      $scope.rechercheReglementForm.$setPristine();
      $scope.rechercheReglement($scope.reglementCourante);
      // bouton pdf show
      $scope.modePdf = 'NotActif';
      $scope.displayMode = 'list';
    };

    // declaration variable
    $scope.displayAlert = 'sleep';

    /***************************************************************************
	 * Suppression de reglement *
	 **************************************************************************/
    $scope.deleteValue = 'oui';
    // Annuler Ajout
    $scope.cancelAddReglement = function (
      rowform,
      index,
      id,
      designation,
      liste
    ) {
      // console.log("* Designation
      // "+liste[0].designation);
      if (angular.isDefined(id)) {
        // console.log("DEF ID");

        $scope.deleteValue = 'non';
        rowform.$cancel();
        // console.log("CANCEL");
      } else {
        // console.log("UND ID " + id);

        if (designation == '') {
          $scope.deleteValue == 'oui';
          // console.log("Designation : " +
          // designation);
          // console.log("$scope.deleteValueOUI : " +
          // $scope.deleteValue);
          liste.splice(index, 1);
          rowform.$cancel();
          // console.log("DELETE");
        } else {
          // console.log("Designation :" +
          // designation);
          // console.log("$scope.deleteValueNON : " +
          // $scope.deleteValue);
          rowform.$cancel();
          // console.log("CANCEL");
        }
      }
      $scope.deleteValue = 'oui';
    };

    // declaration variable
    $scope.displayAlert = 'sleep';

    // Liste des Clients
    $scope.listClients = function () {
      $http
        .get(UrlCommun + '/partieInteressee/all')
        .success(function (dataProduit) {
          $scope.listClients = dataProduit;
        });
    };
    $scope.listClients();
    $scope.rechercheReglement($scope.reglementCourante);

    // Liste des Clients
    $scope.listTypes = function () {
      $http
        .get(
          UrlAtelier + '/gestionnaireLogistiqueCache/listeTypeReglementCache'
        )
        .success(function (dataProduit) {
          $scope.listTypes = dataProduit;
        });
    };
    $scope.listTypes();

    // methode appelé lors de check le checkbox Montant LB
    $scope.changeMontantLBL = function (bool, index) {
      $scope.CheckWithShift();

      if (bool == true) {
        $scope.finalBLList[index].disable = false;
        $scope.finalBLList[index].montantRegle =
          $scope.finalBLList[index].montantBL;
      } else {
        $scope.finalBLList[index].disable = true;
        $scope.finalBLList[index].montantRegle = 0;
      }
    };

    // methode appelé lors de check le checkbox Montant LB
    $scope.changeMontantLBLById = function (bool, id) {
      var index = $scope.finalBLList.findIndex((record) => record.id === id);

      $scope.CheckWithShift();

      if (bool == true) {
        $scope.finalBLList[index].disable = false;
        $scope.finalBLList[index].montantRegle =
          $scope.finalBLList[index].montantBL;
      } else {
        $scope.finalBLList[index].disable = true;
        $scope.finalBLList[index].montantRegle = 0;
      }
    };

    // methode appelé lors de check le checkbox Montant Facture
    $scope.changeMontantLFactures = function (bool, index) {
      $scope.CheckWithShift();
      if (bool == true) {
        $scope.finalFacturesList[index].disable = false;
        $scope.finalFacturesList[index].montantRegle =
          $scope.finalFacturesList[index].montantFacture;
      } else {
        $scope.finalFacturesList[index].disable = true;
        $scope.finalFacturesList[index].montantRegle = 0;
      }
    };

    // methode appelé lors de check le checkbox Montant Facture
    $scope.changeMontantLFacturesById = function (bool, id) {
      var index = $scope.finalFacturesList.findIndex(
        (record) => record.id === id
      );

      $scope.CheckWithShift();
      if (bool == true) {
        $scope.finalFacturesList[index].disable = false;
        $scope.finalFacturesList[index].montantRegle =
          $scope.finalFacturesList[index].montantFacture;
      } else {
        $scope.finalFacturesList[index].disable = true;
        $scope.finalFacturesList[index].montantRegle = 0;
      }
    };

    // Modification Reglement
    $scope.modifierOuCreerReglement = function () {
      var index = this.row.rowIndex;

      $('.BL_FACT_Reglement').hide();
      $('.elementReglement').show();

      $scope.disableClient = true;
      $scope.disableValider = true;
      // bouton pdf hide
      $scope.modePdf = 'actif';
      $scope.isEnCoursValider = true;
      $scope.msgChargement = 'Chargement est En Cours';
      $scope.displayMode = 'edit';

      // get Reglement
      $http
        .get(UrlAtelier + '/reglementInverse/getById:' + $scope.myData[index].id)
        .success(function (datagetReglement) {
          $scope.idClient = datagetReglement.partieIntId;
          $scope.idGroupeClient = datagetReglement.groupeClientId;

          if ($scope.idGroupeClient != null && $scope.idClient == null) {
            initCommercialService
              .getRefFactureListByGroupe($scope.idGroupeClient)
              .then(
                function (success) {
                  $scope.b = success;
                  // $log.debug(">>>>> Click B="+JSON.stringify($scope.b,
                  // null, " "));

                  $scope.listRefFacture = $scope.b;

                  // $log.debug(">>>>> Click
                  // ....listRefFacture="+JSON.stringify($scope.listRefFacture,
                  // null, " "));

                  // $log.debug('*** RefFactureList *** : '+
                  // JSON.stringify(success,null, " "));
                },
                function (error) {
                  $log.error('****ErrorRefFactureList : ' + error);
                }
              );
            initCommercialService
              .getRefBLListByGroupe($scope.idGroupeClient)
              .then(
                function (success) {
                  $scope.bL = success;
                  // $log.debug(">>>>> Click B="+JSON.stringify($scope.bL,
                  // null, " "));

                  $scope.listRefBL = $scope.bL;

                  // $log.debug(">>>>> Click
                  // ....listRefBL="+JSON.stringify($scope.listRefBL, null, "
                  // "));

                  // $log.debug('*** listRefBL *** : '+
                  // JSON.stringify(success,null, " "));
                },
                function (error) {
                  $log.error('****ErrorRefBLList : ' + error);
                }
              );
          } else {
            initCommercialService.getRefFactureList($scope.idClient).then(
              function (success) {
                $scope.b = success;
                // $log.debug(">>>>> Click B="+JSON.stringify($scope.b, null, "
                // "));

                $scope.listRefFacture = $scope.b;

                // $log.debug(">>>>> Click
                // ....listRefFacture="+JSON.stringify($scope.listRefFacture,
                // null, " "));

                // $log.debug('*** RefFactureList *** : '+
                // JSON.stringify(success,null, " "));
              },
              function (error) {
                $log.error('****ErrorRefFactureList : ' + error);
              }
            );
            initCommercialService.getRefBLList($scope.idClient).then(
              function (success) {
                $scope.bL = success;
                // $log.debug(">>>>> Click B="+JSON.stringify($scope.bL, null, "
                // "));

                $scope.listRefBL = $scope.bL;

                // $log.debug(">>>>> Click
                // ....listRefBL="+JSON.stringify($scope.listRefBL, null, " "));

                // $log.debug('*** listRefBL *** : '+
                // JSON.stringify(success,null, " "));
              },
              function (error) {
                $log.error('****ErrorRefBLList : ' + error);
              }
            );
          }

          // $scope.Valider(datagetReglement.partieIntId);

          console.log('reglement update: ' + datagetReglement.idDepot);
          $scope.listeDocumentProduit = datagetReglement.listDocReglement;
          $scope.reglementCourante.idDepot = datagetReglement.idDepot;
          $scope.finalOperationsList = datagetReglement.listDetailsReglement;
          $scope.finalElementList = datagetReglement.listElementReglement; 

          for (var i = 0; i < $scope.finalElementList.length; i++) {
            $scope.finalElementList[i].isNew = false;

            if ($scope.finalElementList[i].refFacture != null) {
              $scope.finalElementList[i].checked = true;
            } else {
              $scope.finalElementList[i].checked = false;
            }
          }
          $scope.isEnCoursValider = false;
        });

      $scope.reglementCourante = $scope.myData[index]
        ? angular.copy($scope.myData[index])
        : {};

      $scope.displayMode = 'edit';
    };

    // Sauvegarder Reglement
    $scope.sauvegarderAjout = function (reglement) {
      if (angular.isDefined(reglement.id)) {
        $scope.updateReglement(reglement);
      } else {
        $scope.creerReglement(reglement);
      }
    };

    /** *************************************************************************** */
    // Mise à jour des Reglements
    $scope.updateReglement = function (reglement) {
      reglement.listDocReglement= $scope.listeDocumentProduit;

      reglement.listDetailsReglement = $scope.finalOperationsList;
      reglement.listElementReglement = $scope.finalElementList; 
      $scope.test = reglement.listElementReglement;

      console.log('update:magazin: ' + reglement.idDepot);

      $http
        .put(UrlAtelier + '/reglementInverse/update', reglement)
        .success(function (reglementModifiee) {
          // TODO Code à revoir
          for (var i = 0; i < $scope.myData.length; i++) {
            if ($scope.myData[i].id == reglementModifiee) {
              $scope.myData[i] = reglementModifiee;

              break;
            }
          }

          $scope.annulerAjout();
        });
    };

    // Création Reglement
    $scope.creerReglement = function (reglement) {
      reglement.listDetailsReglement = $scope.finalOperationsList;
      reglement.listDocReglement= $scope.listeDocumentProduit;

      var tmplistElementReglement = [];
      // Facture
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
      // BL
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
        .post(UrlAtelier + '/reglementInverse/create', reglement)
        .success(function (newreglement) {
          // TODO getId

          $scope.annulerAjout();
        });
    };

    $scope.InitializeArray = function () {
      // initialisation des arrays
      $scope.finalOperationsList = [];
      for (i = 1; i < 2; i++) {
        $scope.addElement(i);
      }
      $scope.finalBLList = [];
      $scope.finalFacturesList = [];

      $scope.selectedAllBL = [];
      for (i = 1; i < 11; i++) {
        // $scope.addElement(i);
        $scope.addBLElement(i);
        $scope.addFacturesElement(i);
      }
    };

    // Suppression Reglement
    $scope.supprimerReglement = function () {
      $http({
        method: 'DELETE',
        url: UrlAtelier + '/reglementInverse/delete:' + $scope.myData[$scope.index].id,
      }).success(function () {
        $scope.myData.splice($scope.index, 1);
        $scope.closePopupDelete();
        $scope.rechercheReglement($scope.reglementCourante);
      });
    };

    // Supprimer Detail
    $scope.removeDetailRegelement = function (index) {
      $scope.finalOperationsList.splice(index, 1);
    };

    /** * PDF ** */
    // boutton Generer
    // $scope.Generate = function(idreg) {

    // var res = $scope.array.toString();
    // var resP = $scope.arrayP.toString();
    //
    // var url =
    // UrlAtelier+"/report/ficheSuiveuse?ordreFabricationId="+idreg+"&paquetsList="+resP+"&operationsList="+res+"&type=pdf";
    // downloadService.download(url)
    // .then(
    // function(success) {
    // $log.debug('success : '+ success);
    // //$scope.annulerAjout();
    // },
    // function(error) {
    // $log.debug('error : '+ error);
    // });

    // }
    // generer rapport de tous les Reglement : List
    $scope.downloadAllReglements = function (reglementCourante) {
      if (typeof reglementCourante.dateReglementMin === 'undefined') {
        var dateMin = '';
        console.log('--------undfined -------');
      } else {
        var longDateMin = Date.parse(reglementCourante.dateReglementMin);
        var varDateMin = new Date(longDateMin);
        var dateMin =
          varDateMin.getFullYear() +
          '-' +
          (varDateMin.getMonth() + 1) +
          '-' +
          varDateMin.getDate();
        console.log('--------dateMin -------' + dateMin);
      }

      console.log('--------dateMin 2-------' + dateMin);

      if (typeof reglementCourante.dateReglementMax === 'undefined') {
        var dateMax = '';
      } else {
        var longDateMax = Date.parse(reglementCourante.dateReglementMax);
        var varDateMax = new Date(longDateMax);
        var dateMax =
          varDateMax.getFullYear() +
          '-' +
          (varDateMax.getMonth() + 1) +
          '-' +
          varDateMax.getDate();
      }

      var url;

      if ($scope.reglementCourante.partieIntId == null) {
        $scope.reglementCourante.partieIntId = '';
      }

      console.log(
        '------- reglementCourante av url --------- ' +
        JSON.stringify(reglementCourante, null, '  ')
      );

      url =
        UrlAtelier +
        '/reportgc/reglementInverseList?partieIntId=' +
        reglementCourante.partieIntId +
        '&dateReglementDu=' +
        dateMin +
        '&dateReglementAu=' +
        dateMax +
        '&montantMin=' +
        reglementCourante.montantMin +
        '&montantMax=' +
        reglementCourante.montantMax +
        '&idDepot=' +
        reglementCourante.idDepot +

       '&declarerRech=' +
        reglementCourante.declarerRech +

       '&hasElementReglement=' +
        reglementCourante.hasElementReglement +

       '&hasDetailReglement=' +
        reglementCourante.hasDetailReglement +

        '&type=pdf';

      console.log('-- URL--- :' + url);



      var a = document.createElement('a');
      document.body.appendChild(a);
      downloadService.download(url).then(function (result) {
        var heasersFileName = result.headers(['content-disposition']).substring(17);
        var fileName = heasersFileName.split('.');
        fileName[0] = 'Liste_Règlement_' + formattedDate(new Date());
        var typeFile = result.headers(['content-type']);
        var file = new Blob([result.data], { type: typeFile });
        var fileURL = window.URL.createObjectURL(file);
        a.href = fileURL;
        a.download = fileName[0];
        $window.open(fileURL)
        a.click();
      });

      // downloadService.download(url).then(
      // function (success) {
      // //$log.debug('success : ' + success);
      // },
      // function (error) {
      // //$log.debug('error : ' + error);
      // }
      // );
    };

    function formattedDate(date) {
      var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;
      return [year, month, day].join('-');
    }

    // downloadReglement
    $scope.downloadReglement = function (reglementCourante) {
      console.log(
        '------- reglementCourante ** ** ' +
        JSON.stringify(reglementCourante, null, '  ')
      );

      var url;

      url =
        UrlAtelier +
        '/reportgc/reglementInverse?id=' +
        reglementCourante.id +
        '&type=pdf';

      console.log('------- URL---------- :' + url);


      var fileName = '  Liste Bon Sortie	';
      var a = document.createElement('a');
      document.body.appendChild(a);
      downloadService.download(url).then(function (result) {
        var heasersFileName = result.headers(['content-disposition']).substring(22);
        var fileName = heasersFileName.split('.');
        fileName[0] = 'Règlement_' + formattedDate(new Date());



        var typeFile = result.headers(['content-type']);
        var file = new Blob([result.data], { type: typeFile });
        var fileURL = window.URL.createObjectURL(file);
        a.href = fileURL;
        a.download = fileName[0];
        $window.open(fileURL)
        a.click();
      });


      // downloadService.download(url).then(
      // function (success) {
      // //$log.debug('success : ' + success);
      // },
      // function (error) {
      // //$log.debug('error : ' + error);
      // }
      // );
    };




    /** DEBUT***************** DOCUMENTS REGLEMENTS  *******************/

    $scope.listeDocumentProduit = [];
    $scope.name = "FINANCE_CLIENT_REGLEMENT";

    $scope.listeTypeDocumentsCache = $scope.listeTypeDocumentsCacheALL.filter(e => e.module === 'FINANCE_CLIENT_REGLEMENT');



    $scope.showStatus = function (id) {

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
      var designation = "";
      //console.log("show status length: "+$scope.listeTypeDocumentsCache.length);
      for (var i = 0; i < $scope.listeTypeDocumentsCache.length; i++) {
        //console.log("id: "+id+"----listeTypeDocumentCache[i]="+ $scope.listeTypeDocumentsCache[i].id);
        //console.log("---module: "+$scope.listeTypeDocumentCache[i].module);
        if ($scope.listeTypeDocumentsCache[i].id == id) {

          designation = $scope.listeTypeDocumentsCache[i].designation;
          //console.log("designation type doc :" +designation);
          return designation;
        }

      }
    };

    // ajout d'un DocumentProduit
    $scope.ajoutDocumentProduit = function () {

      $scope.DocumentProduitInserree = {
        typeDocumentId: '',
        uidDocument: '',
        path: ''

      };
      $scope.listeDocumentProduit
        .push($scope.DocumentProduitInserree);

    };

    // Enregistrer DocumentProduit
    $scope.saveDocumentProduit = function (
      dataDocumentProduit, id) {
      $scope.deleteValue = "non";
      $log.debug("$scope.deleteValue :"
        + $scope.deleteValue);
      angular.extend(dataDocumentProduit, {
        id: id
      });
    };

    // Supprimer DocumentProduit
    $scope.removeDocumentProduit = function (index) {
      $scope.listeDocumentProduit.splice(index, 1);
    };



    /**FIN ***************** DOCUMENTS REGLEMENTS  *******************/

    $scope.pagingOptions = {
      pageSizes: [5, 10, 20],
      pageSize: 20,
      currentPage: 1,
    };
    $scope.colDefs = [];
    $scope.$watch('myData', function () {
      $scope.colDefs = [
        {
          field: 'reference',
          displayName: 'Ref.Reg',
          // width: '13%'
        },
        {
          field: 'groupeClientDesignation',
          displayName: 'Groupe',
          // width: '15%'
        },

        {
          field: 'partieIntReference',
          displayName: 'Ref.Client',
          // width: '15%'
        },
        {
          field: 'partieIntAbreviation',
          displayName: 'Client',
          // width: '20%'
        },

        {
          field: 'montantTotal',
          displayName: 'Montant',
          cellFilter: 'number : 3'
          // width: '16%'
        },
        {
          field: 'date',
          displayName: 'Date',
          cellFilter: "date:'dd-MM-yyyy'",
          // width: '10%'
        },
        {
          field: '',
          width: '10%',
          cellTemplate:
            `<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
            <button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerReglement()">
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
            </button>
            <button class="ms-CommandButton-button"  ng-click="showPopupDelete(10)" permission="['Finance_Client_Delete']">
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
           </button>
           	</div> `,

          // '<div class="buttons TableHeaderalignment"
          // ng-show="!rowform.$visible" >' +
          // '<button data-nodrag class="btn btn-default btn-xs"
          // ng-click="modifierOuCreerReglement()"><i class="fa fa-fw
          // fa-pencil"></i></button>' +
          // '<button data-nodrag class="btn btn-default btn-xs"
          // ng-click="showPopupDelete(10)"> <i class="fa fa-fw
          // fa-trash-o"></i></button></div>'
        },
      ];
    });

    $scope.typeAlert = 0;
    $scope.filterOptions = {
      filterText: '',
      useExternalFilter: true,
    };
    $scope.totalServerItems = 0;

    $scope.setPagingData = function (data, page, pageSize) {
      var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
      $scope.myData = pagedData;
      $scope.totalServerItems = data.length;
      if (!$scope.$$phase) {
        $scope.$apply();
      }
    };

    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
      setTimeout(function () {
        var data;
        var reglementCourante = $scope.reglementCourante;
        if (searchText) {
          var ft = searchText.toLowerCase();
          $http
            .post(
              UrlAtelier + '/reglementInverse/rechercheMulticritere',
              reglementCourante
            )
            .success(function (largeLoad) {
              data = largeLoad.list.filter(function (item) {
                return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
              });
              $scope.setPagingData(data, page, pageSize);
            });
        } else {
          $http
            .post(
              UrlAtelier + '/reglementInverse/rechercheMulticritere',
              reglementCourante
            )
            .success(function (largeLoad) {
              $scope.setPagingData(largeLoad.list, page, pageSize);
            });
        }
      }, 100);
    };

    $scope.getPagedDataAsync(
      $scope.pagingOptions.pageSize,
      $scope.pagingOptions.currentPage
    );

    $scope.$watch(
      'pagingOptions',
      function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync(
            $scope.pagingOptions.pageSize,
            $scope.pagingOptions.currentPage,
            $scope.filterOptions.filterText
          );
        }
      },
      true
    );
    $scope.$watch(
      'filterOptions',
      function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync(
            $scope.pagingOptions.pageSize,
            $scope.pagingOptions.currentPage,
            $scope.filterOptions.filterText
          );
        }
      },
      true
    );

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

    // check La liste des BL
    $scope.checkAllBL = function () {
      if ($scope.selectedAllBL == true) {
        $scope.selectedAllBL = true;
      } else {
        $scope.selectedAllBL = false;
      }
      angular.forEach($scope.finalBLList, function (item) {
        item.checked = $scope.selectedAllBL;
        item.disable = !$scope.selectedAllBL;
        item.montantRegle = item.montantBL;
      });
    };

    // check La liste des Factures
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
    };

    // boutton valider les reglements
    $scope.Valider = function (clientID) {
      $scope.isEnCoursValider = true;
      $scope.msgChargement = 'Validation est En Cours';

      $http
        .get(UrlAtelier + '/reglementInverse/validateByClientId:' + clientID)
        .success(function (reglement) {
          $scope.finalBLList = reglement.listLivraisonNonRegle;
          $scope.finalFacturesList = reglement.listFactureNonRegle;

          $scope.test = $scope.finalFacturesList;
          $scope.test.concat($scope.finalBLList);
          $scope.DisableMontantRegCol();
          $scope.isEnCoursValider = false;
        });
    };

    // boutton valider Groupe client les reglements
    $scope.ValiderGroupeClient = function (groupeClientId) {
      $scope.isEnCoursValider = true;
      $scope.msgChargement = 'Validation est En Cours';

      $http
        .get(
          UrlAtelier + '/reglementInverse/validateByGroupeClientId:' + groupeClientId
        )
        .success(function (reglement) {
          $scope.finalBLList = reglement.listLivraisonNonRegle;
          $scope.finalFacturesList = reglement.listFactureNonRegle;

          $scope.test = $scope.finalFacturesList;
          $scope.test.concat($scope.finalBLList);
          $scope.DisableMontantRegCol();

          $scope.isEnCoursValider = false;
        });
    };

    // disable la colonne Montant reglé
    $scope.DisableMontantRegCol = function () {
      for (var i = 0; i < $scope.finalBLList.length; i++) {
        $scope.finalBLList[i].checked = false;
        $scope.finalBLList[i].disable = true;
      }

      for (var i = 0; i < $scope.finalFacturesList.length; i++) {
        $scope.finalFacturesList[i].checked = false;
        $scope.finalFacturesList[i].disable = true;
      }
    };

    // used to add new element into list of table
    $scope.addElement = function (_ordre) {
      var newOrdre = 1;

      if ($scope.finalOperationsList.length == 0) newOrdre = 1;
      else
        newOrdre =
          parseInt(
            $scope.finalOperationsList[$scope.finalOperationsList.length - 1]
              .ordre
          ) + 1;

      /*
		 * var tmpElement = { ordre: (_ordre === undefined) ?
		 * (parseInt($scope.finalOperationsList[$scope.finalOperationsList.length -
		 * 1].ordre) + 1) : _ordre, typeReglementId : '', numPiece :'', banque:
		 * '', montant: 0, dateEmission : '', dateEcheance : '' };
		 */

      var tmpElement = {
        ordre: _ordre === undefined ? newOrdre : _ordre,
        typeReglementId: '',
        numPiece: '',
        banque: '',
        montant: 0,
        dateEmission: new Date(),
        dateEcheance: '',
      };

      if ($scope.finalOperationsList.indexOf(tmpElement) == -1) {
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
        isNew: true,
      };

      if ($scope.finalElementList.indexOf(tmpElement) == -1) {
        $scope.finalElementList.push(tmpElement);
      }

      // $scope.idClient = datagetReglement.partieIntId;
      // $scope.idGroupeClient = datagetReglement.groupeClientId;

      /*
		 * if($scope.idGroupeClient != null && $scope.idClient == null){
		 * 
		 * initCommercialService.getRefFactureListByGroupe($scope.idGroupeClient)
		 * .then( function(success) { $scope.b = success; // $log.debug(">>>>>
		 * Click B="+JSON.stringify($scope.b, null, " "));
		 * 
		 * $scope.listRefFacture = $scope.b;
		 *  // $log.debug(">>>>> Click
		 * ....listRefFacture="+JSON.stringify($scope.listRefFacture, null, "
		 * "));
		 *  // $log.debug('*** RefFactureList *** : '+
		 * JSON.stringify(success,null, " ")); }, function(error) {
		 * $log.error('****ErrorRefFactureList : '+ error); });
		 * initCommercialService.getRefBLListByGroupe($scope.idGroupeClient)
		 * .then( function(success) { $scope.bL = success; //$log.debug(">>>>>
		 * Click B="+JSON.stringify($scope.bL, null, " "));
		 * 
		 * $scope.listRefBL = $scope.bL;
		 * 
		 * //$log.debug(">>>>> Click
		 * ....listRefBL="+JSON.stringify($scope.listRefBL, null, " "));
		 * 
		 * //$log.debug('*** listRefBL *** : '+ JSON.stringify(success,null, "
		 * ")); }, function(error) { $log.error('****ErrorRefBLList : '+ error);
		 * });
		 * 
		 * }else {
		 * 
		 * initCommercialService.getRefFactureList($scope.idClient) .then(
		 * function(success) { $scope.b = success; //$log.debug(">>>>> Click
		 * B="+JSON.stringify($scope.b, null, " "));
		 * 
		 * $scope.listRefFacture = $scope.b;
		 * 
		 * //$log.debug(">>>>> Click
		 * ....listRefFacture="+JSON.stringify($scope.listRefFacture, null, "
		 * "));
		 * 
		 * //$log.debug('*** RefFactureList *** : '+
		 * JSON.stringify(success,null, " ")); }, function(error) {
		 * $log.error('****ErrorRefFactureList : '+ error); });
		 * initCommercialService.getRefBLList($scope.idClient) .then(
		 * function(success) { $scope.bL = success; //$log.debug(">>>>> Click
		 * B="+JSON.stringify($scope.bL, null, " "));
		 * 
		 * $scope.listRefBL = $scope.bL;
		 * 
		 * //$log.debug(">>>>> Click
		 * ....listRefBL="+JSON.stringify($scope.listRefBL, null, " "));
		 * 
		 * //$log.debug('*** listRefBL *** : '+ JSON.stringify(success,null, "
		 * ")); }, function(error) { $log.error('****ErrorRefBLList : '+ error);
		 * });
		 *  }
		 */
    };

    $scope.onChangeRefFacture = function (refFacture, index) {
      if (refFacture != null) {
        // $log.debug(">>>>> Click onChangeRefFacture ...");
        // $log.debug(">>>>> RefFacture = ",refFacture);
        // $log.debug(">>>>> index = ",index);

        var elementListRefFacture = $scope.listRefFacture.filter(
          (e) => e.numFacture === refFacture
        );

        if (elementListRefFacture && elementListRefFacture[0]) {
          $scope.finalElementList[index].montantDemande =
            elementListRefFacture[0].montantFacture;
          $scope.finalElementList[index].dateEcheance =
            elementListRefFacture[0].date;
        }
      }
    };

    $scope.onChangeRefBL = function (refBL, index) {
      if (refBL != null) {
        // $log.debug(">>>>> Click onChangeRefBL ...");
        // $log.debug(">>>>> RefBL = ",refBL);
        // $log.debug(">>>>> index = ",index);

        var elementListRefBL = $scope.listRefBL.filter(
          (e) => e.numBL === refBL
        );

        if (elementListRefBL && elementListRefBL[0]) {
          $scope.finalElementList[index].montantDemande =
            elementListRefBL[0].montantBL;
          $scope.finalElementList[index].dateEcheance =
            elementListRefBL[0].date;
        }
      }
    };


    $scope.calculerPUTTC = function(prixUnitaireHT,taxeId){

      var element = $scope.listeTaxes.filter(function (node) {
        return node.id == taxeId;
      });

      if (angular.isDefined(element[0]) && prixUnitaireHT != null) {
        var valeurTaxe = element[0].valeur;

       var prixVenteTTC = prixUnitaireHT * (1 + valeurTaxe / 100);
         
        
        //$scope.produitCourante.prixVenteTTC.toFixed(3);
        return Math.round(prixVenteTTC*1000)/1000;
      }

    }

      // used to delete an element from the list
      $scope.showPanelDetailBlorFacture = function (item, indexLine) {

        console.log("Appel showPanelDetailBlorFacture ");


        if($scope.finalElementList[indexLine].checked){
        
          console.log("Facture ");
           //Facture   //refFacture


           $http
           .get(
             UrlAtelier
             + "/facture/getFactureByReference:" + $scope.finalElementList[indexLine].refFacture)
           .success(
             function (datagetFactureVente) {



              $scope.currentFactureOrBl = datagetFactureVente ;


              $scope.currentFactureOrBl.type  = 'Facture';

           

              $scope.currentFactureOrBl.detail = datagetFactureVente.listDetFactureVente;

             });



        }else

        {
          console.log("BL ");
          //BL  //refBL

		// getBonLivVente
    $http
    .get(
        UrlAtelier
            + "/bonlivraison/getBonLivraisonByReference?reference="
            + $scope.finalElementList[indexLine].refBL)
    .success(
        function(
            dataGetBonLivVente) {

              $scope.currentFactureOrBl = dataGetBonLivVente ;
              $scope.currentFactureOrBl.type  = 'BL';

              $scope.currentFactureOrBl.detail = dataGetBonLivVente.listDetLivraisonVente;

          

            })  ;

          




        }

       



          document.getElementById("btnPanelShowDetailBLorFacture").click();


      /*  if ($scope.finalElementList.length > 1) {
          // delete line from final list
          $scope.finalElementList.splice(indexLine, 1);
        }*/
      };
    // used to delete an element from the list
    $scope.deleteElement = function (item, indexLine) {
      //if ($scope.finalElementList.length > 1) {
        // delete line from final list
        $scope.finalElementList.splice(indexLine, 1);
     // }
    };

    // liste des BL
    $scope.addBLElement = function (_ordre) {
      /*
		 * var tmpElement = { ordre: (_ordre === undefined) ?
		 * (parseInt($scope.finalBLList[$scope.finalBLList.length - 1].ordre) +
		 * 1) : _ordre, code : '', designation : '', temps: 0, pdh: 0, sectionId :
		 * null, machineId : null, observations: '', operationId : null,
		 * disable: true, comptage : false, checked : false };
		 * 
		 * if ($scope.finalBLList .indexOf(tmpElement) == -1) {
		 * $scope.finalBLList.push(tmpElement);
		 * 
		 * var t = parseInt(_ordre)+1; _ordre =t;
		 * 
		 * 
		 * 
		 *  }
		 */
    };

    // liste des Factures
    $scope.addFacturesElement = function (_ordre) {
      /*
		 * var tmpElement = { ordre: (_ordre === undefined) ?
		 * (parseInt($scope.finalFacturesList[$scope.finalFacturesList.length -
		 * 1].ordre) + 1) : _ordre, code : '', designation : '', temps: 0, pdh:
		 * 0, sectionId : null, machineId : null, observations: '', operationId :
		 * null, disable: true, comptage : false, checked : false };
		 * 
		 * if ($scope.finalFacturesList .indexOf(tmpElement) == -1) {
		 * $scope.finalFacturesList.push(tmpElement);
		 * 
		 * var t = parseInt(_ordre)+1; _ordre =t;
		 *  }
		 * 
		 * 
		 */
    };

    _init();
  },
]);
// end controller
