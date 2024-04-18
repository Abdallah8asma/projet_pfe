'use strict'
	angular
			.module('gpro.stockEChart', [])
			.controller(
					'StockCE',
					[
							'$scope',
							'$http',
							'$filter',
							'$rootScope',
							'downloadService',
							'UrlAtelier',
							'UrlCommun',
							function($scope, $http, $filter,$rootScope,downloadService, UrlAtelier,UrlCommun) {
				
								$scope.chartType = "gse1";
								
								$scope.currentRequestgse1 = {};
								$scope.currentRequestgse2 = {};
								$scope.currentRequestgss = {};
								
								$scope.switchToChartgse1= function(){
									$scope.chartType = "gse1";
									
								};
								$scope.switchToChartgse2 = function(){
									$scope.chartType = "gse2";
									
								};
								$scope.switchToChartgss = function(){
									$scope.chartType = "gss";
									
								};
								
								$scope.annulergse1 = function(){
									$scope.currentRequestgse1 = {};
									$scope.gse1({});
									$scope.requestGse1Form.$setPristine();
								};
								$scope.annulergse2 = function(){
									$scope.currentRequestgse2 = {};
									$scope.gse2({});
									$scope.requestGse2Form.$setPristine();
								};
								$scope.annulergss = function(){
									$scope.currentRequestgss = {};
									$scope.gss({});
									$scope.requestGssForm.$setPristine();
								};
								
								$scope.Listesousfamille = function() {
									$http
											.get(UrlCommun+"/gestionnaireCache/listeSousFamilleArticleCache")
											.success(
													function(data) {
														console.log(data);
														$scope.Listesousfamille = data;

													});
								}
								$scope.Listesousfamille();
								$scope.Listearticle = function() {
									$http
											.get(UrlCommun+"/gestionnaireCache/listeTypeArticleCache")
											.success(
													function(data) {
														console.log(data);
														$scope.Listearticle = data;

													});
								}
								$scope.Listearticle();
								
						     $scope.gse1= function(currentRequestgse1,d) {
						    	 
						    		console.log(currentRequestgse1);
						    		
						    
			                        	 var result = [];
										 var resultm = [];
										 var resulta = [];
										
								            
								           	var datachart=result;
								            	if($scope.d==1)
												{
													datachart=result;
												}
												else if($scope.d==2)
													{
													datachart=resultm;
													}
												else if($scope.d==3)
												{
												datachart=resulta;
												}
												
								            	
								
									var urlBase=UrlAtelier+"/variationChart/rechercheMulticritere";
									var res = $http.post(urlBase, currentRequestgse1);
									res.success(function(data, status, headers, config) {
										console.log(data);
								
										var c=[];
										 var testm=[];
									     var testa=[];
									 	$scope.nombreResultaRecherchergse1=data.nombreResultaRechercher;
										if(data.nombreResultaRechercher != 0){
										
										for (var i = 0; i < data.nombreResultaRechercher; i++) {
																					
											var varDate = new Date(data.listVariation[i].date);
											var d,m;
											if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
											if((varDate.getDate())<10){d='0'+(varDate.getDate());}else{d=(varDate.getDate());}
											var date = varDate.getFullYear()  +'-'+ m +'-'+ d;
											var datem=varDate.getFullYear()  +'-'+ m;
											var datea=varDate.getFullYear();
											if(data.listVariation[i].quantiteReelle != 0)
										 { testm.push({

												"date": datem,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#63ABA7"
													    })	
											testa.push({

												"date": datea,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#63ABA7"
													    })	
													 c.push({
			
															"date": date,
															"qual": data.listVariation[i].quantiteReelle,
															"color":"#63ABA7"
															 })	
													} 
											}
										}
										else 
											{
											$scope.nombreResulta=0;
											c.push({

												"date": "",
												"qual": "",
													"color":"#FFFFFF"
												 })	
											}
										console.log(c);
//******************************** par jour **************************************************				
										
										c.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0,
										            color:"#63ABA7"
										        };
										        result.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										console.log(result);
				//**********************************************************************************	
										
				//**********************************par mois************************************************	
										
										testm.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0,
										            color:"#63ABA7"
										          
										        };
										        resultm.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										console.log(testm);
										console.log(resultm);
										
				//**********************************************************************************		
				//**********************************par ans************************************************	
									
										testa.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0,
										            color:"#63ABA7"
										          
										        };
										        resulta.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										console.log(testa);
										console.log(resulta);
										
				//**********************************************************************************	
										
										
											    
			// **************************** chart begin
										var chart;
										chart = new AmCharts.AmSerialChart();
						                chart.dataProvider = datachart;
						                chart.categoryField = "date";
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
						                valueAxis.title = "Quantite Reelle";
						                valueAxis.axisAlpha = 0;
						                chart.addValueAxis(valueAxis);

						                // GRAPH
						                var graph = new AmCharts.AmGraph();
						                graph.valueField = "qual";
						                graph.colorField = "color";
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
												enabled: true,
												position: "bottom-right"
												}
												chart.initHC = false;
												chart.validateNow();
						                // WRITE
						                chart.write("chartdiv");
		    // **************************** chart end
										
									});
									res.error(function(data, status, headers, config) {
										
										//alert( "failure message: " );
									});
						    		
						    		}
						     $scope.gse1({});
	                         
