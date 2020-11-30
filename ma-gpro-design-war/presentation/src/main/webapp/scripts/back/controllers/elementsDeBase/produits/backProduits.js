'use strict'

var app = angular.module('gpro.back-produits', [ "ngResource" ]);
app.controller('BackProduitCtrl2', [ '$scope', '$log','UrlCommun','UrlAtelier','$http',
	 function($scope,$log, UrlCommun,UrlAtelier,$http) {
	$scope.parametre = "Sous Famille";
	 
	console.log("Back produits / elements de base");
	

    $scope.reloadGs = function () {
    
        $http.get(UrlAtelier + '/gestionnaireCache/reloadGs');
        
        $http.get(UrlCommun + '/gestionnaireCache/reloadReferentiel');

        $http.get(
          UrlAtelier + '/gestionnaireLogistiqueCache/reloadLogistiqueCache');
        
   };
   
   $scope.reloadGs();
   
	//Annuler Ajout
    $scope.cancelAdd = function(rowform, index, id, designation, liste){
    	//$log.debug("* Designation "+liste[0].designation);
    		  if (angular.isDefined(id)) {
    			  $log.debug("ID "+id);
    			  rowform.$cancel();
    		  }else{
    			  $log.debug("ID "+id);
    			  if(designation ==""){
    				  $log.debug("DELETE" +designation);
    				  liste.splice(index, 1);
			    	  rowform.$cancel();
    			  }else{
    				  $log.debug("NOT DELETE "+designation);
    				  rowform.$cancel();
    			  }
    		}
    }
}]);


/*******************************************************************************
 * Gestion des super Familles
 ******************************************************************************/
app
		.controller(
				'backSuperFamilleProduitController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						'UrlAtelier',
						function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.familleCourante = null;
							$scope.listeFamille = [];
							$scope.ListeTaxe = [];
							$scope.resultatRecherche = $scope.listeFamille;
							
						
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(UrlCommun + "/superFamillelProduit/all")
										.success(function(dataFamille) {
											$log.debug("listeFamille " + dataFamille.length);
											$scope.listeFamille = dataFamille;
										});
							}
							
                     	$scope.refresh = function(){
								
								$scope.listeFamilleProduit();
							}
							
							// Liste des Taxes - Marwa 0202108
							$scope.ListeTaxe = function() {
								$http.get(UrlAtelier+"/taxe/getTVA").success(
										function(dataTaxe) {

											$scope.ListeTaxe = dataTaxe;
										});
							}
							
							$scope.ListeTaxe();
							
							$scope.showDesignationTaxe = function(idTaxe){
								
								var element = $scope.ListeTaxe.filter(function(node) {
							        return node.id==idTaxe;
							    });
							//	$scope.produitCourante.tva =  element[0].tva;
								if(element[0]) return   element[0].designation;
								
								
								
							}

							// ajout d'une Famille
							$scope.ajouterFamille = function() {
								$scope.familleCourante = {
									designation : '',
									tva:''
								};
								$scope.listeFamille
										.push($scope.familleCourante);

							};

							// Enregistrer famille
							$scope.saveFamille = function(data, id) {
								if (angular.isDefined(id)) {
									$log.debug("famille existe deja");
									$http
											.post(
													UrlCommun
															+ "/superFamillelProduit/modifierSuperFamilleProduit",
													data)
											.success(function(newfamille) {
												$log.debug("Success Modification");
												angular.extend(newfamille);
											});
								} else {
									$http
											.post(
													UrlCommun
															+ "/superFamillelProduit/creerSuperFamilleProduit",
													data)
											.success(function(newfamille) {
												data.id = newfamille;
												$log.debug("Success Creation");
												angular.extend(newfamille);
									
											});
								}

							}

							// Suppression d'une Famille
							$scope.removeFamilleProduit = function(index) {
								$log.debug("INDEX :" + index);
								$log.debug("**OBJET :" + $scope.listeFamille[index]);
								$log.debug("DELETE .." + $scope.listeFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/superFamillelProduit/supprimerSuperFamilleProduit:"+ $scope.listeFamille[index].id
										}).success(function() {
									$log.debug("Success Delete");
									$scope.listeFamille.splice(index, 1);
								});
								$scope.listeFamille.splice(index, 1);
							}
							$scope.listeFamilleProduit();

						} ]);


