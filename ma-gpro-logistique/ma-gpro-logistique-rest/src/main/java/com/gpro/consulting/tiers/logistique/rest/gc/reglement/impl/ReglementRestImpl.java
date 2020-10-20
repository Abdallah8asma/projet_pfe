package com.gpro.consulting.tiers.logistique.rest.gc.reglement.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IReglementService;

/**
 * Reglement Controller
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@RestController
@RequestMapping("/reglement")
public class ReglementRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ReglementRestImpl.class);
	
	@Autowired
	private IReglementService reglementService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	
	
	@Autowired
	private IGroupeClientService groupeClientService;
	
	
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheReglementValue rechercherMultiCritere(@RequestBody RechercheMulticritereReglementValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		ResultatRechecheReglementValue result = reglementService.rechercherMultiCritere(request);
		
		if(result != null){

			for(ResultatRechecheReglementElementValue element : result.getList()){
		    	
				if(element.getPartieIntId() != null){
					
					PartieInteresseValue partieInteresse = prtieInteresseeService.getById(element.getPartieIntId());
					
					if(partieInteresse != null){
						
			    		element.setPartieIntAbreviation(partieInteresse.getAbreviation());
			    		element.setPartieIntReference(partieInteresse.getReference());
			    		
			    		
					}

		    	}
				
				
				if(element.getGroupeClientId() != null)
	    			element.setGroupeClientDesignation(groupeClientService.rechercheGroupeClientParId(new GroupeClientValue(element.getGroupeClientId())).getDesignation());
	    
				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody ReglementValue reglement) {
		
		 Set<ElementReglementValue> listElementReglement = new HashSet<ElementReglementValue>();
		
		//logger.info("Delegating request to Service layer.");
		
		if(reglement != null){
			
			if(reglement.getListElementReglement() != null){
				
				if(reglement.getListElementReglement().size() > 0){
					
					for(ElementReglementValue elementReglement : reglement.getListElementReglement()){
						
						if(elementReglement.getMontantDemande() != null && elementReglement.getMontantDemande() >0)
							listElementReglement.add(elementReglement);
						
						//logger.info("----refBL: "+elementReglement.getRefBL());
						//logger.info("----refFacture: "+elementReglement.getRefFacture());
						//logger.info("----montant: "+elementReglement.getMontant());
					//logger.info("----montantDemande: "+elementReglement.getMontantDemande());
						//logger.info("----dateEcheance: "+elementReglement.getDateEcheance());
					}
				}
			}
		}
		
		reglement.setListElementReglement(listElementReglement);
		
		return reglementService.create(reglement);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public ReglementValue getById(@PathVariable Long id) {
		  
		//logger.info("Delegating request id: {} to service layer.", id);
		
		return reglementService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody ReglementValue reglement) {
	    
		//logger.info("Delegating request to service layer.");
		
		
		 Set<ElementReglementValue> listElementReglement = new HashSet<ElementReglementValue>();
			
		//logger.info("Delegating request to Service layer.");
		
		if(reglement != null){
			
			if(reglement.getListElementReglement() != null){
				
				if(reglement.getListElementReglement().size() > 0){
					
					for(ElementReglementValue elementReglement : reglement.getListElementReglement()){
						
						if(elementReglement.getMontantDemande() != null && elementReglement.getMontantDemande() >0)
							listElementReglement.add(elementReglement);
						
						//logger.info("----refBL: "+elementReglement.getRefBL());
						//logger.info("----refFacture: "+elementReglement.getRefFacture());
						//logger.info("----montant: "+elementReglement.getMontant());
					//logger.info("----montantDemande: "+elementReglement.getMontantDemande());
						//logger.info("----dateEcheance: "+elementReglement.getDateEcheance());
					}
				}
			}
		}
		
		reglement.setListElementReglement(listElementReglement);
		
		
		return reglementService.update(reglement);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("Delegating request id: {} to service layer.", id);
		  
		reglementService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<ReglementValue> getAll() {
		  
		//logger.info("Delegating request to service layer.");
		
		return reglementService.getAll();
	}
	
	@RequestMapping(value = "/validateByClientId:{clientId}", method = RequestMethod.GET, produces = "application/json")
	public ValidateReglementResultValue validate(@PathVariable Long clientId) {
		  
		//logger.info("Delegating request id: {} to service layer.", clientId);
		
		return reglementService.validateByClientId(clientId);
	}
	
	
	@RequestMapping(value = "/validateByGroupeClientId:{groupeClientId}", method = RequestMethod.GET, produces = "application/json")
	public ValidateReglementResultValue validateGroupeClient(@PathVariable Long groupeClientId) {
		  
		//logger.info("Delegating request id: {} to service layer.", clientId);
		
		return reglementService.validateByGroupeClientId(groupeClientId);
	}

	@RequestMapping(value = "/listeRefReglement", method = RequestMethod.GET, produces =  "application/json")
	public List< ReglementValue> getRefReglementValue() {
		//logger.info("Delegating request to service layer.");
		return  reglementService.listeRefReglementCache();
	}
	
	@RequestMapping(value = "/listeRefFactureNonRegleByClientId:{clientId}", method = RequestMethod.GET, produces =  "application/json")
	public List< RefFactureNonRegleValue> getRefFactureNonRegleByClientId(@PathVariable Long clientId) {
		//logger.info("Delegating request to service layer.");
		return  reglementService.getRefFactureNonRegleByClientId(clientId);
	}
	
	@RequestMapping(value = "/listRefBLNonRegleByClientId:{clientId}", method = RequestMethod.GET, produces =  "application/json")
	public List< RefLivraisonNonRegleValue> getRefBLNonRegleByClientId(@PathVariable Long clientId) {
		//logger.info("Delegating request to service layer.");
		return  reglementService.getRefBLNonRegleByClientId(clientId);
	}
	
	
	@RequestMapping(value = "/listeRefFactureNonRegleByGroupeId:{groupeId}", method = RequestMethod.GET, produces =  "application/json")
	public List< RefFactureNonRegleValue> getRefFactureNonRegleByGroupeId(@PathVariable Long groupeId) {
		//logger.info("Delegating request to service layer.");
		return  reglementService.getRefFactureNonRegleByGroupeId(groupeId);
	}
	
	@RequestMapping(value = "/listRefBLNonRegleByGroupeId:{groupeId}", method = RequestMethod.GET, produces =  "application/json")
	public List< RefLivraisonNonRegleValue> getRefBLNonRegleByGroupeId(@PathVariable Long groupeId) {
		//logger.info("Delegating request to service layer.");
		return  reglementService.getRefBLNonRegleByGroupeId(groupeId);
	}
	
	@RequestMapping(value = "/getCurrentReference", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference() {
 		
 		return  reglementService.getCurrentReference(Calendar.getInstance(),false);
 	}
	
}
