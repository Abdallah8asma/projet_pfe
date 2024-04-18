'use strict'
angular
.module('gpro.manageUsers', [ "ngResource" ])
.controller(
		'ManageUsersController',
		[
		 '$scope',
		 '$filter',
		 '$http',
		 '$log',
		 'downloadService',
		 'UrlCommun',
		 function($scope, $filter, $http, $log, downloadService, UrlCommun) {
			 // Déclaration des variables globales utilisées
			 var data;
			 $scope.displayMode = "list";
			 $scope.displayMenu = "pers";
			 $scope.listePersonne = [];
			 $scope.listeRole = [];
			 $scope.currentUser = {"roleNames" : "User"};
			 $scope.resultatRecherche = $scope.listePersonne;
			 /***************************************************
			  * Gestion de la Menu Pers
			  **************************************************/
			 $scope.changeTaPersonne = function() {
				 $scope.displayMenu = "pers";
			 }
				$scope.listeRoleDesignation=[];
			 $scope.affectationAllOperations = function(type) {
				 
				// $scope.currentUser.listRoles = [];
				 
				 angular.forEach($scope.listeRole,function(element,value){
					 
					 if(type == 'All'){
						 $scope.currentUser.listRoles.push(element.code);
					 }
					 else
						 
					 if(type == 'Consultation' &&  element.code.indexOf('Consult') > -1){
						 $scope.currentUser.listRoles.push(element.code);
					 }
					 else
						 
					 if(type == 'Edition' &&  element.code.indexOf('Edit') > -1){
						 $scope.currentUser.listRoles.push(element.code);
					 }
					 
					 else
						 
					 if(type == 'Delete' &&  element.code.indexOf('Delete') > -1){
							 $scope.currentUser.listRoles.push(element.code);
					   }	
					 
					 
					})
			 }
			 
			 $scope.annulerOperations = function(type) {
				 
			 $scope.currentUser.listRoles = [];
				 
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
			 $scope.cancelAddPersonne = function(rowform, index, id, designation, liste){
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


			 // Lister les personnes
			 $scope.listePersonne = function() {
				 $http.get(UrlCommun + "/role/getAll")
				 .success(function(data) {
					 $log.debug("listePersonnes : "+data.length);
					 $scope.listePersonne= data;
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

			 $scope.rechercherPersonne = function(
					 currentUser) {
				 $http
				 .post(
					UrlCommun
						 + "/user/rechercheMulticritere",
						 currentUser)
						 .success(
								 function(resultat) {
									 $scope.myData = resultat.list;
									 // Pagination du resultat de
									 // data, page,pageSize
									 $scope.setPagingData(
											 $scope.myData,
											 $scope.pagingOptions.currentPage,
											 $scope.pagingOptions.pageSize);
									 $scope.displayMode = "rechercher";
									 $scope.recherchePersonneFormCritere
									 .$setPristine();
								 });

			 }
			 $scope.rechercherPersonne({});
			 // ** Ajout Personne **
			 $scope.AffectationPersonne = function( 
					 personne) {
				
				 $scope.currentUser = {"roleNames" : "User"};
			     $scope.currentUser.listRoles = [];
				 $scope.creationPersonne.$setPristine();
			
				 $scope.displayMode = "edit";
			 }

			 // Ajout et Modification Personne
			 $scope.modifierOuCreerPersonne = function() {
				 var index = this.row.rowIndex;
				 $http
				 .get(
					UrlCommun
						 + "/user/getById:"
						 + $scope.myData[index].id)
						 .success(
								 function(data) {

									$scope.currentUser = data ;
									//$scope.currentUser.listRoles= data.roleNames.split('-');

								 });
				 $scope.currentUser = $scope.myData[index] ? angular
						 .copy($scope.myData[index])
						 : {};

						 $scope.displayMode = "edit";
			 }

			 // Sauvegarder Pers
			 $scope.sauvegarderAjout = function(personne) {
				 if (angular.isDefined(personne.id)) {
					 $scope
					 .updatePersonne(personne);
				 } else {
					 $scope
					 .creerPersonne (personne);
				 }
				// $scope.rechercherPersonne({});
			 }
			 // Mise à jour de la personne
			 $scope.updatePersonne = function(
					 personne) {
				 $http
				 .put(
					UrlCommun
						 + "/user/update",
						 personne)
						 .success(
								 function(
										 personneModifiee) {
									 $log.debug("success modification");
									 for (var i = 0; i < $scope.listePersonne.length; i++) {
										 if ($scope.myData[i].id == personneModifiee.id) {
											 $scope.myData[i] = personneModifiee;
											 break;
										 }
									 }
									 // TODO getId
									 $scope.annulerAjout();
								 });
				
			 }
			 
			 // Création Pers
			 $scope.creerPersonne = function(
					 personne) {
			
				 $http
				 .post(
					UrlCommun
						 + "/user/create",
						 personne)
						 .success(
								 function(newPersonne) {
									 $log.debug("success creation");
									 // TODO getId
									 $scope.annulerAjout();
								 });
				 }

			 // Annulation de l'ajout
			 $scope.annulerAjout = function() {
				 $scope.recherchePersonneFormCritere  
				 .$setPristine();
				 $scope.currentUser = {
													"userName": "",
													"firstName": "",
													"lastName": "",
													"phoneNumber": "",
													"email": "",
													"version": "",
													"roleNames" : "User"
														
													};
				  $scope.currentUser.listRoles = [];
				 $scope.rechercherPersonne({});
				
				 $scope.displayMode = "list";
			 }
			 // Suppression Pers
			 $scope.supprimerPersonne = function() {
				 $log.debug("DELETE .."
						 + $scope.myData[$scope.index].id);
				 $http(
						 {
							 method : "DELETE",
							 url : UrlCommun
							 + "/user/delete:"
							 + $scope.myData[$scope.index].id
						 }).success(function() {
							 $scope.myData.splice($scope.index, 1);
						 });
				 $scope.closePopupDelete();
				 $scope.rechercherPersonne({});
			 }

			 /** Fin de gestion de la personne */
			
			 /************gestion com*****************/
			 $scope.totalServerItems = 0;

			 $scope.colDefs = [];
			 $scope.$watch(
					 'myData',
					 function() {
						 $scope.colDefs = [

						                   {
						                	   field : 'userName',
						                	   displayName : `Nom d'utilisateur`,
						                	   width:'10%'
						                   },
						                   {
						                	   field : 'firstName',
						                	   displayName : 'Nom',
						                	   width:'30%'
						                   },
						                   {
						                	   field : 'lastName',
						                	   displayName : 'Prénom',
						                	   width:'10%'
						                   },
						                   {
						                	   field  : 'email',
						                	   displayName : 'Email',
						                	   width:'15%'
										   },
										   {
											field  : 'phoneNumber',
											displayName : 'GSM',
											width:'15%'
										   },
										
										   {
											field  : 'boutiqueAbreviation',
											displayName : 'Boutique',
											width:'13%'
										    },
									
						                   {
						                	   field : '',
						                	   width:'7%',
																 cellTemplate : 
																 '<div class="ms-CommandButton float-right" ng-show="!rowform.$visible">' +
																 '<button class="ms-CommandButton-button ms-CommandButton-Gpro "ng-click="modifierOuCreerPersonne()">' +
																 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
																 '</button>' +
																 '<button class="ms-CommandButton-button"  ng-click="showPopupDelete(1)">' +
																 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
																 '</button>' +
																 '	</div> ',
																 
																 
																 
																 
																 
												// 				 `<div class="buttons" ng-show="!rowform.$visible">
											  //  <button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerPersonne()">
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
							 var currentUser = $scope.currentUser;
							 if (searchText) {
								 var ft = searchText
								 .toLowerCase();
								 $http
								 .post(
									UrlCommun
										 + "/user/rechercheMulticritere",
										 currentUser)
										 .success(
												 function(
														 largeLoad) {
													 data = largeLoad.list
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
								   UrlCommun
										+ "/user/rechercheMulticritere",
											currentUser)
										.success(
												function(
														largeLoad) {
													$scope
													.setPagingData(
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
					 totalServerItems : 'totalServerItems',
					 pagingOptions : $scope.pagingOptions,
					 selectedItems : $scope.selectedRows,
					 filterOptions : $scope.filterOptions,
			 };

			 /** Fin de gestion des Grids de la personne */

			 /*** PDF ***/
      		//generer rapport de personnes. mode : List
			  function formattedDate(date) {
				var d = new Date(date),
					month = '' + (d.getMonth() + 1),
					day = '' + d.getDate(),
					year = d.getFullYear();

				if (month.length < 2) month = '0' + month;
				if (day.length < 2) day = '0' + day;
				return [year,month,day].join('-');
			}



			 $scope.downloadAllPersonnes = function(currentUser) {
			 	
				var url;
						
				$log.debug("-- currentUser " + JSON.stringify(currentUser, null, "  ") );
				if(currentUser.userName	== null){
					currentUser.userName = "";
				}
				if(currentUser.firstName	== null){
					currentUser.firstName = "";
				}
				if(currentUser.lastName	== null){
					currentUser.lastName= "";
				}	
				if(currentUser.email	== null){
					currentUser.email= "";
				}	
				if(currentUser.phoneNumber	== null){
					currentUser.phoneNumber= "";
				}	
					
				url = UrlCommun +"/reportCommun/listUsers?userName=" + currentUser.userName
				+ "&firstName=" + currentUser.firstName
			   + "&lastName="+currentUser.lastName
			   + "&email="+currentUser.email
			   + "&phoneNumber="+currentUser.phoneNumber
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
