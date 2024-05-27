/**
 * IpSTIT : 192.168.1.150
 */

angular
  .module('config', ['ngResource'])
  .constant('UrlCommun', 'http://54.196.178.43:8080/mt-gpro-commun-rest')
  .constant('UrlAtelier', 'http://54.196.178.43:8080/ma-gpro-logistique-rest');
  // .constant('UrlCommun', 'http://51.83.78.248:8055/mt-gpro-commun-rest')
  // .constant('UrlAtelier', 'http://51.83.78.248:8055/ma-gpro-logistique-rest');
