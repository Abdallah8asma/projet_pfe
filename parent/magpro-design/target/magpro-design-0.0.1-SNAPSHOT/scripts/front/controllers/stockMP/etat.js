'use strict'
/**
 * Gestion Etat
 */
angular.module('gpro.etatMP', [])
.controller('EtatCtrl',[ '$scope', '$http', '$filter','$log', 'UrlAtelier','UrlCommun','downloadService','$window',
	function($scope, $http, $filter,$log,UrlAtelier, UrlCommun,downloadService,$window) {
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
									+ "/magasin/depots")
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

			if(typeof $scope.etatCourant.dateDu === 'undefined'  || $scope.etatCourant.dateDu == null   || $scope.etatCourant.dateDu == ''){
				var dateDu = "";
			}
			else{
				var longDateMin = Date.parse($scope.etatCourant.dateDu);
				var varDateMin = new Date(longDateMin);
				var dateDu = varDateMin.getFullYear()  +'-'+ (varDateMin.getMonth() + 1) +'-'+ varDateMin.getDate();
			}

			if(typeof $scope.etatCourant.dateA === 'undefined'   ||  $scope.etatCourant.dateA == null || $scope.etatCourant.dateA == '' ){
				var dateA = "";
			}
			else{
				var longDateMax = Date.parse($scope.etatCourant.dateA);
				var varDateMax = new Date(longDateMax);
				var dateA = varDateMax.getFullYear()  +'-'+ (varDateMax.getMonth() + 1) +'-'+ varDateMax.getDate();
			}
			//$log.debug("PI  "+produitCourant.partieInteressee );
				if(typeof $scope.etatCourant.quantite === 'undefined'){
				var quantite = "";
			}
			else{
				var quantite=$scope.etatCourant.quantite;
			}
	


			
			var url;
			
				if (typeRapport == "Basique"){
					url = UrlAtelier+ "/reportgs/etatStock?type=xls&articleType=" + $scope.etatCourant.typeArticle+
					
					
				     "&familleArticle="+  $scope.etatCourant.familleArticle +

                     "&article="+  $scope.etatCourant.article +


                     "&magasin="+  $scope.etatCourant.magasin +

                     "&emplacement="+  $scope.etatCourant.emplacement +
	                 "&quantite="+ quantite
					+ "&dimensionArticle="+  $scope.etatCourant.dimensionArticle
					 + "&grammageArticle="+  $scope.etatCourant.grammageArticle
					 + "&operateurQuantite="+  $scope.etatCourant.operateurQuantite
					+ "&dateA="+ dateA
					+"&dateDu="+ dateDu
					+"&orderBy="+ $scope.etatCourant.orderBy
					+"&typeRapport="+ typeRapport ;
				} 
					
				else if (typeRapport == "Global"){
					url = UrlAtelier+ "/reportgs/etatStockGlobal?type=xls&articleType=" + $scope.etatCourant.typeArticle+
					
					     "&familleArticle="+  $scope.etatCourant.familleArticle +

                     "&article="+  $scope.etatCourant.article +


                     "&magasin="+  $scope.etatCourant.magasin +

                     "&emplacement="+  $scope.etatCourant.emplacement +
	
						
					 "&quantite="+ quantite
					 + "&dimensionArticle="+ $scope.etatCourant.dimensionArticle
					  + "&grammageArticle="+ $scope.etatCourant.grammageArticle
					  + "&operateurQuantite="+  $scope.etatCourant.operateurQuantite
					 + "&dateA="+ dateA
					 +"&dateDu="+ dateDu
					+"&typeRapport="+ typeRapport ;

				} 
									
				else{
					url = UrlAtelier+ "/reportgs/etatStockDetaille?type=xls&articleType=" + $scope.etatCourant.typeArticle+
					
					  "&familleArticle="+  $scope.etatCourant.familleArticle +

                     "&article="+  $scope.etatCourant.article +


                     "&magasin="+  $scope.etatCourant.magasin +

                     "&emplacement="+  $scope.etatCourant.emplacement +
					
					 "&quantite="+ quantite
					 + "&dimensionArticle="+ $scope.etatCourant.dimensionArticle
					  + "&grammageArticle="+ $scope.etatCourant.grammageArticle
					  + "&operateurQuantite="+  $scope.etatCourant.operateurQuantite
					 + "&dateA="+ dateA
					 +"&dateDu="+ dateDu
					
					
					+"&typeRapport="+ typeRapport ;
				}
					
			


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

					
				$scope.traitementEnCoursGenererLivraison="false";

				});

		};	
		

			   /*** PDF ***/
			   $scope.downloadBarCode = function(etatCourant) {

				$scope.traitementEnCoursGenererLivraison="true";
								 	
							
				var url;

				if(typeof $scope.etatCourant.dateDu === 'undefined'  || $scope.etatCourant.dateDu == null   || $scope.etatCourant.dateDu == ''){
					var dateDu = "";
				}
				else{
					var longDateMin = Date.parse($scope.etatCourant.dateDu);
					var varDateMin = new Date(longDateMin);
					var dateDu = varDateMin.getFullYear()  +'-'+ (varDateMin.getMonth() + 1) +'-'+ varDateMin.getDate();
				}

				if(typeof $scope.etatCourant.dateA === 'undefined'   ||  $scope.etatCourant.dateA == null || $scope.etatCourant.dateA == '' ){
					var dateA = "";
				}
				else{
					var longDateMax = Date.parse($scope.etatCourant.dateA);
					var varDateMax = new Date(longDateMax);
					var dateA = varDateMax.getFullYear()  +'-'+ (varDateMax.getMonth() + 1) +'-'+ varDateMax.getDate();
				}
				//$log.debug("PI  "+produitCourant.partieInteressee );
					if(typeof $scope.etatCourant.quantite === 'undefined'){
					var quantite = "";
				}
				else{
					var quantite=$scope.etatCourant.quantite;
				}
			
				
				//$log.debug("-- produitCourant After" + JSON.stringify(produitCourant, null, "  ") );
				   url = UrlAtelier + "/reportgs/listEtatStockBarCode?typeArticle="+etatCourant.typeArticle 
									
										 + "&familleArticle="+etatCourant.familleArticle 
										 + "&article="+etatCourant.article 
										 + "&magasin="+etatCourant.magasin 	
										 + "&numeroBonEntree="+etatCourant.numeroBonEntree 	
										 + "&quantite="+ quantite
										 + "&dimensionArticle="+etatCourant.dimensionArticle
										 + "&grammageArticle="+etatCourant.grammageArticle
										 + "&dateA="+ dateA
										 +"&dateDu="+ dateDu
										 + "&operateurQuantite="+etatCourant.operateurQuantite
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

						
					$scope.traitementEnCoursGenererLivraison="false";

					});



			   }
    //Voir Etat
	$scope.voirEtat = function(){

		console.log("voirEtat");
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

	$scope.etatCourant.typeArticle = "1";
	$scope.voirEtat();



	//$scope.rechercherEtat({});
	
	
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
				field: 'dateEntree',
				displayName: 'Date Entree',
				cellFilter: 'date:\'dd-MM-yyyy\'',
					width : '7%'
			},
			{
				field : 'numeroBonEntree',
				displayName : 'N° B.E',
				width :'10%'
			},
			
				{
					field : 'referenceArticle',
					displayName : 'Ref.',
					width :'15%'
				},
				{
					field : 'libelleArticle',
					displayName : 'Description',
					width :'25%'
				},
				{
					field : 'familleArticle',
					displayName : 'Famille',
					width :'10%'
				},

				{
					field : 'qteEntree',
					displayName : 'Q.Entree',
					width :'8%'

				},
				
				{
					field : 'qteActuelle',
					displayName : 'Q.Actuelle.',
					width :'8%'

				},
				{
					field : 'stockMin',
					displayName : 'Stock.Min',
					width :'8%'

				},
			/*	{
					field : 'prixUnitaire',
					displayName : 'Price / U',
					width :'8%'
				},*/
			/* 	{
					field : 'pmp',
					displayName : 'PMP',
					width :'6%'
				}, */
				/*{
					field : 'prixTotal',
					displayName : 'Total P.',
					width :'8%'
				},*/

				{
					field : 'grammageArticle',
					displayName : 'grammage',
					width :'10%'
				},
				{
					field : 'dimensionArticle',
					displayName : 'dimension',
					width :'10%'
				},
				
					{
					field : 'producteurArticle',
					displayName : 'Producteur',
					width :'8%'
				},
				{
					field : 'designationMagasin',
					displayName : 'Store',
					width :'10%'
				}
		/* 		{
					field : 'emplacement',
					displayName : 'Placement',
					width :'10%'
				} */
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
					displayName : 'Ref',
					width :'10%'
				},
				{
					field : 'libelleArticle',
					displayName : 'Description',
					width :'30%'
				},
				{
					field : 'familleArticle',
					displayName : 'Family',
					width :'10%'
				},
				
				{
					field : 'qteActuelle',
					displayName : 'Quantity',
					width :'8%'

				},
				{
					field : 'prixUnitaire',
					displayName : 'Price / U',
					width :'8%'
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'6%'
				},
				{
					field : 'prixTotal',
					displayName : 'Total P',
					width :'8%'
				},
				{
					field : 'designationMagasin',
					displayName : 'Store',
					width :'10%'
				},
				{
					field : 'emplacement',
					displayName : 'Placement',
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
					displayName : 'Ref',
					width :'10%'
				},
				{
					field : 'libelleArticle',
					displayName : 'Description',
					width :'30%'
				},
				{
					field : 'familleArticle',
					displayName : 'Family',
					width :'10%'
				},
				
				{
					field : 'qteActuelle',
					displayName : 'Quantity',
					width :'8%'

				},
				{
					field : 'prixUnitaire',
					displayName : 'Price / U',
					width :'8%'
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'6%'
				},
				{
					field : 'prixTotal',
					displayName : 'Total P.',
					width :'8%'
				},
				{
					field : 'designationMagasin',
					displayName : 'Store',
					width :'10%'
				},
				{
					field : 'emplacement',
					displayName : 'Placement',
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