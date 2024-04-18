angular.module('commun.ProduitServices', [])

.service('ProduitServices', function($http, $q, UrlCommun){
	
	that = this;
	
	/****************** Get produit par ID *************************/
	
	that.getProduitById =  function(id){		
		var deferred = $q.defer();
		return $http.get(UrlCommun+'/produit/getId:'+id)
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




