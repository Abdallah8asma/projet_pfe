package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ICompteComptableDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ICompteComptableService;

@Service
@Transactional
public class CompteComptableServiceImpl  implements ICompteComptableService{
	@Autowired
	ICompteComptableDomaine compteComptableDomaine;

	@Override
	public CompteComptableValue rechercheCompteComptableParId(Long pCompteComptableValue) {
		return compteComptableDomaine.rechercheCompteComptableById(pCompteComptableValue);
	}

	@Override
	public List<CompteComptableValue> listeCompteComptable() {
		return compteComptableDomaine.listeCompteComptable();
	}
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerCompteComptable(CompteComptableValue pCompteComptableValue) {
		return compteComptableDomaine.creerCompteComptable(pCompteComptableValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerCompteComptable(Long pId ) {
		compteComptableDomaine.supprimerCompteComptable(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierCompteComptable(CompteComptableValue pCompteComptableValue) {
		return compteComptableDomaine.modifieruniteArticle(pCompteComptableValue);
	}

}
