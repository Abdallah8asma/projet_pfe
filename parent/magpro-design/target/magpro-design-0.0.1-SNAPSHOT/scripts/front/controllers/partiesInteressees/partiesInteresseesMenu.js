'use strict';
/**
 * Menu Element de Base
 */
angular
  .module('gpro.menuPartiesInteressees', [])
  .controller('MenuPartiesInteresseesController', function (
    $scope,
    initReferentielService,
    $log,
    $http,
    UrlCommun,
    $route
  ) {
    $log.info('***PI Menu***');

    // $scope.ITEM = 'emtpyPI';
    var current = $route.current;
    switch (current.params['pi']) {
      case 'client':
        $scope.ITEM = 'client';
        break;
      case 'fournisseur':
        $scope.ITEM = 'fournisseur';
        break;
    }
 
    $log.info($scope.ITEM);
    $scope.clientActif = {};
    $scope.getClientActif = function () {
      //TODO cache
      $http
        .get(UrlCommun + '/baseInfo/getClientActif')
        .success(function (baseInfo) {
          //$log.debug("baseInfo : ",baseInfo);
          $scope.clientActif = baseInfo;
        });
    };

    $scope.getClientActif();
    initReferentielService.getDeviseList().then(
      function (success) {
        $scope.ListTypeArticleCache = success;
        // $log.debug('*** Device *** : ' + JSON.stringify(success, null, '  '));
      },
      function (error) {
        //$log.error('****error : ' + error);
      }
    );
    initReferentielService.getTypeArticleList().then(
      function (success) {
        $scope.ListDeviseCache = success;
        /* $log.debug(
          '*** TypeArticle *** : ' + JSON.stringify(success, null, '  ')
        );*/
      },
      function (error) {
        // $log.error('****error : ' + error);
      }
    );
  });
