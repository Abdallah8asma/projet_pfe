'use strict'

var app = angular.module('gpro.back-articles', [ "ngResource" ]);
app.controller('BackPartieInteresseeCtrl2', [ '$scope', function($scope) {

	console.log("Back articles / elements de base");
	/** ***Liste desVariables **** */

	$scope.displayMenu = "eb";
	$scope.parametre = "Unite";
	$scope.displayBORF = 'b';
	/***************************************************************************
	 * Gestion de la Menu PI
	 **************************************************************************/
	$scope.changeTaPartieInteresse = function() {
		$scope.displayMenu = "pi";
	}
	$scope.changeTaElementBase = function() {
		$scope.displayMenu = "eb";
	}
	$scope.changeTaGestionCommerciale = function() {
		$scope.displayMenu = "gc";
	}
	
	  //Annuler Ajout
    $scope.cancelAdd = function(rowform, index, id, designation, liste){
    	$log.debug("* Designation "+liste[0].designation);
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
    
} ]);
/*******************************************************************************
 * Gestion des unites
 ******************************************************************************/

app.controller('BackArticleUniteController', [
		'$scope',
		'$filter',
		'$http',
		'$log',
		'UrlCommun',
		function($scope, $filter, $http,$log, UrlCommun) {
			/** ***Liste desVariables ***** */
			$scope.listeUniteArticle = [];
			$scope.UniteArticleInserree = null;

			// Lister la UniteArticles des fils
			$scope.listeUniteArticleFct = function() {
				$http.get(UrlCommun + "/uniteArticle/all").success(
						function(dataUnite) {
							$log.debug("listeUnite : " + dataUnite.length);
							if(dataUnite.length>0)
							$scope.listeUniteArticle = dataUnite;

						});
			}

			// ajout d'un UniteArticle
			$scope.ajoutUniteArticle = function() {
				$scope.UniteArticleInserree = {

					designation : ''

				};
				$scope.listeUniteArticle.push($scope.UniteArticleInserree);

			};

			// Enregistrer Representant
			$scope.saveUniteArticle = function(dataUnite, id) {

				if ((angular.isDefined(dataUnite.id))) {
					$log.debug("Modifier Ajout" + dataUnite.designation);

					$http.post(UrlCommun + "/uniteArticle/modifierUniteArticle",
							dataUnite).success(function(UniteArticleModifiee) {
						$log.debug("Success Modification");
						angular.extend(UniteArticleModifiee);
					});
				} else {

					if (dataUnite.designation == "") {
						$log.debug("Entree Vide !");
					} else {

						$http.post(UrlCommun + "/uniteArticle/creerUniteArticle",
								dataUnite).success(function(newUniteArticle) {
							$log.debug("Success Creation");
							angular.extend(newUniteArticle);

						});
					}
				}
			};
			// Supprimer UniteArticle
			$scope.removeUniteArticle = function(index) {
				$log.debug("INDEX :" + index);
				$log.debug("**OBJET :" + $scope.listeUniteArticle[index]);
				$log.debug("DELETE .." + $scope.listeUniteArticle[index].id);
				$http(
						{
							method : "DELETE",
							url : UrlCommun
									+ "/uniteArticle/supprimerUniteArticle:"
									+ $scope.listeUniteArticle[index].id
						}).success(function() {
					$log.debug("Success Delete");
					// $scope.listeUniteArticle.splice(index, 1);
				});
				$scope.listeUniteArticle.splice(index, 1);
			};
			$scope.listeUniteArticleFct();
			/** Fin de gestion des Unites */

		} ]);
/*******************************************************************************
 * Gestion des Metrage
 ******************************************************************************/
app.controller('BackArticleMetrageController', [
		'$scope',
		'$filter',
		'$http',
		'$log',
		'UrlCommun',
		function($scope, $filter, $http,$log, UrlCommun) {

			/** ***Liste desVariables ***** */
			$scope.listeMetrage = [];
			$scope.metrageInserree = {};
			
			// Lister la Metrages des fils
			$scope.listeMetrageFct = function() {
				$http.get(UrlCommun + "/metrage/all").success(
						function(dataMetrage) {
							$log.debug("listeMetrage : " + dataMetrage.length);
							if(dataMetrage.length>0)
							 $scope.listeMetrage = dataMetrage;

						});
			}
			
			// ajout d'un Metrage
			$scope.ajoutMetrage = function() {
				
				$scope.metrageInserree = {

					designation : ''

				};
				$scope.listeMetrage.push($scope.metrageInserree);

			};

			// Enregistrer Metrage
			$scope.saveMetrage = function(dataMetrage, id) {

				if ((angular.isDefined(dataMetrage.id))) {
					$log.debug("Modifier " + dataMetrage.designation);

					$http.post(UrlCommun + "/metrage/modifierMetrage",
							dataMetrage).success(function(MetrageModifiee) {
						$log.debug("Success Modification");
						angular.extend(MetrageModifiee);
					});
				} else {

					if (dataMetrage.designation == "") {
						$log.debug("designation vide !");
					} else {

						$http.post(UrlCommun + "/metrage/creerMetrage",
								dataMetrage).success(function(newMetrage) {
							$log.debug("Success Creation");
							angular.extend(newMetrage);

						});
					}
				}

			};

			// Supprimer Metrage
			$scope.removeMetrage = function(index) {

				$log.debug("INDEX :" + index);
				$log.debug("**OBJET :" + $scope.listeMetrage[index]);
				$log.debug("DELETE .." + $scope.listeMetrage[index].id);
				$http(
						{
							method : "DELETE",
							url : UrlCommun + "/metrage/supprimerMetrage:"
									+ $scope.listeMetrage[index].id
						}).success(function() {
					$log.debug("Success Delete");
					 
				});
				$scope.listeMetrage.splice(index, 1);
			};
			$scope.listeMetrageFct();
			
			/** Fin de gestion des Metrage */

		} ]);
/*******************************************************************************
 * Gestion des Matieres
 ******************************************************************************/
app.controller(
				'BackArticleMatiereController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						function($scope, $filter, $http,$log, UrlCommun) {

							/** ***Liste desVariables ***** */
							$scope.listeMatiere = [];
							$scope.matiereInserree = {};
							// Lister la Matieres des fils
							$scope.listeMatiereFct = function() {
								$http
										.get(UrlCommun + "/matiereArticle/all")
										.success(
												function(dataMatiere) {
													$log.debug("listeMatiere : "+ dataMatiere.length);
													if(dataMatiere.length>0)
													$scope.listeMatiere = dataMatiere;

												});
							}

							// ajout d'un Matiere
							$scope.ajoutMatiere = function() {
								$scope.matiereInserree = {

									designation : ''

								};
								$scope.listeMatiere.push($scope.matiereInserree);

							};

							// Enregistrer Matiere
							$scope.saveMatiere = function(dataMatiere, id) {

								if ((angular.isDefined(dataMatiere.id))) {
									$log.debug("Modifier Ajout"
											+ dataMatiere.designation);

									$http
											.post(
													UrlCommun
															+ "/matiereArticle/modifierMatiereArticle",
													dataMatiere)
											.success(
													function(MatiereModifiee) {
														console
																.log("Success Modification");
														angular
																.extend(MatiereModifiee);
													});
								} else {

									if (dataMatiere.designation == "") {
										$log.debug("Entree Vide !");
									} else {

										$http
												.post(
														UrlCommun
																+ "/matiereArticle/creerMatiereArticle",
														dataMatiere)
												.success(
														function(newMatiere) {
															console
																	.log("Success Creation");
															angular
																	.extend(newMatiere);

														});
									}
								}

							};

							// Supprimer Matiere
							$scope.removeMatiere = function(index) {

								$log.debug("INDEX :" + index);
								$log.debug("**OBJET :"
										+ $scope.listeMatiere[index]);
								$log.debug("DELETE .."
										+ $scope.listeMatiere[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/matiereArticle/supprimerMatiereArticle:"
													+ $scope.listeMatiere[index].id
										}).success(function() {
									$log.debug("Success Delete");
									// $scope.listeMatiere.splice(index, 1);
								});
								$scope.listeMatiere.splice(index, 1);
							};

							$scope.listeMatiereFct();
							/** Fin de gestion des Matieres */

						} ]);

