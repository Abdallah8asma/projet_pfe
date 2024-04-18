'use strict'

angular
  .module('gpro.backMagasin', ["ngResource"])
  .controller('backMagasinController', ['$scope','$filter', '$http','UrlAtelier','UrlCommun', function ($scope,$filter, $http, UrlAtelier, UrlCommun) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.magasinCourante = null;
	    $scope.listeMagasin=[]; 
	    $scope.resultatRecherche=$scope.listeMagasin;
	    /********************
	     * Gestion de la  magasin Produit
	     *********************************/
	    
	    //Lister magasin  
	    $scope.listeMagasin = function () {
	    	$http.get(UrlAtelier+"/magasin/all").success(function (data) {
	    		$scope.listeMagasin = data;
	    	});
	    }
	     $scope.listeMagasin();
	     
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
			  alert("Magasin existe deja");
			     console.log(data);
			     $http.post(UrlAtelier + "/magasin/modifierMagasin", data)
			     .success(function (newMagasin) {
			    	angular.extend(newMagasin);
			     });
	        } else {
	        	console.log(data);
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

					