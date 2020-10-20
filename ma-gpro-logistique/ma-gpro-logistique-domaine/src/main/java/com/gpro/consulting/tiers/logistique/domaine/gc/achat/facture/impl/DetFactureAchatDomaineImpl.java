package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.IDetFactureAchatDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IDetFactureAchatPersistance;

/**
 * The Class DetFactureAchatDomaineImpl.
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
@Component
public class DetFactureAchatDomaineImpl implements IDetFactureAchatDomaine {

	/** The det facture achat persistance. */
	@Autowired
	private IDetFactureAchatPersistance detFactureAchatPersistance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.
	 * IDetFactureAchatDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.DetFactureAchatValue)
	 */
	@Override
	public String create(DetFactureAchatValue bonFactureValue) {

		return detFactureAchatPersistance.create(bonFactureValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.
	 * IDetFactureAchatDomaine#getById(java.lang.Long)
	 */
	@Override
	public DetFactureAchatValue getById(Long id) {

		return detFactureAchatPersistance.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.
	 * IDetFactureAchatDomaine#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.DetFactureAchatValue)
	 */
	@Override
	public String update(DetFactureAchatValue bonFactureValue) {

		return detFactureAchatPersistance.update(bonFactureValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.
	 * IDetFactureAchatDomaine#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		detFactureAchatPersistance.delete(id);
	}

}
