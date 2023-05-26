'use strict';

angular
  .module('themesApp', [
    'easypiechart',
    'toggle-switch',
    'ui.bootstrap',
    'ui.tree',
    'ui.bootstrap.tooltip',
    'ui.select2', 
    'ngGrid',
    'xeditable',
    'flow',
    'theme.services',
    'theme.directives',
    'theme.navigation-controller',
    'theme.notifications-controller',
    'theme.messages-controller',
    'theme.colorpicker-controller',
    'theme.layout-horizontal',
    'theme.layout-boxed',
    // 'theme.vector_maps',
    // 'theme.google_maps',
    'theme.calendars',
    'theme.gallery',
    'theme.tasks',
    'theme.ui-tables-basic',
    'theme.ui-panels',
    'theme.ui-ratings',
    'theme.ui-modals',
    'theme.ui-tiles',
    'theme.ui-alerts',
    'theme.ui-sliders',
    'theme.ui-progressbars',
    'theme.ui-paginations',
    'theme.ui-carousel',
    'theme.ui-tabs',
    'theme.ui-nestable',
    'theme.form-components',
    'theme.form-directives',
    'theme.form-validation',
    'theme.form-inline',
    'theme.form-image-crop',
    'theme.form-uploads',
    'theme.tables-ng-grid',
    'theme.tables-editable',
    'theme.charts-flot',
    'components.donwload',
    'components.initReferentiel',
    'components.initLogistique',
    'components.initCommercial',
    'config',

    'gpro.menuPartiesInteressees',
    'gpro.partieInteresseeFournisseur',
    'gpro.partieInteresseeClient',
    'gpro.back-partieInteressee',
    'gpro.menuElementBase',
    'gpro.articles',
    'gpro.elementDeBaseReporting',
    'gpro.stockNvMenu',
    'gpro.stockNvRetour',
    'gpro.stockNvSortie',
    'gpro.stockNvBonInventaire',
    'gpro.stockNvRetourDetail',
    'gpro.stockNv',
    'gpro.back-articles',
    'gpro.produit',
    'gpro.produitFinancier',
    'gpro.produitSerialisable',
    'gpro.clientprix',
    'gpro.fournisseurprix',
    'gpro.back-produits',
    'gpro.gestionnaireDocument',
    'gpro.menuFinance',
    'gpro.soldeClient',
    'gpro.soldeFournisseur',
    'gpro.echeancier',
    'gpro.echeancierFournisseur',
    'gpro.rapprochement',
    'gpro.rapprochementFournisseur',
    'gpro.FinanceReporting',
    'gpro.gcReportingFNclient',
    'gpro.gcReportingFNfournisseur',
    'gpro.menuFinanceFournisseur',
    'atelier.menuMagasin',
    'atelier.stockage',
    'atelier.rouleau',
    'atelier.bonSortie',
    /*'atelier.production',*/
    'gpro.back-commercial',
    'gpro.back-generateur',
    'gpro.back-generateur-mensuel',
    'atelier.menuGC',
    'atelier.menuGA',
    'gpro.gcVenteBC',
    'gpro.gcDetBonCommande',
    'gpro.gcVenteDevis',
    'gpro.gcReception',
    'gpro.gcReceptionAchat',
    'gpro.gcReceptionDetailAchat',
    'gpro.reglement',
    'gpro.elementReglement',
    'gpro.reglementfournisseur',
    'gpro.elementReglementAchat',
    'gpro.bondelivraison',
    'gpro.bondelivraisonGC',
    'gpro.detbondelivraisonGC',
    'gpro.marche',
    'gpro.factureVente',
    'gpao.detailsFactureVente',
    'gpro.factureAvoirVente',
    'gpro.factureRetourVente',
    'gpro.back-logistique',
    'gpro.gcAchatBC',
  'gpro.remiseVente','gpro.packageVente',
  'gpro.bondelivraisonBS',




  /*** reglement inverse ***/

'gpro.reglementInverse',
'gpro.elementReglementInverse',
 'gpro.echeancierInverse',

  


 /*** reglement achat inverse */

'gpro.reglementInversefournisseur',
'gpro.elementReglementInverseAchat',
 'gpro.echeancierInverseFournisseur',

    /** ************ modules Achat *********** */

    'gpro.factureAchat',
    'gpro.detFactureAchat',
    'gpro.factureAvoirAchat',
    'gpro.factureRetourAchat',

    /** ************ modules themes *********** */

    'theme.charts-canvas',
    'theme.charts-svg',
    'theme.charts-inline',
    'theme.pages-controllers',
    'theme.dashboard',
    'theme.templates',
    'theme.template-overrides',
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
  
    'pascalprecht.translate',
    'language.translate-controller',

    /** ************ Nouveaux controllers *********** */
    'atelier.couts',
    /*'atelier.facon',*/
    'gpro.reportingLogistique',
    /*'atelier.inventaire',*/

    /** **********gestion de stock******************** */
    'gpro.etat',
    'gpro.historique',
    'gpro.back',
    // 'gpro.stockMouvementOF',
    'gpro.backRaisonMouvement',
    'gpro.backMagasin',
    'gpro.backEmplacement',
    //'gpro.PopupStockEntree',
    //'gpro.PopupStockSortie',
    'gpro.stock',
    'gpro.stockEChart',

    /** ***************Facon******************* */

    'gpro.menuFacon',
    'gpro.bondelivraisonFacon',
    'gpro.factureVenteFacon',
    'atelier.bonSortieFacon',
    'atelier.lancementFacon',

    /** ***************FLOTTE******************* */

    'gpro.chauffeur',
    'gpro.engin',
    'gpro.remorque',

    /** ************ Caisse ****************** */
    'gpro.glCaisse',
    'gpro.mvtCaisse',
    /** ************ Nouveaux Services ****************** */
    'atelier.CoutsServices',
    'atelier.faconServices',
    'commun.PIServices',
    'commun.ProduitServices',
    'atelier.traitementFaconServices',
    'atelier.methodeTeintureServices',
    'atelier.bonLivraisonServices',
    'atelier.factureServices',
    'atelier.factureAchatServices',
    'atelier.traitementFicheServices',
    'gpro.menuAuthorization','gpro.manageUsers','gpro.manageOperations','gpro.manageBoutique','gpro.manageSociete','gpro.manageMagasin',
    'amchart',
    
    /************* analyse *******************/
	'gpro.analyseVente',
	'gpro.analyseAchat',
	'gpro.analyseStock',
	'gpro.dashbord',
	
	/************* Transfert *******************/
	'gpro.transfertMenu',
	'gpro.transfertBonTransfertReception',
	'gpro.transfertBonTransfertSortie',
	
	
    /************* Stock MP *******************/
	
  'gpro.stockSortieMP',
  'gpro.stockEntreeMP',
  'gpro.etatMP',
  'gpro.historiqueMP',
  'gpro.stockMPmenu',
  'gpro.PopupStockEntreeMP',
  'gpro.PopupStockSortieMP',
   /************* logistique nv *******************/

   'atelier.logistique',
   'atelier.suivieOF',
   'gpro.film',
   'gpro.forme',
   'gpro.service',
   /***************produitComposition */
   'gpao.produitComposition'

	
	
  ])

  .controller('MainController', [
    '$scope',
    '$rootScope',
    '$global',
    '$timeout',
    'progressLoader',
    '$location',
    '$http',
    'UrlCommun',
    'UrlAtelier',
    'AuthenticationService',
    'jwtHelper',
    'Auth',

    function (
      $scope,
      $rootScope,
      $global,
      $timeout,
      progressLoader,
      $location,
      $http,
      UrlCommun,
      UrlAtelier,
      AuthenticationService,
      jwtHelper,
      Auth,
      permission
      
    ) {
      $scope.style_fixedHeader = $global.get('fixedHeader');
      $scope.style_headerBarHidden = $global.get('headerBarHidden');
      $scope.style_layoutBoxed = $global.get('layoutBoxed');
      $scope.style_fullscreen = $global.get('fullscreen');
      $scope.style_leftbarCollapsed = $global.get('leftbarCollapsed');
      $scope.style_leftbarShown = $global.get('leftbarShown');
      $scope.style_rightbarCollapsed = $global.get('rightbarCollapsed');
      $scope.style_isSmallScreen = false;
      $scope.style_showSearchCollapsed = $global.get('showSearchCollapsed');
      $scope.style_layoutHorizontal = $global.get('layoutHorizontal');
      $scope.displayBORF = 'f';
      $scope.displayMode = 'front';
      $scope.selectedMenu = 'Dashboard';
      $scope.selectedSousMenu = '';
      $rootScope.permissions = [];

      $scope.emailUser = localStorage.getItem('email');
      $scope.nameUser = localStorage.getItem('name'); 
      $scope.jobUser = localStorage.getItem('job'); 


   $scope.changeCurrentMode= function (passBlackMode) {
	
	
	 if(passBlackMode != "159753")
       return ;


      $scope.passBlackMode = "";
	
	                             $http
									.get(
												UrlCommun
														+ "/baseInfo/updateCurrentMode"
												)
										.success(
												function(blackMode) {
													
													  if(blackMode == "true"){
														alert("B.M est active") ;
													  }else
											         {
												        alert("B.M est désactive") ; 
										          	  }
													});
	
	
	}

      $scope.getOperation = function () {
        if (
          localStorage.getItem('currentUser') != null &&
          localStorage.getItem('currentUser') != undefined
        ) {
          var expToken = jwtHelper.decodeToken(
            localStorage.getItem('currentUser')
          );

          $rootScope.permissions = expToken.operations;
        } else $rootScope.permissions = [];
      };

      $scope.getOperation();

      $scope.upSelectedMenu = function (titleMenu, titleSousMenu) {
        $scope.selectedMenu = titleMenu;
        $scope.selectedSousMenu = titleSousMenu;
        //   $location.path('#/partiesInteresseesMenu/fournisseur');
        //  $route.current.templateUrl = '#/partiesInteresseesMenu/fournisseur'
        // window.location.href = '#/partiesInteresseesMenu/fournisseur';
      };

      $scope.goBack = function () {
        $scope.displayMode = 'back';
        $scope.displayBORF = 'b';
        window.location.href = '#/backPartiesInteressees';
        $('#menuFront').removeClass('navFront').addClass('navBack');
        $('#menuFront>ul').removeClass('navGPRO').addClass('navGPROBack');
        $('#menuFront>ul>li').removeClass('active');
        $('#menuFront>ul>li').eq(6).addClass('active');
      };
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

        $scope.getClientActif ();
          
			 
      $scope.reloadAllCache = function () {
    
    	  $http.get(UrlCommun + '/gestionnaireCache/reloadReferentiel');
    	  $http.get(UrlAtelier + '/gestionnaireCache/reloadGs');
    	  $http.get( UrlAtelier + '/gestionnaireLogistiqueCache/reloadLogistiqueCache' );
    	           
    	          
      }

      $scope.reloadGs = function () {
        $http.get(UrlAtelier + '/gestionnaireCache/reloadGs');
      };

      if($rootScope.isLoggedIn == true){
        $scope.getClientActif();
        $scope.reloadGs();
      }


     

      $scope.goFront = function () {
        $http.get(UrlCommun + '/gestionnaireCache/reloadReferentiel');

        $http.get(
          UrlAtelier + '/gestionnaireLogistiqueCache/reloadLogistiqueCache'
        );

        $http.get(UrlAtelier + '/gestionnaireCache/reloadGs');

        $scope.displayMode = 'front';
        $scope.displayBORF = 'f';
        window.location.href = '#';
        $('#menuFront').removeClass('navBack').addClass('navFront');
        $('#menuFront>ul').removeClass('navGPROBack').addClass('navGPRO');
        $('#menuFront>ul>li').removeClass('active');
        $('#menuFront>ul>li').eq(1).addClass('active');
      };
      $scope.hideSearchBar = function () {
        $global.set('showSearchCollapsed', false);
      };

      $scope.hideHeaderBar = function () {
        $global.set('headerBarHidden', true);
      };

      $scope.showHeaderBar = function ($event) {
        $event.stopPropagation();
        $global.set('headerBarHidden', false);
      };

      $scope.toggleLeftBar = function () {
        if ($scope.style_isSmallScreen) {
          return $global.set('leftbarShown', !$scope.style_leftbarShown);
        }
        $global.set('leftbarCollapsed', !$scope.style_leftbarCollapsed);
      };

      $scope.toggleRightBar = function () {
        $global.set('rightbarCollapsed', !$scope.style_rightbarCollapsed);
      };

      $scope.$on('globalStyles:changed', function (event, newVal) {
        $scope['style_' + newVal.key] = newVal.value;
      });
      $scope.$on('globalStyles:maxWidth767', function (event, newVal) {
        $timeout(function () {
          $scope.style_isSmallScreen = newVal;
          if (!newVal) {
            $global.set('leftbarShown', false);
          } else {
            $global.set('leftbarCollapsed', false);
          }
        });
      });
      $rootScope.isLoggedIn = false;
      $scope.loginError = false;
      $scope.userlogin = {};
      $scope.currentUser = AuthenticationService.getFromStorageCurrentUser();

      if ($scope.currentUser != null) {
        $rootScope.isLoggedIn = true;
        
    
        //window.location.href = '/';
      } else {
        $rootScope.isLoggedIn = false;
        window.location.href = '#/login';
      }

      $scope.logOut = function () {
      //   $scope.userlogin.userName = '';
      //  $scope.userlogin.password = '';
      window.location.href = '#/login';
       localStorage.removeItem('currentUser');
       localStorage.removeItem('email');
       localStorage.removeItem('name');
       localStorage.removeItem('job');
   
      //  AuthenticationService.logout();

        $scope.loginError = false;
        $global.set('fullscreen', true);
        $rootScope.isLoggedIn = false;
      };


      $scope.loginPostAction = function (userlogin) {
        AuthenticationService.login(
          userlogin.userName,
          userlogin.password,
          function (resultat) {
            if (resultat == true) {
              $scope.currentUser = AuthenticationService.getFromStorageCurrentUser();
              $scope.emailUser = localStorage.getItem('email');
              $scope.nameUser = localStorage.getItem('name'); 
              $scope.jobUser = localStorage.getItem('job'); 
     
              $global.set('fullscreen', false);
              $rootScope.isLoggedIn = true;
              window.location.href = '#/';
              var expToken = jwtHelper.decodeToken(
                localStorage.getItem('currentUser')
              );
              $rootScope.permissions = expToken.operations;
              // var tokenPayload = jwtHelper.decodeToken(expToken);

              $scope.getClientActif();

              
            } else {
              $scope.loginError = true;
              $scope.userlogin.userName = '';
              $scope.userlogin.password = '';
            }
          }
        );
      };

      $scope.logIn = function (userlogin) {
        /*  var expToken =
          'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1lciIsInVzZXJJZCI6IjUiLCJyb2xlIjoiQ09OVFJPTEVVUiJ9.D6g8IMHsK2EApcx_VRim-krMBrppiOunmNHHv6swg9tQxnXp0xPVkl3Q48LuhPYHJ_eyA93WgxmxJJTIgvfwgg';*/

        // $rootScope.permissions.push(tokenPayload.role);
        // var permissionsBack = $scope.permissions;
        // localStorage.setItem('token', expToken);

      
        $scope.loginPostAction($scope.userlogin);
      };

      $rootScope.hasOperation = function (operation) {
        var currentUser;
        currentUser = AuthenticationService.getFromStorageCurrentUser();
        if (currentUser !== null && currentUser.roleNames !== null) {
          if (currentUser.roleNames.indexOf(operation) > -1) {
            return true;
          } else {
            return false;
          }
        } else {
          return false;
        }
      };
      $scope.rightbarAccordionsShowOne = false;
      $scope.rightbarAccordions = [
        {
          open: true,
        },
        {
          open: true,
        },
        {
          open: true,
        },
        {
          open: true,
        },
        {
          open: true,
        },
        {
          open: true,
        },
        {
          open: true,
        },
      ];

      $scope.$on('$routeChangeStart', function (e) {
        progressLoader.start();
        progressLoader.set(50);
      });
      $scope.$on('$routeChangeSuccess', function (e) {
        progressLoader.end();
      });
    },
  ])

  .config([
    '$logProvider',
    function ($logProvider) {
      $logProvider.debugEnabled(true);
    },
  ])

  .config([
    '$httpProvider',
    function ($httpProvider) {
      // initialize get if not there
      if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
      }

      // Answer edited to include suggestions from
      // comments
      // because previous version of code introduced
      // browser-related errors

      // disable IE ajax request caching
      $httpProvider.defaults.headers.get['If-Modified-Since'] =
        'Mon, 26 Jul 1997 05:00:00 GMT';
      // extra
      $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
      $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    },
  ])
  .config([
    '$provide',
    '$routeProvider',
    '$translateProvider',
    function ($provide, $routeProvider, $translateProvider) {
      $routeProvider
        .when('/', {
          templateUrl: 'views/front/dashboard.html',
        })

        // .when('/login', {
        // 	templateUrl: 'views/login.html',
        // 	controller: 'SignupPageController'
        // })

        .when('/calendar', {
          templateUrl: 'views/calendar.html',
          resolve: {
            lazyLoad: [
              'lazyLoad',
              function (lazyLoad) {
                return lazyLoad.load([
                  'assets/plugins/fullcalendar/fullcalendar.js',
                ]);
              },
            ],
          },
        })

        .when('/form-ckeditor', {
          templateUrl: 'views/form-ckeditor.html',
          resolve: {
            lazyLoad: [
              'lazyLoad',
              function (lazyLoad) {
                return lazyLoad.load([
                  'assets/plugins/form-ckeditor/ckeditor.js',
                  'assets/plugins/form-ckeditor/lang/fr.js',
                ]);
              },
            ],
          },
        })
        .when('/form-imagecrop', {
          templateUrl: 'views/form-imagecrop.html',
          resolve: {
            lazyLoad: [
              'lazyLoad',
              function (lazyLoad) {
                return lazyLoad.load([
                  'assets/plugins/jcrop/js/jquery.Jcrop.js',
                ]);
              },
            ],
          },
        })
        .when('/form-wizard', {
          templateUrl: 'views/form-wizard.html',
          resolve: {
            lazyLoad: [
              'lazyLoad',
              function (lazyLoad) {
                return lazyLoad.load([
                  'bower_components/jquery-validation/dist/jquery.validate.js',
                  'bower_components/stepy/lib/jquery.stepy.js',
                ]);
              },
            ],
          },
        })
        .when('/form-masks', {
          templateUrl: 'views/form-masks.html',
          resolve: {
            lazyLoad: [
              'lazyLoad',
              function (lazyLoad) {
                return lazyLoad.load([
                  'bower_components/jquery.inputmask/dist/jquery.inputmask.bundle.js',
                ]);
              },
            ],
          },
        })
        /*
         * .when('/maps-vector', { templateUrl:
         * 'views/maps-vector.html', resolve: {
         * lazyLoad: ['lazyLoad', function
         * (lazyLoad) { return lazyLoad.load([
         * 'bower_components/jqvmap/jqvmap/maps/jquery.vmap.europe.js',
         * 'bower_components/jqvmap/jqvmap/maps/jquery.vmap.usa.js'
         * ]); }] } })
         */
        .when('/charts-canvas', {
          templateUrl: 'views/charts-canvas.html',
          resolve: {
            lazyLoad: [
              'lazyLoad',
              function (lazyLoad) {
                return lazyLoad.load([
                  'bower_components/Chart.js/Chart.min.js',
                ]);
              },
            ],
          },
        })
        .when('/charts-svg', {
          templateUrl: 'views/charts-svg.html',
          resolve: {
            lazyLoad: [
              'lazyLoad',
              function (lazyLoad) {
                return lazyLoad.load([
                  'bower_components/raphael/raphael.js',
                  'bower_components/morris.js/morris.js',
                ]);
              },
            ],
          },
        })

        /**
         * ********************** Parties
         * Intéressées ***************************
         */

        // .when(
        // 	'/partiesInteressees',
        // 	{
        // 		// templateUrl: function (param)
        // 		// { return
        // 		// 'views/partiesInteressees/'+param.templateFile+'.html'
        // 		// }
        // 		templateUrl: 'views/front/partiesInteressees/partiesInteressees.html'
        // 	})

        /**
         * ********************** Elements de base
         * ***************************
         */

        .when('/elementsDeBase', {
          // templateUrl: function (param)
          // { return
          // 'views/front/elementsDeBase/'+param.templateFile+'.html'
          // }
          templateUrl: 'views/front/elementsDeBase/elementsDeBase.html',
        })
        .when('/elementsDeBase/:type', {
          templateUrl: 'views/front/elementsDeBase/elementsDeBase.html',
        })

        .when('/front/elementsDeBase/articles/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/elementsDeBase/articles/' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/elementsDeBase/film/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/elementsDeBase/film/' +
              param.templateFile +
              '.html'
            );
          },
        })
        .when('/front/elementsDeBase/forme/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/elementsDeBase/forme/' +
              param.templateFile +
              '.html'
            );
          },
        })
        .when('/front/elementsDeBase/service/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/elementsDeBase/service/' +
              param.templateFile +
              '.html'
            );
          },
        })
        // Articles est redirigée vers produits
        .when('/articles', {
          templateUrl: function (param) {
            return 'views/front/elementsDeBase/' + param.templateFile + '.html';
          },
          // templateUrl:
          // 'views/front/elementsDeBase/produits/produits.html'
        })

        .when('/front/elementsDeBase/produits/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/elementsDeBase/produits' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/elementsDeBase/flotte/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/elementsDeBase/flotte' + param.templateFile + '.html'
            );
          },
        })





















        /**
         * ********************** Logistique Ancien******************
         * ***************************
         */

       /*  .when('/logistique', {
          // templateUrl: function (param)
          // { return
          // 'views/front/logistique/'+param.templateFile+'.html'
          // }
          templateUrl: 'views/front/logistique/logistique.html',
        })

        .when('/front/logistique/production/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/production/' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/logistique/enroulage:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/enroulage' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/stockage/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/stockage/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/expedition:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/expedition' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/livraison:templateFile', {
          templateUrl: function (param) {
            return 'views/front/logistique/livraison.html';
          },
        })

        .when('/front/logistique/inventaire/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/inventaire/' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/logistique/facon:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/facon' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/reporting:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/reporting' + param.templateFile + '.html'
            );
          },
        })






 */











        // gestion de stock
        .when('/stock', {
          templateUrl: 'views/front/gestionStock/mvtStock/stock.html',
        })

        .when('/front/stock/stockCharts:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionStock/stockCharts' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/stock/stockChartsEntre:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionStock/stockChartsEntre' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/stock/stockChartsSortie:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionStock/stockChartsSortie' +
              param.templateFile +
              '.html'
            );
          },
        })













        /*
         * .when('/front/stock/stockEntree:templateFile', {
         * templateUrl: function (param) { return
         * 'views/front/gestionStock/stockEntree'+param.templateFile+'.html' } })
         */

        .when('/stockSortie', {
          templateUrl: function (param) {
            return 'views/front/gestionStock/mvtStock/stockSortie.html';
          },
        })

        .when('/historique', {
          templateUrl: function (param) {
            return 'views/front/gestionStock/historique/historique.html';
          },
        })

        .when('/etat', {
          templateUrl: function (param) {
            return 'views/front/gestionStock/etat/etat.html';
          },
        })

        .when('/ChartGraphique', {
          templateUrl: function (param) {
            return 'views/front/gestionStock/tableauDeBord/ChartGraphique.html';
          },
        })

        /**
         * ********************** Gestion
         * commerciale ***************************
         */

        .when('/gestionCommerciale', {
          // templateUrl: function (param)
          // { return
          // 'views/front/gestionCommerciale/'+param.templateFile+'.html'
          // }
          templateUrl: 'views/front/gestionCommerciale/gestionCommerciale.html',
        })

        .when('/gestionCommerciale/:type', {
          templateUrl: 'views/front/gestionCommerciale/gestionCommerciale.html',
        })

        .when('/front/gestionCommerciale/bonCommande/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/bonCommande/' +
              param.templateFile +
              '.html'
            );
          },
        })
        .when('/front/gestionCommerciale/devis/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/devis/' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/gestionCommerciale/livraisonBS:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/LivraisonBS' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/gestionCommerciale/detlivraison/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/detlivraison/' +
              param.templateFile +
              '.html'
            );
          },
        })


        .when('/front/gestionCommerciale/reception/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/reception/' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/gestionCommerciale/marche:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/marche' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/gestionCommerciale/facture/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/facture/' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/gestionCommerciale/factureAvoir:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/factureAvoir' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/gestionCommerciale/reporting:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionCommerciale/reporting' +
              param.templateFile +
              '.html'
            );
          },
        })

        /**
         * ********************** Gestion Achat
         * ***************************
         */

        .when('/gestionAchat', {
          // templateUrl: function (param)
          // { return
          // 'views/front/gestionCommerciale/'+param.templateFile+'.html'
          // }
          templateUrl: 'views/front/gestionAchat/gestionAchat.html',
        })

        .when('/gestionAchat/:type', {
          templateUrl: 'views/front/gestionAchat/gestionAchat.html',
        })
        .when('/front/gestionAchat/reception/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/gestionAchat/reception/' +
              param.templateFile +
              '.html'
            );
          },
        })
        /**
         * ************************* GESTION DE
         * CAISSSE
         * ****************************************
         */
        // .when('/caisse', {
        // templateUrl:'views/front/caisse/caisse.html'
        // })
        // .when('/mvtCaisse', {
        // templateUrl:'views/front/caisse/mouvementCaisse.html'
        // })
        /** ************* Stock Nv ****************** */
        .when('/stockNvMenu', {
          templateUrl: 'views/front/stockNv/stockNvMenu.html',
        })

        .when('/stockNvMenu/:type', {
          templateUrl: 'views/front/stockNv/stockNvMenu.html',
        })
        
        
          /** ************* Transfert ****************** */
        .when('/transfert', {
          templateUrl: 'views/front/transfert/transfertMenu.html',
        })

        .when('/transfert/:type', {
          templateUrl: 'views/front/transfert/transfertMenu.html',
        })


        /********** Stock Mp ******************************** */

                
        /**
         * ********************** StockMp
         * ***************************
         */

        .when('/stockMP', {
   
          templateUrl: 'views/front/stockMP/stockMP.html',
          
        })
        .when('/stockMP/:type', {
         
          templateUrl: 'views/front/stockMP/stockMP.html',
        })
        .when('/front/stockMP/etat/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/stockMP/etat/' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/stockMP/sortie:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/stockMP/sortie/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/stockMP/historique/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/stockMP/historique/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/stockMP/tableaudebord:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/stockMP/tableaudebord/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/stockMP/entree:templateFile', {
          templateUrl: function (param) {
            return 'views/front/stockMP/entree/' +
            param.templateFile +
            '.html';
          },
        })






         /**
         * ********************** Logistique nv
         * ***************************
         */

        .when('/logistique', {
       
          templateUrl: 'views/front/logistique/logistique.html',
        })
        .when('/logistique/:type', {
         
          templateUrl: 'views/front/logistique/logistique.html',
        })
        .when('/front/logistique/production/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/production/' +
              param.templateFile + '.html'
            );
          },
        })


        .when('/front/logistique/suivieof:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/suivieof/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/stockage/:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/stockage/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/expedition:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/expedition/' + param.templateFile + '.html'
            );
          },
        })

   

        .when('/front/logistique/reporting:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/reporting/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/of:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/of/' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/logistique/livraison:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/logistique/livraison/' + param.templateFile + '.html'
            );
          },
        })




        /**
         * ********************** Finance
         * ***************************
         */

        .when('/finance', {
          templateUrl: 'views/front/finance/finance.html',
        })

        .when('/finance/:type', {
          // templateUrl: function (param)
          // { return
          // 'views/partiesInteressees/'+param.templateFile+'.html'
          // }
          templateUrl: 'views/front/finance/finance.html',
        })

        .when('/front/finance/couts:templateFile', {
          templateUrl: function (param) {
            return 'views/front/finance/couts' + param.templateFile + '.html';
          },
        })

        .when('/front/finance/reglement:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/finance/reglement' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/finance/soldeClient:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/finance/soldeClient' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/finance/paiement:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/finance/paiement' + param.templateFile + '.html'
            );
          },
        })

      .when('/front/finance/rapprochement:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/finance/rapprochement' + param.templateFile + '.html'
            );
          },
        })

        .when('/front/finance/reporting:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/finance/reporting' + param.templateFile + '.html'
            );
          },
        })
        .when('/front/finance/caisse:templateFile', {
          templateUrl: function (param) {
            return 'views/front/finance/caisse' + param.templateFile + '.html';
          },
        })
        .when('/front/finance/mouvementCaisse:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/finance/mouvementCaisse' +
              param.templateFile +
              '.html'
            );
          },
        })

        /**
         * ********************** Finance
         * Fournisseur ***************************
         */

        .when('/financeFournisseur', {
          templateUrl: 'views/front/financeFournisseur/financeFournisseur.html',
        })

        .when('/financeFournisseur/:type', {
          templateUrl: 'views/front/financeFournisseur/financeFournisseur.html',
        })

        .when('/front/financeFournisseur/reglement:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/financeFournisseur/reglement' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/financeFournisseur/soldeFournisseur:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/financeFournisseur/soldeFournisseur' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/financeFournisseur/paiement:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/financeFournisseur/paiement' +
              param.templateFile +
              '.html'
            );
          },
        })

   .when('/front/financeFournisseur/rapprochement:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/financeFournisseur/rapprochement' +
              param.templateFile +
              '.html'
            );
          },
        })

        .when('/front/financeFournisseur/reporting:templateFile', {
          templateUrl: function (param) {
            return (
              'views/front/financeFournisseur/reporting' +
              param.templateFile +
              '.html'
            );
          },
        })
        /**
         * ********************** Facon
         * ***************************
         */

        .when('/facon', {
          templateUrl: 'views/front/facon/menuFacon.html',
        })

        /**
         * ************************** Back
         * ************************************
         */

        /**
         * **************** Parties intéressées
         * ***********************
         */

        .when('/partiesInteresseesMenu/:pi', {
          templateUrl:
            'views/front/partiesInteressees/partiesInteresseesMenu.html',
          // controller: 'MenuPartiesInteresseesController',
        })

        .when(
          '/backAuthorization/:typeMenuBack',
          {
            templateUrl : 'views/back/utilisateurs/menuAuthorization.html'
          })

        // .when('/partiesInteresseesMenu', {
        //   templateUrl:
        //     'views/front/partiesInteressees/partiesInteresseesMenu.html',
        //   //	controller: 'partieInteresseeClient'
        // })

        /*		.when('/partiesInteresseesMenu/client', {
          templateUrl:
            'views/front/partiesInteressees/partiesInteresseesClient.html',
          //	controller: 'partieInteresseeClient'
        })*/

        // .when(
        // 	'/partiesInteresseesMenu',
        // 	{
        // 		templateUrl: 'views/front/partiesInteressees/partiesInteresseesMenu.html'
        // 	})

        .when('/backPartiesInteressees', {
          templateUrl:
            'views/back/partiesInteressees/partiesInteressees/backPartiesInteressees.html',
        })

        /**
         * **************** Elements de base
         * ***********************
         */

        .when('/backArticles', {
          templateUrl: 'views/back/elementsDeBase/articles/backArticles.html',
        })

        .when('/backArticles/:type', {
          templateUrl: 'views/back/elementsDeBase/articles/backArticles.html',
        })

        .when('/back/elementsDeBase/produit:templateFile', {
          templateUrl: function (param) {
            return (
              'views/back/elementsDeBase/produits' +
              param.templateFile +
              '.html'
            );
          },
        })

        /**
         * **************** Logistique
         * ***********************
         */

        .when('/backStock', {
          templateUrl: 'views/back/stock/backStock.html',
        })

        /**
         * **************** Commercial
         * ***********************
         */

        .when('/backCommercial', {
          templateUrl: 'views/back/commercials/commercial/backCommercial.html',
        })

        .when('/backGenerateur', {
          templateUrl: 'views/back/commercials/generateur/backGenerateur.html',
        })

       .when('/backGenerateurMensuel', {
          templateUrl: 'views/back/commercials/generateurMensuel/backGenerateurMensuel.html',
        })
        .when('/:templateFile', {
          templateUrl: function (param) {
            return 'views/' + param.templateFile + '.html';
          },
        })
        .when('/backCommercial', {
          templateUrl: 'views/back/commercials/commercial/backCommercial.html',
        })
        
         .when('/analyse/vente', {
          templateUrl: 'views/front/analyse/analyseVente.html',
        })
        
         .when('/analyse/achat', {
          templateUrl: 'views/front/analyse/analyseAchat.html',
        })

        .when('/analyse/stock', {
          templateUrl: 'views/front/analyse/analyseStock.html',
        })
	

        .otherwise({
          redirectTo: '/',
        });

      $translateProvider
        .useStaticFilesLoader({
          prefix: 'translations/',
          suffix: '.json',
        })
        .preferredLanguage('fr');
    },
  ])

  .config([
    '$httpProvider',
    function ($httpProvider) {
      $httpProvider.interceptors.push(function () {
        return {
          request: function (req) {
            // Set the `Authorization` header for every outgoing HTTP request
            req.headers.Authorization = `Bearer  ${JSON.parse(
              localStorage.getItem('currentUser')
            )}`;
            return req;
          },
        };
      });
    },
  ])

  // .constant('COMBOS', {
  //   MERCHANT: {
  //     EDIT: ['merchant-edit'],
  //   },
  // })

  .factory('Auth', function ($rootScope) {
    var auth = {};

    /**
     *  Saves the current user in the root scope
     *  Call this in the app run() method
     */
    auth.init = function () {
      if (auth.isLoggedIn()) {
        $rootScope.user = localStorage.getItem('currentUser');
      }
    };

    // auth.logout = function () {
    //   delete $sessionStorage.user;
    //   delete $rootScope.user;
    // };

    auth.checkPermissionForView = function (view) {
      if (!view.requiresAuthentication) {
        return true;
      }

      return userHasPermissionForView(view);
    };

    var userHasPermissionForView = function (view) {
      if (!auth.isLoggedIn()) {
        return false;
      }

      if (!view.permissions || !view.permissions.length) {
        return true;
      }

      return auth.userHasPermission(view.permissions);
    };

    auth.userHasPermission = function (permissions) {
      if (!auth.isLoggedIn()) {
        return false;
      }

      var found = false;
      angular.forEach(permissions, function (permission, index) {
        if ($rootScope.permissions.indexOf(permission) >= 0) {
          found = true;
          return;
        }
      });

      return found;
    };

    auth.currentUser = function () {
      var user = `${localStorage.getItem('currentUser')}`;
      return user;
    };

    auth.isLoggedIn = function () {
      var user = `${localStorage.getItem('currentUser')}`;
      return user != null;
    };

    return auth;
  })

  .directive('permission', [
    'Auth',
    function (Auth) {
      return {
        restrict: 'A',
        scope: {
          permission: '=',
        },

        link: function (scope, elem, attrs) {
          scope.$watch(Auth.isLoggedIn, function () {
            if (Auth.userHasPermission(scope.permission)) {
              elem.show();
            } else {
              elem.hide();
            }
          });
        },
      };
    },
  ])

  .run([
    '$rootScope',
    '$location',
    'Auth',
    function ($rootScope, $location, Auth) {
      Auth.init();

      Auth.isLoggedIn = function () {
        return localStorage.getItem('currentUser');
      };

      $rootScope.$on('$routeChangeStart', function (event, next) {
        if (!Auth.checkPermissionForView(next)) {
          event.preventDefault();
          $location.path('/login');
        }
      });
    },
  ]);
