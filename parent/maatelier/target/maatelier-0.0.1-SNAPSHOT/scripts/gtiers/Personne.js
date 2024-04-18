'use strict'

angular
  .module('theme.personne', ["ngResource"])
 .constant("baseUrl", "http://localhost:8080/mt-gpro-commun-rest")
  .controller('PersonneController', ['$scope', '$http','baseUrl', function ($scope,$http, baseUrl) {
	  
	  
	  $scope.displayMode = "list";
	    $scope.currentPersonne = null;

	    $scope.listPersonnes = function () {
	    
	    	
	    	$http.get(baseUrl+"/partieInteressee/all").success(function (data) {
	    		$scope.personnes = data;
	    		});
	    	
	    }
	    
	    $scope.supprimerPersonne = function (personne) {
	    	$http({
	    		method: "DELETE",
	    		url: baseUrl + "/personne/supprimer:{"+personne.id+"}"
	    		}).success(function () {
	    	$scope.personnes.splice($scope.personnes.indexOf(personne), 1);
	    	});
	    }

	    $scope.creerPersonne = function (personne) {
	    	$http.post(baseUrl + "/personne/creerPersonne", personne).success(function (newPersonne) {
	        $scope.personnes.push(newPersonne);
	        $scope.displayMode = "list";
	    	});
	    }

	    $scope.updatePersonne = function (personne) {
	    	$http({
	    		url: baseUrl +"/personne/modifierPersonne:{"+personne.id+"}",
	    		method: "PUT",
	    		data: personne
	    		}).success(function (personneModifiee) {
	        for (var i = 0; i < $scope.personnes.length; i++) {
	            if ($scope.personnes[i].id == personneModifiee.id) {
	                $scope.personnes[i] = personneModifiee;
	                break;
	            }
	        }
	        $scope.displayMode = "list";
	    		});
	    }

	    $scope.modifierOucreerPersonne = function (personne) {
	        $scope.currentPersonne =
	            personne ? angular.copy(personne) : {};
	        $scope.displayMode = "edit";
	    }

	    $scope.sauvegarderAjout = function (personne) {
	        if (angular.isDefined(personne.id)) {
	            $scope.updatePersonne(personne);
	        } else {
	            $scope.creerPersonne(personne);
	        }
	    }

	    $scope.annulerAjout = function () {
	        $scope.currentPersonne = {};
	        $scope.displayMode = "list";
	    }

	    $scope.listPersonnes();
	    
  }
  ])

					