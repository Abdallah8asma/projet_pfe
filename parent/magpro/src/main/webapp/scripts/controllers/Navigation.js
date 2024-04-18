'use strict';

angular
  .module('theme.navigation-controller', [])
  .controller('NavigationController', [
    '$scope',
    '$location',
    '$timeout',
    '$global',
    function ($scope, $location, $timeout, $global) {
      $scope.menu = [
        {
          label: '  Tableau de bord',
          iconClasses: 'fa fa-tachometer',
          url: '#/',
          back: 'f',
        },
        {
          label: '  Client/ Fournisseur',
          iconClasses: 'fa fa-group',
          // url: '#/partiesInteresseesMenu',
          back: 'f',
          children: [
            {
              label: "Clients",
              iconClasses: "fa fa-suitcase",
              url: "#/partiesInteresseesMenu/client",
              active: true

            },
            {
              label: "Fournisseur ",
              iconClasses: "fa fa-suitcase",
              url: "#/partiesInteresseesMenu/fournisseur",
              active: true
            },
          ]
        },
        {
          label: '  Elements de base',
          iconClasses: 'fa fa-cubes',
          //  url: '#/elementsDeBase',
          back: 'f',
          children: [
            {
              label: "Articles",
              iconClasses: "fa fa-suitcase",
              url: "#/elementsDeBase/produit",
              active: true

            },
            {
              label: "Articles Serialisables",
              iconClasses: "fa fa-suitcase",
              url: "#/elementsDeBase/produitSerialisable",
              active: true
            },
            {
              label: "Articles Reporting",
              iconClasses: "fa fa-suitcase",
              url: "#/elementsDeBase/elementDeBaseReporting",
              active: true
            },
          ]
        },
        //        {
        //            label:"Articles",
        //            iconClasses:"fa fa-cubes",
        //            url:"#/articles",
        //            back:"f"
        //        },
        {
          label: 'Stock',
          iconClasses: 'fa fa-floppy-o',
          // url: '#/stockNvMenu',
          back: 'f',
          children: [
            {
              label: "Etat",
              iconClasses: "fa fa-suitcase",
              url: "#/stockNvMenu/stockNv",
              active: true

            },
            {
              label: "Retour",
              iconClasses: "fa fa-suitcase",
              url: "#/stockNvMenu/stockNvRetour",
              active: true
            },
            {
              label: "Retour Détaille",
              iconClasses: "fa fa-suitcase",
              url: "#/stockNvMenu/stockNvRetourDetail",
              active: true
            },
            {
              label: "Sortie",
              iconClasses: "fa fa-suitcase",
              url: "#/stockNvMenu/stockNvSortie",
              active: true
            },
          ]
        },
        //        {
        //            label:"  Logistique",
        //            iconClasses:"fa fa-exchange",
        //            url:"#/logistique",
        //            back:"f"
        //        },
        //        {
        //            label:"  Gestion de Stock",
        //            iconClasses:"fa fa-exchange",
        //            url:"#/stock",
        //            back:"f"
        //        },
        {
          label: '  Achat ',
          iconClasses: 'fa fa-usd',
          //  url: '#/gestionAchat',
          back: 'f',
          children: [
            {
              label: "Commande",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionAchat/bcAchat",
              active: true

            },
            {
              label: "Réception",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionAchat/reception",
              active: true
            },
            {
              label: "Réception Détaille",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionAchat/receptionDetail",
              active: true
            },
            {
              label: "Facture",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionAchat/factureAchat",
              active: true
            },
            {
              label: "Facture Avoir",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionAchat/factureAvoirAchat",
              active: true
            },
            {
              label: "Avoir Spec.",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionAchat/factureRetourAchat",
              active: true
            },
          ]


        },
        {
          label: '  Vente ',
          iconClasses: 'fa fa-shopping-cart',
          // url: '#/gestionCommerciale',
          back: 'f',
          children: [
            {
              label: "Prix Client",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/prixClient",
              active: true

            },
            {
              label: "Devis",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/devis",
              active: true
            },
            {
              label: "Bon Commande",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/bonCommande",
              active: true
            },
            {
              label: "Marché",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/marche",
              active: true
            },
            {
              label: "Livraison",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/livraison",
              active: true
            },
            {
              label: "Det.Livraison",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/detlivraison",
              active: true
            },
            {
              label: "Facture",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/factureVente",
              active: true
            },
            {
              label: "Facture Avoir",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/factureAvoirVente",
              active: true
            },
            {
              label: "Avoir Sp",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/factureRetourVente",
              active: true
            },
            {
              label: "Reporting",
              iconClasses: "fa fa-suitcase",
              url: "#/gestionCommerciale/reporting",
              active: true
            },
          ]

        },

        {
          label: '  Finance Client',
          iconClasses: 'fa fa-cogs',
          //  url: '#/finance',
          back: 'f',

          children: [
            {
              label: "Réglement",
              iconClasses: "fa fa-suitcase",
              url: "#/finance/reglementVente",
              active: true

            },
            {
              label: "Det.Réglement",
              iconClasses: "fa fa-suitcase",
              url: "#/finance/elementReglementVente",
              active: true
            },
            {
              label: "Solde Client",
              iconClasses: "fa fa-suitcase",
              url: "#/finance/soldeClient",
              active: true
            },
            {
              label: "Paiement",
              iconClasses: "fa fa-suitcase",
              url: "#/finance/echeancier",
              active: true
            },
            {
              label: "Reporting",
              iconClasses: "fa fa-suitcase",
              url: "#/finance/reporting",
              active: true
            },
            {
              label: "Caisse",
              iconClasses: "fa fa-suitcase",
              url: "#/finance/caisse",
              active: true
            },

            {
              label: "Mouvement Caisse",
              iconClasses: "fa fa-suitcase",
              url: "#/finance/mvtCaisse",
              active: true
            },
          ]


        },
        {
          label: '  Finance Fournisseur',
          iconClasses: 'fa fa-cogs',
          // url: '#/financeFournisseur',
          back: 'f',
          children: [
            {
              label: "Réglement",
              iconClasses: "fa fa-suitcase",
              url: "#/financeFournisseur/reglementVente",
              active: true

            },
            {
              label: "Det.Réglement",
              iconClasses: "fa fa-suitcase",
              url: "#/financeFournisseur/elementReglementAchat",
              active: true
            },

            {
              label: "Paiement",
              iconClasses: "fa fa-suitcase",
              url: "#/financeFournisseur/echeancier",
              active: true
            },
            {
              label: "Reporting",
              iconClasses: "fa fa-suitcase",
              url: "#/financeFournisseur/reporting",
              active: true
            },

          ]


        },
        //        {
        //            label:"  Fa&ccedil;on",
        //            iconClasses:"fa fa-cogs",
        //            url:"#/facon",
        //            back:"f"
        //        },






        {
          label: 'Client/Fournisseur',
          iconClasses: '',
          url: '#/backPartiesInteressees',
          back: 'b',

        },

        {
          label: 'Elements De Base',
          iconClasses: '',
          url: '#/backArticles',
          back: 'b',


        },

        {
          label: 'Stock',
          iconClasses: '',
          url: '#/backStock',
          back: 'b',
        },
        {
          label: 'Commercial',
          iconClasses: '',
          url: '#/backCommercial',
          back: 'b',
        },
        {
          label: 'Generateur',
          iconClasses: '',
          url: '#/backGenerateur',
          back: 'b',
        },
      ];
      //    .navFront{
      //    	background-color: #1f8597; margin: 20px auto 20px auto
      //    }
      //    .navBack{
      //    	background-color: #c2c2c0; margin: 20px auto 20px auto
      //    }
      var setParent = function (children, parent) {
        angular.forEach(children, function (child) {
          child.parent = parent;
          if (child.children !== undefined) {
            setParent(child.children, child);
          }
        });
      };

      $scope.findItemByUrl = function (children, url) {
        for (var i = 0, length = children.length; i < length; i++) {
          if (children[i].url && children[i].url.replace('#', '') == url)
            return children[i];
          if (children[i].children !== undefined) {
            var item = $scope.findItemByUrl(children[i].children, url);
            if (item) return item;
          }
        }
      };

      setParent($scope.menu, null);

      $scope.openItems = [];
      $scope.selectedItems = [];
      $scope.selectedFromNavMenu = false;

      $scope.select = function (item) {
        // close open nodes
        if (item.open) {
          item.open = false;
          return;
        }
        for (var i = $scope.openItems.length - 1; i >= 0; i--) {
          $scope.openItems[i].open = false;
        }
        $scope.openItems = [];
        var parentRef = item;
        while (parentRef !== null) {
          parentRef.open = true;
          $scope.openItems.push(parentRef);
          parentRef = parentRef.parent;
        }

        // handle leaf nodes
        if (!item.children || (item.children && item.children.length < 1)) {
          $scope.selectedFromNavMenu = true;
          for (var j = $scope.selectedItems.length - 1; j >= 0; j--) {
            $scope.selectedItems[j].selected = false;
          }
          $scope.selectedItems = [];
          var parentRef = item;
          while (parentRef !== null) {
            parentRef.selected = true;
            $scope.selectedItems.push(parentRef);
            parentRef = parentRef.parent;
          }
        }
      };

      $scope.$watch(
        function () {
          return $location.path();
        },
        function (newVal, oldVal) {
          if ($scope.selectedFromNavMenu == false) {
            var item = $scope.findItemByUrl($scope.menu, newVal);
            if (item)
              $timeout(function () {
                $scope.select(item);
              });
          }
          $scope.selectedFromNavMenu = false;
        }
      );

      // searchbar
      $scope.showSearchBar = function ($e) {
        $e.stopPropagation();
        $global.set('showSearchCollapsed', true);
      };
      $scope.$on('globalStyles:changed:showSearchCollapsed', function (
        event,
        newVal
      ) {
        $scope.style_showSearchCollapsed = newVal;
      });
      $scope.goToSearch = function () {
        $location.path('/extras-search');
      };
    },
  ]);
