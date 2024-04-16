package com.gpro.consulting.tiers.logistique.rest.gs.impl;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService;
import com.gpro.consulting.tiers.logistique.service.gs.IBonMouvementService;
import com.gpro.consulting.tiers.logistique.service.gs.IGestionnaireGSCacheService;

@Controller
@RequestMapping("/bonMouvementStock")
public class BonMouvementStockRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(BonMouvementStockRestImpl.class);

	@Autowired
	private IBonMouvementService bonMouvementService;

	@Autowired
	private IGestionnaireGSCacheService gestionnaireGSCacheService;
	@Autowired
	private IMiseService miseService;


	/*******************************
	 * get bonMouvement Stock par type
	 *********************************/
	@RequestMapping(value = "/getBonMouvementParType:{type}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<BonMouvementStockValue> getByTypeBonMouvementStock(@PathVariable final String type) {
		List<BonMouvementStockValue> vMvt = bonMouvementService.getByTypeBonMouvement(type);
		for (BonMouvementStockValue bon : vMvt) {
			Map<String, String> mapA = gestionnaireGSCacheService
					.rechercherBonMouvementParId(bon.getRaisonMouvementId());
			bon.setRaisonMouvementDesignation(mapA.get("raison"));
		}
		return vMvt;
	}

	/*******************************
	 * bonMouvement Stock
	 *********************************/
	@RequestMapping(value = "/rechercheParCritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonMouvementStockValue rechercheParCritereBonMouvementStock(
			@RequestBody final RechercheMulticritereBonMouvementStockValue pBonMouvementStock) {

		ResultatRechecheBonMouvementStockValue vMvt = bonMouvementService
				.rechercherBonMouvementMultiCritere(pBonMouvementStock);

		for (BonMouvementStockValue bon : vMvt.getBonMouvementStock()) {

			Map<String, String> mapA = gestionnaireGSCacheService
					.rechercherBonMouvementParId(bon.getRaisonMouvementId());
			bon.setRaisonMouvementDesignation(mapA.get("raison"));

			String client = gestionnaireGSCacheService.rechercherclientId(bon.getPartieintId());
			bon.setClient(client);
			
			
			if(bon.getOfId() != null) {
				
				   MiseValue mise=miseService.rechercheMiseParId(bon.getOfId()) ;
					
					
					bon.setNumOF(mise.getReference());
				
			}
		
				
			
		}

		return vMvt;
	}

	/*******************************
	 * All bonMouvement stock
	 *********************************/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<BonMouvementStockValue> viewAllBonMouvementStock() {
		List<BonMouvementStockValue> vMvt = bonMouvementService.listeBonMouvement();
		return vMvt;
	}

	/**********************
	 * Méthode de Creation d'un Bon de mouvement stock
	 **********************/
	@RequestMapping(value = "/creerBonMouvementStock", method = RequestMethod.POST)
	public @ResponseBody String creerBonMouvementStock(@RequestBody BonMouvementStockValue pBonMouvement) {
		return this.bonMouvementService.creerBonMouvement(pBonMouvement);
	}

	/**********************
	 * Méthode de modification Bon de mouvement stock
	 **********************/
	@RequestMapping(value = "/modifierBonMouvementStock", method = RequestMethod.POST)
	public @ResponseBody String modifieBonMouvementStock(@RequestBody BonMouvementStockValue pBonMouvement) {

		//System.out.println("bonMvt rest ---" + pBonMouvement.toString());
		return this.bonMouvementService.modifierBonMouvement(pBonMouvement);
	}

	/**********************
	 * Méthode de Suppression BonMouvementStockValue
	 **********************/
	@RequestMapping(value = "/supprimerBonMouvementStock:{id}", method = RequestMethod.DELETE)
	public void supprimerBonMouvementStock(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		
		//System.out.println("---Rest supprimerBonMouvementStock--idSupp:"+idSupp);
		bonMouvementService.supprimerBonMouvement(idSupp);
	}

	/************* Methode get bon Mouvement By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonMouvementStockValue getBonMouvementParId(@PathVariable Long id) {
		return bonMouvementService.rechercheBonMouvementParId(id);
	}

	// Added on 09/11/2016, by Zeineb Medimagh

	/*******
	 * get liste Numeros Bon Mouvement par type (Reservation / entree/ sortie)
	 * Retourne la liste des bonMvt (Id / Numero / idOF)
	 *********/

	@RequestMapping(value = "/getListeNumParType", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ResultatListeBonMvtParTypeValue> getListeNumParType(@RequestParam String type) {
		return bonMouvementService.getListeNumerosParType(type);
	}

	// Added on 09/11/2016, by Zeineb Medimagh

	/*******
	 * get liste Bon Mouvement OF - sortie
	 *********/

	// @RequestMapping(value = "/getListeMouvementsSortie", method =
	// RequestMethod.GET, produces = "application/json")
	// public @ResponseBody List<MouvementOfVue>
	// getListeMouvementsSortie(@RequestParam Long bonMouvementId) {
	// return bonMouvementService.getListeMouvementsSortie(bonMouvementId);
	// }

	@RequestMapping(value = "/setType", method = RequestMethod.POST)
	public @ResponseBody void setType(@RequestParam String type) {

		List<BonMouvementStockValue> list = bonMouvementService.getByTypeBonMouvement(type);

		logger.info("length of list -----" + list.size());

		for (BonMouvementStockValue bonMouvementStockValue : list) {

			for (MouvementStockValue mouvement : bonMouvementStockValue.getMouvements()) {
				mouvement.setType(type);
			}

			logger.info("bonMouvementStockValue.toString()----" + bonMouvementStockValue.toString());
			String id = bonMouvementService.modifierBonMouvement(bonMouvementStockValue);

			logger.info("id ------" + id);
		}
	}

	// added by Hajer Amri on 01/02/2017
	/*******************************
	 * get bonMouvement Stock par numero
	 *********************************/
	@RequestMapping(value = "/rechercheBonMouvementParNum:{numero}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonMouvementStockValue rechercheBonMouvementParNum(@PathVariable String numero) {
		return bonMouvementService.rechercheBonMouvementParNum(numero);
	}
	

}
