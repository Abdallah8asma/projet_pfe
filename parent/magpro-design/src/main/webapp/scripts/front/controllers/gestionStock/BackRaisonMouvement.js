'use strict'

angular
  .module('gpro.backRaisonMouvement', ["ngResource"])
  .controller('backRaisonMouvementController', ['$scope','$filter', '$http','$log','UrlAtelier','UrlCommun', function ($scope,$filter, $http, $log,UrlAtelier, UrlCommun) {
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

					