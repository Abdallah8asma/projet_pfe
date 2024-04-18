'use strict'
/**
 * Menu Authorization
 */
angular
		.module('gpro.menuAuthorization', [])
		.controller(
				'MenuAuthorizationController',function($scope, $rootScope,$route){
//					if($rootScope.ITEM == null){
//						$rootScope.ITEM = 'article';
//					}
          



					// $scope.ITEM = 'empty';
					
					// $scope.goManagerUser = function(){$scope.ITEM = 'managerUser';}
					// $scope.goManagerOperation = function(){$scope.ITEM = 'managerOperation';}
					// $scope.goManagerBoutique = function(){$scope.ITEM = 'managerBoutique';}
			
          
          
          $scope.ITEM = 'empty';
          var current = $route.current;
          
          
          //console.log(current )
              switch (current.params['typeMenuBack']) {
                case 'managerUser':
                  $scope.ITEM = 'managerUser';
                  $scope.goManagerUser = function(){$scope.ITEM = 'managerUser';}
                  break;
                case 'managerOperation':
                  $scope.ITEM = 'managerOperation';
                  $scope.goManagerOperation = function(){$scope.ITEM = 'managerOperation';}
                  break;
          
                  case 'managerBoutique':
                  $scope.ITEM = 'managerBoutique';
                  $scope.goManagerBoutique = function(){$scope.ITEM = 'managerBoutique';}
                  break;
                  
                  case 'managerSociete':
                      $scope.ITEM = 'managerSociete';
                      $scope.goManagerSociete = function(){$scope.ITEM = 'managerSociete';}
                      break;
                      
                  case 'managerMagasin':
                      $scope.ITEM = 'managerMagasin';
                      $scope.goManagerMagasin = function(){$scope.ITEM = 'managerMagasin';}
                      break;
              }
          

				});
