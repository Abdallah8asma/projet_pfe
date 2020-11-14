package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ICompteComptablePIDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ICompteComptablePIService;

@Service
@Transactional
public class CompteComptablePIServiceImpl  implements ICompteComptablePIService{
	@Autowired
	ICompteComptablePIDomaine compteComptableDomaine;

	@Override
	public CompteComptablePIValue rechercheCompteComptablePIParId(Long pCompteComptablePIValue) {
		return compteComptableDomaine.rechercheCompteComptablePIById(pCompteComptablePIValue);
	}

	@Override
	public List<CompteComptablePIValue> listeCompteComptablePI() {
		return compteComptableDomaine.listeCompteComptablePI();
	}
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue) {
		return compteComptableDomaine.creerCompteComptablePI(pCompteComptablePIValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerCompteComptablePI(Long pId ) {
		compteComptableDomaine.supprimerCompteComptablePI(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue) {
		return compteComptableDomaine.modifieruniteArticle(pCompteComptablePIValue);
	}

	@Override
	public CompteComptablePIValue rechercheCompteComptablePIParDesignation(String designation) {
		// TODO Auto-generated method stub
		return compteComptableDomaine.rechercheCompteComptablePIParDesignation(designation);
	}

}
