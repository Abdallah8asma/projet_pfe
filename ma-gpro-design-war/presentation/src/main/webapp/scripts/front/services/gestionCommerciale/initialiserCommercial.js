(function() {
    'use strict';

    var initModule = angular.module('components.initCommercial', []);

    initModule.factory('initCommercialService', ['$q', '$http', '$log', '$timeout', '$window','UrlCommun','UrlAtelier',
        function($q, $http, $log, $timeout, $window,UrlCommun,UrlAtelier) {
            
            	
				return {
					
					
						getRefFactureAchatList: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglementAchat/listeRefFactureNonRegleByFournisseurId:"+idClient)
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
				getRefBRList: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglementAchat/listRefBRNonRegleByFournisseurId:"+idClient)
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
				getRefFactureAvoirList: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglement/listeRefFactureAvoirNonRegleByClientId:"+idClient)
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
				getRefFactureAvoirListByGroupe: function(idClient){
					/** Devise **/
					var defer = $q.defer();
				    $http
				    	.get(UrlAtelier + "/reglement/listeRefFactureAvoirNonRegleByGroupeId:"+idClient)
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