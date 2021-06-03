'use strict'
/**
 * Menu Finance
 */
angular
	.module('gpro.menuFinanceFournisseur', [])
	.controller(
		'MenuFinanceFournisseurController', function ($rootScope, $scope, $http, $log, initCommercialService, UrlAtelier, $route,UrlCommun) {
		
			$scope.clientActif = {};
			$scope.getClientActif = function () {
				//TODO cache
				$http
					.get(
						UrlCommun
						+ "/baseInfo/getClientActif")
					.success(
						function (baseInfo) {
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

			initCommercialService.getDeviseList()
				.then(
					function (success) {
						$scope.ListTypeArticleCache = success;
						$log.debug('*** Device *** : ' + JSON.stringify(success, null, "  "));
					},
					function (error) {
						$log.error('****error : ' + error);
					});

			initCommercialService.getTypeArticleList() 
				.then(
					function (success) {
						$scope.ListDeviseCache = success;
						$log.debug('*** TypeArticle *** : ' + JSON.stringify(success, null, "  "));
					},
					function (error) {
						$log.error('****error : ' + error);
					});
			var reloadListMarche = function () {
				$http
					.get(UrlAtelier + "/marche/getAll")
					.success(
						function (dataMarche) {

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
				case 'elementReglementAchat':
					$scope.ITEM = 'elementReglementAchat';
					break;
				case 'soldeFournisseur':
					$scope.ITEM = 'soldeFournisseur';
					break;
				case 'echeancier':
					$scope.ITEM = 'echeancier';
					break;
				case 'reporting':
					$scope.ITEM = 'reporting';
					break;
				case 'rapprochement':
					$scope.ITEM = 'rapprochement';
				break;
				
				
					case 'reglementInverseVente':
					$scope.ITEM = 'reglementInverseVente';
					break;
				case 'elementReglementInverseAchat':
					$scope.ITEM = 'elementReglementInverseAchat';
					break;
						case 'echeancierInverse':
					$scope.ITEM = 'echeancierInverse';
					break;
					
					
			}

			$scope.goCoutsVente = function () {
				$scope.ITEM = 'coutsVente';
			}

			$scope.goReglementVente = function () {
				$scope.ITEM = 'reglementVente';
			}

			$scope.goElementReglementAchat = function () {
				$scope.ITEM = 'elementReglementAchat';
			}

			$scope.goSoldeClient = function () {
				$scope.ITEM = 'soldeClient';
			}
			$scope.goEcheancier = function () {
				$scope.ITEM = 'echeancier';
			}
			$scope.goReporting = function () {
				$scope.ITEM = 'reporting';
			}
		})
	//Filtre sur le champ prix du tableau ProduitBS: retourne les 3 chiffres apres le point .
	.filter('prixFiltre', function () {
		return function (prix) {
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