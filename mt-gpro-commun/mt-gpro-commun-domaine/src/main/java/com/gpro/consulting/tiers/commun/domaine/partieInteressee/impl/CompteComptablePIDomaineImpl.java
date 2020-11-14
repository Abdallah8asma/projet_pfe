package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CompteComptablePIValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ICompteComptablePIDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ICompteComptablePIPersistance;


/**
 * The Class CompteComptablePIDomaineImpl.
 * @author mohamed
 */
@Component
public class CompteComptablePIDomaineImpl implements ICompteComptablePIDomaine{
	
	@Autowired
	ICompteComptablePIPersistance  compteComptablePersistance ;
	
	/* ajouter unite */
	@Override
	public String creerCompteComptablePI(CompteComptablePIValue pCompteComptablePIValue) {
		return compteComptablePersistance.creerCompteComptablePI(pCompteComptablePIValue);
	}

	/* supprimer unite
	 */
	@Override
	public void supprimerCompteComptablePI(Long pCompteComptablePIId) {
	 compteComptablePersistance.supprimerCompteComptablePI(pCompteComptablePIId);
	}

	/* modifier unite
	 */
	@Override
	public String modifieruniteArticle(CompteComptablePIValue pCompteComptablePIValue) {
		return compteComptablePersistance.modifierCompteComptablePI(pCompteComptablePIValue);
	}

	@Override
	public CompteComptablePIValue rechercheCompteComptablePIById(Long pCompteComptablePIId) {
		return compteComptablePersistance.rechercheCompteComptablePIById(pCompteComptablePIId);
	}

	@Override
	public List<CompteComptablePIValue> listeCompteComptablePI() {
		return compteComptablePersistance.listeCompteComptablePI();
	}

	@Override
	public CompteComptablePIValue rechercheCompteComptablePIParDesignation(String designation) {
		// TODO Auto-generated method stub
		return compteComptablePersistance.rechercheCompteComptablePIParDesignation(designation);
	}

}
