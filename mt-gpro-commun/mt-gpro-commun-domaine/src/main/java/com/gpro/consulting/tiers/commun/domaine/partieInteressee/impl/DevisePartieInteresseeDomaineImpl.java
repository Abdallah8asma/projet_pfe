/**
 * 
 */
package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IDeviseDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IDevisePersistance;

/**
 * @author ameni
 *
 */

@Component
public class DevisePartieInteresseeDomaineImpl implements IDeviseDomaine {

	@Autowired
	IDevisePersistance devisePartieInteressePersistance;

	/************************ Creation Devise ***************************/
	@Override
	public String creerDevise(DeviseValue pDeviseValue) {
		return devisePartieInteressePersistance.creerDevise(pDeviseValue);
	}

	/*********************** suppression Devise **************************/
	@Override
	public void supprimerDevise(Long pId) {
		devisePartieInteressePersistance.supprimerDevise(pId);
	}

	/************************ Modifier Devise ****************************/
	@Override
	public String modifierDevise(DeviseValue pDeviseValue) {
		return devisePartieInteressePersistance.modifierDevise(pDeviseValue);
	}

	/************************ Rechercher Devise ***************************/
	@Override
	public DeviseValue rechercheDeviseParId(DeviseValue pDeviseValue) {
		return devisePartieInteressePersistance
				.rechercheDeviseParId(pDeviseValue);
	}

	/************************ Liste des Devise ***************************/
	@Override
	public List<DeviseValue> listeDevise() {
		return devisePartieInteressePersistance.listeDevise();
	}

}