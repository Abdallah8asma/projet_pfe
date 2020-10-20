/**
 * 
 */
package com.gpro.consulting.tiers.logistique.rest.atelier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.service.atelier.bonReception.ITraitementService;

/**
 * @author Ameni
 *
 */
@Controller
@RequestMapping("/traitement")
public class TraitementRestImpl {

	@Autowired
	private ITraitementService vTraitementService;

	public TraitementRestImpl() {
		// Constructeur vide
	}

	/*******************************
	 * recherche multicritere Traitement
	 *********************************/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TraitementValue> viewAllTraitement() {
		return vTraitementService.listeTraitement();
	}

	/**********************
	 * Méthode de modification d'un Traitement
	 **********************/
	/**
	 * @param pPhaseOfValue
	 * @return
	 */
	@RequestMapping(value = "/modifieTraitement", method = RequestMethod.POST)
	public @ResponseBody String modifierTraitement(@RequestBody TraitementValue pTraitementValue) {
		return this.vTraitementService.modifierTraitement(pTraitementValue);

	}

	/**********************
	 * Méthode de modification d'un Traitement
	 **********************/
	@RequestMapping(value = "/supprimerTraitement:{id}", method = RequestMethod.DELETE)
	public void supprimerTraitement(@PathVariable("id") String id) {
		vTraitementService.supprimerTraitement(Long.valueOf(id));

	}

	@RequestMapping(value = "/creerTraitement", method = RequestMethod.POST)
	public @ResponseBody String creerTraitement(@RequestBody TraitementValue pTraitementValue) {

		return this.vTraitementService.creerTraitement(pTraitementValue);
	}

}
