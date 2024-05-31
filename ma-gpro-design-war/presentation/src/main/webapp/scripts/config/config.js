/**
 * IpSTIT : 192.168.1.150
 */

angular.module('config', ['ngResource'])
    .constant('UrlCommun', "http://localhost:8889/mt-gpro-commun-rest-3.5.0.0-SNAPSHOT")
    .constant('UrlAtelier', "http://localhost:8890/ma-gpro-logistique-rest-3.5.0.0-SNAPSHOT");
  // .constant('UrlCommun', 'http://51.83.78.248:8055/mt-gpro-commun-rest')
  // .constant('UrlAtelier', 'http://51.83.78.248:8055/ma-gpro-logistique-rest');
