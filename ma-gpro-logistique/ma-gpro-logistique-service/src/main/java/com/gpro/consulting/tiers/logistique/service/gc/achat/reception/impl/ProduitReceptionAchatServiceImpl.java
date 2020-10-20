package com.gpro.consulting.tiers.logistique.service.gc.achat.reception.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.IProduitReceptionAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IProduitReceptionAchatService;

/**
 * The Class ProduitReceptionAchatServiceImpl.
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */

@Component
public class ProduitReceptionAchatServiceImpl implements IProduitReceptionAchatService {

	/** The produit reception domaine. */
	@Autowired
	private IProduitReceptionAchatDomaine produitReceptionDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitReceptionAchatValue)
	 */
	@Override
	public String create(ProduitReceptionAchatValue ProduitReceptionAchatValue) {
		return produitReceptionDomaine.create(ProduitReceptionAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitReceptionAchatValue getById(Long id) {
		return produitReceptionDomaine.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitReceptionAchatValue)
	 */
	@Override
	public String update(ProduitReceptionAchatValue ProduitReceptionAchatValue) {
		// TODO Auto-generated method stub
		return produitReceptionDomaine.update(ProduitReceptionAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.reception.
	 * IProduitReceptionAchatService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		produitReceptionDomaine.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getAll()
	 */
	@Override
	public List<ProduitReceptionAchatValue> getAll() {
		return produitReceptionDomaine.getAll();
	}

	@Override
	public ResultatRechecheProduitReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereProduitReceptionAchatValue request) {
		// TODO Auto-generated method stub
		return produitReceptionDomaine.rechercherMultiCritere(request);
	}

}
