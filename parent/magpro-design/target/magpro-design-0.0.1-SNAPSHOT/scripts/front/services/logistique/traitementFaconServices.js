angular.module('atelier.traitementFaconServices', [])

.service('traitementFaconServices', function($http, $q, UrlAtelier){
	
	that = this;
	
	/****************** Liste des traitements façon *************************/
	
	that.getListeTraitementFacon =  function(){		
		var deferred = $q.defer();
		return $http.get(UrlAtelier+'/traitementFacon/getAll')
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
	
/****************** Ajouter un traitement façon *************************/
	
	that.ajouterTraitementFacon =  function(traitementFacon){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/traitementFacon/create', traitementFacon)
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
	
/****************** modifier un traitement façon *************************/
	
	that.modifierTraitementFacon =  function(traitementFacon){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/traitementFacon/update', traitementFacon)
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
	
/****************** supprimer un traitement façon *************************/
	
	that.deleteTraitementFacon =  function(id){		
		var deferred = $q.defer();
		return $http.delete(UrlAtelier+'/traitementFacon/delete:'+ id)
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
	
/****************** modifier le PU d'un traitement façon *************************/
	
	that.setPUTraitementFacon =  function(id, nouveauPU){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/traitementFacon/setPU?traitementFaconId='+ id+"&nouveauPU="+nouveauPU)
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




