angular.module('atelier.CoutsServices', [])

.service('CoutsService', function($http, $q, UrlAtelier){
	
	that = this;
	

	that.rechercheMultricritereAvecCout =  function(ficheCourante){		
		var deferred = $q.defer();
		return $http.post(UrlAtelier+'/ficheSuiveuse/rechercheMulticritereAvecCout', ficheCourante)
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




