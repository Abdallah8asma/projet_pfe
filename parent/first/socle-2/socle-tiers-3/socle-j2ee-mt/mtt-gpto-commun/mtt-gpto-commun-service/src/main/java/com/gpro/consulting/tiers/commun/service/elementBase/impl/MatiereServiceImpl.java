package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IMatiereDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IMatiereService;

@Service
@Transactional
public class MatiereServiceImpl implements IMatiereService{

	@Autowired
	IMatiereDomaine ebMatiereDomaine;
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerMatiere(MatiereArticleValue pMatiereValue) {
		return ebMatiereDomaine.creerMatiere(pMatiereValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerMatiere(Long pId) {
		ebMatiereDomaine.supprimerMatiere(pId);		
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierMatiere(MatiereArticleValue pMatiereValue) {
		
		return ebMatiereDomaine.modifierMatiere(pMatiereValue);
	}

	/************************** Recherche Matiere *****************************/
	@Override
	public MatiereArticleValue rechercheMatiereParId(MatiereArticleValue pMatiereValue) {
		
		return ebMatiereDomaine.rechercheMatiereParId(pMatiereValue);
	}
	
	/************************** Liste des Matieres *****************************/
	@Override
	public List<MatiereArticleValue> listeMatiere() {
		
		return ebMatiereDomaine.listeMatiere();
	}
	
}
