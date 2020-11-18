package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OperationProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IOperationProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IOperationProduitPersistance;


/**
 * The Class OperationProduitDomaineImpl.
 * @author mohamed
 */
@Component
public class OperationProduitDomaineImpl implements IOperationProduitDomaine{
	
	@Autowired
	IOperationProduitPersistance  optionProduitPersistance ;
	
	/* ajouter unite */
	@Override
	public String creerOperationProduit(OperationProduitValue pOperationProduitValue) {
		return optionProduitPersistance.creerOperationProduit(pOperationProduitValue);
	}

	/* supprimer unite
	 */
	@Override
	public void supprimerOperationProduit(Long pOperationProduitId) {
	 optionProduitPersistance.supprimerOperationProduit(pOperationProduitId);
	}

	/* modifier unite
	 */
	@Override
	public String modifierOperationProduit(OperationProduitValue pOperationProduitValue) {
		return optionProduitPersistance.modifierOperationProduit(pOperationProduitValue);
	}

	@Override
	public OperationProduitValue rechercheOperationProduitById(Long pOperationProduitId) {
		return optionProduitPersistance.rechercheOperationProduitById(pOperationProduitId);
	}

	@Override
	public List<OperationProduitValue> listeOperationProduit() {
		return optionProduitPersistance.listeOperationProduit();
	}

}
