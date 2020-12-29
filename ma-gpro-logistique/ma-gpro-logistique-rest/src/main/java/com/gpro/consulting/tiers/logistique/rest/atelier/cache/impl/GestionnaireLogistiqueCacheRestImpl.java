package com.gpro.consulting.tiers.logistique.rest.atelier.cache.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.ITaxeDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.ITaxeService;

/**
 * @author Ameni
 *
 */

@Controller
@RequestMapping("/gestionnaireLogistiqueCache")
public class GestionnaireLogistiqueCacheRestImpl {

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@Autowired
	private IProduitService produitService;
	
	@Autowired
	private ITaxeService taxeService;
	

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionnaireLogistiqueCacheRestImpl.class);

	@RequestMapping(value = "/listeClientCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PartieInteresseCacheValue> viewClient(){
		//LOGGER.info("Entrer Cache Client");
		return gestionnaireLogistiqueCacheService.getListeClient();
    }

	
	//commente par samer le 23.09.20
 /*	@RequestMapping(value = "/listeProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ProduitValue> viewProduit(){
		//LOGGER.info("Entrer Cache Produit");
		reloadLogistiqueCache();
		return gestionnaireLogistiqueCacheService.getListeProduit();
    }
   */ 
	
	
	@RequestMapping(value = "/listeProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ProduitValue> viewProduit(){
		//LOGGER.info("Entrer Cache Produit");
		//reloadLogistiqueCache();
		List<ProduitValue> liste = produitService.listeProduit();
		
		
		/*for (ProduitValue produit : liste) {

			if (produit.getIdTaxe() != null) {
				TaxeValue taxe = taxeService.getById(produit.getIdTaxe());
				if (taxe != null) {
					produit.setTauxTVA(taxe.getValeur());
				}

			}

		}*/
		
		
		
		return liste;
    }
	
	

	@RequestMapping(value = "/listeEntrepotCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EntrepotValue> viewEntrepot(){
		//LOGGER.info("Entrer Cache Entrepot");
		return gestionnaireLogistiqueCacheService.getListEntrepot();
    }
	
	@RequestMapping(value = "/listeChoixRouleauCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ChoixRouleauValue> viewChoixRouleau(){
		//LOGGER.info("Entrer Cache ChoixRouleau");
		return gestionnaireLogistiqueCacheService.getListChoixRouleau();
    }
	
	@RequestMapping(value = "/listeTraitementCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TraitementValue> viewTraitementCache(){
		//LOGGER.info("Entrer Cache Traitement");
		return gestionnaireLogistiqueCacheService.getListTraitement();
    }
	
	@RequestMapping(value = "/listeMachineCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MachineValue> viewMachineCache(){
		//LOGGER.info("Entrer Cache Machine");
		return gestionnaireLogistiqueCacheService.getListMachine();
    }
	
	@RequestMapping(value = "/listeTypeReglementCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TypeReglementValue> viewTypeReglementCache(){
		//LOGGER.info("Entrer Cache TypeReglement");
		return gestionnaireLogistiqueCacheService.getListTypeReglement();
    }
	
	
	@RequestMapping(value = "/listeTypeReglementAchatCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TypeReglementAchatValue> viewTypeReglementAchatCache(){
		//LOGGER.info("Entrer Cache TypeReglement");
		return gestionnaireLogistiqueCacheService.getListTypeReglementAchat();
    }
	
	@RequestMapping(value = "/listeModePaiementCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ModePaiementValue> viewModePaiementCache(){
		//LOGGER.info("Entrer Cache ModePaiement");
		return gestionnaireLogistiqueCacheService.getListModePaiement();
    }
	
	
	
	/**
	 * Added by Zeineb Medimagh
	 * 29/09/2016
	 */
	
	@RequestMapping(value = "/listeTraitementFaconCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TraitementFaconValue> viewTraitementFaconCache(){
		//LOGGER.info("Entrer Cache Traitement Façon");
		return gestionnaireLogistiqueCacheService.getListTraitementFacon();
    }
	
	/**
	 * Refresh du Cache
	 * @return
	 */
	@RequestMapping(value = "/reloadLogistiqueCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody void reloadLogistiqueCache(){
		//LOGGER.info("reloadLogistiqueCache");
		gestionnaireLogistiqueCacheService.reloadLogistiqueCache();
    }
}
