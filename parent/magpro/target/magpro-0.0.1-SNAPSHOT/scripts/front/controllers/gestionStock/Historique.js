'use strict'
/**
 * Gestion Historique
 */
angular
		.module('gpro.historique', [])
		.controller(
				'HistoriqueCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'UrlAtelier',
						'UrlCommun',
						'downloadService',
						function($scope, $http, $filter,$log,UrlAtelier, UrlCommun,downloadService) {
							// declaration variable
							$scope.historiqueCourant = {};
							$scope.myData = [];
							$scope.listeArticle = [];
							$scope.listeMagasinCache = [];
							$scope.ListeEmplacementCache = [];
							$scope.ListeRaisonCache = [];
							$scope.listeClientCache = [];
							$scope.listeFournisseurCache = [];
							$scope.ListeMises = [];
							
							var type ;
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/

							// Liste des Articles
							$scope.listeArticleFct = function() {
								$http
										.get(
												UrlCommun
														+ "/article/all")
										.success(
												function(dataArticle) {
													$scope.listeArticle = dataArticle;

												});
								
							}

							 //Liste famille cache
                             	$scope.listeFamilleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeFamilleArticleCache")
										.success(
												function(dataC) {
													$scope.listeFamilleCache = dataC;
													$log.debug("Famille : "+dataC.length);

												});
							}
							//Liste sous famille cache 
								$scope.listeSousFamilleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeSousFamilleArticleCache")
										.success(
												function(dataC) {
													$scope.listeSousFamilleArticleCache = dataC;
													
												});
							}
                             


							// Liste des Client cache
							$scope.listeClientCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireCache/listeClientCache")
										.success(
												function(dataC) {
													$scope.listeClientCache = dataC;

												});
							}
							// Liste des Fournisseur cache
							$scope.listeFournisseurCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireCache/listeFournisseurCache")
										.success(
												function(dataF) {
													$scope.listeFournisseurCache = dataF;

												});
							}
							// Liste des magasinCache
							$scope.listeMagasinCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireCache/listeMagasinCache")
										.success(
												function(dataMagasin) {
													$scope.listeMagasinCache = dataMagasin;

												});
							}
							// Liste des emplacementCache
							$scope.ListeEmplacementCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireCache/listEmplacementCache")
										.success(
												function(dataEmplacementCache) {
													$scope.ListeEmplacementCache = dataEmplacementCache;

												});
							}
							// Liste des raisonCache
							$scope.ListeRaisonCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireCache/listeRaisonCache")
										.success(
												function(dataSousFamilleCache) {
													$scope.ListeRaisonCache = dataSousFamilleCache;

												});
							}
							
							// Liste des mises
							$scope.ListeMises = function() {
								$http
										.get(
												UrlAtelier
														+ "/mise/all")
										.success(
												function(dataMises) {
													$scope.ListeMises = dataMises;
//													$log.debug("-----$scope.ListeMises " + JSON.stringify($scope.ListeMises , null,"  ") );


												});
							}
							
//							$scope.listeOF = function() {
//	                              $http
//	                                  .get(baseUrlGpao + "/ordreFabrication/all")
//	                                  .success(
//	                                      function(data) {
//	                                        $log.debug("liste OF : " + data.length);
//	                                        $scope.listeOF = data;
//
//	                                      });
//	                            }

							
							$scope.listeArticleFct();
							$scope.listeFamilleCache();
							$scope.listeSousFamilleCache();
							$scope.listeClientCache();
							$scope.listeFournisseurCache();
							$scope.listeMagasinCache();
							$scope.ListeRaisonCache();
							$scope.ListeEmplacementCache();
							$scope.ListeMises();
