'use strict'

angular
		.module('atelier.stockage', [ "ngResource" ])
		.controller(
				'stockageController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'UrlCommun',
						'UrlAtelier',
						'$window',
						function($scope, $filter, $http ,$log, downloadService, UrlCommun, UrlAtelier,$window) {
							// Déclaration des variables globales utilisés
							$log.info("============= nouveau stockage ===============");
							var data;
							$scope.displayMode = "list";
							$scope.listePartieInteressee = [];
							$scope.listeEntrepot = [];
							$scope.listeProduitCache = [];
							

							function formattedDate(date) {
							    var d = new Date(date),
							        month = '' + (d.getMonth() + 1),
							        day = '' + d.getDate(),
							        year = d.getFullYear();

							    if (month.length < 2) month = '0' + month;
							    if (day.length < 2) day = '0' + day;
							    return [year,month,day].join('-');
							}
							
							/** ** PDF ** */
							$scope.download = function(inventaireCourant) {
								
								
								if(inventaireCourant.metrageDu == null)
							     	inventaireCourant.metrageDu ='';
			
				
								if(inventaireCourant.metrageA == null)
							     	inventaireCourant.metrageA ='';
			
			
								
								var newdateFormat=null;
								if(angular.isDefined(inventaireCourant.dateEtat)){
									//$log.debug("==dateEtat "+inventaireCourant.dateEtat);
									//$log.debug("==dateEtat AFTER CONVERT "+formattedDate(inventaireCourant.dateEtat));
									
									if(inventaireCourant.dateEtat != null){
									var newdateFormat = formattedDate(inventaireCourant.dateEtat);
									$log.debug("===== newdateFormat "+newdateFormat);
									}else{
										$log.debug("===== newdateFormat is Null");
									}
								}else{
									$log.debug("==dateEtat Undefined");
								}
							/*	var url = UrlAtelier+"/report/inventaire?client="+inventaireCourant.client+
															  "&nombreColieDu="+inventaireCourant.nombreColieDu+
															  "&nombreColieA="+inventaireCourant.nombreColieA+
															  "&entrepot="+inventaireCourant.entrepot+
															  "&emplacement="+inventaireCourant.emplacement+
															  "&metrageDu="+inventaireCourant.metrageDu+
															  "&metrageA="+inventaireCourant.metrageA+
															  "&dateEtat="+newdateFormat+
															  "&designationQuiContient="+inventaireCourant.designationQuiContient+
															  "&referenceProduit="+inventaireCourant.idProduitParRef+
															  "&fini="+inventaireCourant.fini+
															  "&orderBy="+inventaireCourant.orderBy+
															  "&type=pdf";

															  */


															  var url = UrlAtelier+"/report/inventaire?client="+inventaireCourant.client+
															  "&nombreColieDu="+
															  "&nombreColieA="+
															 
														
															  "&metrageDu="+inventaireCourant.metrageDu+
															  "&metrageA="+inventaireCourant.metrageA+
															  "&dateEtat="+newdateFormat+
															  "&designationQuiContient="+inventaireCourant.designationQuiContient+
															  "&referenceProduit="+inventaireCourant.idProduitParRef+
															  "&fini=" +
															  "&orderBy="+inventaireCourant.orderBy+
															  "&typeOf="+inventaireCourant.typeOf+
															  "&type=pdf";
								/*downloadService.download(url)
										.then(
												function(success) {
													$log.debug('success : '
															+ success);
												},
												function(error) {
													$log.debug('error : '
															+ error);
												});*/




												var a = document.createElement('a');
												document.body.appendChild(a);
												downloadService.download(url).then(function (result) {
													var heasersFileName = result.headers(['content-disposition']).substring(17);
												var fileName = heasersFileName.split('.');
											var typeFile = result.headers(['content-type']);
											var file = new Blob([result.data], {type: typeFile});
											var fileURL = window.URL.createObjectURL(file);
											if(typeFile == 'application/vnd.ms-excel'){
					
											 // a.href = fileURL;
												 a.download = fileName[0];
												$window.open(fileURL)
												 a.click();
							
											}else{
										
												a.href = fileURL;
												a.download = fileName[0];
											 $window.open(fileURL)
												a.click();
							
											}
												
											$scope.traitementEnCoursGenererLivraison="false";
		
											});


							};
							
							
							
							
							
							
							
							
											/** ** PDF BY OF ** */
							$scope.downloadPDFbyOF = function(inventaireCourant) {
								
								
								if(inventaireCourant.metrageDu == null)
							     	inventaireCourant.metrageDu ='';
			
				
								if(inventaireCourant.metrageA == null)
							     	inventaireCourant.metrageA ='';
			
			
								
								var newdateFormat=null;
								if(angular.isDefined(inventaireCourant.dateEtat)){
									//$log.debug("==dateEtat "+inventaireCourant.dateEtat);
									//$log.debug("==dateEtat AFTER CONVERT "+formattedDate(inventaireCourant.dateEtat));
									
									if(inventaireCourant.dateEtat != null){
									var newdateFormat = formattedDate(inventaireCourant.dateEtat);
									$log.debug("===== newdateFormat "+newdateFormat);
									}else{
										$log.debug("===== newdateFormat is Null");
									}
								}else{
									$log.debug("==dateEtat Undefined");
								}
							/*	var url = UrlAtelier+"/report/inventaireByOF?client="+inventaireCourant.client+
															  "&nombreColieDu="+inventaireCourant.nombreColieDu+
															  "&nombreColieA="+inventaireCourant.nombreColieA+
															  "&entrepot="+inventaireCourant.entrepot+
															  "&emplacement="+inventaireCourant.emplacement+
															  "&metrageDu="+inventaireCourant.metrageDu+
															  "&metrageA="+inventaireCourant.metrageA+
															  "&dateEtat="+newdateFormat+
															  "&designationQuiContient="+inventaireCourant.designationQuiContient+
															  "&referenceProduit="+inventaireCourant.idProduitParRef+
															  "&fini="+inventaireCourant.fini+
															  "&orderBy="+inventaireCourant.orderBy+
															  "&type=pdf";

															  */


															  var url = UrlAtelier+"/report/inventaireByOF?client="+inventaireCourant.client+
															  "&nombreColieDu="+
															  "&nombreColieA="+
															 
														
															  "&metrageDu="+inventaireCourant.metrageDu+
															  "&metrageA="+inventaireCourant.metrageA+
															  "&dateEtat="+newdateFormat+
															  "&designationQuiContient="+inventaireCourant.designationQuiContient+
															  "&referenceProduit="+inventaireCourant.idProduitParRef+
															  "&fini=" +
															  "&orderBy="+inventaireCourant.orderBy+
															  "&typeOf="+inventaireCourant.typeOf+
															  "&type=pdf";
								/*downloadService.download(url)
										.then(
												function(success) {
													$log.debug('success : '
															+ success);
												},
												function(error) {
													$log.debug('error : '
															+ error);
												});*/




												var a = document.createElement('a');
												document.body.appendChild(a);
												downloadService.download(url).then(function (result) {
													var heasersFileName = result.headers(['content-disposition']).substring(17);
												var fileName = heasersFileName.split('.');
											var typeFile = result.headers(['content-type']);
											var file = new Blob([result.data], {type: typeFile});
											var fileURL = window.URL.createObjectURL(file);
											if(typeFile == 'application/vnd.ms-excel'){
					
											 // a.href = fileURL;
												 a.download = fileName[0];
												$window.open(fileURL)
												 a.click();
							
											}else{
										
												a.href = fileURL;
												a.download = fileName[0];
											 $window.open(fileURL)
												a.click();
							
											}
												
											$scope.traitementEnCoursGenererLivraison="false";
		
											});


							};


									
											/** ** Excel BY OF ** */
											$scope.downloadAllProduitsExcelByOF = function(inventaireCourant) {
								
								
												if(inventaireCourant.metrageDu == null)
													 inventaireCourant.metrageDu ='';
							
								
												if(inventaireCourant.metrageA == null)
													 inventaireCourant.metrageA ='';
							
							
												
			
											
												
												
													var newdateFormat=null;
								if(angular.isDefined(inventaireCourant.dateEtat)){
									//$log.debug("==dateEtat "+inventaireCourant.dateEtat);
									//$log.debug("==dateEtat AFTER CONVERT "+formattedDate(inventaireCourant.dateEtat));
									
									if(inventaireCourant.dateEtat != null){
									var newdateFormat = formattedDate(inventaireCourant.dateEtat);
									$log.debug("===== newdateFormat "+newdateFormat);
									}else{
										$log.debug("===== newdateFormat is Null");
									}
								}else{
									$log.debug("==dateEtat Undefined");
								}
							
																			  var url = UrlAtelier+"/fichesLogistique/inventaireByOF?client="+inventaireCourant.client+
																			  "&nombreColieDu="+""+
																			  "&nombreColieA="+""+
																			 
																		
																			  "&metrageDu="+inventaireCourant.metrageDu+
																			  "&metrageA="+inventaireCourant.metrageA+
																			  "&dateEtat="+newdateFormat+
																			  "&designationQuiContient="+inventaireCourant.designationQuiContient+
																			  "&referenceProduit="+inventaireCourant.idProduitParRef+
																			  "&fini=" +
																			  "&orderBy="+inventaireCourant.orderBy+
																			  "&typeOf="+inventaireCourant.typeOf+
																			  "&type=Excel";
						
				
				
																var a = document.createElement('a');
																document.body.appendChild(a);
																downloadService.download(url).then(function (result) {
																	var heasersFileName = result.headers(['content-disposition']).substring(17);
																var fileName = heasersFileName.split('.');
															var typeFile = result.headers(['content-type']);
															var file = new Blob([result.data], {type: typeFile});
															var fileURL = window.URL.createObjectURL(file);
															if(typeFile == 'application/vnd.ms-excel'){
									
															 // a.href = fileURL;
																 a.download = fileName[0];
																$window.open(fileURL)
																 a.click();
											
															}else{
														
																a.href = fileURL;
																a.download = fileName[0];
															 $window.open(fileURL)
																a.click();
											
															}
																
															$scope.traitementEnCoursGenererLivraison="false";
						
															});
				
				
											};
	/** ** Excel  ** */
	$scope.downloadAllProduitsExcel = function(inventaireCourant) {
								
								
		if(inventaireCourant.metrageDu == null)
			 inventaireCourant.metrageDu ='';


		if(inventaireCourant.metrageA == null)
			 inventaireCourant.metrageA ='';


		
							var newdateFormat=null;
								if(angular.isDefined(inventaireCourant.dateEtat)){
									//$log.debug("==dateEtat "+inventaireCourant.dateEtat);
									//$log.debug("==dateEtat AFTER CONVERT "+formattedDate(inventaireCourant.dateEtat));
									
									if(inventaireCourant.dateEtat != null){
									var newdateFormat = formattedDate(inventaireCourant.dateEtat);
									$log.debug("===== newdateFormat "+newdateFormat);
									}else{
										$log.debug("===== newdateFormat is Null");
									}
								}else{
									$log.debug("==dateEtat Undefined");
								}

									  var url = UrlAtelier+"/fichesLogistique/inventaire?client="+inventaireCourant.client+
									  "&nombreColieDu="+""+
									  "&nombreColieA="+""+
									 
								
									  "&metrageDu="+inventaireCourant.metrageDu+
									  "&metrageA="+inventaireCourant.metrageA+
									  "&dateEtat="+newdateFormat+
									  "&designationQuiContient="+inventaireCourant.designationQuiContient+
									  "&referenceProduit="+inventaireCourant.idProduitParRef+
									  "&fini=" +
									  "&orderBy="+inventaireCourant.orderBy+
									  "&typeOf="+inventaireCourant.typeOf+
									  "&type=Excel";



						var a = document.createElement('a');
						document.body.appendChild(a);
						downloadService.download(url).then(function (result) {
							var heasersFileName = result.headers(['content-disposition']).substring(17);
						var fileName = heasersFileName.split('.');
					var typeFile = result.headers(['content-type']);
					var file = new Blob([result.data], {type: typeFile});
					var fileURL = window.URL.createObjectURL(file);
					if(typeFile == 'application/vnd.ms-excel'){

					 // a.href = fileURL;
						 a.download = fileName[0];
						$window.open(fileURL)
						 a.click();
	
					}else{
				
						a.href = fileURL;
						a.download = fileName[0];
					 $window.open(fileURL)
						a.click();
	
					}
						
					$scope.traitementEnCoursGenererLivraison="false";

					});


	};



							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.rechercheInventaireForm
										.$setPristine();
								$scope.inventaireCourant = {
											 "client":'',
											"nombreColieDu":'',
											 "nombreColieA":'',
											 "entrepot":'',
											 "emplacement":'',
											 "metrageDu":'',
											 "metrageA":'',
											 "dateEtat":'',
											 "designationQuiContient":'',
											 "referenceProduit":'',
											 "fini":'',
											 "orderBy":'',
								};
								$scope.displayMode = "list";
							}
							
							/********************************
							 * Gestion des listes deroulantes
							 * à changer par Cache
							 ********************************/
							//listeOrigine
							$scope.listeOrigine = [{id:1,designation:"oui"},{id:2,designation:"non"},{id:3,designation:"tous"}];
          					
							//listeTri
							$scope.listeTri = [{id:1,designation:"reference"},{id:2,designation:"metrage"},{id:3,designation:"prixUnitaire"}];
          					
							// listeEntrepotCache
							$scope.listeEntrepotCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeEntrepotCache").success(
										function(data) {
											$log.debug("listeEntrepotCache "+data.length);
											$scope.listeEntrepotCache = data;

										});
							}
													
							// Liste des PartieInteressee (avec FamilleId=1)
							$scope.listeClientCache = function() {
								$http
										.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeClientCache")
										.success(
												function(dataPartieInteressee) {

													$scope.listeClientCache = dataPartieInteressee;
												});
							}
							
							//Liste des Produits (cache)
							$scope.listeProduitCache = function() {
								$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeProduitCache").success(
										function(data) {
											console.log("listeProduitCache "+data.length);
											$scope.listeProduitCache = data;

										});
							}
							$scope.listeClientCache();
							$scope.listeEntrepotCache();
							$scope.listeProduitCache();
							

				
						} ])
		