angular.module('gpro.UserServices', [])

.service(
		'UserService',
		function($http, $q, UrlCommun) {

			that = this;

			that.getByEmail = function(email) {
				console.log("enter to service  "+ email);
				var deferred = $q.defer();
				return $http.get(UrlCommun + '/user/getByEmail?email=' + email)
						.then(function(response) {
							deferred.resolve(response.data);
							return deferred.promise;
						}, function(response) {
							// the following line rejects the promise
							deferred.reject(response);
							// promise is returned
							return deferred.promise;
						});
			};

			that.getListeCommerciaux = function(email) {

				var deferred = $q.defer();
				return $http.get(UrlCommun + '/user/getAll')
						.then(function(response) {
							deferred.resolve(response.data);
							return deferred.promise;
						}, function(response) {
							// the following line rejects the promise
							deferred.reject(response);
							// promise is returned
							return deferred.promise;
						});
			};

		})