/*******************************************************************************
 * Gestion des Familles
 ******************************************************************************/
app
		.controller(
				'backFamilleProduitController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						'UrlAtelier',
						function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.familleCourante = null;
							$scope.listeFamille = [];
							$scope.ListeTaxe = [];
							$scope.resultatRecherche = $scope.listeFamille;
							
							$scope.listeSuperFamille = [];
							
							
							// Lister Famille produit
							$scope.listeSuperFamille = function() {
								$http.get(UrlCommun + "/superFamillelProduit/all")
										.success(function(dataFamille) {
											$log.debug("liste Super Famille " + dataFamille.length);
											$scope.listeSuperFamille = dataFamille;
										});
							}
							$scope.listeSuperFamille();
							
							// GetId.designation StdTaille
							$scope.type = {

								status : ''
							};
							$scope.showStatusFamille = function(id) {
								$scope.type.status = id;
								var selected = $filter('sousFamillefilterBackProduit')(
										$scope.listeSuperFamille, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}

							};
						
							
							
	                    	$scope.superFamilleChanged = function (idFamille,index){
								
								console.log("familleChanged ,data = ",idFamille+" index = ",index);
								
								
								var element = $scope.listeSuperFamille.filter(function(node) {
							        return node.id==idFamille;
							    });
								
								console.log("element = ",element);
								

								
							   $scope.listeSousFamille[index].idTaxe = 	element[0].idTaxe;
							
							
					        	return element[0].idTaxe;
								
								
							}
	                 	
	               
							
						
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(UrlCommun + "/famillelProduit/all")
										.success(function(dataFamille) {
											$log.debug("listeFamille " + dataFamille.length);
											$scope.listeFamille = dataFamille;
										});
							}
							
                     	$scope.refresh = function(){
								
								$scope.listeFamilleProduit();
							}
							
							// Liste des Taxes - Marwa 0202108
							$scope.ListeTaxe = function() {
								$http.get(UrlAtelier+"/taxe/getTVA").success(
										function(dataTaxe) {

											$scope.ListeTaxe = dataTaxe;
										});
							}
							
							$scope.ListeTaxe();
							
							$scope.showDesignationTaxe = function(idTaxe){
								
								var element = $scope.ListeTaxe.filter(function(node) {
							        return node.id==idTaxe;
							    });
							//	$scope.produitCourante.tva =  element[0].tva;
								if(element[0]) return   element[0].designation;
								
								
								
							}
							
	                    $scope.showDesignationSuperFamille = function(idTaxe){
								
								var element = $scope.listeSuperFamille.filter(function(node) {
							        return node.id==idTaxe;
							    });
							//	$scope.produitCourante.tva =  element[0].tva;
								if(element[0]) return   element[0].designation;
								
								
								
							}

							// ajout d'une Famille
							$scope.ajouterFamille = function() {
								$scope.familleCourante = {
									designation : '',
									tva:''
								};
								$scope.listeFamille
										.push($scope.familleCourante);

							};

							// Enregistrer famille
							$scope.saveFamille = function(data, id) {
								if (angular.isDefined(id)) {
									$log.debug("famille existe deja");
									$http
											.post(
													UrlCommun
															+ "/famillelProduit/modifierFamilleProduit",
													data)
											.success(function(newfamille) {
												$log.debug("Success Modification");
												angular.extend(newfamille);
											});
								} else {
									$http
											.post(
													UrlCommun
															+ "/famillelProduit/creerFamilleProduit",
													data)
											.success(function(newfamille) {
												data.id = newfamille;
												$log.debug("Success Creation");
												angular.extend(newfamille);
									
											});
								}

							}

							// Suppression d'une Famille
							$scope.removeFamilleProduit = function(index) {
								$log.debug("INDEX :" + index);
								$log.debug("**OBJET :" + $scope.listeFamille[index]);
								$log.debug("DELETE .." + $scope.listeFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/famillelProduit/supprimerFamilleProduit:"+ $scope.listeFamille[index].id
										}).success(function() {
									$log.debug("Success Delete");
									$scope.listeFamille.splice(index, 1);
								});
								$scope.listeFamille.splice(index, 1);
							}
							$scope.listeFamilleProduit();

						} ])		.filter('sousFamillefilterBackProduit', function() {
							  return function(listeSousFamille, id) {
									 var listeSousFamilleFiltre = [];
									// $log.debug("IdSousFamille=  "+id.id);
									// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
									 angular.forEach(listeSousFamille, function(sousFamille, key){
										
										if(sousFamille.id == id.id){
											//$log.debug(sousFamille.id +" == "+ id.id);
											listeSousFamilleFiltre.push(sousFamille);
										}
											
									 });
									// $log.debug(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
									 return listeSousFamilleFiltre;
								  };
								});;
