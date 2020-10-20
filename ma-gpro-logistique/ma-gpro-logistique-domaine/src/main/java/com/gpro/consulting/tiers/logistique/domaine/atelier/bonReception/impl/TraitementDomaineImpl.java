/**
 * 
 */
package com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.ITraitementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.ITraitementPersistance;

/**
 * @author Ameni
 *
 */

@Component
public class TraitementDomaineImpl implements ITraitementDomaine {

	/** bean Persisatncce */
	  @Autowired
	  private ITraitementPersistance vTraitementPersistance;   

	  
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerTraitement(TraitementValue pTraitementValue) {
		return vTraitementPersistance.creerTraitement(pTraitementValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerTraitement(Long pId) {
		vTraitementPersistance.supprimerTraitement(pId);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifierTraitement(TraitementValue pTraitementValue) {
		return vTraitementPersistance.modifierTraitement(pTraitementValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TraitementValue> listeTraitement() {
		return vTraitementPersistance.listeTraitement();
	}

	@Override
	public TraitementValue getTraitementParId(Long id) {
		return vTraitementPersistance.getTraitementParId(id);
	}
	
}
