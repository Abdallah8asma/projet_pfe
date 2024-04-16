package com.gpro.consulting.tiers.logistique.rest.gl.facon.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.RechercheMulticritereFicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gl.facon.IFicheFaconService;

/**
 * Fiche Façon Controller
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@RestController
@RequestMapping("/ficheFacon")
public class FicheFaconRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheFaconRestImpl.class);
	
	@Autowired
	private IFicheFaconService ficheFaconService;
	
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody FicheFaconValue FicheFaconValue) {
		
		return ficheFaconService.create(FicheFaconValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public FicheFaconValue getById(@PathVariable Long id) {
		  
		return ficheFaconService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody FicheFaconValue FicheFaconValue) {
	    
		return ficheFaconService.update(FicheFaconValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		ficheFaconService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<FicheFaconValue> getAll() {
		  
		return ficheFaconService.getAll();
	}
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheFicheFaconValue rechercherMultiCritere(@RequestBody RechercheMulticritereFicheFaconValue request) {
		 
		//logger.info("rechercheMulticritere: Delegating request to service layer.");
		
		ResultatRechecheFicheFaconValue result = ficheFaconService.rechercherMultiCritere(request);
		
		if( result != null){
			
			Map<Long, ProduitValue> mapProduitById = gestionnaireLogistiqueCacheService.mapPtoduitById();
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			
			for(ResultatRechecheFicheFaconElementValue element : result.getList()){
				
				Long produitId = element.getProduitId();
				Long clientId = element.getPartieIntId();
				
				if(produitId != null){
					
					if(mapProduitById.containsKey(produitId)){
						
						ProduitValue produit = mapProduitById.get(produitId);
						
						if(produit != null){							
							element.setProduitReference(produit.getReference());
							element.setProduitDesignation(produit.getDesignation());	
							element.setCodeCouleur(produit.getCodeCouleur());
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
