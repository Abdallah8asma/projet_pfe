'use strict';

angular.module('themesApp', [
    'easypiechart',
    'toggle-switch',
    'ui.bootstrap',
    'ui.tree',
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
//    'theme.vector_maps',
//    'theme.google_maps',
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
    'theme.personne',
    'theme.client',
    'config',
    'components.donwload',
    'gpro.reception',
    'gpro.rouleau',
    'gpro.mise',
    'gpro.partieInteressee',
    'gpro.back-partieInteressee',
    'gpro.produit',
    'gpro.back-produits',
    'gpro.gestionnaireDocument',
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
    'ngAnimate',    
    'pascalprecht.translate',
    'language.translate-controller',
    /* auth */
    'gpromoteApp.cookiesServices',
	 'gpro.UserServices',
	 

	 'gpromoteApp.authController',
	 'gpromoteApp.authServices',
	 'LocalStorageModule'
  ])
  		
  
  
  .config(['$provide', '$routeProvider', '$translateProvider', function ($provide, $routeProvider, $translateProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/index.html',
      })
      .when('/login', {
        templateUrl: 'views/login.html' 
      })
      .when('/calendar', {
        templateUrl: 'views/calendar.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'assets/plugins/fullcalendar/fullcalendar.js'
            ]);
          }]
        }
      })
      .when('/form-ckeditor', {
        templateUrl: 'views/form-ckeditor.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'assets/plugins/form-ckeditor/ckeditor.js',
              'assets/plugins/form-ckeditor/lang/fr.js'
            ]);
          }]
        }
      })
      .when('/form-imagecrop', {
        templateUrl: 'views/form-imagecrop.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'assets/plugins/jcrop/js/jquery.Jcrop.js'
            ]);
          }]
        }
      })
      .when('/form-wizard', {
        templateUrl: 'views/form-wizard.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/jquery-validation/dist/jquery.validate.js',
              'bower_components/stepy/lib/jquery.stepy.js'
            ]);
          }]
        }
      })
      .when('/form-masks', {
        templateUrl: 'views/form-masks.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/jquery.inputmask/dist/jquery.inputmask.bundle.js'
            ]);
          }]
        }
      })
      /*
      .when('/maps-vector', {
        templateUrl: 'views/maps-vector.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/jqvmap/jqvmap/maps/jquery.vmap.europe.js',
              'bower_components/jqvmap/jqvmap/maps/jquery.vmap.usa.js'
            ]);
          }]
        }
      })
      */
      .when('/charts-canvas', {
        templateUrl: 'views/charts-canvas.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/Chart.js/Chart.min.js'
            ]);
          }]
        }
      })
      .when('/charts-svg', {
        templateUrl: 'views/charts-svg.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/raphael/raphael.js',
              'bower_components/morris.js/morris.js'
            ]);
          }]
        }
      })
      .when('/:templateFile', {
        templateUrl: function (param) { return 'views/'+param.templateFile+'.html' }
      })
      .otherwise({
        redirectTo: '/'
      });
    
    $translateProvider
	  .useStaticFilesLoader({
	    prefix: 'translations/',
	    suffix: '.json'
	})
	.preferredLanguage('fr');
  }])
  
    .run( function($rootScope, $location) {

    // register listener to watch route changes
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
      if ( $rootScope.isAuthentified == null ) {
        // no logged user, we should be going to #login
        if ( next.templateUrl == "views/login.html" ) {
          // already going to #login, no redirect needed
        } else {
          // not going to #login, we should redirect now
          $location.path( "/login" );
        }
      }         
    });
    
 })
  
  
  .controller('MainController', ['$scope', '$rootScope','$global', '$timeout', 'progressLoader', '$location','$http','baseUrl','authServices',
                        		 'LoginFactory',    'UrlCommun',
                             'UrlAtelier','AuthenticationService',
                             'jwtHelper', function ($scope,$rootScope, $global, $timeout, progressLoader, $location,$http,baseUrl, authServices, LoginFactory,    UrlCommun,
                              UrlAtelier,
                              AuthenticationService,
                              jwtHelper) {
   
	  
	  $rootScope.isAuthentified=null;
	  
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
    $scope.displayMode ='front';


    $rootScope.permissions = [];

    $scope.emailUser = localStorage.getItem('email');
    $scope.nameUser = localStorage.getItem('name'); 
    $scope.jobUser = localStorage.getItem('job'); 


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


    $scope.goBack = function(){
     	$scope.displayMode ='back';	
     	$scope.displayBORF = 'b';
    	window.location.href = "#/backPartieInteresse";
    	$("#menuFront").removeClass("navFront").addClass("navBack");
    	$("#menuFront>ul").removeClass("navGPRO").addClass("navGPROBack");
    	$("#menuFront>ul>li").removeClass("active");
     	$( "#menuFront>ul>li" ).eq( 6 ).addClass("active");
    }
    $scope.goFront = function(){
    	
    	$http.get(baseUrl
						+ "/gestionnaireCache/reloadReferentiel");
    	$scope.displayMode ='front';
    	$scope.displayBORF = 'f';
    	window.location.href = "#/partieInteressee";
    	$("#menuFront").removeClass("navBack").addClass("navFront");
    	$("#menuFront>ul").removeClass("navGPROBack").addClass("navGPRO");
     	$("#menuFront>ul>li").removeClass("active");
     	$( "#menuFront>ul>li" ).eq( 1 ).addClass("active");
    }
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
      $scope['style_'+newVal.key] = newVal.value;
    });
    $scope.$on('globalStyles:maxWidth767', function (event, newVal) {
      $timeout( function () {      
        $scope.style_isSmallScreen = newVal;
        if (!newVal) {
          $global.set('leftbarShown', false);
        } else {
          $global.set('leftbarCollapsed', false);
        }
      });
    });

    // there are better ways to do this, e.g. using a dedicated service
    // but for the purposes of this demo this will do :P
    
    //$scope.isLoggedIn = true;



    $rootScope.isLoggedIn = false;
    $scope.loginError = false;
    $scope.userlogin = {};
    $scope.currentUser = AuthenticationService.getFromStorageCurrentUser();

    if ($scope.currentUser != null) {
      $rootScope.isLoggedIn = true;


      $rootScope.userLogged=$scope.currentUser;
      $rootScope.isAuthentified =true;
      window.location.href = '#/';
  
      
  
      //window.location.href = '/';
    } else {
      $rootScope.isLoggedIn = false;
      $rootScope.isAuthentified =false;
      window.location.href = '#/login';
    }



  /*  $scope.logOut = function () {
      $scope.isLoggedIn = false;
    };
    $scope.logIn = function () {
      $scope.isLoggedIn = true;
    };*/

    $scope.getFromStorageName = function () {
    return AuthenticationService.getFromStorageName(); 

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

    $scope.logIn = function (userlogin) {
      /*  var expToken =
        'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW1lciIsInVzZXJJZCI6IjUiLCJyb2xlIjoiQ09OVFJPTEVVUiJ9.D6g8IMHsK2EApcx_VRim-krMBrppiOunmNHHv6swg9tQxnXp0xPVkl3Q48LuhPYHJ_eyA93WgxmxJJTIgvfwgg';*/
  
      // $rootScope.permissions.push(tokenPayload.role);
      // var permissionsBack = $scope.permissions;
      // localStorage.setItem('token', expToken);
  
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
            } else {
              $scope.loginError = true;
              $scope.userlogin.userName = '';
              $scope.userlogin.password = '';
            }
          }
        );
      };
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
    $scope.rightbarAccordions = [{open:true},{open:true},{open:true},{open:true},{open:true},{open:true},{open:true}];

    $scope.$on('$routeChangeStart', function (e) {
      // console.log('start: ', $location.path());
      progressLoader.start();
      progressLoader.set(50);
    });
    $scope.$on('$routeChangeSuccess', function (e) {
      // console.log('success: ', $location.path());
      progressLoader.end();
    });
    
  /*  $scope.logout = function() {
    	console.info('try to logout ');
    	LoginFactory.logout(function (data, status, headers, config) {
			// Success handler
//			delete $cookies['JSESSIONID'];
			console.info('The user has been logged out!'+ data + " " + status);
			$rootScope.isAuthentified =null;
			$location.path( "/login" );

		}, function(data, status, headers, config) {
			// Failure handler
			console.error('Something went wrong while trying to logout... ', data, status, headers, config);
		})
  }*/

  



    
  }])
  // Enable / Disable Debugging mode : true/false
.config(['$logProvider', function($logProvider){
    $logProvider.debugEnabled(false);
}])



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
