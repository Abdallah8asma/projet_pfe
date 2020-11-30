'use strict'
/**
 * Menu stockmp
 */
angular
	.module('gpro.stockMPmenu', [])
	.controller('stockMPcontroller', function ($scope, initReferentielService, $log, $route) {
			$scope.ITEM = 'emtpyBase';
			var current = $route.current;
			switch (current.params['type']) {
				case 'entree':
					$scope.ITEM = 'entree';
					break;
				case 'sortie':
					$scope.ITEM = 'sortie';
					break;
				case 'historique':
					$scope.ITEM = 'historique';
					break;

				case 'etat':
					$scope.ITEM = 'etat';
                    break;
                    
				case 'tableaudebord':
					$scope.ITEM = 'tableaudebord';
					break;
			}});

/*
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
 */