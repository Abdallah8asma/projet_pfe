package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;

/**
 * The Class ProduitServiceImpl.
 * @author med
 */
@Service
@Transactional
public class ProduitServiceImpl implements IProduitService{
	/** The produit domaine. */
	@Autowired
	IProduitDomaine produitDomaine;

	//creer produit
	@Override
	public String creerProduit(ProduitValue pProduitValue) {
		return produitDomaine.creerProduit(pProduitValue);
	}
    
	//supprimer
	@Override
	public void supprimerProduit(Long pProduitId) {
       produitDomaine.supprimerProduit(pProduitId);		
	}

	//modifier
	@Override
	public String modifierProduit(ProduitValue pProduitValue) {
		return produitDomaine.modifierProduit(pProduitValue);
	}

	//recherche par id
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId) {
		return produitDomaine.rechercheProduitById(pProduitId);
	}

	//liste produit
	@Override
	public List<ProduitValue> listeProduit() {
		return produitDomaine.listeProduit();
	}

	/**
	 * Gets the produit domaine.
	 *
	 * @return the produit domaine
	 */
	public IProduitDomaine getProduitDomaine() {
		return produitDomaine;
	}

	/**
	 * Sets the produit domaine.
	 *
	 * @param produitDomaine the new produit domaine
	 */
	public void setProduitDomaine(IProduitDomaine produitDomaine) {
		this.produitDomaine = produitDomaine;
	}


	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritere(
			RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
		  return produitDomaine.rechercherProduitMultiCritere(pRechercheProduitMulitCritere);

		
	}
	
	//rech mult client
	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritereClient(
			RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
		  return produitDomaine.rechercherProduitMultiCritereClient(pRechercheProduitMulitCritere);

		
	}

	@Override
	public ProduitValue rechercheProduitParReference(String reference) {
 
		return produitDomaine.rechercheProduitParReference(reference);
	}

	@Override
	public Map<Long, ProduitValue> mapProduitById() {
		// TODO Auto-generated method stub
		return produitDomaine.mapProduitById()	;
		}

	@Override
	public List<ProduitValue> rechercheProduitFinance() {
		return produitDomaine.rechercheProduitFinance();
	}

	@Override
	public List<ProduitValue> rechercheProduitNonFinance() {
		// TODO Auto-generated method stub
		return produitDomaine.rechercheProduitNonFinance();
	}

}
