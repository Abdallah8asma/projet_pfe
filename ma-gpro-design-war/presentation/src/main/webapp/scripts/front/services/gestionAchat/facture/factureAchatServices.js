angular.module('atelier.factureAchatServices', [])

.service('FactureAchatServices', function($http, $q, UrlAtelier){
	
	that = this;
	
/***************** validate facture Facon***************/
	
	that.validateFacon =  function(listeBonLivraison,factureVenteId ){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/factureAchat/validateFacon?factureVenteId='+factureVenteId, listeBonLivraison)
					.then(function (response) {
			                deferred.resolve(response.data);
			                return deferred.promise;
			            }, function (response) {
			                // the following line rejects the promise
			                deferred.reject(response);
			                // promise is returned
			                return deferred.promise;
			            });
	};
	
	
/***************** validate facture Fini***************/
	
	that.validateFini =  function(listeBonLivraison,factureVenteId ){		
		var deferred = $q.defer();
		//Ancien valider permet la validation on regroupant par produit
		//return $http.post(UrlAtelier+'/factureAchat/validate?factureAchatId='+factureVenteId, listeBonLivraison)
		return $http.post(UrlAtelier+'/factureAchat/validateWithoutRegroupement?factureAchatId='+factureVenteId, listeBonLivraison)
					.then(function (response) {
			                deferred.resolve(response.data);
			                return deferred.promise;
			            }, function (response) {
			                // the following line rejects the promise
			                deferred.reject(response);
			                // promise is returned
			                return deferred.promise;
			            });
	};
	
/****************** Supprimer livraison *************************/
	
	that.supprimerFacture =  function(id){		
		var deferred = $q.defer();
		return $http.delete(UrlAtelier+'/factureAchat/deleteFacture:'+id)
					.then(function (response) {
			                deferred.resolve(response.data);
			                return deferred.promise;
			            }, function (response) {
			                // the following line rejects the promise
			                deferred.reject(response);
			                // promise is returned
			                return deferred.promise;
			            });
	};
	
	
})




