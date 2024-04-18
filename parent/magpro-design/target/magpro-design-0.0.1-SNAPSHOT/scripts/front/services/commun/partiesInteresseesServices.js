angular.module('commun.PIServices', [])

.service('PIServices', function($http, $q, UrlCommun){
	
	that = this;
	
	/****************** Get Parti Intéressé par ID *************************/
	
	that.getPIById =  function(id){		
		var deferred = $q.defer();
		return $http.get(UrlCommun+'/partieInteressee/getId:'+id)
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




