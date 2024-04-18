'use strict'

angular
	.module('theme.directives', [])
	.directive('disableAnimation', ['$animate', function ($animate) {
		return {
			restrict: 'A',
			link: function ($scope, $element, $attrs) {
				$attrs.$observe('disableAnimation', function (value) {
					$animate.enabled(!value, $element);
				});
			}
		}
	}])
	.directive('slideOut', function () {
		return {
			restrict: 'A',
			scope: {
				show: '=slideOut'
			},
			link: function (scope, element, attr) {
				element.hide();
				scope.$watch('show', function (newVal, oldVal) {
					if (newVal !== oldVal) {
						element.slideToggle({
							complete: function () {
								scope.$apply();
							}
						});
					}
				});
			}
		}
	})
	.directive('slideOutNav', ['$timeout', function ($t) {
		return {
			restrict: 'A',
			scope: {
				show: '=slideOutNav'
			},
			link: function (scope, element, attr) {
				scope.$watch('show', function (newVal, oldVal) {
					if ($('body').hasClass('collapse-leftbar')) {
						if (newVal == true)
							element.css('display', 'block');
						else
							element.css('display', 'none');
						return;
					}
					if (newVal == true) {
						element.slideDown({
							complete: function () {
								$t(function () {
									scope.$apply()
								})
							}
						});
					} else if (newVal == false) {
						element.slideUp({
							complete: function () {
								$t(function () {
									scope.$apply()
								})
							}
						});
					}
				});
			}
		}
	}])
	.directive('panel', function () {
		return {
			restrict: 'E',
			transclude: true,
			scope: {
				panelClass: '@',
				heading: '@',
				panelIcon: '@'
			},
			templateUrl: 'templates/panel.html',
		}
	})
	.directive('pulsate', function () {
		return {
			scope: {
				pulsate: '='
			},
			link: function (scope, element, attr) {
				// stupid hack to prevent FF from throwing error
				if (element.css('background-color') == "transparent") {
					element.css('background-color', "rgba(0,0,0,0.01)");
				}
				$(element).pulsate(scope.pulsate);
			}
		}
	})
	.directive('prettyprint', function () {
		return {
			restrict: 'C',
			link: function postLink(scope, element, attrs) {
				element.html(prettyPrintOne(element.html(), '', true));
			}
		};
	})
	.directive(
		"passwordVerify",
		function () {
			return {
				require: "ngModel",
				scope: {
					passwordVerify: '='
				},
				link: function (scope, element, attrs, ctrl) {
					scope.$watch(function () {
						var combined;

						if (scope.passwordVerify || ctrl.$viewValue) {
							combined = scope.passwordVerify + '_'
								+ ctrl.$viewValue;
						}
						return combined;
					}, function (value) {
						if (value) {
							ctrl.$parsers.unshift(function (viewValue) {
								var origin = scope.passwordVerify;
								if (origin !== viewValue) {
									ctrl.$setValidity("passwordVerify",
										false);
									return undefined;
								} else {
									ctrl.$setValidity("passwordVerify",
										true);
									return viewValue;
								}
							});
						}
					});
				}
			};
		})
	.directive(
		'backgroundSwitcher',
		function () {
			return {
				restrict: 'EA',
				link: function (scope, element, attr) {
					$(element).click(
						function () {
							$('body').css('background',
								$(element).css('background'));
						});
				}
			};
		})
	.directive('panelControls', [function () {
		return {
			restrict: 'E',
			require: '?^tabset',
			link: function (scope, element, attrs, tabsetCtrl) {
				var panel = $(element).closest('.panel');
				if (panel.hasClass('.ng-isolate-scope') == false) {
					$(element).appendTo(panel.find('.options'));
				}
			}
		};
	}])
	.directive(
		'panelControlCollapse',
		function () {
			return {
				restrict: 'EAC',
				link: function (scope, element, attr) {
					element.bind('click', function () {
						$(element).toggleClass(
							"fa-chevron-down fa-chevron-up");
						$(element).closest(".panel")
							.find('.panel-body').slideToggle({
								duration: 200
							});
						$(element).closest(".panel-heading")
							.toggleClass('rounded-bottom');
					})
					return false;
				}
			};
		})
	.directive(
		'icheck',
		function ($timeout, $parse) {
			return {
				require: '?ngModel',
				link: function ($scope, element, $attrs, ngModel) {
					return $timeout(function () {
						var parentLabel = element.parent('label');
						if (parentLabel.length)
							parentLabel.addClass('icheck-label');
						var value;
						value = $attrs['value'];

						$scope.$watch($attrs['ngModel'], function (
							newValue) {
							$(element).iCheck('update');
						})

						return $(element)
							.iCheck(
								{
									checkboxClass: 'icheckbox_minimal-blue',
									radioClass: 'iradio_minimal-blue'

								})
							.on(
								'ifChanged',
								function (event) {
									if ($(element).attr('type') === 'checkbox'
										&& $attrs['ngModel']) {
										$scope
											.$apply(function () {
												return ngModel
													.$setViewValue(event.target.checked);
											});
									}
									if ($(element).attr('type') === 'radio'
										&& $attrs['ngModel']) {
										return $scope
											.$apply(function () {
												return ngModel
													.$setViewValue(value);
											});
									}
								});
					});
				}
			};
		})
	.directive('knob', function () {
		return {
			restrict: 'EA',
			template: '<input class="dial" type="text"/>',
			scope: {
				options: '='
			},
			replace: true,
			link: function (scope, element, attr) {
				$(element).knob(scope.options);
			}
		}
	})
	.directive('uiBsSlider', ['$timeout', function ($timeout) {
		return {
			link: function (scope, element, attr) {
				// $timeout is needed because certain wrapper directives
				// don't
				// allow for a correct calculaiton of width
				$timeout(function () {
					element.slider();
				});
			}
		};
	}])
	.directive('tileLarge', function () {
		return {
			restrict: 'E',
			scope: {
				item: '=data'
			},
			templateUrl: 'templates/tile-large.html',
			replace: true,
			transclude: true
		}
	})
	.directive('tileMini', function () {
		return {
			restrict: 'E',
			scope: {
				item: '=data'
			},
			replace: true,
			templateUrl: 'templates/tile-mini.html'
		}
	})
	.directive('tile', function () {
		return {
			restrict: 'E',
			scope: {
				heading: '@',
				type: '@'
			},
			transclude: true,
			templateUrl: 'templates/tile-generic.html',
			link: function (scope, element, attr) {
				var heading = element.find('tile-heading');
				if (heading.length) {
					heading.appendTo(element.find('.tiles-heading'));
				}
			},
			replace: true
		}
	})
	.directive('jscrollpane', ['$timeout', function ($timeout) {
		return {
			restrict: 'A',
			scope: {
				options: '=jscrollpane'
			},
			link: function (scope, element, attr) {
				$timeout(function () {
					if (navigator.appVersion.indexOf("Win") != -1)
						element.jScrollPane($.extend({
							mouseWheelSpeed: 20
						}, scope.options))
					else
						element.jScrollPane(scope.options);
					element.on('click', '.jspVerticalBar', function (event) {
						event.preventDefault();
						event.stopPropagation();
					});
					element.bind('mousewheel', function (e) {
						e.preventDefault();
					});
				});
			}
		};
	}])
	// specific to app
	.directive('stickyScroll', function () {
		return {
			restrict: 'A',
			link: function (scope, element, attr) {
				function stickyTop() {
					var topMax = parseInt(attr.stickyScroll);
					var headerHeight = $('header').height();
					if (headerHeight > topMax)
						topMax = headerHeight;
					if ($('body').hasClass('static-header') == false)
						return element.css('top', topMax + 'px');
					var window_top = $(window).scrollTop();
					var div_top = element.offset().top;
					if (window_top < topMax) {
						element.css('top', (topMax - window_top) + 'px');
					} else {
						element.css('top', 0 + 'px');
					}
				}

				$(function () {
					$(window).scroll(stickyTop);
					stickyTop();
				});
			}
		}
	})
	.directive(
		'rightbarRightPosition',
		function () {
			return {
				restrict: 'A',
				scope: {
					isFixedLayout: '=rightbarRightPosition'
				},
				link: function (scope, element, attr) {
					scope.$watch('isFixedLayout', function (newVal,
						oldVal) {
						if (newVal != oldVal) {
							setTimeout(
								function () {
									var $pc = $('#page-content');
									var ending_right = ($(window)
										.width() - ($pc
											.offset().left + $pc
												.outerWidth()));
									if (ending_right < 0)
										ending_right = 0;
									$('#page-rightbar').css(
										'right', ending_right);
								}, 100);
						}
					});
				}
			};
		})
	.directive(
		'fitHeight',
		[
			'$window',
			'$timeout',
			'$location',
			function ($window, $timeout, $location) {
				return {
					restrict: 'A',
					scope: true,
					link: function (scope, element, attr) {
						scope.docHeight = $(document).height();
						var setHeight = function (newVal) {
							var diff = $('header').height();
							if ($('body').hasClass(
								'layout-horizontal'))
								diff += 112;
							if ((newVal - diff) > element
								.outerHeight()) {
								element.css('min-height',
									(newVal - diff) + 'px');
							} else {
								element.css('min-height', $(window)
									.height()
									- diff);
							}
						};
						scope.$watch('docHeight', function (newVal,
							oldVal) {
							setHeight(newVal);
						});
						$(window).on('resize', function () {
							setHeight($(document).height());
						});
						var resetHeight = function () {
							scope.docHeight = $(document).height();
							$timeout(resetHeight, 1000);
						}
						$timeout(resetHeight, 1000);
					}
				};
			}])
	.directive('jscrollpaneOn', ['$timeout', function ($timeout) {
		return {
			restrict: 'A',
			scope: {
				applyon: '=jscrollpaneOn'
			},
			link: function (scope, element, attr) {
				scope.$watch('applyon', function (newVal) {
					if (newVal == false) {
						var api = element.data('jsp');
						if (api)
							api.destroy();
						return;
					}
					$timeout(function () {
						element.jScrollPane({
							autoReinitialise: true
						});
					});
				});
			}
		};
	}])
	.directive('backToTop', function () {
		return {
			restrict: 'AE',
			link: function (scope, element, attr) {
				element.click(function (e) {
					$('body').scrollTop(0);
				});
			}
		}
	})
	.directive(
		'popUp',
		function ($compile) {
			return {
				restrict: 'E',
				templateUrl: 'views/templates/popup.html',
				link: function (scope, element, attr, model) {

				},
				controller: function ($scope, $element, $http, $resource, UrlCommun, UrlAtelier) {

					// Message de popup de Suppression 
					$scope.messages = ["",
						"Suppression d'une Partie Interessée ", //1 P.I
						"Erreur\n\nMerci de vérifier le metrage saisi.", //2 controle du champs : metrage
						"Suppression d'un Produit ..", //3 Element de Base > Produit
						"Suppression d'un Bon de Sortie ..", //4 Logistique > BonSortie
						"Suppression d'un Bon de Livraison de Vente ..", //5 Gest.Commerciale > Vente
						"Suppression d'un Marché ..", //6 Gest.Commerciale > Marché
						"Suppression F: ", //7 Gest.Commerciale > Facture
						"Suppression d'une Fiche Suiveuse ..",//8 Logistique > FicheSuiveuse
						"Suppression d'un Article ",//9 Element de Base > Article
						"Suppression d'une Reglement",//10 Gestion Commertiale> Reglement
						"Suppression d'une Fiche Façon",//11 Logistique > Façon > FicheFaçon
						"Suppression d'un bon de commande",//12 Gestion Commerciale > Bon de reception
						"Suppression d'un bon d'inventaire",//13 Logistique > Bon d'inventaire
						"Suppression d'un bon d'entré",//14 stock > Bon d'entré
						"Suppression d'un bon de sortie",//15 stock > Bon de sortie
						"Suppression d'une caisse",//16 suppression caisse //finance client
						"Suppression Bon Recep. Achat",//17 suppression caisse //finance client
						"Supp. du BC Achat",//18  Achat > Bon de commande
						"Supp. du Bon Stock",//19  Achat > Bon de commande
						"Supp. Remise",//20  Achat > Remise vente
						"Supp. Package",//21  Achat > Remise vente
						"Supp. Facture"//22  Achat > Facture vente
					];

					// show POPUP
					$scope.showPopupDelete = function (type, id) {

						
				
					
			
						switch (type) {
							case 1:
								// suppression partie interessée (PartieInteressee)
								$scope.index = this.row.rowIndex;
								$scope.message = 1;
								break;
							case 2:
								// alert metrage
								$scope.message = 2;
								break;
							case 3:
								// suppression produit (ElementBase)
								$scope.index = this.row.rowIndex;
								$scope.message = 3;
								break;
							case 4:
								// suppression BonSortie (Logistique / Expedition)
								$scope.index = this.row.rowIndex;
								$scope.message = 4;
								break;
							case 5:
								// suppression BonLivraisonVente (GC / Vente)
								$scope.index = this.row.rowIndex;
								$scope.message = 5;
								break;
							case 6:
								// suppression Marche (GC / Marché)
								$scope.index = this.row.rowIndex;
								$scope.message = 6;
								break;
							case 7:
								// suppression FactureVente (GC / FactureVente)
								$scope.index = this.row.rowIndex;
								$scope.message = 7;
								break;
							case 8:
								// suppression FicheSuiveuse (Logistique / Production)
								$scope.index = this.row.rowIndex;
								$scope.message = 8;
								break;
							case 9:
								// suppression Article (ElementBase)
								$scope.index = this.row.rowIndex;
								$scope.message = 9;
								break;
							case 10:
								// suppression Gestion Comemrtiale (Reglement)
								$scope.index = this.row.rowIndex;
								$scope.message = 10;
								break;
							case 11:
								// suppression ficheFaçon
								$scope.index = this.row.rowIndex;
								$scope.message = 11;
								break;
							case 12:
								// suppression d'un bon de commande
								$scope.index = this.row.rowIndex;
								$scope.message = 12;
								break;
							case 13:
								// suppression d'un bon d'inventaire
								$scope.index = this.row.rowIndex;
								$scope.message = 13;
								break;
							case 14:
								// suppression d'un bon d'entré (stock)
								$scope.index = this.row.rowIndex;
								$scope.message = 14;
								break;
							case 15:
								// suppression d'un bon de sortie (stock)
								$scope.index = this.row.rowIndex;
								$scope.message = 15;
								break;
							case 16:
								// suppression d'une caisse
								$scope.index = this.row.rowIndex;
								$scope.message = 16;
								break;

							case 17:
								// suppression d'un bon de reception Achat
								$scope.index = this.row.rowIndex;
								$scope.message = 17;
								$scope.responseMsgDeleteReceptionAchat = "";
								break;

							case 18:
								// suppression d'une commande achat
								$scope.index = this.row.rowIndex;
								$scope.message = 18;
								break;

							case 19:
								// suppression d'une commande achat
								$scope.index = this.row.rowIndex;
								$scope.message = 19;
								break;
							case 20:
								// suppression Remise
								$scope.index = this.row.rowIndex;
								$scope.message = 20;
								break;
							case 21:
								// suppression package
								$scope.index = this.row.rowIndex;
								$scope.message = 21;
								break;
							case 22:
								// suppression Facture Achat
								$scope.index = this.row.rowIndex;
								$scope.message = 22;	
						}

				
						$('div[id=fade]').css("display", "block");
						$('div[id=light]').fadeIn("slow");
						$('#lightS').fadeIn("slow");
						$('#lightS').draggable();
						$('div[id=light]').draggable();
						$('div[id=fade]').fadeIn("slow");
				
					}
					// close POPUP
					$scope.closePopupDelete = function (type) {
						$('div[id=fade]').css("display", "none");
						$('#lightS').fadeOut();
						$('div[id=light]').fadeOut();
						$('#fade').fadeOut();

					}


				}
			}
		})

