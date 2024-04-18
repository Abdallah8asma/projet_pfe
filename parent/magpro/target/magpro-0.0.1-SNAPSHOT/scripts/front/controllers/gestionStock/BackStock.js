'use strict'
angular.module('gpro.back', [ "ngResource" ])
.controller('backStockCtrl',[ '$scope', '$filter', '$http','$log', 'UrlAtelier','UrlCommun',
	function($scope, $filter, $http,$log, UrlAtelier,UrlCommun) {
			/** ***Liste desVariables **** */
			$scope.parametre = "Magasin";
	}])
	.controller('backMagasinController', ['$scope','$filter', '$http','UrlAtelier','UrlCommun', function ($scope,$filter, $http,UrlAtelier, UrlCommun) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.magasinCourante = null;
	    $scope.listeMagasin=[];
		$scope.listeSites=[];
	    $scope.resultatRecherche=$scope.listeMagasin;
	    /********************
	     * Gestion de la  magasin Produit
	     *********************************/
	    $scope.siteChoisi = [];
	    $scope.addSite = function(index){
	    	  $scope.listeMagasin[index].piComSiteId = $scope.siteChoisi[$index].piComSiteId;
	    }
	    //Lister magasin  
	    $scope.listeMagasin = function () {
	    	$http.get(UrlAtelier+"/magasin/all").success(function (data) {
	    		$scope.listeMagasin = data;
	    	});
	    }
	     $scope.listeMagasin();
	     //Lister sites  
		    $scope.listeSites = function () {
		    	$http.get(UrlCommun+"/gestionnaireCache/listeSitePartieInteresseeCache").success(function (data) {
		    		$scope.listeSites = data;
		    	});
		    }
		     $scope.listeMagasin();
		     $scope.listeSites();
		     
				// GetId.designation
				$scope.type = {

					status : ''
				};

				$scope.showStatus = function(id) {

					$scope.type.status = id;
					var selected = $filter('filter')(
							$scope.listeSites, {
								id : $scope.type.status
							});

					if ($scope.type.status && selected.length) {
						return selected[0].nom;
					} else {
						return "Not Set";
					}
				};
	     
	     // ajout d'un couleur
		    $scope.ajouterMagasin = function() {
		      $scope.magasinCourante = {
		        designation: '',
		      };
		      $scope.listeMagasin.push($scope.magasinCourante);
		      
		    };
		   
	
	   //Enregistrer Magasin
	  $scope.saveMagasin = function(data, id) {
		  if (angular.isDefined(id)) {
			     $log.debug(data);
			     $http.post(UrlAtelier + "/magasin/modifierMagasin", data)
			     .success(function (newMagasin) {
			    	angular.extend(newMagasin);
			     });
	        } else {
	        	$log.debug(data);
			     $http.post(UrlAtelier + "/magasin/creerMagasin", data)
			     .success(function (newMagasin) {
				    	angular.extend(newMagasin);
			     });
	        }
		  }
	   // Suppression Magasin produit
	    $scope.removeMagasinProduit = function (id) {
	    	 alert(""+id);
        	$http({
 	    		method: "DELETE",
 	    		url: UrlAtelier + "/magasin/supprimerMagasin:"+id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
	    }

   }
  ])
   .controller('backEmplacementController', ['$scope','$filter', '$http','UrlAtelier', function ($scope,$filter, $http, UrlAtelier) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.emplacementCourante = null;
	    $scope.listeEmplacement=[]; 
	    $scope.listeEmplacementMagasin=[]; 
	    $scope.resultatRecherche=$scope.listeEmplacement;
	    /********************
	     * Gestion de la  emplacement 
	     *********************************/
	    
	    //Lister emplacement  
	    $scope.listeEmplacement = function () {
	    	$http.get(UrlAtelier+"/emplacement/all").success(function (dataMagasin) {
	    		$scope.listeEmplacement = dataMagasin;
	    	});
	    }
	    $scope.listeEmplacement();
	  //Lister magasin  
	    $scope.listeEmplacementMagasin = function () {
	    	$http.get(UrlAtelier+"/magasin/all").success(function (data) {
	    		$scope.listeEmplacementMagasin = data;
	    	});
	    }
	    $scope.listeEmplacementMagasin();
	    
		//GetId.designation
	  	    $scope.magasin = {
	  	    	    status: ''
	  	    	  }; 
	  	    	$scope.showStatus = function(id) {
					$scope.magasin.status = id;
					var selected = $filter('filter')(
							$scope.listeEmplacementMagasin, {
								id : $scope.magasin.status
							});
					if ($scope.magasin.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				};
	 
	  	    	  
	     // ajout d'un emlacement
		    $scope.ajouterEmplacement = function() {
		      $scope.emplacementCourante = {
		        designation: '',
		        magasin :'',
		      };
		      $scope.listeEmplacement.push($scope.emplacementCourante);
		      
		    };
		   
	
	   //Enregistrer emplacement
	  $scope.saveEmplacement = function(data, id) {
		  if (angular.isDefined(id)) {
			     $log.debug(data);
			     $log.debug("id  modifier "+id);
			     $http.post(UrlAtelier + "/emplacement/modifierEmplacement", data)
			     .success(function (newEmplacement) {
			    	angular.extend(newEmplacement);
			     });
	        } else {
	        	 $log.debug("id ajouter "+id);
	        	 $log.debug(data);
			     $http.post(UrlAtelier + "/emplacement/creerEmplacement", data)
			     .success(function (newEmplacement) {
				    	angular.extend(newEmplacement);
			     });
	        }
		     
		  }
		    
	  
	   // Suppression emplacement 
	    $scope.removeEmplacement = function (id) {
        	$http({
 	    		method: "DELETE",
 	    		url: UrlAtelier + "/emplacement/supprimerEmplacement:"+$scope.listeEmplacement[id].id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
        	$scope.listeEmplacement.splice(id, 1);

	    }
   	 $scope.listeEmplacement();
   }
  ])
   .controller('backEmplacementController', ['$scope','$filter', '$http','UrlAtelier', function ($scope,$filter, $http, UrlAtelier) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.emplacementCourante = null;
	    $scope.listeEmplacement=[]; 
	    $scope.listeEmplacementMagasin=[]; 
	    $scope.resultatRecherche=$scope.listeEmplacement;
	    /********************
	     * Gestion de la  emplacement 
	     *********************************/
	    
	    //Lister emplacement  
	    $scope.listeEmplacement = function () {
	    	$http.get(UrlAtelier+"/emplacement/all").success(function (dataMagasin) {
	    		$scope.listeEmplacement = dataMagasin;
	    	});
	    }
	    $scope.listeEmplacement();
	  //Lister magasin  
	    $scope.listeEmplacementMagasin = function () {
	    	$http.get(UrlAtelier+"/magasin/all").success(function (data) {
	    		$scope.listeEmplacementMagasin = data;
	    	});
	    }
	    $scope.listeEmplacementMagasin();
	    
		//GetId.designation
	  	    $scope.magasin = {
	  	    	    status: ''
	  	    	  }; 
	  	    	$scope.showStatus = function(id) {
					$scope.magasin.status = id;
					var selected = $filter('filter')(
							$scope.listeEmplacementMagasin, {
								id : $scope.magasin.status
							});
					if ($scope.magasin.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				};
	 
	  	    	  
	     // ajout d'un emlacement
		    $scope.ajouterEmplacement = function() {
		      $scope.emplacementCourante = {
		        designation: '',
		        magasin :'',
		      };
		      $scope.listeEmplacement.push($scope.emplacementCourante);
		      
		    };
		   
	
	   //Enregistrer emplacement
	  $scope.saveEmplacement = function(data, id) {
		  if (angular.isDefined(id)) {
			     $log.debug(data);
			     $log.debug("id  modifier "+id);
			     $http.post(UrlAtelier + "/emplacement/modifierEmplacement", data)
			     .success(function (newEmplacement) {
			    	angular.extend(newEmplacement);
			     });
	        } else {
	        	 $log.debug("id ajouter "+id);
	        	 $log.debug(data);
			     $http.post(UrlAtelier + "/emplacement/creerEmplacement", data)
			     .success(function (newEmplacement) {
				    	angular.extend(newEmplacement);
			     });
	        }
		     
		  }
		    
	  
	   // Suppression emplacement 
	    $scope.removeEmplacement = function (id) {
        	$http({
 	    		method: "DELETE",
 	    		url: UrlAtelier + "/emplacement/supprimerEmplacement:"+$scope.listeEmplacement[id].id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
        	$scope.listeEmplacement.splice(id, 1);

	    }
   	 $scope.listeEmplacement();
   }
  ])
   .controller('backRaisonMouvementController', ['$scope','$filter', '$http','UrlAtelier', function ($scope,$filter, $http, UrlAtelier) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.raisonCourante = null;
	    $scope.listeRaison=[]; 
	    $scope.resultatRecherche=$scope.listeRaison;
	    /********************
	     * Gestion de la  Raison 
	     *********************************/
	    
	    //Lister Raison  
	    $scope.listeRaison = function () {
	    	$http.get(UrlAtelier+"/raisonMouvement/all").success(function (data) {
	    		$scope.listeRaison = data;
	    	});
	    }
	     $scope.listeRaison();
	     
	     // ajout d'un couleur
		    $scope.ajouterRaison = function() {
		      $scope.raisonCourante = {
		        designation: '',
		      };
		      $scope.listeRaison.push($scope.raisonCourante);
		      
		    };
		   
	
	   //Enregistrer Raison
	  $scope.saveRaison = function(data, id) {
		  if (angular.isDefined(id)) {
			  alert("Raison existe deja");
			     $log.debug(data);
			     $http.post(UrlAtelier + "/raisonMouvement/modifierRaisonMouvement", data)
			     .success(function (newRaison) {
			    	angular.extend(newRaison);
			     });
	        } else {
	        	$log.debug(data);
			     $http.post(UrlAtelier + "/raisonMouvement/creerRaisonMouvement", data)
			     .success(function (newRaison) {
				    	angular.extend(newRaison);
			     });
	        }
		     
		  }
		    
	  
	   // Suppression Raison 
	    $scope.removeRaison = function (id) {
	    	 alert(""+id);
        	$http({
 	    		method: "DELETE",
 	    		url: UrlAtelier + "/raisonMouvement/supprimerRaisonMouvement:"+id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
	    }

   }
  ])
