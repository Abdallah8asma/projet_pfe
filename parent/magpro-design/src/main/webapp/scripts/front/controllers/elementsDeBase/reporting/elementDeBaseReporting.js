'use strict';
var app = angular.module('gpro.elementDeBaseReporting', []);

app.controller('elementDeBaseReportingCtrl', [
  '$scope',
  '$http',
  '$filter',
  '$log',
  'downloadService',
  'UrlCommun',
  'UrlAtelier',
  '$window',
  function (
    $scope,
    $http,
    $filter,
    $log,
    downloadService,
    UrlCommun,
    UrlAtelier,
    $window
  ) {
    $scope.ListProduit = [];

    // Liste des Produit
    $scope.listeProduit = function () {
      $http.get(UrlCommun + '/produit/all').success(function (dataProduit) {
        $scope.ListProduit = dataProduit;
      });
    };
    $scope.listeProduit();

    // annuler Recherche
    $scope.annulerProduitAjout = function () {
      $scope.fnReportingCourant = {
        produitId: '',
        dateMin: '',
        dateMax: '',
      };

      $scope.rechercheProduitForm.$setPristine();
    };

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
      var newdateSaisieMinFormat = '';
      if (angular.isDefined(fnReportingCourant.dateMin)) {
        if (fnReportingCourant.dateMin != '') {
          newdateSaisieMinFormat = formattedDate(fnReportingCourant.dateMin);
        } else {
          newdateSaisieMinFormat = '';
        }
      }

      var newdateSaisieMaxFormat = '';
      if (angular.isDefined(fnReportingCourant.dateMax)) {
        if (fnReportingCourant.dateMin != '') {
          newdateSaisieMaxFormat = formattedDate(fnReportingCourant.dateMax);
        } else {
          newdateSaisieMaxFormat = '';
        }
      }

      $log.info(
        'fnReportingCourantChaine ' +
          JSON.stringify(fnReportingCourant, null, '  ')
      );

      var url;
      //Produit
      url =
        UrlAtelier +
        '/fiches/mouvementProduit?produitId=' +
        fnReportingCourant.produitId +
        '&dateMin=' +
        newdateSaisieMinFormat +
        '&dateMax=' +
        newdateSaisieMaxFormat;

      $log.info('URL ' + url);

      var fileName = 'Liste des Reporting';
      var a = document.createElement('a');
      document.body.appendChild(a);
      downloadService.download(url).then(function (result) {
        var heasersFileName = result.headers(['content-disposition']).substring(17);
        var fileName = heasersFileName.split('.');
        fileName[0] =  'Historique_Articles_' + formattedDate(new Date());
      var typeFile = result.headers(['content-type']);
      var file = new Blob([result.data], {type: typeFile});
      var fileURL = window.URL.createObjectURL(file);
      if(typeFile == 'application/vnd.ms-excel'){
       
        a.href = fileURL;
         a.download = fileName[0];
         // $window.open(fileURL)
         a.click();

      }else{
       
        a.href = fileURL;
        a.download = fileName[0];
       $window.open(fileURL)
        a.click();

      }
        
       
      });


      //Generate
      // downloadService.download(url).then(
      //     function(success) {
      //          $log.debug('success : ' + success);
      //     }, function(error) {
      //          $log.debug('error : ' + error);
      //     });
    };
  },
]);
