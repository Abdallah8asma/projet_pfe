package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ITypePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ITypePartieInteresseePersistance;

/**
 * The Class TypePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class TypePartieInteresseeDomaineImpl implements
		ITypePartieInteresseeDomaine {

	@Autowired
	ITypePartieInteresseePersistance typePartieInteresseePersistance;

	
	/*************create Type Partie Intersesse******************/
	@Override
	public String creerTypePartieInteressee(TypeValue pTypeValue) {
		return typePartieInteresseePersistance.creerTypePartieInteressee(pTypeValue);
	}

	@Override
	public void supprimerTypePartieInteressee(TypeValue pTypeValue) {
		typePartieInteresseePersistance.supprimerTypePartieInteressee(pTypeValue);
		
	}

	@Override
	public String modifierTypePartieInteressee(TypeValue pTypeValue) {
		return typePartieInteresseePersistance.modifierTypePartieInteressee(pTypeValue);
	}

	@Override
	public TypeValue rechercheTypePartieInteresseeParId(TypeValue pTypeValue) {
		return typePartieInteresseePersistance.rechercheTypePartieInteresseeParId(pTypeValue);
	}

	@Override
	public List<TypeValue> listetypePartieInteressee() {
		return typePartieInteresseePersistance.listeTypePartieIntPartieInteressee();
	}

}
