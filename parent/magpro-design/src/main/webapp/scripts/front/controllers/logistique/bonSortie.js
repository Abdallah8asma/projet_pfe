'use strict'

 angular
.module('atelier.bonSortie', [ "ngResource", "ngCsvImport"])
.controller(
              		'bonSortieController',
              		[
              				'$scope',
              				'$filter',
              				'$http',
              				'$log',
              				'$parse',
              				'downloadService',
              				'UrlCommun',
							  'UrlAtelier',
							  '$window',
              				function($scope, $filter, $http, $log, $parse, downloadService, UrlCommun, UrlAtelier,$window) {
              					$log.info("=========Bon Sortie========");
              					// Déclaration des variables globales utilisés
              					$scope.today = new Date();
                        var data;
              					$scope.displayMode = "list";
              					//bouton pdf hide
              					$scope.modePdf = "notActive";
              					$scope.bonSortieCourant = null;
              					//init modification Metrage du rouleau
              					$scope.isModifie = false;
              					$scope.listeRouleauFini = [];
              					$scope.listSousFamilleProduitCache = [];
                        $scope.listeRefMiseREF = [];
                        $scope.listeCodeBarreFromMise = [];
                        $scope.nbRouleauFiniRestant = 0;
                        $scope.refrenceMise = '';
              					//init urlValider
              					$scope.urlValider = "";
              					//Pavet SolderMise ne s'affiche pas au demarrage de la page
                        $scope.isCollapsed = true;
              					/********************************
              					 * Gestion des listes deroulantes*
              					 * à changer par Cache
              					 ********************************/
      					$scope.bonSortieCourantS = {};
     $scope.traitementEnCours = "false";
              				//Liste Type des Bons de sortie
              				$scope.listeTypeBS = [{id:1,designation:"Expédition"},{id:2,designation:"Echantillon"}];
              				$scope.bonSortieCourantS.listeTypeBS = [{id:"1",designation:"Expédition"},{id:"2",designation:"Echantillon"}];

              			$scope.bonSortieCourantS.type = $scope.bonSortieCourantS.listeTypeBS[0];
              					console.log("-----$scope.bonSortieCourant.type-----"+$scope.bonSortieCourantS.type);
              					
              			  $scope.traitementEnCours = "false";
$scope.removeCode =function(index){
$scope.listCode.splice(index,1);
}	
              				// Lister les BonSortie
          						$scope.rechercherBonSortie = function(bonSortieCourant) {
          							$http.post(
          											UrlAtelier
          													+ "/bonsortiefini/rechercheMulticritere",
          											bonSortieCourant)
          									.success(
          											function(resultat) {
          												$scope.myData = resultat.list;
          												// Pagination du resultat de
          												// recherche
          												// data, page,pageSize
          												$scope.setPagingData(
          																$scope.myData,
          																$scope.pagingOptions.currentPage,
          																$scope.pagingOptions.pageSize);

          												$log.debug("listeBonSortie : "+ resultat.list.length);
          												
          												$scope.rechercheBonSortieForm.$setPristine();
          											});

          						}
          					
              					
              					
              					
              					
              					
              					
              					//listVerifRempli: teste sur le nbrColi, si >0 => oui sinon non
              					$scope.listVerifRempli = [{value:"oui",designation:"Oui"},{value:"non",designation:"Non"}];
              					
              					//listeChoixRouleauCache
              					$scope.listeChoixRouleauCache = function() {
              						$http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeChoixRouleauCache").success(
              								function(data) {
              									$scope.listeChoixRouleauCache = data;
              									$log.debug("listeChoixRouleauCache : "+data.length);
              								});
              					}
              		// Liste refMise from Rouleaufini (avec bonSortieId= null)
                  $scope.listeRefMise = function() {
                    $http
                        .get(UrlAtelier + "/rouleaufini/getListRefMiseRouleauNonSortie")
                        .success(
                            function(data) {
                              $log.info("listeRefMise : "+data.length);
                              $scope.listeRefMiseREF = data;
                            });
                  }



    							// Liste des PartieInteressee (avec FamilleId=1)
    							$scope.listeClientCache = function() {
    								$http
    										.get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
    										.success(
    												function(dataPartieInteressee) {

    													$scope.listeClientCache = dataPartieInteressee;
    												});
    							}
    							
              					// Liste des Produits
              					$scope.listeProduit = function() {
              						$http.get(UrlCommun + "/produit/all").success(
              								function(data) {
              									$log.debug("listeProduitCache "+data.length);
              									$scope.listeProduit = data;
              									// Liste SousFamilleProduitCache
              	    								$http.get(UrlCommun + "/gestionnaireCache/listeSousFamilleProduitCache")
              	    									 .success(
              	    											 function(dataSousFamilleProduitCache) {
              	    													$log.debug(" listeSousFamilleProduitCache : "
              	    																	+ dataSousFamilleProduitCache.length);
              	    													$scope.listSousFamilleProduitCache = dataSousFamilleProduitCache;

              	    												});

              								});
              					}
              					$scope.listeRefMise();
              					$scope.listeProduit();
              					$scope.listeClientCache();
              					$scope.listeChoixRouleauCache();
	              				
              					/**
	              				 * saisie des codes à barre avec un PDA
	              				 */
              					$scope.csv = {
              					      content: null,
              					      header: false,
              					      headerVisible: true,
              					      separator: ';',
              					      separatorVisible: true,
              					      result: null,
              					      encoding: 'ISO-8859-1',
              					      encodingVisible: true,
              					    };
              					
              					    var _lastGoodResult = '';
              					    $scope.toPrettyJSON = function (json, tabWidth) {
              					    	$log.info("--toPrettyJSON : "+JSON.stringify(json)+" tabWidth : "+tabWidth);
              					      var objStr = JSON.stringify(json);
              					      var obj = null;
              					      try {
              					        obj = $parse(objStr)({});
              					      } catch(e){
              					        // eat $parse error
              					        return _lastGoodResult;
              					      }

              					      var result = JSON.stringify(obj, null, Number(tabWidth));
              					      _lastGoodResult = result;

              					      return result;
              					    };
              					    
              					  //reload du resultat CSV, recuperation de tous les references des BS et les affecter à la liste des codes
              					  $scope.$watch(
              		                    "csv.result",
              		                    function handleCSVResultChange( newValue, oldValue ) {
              		                        $log.debug( "***$scope.csv.result:", JSON.stringify(newValue,null, "  ") );
              		                        $log.debug("---- MODE : "+ $scope.modePdf);
              		                      
              		                        //ListeCode à barre correspondante à ce BonSortie
											angular.forEach(newValue, function(ligneCsv, key){
												$log.debug("-- ligneCsv .reference "+ligneCsv[1]);
												
												 //show btn Valider
		              							  $scope.modeValider = true ;
		              							 //bouton PDF activé : mode modification , else : mode creation
		              							  if( $scope.modePdf == "actif"){
		              								 //ajouter les codes à barres qui sont identique à ce BS
		              								 if(ligneCsv[0] === $scope.bonSortieCourant.reference){
		              									//increment nbr de colis si le code à barre n'existe pas dans listeCode
				              							 if($scope.listCode.indexOf(ligneCsv[1]) == -1){
				              								//add the bareCodes entred to the liste 
				                  							$scope.listCode.push(ligneCsv[1]);
				                  							//inc nbrColi
				              								$scope.nbrColis ++;
				              							  }else{
				              								$log.debug(ligneCsv[1] +"Existe");
				              							  }
				              							   //init field codeBarre
				              							   $scope.codeBarre = "";
		              								 }else{
		              									 $log.error("ligneCsv[0] != $scope.bonSortieCourant.reference")
		              								 }
		              							  }else{
		              								//increment nbr de colis si le code à barre n'existe pas dans listeCode
			              							 if($scope.listCode.indexOf(ligneCsv[1]) == -1){
			              								//add the bareCodes entred to the liste 
			                  							$scope.listCode.push(ligneCsv[1]);
			                  							//inc nbrColi
			              								$scope.nbrColis ++;
			              							  }else{
			              								$log.debug(ligneCsv[1] +"Existe");
			              							  }
			              							   //init field codeBarre
			              							   $scope.codeBarre = "";
		              							  }
		              							 
												
											});
              		                        
              		                    }
              		                );
              					    
              					/**
              					 *  saisie des codes à barre et clique sur "Enter" 
              					 */
              					$scope.listCode = [];
              					$scope.nbrColis = 0;
              					$scope.modeValider = false;
              					$scope.keyPress = function(keyCode, code){
              						 
              						   if(keyCode == '13'){
              							   //show btn Valider
              							   $scope.modeValider = true ;
              							   //increment nbr de colis si le code à barre n'existe pas dans listeCode
              							  // $log.debug("ListeCode ="+JSON.stringify($scope.listCode,null,"  ") );
              							   if($scope.listCode.indexOf(code) == -1){
              								   //add the bareCodes entred to the liste 
                  							   $scope.listCode.push(code);
                  							   //inc nbrColi
              								   $scope.nbrColis ++;
              							   }else{
              								   $log.debug(code +"Existe");
              							   }
              							   //init field codeBarre
              							   $scope.codeBarre = "";
              						   }
              						}
              					
              					/**
              					 * validerCode: en cliquant sur ce bouton 'Valider', le tableau complet s'affiche.  view2 activée
              					 */ 
              					$scope.displayView = "view1";
              					$scope.validerCode = function(){
              						$log.debug(" Valider ..");
              						$log.debug("*-- listCode : "+JSON.stringify($scope.listCode, null, "    ") );
              						
              						$scope.modeValider = true;
              						
              						//idBonSortie: si undefined -> urlValier SANS id, sinon -> urlValier AVEC idBonSortie
              						
              						if(angular.isDefined($scope.idBonSortie)){
                                    	if($scope.idBonSortie != ''){
                                    		
	                                 	   //Url With idBonSortie
                                    		$scope.urlValider =  UrlAtelier+ "/bonsortiefini/validateBonSortieFini?id="+$scope.idBonSortie;
	                                 	   $log.debug("-- urlValider Avec idBonSortie : "+ $scope.urlValider );
	                                 	
	                                 	   //Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne soont PAS affectés au BonSortie Auparavant.
	                                       $http
	                                              .post(
	                                           		  $scope.urlValider,$scope.listCode)
	                						.success(
	                								function(resultat) {
	                									//listeRouleauFini
	                									$scope.listeRouleauFini = resultat;
	                									//inc nbrColi
	                 								   $scope.nbrColis =resultat.length;
	                 								   
	                									$log.debug("listeRouleauFini : "+ resultat.length);
	                									$log.debug("-- listeRouleauFini : "+JSON.stringify($scope.listeRouleauFini, null, "    ") );
	                								});
                                    	}else{
                                    		//IDVIDE
											$log.debug("$scope.idBonSortie vide! ");
											


													
											//Url With idBonSortie
											$scope.urlValider = UrlAtelier + "/bonsortiefini/validateBonSortieFini";
											//$log.debug("-- urlValider Sans idBonSortie : "+ $scope.urlValider );
										
											//Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne soont PAS affectés au BonSortie Auparavant.
										  $http
												 .post(
														$scope.urlValider,$scope.listCode)
										   .success(
												   function(resultat) {
													   //listeRouleauFini
													   $scope.listeRouleauFini = resultat;
													   //inc nbrColi
													  $scope.nbrColis = resultat.length;
									  
  
													   $log.debug("listeRouleauFini : "+ resultat.length);
													   $log.debug("-- listeRouleauFini : "+JSON.stringify($scope.listeRouleauFini, null, "    ") );
												   });
                                    	}
                                    }else{
                                		//Url With idBonSortie
                  						$scope.urlValider = UrlAtelier + "/bonsortiefini/validateBonSortieFini";
                  						$log.debug("-- urlValider Sans idBonSortie : "+ $scope.urlValider );
                  					
                  						//Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne soont PAS affectés au BonSortie Auparavant.
                                        $http
                                               .post(
                                            		  $scope.urlValider,$scope.listCode)
                 						.success(
                 								function(resultat) {
                 									//listeRouleauFini
                 									$scope.listeRouleauFini = resultat;
                 									//inc nbrColi
                  								  $scope.nbrColis = resultat.length;
                                    

                 									$log.debug("listeRouleauFini : "+ resultat.length);
                 									$log.debug("-- listeRouleauFini : "+JSON.stringify($scope.listeRouleauFini, null, "    ") );
                 								});
                                	}
                                  
              						$scope.displayMode = "edit";
              						$scope.displayView = "view2";
              						
              					}
              					//Choix
              					$scope.choixId = {

                  						status : ''
                  					};
              					$scope.showChoix = function(id){
              						$scope.choixId.status = id;
              						var selected = $filter('filter')(
              								$scope.listeChoixRouleauCache, {
              									id : $scope.choixId.status
              								});
              						if ($scope.choixId.status && selected.length) {
              							return selected[0].designation;
              						}else{
              							return "Not Set";
              						}
              						
              					}
              				
              					//TypeBS
              					$scope.typeBSId = {

                  						status : ''
                  					};
              					$scope.showTypeBS = function(id){
              						$scope.typeBSId.status = id;
              						var selected = $filter('filter')(
              								$scope.listeTypeBS, {
              									id : $scope.typeBSId.status
              								});
              						if ($scope.typeBSId.status && selected.length) {
              							return selected[0].designation;
              						}else{
              							return "Not Set";
              						}
              						
              					}
              					/***************************************************
    							 * Gestion des Rouleaux
    							 **************************************************/
              					$scope.deleteValue="oui";
              					//Annuler Ajout
              					$scope.cancelAddRouleau = function(rowform, index, id, designation, liste){
  						    	//$log.debug("* Designation "+liste[0].designation);
  						    		  if (angular.isDefined(id)) {
  						    				  $log.debug("DEF ID");
  						    				  $scope.deleteValue="non";
  						    				  rowform.$cancel();
  						    				  $log.debug("CANCEL");
  						    		  }else{	
  						    			  $log.debug("UND ID  "+id);
  						    			  if(designation ==""){
  						    				  $scope.deleteValue=="oui"
  						    				  $log.debug("Designation : "+reference);
  						    				  $log.debug("$scope.deleteValueOUI : "+$scope.deleteValue);
  						    				  liste.splice(index, 1);
  									    	  rowform.$cancel();
  						    				  $log.debug("DELETE")
  						    			  }else{
  						    				  $log.debug("Designation :"+reference);
  						    				  $log.debug("$scope.deleteValueNON : "+$scope.deleteValue);
  						    				  rowform.$cancel();
  						    				  $log.debug("CANCEL");
  						    			  }
  						    		}
  						    		  $scope.deleteValue="oui";
  						    }
              					
    							// Enregistrer Rouleau
    							$scope.saveRouleau = function( data, id, index) {
    								
    								//$log.debug("---metrageNew: "+data.metrage +" metrageOld: "+ $scope.listeRouleauFini[index].metrage+" index "+index);
    								$scope.deleteValue = "non";
    								if (parseInt(data.metrage) > parseInt($scope.listeRouleauFini[index].metrage) ) {
    									$scope.showPopupDelete(2);
    									$scope.rowform.$cancel();
    									return;
    								} else {
    									$scope.isModifie = true;
    									angular.extend(data, {
    										id : id
    									});
    								}
    							};

    							// Supprimer Rouleau
    							$scope.removeRouleau = function(index) {
    								$scope.listeRouleauFini.splice(index, 1);
    								//dec nbrColi 
   								   $scope.nbrColis --;
    							};
    							/** Fin de gestion des Rouleaux */
              					/***************************************************
              					 * Gestion des Bons de Sortie
              					 **************************************************/
              					
              				    $scope.pagingOptions = {
              							pageSizes : [ 5, 10, 13, 65,130 ],
              							pageSize : 65,
              							currentPage : 1
              						};

//              						// Lister les BonSortie
              						$scope.rechercherBonSortie = function(bonSortieCourant) {
              							$http.post(
              											UrlAtelier
              													+ "/bonsortiefini/rechercheMulticritere",
              											bonSortieCourant)
              									.success(
              											function(resultat) {
              												$scope.myData = resultat.list;
              												// Pagination du resultat de
              												// recherche
              												// data, page,pageSize
              												$scope.setPagingData(
              																$scope.myData,
              																$scope.pagingOptions.currentPage,
              																$scope.pagingOptions.pageSize);

              												$log.debug("listeBonSortie : "+ resultat.list.length);
              												
              												$scope.rechercheBonSortieForm.$setPristine();
              											});

              						}
//              					
              						// annuler Recherche
              						$scope.annulerAjout = function() {
	
	                              $scope.traitementEnCours = "false";


              							$scope.modePdf = "notActive";
              							$scope.bonSortieCourant = {
					              									"reference": "",
					              									"type": "",
					              									"partieInt": null,
					              									"dateSortieMin": "",
					              									"dateSortieMax": ""
					              									};

                            $scope.bonSortieCourant.dateSortie = new Date();
              							$scope.nbrColis = 0;
              							$scope.listCode = [];
              							$scope.modeValider = false;
              							$scope.listeRouleauFini = [];
              							$scope.codeBarre = "";
              							$scope.idBonSortie = '';
                            //Pavet SolderMise ne s'affiche pas au demarrage de la page
                            $scope.isCollapsed = true;
              							$scope.rechercherBonSortie({});
              							$scope.rechercheBonSortieForm.$setPristine();
              							$scope.creationBonSortie.$setPristine();
              							$scope.displayMode = "list";
              							$scope.displayView = "view1";

                                       


              						}
              						
              						//
              						// ** Ajout BonSortie
              						$scope.AffectationBonSortie = function(bonSortie) {
              							$scope.bonSortieCourant = {};
              							$scope.bonSortieCourant = bonSortie ? angular
              									.copy(bonSortie) : {};

                                       $scope.bonSortieCourant.dateSortie = new Date();

              							
              						//mode edit activé
              							$scope.displayMode = "edit";
              						
              						// show tableau Code à Barre
              							$log.debug("mode : Code à Barre");
          								$scope.displayView = "view1";
              						}
              						
              						// Ajout et Modification BonSortie
              						$scope.modifierOuCreerBonSortie = function(index) {
              							
              							// bouton PDF activé
              							$scope.modePdf = "actif";
              							
              							var index = this.row.rowIndex;
              							
              							//idBonSortie: va etre affecté à l'Url du service Valider en cas de modification 
              					    	$scope.idBonSortie = $scope.myData[index].id;
              					    	$log.debug("idBonSortie "+$scope.idBonSortie);
              							
              							// getBonSortie
              							$http
              									.get(
              											UrlAtelier
              													+ "/bonsortiefini/getBonSortieFiniById:"
              													+ $scope.myData[index].id)
              									.success(
              											function(datagetBonSortie) {
              												// Nbre Colis de ce bon de sortie
              												$scope.nbrColis = datagetBonSortie.nbColis; 
              												//Liste de rouleaux correspendantes à ce bon de sortie
              												$scope.listeRouleauFini = datagetBonSortie.listeRouleauFini;
              												//ListeCode à barre correspondante à ce BonSortie
              												angular.forEach($scope.listeRouleauFini, function(rouleauFini, key){
              													$scope.listCode.push(rouleauFini.reference); 
              												});
              												$scope.creationBonSortie.$setPristine();
              												$scope.myData[index].listeRouleauFini = $scope.listeRouleauFini;

              											});

              							$scope.bonSortieCourant = $scope.myData[index] ? angular
              									.copy($scope.myData[index])
              									: {};
              						
              						// mode edit activé	
              							$scope.displayMode = "edit";
              						// show codeABarre
              							$log.debug("mode : codeABarre");
          								$scope.displayView = "view1";
          							//modeValider true
          								$scope.modeValider = true;
              						}

              						// Sauvegarder bonSortie
              						$scope.sauvegarderBonSortie = function(bonSortie) {
              							$log.debug("-----$scope.csv.result;" +$scope.csv.result);
              							$log.debug("Sauvegarder Modification" + bonSortie.id);
              							if (angular.isDefined(bonSortie.id)) {
              								$scope.updateBonSortie(bonSortie);
              							} else {
              								$log.debug("Sauvegarder Ajout" + bonSortie.reference);
              								$scope.creerBonSortie(bonSortie);
              							}
              						
              							//$scope.rechercherBonSortie({});
              						}

              						// Mise à jour des BonSorties
              						$scope.updateBonSortie = function(bonSortie) {
	
                                   	$scope.traitementEnCours = "true";
              							
          								// si le metrage est modifié, l'attribut rouleauFini.metrageModif prend  'true', sinon 'false'. 
              							angular.forEach($scope.listeRouleauFini, function(rouleauFini, key){
	              							if($scope.isModifie == true ){
	              								rouleauFini.metrageModif = true;
	              							}else{
	              								rouleauFini.metrageModif = false;
	              							}
              							});
              							
              							bonSortie.nbColis = $scope.nbrColis;
              							bonSortie.listeRouleauFini = $scope.listeRouleauFini;

              							//$log.debug("-- OBJ modifié : "+JSON.stringify(bonSortie, null, "    ") );
              							
              							$http
              									.post(
              											UrlAtelier
              													+ "/bonsortiefini/updateBonSortieFini",	bonSortie)
              									.success(
              											function(bonSortieModifiee) {

              												for (var i = 0; i < $scope.myData.length; i++) {

              													if ($scope.myData[i].id == bonSortieModifiee.id) {

              														$scope.myData[i] = bonSortieModifiee;
              														break;
              													}
              												}

              											//getBonSortie 
        													$http.get(UrlAtelier + "/bonsortiefini/getBonSortieFiniById:"
        																		 + bonSortieModifiee)
        															.success(
        																	function(dataGetBonSortie) {
        																		// show codeABarre
        								              							$log.debug("mode : codeABarre");
        								          								$scope.displayView = "view1";
        								          								//modeValider true
        								          								$scope.modeValider = true;
        																		
        								          								$log.debug("listCode "+ JSON.stringify($scope.listCode,null," "))
        								          								//vider la liste et la remplacer par la liste Reelle des Rouleaux
        								          								$scope.listCode = [];
        								          								//ListeCode à barre correspondante à ce BonSortie
        			              												angular.forEach(dataGetBonSortie.listeRouleauFini, function(rouleauFini, key){
        			              													$scope.listCode.push(rouleauFini.reference); 
        			              												});
        			              												
        								          								// Attributs de Recherche
        																		$scope.bonSortieCourant = dataGetBonSortie ? angular
        								              									.copy(dataGetBonSortie)
        								              									: {};

                                                                                  $scope.traitementEnCours = "false";
        																	});
              												//$scope.annulerAjout ();
              											});
              						}

              						// Création BonSortie
              						$scope.creerBonSortie = function(bonSortie) {
	
		                                $scope.traitementEnCours = "true";
          								
              							// si le metrage est modifié, l'attribut rouleauFini.metrageModif prend  'true', sinon 'false'. 
              							angular.forEach($scope.listeRouleauFini, function(rouleauFini, key){
	              							if($scope.isModifie == true ){
	              								rouleauFini.metrageModif = true;
	              							}else{
	              								rouleauFini.metrageModif = false;
	              							}
              							});
              							
              							bonSortie.nbColis = $scope.nbrColis;
              							bonSortie.listeRouleauFini = $scope.listeRouleauFini;

              						//	$log.debug("-- OBJ cree : "+JSON.stringify(bonSortie, null, "    ") );
              							
              							$http.post(UrlAtelier + "/bonsortiefini/createBonSortieFini", bonSortie)
              								 .success(
              									function(newBonSortie) {
              									//idBonSortie : valider avec idBonSortie
              			  						$scope.idBonSortie = newBonSortie;
              										
              			  							$log.debug("Success Creation"+ bonSortie.designation);
              										
              										//getBonSortie 
              			  						$log.debug("getId : "+ newBonSortie );
													$http.get(UrlAtelier + "/bonsortiefini/getBonSortieFiniById:"
																		 + newBonSortie)
															.success(
																	function(dataGetBonSortie) {
																		
																		$log.debug("dataGetBonSortie : " + JSON.stringify(dataGetBonSortie,null,"  ") );
																		// bouton PDF activé
								              							$scope.modePdf = "actif";
								              							
																		// show codeABarre
								              							$log.debug("mode : codeABarre");
								          								$scope.displayView = "view1";
								          								//modeValider true
								          								$scope.modeValider = true;
								          								//vider la liste et la remplacer par la liste Reelle des Rouleaux
								          								$scope.listCode = [];
								          								//ListeCode à barre correspondante à ce BonSortie
			              												angular.forEach(dataGetBonSortie.listeRouleauFini, function(rouleauFini, key){
			              													$scope.listCode.push(rouleauFini.reference); 
			              												});
								          								
								          								// Attributs de Recherche
																		$scope.bonSortieCourant = dataGetBonSortie ? angular
								              									.copy(dataGetBonSortie)
								              									: {};
			
			
		                                                             	$scope.traitementEnCours = "false";
																	});
              									});
              						}

              						// Suppression BonSortie
              						$scope.supprimerBonsortie = function() {
              							
              							$http(
              									{
              										method : "DELETE",
              										url : UrlAtelier
              												+ "/bonsortiefini/deleteBonSortieFini:"
              												+ $scope.myData[$scope.index].id
              									}).success(function() {
              								$log.debug("Success Delete");
              								$scope.myData.splice($scope.index, 1);
              								$scope.closePopupDelete();
              								//$scope.$apply();
              							});
              							$scope.closePopupDelete();
              							$scope.rechercherBonSortie({});
              						}
              					
              		  /*** PDF ***/
                      //generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
                      $scope.download = function(id, avecMise ) {

                              if(avecMise == 0)
                                {avecMise = "non";}
                              else if(avecMise == 1)
                                {avecMise = "oui";}

        								
        								var url = UrlAtelier+"/report/bonsortiefini?id=" + id+"&avecMise="+avecMise+"&type=pdf";
        								downloadService.download(url)
        										.then(
        												function(success) {
        													$log.debug('success : '+ success);
        													
        												},
        												function(error) {
        													$log.debug('error : '+ error);
														});
														

														var a = document.createElement('a');
														document.body.appendChild(a);
														downloadService.download(url).then(function (result) {
															var heasersFileName = result.headers(['content-disposition']).substring(17);
														var fileName = heasersFileName.split('.');
													var typeFile = result.headers(['content-type']);
													var file = new Blob([result.data], {type: typeFile});
													var fileURL = window.URL.createObjectURL(file);
													if(typeFile == 'application/vnd.ms-excel'){
							
													 // a.href = fileURL;
														 a.download = fileName[0];
														$window.open(fileURL)
														 a.click();
									
													}else{
												
														a.href = fileURL;
														a.download = fileName[0];
													 $window.open(fileURL)
														a.click();
									
													}
														
													$scope.traitementEnCoursGenererLivraison="false";
				
													});



        							};
        							
        							//generer rapport de tous les bons de sortie. mode : List 

                      //conversion date en String
                        function formattedDate(date) {
                            var d = new Date(date),
                                month = '' + (d.getMonth() + 1),
                                day = '' + d.getDate(),
                                year = d.getFullYear();

                            if (month.length < 2) month = '0' + month;
                            if (day.length < 2) day = '0' + day;
                            return [year,month,day].join('-');
                        }
        							 $scope.downloadAllBonSortie = function(bonSortieCourant) {

                          var newdateSortieMinFormat="";
                          if(angular.isDefined(bonSortieCourant.dateSortieMin)){
                            $log.debug("==dateSortieMin "+bonSortieCourant.dateSortieMin);
                            
                            if(bonSortieCourant.dateSortieMin != ""){
                              newdateSortieMinFormat = formattedDate(bonSortieCourant.dateSortieMin);
                              $log.info("===== newdateSortieMinFormat "+newdateSortieMinFormat);
                            }else{
                              $log.info("===== newdateSortieMinFormat is Null");
                              newdateSortieMinFormat = "";
                            }
                          }else{
                            $log.debug("==dateSortieMin Undefined");
                          }

                          var newdateSortieMaxFormat="";
                          if(angular.isDefined(bonSortieCourant.dateSortieMax)){
                            $log.debug("==dateSortieMax "+bonSortieCourant.dateSortieMax);
                            
                            if(bonSortieCourant.dateSortieMax != ""){
                              newdateSortieMaxFormat = formattedDate(bonSortieCourant.dateSortieMax);
                              $log.info("===== newdateSortieMaxFormat "+newdateSortieMaxFormat);
                            }else{
                              $log.info("===== newdateSortieMaxFormat is Null");
                              newdateSortieMaxFormat = "";
                            }
                          }else{
                            $log.debug("==dateSortieMax Undefined");
                          }

        								 $log.debug("-- bonSortieCourant" + JSON.stringify($scope.bonSortieCourant, null, "  ") );
        								 var url;
        									if($scope.bonSortieCourant.partieInt == null){
                           
        										var url = UrlAtelier + "/report/listbonsortie?reference=" + bonSortieCourant.reference
                        												 + "&dateSortieMin="+newdateSortieMinFormat
                        												 + "&dateSortieMax="+newdateSortieMaxFormat
                        												 + "&typeBonSortie="+bonSortieCourant.type
                        												 + "&partieInt="
                        												 +"&rempli="+bonSortieCourant.rempli
                        												 + "&type=pdf";
                          }else{
                            var url = UrlAtelier + "/report/listbonsortie?reference=" + bonSortieCourant.reference
                        												 + "&dateSortieMin="+newdateSortieMinFormat
                        												 + "&dateSortieMax="+newdateSortieMaxFormat
                        												 + "&typeBonSortie="+bonSortieCourant.type
                        												 + "&partieInt="+bonSortieCourant.partieInt
                        												 +"&rempli="+bonSortieCourant.rempli
                        												 + "&type=pdf";
                          }

        								 downloadService.download(url).then(
        										 function(success) {
        											 $log.debug('success : ' + success);
        										 }, function(error) {
        											 $log.debug('error : ' + error);
        										 });
        							 };
              						
                      /***************************************************
                       * Paver SolerMise
                       **************************************************/
                      $scope.verifierRefMise = function(refrenceMise){

                        $http
                        .get(UrlAtelier + "/rouleaufini/getListCodeBarreByRefMiseAndIdBSisNull2?refMise="+refrenceMise)
                        .success(
                            function(data) {
                              $log.info('ListCodeBarreFromMise : ' + data);
                              $scope.listeCodeBarreFromMise = data;
                              $scope.nbRouleauFiniRestant = data.length;
                            });

                      }

                      $scope.solderCode = function(){

                        var L1 = $scope.listeCodeBarreFromMise; //["aa","vv","cc"];
                        var L2 = $scope.listCode;//["ee","gg","aa", "rr", "cc","zz"];

                        var trouve = false;
                        var listToAdd = [];
                        for(var i = 0; i<= L1.length; i++){
                          for(var y = 0; y<= L2.length; y++){
                            if(L1[i] == L2[y]){
                              L1.splice(i, 1);
                              trouve = true;
                            }else{
                              trouve = false;
                            }
                            
                          }
                          if(trouve == false){
                            listToAdd.push(L1[i]);
                          }
                        }

                        angular.forEach(listToAdd, function(newCodeBarre, key){
                          $scope.listCode.push(newCodeBarre);
                        });
                        
                        $log.info(" listToAdd : "+JSON.stringify(listToAdd, null, "  "));
                      }
              					/***************************************************
              					 * Gestion des Grids de recherche
              					 **************************************************/
              						
              					 $scope.colDefs = [];
              					$scope.$watch('myData',function() {
              							$scope.colDefs = [
              								{
              									field : 'reference',
              									displayName : 'Réf.BS',
                                width:'10%'
              								},
              				/*				{
              									field : 'type',
              									displayName : 'Type',
                                width:'10%'
              									
              								},*/
              								{
              									field : 'partieIntDesignation',
              									displayName : 'Client',
                                width:'50%'
              								},
              								{
              									field : 'dateSortie',
              									displayName : 'Date Sortie',
              									cellFilter: 'date:"dd-MM-yyyy"',
                                width:'10%'
              								},
              								{
              									field : 'nbColis',
              									displayName : 'Nbre.Colis',
                                width:'10%'
              								},
              								{
              									field : 'metrageTotal',
              									displayName : 'Qte Total',
                                width:'10%'
              								},
              								{
              									field : '',
                                width:'10%',
												  cellTemplate :
												  `<div class="ms-CommandButton float-right"  ng-show="!rowform.$visible" >
																<button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerBonSortie()">
															<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
															</button>
																<button class="ms-CommandButton-button"  ng-click="showPopupDelete(4)" permission="['Production_Expedition_Delete']">
																<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
																</button>
																</div>`,
														
														
															} ];
              									});
              								$scope.filterOptions = {
              									filterText : "",
              									useExternalFilter : true
              								};

              								$scope.totalServerItems = 0;

              								$scope.setPagingData = function(data, page,
              										pageSize) {
              									var pagedData = data.slice((page - 1)
              											* pageSize, page * pageSize);
              									$scope.myData = pagedData;
              									$scope.totalServerItems = data.length;
              									if (!$scope.$$phase) {
              										$scope.$apply();
              									}
              								};
//**************************************************
              								
              								
              								$scope.getPagedDataAsync = function(pageSize, page,
              										searchText) {
              									setTimeout(
              											function() {
              												var data;
              												var bonSortieCourant  = $scope.bonSortieCourant;
              												if (searchText) {
              													var ft = searchText.toLowerCase();
//              		              								$scope.bonSortieCourantS = {"type" : "Expédition"};

              													$http
              															.post(
              																	UrlAtelier
              																	+ "/bonsortiefini/rechercheMulticritere",$scope.bonSortieCourantS)
              															.success(
              																	function(
              																			largeLoad) {
              																		data = largeLoad.list
              																				.filter(function(item) {
              																					return JSON.stringify(item)
              																							.toLowerCase()
              																							.indexOf(ft) != -1;
              																				});
              																		$scope.setPagingData( data,	page, pageSize);
              																	});

              												} else {
              													
              													$http
              															.post(
              																	UrlAtelier
              																	+ "/bonsortiefini/rechercheMulticritere", $scope.bonSortieCourantS)
              															.success(
              																	function(largeLoad) {
              																		$scope.setPagingData(
              																						largeLoad.list,
              																						page,
              																						pageSize);
              																	});
              												}
              											}, 100);
              								};

              								$scope.getPagedDataAsync(
              										$scope.pagingOptions.pageSize,
              										$scope.pagingOptions.currentPage);

              								$scope
              										.$watch(
              												'pagingOptions',
              												function(newVal, oldVal) {
              													if (newVal !== oldVal
              															&& newVal.currentPage !== oldVal.currentPage) {
              														$scope
              																.getPagedDataAsync(
              																		$scope.pagingOptions.pageSize,
              																		$scope.pagingOptions.currentPage,
              																		$scope.filterOptions.filterText);
              													}
              												}, true);
              								$scope.$watch('filterOptions', function(newVal,
              										oldVal) {
              									if (newVal !== oldVal) {
              										$scope.getPagedDataAsync(
              												$scope.pagingOptions.pageSize,
              												$scope.pagingOptions.currentPage,
              												$scope.filterOptions.filterText);
              									}
              								}, true);

              								$scope.gridOptions = {
              									data : 'myData',
              									columnDefs : 'colDefs',
              									enablePaging : true,
              									showFooter : true,
                                enableHighlighting : true,
              									totalServerItems : 'totalServerItems',
              									pagingOptions : $scope.pagingOptions,
              									selectedItems : $scope.selectedRows,
              									filterOptions : $scope.filterOptions,
              								};
              					/** Fin de gestion des Grids de la BonSortie */
              					
              				} ])
              .filter('showProduitFilterBS', function() {
				  return function(listeProduit, id) {
					 var listeProduitFiltre = [];
					// $log.debug("IdProduitt=  "+id.id);
					// $log.debug("listeProduit "+ JSON.stringify(listeProduit, null, "    "));
					 angular.forEach(listeProduit, function(produit, key){
						
						if(produit.id == id.id){
							//$log.debug(produit.id +" == "+ id.id);
							listeProduitFiltre.push(produit);
						}
							
					 });
					// $log.debug(" ** listeProduitFiltre "+ JSON.stringify(listeProduitFiltre, null, "    "));
					 return listeProduitFiltre;
				  };
				})
			 .filter('filterSousFamilleBS', function() {
				  return function(listeSousFamille, id) {
					 var listeSousFamilleFiltre = [];
					// $log.debug("IdSousFamille=  "+id.id);
					// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
					 angular.forEach(listeSousFamille, function(sousFamille, key){
						
						if(sousFamille.id == id.id){
							//$log.debug(sousFamille.id +" == "+ id.id);
							listeSousFamilleFiltre.push(sousFamille);
						}
							
					 });
					// $log.debug(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
					 return listeSousFamilleFiltre;
				  };
				})
       .directive('datepickerLocalDate', ['$parse', function ($parse) {
        var directive = {
            restrict: 'A',
            require: ['ngModel'],
            link: link
        };
        return directive;
 
        function link(scope, element, attr, ctrls) {
            var ngModelController = ctrls[0];
 
            // called with a JavaScript Date object when picked from the datepicker
            ngModelController.$parsers.push(function (viewValue) {
                // undo the timezone adjustment we did during the formatting
                viewValue.setMinutes(viewValue.getMinutes() - viewValue.getTimezoneOffset());
                // we just want a local date in ISO format
                return viewValue.toISOString().substring(0, 10);
            });
 
            // called with a 'yyyy-mm-dd' string to format
            ngModelController.$formatters.push(function (modelValue) {
                if (!modelValue) {
                    return undefined;
                }
                // date constructor will apply timezone deviations from UTC (i.e. if locale is behind UTC 'dt' will be one day behind)
                var dt = new Date(modelValue);
                // 'undo' the timezone offset again (so we end up on the original date again)
                dt.setMinutes(dt.getMinutes() + dt.getTimezoneOffset());
                return dt;
            });
        }
    }])
              .controller(
              		'DateIntroCtrl',
              		[
              				'$scope',
              				function($scope) {
              					$scope.maxToDay = new Date();
              					// date piker defit
//              					$scope.today = function() {
//              						$scope.articleCourante.dateIntroduction = new Date();
//              					};
//              					$scope.today();
              					$scope.clear = function() {
              						$scope.articleCourante.dateIntroduction = null;
              					};
              					// Disable weekend selection
              					$scope.disabled = function(date, mode) {
              						return (mode === 'day' && (date.getDay() === 0 || date
              								.getDay() === 6));
              					};
              					$scope.toggleMin = function() {
              						$scope.minDate = $scope.minDate ? null
              								: new Date();
              					};
              					$scope.toggleMin();
              					$scope.open = function($event) {
              						$event.preventDefault();
              						$event.stopPropagation();
              						$scope.opened = true;
              					};
              					$scope.dateOptions = {
              						formatYear : 'yy',
              						startingDay : 1
              					};
              					$scope.initDate = new Date('20/08/2015');
              					 $scope.formats = ['dd-MMMM-yyyy', 'dd/MM/yyyy', 'dd.MM.yyyy', 'shortDate'];
              					    $scope.format = $scope.formats[0];

              				} ])
