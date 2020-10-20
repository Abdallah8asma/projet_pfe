package com.gpro.consulting.tiers.logistique.rest.gl.facon.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.service.gl.facon.ITraitementFaconService;

/**
 * Traitement Façon Controller
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@RestController
@RequestMapping("/traitementFacon")
public class TraitementFaconRestImpl {

	@Autowired
	private ITraitementFaconService traitementFaconService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody TraitementFaconValue traitementFaconValue) {

		return traitementFaconService.create(traitementFaconValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public TraitementFaconValue getById(@PathVariable Long id) {

		return traitementFaconService.getById(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody TraitementFaconValue traitementFaconValue) {

		return traitementFaconService.update(traitementFaconValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {

		traitementFaconService.delete(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<TraitementFaconValue> getAll() {

		return traitementFaconService.getAll();
	}

	// Méthode créée pour copier le contenu à partir de la table gl_traitement
	/*
	 * @RequestMapping(value = "/createAll", method = RequestMethod.POST) public
	 * void createAll(@RequestBody List<TraitementFaconValue>
	 * listTraitementFaconValue) {
	 * 
	 * for (TraitementFaconValue traitementFaconValue :
	 * listTraitementFaconValue) { this.create(traitementFaconValue); } }
	 */

}
