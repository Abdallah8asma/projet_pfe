angular
.module('gpro.factureAvoirVente', [ "ngResource" ])
.controller(
		'factureAvoirVenteController',
		[
		 '$scope',
		 '$filter',
		 '$http',
		 '$log',
		 'downloadService',
		 'UrlCommun',
		 'UrlAtelier',
		 '$window',
		 function($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier,$window) {
			 $log.info("=========Facture Avoir========");
			 var data;
			 $scope.displayMode = "list";
			 // bouton pdf hide
			 $scope.modePdf = "notActive";
			 $scope.factureVenteCourant = {"type" : "Avoir" ,"avecRetourStock":false};
			 //mode list activé
			 $scope.displayMode = "list";
			 $scope.listeBonSortie = [];	
			 $scope.listDetFactureVente = [];
			 $scope.listDetFactureVentePRBL = [];
			 $scope.idFactureVente ='';
			 //liste des ReferencesBS
			 $scope.tagReferenceBLivList = [];
			 $scope.listTaxeFacture = [];

			 $scope.ListeDevise=[];


			 //Tableau de Taxe Prédefini 
			 $scope.listTaxeFactureInit = [
			                               
										   
										   
						{//FODEC 
							taxeId: 1,
							pourcentage: 1,
							montant: '',
						},

						{// TVA
							taxeId: 2,
							pourcentage: 19,
							montant: '',
						},
					/*	{// TVA7
							taxeId: 4,
							pourcentage: 7,
							montant: '',
						},
						{// TVA13
							taxeId: 5,
							pourcentage: 13,
							montant: '',
						},*/
						{// TIMBRE
							taxeId: 3,
							pourcentage: '',
							montant: 0.600,
						}
										   
										   ];
			 //modeValider
			 $scope.modeValider = "notActif";
			 //init deleteValue pour cancelAddBLVente
			 $scope.deleteValue = "oui";
			 
			 $scope.hiddenNotif ="true";
				
				$scope.traitementEnCours = "false";
				
				$scope.traitementEnCoursGenerer = "false";

				
				/**************************************
				 * Notification *
				 **************************************/
				$scope.showNotif = function(){
					$scope.hiddenNotif ="false";
				}
										
				$scope.closeNotif = function(){
					$scope.hiddenNotif ="true";
				}
				
			 
			 /**************************************************
			  *Gestion des DropListe & cache 
			  **************************************************/
			 $scope.listePartieInteresseeCache = function() {
				 $http
				 .get(UrlCommun + "/gestionnaireCache/listePartieInteresseeCache")
				 .success(
						 function(dataPartieInteressee) {
							 $scope.listePartieInteresseeCache = dataPartieInteressee;
							 $log.debug("listePartieInteresseeCache : "+dataPartieInteressee.length)

						 });
			 }

			 //TODO: Liste des Taxes A remplacer par une liste extraite de la cache 
			 $scope.listeTaxes = function() {
				 $http
				 .get(UrlAtelier + "/taxe/getAll")
				 .success(
						 function(dataTaxe) {

							 $scope.listeTaxes = dataTaxe;
						 });
			 }

			 //TODO: Liste des Marches A remplacer par une liste extraite de la cache 
			/* $scope.listeMarche = function() {
				 $http
				 .get(UrlAtelier + "/marche/getAll")
				 .success(
						 function(dataMarche) {

							 $scope.listeMarche = dataMarche;
							 $log.debug("------Vente Js: listeMarche : "+ dataMarche.length);
						 });
			 }*/
			 
			 //TODO: Liste des modePaiement A remplacer par une liste extraite de la cache 
			 $scope.listeModePaiement = function() {
				 $http
				 .get(UrlAtelier + "/gestionnaireLogistiqueCache/listeModePaiementCache")
				 .success(
						 function(dataPaiement) {

							 $scope.listeModePaiement = dataPaiement;
						 });
			 }
			 
			 $scope.listeRaisonFacture = function() {
				 $http
				 .get(UrlAtelier + "/raisonFacture/all")
				 .success(
						 function(dataTaxe) {

							 $scope.listeRaisonFacture = dataTaxe;
						 });
			 }
			 
				// Liste des listGroupeClient
				$scope.listGroupeClient = function() {
					$http
							.get(
									UrlCommun
											+ "/groupeClient/all")
							.success(
									function(dataCategorieCache) {
									//	$log.debug("listeCategorie : "+dataCategorieCache.length);
										$scope.listGroupeClient = dataCategorieCache;

									});
				}

						     // Liste des Devises
							 $scope.ListeDevise = function () {
								$http.get(UrlCommun + '/devise/all').success(function (dataDevise) {
								 $scope.ListeDevise = dataDevise;
										 });
									 };
									 
		 
									 
				$scope.ListeDevise();
				
				
				$scope.listGroupeClient();
			 
			 $scope.listeRaisonFacture();
			 
			 $scope.listePartieInteresseeCache();
			 $scope.listeTaxes();
			 //$scope.listeMarche();
			 $scope.listeModePaiement();

			 /***************************************************
			  * Conversion des Ids/Designation
			  **************************************************/
			 //TypeTaxe
			 $scope.typeTaxeId = {

					 status : ''
			 };
			 $scope.showTaxe = function(id){
				 $scope.typeTaxeId.status = id;
				 var selected = $filter('filter')(
						 $scope.listeTaxes, {
							 id : $scope.typeTaxeId.status
						 });
				 if ($scope.typeTaxeId.status && selected.length) {
					 return selected[0].designation;
				 }else{
					 return "Not Set";
				 }

			 }

			 /**************************************************
			  *Gestion de suppression d'une ligne d'un tableau
			  *************************************************/

			 // Annuler Ajout
			 $scope.cancelAddBLVente = function(rowform, index, id, designation, liste) {
				 $log.debug("cancelAddBLVente");
				 $log.debug("* Designation"+liste[0].designation);
				 $log.debug("* ID"+id);
				 if (angular.isDefined(id)) {

					 $log.debug("DEF ID");
					 $scope.deleteValue = "non";
					 rowform.$cancel();
					 $log.debug("CANCEL");
				 } else {
					 $log.debug("ID  UNDEFINED " );
					 if (designation == "") {
						 $scope.deleteValue == "oui"
							 $log.debug("Designation : "+ designation);
						 $log.debug("$scope.deleteValueOUI : "+ $scope.deleteValue);
						 liste.splice(index, 1);
						 rowform.$cancel();
						 $log.debug("DELETE")
					 } else {
						 $log.debug("Designation :"+ designation);
						 $log.debug("$scope.deleteValueNON : "+ $scope.deleteValue);
						 rowform.$cancel();
						 $log.debug("CANCEL");
					 }
				 }
				 $scope.deleteValue = "oui";
			 }
			 /**************************************************
			  *Gestion des TaxeBLiv
			  **************************************************/
			 /** Mode Creation **/
			 $scope.addTaxeBLivInit = function() {
				 $scope.inserted = {
						 taxeId: '',
						 pourcentage: '',
						 montant: '',
				 };
				 $scope.listTaxeFactureInit.push($scope.inserted);
			 };

			 // remove TaxeBLivInit
			 $scope.removeTaxeBLivInit = function(index) {
				 $scope.listTaxeFactureInit.splice(index, 1);
			 };

			 /** Mode Modification **/
			 // add TaxeBLiv, /_!_\  'produitId' DOIT etre TJR UNDEFINED dans l'objet ajouté pour que cancelBLVente fonctionne correctement
			 $scope.addTaxeBLiv = function() {
				 $scope.inserted = {
						 taxeId: '',
						 pourcentage: '',
						 montant: '',
				 };
				 $scope.listTaxeFacture.push($scope.inserted);
			 };

			 //saveTaxeBLiv
			 $scope.saveTaxeBLiv = function(data, id) {
				 //cancelAdd variable
				 $scope.deleteValue = "non";
				 $log.debug("$scope.deleteValue :" + $scope.deleteValue);
				 //$scope.user not updated yet
				 angular.extend(data, {id: id});
			 };

			 // remove TaxeBLiv
			 $scope.removeTaxeBLiv = function(index) {
				 $scope.listTaxeFacture.splice(index, 1);
			 };

			 $scope.taxeIdRemove = [1,2];
			 // Filtre de la dropList des taxes
			 $scope.filterTaxes = function () {

				 return function (item) {
					 var condition = false;

					 for(var k=0; k<$scope.taxeIdRemove.length; k++){
						 if(item.id != $scope.taxeIdRemove[k]){
							 condition = true
						 }else{
							 condition = false;
							 break;
						 }
					 }

					 if (condition==true){
						 return true;
					 }
					 return false;
				 };
			 };
			 /**************************************************
			  *Gestion des ProduitBS
			  **************************************************/
			$scope.listReferenceBL = [];

			  $scope.getAvailableRefBLByClient = function (idClient) {
				$scope.listReferenceBL = [];

				if(angular.isDefined(idClient)){
					if(idClient != null){
						$http
						.get(
								UrlAtelier
								+ "/facture/getAvailableListFactureRefByClient:"+idClient)
								.success(
										function(resultat) {
											$log.debug("----ResultatListBL "+resultat.length);
											
											angular.forEach(resultat, function(element, key){
												$scope.listReferenceBL.push(element.referenceFacture);
											});

											//$log.debug("listBL : "+resultat.list.length);
											//$log.debug("--listReferenceBL : "+JSON.stringify($scope.listReferenceBL, null, "    "));
			         			});
						}
				 	
					}
		        }
		        
			//champ autoSaisie du champs: referenceBS 
			$scope.select2TaggingOptions = {
					 'multiple': true,
					 'simple_tags': true,
					 'tags': function () {
							 //reload de la liste des RefBS
							 	$scope.listNewReferenceBL = [];
							 	$scope.listNewReferenceBL = $scope.listReferenceBL;

								$log.debug("--OnClicklistNewReferenceBL : "+JSON.stringify($scope.listNewReferenceBL, null, "    "));
								return $scope.listNewReferenceBL;
						    }
					  
			 };

			 //bouton ValiderBS: tagReferenceBLivList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : debugLivraison sous la forme de ref1-ref2-.. 

			 $scope.validerFacture = function(){

				 
				 //idFactureVente: si undefined -> urlValier SANS idFactureVente, sinon -> idFactureVente AVEC idFactureVente
				 $log.debug("Valider : idFactureVente "+ $scope.idFactureVente );
				 //mode Creation
				 if($scope.idFactureVente == ''){
					 $log.debug("Creation: idFactureVente == '' ");
					 //Url Without idBonLivraison
					 var urlValider = UrlAtelier+ "/facture/validateFacture";
					 $log.debug("-- urlValider Sans idFactureVente : "+ urlValider );

					 $http
					 .post(
							 urlValider,$scope.tagReferenceBLivList)
							 .success(
									 function(resultat) {
										 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
										 $scope.modeValider = "actif";
										 //setDateInto = dateLivraison du 1erBS
										 //$scope.factureVenteCourant.date = resultat.dateLivrison;
										 angular.forEach(resultat.listDetFactureVente, function(element, key){
										 	element.id = null;
											$scope.listDetFactureVentePRBL.push(element);
										 });
										 
										 $scope.factureVenteCourant.idDepot = resultat.idDepot;
										 //listDetFactureVentePRBL
										// $log.debug("-- listDetFactureVentePRBL Size : "+ $scope.listDetFactureVentePRBL.length);

										// $log.debug("-- listDetFactureVentePRBL : "+ JSON.stringify($scope.listDetFactureVentePRBL,null,'  '));

									 });

				 }else{
				 	//Mode Modification
					//Url With idFactureVente
					 var urlValider = UrlAtelier+ "/facture/validateFacture?factureVenteId="+$scope.idFactureVente;
					 $log.debug("-- urlValider : "+ urlValider );

					 //Invocation du service Validate qui nous recupere la liste des Produit.
					 $http
					 .post(urlValider,$scope.tagReferenceBLivList)
					 .success(
							 function(resultat) {
								 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
								 $scope.modeValider = "actif";
								//setDateInto = dateLaivraison du 1erBL
								// $scope.factureVenteCourant.date = resultat.dateLivrison;
								 //listDetFactureVente
								 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
								 $scope.factureVenteCourant.idDepot = resultat.idDepot;
								 $log.debug("-- listDetFactureVentePRBL : "+JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );

							 });

				 }

			 }            
			 /*************************************************
			  * Gestion de la table Produit: table DIRECTEMENT 
			  * editable sur le champ 'Remise' seulement.
			  *************************************************/
			 $scope.remiseChanged = function(index){
				 if($scope.idFactureVente ==''){

					 //remiseChangedOnCreation
					 $log.info("remiseChangedOnCreation INDEX Changed "+ index);
					 $log.info("listDetFactureVentePRBL Remise : "+ $scope.listDetFactureVentePRBL[index].remise);

					 $scope.listDetFactureVentePRBL[index].remise = $scope.listDetFactureVentePRBL[index].remise;
					 $scope.listDetFactureVentePRBL[index].quantite = $scope.listDetFactureVentePRBL[index].quantite;
					 $scope.listDetFactureVentePRBL[index].prixUnitaireHT = $scope.listDetFactureVentePRBL[index].prixUnitaireHT;
					 $log.info("-- listDetFactureVente After Remise Change : "+ JSON.stringify($scope.listDetFactureVentePRBL,null,'  '));

				 }else{

					 //remiseChangedOnModification
					 $log.info("remiseChangedOnModification INDEX Changed "+ index);
					 $log.info("listDetFactureVentePRBL Remise : "+ $scope.listDetFactureVentePRBL[index].remise);

					 if($scope.listDetFactureVentePRBL[index].id != null){
						 $log.info("--old-- : "+JSON.stringify($scope.listDetFactureVentePRBL[index],null,"  "));
						 $scope.factureVenteCourant.listDetFactureVente[index].remise = $scope.listDetFactureVentePRBL[index].remise;
						 $scope.factureVenteCourant.listDetFactureVente[index].quantite = $scope.listDetFactureVentePRBL[index].quantite;
						 $scope.factureVenteCourant.listDetFactureVente[index].prixUnitaireHT = $scope.listDetFactureVentePRBL[index].prixUnitaireHT;

					 }else{
						 $log.info("--NEW-- : "+JSON.stringify($scope.listDetFactureVentePRBL[index],null,"  "));
						 $scope.factureVenteCourant.listDetFactureVente.push($scope.listDetFactureVentePRBL[index]);
					 }

					 // $scope.factureVenteCourant.listDetFactureVente[index].remise = $scope.listDetFactureVentePRBL[index].remise;
					 $log.info("-=====- factureVenteCourant listDetFactureVente After Remise Change : "+ JSON.stringify($scope.factureVenteCourant.listDetFactureVente,null,'  '));
				 }

			 }

			 /***************************************************
			  * Gestion facture -Vente
			  **************************************************/
			 $scope.pagingOptions = {
					 pageSizes : [ 5, 10, 13 ],
					 pageSize : 13,
					 currentPage : 1
			 };

			 //Recherche des Bons de Vente
			 $scope.rechercherFactureVente = function(factureVenteCourant) {
			 	factureVenteCourant.type = "Avoir";
				 $http
				 .post(UrlAtelier+
						 "/facture/rechercheMulticritere",
						 factureVenteCourant)
						 .success(
								 function(resultat) {
									 $scope.myData = resultat.list;
									 // Pagination du resultat de recherche
									 // data, page, pageSize
									 $scope.setPagingData(
											 $scope.myData,
											 $scope.pagingOptions.currentPage,
											 $scope.pagingOptions.pageSize);

									 $log.debug("listeFacture : "+ resultat.list.length);

									 $scope.rechercheFactureVenteForm.$setPristine();
								 });

			 }

			 // Annuler Recherche
			 $scope.annulerAjout = function(){
				 
					$scope.traitementEnCoursGenerer = "false";


					$scope.closeNotif();
				 $scope.idFactureVente = '';
				 $log.debug("Annuler :$scope.idFactureVente "+$scope.idFactureVente);
				 //bouton Valider en mode : notActive
				 $scope.modeValider = "notActif";
				 //bouton PDF en mode : notActive
				 $scope.modePdf = "notActive";
				 //vider la liste des refBS 
				 $scope.tagReferenceBLivList = [];
				 $scope.listTaxeFacture = [];
				 $scope.listDetFactureVente = [];
				 $scope.listDetFactureVentePRBL = [];
				 $scope.listeDocumentProduit = [];
				 //Tableau Prédefini 
				$scope.listTaxeFactureInit = [
							{//FODEC 
							taxeId: 1,
							pourcentage: 1,
							montant: '',
						},

						{// TVA
							taxeId: 2,
							pourcentage: 19,
							montant: '',
						},
					/*	{// TVA7
							taxeId: 4,
							pourcentage: 7,
							montant: '',
						},
						{// TVA13
							taxeId: 5,
							pourcentage: 13,
							montant: '',
						},*/
						{// TIMBRE
							taxeId: 3,
							pourcentage: '',
							montant: 0.600,
						}

						];
				 //initialiser le design des champs
				 $scope.creationFactureVenteForm.$setPristine();
				 $scope.rechercheFactureVenteForm.$setPristine();
				 //init de l'objet courant  
				 $scope.factureVenteCourant = {
				 								  "reference":"",
												  "referenceFacture": "",
												  "partieIntId": null,
												  "referenceBl": "",
												  "dateFactureMin": "",
												  "dateFactureMax": "",
												  "prixMin": "",
												  "prixMax": "",
												  "metrageMin": "",
												  "metrageMax": "",
												  "type":"Avoir",
												  "nature":"",
												  "avecRetourStock":false
										 		};
				 //init de la Grid 
				 $scope.rechercherFactureVente($scope.factureVenteCourant);

				 //interface en mode : list
				 $scope.displayMode = "list";			
			 }

			 // AffectationBLVente BonLivVente
			 $scope.affectationBLVente = function(factureVente) {

				 $scope.factureVenteCourant = {"date":new Date(),"nature":"",
						 "avecRetourStock":false, "devise":"2"};
				/* $scope.factureVenteCourant = factureVente ? angular
						 .copy(factureVente) : {};*/
						 
						 $http.get(UrlAtelier + "/facture/getCurrentReferenceByTypeFacture:Avoir" )
						 .success(
								 function(res) {
									 
										$scope.factureVenteCourant.reference = res;
										$scope.factureVenteCourant.refAvantChangement = res;
								 });
						 
					

						 //mode edit activé
						 $scope.displayMode = "edit";

			 }


			 // Ajout et Modification Bon de Vente
			 $scope.modifierOuCreerBonLVente = function() {
				 $log.debug("modeConsultation/Modification. ");
				 //index de la ligne selectionnée dans la Grid.
				 var index = this.row.rowIndex;

				 //idFactureVente: va etre affecté à l'Url du service Valider en cas de modification 
				 $scope.idFactureVente = $scope.myData[index].id;
				 $log.debug("idFactureVente "+$scope.idFactureVente);

				 // bouton PDF activé
				 $scope.modePdf = "actif";

				 var index = this.row.rowIndex;
				 // getBonVente
				 $http
				 .get(
						 UrlAtelier
						 + "/facture/getFactureById:"+ $scope.idFactureVente)
						 .success(
								 function(datagetFactureVente) {

									 //init du champ tagReferenceBLivList, 
									 $log.debug("-- infoLivraison " + datagetFactureVente.infoLivraison);
									 $log.debug("-- infoLivraison Length " + datagetFactureVente.infoLivraison.length);
									 //recuperation des refBLiv sous le format X-Y-Z
									 var refBLiv = datagetFactureVente.infoLivraison.split("-");
									 //affectation des references à la liste sous le format X,Y,Z
									 $scope.tagReferenceBLivList = refBLiv;

									 //Liste des TaxeLivraisonVente (pour la table Taxe) & detailsLivraisonVente ( pour la table Produit ) correspendants à ce bon de vente
									 var urlValider = UrlAtelier+ "/facture/validateFacture?factureVenteId="+$scope.idFactureVente;
									 $log.info("-**- URL : " + urlValider +" RefBLiv "+$scope.tagReferenceBLivList);
									 //Invocation du service Validate qui nous recupere la liste des ProduitBL.
									
									 $scope.modeValider = "actif";
									 $scope.listDetFactureVentePRBL = datagetFactureVente.listDetFactureVente;
									 /* $http
									 .post(urlValider,$scope.tagReferenceBLivList)
									 .success(
											 function(resultat) {
												 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
												 $scope.modeValider = "actif";

												 $log.debug("-%%%- resultat.listDetFactureVente : "+JSON.stringify(resultat.listDetFactureVente, null, "    ") );
												 //listDetFactureVente
												 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
												 $log.debug("-%%%- listDetFactureVentePRBL : "+JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );
												 $scope.myData[index].listDetFactureVente = $scope.listDetFactureVentePRBL;
											 });*/

									 $scope.listTaxeFacture = datagetFactureVente.listTaxeFacture;
									 $scope.listeDocumentProduit = datagetFactureVente.listDocFactureVente;

									 //affectation du resultat à myData

									 $scope.myData[index].listTaxeFacture = $scope.listTaxeFacture;
									 $log.debug("getFactureId : "+$scope.myData[index].id + " : "+JSON.stringify($scope.myData[index],null,"  ") );
								 });


								 var dateLivraison = null;
								 if ($scope.myData[index].date !== null) {
									 dateLivraison = $scope.modifierFormatDate($scope.myData[index].date);
								 } else {
									 dateLivraison = null;
								 }
			 
			 
			 
								 $scope.factureVenteCourant = Object.assign($scope.myData[index], { date: dateLivraison })
									 //  $scope.partieInteresseeCourante = $scope.myData[index]
									 ? angular.copy($scope.myData[index])
									 : {};



				//  $scope.factureVenteCourant = $scope.myData[index] ? angular
				// 		 .copy($scope.myData[index])
				// 		 : {};
 
						 // mode edit activé	
						 $scope.displayMode = "edit";

			 }



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



			 // Sauvegarder bon de Vente
			 $scope.sauvegarderBonVente = function(factureVente) {
				 

					$scope.traitementEnCours = "true";
				 
				 factureVente.natureLivraison="FINI";

				 if (angular.isDefined(factureVente.id)) {
					 $log.debug("Sauvegarder Modification : " + factureVente.reference);
					 $scope.updateFactureVente(factureVente);
				 } else {
					 $log.debug("Sauvegarder Ajout : " + factureVente.reference);
					 $scope.creerFactureVente(factureVente);
				 }
				 //Actualiser la Grid
				 $scope.rechercherFactureVente({});
			 }

			 // Mise à jour des Factures de Vente
			 $scope.updateFactureVente = function(factureVente) {
				 
				 //Liste des TaxeLivraisonVente (pour la table Taxe) & detailsLivraisonVente ( pour la table Produit ) correspendants à ce bon de vente
				 $log.debug("-- tagReferenceBLivList " + JSON.stringify($scope.tagReferenceBLivList) );	
				 var urlValider = UrlAtelier+ "/facture/validateFacture?factureVenteId="+factureVente.id;
				 $log.debug("--------URL Update "+urlValider);
				 $http
				 .post(urlValider,$scope.tagReferenceBLivList)
				 .success(
						 function(resultat) {
							 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
							 $scope.modeValider = "actif";
							 //listDetFactureVente
							 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
						 });
				 $log.debug("Update------ listDetFactureVentePRBL :" );
				 $log.debug(JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );


				 $log.info("======== AVANT :" );
				 $log.debug("factureVente.listDetFactureVente : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );

				 factureVente.listDetFactureVente = $scope.listDetFactureVentePRBL;
				 factureVente.listDocFactureVente = $scope.listeDocumentProduit;

				 $log.info("======== APRES :" );
				 $log.debug("listDetFactureVentePRBL : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );


				 factureVente.listTaxeFacture = $scope.listTaxeFacture;
				 //tagReferenceBLivList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : infoLivraison sous la forme de ref1-ref2-.. 
				 $log.debug("Join  "+$scope.tagReferenceBLivList.join('-'));
				 factureVente.infoLivraison = $scope.tagReferenceBLivList.join('-');

				 $log.debug("Modification Facture : "+ JSON.stringify(factureVente,null,"  "));

				 $http.post(UrlAtelier+ "/facture/update",factureVente)
				 .success(
						 function(factureVenteId) {
							 

								$scope.traitementEnCours = "false";
										
							        	$scope.showNotif();

						 	$log.debug("success Modification ");
							 for (var i = 0; i < $scope.myData.length; i++) {

								 if ($scope.myData[i].id == factureVenteId) {

									 $scope.myData[i] = factureVenteId;
									 break;
								 }
							 }

							 //getBonLivVente 
							 $http.get(UrlAtelier + "/facture/getFactureById:" + factureVenteId)
							 .success(
									 function(dataGetFactureVente) {
										 //bouton Valider Off
										 $scope.modeValider = "notActif";
										 // bouton PDF activé
										 $scope.modePdf = "actif";

										 //getTableaux
										 $scope.listTaxeFacture = dataGetFactureVente.listTaxeFacture; 
										 $scope.listDetFactureVente = dataGetFactureVente.listDetFactureVente;
										 $scope.listeDocumentProduit = dataGetFactureVente.listDocFactureVente;
										 
										 // Attributs de Recherche
										 $scope.factureVenteCourant = dataGetFactureVente ? angular
												 .copy(dataGetFactureVente)
												 : {};
												 $log.debug("get factureVente : "+ JSON.stringify($scope.factureVenteCourant,null,"  "));
									 });
						 });

			 }

			 // Création FactureVente
			 $scope.creerFactureVente = function(factureVente) {
				 //affectation des listes à l'objet 'factureVente' pour le creer
				 $log.debug("-------- listDetFactureVentePRBL :" );
				 $log.debug(JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );

				 $log.debug("======== AVANT :" );
				 $log.debug("factureVente.listDetFactureVente : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );

				 factureVente.listDetFactureVente = $scope.listDetFactureVentePRBL;
				 factureVente.listDocFactureVente =  $scope.listeDocumentProduit;

				 $log.debug("======== APRES :" );
				 $log.debug("listDetFactureVentePRBL : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );

				 factureVente.listTaxeFacture = $scope.listTaxeFactureInit;
				  //tagReferenceBLivList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : infoLivraison sous la forme de ref1-ref2-.. 
				  $log.debug("Join  "+$scope.tagReferenceBLivList.join('-'));
				  factureVente.infoLivraison = $scope.tagReferenceBLivList.join('-');

				  //Type
				  factureVente.type = "Avoir";

				 $log.debug("Creation factureAvoir -Vente : "+ JSON.stringify(factureVente,null,"  "));

				 $http.post(UrlAtelier + "/facture/create",factureVente)
				 .success(
						 function(factureVenteId) {
							 

								$scope.traitementEnCours = "false";
										
							        	$scope.showNotif();
							 $log.debug("Success Creation"+ factureVenteId);

							 //idFactureVente : valider avec idBonLiv
							 $scope.idFactureVente = factureVenteId;
							 $log.debug("Valider idFactureVente : "+$scope.idFactureVente);

							 //getBonLivVente 
							 $http.get(UrlAtelier + "/facture/getFactureById:" + factureVenteId)
							 .success(
									 function(dataGetFactureVente) {
										 //bouton Valider Off
										 $scope.modeValider = "notActif";
										 // bouton PDF activé
										 $scope.modePdf = "actif";

										 //getTableaux
										 $scope.listTaxeFacture = dataGetFactureVente.listTaxeFacture; 
										 $scope.listeDocumentProduit = dataGetFactureVente.listDocFactureVente;

										 // Attributs de Recherche
										 $scope.factureVenteCourant = dataGetFactureVente ? angular
												 .copy(dataGetFactureVente)
												 : {};
												 $log.debug("get factureVente : "+ JSON.stringify($scope.factureVenteCourant,null,"  "));

									 });

							 //init du champ tagReferenceBLivList, 
							 $log.debug("-- infoLivraison " + factureVente.infoLivraison);
							 //recuperation des refBS sous le format X-Y-Z
							 var refBS = factureVente.infoLivraison.split("-");
							 //affectation des references à la liste sous le format X,Y,Z
							 $scope.tagReferenceBLivList = refBS;

							 //Liste des TaxeLivraisonVente (pour la table Taxe) & detailsLivraisonVente ( pour la table Produit ) correspendants à ce bon de vente
							 var urlValider = UrlAtelier+ "/facture/validateFacture?factureVenteId="+factureVenteId;
							 $log.debug("--------URL Create "+urlValider);
							 $http
							 .post(urlValider,$scope.tagReferenceBLivList)
							 .success(
									 function(resultat) {
										 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
										 $scope.modeValider = "actif";
										 //listDetFactureVente
										 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
									 });
							 $log.debug("OLD------ listDetFactureVentePRBL :" );
							 $log.debug(JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );



						 });

			 }
			 
			 
             $scope.removeProduit = function(index){
            	 
            	 $scope.listDetFactureVentePRBL.splice(index,1);
            	 
             }

			 // Suppression FactureVente
			 $scope.supprimerFactureVente = function() {
				 $log.debug("deleting ..");
				 //TODO: Service de suppression: à revoir. erreur: operation executée mais avec msg  403!
				 // var index = this.row.rowIndex;
				 $http(
						 {
							 method : "DELETE",
							 url : UrlAtelier
							 + "/facture/deleteFacture:"
							 + $scope.myData[$scope.index].id
						 }).success(function() {
							 $log.debug("Success Delete");
							 $scope.myData.splice($scope.index, 1);
							 //  $scope.closePopupDelete();
							 //  $scope.rechercherFactureVente({});
						 });
				 $scope.closePopupDelete();
				 $scope.rechercherFactureVente({});	
			 }

			 /*** PDF ***/
			//conversion date en String
			  function formattedDate(date) {
				    var d = new Date(date),
				        month = '' + (d.getMonth() + 1),
				        day = '' + d.getDate(),
				        year = d.getFullYear();

				    if (month.length < 2) month = '0' + month;
				    if (day.length < 2) day = '0' + day;
				    return [year,month,day].join('-');
				}

			//generer rapport apres creation d'une facture. mode : Modification/Consultation 
			$scope.download = function (id, typerapport,avecObservation) {
					
					if(avecObservation === undefined)
					        avecObservation =false;

					$scope.traitementEnCoursGenererAll = "true";
					$log.debug("-- id" + id);
					var url = UrlAtelier + "/reportgc/facture?id=" + id
						+ "&typerapport=" + typerapport
						+ "&avecObservation=" + avecObservation
						
						+ "&type=pdf";


					var numeroFacture = '_';
					if ($scope.factureVenteCourant.reference)
						numeroFacture = $scope.factureVenteCourant.reference + '_';
					var fileName = 'Facture_' + numeroFacture + formattedDate(new Date());
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
							console.log('llll pdf');
							a.href = fileURL;
							a.download = fileName[0];
							$window.open(fileURL)
							a.click();

						}

						$scope.traitementEnCoursGenererAll = "false";

					});
					
					
			}

			//generer rapport de tous les bons de livraison. mode : List 
			 $scope.downloadAllFactures = function(factureVenteCourant) {
				 
					$scope.traitementEnCoursGenerer = "true";

			 	var newdateFacMinFormat="";
				if(angular.isDefined(factureVenteCourant.dateFactureMin)){
					$log.debug("==dateFactureMin "+factureVenteCourant.dateFactureMin);
					
					if(factureVenteCourant.dateFactureMin != ""){
						newdateFacMinFormat = formattedDate(factureVenteCourant.dateFactureMin);
						$log.debug("===== newdateFacMinFormat "+newdateFacMinFormat);
					}else{
						$log.debug("===== newdateFacMinFormat is Null");
						newdateLivMinFormat = "";
					}
				}else{
					$log.info("==dateFactureMin Undefined");
				}

				var newdateFacMaxFormat="";
				if(angular.isDefined(factureVenteCourant.dateFactureMax)){
					$log.debug("==dateFactureMax "+factureVenteCourant.dateFactureMax);
					
					if(factureVenteCourant.dateFactureMax != ""){
						newdateFacMaxFormat = formattedDate(factureVenteCourant.dateFactureMax);
						$log.debug("===== newdateFacMaxFormat "+newdateFacMaxFormat);
					}else{
						$log.debug("===== newdateFacMaxFormat is Null");
						newdateFacMaxFormat = "";
					}
				}else{
					$log.debug("==dateFactureMax Undefined");
				}
				if(typeof $scope.factureVenteCourant.devise === 'undefined'  ||  $scope.factureVenteCourant.devise == null){
					var newDevise = "";
				}
				else{
					var newDevise=$scope.factureVenteCourant.devise;
				}
				if(typeof $scope.factureVenteCourant.groupeClientId === 'undefined'  ||  $scope.factureVenteCourant.groupeClientId == null){
					var groupeClient = "";
				}
				else{
					var groupeClient=$scope.factureVenteCourant.groupeClientId;
				}
				 $log.debug("-- factureVenteCourant" + JSON.stringify(factureVenteCourant, null, "  ") );

				var url;
				$log.debug("PI  "+factureVenteCourant.partieIntId );
				 if(factureVenteCourant.partieIntId == null){
				 	factureVenteCourant.partieIntId = "";
				 	$log.debug("=>PI  "+factureVenteCourant.partieIntId );
        			url = UrlAtelier + "/reportgc/listfacture?referenceFacture=" + factureVenteCourant.referenceFacture
					 					 + "&typeFacture=Avoir"
					 					 + "&referenceBl=" + factureVenteCourant.referenceBl
					 					 + "&partieIntId="+factureVenteCourant.partieIntId
										 + "&dateFactureMin="+newdateFacMinFormat
										 + "&dateFactureMax="+newdateFacMaxFormat
										 + "&metrageMin="+factureVenteCourant.metrageMin
										 + "&metrageMax="+factureVenteCourant.metrageMax
										 + "&prixMin="+factureVenteCourant.prixMin
										 + "&prixMax="+factureVenteCourant.prixMax
										 + "&natureLivraison="
										 + "&groupeClientId="+groupeClient
										 + "&devise=" +newDevise		 
										 + "&type=pdf";
										 
                  }else{
                    url = UrlAtelier + "/reportgc/listfacture?referenceFacture=" + factureVenteCourant.referenceFacture
					 					 + "&typeFacture=Avoir"
					 					 + "&referenceBl=" + factureVenteCourant.referenceBl
										 + "&partieIntId="+factureVenteCourant.partieIntId
										 + "&dateFactureMin="+newdateFacMinFormat
										 + "&dateFactureMax="+newdateFacMaxFormat
										 + "&metrageMin="+factureVenteCourant.metrageMin
										 + "&metrageMax="+factureVenteCourant.metrageMax
										 + "&prixMin="+factureVenteCourant.prixMin
										 + "&prixMax="+factureVenteCourant.prixMax
										 + "&groupeClientId="+groupeClient	
										 + "&devise=" +newDevise
										 + "&type=pdf";
                   }
									 $log.debug("-- URL" + url );
									 

			
									 var a = document.createElement('a');
									 document.body.appendChild(a);
									 downloadService.download(url).then(function (result) {
										var heasersFileName = result.headers(['content-disposition']).substring(17);
										var fileName = heasersFileName.split('.');
									var typeFile = result.headers(['content-type']);
									var file = new Blob([result.data], {type: typeFile});
									var fileURL = window.URL.createObjectURL(file);
									if(typeFile == 'application/vnd.ms-excel'){
			
									 // a.href = fileURL;
										 a.download = fileName[0];
										$window.open(fileURL)
										 a.click();
					
									}else{
								
										a.href = fileURL;
										a.download = fileName[0];
									 $window.open(fileURL)
										a.click();
					
									}
									
									$scope.traitementEnCoursGenerer = "false";

										
									 
									});


				//  downloadService.download(url).then(
				// 		 function(success) {
				// 			 $log.debug('success : ' + success);
				// 		 }, function(info) {
				// 			 $log.debug('info : ' + info);
				// 		 });
			 };
			 
			 
			 
			 
			 
			 
			 /** DEBUT***************** DOCUMENTS FACTURE AVOIR  *******************/
			 
		      $scope.listeDocumentProduit = [];
			  $scope.name = "AVOIR";
			
			

				$scope.getListeTypeDocumentsCache = function() {
					$http
							.get(
									UrlCommun
											+ "/gestionnaireCache/listeTypeDocumentCache")
							.success(
									function(dataTypeDocumentCache) {
								

											
									
								$scope.listeTypeDocumentsCache = dataTypeDocumentCache.filter(e => e.module === 'VENTE_AVOIR');
			

									});
				}
				$scope.getListeTypeDocumentsCache();

			$scope.showStatus = function(id) {

				/*$scope.type.status = id;
				var selected = $filter('filterProduit')(
						$scope.ListTypeDocumentCache, {
							id : $scope.type.status
						});
				if ($scope.type.status && selected.length) {
					return selected[0].designation;
				} else {
					return "Not Set";
				}*/
				 var designation="";
				 //console.log("show status length: "+$scope.listeTypeDocumentsCache.length);
		    	   for(var i=0;i<$scope.listeTypeDocumentsCache.length;i++)
	    		   {
		    		   //console.log("id: "+id+"----listeTypeDocumentCache[i]="+ $scope.listeTypeDocumentsCache[i].id);
		    		   //console.log("---module: "+$scope.listeTypeDocumentCache[i].module);
		    		   if($scope.listeTypeDocumentsCache[i].id==id ){
		    			   
		    			   designation= $scope.listeTypeDocumentsCache[i].designation;
		    			   //console.log("designation type doc :" +designation);
		    			   return designation;
		    		   }
		    		   
	    		   }
			};
			
			// ajout d'un DocumentProduit
			$scope.ajoutDocumentProduit = function() {

				$scope.DocumentProduitInserree = {
					typeDocumentId : '',
					uidDocument : '',
					path : ''

				};
				$scope.listeDocumentProduit
						.push($scope.DocumentProduitInserree);

			};

			// Enregistrer DocumentProduit
			$scope.saveDocumentProduit = function(
					dataDocumentProduit, id) {
				$scope.deleteValue = "non";
				$log.debug("$scope.deleteValue :"
						+ $scope.deleteValue);
				angular.extend(dataDocumentProduit, {
					id : id
				});
			};

			// Supprimer DocumentProduit
			$scope.removeDocumentProduit = function(index) {
				$scope.listeDocumentProduit.splice(index, 1);
			};
			
			
		
			/**FIN ***************** DOCUMENTS FACTURE AVOIR  *******************/
			 
			 
			 
			 
			 
			 
			 /***************************************************
			  * Gestion des Grids de recherche
			  **************************************************/
			 $scope.colDefs = [];
			 $scope
			 .$watch(
					 'myData',
					 function() {
						 $scope.colDefs = [
					          {
					            field: 'reference',
					            displayName: 'Réf.Avoir',
					            // width:'15%'
					          },
					          {
					            field: 'partieIntAbreviation',
					            displayName: 'client',
					            //  width:'30%'
					          },
					          {
					            field: 'infoLivraison',
					            displayName: 'Réf.Fact.',
					          },
					          {
					            field: 'date',
					            displayName: 'Date facture',
					            cellFilter: "date: 'yyyy-MM-dd'",
					            // width:'8%'
					          },
					          {
					            field: 'metrageTotal',
					            displayName: 'Qte Totale',
					            //   width:'6%'
					          },

					          {
					            field: 'montantHTaxe',
					            displayName: 'Montant HT',
					            cellFilter: 'prixFiltre',
					            //	width: '8%'
					          },
					          {
					            field: 'montantTaxe',
					            displayName: 'Mont. Taxe',
					            cellFilter: 'prixFiltre',
					            //	width: '8%'
					          },
					          {
					            field: 'montantTTC',
					            displayName: 'Montant TTC',
					            cellFilter: 'prixFiltre',
					            //	width: '8%'
					          },
					          {
					            field: '',
					            //	width: '5%',
					            cellTemplate:
											`<div class="ms-CommandButton float-right"   >
					              <button class="ms-CommandButton-button ms-CommandButton-Gpro "  ng-click="modifierOuCreerBonLVente()">
					              <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>
					              </button>
					              <button class="ms-CommandButton-button"  ng-click="showPopupDelete(7)" permission="['Vente_Delete']">
					              <span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>
					             </button>
					              	</div> `, 

					            // '<div class="buttons" ng-show="!rowform.$visible">'
					            // 	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerBonLVente()">	<i class="fa fa-fw fa-pencil"></i></button>'
					            // 	+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(7)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
					          },
					        ];
					 });

			 $scope.filterOptions = {
					 filterText : "",
					 useExternalFilter : true
			 };

			 $scope.totalServerItems = 0;

			 $scope.setPagingData = function(data, page,
					 pageSize) {
				 var pagedData = data.slice((page - 1)
						 * pageSize, page * pageSize);
				 $scope.myData = pagedData;
				 $scope.totalServerItems = data.length;
				 if (!$scope.$$phase) {
					 $scope.$apply();
				 }
			 };

			 $scope.getPagedDataAsync = function(pageSize, page,
					 searchText) {
				 setTimeout(
						 function() {
							 var data;
							 var factureVenteCourant  = $scope.factureVenteCourant;
							 factureVenteCourant.type = "Avoir";				
							 if (searchText) {
								 var ft = searchText.toLowerCase();
								 $http
								 .post(
										 UrlAtelier
										 + "/facture/rechercheMulticritere",factureVenteCourant)
										 .success(
												 function(
														 largeLoad) {
													 data = largeLoad.list
													 .filter(function(item) {
														 return JSON.stringify(item)
														 .toLowerCase()
														 .indexOf(ft) != -1;
													 });
													 $scope.setPagingData( data,	page, pageSize);
												 });

							 } else {

								 $http
								 .post(
										 UrlAtelier
										 + "/facture/rechercheMulticritere",factureVenteCourant)
										 .success(
												 function(largeLoad) {
													 $scope.setPagingData(
															 largeLoad.list,
															 page,
															 pageSize);
												 });
							 }
						 }, 100);
			 };

			 $scope.getPagedDataAsync(
					 $scope.pagingOptions.pageSize,
					 $scope.pagingOptions.currentPage);

			 $scope
			 .$watch(
					 'pagingOptions',
					 function(newVal, oldVal) {
						 if (newVal !== oldVal
								 && newVal.currentPage !== oldVal.currentPage) {
							 $scope
							 .getPagedDataAsync(
									 $scope.pagingOptions.pageSize,
									 $scope.pagingOptions.currentPage,
									 $scope.filterOptions.filterText);
						 }
					 }, true);
			 $scope.$watch('filterOptions', function(newVal,
					 oldVal) {
				 if (newVal !== oldVal) {
					 $scope.getPagedDataAsync(
							 $scope.pagingOptions.pageSize,
							 $scope.pagingOptions.currentPage,
							 $scope.filterOptions.filterText);
				 }
			 }, true);

			 $scope.gridOptions = {
					 data : 'myData',
					 columnDefs : 'colDefs',
					 enablePaging : true,
					 showFooter : true,
					 enableColumnResize: true,
					 enableHighlighting : true,
					 totalServerItems : 'totalServerItems',
					 pagingOptions : $scope.pagingOptions,
					 selectedItems : $scope.selectedRows,
					 filterOptions : $scope.filterOptions,
			 };
			 /** Fin de gestion des Grids de la BonVente */

		 } ])
		 //Filtre sur le champ prix du tableau ProduitBS: retourne les 3 chiffres apres le point .
		 .filter('prixFiltre', function() {
			 return function(prix) {
				 if(prix){
					 prix = prix.toString();
					 // $log.debug("Prix "+prix);
					 if(prix.indexOf(".") == -1){
						 return prix;
					 }else{
						 var index = prix.indexOf(".");
						 // $log.debug("index . "+index);
						 return prix.substring(0,index+4);
					 } 
				 }
			 };
		 });