package com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.IProduitCommandeAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande.IProduitCommandeAchatService;

/**
 * @author Hamdi Etteieb
 * @since 16/09/2018
 * 
 */

@Component
public class ProduitCommandeAchatServiceImpl implements IProduitCommandeAchatService {

	@Autowired
	private IProduitCommandeAchatDomaine produitCommandeDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String create(ProduitCommandeAchatValue produitCommandeValue) {
		return produitCommandeDomaine.create(produitCommandeValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitCommandeAchatValue getById(Long id) {
		return produitCommandeDomaine.getById(id);
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
		return produitCommandeDomaine.update(produitCommandeValue);
	}

	@Override
	public void delete(Long id) {
		produitCommandeDomaine.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getAll()
	 */
	@Override
	public List<ProduitCommandeAchatValue> getAll() {
		return produitCommandeDomaine.getAll();
	}

}