/*******************************************************************************
 * Gestion des Grosseur
 ******************************************************************************/
app.controller('BackArticleGrosseurController', [
		'$scope',
		'$filter',
		'$http',
		'$log',
		'UrlCommun',
		function($scope, $filter, $http, $log, UrlCommun) {

			/** ***Liste desVariables ***** */
			$scope.listeGrosseur = [];
			$scope.grosseurInserree = null;
			// Lister la grosseurs des fils
			$scope.listeGrosseurFct = function() {
				$http.get(UrlCommun + "/grosseur/all").success(
						function(dataGrosseur) {
							$log.debug("listeGrosseur : " + dataGrosseur.length);
							if(dataGrosseur.length>0)
							$scope.listeGrosseur = dataGrosseur;

						});
			}

			// ajout d'un Grosseur
			$scope.ajoutGrosseur = function() {
				$scope.grosseurInserree = {

					designation : ''

				};
				$scope.listeGrosseur.push($scope.grosseurInserree);

			};

			// Enregistrer Grosseur
			$scope.saveGrosseur = function(dataGrosseur, id) {

				if ((angular.isDefined(dataGrosseur.id))) {
					$log.debug("Modifier Ajout" + dataGrosseur.designation);

					$http.post(UrlCommun + "/grosseur/modifierGrosseur",
							dataGrosseur).success(function(grosseurModifiee) {
						$log.debug("Success Modification");
						angular.extend(grosseurModifiee);
					});
				} else {

					if (dataGrosseur.designation == "") {
						$log.debug("Entree Vide !");
					} else {

						$http.post(UrlCommun + "/grosseur/creerGrosseur",
								dataGrosseur).success(function(newGrosseur) {
							$log.debug("Success Creation");
							angular.extend(newGrosseur);

						});
					}
				}

			};

			// Supprimer Grosseur
			$scope.removeGrosseur = function(index) {

				$log.debug("INDEX :" + index);
				$log.debug("**OBJET :" + $scope.listeGrosseur[index]);
				$log.debug("DELETE .." + $scope.listeGrosseur[index].id);
				$http(
						{
							method : "DELETE",
							url : UrlCommun + "/grosseur/supprimerGrosseur:"
									+ $scope.listeGrosseur[index].id
						}).success(function() {
					$log.debug("Success Delete");
					// $scope.listeGrosseur.splice(index, 1);
				});
				$scope.listeGrosseur.splice(index, 1);
			};

			$scope.listeGrosseurFct();
			/** Fin de gestion des Grosseurs */

		} ]);

