package com.gpro.consulting.tiers.logistique.rest.gs.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.impl.EmplacementPersistanceImpl;
import com.gpro.consulting.tiers.logistique.service.gs.IEmplacementService;

@Controller
@RequestMapping("/emplacement")
public class EmplacementRestImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(EmplacementPersistanceImpl.class);

	@Autowired
	private IEmplacementService emplacementService;
	

  /******************************* All Emplacements *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < EmplacementValue > viewAllEmplacement() {
    return emplacementService.listeEmplacementValue();
  }
  
 
  
  /**********************  Méthode de Creation d'un Emplacement **********************/
  @RequestMapping(value = "/creerEmplacement", method = RequestMethod.POST)
  public @ResponseBody String creerEmplacement(@RequestBody EmplacementValue pEmplacementValue) {
    return emplacementService.creerEmplacement(pEmplacementValue);
  }

  /**********************  Méthode de modification d'un Emplacement **********************/
  @RequestMapping(value = "/modifierEmplacement", method = RequestMethod.POST)
  public @ResponseBody String modifierEmplacement(@RequestBody EmplacementValue pEmplacementValue) {
	  logger.info("id rest : "+pEmplacementValue.getId());
    return emplacementService.modifierEmplacement(pEmplacementValue);
  }
  
  /**********************  Méthode de Suppression d'un Article **********************/
  @RequestMapping(value = "/supprimerEmplacement:{id}", method = RequestMethod.DELETE)
  public void supprimerEmplacement(@PathVariable("id") String id) {
	  Long idSupp= Long.parseLong(id);
       emplacementService.supprimerEmplacement(idSupp);
  }


}
