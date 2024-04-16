package com.gpro.consulting.tiers.logistique.domaine.gc.reception.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reception.IProduitReceptionDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.IProduitReceptionPersistance;

/**
 *@author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class ProduitReceptionDomaineImpl implements IProduitReceptionDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(ProduitReceptionDomaineImpl.class);

	@Autowired
	private IProduitReceptionPersistance produitReceptionPersistance;
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitReceptionValue)
	 */
	@Override
	public String create(ProduitReceptionValue produitReceptionValue) {
		return produitReceptionPersistance.create(produitReceptionValue);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitReceptionValue getById(Long id) {
		return produitReceptionPersistance.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#update(com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitReceptionValue)
	 */
	@Override
	public String update(ProduitReceptionValue produitReceptionValue) {
		// TODO Auto-generated method stub
		return produitReceptionPersistance.update(produitReceptionValue);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		produitReceptionPersistance.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine#getAll()
	 */
	@Override
	public List<ProduitReceptionValue> getAll() {
		return produitReceptionPersistance.getAll();
	}
	

}
