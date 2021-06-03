'use strict'
/**
 * Menu Finance
 */
angular.module('gpro.menuFinance', []).controller(
	'MenuFinanceController',
	function($rootScope, $scope, $http, $log, initCommercialService,
		UrlAtelier, $route, UrlCommun) {


		$scope.clientActif = {};
		$scope.getClientActif = function() {
			//TODO cache
			$http
				.get(
					UrlCommun
					+ "/baseInfo/getClientActif")
				.success(
					function(baseInfo) {
						// $log.debug("baseInfo : ",baseInfo);
						$scope.clientActif = baseInfo;
					});
		}
		$scope.getClientActif();

		$scope.listeTypeDocumentsCacheALL = [];

		$scope.getListeTypeDocumentsCache = function() {
			$http
				.get(
					UrlCommun
					+ "/gestionnaireCache/listeTypeDocumentCache")
				.success(
					function(dataTypeDocumentCache) {


						$scope.listeTypeDocumentsCacheALL = dataTypeDocumentCache;


					});
		}
		$scope.getListeTypeDocumentsCache();

		initCommercialService.getDeviseList().then(
			function(success) {
				$scope.ListTypeArticleCache = success;
				$log.debug('*** Device *** : '
					+ JSON.stringify(success, null, "  "));
			}, function(error) {
				$log.error('****error : ' + error);
			});

		initCommercialService.getTypeArticleList().then(
			function(success) {
				$scope.ListDeviseCache = success;
				$log.debug('*** TypeArticle *** : '
					+ JSON.stringify(success, null, "  "));
			}, function(error) {
				$log.error('****error : ' + error);
			});
		var reloadListMarche = function() {
			$http.get(UrlAtelier + "/marche/getAll").success(
				function(dataMarche) {

					$rootScope.bllisteMarche = dataMarche;
				});

		}

		reloadListMarche();






		$rootScope.bllisteMarche = [];
		$scope.ITEM = 'emtpyFinance';
		var current = $route.current;

		switch (current.params['type']) {
			case 'reglementVente':
				$scope.ITEM = 'reglementVente';
				break;
			case 'elementReglementVente':
				$scope.ITEM = 'elementReglementVente';
				break;
			case 'soldeClient':
				$scope.ITEM = 'soldeClient';
				break;
			case 'echeancier':
				$scope.ITEM = 'echeancier';
				break;
			case 'reporting':
				$scope.ITEM = 'reporting';
				break;
			case 'caisse':
				$scope.ITEM = 'caisse';
				break;
			case 'mvtCaisse':
				$scope.ITEM = 'mvtCaisse';
				break;
			case 'rapprochement':
				$scope.ITEM = 'rapprochement';
				break;


			case 'reglementInverseVente':
				$scope.ITEM = 'reglementInverseVente';
				break;
			case 'elementReglementInverseVente':
				$scope.ITEM = 'elementReglementInverseVente';
				break;

			case 'echeancierInverse':
				$scope.ITEM = 'echeancierInverse';
				break;


		}

		$scope.goCoutsVente = function() {
			$scope.ITEM = 'coutsVente';
		}

		$scope.goReglementVente = function() {
			$scope.ITEM = 'reglementVente';
		}

		$scope.goElementReglementVente = function() {
			$scope.ITEM = 'elementReglementVente';
		}

		$scope.goSoldeClient = function() {
			$scope.ITEM = 'soldeClient';
		}
		$scope.goEcheancier = function() {
			$scope.ITEM = 'echeancier';
		}
		$scope.goReporting = function() {
			$scope.ITEM = 'reporting';
		}
		$scope.goCaisse = function() {
			$scope.ITEM = 'caisse';
		}
		$scope.goMvtCaisse = function() {
			$scope.ITEM = 'mvtCaisse';
		}

	})
	// Filtre sur le champ prix du tableau ProduitBS: retourne les 3 chiffres apres
	// le point .
	.filter('prixFiltre', function() {
		return function(prix) {
			if (prix) {
				prix = prix.toString();
				// $log.debug("Prix "+prix);
				if (prix.indexOf(".") == -1) {
					return prix;
				} else {
					var index = prix.indexOf(".");
					// $log.debug("index . "+index);
					return prix.substring(0, index + 4);
				}
			}
		};
	});