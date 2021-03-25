
'use strict'

angular
    .module('gpro.analyseStock', [])
    .controller(
        'AnalyseStockController', [
            '$scope',
            '$http',
            '$log',
            '$filter',
            'downloadService',
            'UrlAtelier',
            'UrlCommun',
            '$rootScope',
            '$route',

            function($scope, $http,$log, $filter,
                downloadService, UrlAtelier,UrlCommun,
                $rootScope,$route) {
            	
            	$scope.init=true;
            	
            	$scope.listBoutique=[];
            	
            	  $scope.CritRecherche = {
                          "boutiqueId":"",
                          "dateMin":"",
                          "dateMax":""
                        };
            	

           $scope.displayChartSousFamilleMvtSortie=false;
           $scope.displayChartSousFamilleMvtEntree=false;

           $scope.displayChartArticleMvtSortie=false;
           $scope.displayChartArticleMvtEntree=false;


           $scope.displayChartSousFamilleActuelle=false;
        
           $scope.displayChartArticleActuelle=false;

           $scope.displayChartArticleActuelleEntreeSortie=false;
           $scope.displayChartSousFamilleActuelleEntreeSortie=false;
            	
            	
            	
            	var chartSousFamilleMvtSortie;
            	var chartSousFamilleActuelle;
            	var chartSousFamilleMvtEntree;

            	var chartArticleMvtSortie;
            	var chartArticleMvtEntree;


                var chartArticleActuelle;
            

                 var chartArticleActuelleEntreeSortie;
	             var chartSousFamilleActuelleEntreeSortie;
            	
            	
             	$scope.boutiques=[];


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
							
								$scope.listeFamilleCache();
								
								
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
							
								$scope.listeSousFamilleCache();

				$scope.getAllBoutiques=function(){
					 $http
	                 .get(
	                		 UrlCommun +
	                     "/boutique/all")
	                 .success(
	                     function(	
	                         resultat) {
	                    	 $scope.boutiques=resultat;
	                     });
				}
				
				$scope.getAllBoutiques();

		
				
			
				
				
				$scope.createChartMvtSortieBySousFamille = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax,
                               "type":"SORTIE",
                               "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle  ,
                                 "sousFamilleArticle" :$scope.CritRecherche.sousFamilleArticle 
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getBySousFamille",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartSousFamilleMvtSortie, "chartSousFamilleMvtSortie","Qte Sortie Par Sous Famille", "abreviation", "montant",resultat);


    	            	$scope.displayChartSousFamilleMvtSortie=true;                          
                     });
					
					

				}
				
									
	
				
				
						$scope.createChartMvtEntreeBySousFamille = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax,
                               "type":"ENTREE",
                               "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle,
                             "sousFamilleArticle" :$scope.CritRecherche.sousFamilleArticle 
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getBySousFamille",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartSousFamilleMvtEntree, "chartSousFamilleMvtEntree","Qte Entree Par Sous Famille", "abreviation", "montant",resultat);


    	            	$scope.displayChartSousFamilleMvtEntree=true;                          
                     });
					
					

				}
				
				
				
				
						
				$scope.createChartActuelleBySousFamille = function(){
					var request= {


                               "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle  ,
                              "sousFamilleArticle" :$scope.CritRecherche.sousFamilleArticle 
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getActuelleBySousFamille",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartSousFamilleActuelle, "chartSousFamilleActuelle","Qte Actuelle Par Sous Famille", "abreviation", "montant",resultat);


    	            	$scope.displayChartSousFamilleActuelle=true;                          
                     });
					
					

				}
				
				
						$scope.createChartActuelleByArticle = function(){
					var request= {


                               "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle,
                                  "sousFamilleArticle" :$scope.CritRecherche.sousFamilleArticle 
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getActuelleByArticle",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartArticleActuelle, "chartArticleActuelle","Qte Actuelle Par Sous Famille", "abreviation", "montant",resultat);


    	            	$scope.displayChartArticleActuelle=true;                          
                     });
					
					

				}
				
				
				
				
				
				
				
				
							
				$scope.createChartMvtSortieByArticle = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax,
                               "type":"SORTIE",
                               "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle,
                                "sousFamilleArticle" :$scope.CritRecherche.sousFamilleArticle   
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getByArticle",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartArticleMvtSortie, "chartArticleMvtSortie","Qte Sortie Par Article", "abreviation", "montant",resultat);


    	            	$scope.displayChartArticleMvtSortie=true;                          
                     });
					
					

				}
				
									
	
				
				
						$scope.createChartMvtEntreeByArticle = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax,
                               "type":"ENTREE",
                               "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle  
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getByArticle",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartArticleMvtEntree, "chartArticleMvtEntree","Qte Entree Par Article", "abreviation", "montant",resultat);


    	            	$scope.displayChartArticleMvtEntree=true;                          
                     });
					
					

				}
				
				
				// actuel / enree / sortie
				
				
									
				$scope.createChartActuelleEntreeSortieBySousFamille = function(){
					var request= {

         "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax,
                               "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle  ,
                              "sousFamilleArticle" :$scope.CritRecherche.sousFamilleArticle 
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getActuelleParRapportSortieAndEntreeBySousFamille",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerialMultipleData(chartSousFamilleActuelleEntreeSortie, "chartSousFamilleActuelleEntreeSortie","Qte Act/Ent./Sort. Par Sous Famille", "abreviation", "quantiteActuelle","quantiteEntree","quantiteSortie",resultat);


    	            	$scope.displayChartSousFamilleActuelleEntreeSortie=true;                          
                     });
					
					

				}
				
				
						$scope.createChartActuelleEntreeSortieByArticle = function(){
					var request= {

         "dateDe":$scope.CritRecherche.dateMin,
	                            "dateA":$scope.CritRecherche.dateMax,
                                "typeArticle":$scope.CritRecherche.typeArticle , 
                                "familleArticle":$scope.CritRecherche.familleArticle,
                                "sousFamilleArticle" :$scope.CritRecherche.sousFamilleArticle 
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/mouvementStockChart/getActuelleParRapportSortieAndEntreeByArticle",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerialMultipleData(chartArticleActuelleEntreeSortie, "chartArticleActuelleEntreeSortie","Qte Act/Ent./Sort. Par Article", "abreviation", "quantiteActuelle","quantiteEntree","quantiteSortie",resultat);


    	            	$scope.displayChartArticleActuelleEntreeSortie=true;                          
                     });
					
					

				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				$scope.createAmChartFunnel=function(chart, idChart,title, parametre1, parametre2,data){
					
					chart = am4core.create(idChart, am4charts.SlicedChart);
					chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect

					chart.data = data;

					var series = chart.series.push(new am4charts.FunnelSeries());
					series.colors.step = 2;
					series.dataFields.value = parametre2;
					series.dataFields.category = parametre1;
					series.alignLabels = true;

					series.labelsContainer.paddingLeft = 15;
					series.labelsContainer.width = 200;

					//series.orientation = "horizontal";
					//series.bottomRatio = 1;

					chart.legend = new am4charts.Legend();
					chart.legend.position = "left";
					chart.legend.valign = "bottom";
					chart.legend.margin(5,5,20,5);
					
					
	                $scope.init=false;


					
					
				}
				
				
			
				
				
				
				
				$scope.createAmChartSerial=function(chart, idChart,title, parametre1, parametre2,data){
					
					
					
					 AmCharts.theme = AmCharts.themes.light;
					chart = new AmCharts.AmSerialChart();
	                chart.dataProvider = data;
	                chart.categoryField = parametre1;
	                chart.startDuration = 1;
	                

	                // AXES
	                // category
	                var categoryAxis = chart.categoryAxis;
	                categoryAxis.labelRotation = 45; // this line makes category values to be rotated
	                categoryAxis.gridAlpha = 0;
	                categoryAxis.fillAlpha = 1;
	                categoryAxis.fillColor = "#FAFAFA";
	                categoryAxis.gridPosition = "start";
	                
	              // categoryAxis.parseDates = true;
	    		//	categoryAxis.minPeriod = "MM";
	    			//categoryAxis.equalSpacing = true;
	               


	                // value
	                var valueAxis = new AmCharts.ValueAxis();
	                valueAxis.dashLength = 5;
	                //valueAxis.title =  parametre1;
	                valueAxis.axisAlpha = 0;
	                chart.addValueAxis(valueAxis);
	                

	                // GRAPH
	                var graph = new AmCharts.AmGraph();
	                graph.valueField = parametre2;
	                //graph.colorField = "#85c9c8";
	                graph.precision= 3;
	                graph.balloonText = "<b>[[category]]: [[value]]</b>";
	                graph.type = "column";
	                graph.lineAlpha = 0;
	                graph.fillAlphas = 1;
	                chart.addGraph(graph);

	                // CURSOR
	                var chartCursor = new AmCharts.ChartCursor();
	                chartCursor.cursorAlpha = 0;
	                chartCursor.zoomable = false;
	                chartCursor.categoryBalloonEnabled = false;
	                chart.addChartCursor(chartCursor);

	                chart.creditsPosition = "top-right";
	                chart.export = {
							enabled: false,
							position: "bottom-right"
							}
							chart.initHC = false;
							chart.validateNow();
	                // WRITE
	                chart.write(idChart);
// **************************** chart end
	                
	                
	                $scope.init=false;
				}
				
				
				
				
				
				
				
				// multiple data Amchart
						$scope.createAmChartSerialMultipleData=function(chart, idChart,title, parametre1, parametre2,parametre3,parametre4,data){
					
					
					
					 AmCharts.theme = AmCharts.themes.light;
					chart = new AmCharts.AmSerialChart();
	                chart.dataProvider = data;
	                chart.categoryField = parametre1;
	                chart.startDuration = 1;
	                

	                // AXES
	                // category
	                var categoryAxis = chart.categoryAxis;
	                categoryAxis.labelRotation = 45; // this line makes category values to be rotated
	                categoryAxis.gridAlpha = 0;
	                categoryAxis.fillAlpha = 1;
	                categoryAxis.fillColor = "#FAFAFA";
	                categoryAxis.gridPosition = "start";
	                
	              // categoryAxis.parseDates = true;
	    		//	categoryAxis.minPeriod = "MM";
	    			//categoryAxis.equalSpacing = true;
	               


	                // value
	                var valueAxis = new AmCharts.ValueAxis();
	                valueAxis.dashLength = 5;
	                //valueAxis.title =  parametre1;
	                valueAxis.axisAlpha = 0;
	                chart.addValueAxis(valueAxis);
					
	                

	                // GRAPH
	                var graph = new AmCharts.AmGraph();
	                graph.valueField = parametre2;
	                //graph.colorField = "#0000FF";
	                graph.precision= 3;
	                graph.balloonText = "<b>[[category]]: [[value]]</b>";
	                graph.type = "column";
	                graph.lineAlpha = 0;
	                graph.fillAlphas = 1;
	                chart.addGraph(graph);

                  // GRAPH2
	                var graph2 = new AmCharts.AmGraph();
	                graph2.valueField = parametre3;
	                //graph.colorField = "#FF0000";
	                graph2.precision= 3;
	                graph2.balloonText = "<b>[[category]]: [[value]]</b>";
	                graph2.type = "column";
	                graph2.lineAlpha = 0;
	                graph2.fillAlphas = 1;
	                chart.addGraph(graph2);



                 // GRAPH3
	                var graph3 = new AmCharts.AmGraph();
	                graph3.valueField = parametre4;
	                //graph.colorField = "#85c9c8";
	                graph3.precision= 3;
	                graph3.balloonText = "<b>[[category]]: [[value]]</b>";
	                graph3.type = "column";
	                graph3.lineAlpha = 0;
	                graph3.fillAlphas = 1;
	                chart.addGraph(graph3);




	                // CURSOR
	                var chartCursor = new AmCharts.ChartCursor();
	                chartCursor.cursorAlpha = 0;
	                chartCursor.zoomable = false;
	                chartCursor.categoryBalloonEnabled = false;
	                chart.addChartCursor(chartCursor);

	                chart.creditsPosition = "top-right";
	                chart.export = {
							enabled: false,
							position: "bottom-right"
							}
							chart.initHC = false;
							chart.validateNow();
	                // WRITE
	                chart.write(idChart);
// **************************** chart end
	                
	                
	                $scope.init=false;
				}
				
				
				
				
				
				
				
				
				$scope.createAmChartPie=function(chart, idChart,title, parametre1, parametre2,value1,value2){
					
				  	
					// var legend;
					
					chart = new AmCharts.AmPieChart();
					chart.dataProvider = [
						  {
							  "famille":parametre1,
							  "value":value1
						  },
						  {
							  "famille":parametre2,
							  "value":value2
							 // "color":color[2]
						  }
						  
					  ];
					chart.titleField = "famille";
					chart.valueField = "value";
					chart.colorField= "color";
					chart.outlineColor = "#FFFFFF";
					chart.outlineAlpha = 0.8;
					chart.outlineThickness = 2;
					
					// this makes the chart 3D
					chart.depth3D = 15;
					chart.angle = 30;

					chart.export = {
						enabled : false,
						fileName : "export"
					}

							chart.labelsEnable = false,
							chart.autoMargins = false,
							chart.pullOutRadius = 30,

							// WRITE
							chart
									.write(idChart);
					
					
	            	$scope.init=false;

					
				}
				
				
				
				$scope.annuler=function(){
					
	            	$scope.init=true;

	            	 
	           $scope.displayChartSousFamilleMvtSortie=false;
           $scope.displayChartSousFamilleMvtEntree=false;

           $scope.displayChartArticleMvtSortie=false;
           $scope.displayChartArticleMvtEntree=false;


           $scope.displayChartSousFamilleActuelle=false;
	            	
           $scope.displayChartArticleActuelle=false;

           $scope.displayChartArticleActuelleEntreeSortie=false;
           $scope.displayChartSousFamilleActuelleEntreeSortie=false;

	            	 $scope.CritRecherche = {
						       "typeArticle":"1",
	                          "boutiqueId":"",
	                          "dateMin":"",
	                          "dateMax":""
	                        };
				}

            	
                             
            }]) ;