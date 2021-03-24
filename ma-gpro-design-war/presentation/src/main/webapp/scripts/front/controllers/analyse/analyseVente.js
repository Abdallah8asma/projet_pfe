
'use strict'

angular
    .module('gpro.analyseVente', [])
    .controller(
        'AnalyseVenteController', [
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
            	

              	$scope.displayChartCaBl=false;
            	$scope.displayChartCaFacture=false;
            	$scope.displayChartReglement=false;
            	$scope.displayChartTop10Article=false;
            	$scope.displayChartTop10Client=false;
            	$scope.displayChartCAClient=false;
            	$scope.displayChartCASousFamille=false;
            	$scope.displayChartCAFamille=false;
            	$scope.displayChartCAGroupe=false;
            	$scope.displayChartTop10Groupe=false; 

            	
            	
            	
            	var chartCaGroupe;
            	var chartCaSousFamille;
            	var chartCaFamille;
            	var chartCaClient;
            	var chartTop10Groupe;
            	var chartTop10Client;
            	var chartTop10Article;
            	var chartReglement;
            	var chartCaFacture;
            	var chartCaBl;
            	
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
				$scope.createChartCaBl= function(){
					
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateIntroductionMin":$scope.CritRecherche.dateMin,
	                          "dateIntroductionMax":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                     .post(
                         UrlAtelier +
                         "/chartGc/getChiffreAffaireBLbyMonth",
                         request)
                     .success(
                         function(	
                             resultat) {
                            $scope.createAmChartSerial(chartCaBl, "chartCaBl","CA BL", "mois", "montantTTC",resultat);
        					$scope.displayChartCaBl=true;	
                            
                         });
					
					
					
				}
				$scope.createChartCaFacture= function(){
					
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateFactureMin":$scope.CritRecherche.dateMin,
	                          "dateFactureMax":$scope.CritRecherche.dateMax,
	                          "type":"Normal"
	                        };
					
					
					 $http
                     .post(
                         UrlAtelier +
                         "/chartGc/getChiffreAffaireFactureByMonth",
                         request)
                     .success(
                         function(	
                             resultat) {
                            $scope.createAmChartSerial(chartCaFacture, "chartCaFacture","chartCaFacture", "mois", "montantTTC",resultat);
        					$scope.displayChartCaFacture=true;	
                            
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
                       "/chartGc/getReglementByMonth",
                       request)
                   .success(
                       function(	
                           resultat) {
                          $scope.createAmChartSerial(chartReglement, "chartReglement","Règlement", "mois", "montantRegement",resultat);
      	            	$scope.displayChartReglement=true;
                          
                       });
					
					
					
					

				}
				$scope.createChartTop10Article= function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                   .post(
                		   UrlAtelier +
                       "/chartGc/getTop10Article",request)
                   .success(
                       function(	
                           resultat) {
                          $scope.createAmChartFunnel(chartTop10Article, "chartTop10Article","TOP 10 - Article", "designation", "quantite",resultat);

      	            	$scope.displayChartTop10Article=true;                          
                       });
					

				}
				
				
				$scope.createChartTop10Client= function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/getTop10Client",
                     request)
                 .success(
                     function(	
                         resultat) {
                        $scope.createAmChartFunnel(chartTop10Client, "chartTop10Client","TOP 10 - Client", "abreviation", "quantite",resultat);

    	            	$scope.displayChartTop10Client=true;                          
                     });
					
					
					
					
					

				}
				
				
				$scope.createChartTop10Groupe= function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/getTop10Groupe",
                     request)
                 .success(
                     function(	
                         resultat) {
                        $scope.createAmChartFunnel(chartTop10Groupe, "chartTop10Groupe","TOP 10 - groupe", "abreviation", "quantite",resultat);

    	            	$scope.displayChartTop10Groupe=true;                          
                     });
					
					
					
					
					

				}
				
				
				$scope.createChartCAByClient = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/getChiffreAffaireByClient",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartCaClient, "chartCaClient","CA Par Client", "abreviation", "montantTTC",resultat);


    	            	$scope.displayChartCAClient=true;                          
                     });
					
					
					

				}
				
				$scope.createChartCAByGroupe = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/getChiffreAffaireByGroupe",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartCaGroupe, "chartCaGroupe","CA Par Groupe", "abreviation", "montantTTC",resultat);


    	            	$scope.displayChartCAGroupe=true;                          
                     });
					
					
					

				}
				
				
				$scope.createChartCABySousFamille = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/getChiffreAffaireBySousFamille",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartCaSousFamille, "chartCaSousFamille","CA Par Sous Famille", "abreviation", "montant",resultat);


    	            	$scope.displayChartCASousFamille=true;                          
                     });
					
					

				}
				
				
				
				$scope.createChartCAByFamille = function(){
					var request= {
	                          "boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":$scope.CritRecherche.dateMin,
	                          "dateA":$scope.CritRecherche.dateMax
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/getChiffreAffaireByFamille",
                     request)
                 .success(
                     function(	
                         resultat) {
                         $scope.createAmChartSerial(chartCaFamille, "chartCaFamille","CA Par Famille", "abreviation", "montant",resultat);


    	            	$scope.displayChartCAFamille=true;                          
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

	            	 
	            	$scope.displayChartCaBl=false;
	            	$scope.displayChartCaFacture=false;
	            	$scope.displayChartReglement=false;
	            	$scope.displayChartTop10Article=false;
	            	$scope.displayChartTop10Client=false;
	            	$scope.displayChartTop10Groupe=false;
	            	$scope.displayChartCAClient=false;
	            	$scope.displayChartCAGroupe=false;
	            	$scope.displayChartCASousFamille=false;
	            	$scope.displayChartCAFamille=false;
	            	
	            	 $scope.CritRecherche = {
	                          "boutiqueId":"",
	                          "dateMin":"",
	                          "dateMax":""
	                        };
				}

            	
                             
            }]) ;