package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitSerialisableDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;

/**
 * The Class ProduitSerialisableDomaineImpl.
 * *@author med
 */

@Component
public class ProduitSerialisableDomaineImpl implements IProduitSerialisableDomaine {
	
	/** The produit persistance. */
	@Autowired
	IProduitSerialisablePersistance produitSerialisablePersistance;


	
	/* 
	 * creer produit
	 */
	@Override
	public String creerProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue) {
		return produitSerialisablePersistance.creerProduitSerialisable(pProduitSerialisableValue);
	}

	/* 
	 * supprimer produit
	 */
	@Override
	public void supprimerProduitSerialisable(Long pProduitSerialisableId) {
		produitSerialisablePersistance.supprimerProduitSerialisable(pProduitSerialisableId);		
	}

	/* 
	 * modifier produit
	 */
	@Override
	public String modifierProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue) {
		return produitSerialisablePersistance.modifierProduitSerialisable(pProduitSerialisableValue);
	}

	/* 
	 * recherche by id produit
	 */
	@Override
	public ProduitSerialisableValue rechercheProduitSerialisableById(Long pProduitSerialisableId) {
		return produitSerialisablePersistance.rechercheProduitSerialisableById(pProduitSerialisableId);
	}

	/* 
	 * liste produit 
	 */
	@Override
	public List<ProduitSerialisableValue> listeProduitSerialisable() {
           return produitSerialisablePersistance.listeProduitSerialisable();
	}

	@Override
	public ResultatRechecheProduitSerialisableValue rechercherProduitSerialisableMultiCritere(
			RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere) {
		return produitSerialisablePersistance.rechercherProduitSerialisableMultiCritere(pRechercheProduitSerialisableMulitCritere);
	}
	
	
	
}
