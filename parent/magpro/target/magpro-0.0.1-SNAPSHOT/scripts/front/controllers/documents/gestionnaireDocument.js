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
		[ '$scope', '$http','$log','UrlCommun','downloadService','$window' ,function($scope,$http,$log,UrlCommun,downloadService ,$window){
		//	$scope.uploadUrl = "http://localhost:8080/mt-gpro-commun-rest/gestionnaireDocument";
			
			//declaration variable
			$scope.isUploaded = false;
			$scope.uploadFile = function(module) {
				
				$scope.isUploaded = true;
				var fd = new FormData();
				var file = $scope.myFile;
				var name = $scope.name;
				fd.append('file', file);
				fd.append('person', name);
				fd.append('module', module);
				$http.post(UrlCommun+"/gestionnaireDocument/upload", fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}
				}).success(function(data) {
					
					
					if(data.partieConcernee == 'CLIENT'){
					$scope.DocPartieInteresseInserree.vUUIDDocument = data.uuid;
					$scope.DocPartieInteresseInserree.path = data.nomFichier;
					}
					else if(data.partieConcernee == 'FOURNISSEUR'){
						$scope.DocPartieInteresseInserree.vUUIDDocument = data.uuid;
						$scope.DocPartieInteresseInserree.path = data.nomFichier;
					}
					 
					else if(data.partieConcernee == 'PRODUIT'){
						$scope.DocumentProduitInserree.uidDocument = data.uuid;
						$scope.DocumentProduitInserree.path = data.nomFichier;		
					}
					else if(data.partieConcernee == 'BL'){
						$scope.DocumentProduitInserree.uidDocument = data.uuid;
						$scope.DocumentProduitInserree.path = data.nomFichier;		
					}
					
					else if(data.partieConcernee == 'FACTURE'){
						$scope.DocumentProduitInserree.uidDocument = data.uuid;
						$scope.DocumentProduitInserree.path = data.nomFichier;		
					}
					
					else if(data.partieConcernee == 'AVOIR'){
						$scope.DocumentProduitInserree.uidDocument = data.uuid;
						$scope.DocumentProduitInserree.path = data.nomFichier;		
					}
					
					else if(data.partieConcernee == 'FINANCE_CLIENT_REGLEMENT'){
						$scope.DocumentProduitInserree.uidDocument = data.uuid;
						$scope.DocumentProduitInserree.path = data.nomFichier;		
					}
					
					else if(data.partieConcernee == 'ACHAT_RECEPTION'){
						$scope.documentReceptionAchatInserree.uidDocument = data.uuid;
						$scope.documentReceptionAchatInserree.path = data.nomFichier;		
					}
					
					else if(data.partieConcernee == 'ACHAT_FACTURE'){
						$scope.documentFactureAchatInserree.uidDocument = data.uuid;
						$scope.documentFactureAchatInserree.path = data.nomFichier;		
					}
					
					else if(data.partieConcernee == 'ACHAT_AVOIR'){
						$scope.documentFactureAchatInserree.uidDocument = data.uuid;
						$scope.documentFactureAchatInserree.path = data.nomFichier;		
					}
					
					else if(data.partieConcernee == 'FINANCE_FOURNISSEUR_REGLEMENT'){
						$scope.documentReglementAchatInserree.uidDocument = data.uuid;
						$scope.documentReglementAchatInserree.path = data.nomFichier;		
					}
					else if(data.partieConcernee == 'A'){
					$scope.DocumentArticleInserree.uidDocument = data.uuid;
					$scope.DocumentArticleInserree.path = data.nomFichier;	
					}
					else if(data.partieConcernee == 'BCV'){
						$scope.documentReceptionVenteInserree.uidDocument = data.uuid;
						$scope.documentReceptionVenteInserree.path = data.nomFichier;
					}
					
				}).error(function() {
				});
			}
			
			$scope.downloadFile = function(uuid, module){
				var url = UrlCommun+"/gestionnaireDocument/document/"+uuid+"?module="+module;
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
				

				$scope.bin2String=function (array) {
					var result = "";
					for (var i = 0; i < array.length; i++) {
						result += String.fromCharCode(parseInt(array[i], 2));
					}
					return result;
				}



				// downloadService.download(url)
				// 		.then(
				// 				function(success) {
				// 					console.log('success : '
				// 							+ success);
				// 				},
				// 				function(error) {
				// 					console.log('error : '
				// 							+ error);
				// 				});
			}
		 
			
		} ])
		
