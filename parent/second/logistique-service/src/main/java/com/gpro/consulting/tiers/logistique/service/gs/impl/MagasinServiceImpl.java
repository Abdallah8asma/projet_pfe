package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMagasinValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMagasinDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;

/**
 * The Class MagasinServiceImpl.
 */
@Service
@Transactional
public class MagasinServiceImpl   implements IMagasinService{
     
     /** The magasin domaine. */
     @Autowired
     IMagasinDomaine magasinDomaine;
	
	/* (non-Javadoc)
	 * creer
	 */
	@Override
	public String creerMagasin(MagasinValue pMagasinValue) {
		return magasinDomaine.creerMagasin(pMagasinValue);
	}

	/* (non-Javadoc)
	 * supprimer
	 */
	@Override
	public void supprimerMagasin(Long pId) {
		magasinDomaine.supprimerMagasin(pId);
	}

	/* (non-Javadoc)
	 * modifier
	 */
	@Override
	public String modifierMagasin(MagasinValue pMagasinValue) {
		 return magasinDomaine.modifierMagasin(pMagasinValue);
	}

	/* (non-Javadoc)
	 * recherche par id
	 */
	@Override
	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue) {
		  return magasinDomaine.rechercheMagasinParId(pMagasinValue);
	}

	/* (non-Javadoc)
	 * liste magasin 
	 */
	@Override
	public List<MagasinValue> listeMagasin() {
		 return magasinDomaine.listeMagasin();
	}

	@Override
	public List<MagasinValue> listeDepot() {
		return magasinDomaine.listeDepot();
	}

	@Override
	public List<MagasinValue> listePDV() {
		return magasinDomaine.listePDV();
	}

	@Override
	public  List < MagasinValue > rechercheMulticritere(RechercheMulticritereMagasinValue pMagasinValue) {
		// TODO Auto-generated method stub
		return magasinDomaine.rechercheMulticritere(pMagasinValue);
	}	
	
}
