package com.gpro.consulting.tiers.logistique.domaine.caisse.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheCaisseValue;
import com.gpro.consulting.tiers.logistique.domaine.caisse.ICaisseDomaine;
import com.gpro.consulting.tiers.logistique.persistance.caisse.ICaissePersistance;

// TODO: Auto-generated Javadoc
/**
 * Implementation of {@link ICaisseDomaine}.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
@Component
public class CaisseDomaineImpl implements ICaisseDomaine {

	/** The caisse persistance. */
	@Autowired
	ICaissePersistance caissePersistance;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CaisseDomaineImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.logistique.domaine.caisse.ICaisseDomaine#create
	 * (com.gpro.consulting.tiers.logistique.coordination.caisse.value.
	 * CaisseValue)
	 */
	@Override
	public String create(CaisseValue caisseValue) {
		caisseValue.setMontantCheque(0.0);
		caisseValue.setMontantEspeces(0.0);
		return caissePersistance.create(caisseValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.ICaisseDomaine#
	 * getById(java.lang.Long)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.logistique.domaine.caisse.ICaisseDomaine#update
	 * (com.gpro.consulting.tiers.logistique.coordination.caisse.value.
	 * CaisseValue)
	 */
	@Override
	public String update(CaisseValue caisseValue) {
		return caissePersistance.update(caisseValue);
	}

	@Override
	public CaisseValue getById(Long id) {
		return caissePersistance.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.ICaisseDomaine#
	 * rechercherMultiCritere(com.gpro.consulting.tiers.logistique.coordination.
	 * caisse.value.RechercheMulticritereCaisseValue)
	 */
	@Override
	public ResultatRechercheCaisseValue rechercherMultiCritere(RechercheMulticritereCaisseValue request) {
		return caissePersistance.rechercherMultiCritere(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.logistique.domaine.caisse.ICaisseDomaine#delete
	 * (java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		caissePersistance.delete(id);
	}

	@Override
	public List<CaisseValue> getAll() {
		// TODO Auto-generated method stub
		return caissePersistance.getAll();
	}

	@Override
	public List<CaisseValue> getCAisseNonCloture() {
		// TODO Auto-generated method stub
		return caissePersistance.getCaisseNonCloturer();
	}

}
