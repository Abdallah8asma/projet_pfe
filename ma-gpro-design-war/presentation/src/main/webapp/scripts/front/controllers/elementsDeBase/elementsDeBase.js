'use strict'
/**
 * Menu Element de Base
 */
angular
	.module('gpro.menuElementBase', [])
	.controller(
		'MenuElementBaseController', function ($scope, initReferentielService, $log, $route) {
			$scope.ITEM = 'emtpyBase';
			var current = $route.current;
			switch (current.params['type']) {
				case 'produit':
					$scope.ITEM = 'produit';
					break;
				case 'produitSerialisable':
					$scope.ITEM = 'produitSerialisable';
					break;
				case 'elementDeBaseReporting':
					$scope.ITEM = 'elementDeBaseReporting';
					break;

				case 'article':
					$scope.ITEM = 'article';
					break;

					case 'produitFinancier':
						$scope.ITEM = 'produitFinancier';
						break;
						case 'produitComposition':
							$scope.ITEM = 'produitComposition';
							break;
				
						case 'film':
							$scope.ITEM = 'film';
							break;		
							case 'forme':
								$scope.ITEM = 'forme';
								break;	
								case 'service':
									$scope.ITEM = 'service';
									break;	
			}


			$scope.goArticle = function () { $scope.ITEM = 'article'; }
			$scope.goProduit = function () { $scope.ITEM = 'produit'; }
			$scope.goProduitSerialisable = function () { $scope.ITEM = 'produitSerialisable'; }
			$scope.goElementDeBaseReporting = function () { $scope.ITEM = 'elementDeBaseReporting'; }
			$scope.goEngin = function () { $scope.ITEM = 'engin'; }
			$scope.goChauffeur = function () { $scope.ITEM = 'chauffeur'; }
			$scope.goRemorque = function () { $scope.ITEM = 'remorque'; }


			$log.info('***Element de Base***');

			initReferentielService.getDeviseList()
				.then(
					function (success) {
						$scope.ListTypeArticleCache = success;
						$log.debug('*** Device *** : ' + JSON.stringify(success, null, "  "));
					},
					function (error) {
						$log.error('****error : ' + error);
					});

			initReferentielService.getTypeArticleList()
				.then(
					function (success) {
						$scope.ListDeviseCache = success;
						$log.debug('*** TypeArticle *** : ' + JSON.stringify(success, null, "  "));
					},
					function (error) {
						$log.error('****error : ' + error);
					});
		});
