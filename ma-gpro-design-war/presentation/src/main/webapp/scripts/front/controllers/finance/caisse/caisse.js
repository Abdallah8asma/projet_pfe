'use strict';
angular.module('gpro.glCaisse', []).controller('caisseCtrl', [
  '$scope',
  '$http',
  '$filter',
  '$rootScope',
  '$log',
  'downloadService',
  'UrlAtelier',
  'UrlCommun',
  '$window',
  function (
    $scope,
    $http,
    $filter,
    $rootScope,
    $log,
    downloadService,
    UrlAtelier,
    UrlCommun,
    $window
  ) {
    // ** Variables Recherche
    $scope.listProduitReceptions = [];
    $scope.ListClientcaisseCache = [];

    // **Variables Modif/Ajout
    // init objetCourant
    $scope.caisseCourant = {
      dateClotureDe: '',
      dateClotureAu: '',
      reference: '',
      montantEspecesDe: '',
      MontantChequeDe: '',
      MontantChequeAu: '',
      cloture: '',
    };

    $scope.showAdd = function () {
      $scope.displayMode = 'edit';
      $scope.mode = 'Notactif';
      $scope.caisseCourant = undefined;
      $scope.creationSSForm.$setPristine();
    };
    $scope.listeProduitCache = [];
    $scope.listeDocumentcaisse = [];
    // **Variable Grid
    $scope.myDatacaisse = [];
    // bouton pdf hide
    $scope.modePdf = 'notActive';

    $scope.displayMode = 'list';

    /***************************************************
     *
     **************************************************/

    // REST SERVICE MAGAZINS
    $scope.listeMagazinCache = function () {
      $http.get(UrlAtelier + '/magasin/depots').success(function (dataMagazin) {
        $scope.listeMagazinCache = dataMagazin;
      });
    };

    $scope.listeMagazinCache();
    /** ********************************************* */
    $scope.setReference = function (id) {
      var myArray = [];
      myArray = $scope.listeMagazinCache;
      var magazin = _.find(myArray, function (obj) {
        return obj.id == id;
      });
      if ($scope.caisseCourant.date != null) {
        $scope.caisseCourant.reference =
          'C-' +
          magazin.designation +
          '-' +
          formattedDate($scope.caisseCourant.date);
      }
    };

    /** ********************************************* */
    $scope.pagingOptions = {
      pageSizes: [5, 10, 13],
      pageSize: 13,
      currentPage: 1,
    };

    // Annulation de l'ajout
    $scope.annulerAjout = function () {
      $scope.cnt = 0;
      // bouton pdf hide
      $scope.modePdf = 'notActive';
      // vider le tab
      $scope.varPrix = [
        {
          index: '',
          prix: '',
        },
      ];
      // init objetCourant
      $scope.caisseCourant = {
        dateClotureDe: '',
        dateClotureAu: '',
        reference: '',
        montantEspecesDe: '',
        MontantChequeDe: '',
        MontantChequeAu: '',
        cloture: '',
      };
      $scope.recherchercaisse({});
      $scope.listProduitReceptions = [];
      $scope.creationSSForm.$setPristine();
      $scope.rechercheSSForm.$setPristine();
      $scope.displayMode = 'list';
      $scope.closeNotif();
    };
    $scope.selectMagasin = function (valeur) {
      console.log('-- id' + valeur);
    };
    $scope.recherchercaisse = function (caisseCourante) {
      $http
        .post(UrlAtelier + '/caisse/rechercheMulticritere', caisseCourante)
        .success(function (data) {
          $scope.myDatacaisse = data.liste;
        });
    };

    /** * PDF ** */
    // conversion date en String
    function formattedDate(date) {
      var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;
      return [day, month, year].join('/');
    }

    // generer rapport apres creation d'un bon de
    // sortie. mode : Modification/Consultation
    $scope.download = function (id, pRapportPrix) {
      // init checkbox : 'non' :rapport sans Prix /
      // 'oui'
      // rapport avec prix
      $scope.checkboxModel = {
        rapportPrix: 'oui',
      };

      console.log('-- id' + id);
      var url =
        UrlAtelier +
        '/reportgc/bonReception?id=' +
        id +
        '&avecPrix=' +
        pRapportPrix +
        '&type=pdf';


      var a = document.createElement('a');
      document.body.appendChild(a);
      downloadService.download(url).then(function (result) {
        var heasersFileName = result.headers(['content-disposition']).substring(17);
        var fileName = heasersFileName.split('.');
        var typeFile = result.headers(['content-type']);
        var file = new Blob([result.data], { type: typeFile });
        var fileURL = window.URL.createObjectURL(file);
        a.href = fileURL;
        a.download = fileName[0];
        $window.open(fileURL)
        a.click();
      });


      // downloadService.download(url).then(
      //   function (success) {
      //     console.log('success : ' + success);
      //     // $scope.annulerAjout();
      //   },
      //   function (error) {
      //     console.log('error : ' + error);
      //   }
      // );
    };

    // generer rapport de tous les bons de livraison.
    // mode : List

    /***************************************************
     * Notifications
     **************************************************/

    $scope.hiddenNotif = 'true';

    $scope.showNotif = function () {
      $scope.hiddenNotif = 'false';
    };

    $scope.closeNotif = function () {
      $scope.hiddenNotif = 'true';
    };

    /***************************************************
     * Gestion des Bon de reception
     **************************************************/

    $scope.isLoading = false;

    // Ajout et Modification caisse
    $scope.modifierOuCreercaisse = function () {
      $scope.isLoading = true;

      var index = this.row.rowIndex;
      // getcaisse
      $http
        .get(UrlAtelier + '/caisse/getById:' + $scope.myDatacaisse[index].id)
        .success(function (datagetcaisse) {
          $scope.caisseCourant = datagetcaisse;

          $scope.mode = 'actif';
          $scope.isLoading = false;

          $scope.myDatacaisse[index].listProduitReceptions =
            $scope.listProduitReceptions;
        });


      var dateDebut = null;
      var dateCloture = null;
      if ($scope.myDatacaisse[index].date !== null) {
        dateDebut = $scope.modifierFormatDate($scope.myDatacaisse[index].date);
      } else {
        dateDebut = null;
      }


      if ($scope.myDatacaisse[index].dateCloture !== null) {
        dateCloture = $scope.modifierFormatDate($scope.myDatacaisse[index].dateCloture);
      } else {
        dateCloture = null;
      }


      $scope.caisseCourant = Object.assign($scope.myDatacaisse[index], { date: dateDebut }, { dateCloture: dateCloture })
        //  $scope.partieInteresseeCourante = $scope.myData[index]
        ? angular.copy($scope.myDatacaisse[index])
        : {};




      // $scope.caisseCourant = $scope.myDatacaisse[index]
      //   ? angular.copy($scope.myDatacaisse[index])
      //   : {};
      $scope.displayMode = 'edit';
    };

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

    // Sauvegarder caisse
    $scope.sauvegarderAjout = function (reception) {
      if (angular.isDefined(reception.id)) {
        $log.debug('Sauvegarder Modification' + reception.id);
        $scope.updatecaisse(reception);
      } else {
        $log.debug('Sauvegarder Ajout' + reception.reference);
        $scope.creercaisse(reception);
      }
    };

    // Création d'un Bon de reception de vente
    $scope.creercaisse = function (reception) {
      $http
        .post(UrlAtelier + '/caisse/create', reception)
        .success(function (newReceptionId) {
          $scope.annulerAjout();
        });
    };

    $scope.updatecaisse = function (reception) {
      $log.debug('Update ' + JSON.stringify(reception, null, '  '));

      $http
        .post(UrlAtelier + '/caisse/update', reception)
        .success(function (receptionModifiee) {
          //
          $scope.annulerAjout();
          $log.debug('success Modification ' + receptionModifiee);

          // getcaisse
          $http
            .get(UrlAtelier + '/caisse/getById:' + receptionModifiee)
            .success(function (datagetcaisse) {
              // bouton
              // PDF
              // activé
              $scope.modePdf = 'actif';

              // produitReception
              $scope.listProduitReceptions =
                datagetcaisse.listProduitReceptions;

              // disable
              // update
              // de
              // la
              // dropList
              // 'Produit'
              angular.forEach($scope.listProduitReceptions, function (
                produitReception,
                key
              ) {
                produitReception.checked = true;

                // $scope.updateProduitCommandDetails(produitReception);
                $scope.productFilter = [];
                $scope.sousFamilleFilter = [];

                $scope.getProductFilter(produitReception.produitId);

                if ($scope.productFilter.length > 0) {
                  produitReception.designation =
                    $scope.productFilter[0].designation;
                  produitReception.prixUnitaire =
                    $scope.productFilter[0].prixUnitaire;

                  $scope.getSousFamilleFilter(
                    $scope.productFilter[0].sousFamilleId
                  );

                  if ($scope.sousFamilleFilter.length > 0) {
                    produitReception.sousFamille =
                      $scope.sousFamilleFilter[0].designation;
                  }
                }
              });

              // document
              // $scope.listeDocumentcaisse
              // =
              // datagetcaisse.documentcaisses;

              $scope.caisseCourant = datagetcaisse
                ? angular.copy(datagetcaisse)
                : {};

              $scope.showNotif();
            });
        });
    };

    // suppression BonReception
    $scope.supprimercaisse = function () {
      // var index = this.row.rowIndex;
      $http({
        method: 'DELETE',
        url:
          UrlAtelier + '/caisse/delete:' + $scope.myDatacaisse[$scope.index].id,
      })
        .success(function () {
          $log.debug('Success Delete');
          $scope.myDatacaisse.splice($scope.index, 1);
        })
        .error(function () {
          $log.debug('Erreur');
          $scope.myDatacaisse.splice($scope.index, 1);
        });
      $scope.closePopupDelete();
      $scope.recherchercaisse({});
    };

    /***************************************************
     * Gestion de la Grid Caisse
     **************************************************/
    $scope.typeAlert = 3;

    $scope.colDefs = [];
    $scope.$watch('myDatacaisse', function () {
      $scope.colDefs = [
        {
          field: 'reference',
          displayName: 'Reference',
          width: '15%',
        },
        {
          field: 'magasinReference',
          displayName: 'magasin',
          width: '15%',
        },
        {
          field: 'montantEspeces',
          displayName: 'Montant Especes',
          width: '15%',
        },
        {
          field: 'montantCheque',
          displayName: 'Montant Cheque',
          width: '15%',
        },
        {
          field: 'dateCloture',
          displayName: 'Date Cloture',
          cellFilter: "date:'dd-MM-yyyy'",
          width: '15%',
        },
        {
          field: 'date',
          displayName: 'Date',
          cellFilter: "date:'dd-MM-yyyy'",
          width: '15%',
        },

        {
          field: '',
          width: '5%',
          cellTemplate:
            `<div class="ms-CommandButton float-right"   >
            <button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreercaisse()">
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
            </button>
           <button class="ms-CommandButton-button"  ng-click="showPopupDelete(16)" permission="['Finance_Client_Delete']">
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
            </button>
            	</div> `,

          // '<div class="buttons">'
          // 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreercaisse()"><i class="fa fa-fw fa-pencil"></i></button>'
          // 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(16)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
        },
      ];
    });

    $scope.filterOptions = {
      filterText: '',
      useExternalFilter: true,
    };

    $scope.totalServerItems = 0;

    $scope.setPagingData = function (data, page, pageSize) {
      var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
      $scope.myDatacaisse = pagedData;
      $scope.totalServerItems = data.length;
      if (!$scope.$$phase) {
        $scope.$apply();
      }
      $scope.isLoading = false;
    };

    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
      setTimeout(function () {
        var data;
        var caisseCourante = $scope.caisseCourant;
        if (searchText) {
          var ft = searchText.toLowerCase();
          $scope.isLoading = true;
          $http
            .post(UrlAtelier + '/caisse/rechercheMulticritere', caisseCourante)
            .success(function (largeLoad) {
              $scope.myDatacaisse = largeLoad.liste.filter(function (item) {
                return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
              });
              $scope.setPagingData(data, page, pageSize);
            });
        } else {
          $scope.isLoading = true;

          $http
            .post(UrlAtelier + '/caisse/rechercheMulticritere', caisseCourante)
            .success(function (largeLoad) {
              console.log('largeLoad' + JSON.stringify(largeLoad, null, ' '));
              $scope.setPagingData(largeLoad.liste, page, pageSize);
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

    $scope.gridOptions = {
      data: 'myDatacaisse',
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

    /** Fin de gestion des Grids Vente BC */
  },
]);
