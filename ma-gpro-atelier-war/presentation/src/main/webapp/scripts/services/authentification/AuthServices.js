//'use strict';

angular.module('gpromoteApp.authServices', ['LocalStorageModule'])

.config(['$httpProvider',
         'localStorageServiceProvider',
         function ($httpProvider, localStorageServiceProvider) {

	  localStorageServiceProvider
	  .setPrefix('gpromoteApp')
	  .setStorageType('sessionStorage')
	  .setStorageCookie(45, '<path>')
	  .setStorageCookieDomain('<domain>')
	  .setNotify(true, true)
	  
	$httpProvider.defaults.withCredentials = true;
	// Tough luck: the default cookie-to-header mechanism is not working for cross-origin requests!
	$httpProvider.defaults.xsrfCookieName = 'XSRF-TOKEN'; // The name of the cookie sent by the server
	$httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN'; // The default header name picked up by Spring Security

}])

.service('authServices', function($http, $q) {

	that = this;

	var urlBase = "http://localhost:8080/ma-gpro-logistique-rest";

})	
	

	.factory('LoginFactory', function ($http, $resource,Cookies) {
		var urlBase = "http://localhost:8080/ma-gpro-logistique-rest";

		var loginResources = $resource(urlBase + '/login', {}, {
			options: {method: 'OPTIONS', cache: false}
		});

		var logoutResources = $resource(urlBase + '/login', {}, {
			options: {method: 'OPTIONS', cache: false}
		});

		/**
		 * Tries to detect whether the response elements returned indicate an invalid or missing CSRF token...
		 */
		var isCSRFTokenInvalidOrMissing = function (data, status) {
			return (status === 403 && data.message && data.message.toLowerCase().indexOf('csrf') > -1)
				|| (status === 0 && data === null);
		};
		
		
		return {
			/**
			 * Service function that logs in the user with the specified username and password.
			 * To handle the returned promise we use a successHandler/errorHandler approach because we want to have
			 * access to the additional information received when the failure handler is invoked (status, etc.).
			 */
			login: function(username, password, successHandler, errorHandler) {

				// Obtain a CSRF token
				loginResources.options().$promise.then(function (response) {
					console.log('Obtained a CSRF token in a cookie', response);
//
//					// Extract the CSRF token
					var csrfToken = Cookies.getFromDocument($http.defaults.xsrfCookieName);
					console.log('Extracted the CSRF token from the cookie', csrfToken);
//
//					// Prepare the headers
					var headers = {
						'Content-Type': 'application/x-www-form-urlencoded'
					};
					headers[$http.defaults.xsrfHeaderName] = csrfToken;

					// Post the credentials for logging in
					$http.post(urlBase + '/login', 'username=' + username + '&password=' + password, {
						headers: headers
					})
						.success(successHandler)
						
//						.error(errorHandler)
						.error(function (data, status, headers, config) {

							console.error('error login');

							if (isCSRFTokenInvalidOrMissing(data, status)) {
								console.error('The obtained CSRF token was either missing or invalid. Have you turned on your cookies?');

							} else {
								// Nope, the error is due to something else. Run the error handler...
								errorHandler(data, status, headers, config);
							}
						})

				})
			},

			logout: function(successHandler, errorHandler) {

				// Obtain a CSRF token
				logoutResources.options().$promise.then(function (response) {
					console.log('Obtained a CSRF token in a cookie', response);

//					// Extract the CSRF token
					var csrfToken = Cookies.getFromDocument($http.defaults.xsrfCookieName);
					console.log('Extracted the CSRF token from the cookie', csrfToken);

					// Prepare the headers
					var headers = {
						'Content-Type': 'application/x-www-form-urlencoded'
					};
					headers[$http.defaults.xsrfHeaderName] = csrfToken;

					// Post the credentials for logging out
					$http.post(urlBase + '/logout', '', {
						headers: headers
					})
						.success(successHandler)
						.error(function(data, status, headers, config) {

							if (isCSRFTokenInvalidOrMissing(data, status)) {
								console.error('The obtained CSRF token was either missing or invalid. Have you turned on your cookies?');

							} else {
								// Nope, the error is due to something else. Run the error handler...
								errorHandler(data, status, headers, config);
							}
						});

				})
			}
		}
		
	})
