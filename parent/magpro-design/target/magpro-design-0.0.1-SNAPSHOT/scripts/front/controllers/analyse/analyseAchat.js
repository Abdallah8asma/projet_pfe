
'use strict'

angular
    .module('gpro.analyseAchat', [])
    .controller(
        'AnalyseAchatController', [
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
                	

                  	$scope.displayChartDepenseBr=false;
                	$scope.displayChartDepenseFacture=false;
                	$scope.displayChartReglement=false;
                	$scope.displayChartTop10Article=false;
                	$scope.displayChartTop3Frs=false;
                	$scope.displayChartTop3Frs=false;

                	
                	
                	
                	var chartCA;
                	var chartReglement;
                	var chartTop10Article;
                	var chartTop10Client;
                	
                	$scope.boutiques=[];

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
                	
    				$scope.createChartDepenseBr= function(){
    					
    					var request= {
    	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
    	                          "dateLivraisonDu":$scope.CritRecherche.dateMin,
    	                          "dateLivraisonA":$scope.CritRecherche.dateMax
    	                          };
    					
    					
    					 $http
                         .post(
                             UrlAtelier +
                             "/chartGc/getDepenseBRbyMonth",
                             request)
                         .success(
                             function(	
                                 resultat) {
                                $scope.createAmChartSerial(chartDepenseBR, "chartDepenseBR","Dépense BR", "mois", "montantTTC",resultat);
            					$scope.displayChartDepenseBr=true;	
                                
                             });
    					
    					
    					
    				}
    				$scope.createChartDepenseFacture= function(){
    					
    					var request= {
    	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
    	                          "dateFactureMin":$scope.CritRecherche.dateMin,
    	                          "dateFactureMax":$scope.CritRecherche.dateMax,
    	                          "type":"Normal"
    	                        };
    					
    					
    					 $http
                         .post(
                             UrlAtelier +
                             "/chartGc/getDepenseFacturebyMonth",
                             request)
                         .success(
                             function(	
                                 resultat) {
                                $scope.createAmChartSerial(chartDepenseFacture, "chartDepenseFacture","Dépense Facture", "mois", "montantTTC",resultat);
            					$scope.displayChartDepenseFacture=true;	
                                
                             });
    					
    					
    				}
    				$scope.createChartReglement= function(){
    					
    					var request= {
    	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
    	                          "dateReglementMin":$scope.CritRecherche.dateMin,
    	                          "dateReglementMax":$scope.CritRecherche.dateMax
    	                        };
    					
    					
    					 $http
                       .post(
                           UrlAtelier +
                           "/chartGc/getReglementAchatByMonth",
                           request)
                       .success(
                           function(	
                               resultat) {
                              $scope.createAmChartSerial(chartReglement, "chartReglement","Règlement", "mois", "montantTTC",resultat);
          	            	$scope.displayChartReglement=true;
                              
                           });
    					
    					
    					
    					

    				}
    				$scope.createChartTop10Article= function(){
    					var request= {
    	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
    	                          "dateLivraisonDu":$scope.CritRecherche.dateMin,
    	                          "dateLivraisonA":$scope.CritRecherche.dateMax
    	                          };
    					
    					
    					 $http
                       .post(
                           UrlAtelier +
                           "/chartGc/getTop10ArticleAchat",
                           request)
                       .success(
                           function(	
                               resultat) {
                              $scope.createAmChartFunnel(chartTop10Article, "chartTop10Article","TOP 10 - Article", "designation", "qte",resultat);

          	            	$scope.displayChartTop10Article=true;                          
                           });
    					

    				}
    				
    				
    				$scope.createChartTop3Frs= function(){
    					var request= {
    	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
    	                          "dateLivraisonDu":$scope.CritRecherche.dateMin,
    	                          "dateLivraisonA":$scope.CritRecherche.dateMax
    	                          };
    					
    					
    					 $http
                     .post(
                         UrlAtelier +
                         "/chartGc/getTop3Fournisseur",
                         request)
                     .success(
                         function(	
                             resultat) {
                            $scope.createAmChartFunnel(chartTop3Frs, "chartTop3Frs","TOP 3 - Fournisseur", "designation", "qte",resultat);

        	            	$scope.displayChartTop3Frs=true;                          
                         });
    					
    					
    					
    					
    					

    				}
    				
    				
    				$scope.createChartDepenseParFrs = function(){
    					var request= {
    	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
    	                          "dateLivraisonDu":$scope.CritRecherche.dateMin,
    	                          "dateLivraisonA":$scope.CritRecherche.dateMax
    	                          };
    					
    					
    					 $http
                     .post(
                         UrlAtelier +
                         "/chartGc/getDepenseFournisseur",
                         request)
                     .success(
                         function(	
                             resultat) {
                             $scope.createAmChartSerial(chartTop3Frs, "chartTop3Frs","Dépense Par Fournisseur", "designation", "montant",resultat);


        	            	$scope.displayChartTop3Frs=true;                          
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

    	            	$scope.displayChartDepenseBr=false;
    	            	$scope.displayChartDepenseFacture=false;
    	            	$scope.displayChartReglement=false;
    	            	$scope.displayChartTop10Article=false;
    	            	$scope.displayChartTop3Frs=false;
    	            	$scope.displayChartTop3Frs=false;

    	            	
    	            	 $scope.CritRecherche = {
    	                          "boutiqueId":"",
    	                          "dateMin":"",
    	                          "dateMax":""
    	                        };
    				}

                	
                                 
                }]) ;