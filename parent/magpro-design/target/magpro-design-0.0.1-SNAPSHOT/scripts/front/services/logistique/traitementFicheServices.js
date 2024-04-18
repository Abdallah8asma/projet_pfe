angular.module('atelier.traitementFicheServices', [])

.service('traitementFicheServices', function($http, $q, UrlAtelier){
	
	that = this;
	

/****************** get by id traitement fiche *************************/
	
	that.getbyId =  function(id){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/traitementFiche/getById?id:'+ id)
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
	
/****************** modifier le PU d'un traitement fiche *************************/
	
	that.setPUTraitementFiche =  function(id, nouveauPU, idFiche){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/traitementFiche/setTraitementPU?id='+ id+"&nouveauPU="+nouveauPU+ "&idFiche="+ idFiche)
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




