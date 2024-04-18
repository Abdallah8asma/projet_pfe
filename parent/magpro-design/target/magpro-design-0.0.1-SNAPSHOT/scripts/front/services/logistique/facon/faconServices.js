angular.module('atelier.faconServices', [])

.service('FaconServices', function($http, $q, UrlAtelier){
	
	that = this;
	
/***************** Recherche multicritere fiche façon ***************/
	
	that.getResultatRechercheFiche =  function(ficheCourante){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/ficheFacon/rechercheMulticritere',ficheCourante)
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
	
/***************** Ajout fiche façon ***************/
	
	that.createFicheFacon =  function(ficheCourante){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/ficheFacon/create',ficheCourante)
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
	
/***************** Update fiche façon ***************/
	
	that.updateFicheFacon =  function(ficheCourante){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/ficheFacon/update',ficheCourante)
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
	
	/****************** Liste bons de reception *************************/
	
	that.getListeBonReception =  function(){		
		var deferred = $q.defer();
		return $http.get(UrlAtelier+'/bonreception/getAll')
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
	
	/****************** Supprimer fiche façon *************************/
	
	that.supprimerFicheFacon =  function(id){		
		var deferred = $q.defer();
		return $http.delete(UrlAtelier+'/ficheFacon/delete:'+id)
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
	
	/****************** Get bon de reception par reference *************************/
	
	that.geListeBonReceptionByReference =  function(refBonReception){	
		console.log("refBonReception service:"+ refBonReception);
		var deferred = $q.defer();
		return $http.get(UrlAtelier+'/bonreception/getBonReceptionByReference:' + refBonReception)
					.then(function (response) {
						console.log("response.data service:"+ JSON.stringify(response.data, null, " "));
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




