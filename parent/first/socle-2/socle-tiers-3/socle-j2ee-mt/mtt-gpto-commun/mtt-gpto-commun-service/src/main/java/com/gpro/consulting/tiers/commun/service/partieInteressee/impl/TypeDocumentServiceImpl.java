package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ITypeDocumentDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ITypeDocumentService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class TypeDocumentServiceImpl implements ITypeDocumentService {
    @Autowired
    ITypeDocumentDomaine typeDocumentDomaine;

	@Override
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(TypeDocumentValue pTypeDocumentValue) {
		  return typeDocumentDomaine.rechercheTypeDocumentPartieInteresseeParId(pTypeDocumentValue);
	}

	@Override
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee() {
		return typeDocumentDomaine.listeTypeDocumentPartieInteressee();
	}

	@Override
	public String creerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentDomaine.creerTypeDocumentPartieInteressee(pTypeDocumentValue);
	}

	@Override
	public void supprimerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		typeDocumentDomaine.supprimerTypeDocumentPartieInteressee(pTypeDocumentValue);		
	}

	@Override
	public String modifierTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentDomaine.modifierTypeDocumentPartieInteressee(pTypeDocumentValue);
	}
 
}
