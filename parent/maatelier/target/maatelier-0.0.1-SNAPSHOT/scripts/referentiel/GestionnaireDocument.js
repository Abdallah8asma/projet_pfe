/**
 * Gestionnaire de document: chargement et téléchargement
 */

'use strict'
var gestionnaireDocument = angular.module('gpro.gestionnaireDocument', [ "ngResource" ]);
		
gestionnaireDocument.directive(
		'fileModel', [ '$parse', function($parse) {
			return {
				restrict : 'A',
				link : function(scope, element, attrs) {
					var model = $parse(attrs.fileModel);
					var modelSetter = model.assign;
					element.bind('change', function() {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
						});
					});
				}
			};
		} ]);
gestionnaireDocument.service('fileUpload', [ '$http', function($http) {

	}
]);
gestionnaireDocument.controller('UploadCtrl',
		[ '$scope', '$http', function($scope,$http){
			$scope.uploadUrl = "http://localhost:8080/mt-gpro-commun-rest/gestionnaireDocument";
			
			//declaration variable
			$scope.isUploaded = false;
			$scope.uploadFile = function() {
				
				$scope.isUploaded = true;
				var fd = new FormData();
				var file = $scope.myFile;
				var name = $scope.name;
				fd.append('file', file);
				fd.append('person', name);
				$http.post($scope.uploadUrl+"/upload", fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}
				}).success(function(data) {
					if(data.partieConcernee == 'PI'){
					$scope.DocPartieInteresseInserree.vUUIDDocument = data.uuid;
					$scope.DocPartieInteresseInserree.path = data.nomFichier;
					}else if(data.partieConcernee == 'A'){
					$scope.DocumentArticleInserree.uidDocument = data.uuid;
					$scope.DocumentArticleInserree.path = data.nomFichier;	
					}else if(data.partieConcernee == 'P'){
						$scope.DocumentProduitInserree.uidDocument = data.uuid;
						$scope.DocumentProduitInserree.path = data.nomFichier;		
					}
				}).error(function() {
				});
			}
		$scope.downloadFile = function(uuid){
			console.log("*uuid "+uuid);
			$http.get("http://localhost:8080/mt-gpro-commun-rest/gestionnaireDocument/document/"+uuid)
			.success(function(data){
				$scope.dataByte = data;
			}).error(function() {
				console.log("Erreur");
		    });
		}
		
			
		} ])
		
/*************Doc Commande Vente ************/
		gestionnaireDocument.controller('UploadCtrlCommandeVente',
		[ '$scope', '$http', function($scope,$http){
			
			$scope.uploadUrl = "http://localhost:8080/mt-gpro-commun-rest/gestionnaireDocument";
			//declaration des variables
			$scope.isUploadedCommandeVente = false;
			$scope.uploadFileCommandeVente = function() {
				$scope.isUploadedCommandeVente = true;
				var fd = new FormData();
				var file = $scope.myFile;
				var name = $scope.name;
				console.log("File + Name "+$scope.myFile + $scope.name);
				fd.append('file', file);
				fd.append('person', name);
				$http.post($scope.uploadUrl+"/upload", fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}
				}).success(function(data) {
					console.log("DATA partieConcernee :" + data.partieConcernee);
					
					 if(data.partieConcernee == 'BCV'){
					$scope.documentCommandeVenteInserree.uidDocument = data.uuid;
					$scope.documentCommandeVenteInserree.path = data.nomFichier;
					}
				}).error(function() {
					console.log("erreur Upload");
				});
			}
		$scope.downloadFileCommandeVente = function(uuid){
			console.log("*uuid "+uuid);
			$http.get("http://localhost:8080/mt-gpro-commun-rest/gestionnaireDocument/document/"+uuid)
			.success(function(data){
				$scope.dataByte = data;
			}).error(function() {
				console.log("Erreur Download");
		    });
		}
		
			
		} ])