'use strict'
angular
.module('gpro.manageOperations', [ "ngResource" ])
.controller(
		'ManageOperationsController',
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
			 $scope.listeRole = [];
			 $scope.listeRole = [];
			 $scope.currentRole = {};
			 $scope.resultatRecherche = $scope.listeRole;
			 
		
			 /***************************************************
			  * Gestion de la Menu Pers
			  **************************************************/
			 $scope.changeTaRole = function() {
				 $scope.displayMenu = "pers";
			 }
			

			
		
			
			 /***************************************************
			  * Gestion de la Pers
			  **************************************************/
			 

			 
			 $scope.rechercherRole = function(
					 currentUser) {
				 $http
				 .post(
					UrlCommun
						 + "/role/rechercheMulticritere",
						 currentUser)
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
									 $scope.rechercheRoleFormCritere
									 .$setPristine();
								 });

			 }
			 $scope.rechercherRole({});
			 
			 $scope.deleteValue="oui";
			 //Annuler Ajout
			 $scope.cancelAddRole = function(rowform, index, id, designation, liste){
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
			 $scope.listeRole = function() {
				 $http.get(UrlCommun + "/role/getAll")
				 .success(function(data) {
					 $log.debug("listeRoles : "+data.length);
					 $scope.listeRole= data;
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
								

						$scope.displayMode = "edit";
			}
			$scope.listeRoles ();
			$scope.select2TaggingOptions = {
				'multiple': true,
				'simple_tags': true,
				'tags': function(){
					$scope.listeRoleDesignation=[];
					angular.forEach($scope.listeRole,function(element,value){
						$scope.listeRoleDesignation.push(element.designation);
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

		
			 // ** Ajout Role **
			 $scope.AffectationRole = function( 
					 personne) {
				 $scope.currentRole = {};
				 $scope.creationRole.$setPristine();
			
				 $scope.displayMode = "edit";
			 }

			 // Ajout et Modification Role
			 $scope.modifierOuCreerRole = function() {
				 var index = this.row.rowIndex;
				 $http
				 .get(
					UrlCommun
						 + "/role/getById:"
						 + $scope.myData[index].id)
						 .success(
								 function(data) {

									$scope.currentRole = data ;
								//	$scope.currentRole.listRoles= data.roleNames.split('-');
									
									 $scope.currentRole = $scope.myData[index] ? angular
											 .copy($scope.myData[index])
											 : {};

											 $scope.displayMode = "edit";

								 });
			
			 }

	
			 // Sauvegarder Pers
			 $scope.sauvegarderAjout = function(personne) {
				 if (angular.isDefined(personne.id)) {
					 $scope
					 .updateRole(personne);
				 } else {
					 $scope
					 .creerRole (personne);
				 }
				// $scope.rechercherRole({});
			 }
			 // Mise à jour de la personne
			 $scope.updateRole = function(
					 personne) {
				 $http
				 .put(
					UrlCommun
						 + "/role/update",
						 personne)
						 .success(
								 function(
										 personneModifiee) {
									 $log.debug("success modification");
									/* for (var i = 0; i < $scope.listeRole.length; i++) {
										 if ($scope.myData[i].id == personneModifiee.id) {
											 $scope.myData[i] = personneModifiee;
											 break;
										 }
									 } */
									 // TODO getId
									 $scope.annulerAjout();
								 });
				
			 }
			 
			 // Création Pers
			 $scope.creerRole = function(
					 personne) {
			
				 $http
				 .post(
					UrlCommun
						 + "/role/create",
						 personne)
						 .success(
								 function(newRole) {
									 $log.debug("success creation");
									 // TODO getId
									 $scope.annulerAjout();
								 });
				 }

			 // Annulation de l'ajout
			 $scope.annulerAjout = function() {
				 $scope.rechercheRoleFormCritere
				 .$setPristine();
				 $scope.currentRole = {"version": "ma-gpro"	};
													
													
												
				 $scope.rechercherRole({});
				
				 $scope.displayMode = "list";
			 }
			 // Suppression Pers
			 $scope.supprimerRole = function() {
				 
				 var index = this.row.rowIndex;
				 $log.debug("DELETE .."
						 + $scope.myData[index].id);
				 $http(
						 {
							 method : "DELETE",
							 url : UrlCommun
							 + "/role/delete:"
							 + $scope.myData[index].id
						 }).success(function() {
							 $scope.myData.splice(index, 1);
						 });
				 $scope.closePopupDelete();
				 $scope.rechercherRole({});
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
						                	   field : 'code',
						                	   displayName : `Code`,
						                	   width:'25%'
						                   },
						                   {
						                	   field : 'designation',
						                	   displayName : 'Designation',
						                	   width:'35%'
						                   },
						                   {
						                	   field : 'module',
						                	   displayName : 'Module',
						                	   width:'30%'
						                   },
						                  
									
						                   {
						                	   field : '',
						                	   width:'7%', 
																 cellTemplate :
																 '<div class="ms-CommandButton float-right"  ng-show="!rowform.$visible">' +
																 '<button class="ms-CommandButton-button ms-CommandButton-Gpro "  ng-click="modifierOuCreerRole()">' +
																 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Edit ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
																 '</button>' +
																 '<button class="ms-CommandButton-button"  ng-click="supprimerRole()">' +
																 '<span class="ms-CommandButton-icon "><i class="ms-Icon ms-Icon--Delete ms-Icon-Gpro" aria-hidden="true" ></i></span>' +
																 '</button>' +
																 '	</div> ',
																 
																 
																 
																 
																 
												// 				 `<div class="buttons" ng-show="!rowform.$visible">
											  //  <button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerRole()">
												// 	   <i class="fa fa-fw fa-pencil"></i></button>
											  //  <button data-nodrag class="btn btn-default btn-xs"	ng-click="supprimerRole()">	
											  //  <i class="fa fa-fw fa-trash-o"></i></button></div>`
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
							 var currentRole = $scope.currentRole;
							 if (searchText) {
								 var ft = searchText
								 .toLowerCase();
								 $http
								 .post(
									UrlCommun
										 + "/role/rechercheMulticritere",
										 currentRole)
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
								   UrlCommun
										+ "/role/rechercheMulticritere",
											currentRole)
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



			 $scope.downloadAllRoles = function(currentRole) {
			 	
				var url;
						
				$log.debug("-- currentRole " + JSON.stringify(currentRole, null, "  ") );
				if(currentRole.userName	== null){
					currentRole.userName = "";
				}
				if(currentRole.firstName	== null){
					currentRole.firstName = "";
				}
				if(currentRole.lastName	== null){
					currentRole.lastName= "";
				}	
				if(currentRole.email	== null){
					currentRole.email= "";
				}	
				if(currentRole.phoneNumber	== null){
					currentRole.phoneNumber= "";
				}	
					
				url = UrlCommun +"/reportCommun/listUsers?userName=" + currentRole.userName
				+ "&firstName=" + currentRole.firstName
			   + "&lastName="+currentRole.lastName
			   + "&email="+currentRole.email
			   + "&phoneNumber="+currentRole.phoneNumber
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
