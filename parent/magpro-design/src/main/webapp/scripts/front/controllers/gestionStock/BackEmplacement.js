'use strict'

angular
  .module('gpro.backEmplacement', ["ngResource"])
  .controller('backEmplacementController', ['$scope','$filter', '$http','$log','UrlAtelier','UrlCommun', function ($scope,$filter, $http,$log, UrlAtelier, UrlCommun) {
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
	    		$scope.listeEmlacementMagasin = data;
	    	});
	    }
	    $scope.listeEmplacementMagasin();
	    
		//GetId.designation
	  	    $scope.magasin = {
	  	    	    status: ''
	  	    	  }; 
	  	    	  $scope.showStatus = function(id) {
	  	    		$scope.magasin.status= id;
	  	    	    var selected = $filter('filter')($scope.listeEmplacementMagasin, {id: $scope.magasin.status});
	  	    	    return ($scope.magasin.status && selected.length) ? selected[0].designation : 'Not set';
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

					