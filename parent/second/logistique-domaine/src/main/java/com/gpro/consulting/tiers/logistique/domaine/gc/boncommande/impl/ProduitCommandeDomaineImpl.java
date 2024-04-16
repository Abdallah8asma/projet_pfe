package com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl.DevisePartieInteresseeDomaineImpl;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IDevisePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
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
	IProduitPersistance produitPersistance;
	
	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private IProduitCommandePersistance produitCommandePersistance;
	@Autowired
	private 
	IDevisePersistance devisePersistance;

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

	@Override
	public ResultatRechecheProduitBonCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitBonCommandeValue request) {
	
		
		ResultatRechecheProduitBonCommandeValue result = produitCommandePersistance.rechercherMultiCritere(request);
	
		for(ProduitCommandeValue produitCommandeValue : result.getListProduitCommandeValues()){
			
			
			
			if(produitCommandeValue.getProduitId()!=null)
			{
			ProduitValue produitValue=	produitPersistance.rechercheProduitById(produitCommandeValue.getProduitId());
			produitCommandeValue.setProduitDesignation(produitValue.getDesignation());
			produitCommandeValue.setProduitReference(produitValue.getReference());
			
		
			}
			if(produitCommandeValue.getPartieIntersseId()!=null)
			{
				PartieInteresseValue partieInteresseValue=partieInteresseePersistance.getPartieInteresseById(produitCommandeValue.getPartieIntersseId());
				produitCommandeValue.setPartieIntersseAbbreviation(partieInteresseValue.getAbreviation());
			}
		  
		}
			
		
		return result;
	}

}