// **************************** chart 2 *************************************************************************************************
	                       
						     $scope.gse2= function(currentRequestgse2,d2) {
						    	 
	                        	 var result = [];
								 var resultm = [];
								 var resulta = [];
								 
						            		var datachart=result;
						            	if($scope.d==1)
										{
											datachart=result;
										}
										else if($scope.d==2)
											{
											datachart=resultm;
											}
										else if($scope.d==3)
										{
										datachart=resulta;
										}
										
						            	var urlBase=UrlAtelier+"/variationChart/rechercheMulticritere";
										var res = $http.post(urlBase, currentRequestgse2);
							res.success(function(data, status, headers, config) {
								console.log("sssss");
								console.log(data);
								//return data;
								var c=[];
								var testm=[];
							     var testa=[];
							 	$scope.nombreResultaRecherchergse2=data.nombreResultaRechercher;
								if(data.nombreResultaRechercher != 0){
								
								for (var i = 0; i < data.nombreResultaRechercher; i++) {
																			
									var varDate = new Date(data.listVariation[i].date);
									var d,m;
									if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
									if((varDate.getDate())<10){d='0'+(varDate.getDate());}else{d=(varDate.getDate());}
									var date = varDate.getFullYear()  +'-'+ m +'-'+ d;
									var datem=varDate.getFullYear()  +'-'+ m;
									var datea=varDate.getFullYear();
									
									if(data.listVariation[i].quantite != 0)
								 { testm.push({

										"date": datem,

										"qual": data.listVariation[i].quantiteReelle,
										"color":"#63ABA7"
											    })	
									testa.push({

										"date": datea,

										"qual": data.listVariation[i].quantiteReelle,
										"color":"#63ABA7"
											    })	
											 c.push({
	
													"date": date,
													"qual": data.listVariation[i].quantiteReelle,
													"color":"#63ABA7"
													 })	
											} 
									}
								}
								
								//******************************** par jour **************************************************				
								
								c.reduce(function (res, value) {
								    if (!res[value.date]) {
								        res[value.date] = {
								            
								            date: value.date,
								            qual: 0,
								            color:"#63ABA7"
								          
								        };
								        result.push(res[value.date])
								    }
								    res[value.date].qual += value.qual
								    return res;
								}, {})
								console.log(result);
		//**********************************************************************************	
								
		//**********************************par mois************************************************	
								
								testm.reduce(function (res, value) {
								    if (!res[value.date]) {
								        res[value.date] = {
								            
								            date: value.date,
								            qual: 0,
								            color:"#63ABA7"
								          
								        };
								        resultm.push(res[value.date])
								    }
								    res[value.date].qual += value.qual
								    return res;
								}, {})
								
								console.log(testm);
								console.log(resultm);
								
		//**********************************************************************************		
		//**********************************par ans************************************************	
							
								testa.reduce(function (res, value) {
								    if (!res[value.date]) {
								        res[value.date] = {
								            
								            date: value.date,
								            qual: 0,
								            color:"#63ABA7"
								          
								        };
								        resulta.push(res[value.date])
								    }
								    res[value.date].qual += value.qual
								    return res;
								}, {})
								
								console.log(testa);
								console.log(resulta);
								
		//**********************************************************************************	
								
								
	// **************************** chart begin
								var chart;
								chart = new AmCharts.AmSerialChart();
				                chart.dataProvider = datachart;
				                chart.categoryField = "date";
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
				    			//categoryAxis.minPeriod = "MM";
				    			//categoryAxis.equalSpacing = true;
				               


				                // value
				                var valueAxis = new AmCharts.ValueAxis();
				                valueAxis.dashLength = 5;
				                valueAxis.title = "Quantite Reelle";
				                valueAxis.axisAlpha = 0;
				                chart.addValueAxis(valueAxis);

				                // GRAPH
				                var graph = new AmCharts.AmGraph();
				                graph.valueField = "qual";
				                graph.colorField = "color";
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
										enabled: true,
										position: "bottom-right"
										}
										chart.initHC = false;
										chart.validateNow();
				                // WRITE
				                chart.write("chartdiv2");
    // **************************** chart end
								
							});
							res.error(function(data, status, headers, config) {
								
								//alert( "failure message: " );
							});
							 }
						     $scope.gse2({});
						     
						     $scope.gss= function(currentRequestgss,ds) {
						    	 var result = [];
								 var resultm = [];
								 var resulta = [];
						    	 var datachart=result;
						    	 if($scope.ds==1)
									{
										datachart=result;
									}
									else if($scope.ds==2)
										{
										datachart=resultm;
										}
									else if($scope.ds==3)
									{
									datachart=resulta;
									}
									
						    	 var urlBase=UrlAtelier+"/variationChart/rechercheMulticritere";
									var res = $http.post(urlBase, currentRequestgss);
						    	 res.success(function(data, status, headers, config) {
										console.log("sssss");
										console.log(data);
										//return data;
										var c=[];
									     var testm=[];
									     var testa=[];
									     $scope.nombreResultaRecherchergss=data.nombreResultaRechercher;
										if(data.nombreResultaRechercher != 0){
										
										for (var i = 0; i < data.nombreResultaRechercher; i++) {
											var varDate = new Date(data.listVariation[i].date);
											var d,m;
											if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
											if((varDate.getDate())<10){d='0'+(varDate.getDate());}else{d=(varDate.getDate());}

											var date = varDate.getFullYear()  +'-'+ m +'-'+ d;
											var datem=varDate.getFullYear()  +'-'+ m;
											var datea=varDate.getFullYear();
											testm.push({

												"date": datem,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#3F51B5"
													    })	
											testa.push({

												"date": datea,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#3F51B5"
													    })	
											c.push({

												"date": date,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#3F51B5"
													    })	
											}}
										
				//******************************** par jour **************************************************				
										
										c.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0
										          
										        };
										        result.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										console.log(result);
				//**********************************************************************************	
										
				//**********************************par mois************************************************	
										
										testm.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0
										          
										        };
										        resultm.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										console.log(testm);
										console.log(resultm);
										
				//**********************************************************************************		
				//**********************************par ans************************************************	
									
										testa.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0
										          
										        };
										        resulta.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										console.log(testa);
										console.log(resulta);
										
				//**********************************************************************************	
										
									 
									var chart;
										chart = new AmCharts.AmSerialChart();
						                chart.dataProvider = datachart;
						                chart.categoryField = "date";
						                chart.startDuration = 1;

						                // AXES
						                // category
						                var categoryAxis = chart.categoryAxis;
						                categoryAxis.labelRotation = 45; // this line makes category values to be rotated
						                categoryAxis.gridAlpha = 0;
						                categoryAxis.fillAlpha = 1;
						                categoryAxis.fillColor = "#FAFAFA";
						                categoryAxis.gridPosition = "start";
						                //categoryAxis.parseDates = true;
							    		//categoryAxis.minPeriod = "DD";
							    		//categoryAxis.equalSpacing = true;

						                // value
						                var valueAxis = new AmCharts.ValueAxis();
						                valueAxis.dashLength = 5;
						                valueAxis.title = "Quantite Reelle";
						                valueAxis.axisAlpha = 0;
						                chart.addValueAxis(valueAxis);

						                // GRAPH
						                var graph = new AmCharts.AmGraph();
						                graph.valueField = "qual";
						                graph.colorField = "color";
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
						                var today = new Date();
						                var dd = today.getDate();
						                var mm = today.getMonth()+1; //January is 0!
						                var yyyy = today.getFullYear();

						                if(dd<10) {
						                    dd='0'+dd
						                } 

						                if(mm<10) {
						                    mm='0'+mm
						                } 

						                today = mm+'/'+dd+'/'+yyyy;
						                
										chart.export = {
											enabled: true,
											position: "bottom-right",
											fileName:"V.SORTIE "+ today,
											content:  [ "Date :"+today, "Titre : Variation des SORTIE", {
											    "image": "reference",
											    "fit": [ 523.28, 769.89 ] // fit image to A4
											  } ]
												
										}
										
										chart.initHC = false;
										chart.validateNow();
								        

						                // WRITE
						            
						                chart.write("chartdiv3");
										
									});
									res.error(function(data, status, headers, config) {
										
										//alert( "failure message: " );
									});
						     }
						     $scope.gss({});
									
							
}]);

		
