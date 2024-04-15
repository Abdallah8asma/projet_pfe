package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CategorieValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ICategoriePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ICategoriePartieInteresseePersistance;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class CategoriePartieInteresseeDomaineImpl implements
		ICategoriePartieInteresseeDomaine {

	@Autowired
	ICategoriePartieInteresseePersistance categoriePartieInteresseePersistance;

	/*
	 * methode de creation cathegorie Partie Interessée
	 */
	@Override
	public String creerCategoriePartieInteressee(CategorieValue pCategorieValue) {

		return categoriePartieInteresseePersistance
				.creerCategoriePartieInteressee(pCategorieValue);
	}

	
	/*******************supprimer categorie partie interesse **********************/
	@Override
	public void supprimerCategoriePartieInteressee(
			Long pCategorieValue) {
		categoriePartieInteresseePersistance.supprimerCategoriePartieInteressee(pCategorieValue);
		
	}
	
	
	/*******************modifier categorie partie interesse **********************/
	@Override
	public String modifierCategoriePartieInteressee(CategorieValue pCategorieValue) {
		return categoriePartieInteresseePersistance.modifierCategoriePartieInteressee(pCategorieValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public CategorieValue rechercheCategoriePartieInteresseeParId(CategorieValue pCategorieValue) {
		return categoriePartieInteresseePersistance.rechercheCategoriePartieInteresseeParId(pCategorieValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public List<CategorieValue> listeCategoriePartieInteressee() {
		return categoriePartieInteresseePersistance.listeCategoriePartieInteressee();
	}	

}
