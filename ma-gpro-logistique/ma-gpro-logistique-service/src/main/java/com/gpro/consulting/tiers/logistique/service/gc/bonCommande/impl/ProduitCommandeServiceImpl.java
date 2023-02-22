package com.gpro.consulting.tiers.logistique.service.gc.bonCommande.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IProduitCommandeDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IProduitCommandeService;

/**
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class ProduitCommandeServiceImpl implements IProduitCommandeService {

	private static final Logger logger = LoggerFactory.getLogger(ProduitCommandeServiceImpl.class);

	@Autowired
	private IProduitCommandeDomaine produitCommandeDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitCommandeValue)
	 */
	@Override
	public String create(ProduitCommandeValue produitCommandeValue) {
		return produitCommandeDomaine.create(produitCommandeValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitCommandeValue getById(Long id) {
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
	public String update(ProduitCommandeValue produitCommandeValue) {
		// TODO Auto-generated method stub
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
	public List<ProduitCommandeValue> getAll() {
		return produitCommandeDomaine.getAll();
	}

	@Override
	public ResultatRechecheProduitBonCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitBonCommandeValue request) {
		// TODO Auto-generated method stub
		return produitCommandeDomaine.rechercherMultiCritere(request);
	}

}
