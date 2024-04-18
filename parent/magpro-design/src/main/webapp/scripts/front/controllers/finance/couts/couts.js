'use strict'

 angular
.module('atelier.couts', [ "ngResource" ])
.controller(
		'coutsController',
		[
				'$scope',
				'$filter',
				'$http',
                '$log',
				'downloadService',
				'UrlCommun',
				'UrlAtelier',
        'CoutsService',
        '$window',
				function($scope,
						 $filter,
						 $http,
						 $log,
						 downloadService,
						 UrlCommun,
						 UrlAtelier,
             CoutsService,
             $window
						 ) {
					
					/**
					 $log.info("========= Finance/Couts ========");
                            // Déclaration des variables globales utilisés
                            var data;
                            $scope.displayMode = "list";
                            //modePdf notActif
                            $scope.modePdf = "NotActif";
                            //init deleteValue pour cancelAddBLVente
                            $scope.deleteValue = "oui";

                            //directActif
                            $scope.directActif = false;
                            //Declaration de objtCourant de recherche
                            $scope.ficheCouranteRecherche = {};
                            $scope.ficheCourante = {};
                            //afficher les attributs d'une Mise
                            $scope.remplir = false;

                            //init counterRecette
                            $scope.counterRecette = 0;

                            //init index listTraitementMise
                            $scope.indexPrepa = 0;
                            $scope.indexPES = 0;
                            $scope.indexCoton = 0;
                            $scope.indexFinissage = 0;

                            //RechercheMise
                            $scope.resultatRechercheMise = [];

                            // init ListeTraitements
                            $scope.listeTraitementsMise = [];
                           //init subListControle
                            $scope.listeTraitementMisePreparation = [];
                            $scope.listeTraitementMiseTeinturePES = [];
                            $scope.listeTraitementsMiseTeintureCoton = [];
                            $scope.listeTraitementsMiseFinissage = [];
                         
                            //init ListeControle
                            $scope.listElementControle = [];
                            //init subListControle -- mode Creation
                            $scope.listControleCotonInit = [  {//PH
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Teinture - Coton",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 1,
                                                               ficheSuiveuseId:null
                                                             },
                                                             {//TR
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Teinture - Coton",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 2,
                                                               ficheSuiveuseId:null
                                                             }];

                          $scope.listControlePESInit = [     {//PH
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Teinture - PES",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 1,
                                                               ficheSuiveuseId:null

                                                             },
                                                             {//TR
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Teinture - PES",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 2,
                                                               ficheSuiveuseId:null
                                                             }];

                            $scope.listControleFinissageInit = [{//PH
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Finissage",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 1,
                                                               ficheSuiveuseId:null
                                                             },
                                                             {//TR
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Finissage",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 2,
                                                               ficheSuiveuseId:null
                                                             }];

                             $scope.listControlePreparationInit = [{//PH
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Preparation",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 1,
                                                               ficheSuiveuseId:null
                                                             },
                                                             {//TR
                                                               valeur: null,
                                                               valeurCorrige: null ,
                                                               methode: "",
                                                               temps:"",
                                                               type:"Preparation",
                                                               observations:"",
                                                               valeurTheorique: null ,
                                                               controleId: 2,
                                                               ficheSuiveuseId:null
                                                             }];
                            //init subListControle -- mode Modification
                            $scope.listControleCoton = [];
                            $scope.listControlePES = [];
                            $scope.listControleFinissage = [];
                            $scope.listControlePreparation = [];

                            // init listesRecettesMise 
                            $scope.listElementRecetteMise = [];
                            // init subListRecette
                            $scope.listRecetteCoton = [];
                            $scope.listRecettePES = [];
                            $scope.listRecetteFinissage = [];
                            $scope.listRecettePreparation = [];
                            
                            /********************************
                             * Gestion des listes deroulantes
                             * TODO cache
                             ********************************/
                            // Liste des Mises
                            $scope.listeMise = function() {
                                   var mise ={};
                                   $http
                                   .post(
                                                 UrlAtelier
                                                               + "/mise/rechercheMiseMulticritere",mise)
                                   .success(
                                                 function(resultat) {
                                                        $log.debug("listeMise : "+resultat.listeElementRechecheMiseValeur.length);
                                                        $scope.listeMise = resultat.listeElementRechecheMiseValeur;

                                                               });
                            }
                            
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

                            // Liste MetrageCache
                            $scope.listeMetragesArticleCache = function() {
                                   $http  .get(UrlCommun + "/gestionnaireCache/listeMetrageArticleCache")
                                          .success(
                                                 function(dataMetrageCache) {
                                                        $log.debug("listeMetrageCache " + dataMetrageCache.length);
                                                        $scope.listeMetragesArticleCache = dataMetrageCache;

                                                 });
                            }

                            // Liste Article
                            $scope.listeArticle = function() {
                                   $http  .get(UrlCommun + "/article/all")
                                          .success(
                                                 function(dataArticle) {
                                                        $log.debug("listeArticle " + dataArticle.length);
                                                        $scope.listeArticle = dataArticle;

                                                 });
                            }

                            // Liste des SousFamilleCache
                            $scope.listSousFamillesArticleCache = function() {
                              $http
                                  .get(
                                      UrlCommun
                                          + "/gestionnaireCache/listeSousFamilleArticleCache")
                                  .success(
                                      function(dataSousFamilleCache) {
                                        $log.debug("listeSousFamilleCache :"
                                                + dataSousFamilleCache.length);
                                        $scope.listSousFamillesArticleCache = dataSousFamilleCache;

                                      });
                            }

                            // Liste Traitement
                            $scope.listeTraitementCaches = function() {
                              $http
                                  .get(UrlAtelier + "/gestionnaireLogistiqueCache/listeTraitementCache")
                                  .success(
                                      function(data) {
                                        $log.debug("Traitement : " + data.length);
                                        $scope.listeTraitementCaches = data;

                                      });
                            }

                            // Liste Machines
                            $scope.listeMachines = function() {
                              $http
                                  .get(UrlAtelier + "/gestionnaireLogistiqueCache/listeMachineCache")
                                  .success(
                                      function(data) {
                                        $log.debug("Machines : " + data.length);
                                        $scope.listeMachines = data;

                                      });
                            }

                            // Liste Controles
                            $scope.listeControles = function() {
                              $http
                                  .get(UrlAtelier + "/controle/getAll")
                                  .success(
                                      function(data) {
                                        $log.debug("Controles : " + data.length);
                                        $scope.listeControles = data;

                                      });
                            }

                            $scope.listeMarche = function() {
                              $http
                                  .get(UrlAtelier + "/marche/getAll")
                                  .success(
                                      function(dataMarche) {

                                        $scope.listeMarche = dataMarche;
                                        $log.debug("Marches : " + dataMarche.length);
                                      });
                            }

                            $scope.listeFicheSuiveuseFct = function() {
                              $log.debug("get listFicheSuiveuse ..");
                              $http
                                  .get(UrlAtelier + "/ficheSuiveuse/getAll")
                                  .success(
                                      function(data) {

                                        $scope.listeFicheSuiveuse = data;
                                        $log.debug("listeFicheSuiveuses : " + data.length);
                                      });
                            }

                            //liste Methode pour : Preparation & Finissage
                            $scope.listeMethodeCalcule = [{"value":"poids","designation":"Poids"},
                                                          {"value":"volume","designation":"Volume"},
                                                          {"value":"direct","designation":"Directe"}]
                            
                            //liste Methode pour: Teinture PES
                            $scope.listeMethodeTeinturePES = [{"value":"poids","designation":"Poids"},
                                                              {"value":"poidsPES","designation":"poids PES"},
                                                              {"value":"volume","designation":"Volume"},
                                                              {"value":"volumePES","designation":"volume PES"},
                                                              {"value":"direct","designation":"Directe"}
                                                              ]
                            
                            //liste Methode pour: Teinture Coton
                            $scope.listeMethodeTeintureCoton = [  {"value":"poids","designation":"Poids"},
                                                                  {"value":"poidsCoton","designation":"Poids Coton"},
                                                                  {"value":"volume","designation":"Volume"},
                                                                  {"value":"volumeCoton","designation":"Volume Coton"},
                                                                  {"value":"direct","designation":"Directe"}
                                                              ]

                            $scope.listeMise();
                            $scope.listeProduitCache();
                            $scope.listeClientCache();
                            $scope.listeMetragesArticleCache();
                            $scope.listeArticle();
                            $scope.listSousFamillesArticleCache();
                            $scope.listeTraitementCaches();
                            $scope.listeMachines();
                            $scope.listeControles();
                            $scope.listeMarche();
                            $scope.listeFicheSuiveuseFct();

                            /***************************************************
                             *
                             ***************************************************/
                            /*$scope.checkMethode = function (methode){
                              if(methode == "direct"){
                                $scope.directActif = true;
                              }else{
                                $scope.directActif = false;
                              }
                              
                            }*/
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

                            /***************************************************
                             * Gestion des Fiches Suiveuses
                             **************************************************/
                            
                            /***************** FicheSuiveuse *****************/

                            $scope.initSubLists = function(){

                            $scope.listeTraitementMisePreparation = [
                                              { 'order':1,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':"",
                                                'type': "Preparation",
                                                'listElementRecetteMise': []
                                              }];
                           $scope.listeTraitementMiseTeinturePES = [
                                              { 'order':2,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':"",
                                                'type': "Teinture - PES",
                                                'listElementRecetteMise': []
                                              }];
                            $scope.listeTraitementsMiseTeintureCoton = [
                                              { 'order':3,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':"",
                                                'type': "Teinture - Coton",
                                                'listElementRecetteMise': []
                                              }];

                            $scope.listeTraitementsMiseFinissage = [
                                              { 'order':4,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':'',
                                                'type': "Finissage",
                                                'listElementRecetteMise': []
                                              }];

                            }

                            $scope.initSubLists();

                            
                            $scope.remplirFicheSuiveuse = function(idFicheSuiveuse) {
                            // getFicheSuiveuse
                              if(idFicheSuiveuse != null){
                                $scope.modeDuplication = "actif";
                                $http
                                          .get( UrlAtelier
                                                        + "/ficheSuiveuse/getById:" +idFicheSuiveuse)
                                          .success(
                                                 function(ficheSuiveuse) {

                                                  //viderSubLists
                                                  $scope.viderSubLists ();

                                                  //Liste des controle correspendantes à cette ficheSuiveuse
                                                      //Classifier listeElementControle : Teinture - Coton, Teinture - PES, Finissage, Preparation
                                                      $scope.classifierListElementControle(ficheSuiveuse.listElementControle);

                                                  //Liste des traitements correspendantes à cette ficheSuiveuse
                                                      //Classifier listeTraitementsMise : Teinture - Coton, Teinture - PES, Finissage, Preparation
                                                      $scope.classifierListeTraitementsMise(ficheSuiveuse.listeTraitementsMise);

                                                    //vider duplicated ficheSuiveuse : 'id : null'
                                                    ficheSuiveuse.id = undefined;

                                                    angular.forEach(ficheSuiveuse.listElementControle, function(elementControleMise, key){
                                                      
                                                        elementControleMise.id = null;
                                                    });

                                                    angular.forEach(ficheSuiveuse.listeTraitementsMise, function(elementTraitementMise, key){
                                                     
                                                      elementTraitementMise.id = null;
                                                      
                                                      angular.forEach(elementTraitementMise.listElementRecetteMise, function(elementRecetteMise, key){
                                                        elementRecetteMise.id = null;
                                                      });
                                                    
                                                    });

                                                    
                                                  //Affectation des listes à l'objet courant
                                                  $scope.ficheCourante = ficheSuiveuse;
                                                 });
                              }
                                   
                            };



                            /********************* Mise *********************/
                            $scope.clientId = {

                                status : ''
                            };
                            
                            $scope.remplirMise = function(idMise) {
                              $scope.remplir = true;
                              $log.debug("--- IdMise "+idMise);
                              
                              // getMise
                            if(angular.isDefined(idMise)){
                              if((idMise!= null)){
                                $http
                                    .get(
                                        UrlAtelier + "/mise/getMiseId:"+ idMise)
                                        .success(
                                    function(datagetMise) {
                                      $scope.resultatRechercheMise = datagetMise;
                                      
                                      //Mise : reference
                                        $log.debug("***Mise Reference : "+datagetMise.reference);
                                        $scope.resultatRechercheMise.referenceMise = datagetMise.reference;

                                      //Mise : metrage
                                        $log.debug("***Mise metrage : "+datagetMise.metrage);
                                        $scope.resultatRechercheMise.metrage = datagetMise.metrage;

                                      //Mise: poids
                                        $log.debug("***Mise poids : "+datagetMise.poids);
                                        $scope.resultatRechercheMise.poids = datagetMise.poids;

                                      //Produit :produitId
                                        $log.debug("***TissuId : " + datagetMise.produitId );
                                        $scope.resultatRechercheMise.produitId = datagetMise.produitId;
                                      //Client : partieIntId
                                        $log.debug("***ClientId : " + datagetMise.partieintId);
                                        $scope.resultatRechercheMise.partieIntId = datagetMise.partieintId;

                                      /**conversion Produit **/
                                      /*Methode 1: utilise le service getId pour convertir le produit avec l'Id x à sa propre reference
                                       */
                                      if(datagetMise.produitId!= null){
                                        $http
                                        .get(
                                            UrlCommun
                                                + "/produit/getId:"+ datagetMise.produitId)
                                        .success(
                                            function(datagetProduit) {
                                              $log.debug(" --TissuId : " + datagetProduit.id);
                                              $log.debug(" --TissuReference : " + datagetProduit.reference);
                                              $log.debug(" --TissuDesignation : " + datagetProduit.designation);
                                              $log.debug(" --TissuComposition : " + datagetProduit.composition);
                                              
                                              //affectation de produitId
                                              $scope.resultatRechercheMise.produitId = datagetProduit.id;
                                              $scope.resultatRechercheMise.reference = datagetProduit.reference;
                                              $scope.resultatRechercheMise.designation = datagetProduit.designation
                                              $scope.resultatRechercheMise.composition = datagetProduit.composition;
                                              $scope.resultatRechercheMise.referenceInterne = datagetProduit.referenceInterne;
                                            });
                                      }else{
                                        $log.debug(" -- aucun Produit n'est affecté à ce Rouleau !");
                                      }

                                    });
                              }else{
                                $log.debug("idMise is null");
                              }
                            }else{
                              $log.debug("idMise is Undefined");
                            }

                      } // end of method remplirMise()

                            $scope.pagingOptions = {
                                          pageSizes : [ 5, 10, 13 ],
                                          pageSize : 13,
                                          currentPage : 1
                                   };

                            

							//Init data list
							$scope.initMyData = [];
							
                            // Lister les Fiches
                            $scope.rechercherFiche = function(ficheCourante) {

                            	CoutsService.rechercheMultricritereAvecCout(ficheCourante).then(function(result) {
                					$scope.myData = result.list;
                					$scope.initMyData = $scope.myData;
                					
                                    // Pagination du resultat de recherche
                                    // data, page,pageSize
                                    $scope.setPagingData(
                                                         $scope.myData,
                                                         $scope.pagingOptions.currentPage,
                                                         $scope.pagingOptions.pageSize);

                                    $log.debug("listeFiche : "+ result.list.length);
                                    
                                    $scope.rechercheFicheForm.$setPristine();
                				}, function(error) {
                					console.log(error.statusText);
                				});
                            }
                     
                            //Init liste fiche (ng-grid)
                            $scope.rechercherFiche({});

                            // annuler Recherche FicheSuiveuse
                            $scope.annulerAjout = function() {
                              
                              //modePdf notActif
                              $scope.modePdf = "NotActif";
                              //modeDuplication 
                              $scope.modeDuplication = "Notactif";
                              
                              //init counterRecette
                              $scope.counterRecette = 0;

                              //OrdreControle -Preparation
                              $scope.indexPrepa = 0;
                              $scope.indexPES = 0;
                              $scope.indexCoton = 0;
                              $scope.indexFinissage = 0;

                              //counter recette s'incremente par 1 au moment du clique sur Ajouter Recette
                              $scope.counterRecette = 0;

                              $scope.resultatRechercheMise = [];
                              $scope.id = {};

                              $scope.idFiche = {};

                              //TODO init objetCourant 
                              $scope.ficheCouranteRecherche = {};

                              $scope.ficheCourante = {
                                                  /*"id": null,
                                                  "referenceMise": "",
                                                  "date": null,
                                                  "partieIntId": "",
                                                  "produitId": "",
                                                  "typeLivraison": "",
                                                  "marcheId":null,
                                                  "dateLivraison": null,
                                                  "dateLancement": null,
                                                  "cuisinier": "",
                                                  "conducteur": "",
                                                  "poids": "",
                                                  "observations": "",
                                                  "volume": "",
                                                  "rapportBain":null,
                                                  "laizeDemFini": null,
                                                  "listElementRecetteMise": [],
                                                  "listElementControle": [],
                                                  "listeTraitementsMise": []*/
                              };

                              //initCounter
                              $scope.orderCounter = 4;

                              // init ListeTraitements
                              $scope.listeTraitementsMise = [];
                              //init subListTraitement
                              $scope.initSubLists();

                              $scope.listElementControle = [];
                              //init subListControle -- mode Creation
                              $scope.listControleCotonInit = [  {//PH
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 temps:"",
                                                                 type:"Teinture - Coton",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 1,
                                                                 ficheSuiveuseId:null
                                                               },
                                                               {//TR
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 methode: "",
                                                                 temps:"",
                                                                 type:"Teinture - Coton",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 2,
                                                                 ficheSuiveuseId:null
                                                               }];

                            $scope.listControlePESInit = [     {//PH
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 methode: "",
                                                                 temps:"",
                                                                 type:"Teinture - PES",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 1,
                                                                 ficheSuiveuseId:null

                                                               },
                                                               {//TR
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 methode: "",
                                                                 temps:"",
                                                                 type:"Teinture - PES",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 2,
                                                                 ficheSuiveuseId:null
                                                               }];

                              $scope.listControleFinissageInit = [{//PH
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 methode: "",
                                                                 temps:"",
                                                                 type:"Finissage",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 1,
                                                                 ficheSuiveuseId:null
                                                               },
                                                               {//TR
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 methode: "",
                                                                 temps:"",
                                                                 type:"Finissage",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 2,
                                                                 ficheSuiveuseId:null
                                                               }];

                               $scope.listControlePreparationInit = [{//PH
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 methode: "",
                                                                 temps:"",
                                                                 type:"Preparation",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 1,
                                                                 ficheSuiveuseId:null
                                                               },
                                                               {//TR
                                                                 valeur: null,
                                                                 valeurCorrige: null ,
                                                                 methode: "",
                                                                 temps:"",
                                                                 type:"Preparation",
                                                                 observations:"",
                                                                 valeurTheorique: null ,
                                                                 controleId: 2,
                                                                 ficheSuiveuseId:null
                                                               }];

                              //init subListControle -- mode Modification
                              $scope.listControleCoton = [];
                              $scope.listControlePES = [];
                              $scope.listControleFinissage = [];
                              $scope.listControlePreparation = [];

                              // init listesRecettesMise 
                              $scope.listElementRecetteMise = [];
                             
                              //refresh Grid
                              $scope.rechercherFiche({});
                              //init des champs
                              $scope.rechercheFicheForm.$setPristine();
                              $scope.creationFicheForm.$setPristine();
                              $scope.prepaForm.$setPristine();
                              $scope.teintureCotonForm.$setPristine();
                              $scope.teinturePESForm.$setPristine();
                              $scope.finissageForm.$setPristine();
                              
                              //mode Recherche & Grid
                              $scope.displayMode = "list";
                            }
                                
                             // Affectation FicheSuiveuse
                            $scope.affectationFicheSuiveuse = function(ficheSuiveuse) {

                                  $scope.ficheCourante = {};

                                  $scope.ficheCourante = ficheSuiveuse ? angular
                                                  .copy(ficheSuiveuse) : {};
                                  //get new lisFichesuiveuse
                                  $scope.listeFicheSuiveuseFct();
                                  
                                  //mode edit activé
                                   $scope.displayMode = "edit";

                            }

                            
                            //Classification des listeControle selon le Type
                            $scope.classifierListElementControle = function(listElementControle){
                              // subListes de listElementControle: Teinture - Coton, Teinture - PES, Finissage, Preparation
                              angular.forEach(listElementControle, function(elementControleMise, key){
                                
                                var type = elementControleMise.type;
                                //$log.debug("--- TYPE "+ type);

                                 switch (type) {
                                      case 'Teinture - Coton':
                                          $log.debug("Teinture - Coton : ");
                                          $scope.listControleCoton.push(elementControleMise);
                                          break;
                                      case 'Teinture - PES':
                                          $log.debug("Teinture - PES : ");
                                          $scope.listControlePES.push(elementControleMise);
                                          break;
                                      case 'Finissage':
                                          $log.debug("Finissage : ");
                                          $scope.listControleFinissage.push(elementControleMise);
                                          break;
                                      case 'Preparation':
                                          $log.debug("Preparation : ");
                                          $scope.listControlePreparation.push(elementControleMise);
                                          break;
                                          
                                      default:
                                           $log.debug("elementControleMise n'a pas encore de Type ");
                                  }
                                
                              });
                            }

                            $scope.classifierListeTraitementsMise = function (listeTraitementsMise){
                              var counterRecetteTmp = 0;
                              var counterTraitementTmp = 0;

                              angular.forEach(listeTraitementsMise, function(elementTraitementMise, key){
                                         
                              /* Init orderCounter: 
                                - en mode Creation orderCounter doit commencer par 4
                                - en mode Modification orderCounter doit commencer par le plus grand ordre 
                                  déja atrribué à la listeTraitementMise. En cas ou est vide ou a 
                                  que un nbre d'element < 4, orderCounter + 4 pour qu'il n'y aura pas de 
                                  chauvauchement d'ordre
                              */
                                if($scope.modePdf == "actif"){
                                  $log.debug("Traitement ORDER :" + elementTraitementMise.order );
                                    if(elementTraitementMise.order > counterTraitementTmp){
                                      counterTraitementTmp = elementTraitementMise.order;
                                    }
                                }                 

                                
                                if($scope.modeDuplication == "actif"){
                                  $log.debug("Traitement ORDER :" + elementTraitementMise.order );
                                    if(elementTraitementMise.order > counterTraitementTmp){
                                      counterTraitementTmp = elementTraitementMise.order;
                                    }
                                } 
                                //init counterRecette
                                  angular.forEach(elementTraitementMise.listElementRecetteMise, function(elementRecetteMise, key){
                                    $log.debug("Recette ORDER :" + elementRecetteMise.order );
                                    if(elementRecetteMise.order > counterRecetteTmp){
                                      counterRecetteTmp = elementRecetteMise.order;
                                    }
                                  });
                                
                                var type = elementTraitementMise.type;
                                $log.debug("--- TYPE "+ type);
                                
                                 switch (type) {
                                      case 'Teinture - Coton':
                                          $log.debug("Teinture - Coton :"); 
                                          //init OrdreCounter
                                          $scope.indexCoton = $scope.listeTraitementsMiseTeintureCoton.length;
                                          $scope.listeTraitementsMiseTeintureCoton.push(elementTraitementMise);
                                          break;
                                      case 'Teinture - PES':
                                          $log.debug("Teinture - PES : ");
                                          //init OrdreCounter
                                          $scope.indexPES = $scope.listeTraitementMiseTeinturePES.length;
                                          $scope.listeTraitementMiseTeinturePES.push(elementTraitementMise);
                                          break;
                                      case 'Finissage':
                                          $log.debug("Finissage : ");
                                          //init OrdreCounter
                                          $scope.indexFinissage = $scope.listeTraitementsMiseFinissage.length;
                                          $scope.listeTraitementsMiseFinissage.push(elementTraitementMise);
                                          break;
                                      case 'Preparation':
                                          $log.debug("Preparation : ");
                                          //initOrdreCounter
                                          $scope.indexPrepa = $scope.listeTraitementMisePreparation.length;
                                          $scope.listeTraitementMisePreparation.push(elementTraitementMise);
                                          break;
                                          
                                      default:
                                           $log.debug("elementTraitementMise n'a pas encore de Type ");
                                  }

                              });

                              /*init orderCounter: 
                                - en mode Creation orderCounter commence par 4
                                - en mode Modification orderCounter commence par le plus grand ordre deja atrribué à la listeTraitementMise
                              */
                              if($scope.modePdf == "actif"){
                                $log.debug("----------- dernier ordre dans les listTraitementMise :" + counterTraitementTmp );
                                $scope.orderCounter = counterTraitementTmp + 4;
                              }
                              
                              if($scope.modeDuplication == "actif"){
                                $log.debug("----------- dernier ordre dans les listTraitementMise :" + counterTraitementTmp );
                                $scope.orderCounter = counterTraitementTmp + 4;
                              }

                              //init counterRecette 
                              $log.debug("----------- dernier ordre dans les listElementRecetteMise :" + counterRecetteTmp );
                              $scope.counterRecette = counterRecetteTmp;


                              $log.debug("-Prep : " +$scope.listeTraitementMisePreparation.length); 
                              $log.debug("-PES : " +$scope.listeTraitementMiseTeinturePES.length);
                              $log.debug("-Coton : " +$scope.listeTraitementsMiseTeintureCoton.length);
                              $log.debug("-Fini : " +$scope.listeTraitementsMiseFinissage.length);
                              
                              //mode d'affichage des listes : Liste Predefinie vide ou liste de F.Suiveuse remplie     
                              var listeTraitementMisePreparationTmp = $scope.listeTraitementMisePreparation;
                              if(listeTraitementMisePreparationTmp.length > 0 ){
                                $log.debug("-Prep > " +listeTraitementMisePreparationTmp.length); 
                                $scope.listeTraitementMisePreparation = listeTraitementMisePreparationTmp;
                              }else{
                                $scope.listeTraitementMisePreparation = [
                                                                          { 'order':1,
                                                                            'traitementId':null,
                                                                            'machineId':"",
                                                                            'observations':"",
                                                                            'type': "Preparation",
                                                                            'listElementRecetteMise': []
                                                                          }];

                              }
                              var listeTraitementMiseTeinturePESTmp = $scope.listeTraitementMiseTeinturePES;
                              if(listeTraitementMiseTeinturePESTmp.length > 0 ){
                                $log.debug("-PES > " + listeTraitementMiseTeinturePESTmp.length);
                                $scope.listeTraitementMiseTeinturePES = listeTraitementMiseTeinturePESTmp;
                              }else{
                                $scope.listeTraitementMiseTeinturePES = [
                                                                          { 'order':2,
                                                                            'traitementId':null,
                                                                            'machineId':"",
                                                                            'observations':"",
                                                                            'type': "Teinture - PES",
                                                                            'listElementRecetteMise': []
                                                                          }];
                              }
                              var listeTraitementsMiseTeintureCotonTmp = $scope.listeTraitementsMiseTeintureCoton;
                              if(listeTraitementsMiseTeintureCotonTmp.length > 0 ){
                                $log.debug("-Coton > " + listeTraitementsMiseTeintureCotonTmp.length);
                                $scope.listeTraitementsMiseTeintureCoton = listeTraitementsMiseTeintureCotonTmp;
                              }else {
                                $scope.listeTraitementsMiseTeintureCoton = [
                                                                            { 'order':3,
                                                                              'traitementId':null,
                                                                              'machineId':"",
                                                                              'observations':"",
                                                                              'type': "Teinture - Coton",
                                                                              'listElementRecetteMise': []
                                                                            }];
                              }
                              var listeTraitementsMiseFinissageTmp = $scope.listeTraitementsMiseFinissage;
                              if(listeTraitementsMiseFinissageTmp.length > 0 ){
                                $log.debug("-Fini > " + listeTraitementsMiseFinissageTmp.length);
                                $scope.listeTraitementsMiseFinissage = listeTraitementsMiseFinissageTmp;
                              }else{
                                $scope.listeTraitementsMiseFinissage = [
                                                                        { 'order':4,
                                                                          'traitementId':null,
                                                                          'machineId':"",
                                                                          'observations':'',
                                                                          'type': "Finissage",
                                                                          'listElementRecetteMise': []
                                                                        }];
                              }



                            }

                            // Sauvegarder Fiche Suiveuse
                            $scope.sauvegarderFicheSuiveuse = function(ficheSuiveuse) {

                                   if (angular.isDefined(ficheSuiveuse.id)) {
                                           $log.debug("Sauvegarder Modification : " + ficheSuiveuse.id);
                                           $scope.updateFicheSuiveuse(ficheSuiveuse);
                                   } else {
                                           $log.debug("Sauvegarder Ajout : " + ficheSuiveuse.id);
                                           $scope.creerFicheSuiveuse(ficheSuiveuse);
                                   }
                                   //get new list Ficheuiveuse
                                   $scope.listeFicheSuiveuseFct();
                                   
                            }

                            // MiseAJour Fichesuiveuse
                            $scope.updateFicheSuiveuse = function(ficheSuiveuse) {
                              
                              //Controle
                              //Tmp listControleRecette
                              var listControleRecetteTmp = [];

                               //listControleRecette: Coton, PES, Finissage, Preparation:
                                angular.forEach($scope.listControleCoton, function(elementControleCoton, key){
                                  
                                  listControleRecetteTmp.push(elementControleCoton);
                                  
                                });

                               angular.forEach($scope.listControlePES, function(elementControlePES, key){
                                
                                  listControleRecetteTmp.push(elementControlePES);
                                
                              });

                              angular.forEach($scope.listControleFinissage, function(elementControleFinissage, key){
                              
                                listControleRecetteTmp.push(elementControleFinissage);
                              
                              });

                              angular.forEach($scope.listControlePreparation, function(elementControlePreparation, key){
                              
                                listControleRecetteTmp.push(elementControlePreparation);
                              
                              });

                              //Operations & Recette 
                              //Tmp listeTraitementsMiseTmp
                              var listeTraitementsMiseTmp = []; 

                              //listeTraitementsMise: TeinturePES, TeintureCoton, Finissage, Preparation
                              angular.forEach($scope.listeTraitementMiseTeinturePES, function(elementOperationTeinturePES, key){
                                  $log.debug("--Tein.PES.traitementId : "+ elementOperationTeinturePES.traitementId);
                                if(elementOperationTeinturePES.traitementId != null){
                                      $log.debug("Push");
                                      listeTraitementsMiseTmp.push(elementOperationTeinturePES);
                                }
                                  
                              });

                              angular.forEach($scope.listeTraitementsMiseTeintureCoton, function(elementOperationTeintureCoton, key){
                                  $log.debug("--Tein.Coton.traitementId : "+ elementOperationTeintureCoton.traitementId);
                                if(elementOperationTeintureCoton.traitementId != null){
                                      $log.debug("Push");
                                       listeTraitementsMiseTmp.push(elementOperationTeintureCoton);
                                }

                              });

                              angular.forEach($scope.listeTraitementsMiseFinissage, function(elementOperationFinissage, key){
                                $log.debug("--Finissage.traitementId : "+ elementOperationFinissage.traitementId);
                                if(elementOperationFinissage.traitementId != null){
                                      $log.debug("Push");
                                      listeTraitementsMiseTmp.push(elementOperationFinissage);
                                }
                              
                              });

                              angular.forEach($scope.listeTraitementMisePreparation, function(elementOperationPreparation, key){
                                $log.debug("--Prepa.traitementId : "+ elementOperationPreparation.traitementId);
                                  if(elementOperationPreparation.traitementId != null){
                                    $log.debug("Push");
                                    listeTraitementsMiseTmp.push(elementOperationPreparation);
                                  }
                              
                              });

                              // Affectation des listes à l'objet
                             
                              ficheSuiveuse.listElementControle = listControleRecetteTmp; 
                              ficheSuiveuse.listeTraitementsMise = listeTraitementsMiseTmp;
                                
                            $log.debug("-- OBJ modifié : "+JSON.stringify(ficheSuiveuse, null, "    ") );
                            
                                   $http  .post( UrlAtelier + "/ficheSuiveuse/update",   ficheSuiveuse)
                                          .success(
                                                 function(ficheSuiveuseId) {
                                                       
                                                 //getBonSortie 
                                                 $http  .get(UrlAtelier + "/ficheSuiveuse/getById:"
                                                                                     + ficheSuiveuseId)
                                                        .success(
                                                               function(dataGetFicheSuiveuse) {
                                                               //modeValider true
                                                               $scope.modeValider = true;

                                                               // bouton PDF activé
                                                                $scope.modePdf = "actif";
                                                                

                                                      //vider subLists
                                                      $scope.viderSubLists();

                                                      //Liste des controle correspendantes à cette ficheSuiveuse
                                                      //Classifier listeElementControle : Teinture - Coton, Teinture - PES, Finissage, Preparation
                                                      $scope.classifierListElementControle(dataGetFicheSuiveuse.listElementControle);

                                                      //Liste des traitements correspendantes à cette ficheSuiveuse
                                                      //Classifier listeTraitementsMise : Teinture - Coton, Teinture - PES, Finissage, Preparation
                                                      $scope.classifierListeTraitementsMise(dataGetFicheSuiveuse.listeTraitementsMise);

                                                      /** Affectation  **/
                                                      //Affectation des listes à l'objet courant
                                                      $scope.ficheCourante = dataGetFicheSuiveuse; 

                                                               });
                                                 });
                            }

                            $scope.viderSubLists = function(){
                              //init subListOperation
                              $scope.listeTraitementMisePreparation = [];
                              $scope.listeTraitementMiseTeinturePES = [];
                              $scope.listeTraitementsMiseTeintureCoton = [];
                              $scope.listeTraitementsMiseFinissage = [];
                              //init subListControle
                              $scope.listControleCoton = [];
                              $scope.listControlePES = [];
                              $scope.listControleFinissage = [];
                              $scope.listControlePreparation = [];
                            }

                            // Création FicheSuiveuse
                            $scope.creerFicheSuiveuse = function(ficheSuiveuse) {
                              
                              //Affectation des attributs recherchés
                              $log.debug("------ReferenceMise "+$scope.resultatRechercheMise.referenceMise);
                              ficheSuiveuse.referenceMise = $scope.resultatRechercheMise.referenceMise;
                              
                              $log.debug("--PoidsMise "+$scope.resultatRechercheMise.poids);
                              ficheSuiveuse.poids = $scope.resultatRechercheMise.poids;

                              $log.debug("--TissuId "+ $scope.resultatRechercheMise.produitId);
                              ficheSuiveuse.produitId = $scope.resultatRechercheMise.produitId;
                              
                              $log.debug("--ClientId "+ $scope.resultatRechercheMise.partieIntId);
                              ficheSuiveuse.partieIntId = $scope.resultatRechercheMise.partieIntId;


                              /*//Tmp listControleRecette
                              var listControleRecetteInitTmp = [];

                               //listControleRecette: Coton, PES, Finissage, Preparation:
                                angular.forEach($scope.listControleCotonInit, function(elementControleCotonInit, key){
                                  
                                  listControleRecetteInitTmp.push(elementControleCotonInit);
                                  
                                });

                               angular.forEach($scope.listControlePESInit, function(elementControlePESInit, key){
                                  
                                  listControleRecetteInitTmp.push(elementControlePESInit);
                                
                              });

                              angular.forEach($scope.listControleFinissageInit, function(elementControleFinissageInit, key){
                              
                                listControleRecetteInitTmp.push(elementControleFinissageInit);
                              
                              });

                              angular.forEach($scope.listControlePreparationInit, function(elementControlePreparationInit, key){
                              
                                listControleRecetteInitTmp.push(elementControlePreparationInit);
                              
                              }); */

                              //Controle
                              //Tmp listControleRecette
                              var listControleRecetteTmp = [];

                               //listControleRecette: Coton, PES, Finissage, Preparation:
                                angular.forEach($scope.listControleCoton, function(elementControleCoton, key){
                                  
                                  listControleRecetteTmp.push(elementControleCoton);
                                  
                                });

                               angular.forEach($scope.listControlePES, function(elementControlePES, key){
                                
                                  listControleRecetteTmp.push(elementControlePES);
                                
                              });

                              angular.forEach($scope.listControleFinissage, function(elementControleFinissage, key){
                              
                                listControleRecetteTmp.push(elementControleFinissage);
                              
                              });

                              angular.forEach($scope.listControlePreparation, function(elementControlePreparation, key){
                              
                                listControleRecetteTmp.push(elementControlePreparation);
                              
                              });

                              //Operations & Recette 
                              //Tmp listeTraitementsMiseTmp
                              var listeTraitementsMiseTmp = []; 

                              //listeTraitementsMise: TeinturePES, TeintureCoton, Finissage, Preparation
                              angular.forEach($scope.listeTraitementMiseTeinturePES, function(elementOperationTeinturePES, key){
                                  $log.debug("--Tein.PES.traitementId : "+ elementOperationTeinturePES.traitementId);
                                if(elementOperationTeinturePES.traitementId != null){
                                      $log.debug("Push");
                                      listeTraitementsMiseTmp.push(elementOperationTeinturePES);
                                }
                                  
                              });

                              angular.forEach($scope.listeTraitementsMiseTeintureCoton, function(elementOperationTeintureCoton, key){
                                  $log.debug("--Tein.Coton.traitementId : "+ elementOperationTeintureCoton.traitementId);
                                if(elementOperationTeintureCoton.traitementId != null){
                                      $log.debug("Push");
                                       listeTraitementsMiseTmp.push(elementOperationTeintureCoton);
                                }

                              });

                              angular.forEach($scope.listeTraitementsMiseFinissage, function(elementOperationFinissage, key){
                                $log.debug("--Finissage.traitementId : "+ elementOperationFinissage.traitementId);
                                if(elementOperationFinissage.traitementId != null){
                                      $log.debug("Push");
                                      listeTraitementsMiseTmp.push(elementOperationFinissage);
                                }
                              
                              });

                              angular.forEach($scope.listeTraitementMisePreparation, function(elementOperationPreparation, key){
                                $log.debug("--Prepa.traitementId : "+ elementOperationPreparation.traitementId);
                                  if(elementOperationPreparation.traitementId != null){
                                    $log.debug("Push");
                                    listeTraitementsMiseTmp.push(elementOperationPreparation);
                                  }
                              
                              });


                             // Affectation des listes à l'objet
                             // ficheSuiveuse.listElementRecetteMise = listElementRecetteMiseTmp ;
                              ficheSuiveuse.listElementControle = listControleRecetteTmp;  //Init : listControleRecetteInitTmp
                              ficheSuiveuse.listeTraitementsMise = listeTraitementsMiseTmp;
                              
                                  $log.debug("-- OBJ crée : "+JSON.stringify(ficheSuiveuse, null, "    ") );
                                   
                                   $http  .post(UrlAtelier + "/ficheSuiveuse/create", ficheSuiveuse)
                                          .success(
                                                 function(ficheSuiveuseId) {
                                                        
                                                        $log.debug("Success Creation"+ ficheSuiveuse.designation);
                                                        
                                                        //getFicheSuiveuse 
                                                        $http  .get(UrlAtelier + "/ficheSuiveuse/getById:"
                                                                                                   + ficheSuiveuseId)
                                                               .success(
                                                                      function(dataGetFicheSuiveuse) {
                                                                             
                                                                             $log.debug("dataGetFicheSuiveuse : " + JSON.stringify(dataGetFicheSuiveuse,null,"  ") );
                                                                             // bouton PDF activé
                                                                             $scope.modePdf = "actif";
                                                                             
                                                                            //vider subLists
                                                                            $scope.viderSubLists();

                                                                             //Liste des controle correspendantes à cette ficheSuiveuse
                                                                            //Classifier listeElementControle : Teinture - Coton, Teinture - PES, Finissage, Preparation
                                                                            $scope.classifierListElementControle(dataGetFicheSuiveuse.listElementControle);

                                                                            //Liste des traitements correspendantes à cette ficheSuiveuse
                                                                            //Classifier listeTraitementsMise : Teinture - Coton, Teinture - PES, Finissage, Preparation
                                                                            $scope.classifierListeTraitementsMise(dataGetFicheSuiveuse.listeTraitementsMise);

                                                                            /** Affectation  **/

                                                                            //Affectation des listes à l'objet courant
                                                                            $scope.ficheCourante = dataGetFicheSuiveuse; 
                                                                            
                                                                             });
                                                 });
                            }

                            // Suppression FcheSuiveuse
                            $scope.supprimerFicheSuiveuse = function() {
                                   $http(
                                          {
                                                 method : "DELETE",
                                                 url : UrlAtelier
                                                               + "/ficheSuiveuse/delete:"+ $scope.myData[$scope.index].id
                                          }).success(function() {
                                                 $log.debug("Success Delete");
                                                 $scope.myData.splice($scope.index, 1);
                                                 $scope.closePopupDelete();
                                          
                                   });
                                   $scope.closePopupDelete();
                                   $scope.rechercherFiche({});
                            }  

                            /**************************************************
                            *Gestion de suppression d'une ligne d'un tableau
                            *************************************************/

                            $scope.deleteValue = "oui";
                              // Annuler Ajout
                              $scope.cancelAddFicheSuiveuse = function(rowform, index,
                                  id, designation, liste) {
                                 $log.debug("******Designation"+liste[0].designation);
                                if (angular.isDefined(id)) {

                                  $log.debug("DEF ID");
                                  $scope.deleteValue = "non";
                                  rowform.$cancel();
                                  $log.debug("CANCEL");
                                } else {
                                  $log.debug("UND ID  " + id);
                                  if (designation == "") {
                                    $scope.deleteValue == "oui"
                                    $log.debug("Designation : "
                                        + designation);
                                    $log.debug("$scope.deleteValueOUI : "
                                        + $scope.deleteValue);
                                    liste.splice(index, 1);
                                    rowform.$cancel();
                                    $log.debug("DELETE")
                                  }else {
                                    $log.debug("Designation :"
                                        + designation);
                                    $log.debug("$scope.deleteValueNON : "
                                        + $scope.deleteValue);
                                    rowform.$cancel();
                                    $log.debug("CANCEL");
                                  }
                                }
                                $scope.deleteValue = "oui";
                              }

                             /**************************************************
                              * Conversion Id / Designation Tableau 
                              *************************************************/
                              // showArticleDetails : reference, Designation, poids, famille

                              $scope.articleId ={
                                status:''
                              }

                               $scope.sousFamilleId ={
                                status:''
                              }

                              $scope.showArticleDetails = function(id, attributRecherche) {
                                
                                $scope.articleId.status = id;
                                var selected = $filter('showProduitFilterBS')(
                                    $scope.listeArticle, {
                                      id : $scope.articleId.status
                                    });
                                if ($scope.articleId.status && selected.length) {
                                  if(attributRecherche == 'reference'){
                                    return selected[0].ref;
                                  }
                                  else if(attributRecherche == 'designation'){
                                    return selected[0].designation;
                                  }
                                  else if(attributRecherche == 'poids'){
                                    return selected[0].poids;
                                  }
                                  else if(attributRecherche == 'sousFamille'){ // de sousFamilleId on récupère la familleid puis on fait la conversion id => designation
                                    $scope.sousFamilleId.status = selected[0].sousFamilleArtEntite;
                                    var selected2 = $filter('showProduitFilterBS')(
                                        $scope.listSousFamillesArticleCache, {
                                          id : $scope.sousFamilleId.status
                                        });
                                      if ($scope.sousFamilleId.status && selected2.length) {
                                        return selected2[0].designation;
                                      }else{
                                        return "NotSet";
                                      }
                                  }
                                } 
                              }

                              // showTraitementDetails : Designation

                              $scope.traitementId ={
                                status:''
                              }
                              $scope.showTraitementDetails = function(id, attributRecherche) {
                                
                                $scope.traitementId.status = id;
                                var selected = $filter('showProduitFilterBS')(
                                    $scope.listeTraitementCaches, {
                                      id : $scope.traitementId.status
                                    });
                                if ($scope.traitementId.status && selected.length) {
                                  if(attributRecherche == 'designation'){
                                    return selected[0].designation;
                                  }
                                } 
                              }

                              // showControleDetails : Designation
                              $scope.controleId ={
                                status:''
                              }
                              $scope.showControleDetails = function(id, attributRecherche) {
                                
                                $scope.controleId.status = id;
                                var selected = $filter('showProduitFilterBS')(
                                    $scope.listeControles, {
                                      id : $scope.controleId.status
                                    });
                                if ($scope.controleId.status && selected.length) {
                                  if(attributRecherche == 'designation'){
                                    return selected[0].designation;
                                  }
                                } 
                              }

                              // showMachineDetails : Designation
                              $scope.machineleId ={
                                status:''
                              }
                              $scope.showMachineDetails = function(id, attributRecherche) {
                                
                                $scope.machineleId.status = id;
                                var selected = $filter('showProduitFilterBS')(
                                    $scope.listeMachines, {
                                      id : $scope.machineleId.status
                                    });
                                if ($scope.machineleId.status && selected.length) {
                                  if(attributRecherche == 'designation'){
                                    return selected[0].designation;
                                  }
                                } 
                              }
                          
                            /***************************************************
                             * Gestion Operations : Teinture, Finissage, Preparation
                             **************************************************/
                            //Ordre Traitement  
                            $scope.orderCounter = 4;

            //---------- Preparation 
                            
                            $scope.listeTraitementMisePreparation = [];
                            $scope.listeTraitementMisePreparation = [
                                              { 'order':1,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':"",
                                                'type': "Preparation",
                                                'listElementRecetteMise': []
                                              }];

                            $scope.AjouterBlocPrepa = function(){
                              $scope.orderCounter++;
                              //bloc indexPrepa : index du nouveau Bloc à créer
                              $scope.indexPrepa++;
                              
                              $scope.listeTraitementMisePreparation[$scope.indexPrepa]= 
                                                { "order":$scope.orderCounter,
                                                  'traitementId':null,
                                                  'machineId':"",
                                                  'observations':"",
                                                  'type': "Preparation",
                                                  'listElementRecetteMise': []
                                               
                                              };
                                             
                              }

                           /* pour counterRecettes, 
                                - pour une nouvelle Création :
                                    counterRecettes = 0;
                                - pour une modification: 
                                    counterRecettes = le plus grand ordre attribué aux 
                                    listesElementRecette */ 
                            
                            $scope.AjouterRecettePrepa = function(index){
                              $scope.counterRecette++;
                              $log.debug("---------- Index "+index);
                              if(angular.isDefined($scope.listeTraitementMisePreparation[index].listElementRecetteMise)){
                                $log.debug("defined =>> push");
                                $scope.listeTraitementMisePreparation[index].listElementRecetteMise.push(
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Preparation"
                                                            });
                                                            
                              }else{
                                $log.debug("Undefined =>> Affectation");
                                $scope.listeTraitementMisePreparation[index].listElementRecetteMise = [
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Preparation"
                                                            }];
                              }

                            }

                            $scope.removeRecettePreparation = function(indexX, indexY){
                              $log.debug("Delete Recette .. " + $scope.listeTraitementMisePreparation[indexX].listElementRecetteMise[indexY]);
                              $scope.listeTraitementMisePreparation[indexX].listElementRecetteMise.splice(indexY, 1);
                            }
                             // remove Operations Preparation
                            $scope.removeTraitementMisePreparation = function(indexX) {
                              $log.debug("Delete Bloc Preparation .. "+ indexX);
                              $scope.listeTraitementMisePreparation.splice(indexX, 1);
                              $scope.indexPrepa = $scope.listeTraitementMisePreparation.length -1;
                            };

            //---------- Teinture PES
                            //OrdreControlePreparation
                           
                           $scope.listeTraitementMiseTeinturePES = [];
                           $scope.listeTraitementMiseTeinturePES = [
                                              { 'order':2,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':"",
                                                'type': "Teinture - PES",
                                                'listElementRecetteMise': []
                                              }];

                            $scope.AjouterBlocTeinturePES = function(){
                              $scope.orderCounter++;
                              $log.debug("AjouterBlocTeinturePES");
                              //bloc indexPES
                              $scope.indexPES++;
                              

                            $scope.listeTraitementMiseTeinturePES[$scope.indexPES]= 
                                              { "order":$scope.orderCounter,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':'',
                                                'type': "Teinture - PES",
                                                'listElementRecetteMise': []
                                             
                                            };
                           }
                            
                            $scope.AjouterRecetteTeinturePES = function(index){
                              $scope.counterRecette++;
                              $log.debug("---------- Index "+index);
                              if(angular.isDefined($scope.listeTraitementMiseTeinturePES[index].listElementRecetteMise)){
                                $log.debug("defined =>> push");
                                $scope.listeTraitementMiseTeinturePES[index].listElementRecetteMise.push(
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Teinture - PES"
                                                            });
                                                            
                              }else{
                                $log.debug("Undefined =>> Affectation");
                                $scope.listeTraitementMiseTeinturePES[index].listElementRecetteMise = [
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Teinture - PES"
                                                            }];
                              }

                            }

                            $scope.removeRecetteTeinturePES = function(indexX, indexY){
                              $log.debug("Delete Recette .. " + $scope.listeTraitementMiseTeinturePES[indexX].listElementRecetteMise[indexY]);
                              $scope.listeTraitementMiseTeinturePES[indexX].listElementRecetteMise.splice(indexY, 1);
                            }

                             // remove Operations PES
                             $scope.removeTraitementMiseTeinturePES = function(indexX) {
                                $log.debug("Delete Bloc Teinture PES .. "+ indexX);
                               $scope.listeTraitementMiseTeinturePES.splice(indexX, 1);
                               $scope.indexPES = $scope.listeTraitementMiseTeinturePES.length -1;
                             };

            //------------Teinture Coton

                            //OrdreControleCoton

                            $scope.listeTraitementsMiseTeintureCoton = [];
                            $scope.listeTraitementsMiseTeintureCoton = [
                                              { 'order':3,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':"",
                                                'type': "Teinture - Coton",
                                                'listElementRecetteMise': []
                                              }];

                            $scope.AjouterBlocTeintureCoton = function(){
                              $scope.orderCounter++;
                             // $log.debug("AjouterBlocTeintureCoton");
                              //bloc indexCoton
                              $scope.indexCoton++;
                              
                            $scope.listeTraitementsMiseTeintureCoton[$scope.indexCoton]= 
                                              { "order":$scope.orderCounter,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':'',
                                                'type': "Teinture - Coton",
                                                'listElementRecetteMise': []
                                             
                                            };
                           }
                            
                            $scope.AjouterRecetteTeintureCoton = function(index){
                              $scope.counterRecette++;
                              $log.debug("---------- Index "+index);
                              if(angular.isDefined($scope.listeTraitementsMiseTeintureCoton[index].listElementRecetteMise)){
                                $log.debug("defined =>> push");
                                $scope.listeTraitementsMiseTeintureCoton[index].listElementRecetteMise.push(
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Teinture - Coton"
                                                            });
                                                            
                              }else{
                                $log.debug("Undefined =>> Affectation");
                                $scope.listeTraitementsMiseTeintureCoton[index].listElementRecetteMise = [
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Teinture - Coton"
                                                            }];
                              }

                            }

                            // remove RecetteTeintureCoton
                            $scope.removeRecetteTeintureCoton = function(indexX, indexY){
                              $log.debug("Delete Recette Coton .. " + $scope.listeTraitementsMiseTeintureCoton[indexX].listElementRecetteMise[indexY]);
                              $scope.listeTraitementsMiseTeintureCoton[indexX].listElementRecetteMise.splice(indexY, 1);
                            }
                            
                            // remove Bloc TeintureCoton
                            $scope.removeTraitementMiseTeintureCoton = function(indexX) {
                              $log.debug("Delete Bloc Teinture-Coton .. " + indexX);
                              $scope.listeTraitementsMiseTeintureCoton.splice(indexX, 1);
                              $scope.indexCoton = $scope.listeTraitementsMiseTeintureCoton.length -1;
                             };

            // --------- Finissage

                             //OrdreControleFinissage

                            $scope.listeTraitementsMiseFinissage = [];
                            $scope.listeTraitementsMiseFinissage = [
                                              { 'order':4,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':'',
                                                'type': "Finissage",
                                                'listElementRecetteMise': []
                                              }];

                            $scope.AjouterBlocFinissage = function(){
                              $scope.orderCounter++;
                             // $log.debug("AjouterBlocFinissage");
                              //bloc indexFinissage
                              $scope.indexFinissage++;

                            $scope.listeTraitementsMiseFinissage[$scope.indexFinissage]= 
                                              { "order":$scope.orderCounter,
                                                'traitementId':null,
                                                'machineId':"",
                                                'observations':'',
                                                'type': "Finissage",
                                                'listElementRecetteMise': []
                                             
                                            };
                           }
                            
                            $scope.AjouterRecetteFinissage = function(index){
                              $scope.counterRecette++;
                              $log.debug("---------- Index "+index);
                              if(angular.isDefined($scope.listeTraitementsMiseFinissage[index].listElementRecetteMise)){
                                $log.debug("defined =>> push");
                                $scope.listeTraitementsMiseFinissage[index].listElementRecetteMise.push(
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Finissage"
                                                            });
                                                            
                              }else{
                                $log.debug("Undefined =>> Affectation");
                                $scope.listeTraitementsMiseFinissage[index].listElementRecetteMise = [
                                                            { "articleId": '',
                                                              "order":$scope.counterRecette,
                                                              "pourcentage": null,
                                                              "methode":"",
                                                              "poids": null,
                                                              "rajout":null,
                                                              "temperature":null,
                                                              "temps":null,
                                                              "ph":null,
                                                              "observations": "",
                                                              "type": "Finissage"
                                                            }];
                              }

                            }
                            // remove RecetteFinissage
                            $scope.removeRecetteFinissage = function(indexX, indexY){
                              $log.debug("Delete Recette Finisage .. " + $scope.listeTraitementsMiseFinissage[indexX].listElementRecetteMise[indexY]);
                              $scope.listeTraitementsMiseFinissage[indexX].listElementRecetteMise.splice(indexY, 1);
                            }
                            
                             // remove Bloc TraitementFinissage
                             $scope.removeTraitementMiseFinissage = function(indexX) {
                              $log.debug("Delete Bloc Finisage .. "+ indexX);
                              $scope.listeTraitementsMiseFinissage.splice(indexX, 1);
                              $scope.indexFinissage = $scope.listeTraitementsMiseFinissage.length -1;
                             };

                           /****************************************************************
                             * Gestion Controle :  * Conton, * PES, * Finissage, * Peparation
                             ****************************************************************/ 
            // --------- Preparation
                          /** Mode Creation **/
                           $scope.addControlePreparationInit = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Preparation",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                              };
                             $scope.listControlePreparationInit.push($scope.inserted);
                           };

                           // remove ControleInit
                           $scope.removeControlePreparationInit = function(index) {
                             $scope.listControlePreparationInit.splice(index, 1);
                           };

                           /** Mode Modification **/
                           $scope.addControlePreparation = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Preparation",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                             };
                             $scope.listControlePreparation.push($scope.inserted);
                           };

                           // remove Controle
                           $scope.removeControlePreparation = function(index) {
                             $scope.listControlePreparation.splice(index, 1);
                           };

                           //saveControle
                           $scope.saveControlePreparation = function(data, id) {
                             //cancelAdd variable
                             $scope.deleteValue = "non";
                             $log.debug("$scope.deleteValue :" + $scope.deleteValue);
                             //$scope.user not updated yet
                             angular.extend(data, {id: id});
                           };

            // --------- PES
                          /** Mode Creation **/
                           $scope.addControlePESInit = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Teinture - PES",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                              };
                             $scope.listControlePESInit.push($scope.inserted);
                           };

                           // remove ControleInit
                           $scope.removeControlePESInit = function(index) {
                             $scope.listControlePESInit.splice(index, 1);
                           };

                           /** Mode Modification **/
                           $scope.addControlePES = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Teinture - PES",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                             };
                             $scope.listControlePES.push($scope.inserted);
                           };

                           // remove Controle
                           $scope.removeControlePES = function(index) {
                             $scope.listControlePES.splice(index, 1);
                           };

                           //saveControle
                           $scope.saveControlePES = function(data, id) {
                             //cancelAdd variable
                             $scope.deleteValue = "non";
                             $log.debug("$scope.deleteValue :" + $scope.deleteValue);
                             //$scope.user not updated yet
                             angular.extend(data, {id: id});
                           };

            // --------- Coton
                          /** Mode Creation **/
                           $scope.addControleCotonInit = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Teinture - Coton",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                              };
                             $scope.listControleCotonInit.push($scope.inserted);
                           };

                           // remove ControleInit
                           $scope.removeControleCotonInit = function(index) {
                             $scope.listControleCotonInit.splice(index, 1);
                           };

                           /** Mode Modification **/
                           $scope.addControleCoton = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Teinture - Coton",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                             };
                             $scope.listControleCoton.push($scope.inserted);
                           };

                           // remove Controle
                           $scope.removeControleCoton = function(index) {
                             $scope.listControleCoton.splice(index, 1);
                           };

                           //saveControle
                           $scope.saveControleCoton = function(data, id) {
                             //cancelAdd variable
                             $scope.deleteValue = "non";
                             $log.debug("$scope.deleteValue :" + $scope.deleteValue);
                             //$scope.user not updated yet
                             angular.extend(data, {id: id});
                           };

            // --------- Finissage
                          /** Mode Creation **/
                           $scope.addControleFinissageInit = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Finissage",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                              };
                             $scope.listControleFinissageInit.push($scope.inserted);
                           };

                           // remove ControleInit
                           $scope.removeControleFinissageInit = function(index) {
                             $scope.listControleFinissageInit.splice(index, 1);
                           };

                           /** Mode Modification **/
                           $scope.addControleFinissage = function() {

                             $scope.inserted = {
                                                "controleId": '',
                                                "methode": "",
                                                "temps":"",
                                                "type":"Finissage",
                                                "valeur": null,
                                                "valeurCorrige" : null,
                                                "valeurTheorique": null,
                                                "observations": "",
                                                "ficheSuiveuseId": null
                                             };
                             $scope.listControleFinissage.push($scope.inserted);
                           };

                           // remove Controle
                           $scope.removeControleFinissage = function(index) {
                             $scope.listControleFinissage.splice(index, 1);
                           };

                           //saveControle
                           $scope.saveControleFinissage = function(data, id) {
                             //cancelAdd variable
                             $scope.deleteValue = "non";
                             $log.debug("$scope.deleteValue :" + $scope.deleteValue);
                             //$scope.user not updated yet
                             angular.extend(data, {id: id});
                           };

                             /*************************************************
                              * Pdf
                              *************************************************/


