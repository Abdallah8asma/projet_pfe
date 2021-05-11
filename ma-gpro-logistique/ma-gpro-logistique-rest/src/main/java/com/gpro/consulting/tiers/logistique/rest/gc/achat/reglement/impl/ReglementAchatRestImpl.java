package com.gpro.consulting.tiers.logistique.rest.gc.achat.reglement.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.IReglementAchatService;

/**
 * Reglement Controller
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@RestController
@RequestMapping("/reglementAchat")
public class ReglementAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ReglementAchatRestImpl.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Autowired
	private IReglementAchatService reglementAchatService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheReglementAchatValue rechercherMultiCritere(@RequestBody RechercheMulticritereReglementAchatValue request) {
		 
		//logger.info(" Delegating request to service layer.");
		
		ResultatRechecheReglementAchatValue result = reglementAchatService.rechercherMultiCritere(request);
		
		if(result != null){

			for(ResultatRechecheReglementAchatElementValue element : result.getList()){
		    	
				if(element.getPartieIntId() != null){
					
					PartieInteresseValue partieInteresse = prtieInteresseeService.getById(element.getPartieIntId());
					
					if(partieInteresse != null){
						
			    		element.setPartieIntAbreviation(partieInteresse.getAbreviation());
			    		element.setPartieIntReference(partieInteresse.getReference());
					}

		    	}
				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody ReglementAchatValue reglement) {
		
		
		 Set<ElementReglementAchatValue> listElementReglement = new HashSet<ElementReglementAchatValue>();
		
		//logger.info("Delegating request to Service layer.");
		
		if(reglement != null){
			
			if(reglement.getListElementReglement() != null){
				
				if(reglement.getListElementReglement().size() > 0){
					
					for(ElementReglementAchatValue elementReglement : reglement.getListElementReglement()){
						
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
		
		return reglementAchatService.create(reglement);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public ReglementAchatValue getById(@PathVariable Long id) {
		  
		//logger.info("Delegating request id: {} to service layer.", id);
		
		return reglementAchatService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody ReglementAchatValue reglement) {
	    
		//logger.info("Delegating request to service layer.");
		
		return reglementAchatService.update(reglement);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("Delegating request id: {} to service layer.", id);
		  
		reglementAchatService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<ReglementAchatValue> getAll() {
		  
		//logger.info("Delegating request to service layer.");
		
		return reglementAchatService.getAll();
	}
	
	@RequestMapping(value = "/validateByFournisseurId:{clientId}", method = RequestMethod.GET, produces = "application/json")
	public ValidateReglementResultValue validate(@PathVariable Long clientId) {
		  
		//logger.info("Delegating request id: {} to service layer.", clientId);
		
		return reglementAchatService.validateByFournisseurId(clientId);
	}
	
	
	@RequestMapping(value = "/validateByGroupeFournisseurId:{groupeClientId}", method = RequestMethod.GET, produces = "application/json")
	public ValidateReglementResultValue validateGroupeFournisseur(@PathVariable Long groupeClientId) {
		  
		//logger.info("Delegating request id: {} to service layer.", clientId);
		
		return reglementAchatService.validateByGroupeFournisseurId(groupeClientId);
	}

	@RequestMapping(value = "/listeRefReglement", method = RequestMethod.GET, produces =  "application/json")
	public List< ReglementAchatValue> getRefReglementValue() {
		//logger.info("Delegating request to service layer.");
		return  reglementAchatService.listeRefReglementCache();
	}
	
	@RequestMapping(value = "/listeRefFactureNonRegleByFournisseurId:{clientId}", method = RequestMethod.GET, produces =  "application/json")
	public List< RefFactureNonRegleValue> getRefFactureNonRegleByFournisseurId(@PathVariable Long clientId) {
		//logger.info("Delegating request to service layer.");
		return  reglementAchatService.getRefFactureNonRegleByFournisseurId(clientId);
	}
	
	@RequestMapping(value = "/listRefBRNonRegleByFournisseurId:{clientId}", method = RequestMethod.GET, produces =  "application/json")
	public List< RefLivraisonNonRegleValue> getRefBLNonRegleByClientId(@PathVariable Long clientId) {
		//logger.info("Delegating request to service layer.");
		return  reglementAchatService.getRefBLNonRegleByFournisseurId(clientId);
	}
	
	@RequestMapping(value = "/getCurrentReference", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReference() {
 		
 		return  reglementAchatService.getCurrentReference(Calendar.getInstance(),false);
 	}
	
	
	@RequestMapping(value = "/getCurrentReferenceMensuelByDate:{date}", method = RequestMethod.GET, produces =  "application/json")
 	public @ResponseBody String getCurrentReferenceMensuelByDate( @PathVariable String date) {
 		
 		return  reglementAchatService.getCurrentReferenceMensuelByDate(stringToCalendar(date),false);
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
