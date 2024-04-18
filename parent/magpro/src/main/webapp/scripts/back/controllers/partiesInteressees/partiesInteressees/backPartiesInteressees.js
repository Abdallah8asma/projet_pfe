'use strict'

var app = angular.module('gpro.back-partieInteressee', ["ngResource"]);

	app.controller('BackPartieInteresseeCtrl',['$scope','$log',function($scope, $log){
		
		console.log("Back parties interessees");
		
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
		
		$scope.listeModuleDocuments = [ 
			 
			 {"module":'ELEMENT_DE_BASE_PRODUIT'} ,
			 {"module":'PI_CLIENT'} ,
			 {"module":'PI_FOURNISSEUR'} ,
			 {"module":'VENTE_LIVRAISON'} ,
			 {"module":'VENTE_FACTURE'} ,
			 {"module":'VENTE_AVOIR'} ,
			 {"module":'ACHAT_RECEPTION'} ,
			 {"module":'ACHAT_FACTURE'} ,
			 {"module":'ACHAT_AVOIR'} ,
			 {"module":'FINANCE_CLIENT_REGLEMENT'} ,
			 {"module":'FINANCE_FOURNISSEUR_REGLEMENT'} 
		
		         ];
		
		  //Annuler Ajout
	    $scope.cancelAdd = function(rowform, index, id, designation, liste){
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
	 /*****************************
     * Gestion des familles 
     ****************************/
	  app.controller('backFamillePIController', ['$scope','$filter', '$http', '$log', 'UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
			    	$http.get(UrlCommun+"/famillePI/all").success(function (data) {
			    		$log.debug("listeFamille : "+data.length);
			    		$scope.listeFamille = data;
			    	});
			    }
			  
			     // ajout d'une Famille
				    $scope.ajouterFamille = function() {
				      $scope.familleCourante = {
				        designation: ''
				      };
				    
				     // $log.debug("* $scope.familleCourante "+ $scope.familleCourante);
				      $scope.listeFamille.push($scope.familleCourante);
				    };
				    
				  
			   //Enregistrer famille
			  $scope.saveFamille = function(data, id) {
				  if (angular.isDefined(id)) {
					     $http.post(UrlCommun + "/famillePI/modifierFamillePI", data)
					     .success(function (newfamille) {
					    	 $log.debug("Success Modification");
					    	angular.extend(newfamille);
					     });
			        } else {
					     $http.post(UrlCommun + "/famillePI/creerFamillePI", data)
					     .success(function (newfamille) {
					    	 $log.debug("Success Creation");
						    	angular.extend(newfamille);
					     });
			        }
				     
				  }
			   // Suppression famille produit
			    $scope.removeFamilleProduit = function (index) {
			    	$log.debug("INDEX :" + index);
					$log.debug("**OBJET :" + $scope.listeFamille[index]);
					$log.debug("DELETE .." + $scope.listeFamille	[index].id);
		        	$http({
		 	    		method: "DELETE",
		 	    		url: UrlCommun + "/famillePI/supprimerFamillePI:"+$scope.listeFamille[index].id
		 	    		}).success(function () {
						$log.debug("Success Delete");
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
	  app.controller('backCathegoriePIController', ['$scope','$filter', '$http','$log','UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
			    	$http.get(UrlCommun+"/cathegoriePI/all").success(function (data) {
			    		$log.debug("listeCathegorie : "+data.length);
			    		$scope.listeCathegorie = data;
			    	});
			    }
			   
			     // ajout d'une cateegories
				    $scope.ajouterCathegorie = function() {
				    	 $scope.CategorieCourante = {
							        designation: ''
							      };
				      $log.debug("* $scope.CategorieCourante "+ $scope.CategorieCourante);
				      $scope.listeCathegorie.push($scope.CategorieCourante);
				    };
			   //Enregistrer cateegories
			  $scope.saveCathegorie = function(data, id) {
				  $log.debug("Categorie "+data);
				  if (angular.isDefined(id)) {
					     $http.post(UrlCommun + "/cathegoriePI/modifierCathegoriePI", data)
					     .success(function (newcathegorie) {
					    	 $log.debug("Success Modification");
					    	angular.extend(newcathegorie);
					     });
			        } else {
			        	$log.debug(data);
					     $http.post(UrlCommun + "/cathegoriePI/creerCathegoriePI", data)
					     .success(function (newcathegorie) {
					    	 $log.debug("Success Creation");
						    	angular.extend(newcathegorie);
					     });
			        }
				  }
			   // Suppression famille produit
			    $scope.removecathegorie = function (index) {
			    	$log.debug("INDEX :" + index);
					$log.debug("**OBJET :" + $scope.listeCathegorie[index]);
					$log.debug("DELETE .." + $scope.listeCathegorie[index].id);

			    		$http({
			 	    		method: "DELETE",
			 	    		url: UrlCommun + "/cathegoriePI/supprimerCathegoriePI:"+ $scope.listeCathegorie[index].id
			 	    		}).success(function () {
			 	    			 $log.debug("Success Delete");
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
	  app.controller('backTypePIController', ['$scope','$filter', '$http', '$log', 'UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
			    	$http.get(UrlCommun+"/typePartieIntersse/all").success(function (data) {
			    		$log.debug("listeType : "+data.length);
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
					     $http.post(UrlCommun + "/typePartieIntersse/modifierType", data)
					     .success(function (newtype) {
					    	 $log.debug("Success Modification");
					    	angular.extend(newtype);
					     });
					     $scope.listeTypePI();
					     
			        } else {
			        	$log.debug(data);
					     $http.post(UrlCommun + "/typePartieIntersse/createType", data)
					     .success(function (newtype) {
					    	 $log.debug("Success Ctreation");
						    	angular.extend(newtype);
					     });
					     $scope.listeTypePI();
			        }
				  
				  }
				    
			  
			   // Suppression Type
			    $scope.removeTypePI = function (index) {
			    	$log.debug("INDEX :" + index);
					$log.debug("**OBJET :" + $scope.listeType[index]);
					$log.debug("DELETE .." + $scope.listeType[index].id);

		        	$http({
		 	    		method: "DELETE",
		 	    		url: UrlCommun + "/typePartieIntersse/deleteType:"+ $scope.listeType[index].id
		 	    		}).success(function () {
		 	    			$log.debug("Success Delete");
		 	    			$scope.listeType.splice(index, 1);
		 	    	});
 	    			
			    }
			    $scope.listeTypePI();
		   }
		  ]);
	  /*****************************
	     * Gestion de Devise 
	     ****************************/
	  app.controller('backDeviseController', ['$scope','$filter', '$http', '$log', 'UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
			    	$http.get(UrlCommun+"/devise/all").success(function (dataDevise) {
			    		$log.debug("listeDevise : "+dataDevise.length);
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
					     $http.post(UrlCommun + "/devise/modifierDeviseProduit", devise)
					     .success(function (newDevise) {
					    	angular.extend(newDevise);
					     });
					  //   $scope.listeDevise();
					     
			        } else {
			        	$log.debug(devise);
					     $http.post(UrlCommun + "/devise/creerDeviseProduit", devise)
					     .success(function (newDevise) {
						    	angular.extend(newDevise);
					     });
					   //  $scope.listeDevise();
			        }
				  }
			   // Suppression Devise
			    $scope.removeDevise = function (index) {
			    	$log.debug("INDEX :" + index);
					$log.debug("**OBJET :" + $scope.listeDevise[index]);
					$log.debug("DELETE .." + $scope.listeDevise[index].id);

		        	$http({
		 	    		method: "DELETE",
		 	    		url: UrlCommun + "/devise/supprimerDeviseProduit:"+ $scope.listeDevise[index].id
		 	    		}).success(function () {
		 	    			$log.debug("DELETE DEVISE");
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
	  		'$log',
	  		'UrlCommun',
	  		function($scope, $filter, $http,$log, UrlCommun) {

	  			/** ***Liste desVariables ***** */
	  			$scope.listeTypeDoc = [];

	  			// Lister la TypeDocs des fils
	  			$scope.listeTypeDoc = function() {
	  				$http.get(UrlCommun + "/typeDocument/all").success(
	  						function(dataTypeDoc) {
	  							$log.debug("listeTypeDoc" + dataTypeDoc.length);
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
	  					$log.debug("TypeDoc Existe deja ");

	  					$http.post(UrlCommun + "/typeDocument/modifierTypeDocument",
	  							dataTypeDoc).success(function(TypeDocModifiee) {
	  						$log.debug("Success Modification");
	  						angular.extend(TypeDocModifiee);
	  					});
	  				} else {

	  						$http.post(UrlCommun + "/typeDocument/createTypeDocument",
	  								dataTypeDoc).success(function(newTypeDoc) {
	  							$log.debug("Success Creation");
	  							angular.extend(newTypeDoc);

	  						});
	  					
	  				}

	  			};

	  			// Supprimer TypeDoc
	  			$scope.removeTypeDoc = function(index) {

	  				$log.debug("INDEX :" + index);
	  				$log.debug("**OBJET :" + $scope.listeTypeDoc[index]);
	  				$log.debug("DELETE .." + $scope.listeTypeDoc[index].id);
	  				$http(
	  						{
	  							method : "DELETE",
	  							url : UrlCommun + "/typeDocument/deleteTypeDocument:"
	  									+ $scope.listeTypeDoc[index].id
	  						}).success(function() {
	  					$log.debug("Success Delete");
	  					 $scope.listeTypeDoc.splice(index, 1);
	  				});
	  			};

	  			$scope.listeTypeDoc();
	  			/** Fin de gestion des TypeDoc */

	  		} ]);

	  /*****************************
	     * Gestion des Sites 
	     ****************************/
		  app.controller('backSitePIController', ['$scope','$filter', '$http', '$log', 'UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
				    	$http.get(UrlCommun+"/sitePartieIntersse/all").success(function (data) {
				    		$log.debug("listeSite : "+data.length);
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
						     $http.post(UrlCommun + "/sitePartieIntersse/modifierSite", data)
						     .success(function (newSite) {
						    	 $log.debug("Success Modification");
						    	angular.extend(newSite);
						     });
				        } else {
						     $http.post(UrlCommun + "/sitePartieIntersse/createSite", data)
						     .success(function (newSite) {
						    	 $log.debug("Success Creation");
							    	angular.extend(newSite);
						     });
				        }
					     
					  }
				   // Suppression Site produit
				    $scope.removeSite = function (index) {
				    	$log.debug("INDEX :" + index);
						$log.debug("**OBJET :" + $scope.listeSite[index]);
						$log.debug("DELETE .." + $scope.listeSite	[index].id);
			        	$http({
			 	    		method: "DELETE",
			 	    		url: UrlCommun + "/sitePartieIntersse/deleteSite:"+$scope.listeSite[index].id
			 	    		}).success(function () {
							$log.debug("Success Delete");
							$scope.listeSite.splice(index, 1);
						});
			        	$scope.listeSite.splice(index, 1);
				    }
				    $scope.listeSite();
			   }
			  ]);
			  
			  
			  	  /*****************************
	     * Gestion des Groupe clients 
	     ****************************/
	  app.controller('backGroupeClientController', ['$scope','$filter', '$http','$log','UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
			    	$http.get(UrlCommun+"/groupeClient/all").success(function (data) {
			    		$log.debug("listeCathegorie : "+data.length);
			    		$scope.listeCathegorie = data;
			    	});
			    }
			   
			     // ajout d'une cateegories
				    $scope.ajouterCathegorie = function() {
				    	 $scope.CategorieCourante = {
							        designation: ''
							      };
				      $log.debug("* $scope.CategorieCourante "+ $scope.CategorieCourante);
				      $scope.listeCathegorie.push($scope.CategorieCourante);
				    };
			   //Enregistrer cateegories
			  $scope.saveCathegorie = function(data, id) {
				  $log.debug("Categorie "+data);
				  if (angular.isDefined(id)) {
					     $http.post(UrlCommun + "/groupeClient/modifier", data)
					     .success(function (newcathegorie) {
					    	 $log.debug("Success Modification");
					    	angular.extend(newcathegorie);
					     });
			        } else {
			        	$log.debug(data);
					     $http.post(UrlCommun + "/groupeClient/creer", data)
					     .success(function (newcathegorie) {
					    	 $log.debug("Success Creation");
						    	angular.extend(newcathegorie);
					     });
			        }
				  }
			   // Suppression famille produit
			    $scope.removecathegorie = function (index) {
			    	$log.debug("INDEX :" + index);
					$log.debug("**OBJET :" + $scope.listeCathegorie[index]);
					$log.debug("DELETE .." + $scope.listeCathegorie[index].id);

			    		$http({
			 	    		method: "DELETE",
			 	    		url: UrlCommun + "/groupeClient/supprimer:"+ $scope.listeCathegorie[index].id
			 	    		}).success(function () {
			 	    			 $log.debug("Success Delete");
			 	    			$scope.listeCathegorie.splice(index, 1);
			 	    			
			 	    	});
			    		$scope.listeCathegorie.splice(index, 1);
			    }
			    $scope.listeCathegoriePI();
		   }
		  ]);
	  
	  
	  
	  /*****************************
	     * Gestion des Regions PI 
	     ****************************/
	  app.controller('backRegionPIController', ['$scope','$filter', '$http','$log','UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
			    	$http.get(UrlCommun+"/region/getAll").success(function (data) {
			    		$log.debug("listeCathegorie : "+data.length);
			    		$scope.listeCathegorie = data;
			    	});
			    }
			   
			     // ajout d'une cateegories
				    $scope.ajouterCathegorie = function() {
				    	 $scope.CategorieCourante = {
							        designation: ''
							      };
				      $log.debug("* $scope.CategorieCourante "+ $scope.CategorieCourante);
				      $scope.listeCathegorie.push($scope.CategorieCourante);
				    };
			   //Enregistrer cateegories
			  $scope.saveCathegorie = function(data, id) {
				  $log.debug("Categorie "+data);
				  if (angular.isDefined(id)) {
					     $http.post(UrlCommun + "/region/update", data)
					     .success(function (newcathegorie) {
					    	 
					    	 $log.debug("Success Modification");
					    	angular.extend(newcathegorie);
					    	 $scope.listeCathegoriePI();
					    	 
					     });
			        } else {
			        	$log.debug(data);
					     $http.post(UrlCommun + "/region/create", data)
					     .success(function (newcathegorie) {
					    	  
					    	 $log.debug("Success Creation");
						    	angular.extend(newcathegorie);
						    	$scope.listeCathegoriePI();
					     });
			        }
				  }
			   // Suppression famille produit
			    $scope.removecathegorie = function (index) {
			    	$log.debug("INDEX :" + index);
					$log.debug("**OBJET :" + $scope.listeCathegorie[index]);
					$log.debug("DELETE .." + $scope.listeCathegorie[index].id);

			    		$http({
			 	    		method: "DELETE",
			 	    		url: UrlCommun + "/region/delete:"+ $scope.listeCathegorie[index].id
			 	    		}).success(function () {
			 	    			 $log.debug("Success Delete");
			 	    			$scope.listeCathegorie.splice(index, 1);
			 	    			$scope.listeCathegoriePI();
			 	    			
			 	    	});
			    		//$scope.listeCathegorie.splice(index, 1);
			    }
			    $scope.listeCathegoriePI();
		   }
		  ]);


		    /*****************************
	     * Gestion des banque 
	     ****************************/
	  app.controller('backBanquePIController', ['$scope','$filter', '$http','$log','UrlCommun', function ($scope,$filter, $http, $log, UrlCommun) {
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
				  $http.get(UrlCommun+"/banquePI/all").success(function (data) {
					  $log.debug("listeCathegorie : "+data.length);
					  $scope.listeCathegorie = data;
				  });
			  }
			 
			   // ajout d'une cateegories
				  $scope.ajouterCathegorie = function() {
					   $scope.CategorieCourante = {
								  designation: ''
								};
					$log.debug("* $scope.CategorieCourante "+ $scope.CategorieCourante);
					$scope.listeCathegorie.push($scope.CategorieCourante);
				  };
			 //Enregistrer cateegories
			$scope.saveCathegorie = function(data, id) {
				$log.debug("Categorie "+data);
				if (angular.isDefined(id)) {
					   $http.post(UrlCommun + "/banquePI/modifierBanquePI", data)
					   .success(function (newcathegorie) {
						   $log.debug("Success Modification");
						  angular.extend(newcathegorie);
					   });
				  } else {
					  $log.debug(data);
					   $http.post(UrlCommun + "/banquePI/creerBanquePI", data)
					   .success(function (newcathegorie) {
						data.id = newcathegorie;
						   $log.debug("Success Creation");
							  angular.extend(newcathegorie);
					   });
				  }
				}
			 // Suppression famille produit
			  $scope.removecathegorie = function (index) {
				  $log.debug("INDEX :" + index);
				  $log.debug("**OBJET :" + $scope.listeCathegorie[index]);
				  $log.debug("DELETE .." + $scope.listeCathegorie[index].id);

					  $http({
						   method: "DELETE",
						   url: UrlCommun + "/banquePI/supprimerBanquePI:"+ $scope.listeCathegorie[index].id
						   }).success(function () {
								$log.debug("Success Delete");
							   $scope.listeCathegorie.splice(index, 1);
							   
					   });
					  $scope.listeCathegorie.splice(index, 1);
			  }
			  $scope.listeCathegoriePI();
		 }
		]);