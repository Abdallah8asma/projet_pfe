
'use strict'

angular
    .module('gpro.dashbord', [])
    .controller(
        'dashbordController', [
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
            	
                $scope.reglementChartList =[];
            	$scope.resultEtiquettes=[];
                
                //$scope.year=null;
                
                $scope.date=new Date();       	
        		$scope.date.setDate(1);
				$scope.date.setMonth($scope.date.getMonth() - 12);
					
				$scope.year=$scope.date.getFullYear();
				

				
				        	$scope.getReglementChart = function(){
           

            		
            		var request= {
                            
                              };
            		
            		
            		 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/getReglementChart",
                     request)
                 .success(
                     function(	
                         resultat) {
	
	                        $scope.reglementChartList = resultat;
	                        
                    	      $scope.createAmChartSerialWith3Data(chartReglement, "chartReglement","chartReglement", "mois", "reglementPrevision","reglementPaye","reglementEchu",resultat);
      					
                     });
            		
            		

            	}
            	
				
				$scope.getReglementChart();
                	
            	$scope.etuiquettes = function(){
					var date=new Date();
	
					date.setDate(1);
	
					
					var request= {
	                          //"boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateDe":date,
	                          "dateA":new Date()
	                          };
					
					
					 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/tableauDeBordEtiquettes",
                     request)
                 .success(
                     function(	
                         resultat) {
                    	 $scope.resultEtiquettes=resultat;
                     });
					
					

				}
            	
            	
            	$scope.createCHartCaByMonth=function(){
            		
            		
            		
            		var request= {
	                          //"boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateFactureMin":$scope.date,
	                          "dateFactureMax":new Date(),
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
                          $scope.createAmChartSerialWith2Data(chartCaFacture, "chartCaFacture","chartCaFacture", "mois", "montantTTC","cibleCA",resultat);
      					
                          
                       });
            	}
            	
            	
            	$scope.createChartDepenseFacture= function(){
					
					var request= {
	                          //"boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateFactureMin":$scope.date,
	                          "dateFactureMax":new Date(),
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
        				
                            
                         });
					
					
				}
				
				
				//chartDifferenceCaFactureDepense
				
				
				      	$scope.createChartDifferenceCaFactureDepenseByMonth=function(){
            		
            		
            		
            		var request= {
	                          //"boutiqueId":$scope.CritRecherche.boutiqueId,
	                          "dateFactureMin":$scope.date,
	                          "dateFactureMax":new Date(),
	                          "type":"Normal"
	                        };
					
					
					 $http
                   .post(
                       UrlAtelier +
                       "/chartGc/getDifferenceChiffreAffaireVenteAchatByMonth",
                       request)
                   .success(
                       function(	
                           resultat) {
                          $scope.createAmChartSerialDifference(chartDifferenceCaFactureDepense, "chartDifferenceCaFactureDepense","Difference", "mois", "montantTTC",resultat);
      					
                          
                       });
            	}
            	
            	
            	
            	/********************************************************************************/
            	
            	$scope.etuiquetteMeilleurClient = function(){
            		var date=new Date();

            		date.setDate(1);

            		
            		var request= {
                              "dateDe":date,
                              "dateA":new Date()
                              };
            		
            		
            		 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/etuiquetteMeilleurClient",
                     request)
                 .success(
                     function(	
                         resultat) {
                    	 $scope.resultEtiquettes.topClientByMonth=resultat;
                     });
            		
            		

            	}
            	
            	
            	
            	$scope.etuiquetteCAFacture = function(){
            		var date=new Date();

            		date.setDate(1);

            		
            		var request= {
                              "dateDe":date,
                              "dateA":new Date()
                              };
            		
            		
            		 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/etuiquetteCAFacture",
                     request)
                 .success(
                     function(	
                         resultat) {
                    	 $scope.resultEtiquettes.CaFactureByMonth=resultat;
                     });
            		
            	}
            	
            	
            	$scope.etuiquetteReglement = function(){
            		var date=new Date();

            		date.setDate(1);

            		
            		var request= {
                              "dateDe":date,
                              "dateA":new Date()
                              };
            		
            		
            		 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/etuiquetteReglement",
                     request)
                 .success(
                     function(	
                         resultat) {
                    	 $scope.resultEtiquettes.ReglementByMonth=resultat;
                     });
            		
            		

            	}
            	
            	$scope.etuiquetteNbrClient = function(){
            		var date=new Date();

            		date.setDate(1);

            		
            		var request= {
                              "dateDe":date,
                              "dateA":new Date()
                              };
            		
            		
            		 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/etuiquetteNbrClient",
                     request)
                 .success(
                     function(	
                         resultat) {
                    	 $scope.resultEtiquettes.nbClient=resultat;
                     });
            		
            	}
            	
            	
            	$scope.etuiquetteNbrFournisseur = function(){
            		var date=new Date();

            		date.setDate(1);

            		
            		var request= {
                              "dateDe":date,
                              "dateA":new Date()
                              };
            		
            		
            		 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/etuiquetteNbrFournisseur",
                     request)
                 .success(
                     function(	
                         resultat) {
                    	 $scope.resultEtiquettes.nbFournisseur=resultat;
                     });
            		
            	}
            	
            	$scope.etuiquetteReglementEchus = function(){
            		var date=new Date();

            		date.setDate(1);

            		
            		var request= {
                              "dateDe":date,
                              "dateA":new Date()
                              };
            		
            		
            		 $http
                 .post(
                     UrlAtelier +
                     "/chartGc/etuiquetteReglementEchus",
                     request)
                 .success(
                     function(	
                         resultat) {
                    	 $scope.resultEtiquettes.ReglementEchus=resultat;
                     });
            		
            	}
            	
            	$scope.etuiquetteNbrFournisseur();
            	$scope.etuiquetteNbrClient();
            	$scope.etuiquetteReglement();
            	$scope.etuiquetteCAFacture();
            	$scope.etuiquetteMeilleurClient();
            	$scope.etuiquetteReglementEchus();
            	
            	/*******************************************************************************/
            	
            	
            	//$scope.etuiquettes();
            	
            	$scope.createCHartCaByMonth();
            	
            	$scope.createChartDepenseFacture();


                 $scope.createChartDifferenceCaFactureDepenseByMonth();
            	
        		$scope.createAmChartSerial=function(chart, idChart,title, parametre1, parametre2,data){
					
					
					
					 AmCharts.theme = AmCharts.themes.dark;
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
					graph.fillColors = "#0275d8";
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
//**************************** chart end
	                
	                
	               
				}
				
				
				// $scope.createAmChartSerialWith2Data(chartDifferenceCaFactureDepense, "chartDifferenceCaFactureDepense","Difference", "mois", "montantTTC","cibleCA",resultat);
				$scope.createAmChartSerialWith2Data=function(chart, idChart,title, parametre1, parametre2,parametre3,data){
					
					
					
					 AmCharts.theme = AmCharts.themes.dark;
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
					graph.fillColors = "#0275d8";


                 // GRAPH2
	                var graph2 = new AmCharts.AmGraph();
	                graph2.valueField = parametre3;
	                //graph.colorField = "#85c9c8";
	                graph2.precision= 3;
	                graph2.balloonText = "<b>[[category]]: [[value]]</b>";
	                graph2.type = "line";
					graph2.fillAlphas = 0;
					graph2.lineAlphas =  0.2;
					graph2.bulletSize = 10;
					graph2.bulletBorderColor= "#ffffff";
					graph2.bulletBorderAlpha= 1;
					graph2.bulletBorderThickness= 2;
					graph2.lineThickness= 3;

	                chart.addGraph(graph2);
				//	graph2.fillColors = "green";
				//	graph2.lineThickness = 3;
					graph2.lineColor = "green";
				//	graph2.useLineColorForBulletBorder = true;
/**
Type of the graph. Possible values are: 
"line", "column", "step", "smoothedLine", "candlestick", "ohlc". XY and Radar charts can only display "line" type graphs.
*/


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
//**************************** chart end
	                
	                
	               
				}
				
				
				
				
				
					// $scope.createAmChartSerialWith3Data(chartDifferenceCaFactureDepense, "chartDifferenceCaFactureDepense","Difference", "mois", "montantTTC","cibleCA",resultat);
				$scope.createAmChartSerialWith3Data=function(chart, idChart,title, parametre1, parametre2,parametre3,parametre4,data){
					
					
					
					 AmCharts.theme = AmCharts.themes.dark;
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
					graph.fillColors = "#0275d8";

                 // GRAPH2
	                var graph2 = new AmCharts.AmGraph();
	                graph2.valueField = parametre3;
	                //graph.colorField = "#85c9c8";
	                graph2.precision= 3;
	                graph2.balloonText = "<b>[[category]]: [[value]]</b>";
	                graph2.type = "line";
					graph2.fillAlphas = 0;
					graph2.lineAlphas =  0.2;
					graph2.bulletSize = 10;
					graph2.bulletBorderColor= "#ffffff";
					graph2.bulletBorderAlpha= 1;
					graph2.bulletBorderThickness= 2;
					graph2.lineThickness= 3;
					graph2.lineColor = "green";
	                chart.addGraph(graph2);
					//graph2.fillColors = "green";


                 // GRAPH2
	                var graph3 = new AmCharts.AmGraph();
	                graph3.valueField = parametre4;
	                //graph.colorField = "#85c9c8";
	                graph3.precision= 3;
	                graph3.balloonText = "<b>[[category]]: [[value]]</b>";
					graph3.type = "line";
					graph3.fillAlphas = 0;
					graph3.lineAlphas =  0.2;
					graph3.bulletSize = 10;
					graph3.bulletBorderColor= "#ffffff";
					graph3.bulletBorderAlpha= 1;
					graph3.bulletBorderThickness= 2;
					graph3.lineThickness= 3;
					graph3.lineColor = "purple";
	                chart.addGraph(graph3);
					//graph3.fillColors = "purple";


/**
Type of the graph. Possible values are: 
"line", "column", "step", "smoothedLine", "candlestick", "ohlc". XY and Radar charts can only display "line" type graphs.
*/


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
//**************************** chart end
	                
	                
	               
				}
				
				
				
				
				
				
				 		$scope.createAmChartSerialDifference=function(chart, idChart,title, parametre1, parametre2,data){
					
						/* 	var chart = new CanvasJS.Chart("chartDifferenceCaFactureDepense", {
								theme: "light1", // "light1", "ligh2", "dark1", "dark2"
								animationEnabled: true,
								//title: {
								//	text: "Company Finance"
								//},
								axisY: {
								//	title: "Amount (in USD)",
								//	prefix: "$",
								//	suffix: "k",
								//	lineThickness: 0,
								//	includeZero: true,
									interval: 250
								},
								data: [{
									type: "waterfall",
									indexLabel: "{y}",
									indexLabelFontColor: "#EEEEEE",
									indexLabelPlacement: "inside",
									yValueFormatString: "#,##0",
									dataPoints: [
										{ label: data[0].mois,  y: data[0].montantTTC },
										{ label:  data[1].mois,  y: data[1].montantTTC },
										{ label:  data[2].mois,  y: data[2].montantTTC},
										{ label: data[3].mois,  y: data[3].montantTTC},
										{ label: data[4].mois,  y: data[4].montantTTC},
										{ label: data[5].mois,  y: data[5].montantTTC},
										{ label:  data[6].mois,  y: data[6].montantTTC },
									]
								}]
							});
							chart.render(); */
					
					 AmCharts.theme = AmCharts.themes.dark;
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
					graph.fillColors = "green";
					graph.negativeFillColors = "red";
					console.log("graph " , graph);
					

					
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
//**************************** chart end
	                
	                
	               
				}
				
				
				
				
				
                	
                                 
                }]) ;