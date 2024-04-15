/**
 * 
 */
package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IDeviseService;

/**
 * @author ameni
 *
 */
@Controller
@RequestMapping("/devise")
public class DevisePartieInteresseeRestImpl {
	@Autowired
	private IDeviseService devisePartieInteresseeService;

	List<DeviseValue> listeType = new ArrayList<>();

	/************* get Devise By ID *************/
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DeviseValue getDevise(@PathVariable Long id) {
		DeviseValue pDeviseValue = new DeviseValue();
		pDeviseValue.setId(id);
		return devisePartieInteresseeService.rechercheDeviseParId(pDeviseValue);
	}

	/********** all type ***********/
	/**
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DeviseValue> viewAllType() {
		return this.listeType = devisePartieInteresseeService.listeDevise();
	}

	/**
	 * @param pDeviseValue
	 * @return
	 */
	@RequestMapping(value = "/creerDeviseProduit", method = RequestMethod.POST)
	public @ResponseBody String creerDeviseProduit(
			@RequestBody DeviseValue pDeviseValue) {
		// transformation et transcodage des referenciel
		return devisePartieInteresseeService.creerDevise(pDeviseValue);
	}

	/**
	 * @param pDeviseValue
	 * @return
	 */
	@RequestMapping(value = "/modifierDeviseProduit", method = RequestMethod.POST)
	public @ResponseBody String modifierDeviseProduit(
			@RequestBody DeviseValue pDeviseValue) {
		return this.devisePartieInteresseeService.modifierDevise(pDeviseValue);
	}

	/**
	 * @param id
	 */
	@RequestMapping(value = "/supprimerDeviseProduit:{id}", method = RequestMethod.DELETE)
	public void supprimerDevise(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		DeviseValue color = new DeviseValue();
		color.setId(idSupp);
		devisePartieInteresseeService.supprimerDevise(idSupp);
	}

	/**
	 * @return the listeType
	 */
	public List<DeviseValue> getListeType() {
		return listeType;
	}

	/**
	 * @param listeType
	 *            the listeType to set
	 */
	public void setListeType(List<DeviseValue> listeType) {
		this.listeType = listeType;
	}

}
