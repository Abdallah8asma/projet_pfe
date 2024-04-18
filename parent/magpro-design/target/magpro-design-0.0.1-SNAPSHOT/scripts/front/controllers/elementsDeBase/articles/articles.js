'use strict'
angular
		.module('gpro.articles', [ "ngResource" ])
		.controller(
				'ArticleController',
				[
						'$scope',
						'$rootScope',
						'$translate',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'initReferentielService',
						'UrlCommun',
						'UrlAtelier',						
						'$window',
						function($scope, $rootScope, $translate, $filter, $http, $log, downloadService,
							initReferentielService, UrlCommun,UrlAtelier,$window) {
							// Déclaration des variables globales utilisées
							$log.info("=============ARTICLE===============");
							/** ***Liste desVariables **** */
							var data;
							$scope.articleBD = [];
							$scope.displayMode = "list";
							$scope.articleCourante = {};
							$scope.listeDocuments = [];
							$scope.listeSeuilApprovisionnement = [];
							$scope.listeDocumentArticle = [];
							$scope.listeGetSeuil = [];
							$scope.mySelections = [];
							$scope.resultatRecherche = $scope.listeArticle;
							$scope.ListType = [];
							$scope.ListGrosseur = [];
							$scope.ListSites = [];
							$scope.ListTypeArticleCache = [];
							$scope.ListFamilleArticleCache = [];
							$scope.ListSousFamilleArticleCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.ListUniteArticleCache = [];
							$scope.ListGrosseurArticleCache = [];
							$scope.ListMetrageArticleCache = [];
							$scope.ListMatiereArticleCache = [];
							$scope.ListTypeDocumentCache = [];
							$scope.articleCourante = {};

							$scope.ListeTaxe = [];
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/
							
							// Liste des TypesCache

							  
							$scope.articleCourante ={
								"typeEntite":'Matiere'
							}
							$scope.listeTypesArticleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeTypeArticleCache")
										.success(
												function(dataType) {
													$log.debug("*LISTTypeCache :"
																	+ dataType.length);
													$scope.ListTypeArticleCache = dataType;

												});
							}

							// Liste des FamilleCache
							$scope.ListFamillesArticleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeFamilleArticleCache")
										.success(
												function(dataFamilleCache) {
													$log.debug("*LISTEFamilleCache :"
																	+ dataFamilleCache.length);
													$scope.ListFamilleArticleCache = dataFamilleCache;

												});
							}

							// Liste des SousFamilleCache
							$scope.ListSousFamillesArticleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeSousFamilleArticleCache")
										.success(
												function(dataSousFamilleCache) {
													$log.debug("*LISTESousFamilleCache :"
																	+ dataSousFamilleCache.length);
													$scope.ListSousFamilleArticleCache = dataSousFamilleCache;

												});
							}

							// Liste des SiteeCache
							$scope.listeSitesPartieInteresseeCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeSitePartieInteresseeCache")
										.success(
												function(dataSiteCache) {
													$log.debug("*LISTESiteCache :"
																	+ dataSiteCache.length);
													$scope.listeSitePartieInteresseeCache = dataSiteCache;

												});
							}

							// Liste des UniteCache
							$scope.listeUnitesArticleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeUniteArticleCache")
										.success(
												function(dataUniteCache) {
													$log.debug("*LISTEUniteCache :"
																	+ dataUniteCache.length);
													$scope.ListUniteArticleCache = dataUniteCache;

												});
							}

							// Liste MatiereCache
							$scope.listeMatiereArticlesCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeMatiereArticleCache")
										.success(
												function(dataMatiereCache) {
													$log.debug("*LISTEMatiereCache :"
																	+ dataMatiereCache.length);
													$scope.ListMatiereArticleCache = dataMatiereCache;

												});
							}

							// Liste MetrageCache
							$scope.listeMetragesArticleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeMetrageArticleCache")
										.success(
												function(dataMetrageCache) {
													$log.debug("*LISTEMetrageCache :"
																	+ dataMetrageCache.length);
													$scope.ListMetrageArticleCache = dataMetrageCache;

												});
							}

							// Liste GrosseurCache
							$scope.listeGrosseursArticleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeGrosseurArticleCache")
										.success(
												function(dataGrosseurCache) {
													$log.debug("*LISTEGrosseurCache :"
																	+ dataGrosseurCache.length);
													$scope.ListGrosseurArticleCache = dataGrosseurCache;

												});
							}

							// Liste TypeDocumentCache
							$scope.listeTypeDocumentsCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeTypeDocumentCache")
										.success(
												function(dataTypeDocumentCache) {
													$log.debug("*LISTTypeDocumentCache :"
																	+ dataTypeDocumentCache.length);
													$scope.ListTypeDocumentCache = dataTypeDocumentCache;

												});
							}

							//$scope.listeTypesArticleCache();
							$scope.ListFamillesArticleCache();
							$scope.ListSousFamillesArticleCache();
							$scope.listeSitesPartieInteresseeCache();
							$scope.listeUnitesArticleCache();
							$scope.listeMatiereArticlesCache();
							$scope.listeMetragesArticleCache();
							$scope.listeGrosseursArticleCache();
							$scope.listeTypeDocumentsCache();

							/***************************************************
							 * Gestion des Articles *
							 **************************************************/
							
							$scope.deleteValue = "oui";
							// Annuler Ajout
							$scope.cancelAddArticle = function(rowform, index,
									id, designation, liste) {
								// $log.debug("* Designation
								// "+liste[0].designation);
								if (angular.isDefined(id)) {

									$log.debug("DEF ID");
									$scope.deleteValue = "non";
									rowform.$cancel();
									$log.debug("CANCEL");
								} else {
									$log.debug("UND ID  " + id);
									if (designation == "") {
										$scope.deleteValue == "oui"
										$log.debug("Designation : "
												+ designation);
										$log.debug("$scope.deleteValueOUI : "
												+ $scope.deleteValue);
										liste.splice(index, 1);
										rowform.$cancel();
										$log.debug("DELETE")
									} else {
										$log.debug("Designation :"
												+ designation);
										$log.debug("$scope.deleteValueNON : "
												+ $scope.deleteValue);
										rowform.$cancel();
										$log.debug("CANCEL");
									}
								}
								$scope.deleteValue = "oui";
							}

							// declaration variable
							$scope.displayAlert = "sleep";
							
							//Init data list
							$scope.initMyData = [];
							
							// Rechercher Article
							$scope.rechercherArticle = function(articleCourante) {
								articleCourante.typeEntite = "1";
								$http
										.post(
												UrlCommun
														+ "/article/rechercheArticleMulticritere",
												articleCourante)
										.success(
												function(resultat) {
													$scope.myData = resultat.articleValues;
													$scope.initMyData = $scope.myData;
													$log.debug("resultat de recherche des Articles .. "+$scope.myData.length);
													//data, page,pageSize
													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																					   $scope.pagingOptions.pageSize										
																					   );
													
													$scope.rechercheArticleForm.$setPristine();
													$scope.displayMode = "rechercher";
													$scope.displayAlert = "sleep";
												});
							}
							
							//Initialiser la liste des articles (ng-grid)
							$scope.rechercherArticle({});
							
							// ** Ajout Article
							$scope.AffectationArticle = function(article) {
								$scope.articleCourante = article ? angular
										.copy(article) : {};
								$scope.articleCourante = {};
								$scope.creationArticleForm.$setPristine();

								$scope.displayMode = "edit";
							}
							
							// Ajout et Modification Article
							$scope.modifierOuCreerArticle = function() {
								var index = this.row.rowIndex;
								// getArticle
								$http
										.get(
												UrlCommun
														+ "/article/getId:"
														+ $scope.myData[index].id)
										.success(
												function(datagetArticle) {

													$scope.listeSeuilApprovisionnement = datagetArticle.seuilEntities;
													$scope.listeDocumentArticle = datagetArticle.documentEntites;

													$scope.myData[index].seuilEntities = $scope.listeSeuilApprovisionnement;
													$scope.myData[index].documentEntites = $scope.listeDocumentArticle;
												});

								$scope.articleCourante = $scope.myData[index] ? angular
										.copy($scope.myData[index])
										: {};

								$scope.displayMode = "edit";
							}

							// Sauvegarder Article
							$scope.sauvegarderAjout = function(article) {

								if (angular.isDefined(article.id)) {
									$log.debug("Sauvegarder Modification"
											+ article.reference);
									$scope.updateArticle(article);
								} else {
									$log.debug("Sauvegarder Ajout");
									$scope.creerArticle(article);
								}
								$scope.rechercherArticle({});
							}

							// Mise à jour des Articles
							$scope.updateArticle = function(article) {
								article.seuilEntities = $scope.listeSeuilApprovisionnement;
								article.documentEntites = $scope.listeDocumentArticle;

								$http
										.post(
												UrlCommun
														+ "/article/modifierArticle",
												article)
										.success(
												function(articleModifiee) {
													//TODO Code à revoir
													for (var i = 0; i < $scope.myData.length; i++) {

														if ($scope.myData[i].id == articleModifiee) {
															$scope.myData[i] = articleModifiee;
															break;
														}
													}
													//TODO getId
													$scope.annulerAjout();
												});
							}
							// Création Article
							$scope.creerArticle = function(article) {
								article.seuilEntities = $scope.listeSeuilApprovisionnement;
								article.documentEntites = $scope.listeDocumentArticle;
								$log.debug("creation "+article);
								
								$http
										.post(
												UrlCommun
														+ "/article/creerArticle",
												article)
										.success(
												function(newArticle) {
													$log.debug("Success Creation : id= "+ newArticle);
													
													//TODO getId
													$scope.annulerAjout();
												});
								
							}
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.articleCourante = {};
								$scope.creationArticleForm.$setPristine();
								$scope.rechercheArticleForm.$setPristine();
								$scope.listeSeuilApprovisionnement = [];
								$scope.listeDocumentArticle = []
								$scope.rechercherArticle({});
								$scope.displayMode = "list";
							}
							// Suppression Article
							$scope.supprimerArticle = function() {
								
								$log.debug("DELETE .."
										+ $scope.myData[$scope.index].id);
								
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/article/supprimerArticle:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
											$scope.myData.splice($scope.index, 1);
								});
								$scope.closePopupDelete();
								$scope.rechercherArticle({});
							};


							$scope.downloadAllArticlesExcel = function (
								articleCourante) {
			
								$http({
									url: UrlCommun + "/fiches/listArticle",
									method: "POST",
									data: articleCourante, // this is your json
																	// data string
									headers: {
										'Content-type': 'application/json',
									},
									responseType: 'arraybuffer'
								}).success(function (data, status, headers, config) {
			
									var blob = new Blob([data], { type: "application/vnd.ms-excel" });
			
			
									var fileName = 'Matiere_Premiere_' + formattedDate(new Date());
									var link = document.createElement('a');
									link.href = window.URL.createObjectURL(blob);
									link.download = fileName;
									link.click();
									window.URL.revokeObjectURL(link.href);
			
			
									// var objectUrl = URL.createObjectURL(blob);
									// window.open(objectUrl);
								}).error(function (data, status, headers, config) {
									// upload failed
								});
			
			
							};
							/** Fin de gestion des Articles */



							$scope.downloadAllArticleBarCode = function(articleCourante) {

								$scope.traitementEnCoursGenererLivraison="true";
								 	
							
								var url;
								//$log.debug("PI  "+produitCourant.partieInteressee );
								
							
								
								//$log.debug("-- produitCourant After" + JSON.stringify(produitCourant, null, "  ") );
				       			url = UrlCommun + "/reportCommun/listarticle?famille=" + articleCourante.familleEntite
									 					
														 + "&type=pdf";
				

											
										var a = document.createElement('a');
										document.body.appendChild(a);
										downloadService.download(url).then(function (result) {
											var heasersFileName = result.headers(['content-disposition']).substring(17);
										var fileName = heasersFileName.split('.');
									var typeFile = result.headers(['content-type']);
									var file = new Blob([result.data], {type: typeFile});
									var fileURL = window.URL.createObjectURL(file);


									a.download = fileName[0];
									$window.open(fileURL)
									 a.click();

								/*	if(typeFile == 'application/vnd.ms-excel'){
			
									 // a.href = fileURL;
										 a.download = fileName[0];
										$window.open(fileURL)
										 a.click();
					
									}else{
								
										a.href = fileURL;
										a.download = fileName[0];
									 $window.open(fileURL)
										a.click();
					
									}*/
										
									$scope.traitementEnCoursGenererLivraison="false";

									});





							 };
							 

									// conversion date en String
				function formattedDate(date) {
					var d = new Date(date), month = ''
						+ (d.getMonth() + 1), day = ''
							+ d.getDate(), year = d.getFullYear();

					if (month.length < 2)
						month = '0' + month;
					if (day.length < 2)
						day = '0' + day;
					return [year, month, day].join('-');
				}

							/***************************************************
							 * Gestion des SeuilApprovisionnement
							 **************************************************/
							$scope.showBtnCalender = true;
							// show bottons Calender
							$scope.showBC = function() {
								$scope.showBtnCalender = true;
							}
							// ajout d'un SeuilApprovisionnement
							$scope.ajoutSeuilApprovisionnement = function() {

								$scope.SeuilApprovisionnementInserree = {

									dateDebut : '',
									dateFin : '',
									maxSeuil : '',
									minSeuil : ''

								};
								$scope.listeSeuilApprovisionnement
										.push($scope.SeuilApprovisionnementInserree);
							};
							$scope.minSeuil = [];
							// Enregistrer SeuilApprovisionnement
							$scope.saveSeuilApprovisionnement = function(
									dataSeuilApprovisionnement, id, index) {
								// $scope.Representant not updated yet
								if (parseInt($scope.listeSeuilApprovisionnement[index].minSeuil) <= parseInt($scope.listeSeuilApprovisionnement[index].maxSeuil)) {
									angular.extend(dataSeuilApprovisionnement,
											{
												id : id
											});
									$scope.showBtnCalender = false;
									$scope.deleteValue = "non";
									$("#min" + index).css('border',
											'solid 1px #ccc');
									$("#max" + index).css('border',
											'solid 1px #ccc');
								} else {
									$scope.listeSeuilApprovisionnement[index].minSeuil = "";
									$scope.listeSeuilApprovisionnement[index].maxSeuil = "";
									// $scope.rowform.$visible = true;
									// $("#min"+index).removeClass('ng-valid');
									// $("#min"+index).removeClass('ng-dirty');
									// $("#min"+index).addClass('ng-invalid');
									$("#min" + index).css('border',
											'solid 1px #ff0000');
									$("#max" + index).css('border',
											'solid 1px #ff0000');
									$scope.rowform.$visible = true;
								}
							};
							// Supprimer SeuilApprovisionnement
							$scope.removeSeuilApprovisionnement = function(
									index) {
								$log.debug("INDEX :" + index);
								$scope.listeSeuilApprovisionnement.splice(
										index, 1);
								$log.debug("Success Delete Seuil ");
							};
							/** Fin de gestion des SeuilApprovisionnement */

							/***************************************************
							 * Gestion des DocumentArticle
							 **************************************************/
							$scope.name = "A";
							// GetId.designation
							$scope.type = {

								status : ''
							};
							$scope.showStatus = function(id) {

								$scope.type.status = id;
								var selected = $filter('filterListArticle')(
										$scope.ListTypeDocumentCache, {
											id : $scope.type.status
										});
								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							};
							// ajout d'un DocumentArticle
							$scope.ajoutDocumentArticle = function() {

								$scope.DocumentArticleInserree = {

									typeDocumentEntite : '',
									path : '',
									uidDocument : ''

								};
								$scope.listeDocumentArticle
										.push($scope.DocumentArticleInserree);
							};

							// Enregistrer DocumentArticle
							$scope.saveDocumentArticle = function(
									dataDocumentArticle, id) {
								$scope.deleteValue = "non";
								$log.debug("deleteValue = "
										+ $scope.deleteValue);
								// $scope.Representant not updated yet
								angular.extend(dataDocumentArticle, {
									id : id
								});
								$log.debug("Success Save Document "
										+ dataDocumentArticle.id);
							};

							// Supprimer DocumentArticle
							$scope.removeDocumentArticle = function(index) {
								$scope.listeDocumentArticle.splice(index, 1);
							};

							// Download Document
							$scope.download = function(uuid) {
								downloadService.download(uuid)
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



     // Liste des Taxes
  	 $scope.ListeTaxe = function () {
        $http.get(UrlAtelier + '/taxe/getTVA').success(function (dataTaxe) {
          $scope.ListeTaxe = dataTaxe;
        });
      };

      $scope.ListeTaxe();  


 /***    calcule de prix de Achat HT       **/

 $scope.calculerPrixAchatHT = function(prixAchatTTC,taxeId){
			
	var element = $scope.ListeTaxe.filter(function(node) {
		return node.id==taxeId;
	});
	
	if(angular.isDefined(element[0]) && prixAchatTTC != null){
		
		var valeurTaxe = element[0].valeur;
		
		$scope.articleCourante.pu = prixAchatTTC / (1+(valeurTaxe/100));
		
		//$scope.produitCourante.prixAchat.toFixed(3);
		
		$scope.articleCourante.pu = Math.round($scope.articleCourante.pu*1000)/1000;
		
	}
	
	
}
 



/***    calcule de prix de Achat TTC      **/
 $scope.calculerPrixAchatTTC = function (prixAchatHT, taxeId) {
var element = $scope.ListeTaxe.filter(function (node) {
return node.id == taxeId;
});

if (angular.isDefined(element[0]) && prixAchatHT != null) {
var valeurTaxe = element[0].valeur;
$scope.articleCourante.tva=valeurTaxe; 
$scope.articleCourante.puTTC =
	prixAchatHT * (1 + valeurTaxe / 100);

// $scope.produitCourante.prixAchatTTC.toFixed(3);
$scope.articleCourante.puTTC = Math.round($scope.articleCourante.puTTC*1000)/1000;
}
};


 

 



							/** Fin de gestion des DocumentArticle */

							/**
							 * ****************Gestion
							 * grid**********************
							 * 
							 * 
							 */
							
							$scope.pagingOptions = {
									pageSizes : [ 5, 10, 13 ],
									pageSize : 13,
									currentPage : 1
								};
							$scope.colDefs = [];
							
							$scope.setColumnDefs = function(){
								
								$scope
								.$watch(
										'myData',
										function() {
											$scope.colDefs = [
													{
														field : 'ref',
														displayName : $translate.instant('article_ref'),
														width:'10%'
													},
													{
														field : 'designation',
														displayName : $translate.instant('article'),
														width:'30%'
													},
													{
														field : 'typeArticleDesignation',
														displayName : $translate.instant('type'),
														width:'10%'
													},
													{
														field : 'familleArticleDesignation',
														displayName : $translate.instant('famille'),
														width:'10%'
													},
													{
														field : 'sousFamilleArtEntiteDesignation',
														displayName : $translate.instant('sous_famille'),
														width:'10%'
													},
													{
														field : 'pu',
														displayName : $translate.instant('prix_unit'),
														width:'5%'
													},
													{
														field : 'pmp',
														displayName : $translate.instant('pmp'),
														width:'5%'
													},
												/* 	{
														field : 'siteEntiteDesignation',
														displayName : $translate.instant('site'),
														width:'10%'
													}, */
												
													{
														field : 'dimension',
														displayName : $translate.instant('dimension'),
														width:'10%'
													},
												
													{
														field : 'grammage',
														displayName : $translate.instant('grammage'),
														width:'10%'
													},
												
													{
														field: '',
														cellTemplate:
														`<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
														  <button class="ms-CommandButton-button ms-CommandButton-Gpro  " ng-click="modifierOuCreerArticle()">
														  <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
														  </button>
														  <button class="ms-CommandButton-button"  ng-click="showPopupDelete(9)" permission="['Matiere_premiere_Delete']">
														  <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
														 </button>
															  </div> `,
													width:'20%'
													  }];
										});
							};
							
							$scope.setColumnDefs();
							
							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};

							$scope.totalServerItems = 0;

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
								setTimeout(
										function() {
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												
												var data  = $scope.initMyData.filter(function(item) {
							                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
												});
												
							                        $scope
													.setPagingData(
															data,
															page,
															pageSize);
													

											} else {
												
												$scope
												.setPagingData(
														$scope.initMyData,
														page,
														pageSize);
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
								data : 'myData',
								columnDefs : 'colDefs',
								enablePaging : true,
								showFooter : true,
								enableColumnResize: true,
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};
							
							
							$rootScope.$on('$translateChangeSuccess', function() {

								$scope.setColumnDefs();  
							});

							/* Fin de gestion des Grids de la partie Interesse */
						} ])
		.controller(
				'DatepickerDController2',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
							// date piker defit
							// $scope.today = function() {
							// $scope.articleCourante.dateIntroduction = new
							// Date();
							// };
							// $scope.today();
							$scope.clear = function() {
								$scope.articleCourante.dateIntroduction = null;
							};
							// Disable weekend selection
							$scope.disabled = function(date, mode) {
								return (mode === 'day' && (date.getDay() === 0 || date
										.getDay() === 6));
							};
							$scope.toggleMin = function() {
								$scope.minDate = $scope.minDate ? null
										: new Date();
							};
							$scope.toggleMin();
							$scope.open = function($event) {
								$event.preventDefault();
								$event.stopPropagation();
								$scope.opened = true;
							};
							$scope.dateOptions = {
								formatYear : 'yy',
								startingDay : 1
							};
							$scope.initDate = new Date('20/08/2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];
						} ])
		.controller(
				'DatepickerControllerMin',
				[
						'$scope',
						function($scope) {
							// date piker defit
							$scope.today = function() {
								$scope.seuilApprovisionnement.dateDebut = new Date();
							};
							$scope.today();
							$scope.clear = function() {
								$scope.seuilApprovisionnement.dateDebut = null;
							};
							// Disable weekend selection
							$scope.disabled = function(date, mode) {
								return (mode === 'day' && (date.getDay() === 0 || date
										.getDay() === 6));
							};
							$scope.toggleMin = function() {
								$scope.minDate = $scope.minDate ? null
										: new Date();
							};
							$scope.toggleMin();
							$scope.open = function($event) {
								$event.preventDefault();
								$event.stopPropagation();
								$scope.opened = true;
							};
							$scope.dateOptions = {
								formatYear : 'yy',
								startingDay : 1
							};
							$scope.initDate = new Date('20/08/2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])
		.filter('filterListArticle', function() {
		  return function(liste, id) {
			 var listeFiltre = [];
			// $log.debug("IdSousFamille=  "+id.id);
			// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
			 angular.forEach(liste, function(elementListe, key){
				
				if(elementListe.id == id.id){
					//$log.debug(sousFamille.id +" == "+ id.id);
					listeFiltre.push(elementListe);
				}
					
			 });
			// $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
			 return listeFiltre;
		  };
		})
		.controller(
				'DatepickerControllerMax',
				[
						'$scope',
						function($scope) {
							// date piker defit
							$scope.today = function() {
								$scope.seuilApprovisionnement.dateFin = new Date();
							};
							$scope.today();
							$scope.clear = function() {
								$scope.seuilApprovisionnement.dateFin = null;
							};
							// Disable weekend selection
							$scope.disabled = function(date, mode) {
								return (mode === 'day' && (date.getDay() === 0 || date
										.getDay() === 6));
							};
							$scope.toggleMin = function() {
								$scope.minDate = $scope.minDate ? null
										: new Date();
							};
							$scope.toggleMin();
							$scope.open = function($event) {
								$event.preventDefault();
								$event.stopPropagation();
								$scope.opened = true;
							};
							$scope.dateOptions = {
								formatYear : 'yy',
								startingDay : 1
							};
							$scope.initDate = new Date('20/08/2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])