/*******************************************************************************
 * Gestion des Familles
 ******************************************************************************/
app.controller(
				'BackArticleFamilleController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						function($scope, $filter, $http, $log, UrlCommun) {

							/** ***Liste desVariables ***** */
							$scope.listeFamilleArticle = [];
							$scope.listeTypeArticle = []; //
							$scope.typeArticleActuel = [];
							$scope.FamilleArticleInserree = null;
							$scope.type = {
								
								designation : ''
							};

							// Lister la FamilleArticles des fils
							$scope.listeFamilleArticleFct = function() {

								$http
										.get(UrlCommun + "/familleArticle/all")
										.success(
												function(dataFamilleArticle) {
													$log.debug("listeFamilleArticle : "
																	+ dataFamilleArticle.length);
													if(dataFamilleArticle.length>0)
													$scope.listeFamilleArticle = dataFamilleArticle;

												});
								$http
										.get(UrlCommun + "/typeArticle/all")
										.success(
												function(dataTypeArticle) {
													$log.debug("listeTypeArticle : "
																	+ dataTypeArticle.length);
													if(dataTypeArticle.length>0)
													$scope.listeTypeArticle = dataTypeArticle;

												});
							}

							// GetId.designation
							$scope.type = {

								status : ''
							};

							$scope.showStatus = function(id) {

								$scope.type.status = id;
								var selected = $filter('filterListBackArticle')(
										$scope.listeTypeArticle, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}

								// return ($scope.type.status &&
								// selected.length) ? selected[0].designation:
								// 'Not set';
							};

							// ajout d'un FamilleArticle
							$scope.ajoutFamilleArticle = function() {
								$scope.FamilleArticleInserree = {

									designation : '',
									idTypeArticle : ''

								};
								$scope.listeFamilleArticle
										.push($scope.FamilleArticleInserree);

							};

							// Enregistrer FamilleArticle
							$scope.saveFamilleArticle = function(
									dataFamilleArticle, id) {

								if ((angular.isDefined(dataFamilleArticle.id))) {
									$log.debug("Modifier Ajout"
											+ dataFamilleArticle.designation);

									$http
											.post(
													UrlCommun
															+ "/familleArticle/modifierFamilleArticle",
													dataFamilleArticle)
											.success(
													function(
															FamilleArticleModifiee) {
														$log.debug("Success Modification");
														angular.extend(FamilleArticleModifiee);
													});
								} else {

									if (dataFamilleArticle.designation == "") {
										// alert("Entree Vide !");
										$log.debug("Entree Vide !");
									} else {

										$http
												.post(
														UrlCommun
																+ "/familleArticle/creerFamilleArticle",
														dataFamilleArticle)
												.success(
														function(
																newFamilleArticle) {
															console
																	.log("Success Creation");
															angular
																	.extend(newFamilleArticle);

														});
									}
								}

							};

							// Supprimer FamilleArticle
							$scope.removeFamilleArticle = function(index) {

								$log.debug("INDEX :" + index);
								$log.debug("**OBJET :"
										+ $scope.listeFamilleArticle[index]);
								$log.debug("DELETE .."
										+ $scope.listeFamilleArticle[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/familleArticle/supprimerFamilleArticle:"
													+ $scope.listeFamilleArticle[index].id
										}).success(function() {
									$log.debug("Success Delete");
									// $scope.listeFamilleArticle.splice(index,
									// 1);
								});
								$scope.listeFamilleArticle.splice(index, 1);
							};

							$scope.listeFamilleArticleFct();
							/** Fin de gestion des FamilleArticles */

						} ]);

