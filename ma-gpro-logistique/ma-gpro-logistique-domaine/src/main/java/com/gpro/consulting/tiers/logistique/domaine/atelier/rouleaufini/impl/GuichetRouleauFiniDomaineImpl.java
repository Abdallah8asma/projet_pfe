package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.GuichetRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IGuichetRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IGuichetRouleauFiniPersistance;

/**
 *  Générateur de référence du Rouleau Fini

 * @author rkhaskho
 *
 */

@Component
public class GuichetRouleauFiniDomaineImpl  implements IGuichetRouleauFiniDomaine{
	
	/** Service Persisantce */
	@Autowired
	IGuichetRouleauFiniPersistance guichetRouleauFiniPersistance;

	/**
	 *  (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.rouleaufini.IGuichetRouleauFiniDomaine#getNextNumReference()
	 */
	@Override
	public Long getNextNumReference() {
		return this.guichetRouleauFiniPersistance.getNextNumReference();
	}

	/**
	 *  (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.rouleaufini.IGuichetRouleauFiniDomaine#modifierGuichetBonReception(com.gpro.consulting.tiers.logistique.coordination.rouleaufini.value.GuichetRouleauFiniValue)
	 */
	@Override
	public Long modifierGuichetBonReception(GuichetRouleauFiniValue pGuichetValeur) {
		return this.guichetRouleauFiniPersistance.modifierGuichetRouleauFini(pGuichetValeur);
	}

	@Override
	public Integer getPrefixe() {
		// TODO Auto-generated method stub
		return this.guichetRouleauFiniPersistance.getPrefixe();
	}
}
