package com.gpro.consulting.tiers.logistique.rest.gc.achat.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ListProduitAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IFactureAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IReceptionAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IBonLivraisonService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;

/**
 * Facture Achat Controller
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Controller
@RequestMapping("/factureAchat")
public class FactureAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(FactureAchatRestImpl.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private IFactureAchatService factureService;

	@Autowired
	private IBonLivraisonService bonLivraisonService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@Autowired
	private IModePaiementService modePaiementService;
	
	
	@Autowired
	private IReceptionAchatService receptionAchatService;
	
	@Autowired
	private IProduitService produitService;
	
	@Autowired
	private IArticleService articleService;

	/** Facture **/
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateBonReception(@RequestBody List<String> refBonReceptionList,
			@RequestParam(value = "factureAchatId", required = false) Long factureAchatId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonReceptionList != null && refBonReceptionList.size() > 0) {
			list = receptionAchatService.getProduitElementList(refBonReceptionList, factureAchatId);

		}

		return list;
	}
	
	
	@RequestMapping(value = "/validateWithoutRegroupement", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateBonReceptionWithoutRegroupement(@RequestBody List<String> refBonReceptionList,
			@RequestParam(value = "factureAchatId", required = false) Long factureAchatId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonReceptionList != null && refBonReceptionList.size() > 0) {
			list = receptionAchatService.getProduitElementListWithoutRegroupement(refBonReceptionList, factureAchatId);

		}

		return list;
	}

	/** FactureAvoir **/
	@RequestMapping(value = "/validateFacture", method = RequestMethod.POST)
	public @ResponseBody ListProduitAchatElementValue validateFacture(@RequestBody List<String> refFactureList,
			@RequestParam(value = "factureAchatId", required = false) Long factureAchatId) {

		ListProduitAchatElementValue list = new ListProduitAchatElementValue();

		if (refFactureList != null && refFactureList.size() > 0) {
			list = factureService.getProduitElementList(refFactureList, factureAchatId);
		}

		return list;
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheFactureAchatValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereFactureAchatValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		// Facon
		// request.setNatureLivraison("FACON");

		ResultatRechecheFactureAchatValue result = factureService.rechercherMultiCritere(request);

		if (result != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			Map<Long, ModePaiementValue> mapModePaiementById = new HashMap<Long, ModePaiementValue>();

			List<ModePaiementValue> modePaiementList = modePaiementService.getAll();

			for (ModePaiementValue modePaiement : modePaiementList) {
				Long key = modePaiement.getId();
				if (mapModePaiementById.get(key) == null) {
					mapModePaiementById.put(modePaiement.getId(), modePaiement);
				}
			}

			for (FactureAchatValue factureAchatValue : result.getList()) {
				if (factureAchatValue != null) {

					PartieInteresseValue client = mapClientById.get(factureAchatValue.getPartieIntId());
					ModePaiementValue modePaiement = mapModePaiementById.get(factureAchatValue.getModepaiementId());

					if (client != null) {
						factureAchatValue.setPartieIntAbreviation(client.getAbreviation());
					}
					if (modePaiement != null) {
						factureAchatValue.setModepaiement(modePaiement.getDesignation());
					}
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody FactureAchatValue factureValue) {

		//logger.info("createFacture: Delegating request to Service layer.");

		return this.factureService.createFacture(factureValue);
	}

	@RequestMapping(value = "/getFactureById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody FactureAchatValue getFactureById(@PathVariable Long id) {

		//logger.info("getFactureById: Delegating request id {} to service layer.", id);

		FactureAchatValue factureAchatValue = factureService.getFactureById(id);

		if (factureAchatValue != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(factureAchatValue.getPartieIntId());
			if (client != null) {
				factureAchatValue.setPartieIntAbreviation(client.getAbreviation());
			}
			
			
	      /*      for(DetFactureAchatValue factureDet :factureAchatValue.getListDetFactureAchat()) {
				
				if(factureDet.getProduitId() != null) {
					
					
				//	ProduitValue prod = produitService.rechercheProduitById(factureDet.getProduitId());
					ArticleValue articleValue=new ArticleValue();
					articleValue.setId(factureDet.getProduitId());
					
					
					ArticleValue prod=articleService.rechercheArticleParId(articleValue);
					factureDet.setProduitReference(prod.getRef());
					factureDet.setProduitDesignation(prod.getDesignation());
				
				}
			
				
			}*/
			
			
		}

		return factureAchatValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody FactureAchatValue factureValue) {

		//logger.info("UpdateFactureAchat: Delegating request to service layer.");

		return this.factureService.updateFacture(factureValue);
	}

	@RequestMapping(value = "/deleteFacture:{id}", method = RequestMethod.DELETE)
	public void deleteFacture(@PathVariable Long id) {

		//logger.info("deleteFacture: Delegating request id {} to service layer.", id);

		factureService.deleteFacture(id);
	}

	@RequestMapping(value = "/getAvailableListFactureRefByClient:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<FactureAchatVue> getListFactureRefByClient(@PathVariable Long idClient) {

		//logger.info("getAvailableListFactureRefByClient");
		return factureService.getListFactureRefByClient(idClient);
	}

	// Added on 11/10/2016, by Zeineb Medimagh
	/** Facture Façon **/
	@RequestMapping(value = "/validateFacon", method = RequestMethod.POST)
	public @ResponseBody ListTraitFaconElementValue validateBonLivraisonFacon(
			@RequestBody List<String> refBonLivraisonList,
			@RequestParam(value = "factureAchatId", required = false) Long factureAchatId) {

		ListTraitFaconElementValue list = new ListTraitFaconElementValue();

		if (refBonLivraisonList != null && refBonLivraisonList.size() > 0) {
			list = bonLivraisonService.getTraitementFaconElementList(refBonLivraisonList, factureAchatId);
		}

		return list;
	}
	
	
	@RequestMapping(value = "/getCurrentReferenceByTypeFacture:{type}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference(@PathVariable String type) {
 		
 		return  factureService.getCurrentReference(type,Calendar.getInstance(),false);
 	}
	
	/** FactureAvoir Special getArticleAvoirSpecial **/
	@RequestMapping(value = "/getArticleAvoir", method = RequestMethod.GET)
	public @ResponseBody ListProduitElementValue getArticleAvoir(
			@RequestParam(value = "clientId", required = false) Long clientId) {

		return  factureService.getArticleAvoir(clientId);
		
	}
	

	@RequestMapping(value = "/getCurrentReferenceMensuel:{type}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceMensuel(@PathVariable String type) {
 		
 		return  factureService.getCurrentReferenceMensuel(type,Calendar.getInstance(),false);
 	}
	
	@RequestMapping(value = "/getCurrentReferenceMensuelByTypeAndDeclareAndDate:{type}:{declarer}:{date}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceMensuelDeclarer(@PathVariable String type,@PathVariable boolean declarer,@PathVariable String date) {
 		
 		return  factureService.getCurrentReferenceMensuelDeclarer(type,declarer,stringToCalendar(date),false);
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
