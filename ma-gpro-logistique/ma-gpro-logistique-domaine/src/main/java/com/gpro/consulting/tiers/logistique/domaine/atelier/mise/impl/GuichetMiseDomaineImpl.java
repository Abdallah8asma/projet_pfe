package com.gpro.consulting.tiers.logistique.domaine.atelier.mise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.GuichetMiseValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IGuichetMiseDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IGuichetMisePersistance;

/**
 *  Générateur de référence de la mise

 * @author rkhaskho
 *
 */

@Component
public class GuichetMiseDomaineImpl  implements IGuichetMiseDomaine{
	
	/** Service Persisantce */
	@Autowired
	IGuichetMisePersistance guichetMisePersistance;

	/**
	 *  (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.Mise.IGuichetMiseDomaine#getNextNumReference()
	 */
	@Override
	public Long getNextNumReference() {
		return this.guichetMisePersistance.getNextNumReference();
	}

	/**
	 *  (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.Mise.IGuichetMiseDomaine#modifierGuichetMise(com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.GuichetMiseValue)
	 */
	@Override
	public Long modifierGuichetMise(GuichetMiseValue pGuichetValeur) {
		return this.guichetMisePersistance.modifierGuichetMise(pGuichetValeur);
	}


	
	

}
