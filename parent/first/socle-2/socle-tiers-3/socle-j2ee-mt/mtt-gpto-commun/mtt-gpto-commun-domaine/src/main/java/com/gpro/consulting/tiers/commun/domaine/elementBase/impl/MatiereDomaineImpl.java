package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IMatiereDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IMatiereArticlePersistance;

@Component
public class MatiereDomaineImpl implements IMatiereDomaine{

	@Autowired
	IMatiereArticlePersistance ebMatierePersistance;
	
	/************************ Creation Matiere ***************************/
	@Override
	public String creerMatiere(MatiereArticleValue pMatiereValue) {
		return ebMatierePersistance.creerMatiere(pMatiereValue);
	}

	/*********************** suppression Matiere **************************/
	@Override
	public void supprimerMatiere(Long pId) {
		ebMatierePersistance.supprimerMatiere(pId);		
	}

	/************************ Modifier Matiere ****************************/
	@Override
	public String modifierMatiere(MatiereArticleValue pMatiereValue) {
		return ebMatierePersistance.modifierMatiere(pMatiereValue);
	}

	/************************ Rechercher Matiere ***************************/
	@Override
	public MatiereArticleValue rechercheMatiereParId(MatiereArticleValue pMatiereValue) {
		return ebMatierePersistance.rechercheMatiereParId(pMatiereValue);
	}

	/************************ Liste des Matieres ***************************/
	@Override
	public List<MatiereArticleValue> listeMatiere() {
		return ebMatierePersistance.listeMatiere();
	}

}
