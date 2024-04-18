angular
	.module('gpao.produitComposition', ["ngResource"])
	.controller(
		'produitCompositionController',
		[
			'$scope',
			'$filter',
			'$http',
			'$log',
			'downloadService',
			'UrlCommun',
			'UrlAtelier',
			'FactureServices',
			'traitementFaconServices', '$window',
			function ($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier, FactureServices, traitementFaconServices, $window) {

				const CODE_ERROR_DUPLICATE_BL_IN_FACTURE = "CODE_ERROR_DUPLICATE_BL_IN_FACTURE";

				$scope.refBLIsInvalid = "false";

				$log.info("=========Facture========");
				var data;
				$scope.displayMode = "list";
				// bouton pdf hide
				$scope.modePdf = "notActive";
				$scope.produitCompositionCourant = { "type": "Normal" , "declarerecherche" :"oui" };
				// mode list activé
				$scope.displayMode = "list";
				$scope.listeBonSortie = [];
				$scope.listDetFactureVente = [];
				$scope.listDetFactureVentePRBL = [];
				$scope.idFactureVente = '';
				// liste des ReferencesBS
				$scope.tagReferenceBLivList = [];
				$scope.tagReferenceBLivListExterne = [];
				$scope.listTaxeFacture = [];
				$scope.listBLDetaille = [];
				$scope.listeSousFamilleArticle=[]
				// Tableau de Taxe Prédefini
				$scope.ListeDevise = [];

				$scope.listeProduitFinancier = [];
				$scope.listeProduitNonFinancier = [];

				$scope.isCollapsed = true;
				$scope.listeImpressionProduit = [];
				
				$scope.produitIdFinancier ="";
				$scope.produitIdNonFinancier ="";


				$scope.hiddenNotifSucc = "false";

				$scope.closeNotifS = function () {
					$scope.hiddenNotifSucc = "false";
				}
				
				
			

				

			

				// Liste des listGroupeClient
				$scope.listGroupeClient = function () {
					$http
						.get(
							UrlCommun
							+ "/groupeClient/all")
						.success(
							function (dataCategorieCache) {
								// $log.debug("listeCategorie :
								// "+dataCategorieCache.length);
								$scope.listGroupeClient = dataCategorieCache;

							});
				}
				$scope.listGroupeClient();


				/************************************  DEBUT REGLEMENT ****************************/
				$scope.isCollapsedDetailReglement = true;

				$scope.myStyleBtn = {

					"background-color": "yellow"

				};

				// Liste type reglement
				$scope.listTypes = function () {


					$http
						.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeTypeReglementCache")
						.success(
							function (dataProduit) {

								$scope.listTypes = dataProduit;
							});
				}
				$scope.listTypes();

				


				// modeValider
				$scope.modeValider = "notActif";
				// init deleteValue pour cancelAddBLVente
				$scope.deleteValue = "oui";

				/***************************************************************
					* Gestion des DropListe & cache
					**************************************************************/
				$scope.listePartieInteresseeCache = function () {
					$http
						.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
						.success(
							function (dataPartieInteressee) {
								$scope.listePartieInteresseeCache = dataPartieInteressee;
								$log.debug("listePartieInteresseeCache : " + dataPartieInteressee.length)

							});
				}
				$scope.getSousFamilleArticle = function () {
					$http.get(UrlCommun + '/sousFamilleArticle/all').success(function (data) {
					  $scope.listeSousFamilleArticle = data;
					});
				  };
			
				  $scope.getSousFamilleArticle();

				
				

				/***************************************************************
					* Gestion facture -Vente
					**************************************************************/
				$scope.pagingOptions = {
					pageSizes: [5, 10, 13, 52, 130],
					pageSize: 52,
					currentPage: 1
				};

				// Recherche des Bons de Vente
				$scope.rechercherProduitComposition = function (produitCompositionCourant) {

					
					$http
						.post(UrlCommun +
							"/articleProduit/rechercheMulticritere",
							produitCompositionCourant)
						.success(
							function (resultat) {
								$scope.myData = resultat.list;
								// Pagination du resultat de recherche
								// data, page, pageSize
								$scope.setPagingData(
									$scope.myData,
									$scope.pagingOptions.currentPage,
									$scope.pagingOptions.pageSize);

								$log.info("========listeFacture : " + resultat.list.length);

								$scope.rechercheProduitCompositionForm.$setPristine();
							});

				}
                // Liste des produits
							$scope.listeProduitCache = function() {
								$http
										.get(
												UrlAtelier
														+ "/gestionnaireLogistiqueCache/listeProduitCache")
										.success(
												function(data) {
													console
															.log("listeProduitCache "
																	+ data.length);
													$scope.listeProduitCache = data;

												});
							}

							$scope.listeProduitCache();

				// Annuler Recherche
				$scope.annulerAjout = function () {
					
                    $scope.cnt=0;
					// init de l'objet courant
					$scope.produitCompositionCourant = 
                    {
						
						
                        "impressionProduitId":"",
                        "infoMatiere":"",
                        "articleId":"",
                        "qteA":"",
                        "qteDe":"",
                        "grammage":"",
						"dimension":"",
                        "produitSemiFini":"",
                        "sousFamilleArticleId":"",
						
						
                        

					};
					$scope.rechercherProduitComposition({});
							$scope.rechercheProduitCompositionForm.$setPristine();
							$scope.displayMode = "list";

					

				
				}
				
				
				/** * PDF ** */
				// conversion date en String
				function formattedDate(date) {
					
					if(date === undefined) return "";
					if(date == null) return "";
					
					
					var d = new Date(date),
						month = '' + (d.getMonth() + 1),
						day = '' + d.getDate(),
						year = d.getFullYear();

					if (month.length < 2) month = '0' + month;
					if (day.length < 2) day = '0' + day;
					return [year, month, day].join('-');
				}
				$scope.getAllImpressionProduit = function () {
					$http.get(UrlCommun + '/impressionProduit/all').success(function (data) {
					  $scope.listeImpressionProduit = data;
					});
				  };
			
				  $scope.getAllImpressionProduit();

				

				

			

				// generer rapport de tous les bons de livraison. mode : List
				$scope.downloadProduitCompositionExcel = function (produitCompositionCourant) 
				{
					$scope.chargementEnCours = "true";
							
							
								   $http({
									   url: UrlCommun+ "/fiches/listArticleProduit",
									   method: "POST",
									   data: produitCompositionCourant,
									    // this is your json
																	   // data string
									   headers: {
										   'Content-type': 'application/json',
									   },
									   responseType: 'arraybuffer'
								   }).success(function (data, status, headers, config) {
									   
										$scope.chargementEnCours = "false";
										
										

									   var blob = new Blob([data], { type: "application/vnd.ms-excel" });


									   var fileName = 'Liste des Articles du Produit' + formattedDate(new Date());
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




				

					


				/***************************************************************
					* Gestion des Grids de recherche
					**************************************************************/
				$scope.colDefs = [];
				$scope
					.$watch(
						'myData',
						function () {
							$scope.colDefs = [

								
								
								
								{
									field : 'referenceArticle',
									displayName : 'Ref.Produit',
									width : '10%'
								},
								{
									field : 'designationArticle',
									displayName : 'Designation',
									width : '11%'
								},
								{
									field: 'referenceFamilleArticle',
									displayName: 'Ref.FamilleArticle',
									 width:'15%',
								},
                                {
									field: 'qte',
									displayName: 'Quantite',
									 width:'8%'
								},
                                {
									field: 'grammage',
									displayName: 'Grammage',
									 width:'10%'
								},
                                {
									field: 'dimension',
									displayName: 'Dimension',
									 width:'10%'
								},
                                {
									field: 'infoMatiere',
									displayName: 'Info Matiere',
									 width:'10%'
								},
                                {
									field: 'produitSemiFini',
									displayName: 'Produit Semi Fini',
									 width:'13%'
								},
								{
									field: 'impressionDesignation',
									displayName: 'Impression ',
									 width:'13%'
								},


								
								
                            ];
						});

				$scope.filterOptions = {
					filterText: "",
					useExternalFilter: true
				};

				$scope.totalServerItems = 0;

				$scope.setPagingData = function (data, page,
					pageSize) {
					var pagedData = data.slice((page - 1)
						* pageSize, page * pageSize);
					$scope.myData = pagedData;
					$scope.totalServerItems = data.length;
					if (!$scope.$$phase) {
						$scope.$apply();
					}
				};

				$scope.getPagedDataAsync = function (pageSize, page,
					searchText) {
					setTimeout(
						function () {
							var data;
							var produitCompositionCourant = $scope.produitCompositionCourant;
							

							if (searchText) {
								var ft = searchText.toLowerCase();


								$http
						           .post(
                                    UrlCommun +
							           "/articleProduit/rechercheMulticritere",
							                produitCompositionCourant)
									.success(
										function (
											largeLoad) {
											data = largeLoad.list
												.filter(function (item) {
													return JSON.stringify(item)
														.toLowerCase()
														.indexOf(ft) != -1;
												});
											$scope.setPagingData(data, page, pageSize);
										});

							} else {

								$http
						           .post(
                                    UrlCommun +
							           "/articleProduit/rechercheMulticritere",
							                produitCompositionCourant)
									.success(
										function (largeLoad) {
											$log.info("========Grid listeFacture : " + largeLoad.list.length);
											$scope.setPagingData(
												largeLoad.list,
												page,
												pageSize);
										});
							}
						}, 100);
				};

				$scope.getPagedDataAsync(
					$scope.pagingOptions.pageSize,
					$scope.pagingOptions.currentPage);

				$scope
					.$watch(
						'pagingOptions',
						function (newVal, oldVal) {
							if (newVal !== oldVal
								&& newVal.currentPage !== oldVal.currentPage) {
								$scope
									.getPagedDataAsync(
										$scope.pagingOptions.pageSize,
										$scope.pagingOptions.currentPage,
										$scope.filterOptions.filterText);
							}
						}, true);
				$scope.$watch('filterOptions', function (newVal,
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
					 enableHighlighting : true,
					 totalServerItems : 'totalServerItems',
					 pagingOptions : $scope.pagingOptions,
					 selectedItems : $scope.selectedRows,
					 filterOptions : $scope.filterOptions,
				};
				/** Fin de gestion des Grids de la BonVente */

			}])
	// Filtre sur le champ prix du tableau ProduitBS: retourne les 3
	// chiffres apres le point .
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