'use strict'
/**
 * Menu Gestion_Commerciale
 */
angular
	.module('atelier.menuGA', [])
	.controller(
		'MenugestionAchatController', function ($rootScope, $scope, $http, $log, initCommercialService, UrlAtelier, UrlCommun, $route) {

			$scope.clientActif = {};
			$scope.listeTypeDocumentsCacheALL = [];
			
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
			//	$scope.ITEM = 'emtpyGc';

			var current = $route.current;
			switch (current.params['type']) {
				case 'bcAchat':
					$scope.ITEM = 'bcAchat';
					break;
				case 'reception':
					$scope.ITEM = 'reception';
					break;
				case 'receptionDetail':
					$scope.ITEM = 'receptionDetail';
					break;
				case 'factureAchat':
					$scope.ITEM = 'factureAchat';
					break;
				case 'factureAchatDetail':
						$scope.ITEM = 'factureAchatDetail';
						break;	
				case 'factureAvoirAchat':
					$scope.ITEM = 'factureAvoirAchat';
					break;
				case 'factureRetourAchat':
					$scope.ITEM = 'factureRetourAchat';
					break;
				case 'prixFournisseur':
					$scope.ITEM = 'prixFournisseur';
					break;	
					
					
					
			}

			/** redirection from br to facture **/
			$scope.redirectToFactureAchat = function (br) {
				$scope.ITEM = 'factureAchat';
				$scope.navMode = "redirection";
				$scope.sendedBR = br;

			}


			$scope.goBonCommande = function () {
				reloadListMarche();
				$scope.ITEM = 'bonCommande';
			}
			$scope.goReception = function () {
				$scope.ITEM = 'reception';
			}
			$scope.goReceptionDetail = function () {
				$scope.ITEM = 'receptionDetail';
			}
			$scope.goMarche = function () {
				reloadListMarche();
				$scope.ITEM = 'marche';
			}
			$scope.goLivraison = function () {
				//						reloadListMarche();
				$scope.ITEM = 'livraison';
			}
			$scope.goFactureVente = function () {
				$scope.ITEM = 'factureVente';
			}
			$scope.goFactureAvoirVente = function () {
				$scope.ITEM = 'factureAvoirVente';
			}
			$scope.goReglementVente = function () {
				$scope.ITEM = 'reglementVente';
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
			$scope.goFactureAchat = function () {
				$scope.ITEM = 'factureAchat';
			}

			$scope.goFactureAvoirAchat = function () {
				$scope.ITEM = 'factureAvoirAchat';
			}

			$scope.goFactureRetourAchat = function () {
				$scope.ITEM = 'factureRetourAchat';
			}

			$scope.goBCAchat = function () {
				$scope.ITEM = 'bcAchat';
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