/*******************************************************************************
 * Gestion des SousFamilles
 ******************************************************************************/
app
		.controller(
				'backSousSousFamilleProduitController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						'UrlAtelier',
						function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.SousFamilleCourante = null;
							$scope.listeSousFamille = [];
							$scope.resultatRecherche = $scope.listeSousFamille;
							
	                        $scope.refresh = function(){
								
								$scope.listeSousFamilleProduit();
							}
							
							$scope.familleChanged = function (idFamille,index){
								
								console.log("familleChanged ,data = ",idFamille+" index = ",index);
								
								
								var element = $scope.listeFamille.filter(function(node) {
							        return node.id==idFamille;
							    });
								
								console.log("element = ",element);
								

								
							$scope.listeSousFamille[index].idTaxe = 	element[0].idTaxe;
							
							
					        	return element[0].idTaxe;
								
								
							}
							
							
							// Liste des Taxes - Marwa 0202108
							$scope.ListeTaxe = function() {
								$http.get(UrlAtelier+"/taxe/getTVA").success(
										function(dataTaxe) {

											$scope.ListeTaxe = dataTaxe;
										});
							}
							
							$scope.ListeTaxe();
							
							$scope.showDesignationTaxe = function(idTaxe){
								
								var element = $scope.ListeTaxe.filter(function(node) {
							        return node.id==idTaxe;
							    });
							//	$scope.produitCourante.tva =  element[0].tva;
								if(element[0]) return   element[0].designation;
								
								
								
							}
							
							// Lister SousFamille
							$scope.listeSousFamilleProduit = function() {
								$http.get(UrlCommun + "/sousFamilleProduit/all")
										.success(function(dataSousFamille) {
											$log.debug("listeSousFamille " + dataSousFamille.length);
											$scope.listeSousFamille = dataSousFamille;
										});
							}
							
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(UrlCommun + "/famillelProduit/all")
										.success(function(dataFamille) {
											$log.debug("listeFamille : "+dataFamille.length);
											$scope.listeFamille = dataFamille;
										});
							}
							
							// GetId.designation StdTaille
							$scope.type = {

								status : ''
							};

							$scope.showStatusFamille = function(id) {
								$scope.type.status = id;
								var selected = $filter('sousFamillefilterBackProduit')(
										$scope.listeFamille, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}

							};
							
							$scope.showStatusFamilleTVA = function(idSousFamille,id) {
								$scope.type.status = id;
								var selected = $filter('sousFamillefilterBackProduit')(
										$scope.listeFamille, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].tva;
								} else {
									return "Not Set";
								}

							};

							
							// ajout d'une sousFamille
							$scope.ajouterSousFamille = function() {
														
								$scope.SousFamilleCourante = {
									designation : '',
									familleProduitId:'',
									tva:''
								};
								$scope.listeSousFamille
										.push($scope.SousFamilleCourante);

							};

							// Enregistrer SousFamille
							$scope.saveSousFamille = function(data, id) {
								
							//	$log.debug("data = ",data);
								
				
								
					/*	      	if (data.tva == ''){
						       		
						       		var idFamille = data.familleProduitId;
						       		
						       	  console.log("idFamille = ",idFamille);
								
							//	console.log("$scope.listeFamille" + $scope.listeFamille);
								var element = $scope.listeFamille.filter(function(node) {
							        return node.id==idFamille;
							    });
								
								data.tva = element[0].tva;
							}
					*/			
								
								if (angular.isDefined(id)) {
									$log.debug("SousFamille existe deja");
									$http.post(
												UrlCommun+ "/sousFamilleProduit/modifierSousFamilleProduit",
													data)
											.success(function(newSousFamille) {
												$log.debug("Success Modification");
												angular.extend(newSousFamille);
											});
								} else {
                          
							
									$http.post(
											UrlCommun+ "/sousFamilleProduit/creerSousFamilleProduit",
											data)
											.success(function(newSousFamille) {
												data.id = newSousFamille;
												$log.debug("Success Creation");
												angular.extend(newSousFamille);
											});
								}

							}

							// Suppression SousFamille produit
							$scope.removeSousFamilleProduit = function(index) {
								$log.debug("INDEX :" + index);
								$log.debug("**OBJET :" + $scope.listeSousFamille[index]);
								$log.debug("DELETE .." + $scope.listeSousFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/sousFamilleProduit/supprimerSousFamilleProduit:"
													+ $scope.listeSousFamille[index].id
										}).success(function() {
									$log.debug("Success Delete");
									$scope.listeSousFamille.splice(index, 1);
								});
								$scope.listeSousFamille.splice(index, 1);
							}
							
							$scope.listeFamilleProduit();
							$scope.listeSousFamilleProduit();
						} ])



						/*******************************************************************************
 * Gestion des Impression produit
 ******************************************************************************/
