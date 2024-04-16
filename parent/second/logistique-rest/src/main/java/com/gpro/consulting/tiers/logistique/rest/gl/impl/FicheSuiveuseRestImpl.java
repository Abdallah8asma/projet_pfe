package com.gpro.consulting.tiers.logistique.rest.gl.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.RechercheMulticritereFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseCoutValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseElemCoutValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse.IFicheSuiveuseService;

/**
 * FicheSuiveuse Controller
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */

@RestController
@RequestMapping("/ficheSuiveuse")
public class FicheSuiveuseRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveuseRestImpl.class);
	
	@Autowired
	private IFicheSuiveuseService ficheSuiveuseService;
	
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheFicheSuiveuseValue rechercherMultiCritere(@RequestBody RechercheMulticritereFicheSuiveuseValue request) {
		 
		//logger.info("rechercheMulticritere: Delegating request to service layer.");
		
		ResultatRechecheFicheSuiveuseValue result = ficheSuiveuseService.rechercherMultiCritere(request);
		
		if( result != null){
			
			Map<Long, ProduitValue> mapProduitById = gestionnaireLogistiqueCacheService.mapPtoduitById();
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			
			for(ResultatRechecheFicheSuiveuseElementValue element : result.getList()){
				
				Long produitId = element.getProduitId();
				Long clientId = element.getPartieIntId();
				
				if(produitId != null){
					
					if(mapProduitById.containsKey(produitId)){
						
						ProduitValue produit = mapProduitById.get(produitId);
						
						if(produit != null){
							
							element.setProduitReference(produit.getReference());
							element.setProduitDesignation(produit.getDesignation());	
						}
						
					}

				}
				
				if(clientId != null){
					
					if(mapClientById.containsKey(clientId)){
						
						PartieInteresseValue client = mapClientById.get(clientId);
						
						if(client != null){
							
							element.setClientReference(client.getReference());
							element.setClientAbreviation(client.getAbreviation());
						}
						
					}

				}

			}
		}

		return result;
	 }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody FicheSuiveuseValue ficheSuiveuseValue) {
		
		//logger.info("createFicheSuiveuse: Delegating request to Service layer.");
		
		return ficheSuiveuseService.create(ficheSuiveuseValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public FicheSuiveuseValue getById(@PathVariable Long id) {
		  
		//logger.info("getFicheSuiveuseById: Delegating request id {} to service layer.", id);
		
		return ficheSuiveuseService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody FicheSuiveuseValue ficheSuiveuseValue) {
	    
		//logger.info("UpdateFicheSuiveuse: Delegating request to service layer.");
		
		return ficheSuiveuseService.update(ficheSuiveuseValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("deleteFicheSuiveuse: Delegating request id {} to service layer.", id);
		  
		ficheSuiveuseService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<FicheSuiveuseValue> getAll() {
		  
		//logger.info("getAll: Delegating request id to service layer.");
		
		return ficheSuiveuseService.getAll();
	}

	/***************************** Recherche multicritères fiche suiveuse avec couts ********************************/

	@RequestMapping(value = "/rechercheMulticritereAvecCout", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheFicheSuiveuseCoutValue rechercherMultiCritereAvecCout(@RequestBody RechercheMulticritereFicheSuiveuseValue request) {
		 
		//logger.info("rechercheMulticritereAvecCout: Delegating request to service layer.");
		//logger.info("rechercheMulticritere avec cout : request\n "+ request.toString() +"\n");
		
		ResultatRechecheFicheSuiveuseCoutValue result = ficheSuiveuseService.rechercherMultiCritereAvecCout(request);
		//logger.info("------ REST : Resultat recherche -------- "+ result.toString() +"\n");
		
		if( result != null){
			
			Map<Long, ProduitValue> mapProduitById = gestionnaireLogistiqueCacheService.mapPtoduitById();
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			
			for(ResultatRechecheFicheSuiveuseElemCoutValue element : result.getList()){
				
				Long produitId = element.getProduitId();
				Long clientId = element.getPartieIntId();
				
				if(produitId != null){
					
					if(mapProduitById.containsKey(produitId)){
						
						ProduitValue produit = mapProduitById.get(produitId);
						
						if(produit != null){
							
							element.setProduitReference(produit.getReference());
							element.setProduitDesignation(produit.getDesignation());	
						}
						
					}

				}
				
				if(clientId != null){
					
					if(mapClientById.containsKey(clientId)){
						
						PartieInteresseValue client = mapClientById.get(clientId);
						
						if(client != null){
							
							element.setClientReference(client.getReference());
							element.setClientAbreviation(client.getAbreviation());
						}
						
					}

				}

			}
		}

		return result;
	 }
}
