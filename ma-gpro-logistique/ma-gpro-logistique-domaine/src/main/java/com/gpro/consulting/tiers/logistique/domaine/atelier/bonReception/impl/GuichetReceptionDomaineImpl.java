package com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.IGuichetBonReceptionDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.IGuichetBonReceptionPersistance;

/**
 *  Générateur de référence du Bon Reception

 * @author rkhaskho
 *
 */

@Component
public class GuichetReceptionDomaineImpl  implements IGuichetBonReceptionDomaine{
	
	/** Service Persisantce */
	@Autowired
	IGuichetBonReceptionPersistance guichetBonReceptionPersistance;

	/**
	 *  (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.bonReception.IGuichetBonReceptionDomaine#getNextNumReference()
	 */
	@Override
	public Long getNextNumReference() {
		return this.guichetBonReceptionPersistance.getNextNumReference();
	}

	/**
	 *  (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.bonReception.IGuichetBonReceptionDomaine#modifierGuichetBonReception(com.gpro.consulting.tiers.logistique.coordination.bonReception.value.GuichetBonReceptionValue)
	 */
	@Override
	public Long modifierGuichetBonReception(GuichetBonReceptionValue pGuichetValeur) {
		return this.guichetBonReceptionPersistance.modifierGuichetBonReception(pGuichetValeur);
	}
}
