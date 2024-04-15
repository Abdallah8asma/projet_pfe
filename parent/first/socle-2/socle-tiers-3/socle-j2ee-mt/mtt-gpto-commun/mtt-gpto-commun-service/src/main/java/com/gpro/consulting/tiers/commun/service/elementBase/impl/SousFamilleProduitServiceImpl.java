package com.gpro.consulting.tiers.commun.service.elementBase.impl;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISousFamilleProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ISousFamilleProduitService;

/**
 * The Class SousFamilleProduitImpl.
 * @author med
 */
@Service
@Transactional
public class SousFamilleProduitServiceImpl implements ISousFamilleProduitService{
	
	/** The sous famille produit domaine. */
	@Autowired
	ISousFamilleProduitDomaine sousFamilleProduitDomaine;

	//creer sous famille article
	@Override
	public String creerSousFamilleProduit(
			SousFamilleProduitValue pSousFamilleProduitValue) {
		return sousFamilleProduitDomaine.creerSousFamilleProduit(pSousFamilleProduitValue);
	}

	//supprimer
	@Override
	public void supprimerSousFamilleProduit(Long pSousFamilleProduitId) {
		sousFamilleProduitDomaine.supprimerSousFamilleProduit(pSousFamilleProduitId);
	}
	//modifier
	@Override
	public String modifierSousFamilleProduit(
			SousFamilleProduitValue pSousFamilleProduitValue) {
		return sousFamilleProduitDomaine.modifierSousFamilleProduit(pSousFamilleProduitValue);
	}
	
    //recherche par id
	@Override
	public SousFamilleProduitValue rechercheSousFamilleProduitById(
			Long pSousFamilleProduitId) {
		return sousFamilleProduitDomaine.rechercheSousFamilleProduitById(pSousFamilleProduitId);

	}

	//liste famille article
	@Override
	public List<SousFamilleProduitValue> listeSousFamilleProduit() {
		return sousFamilleProduitDomaine.listeSousFamilleProduit();
	}

}