app
.controller(
		'backImpressionProduitController',
		[
				'$scope',
				'$filter',
				'$http',
				'$log',
				'UrlCommun',
				'UrlAtelier',
				function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
					// Déclaration des variables globales utilisées
					var data;
					$scope.displayMode = "";
					$scope.familleCourante = null;
					$scope.listeFamille = [];
					$scope.ListeTaxe = [];
					$scope.resultatRecherche = $scope.listeFamille;
					
				
					// Lister Famille produit
					$scope.listeFamilleProduit = function() {
						$http.get(UrlCommun + "/impressionProduit/all")
								.success(function(dataFamille) {
									$log.debug("listeFamille " + dataFamille.length);
									$scope.listeFamille = dataFamille;
								});
					}
					
				 $scope.refresh = function(){
						
						$scope.listeFamilleProduit();
					}
					
				
					
				

					// ajout d'une Famille
					$scope.ajouterFamille = function() {
						$scope.familleCourante = {
							designation : '',
							description:''
						};
						$scope.listeFamille
								.push($scope.familleCourante);

					};

					// Enregistrer famille
					$scope.saveFamille = function(data, id) {
						if (angular.isDefined(id)) {
							$log.debug("famille existe deja");
							$http
									.post(
											UrlCommun
													+ "/impressionProduit/modifierImpressionProduit",
											data)
									.success(function(newfamille) {
										$log.debug("Success Modification");
										angular.extend(newfamille);
									});
						} else {
							$http
									.post(
											UrlCommun
													+ "/impressionProduit/creerImpressionProduit",
											data)
									.success(function(newfamille) {
										data.id = newfamille;
										$log.debug("Success Creation");
										angular.extend(newfamille);
							
									});
						}

					}

					// Suppression d'une Famille
					$scope.removeFamilleProduit = function(index) {
						$log.debug("INDEX :" + index);
						$log.debug("**OBJET :" + $scope.listeFamille[index]);
						$log.debug("DELETE .." + $scope.listeFamille[index].id);
						$http(
								{
									method : "DELETE",
									url : UrlCommun
											+ "/impressionProduit/supprimerImpressionProduit:"+ $scope.listeFamille[index].id
								}).success(function() {
							$log.debug("Success Delete");
							$scope.listeFamille.splice(index, 1);
						});
						$scope.listeFamille.splice(index, 1);
					}
					$scope.listeFamilleProduit();

				} ])



				
						/*******************************************************************************
 * Gestion des Option Produit
 ******************************************************************************/
