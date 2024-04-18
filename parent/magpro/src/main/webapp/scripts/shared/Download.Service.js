(function () {
  'use strict';

  var downloadModule = angular.module('components.donwload', []);

  downloadModule.factory('downloadService', function ($http) {
    return {
      download: function (url) {
        return $http
          .get(url, { responseType: 'arraybuffer' })
          .then(function (response) {
            return response;
          });
      },
    };
  });

  //   downloadModule.factory('downloadService', [
  //     '$q',
  //     '$timeout',
  //     '$window',
  //     function ($q, $timeout, $window) {
  //       return {
  //         download: function (url) {
  //           var defer = $q.defer();

  //           $timeout(function () {
  //             // $window.location = UrlAtelier+"/report/inventaire?critereRechercheRouleauStandard="+inventaireCourant+"&type=pdf";
  //             // $window.location = url;
  //             $window.open(url);
  //           }, 1000).then(
  //             function () {
  //               defer.resolve('success');
  //             },
  //             function () {
  //               defer.reject('error');
  //             }
  //           );
  //           return defer.promise;
  //         },
  //       };
  //     },
  //   ]);
})();
