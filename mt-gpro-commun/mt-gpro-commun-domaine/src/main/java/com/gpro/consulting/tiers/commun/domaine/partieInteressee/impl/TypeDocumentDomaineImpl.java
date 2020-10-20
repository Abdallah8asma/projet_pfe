package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ITypeDocumentDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ITypeDocumentPersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class TypeDocumentDomaineImpl implements ITypeDocumentDomaine {

	/** The type document persistance. */
	@Autowired 
	ITypeDocumentPersistance typeDocumentPersistance;

	/* (non-Javadoc)
	 * recherche par id type document
	 */
	@Override
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentPersistance.rechercheTypeDocumentPartieInteresseeParId(pTypeDocumentValue);
	}

	/* (non-Javadoc)
	 * lister type document 
	 */
	@Override
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee() {
		 return typeDocumentPersistance.listeTypeDocumentPartieInteressee();
	}

	
	/* (non-Javadoc)
	 * creer type docuement 
	 */
	@Override
	public String creerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentPersistance.creerTypeDocumentPartieInteressee(pTypeDocumentValue);
	}

	/* (non-Javadoc)
	 * supprimer type document 
	 */
	@Override
	public void supprimerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		typeDocumentPersistance.supprimerTypeDocumentPartieInteressee(pTypeDocumentValue);		
	}

	/* (non-Javadoc)
	 * modifier type document 
	 */
	@Override
	public String modifierTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentPersistance.modifierTypeDocumentPartieInteressee(pTypeDocumentValue);
	}
	
}
