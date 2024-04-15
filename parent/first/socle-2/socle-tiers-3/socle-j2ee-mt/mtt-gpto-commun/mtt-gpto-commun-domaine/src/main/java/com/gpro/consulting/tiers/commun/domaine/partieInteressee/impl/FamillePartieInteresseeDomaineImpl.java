package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IFamillePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IFamillePartieInteresseePersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Component
public class FamillePartieInteresseeDomaineImpl implements
		IFamillePartieInteresseeDomaine {

	/** The famille partie interessee persistance. */
	@Autowired
	IFamillePartieInteresseePersistance famillePartieInteresseePersistance;

	/* (non-Javadoc)
	 * recherche par id famille pi
	 */
	@Override
	public FamilleValue rechercheFamillePartieInteresseeParId(FamilleValue pFamilleValue) {
		return famillePartieInteresseePersistance.rechercheFamillePartieInteresseeParId(pFamilleValue);

	}

	/* (non-Javadoc)
	 * liste famille
	 */
	@Override
	public List<FamilleValue> listeFamillePartieInteressee() {
		return famillePartieInteresseePersistance.listeFamillePartieInteressee();
	}

	/* (non-Javadoc)
	 * creer famille 
	 */
	@Override
	public String creerFamillePartieInteressee(FamilleValue pFamilleValue) {
		return famillePartieInteresseePersistance.creerFamillePartieInteressee(pFamilleValue);
	}

	/* (non-Javadoc)
	 * supprimer famille 
	 */
	@Override
	public void supprimerFamillePartieInteressee(Long pFamilleValue) {
        famillePartieInteresseePersistance.supprimerFamillePartieInteressee(pFamilleValue);		
	}

	/* (non-Javadoc)
	 * modifier famille 
	 */
	@Override
	public String modifierFamillePartieInteressee(FamilleValue pFamilleValue) {
		return famillePartieInteresseePersistance.modifierFamillePartieInteressee(pFamilleValue);
	}

	
}
