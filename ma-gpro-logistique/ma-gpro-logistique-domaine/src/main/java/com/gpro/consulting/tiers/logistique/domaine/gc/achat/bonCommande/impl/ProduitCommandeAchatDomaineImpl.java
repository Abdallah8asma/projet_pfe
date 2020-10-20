package com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.IProduitCommandeAchatDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.IProduitCommandeAchatPersistance;

/**
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class ProduitCommandeAchatDomaineImpl implements IProduitCommandeAchatDomaine {

	@Autowired
	private IProduitCommandeAchatPersistance produitCommandePersistance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String create(ProduitCommandeAchatValue produitCommandeValue) {
		return produitCommandePersistance.create(produitCommandeValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitCommandeAchatValue getById(Long id) {
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
	public String update(ProduitCommandeAchatValue produitCommandeValue) {
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
	public List<ProduitCommandeAchatValue> getAll() {
		return produitCommandePersistance.getAll();
	}

}
