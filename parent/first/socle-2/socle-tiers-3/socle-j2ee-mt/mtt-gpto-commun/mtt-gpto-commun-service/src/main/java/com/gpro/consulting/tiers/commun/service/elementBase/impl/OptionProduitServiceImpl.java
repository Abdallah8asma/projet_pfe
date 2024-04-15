package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IOptionProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IOptionProduitService;

@Service
@Transactional
public class OptionProduitServiceImpl  implements IOptionProduitService{
	@Autowired
	IOptionProduitDomaine optionProduitDomaine;

	@Override
	public OptionProduitValue rechercheOptionProduitParId(Long pOptionProduitValue) {
		return optionProduitDomaine.rechercheOptionProduitById(pOptionProduitValue);
	}

	@Override
	public List<OptionProduitValue> listeOptionProduit() {
		return optionProduitDomaine.listeOptionProduit();
	}
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerOptionProduit(OptionProduitValue pOptionProduitValue) {
		return optionProduitDomaine.creerOptionProduit(pOptionProduitValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerOptionProduit(Long pId ) {
		optionProduitDomaine.supprimerOptionProduit(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierOptionProduit(OptionProduitValue pOptionProduitValue) {
		return optionProduitDomaine.modifieruniteProduit(pOptionProduitValue);
	}

}
