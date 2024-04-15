package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitSerialisableDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitSerialisableService;

/**
 * The Class ProduitSerialisableServiceImpl.
 * @author med
 */
@Service
@Transactional
public class ProduitSerialisableServiceImpl implements IProduitSerialisableService{
	/** The produit domaine. */
	@Autowired
	IProduitSerialisableDomaine produitSerialisableDomaine;

	//creer produit
	@Override
	public String creerProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue) {
		return produitSerialisableDomaine.creerProduitSerialisable(pProduitSerialisableValue);
	}
    
	//supprimer
	@Override
	public void supprimerProduitSerialisable(Long pProduitSerialisableId) {
       produitSerialisableDomaine.supprimerProduitSerialisable(pProduitSerialisableId);		
	}

	//modifier
	@Override
	public String modifierProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue) {
		return produitSerialisableDomaine.modifierProduitSerialisable(pProduitSerialisableValue);
	}

	//recherche par id
	@Override
	public ProduitSerialisableValue rechercheProduitSerialisableById(Long pProduitSerialisableId) {
		return produitSerialisableDomaine.rechercheProduitSerialisableById(pProduitSerialisableId);
	}

	//liste produit
	@Override
	public List<ProduitSerialisableValue> listeProduitSerialisable() {
		return produitSerialisableDomaine.listeProduitSerialisable();
	}

	/**
	 * Gets the produit domaine.
	 *
	 * @return the produit domaine
	 */
	public IProduitSerialisableDomaine getProduitSerialisableDomaine() {
		return produitSerialisableDomaine;
	}

	/**
	 * Sets the produit domaine.
	 *
	 * @param produitSerialisableDomaine the new produit domaine
	 */
	public void setProduitSerialisableDomaine(IProduitSerialisableDomaine produitSerialisableDomaine) {
		this.produitSerialisableDomaine = produitSerialisableDomaine;
	}


	@Override
	public ResultatRechecheProduitSerialisableValue rechercherProduitSerialisableMultiCritere(
			RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere) {
		  return produitSerialisableDomaine.rechercherProduitSerialisableMultiCritere(pRechercheProduitSerialisableMulitCritere);

		
	}
	


}
