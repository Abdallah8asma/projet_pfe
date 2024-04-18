'use strict'

 angular
.module('atelier.lancementFacon', [ "ngResource" ])
.controller(
		'lancementFaconController',
		[
				'$scope',
				'$filter',
				'$http',
                '$log',
				'downloadService',
				'UrlCommun',
				'UrlAtelier',
				'FaconServices',
				'PIServices',
				'ProduitServices',
				'traitementFaconServices',
				'MethodeTeintureServices',
				'traitementFicheServices',
				function($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier,FaconServices, PIServices,ProduitServices,traitementFaconServices, MethodeTeintureServices, traitementFicheServices) {
					 
					$log.info("=========Facon========");
					
                            // Déclaration des variables globales utilisés
                            var data;
                            $scope.displayMode = "list";
                            //modePdf notActif
                            $scope.modePdf = "NotActif";
                            
                            // Objet de fiche à rechercher (contient les critères de recherche)
                            $scope.ficheCourante = {
                            		"produitId": "",
                            	    "partieIntId": "",
                            	    "refBonReception": "",
                            	    "listeTraitementsFiche": []
                            };
                            $scope.listeTraitementFacon=[];
                            
                         
                             /***************************************************
                             * Animations
                             **************************************************/
                            //Recette / Controle
                            $scope.animateArticleFourniture = function() {
                                   $("#articles").click(
                                                 function() {
                                                        $scope.openOrClose(
                                                                      'panel-articles',
                                                                      '#articles', 'hidePlus');
                                                 });
                                   $("#tissuBtn").click(
                                                 function() {
                                                        $scope.openOrClose('tissu',
                                                                      '#tissuBtn',
                                                                      'hidePlusTissu');
                                                 });
                                   $("#filBtn").click(
                                                 function() {
                                                        $scope.openOrClose('fil',
                                                                      '#filBtn', 'hidePlusFil');
                                                 });
                                   $("#fournituresBtn").click(
                                                 function() {
                                                        $scope.openOrCloseInv(
                                                                      'fournitures',
                                                                      '#fournituresBtn',
                                                                      'hidePlusFourniture');
                                                 });
                                   $("#preparationBtn").click(
                                                 function() {
                                                        $scope.openOrCloseInv(
                                                                      'preparations',
                                                                      '#preparationBtn',
                                                                      'hidePlusPreparation');
                                                 });
                            }

                            //Operations
                            $scope.animateOperations = function() {
                                   $("#operations").click(
                                                 function() {
                                                        $scope.openOrClose(
                                                                      'panel-operations',
                                                                      '#operations', 'hidePlus');
                                                 });

                                   $("#preparationOperationsBtn").click(
                                                 function() {
                                                        $scope.openOrCloseInv(
                                                                      'preparationsOperations',
                                                                      '#preparationOperationsBtn',
                                                                      'hidePlusPreparationOperations');
                                                 });
                                   $("#teintureOperationsBtn").click(
                                                 function() {
                                                        $scope.openOrCloseInv(
                                                                      'teintureOperations',
                                                                      '#teintureOperationsBtn',
                                                                      'hidePlusTeintureOperations');
                                                 });
                                   $("#finissageOperationsBtn").click(
                                                 function() {
                                                        $scope.openOrCloseInv(
                                                                      'finissageOperations',
                                                                      '#finissageOperationsBtn',
                                                                      'hidePlusFinissageOperations');
                                                 });

                                   
                            }

                            $scope.openOrClose = function(id_panel, id_div,
                                          classe) {
                                   $("div[id=" + id_panel + "]").slideToggle(
                                                 "slow");
                                   $(id_div).toggleClass(classe);
                                   if ($(id_div).hasClass(classe)) {
                                          $(id_div).text('+');
                                   } else {
                                          $(id_div).text('-');
                                   }
                            }
                            $scope.openOrCloseInv = function(id_panel, id_div,
                                          classe) {
                                   $("div[id=" + id_panel + "]").slideToggle(
                                                 "slow");
                                   $(id_div).toggleClass(classe);
                                   if ($(id_div).hasClass(classe)) {
                                          $(id_div).text('-');
                                   } else {
                                          $(id_div).text('+');
                                   }
                            }


                            $scope.animateArticleFourniture();
                            $scope.animateOperations();
                            
                            
                            /********************************
                             * Gestion des listes deroulantes
                             * TODO cache
                             ********************************/
                            
                            // Liste des produits
                            $scope.listeProduitCache = function() {
                                   $http.get(UrlAtelier + "/gestionnaireLogistiqueCache/listeProduitCache").success(
                                                 function(data) {
                                                        $log.debug("listeProduitCache "+data.length);
                                                        $scope.listeProduitCache = data;

                                                 });
                            }

                            // Liste des PartieInteressee (avec FamilleId=1)
                            $scope.listeClientCache = function() {
                                   $http
                                                 .get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
                                                 .success(
                                                               function(dataPartieInteressee) {
                                                                      $log.debug("listeClientCache " + dataPartieInteressee.length);
                                                                      $scope.listeClientCache = dataPartieInteressee;
                                                               });
                            }


                         // Liste des ref. bon reception (Hors cache)
                            $scope.listeBonReception = function() {
                            	FaconServices.getListeBonReception().then(function(resultat) {
                            		$scope.listeBonReception=resultat;
                				}, function(error) {
                					console.log(error.statusText);
                				});
                            }
                            
                         // Liste des méthode de teinture (hors cache)
                            $scope.listeMethodeTeinture = function() {
                            	MethodeTeintureServices.getlisteMethodeTeinture().then(function(resultat) {
                            		$scope.listeMethodeTeinture=resultat;
                				}, function(error) {
                					console.log(error.statusText);
                				});
                            }

                         // Liste des traitements façon (hors cache)
                            $scope.getListeTraitementFacon = function(){                            	
                            	traitementFaconServices.getListeTraitementFacon().then(function(listeTraitementFacon) {
                                 	$scope.listeTraitementFacon=listeTraitementFacon;                                     
                 				}, function(error) {
                 					console.log(error.statusText);
                 				}); 
                            }
                            
                            $scope.getListeTraitementFacon();
                            $scope.listeProduitCache();
                            $scope.listeClientCache();
                            $scope.listeBonReception();
                            $scope.listeMethodeTeinture();

                            /***************************************************
                             ******** Gestion des Fiches façon
                             **************************************************/
                            
                            // Lister les Fiches façon au click du bouton rechercher
                            
                            $scope.rechercherFiche = function(ficheCourante) {
                            	$log.debug("---------- Liste des fiches façon- table ---------");                         
                            	$log.debug("---Recherche ficheCourante:  "+JSON.stringify(ficheCourante, null, "  "));
                                  
                            	FaconServices.getResultatRechercheFiche(ficheCourante).then(function(resultat) {
                            		$log.debug("---Résultat Recherche ficheCourante:  "+JSON.stringify(ficheCourante, null, "  "));
                            		$scope.myData = resultat.list;
                            		
                            		 // Pagination du resultat de recherche
                                    // data, page,pageSize
                                    $scope.setPagingData(
                                                         $scope.myData,
                                                         $scope.pagingOptions.currentPage,
                                                         $scope.pagingOptions.pageSize);

                                    $log.debug("listeFicheFacon length: "+ resultat.list.length);
                                    
                                    $scope.rechercheFicheForm.$setPristine();
                                    
                				}, function(error) {
                					console.log(error.statusText);
                				});
                            	
                            }
                     

                            //Interface ajout fiche façon 
                            //annuler ajout FicheFacon
                            $scope.annulerAjout = function() {                              
                              //modePdf notActif
                              $scope.modePdf = "NotActif";
                              
                              $scope.ficheCourante = {};

                              //init des champs
                              $scope.creationFicheForm.$setPristine();
                              //$scope.traitementForm.$setPristine();
                              
                              //mode Recherche & Grid
                              $scope.displayMode = "list";
                            }
                                
                            // Interface principale fiche façon
                            //Annuler recherche
                            $scope.annulerRecherche= function(){
                            	//TODO init objetCourant 
                                $scope.ficheCouranteRecherche = {};
                            	$scope.rechercheFicheForm.$setPristine();
                            }
                            
                            // Interface principale fiche façon - btn ajouter
                            // Affectation FicheFacon
                            // Afficher l'interface d'ajout
                            $scope.affectationFicheFacon = function(ficheFacon) {

                                  $scope.ficheCourante = {};

                                  // Si ficheFacon= {} ==> Mode Ajout
                                  // Sinon ==> Mode Edit (ficheFacon récupéré à partir du grid)
                                  $scope.ficheCourante = ficheFacon ? angular
                                                  .copy(ficheFacon) : {};
                                                  
                                  //mode edit activé
                                   $scope.displayMode = "edit";

                            }

                                  
                            
                            // interface ajout fiche façon
                            //Lorsqu'on choisit un bon de reception, on remplit les champs 
                            // relatifs au produit et client
                            
                            $scope.remplirChamps = function(refBonReception) {
                                $scope.remplir = true;
                                $log.debug("--- refBonReception "+refBonReception);
                                                                
                                if(refBonReception != null){
                                	 FaconServices.geListeBonReceptionByReference(refBonReception).then(function(bonReception) {
                                     	
                                     	$log.debug("***bonReception : " + JSON.stringify(bonReception,null," "));
                                     	
                                     	//Récupérer le client à partir de l'id                                 
                                         
                                         PIServices.getPIById(bonReception.partieInteressee).then(function(objetClient) {
                                         	$scope.ficheCourante.partieIntId=objetClient.id;
                                         	$scope.ficheCourante.clientAbreviation=objetClient.abreviation;
                                         	$scope.ficheCourante.clientDesignation=objetClient.designation;
                                             
                         				}, function(error) {
                         					console.log(error.statusText);
                         				}); // Fin PIServices.getPIById()
                                         
                                         //Récupérer le produit à partir de l'id                                 
                                         
                                         ProduitServices.getProduitById(bonReception.produit).then(function(objetProduit) {
                                         	
                                        	 $scope.ficheCourante.produitId=objetProduit.id;
                                        	 $scope.ficheCourante.produitDesignation=objetProduit.designation;
                                        	 $scope.ficheCourante.produitReference=objetProduit.reference;
                                        	 $scope.ficheCourante.codeCouleur=objetProduit.codeCouleur;
//                                             
                         				}, function(error) {
                         					console.log(error.statusText);
                         				}); // Fin PIServices.getPIById()
                                         
                     				}, function(error) {
                     					console.log(error.statusText);
                     				}); // Fin FaconService.geListeBonReceptionByReference()
                                }
                               
                                
                            } // end 
                            
                            
                            
                             
                            /*$scope.ajouterTraitement = function() {
      					      $scope.TraitementCourant = {
      					        designation: ''
      					      };
      					      $scope.ficheCourante.listeTraitementsFiche.push($scope.TraitementCourant);
                            }*/
                            
                            // getRefMise Par RefBonReception 
//                            $scope.getRefMiseParRefBR = function(refBonReception) {
//                            	
//                                $log.debug("-- getRefMiseParRefBonReception refBonReception---\n "+ JSON.stringify(refBonReception, null, " "));
//                               
//                                $http
//                                .post(UrlAtelier + "/mise/listRefMiseParRefBR",refBonReception)
//                                .success(
//                                              function(data) {
//                                                     $log.debug("---getRefMiseParRefBR data--- " + JSON.stringify(data, null, " "));
//                                                     $scope.refMise = data ;
//                                                     $log.debug("---getRefMiseParRefBR  $scope.refMise--- " + JSON.stringify( $scope.refMise	, null, " "));
//
//                                              });  
//                            
//                            }
                            
                            //Update fiche façon - methode appelé dans le grid
                            $scope.modifierFicheFacon = function() {                           
                                  // bouton PDF activé
                                  $scope.modePdf = "actif";

                                  //index de l'obj selectionné
                                  var index = this.row.rowIndex;

                                  $log.debug("Modifier id: "+$scope.myData[index].id);
                                  $log.debug("Modifier id: "+JSON.stringify($scope.myData[index], null, " "));
                                  $log.debug("$scope.myData: "+ JSON.stringify($scope.myData, null, " "));
                                    
                                  var ligneAModifier = [];
//                                  $scope.refMise = {}
                                  ligneAModifier = $filter('filter')($scope.myData, {id: $scope.myData[index].id});
                                  
                                  $log.debug("ligneAModifier: "+ JSON.stringify(ligneAModifier, null, " "));
                                  $scope.ficheCourante=ligneAModifier[0];
//                                  $scope.refMise =  $scope.getRefMiseParRefBR( $scope.ficheCourante.refBonReception); 
                                  //listRefMiseParRefBR
                                  $http
                                  .post(UrlAtelier + "/mise/listRefMiseParRefBR",$scope.ficheCourante.refBonReception)
                                  .success(
                                                function(data) {
                                                       $log.debug("---getRefMiseParRefBR data--- " + JSON.stringify(data, null, " "));
                                                       $scope.refMise = data;
                                                       $log.debug("---getRefMiseParRefBR  $scope.refMise--- " + JSON.stringify( $scope.refMise	, null, " "));

                                                }); 
                                  

                                   // mode edit activé  
                                   $scope.displayMode = "edit";
                            }

                            // Interface ajout/edit 
                            // Sauvegarder Fiche Façon
                            $scope.sauvegarderFicheFacon = function(ficheCourante) {

                                   if (angular.isDefined(ficheCourante.id)) {
                                           $log.debug("Sauvegarder Modification : " + ficheCourante.id);
                                           $scope.updateFicheFacon(ficheCourante);
                                   } else {
                                           $log.debug("Sauvegarder Ajout : " + ficheCourante.id);
                                           $scope.creerFicheFacon(ficheCourante);
                                   }
                                                                     
                            }

                            // MiseAJour Fiche Façon 
                            $scope.updateFicheFacon = function(ficheCourante) {
                              
                                $log.debug("-- Fiche Façon à MAJ ---\n "+ JSON.stringify(ficheCourante, null, " "));
                                FaconServices.updateFicheFacon(ficheCourante).then(function(id) {
                            		$log.debug("id fiche façon" + id);
                            		
      						        //Update grid
      						        if(ficheCourante.id!= null){
      						        	for (var i = 0; i < $scope.myData.length; i++) {
      										if ($scope.myData[i].id == ficheCourante.id) {
      											$scope.myData[i] = ficheCourante;
      											break;
      										}
      									}
      						        }
      						        
                            		$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
                            		$scope.ficheCourante = {};
                            		$scope.annulerAjout();
                				  }, function(error) {
                					console.log(error.statusText);
                				  });  
                            
                            }
                           
                            // Création FicheFaçon
                            $scope.creerFicheFacon = function(ficheCourante) {
                              
                              //$log.debug("-- Fiche Façon à ajouter ---\n "+ JSON.stringify(ficheCourante, null, " "));
                              /*
                              for (var int = 0; int < ficheCourante.listeTraitementsFiche.length; int++) {
                            	  ficheCourante.listeTraitementsFiche[int].traitementId= ficheCourante.listeTraitementsFiche[int].id;
                            	 delete ficheCourante.listeTraitementsFiche[int].id;
							}*/
                              
                              $log.debug("-- Fiche Façon à ajouter ---\n "+ JSON.stringify(ficheCourante, null, " "));
                              FaconServices.createFicheFacon(ficheCourante).then(function(newId) {
                          		$log.debug("Nouveau id fiche façon" + newId);
                          		$scope.myData.push(ficheCourante);
                          		$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
                          		$scope.ficheCourante = {};
                          		$scope.annulerAjout();
              				  }, function(error) {
              					console.log(error.statusText);
              				  });  
                            }

                            // Interface principale - btn delete grid
                            // Suppression FcheFacon
                            $scope.supprimerFicheFacon = function() {
                            	
                            	FaconServices.supprimerFicheFacon($scope.myData[$scope.index].id).then(function(resultat) {
                            		$log.debug("Success Delete id :" + $scope.myData[$scope.index].id);
                                    $scope.myData.splice($scope.index, 1);
                                    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
                                    $scope.closePopupDelete();
                				}, function(error) {
                					console.log(error.statusText);
                				});
                            	   
                            }  

                          
                            // Interface ajout fiche façon
                            // Ajouter une ligne de traitement dans le tableau
                            $scope.AjouterTraitementFacon = function(){
                                if(angular.isDefined($scope.ficheCourante.listeTraitementsFiche)){
                                  $log.debug("defined =>> push");
                                  $scope.ficheCourante.listeTraitementsFiche.push(
                                                              { 
                                                            	  "traitementId": "",
                                                            	  "pu": ""
                                                              });
                                                              
                                }else{
                                  $log.debug("Undefined =>> Affectation");
                                  $scope.ficheCourante.listeTraitementsFiche = [
                                                              { 
                                                            	  "traitementId": "",
                                                            	  "pu": ""
                                                              }
                                                             ];
                                }

                              }

                            // Interface ajout fiche façon
                            //Supprimer une ligne de traitement dans le tableau
                            $scope.removeTraitement=function(indexX){
                            	$log.debug("indexX "+ indexX);
                            	$log.debug("FIche before delete " + JSON.stringify($scope.ficheCourante,null, " "));
                            	$log.debug("Delete traitement .. " + JSON.stringify($scope.ficheCourante.listeTraitementsFiche[indexX],null, " "));
                            	$scope.ficheCourante.listeTraitementsFiche.splice(indexX,1);
                            	$log.debug("FIche after delete " + JSON.stringify($scope.ficheCourante,null, " "));
                            }
                            


                            /*************************************************
                  			  ******* Set pu d'un traitement ajouté 
                  			  ************************************************/
                  			 
                  			 $scope.setPU = function(index, traitementId){
                  				 
                  				 $log.debug("----index --"+ index);
                  				 
	                  			 var ligneTraitement = [];
	                  			 
	                  			 ligneTraitement = $filter('filter') ($scope.listeTraitementFacon , {id : traitementId});
	                  			 
	                  			 $log.debug("-----ligneTraitement : filter ------"+ JSON.stringify(ligneTraitement, null, " "));
	                  			 
	                  			 $log.debug("-----$scope.ficheCourante ------"+ JSON.stringify($scope.ficheCourante, null, " "));
	                  			 
	                  			 if(ligneTraitement.length > 0){
	                  				$scope.ficheCourante.listeTraitementsFiche[index].pu = ligneTraitement[0].pu;
	                  			 }         			 
                  			 }
                  			 
                  			 
                          /*************************************
               			  ******* Prix traitement changed
               			  **************************************/
               			 
               			 $scope.prixTraitementChanged = function(index){
               				 
               				 var nouveauPrix = $scope.ficheCourante.listeTraitementsFiche[index].pu;
               				 var idTraitementFicheAChanger = $scope.ficheCourante.listeTraitementsFiche[index].id;
               				 var idFiche = $scope.ficheCourante.id;
               				 $log.debug("----id fiche---" + idFiche );
               				 
               				 if(nouveauPrix != null){
               					traitementFicheServices.setPUTraitementFiche(idTraitementFicheAChanger, nouveauPrix, idFiche).then(function(result) {
               	                  	$log.debug("Prix modifié avec succes");                                   
               	  				}, function(error) {
               	  					console.log(error.statusText);
               	  				}); 
               				 }
               			 }
                          
                             /*************************************************
                              * Pdf
                              *************************************************/

                            //generer rapport d'une fiche façon après enregistrement
                            $scope.download = function(id) {

                              $log.debug("-- id "+id);
                           
							var url = 


										+ "/reportLogistique/reportingFicheFacon?id="
										+ id
										+ "&type=pdf";
                              downloadService.download(url)
                                  .then(
                                      function(success) {
                                        $log.debug('success : '+ success);
                                      },
                                      function(error) {
                                        $log.debug('error : '+ error);
                                      });
                            };
                            
                            
                            
                            /***************************************************
                             * Gestion des Grids de recherche
                             **************************************************/
                            $scope.colDefs = [];
                            $scope.$watch('myData',function() {
                            $scope.colDefs = [
									{
									    field : 'id',
									    displayName : 'ID',
									    visible:false
									
									},
                                   {
                                          field : 'produitReference',
                                          displayName : 'Réf.Produit',
                                          width:'10%'
                                   },
                                   {
                                          field : 'produitDesignation',
                                          displayName : 'Produit',
                                          width:'20%'
                                   },
                                   {
                                          field : 'clientAbreviation',
                                          displayName : 'Client',
                                          width:'20%'
                                   },
                                   {
                                       field : 'refBonReception',
                                       displayName : 'Réf. Bon Réception',
                                       width:'14%'
                                   },
                                   {
                                       field : 'methodeTeinture',
                                       displayName : 'Méthode Teinture',
                                       width:'10%'
                                   },
                                   {
                                       field : 'observations',
                                       displayName : 'Observations',
                                       width:'20%'
                                   },
                                   {
                                          field : '',
                                          width:'6%',
                                          cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
                                                 +'<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierFicheFacon()"> <i class="fa fa-fw fa-pencil"></i></button>'
                                                 + '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(11)"><i class="fa fa-fw fa-trash-o"></i></button></div>'
                                                                                                  
                                   } ];
                                   
                                   });


                            	$scope.pagingOptions = {
                                          pageSizes : [ 5, 10, 13 ],
                                          pageSize : 13,
                                          currentPage : 1
                                   };
                            
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

                                   // Initialier ng-grid avec contenu
                                   
                                   $scope.getPagedDataAsync = function(pageSize, page,
                                           searchText) {
                                    setTimeout(
                                                  function() {
                                                         var data;
                                                        // var ficheCourante = $scope.ficheCouranteRecherche;
                                                         	FaconServices.getResultatRechercheFiche($scope.ficheCourante).then(function(largeLoad) {
                                                         		if (searchText){
                                                         				var ft = searchText.toLowerCase();
                                                                      $log.debug("---success "+JSON.stringify(largeLoad.list, null, "  "));
                                                                      data = largeLoad.list
                                                                                    .filter(function(item) {
                                                                                           return JSON.stringify(item)
                                                                                                         .toLowerCase()
                                                                                                         .indexOf(ft) != -1;
                                                                                    });
                                                                      $scope.setPagingData( data, page, pageSize);
                                                               
                                                         		}else{
                                                                      $scope.setPagingData(
                                                                                                  largeLoad.list,
                                                                                                  page,
                                                                                                  pageSize);
                                                               }
                                                         		}	
                                          				, function(error) {
                                          					console.log(error.statusText);
                                          				});
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
                                          filterOptions : $scope.filterOptions
                                   };
                            /** Fin de gestion des Grids de la produit */
				
                            } 
		
              ])
