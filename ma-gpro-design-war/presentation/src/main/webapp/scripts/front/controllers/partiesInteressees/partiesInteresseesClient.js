'use strict';
angular
  .module('gpro.partieInteresseeClient', ['ngResource'])
  .controller(
    'PartieInteresseeClientController',

    [
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
        // Déclaration des variables globales utilisées
        $log.info('=============PartieInteressee===============');
        var data;
        $scope.displayMode = 'list';
        $scope.displayMenu = 'pi';
        $scope.listeRepresentant = [];
        $scope.listeDocument = [];
        $scope.ListCategoriePICache = [];
        $scope.ListTypePICache = [];
        $scope.ListFamillePICache = [];
        $scope.ListSitePICache = [];
        $scope.listeRegionCache = [];
        $scope.listeBanque = [];
        $scope.ListActifPI = [
          { actif: true, designation: 'oui' },
          { actif: false, designation: 'non' },
        ];
        $scope.partieInteresseeCourante = {};
        $scope.resultatRecherche = $scope.listePartieInteressee;


     	$scope.ListeDevise = [];




        /***************************************************
         * Gestion de la Menu PI
         **************************************************/


		$scope.ListeDevise = function () {
					$http.get(UrlCommun + '/devise/all').success(function (dataDevise) {
						$scope.ListeDevise = dataDevise;
					});
				};



				$scope.ListeDevise();

         
        $scope.changeTaPartieInteresse = function () {
          $scope.displayMenu = 'pi';
        };
        $scope.changeTaElementBase = function () {
          $scope.displayMenu = 'eb';
        };
        $scope.changeTaGestionCommerciale = function () {
          $scope.displayMenu = 'gc';
        };

        $log.info('***Sous Menu Client ***');
        $scope.downloadAllExcel = function (partieInteresseeCourante) {
          var url;

          url =
            UrlCommun +
            '/fiches/listpi?vReference=' +
            partieInteresseeCourante.vReference +
            '&vRaisonSociale=' +
            partieInteresseeCourante.vRaisonSociale +
            '&vCategoriePartieInteressee=' +
            partieInteresseeCourante.vCategoriePartieInteressee +
            '&groupeClientId=' +
            partieInteresseeCourante.groupeClientId +
            '&vTypePartieInteressee=' +
            partieInteresseeCourante.vTypePartieInteressee +
            '&actif=' +
            partieInteresseeCourante.actif +
            '&vFamillePartieInteressee=1'  +

            '&nature=' +
            partieInteresseeCourante.nature
            
            ;

          $log.debug('-- URL--- :' + url);

          // $scope.download = function (url) {

          var a = document.createElement('a');
          document.body.appendChild(a);
          downloadService.download(url).then(function (result) {
            var heasersFileName = result.headers(['content-disposition']).substring(17);
            var fileName = heasersFileName.split('.');

            fileName[0] = 'Liste_Client_' + formattedDate(new Date());


            var typeFile = result.headers(['content-type']);
            var file = new Blob([result.data], { type: typeFile });
            var fileURL = window.URL.createObjectURL(file);
            if (typeFile == 'application/vnd.ms-excel') {

              a.href = fileURL;
              a.download = fileName[0];
              // $window.open(fileURL)
              a.click();

            } else {

              a.href = fileURL;
              a.download = fileName[0];
              $window.open(fileURL)
              a.click();

            }


            function formattedDate(date) {
              var d = new Date(date),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();

              if (month.length < 2) month = '0' + month;
              if (day.length < 2) day = '0' + day;
              return [year, month, day].join('-');
            }


          });
          //};

          // downloadService.download(url).then(
          //   function (success) {
          //     // $log.debug('success : ' + success);
          //   },
          //   function (error) {
          //     // $log.debug('error : ' + error);
          //   }
          // );
        };

        // Liste des CategorieCache
        $scope.listGroupeClient = function () {
          $http
            .get(UrlCommun + '/groupeClient/all')
            .success(function (dataCategorieCache) {
              //	$log.debug("listeCategorie : "+dataCategorieCache.length);
              $scope.listGroupeClient = dataCategorieCache;
            });
        };
        $scope.listGroupeClient();

        /***************************************************
         * Gestion Cache
         */
        // Liste des DeviseCache
        $scope.ListDeviseCache = function () {
          $http
            .get(UrlCommun + '/gestionnaireCache/listeDeviseCache')
            .success(function (dataDeviseCache) {
              $log.debug('listeDeviseCache :' + dataDeviseCache.length);
              $scope.ListDeviseCache = dataDeviseCache;
            });
        };
        // Liste des TypeDocs
        $scope.listeTypeDocumentCache = function () {
          $http
            .get(
              UrlCommun
              + "/gestionnaireCache/listeTypeDocumentCache")
            .success(
              function (dataTypeDocCache) {


                $scope.listeTypeDocumentCache = dataTypeDocCache.filter(e => e.module === 'PI_CLIENT');

              });
        }
        // Liste des regions
        $scope.listeRegionCache = function () {
          //TODO cache
          $http
            .get(UrlCommun + '/region/getAll')
            .success(function (dataRegionCache) {
              $log.debug('listeRegionCache : ' + dataRegionCache.length);
              $scope.listeRegionCache = dataRegionCache;
            });
        };


            // get Liste des compte comptable PI
            $scope.getListeCompteComptable = function () {
              //TODO cache
              $http
                .get(UrlCommun + '/compteComptablePI/all')
                .success(function (dataRegionCache) {
                  $log.debug('compte comptable : ' + dataRegionCache.length);
                  $scope.listeCompteComptable = dataRegionCache;
                });
            };


            $scope.getListeBanquePI = function () {
              $http.get(UrlCommun+"/banquePI/all").success(function (data) {
                $log.debug("listeCathegorie : "+data.length);
                $scope.listeBanque = data;
              });
            }

            $scope.getListeBanquePI();

      //  $scope.getListeCompteComptable();
        $scope.listeRegionCache();
        $scope.ListDeviseCache();
        $scope.listeTypeDocumentCache();
        /***************************************************
         * Gestion de la PI
         **************************************************/
        $scope.deleteValue = 'oui';
        //Annuler Ajout
        $scope.cancelAddPartieInteressee = function (
          rowform,
          index,
          id,
          designation,
          liste
        ) {
          //$log.debug("* Designation "+liste[0].designation);
          if (angular.isDefined(id)) {
            //  $log.debug("DEF ID");
            $scope.deleteValue = 'non';
            rowform.$cancel();
            //  $log.debug("CANCEL");
          } else {
            // $log.debug("UND ID  "+id);
            if (designation == '') {
              $scope.deleteValue == 'oui';
              $log.debug.log('Designation : ' + designation);
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

        //Variale de Pagination de la grid
        $scope.pagingOptions = {
          pageSizes: [5, 10, 13],
          pageSize: 13,
          currentPage: 1,
        };

        // Lister les parties interessées
        $scope.listePartieInteressee = function () {
          $http
            .get(UrlCommun + '/partieInteressee/all')
            .success(function (data) {
              $log.debug('listePartieInteressee : ' + data.length);
              $scope.partieInteresssees = data;
              $scope.myData = data;
              //data, page,pageSize
              $scope.setPagingData(
                $scope.myData,
                $scope.pagingOptions.currentPage,
                $scope.pagingOptions.pageSize
              );
            });
        };

        //Init data list
        $scope.initMyData = [];

        // Rechercher PI
        $scope.rechercherPartieInteressee = function (
          partieInteresseeCourante
        ) {
          /** permet de récuperer seulement les PI de famille client */
          partieInteresseeCourante.vFamillePartieInteressee = 1;
          $log.debug('recherche en cours ..');
          $http
            .post(
              UrlCommun + '/partieInteressee/recherchePIMulticritere',
              partieInteresseeCourante
            )
            .success(function (resultat) {
              $log.debug(
                'listePartieInteresseeRecherchée: ' +
                resultat.partieInteresseValues.length
              );
              $scope.myData = resultat.partieInteresseValues;
              $scope.initMyData = $scope.myData;

              //data, page,pageSize
              $scope.setPagingData(
                $scope.myData,
                $scope.pagingOptions.currentPage,
                $scope.pagingOptions.pageSize
              );

              $scope.displayMode = 'rechercher';
              $scope.recherchePartieIntereseeFormCritere.$setPristine();
            });
        };

        //Init liste PI (ng-grid)
        $scope.rechercherPartieInteressee($scope.partieInteresseeCourante);
		
		
		
		
		
		$scope.annulerAjoutRapide = function () {
					
						
					// interface en mode : list
					$scope.displayMode = "list";
					
				}
		
		
		
		
		
		
		
		

        // ** Ajout Partie Interesse **
        $scope.AffectationPartieInteressee = function (partieInteressee) {
          $scope.partieInteresseeCourante = {
            dateIntroduction: new Date(),
            actif: true,
           nature :'Client'
          };
       
          $scope.creationPartieInteressee.$setPristine();
          $scope.listeRepresentant = [];
          $scope.listeDocument = [];

          $http
            .get(UrlCommun + '/partieInteressee/getCurrentReferenceByFamille:1')
            .success(function (datagetPI) {
              $scope.partieInteresseeCourante.reference = datagetPI;

              $scope.partieInteresseeCourante.refAvantChangement = datagetPI;
            });

          $scope.displayMode = 'edit';
        };

        // Ajout et Modification Partie Interessee
        $scope.modifierOuCreerPartieInteressee = function () {
          var index = this.row.rowIndex;
          // getArticle
          $http
            .get(
              UrlCommun + '/partieInteressee/getId:' + $scope.myData[index].id
            )
            .success(function (datagetPI) {
              $scope.listeRepresentant = datagetPI.representants;
              $scope.listeDocument = datagetPI.documents;
              $scope.myData[index].representants = $scope.listeRepresentant;
              $scope.myData[index].documents = $scope.listeDocument;
              //		$scope.partieInteresseeCourante.actif=datagetPI.actif.toString();
              //													if(datagetPI.actif == true){
              //														$scope.partieInteresseeCourante.actif='oui';
              //														$scope.ListActifPI.actif=true;
              //													}else{
              //														$scope.partieInteresseeCourante.actif='non';
              //														$scope.ListActifPI.actif=false;
              //													}
              $log.debug(
                '$scope.myData[index].actif : ' + $scope.myData[index].actif
              );
            });

          if ($scope.myData[index].dateIntroduction == null) {

            $scope.partieInteresseeCourante = $scope.myData[index]
              ? angular.copy($scope.myData[index])
              : {};

          } else {
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
            ] = dateTimeFormat.formatToParts($scope.myData[index].dateIntroduction);
            $scope.dateParEdition = `${year}-${month}-${day}`;
           
            $scope.partieInteresseeCourante = Object.assign($scope.myData[index], { dateIntroduction: $scope.dateParEdition })
              //  $scope.partieInteresseeCourante = $scope.myData[index]
              ? angular.copy($scope.myData[index])
              : {};

        


          }




          $scope.displayMode = 'edit';
        };

        // Sauvegarder PI
        $scope.sauvegarderAjout = function (partieInteressee) {
          if (angular.isDefined(partieInteressee.id)) {
            $scope.updatePartieInteressee(partieInteressee);
          } else {
            $scope.creerPartieInteressee(partieInteressee);
          }
        };
        // Mise à jour de la partie interessée
        $scope.updatePartieInteressee = function (partieInteressee) {
          partieInteressee.representants = $scope.listeRepresentant;
          partieInteressee.documents = $scope.listeDocument;
          $http
            .post(
              UrlCommun + '/partieInteressee/modifierPartieInteressee',
              partieInteressee
            )
            .success(function (partieInteresseeModifiee) {
              /*for (var i = 0; i < $scope.listePartieInteressee.length; i++) {
									if ($scope.myData[i].id == partieInteresseeModifiee.id) {
										$scope.myData[i] = partieInteresseeModifiee;
										break;
									}
								}*/
              $scope.rechercherPartieInteressee({});
              $http.get(UrlCommun + '/gestionnaireCache/reloadReferentiel');
              $scope.displayMode = 'list';
            });
          $scope.recherchePartieIntereseeFormCritere.$setPristine();
          $scope.creationPartieInteressee.$setPristine();
          $scope.partieInteresseeCourante = {};
        };
        // Création PI
        $scope.creerPartieInteressee = function (partieInteressee) {
          partieInteressee.representants = $scope.listeRepresentant;
          partieInteressee.documents = $scope.listeDocument;
          /** permet de client un PI de famille client **/
          partieInteressee.famillePartieInteressee = 1;
          $http
            .post(
              UrlCommun + '/partieInteressee/creerPartieInteressee',
              partieInteressee
            )
            .success(function (newPartieInteressee) {
              /*$scope.myData
										.push(newPartieInteressee);*/
              $scope.displayMode = 'list';
              $scope.rechercherPartieInteressee({});
              $http.get(UrlCommun + '/gestionnaireCache/reloadReferentiel');

              var soldeClient = {
                partIntId: newPartieInteressee,
                dateIntroduction: new Date(),
                bloque: false,
                soldeInitial: 0,
                seuil: 0,
                chiffreAffaireTmp: 0,
                reglementTmp: 0,
              };

              $http
                .post(UrlAtelier + '/soldeClient/create', soldeClient)
                .success(function (newSoldeClient) { });
            });
          $scope.recherchePartieIntereseeFormCritere.$setPristine();
          $scope.creationPartieInteressee.$setPristine();
          $scope.partieInteresseeCourante = {};
        };

        // Annulation de l'ajout
        $scope.annulerAjout = function () {
          $scope.recherchePartieIntereseeFormCritere.$setPristine();
          $scope.partieInteresseeCourante = {};
          $scope.rechercherPartieInteressee($scope.partieInteresseeCourante);
          $scope.listeRepresentant = [];
          $scope.listeDocument = [];
          $scope.displayMode = 'list';
        };

        // Suppression PI
        $scope.supprimerPartieInteressee = function () {
          $log.debug('DELETE ..' + $scope.myData[$scope.index].id);
          $http({
            method: 'DELETE',
            url:
              UrlCommun +
              '/partieInteressee/supprimerPartieInteressee:' +
              $scope.myData[$scope.index].id,
          }).success(function () {
            $log.debug('Success Delete');
            $scope.myData.splice($scope.index, 1);

            $http.get(UrlCommun + '/gestionnaireCache/reloadReferentiel');
          });
          $scope.closePopupDelete();
          $scope.rechercherPartieInteressee({});
        };
        //	$scope.listePartieInteressee();

        /** Fin de gestion de la partie Interessée */

        /***************************************************
         * Gestion des representants
         **************************************************/
        // ajout d'un Representant
        $scope.ajoutRepresentant = function () {
          $scope.representantInserree = {
            nom: '',
            fonction: null,
            email: null,
          };
          $scope.listeRepresentant.push($scope.representantInserree);
        };

        // Enregistrer Representant
        $scope.saveRepresentant = function (data, id) {
          $scope.deleteValue = 'non';
          var regExpValidEmail = new RegExp(
            '^[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}$',
            'gi'
          );
          // $scope.Representant not updated yet
          if (!data.email || !data.email.match(regExpValidEmail)) {
            $scope.showPopupDelete(2);
            $scope.rowform.$cancel();
            return;
          } else {
            angular.extend(data, {
              id: id,
            });
          }
        };

        // Supprimer representant
        $scope.removeRepresentant = function (index) {
          $scope.listeRepresentant.splice(index, 1);
        };
        /** Fin de gestion des representants */

        /***************************************************
         * Gestion des Grids de recherche
         **************************************************/
        $scope.colDefs = [];
        $scope.$watch('myData', function () {
          $scope.colDefs = [


         /*    {
              field: 'dateIntroduction',
              displayName: 'Date Introd.',
              width: '9%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
              cellFilter: "date: 'yyyy-MM-dd'"
            }, */
            {
              field: 'reference',
              displayName: 'Référence',
              width: '8%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            },
            {
              field: 'raisonSociale',
              displayName: 'Raison Sociale',
              width: '20%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            },

            {
              field: 'matriculeFiscal',
              displayName: 'Matricule Fisc.',
              width: '12%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            },

            /* {
               field: 'famillePartieInteresseeDesignation',
               displayName: 'Famille',
               //	width: '10%',
               fontfamily: 'Poppins, Helvetica, sans-serif',
             },*/
            /*   {
                field: 'categoriePartieInteresseeDesignation',
                displayName: 'Catégorie',
                //	width: '12%',
                fontfamily: 'Poppins, Helvetica, sans-serif',
              }, 
  
              {
                field: 'groupeClientDesignation',
                displayName: 'Groupe',
                //	width: '12%',
                fontfamily: 'Poppins, Helvetica, sans-serif',
              },*/

         /*    {
              field: 'regionDesignation',
              displayName: 'Ville',
              width: '7%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            }, */
            {
              field: 'representants[0].nom',
              displayName: 'Vis à vis',
              width: '7%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            },
            {
              field: 'telephone',
              displayName: 'Téléphone',
              width: '10%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            },

            {
              field: 'telephoneMobile',
              displayName: 'Téléphone Mobile',
              width: '10%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            },
            {
              field: 'email',
              displayName: 'Email',
              width: '20%',
              fontfamily: 'Poppins, Helvetica, sans-serif',
            },

            // {
              // field: 'compteComptablePartieInteresseeDesignation',
              // displayName: 'Compte Comptable',
              // width: '8%',
              // fontfamily: 'Poppins, Helvetica, sans-serif',
            // },
            // {
              // field: 'nature',
              // displayName: 'Nature',
              // width: '8%',
              // fontfamily: 'Poppins, Helvetica, sans-serif',
            // },


            {
              field: '',
             	width: '10%',
              //fontfamily: 'Poppins, Helvetica, sans-serif',
              cellTemplate:
                `<div class="ms-CommandButton float-right">
               <button class="ms-CommandButton-button ms-CommandButton-Gpro " ng-click="modifierOuCreerPartieInteressee()">
               <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
               </button>
               <button class="ms-CommandButton-button"  ng-click="showPopupDelete(1)"  permission="['Client_Delete']">
                <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
                </button>
                	</div> `,
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
            if (
              newVal !== oldVal &&
              newVal.currentPage !== oldVal.currentPage
            ) {
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
          enableHighlighting: true,
          enableColumnResize: true,
          totalServerItems: 'totalServerItems',
          pagingOptions: $scope.pagingOptions,
          selectedItems: $scope.selectedRows,
          filterOptions: $scope.filterOptions,
        };
        /** Fin de gestion des Grids de la partie Interesse */

        /***************************************************
         * Gestion des Documents
         **************************************************/
        /** ***Liste desVariables ****/
        $scope.listeDocument = [];
        $scope.listeTypeDoc = [];
        $scope.name = 'CLIENT';
        // GetId.designation
        $scope.type = {
          status: '',
        };
        $scope.showStatus = function (id) {
          var designation = '';
          for (var i = 0; i < $scope.listeTypeDocumentCache.length; i++) {
            // console.log(
            //   'listeTypeDocumentCache[i]=' + $scope.listeTypeDocumentCache[i].id
            // );
            if (
              $scope.listeTypeDocumentCache[i].id == id &&
              $scope.listeTypeDocumentCache[i].module == 'PI_CLIENT'
            ) {
              designation = $scope.listeTypeDocumentCache[i].designation;
              //console.log("designation type doc :" +designation);
              return designation;
            }
            /*else{
							return "vide";
						}*/
          }

          /*$scope.type.status = id;
					console.log(" $scope.listeTypeDocumentCache: "+ $scope.listeTypeDocumentCache);
					var selected = $filter('filterProduit')(
						 $scope.listeTypeDocumentCache, {
							id : $scope.type.status
						 });
					 if ($scope.type.status && selected.length) {
						return selected[0].designation;
					 }else {
											return "Not Set";
									 }
					*/
          /*$scope.type.status = id;
					console.log(" $scope.listeTypeDocumentCache: "+ $scope.listeTypeDocumentCache);
					var selected = $filter('filterProduit')(
						 $scope.listeTypeDocumentCache, {
							id : $scope.type.status
						 });
					 if ($scope.type.status && selected.length) {
						return selected[0].designation;
					 }else {
											return "Not Set";
									 }
						 */
        };
        // ajout d'un Document Partie Interesee
        $scope.ajoutDocPI = function () {
          $scope.DocPartieInteresseInserree = {
            typeDocument: '',
            vUUIDDocument: '',
            path: '',
          };
          $scope.listeDocument.push($scope.DocPartieInteresseInserree);
        };

        // Enregistrer Document Partie Interesee
        $scope.saveDocPI = function (dataDocPI, id) {
          $scope.deleteValue = 'non';
          angular.extend(dataDocPI, {
            id: id,
          });
          console.log(
            'Success Save Document in Partie Interessee ' + dataDocPI.id
          );
        };
        // Supprimer Document
        $scope.removeDocPi = function (index) {
          $scope.listeDocument.splice(index, 1);
        };

        //Download Document
        $scope.download = function (uuid) {
          var url = UrlCommun + '/gestionnaireDocument/document/' + uuid;

          //  $scope.download = function (url) {
          //   var fileName = 'file_name.pdf'; 
          //   var a = document.createElement('a');
          //   document.body.appendChild(a);
          //   downloadService.download(url).then(function (result) {
          //     console.log('kkkkkkk', result);
          //     var file = new Blob([result.data], {
          //       type: 'application/vnd.ms-excel',
          //     });
          //     var fileURL = window.URL.createObjectURL(file);
          //     a.href = fileURL;
          //     a.download = fileName;
          //     a.click();
          //   });
          // };

          downloadService.download(url).then(
            function (success) {
              $log.debug('success : ' + success);
            },
            function (error) {
              $log.debug('error : ' + error);
            }
          );
        };

        /***************************************************
         * Gestion de Cache des Referentiels *
         **************************************************/

        // Liste des CategorieCache
        $scope.listCategoriePICache = function () {
          $http
            .get(UrlCommun + '/gestionnaireCache/listeCategoriePICache')
            .success(function (dataCategorieCache) {
              $log.debug('listeCategorie : ' + dataCategorieCache.length);
              $scope.ListCategoriePICache = dataCategorieCache;
            });
        };
        // Liste des FamilleCache
        $scope.listFamillePICache = function () {
          $http
            .get(UrlCommun + '/gestionnaireCache/listeFamillePICache')
            .success(function (dataFamilleCache) {
              $log.debug('listeFamillePICache : ' + dataFamilleCache.length);
              $scope.ListFamillePICache = dataFamilleCache;
            });
        };
        // Liste des TypeCache
        $scope.listTypePICache = function () {
          $http
            .get(UrlCommun + '/gestionnaireCache/listeTypePICache')
            .success(function (dataTypeCache) {
              $log.debug('listeTypePICache : ' + dataTypeCache.length);
              $scope.ListTypePICache = dataTypeCache;
            });
        };
        $scope.listSitePICache = function () {
          $http
            .get(
              UrlCommun + '/gestionnaireCache/listeSitePartieInteresseeCache'
            )
            .success(function (dataSiteCache) {
              $log.debug('listeSitePICache : ' + dataSiteCache.length);
              $scope.ListSitePICache = dataSiteCache;
            });
        };
        $scope.listCategoriePICache();
        $scope.listFamillePICache();
        $scope.listTypePICache();
        $scope.listSitePICache();
      },
    ]
  )
  /*.filter('filterProduit', function() {
				return function(liste, id) {
				 var listeFiltre = [];
				// $log.debug("IdSousFamille=  "+id.id);
				// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
				 angular.forEach(listeSousFamille, function(sousFamille, key){
					
					if(liste.id == id.id){
						//$log.debug(liste.id +" == "+ id.id);
						listeFiltre.push(liste);
					}
						
				 });
				// $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
				 return listeFiltre;
				};
			})*/
  //new filter
  .filter('filterProduit', function () {
    return function (liste, id) {
      var listeFiltre = [];

      angular.forEach(liste, function (elementListe, key) {
        if (elementListe.id == id.id) {
          listeFiltre.push(elementListe);
        }
      });

      return listeFiltre;
    };
  })

  .controller('DatepickerDController', [
    '$scope',
    function ($scope) {
      $scope.maxToDay = new Date();
      //							// date piker defit
      //							$scope.today = function() {
      //								$scope.partieInteresseeCourante.dateIntroduction = new Date();
      //							};
      //							$scope.today();
      //							$scope.clear = function() {
      //								$scope.partieInteresseeCourante.dateIntroduction = null;
      //							};
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
      $scope.initDate = new Date('20-08-2015');
      $scope.formats = [
        'yyyy-MM-dd',
        'dd/MM/yyyy',
        'dd.MM.yyyy',
        'shortDate',
      ];
      $scope.format = $scope.formats[0];

    },
  ]);
