'use strict';

angular
  .module('theme.services', [])
  .service('$global', [
    '$rootScope',
    'EnquireService',
    '$document',
    function ($rootScope, EnquireService, $document) {
      this.settings = {
        fixedHeader: true,
        headerBarHidden: true,
        leftbarCollapsed: false,
        leftbarShown: false,
        rightbarCollapsed: false,
        fullscreen: false,
        layoutHorizontal: false,
        layoutHorizontalLargeIcons: false,
        layoutBoxed: false,
        showSearchCollapsed: false,
      };

      var brandColors = {
        default: '#ecf0f1',

        inverse: '#95a5a6',
        primary: '#3498db',
        success: '#2ecc71',
        warning: '#f1c40f',
        danger: '#e74c3c',
        info: '#1abcaf',

        brown: '#c0392b',
        indigo: '#9b59b6',
        orange: '#e67e22',
        midnightblue: '#34495e',
        sky: '#82c4e6',
        magenta: '#e73c68',
        purple: '#e044ab',
        green: '#16a085',
        grape: '#7a869c',
        toyo: '#556b8d',
        alizarin: '#e74c3c',
      };

      this.getBrandColor = function (name) {
        if (brandColors[name]) {
          return brandColors[name];
        } else {
          return brandColors['default'];
        }
      };

      $document.ready(function () {
        EnquireService.register('screen and (max-width: 767px)', {
          match: function () {
            $rootScope.$broadcast('globalStyles:maxWidth767', true);
          },
          unmatch: function () {
            $rootScope.$broadcast('globalStyles:maxWidth767', false);
          },
        });
      });

      this.get = function (key) {
        return this.settings[key];
      };
      this.set = function (key, value) {
        this.settings[key] = value;
        $rootScope.$broadcast('globalStyles:changed', {
          key: key,
          value: this.settings[key],
        });
        $rootScope.$broadcast(
          'globalStyles:changed:' + key,
          this.settings[key]
        );
      };
      this.values = function () {
        return this.settings;
      };
    },
  ])
  .factory('pinesNotifications', function () {
    return {
      notify: function (args) {
        var notification = new PNotify(args);
        notification.notify = notification.update;
        return notification;
      },
    };
  })
  .factory('progressLoader', function () {
    return {
      start: function () {
     //   $(document).skylo('start');
      },
      set: function (position) {
       // $(document).skylo('set', position);
      },
      end: function () {
      //  $(document).skylo('end');
      },
      get: function () {
      //  return $(document).skylo('get');
      },
      inch: function (amount) {
      /*  $(document).skylo('show', function () {
          $(document).skylo('inch', amount);
        });*/
      },
    };
  })
  .factory('EnquireService', [
    '$window',
    function ($window) {
      return $window.enquire;
    },
  ])
  .factory('$bootbox', [
    '$modal',
    function ($modal) {
      // NOTE: this is a workaround to make BootboxJS somewhat compatible with
      // Angular UI Bootstrap in the absence of regular bootstrap.js
      if ($.fn.modal == undefined) {
        $.fn.modal = function (directive) {
          var that = this;
          if (directive == 'hide') {
            if (this.data('bs.modal')) {
              this.data('bs.modal').close();
              $(that).remove();
            }
            return;
          } else if (directive == 'show') {
            return;
          }

          var modalInstance = $modal.open({
            template: $(this).find('.modal-content').html(),
          });
          this.data('bs.modal', modalInstance);
          setTimeout(function () {
            $('.modal.ng-isolate-scope').remove();
            $(that)
              .css({
                opacity: 1,
                display: 'block',
              })
              .addClass('in');
          }, 100);
        };
      }

      return bootbox;
    },
  ])
  .service('lazyLoad', [
    '$q',
    '$timeout',
    function ($q, $t) {
      var deferred = $q.defer();
      var promise = deferred.promise;
      this.load = function (files) {
        angular.forEach(files, function (file) {
          if (file.indexOf('.js') > -1) {
            // script
            (function (d, script) {
              var fDeferred = $q.defer();
              script = d.createElement('script');
              script.type = 'text/javascript';
              script.async = true;
              script.onload = function () {
                $t(function () {
                  fDeferred.resolve();
                });
              };
              script.onerror = function () {
                $t(function () {
                  fDeferred.reject();
                });
              };

              promise = promise.then(function () {
                script.src = file;
                d.getElementsByTagName('head')[0].appendChild(script);
                return fDeferred.promise;
              });
            })(document);
          }
        });

        deferred.resolve();

        return promise;
      };
    },
  ])
  .filter('safe_html', [
    '$sce',
    function ($sce) {
      return function (val) {
        return $sce.trustAsHtml(val);
      };
    },
  ])

  .service('AuthenticationService', [
    '$http',
    'UrlCommun',
    function ($http, UrlCommun) {
      var service = {};
      service.login = login;
      service.logout = logout;
      service.getFromStorageCurrentUser = getFromStorageCurrentUser;
      return service;

      function login(userName, password, callback) {
        $http
          .post(UrlCommun + '/user/login', {
            userName: userName,
            password: password,
          })
          .success(function (response) {
            response;
            console.log(response)

            if (response.token != null && response.token != undefined) {
              //  console.log("RRRRRESP : ",response.userName);

              localStorage.setItem(
                'currentUser',
                JSON.stringify(response.token)
              );

              localStorage.setItem(
                'email',
                response.email
              );

              localStorage.setItem(
                'job',
              response.job
              );

              
              localStorage.setItem(
                'name',
               `${response.firstName +' '+response.lastName }`
              );
              getFromStorageCurrentUser();

              // execute callback with true to indicate successful login
              callback(true);
            } else {
              // execute callback with false to indicate failed login
              callback(false);
            }
          });
      }

      function getFromStorageCurrentUser() {
        var Currentuser = JSON.parse(localStorage.getItem('currentUser'));
        return Currentuser;
      }
      function logout() {
        // remove user from local storage and clear http auth header
        localStorage.removeItem('currentUser');
      }
    },
  ])
  .service('jwtHelper', function ($window) {
    this.urlBase64Decode = function (str) {
      var output = str.replace(/-/g, '+').replace(/_/g, '/');
      switch (output.length % 4) {
        case 0: {
          break;
        }
        case 2: {
          output += '==';
          break;
        }
        case 3: {
          output += '=';
          break;
        }
        default: {
          throw 'Illegal base64url string!';
        }
      }
      return $window.decodeURIComponent(escape($window.atob(output))); //polyfill https://github.com/davidchambers/Base64.js
    };

    this.decodeToken = function (token) {
      var parts = token.split('.');

      if (parts.length !== 3) {
        throw new Error('JWT must have 3 parts');
      }

      var decoded = this.urlBase64Decode(parts[1]);
      if (!decoded) {
        throw new Error('Cannot decode the token');
      }

      return angular.fromJson(decoded);
    };

    this.getTokenExpirationDate = function (token) {
      var decoded = this.decodeToken(token);

      if (typeof decoded.exp === 'undefined') {
        return null;
      }

      var d = new Date(0); // The 0 here is the key, which sets the date to the epoch
      d.setUTCSeconds(decoded.exp);

      return d;
    };

    this.isTokenExpired = function (token, offsetSeconds) {
      var d = this.getTokenExpirationDate(token);
      offsetSeconds = offsetSeconds || 0;
      if (d === null) {
        return false;
      }

      // Token expired?
      return !(d.valueOf() > new Date().valueOf() + offsetSeconds * 1000);
    };
    
    this.getTokenExpirationBoutiqueId = function (token) {
        var decoded = this.decodeToken(token);

        if (typeof decoded.boutiqueId === 'undefined') {
          return null;
        }

        return decoded.boutiqueId;
      };
      
      this.getTokenExpirationUserId = function (token) {
          var decoded = this.decodeToken(token);

          if (typeof decoded.userId === 'undefined') {
            return null;
          }

          return decoded.userId;
        };
        
        
  });
