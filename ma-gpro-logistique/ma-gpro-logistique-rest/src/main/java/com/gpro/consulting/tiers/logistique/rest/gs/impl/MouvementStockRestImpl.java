package com.gpro.consulting.tiers.logistique.rest.gs.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService;
import com.gpro.consulting.tiers.logistique.service.gs.IGestionnaireGSCacheService;
import com.gpro.consulting.tiers.logistique.service.gs.IMouvementService;

@Controller
@RequestMapping("/mouvementStock")
public class MouvementStockRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(MouvementStockRestImpl.class);

	@Autowired
	private IMouvementService mouvementService;

	@Autowired
	private IGestionnaireGSCacheService gestionnaireGSCacheService;
	
	@Autowired
	private IMiseService miseService;

	/*******************************
	 * mouvement Stock
	 *********************************/
	@RequestMapping(value = "/rechercheParCritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheMouvementValue rechercheParCritereMouvementStock(
			@RequestBody final RechercheMulticritereMouvementValue pMouvementStock) {
		// TODO : cache

		logger.info("mouvementStock--rechercheParCritere");

		Map<Long, PartieInteresseCacheValue> mapClients = new HashMap<Long, PartieInteresseCacheValue>();
		for (PartieInteresseCacheValue client : gestionnaireGSCacheService.getListeClient()) {
			Long key = client.getId();
			if (mapClients.get(key) == null) {
				mapClients.put(key, client);
			}
		}

		ResultatRechecheMouvementValue resultatRecherche = mouvementService
				.rechercherMouvementMultiCritere(pMouvementStock);

		if (resultatRecherche != null) {
			for (MouvementStockValue element : resultatRecherche.getMouvementStock()) {
				
				
				
//				if (element.getBonMouvement() != null) {
//					Long clientId = element.getBonMouvement().getPartieintId();
//					if (clientId != null) {
//						String clientAbreviation = (mapClients.containsKey(clientId))
//								? mapClients.get(clientId).getAbreviation()
//								: null;
//						element.setClientAbreviation(clientAbreviation);
//					}
//				}
				
				if(element.getBonMouvement().getOfId() != null) {
					
		
					element.getBonMouvement().setNumOF(miseService.rechercheMiseParId(element.getBonMouvement().getOfId()).getReference() );
					
				}
			
				
				
				
				
			}
		}

		return resultatRecherche;
	}

	/*******************************
	 * All mouvement stock
	 *********************************/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MouvementStockValue> viewAllMouvementStock() {
		List<MouvementStockValue> vMvt = mouvementService.listeMouvementStock();
		// TODO : cache
		return vMvt;
	}

	/**********************
	 * Méthode de Creation d'un mouvement stock
	 **********************/
	@RequestMapping(value = "/creerMouvementStock", method = RequestMethod.POST)
	public @ResponseBody String creerMouvementStock(@RequestBody MouvementStockValue pMouvement) {
		// this.mouvementService.creerMouvementStock(pMouvement);
		return "";
	}

	/**********************
	 * Méthode de modification d'un mouvement stock
	 **********************/
	@RequestMapping(value = "/modifierMouvementStock", method = RequestMethod.POST)
	public @ResponseBody String modifieMouvementStock(@RequestBody MouvementStockValue pMouvement) {
		// this.mouvementService.modifierMouvementStock(pMouvement);
		return "";
	}

	/**********************
	 * Méthode de Suppression d'un mouvement stock
	 **********************/
	@RequestMapping(value = "/supprimerMouvementStock:{id}", method = RequestMethod.DELETE)
	public void supprimerMouvementStock(@PathVariable("id") String id) {

		// mouvementService.supprimerMouvementStock(Long.valueOf(idSupp));
	}

}
