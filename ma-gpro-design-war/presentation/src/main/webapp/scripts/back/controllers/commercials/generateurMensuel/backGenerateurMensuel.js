'use strict'
angular
.module('gpro.back-generateur-mensuel', [ "ngResource" ])
.controller(
		'BackGenerateurMensuelCtr',
		[
		 '$scope',
		 '$filter',
		 '$http',
		 '$log',
		 'downloadService',
		 'UrlCommun',
	     'UrlAtelier',
		 function($scope, $filter, $http, $log, downloadService, UrlCommun,UrlAtelier) {
			 // Déclaration des variables globales utilisées
			 var data;
		$scope.modePdf = "notActive";
			 $scope.displayMode = "list";
			 $scope.displayMenu = "pers";
			 $scope.listeGenerateurMensuel = [];
			 $scope.listeRole = [];
			 $scope.currentGenerateurMensuel = {"roleNames" : "User"};
			 $scope.resultatRecherche = $scope.listeGenerateurMensuel;
			 /***************************************************
			  * Gestion de la Menu Pers
			  **************************************************/
			 $scope.changeTaGenerateurMensuel = function() {
				 $scope.displayMenu = "pers";
			 }
				$scope.listeRoleDesignation=[];
			 $scope.affectationAllOperations = function(type) {
				 
				// $scope.currentGenerateurMensuel.listRoles = [];
				 
				 angular.forEach($scope.listeRole,function(element,value){
					 
					 if(type == 'All'){
						 $scope.currentGenerateurMensuel.listRoles.push(element.code);
					 }
					 else
						 
					 if(type == 'Consultation' &&  element.code.indexOf('Consult') > -1){
						 $scope.currentGenerateurMensuel.listRoles.push(element.code);
					 }
					 else
						 
					 if(type == 'Edition' &&  element.code.indexOf('Edit') > -1){
						 $scope.currentGenerateurMensuel.listRoles.push(element.code);
					 }
					 
					 else
						 
					 if(type == 'Delete' &&  element.code.indexOf('Delete') > -1){
							 $scope.currentGenerateurMensuel.listRoles.push(element.code);
					   }	
					 
					 
					})
			 }
			 
			 $scope.annulerOperations = function(type) {
				 
			 $scope.currentGenerateurMensuel.listRoles = [];
				 
			 }
			
				// Liste des listGroupeClient
				$scope.listeBoutique = function() {
					$http
							.get(
									UrlCommun
											+ "/boutique/all")
							.success(
									function(dataCategorieCache) {
									// $log.debug("listeCategorie :
									// "+dataCategorieCache.length);
										$scope.listeBoutique = dataCategorieCache;

									});
				}
				$scope.listeBoutique();
			
		
			
			 /***************************************************
			  * Gestion de la Pers
			  **************************************************/
			 $scope.deleteValue="oui";
			 //Annuler Ajout
			 $scope.cancelAddGenerateurMensuel = function(rowform, index, id, designation, liste){
				 if (angular.isDefined(id)) {

					 $log.debug("DEF ID");
					 $scope.deleteValue="non";
					 rowform.$cancel();
					 $log.debug("CANCEL");
				 }else{	
					 $log.debug("UND ID  "+id);
					 if(designation ==""){
						 $scope.deleteValue=="oui"
							 $log.debug("Designation : "+designation);
						 $log.debug("$scope.deleteValueOUI : "+$scope.deleteValue);
						 liste.splice(index, 1);
						 rowform.$cancel();
						 $log.debug("DELETE")
					 }else{
						 $log.debug("Designation :"+designation);
						 $log.debug("$scope.deleteValueNON : "+$scope.deleteValue);
						 rowform.$cancel();
						 $log.debug("CANCEL");
					 }
				 }
				 $scope.deleteValue="oui";
			 }


			 // Lister les generateurMensuels
			 $scope.listeGenerateurMensuel = function() {
				 $http.get(UrlCommun + "/role/getAll")
				 .success(function(data) {
					 $log.debug("listeGenerateurMensuels : "+data.length);
					 $scope.listeGenerateurMensuel= data;
				 });
			 }
			 $scope.listeRoles = function() {
				$http
				.get(
				   UrlCommun      
						+ "/role/getAll"
						)
						.success(
								function(data) {
									$scope.listeRole=data;
								});
								

				
			}
			$scope.listeRoles ();
			$scope.select2TaggingOptions = {
				'multiple': true,
				'simple_tags': true,
				'tags': function(){
					$scope.listeRoleDesignation=[];
					angular.forEach($scope.listeRole,function(element,value){
						$scope.listeRoleDesignation.push(element.code);
					})
				return $scope.listeRoleDesignation;
				} }

			$scope.ajoutRole = function() {
				$scope.Role = {
						designation : '',
						
				};
				$scope.listeRole
				.push($scope.Role);
			};
			$scope.saveRole = function(dataRole, id) {
				$scope.deleteValue = "non";
				angular.extend(dataRole, {
					id : id
				});
				console
				.log("Success Save Role in Users"
						+ dataRole.id);
			};
			$scope.removeRole = function(index) {
				$scope.listeRole.splice(index, 1);
			};
			 // Rechercher Pers
			 $scope.pagingOptions = {
					 pageSizes : [ 5, 10, 13 ],
					 pageSize : 13,
					 currentPage : 1
			 };

			 $scope.rechercherGenerateurMensuel = function(
					 currentGenerateurMensuel) {
				 $http
				 .post(
					UrlAtelier
						 + "/guichet-mensuel/rechercheMulticritere",
						 currentGenerateurMensuel)
						 .success(
								 function(resultat) {
									 $scope.myData = resultat;
									 // Pagination du resultat de
									 // data, page,pageSize
									 $scope.setPagingData(
											 $scope.myData,
											 $scope.pagingOptions.currentPage,
											 $scope.pagingOptions.pageSize);
									 $scope.displayMode = "rechercher";
									 $scope.rechercheGenerateurMensuelFormCritere
									 .$setPristine();
								 });

			 }
			 $scope.rechercherGenerateurMensuel({});
			 // ** Ajout GenerateurMensuel **
			 $scope.AffectationGenerateurMensuel = function( 
					 generateurMensuel) {
				
				 $scope.currentGenerateurMensuel = {};
			     $scope.currentGenerateurMensuel.listRoles = [];
				 $scope.creationGenerateurMensuel.$setPristine();
			
				 $scope.displayMode = "edit";
			 }

			 // Ajout et Modification GenerateurMensuel
			 $scope.modifierOuCreerGenerateurMensuel = function() {
				 var index = this.row.rowIndex;
				 $http
				 .get(
					UrlAtelier
						 + "/guichet-mensuel/getById:"
						 + $scope.myData[index].id)
						 .success(
								 function(data) {
									
										$scope.modePdf = "actif";

									$scope.currentGenerateurMensuel = data ;
									//$scope.currentGenerateurMensuel.listRoles= data.roleNames.split('-');

								 });
				 $scope.currentGenerateurMensuel = $scope.myData[index] ? angular
						 .copy($scope.myData[index])
						 : {};

						 $scope.displayMode = "edit";
			 }

			 // Sauvegarder Pers
			 $scope.sauvegarderAjout = function(generateurMensuel) {
				 if (angular.isDefined(generateurMensuel.id)) {
					 $scope
					 .updateGenerateurMensuel(generateurMensuel);
				 } else {
					 $scope
					 .creerGenerateurMensuel (generateurMensuel);
				 }
				// $scope.rechercherGenerateurMensuel({});
			 }
			 // Mise à jour de la generateurMensuel
			 $scope.updateGenerateurMensuel = function(
					 generateurMensuel) {
				 $http
				 .put(
					UrlAtelier
						 + "/guichet-mensuel/update",
						 generateurMensuel)
						 .success(
								 function(
										 generateurMensuelModifiee) {
									 $log.debug("success modification");
									 for (var i = 0; i < $scope.listeGenerateurMensuel.length; i++) {
										 if ($scope.myData[i].id == generateurMensuelModifiee.id) {
											 $scope.myData[i] = generateurMensuelModifiee;
											 break;
										 }
									 }
									 // TODO getId
									 $scope.annulerAjout();
								 });
				
			 }
			 
			 // Création Pers
			 $scope.creerGenerateurMensuel = function(
					 generateurMensuel) {
			
				 $http
				 .post(
					UrlAtelier
						 + "/guichet-mensuel/create",
						 generateurMensuel)
						 .success(
								 function(newGenerateurMensuel) {
									 $log.debug("success creation");
									 // TODO getId
									 $scope.annulerAjout();
								 });
				 }

			 // Annulation de l'ajout
			 $scope.annulerAjout = function() {
				
				$scope.modePdf = "notActive";
				 $scope.rechercheGenerateurMensuelFormCritere  
				 .$setPristine();
				 $scope.currentGenerateurMensuel = {
													"userName": "",
													"firstName": "",
													"lastName": "",
													"phoneNumber": "",
													"email": "",
													"version": "",
													"roleNames" : "User"
														
													};
				  $scope.currentGenerateurMensuel.listRoles = [];
				 $scope.rechercherGenerateurMensuel({});
				
				 $scope.displayMode = "list";
			 }
			 // Suppression Pers
			 $scope.supprimerGenerateurMensuel = function() {
				 $log.debug("DELETE .."
						 + $scope.myData[$scope.index].id);
				 $http(
						 {
							 method : "DELETE",
							 url : UrlAtelier
							 + "/guichet-mensuel/deleteById:"
							 + $scope.myData[$scope.index].id
						 }).success(function() {
							 $scope.myData.splice($scope.index, 1);
						 });
				 $scope.closePopupDelete();
				 $scope.rechercherGenerateurMensuel({});
			 }

			 /** Fin de gestion de la generateurMensuel */
			
			 /************gestion com*****************/
			 $scope.totalServerItems = 0;

			 $scope.colDefs = [];
			 $scope.$watch(
					 'myData',
					 function() {
						 $scope.colDefs = [

						                   {
						                	   field : 'annee',
						                	   displayName : `Annee`,
						                	   width:'30%'
						                   },
						                   {
						                	   field : 'mois',
						                	   displayName : 'Mois',
						                	   width:'30%'
						                   },
						                   {
						                	   field : 'cibleCA',
						                	   displayName : 'Cible CA',
						                	   width:'30%'
						                   },
						                  
									
						                   {
						                	   field : '',
						                	   width:'7%',
																 cellTemplate : 
																 '<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">' +
																 '<button class="ms-CommandButton-button ms-CommandButton-Gpro "ng-click="modifierOuCreerGenerateurMensuel()">' +
																 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
																 '</button>' +
																 '<button class="ms-CommandButton-button"  ng-click="showPopupDelete(1)">' +
																 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
																 '</button>' +
																 '	</div> ',
																 
																 
																 
																 
																 
												// 				 `<div class="buttons" ng-show="!rowform.$visible">
											  //  <button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerGenerateurMensuel()">
												// 	   <i class="fa fa-fw fa-pencil"></i></button>
											  // <!-- <button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(1)"> 
											  //  <i class="fa fa-fw fa-trash-o"></i></button>--></div>`
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
							 var currentGenerateurMensuel = $scope.currentGenerateurMensuel;
							 if (searchText) {
								 var ft = searchText
								 .toLowerCase();
								 $http
								 .post(
									UrlAtelier
										 + "/guichet-mensuel/rechercheMulticritere",
										 currentGenerateurMensuel)
										 .success(
												 function(
														 largeLoad) {
													 data = largeLoad
													 .filter(function(
															 item) {
														 return JSON
														 .stringify(
																 item)
																 .toLowerCase()
																 .indexOf(
																		 ft) != -1;
													 });
													 $scope
													 .setPagingData(
															 data,
															 page,
															 pageSize);
												 });

							 } else {
								$http
								.post(
								   UrlAtelier
										+ "/guichet-mensuel/rechercheMulticritere",
											currentGenerateurMensuel)
										.success(
												function(
														largeLoad) {
													$scope
													.setPagingData(
															largeLoad,
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
					 totalServerItems : 'totalServerItems',
					 pagingOptions : $scope.pagingOptions,
					 selectedItems : $scope.selectedRows,
					 filterOptions : $scope.filterOptions,
			 };

			 /** Fin de gestion des Grids de la generateurMensuel */

			 /*** PDF ***/
      		//generer rapport de generateurMensuels. mode : List
			  function formattedDate(date) {
				var d = new Date(date),
					month = '' + (d.getMonth() + 1),
					day = '' + d.getDate(),
					year = d.getFullYear();

				if (month.length < 2) month = '0' + month;
				if (day.length < 2) day = '0' + day;
				return [year,month,day].join('-');
			}



			 $scope.downloadAllGenerateurMensuels = function(currentGenerateurMensuel) {
			 	
				var url;
						
				$log.debug("-- currentGenerateurMensuel " + JSON.stringify(currentGenerateurMensuel, null, "  ") );
				if(currentGenerateurMensuel.userName	== null){
					currentGenerateurMensuel.userName = "";
				}
				if(currentGenerateurMensuel.firstName	== null){
					currentGenerateurMensuel.firstName = "";
				}
				if(currentGenerateurMensuel.lastName	== null){
					currentGenerateurMensuel.lastName= "";
				}	
				if(currentGenerateurMensuel.email	== null){
					currentGenerateurMensuel.email= "";
				}	
				if(currentGenerateurMensuel.phoneNumber	== null){
					currentGenerateurMensuel.phoneNumber= "";
				}	
					
				url = UrlCommun +"/reportCommun/listUsers?userName=" + currentGenerateurMensuel.userName
				+ "&firstName=" + currentGenerateurMensuel.firstName
			   + "&lastName="+currentGenerateurMensuel.lastName
			   + "&email="+currentGenerateurMensuel.email
			   + "&phoneNumber="+currentGenerateurMensuel.phoneNumber
			   + "&type=pdf";
					
                   $log.debug("-- URL--- :" + url );
				 downloadService.download(url).then(
						 function(success) {
							// $log.debug('success : ' + success);
						 }, function(error) {
							// $log.debug('error : ' + error);
						 });
			 };


			
			
			
			
		 } ])
		 .filter('filterListProduit', function() {
			 return function(liste, id) {
				 var listeFiltre = [];
				 angular.forEach(liste, function(elementListe, key){

					 if(elementListe.id == id.id){
						 listeFiltre.push(elementListe);
					 }

				 });
				 return listeFiltre;
			 };
		 })
		 .controller(
				 'DatepickerDController',
				 [
				  '$scope',
				  function($scope) {
					  $scope.maxToDay = new Date();
//					  // date piker defit
//					  $scope.today = function() {
//					  $scope.partieInteresseeCourante.dateIntroduction = new Date();
//					  };
//					  $scope.today();
//					  $scope.clear = function() {
//					  $scope.partieInteresseeCourante.dateIntroduction = null;
//					  };
					  // Disable weekend selection
					  $scope.disabled = function(date, mode) {
						  return (mode === 'day' && (date.getDay() === 0 || date
								  .getDay() === 6));
					  };
					  $scope.toggleMin = function() {
						  $scope.minDate = $scope.minDate ? null
								  : new Date();
					  };
					  $scope.toggleMin();
					  $scope.open = function($event) {
						  $event.preventDefault();
						  $event.stopPropagation();
						  $scope.opened = true;
					  };
					  $scope.dateOptions = {
							  formatYear : 'yy',
							  startingDay : 1
					  };
					  $scope.initDate = new Date('20-08-2015');
					  $scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
					                     'dd.MM.yyyy', 'shortDate' ];
					  $scope.format = $scope.formats[0];

				  } ]);
