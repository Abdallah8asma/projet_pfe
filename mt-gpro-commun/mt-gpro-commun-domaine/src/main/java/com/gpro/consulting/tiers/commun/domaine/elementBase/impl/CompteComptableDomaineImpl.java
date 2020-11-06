package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CompteComptableValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ICompteComptableDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ICompteComptablePersistance;


/**
 * The Class CompteComptableDomaineImpl.
 * @author mohamed
 */
@Component
public class CompteComptableDomaineImpl implements ICompteComptableDomaine{
	
	@Autowired
	ICompteComptablePersistance  compteComptablePersistance ;
	
	/* ajouter unite */
	@Override
	public String creerCompteComptable(CompteComptableValue pCompteComptableValue) {
		return compteComptablePersistance.creerCompteComptable(pCompteComptableValue);
	}

	/* supprimer unite
	 */
	@Override
	public void supprimerCompteComptable(Long pCompteComptableId) {
	 compteComptablePersistance.supprimerCompteComptable(pCompteComptableId);
	}

	/* modifier unite
	 */
	@Override
	public String modifieruniteArticle(CompteComptableValue pCompteComptableValue) {
		return compteComptablePersistance.modifierCompteComptable(pCompteComptableValue);
	}

	@Override
	public CompteComptableValue rechercheCompteComptableById(Long pCompteComptableId) {
		return compteComptablePersistance.rechercheCompteComptableById(pCompteComptableId);
	}

	@Override
	public List<CompteComptableValue> listeCompteComptable() {
		return compteComptablePersistance.listeCompteComptable();
	}

}
