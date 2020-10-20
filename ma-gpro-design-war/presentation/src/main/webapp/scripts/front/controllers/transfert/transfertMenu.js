'use strict'
/**
 * Menu Gestion_Commerciale
 */
angular
	.module('gpro.transfertMenu', [])
	.controller(
		'transfertMenuController', function ($scope, $route) {

			//	function($rootScope,$scope, $http, $log, UrlAtelier,UrlCommun )
			$scope.ITEM = 'emtpyGc';
			var current = $route.current;
			switch (current.params['type']) {
			
			
			
				case 'bonTransfertReception':
					$scope.ITEM = 'bonTransfertReception';
					break;
					
				case 'bonTransfertSortie':
					$scope.ITEM = 'bonTransfertSortie';
					break;
				


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