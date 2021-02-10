angular.module('atelier.factureServices', [])

.service('FactureServices', function($http, $q, UrlAtelier){
	
	that = this;
	
/***************** validate facture Facon***************/
	
	that.validateFacon =  function(listeBonLivraison,factureVenteId ){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/facture/validateFacon?factureVenteId='+factureVenteId, listeBonLivraison)
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
		return $http.post(UrlAtelier+'/facture/validate?factureVenteId='+factureVenteId, listeBonLivraison)
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
	
	
	/***************** validate facture Fini By OF***************/
	
	that.validateFiniByOF =  function(listeBonLivraison,factureVenteId ){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/facture/validateByOF?factureVenteId='+factureVenteId, listeBonLivraison)
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
		return $http.delete(UrlAtelier+'/facture/deleteFacture:'+id)
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




