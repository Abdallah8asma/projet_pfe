'use strict';

angular
  .module('gpro.produit', ['ngResource'])
  .controller('produitController', [
    '$scope',
    '$filter',
    '$http',
    '$log',
    'downloadService',
    'UrlCommun',
    'UrlAtelier',
    '$window',

    function (
      $scope,
      $filter,
      $http,
      $log,
      downloadService,
      UrlCommun,
      UrlAtelier,
      $window
    ) {
      // Déclaration des variables globales utilisés
      $log.info('=============PRODUIT===============');
      var data;
      $scope.displayMode = 'list';

      
      $scope.produitCourante = {
    		  
    	        "quantite":0,		
    	        "dateIntroduction": new Date(),
    	        "stock":true, 
    	        "serialisable":false,
              "retour":false,
              "fodec":false,
              "articleProduits":[{
                                'articleId':'',
                                'impressionProduitId':'',
                                "optionArticleProduits":[{
                                                          'designation':'',
                                                          'optionArticleId':'',

                                                         }],
                                "operationArticleProduits":[{
                                                          'designation':'',
                                                          'operationArticleId':'',

                                                           }]                                                               
                              
                                }]
    	  
                  };
                  
      $scope.listeAllArticle = [];
      $scope.listeImpressionProduit = [];
      $scope.listeOptionProduit = [];
      $scope.listeOperationProduit = [];
      

      $scope.listeCompteComptable = [];
	  $scope.ListUniteArticleCache = [];


      $scope.listePartieInteressee = [];
      $scope.listeDocuments = [];
      $scope.listeDocumentProduit = [];
      $scope.resultatRecherche = $scope.listeProduit;
      $scope.ListFamilleProduitCache = [];
      $scope.ListSousFamilleProduitCache = [];
      $scope.listeSitePartieInteresseeCache = [];
      $scope.listeDeviseCache = [];
      $scope.ListeDevise = [];
      $scope.ListeTaxe = [];
      $scope.prixFab = 0;
      $scope.qte = false;

      $scope.hiddenNotifSucc = 'false';
      /***************************************************
       * Gestion de Cache des Referentiels *
       **************************************************/


		// Liste des UniteCache
							$scope.listeUnitesArticleCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/listeUniteArticleCache")
										.success(
												function(dataUniteCache) {
													$log.debug("*LISTEUniteCache :"
																	+ dataUniteCache.length);
													$scope.ListUniteArticleCache = dataUniteCache;

												});
							}
							$scope.listeUnitesArticleCache();


        
								   // Lister Famille produit
								   $scope.getListeFamilleOptionProduit = function() {

									
                    $http.get(UrlCommun + "/utilsEntite/getAllByType:OPTION_PRODUIT")
                        .success(function(dataFamille) {
                          $log.debug("listeFamille " + dataFamille.length);
                          $scope.listeFamilleOptionProduit = dataFamille;
                        });
                  }

                  $scope.getListeFamilleOptionProduit();

    $scope.getSousFamilleArticle = function () {
        $http.get(UrlCommun + '/sousFamilleArticle/all').success(function (data) {
          $scope.listeSousFamilleArticle = data;
        });
      };

      $scope.getSousFamilleArticle();
      $scope.getClientActif = function() {

				//TODO cache
				$http
				  .get(
					UrlCommun
					 + "/baseInfo/getClientActif")
				  .success(
					function(baseInfo) {
					  // $log.debug("baseInfo : ",baseInfo);
					  $scope.clientActif = baseInfo;
				
					});
			  }
        $scope.getClientActif();

           // get Liste des compte comptable PI
           $scope.getListeCompteComptable = function () {
            //TODO cache
            $http
              .get(UrlCommun + '/compteComptable/all')
              .success(function (dataRegionCache) {
                $log.debug('compte comptable : ' + dataRegionCache.length);
                $scope.listeCompteComptable = dataRegionCache;
              });
          };
          $scope.getListeCompteComptable();



      $scope.addArticleProduit = function (articleProduits) {

        console.log('addArticleProduit');

          var artProd = {
            'articleId':'',
            'impressionProduitId':'',
            'qte':'',
            'grammage':'',
            "optionArticleProduits":[{
                                      'designation':'',
                                      'optionArticleId':'',

                                     }],
            "operationArticleProduits":[{
                                      'designation':'',
                                      'operationArticleId':'',
                                      'cout':'',
                                      'temps':''

                                       }]                                                               
          
            };


        articleProduits.push(artProd);
        
      };

      $scope.optionIsChanged = function (optionId,option) {

        console.log('optionIsChanged');


        var element = $scope.listeOptionProduit.filter(function(opt) {
              return opt.id==optionId;
          });
    
         if(angular.isDefined(element[0])){


          console.log('element Trouvee');
             
             option.designation = element[0].designation;

              }

      };

      
      $scope.operationIsChanged = function (operationId,operation) {

        console.log('operationIsChanged');


        var element = $scope.listeOperationProduit.filter(function(opt) {
              return opt.id==operationId;
          });
    
         if(angular.isDefined(element[0])){


          console.log('element Trouvee');
             
             operation.designation = element[0].designation;
             operation.cout = element[0].cout;
             operation.temps = element[0].temps;

              }

      };


      



      $scope.deleteArticleProduit = function (index,artProdList) {

        console.log('deleteArticleProduit');

      
          artProdList.splice(index,1);
      
      };



         $scope.addOptionArticleProduit = function (optionArticleProduits) {

          console.log('addOptionArticleProduit');

          var optionArtProd = {
          'designation':'',
          'optionArticleId':''

                  }   
                  
           optionArticleProduits.push(optionArtProd);  
        
          };


         
    $scope.deleteOptionArticleProduit = function (option,optionArticleProduits) {

      
      console.log('deleteOptionArticleProduit');
        
      var index = optionArticleProduits.indexOf(option);
       
      optionArticleProduits.splice(index,1);
    
    };


    $scope.addOperationArticleProduit = function (operationArticleProduits) {


      console.log('addOperationArticleProduit');

      var operationArtProd = {
      'designation':'',
      'operationArticleId':''

              }   
              
              operationArticleProduits.push(operationArtProd);  
    
      };


     
$scope.deleteOperationArticleProduit = function (operation,operationArticleProduits) {

  

  console.log('deleteOperationArticleProduit');
    
  var index = operationArticleProduits.indexOf(operation);
   
  operationArticleProduits.splice(index,1);

};






      
      $scope.getAllOperationProduit = function () {
        $http.get(UrlCommun + '/operationProduit/all').success(function (data) {
          $scope.listeOperationProduit = data;
        });
      };

      $scope.getAllOperationProduit();


      $scope.getAllOptionProduit = function () {
        $http.get(UrlCommun + '/optionProduit/all').success(function (data) {
          $scope.listeOptionProduit = data;
        });
      };

      $scope.getAllOptionProduit();


      $scope.getAllImpressionProduit = function () {
        $http.get(UrlCommun + '/impressionProduit/all').success(function (data) {
          $scope.listeImpressionProduit = data;
        });
      };

      $scope.getAllImpressionProduit();


      $scope.getAllArticle = function () {
        $http.get(UrlCommun + '/article/all').success(function (data) {
          $scope.listeAllArticle = data;
        });
      };

      $scope.getAllArticle();
      
      
            // Liste des PICache
      $scope.listePartieInteresseeCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listePartieInteresseeCache')
          .success(function (dataPICache) {
            // $log.debug("*LISTEPartieInteresseeCache
            // :"
            // +JSON.stringify(dataPICache,null,"
            // "));

            // $scope.listePartieInteressee
            // = $filter ('filter')
            // (dataPICache ,
            // {famillePartieInteressee
            // : 2});
            $scope.listePartieInteressee = dataPICache;
          });
      };

//$scope.listePartieInteresseeCache();

      /***    Fermer notification Article existant       **/

      $scope.closeNotifS = function () {
        $scope.hiddenNotifSucc = 'false';
      };
      
      
      
      
      $scope.financierIsChanged = function(financier){
    	  
    	  if(financier != null && financier == true){
    		  
    		  $scope.produitCourante.stock = false;
    		  
    		  $scope.produitCourante.serialisable = false;
    	  }
    	  
      }
      
      
      
      
      
      
      
      
      /***    calcule de prix de vente HT       **/
		
		$scope.calculerPrixVenteHT = function(prixVenteTTC,taxeId){
			
			var element = $scope.ListeTaxe.filter(function(node) {
		        return node.id==taxeId;
		    });
			
			if(angular.isDefined(element[0]) && prixVenteTTC != null){
				
				var valeurTaxe = element[0].valeur;
				
				$scope.produitCourante.prixUnitaire = prixVenteTTC / (1+(valeurTaxe/100));
				
			    //$scope.produitCourante.prixUnitaire.toFixed(3);
				$scope.produitCourante.prixUnitaire = Math.round($scope.produitCourante.prixUnitaire*1000)/1000;
			    
			    //Math.round(($scope.produitCourante.prixUnitaire + Number.EPSILON) * 100) / 100;
				
			}
			
			
		}

      /***    calcule de prix de vente TTC       **/

      $scope.calculerPrixVenteTTC = function (prixUnitaireHT, taxeId) {
        var element = $scope.ListeTaxe.filter(function (node) {
          return node.id == taxeId;
        });


        if (angular.isDefined(element[0]) && (prixUnitaireHT != null) ) {


          if($('input[name="chek"]').is(':checked')){
          var valeurTaxe = element[0].valeur;

          $scope.produitCourante.prixVenteTTC = ( prixUnitaireHT * (1 + valeurTaxe / 100)+ 0.01);
          
        }

          else {
            var valeurTaxe = element[0].valeur;

  
            $scope.produitCourante.prixVenteTTC = ( prixUnitaireHT * (1 + valeurTaxe / 100) );

          }
        }
        


          //$scope.produitCourante.prixVenteTTC.toFixed(3);
      	$scope.produitCourante.prixVenteTTC = Math.round($scope.produitCourante.prixVenteTTC*1000)/1000;
        




      };
      
      
      
      
      
      
      
      
      
      /***    calcule de prix de Achat HT       **/
		
		$scope.calculerPrixAchatHT = function(prixAchatTTC,taxeId){
			
			var element = $scope.ListeTaxe.filter(function(node) {
		        return node.id==taxeId;
		    });
			
			if(angular.isDefined(element[0]) && prixAchatTTC != null){
				
				var valeurTaxe = element[0].valeur;
				
				$scope.produitCourante.prixAchat = prixAchatTTC / (1+(valeurTaxe/100));
				
			    //$scope.produitCourante.prixAchat.toFixed(3);
			    
				$scope.produitCourante.prixAchat = Math.round($scope.produitCourante.prixAchat*1000)/1000;
				
			}
			
			
		}

    /***    calcule de prix de Achat TTC       **/

    $scope.calculerPrixAchatTTC = function (prixAchatHT, taxeId) {
      var element = $scope.ListeTaxe.filter(function (node) {
        return node.id == taxeId;
      });

      if (angular.isDefined(element[0]) && prixAchatHT != null) {
        var valeurTaxe = element[0].valeur;

        $scope.produitCourante.prixAchatTTC =prixAchatHT * (1 + valeurTaxe / 100);
        
       // $scope.produitCourante.prixAchatTTC.toFixed(3);
        $scope.produitCourante.prixAchatTTC = Math.round($scope.produitCourante.prixAchatTTC*1000)/1000;
      }
    };

    
    function roundNumber(num, scale) {
    	  if(!("" + num).includes("e")) {
    	    return +(Math.round(num + "e+" + scale)  + "e-" + scale);
    	  } else {
    	    var arr = ("" + num).split("e");
    	    var sig = ""
    	    if(+arr[1] + scale > 0) {
    	      sig = "+";
    	    }
    	    return +(Math.round(+arr[0] + "e" + sig + (+arr[1] + scale)) + "e-" + scale);
    	  }
    	}
      /** Sous Famille Is Changed **/

      $scope.sousFamilleChange = function (id) {
        $log.debug('Sous Famille  is changed : id = ', id);

        var element = $scope.ListSousFamilleProduitCache.filter(function (
          node
        ) {
          return node.id == id;
        });
        //	$scope.produitCourante.tva =  element[0].tva;
        $scope.produitCourante.idTaxe = element[0].idTaxe;
        $scope.produitCourante.serialisable = element[0].serialisable;

        $log.debug('element = ', element);
      };

      // Liste des FamilleCache
      $scope.ListFamillesProduitCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeFamilleProduitCache')
          .success(function (dataFamilleProduitCache) {
            $log.debug('*LISTEFamilleProduitCache :' + dataFamilleProduitCache);
            $scope.ListFamilleProduitCache = dataFamilleProduitCache;
          });
      };

      // Liste des Taxes - Marwa 0202108
      $scope.ListeTaxe = function () {
        $http.get(UrlAtelier + '/taxe/getTVA').success(function (dataTaxe) {
          $scope.ListeTaxe = dataTaxe;
        });
      };

      $scope.ListeTaxe();

      // Liste des SousFamilleCache
      $scope.ListSousFamillesProduitCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeSousFamilleProduitCache')
          .success(function (dataSousFamilleProduitCache) {
            $log.debug(
              '*LISTESousFamilleProduitCache :' +
                dataSousFamilleProduitCache.length
            );
            $scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;
          });
      };

      // Liste des SiteeCache
      $scope.listeSitesPartieInteresseeCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeSitePartieInteresseeCache')
          .success(function (dataSiteCache) {
            $log.debug('*LISTESiteCache :' + dataSiteCache.length);
            $scope.listeSitePartieInteresseeCache = dataSiteCache;
          });
      };

      // Liste des DeviseCache
      /*$scope.ListDeviseCache = function() {
					$http
							.get(
									UrlCommun
											+ "/gestionnaireCache/listeDeviseCache")
							.success(
									function(dataDeviseCache) {
										$log.debug("*LISTEDeviseCache :"
														+ dataDeviseCache.length);
										$scope.ListDeviseCache = dataDeviseCache;

									});
				}*/

      // Liste des PICache
   /*   $scope.listePartieInteresseeCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listePartieInteresseeCache')
          .success(function (dataPICache) {
            //													$log.debug("*LISTEPartieInteresseeCache :"
            //																	+JSON.stringify(dataPICache,null," "));

            $scope.listePartieInteressee = $filter('filter')(dataPICache, {
              famillePartieInteressee: 2,
            });
          });
      };*/

      // Liste TypeDocumentCache
      $scope.listeTypeDocumentsCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeTypeDocumentCache')
          .success(function (dataTypeDocumentCache) {
            $log.debug(
              '*LISTTypeDocumentCache :' + dataTypeDocumentCache.length
            );
            console.log(
              'liste type doc:prod cache:' + dataTypeDocumentCache.length
            );
            var dataTypeDocCache2 = [];
            for (var i = 0; i < dataTypeDocumentCache.length; i++) {
              console.log(
                'dataTypeDocumentCache[i]: ' + dataTypeDocumentCache[i].module
              );
              if (dataTypeDocumentCache[i].module == 'ELEMENT_DE_BASE_PRODUIT') {
                dataTypeDocCache2.push(dataTypeDocumentCache[i]);
                console.log(
                  'moduleid:' +
                    dataTypeDocumentCache[i].id +
                    ' designation: ' +
                    dataTypeDocumentCache[i].designation
                );
              }
            }

            $scope.listeTypeDocumentsCache = dataTypeDocCache2;
          });
      };
      $scope.ListFamillesProduitCache();
      $scope.ListSousFamillesProduitCache();
      $scope.listeSitesPartieInteresseeCache();
      //$scope.ListDeviseCache();
      $scope.listePartieInteresseeCache();
      $scope.listeTypeDocumentsCache();

      /***************************************************
       * Gestion des Produits
       **************************************************/
      $scope.deleteValue = 'oui';
      // Annuler Ajout
      $scope.cancelAddProduit = function (
        rowform,
        index,
        id,
        designation,
        liste
      ) {
        // $log.debug("* Designation
        // "+liste[0].designation);
        if (angular.isDefined(id)) {
          $log.debug('DEF ID');
          $scope.deleteValue = 'non';
          rowform.$cancel();
          $log.debug('CANCEL');
        } else {
          $log.debug('UND ID  ' + id);
          if (designation == '') {
            $scope.deleteValue == 'oui';
            $log.debug('Designation : ' + designation);
            $log.debug('$scope.deleteValueOUI : ' + $scope.deleteValue);
            liste.splice(index, 1);
            rowform.$cancel();
            $log.debug('DELETE');
          } else {
            $log.debug('Designation :' + designation);
            $log.debug('$scope.deleteValueNON : ' + $scope.deleteValue);
            rowform.$cancel();
            $log.debug('CANCEL');
          }
        }
        $scope.deleteValue = 'oui';
      };

      //Variale de Pagination de la grid
      $scope.pagingOptions = {
        pageSizes: [5, 10, 13],
        pageSize: 13,
        currentPage: 1,
      };

      // Liste des produits
      /*$scope.listeProduit = function() {
					$http.get(UrlCommun + "/produit/all").success(
							function(data) {
								$scope.myData = data;
								
								//data, page,pageSize
								$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
													 $scope.pagingOptions.pageSize										
																	 );

							});
				} */
      // Liste des Devises
      $scope.ListeDevise = function () {
        $http.get(UrlCommun + '/devise/all').success(function (dataDevise) {
          $scope.ListeDevise = dataDevise;
        });
      };

      // Liste des PartieInteressee
      /*$scope.listePartieInteressee = function() {
					$http
							.get(UrlCommun + "/partieInteressee/all")
							.success(
									function(dataPartieInteressee) {

										$scope.listePartieInteressee = dataPartieInteressee;
									});
				}*/

      //Init data list
      $scope.initMyData = [];

      // Rechercher produit
      $scope.rechercheProduit = function (produitCourante) {
        $log.debug('recherche en cours ..');
        $http
          .post(
            UrlCommun + '/produit/rechercheProduitMulticritere',
            produitCourante
          )
          .success(function (resultat) {
            $log.debug(
              '----listeProduitRecherchée--- : ' + resultat.produitValues.length
            );
            $log.debug(
              '----listeProduitRecherchée--- : ' +
                JSON.stringify(resultat.produitValues, null, ' ')
            );

            $scope.myData = resultat.produitValues;
            $scope.initMyData = $scope.myData;

            //data, page,pageSize
            $scope.setPagingData(
              $scope.myData,
              $scope.pagingOptions.currentPage,
              $scope.pagingOptions.pageSize
            );

            $scope.rechercheProduitForm.$setPristine();
            $scope.displayMode = 'rechercher';
          });
      };

      //Initialiser la liste des produits (ng-grid)
      $scope.rechercheProduit({});

      // Annulation de l'ajout
      $scope.annulerAjout = function () {
        $scope.cnt = 0;
        $scope.produitCourante = {
          reference: '',
          designation: '',
          famille: '',
          sousfamille: '',
          partieInteressee: '',
          prix_inf: '',
          prix_sup: '',
          site: '',
          etat: '',
          "stock":false
        };

        $scope.rechercheProduitForm.$setPristine();
        $scope.listeDocumentProduit = [];
        //	$scope.rechercheProduit({});
        $scope.displayMode = 'list';
        $scope.hiddenNotifSucc = 'false';
      };
      // ** Ajout Produit
      $scope.AffectationProduit = function (Produit) {
        $scope.hiddenNotifSucc = 'false';

        $scope.produitCourante = Produit ? angular.copy(Produit) : {};

        $scope.displayMode = 'edit';


        
        
        
        $scope.produitCourante ={
        "quantite":0,		
        "dateIntroduction": new Date(),
        "stock":true, 
        "serialisable":false,
        "retour":false,
        "fodec":false,
        "devise":"2",
        
        "articleProduits":[{
          'articleId':'',
          'impressionProduitId':'',
          'qte':'',
          'grammage':'',
          'dimension':'',
          "optionArticleProduits":[{
                                    'designation':'',
                                    'optionArticleId':'',

                                   }],
          "operationArticleProduits":[{
                                    'designation':'',
                                    'operationArticleId':'',
                                    'cout':'',
                                    'temps':''

                                     }]                                                               
        
          }]
                 };
        
        
        $scope.qte = false;
      };

      // Ajout et Modification Produit
      $scope.modifierOuCreerProduit = function () {
        var index = this.row.rowIndex;
        // getProduit
        $http
          .get(UrlCommun + '/produit/getId:' + $scope.myData[index].id)
          .success(function (datagetProduit) {
            $scope.listeDocumentProduit = datagetProduit.documentProduits;

            $scope.creationProduitForm.$setPristine();
            $scope.myData[index].documentProduits = $scope.listeDocumentProduit;

            $scope.produitCourante = datagetProduit;



          });


        $scope.displayMode = 'edit'; 
        $scope.qte = true;
      };
	  
	  
	  
	  $scope.annulerAjoutRapide = function () {
					
						
					// interface en mode : list
					$scope.displayMode = "list";
					
				}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

      // Sauvegarder Produit
      $scope.sauvegarderAjout = function (Produit) {
        $log.debug('Sauvegarder Modification' + Produit);
        if (angular.isDefined(Produit.id)) {
          $scope.updateProduit(Produit);
        } else {
          $log.debug('Sauvegarder Ajout' + Produit.SiteId);
          $scope.creerProduit(Produit);
        }
      };

      // Mise à jour des Produits
      $scope.updateProduit = function (produit) {
        produit.documentProduits = $scope.listeDocumentProduit;
        
        if(produit.idTaxe != null){
  		  
  		  var element = $scope.ListeTaxe.filter(function(node) {
		        return node.id==produit.idTaxe;
		    });
    		
    		if(element && element[0]) produit.tva = element[0].valeur;
  		  
  	  }

        $http
          .post(UrlCommun + '/produit/modifierProduit', produit)
          .success(function (ProduitModifiee) {
            //$log.debug("Success Modification "+ newProduit.designation);

            //TODO :getById
            $scope.annulerAjout();
            $scope.rechercheProduit({});
          });
      };

      // Création Produit
      $scope.creerProduit = function (produit) {
    	  
    	  if(produit.idTaxe != null){
    		  
    		  var element = $scope.ListeTaxe.filter(function(node) {
  		        return node.id==produit.idTaxe;
  		    });
      		
      		if(element && element[0]) produit.tva = element[0].valeur;
    		  
    	  }
    		
    			
    	  
        produit.documentProduits = $scope.listeDocumentProduit;

        $scope.hiddenNotifSucc = 'false';

        $http
          .post(UrlCommun + '/produit/rechercheProduitMulticritere', {
            reference: produit.reference,
          })
          .success(function (res) {
            if (res.nombreResultaRechercher == 0) {
              $http
                .post(UrlCommun + '/produit/creerProduit', produit)
                .success(function (newProduit) {
                  //	$log.debug("Success Creation"+ newProduit.designation);

                  //TODO :getById
                  $scope.annulerAjout();

                  $scope.rechercheProduit({});
                });
            } else {
              $scope.hiddenNotifSucc = 'true';
            }
          });
      };

      // Suppression produit
     		     $scope.supprimerProduit = function() {
							
							$log.debug("DELETE .."
									+ $scope.myData[$scope.index].id);
							
							$http(
									{
										method : "DELETE",
										url : UrlCommun
												+ "/produit/supprimerProduit:"
												+ $scope.myData[$scope.index].id
									}).success(function() {
								$log.debug("Success Delete");
								$scope.myData.splice($scope.index, 1);
								$scope.closePopupDelete();
								$scope.closePopupDelete();
								$scope.rechercheProduit({});
							});
						
						}
						
						

      //$scope.listeProduit();
      //$scope.listePartieInteressee();
      $scope.ListeDevise();

      //generer rapport de tous les bons de livraison. mode : List

      $scope.downloadAllProduits = function (produitCourant) {
        var url;
        //$log.debug("PI  "+produitCourant.partieInteressee );

        if (produitCourant.partieInteressee == null) {
          produitCourant.partieInteressee = '';
        }

        $log.debug(
          '-- produitCourant After' + JSON.stringify(produitCourant, null, '  ')
        );
        url =
          UrlCommun +
          '/reportCommun/listproduit?reference=' +
          produitCourant.reference +
          '&designation=' +
          produitCourant.designation +
          '&sousfamille=' +
          produitCourant.sousfamille +
          '&partieInteressee=' +
          produitCourant.partieInteressee +
          '&etat=' +
          '' +
          '&site=' +
          '' +
          '&prixInf=' +
          produitCourant.prix_inf +
          '&prixSup=' +
          produitCourant.prix_sup +
          '&type=pdf';

        $log.debug('-- URL--- :' + url);

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
          
         
        });


        // downloadService.download(url).then(
        //   function (success) {
        //     // $log.debug('success : ' + success);
        //   },
        //   function (error) {
        //     // $log.debug('error : ' + error);
        //   }
        // );
      };

      $scope.downloadAllProduitsExcel = function (produitCourant) {
        var url;
        //$log.debug("PI  "+produitCourant.partieInteressee );

        if (produitCourant.partieInteressee == null) {
          produitCourant.partieInteressee = '';
        }

        $log.debug(
          '-- produitCourant After' + JSON.stringify(produitCourant, null, '  ')
        );
        url =
          UrlCommun +
          '/fiches/listproduit?reference=' +
          produitCourant.reference +
          '&designation=' +
          produitCourant.designation +
          '&sousfamille=' +
          produitCourant.sousfamille +
          '&partieInteressee=' +
          produitCourant.partieInteressee +
          '&etat=' +
          '' +
          '&site=' +
          '' +
          '&prixInf=' +
          produitCourant.prix_inf +
          '&prixSup=' +
          produitCourant.prix_sup +
          '&type=pdf';

        $log.debug('-- URL--- :' + url);

        var fileName = 'Liste des produits Serialisable';
        var a = document.createElement('a');
        document.body.appendChild(a);
        downloadService.download(url).then(function (result) {
          var heasersFileName = result.headers(['content-disposition']).substring(22);
          var fileName = heasersFileName.split('.');
          fileName[0] =  'Liste_Articles_' + formattedDate(new Date());
        var typeFile = result.headers(['content-type']);
        var file = new Blob([result.data], {type: typeFile});
        var fileURL = window.URL.createObjectURL(file);
        if(typeFile == 'application/vnd.ms-excel'){
        	 a.href = fileURL;
           a.download = fileName[0];
          //$window.open(fileURL)
           a.click();

        }else{
          a.href = fileURL;
          a.download = fileName[0];
         $window.open(fileURL)
          a.click();

        }
          
         
        });

        // downloadService.download(url).then(
        //   function (success) {
        //     // $log.debug('success : ' + success);
        //   },
        //   function (error) {
        //     // $log.debug('error : ' + error);
        //   }
        // );
      };
      
      function formattedDate(date) {
          var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

          if (month.length < 2) month = '0' + month;
          if (day.length < 2) day = '0' + day;
          return [year, month, day].join('-');
        }

      /** Fin de gestion des Produits */

      /***************************************************
       * Gestion des DocumentProduit
       **************************************************/
      $scope.listeDocumentProduit = [];
      $scope.name = 'PRODUIT';
      // GetId.designation
      $scope.type = {
        status: '',
      };
      $scope.showStatus = function (id) {
        /*$scope.type.status = id;
					var selected = $filter('filterProduit')(
							$scope.ListTypeDocumentCache, {
								id : $scope.type.status
							});
					if ($scope.type.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}*/
        var designation = '';
        console.log(
          'show status length: ' + $scope.listeTypeDocumentsCache.length
        );
        for (var i = 0; i < $scope.listeTypeDocumentsCache.length; i++) {
          //console.log("id: "+id+"----listeTypeDocumentCache[i]="+ $scope.listeTypeDocumentsCache[i].id);
          //console.log("---module: "+$scope.listeTypeDocumentCache[i].module);
          if ($scope.listeTypeDocumentsCache[i].id == id) {
            designation = $scope.listeTypeDocumentsCache[i].designation;
            //console.log("designation type doc :" +designation);
            return designation;
          }
        }
      };
      // ajout d'un DocumentProduit
      $scope.ajoutDocumentProduit = function () {
        $scope.DocumentProduitInserree = {
          typeDocumentId: '',
          uidDocument: '',
          path: '',
        };
        $scope.listeDocumentProduit.push($scope.DocumentProduitInserree);
      };

      // Enregistrer DocumentProduit
      $scope.saveDocumentProduit = function (dataDocumentProduit, id) {
        $scope.deleteValue = 'non';
        $log.debug('$scope.deleteValue :' + $scope.deleteValue);
        angular.extend(dataDocumentProduit, {
          id: id,
        });
      };

      // Supprimer DocumentProduit
      $scope.removeDocumentProduit = function (index) {
        $scope.listeDocumentProduit.splice(index, 1);
      };

      //Download Document
      $scope.download = function (uuid) {
        var url = UrlCommun + '/gestionnaireDocument/document/' + uuid;

        downloadService.download(url).then(
          function (success) {
            $log.debug('success : ' + success);
          },
          function (error) {
            $log.debug('error : ' + error);
          }
        );
      };
      /** Fin de gestion des DocumentProduit */

      /***************************************************
       * Gestion des Grids de recherche
       **************************************************/
      $scope.typeAlert = 3;
      $scope.colDefs = [];
      $scope.$watch('myData', function () {
        $scope.colDefs = [
	
	      // {
	    	   // field : 'dateIntroduction',
	    	   // displayName : 'Date Intro',
	    	   // cellFilter: "date: 'yyyy-MM-dd'",
	    	   // width:'10%'
	       // },
		
		   /*  {
              field: 'numero',
              displayName: 'Modele Article',
              width: '10%',
            }, */
          {
            field: 'reference',
            displayName: 'Ref',
            width: '12%',
          },
          {
            field: 'designation',
            displayName: 'Désignation',
            width: '40%',
          },
         /* {
            field: 'unite',
            displayName: 'Unite',
            width: 90,
          },*/
          
      /*     {
              field: 'superFamilleDesignation',
              displayName: 'Famille',
              width: '15%',
            }, */
          // {
            // field: 'familleDesignation',
            // displayName: 'Categorie',
            // width: '10%',
          // },
          // {
            // field: 'sousFamilleDesignation',
            // displayName: 'Sous Categorie',
            // width: '8%',
          // },
          //										{
          //											field : 'partieIntersseDesignation',
          //											displayName : 'PI',
          //											width:200
          //	
      /*    {
            field: 'prixAchatTTC',
            displayName: 'P.Achat TTC',
    		cellFilter: 'number:3',
            cellClass: 'grid-align',
            width: '9%'
          },
		  */
				
				 {
            field: 'partieIntersseDesignation',
            displayName: 'Client',
            width: '20%',
          },
				
				
				

           {
            field: 'prixUnitaire',
            displayName: 'P.Vente HT',
    		cellFilter: 'number:3',
            cellClass: 'grid-align',
            width: '7%'
            },
		  


		  
          {
            field: 'prixVenteTTC',
            displayName: 'P.Vente TTC',
    		cellFilter: 'number:3',
            cellClass: 'grid-align',
            width: '7%'
          },
		  
		  
		/*    {
           
            displayName: 'Marge',
			cellTemplate:`<div>{{(row.entity.prixVenteTTC - row.entity.prixAchatTTC)/(row.entity.prixAchatTTC)*100  | number : 2}}</div>`,
    		
            cellClass: 'grid-align',
            width: '7%'
          },*/
		  
		  
		  
		 /*,
          {
            field: 'quantite',
            displayName: 'Quantité',
            width: 90
          },*/
          {
            field: '',
            cellTemplate:
            `<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
              <button class="ms-CommandButton-button ms-CommandButton-Gpro  " ng-click="modifierOuCreerProduit()">
              <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
              </button>
              <button class="ms-CommandButton-button"  ng-click="showPopupDelete(3)" permission="['Article_Delete']">
              <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
             </button>
              	</div> `,

            // '<div class="buttons" ng-show="!rowform.$visible">
            //<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button>
            // <button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete('$scope.typeAlert')"> <i class="fa fa-fw fa-trash-o"></i></button>'
            // 	+ '</div>',
            //width: 100
          },
        ];
      });
      $scope.filterOptions = {
        filterText: '',
        useExternalFilter: true,
      };

      $scope.totalServerItems = 0;

      $scope.setPagingData = function (data, page, pageSize) {
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.myData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
          $scope.$apply();
        }
      };
	  
	    

      $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
          if (searchText) {
            var ft = searchText.toLowerCase();

            var data = $scope.initMyData.filter(function (item) {
              return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
            });

            $scope.setPagingData(data, page, pageSize);
          } else {
            $scope.setPagingData($scope.initMyData, page, pageSize);
          }
        }, 100);
      };

      $scope.getPagedDataAsync(
        $scope.pagingOptions.pageSize,
        $scope.pagingOptions.currentPage
      );

      $scope.$watch(
        'pagingOptions',
        function (newVal, oldVal) {
          if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
            $scope.getPagedDataAsync(
              $scope.pagingOptions.pageSize,
              $scope.pagingOptions.currentPage,
              $scope.filterOptions.filterText
            );
          }
        },
        true
      );
      $scope.$watch(
        'filterOptions',
        function (newVal, oldVal) {
          if (newVal !== oldVal) {
            $scope.getPagedDataAsync(
              $scope.pagingOptions.pageSize,
              $scope.pagingOptions.currentPage,
              $scope.filterOptions.filterText
            );
          }
        },
        true
      );

      $scope.gridOptions = {
        data: 'myData',
        columnDefs: 'colDefs',
        enablePaging: true,
        enableColumnResize: true,
        showFooter: true,
        enableHighlighting: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        selectedItems: $scope.selectedRows,
        filterOptions: $scope.filterOptions,
      };
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

      /** Fin de gestion des Grids de la partie Interesse */

      ///

      /*
				
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

						$scope.getPagedDataAsync = function(pageSize, page,
								searchText) {
							setTimeout(
									function() {
										var data;
										var produitCourante = $scope.produitCourante;
										if (searchText) {
											var ft = searchText
													.toLowerCase();
											$http
													.post(
															UrlCommun
																	+ "/produit/rechercheProduitMulticritere",produitCourante)
													.success(
															function(
																	largeLoad) {
																data = largeLoad
																		.filter(function(
																				item) {
																			return JSON
																					.stringify(
																							item)
																					.toLowerCase()
																					.indexOf(
																							ft) != -1;
																		});
																$scope
																		.setPagingData(
																				data,
																				page,
																				pageSize);
															});

										} else {
											$http
													.post(UrlCommun
															+ "/produit/rechercheProduitMulticritere",produitCourante)
											.success(function(largeLoad) {
												$scope.setPagingData(
														largeLoad, page,
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
					dataselected : 'myDataselected',
					selectedItems : [],

					data : 'myData',
					columnDefs : [
							{
								field : 'reference',
								displayName : 'Réf.Produit'
							},
							{
								field : 'designation',
								displayName : 'Désignation'
							},
							{
								field : 'familleDesignation',
								displayName : 'Famille'
							},
							{
								field : 'sousFamilleDesignation',
								displayName : 'Sous Famille'
							},
							{
								field : 'partieIntersseDesignation',
								displayName : 'PI'
							},
							{
								field : 'prixUnitaire',
								displayName : 'Prix'
							},
							{
								field : 'siteEntiteDesignation',
								displayName : 'Site'
							},
							{
								field : 'etat',
								displayName : 'Etat'
							},
							{
								field : '',
								 cellTemplate : '<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button> <button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete('
												 + $scope.typeAlert
												 + ')"> <i class="fa fa-fw fa-trash-o"></i></button>'
												 +'</div>'
							} ],
					enablePaging : true,
					showFooter : true,
					totalServerItems : 'totalServerItems',
					pagingOptions : $scope.pagingOptions,
					filterOptions : $scope.filterOptions,

				};*/
      /** Fin de gestion des Grids de la produit */
      $scope.editFicheBesoin = function () {
        var index = this.row.rowIndex;
        window.location.href =
          '#/ficheBesoin?idProduit=' + $scope.myData[index].id;
      };
    },
  ])
  .filter('filterProduit', function () {
    return function (liste, id) {
      var listeFiltre = [];
      // $log.debug("IdSousFamille=  "+id.id);
      // $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
      angular.forEach(listeSousFamille, function (sousFamille, key) {
        if (liste.id == id.id) {
          //$log.debug(liste.id +" == "+ id.id);
          listeFiltre.push(liste);
        }
      });
      // $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
      return listeFiltre;
    };
  })
  .controller('DateIntroCtrl', [
    '$scope',
    function ($scope) {
      $scope.maxToDay = new Date();
      // date piker defit
      //							$scope.today = function() {
      //								$scope.articleCourante.dateIntroduction = new Date();
      //							};
      //							$scope.today();
      $scope.clear = function () {
        $scope.articleCourante.dateIntroduction = null;
      };
      // Disable weekend selection
      $scope.disabled = function (date, mode) {
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
      };
      $scope.toggleMin = function () {
        $scope.minDate = $scope.minDate ? null : new Date();
      };
      $scope.toggleMin();
      $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
      };
      $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1,
      };
      $scope.initDate = new Date('20/08/2015');
      $scope.formats = [
        'dd-MMMM-yyyy',
        'dd/MM/yyyy',
        'dd.MM.yyyy',
        'shortDate',
      ];
      $scope.format = $scope.formats[0];
    },
  ]);
