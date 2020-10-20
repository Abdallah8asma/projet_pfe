package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.IProduitReceptionAchatDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IProduitReceptionAchatPersistance;

/**
 * @author Hamdi Etteieb
 * @since 23/09/2018
 *
 */

@Component
public class ProduitReceptionAchatDomaineImpl implements IProduitReceptionAchatDomaine {

	@Autowired
	private IProduitReceptionAchatPersistance produitReceptionPersistance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ProduitReceptionAchatValue)
	 */
	@Override
	public String create(ProduitReceptionAchatValue ProduitReceptionAchatValue) {
		return produitReceptionPersistance.create(ProduitReceptionAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getById(java.lang.Long)
	 */
	@Override
	public ProduitReceptionAchatValue getById(Long id) {
		return produitReceptionPersistance.getById(id);
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
		return produitReceptionPersistance.update(ProduitReceptionAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		produitReceptionPersistance.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IProduitCommandeDomaine#getAll()
	 */
	@Override
	public List<ProduitReceptionAchatValue> getAll() {
		return produitReceptionPersistance.getAll();
	}

	@Override
	public ResultatRechecheProduitReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereProduitReceptionAchatValue request) {
		// TODO Auto-generated method stub
		return produitReceptionPersistance.rechercherMultiCritere(request);
	}

	@Override
	public List<ResultBestElementValue> getTop10ArticleAchat(RechercheMulticritereProduitReceptionAchatValue request) {
		
		return produitReceptionPersistance.getTop10ArticleAchat(request);
		
	}

	@Override
	public List<ResultBestElementValue> getTop3Fournisseur(RechercheMulticritereProduitReceptionAchatValue request) {
		
		return produitReceptionPersistance.getTop3Fournisseur(request);
	}

	@Override
	public List<ResultBestElementValue> getDepenseFournisseur(RechercheMulticritereProduitReceptionAchatValue request) {
		
		return produitReceptionPersistance.getDepenseFournisseur(request);
	}

}
