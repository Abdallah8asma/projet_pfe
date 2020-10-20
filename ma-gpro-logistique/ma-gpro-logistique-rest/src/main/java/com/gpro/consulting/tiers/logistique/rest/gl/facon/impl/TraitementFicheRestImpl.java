package com.gpro.consulting.tiers.logistique.rest.gl.facon.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;
import com.gpro.consulting.tiers.logistique.service.gl.facon.ITraitementFicheService;

/**
 * Traitement fiche Controller
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@RestController
@RequestMapping("/traitementFiche")
public class TraitementFicheRestImpl {

	@Autowired
	private ITraitementFicheService traitementFicheService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody TraitementFicheValue TraitementFicheValue) {

		return traitementFicheService.create(TraitementFicheValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public TraitementFicheValue getById(@PathVariable Long id) {

		return traitementFicheService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody TraitementFicheValue TraitementFicheValue) {

		return traitementFicheService.update(TraitementFicheValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		traitementFicheService.delete(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<TraitementFicheValue> getAll() {

		return traitementFicheService.getAll();
	}

	@RequestMapping(value = "/setTraitementPU", method = RequestMethod.POST)
	public void setPU(@RequestParam Long id, @RequestParam Double nouveauPU, @RequestParam Long idFiche) {
		traitementFicheService.setTraitementPU(id, nouveauPU, idFiche);
	}
}
