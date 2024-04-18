'use strict'

var app = angular.module('gpro.back-partieInteressee', ["ngResource"]);

	app.controller('BackPartieInteresseeCtrl',['$scope',function($scope){
		// Déclaration des variables globales utilisées

		/** ***Liste desVariables **** */
		
		$scope.displayMenu = "pi";
		$scope.parametre = "Famille";
		/***************************************************
		 * Gestion de la Menu PI
		 **************************************************/
		$scope.changeTaPartieInteresse = function(){
			$scope.displayMenu = "pi";
		}
		$scope.changeTaElementBase = function(){
			$scope.displayMenu = "eb";
		}
		$scope.changeTaGestionCommerciale = function(){
			$scope.displayMenu = "gc";
		}
		
		  //Annuler Ajout
	    $scope.cancelAdd = function(rowform, index, id, designation, liste){
	    	console.log("* Designation "+liste[0].designation);
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
	 /*****************************
     * Gestion des familles 
     ****************************/
	  app.controller('backFamillePIController', ['$scope','$filter', '$http','baseUrl', function ($scope,$filter, $http, baseUrl) {
			//Déclaration des variables globales utilisées
				var data;
			  	$scope.displayMode = "";
			    $scope.familleCourante = null;
			    $scope.listeFamille=[]; 
			    $scope.resultatRecherche=$scope.listeFamille;
			    /********************
			     * Gestion de la  famille Produit
			     *********************************/
			    //Lister Famille  produit
			    $scope.listeFamilleProduit = function () {
			    	$http.get(baseUrl+"/famillePI/all").success(function (data) {
			    		$scope.listeFamille = data;
			    	});
			    }
			  
			     // ajout d'un Famille
				    $scope.ajouterFamille = function() {
				      $scope.familleCourante = {
				        designation: ''
				      };
				    
				     // console.log("* $scope.familleCourante "+ $scope.familleCourante);
				      $scope.listeFamille.push($scope.familleCourante);
				    };
				    
				  
			   //Enregistrer famille
			  $scope.saveFamille = function(data, id) {
				  if (angular.isDefined(id)) {
					     $http.post(baseUrl + "/famillePI/modifierFamillePI", data)
					     .success(function (newfamille) {
					    	 console.log("Success Modification");
					    	angular.extend(newfamille);
					     });
			        } else {
					     $http.post(baseUrl + "/famillePI/creerFamillePI", data)
					     .success(function (newfamille) {
					    	 console.log("Success Creation");
						    	angular.extend(newfamille);
					     });
			        }
				     
				  }
			   // Suppression famille produit
			    $scope.removeFamilleProduit = function (index) {
			    	console.log("INDEX :" + index);
					console.log("**OBJET :" + $scope.listeFamille[index]);
					console.log("DELETE .." + $scope.listeFamille	[index].id);
		        	$http({
		 	    		method: "DELETE",
		 	    		url: baseUrl + "/famillePI/supprimerFamillePI:"+$scope.listeFamille[index].id
		 	    		}).success(function () {
						console.log("Success Delete");
						$scope.listeFamille.splice(index, 1);
					});
		        	$scope.listeFamille.splice(index, 1);
			    }
			    $scope.listeFamilleProduit();
		   }
		  ]);
	  /*****************************
	     * Gestion des Categories 
	     ****************************/
	  app.controller('backCathegoriePIController', ['$scope','$filter', '$http','baseUrl', function ($scope,$filter, $http, baseUrl) {
		  var data;
			  	$scope.displayMode = "";
			    $scope.cathegorieCourante = null;
			    $scope.listeCathegorie=[]; 
			    $scope.resultatRecherche=$scope.listeCathegorie;
			    /********************
			     * Gestion des cateegories 
			     *********************************/
			    //Lister Cathegorie  partie interessee
			    $scope.listeCathegoriePI = function () {
			    	$http.get(baseUrl+"/cathegoriePI/all").success(function (data) {
			    		$scope.listeCathegorie = data;
			    	});
			    }
			   
			     // ajout d'une cateegories
				    $scope.ajouterCathegorie = function() {
				    	 $scope.CategorieCourante = {
							        designation: ''
							      };
				      console.log("* $scope.CategorieCourante "+ $scope.CategorieCourante);
				      $scope.listeCathegorie.push($scope.CategorieCourante);
				    };
			   //Enregistrer cateegories
			  $scope.saveCathegorie = function(data, id) {
				  console.log("Categorie "+data);
				  if (angular.isDefined(id)) {
					     $http.post(baseUrl + "/cathegoriePI/modifierCathegoriePI", data)
					     .success(function (newcathegorie) {
					    	 console.log("Success Modification");
					    	angular.extend(newcathegorie);
					     });
			        } else {
			        	console.log(data);
					     $http.post(baseUrl + "/cathegoriePI/creerCathegoriePI", data)
					     .success(function (newcathegorie) {
					    	 console.log("Success Creation");
						    	angular.extend(newcathegorie);
					     });
			        }
				  }
			   // Suppression famille produit
			    $scope.removecathegorie = function (index) {
			    	console.log("INDEX :" + index);
					console.log("**OBJET :" + $scope.listeCathegorie[index]);
					console.log("DELETE .." + $scope.listeCathegorie[index].id);

			    		$http({
			 	    		method: "DELETE",
			 	    		url: baseUrl + "/cathegoriePI/supprimerCathegoriePI:"+ $scope.listeCathegorie[index].id
			 	    		}).success(function () {
			 	    			 console.log("Success Delete");
			 	    			$scope.listeCathegorie.splice(index, 1);
			 	    			
			 	    	});
			    		$scope.listeCathegorie.splice(index, 1);
			    }
			    $scope.listeCathegoriePI();
		   }
		  ]);
	  /*****************************
	     * Gestion des Types 
	     ****************************/
	  app.controller('backTypePIController', ['$scope','$filter', '$http','baseUrl', function ($scope,$filter, $http, baseUrl) {
			//Déclaration des variables globales utilisées
				var data;
			  	$scope.displayMode = "";
			    $scope.TypePiCourante = null;
			    $scope.listeType=[]; 
			    $scope.resultatRecherche=$scope.listeType;
			    /********************
			     * Gestion de TypePI
			     *********************************/
			    
			    //Lister Type  PI
			    $scope.listeTypePI = function () {
			    	$http.get(baseUrl+"/typePartieIntersse/all").success(function (data) {
			    		$scope.listeType = data;
			    	});
			    }
			     $scope.listeTypePI();
			     // ajout d'un Type
				    $scope.ajouterTypePI = function() {
				      $scope.TypePiCourante = {
				        designation: ''
				      };
				      $scope.listeType.push($scope.TypePiCourante);
				      
				    };
			   //Enregistrer Type
			  $scope.saveTypePI = function(data, id) {
				  if (angular.isDefined(id)) {
					     $http.post(baseUrl + "/typePartieIntersse/modifierType", data)
					     .success(function (newtype) {
					    	 console.log("Success Modification");
					    	angular.extend(newtype);
					     });
					     $scope.listeTypePI();
					     
			        } else {
			        	console.log(data);
					     $http.post(baseUrl + "/typePartieIntersse/createType", data)
					     .success(function (newtype) {
					    	 console.log("Success Ctreation");
						    	angular.extend(newtype);
					     });
					     $scope.listeTypePI();
			        }
				  
				  }
				    
			  
			   // Suppression Type
			    $scope.removeTypePI = function (index) {
			    	console.log("INDEX :" + index);
					console.log("**OBJET :" + $scope.listeType[index]);
					console.log("DELETE .." + $scope.listeType[index].id);

		        	$http({
		 	    		method: "DELETE",
		 	    		url: baseUrl + "/typePartieIntersse/deleteType:"+ $scope.listeType[index].id
		 	    		}).success(function () {
		 	    			console.log("Success Delete");
		 	    			$scope.listeType.splice(index, 1);
		 	    	});
 	    			
			    }
			    $scope.listeTypePI();
		   }
		  ]);
	  /*****************************
	     * Gestion de Devise 
	     ****************************/
	  app.controller('backDeviseController', ['$scope','$filter', '$http','baseUrl', function ($scope,$filter, $http, baseUrl) {
			//Déclaration des variables globales utilisées
				var data;
			  	$scope.displayMode = "";
			    $scope.DeviseCourante = null;
			    $scope.listeDevise=[]; 
			    $scope.resultatRecherche=$scope.listeDevise;
			    /********************
			     * Gestion de Devise
			     *********************************/
			    
			    //Lister Devise
			    $scope.listeDeviseFct = function () {
			    	$http.get(baseUrl+"/devise/all").success(function (dataDevise) {
			    		$scope.listeDevise = dataDevise;
			    	});
			    }
			    
			     
			     // ajout d'une Devise
				    $scope.ajouterDevise = function() {
				      $scope.DeviseCourante = {
				        designation: ''
				      };
				      $scope.listeDevise.push($scope.DeviseCourante);
				      
				    };
			   //Enregistrer Devise
			  $scope.saveDevise = function(devise, id) {
				  if (angular.isDefined(id)) {
					     $http.post(baseUrl + "/devise/modifierDeviseProduit", devise)
					     .success(function (newDevise) {
					    	angular.extend(newDevise);
					     });
					  //   $scope.listeDevise();
					     
			        } else {
			        	console.log(devise);
					     $http.post(baseUrl + "/devise/creerDeviseProduit", devise)
					     .success(function (newDevise) {
						    	angular.extend(newDevise);
					     });
					   //  $scope.listeDevise();
			        }
				  }
			   // Suppression Devise
			    $scope.removeDevise = function (index) {
			    	console.log("INDEX :" + index);
					console.log("**OBJET :" + $scope.listeDevise[index]);
					console.log("DELETE .." + $scope.listeDevise[index].id);

		        	$http({
		 	    		method: "DELETE",
		 	    		url: baseUrl + "/devise/supprimerDeviseProduit:"+ $scope.listeDevise[index].id
		 	    		}).success(function () {
		 	    			console.log("DELETE DEVISE");
		 	    			$scope.listeDevise.splice(index, 1);
		 	    	});
		        	$scope.listeDevise.splice(index, 1);
			    }
			    
			    $scope.listeDeviseFct();
		   }
		  ]);

	  /*******************************************************************************
	   * Gestion des TypeDoc
	   ******************************************************************************/
	  app.controller('backTypeDocController', [
	  		'$scope',
	  		'$filter',
	  		'$http',
	  		'baseUrl',
	  		function($scope, $filter, $http, baseUrl) {

	  			/** ***Liste desVariables ***** */
	  			$scope.listeTypeDoc = [];

	  			// Lister la TypeDocs des fils
	  			$scope.listeTypeDoc = function() {
	  				$http.get(baseUrl + "/typeDocument/all").success(
	  						function(dataTypeDoc) {
	  							console.log("dataTypeDoc" + dataTypeDoc);
	  							$scope.listeTypeDoc = dataTypeDoc;

	  						});
	  			}

	  			// ajout d'un TypeDoc
	  			$scope.ajoutTypeDoc = function() {
	  				$scope.TypeDocInserree = {

	  					designation : ''

	  				};
	  				$scope.listeTypeDoc.push($scope.TypeDocInserree);

	  			};

	  			// Enregistrer Representant
	  			$scope.saveTypeDoc = function(dataTypeDoc, id) {

	  				if ((angular.isDefined(dataTypeDoc.id))) {
	  					console.log("TypeDoc Existe deja ");

	  					$http.post(baseUrl + "/typeDocument/modifierTypeDocument",
	  							dataTypeDoc).success(function(TypeDocModifiee) {
	  						console.log("Success Modification");
	  						angular.extend(TypeDocModifiee);
	  					});
	  				} else {

	  						$http.post(baseUrl + "/typeDocument/createTypeDocument",
	  								dataTypeDoc).success(function(newTypeDoc) {
	  							console.log("Success Creation");
	  							angular.extend(newTypeDoc);

	  						});
	  					
	  				}

	  			};

	  			// Supprimer TypeDoc
	  			$scope.removeTypeDoc = function(index) {

	  				console.log("INDEX :" + index);
	  				console.log("**OBJET :" + $scope.listeTypeDoc[index]);
	  				console.log("DELETE .." + $scope.listeTypeDoc[index].id);
	  				$http(
	  						{
	  							method : "DELETE",
	  							url : baseUrl + "/typeDocument/deleteTypeDocument:"
	  									+ $scope.listeTypeDoc[index].id
	  						}).success(function() {
	  					console.log("Success Delete");
	  					 $scope.listeTypeDoc.splice(index, 1);
	  				});
	  			};

	  			$scope.listeTypeDoc();
	  			/** Fin de gestion des TypeDoc */

	  		} ]);

	  /*****************************
	     * Gestion des Sites 
	     ****************************/
		  app.controller('backSitePIController', ['$scope','$filter', '$http','baseUrl', function ($scope,$filter, $http, baseUrl) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "";
				    $scope.siteCourante = null;
				    $scope.listeSite=[]; 
				    $scope.resultatRecherche=$scope.listeSite;
				    /********************
				     * Gestion de la  Site Produit
				     *********************************/
				    //Lister Site  produit
				    $scope.listeSite = function () {
				    	$http.get(baseUrl+"/sitePartieIntersse/all").success(function (data) {
				    		$scope.listeSite = data;
				    	});
				    }
				  
				     // ajout d'un Site
					    $scope.ajouterSite = function() {
					      $scope.siteCourante = {
					        designation: ''
					      };
					    
					      $scope.listeSite.push($scope.siteCourante);
					    };
					    
					  
				   //Enregistrer Site
				  $scope.saveSite = function(data, id) {
					  if (angular.isDefined(id)) {
						     $http.post(baseUrl + "/sitePartieIntersse/modifierSite", data)
						     .success(function (newSite) {
						    	 console.log("Success Modification");
						    	angular.extend(newSite);
						     });
				        } else {
						     $http.post(baseUrl + "/sitePartieIntersse/createSite", data)
						     .success(function (newSite) {
						    	 console.log("Success Creation");
							    	angular.extend(newSite);
						     });
				        }
					     
					  }
				   // Suppression Site produit
				    $scope.removeSite = function (index) {
				    	console.log("INDEX :" + index);
						console.log("**OBJET :" + $scope.listeSite[index]);
						console.log("DELETE .." + $scope.listeSite	[index].id);
			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrl + "/sitePartieIntersse/deleteSite:"+$scope.listeSite[index].id
			 	    		}).success(function () {
							console.log("Success Delete");
							$scope.listeSite.splice(index, 1);
						});
			        	$scope.listeSite.splice(index, 1);
				    }
				    $scope.listeSite();
			   }
			  ]);