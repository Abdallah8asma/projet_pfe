(function() {
    'use strict';

    var initModule = angular.module('components.initCommercial', []);

    initModule.factory('initCommercialService', ['$q', '$http', '$log', '$timeout', '$window','UrlCommun','UrlAtelier',
        function($q, $http, $log, $timeout, $window,UrlCommun,UrlAtelier) {
            
            	
				return {

				getRefFactureList: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglement/listeRefFactureNonRegleByClientId:"+idClient)
					    .success(function(data){
					    	$log.debug("=========**listeRefFactureList : "+data.length+" idClient : "+idClient);
					    	
				            defer.resolve(data);
					    })
					    .error(function(msg, code) {
				          	defer.reject(msg);
				          	$log.error(msg, code);
				       	});
					return defer.promise;
				},
				getRefBLList: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglement/listRefBLNonRegleByClientId:"+idClient)
					    .success(function(data){
					    	$log.debug("=========**listeRefBLList : "+data.length+" idClient : "+idClient);
					    	
				            defer.resolve(data);
					    })
					    .error(function(msg, code) {
				          	defer.reject(msg);
				          	$log.error(msg, code);
				       	});
					return defer.promise;
				},
				
				getRefFactureListByGroupe: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglement/listeRefFactureNonRegleByGroupeId:"+idClient)
					    .success(function(data){
					    	$log.debug("=========**listeRefFactureList : "+data.length+" groupe : "+idClient);
					    	
				            defer.resolve(data);
					    })
					    .error(function(msg, code) {
				          	defer.reject(msg);
				          	$log.error(msg, code);
				       	});
					return defer.promise;
				},
				getRefBLListByGroupe: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglement/listRefBLNonRegleByGroupeId:"+idClient)
					    .success(function(data){
					    	$log.debug("=========**listeRefBLList : "+data.length+" groupe : "+idClient);
					    	
				            defer.resolve(data);
					    })
					    .error(function(msg, code) {
				          	defer.reject(msg);
				          	$log.error(msg, code);
				       	});
					return defer.promise;
				},
				getDeviseList: function(){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlCommun+ "/gestionnaireCache/listeDeviseCache")
					    .success(function(data){
					    	$log.debug("=========**listeDeviseCache : "+data.length);
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
					    	$log.debug("=========listeTypeArticleCache :"+data.length);
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