'use strict';
angular.module('gpro.mvtCaisse', []).controller('MvtCaissseController', [
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
    $scope.mvtCaisseCourant = {
      dateClotureDe: '',
      dateClotureAu: '',
      reference: '',
      montantEspecesDe: '',
      MontantChequeDe: '',
      MontantChequeAu: '',
    };
    $scope.listeProduitCache = [];
    $scope.listeDocumentcaisse = [];
    // **Variable Grid
    $scope.myDatacaisse = [];
    // bouton pdf hide
    $scope.modePdf = 'notActive';

    $scope.displayMode = 'list';

    /***************************************************
     * Gestion de Cache des Referentiels Gestion
     * Commerciale *
     **************************************************/
    $scope.listeCaisse = [];
    $scope.listeCaisseNonCloture = [];
    $scope.ListTypecaisseCache = [];
    $scope.listeSitePartieInteresseeCache = [];
    $scope.ListTypeDocumentCache = [];
    $scope.ListSousFamilleProduitCache = [];

    // Liste des PartieInteressee (avec FamilleId=1)

    // REST SERVICE MAGAZINS
    $scope.listeMagazinCache = function () {
      $http.get(UrlAtelier + '/magasin/depots').success(function (dataMagazin) {
        $scope.listeMagazinCache = dataMagazin;
      });
    };
    $scope.getAllCaisse = function () {
      $http.get(UrlAtelier + '/caisse/getAll').success(function (data) {
        $scope.listeCaisse = data;
      });
    };
    $scope.getAllCaisse();
    $scope.getAllCaisseNonCloture = function () {
      $http.get(UrlAtelier + '/caisse/getCaisses').success(function (data) {
        $scope.listeCaisseNonCloture = data;
      });
    };
    $scope.getAllCaisseNonCloture();

    /** ********************************************* */

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
      $scope.getAllCaisseNonCloture();
      $scope.mvtCaisseCourant = {};
      $scope.recherchercaisse({});
      $scope.creationSSForm.$setPristine();
      $scope.rechercheSSForm.$setPristine();
      $scope.displayMode = 'list';
      $scope.closeNotif();
    };

    $scope.recherchercaisse = function (mvtCaisseCourant) {
      $http
        .post(UrlAtelier + '/mvtCaisse/rechercheMulticritere', mvtCaisseCourant)
        .success(function (data) {
          $scope.myDatacaisse = data.liste;
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
      return [year, month, day].join('-');
    }

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
        .get(UrlAtelier + '/mvtCaisse/getById:' + $scope.myDatacaisse[index].id)
        .success(function (data) {
          $scope.mvtCaisseCourant = data; 

          $scope.modePdf = 'actif';
          $scope.isLoading = false;
        });


        
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
      // refresh de la liste
      // $scope.recherchercaisse({});
    };

    // Création d'un Bon de reception de vente
    $scope.creercaisse = function (reception) {
      // produitReception
      reception.listProduitReceptions = $scope.listProduitReceptions;

      // DocumentProduitReception
      // reception.documentcaisses =
      // $scope.listeDocumentcaisse ;

      $log.debug('-- Create ' + JSON.stringify(reception, null, '  '));

      $http
        .post(UrlAtelier + '/mvtCaisse/create', reception)
        .success(function (newReceptionId) {
          $scope.annulerAjout();
        });
    };

    $scope.updatecaisse = function (reception) {
      $log.debug('Update ' + JSON.stringify(reception, null, '  '));

      $http
        .post(UrlAtelier + '/mvtCaisse/update', reception)
        .success(function (receptionModifiee) {
          $scope.annulerAjout();
          //

          $log.debug('success Modification ' + receptionModifiee);

          // getcaisse
          $http
            .get(UrlAtelier + '/mvtCaisse/getById:' + receptionModifiee)
            .success(function (data) {
              // bouton
              // PDF
              // activé
              $scope.modePdf = 'actif';

              // produitReception
              $scope.mvtCaisseCourant = datagetcaisse;
            });
        });
    };

    // suppression BonReception
    $scope.supprimercaisse = function () {
      var index = this.row.rowIndex;
      $http({
        method: 'DELETE',
        url: UrlAtelier + '/mvtCaisse/delete:' + $scope.myDatacaisse[index].id,
      })
        .success(function () {
          $log.debug('Success Delete');
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
          field: 'caisseReference',
          displayName: 'Reference Caisse',
          //	width: '25%'
        },
        {
          field: 'destinataire',
          displayName: 'destinataire',
          //		width: '35%'
        },
        {
          field: 'typeMouvement',
          displayName: 'type',
          //	width: '10%'
        },

        {
          field: 'montantMouvement',
          displayName: 'Montant',
          //	width: '10%'
        },
        {
          field: 'date',
          displayName: 'Date',
          cellFilter: "date:'dd-MM-yyyy'",
          //		width: '10%'
        },
        {
          field: '',
          //	width: '5%',
          cellTemplate:
          `<div class="ms-CommandButton float-right"   >
            <button class="ms-CommandButton-button ms-CommandButton-Gpro"  ng-click="modifierOuCreercaisse()">
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
            </button>
            <button class="ms-CommandButton-button"   ng-click="supprimercaisse()" permission="['Finance_Client_Delete']">
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
            </button>
          	</div> `,

          // '<div class="buttons">'
          // 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreercaisse()"><i class="fa fa-fw fa-pencil"></i></button>'
          // 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="supprimercaisse()">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
        var mvtCaisseCourant = $scope.mvtCaisseCourant;
        if (searchText) {
          var ft = searchText.toLowerCase();
          $scope.isLoading = true;
          $http
            .post(
              UrlAtelier + '/mvtCaisse/rechercheMulticritere',
              mvtCaisseCourant
            )
            .success(function (data) {
              $scope.myDatacaisse = data.liste.filter(function (item) {
                return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
              });
              $scope.setPagingData(data, page, pageSize);
            });
        } else {
          $scope.isLoading = true;

          $http
            .post(
              UrlAtelier + '/mvtCaisse/rechercheMulticritere',
              mvtCaisseCourant
            )
            .success(function (data) {
              $scope.setPagingData(data.liste, page, pageSize);
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
