'use strict'

var app = angular.module('gpro.back-logistique', [ "ngResource" ]);
app.controller('BackLogistiqueCtr', [ '$scope','UrlAtelier','$log', function($scope,UrlAtelier,$log) {

	console.log("Back logistique");
	
			// Déclaration des variables globales utilisées

			/** ***Liste desVariables **** */
			
			$scope.displayMenu = "gpao";
			$scope.parametre = "Machine";
			
			  //Annuler Ajout
		    $scope.cancelAdd = function(rowform, index, id, designation, liste){
		    	$log.info("* Designation "+liste[0].designation);
		    		  if (angular.isDefined(id)) {
		    			  $log.info("ID "+id);
		    			  rowform.$cancel();
		    		  }else{
		    			  $log.info("ID "+id);
		    			  if(designation ==""){
		    				  $log.info("DELETE" +designation);
		    				  liste.splice(index, 1);
					    	  rowform.$cancel();
		    			  }else{
		    				  $log.info("NOT DELETE "+designation);
		    				  rowform.$cancel();
		    			  }
		    		}
		    }
		    
		}]);
		 /*****************************
	     * Gestion de Machine 
	     ****************************/
		  app.controller('backMachineController', ['$scope','$filter', '$http','UrlAtelier','$log', function ($scope,$filter, $http, UrlAtelier,$log) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "gpao";
				    $scope.MachineCourante = null;
				    $scope.listeMachine=[]; 
				    /********************
				     * Gestion de Chaine
				     *********************************/
				    
				    //Lister Machine
				    $scope.listeMachineFct = function () {
				    	$http.get(UrlAtelier+"/machine/getAll").success(function (dataMachine) {
				    		if(dataMachine.length>0)
				    		$scope.listeMachine = dataMachine;
				    		$log.info("Machine "+ dataMachine.length)
				    	});
				    }

				    // ajout d'une Machine
				    $scope.ajouterMachine = function() {
					      $scope.MachineCourante = {
					        designation: ''
					      };
					      $scope.listeMachine.push($scope.MachineCourante);
					      
					    };
				  	//Enregistrer Machine
				  	$scope.saveMachine = function(machine, id) {
					  if (angular.isDefined(id)) {
						     $http.post(UrlAtelier + "/machine/update", machine)
						     .success(function (newMachine) {
						    	angular.extend(newMachine);
						     });
						     
				        } else {
						     $http.post(UrlAtelier + "/machine/create", machine)
						     .success(function (newMachine) {
							    	angular.extend(newMachine);
						     });
				        }
					  }
				    // Suppression Machine
				    $scope.removeMachine = function (index) {
				    	$log.info("INDEX :" + index);
						$log.info("**OBJET :" + $scope.listeMachine[index]);
						$log.info("DELETE .." + $scope.listeMachine[index].id);

			        	$http({
			 	    		method: "DELETE",
			 	    		url: UrlAtelier + "/machine/delete:"+ $scope.listeMachine[index].id
			 	    		}).success(function () {
			 	    			$log.info("DELETE Machine");
			 	    		//	$scope.listeMachine.splice(index, 1);
			 	    	});
			        	$scope.listeMachine.splice(index, 1);
				    }
				    
				    $scope.listeMachineFct();
			   }
			  ]);

		/*****************************
	     * Gestion de Traitement 
	     ****************************/
		  app.controller('backTraitementController', ['$scope','$filter', '$http','UrlAtelier','$log', function ($scope,$filter, $http, UrlAtelier,$log) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "gpao";
				    $scope.TraitementCourant = null;
				    $scope.listeTraitement=[]; 
				    /********************
				     * Gestion de Chaine
				     *********************************/
				    
				    //Lister Traitement
				    $scope.listeTraitementFct = function () {
				    	$http.get(UrlAtelier+"/traitement/all").success(function (dataTraitement) {
				    		if(dataTraitement.length>0)
				    		$scope.listeTraitement = dataTraitement;
				    		$log.info("Traitement "+ dataTraitement.length)
				    	});
				    }
				    
				     
				     // ajout d'un Traitement
				    $scope.ajouterTraitement = function() {
					      $scope.TraitementCourant = {
					        designation: ''
					      };
					      $scope.listeTraitement.push($scope.TraitementCourant);
					      
					    };
				   //Enregistrer Traitement
				  $scope.saveTraitement = function(traitement, id) {
					  if (angular.isDefined(id)) {
						     $http.post(UrlAtelier + "/traitement/modifieTraitement", traitement)
						     .success(function (newTraitement) {
						    	angular.extend(newTraitement);
						     });
						     
				        } else {
						     $http.post(UrlAtelier + "/traitement/creerTraitement", traitement)
						     .success(function (newTraitement) {
							    	angular.extend(newTraitement);
						     });
				        }
					  }
				   // Suppression Traitement
				    $scope.removeTraitement = function (index) {
				    	$log.info("INDEX :" + index);
						$log.info("**OBJET :" + $scope.listeTraitement[index]);
						$log.info("DELETE .." + $scope.listeTraitement[index].id);

			        	$http({
			 	    		method: "DELETE",
			 	    		url: UrlAtelier + "/traitement/supprimerTraitement:"+ $scope.listeTraitement[index].id
			 	    		}).success(function () {
			 	    			$log.info("DELETE Traitement");
			 	    		//	$scope.listeTraitement.splice(index, 1);
			 	    	});
			        	$scope.listeTraitement.splice(index, 1);
				    }
				    
				    $scope.listeTraitementFct();
			   }
			  ]);

		  
		  
		  /***********************************
		     * Gestion de Traitement façon
		     ********************************/
		  
			  app.controller('backTraitementFaconController', 
					  ['$scope',
					   '$filter',
					   '$http',
					   'UrlAtelier',
					   '$log',
					   'traitementFaconServices',
					   function ($scope,$filter, $http, UrlAtelier,$log, traitementFaconServices) {
					//Déclaration des variables globales utilisées
						var data;
					  	$scope.displayMode = "gpao";
					    $scope.TraitementFaconCourant = null;
					    $scope.listeTraitementFacon=[]; 
					    /********************
					     * Gestion de Chaine
					     *********************************/
					    
					    //Lister Traitement façon
					    $scope.listeTraitementFaconFct = function () {
					    	traitementFaconServices.getListeTraitementFacon().then(function(dataTraitement) {
					    		if(dataTraitement.length>0)
					    			$scope.listeTraitementFacon = dataTraitement;
					    	}, function(error) {
             					console.log(error.statusText);
             				}); 
					    	
					    }
					    
					     
					     // ajout d'un Traitement façon
					    $scope.ajouterTraitementFacon = function() {
						      $scope.TraitementFaconCourant = {
						        designation: '',
						        pu:''
						      };
						      $scope.listeTraitementFacon.push($scope.TraitementFaconCourant);
						      
						    };
						    
					   //Enregistrer Traitement façon
					  $scope.saveTraitementFacon = function(traitementFacon, id) {
						  $log.debug("id traitement : save " + id);
						  if (angular.isDefined(id)) {
							  traitementFaconServices.modifierTraitementFacon(traitementFacon).then(function(id) {
								  angular.extend(id);
						    	}, function(error) {
	             					console.log(error.statusText);
	             				}); 
							  
					        } else {
					        	traitementFaconServices.ajouterTraitementFacon(traitementFacon).then(function(newid) {
									  angular.extend(newid);
							    	}, function(error) {
		             					console.log(error.statusText);
		             				}); 
					        }
						  }
					  
					  
					   // Suppression Traitement façon
					    $scope.removeTraitementFacon = function (index) {
					    	$log.info("INDEX :" + index);
							$log.info("**OBJET :" + $scope.listeTraitementFacon[index]);
							$log.info("DELETE .." + $scope.listeTraitementFacon[index].id);
							
							traitementFaconServices.deleteTraitementFacon($scope.listeTraitementFacon[index].id).then(function(result) {
								$log.info("DELETE Traitement");
								$scope.listeTraitementFacon.splice(index, 1);
						    	}, function(error) {
	             					console.log(error.statusText);
	             				}); 							
					    }
					    
					    $scope.listeTraitementFaconFct();
				   }
				  ]);


		
