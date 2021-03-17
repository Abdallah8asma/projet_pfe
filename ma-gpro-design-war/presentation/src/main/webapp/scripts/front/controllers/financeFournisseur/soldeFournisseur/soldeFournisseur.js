'use strict';

angular.module('gpro.soldeFournisseur', []).controller('soldeFournisseurController', [
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
    $window
  ) {
    $log.info('=========soldeClient========');

    // Déclaration des variables globales utilisées
    /** ***Liste des Variables **** */
    var data;

    $scope.displayMode = 'list';

    // Rechercher soldeCls
    $scope.recherchesoldeCl = function (soldeClCourante) {
	soldeClCourante.partieIntFamilleId = 2;
	
	
      $http
        .post(
          UrlAtelier + '/soldeClient/rechercheMulticritere',
          soldeClCourante
        )

        .success(function (resultat) {
          $scope.myData = resultat.list;

          $scope.setPagingData(
            $scope.myData,
            $scope.pagingOptions.currentPage,
            $scope.pagingOptions.pageSize
          );

          $scope.recherchesoldeClForm.$setPristine();
          $scope.displayMode = 'rechercher';
          $scope.displayAlert = 'sleep';
        });
    };

    // Annulation de l'ajout
    $scope.annulerAjout = function () {
      $scope.soldeClCourante = { partieIntId: null, bloque: null };

      $scope.creationsoldeClForm.$setPristine();
      $scope.recherchesoldeClForm.$setPristine();
      $scope.recherchesoldeCl({});
      $scope.displayMode = 'list';
    };

    // declaration variable
    $scope.displayAlert = 'sleep';

    // Liste des Clients
    $scope.listClients = function () {
      $http
        .get(UrlCommun + '/gestionnaireCache/listePartieInteresseeCache')
        .success(function (dataProduit) {
          $scope.listClients = dataProduit;
        });
    };
    $scope.listClients();

    $scope.recherchesoldeCl({});

    // Modification soldeCl
    $scope.modifierOuCreersoldeCl = function () {
      var index = this.row.rowIndex;
      // get soldeCl
      $http
        .get(UrlAtelier + '/soldeClient/getById:' + $scope.myData[index].id)
        .success(function (datagetsoldeCl) {});

      $scope.soldeClCourante = $scope.myData[index]
        ? angular.copy($scope.myData[index])
        : {};

      $scope.displayMode = 'edit';
    };

    // Sauvegarder soldeCl
    $scope.sauvegarderAjout = function (soldeCl) {
      if (angular.isDefined(soldeCl.id)) {
        $scope.updatesoldeCl(soldeCl);
      } else {
        $scope.creersoldeCl(soldeCl);
      }
    };

    // Mise à jour des soldeCls
    $scope.updatesoldeCl = function (soldeCl) {
      $http
        .put(UrlAtelier + '/soldeClient/update', soldeCl)
        .success(function (soldeClModifiee) {
          $scope.annulerAjout();
        });
    };

    $scope.pagingOptions = {
      pageSizes: [5, 10, 13],
      pageSize: 13,
      currentPage: 1,
    };
    $scope.colDefs = [];
    $scope.$watch('myData', function () {
      $scope.colDefs = [
        {
          field: 'partieIntReference',
          displayName: 'Reference',
          width: '15%',
        },
        {
          field: 'partieIntAbreviation',
          displayName: 'Client',
          width: '15%',
        },
        {
          field: 'soldeInitial',
          displayName: 'SOLDE Initial',
          width: '15%',
        },
        {
          field: 'dateIntroduction',
          displayName: 'Date',
          cellFilter: "date:'dd-MM-yyyy'",
          width: '15%',
        },
        {
          field: 'bloque',
          displayName: 'Bloquée',
          cellTemplate:
            '<input type="checkbox" ng-model="row.entity.bloque" ng-change="ffff" style="margin: 8px;" ng-disabled="true"/>',
          width: '15%',
        },
        {
          field: 'seuil',
          displayName: 'Seuil',
          width: '5%',
        },
        {
          field: 'observation',
          displayName: 'Observations',
          width: '15%',
        },
       {
                                field: '',
                               
                                cellTemplate:`<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
                                    <button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreersoldeCl()">
                                    <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
                                    </button></div>`

                                     
              }
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
        var soldeClCourante = $scope.soldeClCourante;
            soldeClCourante.partieIntFamilleId = 2;
        if (searchText) {
          var ft = searchText.toLowerCase();
          $http
            .post(
              UrlAtelier + '/soldeClient/rechercheMulticritere',
              soldeClCourante
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
              UrlAtelier + '/soldeClient/rechercheMulticritere',
              soldeClCourante
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

    /*** PDF ***/
    //generer rapport de tous les soldeClient. mode : List
    $scope.downloadAll = function (soldeClCourante) {
      console.log(
        '------- soldeClCourante ' + JSON.stringify(soldeClCourante, null, '  ')
      );
      var url =
        UrlAtelier +
        '/reportgc/soldeClient?clientId=' +
        soldeClCourante.partieIntId +
        '&bloque=' +
        soldeClCourante.bloque +
        '&type=pdf';

      console.log('-- URL--- :' + url);


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
        
       
      });


      // downloadService.download(url).then(
      //   function (success) {
      //     //$log.debug('success : ' + success);
      //   },
      //   function (error) {
      //     //$log.debug('error : ' + error);
      //   }
      // );
    };

    //boutton valider les soldeCls
    $scope.Valider = function (clientID) {
      $http
        .get(UrlAtelier + '/reglement/validateByClientId:' + clientID)
        .success(function (soldeCl) {
          $scope.finalBLList = soldeCl.listLivraisonVente;
          $scope.finalFacturesList = soldeCl.listFactureVentre;

          $scope.DiableMontantRegCol();

          if ($scope.finalBLList.length == 0) {
            $scope.BLError = true;
          }

          if ($scope.finalFacturesList.length == 0) {
            $scope.FacturesError = true;
          }
        });
    };
  },
]);
