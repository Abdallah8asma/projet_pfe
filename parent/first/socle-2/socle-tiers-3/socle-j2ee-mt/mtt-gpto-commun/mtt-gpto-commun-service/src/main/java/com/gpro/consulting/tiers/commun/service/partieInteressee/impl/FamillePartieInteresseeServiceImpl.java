package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IFamillePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IFamillePartieInteresseeService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class FamillePartieInteresseeServiceImpl implements IFamillePartieInteresseeService {
	
	@Autowired
	IFamillePartieInteresseeDomaine famillePartieInteresseeDomaine;


	@Override
	public FamilleValue rechercheFamillePartieInteresseeParId(FamilleValue pFamilleValue) {
		return famillePartieInteresseeDomaine.rechercheFamillePartieInteresseeParId(pFamilleValue);
	}
	
	@Override
	public List<FamilleValue> listeFamillePartieInteressee() {
		return famillePartieInteresseeDomaine.listeFamillePartieInteressee();
	}
	
	@Override
	public String creerFamillePartieInteressee(FamilleValue pFamilleValue) {
		return famillePartieInteresseeDomaine.creerFamillePartieInteressee(pFamilleValue);
	}
	
	@Override
	public void supprimerFamillePartieInteressee(Long pFamilleValue) {
	    famillePartieInteresseeDomaine.supprimerFamillePartieInteressee(pFamilleValue);	
	}
	
	@Override
	public String modifierFamillePartieInteressee(FamilleValue pFamilleValue) {
		 return famillePartieInteresseeDomaine.modifierFamillePartieInteressee(pFamilleValue);
	}

}
