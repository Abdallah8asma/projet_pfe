'use strict';
angular.module('gpro.gcAchatBC', []).controller('AchatBCCtrl', [
  '$scope',
  '$http',
  '$filter',
  '$rootScope',
  '$log',
  'downloadService',
  'UrlAtelier',
  'UrlCommun',
  '$window',
  function (
    $scope,
    $http,
    $filter,
    $rootScope,
    $log,
    downloadService,
    UrlAtelier,
    UrlCommun,
    $window
  ) {
    $log.info('----------- Vente BC ----------');
    // ** Variables Recherche
    $scope.listProduitCommandes = [];
    $scope.ListClientcommandeAchatCache = [];

    $scope.hiddenNotif = "true";

    $scope.traitementEnCours = "false";

    $scope.traitementEnCoursGenererAll = "false";

    /**************************************
     * Notification *
     **************************************/
    $scope.showNotif = function () {
      $scope.hiddenNotif = "false";
    }

    $scope.closeNotif = function () {
      $scope.hiddenNotif = "true";
    }

    // **Variables Modif/Ajout
    // init objetCourant
    $scope.commandeAchatCourante = {
      reference: '',
      partieInteresseId: '',
      idProduitParRef: '',
      idProduitParDesignation: '',
      dateIntroductionDu: '',
      dateIntroductionA: '',
      dateLivraisonDu: '',
      dateLivraisonA: '',
      quantiteDu: '',
      quantiteA: '',
      coutDu: '',
      coutA: '',
      type: '',
    };

    // Tableau de Taxe Prédefini

    $scope.listTaxeLivraisonInitMethod = function () {
      // if ($scope.natureLivraison == "FINI") {
      $scope.listTaxeLivraisonInit = [
        /*
         * {//FODEC taxeId: 1, pourcentage: 1, montant:
         * '', },
         */
        {
          // TVA19
          taxeId: 2,
          pourcentage: 19,
          montant: '',
        },
        {
          // TVA7
          taxeId: 4,
          pourcentage: 7,
          montant: '',
        },
        {
          // TVA13
          taxeId: 5,
          pourcentage: 13,
          montant: '',
        },
      ];
      /*
       * } else { $scope.listTaxeLivraisonInit = [ {//
       * TVA taxeId : 2, pourcentage : 19, montant :
       * '', } ]; }
       */
    };
    $scope.listeProduitCache = [];
    $scope.listeDocumentcommandeAchat = [];
    // **Variable Grid
    $scope.myDatacommandeAchat = [];
    // bouton pdf hide
    $scope.modePdf = 'notActive';

    $scope.displayMode = 'list';

    /***************************************************
     * Gestion de Cache des Referentiels Gestion
     * Commerciale *
     **************************************************/
    $scope.ListEtatcommandeAchatCache = [];
    $scope.ListTypecommandeAchatCache = [];
    $scope.listeSitePartieInteresseeCache = [];
    $scope.ListTypeDocumentCache = [];
    $scope.ListSousFamilleProduitCache = [];

    // Liste des PartieInteressee (avec FamilleId=1)
    $scope.listeClientCache = function () {
      $http
        .get(UrlCommun + '/gestionnaireCache/listePartieInteresseeCache')
        .success(function (dataPartieInteressee) {
          $scope.listeClientCache = dataPartieInteressee;
        });
    };

    // Liste des SiteeCache
    $scope.listeSitesPartieInteresseeCache = function () {
      $http
        .get(UrlCommun + '/gestionnaireCache/listeSitePartieInteresseeCache')
        .success(function (dataSiteCache) {
          console.log('listSiteCache : ' + dataSiteCache.length);
          $scope.listeSitePartieInteresseeCache = dataSiteCache;
        });
    };

    // Liste TypeDocumentCache
    // $scope.listeTypeDocumentsCache = function() {
    // $http
    // .get(baseUrl+"/gestionnaireCache/listeTypeDocumentCache")
    // .success(
    // function(dataTypeDocumentCache) {
    // console.log("listTypeDocumentCache : " +
    // dataTypeDocumentCache.length);
    // $scope.ListTypeDocumentCache =
    // dataTypeDocumentCache;
    // });
    // }
    //
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

    // Liste des Agent
    // $scope.ListAgent = function() {
    // $http
    // .get(baseUrlGc+"/agentGc/all")
    // .success(
    // function(data) {
    // console.log("listAgent: " + data.length);
    // $scope.listAgent = data;
    //
    // VenteCache();
    // $scope.listeEtatCommandeV });
    // }

    // TODO: Liste des Taxes A remplacer par une liste
    // extraite de la cache


    $scope.listeTaxes = function () {
      $http.get(UrlAtelier + '/taxe/getAll').success(function (dataTaxe) {
        $scope.listeTaxes = dataTaxe;
        console.log('===> get taxe');

      });
    };


  	 $scope.ListeTaxe = function () {
        $http.get(UrlAtelier + '/taxe/getTVA').success(function (dataTaxe) {
          $scope.ListeTaxe = dataTaxe;
        });
      };

      $scope.ListeTaxe();  




    // Liste des produits
    $scope.listeProduitCache = function () {
      $http
        .get(UrlCommun + '/article/all')
        .success(function (data) {
          console.log('listeProduitCache ' + data.length);
          $scope.listeProduitCache = data;
        });
    };

				// Liste des unités :uniteArticle
							$scope.listeUniteArticle = function() {
								$http
										.get(UrlCommun + "/uniteArticle/all")
										.success(
												function(data) {
													console
															.log("listeProduitCache "
																	+ data.length+" "+data);
													$scope.listeUniteArticle = data;

												});
							}

	$scope.listeUniteArticle();

    $scope.listeClientCache();
    $scope.listeTaxes();
    // $scope.listeTypeCommandeenteCache();
    $scope.listeSitesPartieInteresseeCache();
    // $scope.listeTypeDocumentsCache();
    $scope.ListSousFamillesProduitCache();
    $scope.listeProduitCache();
    // $scope.ListAgent();
    /** ********************************************* */

    /** ********************************************* */
    $scope.pagingOptions = {
      pageSizes: [5, 10, 13],
      pageSize: 13,
      currentPage: 1,
    };

    $scope.cancelAddcommandeAchat = function (rowform, index, id, designation) {
      if (angular.isDefined(id)) {
        $scope.deleteValue = 'non';
        rowform.$cancel();
        console.log('CANCEL');
      } else {
        $scope.deleteValue == 'oui';
        $scope.listProduitCommandes.splice(index, 1);
        rowform.$cancel();
      }
      $scope.deleteValue = 'oui';
    };
    // ** Ajout Bon de Commande de Vente
    $scope.AffectationVenteBC = function () {
      $scope.listTaxeLivraisonInitMethod();
      $scope.initTaxeRemoved();

      $scope.commandeAchatCourante = {};

      $http
        .get(UrlAtelier + '/commandeAchat/getCurrentReferenceMensuel')
        .success(function (res) {
          $scope.commandeAchatCourante.reference = res;
          $scope.commandeAchatCourante.refAvantChangement = res;
        });

      // $scope.commandeAchatCourante = commande ?
      // angular
      // .copy(commande) : {};

      $scope.displayMode = 'edit';
    };

    // Annulation de l'ajout
    $scope.annulerAjout = function () {
      $scope.traitementEnCoursGenererAll = "false";
      $scope.traitementEnCoursGenererCommande = "false";
      $scope.traitementEnCours = "false";


      $scope.closeNotif();
      $scope.cnt = 0;
      // bouton pdf hide
      $scope.modePdf = 'notActive';
      // vider le tab
      $scope.varPrix = [
        {
          index: '',
          prix: '',
        },
      ];
      // init objetCourant
      $scope.commandeAchatCourante = {
        reference: '',
        partieInteresseId: '',
        idProduitParRef: '',
        idProduitParDesignation: '',
        dateIntroductionDu: '',
        dateIntroductionA: '',
        dateLivraisonDu: '',
        dateLivraisonA: '',
        quantiteDu: '',
        quantiteA: '',
        coutDu: '',
        coutA: '',
      };
      $scope.recherchercommandeAchat({});
      $scope.listProduitCommandes = [];
      $scope.listTaxeLivraison = [];
      $scope.creationSSForm.$setPristine();
      $scope.rechercheSSForm.$setPristine();
      $scope.displayMode = 'list';
      $scope.closeNotif();
    };

    $scope.recherchercommandeAchat = function (commandeAchatCourante) {
      $http
        .post(
          UrlAtelier + '/commandeAchat/rechercheMulticritere',
          commandeAchatCourante
        )
        .success(function (data) {
          $scope.myDatacommandeAchat = data.listCommandeAchat;
        });
    };

    /** * PDF ** */
    // conversion date en String
    function formattedDate(date) {
      var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;
      return [year, month, day].join('-');
    }

    // generer rapport apres creation d'un bon de
    // sortie. mode : Modification/Consultation
    $scope.download = function (id, pRapportPrix, type) {
      $scope.traitementEnCoursGenererCommande = "true";

      // init checkbox : 'non' :rapport sans Prix /
      // 'oui' rapport avec prix
      $scope.checkboxModel = {
        rapportPrix: 'oui',
      };

      console.log('inn commande');

      var url =
        UrlAtelier +
        '/reportAchat/bonCommande?id=' +
        id +
        '&avecPrix=' +
        pRapportPrix +
        '&typerapport=' +
        type +
      
        '&type=pdf';

      console.log('iciiiiiiii stock')

      var fileName = '  Liste Bon Sortie	';
      var a = document.createElement('a');
      document.body.appendChild(a);
      downloadService.download(url).then(function (result) {
        var heasersFileName = result.headers(['content-disposition']).substring(22);
        var fileName = heasersFileName.split('.');
        var typeFile = result.headers(['content-type']);
        var file = new Blob([result.data], { type: typeFile });
        var fileURL = window.URL.createObjectURL(file);
        if (typeFile == 'application/vnd.ms-excel') {
          console.log('llll excel');
          // a.href = fileURL;
          a.download = fileName[0];
          $window.open(fileURL)
          a.click();

        } else {
          console.log('llll pdf');
          a.href = fileURL;
          a.download = fileName[0];
          $window.open(fileURL)
          a.click();

        }

        $scope.traitementEnCoursGenererCommande = "false";



      });



      // downloadService.download(url).then(
      //   function (success) {
      //     console.log('success : ' + success);
      //   },
      //   function (error) {
      //     console.log('error : ' + error);
      //   }
      // );
    };

    // generer rapport de tous les bons de livraison.
    // mode : List

    $scope.dowloadListCmdAchat = function (commandeAchatCourante) {
      $scope.traitementEnCoursGenererAll = "true";


      $http({
        url: UrlAtelier + "/fichesAchat/listCommandesAchat",
        method: "POST",
        data: commandeAchatCourante, // this is your json
        // data string
        headers: {
          'Content-type': 'application/json',
        },
        responseType: 'arraybuffer'
      }).success(function (data, status, headers, config) {

        $scope.traitementEnCoursGenererAll = "false";
        var blob = new Blob([data], { type: "application/vnd.ms-excel" });


        var fileName = 'Achat-Liste_Commandes_' + formattedDate(new Date());
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


    $scope.downloadAllcommandeAchat = function (commandeAchatCourante) {
      $scope.traitementEnCoursGenererAll = "true";

      var newdateLivBCMinFormat = '';
      if (angular.isDefined(commandeAchatCourante.dateLivraisonDu)) {
        if (commandeAchatCourante.dateLivraisonDu != '') {
          newdateLivBCMinFormat = formattedDate(
            commandeAchatCourante.dateLivraisonDu
          );
          console.log('===== newdateLivBCMinFormat ' + newdateLivBCMinFormat);
        } else {
          console.log('===== newdateLivBCMinFormat is Null');
          newdateLivBCMinFormat = '';
        }
      } else {
        console.log('==dateLivraisonDu Undefined');
      }

      var newdateLivBCMaxFormat = '';
      if (angular.isDefined(commandeAchatCourante.dateLivraisonA)) {
        if (commandeAchatCourante.dateLivraisonA != '') {
          newdateLivBCMaxFormat = formattedDate(
            commandeAchatCourante.dateLivraisonA
          );
          // console.log("=====
          // newdateLivBCMaxFormat
          // "+newdateLivBCMaxFormat);
        } else {
          console.log('===== newdateLivBCMaxFormat is Null');
          newdateLivBCMaxFormat = '';
        }
      } else {
        console.log('==dateLivraisonA Undefined');
      }

      var newdateIntroBCMinFormat = '';
      if (angular.isDefined(commandeAchatCourante.dateLivraisonDu)) {
        if (commandeAchatCourante.dateLivraisonDu != '') {
          newdateIntroBCMinFormat = formattedDate(
            commandeAchatCourante.dateLivraisonDu
          );
          console.log(
            '===== newdateIntroBCMinFormat ' + newdateIntroBCMinFormats
          );
        } else {
          console.log('===== newdateIntroBCMinFormat is Null');
          newdateIntroBCMinFormat = '';
        }
      } else {
        console.log('==dateLivraisonDu Undefined');
      }

      var newdateIntroBCMaxFormat = '';
      if (angular.isDefined(commandeAchatCourante.dateIntroductionA)) {
        if (commandeAchatCourante.dateIntroductionA != '') {
          newdateIntroBCMaxFormat = formattedDate(
            commandeAchatCourante.dateIntroductionA
          );
          console.log(
            '===== newdateIntroBCMaxFormat ' + newdateIntroBCMaxFormat
          );
        } else {
          console.log('===== newdateIntroBCMaxFormat is Null');
          newdateIntroBCMaxFormat = '';
        }
      } else {
        console.log('==dateIntroductionA Undefined');
      }

      console.log(
        '-- commandeAchatCourante' +
        JSON.stringify(commandeAchatCourante, null, '  ')
      );

      var url;
      console.log('PI  ' + commandeAchatCourante.vTypePartieInteressee);
      if (commandeAchatCourante.vTypePartieInteressee == null) {
        commandeAchatCourante.vTypePartieInteressee = '';
      }

      console.log('Produit  ' + commandeAchatCourante.vProduit);
      if (commandeAchatCourante.vProduit == null) {
        commandeAchatCourante.vProduit = '';
      }

      url =
        UrlAtelier +
        '/report/listcommandeAchat?reference=' +
        commandeAchatCourante.vReference +
        '&partieInteressee=' +
        commandeAchatCourante.vTypePartieInteressee +
        '&produit=' +
        commandeAchatCourante.vProduit +
        '&dateIntroductionDu=' +
        newdateIntroBCMinFormat +
        '&dateIntroductionA=' +
        newdateIntroBCMaxFormat +
        '&dateLivraisonDu=' +
        newdateLivBCMinFormat +
        '&dateLivraisonA=' +
        newdateLivBCMaxFormat +
        '&typeCommande=' +
        commandeAchatCourante.vTypeCommande +
        '&etatCommande=' +
        commandeAchatCourante.vEtatCommande +
        '&quantiteDu=' +
        commandeAchatCourante.quantiteDu +
        '&quantiteA=' +
        commandeAchatCourante.quantiteA +
        '&coutDu=' +
        commandeAchatCourante.coutDu +
        '&coutA=' +
        commandeAchatCourante.coutA +
        '&type=pdf';

      console.log('-- URL' + url);

      console.log('iciiiiiiii stock')

      var fileName = '  Liste Bon Sortie	';
      var a = document.createElement('a');
      document.body.appendChild(a);
      downloadService.download(url).then(function (result) {
        var heasersFileName = result.headers(['content-disposition']).substring(22);
        var fileName = heasersFileName.split('.');
        var typeFile = result.headers(['content-type']);
        var file = new Blob([result.data], { type: typeFile });
        var fileURL = window.URL.createObjectURL(file);
        if (typeFile == 'application/vnd.ms-excel') {
          console.log('llll excel');
          // a.href = fileURL;
          a.download = fileName[0];
          $window.open(fileURL)
          a.click();

        } else {
          console.log('llll pdf');
          a.href = fileURL;
          a.download = fileName[0];
          $window.open(fileURL)
          a.click();

        }

        $scope.traitementEnCoursGenererAll = "false";

      });




      // downloadService.download(url).then(
      //   function (success) {
      //     // console.log('success : ' +
      //     // success);
      //   },
      //   function (error) {
      //     // console.log('error : ' + error);
      //   }
      // );
    };

    /***************************************************
     * Notifications
     **************************************************/

    $scope.hiddenNotif = 'true';

    $scope.showNotif = function () {
      $scope.hiddenNotif = 'false';
    };

    $scope.closeNotif = function () {
      $scope.hiddenNotif = 'true';
    };

    /***************************************************
     * Gestion des TaxeBLiv
     **************************************************/
    /** Mode Creation * */
    $scope.addTaxeBLivInit = function () {
      $scope.inserted = {
        taxeId: '',
        pourcentage: '',
        montant: '',
      };
      $scope.listTaxeLivraisonInit.push($scope.inserted);
    };

    // remove TaxeBLivInit
    $scope.removeTaxeBLivInit = function (index, taxeId) {
      $scope.listTaxeLivraisonInit.splice(index, 1);

      if (angular.isNumber(taxeId)) {
        var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
        $scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
        $scope.filterTaxes();
      }
    };

    // remove TaxeBLiv
    $scope.removeTaxeBLiv = function (index, taxeId) {
      $scope.listTaxeLivraison.splice(index, 1);
      if (angular.isNumber(taxeId)) {
        var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
        $scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
        $scope.filterTaxes();
      }
    };

    $scope.addTaxeBLiv = function () {
      $scope.inserted = {
        taxeId: '',
        pourcentage: '',
        montant: '',
      };
      $scope.listTaxeLivraison.push($scope.inserted);
    };

    // saveTaxeBLiv
    $scope.saveTaxeBLiv = function (data) {
      $scope.taxeIdRemove.push(data.taxeId);
      $scope.filterTaxes();
    };

    $scope.saveTaxeBLivInit = function (data) {
      $scope.taxeIdRemove.push(data.taxeId);
      $scope.filterTaxes();
    };

    $scope.initTaxeRemoved = function () {
      /*
       * if($scope.natureLivraison == "FINI"){
       * $scope.taxeIdRemove = [1,2]; //FODEC + TVA
       * }else{ $scope.taxeIdRemove = [2]; //TVA
       * uniquement }
       */

      $scope.taxeIdRemove = [2]; // TVA
      // uniquement
    };

    // Filtre de la dropList des taxes
    $scope.filterTaxes = function () {
      return function (item) {
        var condition = false;

        for (var k = 0; k < $scope.taxeIdRemove.length; k++) {
          if (item.id != $scope.taxeIdRemove[k]) {
            condition = true;
          } else {
            condition = false;
            break;
          }
        }

        if (condition == true) {
          return true;
        }
        return false;
      };
    };

    /***************************************************
     * Gestion des Bon de commande
     **************************************************/

    $scope.isLoading = false;

    // Ajout et Modification commandeAchat
    $scope.modifierOuCreercommandeAchat = function (id) {
      $scope.isLoading = true;

     var index = this.row.rowIndex;
      // getcommandeAchat
      $http
        .get(
          UrlAtelier +
          '/commandeAchat/getById:' +
         

           $scope.myDatacommandeAchat[index].id
        ) 

        
        .success(function (datagetcommandeAchat) {
          
          $log.debug(
            'getById : ' +
            $scope.myDatacommandeAchat[index].id +
            ' \n  ' +
            JSON.stringify(datagetcommandeAchat, null, '  ')
          );
          // produitCommande
          $scope.listProduitCommandes =
            datagetcommandeAchat.listProduitCommandes;

          // disable update de la
          // dropList 'Produit'
          angular.forEach($scope.listProduitCommandes, function (
            produitCommande,
            key
          ) {
            produitCommande.checked = true;

            // $scope.updateProduitCommandDetails(produitCommande);

            $scope.productFilter = [];
            $scope.sousFamilleFilter = [];

            $scope.getProductFilter(produitCommande.produitId);

            if ($scope.productFilter.length > 0) {
              produitCommande.designation = $scope.productFilter[0].designation;
              //produitCommande.prixUnitaire = $scope.productFilter[0].prixUnitaire;

             produitCommande.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;



             /*   $scope.getSousFamilleFilter(
                $scope.productFilter[0].sousFamilleId
              );

            if ($scope.sousFamilleFilter.length > 0) {
                produitCommande.sousFamille =
                  $scope.sousFamilleFilter[0].designation;
              }

            */


            }
          });

          // bouton PDF activé
          $scope.modePdf = 'actif';
          $scope.isLoading = false;

          // document
          // $scope.listeDocumentcommandeAchat
          // =
          // datagetcommandeAchat.documentcommandeAchats;
          /**
           * ** Added on 30 03 2018
           * ***
           */
          $scope.listProduitTaxes = datagetcommandeAchat.listProduitTaxes;
          console.log(
            'edit: liste taxe size: ' +
            datagetcommandeAchat.listProduitTaxes.length
          );
          $scope.myDatacommandeAchat[index].listProduitTaxes =
            $scope.listProduitTaxes;
          /** ************************* */
          $scope.myDatacommandeAchat[index].listProduitCommandes =
            $scope.listProduitCommandes;
          // $scope.myDatacommandeAchat[index].documentcommandeAchats
          // =
          // $scope.listeDocumentcommandeAchat;

          /**
           * ********* Added on 30 03
           * 2018 *****
           */
          // Initialiser le filtre des
          // taxe à éliminer
          $scope.taxeIdRemove = [];
          for (var int = 0; int < $scope.listProduitTaxes.length; int++) {
            // $scope.taxeIdRemove.push($scope.listTaxeLivraison[int].taxeId);

            // Temporary remove
            // FODEC from list
            if ($scope.listProduitTaxes[int].taxeId == 1) {
              console.log(
                'index' +
                $scope.listProduitTaxes.indexOf($scope.listProduitTaxes[int])
              );
              $scope.listProduitTaxes.splice(
                $scope.listProduitTaxes.indexOf($scope.listProduitTaxes[int]),
                1
              );
            }
          }
          $scope.filterTaxes();

          /** *** */
        });



      var dateIntroduction = null;
      var dateLivraison = null;
      if ($scope.myDatacommandeAchat[index].dateIntroduction !== null) {
        dateIntroduction = $scope.modifierFormatDate($scope.myDatacommandeAchat[index].dateIntroduction);
      } else {
        dateIntroduction = null;
      }


      if ($scope.myDatacommandeAchat[index].dateLivraison !== null) {
        dateLivraison = $scope.modifierFormatDate($scope.myDatacommandeAchat[index].dateLivraison);
      } else {
        dateLivraison = null;
      }


      $scope.commandeAchatCourante = Object.assign($scope.myDatacommandeAchat[index], { dateIntroduction: dateIntroduction } ,{ dateLivraison: dateLivraison })
        //  $scope.partieInteresseeCourante = $scope.myData[index]
        ? angular.copy($scope.myDatacommandeAchat[index])
        : {};


      // $scope.commandeAchatCourante = $scope.myDatacommandeAchat[index]
      //   ? angular.copy($scope.myDatacommandeAchat[index])
      //   : {};
      $scope.displayMode = 'edit';
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


    // Sauvegarder commandeAchat
    $scope.sauvegarderAjout = function (commande) {
      $scope.traitementEnCours = "true";
      if (angular.isDefined(commande.id)) {
        $log.debug('Sauvegarder Modification' + commande.id);
        $scope.updatecommandeAchat(commande);
      } else {
        $log.debug('Sauvegarder Ajout' + commande.reference);
        $scope.creercommandeAchat(commande);
      }
      // refresh de la liste
      // $scope.recherchercommandeAchat({});
    };

    // Création d'un Bon de commande de vente
    $scope.creercommandeAchat = function (commande) {

      // produitCommande
      commande.listProduitCommandes = $scope.listProduitCommandes;

      /** *** added on 30 03 2018 **** */
      commande.listProduitTaxes = $scope.listTaxeLivraisonInit;
      console.log('create liste taxe: ' + commande.listProduitTaxes.length);

      /** ********* */

      // DocumentProduitCommande
      // commande.documentcommandeAchats =
      // $scope.listeDocumentcommandeAchat ;
      $log.debug('-- Create ' + JSON.stringify(commande, null, '  '));
      commande.type = 'Commande';
      $http
        .post(UrlAtelier + '/commandeAchat/create', commande)
        .success(function (newCommandeId) {
          $scope.traitementEnCours = "false";
          $scope.showNotif();
          $log.debug('success creation : ' + newCommandeId);
          //
          // getcommandeAchat
          $http
            .get(UrlAtelier + '/commandeAchat/getById:' + newCommandeId)
            .success(function (datagetcommandeAchat) {
              // produitCommande
              $scope.listProduitCommandes =
                datagetcommandeAchat.listProduitCommandes;

              // getTableaux
              $scope.listProduitTaxes = datagetcommandeAchat.listProduitTaxes;

              // disable
              // update
              // de la
              // dropList
              // 'Produit'
              angular.forEach($scope.listProduitCommandes, function (
                produitCommande,
                key
              ) {
                produitCommande.checked = true;

                // $scope.updateProduitCommandDetails(produitCommande);

                $scope.productFilter = [];
                $scope.sousFamilleFilter = [];

                $scope.getProductFilter(produitCommande.produitId);

                if ($scope.productFilter.length > 0) {
                  produitCommande.designation =
                    $scope.productFilter[0].designation;


     produitCommande.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;



                  //produitCommande.prixUnitaire = $scope.productFilter[0].prixUnitaire;

             /*    $scope.getSousFamilleFilter(
                    $scope.productFilter[0].sousFamilleId
                  );

                  if ($scope.sousFamilleFilter.length > 0) {
                    produitCommande.sousFamille =
                      $scope.sousFamilleFilter[0].designation;
                  }

*/
                }

                $scope.showNotif();
              });

              // bouton
              // PDF
              // activé
              $scope.modePdf = 'actif';

              // document
              // $scope.listeDocumentcommandeAchat
              // =
              // datagetcommandeAchat.documentcommandeAchats;

              $scope.commandeAchatCourante = datagetcommandeAchat
                ? angular.copy(datagetcommandeAchat)
                : {};

              $scope.taxeIdRemove = [];
              for (var int = 0; int < $scope.listProduitTaxes.length; int++) {
                $scope.taxeIdRemove.push($scope.listProduitTaxes[int].taxeId);
              }
              $scope.filterTaxes();
            });
        });

      console.log('commande liste taxe: ' + commande.listProduitTaxes.length);
    };

    // Mise à jour des Bons de Commandes de Vente
    $scope.updatecommandeAchat = function (commande) {
      // produitCommande
      commande.listProduitCommandes = $scope.listProduitCommandes;
      /** ******** added on 30 03 2018 ******* */
      commande.listTaxeLivraison = $scope.listTaxeLivraisonInit;

      // console.log("liste taxe:
      // "+commande.listTaxeLivraison.length);
      /** *************** */

      // document
      // commande.documentcommandeAchats =
      // $scope.listeDocumentcommandeAchat ;
      $log.debug('Update ' + JSON.stringify(commande, null, '  '));

      $http
        .post(UrlAtelier + '/commandeAchat/update', commande)
        .success(function (commandeModifiee) {
          $scope.traitementEnCours = "false";
          $scope.showNotif();
          // for (var i = 0; i <
          // $scope.myDatacommandeAchat.length;
          // i++) {
          //
          // if
          // ($scope.myDatacommandeAchat[i].id
          // == commandeModifiee.id) {
          // $scope.myDatacommandeAchat[i]
          // = commandeModifiee;
          // break;
          // }
          // }

          $log.debug('success Modification ' + commandeModifiee);

          // getcommandeAchat
          $http
            .get(UrlAtelier + '/commandeAchat/getById:' + commandeModifiee)
            .success(function (datagetcommandeAchat) {
              // bouton
              // PDF
              // activé
              $scope.modePdf = 'actif';

              // produitCommande
              $scope.listProduitCommandes =
                datagetcommandeAchat.listProduitCommandes;

              // disable
              // update
              // de la
              // dropList
              // 'Produit'
              angular.forEach($scope.listProduitCommandes, function (
                produitCommande,
                key
              ) {
                produitCommande.checked = true;

                // $scope.updateProduitCommandDetails(produitCommande);
                $scope.productFilter = [];
                $scope.sousFamilleFilter = [];

                $scope.getProductFilter(produitCommande.produitId);

                if ($scope.productFilter.length > 0) {
                  produitCommande.designation =
                    $scope.productFilter[0].designation;
                


             // produitCommande.prixUnitaire =  $scope.productFilter[0].prixUnitaire;
                   

               /*   $scope.getSousFamilleFilter(
                    $scope.productFilter[0].sousFamilleId
                  );

                  if ($scope.sousFamilleFilter.length > 0) {
                    produitCommande.sousFamille =
                      $scope.sousFamilleFilter[0].designation;
                  }*/

     produitCommande.sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;




                }
              });

              // document
              // $scope.listeDocumentcommandeAchat
              // =
              // datagetcommandeAchat.documentcommandeAchats;

              $scope.commandeAchatCourante = datagetcommandeAchat
                ? angular.copy(datagetcommandeAchat)
                : {};

              $scope.showNotif();

              $scope.taxeIdRemove = [];
              for (var int = 0; int < $scope.listProduitTaxes.length; int++) {
                $scope.taxeIdRemove.push($scope.listProduitTaxes[int].taxeId);
              }
              $scope.filterTaxes();
            });
        });
    };

    /***************************************************
     * Conversion des Ids/Designation
     **************************************************/
    // TypeTaxe
    $scope.typeTaxeId = {
      status: '',
    };
    $scope.showTaxe = function (id) {
      $scope.typeTaxeId.status = id;
      var selected = $filter('filter')($scope.listeTaxes, {
        id: $scope.typeTaxeId.status,
      });
      if ($scope.typeTaxeId.status && selected.length) {
        return selected[0].designation;
      } else {
        return 'Not Set';
      }
    };

    // suppression BonCommande
    $scope.supprimerCommandeAchat = function () {
      // var index = this.row.rowIndex;
      $http({
        method: 'DELETE',
        url:
          UrlAtelier +
          '/commandeAchat/delete:' +
          $scope.myDatacommandeAchat[$scope.index].id,
      })
        .success(function () {
          $log.debug('Success Delete');
          $scope.myDatacommandeAchat.splice($scope.index, 1);
        })
        .error(function () {
          $log.debug('Erreur');
          $scope.myDatacommandeAchat.splice($scope.index, 1);
        });
      $scope.closePopupDelete();
      $scope.recherchercommandeAchat({});
    };

    $scope.updateProduitCommandDetails = function (produitCommande) {
      $scope.productFilter = [];
      $scope.sousFamilleFilter = [];

      $scope.getProductFilter(produitCommande.id);

      if ($scope.productFilter.length > 0) {
        produitCommande.designation = $scope.productFilter[0].designation;
        produitCommande.prixUnitaire = $scope.productFilter[0].prixUnitaire;

        $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);

        if ($scope.sousFamilleFilter.length > 0) {
          produitCommande.sousFamille = $scope.sousFamilleFilter[0].designation;
        }
      }
    };

    // Produit
    $scope.produitId = {
      status: '',
    };
    // SousFamilleProduit
    $scope.sousFamilleProduitId = {
      status: '',
    };

    // showProduitDetails
    $scope.showProduitDetails = function (produitId, attributRecherche) {
      $scope.produitId.status = produitId;
      var selected = $filter('showProduitFilterBS')($scope.listeProduitCache, {
        id: $scope.produitId.status,
      });
      if ($scope.produitId.status && selected.length) {
        if (attributRecherche == 'reference') {
          return selected[0].reference;
        } else if (attributRecherche == 'tissu') {
          return selected[0].designation;
        }
        if (attributRecherche == 'prix') {
          return selected[0].prixUnitaire;
        } else if (attributRecherche == 'type') {
          // conversion de sousFamilleId par son
          // designation
          $scope.sousFamilleProduitId.status = selected[0].sousFamilleId;
          var selected2 = $filter('filterSousFamilleBS')(
            $scope.ListSousFamilleProduitCache,
            {
              id: $scope.sousFamilleProduitId.status,
            }
          );
          if ($scope.sousFamilleProduitId.status && selected2.length) {
            return selected2[0].designation;
          } else {
            return '--';
          }
        }
      }
    };

    $scope.showBtnCalender = true;
    // show bottons Calender
    $scope.showBC = function () {
      $scope.showBtnCalender = true;
    };

    $scope.type = {
      status: '',
    };

    $scope.famille = {
      status: '',
    };

    $scope.varPrix = [
      {
        index: '',
        prix: '',
      },
    ];

    $scope.changePrix = function (event, item, index) { };

    $scope.getProductFilter = function (idProduit) {
	
	
		 $scope.productFilter = $scope.listeProduitCache.filter(function(node) {
					        return node.id==idProduit;
					    });
	
	
     /* $scope.productFilter = $filter('filter')($scope.listeProduitCache, {
        id: idProduit,
      });*/



    };

    $scope.getSousFamilleFilter = function (sousFamilleId) {
    /*  $scope.sousFamilleFilter = $filter('filter')(
        $scope.ListSousFamilleProduitCache,
        {
          id: sousFamilleId,
        }
      );
       */

$scope.sousFamilleFilter =  $scope.ListSousFamilleProduitCache.filter(function(node) {
					        return node.id==sousFamilleId;
					    });

    };

    $scope.clickProduit = function (idProduit, index) {
      $scope.updateProduitCommandDetails($scope.listProduitCommandes[index]);

      $log.debug('idProduit---' + idProduit);

      $scope.productFilter = [];
      $scope.sousFamilleFilter = [];

      $scope.getProductFilter(idProduit);

      if ($scope.productFilter.length > 0) {
        $scope.listProduitCommandes[index].designation =
          $scope.productFilter[0].designation;
        $scope.listProduitCommandes[index].prixUnitaire =
          $scope.productFilter[0].pu;

       // $scope.getSousFamilleFilter($scope.productFilter[0].sousFamilleId);


        $scope.listProduitCommandes[index].designation =
          $scope.productFilter[0].designation;

	  $scope.listProduitCommandes[index].sousFamilleArtEntite = $scope.productFilter[0].sousFamilleArtEntiteDesignation;
	$scope.listProduitCommandes[index].taxeId = $scope.productFilter[0].idTaxe;
						

$scope.listProduitCommandes[index].unite = $scope.productFilter[0].idTaxe;
if($scope.productFilter[0].uniteEntite != null){
		 var prodUnite = $scope.listeUniteArticle.filter(function(node) {
					        return node.id==$scope.productFilter[0].uniteEntite;
					    });
	if(prodUnite.length >0)
	
	$scope.listProduitCommandes[index].unite = prodUnite[0].designation;
}

	


    /*    if ($scope.sousFamilleFilter.length > 0) {
          $scope.listProduitCommandes[index].sousFamille =
            $scope.sousFamilleFilter[0].designation;
        }
*/



        /***Debut get Prix Speciale Fournisseur  ***/


        if (idProduit != null
          && idProduit != ""
          && $scope.commandeAchatCourante.partieIntersseId != null
          && $scope.commandeAchatCourante.partieIntersseId != "") {


          var prixFournisseurRech = {

            "idClient": $scope.commandeAchatCourante.partieIntersseId,
            "idProduit": idProduit

          }


          $http.post(UrlCommun + "/prixClient/PrixClientProduit", prixFournisseurRech
          ).success(
            function (resultat) {


              if (resultat != "" && resultat.prixvente > 0) {


                $scope.listProduitCommandes[index].prixUnitaire = resultat.prixvente;


              }


            });


        }

        /*** Fin get Prix Speciale Fournisseur  ***/



      }
    };

    // ajout d'un Produit
    $scope.ajoutProduit = function () {
      console.log('----------------- New Line ------------------');
      console.log('New Line ');

      $scope.produitInserree = {
        produitId: '',
        quantite: 1,
        prix: '',
        commandeAchatId: '',
        checked: false,
      };

      $scope.listProduitCommandes.push($scope.produitInserree);
    };

    // Enregistrer Produit
    $scope.saveProduit = function (dataProduit, id) {
      $scope.deleteValue = 'non';

      angular.extend(dataProduit, {
        id: id,
      });
      $scope.showBtnCalender = false;
    };

    // Supprimer Produit
    $scope.removeProduit = function (index) {
      $scope.listProduitCommandes.splice(index, 1);
      console.log('Success Delete Produit ');
    };

    /** Fin de gestion des Produit */

    /***************************************************
     * Gestion des DocumentcommandeAchat
     **************************************************/
    $scope.name = 'BCV';

    $scope.listeDocumentcommandeAchat = [];

    // GetId.designation
    $scope.doc = {
      status: '',
    };
    $scope.showStatus = function (id) {
      $scope.doc.status = id;
      var selected = $filter('filter')($scope.ListTypeDocumentCache, {
        id: $scope.doc.status,
      });
      if ($scope.doc.status && selected.length) {
        return selected[0].designation;
      } else {
        return 'Not Set';
      }
    };

    // ajout d'un DocumentcommandeAchat
    $scope.ajoutDocumentcommandeAchat = function () {
      $scope.documentcommandeAchatInserree = {
        typeDocumentId: '',
        uidDocument: '',
        path: '',
      };
      $scope.listeDocumentcommandeAchat.push(
        $scope.documentcommandeAchatInserree
      );
    };

    // Enregistrer DocumentcommandeAchat
    $scope.saveDocumentcommandeAchat = function (
      dataDocumentcommandeAchat,
      id
    ) {
      console.log('**SAVE DOC ' + dataDocumentcommandeAchat);
      $scope.deleteValue = 'non';
      angular.extend(dataDocumentcommandeAchat, {
        id: id,
      });
    };

    // Supprimer DocumentcommandeAchat
    $scope.removeDocumentcommandeAchat = function (index) {
      $scope.listeDocumentcommandeAchat.splice(index, 1);
    };
    /** Fin de gestion des DocumentcommandeAchat */
    /** Fin de gestion des DocumentcommandeAchat */
    /***************************************************
     * Gestion de la Grid Bon de Commande de Vente
     **************************************************/
    $scope.typeAlert = 3;

    $scope.colDefs = [];
    $scope.$watch('myDatacommandeAchat', function () {
      $scope.colDefs = [
        /*	{
										field : '',
										headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
										cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
										width : '3%'
									},*/
        {
          field: 'reference',
          displayName: 'Réf.BC',
          	width : '20%'
        },
        {
          field: 'partieIntersseAbbreviation',
          displayName: 'Fournisseur',
          width : '20%'
        },
        {
          field: 'dateIntroduction',
          displayName: 'Date Cmd.',
          cellFilter: "date:'dd-MM-yyyy'",
          	width : '10%'
        },
        {
          field: 'dateLivraison',
          displayName: 'Date Liv.',
          cellFilter: "date:'dd-MM-yyyy'",
          	width : '10%'
        },
        {
          field: 'quantite',
          displayName: 'Quantite',
          	width : '5%'
        },
        {
          field: 'prixTotal',
          displayName: 'Montant HT',
          	width:'7%'
        },
        {
          field: 'montantTaxe',
          displayName: 'Montant Taxe',
          	width:'7%'
        },
        {
          field: 'montantTTC',
          displayName: 'Montant TTC',
          	width:'7%'
        },

        {
          field: '',
          	width : '10%',
          cellTemplate:
          `<div class="ms-CommandButton float-right"> 
            <button class="ms-CommandButton-button ms-CommandButton-Gpro" ng-click="modifierOuCreercommandeAchat(row.entity.id)"> 
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span> 
            </button> 
            <button class="ms-CommandButton-button"  ng-click="showPopupDelete(18)" permission="['Achat_Delete']"> 
            <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span> 
            </button> 
            	</div> `,

          // '<div class="buttons">'
          // 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreercommandeAchat()"><i class="fa fa-fw fa-pencil"></i></button>'
          // 		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(18)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
      $scope.myDatacommandeAchat = pagedData;
      $scope.totalServerItems = data.length;
      if (!$scope.$$phase) {
        $scope.$apply();
      }
      $scope.isLoading = false;
    };

    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
      setTimeout(function () {
        var data;
        var commandeAchatCourante = $scope.commandeAchatCourante;
        if (searchText) {
          var ft = searchText.toLowerCase();
          $scope.isLoading = true;
          $http
            .post(
              UrlAtelier + '/commandeAchat/rechercheMulticritere',
              commandeAchatCourante
            )
            .success(function (largeLoad) {
              $scope.myDatacommandeAchat = largeLoad.commandeAchatValues.filter(
                function (item) {
                  return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                }
              );
              $scope.setPagingData(data, page, pageSize);
            });
        } else {
          $scope.isLoading = true;

          $http
            .post(
              UrlAtelier + '/commandeAchat/rechercheMulticritere',
              commandeAchatCourante
            )
            .success(function (largeLoad) {
              console.log('largeLoad' + JSON.stringify(largeLoad, null, ' '));
              $scope.setPagingData(largeLoad.listCommandeAchat, page, pageSize);
            });
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
      data: 'myDatacommandeAchat',
      columnDefs: 'colDefs',
      enableColumnResize: true,
      enablePaging: true,
      showFooter: true,
      enableHighlighting: true,
      totalServerItems: 'totalServerItems',
      pagingOptions: $scope.pagingOptions,
      selectedItems: $scope.selectedRows,
      filterOptions: $scope.filterOptions,
    };

    /** Fin de gestion des Grids Vente BC */
  },
]);
/*
 * .filter('showProduitFilterBS', function() { return function(listeProduit, id) {
 * var listeProduitFiltre = []; console.log("IdProduitt= "+id.id);
 * console.log("listeProduit "+ JSON.stringify(listeProduit, null, " "));
 * angular.forEach(listeProduit, function(produit, key){
 *
 * if(produit.id == id.id){ console.log(produit.id +" == "+ id.id);
 * listeProduitFiltre.push(produit); }
 *
 * }); // console.log(" ** listeProduitFiltre "+
 * JSON.stringify(listeProduitFiltre, null, " ")); return listeProduitFiltre; }; })
 *
 * .filter('filterSousFamilleBS', function() { return function(listeSousFamille,
 * id) { var listeSousFamilleFiltre = []; // console.log("IdSousFamille=
 * "+id.id); // console.log("listeSousFamille "+
 * JSON.stringify(listeSousFamille, null, " "));
 * angular.forEach(listeSousFamille, function(sousFamille, key){
 *
 * if(sousFamille.id == id.id){ console.log(sousFamille.id +" == "+ id.id);
 * listeSousFamilleFiltre.push(sousFamille); }
 *
 * }); // console.log(" ** listeSousFamilleFiltre "+
 * JSON.stringify(listeSousFamilleFiltre, null, " ")); return
 * listeSousFamilleFiltre; }; });
 */
