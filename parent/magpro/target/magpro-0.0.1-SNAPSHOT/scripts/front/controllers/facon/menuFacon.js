'use strict'
/**
 * Menu Magasin
 */
angular
		.module('gpro.menuFacon', [])
		.controller(
				'MenuFaconController',function($rootScope,$scope, $http, $log, initLogistiqueService, UrlAtelier){
      
					console.log("****** Menu Facon controller *****");
					
//					initLogistiqueService.getDeviseList()
//						                  .then(
//						                      function(success) {
//						                      	$scope.ListTypeArticleCache = success;
//						                        $log.debug('*** Device *** : '+ JSON.stringify(success,null, "  "));
//						                      },
//						                      function(error) {
//						                        $log.error('****error : '+ error);
//						                      });
//
//					initLogistiqueService.getTypeArticleList()
//						                  .then(
//						                      function(success) {
//						                      	$scope.ListDeviseCache = success;
//						                        $log.debug('*** TypeArticle *** : '+ JSON.stringify(success,null, "  "));
//						                      },
//						                      function(error) {
//						                        $log.error('****error : '+ error);
//						                      });
						                  
//					var reloadListMarche = function(){
//						$http
//						 .get(UrlAtelier + "/marche/getAll")
//						 .success(
//								 function(dataMarche) {
//
//									 $rootScope.bllisteMarche = dataMarche;
//								 });
//						
//					}					
					
//					reloadListMarche();
//					$rootScope.bllisteMarche = [];
					
					$scope.ITEM = 'emtpyMagasin';
					$scope.goLancement = function(){$scope.ITEM = 'lancement';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goExpedition = function(){$scope.ITEM = 'expedition';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goLivraison = function(){$scope.ITEM = 'livraison';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goFacturation = function(){$scope.ITEM = 'facturation';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					
				});
