'use strict';

angular
  .module('gpro.produitSerialisable', ['ngResource'])
  .controller('produitSerialisableController', [
    '$scope',
    '$filter',
    '$http',
    '$log',
    'downloadService',
    'UrlCommun',
    'UrlAtelier',
    '$window',
    function (
      $scope,
      $filter,
      $http,
      $log,
      downloadService,
      UrlCommun,
      UrlAtelier,
      $window
    ) {
      // Déclaration des variables globales utilisés
      $log.info('=============PRODUIT===============');
      var data;
      $scope.displayMode = 'list';
      $scope.produitCourante = {};
      $scope.listePartieInteressee = [];
      $scope.listeDocuments = [];
      $scope.listeDocumentProduit = [];
      $scope.resultatRecherche = $scope.listeProduit;
      $scope.ListFamilleProduitCache = [];
      $scope.ListSousFamilleProduitCache = [];
      $scope.listeSitePartieInteresseeCache = [];
      $scope.listeDeviseCache = [];
      $scope.ListeDevise = [];
      $scope.ListeTaxe = [];
      $scope.prixFab = 0;
      $scope.qte = false;

      $scope.traitementEnCoursGenerer = "false";
      $scope.selectedHistorique = [];
      $scope.consulterHistorique = function (type) {
        //1 BR
        //2 BL
        //3 Facture
        //4 Av

        //console.log("Type : ",type);

        var index = this.row.rowIndex;
        // $scope.numSerieFilter=''
        $scope.selectedProduitsSerialisable = $scope.myData[index];
        // $scope.myDataReceptionAchat[index].produitsSerialisable;
        // $scope.selectedProduitReceptionDetail =
        // $scope.myDataReceptionAchat[index];

        $scope.selectedHistorique = [];

        if (type == 4 && $scope.myData[index].factureAvoirAncien != null) {
          $scope.selectedHistorique = $scope.myData[
            index
          ].factureAvoirAncien.split('&');
        }

        if (type == 3 && $scope.myData[index].factureAncien != null) {
          $scope.selectedHistorique = $scope.myData[index].factureAncien.split(
            '&'
          );
        }

        if (type == 2 && $scope.myData[index].blAncien != null) {
          $scope.selectedHistorique = $scope.myData[index].blAncien.split('&');
        }

        if (type == 1 && $scope.myData[index].brRetour != null) {
          $scope.selectedHistorique = $scope.myData[index].brRetour.split('&');
        }
      };

      /***************************************************
       * Gestion de Cache des Referentiels *
       **************************************************/

      /** get liste produits cache * */
      $scope.listeProduitCache = function () {
        $http
          .get(UrlAtelier + '/gestionnaireLogistiqueCache/listeProduitCache')
          .success(function (data) {
            console.log('listeProduitCache ' + data.length);
            $scope.listeProduitCache = data;
          });
      };
      $scope.listeProduitCache();

      /** Sous Famille Is Changed * */

      $scope.sousFamilleChange = function (id) {
        $log.debug('Sous Famille  is changed : id = ', id);

        var element = $scope.ListSousFamilleProduitCache.filter(function (
          node
        ) {
          return node.id == id;
        });
        // $scope.produitCourante.tva = element[0].tva;
        $scope.produitCourante.idTaxe = element[0].idTaxe;
        $scope.produitCourante.serialisable = element[0].serialisable;

        $log.debug('element = ', element);
      };

      // Liste des FamilleCache
      $scope.ListFamillesProduitCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeFamilleProduitCache')
          .success(function (dataFamilleProduitCache) {
            $log.debug('*LISTEFamilleProduitCache :' + dataFamilleProduitCache);
            $scope.ListFamilleProduitCache = dataFamilleProduitCache;
          });
      };

      // Liste des Taxes - Marwa 0202108
      $scope.ListeTaxe = function () {
        $http
          .get('http://localhost:8080/ma-gpro-logistique-rest/taxe/getTVA')
          .success(function (dataTaxe) {
            $scope.ListeTaxe = dataTaxe;
          });
      };

      $scope.ListeTaxe();

      // Liste des SousFamilleCache
      $scope.ListSousFamillesProduitCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeSousFamilleProduitCache')
          .success(function (dataSousFamilleProduitCache) {
            $log.debug(
              '*LISTESousFamilleProduitCache :' +
              dataSousFamilleProduitCache.length
            );
            $scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;
          });
      };

      // Liste des SiteeCache
      $scope.listeSitesPartieInteresseeCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeSitePartieInteresseeCache')
          .success(function (dataSiteCache) {
            $log.debug('*LISTESiteCache :' + dataSiteCache.length);
            $scope.listeSitePartieInteresseeCache = dataSiteCache;
          });
      };

      // Liste des DeviseCache
      /*
       * $scope.ListDeviseCache = function() { $http .get(
       * UrlCommun +
       * "/gestionnaireCache/listeDeviseCache") .success(
       * function(dataDeviseCache) {
       * $log.debug("*LISTEDeviseCache :" +
       * dataDeviseCache.length); $scope.ListDeviseCache =
       * dataDeviseCache;
       *
       * }); }
       */

      // Liste des PICache
      $scope.listePartieInteresseeCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listePartieInteresseeCache')
          .success(function (dataPICache) {
            // $log.debug("*LISTEPartieInteresseeCache
            // :"
            // +JSON.stringify(dataPICache,null,"
            // "));

            // $scope.listePartieInteressee
            // = $filter ('filter')
            // (dataPICache ,
            // {famillePartieInteressee
            // : 2});
            $scope.listePartieInteressee = dataPICache;
          });
      };

      // Liste TypeDocumentCache
      $scope.listeTypeDocumentsCache = function () {
        $http
          .get(UrlCommun + '/gestionnaireCache/listeTypeDocumentCache')
          .success(function (dataTypeDocumentCache) {
            $log.debug(
              '*LISTTypeDocumentCache :' + dataTypeDocumentCache.length
            );
            console.log(
              'liste type doc:prod cache:' + dataTypeDocumentCache.length
            );
            var dataTypeDocCache2 = [];
            for (var i = 0; i < dataTypeDocumentCache.length; i++) {
              console.log(
                'dataTypeDocumentCache[i]: ' + dataTypeDocumentCache[i].module
              );
              if (dataTypeDocumentCache[i].module == 'PRODUIT') {
                dataTypeDocCache2.push(dataTypeDocumentCache[i]);
                console.log(
                  'moduleid:' +
                  dataTypeDocumentCache[i].id +
                  ' designation: ' +
                  dataTypeDocumentCache[i].designation
                );
              }
            }

            $scope.listeTypeDocumentsCache = dataTypeDocCache2;
          });
      };
      $scope.ListFamillesProduitCache();
      $scope.ListSousFamillesProduitCache();
      $scope.listeSitesPartieInteresseeCache();
      // $scope.ListDeviseCache();
      $scope.listePartieInteresseeCache();
      $scope.listeTypeDocumentsCache();

      /***************************************************
       * Gestion des Produits
       **************************************************/
      $scope.deleteValue = 'oui';
      // Annuler Ajout
      $scope.cancelAddProduit = function (
        rowform,
        index,
        id,
        designation,
        liste
      ) {
        // $log.debug("* Designation
        // "+liste[0].designation);
        if (angular.isDefined(id)) {
          $log.debug('DEF ID');
          $scope.deleteValue = 'non';
          rowform.$cancel();
          $log.debug('CANCEL');
        } else {
          $log.debug('UND ID  ' + id);
          if (designation == '') {
            $scope.deleteValue == 'oui';
            $log.debug('Designation : ' + designation);
            $log.debug('$scope.deleteValueOUI : ' + $scope.deleteValue);
            liste.splice(index, 1);
            rowform.$cancel();
            $log.debug('DELETE');
          } else {
            $log.debug('Designation :' + designation);
            $log.debug('$scope.deleteValueNON : ' + $scope.deleteValue);
            rowform.$cancel();
            $log.debug('CANCEL');
          }
        }
        $scope.deleteValue = 'oui';
      };

      // Variale de Pagination de la grid
      $scope.pagingOptions = {
        pageSizes: [5, 10, 13],
        pageSize: 13,
        currentPage: 1,
      };

      // Liste des produits
      /*
       * $scope.listeProduit = function() {
       * $http.get(UrlCommun + "/produit/all").success(
       * function(data) { $scope.myData = data;
       *
       * //data, page,pageSize
       * $scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
       * $scope.pagingOptions.pageSize );
       *
       * }); }
       */
      // Liste des Devises
      $scope.ListeDevise = function () {
        $http.get(UrlCommun + '/devise/all').success(function (dataDevise) {
          $scope.ListeDevise = dataDevise;
        });
      };

      // Liste des PartieInteressee
      /*
       * $scope.listePartieInteressee = function() { $http
       * .get(UrlCommun + "/partieInteressee/all")
       * .success( function(dataPartieInteressee) {
       *
       * $scope.listePartieInteressee =
       * dataPartieInteressee; }); }
       */

      // Init data list
      $scope.initMyData = [];

      // Rechercher produit
      $scope.rechercheProduit = function (produitCourante) {
        $log.debug('recherche en cours ..');
        $http
          .post(
            UrlCommun +
            '/produitSerialisable/rechercheProduitSerialisableMulticritere',
            produitCourante
          )
          .success(function (resultat) {
            // $log.debug("----listeProduitRecherchée---
            // :
            // "+resultat.produitValues.length);
            // $log.debug("----listeProduitRecherchée---
            // :
            // "+JSON.stringify(resultat.produitValues,null,"
            // "));

            $scope.myData = resultat.produitSerialisableValues;
            $scope.initMyData = $scope.myData;

            // data, page,pageSize
            $scope.setPagingData(
              $scope.myData,
              $scope.pagingOptions.currentPage,
              $scope.pagingOptions.pageSize
            );

            $scope.rechercheProduitForm.$setPristine();
            $scope.displayMode = 'rechercher';
          });
      };

      // Initialiser la liste des produits (ng-grid)
      $scope.rechercheProduit({});

      // Annulation de l'ajout
      $scope.annulerAjout = function () {
        $scope.cnt = 0;
        $scope.produitCourante = {
          reference: '',
          designation: '',
          famille: '',
          sousfamille: '',
          partieInteressee: '',
          prix_inf: '',
          prix_sup: '',
          site: '',
          etat: '',
        };

        $scope.rechercheProduitForm.$setPristine();
        $scope.listeDocumentProduit = [];
        $scope.rechercheProduit({});
        $scope.displayMode = 'list';
      };
      // ** Ajout Produit
      $scope.AffectationProduit = function (Produit) {
        $scope.produitCourante = {};
        $scope.produitCourante = Produit ? angular.copy(Produit) : {};
        $scope.displayMode = 'edit';
        $scope.produitCourante.quantite = 0;
        $scope.qte = false;
      };

      // Ajout et Modification Produit
      $scope.modifierOuCreerProduit = function () {
        var index = this.row.rowIndex;
        // getProduit
        $http
          .get(
            UrlCommun + '/produitSerialisable/getId:' + $scope.myData[index].id
          )
          .success(function (datagetProduit) {
            // $scope.listeDocumentProduit
            // =
            // datagetProduit.documentProduits;

            $scope.creationProduitForm.$setPristine();
            // $scope.myData[index].documentProduits
            // =
            // $scope.listeDocumentProduit;
          });
        var dateVente = null;
        var dateAchat = null;
        var dateFinGarantie = null;
        if ($scope.myData[index].dateVente !== null) {
          dateVente = $scope.modifierFormatDate($scope.myData[index].dateVente);
        } else {
          dateVente = null;
        }
        if ($scope.myData[index].dateAchat !== null) {
          dateAchat = $scope.modifierFormatDate($scope.myData[index].dateAchat);
        } else {
          dateAchat = null;
        }
        if ($scope.myData[index].dateFinGarantie !== null) {
          dateFinGarantie = $scope.modifierFormatDate($scope.myData[index].dateFinGarantie);
        } else {
          dateFinGarantie = null;
        }


        $scope.produitCourante = Object.assign($scope.myData[index], { dateAchat: dateAchat }, { dateVente: dateVente }, { dateFinGarantie: dateFinGarantie })
          //  $scope.partieInteresseeCourante = $scope.myData[index]
          ? angular.copy($scope.myData[index])
          : {};




        /*  $scope.produitCourante = $scope.myData[index]
          ? angular.copy($scope.myData[index])
          : {};*/


        $scope.displayMode = 'edit';
        $scope.qte = true;
      };


      $scope.modifierFormatDate = function (dateUp) {
        const dateTimeFormat = new Intl.DateTimeFormat("en", {
          year: "numeric",
          month: "numeric",
          day: "2-digit",
        });
        var [
          { value: month },
          ,
          { value: day },
          ,
          { value: year }
        ] = dateTimeFormat.formatToParts(dateUp);
        return $scope.dateParEdition = `${year}-${month}-${day}`;

      }

      // Sauvegarder Produit
      $scope.sauvegarderAjout = function (Produit) {
        $log.debug('Sauvegarder Modification' + Produit);
        if (angular.isDefined(Produit.id)) {
          $scope.updateProduit(Produit);
        } else {
          $log.debug('Sauvegarder Ajout' + Produit.SiteId);
          $scope.creerProduit(Produit);
        }
        $scope.rechercheProduit({});
      };

      // Mise à jour des Produits
      $scope.updateProduit = function (produit) {
        produit.documentProduits = $scope.listeDocumentProduit;

        $http
          .post(
            UrlCommun + '/produitSerialisable/modifierProduitSerialisable',
            produit
          )
          .success(function (ProduitModifiee) {
            $log.debug('Success Modification ' + newProduit.designation);

            // TODO :getById
            $scope.annulerAjout();
          });
      };

      // Création Produit
      $scope.creerProduit = function (produit) {
        produit.documentProduits = $scope.listeDocumentProduit;

        $http
          .post(
            UrlCommun + '/produitSerialisable/creerProduitSerialisable',
            produit
          )
          .success(function (newProduit) {
            $log.debug('Success Creation' + newProduit.designation);

            // TODO :getById
            $scope.annulerAjout();
          });
      };

      // Suppression produit
      $scope.supprimerProduit = function () {
        $log.debug('DELETE ..' + $scope.myData[$scope.index].id);

        $http({
          method: 'DELETE',
          url:
            UrlCommun +
            '/produitSerialisable/supprimerProduitSerialisable:' +
            $scope.myData[$scope.index].id,
        }).success(function () {
          $log.debug('Success Delete');
          $scope.myData.splice($scope.index, 1);
          $scope.closePopupDelete();
        });
        $scope.closePopupDelete();
        $scope.rechercheProduit({});
      };

      // $scope.listeProduit();
      // $scope.listePartieInteressee();
      $scope.ListeDevise();

      // generer rapport de tous les bons de livraison.
      // mode : List

      function formattedDate(date) {
        var d = new Date(date), month = ''
          + (d.getMonth() + 1), day = ''
            + d.getDate(), year = d.getFullYear();

        if (month.length < 2)
          month = '0' + month;
        if (day.length < 2)
          day = '0' + day;
        return [year, month, day].join('-');
      }

      $scope.downloadProduitSerialisableNonVendue = function (produitCourant) {
        $scope.traitementEnCoursGenerer = "true";


        $http({
          url: UrlAtelier + "/fiches/listProduitSerialisable",
          method: "POST",
          data: produitCourant, // this is your json
          // data string
          headers: {
            'Content-type': 'application/json',
          },
          responseType: 'arraybuffer'
        }).success(function (data, status, headers, config) {

          $scope.traitementEnCoursGenerer = "false";
          var blob = new Blob([data], { type: "application/vnd.ms-excel" });


          var fileName = 'Produit_Sérialisables_En_Stock_' + formattedDate(new Date());
          var link = document.createElement('a');
          link.href = window.URL.createObjectURL(blob);
          link.download = fileName;
          link.click();
          window.URL.revokeObjectURL(link.href);


          // var objectUrl = URL.createObjectURL(blob);
          // window.open(objectUrl);
        }).error(function (data, status, headers, config) {
          // upload failed
        });
      }

      $scope.downloadAllProduits = function (produitCourant) {
        var url;
        // $log.debug("PI
        // "+produitCourant.partieInteressee );

        if (produitCourant.partieInteressee == null) {
          produitCourant.partieInteressee = '';
        }

        $log.debug(
          '-- produitCourant After' + JSON.stringify(produitCourant, null, '  ')
        );
        url =
          UrlCommun +
          '/reportCommun/listproduit?reference=' +
          produitCourant.reference +
          '&designation=' +
          produitCourant.designation +
          '&sousfamille=' +
          produitCourant.sousfamille +
          '&partieInteressee=' +
          produitCourant.partieInteressee +
          '&etat=' +
          '' +
          '&site=' +
          '' +
          '&prixInf=' +
          produitCourant.prix_inf +
          '&prixSup=' +
          produitCourant.prix_sup +
          '&type=pdf';

        $log.debug('-- URL--- :' + url);

        var fileName = 'Liste des produits Serialisable';
        var a = document.createElement('a');
        document.body.appendChild(a);
        downloadService.download(url).then(function (result) {
          var heasersFileName = result.headers(['content-disposition']).substring(17);
          var fileName = heasersFileName.split('.');
          var typeFile = result.headers(['content-type']);
          var file = new Blob([result.data], { type: typeFile });
          var fileURL = window.URL.createObjectURL(file);
          if (typeFile == 'application/vnd.ms-excel') {

            // a.href = fileURL;
            a.download = fileName[0];
            $window.open(fileURL)
            a.click();

          } else {

            a.href = fileURL;
            a.download = fileName[0];
            $window.open(fileURL)
            a.click();

          }


        });


        // downloadService.download(url).then(
        //   function (success) {
        //     // $log.debug('success : ' +
        //     // success);
        //   },
        //   function (error) {
        //     // $log.debug('error : ' + error);
        //   }
        // );
      };

      /** Fin de gestion des Produits */

      /***************************************************
       * Gestion des DocumentProduit
       **************************************************/
      $scope.listeDocumentProduit = [];
      $scope.name = 'P';
      // GetId.designation
      $scope.type = {
        status: '',
      };
      $scope.showStatus = function (id) {
        /*
         * $scope.type.status = id; var selected =
         * $filter('filterProduit')(
         * $scope.ListTypeDocumentCache, { id :
         * $scope.type.status }); if ($scope.type.status &&
         * selected.length) { return
         * selected[0].designation; } else { return "Not
         * Set"; }
         */
        var designation = '';
        console.log(
          'show status length: ' + $scope.listeTypeDocumentsCache.length
        );
        for (var i = 0; i < $scope.listeTypeDocumentsCache.length; i++) {
          // console.log("id:
          // "+id+"----listeTypeDocumentCache[i]="+
          // $scope.listeTypeDocumentsCache[i].id);
          // console.log("---module:
          // "+$scope.listeTypeDocumentCache[i].module);
          if ($scope.listeTypeDocumentsCache[i].id == id) {
            designation = $scope.listeTypeDocumentsCache[i].designation;
            // console.log("designation type doc :"
            // +designation);
            return designation;
          }
        }
      };
      // ajout d'un DocumentProduit
      $scope.ajoutDocumentProduit = function () {
        $scope.DocumentProduitInserree = {
          typeDocumentId: '',
          uidDocument: '',
          path: '',
        };
        $scope.listeDocumentProduit.push($scope.DocumentProduitInserree);
      };

      // Enregistrer DocumentProduit
      $scope.saveDocumentProduit = function (dataDocumentProduit, id) {
        $scope.deleteValue = 'non';
        $log.debug('$scope.deleteValue :' + $scope.deleteValue);
        angular.extend(dataDocumentProduit, {
          id: id,
        });
      };

      // Supprimer DocumentProduit
      $scope.removeDocumentProduit = function (index) {
        $scope.listeDocumentProduit.splice(index, 1);
      };

      // Download Document
      $scope.download = function (uuid) {
        var url = UrlCommun + '/gestionnaireDocument/document/' + uuid;
        console.log('iciiiii');

        downloadService.download(url).then(
          function (success) {
            $log.debug('success : ' + success);
          },
          function (error) {
            $log.debug('error : ' + error);
          }
        );
      };
      /** Fin de gestion des DocumentProduit */

      /***************************************************
       * Gestion des Grids de recherche
       **************************************************/
      $scope.typeAlert = 3;
      $scope.colDefs = [];
      $scope.$watch('myData', function () {
        $scope.colDefs = [
          {
            field: 'boutiqueAbreviation',
            displayName: 'Boutique',
            width: '15%',
          },
          {
            field: 'reference',
            displayName: 'Réf.Article',
            width: '15%',
          },
          {
            field: 'designation',
            displayName: 'Désignation',
            width: '25%',
          },
          {
            field: 'numSerie',
            displayName: 'N° Serie',
            width: '10%',
          },
          {
            field: 'fournisseurAbreviation',
            displayName: 'Fournisseur',
            width: '10%',
          },
          {
            field: 'numBonReception',
            displayName: 'N° Bon Récep.',
            width: '7%',
          },
          {
            width: '3%',
            cellTemplate:
              '<button data-nodrag class="fa fa-history" ng-click="consulterHistorique(1)" data-target="#SeriesModalConsult" data-toggle="modal"></button>',
          },
          {
            field: 'dateAchat',
            displayName: 'Date Achat',
            cellFilter: "date: 'yyyy-MM-dd'",
            width: '7%',
          },
          /*
           * { field :
           * 'prixAchat',
           * displayName : 'Prix
           * Achat', },
           */
          {
            field: 'partieIntersseDesignation',
            displayName: 'Client',
            width: '10%',
          },

          {
            field: 'numBonLivraison',
            displayName: 'N° Bon Liv.',
            width: '7%',
          },
          {
            width: '3%',
            cellTemplate:
              '<button data-nodrag class="fa fa-history" ng-click="consulterHistorique(2)" data-target="#SeriesModalConsult" data-toggle="modal"></button>',
          },

          {
            field: 'dateVente',
            displayName: 'Date Vente',
            cellFilter: "date: 'yyyy-MM-dd'",
            width: '7%',
          },
          {
            field: 'numFacture',
            displayName: 'N° Facture.',

            width: '7%',
          },
          {
            width: '3%',
            cellTemplate:
              '<button data-nodrag class="fa fa-history" ng-click="consulterHistorique(3)" data-target="#SeriesModalConsult" data-toggle="modal"></button>',
          },

          {
            field: 'dateFacture',
            displayName: 'Date Factur.',
            cellFilter: "date: 'yyyy-MM-dd'",
            width: '7%',
          },

          {
            field: 'prixVente',
            displayName: 'Prix Vente',
            width: '7%',
          },
          {
            field: 'dateFinGarantie',
            displayName: 'D.Fin Garantie',
            cellFilter: "date: 'yyyy-MM-dd'",
            width: '7%',
          },

          {
            field: 'numFactureAvoir',
            displayName: 'N° Avoir',
            width: '7%',
          },
          {
            width: '3%',
            cellTemplate:
              '<button data-nodrag class="fa fa-history" ng-click="consulterHistorique(4)" data-target="#SeriesModalConsult" data-toggle="modal"></button>',
          },
          //
          {
            field: '',
            cellTemplate:
              `<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">
                         <button class="ms-CommandButton-button ms-CommandButton-Gpro ng-show="!rowform.$visible" " ng-click="modifierOuCreerProduit()">
             <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
             </button>
              <button class="ms-CommandButton-button"  ng-click="showPopupDelete($scope.typeAlert)" permission="['Article_serialisable_Delete']">
             <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
             </button>
            	</div> `,

            // 					'<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button> <button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete('
            // 							+ $scope.typeAlert
            // + ')"> <i class="fa fa-fw fa-trash-o"></i></button>'
            // + '</div>',
            width: '10%',
          },
        ];
      });
      $scope.filterOptions = {
        filterText: '',
        useExternalFilter: true,
      };

      $scope.totalServerItems = 0;

      $scope.setPagingData = function (data, page, pageSize) {
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.myData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
          $scope.$apply();
        }
      };

      $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
          if (searchText) {
            var ft = searchText.toLowerCase();

            var data = $scope.initMyData.filter(function (item) {
              return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
            });

            $scope.setPagingData(data, page, pageSize);
          } else {
            $scope.setPagingData($scope.initMyData, page, pageSize);
          }
        }, 100);
      };

      $scope.getPagedDataAsync(
        $scope.pagingOptions.pageSize,
        $scope.pagingOptions.currentPage
      );

      $scope.$watch(
        'pagingOptions',
        function (newVal, oldVal) {
          if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
            $scope.getPagedDataAsync(
              $scope.pagingOptions.pageSize,
              $scope.pagingOptions.currentPage,
              $scope.filterOptions.filterText
            );
          }
        },
        true
      );
      $scope.$watch(
        'filterOptions',
        function (newVal, oldVal) {
          if (newVal !== oldVal) {
            $scope.getPagedDataAsync(
              $scope.pagingOptions.pageSize,
              $scope.pagingOptions.currentPage,
              $scope.filterOptions.filterText
            );
          }
        },
        true
      );

      $scope.gridOptions = {
        data: 'myData',
        columnDefs: 'colDefs',
        enablePaging: true,
        showFooter: true,
        enableColumnResize: true,
        enableHighlighting: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        selectedItems: $scope.selectedRows,
        filterOptions: $scope.filterOptions,
      };

      /** Fin de gestion des Grids de la partie Interesse */

      // /
      /*
       *
       * $scope.filterOptions = { filterText : "",
       * useExternalFilter : true };
       *
       * $scope.totalServerItems = 0;
       *
       * $scope.setPagingData = function(data, page,
       * pageSize) { var pagedData = data.slice((page - 1)
       * pageSize, page * pageSize); $scope.myData =
       * pagedData; $scope.totalServerItems = data.length;
       * if (!$scope.$$phase) { $scope.$apply(); } };
       *
       * $scope.getPagedDataAsync = function(pageSize,
       * page, searchText) { setTimeout( function() { var
       * data; var produitCourante =
       * $scope.produitCourante; if (searchText) { var ft =
       * searchText .toLowerCase(); $http .post( UrlCommun +
       * "uit/rechercheProduitMulticritere",produitCourante)
       * .success( function( largeLoad) { data = largeLoad
       * .filter(function( item) { return JSON .stringify(
       * item) .toLowerCase() .indexOf( ft) != -1; });
       * $scope .setPagingData( data, page, pageSize); });
       *  } else { $http .post(UrlCommun +
       * "/produit/rechercheProduitMulticritere",produitCourante)
       * .success(function(largeLoad) {
       * $scope.setPagingData( largeLoad, page, pageSize);
       * }); } }, 100); };
       *
       * $scope.getPagedDataAsync(
       * $scope.pagingOptions.pageSize,
       * $scope.pagingOptions.currentPage);
       *
       * $scope .$watch( 'pagingOptions', function(newVal,
       * oldVal) { if (newVal !== oldVal &&
       * newVal.currentPage !== oldVal.currentPage) {
       * $scope .getPagedDataAsync(
       * $scope.pagingOptions.pageSize,
       * $scope.pagingOptions.currentPage,
       * $scope.filterOptions.filterText); } }, true);
       * $scope.$watch('filterOptions', function(newVal,
       * oldVal) { if (newVal !== oldVal) {
       * $scope.getPagedDataAsync(
       * $scope.pagingOptions.pageSize,
       * $scope.pagingOptions.currentPage,
       * $scope.filterOptions.filterText); } }, true);
       *
       * $scope.gridOptions = { dataselected :
       * 'myDataselected', selectedItems : [],
       *
       * data : 'myData', columnDefs : [ { field :
       * 'reference', displayName : 'Réf.Produit' }, {
       * field : 'designation', displayName :
       * 'Désignation' }, { field : 'familleDesignation',
       * displayName : 'Famille' }, { field :
       * 'sousFamilleDesignation', displayName : 'Sous
       * Famille' }, { field :
       * 'partieIntersseDesignation', displayName : 'PI' }, {
       * field : 'prixUnitaire', displayName : 'Prix' }, {
       * field : 'siteEntiteDesignation', displayName :
       * 'Site' }, { field : 'etat', displayName : 'Etat' }, {
       * field : '', cellTemplate : '<div class="buttons"
       * ng-show="!rowform.$visible"><button data-nodrag
       * class="btn btn-default btn-xs"
       * ng-click="modifierOuCreerProduit()"> <i class="fa
       * fa-fw fa-pencil"></i></button> <button
       * data-nodrag class="btn btn-default btn-xs"
       * ng-click="showPopupDelete(' + $scope.typeAlert +
       * ')"> <i class="fa fa-fw fa-trash-o"></i></button>' +'</div>' } ],
       * enablePaging : true, showFooter : true,
       * totalServerItems : 'totalServerItems',
       * pagingOptions : $scope.pagingOptions,
       * filterOptions : $scope.filterOptions,
       *  };
       */
      /** Fin de gestion des Grids de la produit */
      $scope.editFicheBesoin = function () {
        var index = this.row.rowIndex;
        window.location.href =
          '#/ficheBesoin?idProduit=' + $scope.myData[index].id;
      };
    },
  ])
  .filter('filterProduit', function () {
    return function (liste, id) {
      var listeFiltre = [];
      // $log.debug("IdSousFamille= "+id.id);
      // $log.debug("listeSousFamille "+
      // JSON.stringify(listeSousFamille, null, " "));
      angular.forEach(listeSousFamille, function (sousFamille, key) {
        if (liste.id == id.id) {
          // $log.debug(liste.id +" == "+ id.id);
          listeFiltre.push(liste);
        }
      });
      // $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre,
      // null, " "));
      return listeFiltre;
    };
  })
  .controller('DateIntroCtrl', [
    '$scope',
    function ($scope) {
      $scope.maxToDay = new Date();
      // date piker defit
      // $scope.today = function() {
      // $scope.articleCourante.dateIntroduction = new
      // Date();
      // };
      // $scope.today();
      $scope.clear = function () {
        $scope.articleCourante.dateIntroduction = null;
      };
      // Disable weekend selection
      $scope.disabled = function (date, mode) {
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
      };
      $scope.toggleMin = function () {
        $scope.minDate = $scope.minDate ? null : new Date();
      };
      $scope.toggleMin();
      $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
      };
      $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1,
      };
      $scope.initDate = new Date('20/08/2015');
      $scope.formats = [
        'dd-MMMM-yyyy',
        'dd/MM/yyyy',
        'dd.MM.yyyy',
        'shortDate',
      ];
      $scope.format = $scope.formats[0];
    },
  ]);
