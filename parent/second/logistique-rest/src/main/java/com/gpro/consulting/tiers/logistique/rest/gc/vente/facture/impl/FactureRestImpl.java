package com.gpro.consulting.tiers.logistique.rest.gc.vente.facture.impl;

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

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ValiderBLPassagerValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IBonLivraisonService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IDetailsReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IElementReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IFactureService;

/**
 * Facture Vente Controller
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Controller
@RequestMapping("/facture")
public class FactureRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(FactureRestImpl.class);

	@Autowired
	private IFactureService factureService;

	@Autowired
	private IBonLivraisonService bonLivraisonService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@Autowired
	private IModePaiementService modePaiementService;
	
	@Autowired
	private IProduitService produitService;
	
	
	@Autowired
	private IDetailsReglementService detailsReglementService;
	
	@Autowired
	private IElementReglementService elementReglementService;
	

	/** Facture **/
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateBonLivraison(@RequestBody List<String> refBonLivraisonList,
			@RequestParam(value = "factureVenteId", required = false) Long factureVenteId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonLivraisonList != null && refBonLivraisonList.size() > 0) {
			list = bonLivraisonService.getProduitElementList(refBonLivraisonList, factureVenteId);

		}

		return list;
	}
	
	
	@RequestMapping(value = "/validateByOF", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateByOF(@RequestBody List<String> refBonLivraisonList,
			@RequestParam(value = "factureVenteId", required = false) Long factureVenteId) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (refBonLivraisonList != null && refBonLivraisonList.size() > 0) {
			list = bonLivraisonService.getProduitElementListByOF(refBonLivraisonList, factureVenteId);

		}

		return list;
	}
	
	@RequestMapping(value = "/validatePassager", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validatePassager(@RequestBody ValiderBLPassagerValue request) {

		ListProduitElementValue list = new ListProduitElementValue();

		if (request != null && request.getDate() != null && request.getPiId() != null ) {
			list = bonLivraisonService.getProduitElementListForPassager(request);

		}

		return list;
	}

	/** FactureAvoir **/
	@RequestMapping(value = "/validateFacture", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateFacture(@RequestBody List<String> refFactureList,
			@RequestParam(value = "factureVenteId", required = false) Long factureVenteId,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		
		


		ListProduitElementValue list = new ListProduitElementValue();

		if (refFactureList != null && refFactureList.size() > 0) {
			
			//if(clientId != null)
			//System.out.println("validateFacture ClientID = "+clientId);
				
			list = factureService.getProduitElementList(refFactureList, factureVenteId,clientId);
		}

		return list;
	}
	
	/** FactureAvoir getArticleAvoir **/
	@RequestMapping(value = "/getArticleAvoir", method = RequestMethod.GET)
	public @ResponseBody ListProduitElementValue getArticleAvoir(
			@RequestParam(value = "clientId", required = false) Long clientId) {

		return  factureService.getArticleAvoir(clientId);
		
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheFactureValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereFactureValue request) {
		
	
		
		request.setOptimized(this.checkForOptimization(request));
		
		/*if (request.getDateFactureMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateFactureMin(date);
		}*/

		//logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);

		// Facon
		// request.setNatureLivraison("FACON");

		ResultatRechecheFactureValue result = factureService.rechercherMultiCritere(request);

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

			for (FactureVenteValue factureVenteValue : result.getList()) {
				if (factureVenteValue != null) {

					PartieInteresseValue client = mapClientById.get(factureVenteValue.getPartieIntId());
					ModePaiementValue modePaiement = mapModePaiementById.get(factureVenteValue.getModepaiementId());

					if (client != null) {
						factureVenteValue.setPartieIntAbreviation(client.getAbreviation());
					}
					if (modePaiement != null) {
						factureVenteValue.setModepaiement(modePaiement.getDesignation());
					}
					
					
				/*if(factureVenteValue.getReglementId() != null) {
						
						RechercheMulticritereDetailReglementValue reqDetailReglement = new RechercheMulticritereDetailReglementValue();
						reqDetailReglement.setReglementId(factureVenteValue.getReglementId() );
                        Double montantPaye = detailsReglementService.getMontantPayer(reqDetailReglement);
						
                        if(factureVenteValue.getMontantTTC() != null)
                        factureVenteValue.setMontantOuvert(factureVenteValue.getMontantTTC() - montantPaye);
						
					}else
					{
						
						  factureVenteValue.setMontantOuvert(factureVenteValue.getMontantTTC());
					}*/
					
					
					//TODO chercher les element reglement de cette facture
					
				
				      Double montantPaye = elementReglementService.getSumMontantPayerByReferenceFacture(factureVenteValue.getReference());
				      
				      Double montantAvoir = new Double(0);
				      
				      
				      RechercheMulticritereFactureValue requestAvoir = new RechercheMulticritereFactureValue();
				      
				      requestAvoir.setType(IConstanteCommerciale.FACTURE_TYPE_AVOIR);
				      requestAvoir.setInfoLivraison(factureVenteValue.getReference());
				      ResultatRechecheFactureValue resultAvoir = factureService.rechercherMultiCritere(requestAvoir);
				      
				      
				      
				      for(FactureVenteValue factureAvoir : resultAvoir.getList()) {
				    	  
				    	  montantAvoir += factureAvoir.getMontantTTC() ;
				      }
				      
					
				      factureVenteValue.setMontantOuvert(factureVenteValue.getMontantTTC() - montantAvoir - montantPaye);
				      
				      if(factureVenteValue.getMontantOuvert() < 0 )
				    	  factureVenteValue.setMontantOuvert(0d);
					
					
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody FactureVenteValue factureValue) {

		//logger.info("createFacture: Delegating request to Service layer.");

		return this.factureService.createFacture(factureValue);
	}

	@RequestMapping(value = "/getFactureById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody FactureVenteValue getFactureById(@PathVariable Long id) {

		//logger.info("getFactureById: Delegating request id {} to service layer.", id);

		FactureVenteValue factureVenteValue = factureService.getFactureById(id);
		
		factureVenteValue.setRefAvantChangement(factureVenteValue.getReference());

		if (factureVenteValue != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(factureVenteValue.getPartieIntId());
			if (client != null) {
				factureVenteValue.setPartieIntAbreviation(client.getAbreviation());
			}
			
			
			for(DetFactureVenteValue factureDet :factureVenteValue.getListDetFactureVente()) {
				
				if(factureDet.getProduitId() != null) {
					ProduitValue prod = produitService.rechercheProduitById(factureDet.getProduitId());
					
					factureDet.setProduitReference(prod.getReference());
					factureDet.setProduitDesignation(prod.getDesignation());
				
				}
			
				
			}
				
				
			
		}

		return factureVenteValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody FactureVenteValue factureValue) {

		//logger.info("UpdateFactureVente: Delegating request to service layer.");

		return this.factureService.updateFacture(factureValue);
	}

	@RequestMapping(value = "/deleteFacture:{id}", method = RequestMethod.DELETE)
	public void deleteFacture(@PathVariable Long id) {

		//logger.info("deleteFacture: Delegating request id {} to service layer.", id);

		factureService.deleteFacture(id);
	}

	@RequestMapping(value = "/getAvailableListFactureRefByClient:{idClient}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<FactureVenteVue> getListFactureRefByClient(@PathVariable Long idClient) {

		//logger.info("getAvailableListFactureRefByClient");
		return factureService.getListFactureRefByClient(idClient);
	}

	
	
	
	@RequestMapping(value = "/getAvailableListFactureRefByClientAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<FactureVenteVue> getListFactureRefByClientAll() {

	//logger.info("getAvailableListFactureRefByClient");
		return factureService.getListFactureRefByClientAll();
	}
	
	
	
	
	
	// Added on 11/10/2016, by Zeineb Medimagh
	/** Facture Façon **/
	@RequestMapping(value = "/validateFacon", method = RequestMethod.POST)
	public @ResponseBody ListTraitFaconElementValue validateBonLivraisonFacon(
			@RequestBody List<String> refBonLivraisonList,
			@RequestParam(value = "factureVenteId", required = false) Long factureVenteId) {

		ListTraitFaconElementValue list = new ListTraitFaconElementValue();

		if (refBonLivraisonList != null && refBonLivraisonList.size() > 0) {
			list = bonLivraisonService.getTraitementFaconElementList(refBonLivraisonList, factureVenteId);
		}

		return list;
	}
	
	@RequestMapping(value = "/getCurrentReferenceByTypeFacture:{type}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference(@PathVariable String type) {
 		
 		return  factureService.getCurrentReference(type,Calendar.getInstance(),false);
 	}
	
	
	@RequestMapping(value = "/getCurrentReferenceByTypeFactureAndDeclarer:{type}:{declarer}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceByTypeFactureAndDeclarer(@PathVariable String type , @PathVariable boolean declarer) {
 		
 		return  factureService.getCurrentReferenceByTypeFactureAndDeclarer(type,declarer,Calendar.getInstance(),false);
 	}
	
	@RequestMapping(value = "/getFactureByReference:{reference}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody FactureVenteValue getFactureByReference(@PathVariable String reference) {

		//logger.info("getFactureById: Delegating request id {} to service layer.", id);

		FactureVenteValue factureVenteValue = factureService.getFactureByReference(reference);

		if (factureVenteValue != null) {

			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(factureVenteValue.getPartieIntId());
			if (client != null) {
				factureVenteValue.setPartieIntAbreviation(client.getAbreviation());
			}
			
			
			for(DetFactureVenteValue factureDet :factureVenteValue.getListDetFactureVente()) {
				
				if(factureDet.getProduitId() != null) {
					ProduitValue prod = produitService.rechercheProduitById(factureDet.getProduitId());
					
					factureDet.setProduitReference(prod.getReference());
					factureDet.setProduitDesignation(prod.getDesignation());
				
				}
			
				
			}
				
				
			
		}

		return factureVenteValue;
	}
	
	public boolean checkForOptimization(RechercheMulticritereFactureValue request) {

		return

		isNullOrEmpty(request.getDateFactureMin())
		&& isNullOrEmpty(request.getDateFactureMax())
		&& isNullOrEmpty(request.getPartieIntId())		
		&& isNullOrEmpty(request.getGroupeClientId());

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
}
