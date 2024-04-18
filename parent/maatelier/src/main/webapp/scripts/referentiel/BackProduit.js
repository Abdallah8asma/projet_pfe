'use strict'

var app = angular.module('gpro.back-produits', [ "ngResource" ]);
app.controller('BackProduitCtrl2', [ '$scope', function($scope) {
	$scope.parametre = "Famille";
	 
	//Annuler Ajout
    $scope.cancelAdd = function(rowform, index, id, designation, liste){
    	//console.log("* Designation "+liste[0].designation);
    		  if (angular.isDefined(id)) {
    			  console.log("ID "+id);
    			  rowform.$cancel();
    		  }else{
    			  console.log("ID "+id);
    			  if(designation ==""){
    				  console.log("DELETE" +designation);
    				  liste.splice(index, 1);
			    	  rowform.$cancel();
    			  }else{
    				  console.log("NOT DELETE "+designation);
    				  rowform.$cancel();
    			  }
    		}
    }
}]);

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
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.familleCourante = null;
							$scope.listeFamille = [];
							$scope.resultatRecherche = $scope.listeFamille;
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(baseUrl + "/famillelProduit/all")
										.success(function(dataFamille) {
											console.log("dataFamille " + dataFamille);
											$scope.listeFamille = dataFamille;
										});
							}

							// ajout d'une Famille
							$scope.ajouterFamille = function() {
								$scope.familleCourante = {
									designation : '',
								};
								$scope.listeFamille
										.push($scope.familleCourante);

							};

							// Enregistrer famille
							$scope.saveFamille = function(data, id) {
								if (angular.isDefined(id)) {
									console.log("famille existe deja");
									$http
											.post(
													baseUrl
															+ "/famillelProduit/modifierFamilleProduit",
													data)
											.success(function(newfamille) {
												console.log("Success Modification");
												angular.extend(newfamille);
											});
								} else {
									$http
											.post(
													baseUrl
															+ "/famillelProduit/creerFamilleProduit",
													data)
											.success(function(newfamille) {
												console.log("Success Creation");
												angular.extend(newfamille);
											});
								}

							}

							// Suppression d'une Famille
							$scope.removeFamilleProduit = function(index) {
								console.log("INDEX :" + index);
								console.log("**OBJET :" + $scope.listeFamille[index]);
								console.log("DELETE .." + $scope.listeFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/famillelProduit/supprimerFamilleProduit:"+ $scope.listeFamille[index].id
										}).success(function() {
									console.log("Success Delete");
									$scope.listeFamille.splice(index, 1);
								});
								$scope.listeFamille.splice(index, 1);
							}
							$scope.listeFamilleProduit();

						} ]);
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
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.SousFamilleCourante = null;
							$scope.listeSousFamille = [];
							$scope.resultatRecherche = $scope.listeSousFamille;
							
							// Lister SousFamille
							$scope.listeSousFamilleProduit = function() {
								$http.get(baseUrl + "/sousFamilleProduit/all")
										.success(function(dataSousFamille) {
											//console.log("**dataSousFamille " + dataSousFamille);
											$scope.listeSousFamille = dataSousFamille;
										});
							}
							
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(baseUrl + "/famillelProduit/all")
										.success(function(dataFamille) {
											$scope.listeFamille = dataFamille;
										});
							}
							
							// GetId.designation StdTaille
							$scope.type = {

								status : ''
							};

							$scope.showStatusFamille = function(id) {
								$scope.type.status = id;
								var selected = $filter('filter')(
										$scope.listeFamille, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}

							};

							
							// ajout d'une sousFamille
							$scope.ajouterSousFamille = function() {
								$scope.SousFamilleCourante = {
									designation : '',
									familleProduitId:''
								};
								$scope.listeSousFamille
										.push($scope.SousFamilleCourante);

							};

							// Enregistrer SousFamille
							$scope.saveSousFamille = function(data, id) {
								
								if (angular.isDefined(id)) {
									console.log("SousFamille existe deja");
									$http.post(
												baseUrl+ "/sousFamilleProduit/modifierSousFamilleProduit",
													data)
											.success(function(newSousFamille) {
												console.log("Success Modification");
												angular.extend(newSousFamille);
											});
								} else {
									$http.post(
											baseUrl+ "/sousFamilleProduit/creerSousFamilleProduit",
													data)
											.success(function(newSousFamille) {
												console.log("Success Creation");
												angular.extend(newSousFamille);
											});
								}

							}

							// Suppression SousFamille produit
							$scope.removeSousFamilleProduit = function(index) {
								console.log("INDEX :" + index);
								console.log("**OBJET :" + $scope.listeSousFamille[index]);
								console.log("DELETE .." + $scope.listeSousFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/sousFamilleProduit/supprimerSousFamilleProduit:"
													+ $scope.listeSousFamille[index].id
										}).success(function() {
									console.log("Success Delete");
									$scope.listeSousFamille.splice(index, 1);
								});
								$scope.listeSousFamille.splice(index, 1);
							}
							
							$scope.listeFamilleProduit();
							$scope.listeSousFamilleProduit();
						} ]);
