angular.module('atelier.methodeTeintureServices', [])

.service('MethodeTeintureServices', function($http, $q, UrlAtelier){
	
	that = this;
	
	
	/****************** Liste methodes de teinture *************************/
	
	that.getlisteMethodeTeinture =  function(){		
		var deferred = $q.defer();
		return $http.get(UrlAtelier+'/methodeTeinture/getAll')
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