app
.controller(
		'backOptionProduitController',
		[
				'$scope',
				'$filter',
				'$http',
				'$log',
				'UrlCommun',
				'UrlAtelier',
				function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
					// Déclaration des variables globales utilisées
					var data;
					$scope.displayMode = "";
					$scope.familleCourante = null;
					$scope.listeFamille = [];
					$scope.ListeTaxe = [];
					$scope.resultatRecherche = $scope.listeFamille;


	   // Lister Famille produit
	   $scope.listeFamilleOption = function() {

									
		$http.get(UrlCommun + "/utilsEntite/getAllByType:OPTION_PRODUIT")
				.success(function(dataFamille) {
					$log.debug("listeFamille " + dataFamille.length);
					$scope.listeFamilleOption = dataFamille;
				});
	}
	$scope.listeFamilleOption();


	$scope.showDesignationFamilleOption = function(idFamilleOption){
								
		var element = $scope.listeFamilleOption.filter(function(node) {
			return node.id==idFamilleOption;
		});
	//	$scope.produitCourante.tva =  element[0].tva;
		if(element[0]) return   element[0].designation;
		
		
		
	}


						// Lister SousFamille Produit
						$scope.listeSousFamilleProduit = function() {
							$http.get(UrlCommun + "/sousFamilleProduit/all")
									.success(function(dataSousFamille) {
										$log.debug("listeSousFamille " + dataSousFamille.length);
										$scope.listeSousFamille = dataSousFamille;
									});
						}

						$scope.listeSousFamilleProduit();
					
				
					// Lister Famille produit
					$scope.listeFamilleProduit = function() {
						$http.get(UrlCommun + "/optionProduit/all")
								.success(function(dataFamille) {
									$log.debug("listeFamille " + dataFamille.length);
									$scope.listeFamille = dataFamille;
								});
					}
					
				 $scope.refresh = function(){
						
						$scope.listeFamilleProduit();
					}
					
				
					
				

					// ajout d'une Famille
					$scope.ajouterFamille = function() {
						$scope.familleCourante = {
							designation : '',
							description:''
						};
						$scope.listeFamille
								.push($scope.familleCourante);

					};

					// Enregistrer famille
					$scope.saveFamille = function(data, id) {
						if (angular.isDefined(id)) {
							$log.debug("famille existe deja");
							$http
									.post(
											UrlCommun
													+ "/optionProduit/modifierOptionProduit",
											data)
									.success(function(newfamille) {
										$log.debug("Success Modification");
										angular.extend(newfamille);
									});
						} else {
							$http
									.post(
											UrlCommun
													+ "/optionProduit/creerOptionProduit",
											data)
									.success(function(newfamille) {
										data.id = newfamille;
										$log.debug("Success Creation");
										angular.extend(newfamille);
							
									});
						}

					}

					// Suppression d'une Famille
					$scope.removeFamilleProduit = function(index) {
						$log.debug("INDEX :" + index);
						$log.debug("**OBJET :" + $scope.listeFamille[index]);
						$log.debug("DELETE .." + $scope.listeFamille[index].id);
						$http(
								{
									method : "DELETE",
									url : UrlCommun
											+ "/optionProduit/supprimerOptionProduit:"+ $scope.listeFamille[index].id
								}).success(function() {
							$log.debug("Success Delete");
							$scope.listeFamille.splice(index, 1);
						});
						$scope.listeFamille.splice(index, 1);
					}
					$scope.listeFamilleProduit();

				} ])


				
						/*******************************************************************************
                                   * Gestion des Operation Produit
 ******************************************************************************/
