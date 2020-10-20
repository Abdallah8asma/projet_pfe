package com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IProduitCommandePersistance;

/**
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class ProduitCommandeDomaineImpl implements IProduitCommandeDomaine {

	@Autowired
	private IProduitCommandePersistance produitCommandePersistance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String create(ProduitCommandeValue produitCommandeValue) {
		return produitCommandePersistance.create(produitCommandeValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitCommandeValue getById(Long id) {
		return produitCommandePersistance.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String update(ProduitCommandeValue produitCommandeValue) {
		// TODO Auto-generated method stub
		return produitCommandePersistance.update(produitCommandeValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		produitCommandePersistance.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getAll()
	 */
	@Override
	public List<ProduitCommandeValue> getAll() {
		return produitCommandePersistance.getAll();
	}

}
