angular
.module('gpro.factureVente', [ "ngResource" ])
.controller(
		'factureVenteController',
		[
		 '$scope',
		 '$filter',
		 '$http',
		 '$log',
		 'downloadService',
		 'UrlCommun',
		 'UrlAtelier',
		 'FactureServices',
		 'traitementFaconServices',
		 function($scope, $filter, $http, $log, downloadService, UrlCommun, UrlAtelier,FactureServices, traitementFaconServices) {
			 $log.info("=========Facture========");
			 var data;
			 $scope.displayMode = "list";
			 // bouton pdf hide
			 $scope.modePdf = "notActive";
			 $scope.factureVenteCourant = {"type": "Normal"};
			 //mode list activé
			 $scope.displayMode = "list";
			 $scope.listeBonSortie = [];	
			 $scope.listDetFactureVente = [];
			 $scope.listDetFactureVentePRBL = [];
			 $scope.idFactureVente ='';
			 //liste des ReferencesBS
			 $scope.tagReferenceBLivList = [];
			 $scope.listTaxeFacture = [];
			 
			 //Tableau de Taxe Prédefini 
			 
			 $scope.listTaxeFactureInitMethod= function(){
				 if($scope.natureLivraison == "FINI"){
					 $scope.listTaxeFactureInit = [
					                                 {//FODEC
					                                	 taxeId: 1,
					                                	 pourcentage: 1,
					                                	 montant: '',
					                                 },
					                                 {//TVA
					                                	 taxeId: 2,
					                                	 pourcentage: 18,
					                                	 montant: '',
					                                 },
					                                 {//TIMBRE
					                                	 taxeId: 3,
					                                	 pourcentage: '',
					                                	 montant: 0.500,
					                                 }];
				 }else{
					 $scope.listTaxeFactureInit = [
					                                 {//TVA
					                                	 taxeId: 2,
					                                	 pourcentage: 18,
					                                	 montant: '',
					                                 },
					                                 {//TIMBRE
					                                	 taxeId: 3,
					                                	 pourcentage: '',
					                                	 montant: 0.500,
					                                 }];
				 }
			 }
			 
			 $scope.initTaxeRemoved = function(){
				 if($scope.natureLivraison == "FINI"){
					 $scope.taxeIdRemove = [1,2,3]; //FODEC + TVA +TIMBRE
				 }else{
					 $scope.taxeIdRemove = [2,3]; //TVA + TIMBRE
				 }
			 }
			 //modeValider
			 $scope.modeValider = "notActif";
			 //init deleteValue pour cancelAddBLVente
			 $scope.deleteValue = "oui";
			 
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
			 
			// Liste des traitements façon (hors cache)
             $scope.getListeTraitementFacon = function(){                            	
             	traitementFaconServices.getListeTraitementFacon().then(function(listeTraitementFacon) {
                  	$scope.listeTraitementFacon=listeTraitementFacon;                                     
  				}, function(error) {
  					console.log(error.statusText);
  				}); 
             }
			 
			 $scope.listePartieInteresseeCache();
			 $scope.listeTaxes();
			 //$scope.listeMarche();
			 $scope.listeModePaiement();
			 $scope.getListeTraitementFacon();
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
				 if (angular.isDefined(id)) {
					 
					 $log.debug("DEF ID" + id);
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
			 $scope.removeTaxeBLivInit = function(index, taxeId) {
				 $scope.listTaxeFactureInit.splice(index, 1);
				 
				 if(angular.isNumber(taxeId)){
					 var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
					 $scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
					 $scope.filterTaxes();
				 }
				 
			 };
			 
			// remove TaxeBLiv
			 $scope.removeTaxeBLiv = function(index, taxeId) {
				 
				 $scope.listTaxeFacture.splice(index, 1);
				 
				 if(angular.isNumber(taxeId)){
					 var indexTaxeRemoved = $scope.taxeIdRemove.indexOf(taxeId);
					 $scope.taxeIdRemove.splice(indexTaxeRemoved, 1);
					 $scope.filterTaxes();
				 }
				 
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
			 $scope.saveTaxeBLiv = function(data) {
				 $scope.taxeIdRemove.push(data.taxeId);
				 $scope.filterTaxes();
			 };

			 $scope.saveTaxeBLivInit=function(data){
				 $scope.taxeIdRemove.push(data.taxeId);
				 $scope.filterTaxes();
			 }
			 
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
								+ "/bonlivraison/getAvailableListBonLivraisonRefByClient:"+idClient)
								.success(
										function(resultat) {
											$log.debug("----ResultatListBL "+resultat.length);
											
											angular.forEach(resultat, function(element, key){
												$scope.listReferenceBL.push(element.referenceBL);
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

			 //bouton ValiderBL: tagReferenceBLivList va contenir les referencesBL selectionnées, puis cette liste va etre affectée au champ : debugLivraison sous la forme de ref1-ref2-.. 

			 $scope.validerBL = function(){

				 $log.debug(" Recherche des Produits appartenants à ces Bons de Livraison ...");
				 $log.debug("-- tagReferenceBLivList : "+JSON.stringify($scope.tagReferenceBLivList, null, "    ") );

				 //idFactureVente: si undefined -> urlValier SANS idFactureVente, sinon -> idFactureVente AVEC idFactureVente
				 $log.debug("Valider : idFactureVente "+ $scope.idFactureVente );

				//Type livraison fini
				 if($scope.natureLivraison == 'FINI'){
					 $scope.validerNatureFini();
					 
				 }else{ // $scope.natureLivraison == 'facon'
					 
					 $scope.validerNatureFacon();
				}
				 
//				 if($scope.idFactureVente == ''){
//					 $log.debug("Log0: idBonLiv == '' ");
//					 //Url Without idBonLivraison
//					 var urlValider = UrlAtelier+ "/facture/validate";
//					 $log.debug("-- urlValider Sans idFactureVente : "+ urlValider );
//
//					 //Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne sont PAS affectés au BonLivraison Auparavant.
//					 $http
//					 .post(
//							 urlValider,$scope.tagReferenceBLivList)
//							 .success(
//									 function(resultat) {
//										 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
//										 $scope.modeValider = "actif";
//										 //setDateInto = dateLivraison du 1erBS
//										 $scope.factureVenteCourant.date = resultat.dateLivrison;
//										 //listDetFactureVentePRBL
//										 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
//										 $log.debug("-- listDetFactureVentePRBL Size : "+ $scope.listDetFactureVentePRBL.length);
//
//										 $log.debug("-- listDetFactureVentePRBL : "+ JSON.stringify($scope.listDetFactureVentePRBL,null,'  '));
//
//									 });
//
//				 }else{
//					 $log.debug("Log1: idBonLiv = " + $scope.idFactureVente);
//
//					 //Url With idFactureVente
//					 var urlValider = UrlAtelier+ "/facture/validate?factureVenteId="+$scope.idFactureVente;
//					 $log.debug("-- urlValider Avec idFactureVente : "+ urlValider );
//
//					 //Invocation du service Validate qui nous recupere la liste des ProduitBS.
//					 $http
//					 .post(urlValider,$scope.tagReferenceBLivList)
//					 .success(
//							 function(resultat) {
//								 //bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
//								 $scope.modeValider = "actif";
//								//setDateInto = dateLaivraison du 1erBL
//								 $scope.factureVenteCourant.date = resultat.dateLivrison;
//								 //listDetFactureVente
//								 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
//								 $log.debug("-- listDetFactureVentePRBL : "+JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );
//
//							 });
//
//				 }

			 }
			 
			 
			 $scope.validerNatureFini = function(){
				 
				 $log.debug("Log1: idBonLiv = " + $scope.idFactureVente);
				 
//				 if($scope.idFactureVente!=null)
				 FactureServices.validateFini($scope.tagReferenceBLivList,$scope.idFactureVente).then(function(resultat){
					//bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
					 $scope.modeValider = "actif";
					 //setDateInto = dateSortie du 1erBS
					 $scope.factureVenteCourant.date = resultat.dateLivrison;
					 //listDetFactureVente
					 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
					 $log.debug("-- listDetFactureVentePRBL : "+JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );

				 }
				 ,function(error){
					 console.log(error.statusText);
				 });
			 }
			
//			 $scope.idFactureVente="";
			 
			 $scope.validerNatureFacon = function(){
				 
				 $log.debug("Log1: idfacture = " + $scope.idFactureVente);
				 
				 FactureServices.validateFacon($scope.tagReferenceBLivList,$scope.idFactureVente).then(function(resultat){
					//bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
					 $scope.modeValider = "actif";
					 
					 $scope.factureVenteCourant.date = resultat.dateSortie;

					 var listeDetFacture = resultat.listDetFactureVente;

					 $scope.listDetFactureVentePRBL= [];
					 angular.forEach(listeDetFacture, function(elementDetFacture,value){
						 
						 var ligneTraitement=[];
						 //Filter retourne un résultat de type []						 
						 ligneTraitement =$filter('filter')($scope.listeTraitementFacon, {id: elementDetFacture.traitementFaconId });
						 elementDetFacture.designationTraitement = ligneTraitement[0].designation;
						 $scope.listDetFactureVentePRBL.push(elementDetFacture);
					 })
					 
					 
					 $log.debug("-- listDetLivraisonVentePRBS Size : "+ $scope.listDetFactureVentePRBL.length);

					 $log.debug("-- listDetLivraisonVentePRBS : "+ JSON.stringify($scope.listDetFactureVentePRBL,null,'  '));

				 }
				 ,function(error){
					 console.log(error.statusText);
				 });
				
			 }
			 /*************************************************
			  * Gestion de la table Produit: table DIRECTEMENT 
			  * editable sur le champ 'Remise' seulement.
			  *************************************************/
			 $scope.remiseChanged = function(index){
				 if($scope.idFactureVente ==''){

					 //remiseChangedOnCreation
					 $log.debug("remiseChangedOnCreation INDEX Changed "+ index);
					 $log.debug("listDetFactureVentePRBL Remise : "+ $scope.listDetFactureVentePRBL[index].remise);

					 $scope.listDetFactureVentePRBL[index].remise = $scope.listDetFactureVentePRBL[index].remise;
					 $log.debug("-- listDetFactureVente After Remise Change : "+ JSON.stringify($scope.listDetFactureVentePRBL,null,'  '));

				 }else{

					 //remiseChangedOnModification
					 $log.debug("remiseChangedOnModification INDEX Changed "+ index);
					 $log.debug("listDetFactureVentePRBL Remise : "+ $scope.listDetFactureVentePRBL[index].remise);

					 if($scope.listDetFactureVentePRBL[index].id != null){
						 $log.debug("--old--");
						 $scope.factureVenteCourant.listDetFactureVente[index].remise = $scope.listDetFactureVentePRBL[index].remise;

					 }else{
						 $log.debug("--NEW--");
						 $scope.factureVenteCourant.listDetFactureVente.push($scope.listDetFactureVentePRBL[index]);

					 }

					 // $scope.factureVenteCourant.listDetFactureVente[index].remise = $scope.listDetFactureVentePRBL[index].remise;
					 $log.debug("-- factureVenteCourant listDetFactureVente After Remise Change : "+ JSON.stringify($scope.factureVenteCourant.listDetFactureVente,null,'  '));
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
				 
				 $scope.factureVenteCourant.natureLivraison = "FACON";
				 $log.debug("----$scope.factureVenteCourant : obj recherche avant---"+ JSON.stringify($scope.factureVenteCourant, null, " "));
				 
				 
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

									 $log.info("========listeFacture : "+ resultat.list.length);

									 $scope.rechercheFactureVenteForm.$setPristine();
								 });

			 }

			 // Annuler Recherche
			 $scope.annulerAjout = function(){

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
												  "type" : "Normal"
										 		};
				 //init de la Grid 
				 //$scope.rechercherFactureVente($scope.factureVenteCourant);

				 //interface en mode : list
				 $scope.displayMode = "list";			
			 }

			 // AffectationBLVente BonLivVente
			 $scope.affectationBLVente = function(factureVente) {
				 $scope.natureLivraison ="FINI";
				 $scope.listTaxeFactureInitMethod();
				 $scope.initTaxeRemoved();
				 $scope.factureVenteCourant = {};
				 $scope.factureVenteCourant = factureVente ? angular
						 .copy(factureVente) : {};

						 //mode edit activé
						 $scope.displayMode = "edit";

			 }
			 
			// AffectationBLFaconVente BonLivVente
			 $scope.affectationBLFaconVente = function(factureVente) {
				 $scope.natureLivraison ="FACON";
				 $scope.listTaxeFactureInitMethod();
				 $scope.initTaxeRemoved();
				 $scope.factureVenteCourant = {};
				 $scope.factureVenteCourant = factureVente ? angular
						 .copy(factureVente) : {};

						 //mode edit activé
						 $scope.displayMode = "edit";

			 }


			 // Ajout et Modification Bon de Vente
			 $scope.modifierOuCreerFacture = function() {
				 $log.debug("modeConsultation/Modification. ");
				 //index de la ligne selectionnée dans la Grid.
				 var index = this.row.rowIndex;

				 //idFactureVente: va etre affecté à l'Url du service Valider en cas de modification 
				 $scope.idFactureVente = $scope.myData[index].id;
				 $log.debug("idFactureVente "+$scope.idFactureVente);

				 // bouton PDF activé
				 $scope.modePdf = "actif";

				//nature livraison
				 $scope.natureLivraison = $scope.myData[index].natureLivraison;
				 
				 // getFActure
				 $http
				 .get(
						 UrlAtelier
						 + "/facture/getFactureById:"+ $scope.idFactureVente)
						 .success(
								 function(datagetFactureVente) {

									 //init du champ tagReferenceBLivList, 
									 
									 //recuperation des refBLiv sous le format X-Y-Z
									 var refBLiv = datagetFactureVente.infoLivraison.split("-");
									 //affectation des references à la liste sous le format X,Y,Z
									 $scope.tagReferenceBLivList = refBLiv;

									 //Liste des TaxeLivraisonVente (pour la table Taxe) & detailsLivraisonVente ( pour la table Produit ) correspendants à ce bon de vente
									 $scope.listDetFactureVentePRBL=[];
									 
									//bouton Valider en mode : Actif :afficher le tableau resultant de DetLivVene
									 $scope.modeValider = "actif";
									 
									 if($scope.natureLivraison == "FINI"){
										 
									 var urlValider = UrlAtelier+ "/facture/validate?factureVenteId="+$scope.idFactureVente;
									 $log.error("-**- URL : " + urlValider +" RefBLiv "+$scope.tagReferenceBLivList);
									 //Invocation du service Validate qui nous recupere la liste des ProduitBL.
									 $http
									 .post(urlValider,$scope.tagReferenceBLivList)
									 .success(
											 function(resultat) {

												 $log.debug("-%%%- resultat.listDetFactureVente : "+JSON.stringify(resultat.listDetFactureVente, null, "    ") );
												 //listDetFactureVente
												 $scope.listDetFactureVentePRBL = resultat.listDetFactureVente;
												 $log.debug("-%%%- listDetFactureVentePRBL : "+JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );
												 $scope.myData[index].listDetFactureVente = $scope.listDetFactureVentePRBL;
											 });
									 
									 }else{
										 angular.forEach(datagetFactureVente.listDetFactureVente, function(elementDetFacture,value){
											 
											 var ligneTraitement=[];
											 //Filter retourne un résultat de type []						 
											 ligneTraitement =$filter('filter')($scope.listeTraitementFacon, {id: elementDetFacture.traitementFaconId });
											 elementDetFacture.designationTraitement = ligneTraitement[0].designation;
											 $scope.listDetFactureVentePRBL.push(elementDetFacture);
										 })
									 
									 	$log.debug("-%%%- listDetFactureVentePRBL : "+JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );

									 }
									 
									 $log.debug("---nature livraison ----" + $scope.natureLivraison);
									 $scope.listTaxeFacture = datagetFactureVente.listTaxeFacture;

									//affectation du resultat à myData
									 $scope.myData[index].listDetFactureVente = $scope.listDetFactureVentePRBL;
									 $scope.myData[index].listTaxeFacture = $scope.listTaxeFacture;
									 
									 //Initialiser le filtre des taxe à éliminer
									 $scope.taxeIdRemove= [];
									 for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
										 $scope.taxeIdRemove.push($scope.listTaxeFacture[int].taxeId);
									}
									 $scope.filterTaxes();
									 
								 });

									 $scope.factureVenteCourant = $scope.myData[index] ? angular
											 .copy($scope.myData[index])
											 : {};
					
											 // mode edit activé	
											 $scope.displayMode = "edit";
											 
									

			 }

			 // Sauvegarder bon de Vente
			 $scope.sauvegarderBonVente = function(factureVente) {

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
				 
				 var urlValider = UrlAtelier+ "/facture/validate?factureVenteId="+factureVente.id;
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


				 $log.debug("======== AVANT :" );
				 $log.debug("factureVente.listDetFactureVente : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );

				 factureVente.listDetFactureVente = $scope.listDetFactureVentePRBL;

				 $log.debug("======== APRES :" );
				 $log.debug("listDetFactureVentePRBL : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );


				 factureVente.listTaxeFacture = $scope.listTaxeFacture;
				 //tagReferenceBLivList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : infoLivraison sous la forme de ref1-ref2-.. 
				 $log.debug("Join  "+$scope.tagReferenceBLivList.join('-'));
				 factureVente.infoLivraison = $scope.tagReferenceBLivList.join('-');

				 $log.debug("Modification Facture : "+ JSON.stringify(factureVente,null,"  "));

				 $http.post(UrlAtelier+ "/facture/update",factureVente)
				 .success(
						 function(factureVenteId) {

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
										 $scope.modeValider = "actif";
										 // bouton PDF activé
										 $scope.modePdf = "actif";

										 //getTableaux
										 $scope.listTaxeFacture = dataGetFactureVente.listTaxeFacture; 
										 $scope.listDetFactureVente = dataGetFactureVente.listDetFactureVente;
										 // Attributs de Recherche
										 $scope.factureVenteCourant = dataGetFactureVente ? angular
												 .copy(dataGetFactureVente)
												 : {};
												 $log.debug("get factureVente : "+ JSON.stringify($scope.factureVenteCourant,null,"  "));
									 
										 if($scope.natureLivraison == "FACON"){
											$scope.listDetFactureVentePRBL=[];
											angular.forEach($scope.factureVenteCourant.listDetFactureVente, function(elementDetFacture,value){
														 
												 var ligneTraitement=[];
												 //Filter retourne un résultat de type []						 
												 ligneTraitement =$filter('filter')($scope.listeTraitementFacon, {id: elementDetFacture.traitementFaconId });
												 elementDetFacture.designationTraitement = ligneTraitement[0].designation;
												 $scope.listDetFactureVentePRBL.push(elementDetFacture);
											 })
										}
										 //Initialiser le filtre des taxe à éliminer
										 $scope.taxeIdRemove= [];
										 for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
											 $scope.taxeIdRemove.push($scope.listTaxeFacture[int].taxeId);
										}
										 $log.debug("$scope.taxeIdRemove"+ JSON.stringify($scope.taxeIdRemove, null, " "));
										 $scope.filterTaxes();
												 
									 });
						 });

			 }

			 // Création FactureVente
			 $scope.creerFactureVente = function(factureVente) {
				 
//				 $log.debug("-----natureLivraison --------" + $scope.natureLivraison ); 
				 
				 //affectation des listes à l'objet 'factureVente' pour le creer
//				 $log.debug("-------- listDetFactureVentePRBL :" );
//				 $log.debug(JSON.stringify($scope.listDetFactureVentePRBL, null, "    ") );

//				 $log.debug("======== AVANT :" );
//				 $log.debug("factureVente.listDetFactureVente : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );

				 factureVente.listDetFactureVente = $scope.listDetFactureVentePRBL;

//				 $log.debug("======== APRES :" );
//				 $log.debug("listDetFactureVentePRBL : "+JSON.stringify(factureVente.listDetFactureVente, null, "    ") );

				 factureVente.listTaxeFacture = $scope.listTaxeFactureInit;
				  //tagReferenceBLivList va contenir les referencesBS selectionnées, puis cette liste va etre affectée au champ : infoLivraison sous la forme de ref1-ref2-.. 
//				  $log.debug("Join  "+$scope.tagReferenceBLivList.join('-'));
				  factureVente.infoLivraison = $scope.tagReferenceBLivList.join('-');

				  //Type
				  factureVente.type = "Normal";
				  factureVente.natureLivraison =$scope.natureLivraison;
				  
				 $log.debug("Creation factureVente : "+ JSON.stringify(factureVente,null,"  "));

				 $http.post(UrlAtelier + "/facture/create",factureVente)
				 .success(
						 function(factureVenteId) {
							 $log.debug("Success Creation"+ factureVenteId);

							 //idFactureVente : valider avec idBonLiv
							 $scope.idFactureVente = factureVenteId;
							 $log.debug("Valider idFactureVente : "+$scope.idFactureVente);

							 //getBonLivVente 
							 $http.get(UrlAtelier + "/facture/getFactureById:" + factureVenteId)
							 .success(
									 function(dataGetFactureVente) {
										 
										 $scope.modeValider = "actif";
										 
										 // bouton PDF activé
										 $scope.modePdf = "actif";

										 //getTableaux
										 $scope.listTaxeFacture = dataGetFactureVente.listTaxeFacture; 

										 // Attributs de Recherche
										 $scope.factureVenteCourant = dataGetFactureVente ? angular
												 .copy(dataGetFactureVente)
												 : {};
										$log.debug("get factureVente : "+ JSON.stringify($scope.factureVenteCourant,null,"  "));
										$scope.listDetFactureVentePRBL=[];
										
										if($scope.natureLivraison == "FACON"){
											
											angular.forEach($scope.factureVenteCourant.listDetFactureVente, function(elementDetFacture,value){
																	 
										    var ligneTraitement=[];
											 //Filter retourne un résultat de type []						 
											 ligneTraitement =$filter('filter')($scope.listeTraitementFacon, {id: elementDetFacture.traitementFaconId });
											 elementDetFacture.designationTraitement = ligneTraitement[0].designation;
											 $scope.listDetFactureVentePRBL.push(elementDetFacture);
											})
										}else{
											 //init du champ tagReferenceBLivList, 
											 $log.debug("-- infoLivraison " + factureVente.infoLivraison);
											 //recuperation des refBS sous le format X-Y-Z
											 var refBS = factureVente.infoLivraison.split("-");
											 //affectation des references à la liste sous le format X,Y,Z
											 $scope.tagReferenceBLivList = refBS;

											 //Liste des TaxeLivraisonVente (pour la table Taxe) & detailsLivraisonVente ( pour la table Produit ) correspendants à ce bon de vente
											 var urlValider = UrlAtelier+ "/facture/validate?factureVenteId="+factureVenteId;
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
										}		
										
										//Initialiser le filtre des taxe à éliminer
										 $scope.taxeIdRemove= [];
										 for (var int = 0; int < $scope.listTaxeFacture.length; int++) {
											 $scope.taxeIdRemove.push($scope.listTaxeFacture[int].taxeId);
										}
										 $log.debug("$scope.taxeIdRemove"+ JSON.stringify($scope.taxeIdRemove, null, " "));
										 $scope.filterTaxes();
												 
									 });

						
						 });
			 }

			 // Suppression FactureVente
			 $scope.supprimerFactureVente = function() {
				 $log.debug("deleting ..");
				 //TODO: Service de suppression: à revoir. erreur: operation executée mais avec msg  403!
				 // var index = this.row.rowIndex;
				 FactureServices.supprimerFacture($scope.myData[$scope.index].id).then(function(resultat){
					 $log.debug("Success Delete");
					 $scope.myData.splice($scope.index, 1);
					 $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
					}
					 ,function(error){
						 console.log(error.statusText);
						 //TODO: Temp => jusqu'à résoudre le probleme de 403
						 $scope.myData.splice($scope.index, 1);
						 $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
					 });
				 $scope.closePopupDelete();
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
			 $scope.download = function(id) {
				 $log.debug("-- id" + id);
				 var url = UrlAtelier+ "/reportgc/facture?id=" + id
									 + "&type=pdf";
				 downloadService.download(url).then(
						 function(success) {
							 $log.debug('success : ' + success);
							 $scope.annulerAjout();
						 }, function(error) {
							 $log.debug('error : ' + error);
						 });
			 };

			//generer rapport de tous les bons de livraison. mode : List 
			 $scope.downloadAllFactures = function(factureVenteCourant) {
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
					$log.error("==dateFactureMin Undefined");
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

				 $log.debug("-- factureVenteCourant" + JSON.stringify(factureVenteCourant, null, "  ") );

				var url;
				$log.debug("PI  "+factureVenteCourant.partieIntId );
				 if(factureVenteCourant.partieIntId == null){
				 	factureVenteCourant.partieIntId = "";
				 	$log.debug("=>PI  "+factureVenteCourant.partieIntId );
        			url = UrlAtelier + "/reportgc/listfacture?referenceFacture=" + factureVenteCourant.referenceFacture
					 					 + "&typeFacture=Normal"
					 					 + "&referenceBl=" + factureVenteCourant.referenceBl
					 					 + "&partieIntId="+factureVenteCourant.partieIntId
										 + "&dateFactureMin="+newdateFacMinFormat
										 + "&dateFactureMax="+newdateFacMaxFormat
										 + "&metrageMin="+factureVenteCourant.metrageMin
										 + "&metrageMax="+factureVenteCourant.metrageMax
										 + "&prixMin="+factureVenteCourant.prixMin
										 + "&prixMax="+factureVenteCourant.prixMax
										 + "&natureLivraison="+factureVenteCourant.natureLivraison
										 + "&type=pdf";
										 
                  }else{
                    url = UrlAtelier + "/reportgc/listfacture?referenceFacture=" + factureVenteCourant.referenceFacture
					 					 + "&typeFacture=Normal"
					 					 + "&referenceBl=" + factureVenteCourant.referenceBl
										 + "&partieIntId="+factureVenteCourant.partieIntId
										 + "&dateFactureMin="+newdateFacMinFormat
										 + "&dateFactureMax="+newdateFacMaxFormat
										 + "&metrageMin="+factureVenteCourant.metrageMin
										 + "&metrageMax="+factureVenteCourant.metrageMax
										 + "&prixMin="+factureVenteCourant.prixMin
										 + "&prixMax="+factureVenteCourant.prixMax
										 + "&natureLivraison="+factureVenteCourant.natureLivraison
										 + "&type=pdf";
                   }
                   $log.debug("-- URL" + url );
				 downloadService.download(url).then(
						 function(success) {
							 $log.debug('success : ' + success);
						 }, function(error) {
							 $log.debug('error : ' + error);
						 });
			 };
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
						                	   field : 'reference',
						                	   displayName : 'Réf.Fac',
						                   },
						                   {
						                	   field : 'partieIntAbreviation',
						                	   displayName : 'Client'
						                   },
						                   {
						                	   field : 'infoLivraison',
						                	   displayName : 'Réf.BL'
						                   },
						                   {
						                	   field : 'date',
						                	   displayName : 'Date facture',
						                	   cellFilter: "date: 'yyyy-MM-dd'"
						                   },
						                   {
						                	   field : 'metrageTotal',
						                	   displayName : 'Métrage Totale'
						                   },
						                   {
						                	   field : 'montantTTC',
						                	   displayName : 'Prix Totale',
						                	   cellFilter: 'prixFiltre'

						                   },
						                   {
						                	   field : 'etat',
						                	   displayName : 'Etat'
						                   },
						                   {
						                	   field : 'natureLivraison',
						                	   displayName : 'Nature livraison'
						                   },
						                   {
						                	   field : '',
						                	   cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
						                		   + '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerFacture()">	<i class="fa fa-fw fa-pencil"></i></button>'
						                		   + '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(7)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
						                   } ];
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
												 	$log.info("========Grid listeFacture : "+ largeLoad.list.length);
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