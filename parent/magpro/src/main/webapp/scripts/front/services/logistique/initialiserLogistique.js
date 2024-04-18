(function() {
    'use strict';

    var initModule = angular.module('components.initLogistique', []);

    initModule.factory('initLogistiqueService', ['$q', '$http', '$log', '$timeout', '$window','UrlCommun','UrlAtelier',
        function($q, $http, $log, $timeout, $window,UrlCommun,UrlAtelier) {
            
            	
				return {
				
				getDeviseList: function(){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlCommun+ "/gestionnaireCache/listeDeviseCache")
					    .success(function(data){
					    	//$log.info("=========listeDeviseCache : "+data.length);
				            defer.resolve(data);
					    })
					    .error(function(msg, code) {
				          	defer.reject(msg);
				          	$log.error(msg, code);
				       	});
					return defer.promise;
				},
				getTypeArticleList: function(){
				    /** TypeArticle **/
				    var defer = $q.defer();
					$http
				    	.get(UrlCommun+ "/gestionnaireCache/listeTypeArticleCache")
					    .success(function(data){
					    	//$log.info("=========listeTypeArticleCache :"+data.length);
				            defer.resolve(data);
					    })
					    .error(function(msg, code) {
				          	defer.reject(msg);
				          	$log.error(msg, code);
				       	});
				    return defer.promise;
				}

               
            };
        }
    ]);
})();