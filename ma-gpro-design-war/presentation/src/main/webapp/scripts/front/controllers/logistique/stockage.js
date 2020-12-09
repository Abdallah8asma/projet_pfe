'use strict'

angular
		.module('atelier.stockage', [ "ngResource" ])
		.controller(
				'stockageController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						function($scope, $filter, $http ,$log, downloadService, UrlCommun, UrlAtelier) {
							// Déclaration des variables globales utilisés
							$log.info("============= nouveau stockage ===============");
							var data;
							$scope.displayMode = "list";
							$scope.listePartieInteressee = [];
							$scope.listeEntrepot = [];
							$scope.listeProduitCache = [];
							

							function formattedDate(date) {
							    var d = new Date(date),
							        month = '' + (d.getMonth() + 1),
							        day = '' + d.getDate(),
							        year = d.getFullYear();

							    if (month.length < 2) month = '0' + month;
							    if (day.length < 2) day = '0' + day;
							    return [year,month,day].join('-');
							}
							
							/** ** PDF ** */
							$scope.download = function(inventaireCourant) {
								
								var newdateFormat=null;
								if(angular.isDefined(inventaireCourant.dateEtat)){
									//$log.debug("==dateEtat "+inventaireCourant.dateEtat);
									//$log.debug("==dateEtat AFTER CONVERT "+formattedDate(inventaireCourant.dateEtat));
									
									if(inventaireCourant.dateEtat != null){
									var newdateFormat = formattedDate(inventaireCourant.dateEtat);
									$log.debug("===== newdateFormat "+newdateFormat);
									}else{
										$log.debug("===== newdateFormat is Null");
									}
								}else{
									$log.debug("==dateEtat Undefined");
								}
								var url = UrlAtelier+"/report/inventaire?client="+inventaireCourant.client+
															  "&nombreColieDu="+inventaireCourant.nombreColieDu+
															  "&nombreColieA="+inventaireCourant.nombreColieA+
															  "&entrepot="+inventaireCourant.entrepot+
															  "&emplacement="+inventaireCourant.emplacement+
															  "&metrageDu="+inventaireCourant.metrageDu+
															  "&metrageA="+inventaireCourant.metrageA+
															  "&dateEtat="+newdateFormat+
															  "&designationQuiContient="+inventaireCourant.designationQuiContient+
															  "&referenceProduit="+inventaireCourant.idProduitParRef+
															  "&fini="+inventaireCourant.fini+
															  "&orderBy="+inventaireCourant.orderBy+
															  "&type=pdf";
								downloadService.download(url)
										.then(
												function(success) {
													$log.debug('success : '
															+ success);
												},
												function(error) {
													$log.debug('error : '
															+ error);
												});
							};

							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.rechercheInventaireForm
										.$setPristine();
								$scope.inventaireCourant = {
											 "client":'',
											"nombreColieDu":'',
											 "nombreColieA":'',
											 "entrepot":'',
											 "emplacement":'',
											 "metrageDu":'',
											 "metrageA":'',
											 "dateEtat":'',
											 "designationQuiContient":'',
											 "referenceProduit":'',
											 "fini":'',
											 "orderBy":'',
								};
								$scope.displayMode = "list";
							}
							
							/********************************
							 * Gestion des listes deroulantes
							 * à changer par Cache
							 ********************************/
							//listeOrigine
							$scope.listeOrigine = [{id:1,designation:"oui"},{id:2,designation:"non"},{id:3,designation:"tous"}];
          					
							//listeTri
							$scope.listeTri = [{id:1,designation:"reference"},{id:2,designation:"metrage"},{id:3,designation:"prixUnitaire"}];
          					
							// listeEntrepotCache
							$scope.listeEntrepotCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeEntrepotCache").success(
										function(data) {
											$log.debug("listeEntrepotCache "+data.length);
											$scope.listeEntrepotCache = data;

										});
							}
													
							// Liste des PartieInteressee (avec FamilleId=1)
							$scope.listeClientCache = function() {
								$http
										.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeClientCache")
										.success(
												function(dataPartieInteressee) {

													$scope.listeClientCache = dataPartieInteressee;
												});
							}
							
							//Liste des Produits (cache)
							$scope.listeProduitCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeProduitCache").success(
										function(data) {
											console.log("listeProduitCache "+data.length);
											$scope.listeProduitCache = data;

										});
							}
							$scope.listeClientCache();
							$scope.listeEntrepotCache();
							$scope.listeProduitCache();
							

				
						} ])
		