//                            Générer rapport général
//                            $scope.download = function(id, recette ) {
//
//                              if(recette == 0)
//                                {recette = "non";}
//                              else if(recette == 1)
//                                {recette = "oui";}
//
//                              $log.debug("-- id "+id+" recette "+recette);
//                              var url = UrlAtelier+"/reportgl/ficheSuiveuse?id=" + id
//                                                  +"&avecMise="+recette
//                                                  +"&type=pdf";
//                              downloadService.download(url)
//                                  .then(
//                                      function(success) {
//                                        $log.debug('success : '+ success);
//                                       // $scope.annulerAjout();
//                                      },
//                                      function(error) {
//                                        $log.debug('error : '+ error);
//                                      });
//                            };
                            
                            
                            // generer Echantillon d'une ficheSuiveuse.
                            $scope.downloadEchantillon = function(id) {

                              $log.debug("-- id "+id);
                              var url = UrlAtelier+"/reportgl/ficheSuiveuseVide?id=" + id
                                                  +"&type=pdf";



                                                  var fileName = '  Liste Bon Sortie	'		;					
                                                  var a = document.createElement('a');
                                                  document.body.appendChild(a);
                                                  downloadService.download(url).then(function (result) {
                                                    var heasersFileName = result.headers(['content-disposition']).substring(22);
                                                    var fileName = heasersFileName.split('.');
                                                  var typeFile = result.headers(['content-type']);
                                                    var file = new Blob([result.data], {type: typeFile});
                                                     var fileURL = window.URL.createObjectURL(file);
                                                     a.href = fileURL;
                                                     a.download = fileName[0];
                                                    $window.open(fileURL)
                                                     a.click();
                                                  });

                              // downloadService.download(url)
                              //     .then(
                              //         function(success) {
                              //           $log.debug('success : '+ success);
                              //          // $scope.annulerAjout();
                              //         },
                              //         function(error) {
                              //           $log.debug('error : '+ error);
                              //         });
                            };
                            
                            /***************************************************
                             * Gestion des Grids de recherche
                             **************************************************/
                            $scope.colDefs = [];
                            $scope.$watch('myData',function() {
                            $scope.colDefs = [
                                   {
                                       field : 'id',
                                       displayName : 'Id',
                                       visible:false
                                   },         
                                   {
                                          field : 'referenceMise',
                                          displayName : 'N°.Mise',
                                          width:'5%'

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
                                          width:'18%'
                                   },
                                   {
                                          field : 'dateLancement',
                                          displayName : 'Date Entrée',
                                          cellFilter: 'date:"dd-MM-yyyy"',
                                          width:'8%'
                                   },
                                   {
                                          field : 'dateLivraison',
                                          displayName : 'Date Sortie',
                                          cellFilter: 'date:"dd-MM-yyyy"',
                                          width:'8%'
                                   },
                                   {
                                          field : 'poidSortie',
                                          displayName : 'Poids S.',
                                          width:'5%'
                                   },                                   
                                   {
                                       field : 'coutRecette',
                                       displayName : 'Recette',
                                       width:'7%'
                                   },
                                   {
                                       field : 'coutTraitement',
                                       displayName : 'Traitement',
                                       width:'7%'
                                   },
                                   {
                                       field : 'coutTotal',
                                       displayName : 'Total',
                                       width:'8%'
                                   },
                                   {
                                          field : '',
                                          width:'4%',
                                          cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
                                                 +'<button data-nodrag class="btn btn-default btn-xs" ng-click="genererPDFGeneral(row.entity,0)">G</button>'
                                                 + '<button data-nodrag class="btn btn-default btn-xs" ng-click="genererPDFDetaille(row.entity,1)">D</button></div>'
                                                                                                  
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

                           		$scope.getPagedDataAsync = function(pageSize, page,
    									searchText) {
    								setTimeout(
    										function() {
    											if (searchText) {
    												var ft = searchText
    														.toLowerCase();
    												
    												var data  = $scope.initMyData.filter(function(item) {
    							                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
    												});
    												
    							                        $scope
    													.setPagingData(
    															data,
    															page,
    															pageSize);
    													

    											} else {
    												
    												$scope
    												.setPagingData(
    														$scope.initMyData,
    														page,
    														pageSize);
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
                                   $scope.myData= [];
                                   $scope.gridOptions = {
                                          data : 'myData',
                                          columnDefs : 'colDefs',
                                          enablePaging : true,
                                          enableColumnResize: true,
                                          showFooter : true,
                                          enableHighlighting : true,
                                          totalServerItems : 'totalServerItems',
                                          pagingOptions : $scope.pagingOptions,
                                          selectedItems : $scope.selectedRows,
                                          filterOptions : $scope.filterOptions,
                                   };
                            /** Fin de gestion des Grids de la produit */
                                   
                                   
               /****************************** Nouvelles méthodes ajoutées *********************************/
                   				
                /*************** Generer PDF Géneral *******************/
                $scope.genererPDFGeneral= function(ligneFicheSuiveuse , recette){
                	console.log("---Generer rapport pdf generale -----") 
                	console.log("--- ligneFicheSuiveuse--"+ JSON.stringify(ligneFicheSuiveuse, null, "    "));
                    console.log("id: "+ ligneFicheSuiveuse.id);
                    console.log("coutRecette: "+ ligneFicheSuiveuse.coutRecette);
                    console.log("coutTraitement: "+ ligneFicheSuiveuse.coutTraitement);
                	
                	if(recette == 0)
                      {recette = "non";}
                    else if(recette == 1)
                      {recette = "oui";}
                                        
                    var url = UrlAtelier+"/reportgl/ficheSuiveuseGeneraleAvecCout?id=" + ligneFicheSuiveuse.id
                                        +"&avecMise="+recette
                                        +"&type=pdf"
                                        +"&coutRecette="+ligneFicheSuiveuse.coutRecette
                                        +"&coutTraitement="+ligneFicheSuiveuse.coutTraitement;



                                   			
                                        var a = document.createElement('a');
                                        document.body.appendChild(a);
                                        downloadService.download(url).then(function (result) {
                                          var heasersFileName = result.headers(['content-disposition']).substring(17);
                                          var fileName = heasersFileName.split('.');
                                        var typeFile = result.headers(['content-type']);
                                          var file = new Blob([result.data], {type: typeFile});
                                           var fileURL = window.URL.createObjectURL(file);
                                           a.href = fileURL;
                                           a.download = fileName[0];
                                          $window.open(fileURL)
                                           a.click();
                                        });

 
                    // downloadService.download(url)
                    //     .then(
                    //         function(success) {
                    //           $log.debug('success : '+ success);
                    //          // $scope.annulerAjout();
                    //         },
                    //         function(error) {
                    //           $log.debug('error : '+ error);
                    //         });
                 }
                
                
                
                 /*************** Generer PDF Détaillé *******************/
				
                $scope.genererPDFDetaille= function(ligneFicheSuiveuse, recette){

                	console.log("---Generer rapport pdf detaille -----")
                	console.log("--- ligneFicheSuiveuse--"+ JSON.stringify(ligneFicheSuiveuse, null, "    "));
                    console.log("id: "+ ligneFicheSuiveuse.id);
                    console.log("coutRecette: "+ ligneFicheSuiveuse.coutRecette);
                    console.log("coutTraitement: "+ ligneFicheSuiveuse.coutTraitement);
                	
                	if(recette == 0)
                      {recette = "non";}
                    else if(recette == 1)
                      {recette = "oui";}
                                        
                    var url = UrlAtelier+"/reportgl/ficheSuiveuseDetailleAvecCout?id=" + ligneFicheSuiveuse.id
                                        +"&avecMise="+recette
                                        +"&type=pdf"
                                        +"&coutRecette="+ligneFicheSuiveuse.coutRecette
                                        +"&coutTraitement="+ligneFicheSuiveuse.coutTraitement;


                                        var fileName = '  Liste Bon Sortie	'		;					
                                        var a = document.createElement('a');
                                        document.body.appendChild(a);
                                        downloadService.download(url).then(function (result) {
                                          var heasersFileName = result.headers(['content-disposition']).substring(22);
                                          var fileName = heasersFileName.split('.');
                                        var typeFile = result.headers(['content-type']);
                                          var file = new Blob([result.data], {type: typeFile});
                                           var fileURL = window.URL.createObjectURL(file);
                                           a.href = fileURL;
                                           a.download = fileName[0];
                                          $window.open(fileURL)
                                           a.click();
                                        });

 
                    // downloadService.download(url)
                    //     .then(
                    //         function(success) {
                    //           $log.debug('success : '+ success);
                    //          // $scope.annulerAjout();
                    //         },
                    //         function(error) {
                    //           $log.debug('error : '+ error);
                    //         });
                    }
                
                
               } // Close controller 
		
              ])

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
             