app
.controller(
		'backOperationProduitController',
		[
				'$scope',
				'$filter',
				'$http',
				'$log',
				'UrlCommun',
				'UrlAtelier',
				function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
					// Déclaration des variables globales utilisées
					var data;
					$scope.displayMode = "";
					$scope.familleCourante = null;
					$scope.listeFamille = [];
					$scope.ListeTaxe = [];
					$scope.resultatRecherche = $scope.listeFamille;





						// Lister SousFamille Produit
						$scope.listeSousFamilleProduit = function() {
							$http.get(UrlCommun + "/sousFamilleProduit/all")
									.success(function(dataSousFamille) {
										$log.debug("listeSousFamille " + dataSousFamille.length);
										$scope.listeSousFamille = dataSousFamille;
									});
						}

						$scope.listeSousFamilleProduit();
					
				
					// Lister Famille produit
					$scope.listeFamilleProduit = function() {
						$http.get(UrlCommun + "/operationProduit/all")
								.success(function(dataFamille) {
									$log.debug("listeFamille " + dataFamille.length);
									$scope.listeFamille = dataFamille;
								});
					}
					
				 $scope.refresh = function(){
						
						$scope.listeFamilleProduit();
					}
					
				
					
				

					// ajout d'une Famille
					$scope.ajouterFamille = function() {
						$scope.familleCourante = {
							designation : '',
							description:''
						};
						$scope.listeFamille
								.push($scope.familleCourante);

					};

					// Enregistrer famille
					$scope.saveFamille = function(data, id) {
						if (angular.isDefined(id)) {
							$log.debug("famille existe deja");
							$http
									.post(
											UrlCommun
													+ "/operationProduit/modifierOperationProduit",
											data)
									.success(function(newfamille) {
										$log.debug("Success Modification");
										angular.extend(newfamille);
									});
						} else {
							$http
									.post(
											UrlCommun
													+ "/operationProduit/creerOperationProduit",
											data)
									.success(function(newfamille) {
										data.id = newfamille;
										$log.debug("Success Creation");
										angular.extend(newfamille);
							
									});
						}

					}

					// Suppression d'une Famille
					$scope.removeFamilleProduit = function(index) {
						$log.debug("INDEX :" + index);
						$log.debug("**OBJET :" + $scope.listeFamille[index]);
						$log.debug("DELETE .." + $scope.listeFamille[index].id);
						$http(
								{
									method : "DELETE",
									url : UrlCommun
											+ "/operationProduit/supprimerOperationProduit:"+ $scope.listeFamille[index].id
								}).success(function() {
							$log.debug("Success Delete");
							$scope.listeFamille.splice(index, 1);
						});
						$scope.listeFamille.splice(index, 1);
					}
					$scope.listeFamilleProduit();

				} ])






         /*
				* Gestion des compte comptable
				******************************************************************************/
			   app
			   .controller(
					   'backCompteComptableController',
					   [
							   '$scope',
							   '$filter',
							   '$http',
							   '$log',
							   'UrlCommun',
							   'UrlAtelier',
							   function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
								   // Déclaration des variables globales utilisées
								   var data;
								   $scope.displayMode = "";
								   $scope.familleCourante = null;
								   $scope.listeFamille = [];
								   $scope.ListeTaxe = [];
								   $scope.resultatRecherche = $scope.listeFamille;
								   
							   
								   // Lister Famille produit
								   $scope.listeFamilleProduit = function() {
									   $http.get(UrlCommun + "/compteComptable/all")
											   .success(function(dataFamille) {
												   $log.debug("listeFamille " + dataFamille.length);
												   $scope.listeFamille = dataFamille;
											   });
								   }
								   
								$scope.refresh = function(){
									   
									   $scope.listeFamilleProduit();
								   }
								   
							   
								   
							   
			   
								   // ajout d'une Famille
								   $scope.ajouterFamille = function() {
									   $scope.familleCourante = {
										   designation : '',
										   description:''
									   };
									   $scope.listeFamille
											   .push($scope.familleCourante);
			   
								   };
			   
								   // Enregistrer famille
								   $scope.saveFamille = function(data, id) {
									   if (angular.isDefined(id)) {
										   $log.debug("famille existe deja");
										   $http
												   .post(
														   UrlCommun
																   + "/compteComptable/modifierCompteComptable",
														   data)
												   .success(function(newfamille) {
													   $log.debug("Success Modification");
													   angular.extend(newfamille);
												   });
									   } else {
										   $http
												   .post(
														   UrlCommun
																   + "/compteComptable/creerCompteComptable",
														   data)
												   .success(function(newfamille) {
													   data.id = newfamille;
													   $log.debug("Success Creation");
													   angular.extend(newfamille);
										   
												   });
									   }
			   
								   }
			   
								   // Suppression d'une Famille
								   $scope.removeFamilleProduit = function(index) {
									   $log.debug("INDEX :" + index);
									   $log.debug("**OBJET :" + $scope.listeFamille[index]);
									   $log.debug("DELETE .." + $scope.listeFamille[index].id);
									   $http(
											   {
												   method : "DELETE",
												   url : UrlCommun
														   + "/compteComptable/supprimerCompteComptable:"+ $scope.listeFamille[index].id
											   }).success(function() {
										   $log.debug("Success Delete");
										   $scope.listeFamille.splice(index, 1);
									   });
									   $scope.listeFamille.splice(index, 1);
								   }
								   $scope.listeFamilleProduit();
			   
							   } ])
			   






							   

         /*
				* Gestion des familles des options 
				******************************************************************************/
			   app
			   .controller(
					   'backFamilleOptionController',
					   [
							   '$scope',
							   '$filter',
							   '$http',
							   '$log',
							   'UrlCommun',
							   'UrlAtelier',
							   function($scope, $filter, $http, $log, UrlCommun,UrlAtelier) {
								   // Déclaration des variables globales utilisées
								   var data;
								   $scope.displayMode = "";
								   $scope.familleCourante = null;
								   $scope.listeFamille = [];
								   $scope.ListeTaxe = [];
								   $scope.resultatRecherche = $scope.listeFamille;
								   
							   
								   // Lister Famille produit
								   $scope.listeFamilleProduit = function() {

									
									   $http.get(UrlCommun + "/utilsEntite/getAllByType:OPTION_PRODUIT")
											   .success(function(dataFamille) {
												   $log.debug("listeFamille " + dataFamille.length);
												   $scope.listeFamille = dataFamille;
											   });
								   }
								   
								$scope.refresh = function(){
									   
									   $scope.listeFamilleProduit();
								   }
								   
							   
								   
							   
			   
								   // ajout d'une Famille
								   $scope.ajouterFamille = function() {
									   $scope.familleCourante = {
										   designation : '',
										   description:'',
										   type:'OPTION_PRODUIT'
									   };
									   $scope.listeFamille
											   .push($scope.familleCourante);
			   
								   };
			   
								   // Enregistrer famille
								   $scope.saveFamille = function(data, id) {
									data.type = 'OPTION_PRODUIT';

									   if (angular.isDefined(id)) {
										   $log.debug("famille existe deja");

										  
										   $http
												   .post(
														   UrlCommun
																   + "/utilsEntite/modifierUtils",
														   data)
												   .success(function(newfamille) {
													   $log.debug("Success Modification");
													   angular.extend(newfamille);
												   });
									   } else {
										   $http
												   .post(
														   UrlCommun
																   + "/utilsEntite/creerUtils",
														   data)
												   .success(function(newfamille) {
													   data.id = newfamille;
													   $log.debug("Success Creation");
													   angular.extend(newfamille);
										   
												   });
									   }
			   
								   }
			   
								   // Suppression d'une Famille
								   $scope.removeFamilleProduit = function(index) {
									   $log.debug("INDEX :" + index);
									   $log.debug("**OBJET :" + $scope.listeFamille[index]);
									   $log.debug("DELETE .." + $scope.listeFamille[index].id);
									   $http(
											   {
												   method : "DELETE",
												   url : UrlCommun
														   + "/utilsEntite/supprimerUtils:"+ $scope.listeFamille[index].id
											   }).success(function() {
										   $log.debug("Success Delete");
										   $scope.listeFamille.splice(index, 1);
									   });
									   $scope.listeFamille.splice(index, 1);
								   }
								   $scope.listeFamilleProduit();
			   
							   } ])
						
			.filter('sousFamillefilterBackProduit', function() {
				  return function(listeSousFamille, id) {
					 var listeSousFamilleFiltre = [];
					// $log.debug("IdSousFamille=  "+id.id);
					// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
					 angular.forEach(listeSousFamille, function(sousFamille, key){
						
						if(sousFamille.id == id.id){
							//$log.debug(sousFamille.id +" == "+ id.id);
							listeSousFamilleFiltre.push(sousFamille);
						}
							
					 });
					// $log.debug(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
					 return listeSousFamilleFiltre;
				  };
				});
