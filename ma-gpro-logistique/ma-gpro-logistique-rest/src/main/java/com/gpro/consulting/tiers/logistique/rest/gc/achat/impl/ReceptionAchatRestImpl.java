package com.gpro.consulting.tiers.logistique.rest.gc.achat.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.rest.utilities.ApiResponse;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande.IBonCommandeAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IReceptionAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IBonCommandeService;

/**
 * The Class ReceptionAchatRestImpl.
 */
@Controller
@RequestMapping("/receptionAchat")
public class ReceptionAchatRestImpl {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ReceptionAchatRestImpl.class);

	/** The reception service. */
	@Autowired
	private IReceptionAchatService receptionService;

	/** The gestionnaire logistique cache service. */
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	
	@Autowired
	private IBonCommandeAchatService bonCommandeAchatService;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	
	@RequestMapping(value = "/validateCommandes", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateCommandes(@RequestBody List<String> refBonCommandesList,
			@RequestParam(value = "receptionAchatId", required = false) Long receptionAchatId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonCommandesList != null && refBonCommandesList.size() > 0) {
			list = bonCommandeAchatService.getProduitElementList(refBonCommandesList, receptionAchatId);

		}

		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recheche bon reception achat value
	 */
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonReceptionAchatValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonReceptionAchatValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		ResultatRechecheBonReceptionAchatValue result = receptionService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			for (ReceptionAchatValue bonReceptionValue : result.getListReceptionAchat()) {
				if (bonReceptionValue != null) {

					PartieInteresseValue client = mapClientById.get(bonReceptionValue.getPartieIntersseId());

					if (client != null) {
						bonReceptionValue.setPartieIntersseAbbreviation(client.getAbreviation());
					}
				}
			}
		}

		return result;
	}

	/**
	 * Creates the bon livraison.
	 *
	 * @param ReceptionAchatValue
	 *            the reception achat value
	 * @return the string
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonLivraison(@RequestBody ReceptionAchatValue ReceptionAchatValue) {

		//logger.info("commandeVente - create: Delegating request to Service layer.");

		return this.receptionService.create(ReceptionAchatValue);
	}

	/**
	 * Gets the bon livraison by id.
	 *
	 * @param id
	 *            the id
	 * @return the bon livraison by id
	 */
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ReceptionAchatValue getBonLivraisonById(@PathVariable Long id) {

		//logger.info("commandeVente - getById: Delegating request id {} to service layer.", id);

		ReceptionAchatValue ReceptionAchatValue = receptionService.getById(id);

		if (ReceptionAchatValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			PartieInteresseValue client = mapClientById.get(ReceptionAchatValue.getPartieIntersseId());
			if (client != null) {
				ReceptionAchatValue.setPartieIntersseAbbreviation(client.getAbreviation());
			}
		}

		return ReceptionAchatValue;
	}
	
	
	
	@RequestMapping(value = "/dupliquerByReference", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ReceptionAchatValue dupliquerByReference(@RequestParam(value = "reference", required = false) String reference) {

		//logger.info("commandeVente - getById: Delegating request id {} to service layer.", id);

		ReceptionAchatValue ReceptionAchatValue = receptionService.getByReference(reference);

		if (ReceptionAchatValue != null) {
			
			
			
			ReceptionAchatValue.setId(null);
			
			if(ReceptionAchatValue.getListProduitReceptions() != null) {
				
				
				for(ProduitReceptionAchatValue prodReception :ReceptionAchatValue.getListProduitReceptions()) {
					
					prodReception.setId(null);
					prodReception.setCommandeAchatId(null);
					
				}
				
			}
			
			/*Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

			PartieInteresseValue client = mapClientById.get(ReceptionAchatValue.getPartieIntersseId());
			if (client != null) {
				ReceptionAchatValue.setPartieIntersseAbbreviation(client.getAbreviation());
			}*/
		}

		return ReceptionAchatValue;
	}

	/**
	 * Update.
	 *
	 * @param bonReceptionValue
	 *            the bon reception value
	 * @return the string
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody ReceptionAchatValue bonReceptionValue) {

		return this.receptionService.update(bonReceptionValue);
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ApiResponse delete(@PathVariable Long id) {

		String res = receptionService.delete(id);
		
		return new ApiResponse(res);
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ReceptionAchatValue> getAll() {

		//logger.info("getAll");

		return receptionService.getAll();
	}

	
	@RequestMapping(value = "/getAvailableListBonReceptionRefByFournisseur:{idFournisseur}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<BonReceptionVue> getListBonReceptionRefByFournisseur(@PathVariable Long idFournisseur) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return receptionService.getListBonReceptionRefByFournisseur(idFournisseur);
	}
	
	
	@RequestMapping(value = "/getAvailableListBonReceptionRefByFournisseurDeclarer:{idFournisseur}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<BonReceptionVue> getListBonReceptionRefByFournisseurDeclarer(@PathVariable Long idFournisseur) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return receptionService.getListBonReceptionRefByFournisseurDeclarer(idFournisseur);
	}

	
	@RequestMapping(value = "/getAllListBonReceptionRefByFournisseur:{idFournisseur}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ReceptionAchatFactureVue> getAllBonReceptionRefByFournisseur(@PathVariable Long idFournisseur) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return receptionService.getAllListBonReceptionRefByFournisseur(idFournisseur);
	}
	
	@RequestMapping(value = "/getCurrentReference", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference() {
 		
 		return  receptionService.getCurrentReference(Calendar.getInstance(),false);
 	}
	
	
	@RequestMapping(value = "/validerFactureAvoirRetour:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ReceptionAchatValue validerFactureAvoirRetour(@PathVariable String reference) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return receptionService.validerFactureAvoirRetour(reference);
	}
	

	@RequestMapping(value = "/validerByRefBL:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ReceptionAchatValue validerBL(@PathVariable String reference) {

		// logger.info("getAvailableListBonLivraisonRefByClient");
		return receptionService.validerBL(reference);
	}
	
	
	@RequestMapping(value = "/getCurrentReferenceMensuel", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceMensuel() {
 		
 		return  receptionService.getCurrentReferenceMensuel(Calendar.getInstance(),false);
 	}
	
	
	@RequestMapping(value = "/getCurrentReferenceMensuelByTypeAndDate:{type}:{date}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceMensuelByType(@PathVariable String type,@PathVariable String date) {
 		
 		return  receptionService.getCurrentReferenceMensuelByType(type,stringToCalendar(date),false);
 	}
	
	
	private Calendar stringToCalendar(String dateString) {
		
		Calendar dateCalendar = null;
		
		if(isNotEmty(dateString)){
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));
				
			} catch (ParseException e) {
				logger.error("parse date exception: "+e.getMessage());
			}
		}
		
		return dateCalendar;
	}
	

	  private boolean isNotEmty(String value) {
		    return value != null && !"".equals(value);

		  }
	

}