//							$scope.listeOF();
							
							   /*** PDF ***/
						
							$scope.download = function(historique, type) {
                                 //redondance de code  a noter!!!!!!!!!!
								if ($scope.historiqueCourant.type == "1") {
								         var type = 1;
									$scope.displayEtat = "fourniture";
									$scope.rechercherHistorique($scope.historiqueCourant);
								} else if ($scope.historiqueCourant.type == "3") {
					                             type = 3;
									$log.debug(	"test" );
									$scope.displayEtat = "filaCoudre";
									
								} else if ($scope.historiqueCourant.type == "2") {
								
									         type = 2;
										$scope.displayEtat = "tissu";
											$log.debug(	"test" );
								} else {
									$scope.displayEtat = "alert";
								}
								//$log.debug("-- id" + id);
								var url = UrlAtelier
										+ "/reportgs/mouvementStockHistory?type=xls&historique=" + historique 
										+ "&articleType=" + type;
								downloadService.download(url).then(
										function(success) {
											//$log.debug('success : ' + success);
											//$scope.annulerAjout();
										}, function(error) {
											//$log.debug('error : ' + error);
										});

							};
							
							
							$scope.downloadDetaille = function(historique, type) {
                                //redondance de code  a noter!!!!!!!!!!
								if ($scope.historiqueCourant.type == "1") {
								         var type = 1;
									$scope.displayEtat = "fourniture";
									$scope.rechercherHistorique($scope.historiqueCourant);
								} else if ($scope.historiqueCourant.type == "3") {
					                             type = 3;
									$log.debug(	"test" );
									$scope.displayEtat = "filaCoudre";
									
								} else if ($scope.historiqueCourant.type == "2") {
								
									         type = 2;
										$scope.displayEtat = "tissu";
											$log.debug(	"test" );
								} else {
									$scope.displayEtat = "alert";
								}
								//$log.debug("-- id" + id);
								var url = UrlAtelier
										+ "/reportgs/mouvementStockHistoryDetaille?type=xls&historique=" + historique 
										+ "&articleType=" + type;
								downloadService.download(url).then(
										function(success) {
											//$log.debug('success : ' + success);
											//$scope.annulerAjout();
										}, function(error) {
											//$log.debug('error : ' + error);
										});

							};
							
							$scope.downloadEtatMvt = function() {

								//DATE_FORMAT = "yyyy-MM-dd"

								if(typeof $scope.historiqueCourant.dateDu === 'undefined'){
									var dateMin = "";
								}
								else{
									var longDateMin = Date.parse($scope.historiqueCourant.dateDu);
									var varDateMin = new Date(longDateMin);
									var dateMin = varDateMin.getFullYear()  +'-'+ (varDateMin.getMonth() + 1) +'-'+ varDateMin.getDate();
								}

								if(typeof $scope.historiqueCourant.dateA === 'undefined'){
									var dateMax = "";
								}
								else{
									var longDateMax = Date.parse($scope.historiqueCourant.dateA);
									var varDateMax = new Date(longDateMax);
									var dateMax = varDateMax.getFullYear()  +'-'+ (varDateMax.getMonth() + 1) +'-'+ varDateMax.getDate();
								}

								var url = UrlAtelier
										+ "/reportgs/etatMouvement?id="+ $scope.historiqueCourant.article+"&dateMin="+ dateMin +"&dateMax="+ dateMax +"&type=pdf" ;
								$log.debug("URL etatMVT "+url)	;	
								downloadService.download(url).then(
										function(success) {
											//$log.debug('success : ' + success);
											//$scope.annulerAjout();
										}, function(error) {
											//$log.debug('error : ' + error);
										});

							};

							// Voir Etat
							$scope.voirHistorique = function() {
								if ($scope.historiqueCourant.type == "1") {
									 type = 1;
									$scope.displayEtat = "fourniture";
									
									console.log("----$scope.historiqueCourant----"+JSON.stringify($scope.historiqueCourant,null," "));

									$scope.rechercherHistorique($scope.historiqueCourant);
								} else if ($scope.historiqueCourant.type == "3") {
										type = 3;
									$log.debug(	"test" );
									$scope.displayEtat = "filaCoudre";
									$scope.rechercherHistorique($scope.historiqueCourant);
									
								} else if ($scope.historiqueCourant.type == "2") {
								
									     type = 2;
										$scope.displayEtat = "tissu";
										$scope.rechercherHistorique($scope.historiqueCourant);
								} else {
									$scope.displayEtat = "alert";
								}
							}
							//Recherche Historique par type
							$scope.rechercherHistorique = function(histCourante) {
								$http
										.post(
												UrlAtelier
														+ "/mouvementStock/rechercheParCritere",
														histCourante)
										.success(
												function(resultat) {
													$scope.myData = resultat.mouvementStock;
													$log.debug("LISTE : myData " + JSON.stringify($scope.myData , null,"  ") );
													$log.debug("valeur"+ resultat.mouvementStock);
												});
							}
							
							$scope.resetFields = function(){
								$scope.historiqueCourant={};
								$scope.historiqueCourant.type='1';
								$scope.myData=[];
							}
							
							
							/***************************************************
							 * Gestion des Grids Etat Fourniture
							 **************************************************/
							$scope.typeAlert = 3;
							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};
							$scope.totalServerItems = 0;
							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 14  ],
								pageSize : 14,
								currentPage : 1
							};
							$scope.setPagingData = function(data, page,
									pageSize) {
								var pagedData = data.slice((page - 1)
										* pageSize, page * pageSize);
								$scope.myData = pagedData;
								$scope.totalServerItems = data.length;
								if (!$scope.$$phase) {
									$scope.$apply();
								}
							};
							$scope.getPagedDataAsync = function(pageSize, page,
									searchText) {
								setTimeout(function() {

									if (searchText) {
										var ft = searchText.toLowerCase();

										data = $scope.myData
												.filter(function(item) {
													return JSON.stringify(item)
															.toLowerCase()
															.indexOf(ft) != -1;
												});
										$scope.setPagingData(data, page,
												pageSize);
									} else {

										$scope.setPagingData($scope.myData,
												page, pageSize);

									}
								}, 100);
							};
							$scope.getPagedDataAsync(
									$scope.pagingOptions.pageSize,
									$scope.pagingOptions.currentPage);

							$scope
									.$watch(
											'pagingOptions',
											function(newVal, oldVal) {
												if (newVal !== oldVal
														&& newVal.currentPage !== oldVal.currentPage) {
													$scope
															.getPagedDataAsync(
																	$scope.pagingOptions.pageSize,
																	$scope.pagingOptions.currentPage,
																	$scope.filterOptions.filterText);
												}
											}, true);
							$scope.$watch('filterOptions', function(newVal,
									oldVal) {
								if (newVal !== oldVal) {
									$scope.getPagedDataAsync(
											$scope.pagingOptions.pageSize,
											$scope.pagingOptions.currentPage,
											$scope.filterOptions.filterText);
								}
							}, true);

							$scope.gridOptions = {
								dataselected : 'myDataselected',
								selectedItems : [],

								data : 'myData',
								columnDefs : [ {
									field : 'bonMouvement.numero',
									displayName : 'N° BE'
								}, {
									field : 'clientAbreviation',
									displayName : 'Client'
								}, {
									field : 'bonMouvement.date',
									displayName : 'Date E',
									cellFilter: 'date:\'dd-MM-yyyy\''
								}, {
									field : 'bonMouvement.responsable',
									displayName : 'Resp. E'
								},  {
									field : 'referenceArticle',
									displayName : 'Réf.'
								}, {
									field : 'designationArticle',
									displayName : 'Désignation'
								}, {
									field : 'familleArticle',
									displayName : 'Famille'
								}, {
									field : 'quantiteReelle',
									displayName : 'Q.R'
								},  {
									field : 'designationMagasin',
									displayName : 'Magasin'
								}, {
									field : 'emplacement',
									displayName : 'Empl.'
								} ],
								enablePaging : true,
								showFooter : true,
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								filterOptions : $scope.filterOptions,

							};
							/** Fin de gestion des Grids Etat Fourniture */

							/** Gestion Grid Etat Fil A coudre * */
							$scope.gridOptionsFAC = {
								dataselected : 'myDataselected',
								selectedItems : [],

								data : 'myData',
								columnDefs : [ {
									field : 'bonMouvement.numero',
									displayName : 'N° BE'
								}, {
									field : 'clientAbreviation',
									displayName : 'Client:'
								}, {
									field : 'bonMouvement.date',
									displayName : 'Date',
									cellFilter: 'date:\'dd-MM-yyyy\''
								}, {
									field : 'bonMouvement.responsable',
									displayName : 'Resp. E'
								}, {
									field : 'bonMouvement.valeur',
									displayName : 'Valeur'
								}, {
									field : 'referenceArticle',
									displayName : 'Réf.'
								}, {
									field : 'designationArticle',
									displayName : 'Désignation'
								}, {
									field : 'familleArticle',
									displayName : 'Famille'
								}, {
									field : 'conesReel',
									displayName : 'Cone'
								}, 
								{
									field : 'finconesReel',
									displayName : 'f.Cone'
								},

								{
									field : 'poidsReel',
									displayName : 'Poids'
								}, 
								
							
								{
									field : 'prixUnitaire',
									displayName : 'PU'
								},
								{
									field : 'designationMagasin',
									displayName : 'Magasin'
								}, {
									field : 'emplacement',
									displayName : 'Empl.'
								} ],
								enablePaging : true,
								showFooter : true,
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								filterOptions : $scope.filterOptions,

							};
							/** Fin Gestion Grid Etat Fil A Coudre * */
							
							/** Gestion Grid Etat Tisuu * */
							$scope.gridOptionsTisuu = {
								dataselected : 'myDataselected',
								selectedItems : [],

								data : 'myData',
								columnDefs : [ {
									field : 'bonMouvement.numero',
									displayName : 'N° BE'
								}, {
									field : 'clientAbreviation',
									displayName : 'Client:'
								}, {
									field : 'bonMouvement.date',
									displayName : 'Date',
									cellFilter: 'date:\'dd-MM-yyyy\''
								}, {
									field : 'bonMouvement.responsable',
									displayName : 'Resp. E'
								}, {
									field : 'bonMouvement.valeur',
									displayName : 'Valeur'
								}, {
									field : 'referenceArticle',
									displayName : 'Réf.'
								}, {
									field : 'designationArticle',
									displayName : 'Désignation'
								}, {
									field : 'familleArticle',
									displayName : 'Famille'
								},  {
									field : 'quantiteReelle',
									displayName : 'M.E'
								}, {
									field : 'nbRouleauxReel',
									displayName : 'N.R.E'
								}, 
								
								{
									field : 'prixUnitaire',
									displayName : 'PU'
								},
								{
									field : 'designationMagasin',
									displayName : 'Magasin'
								}, {
									field : 'emplacement',
									displayName : 'Empl.'
								} ],
								enablePaging : true,
								showFooter : true,
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								filterOptions : $scope.filterOptions,

							};
							/** Fin Gestion Grid Etat Tissu * */
							
							
							$scope.getArticle = function(articleId) {

								if (articleId != null) {
									var resultat = $filter('filter')(
											$scope.listeArticle, {
												id : articleId
											});

									$scope.historiqueCourant.articleDesignation = resultat[0].designation;
								}

							}						
							
							
						} ]);