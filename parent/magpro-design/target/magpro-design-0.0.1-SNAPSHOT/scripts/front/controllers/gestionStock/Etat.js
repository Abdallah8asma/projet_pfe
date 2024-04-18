'use strict'
/**
 * Gestion Etat
 */
angular.module('gpro.etat', [])
.controller('EtatCtrl',[ '$scope', '$http', '$filter','$log', 'UrlAtelier','UrlCommun','downloadService',
	function($scope, $http, $filter,$log,UrlAtelier, UrlCommun,downloadService) {
	//declaration variable
        $scope.etatCourant={};
        $scope.myData = [];
        $scope.listeMagasinCache = [];
		$scope.ListEmplacementCache = [];
		$scope.ListeRaisonCache = [];
		$scope.ListFamillesCache =[] ;
		$scope.listeSousFamilleCache =[];
		$scope.listeArticle = [];
		
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

		 //liste famille cache 
         	// Liste des FamilleCache
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
								$scope.ListEmplacementCache = dataEmplacementCache;

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
		
		$scope.listeArticleFct();
        $scope.listeFamilleCache ();
		$scope.listeSousFamilleCache();
		$scope.listeMagasinCache();
		$scope.ListeRaisonCache();
		$scope.ListeEmplacementCache();
		
		
		   /*** PDF ***/
		$scope.download = function(typeRapport) {
			
			var url;
			
				if (typeRapport == "Basique"){
					url = UrlAtelier+ "/reportgs/etatStock?type=xls&articleType=" + $scope.etatCourant.typeArticle+"&typeRapport="+ typeRapport ;
				} 
					
				else if (typeRapport == "Global"){
					url = UrlAtelier+ "/reportgs/etatStockGlobal?type=xls&articleType=" + $scope.etatCourant.typeArticle+"&typeRapport="+ typeRapport ;

				} 
									
				else{
					url = UrlAtelier+ "/reportgs/etatStockDetaille?type=xls&articleType=" + $scope.etatCourant.typeArticle+"&typeRapport="+ typeRapport ;
				}
					
			downloadService.download(url).then(
					function(success) {
						//$log.debug('success : ' + success);
						//$scope.annulerAjout();
					}, function(error) {
						//$log.debug('error : ' + error);
					});

		};	
    //Voir Etat
	$scope.voirEtat = function(){
		if($scope.etatCourant.typeArticle == "1"){
			
			$scope.displayEtat = "fourniture";
			$scope.rechercherEtat($scope.etatCourant);
			
		}else if($scope.etatCourant.typeArticle == "3"){
			
			$scope.displayEtat = "filaCoudre";
			$scope.rechercherEtat($scope.etatCourant);
			
		}else if($scope.etatCourant.typeArticle == "2"){
			
			$scope.displayEtat = "tissu";
			$scope.rechercherEtat($scope.etatCourant);
			
		}
		else{
			$scope.displayEtat = "alert";
		}
	}
	//Recherche Etat par type
	$scope.rechercherEtat = function(etatCourante) {
		
		console.log("etatCourante" + JSON.stringify(etatCourante,null,""));
		$http
				.post(
						UrlAtelier
						+ "/entiteStock/rechercheParCritere",
								etatCourante)
				.success(
						function(resultat) {
							$scope.myData = resultat.entiteStock;
							
					});


				
	}
	
	
//	$scope.getArticle = function(articleId) {
//
//		if (articleId != null) {
//			var resultat = $filter('filter')(
//					$scope.listeArticle, {
//						id : articleId
//					});
//
//			$scope.etatCourant.article = resultat[0].designation;
//		}
//
//	}	
	
	$scope.resetFields = function(){
		$scope.etatCourant={};
		$scope.etatCourant.typeArticle='1';
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
		pageSizes : [ 5, 10, 20  ],
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
		data : 'myData',
		columnDefs : [
				{
					field : 'referenceArticle',
					displayName : 'Réf.',
					width :'10%'
				},
				{
					field : 'libelleArticle',
					displayName : 'Désignation',
					width :'30%'
				},
				{
					field : 'familleArticle',
					displayName : 'Famille',
					width :'10%'
				},
				
				{
					field : 'qteActuelle',
					displayName : 'Q.Act.',
					width :'8%'

				},
				{
					field : 'prixUnitaire',
					displayName : 'PU',
					width :'8%'
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'6%'
				},
				{
					field : 'prixTotal',
					displayName : 'P.T',
					width :'8%'
				},
				{
					field : 'designationMagasin',
					displayName : 'Magasin',
					width :'10%'
				},
				{
					field : 'emplacement',
					displayName : 'Empl.',
					width :'10%'
				}
				 ],
		enablePaging : true,
		showFooter : true,
		enableHighlighting : true,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		filterOptions : $scope.filterOptions,

	};
	/** Fin de gestion des Grids Etat Fourniture */
	
	/** Gestion Grid Etat Fil A coudre **/
	$scope.gridOptionsFAC = {
			dataselected : 'myDataselected',
			selectedItems : [],

			data : 'myData',
			columnDefs : [
					{
					field : 'referenceArticle',
					displayName : 'Réf.',
					width :'10%'
				},
				{
					field : 'libelleArticle',
					displayName : 'Désignation',
					width :'30%'
				},
				{
					field : 'familleArticle',
					displayName : 'Famille',
					width :'10%'
				},
				
				{
					field : 'qteActuelle',
					displayName : 'Q.Act.',
					width :'8%'

				},
				{
					field : 'prixUnitaire',
					displayName : 'PU',
					width :'8%'
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'6%'
				},
				{
					field : 'prixTotal',
					displayName : 'P.T',
					width :'8%'
				},
				{
					field : 'designationMagasin',
					displayName : 'Magasin',
					width :'10%'
				},
				{
					field : 'emplacement',
					displayName : 'Empl.',
					width :'10%'
				}
					 ],
		enablePaging : true,
		showFooter : true,
		enableHighlighting : true,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		filterOptions : $scope.filterOptions,

	};
	/** Fin Gestion Grid Etat Fil A Coudre **/
	
	/** Gestion Grid Etat Tissu **/
	$scope.gridOptionsTisuu = {
			dataselected : 'myDataselected',
			selectedItems : [],

			data : 'myData',
			columnDefs : [
					{
					field : 'referenceArticle',
					displayName : 'Réf.',
					width :'10%'
				},
				{
					field : 'libelleArticle',
					displayName : 'Désignation',
					width :'30%'
				},
				{
					field : 'familleArticle',
					displayName : 'Famille',
					width :'10%'
				},
				
				{
					field : 'qteActuelle',
					displayName : 'Q.Act.',
					width :'8%'

				},
				{
					field : 'prixUnitaire',
					displayName : 'PU',
					width :'8%'
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'6%'
				},
				{
					field : 'prixTotal',
					displayName : 'P.T',
					width :'8%'
				},
				{
					field : 'designationMagasin',
					displayName : 'Magasin',
					width :'10%'
				},
				{
					field : 'emplacement',
					displayName : 'Empl.',
					width :'10%'
				}
					 ],
					 enablePaging : true,
		showFooter : true,
		enableHighlighting : true,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		filterOptions : $scope.filterOptions,

	};
	/** Fin Gestion Grid Etat Tisuu **/
	
} ]);