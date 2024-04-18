'use strict'
/**
 * Menu Magasin
 */
angular
		.module('atelier.menuMagasin', [])
		.controller(
				'MenuMagasinController',function($scope,$route){
					$scope.ITEM = 'emtpyBase';
					var current = $route.current;
					switch (current.params['type']) {
						case 'production':
							$scope.ITEM = 'production';
							break;
						case 'expedition':
							$scope.ITEM = 'expedition';
							break;
						case 'of':
							$scope.ITEM = 'of';
							break;
		
						case 'reporting':
							$scope.ITEM = 'reporting';
							break;
						case 'stockage':
							$scope.ITEM = 'stockage';
							break;
							
						case 'suivieof':
							$scope.ITEM = 'suivieof';
							break;
						case 'livraison':
							$scope.ITEM = 'livraison';
							break;
						
						}	});

      /* 
					console.log("****** logistique controller *****");
					
					initLogistiqueService.getDeviseList()
						                  .then(
						                      function(success) {
						                      	$scope.ListTypeArticleCache = success;
						                        $log.debug('*** Device *** : '+ JSON.stringify(success,null, "  "));
						                      },
						                      function(error) {
						                        $log.error('****error : '+ error);
						                      });

					initLogistiqueService.getTypeArticleList()
						                  .then(
						                      function(success) {
						                      	$scope.ListDeviseCache = success;
						                        $log.debug('*** TypeArticle *** : '+ JSON.stringify(success,null, "  "));
						                      },
						                      function(error) {
						                        $log.error('****error : '+ error);
						                      });
						                  
					var reloadListMarche = function(){
						$http
						 .get(UrlAtelier + "/marche/getAll")
						 .success(
								 function(dataMarche) {

									 $rootScope.bllisteMarche = dataMarche;
								 });
						
					}					
					
					reloadListMarche();
					$rootScope.bllisteMarche = [];
					
					$scope.ITEM = 'emtpyMagasin';
					$scope.gosuivieof = function(){$scope.ITEM = 'suivieof';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goStockage = function(){$scope.ITEM = 'stockage';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goBonSortie = function(){$scope.ITEM = 'bonSortie';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goProduction = function(){$scope.ITEM = 'production';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goreporting = function(){$scope.ITEM = 'reporting';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goof = function(){$scope.ITEM = 'of';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.bonLivraison = function(){$scope.ITEM = 'bonLivraison';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goVente = function(){
						reloadListMarche();
						$scope.ITEM = 'vente';
						$scope.navMode = "normal";
					}
					
					/*****gestion de stock*****/

					/*$scope.goInventaire = function(){$scope.ITEM = 'inventaire';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					
					$scope.goFacon = function(){$scope.ITEM = 'facon';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					$scope.goReporting = function(){$scope.ITEM = 'reporting';$log.debug("===$scope.ITEM: "+$scope.ITEM);}
					
					/** redirection from Bs to Bl **/
				/*	$scope.redirectToVente = function(referenceBs, dateSortieBs){

						reloadListMarche();
						$scope.ITEM = 'vente';
						$scope.navMode = "redirection";
							$scope.referenceBs = referenceBs;
						$scope.dateSortieBs = dateSortieBs;
					}
					
					$scope.origine = false;
					$scope.redirectToVenteBL = function(referenceBs, dateSortieBs,origine){

						reloadListMarche();
						$scope.ITEM = 'vente';
						$scope.navMode = "redirection";
						$scope.referenceBs = referenceBs;
						$scope.dateSortieBs = dateSortieBs;
						
						if (origine == 1) {
						$scope.origine = true;
						console.log("--------logistique : $scope.origine"+$scope.origine);
						}
						
					} */
					
			