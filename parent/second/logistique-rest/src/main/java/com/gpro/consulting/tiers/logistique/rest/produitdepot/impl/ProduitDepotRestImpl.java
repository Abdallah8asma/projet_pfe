package com.gpro.consulting.tiers.logistique.rest.produitdepot.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.rest.produitdepot.IProduitDepotRest;
import com.gpro.consulting.tiers.logistique.service.produitdepot.IProduitDepotService;


@Controller
@RequestMapping("/ProduitDepot")
public class ProduitDepotRestImpl implements IProduitDepotRest {
	
	
	 @Autowired
	  private IProduitDepotService produitdepotservice;
	  

	
	 @RequestMapping(value = "/rechercheMulticriterePrDe", method = RequestMethod.POST)
	  public @ResponseBody ResultatRechercheProduitDepotValue rechercherProduitDepot(
	    @RequestBody final RechercheMulticritereProduitDepotValue pRechercheMultiCritere) {
	   
		 //System.out.println("rest: id depot: "+pRechercheMultiCritere.getIdDepot()+" idproduit: "+pRechercheMultiCritere.getIdProduit());
		  ResultatRechercheProduitDepotValue vResultatRecherche = produitdepotservice.rechercherProduitDepotMultiCritere(pRechercheMultiCritere);
	    //Traitement : transformation de l'Id sousFamille a sa propre Designation pour chaque Article
	  
	    
	    return vResultatRecherche;
	  
	
	
	

}
}