/*******************************************************************************
 * Gestion des SousFamille
 ******************************************************************************/
app.controller(
				'BackArticleSousFamilleController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						function($scope, $filter, $http,$log, UrlCommun) {

							/** ***Liste desVariables ***** */
							$scope.listeSousFamilleArticle = [];
							$scope.listeFamilleArticle = [];
							$scope.listeTypeArticle = []; //
							$scope.typeArticleActuel = [];
							$scope.SousFamilleArticleInserree = null;
							/*$scope.type = {
								id : '',
								designation : ''
							};
*/
							// Lister la SousFamilleArticles des fils
							$scope.listeSousFamilleArticleFct = function() {
								$http
										.get(
												UrlCommun
														+ "/sousFamilleArticle/all")
										.success(
												function(dataSousFamilleArticle) {
													$log.debug("listeSousFamilleArticle : "
																	+ dataSousFamilleArticle.length);
													if(dataSousFamilleArticle.length>0)
													$scope.listeSousFamilleArticle = dataSousFamilleArticle;

												});
								$http
										.get(UrlCommun + "/familleArticle/all")
										.success(
												function(dataFamilleArticle) {
													$log.debug(" * listeFamilleArticle : "
																	+ dataFamilleArticle.length);
													if(dataFamilleArticle.length>0)
													$scope.listeFamilleArticle = dataFamilleArticle;

												});
								$http
										.get(UrlCommun + "/typeArticle/all")
										.success(
												function(dataTypeArticle) {
													$log.debug(" * * listeTypeArticle : "
																	+ dataTypeArticle.length);
													if(dataTypeArticle.length>0)
													$scope.listeTypeArticle = dataTypeArticle;

												});
							}

							// GetId.designation
							$scope.type = {
								status : ''
							};

							$scope.famille = {
								status : ''
							};

							$scope.showStatusSousFamille = function(id) {
								$scope.famille.status = id;
								var selected = $filter('filterListBackArticle')(
										$scope.listeFamilleArticle, {
											id : $scope.famille.status
										});
								return ($scope.famille.status && selected.length) ? selected[0].designation
										: 'Not set';
							};

							$scope.showStatusSousFamilleType = function(id) {
								$scope.famille.status = id;
								var selected = $filter('filterListBackArticle')(
										$scope.listeFamilleArticle, {
											id : $scope.famille.status
										});
								if ($scope.famille.status && selected.length) {

									$scope.type.status = selected[0].idTypeArticle;
									var selected2 = $filter('filterListBackArticle')(
											$scope.listeTypeArticle, {
												id : $scope.type.status
											});
									return ($scope.type.status && selected2.length) ? selected2[0].designation
											: 'Not set';
								} else {
									return "NOT SET";
								}

							};

							// ajout d'un SousFamilleArticle
							$scope.ajoutSousFamilleArticle = function() {
								$scope.SousFamilleArticleInserree = {

										designation : '',
										idFamilleArticle : ''

								};
								$scope.listeSousFamilleArticle
										.push($scope.SousFamilleArticleInserree);

							};

							// Enregistrer SousFamilleArticle
							$scope.saveSousFamilleArticle = function(
									dataSousFamilleArticle, id) {

								if ((angular
										.isDefined(dataSousFamilleArticle.id))) {
									console
											.log("Modifier Ajout"
													+ dataSousFamilleArticle.designation);

									$http
											.post(
													UrlCommun
															+ "/sousFamilleArticle/modifierSousFamilleArticle",
													dataSousFamilleArticle)
											.success(
													function(
															SousFamilleArticleModifiee) {
														console
																.log("Success Modification");
														angular
																.extend(SousFamilleArticleModifiee);
													});
								} else {

									if (dataSousFamilleArticle.designation == "") {
										$log.debug("Entree Vide !");
									} else {

										$http
												.post(
														UrlCommun
																+ "/sousFamilleArticle/creerSousFamilleArticle",
														dataSousFamilleArticle)
												.success(
														function(
																newSousFamilleArticle) {
															console
																	.log("Success Creation");
															angular
																	.extend(newSousFamilleArticle);

														});
									}
								}

							};

							// Supprimer SousFamilleArticle
							$scope.removeSousFamilleArticle = function(index) {

								$log.debug("INDEX :" + index);
								console
										.log("**OBJET :"
												+ $scope.listeSousFamilleArticle[index]);
								console
										.log("DELETE .."
												+ $scope.listeSousFamilleArticle[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/sousFamilleArticle/supprimerSousFamilleArticle:"
													+ $scope.listeSousFamilleArticle[index].id
										}).success(function() {
									$log.debug("Success Delete");
									// $scope.listeSousFamilleArticle.splice(index,
									// 1);
								});
								$scope.listeSousFamilleArticle.splice(index, 1);
							};

							$scope.listeSousFamilleArticleFct();
							/** Fin de gestion des SousFamilleArticles */

						} ]);
app.filter('filterListBackArticle', function() {
	  return function(liste, id) {
		 var listeFiltre = [];
		// $log.debug("IdSousFamille=  "+id.id);
		// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
		 angular.forEach(liste, function(elementListe, key){
			
			if(elementListe.id == id.id){
				//$log.debug(sousFamille.id +" == "+ id.id);
				listeFiltre.push(elementListe);
			}
				
		 });
		// $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
		 return listeFiltre;
	  };
	})
app.controller('BackElBase', [ '$scope','$route', function($scope, $route) {



	//$scope.ITEM = 'article';
	$scope.ITEM = 'produit';


	var current = $route.current;
	switch (current.params['type']) {
		case 'produit':
			$scope.ITEM = 'produit';
			break;
	

		case 'article':
			$scope.ITEM = 'article';
			break;
	}
	

	$scope.goArticle = function(){$scope.ITEM = 'article';}
	$scope.goProduit = function(){$scope.ITEM = 'produit';}
}]);
