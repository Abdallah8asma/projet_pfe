'use strict'
/**
 * Menu Gestion_Commerciale
 */
angular
	.module('atelier.menuGC', [])
	.controller(
		'MenugestionController', function ($rootScope, $scope, $http, $log, initCommercialService, UrlAtelier, UrlCommun, $route) {

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
			/*
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
				*/

			/** redirection from bl to facture **/
			$scope.redirectToFacture = function (bonLivraison) {
				$scope.ITEM = 'factureVente';
				$scope.navMode = "redirection";
				$scope.sendedBL = bonLivraison;

			}

			//Test des service de cache : TODO : remplacer les par les Ws adequats.
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
			$scope.ITEM = 'emtpyGc';
			var current = $route.current;
			switch (current.params['type']) {
				case 'prixClient':
					$scope.ITEM = 'prixClient';
					break;
				case 'bonCommande':
					$scope.ITEM = 'bonCommande';
					break;
					case 'detBonCommande':
						$scope.ITEM = 'detBonCommande';
						break;	
				case 'devis':
					$scope.ITEM = 'devis';
					break;
				case 'marche':
					$scope.ITEM = 'marche';
					break;
				case 'livraison':
					$scope.ITEM = 'livraison';
					break;
					case 'livraisonBS':
					$scope.ITEM = 'livraisonBS';
					break;
				case 'detlivraison':
					$scope.ITEM = 'detlivraison';
					break;
				case 'factureVente':
					$scope.ITEM = 'factureVente';
					break;
				case 'detailsFactureVente':
					$scope.ITEM = 'detailsFactureVente';
					break;	
				case 'factureAvoirVente':
					$scope.ITEM = 'factureAvoirVente';
					break;
				case 'factureRetourVente':
					$scope.ITEM = 'factureRetourVente';
					break;
				case 'reporting':
					$scope.ITEM = 'reporting';
					break;
					
				case 'remiseVente':
					$scope.ITEM = 'remiseVente';
					break;
					
				case 'packageVente':
					$scope.ITEM = 'packageVente';
					break;
				


			}


			// if (current.params['type'] == 'factureVente') {
			// 	$scope.ITEM = 'factureVente';
			// } else {
			// 	$scope.ITEM = 'livraison';

			// }


			$scope.goPrixClient = function () {
				$scope.ITEM = 'prixClient';
			}

			$scope.goBonCommande = function () {
				reloadListMarche();
				$scope.ITEM = 'bonCommande';
			}

			$scope.goDevis = function () {
				reloadListMarche();
				$scope.ITEM = 'devis';
			}
			/*$scope.goReception = function(){
				$scope.ITEM = 'reception';
				}*/

			$scope.goMarche = function () {
				reloadListMarche();
				$scope.ITEM = 'marche';
			}
			$scope.goLivraison = function () {
				$scope.navMode = "normal";
				//						reloadListMarche();
				$scope.ITEM = 'livraison';
			}

			$scope.goDetLivraison = function () {
				$scope.ITEM = 'detlivraison';
			}

			
			$scope.goFactureAvoirVente = function () {
				$scope.navMode = "normal";
				$scope.ITEM = 'factureAvoirVente';
			}


			$scope.goFactureRetourVente = function () {
				$scope.navMode = "normal";
				$scope.ITEM = 'factureRetourVente';
			}
			$scope.goReglementVente = function () {
				$scope.navMode = "normal";
				$scope.ITEM = 'reglementVente';
			}
			$scope.goSoldeClient = function () {
				$scope.navMode = "normal";
				$scope.ITEM = 'soldeClient';
			}
			$scope.goEcheancier = function () {
				$scope.navMode = "normal";
				$scope.ITEM = 'echeancier';
			}
			$scope.goReporting = function () {
				$scope.ITEM = 'reporting';
			}
			
			$scope.goRemiseVente = function(){
				$scope.navMode = "normal";
				$scope.ITEM = 'remiseVente';
				}
			$scope.goPackageVente = function(){
				$scope.navMode = "normal";
				$scope.ITEM = 'packageVente';
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