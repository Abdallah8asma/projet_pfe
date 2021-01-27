angular.module('atelier.bonLivraisonServices', [])

.service('BonLivraisonServices',['$http','$q','UrlAtelier', function($http, $q, UrlAtelier){
	
	that = this;
	
	
/***************** validate bonCommande ***************/
	
	that.validateBC =  function(refBonCommandesList,livraisonVenteId ){		
		var deferred = $q.defer();                                      
		return $http.post(UrlAtelier+'/bonlivraison/validateCommandes?livraisonVenteId='+livraisonVenteId, refBonCommandesList)
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
	
	
/***************** validate bonLivraison Facon***************/
	
	that.validateFacon =  function(listeBonSortie,livraisonVenteId ){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/bonlivraison/validateFacon?livraisonVenteId='+livraisonVenteId, listeBonSortie)
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
	
	
/***************** validate bonLivraison Fini***************/
	
	that.validateFini =  function(listeBonSortie,livraisonVenteId ){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/bonlivraison/validate?livraisonVenteId='+livraisonVenteId, listeBonSortie)
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
	
	
	/***************** validate bonLivraison Fini By OF***************/
	
	that.validateFiniByOF =  function(listeBonSortie,livraisonVenteId ){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/bonlivraison/validateByOF?livraisonVenteId='+livraisonVenteId, listeBonSortie)
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
	
	that.supprimerLivraison =  function(id){		
		console.log('sssssss');
		console.log(UrlAtelier);
		console.log('sssssss');
		var deferred = $q.defer();
		return $http.delete(UrlAtelier+'/bonlivraison/deleteBonLivraison:'+id)
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
	
	
}])




