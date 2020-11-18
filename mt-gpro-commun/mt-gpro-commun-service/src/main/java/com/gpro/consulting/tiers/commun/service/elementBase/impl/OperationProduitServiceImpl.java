package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IOperationProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IOperationProduitService;

@Service
@Transactional
public class OperationProduitServiceImpl  implements IOperationProduitService{
	@Autowired
	IOperationProduitDomaine optionProduitDomaine;

	@Override
	public OperationProduitValue rechercheOperationProduitParId(Long pOperationProduitValue) {
		return optionProduitDomaine.rechercheOperationProduitById(pOperationProduitValue);
	}

	@Override
	public List<OperationProduitValue> listeOperationProduit() {
		return optionProduitDomaine.listeOperationProduit();
	}
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerOperationProduit(OperationProduitValue pOperationProduitValue) {
		return optionProduitDomaine.creerOperationProduit(pOperationProduitValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerOperationProduit(Long pId ) {
		optionProduitDomaine.supprimerOperationProduit(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierOperationProduit(OperationProduitValue pOperationProduitValue) {
		return optionProduitDomaine.modifierOperationProduit(pOperationProduitValue);
	}

}
