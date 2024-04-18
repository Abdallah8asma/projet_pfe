'use strict'
/**
 * Menu Gestion_Commerciale
 */
angular
	.module('gpro.stockNvMenu', [])
	.controller(
		'stockNvMenuController', function ($scope, $route) {

			//	function($rootScope,$scope, $http, $log, UrlAtelier,UrlCommun )
			$scope.ITEM = 'emtpyGc';
			var current = $route.current;
			switch (current.params['type']) {
				case 'stockNv':
					$scope.ITEM = 'stockNv';
					break;
				case 'stockNvRetour':
					$scope.ITEM = 'stockNvRetour';
					break;
				case 'stockNvRetourDetail':
					$scope.ITEM = 'stockNvRetourDetail';
					break;
				case 'stockNvSortie':
					$scope.ITEM = 'stockNvSortie';
					break;
				case 'stockNvBonInventaire':
					$scope.ITEM = 'stockNvBonInventaire';
					break;


			}

			$scope.goStockNv = function () {

				$scope.ITEM = 'stockNv';
			}

			$scope.goStockNvRetour = function () {

				$scope.ITEM = 'stockNvRetour';
			}

			$scope.goStockNvRetourDetail = function () {

				$scope.ITEM = 'stockNvRetourDetail';
			}

			$scope.goStockNvSortie = function () {

				$scope.ITEM = 'stockNvSortie';
			}
			
			$scope.goStockNvSortie = function () {

				$scope.ITEM = 'stockNvSortie';
			}
			
			$scope.goStockNvBonInventaire = function () {

				$scope.ITEM = 'stockNvBonInventaire';
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