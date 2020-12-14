package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IMoldsService;

@Controller
@RequestMapping("/molds")
public class MoldsRestImpl {

	@Autowired
	private IMoldsService ebMoldsService;

	List<MoldsValue> listeType = new ArrayList<>();

	/************* get Molds By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody MoldsValue getMolds(@PathVariable Long id) {
		MoldsValue pMoldsValue = new MoldsValue();
		pMoldsValue.setId(id);
		return ebMoldsService.rechercheMoldsParId(pMoldsValue);
	}

	/********** all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MoldsValue> viewAllType() {
		return this.listeType = ebMoldsService.listeMolds();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String creerMoldsProduit(@RequestBody MoldsValue pMoldsValue) {
		// transformation et transcodage des referenciel
		return ebMoldsService.creerMolds(pMoldsValue);
	}

	@RequestMapping(value = "/modifierMoldsProduit", method = RequestMethod.POST)
	public @ResponseBody String modifierMoldsProduit(@RequestBody MoldsValue pMoldsValue) {
		return this.ebMoldsService.modifierMolds(pMoldsValue);
	}

	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void supprimerMolds(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		MoldsValue color = new MoldsValue();
		color.setId(idSupp);
		ebMoldsService.supprimerMolds(idSupp);
	}

}
