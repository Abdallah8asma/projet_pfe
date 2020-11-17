package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IOptionProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IOptionProduitPersistance;


/**
 * The Class OptionProduitDomaineImpl.
 * @author mohamed
 */
@Component
public class OptionProduitDomaineImpl implements IOptionProduitDomaine{
	
	@Autowired
	IOptionProduitPersistance  optionProduitPersistance ;
	
	/* ajouter unite */
	@Override
	public String creerOptionProduit(OptionProduitValue pOptionProduitValue) {
		return optionProduitPersistance.creerOptionProduit(pOptionProduitValue);
	}

	/* supprimer unite
	 */
	@Override
	public void supprimerOptionProduit(Long pOptionProduitId) {
	 optionProduitPersistance.supprimerOptionProduit(pOptionProduitId);
	}

	/* modifier unite
	 */
	@Override
	public String modifieruniteProduit(OptionProduitValue pOptionProduitValue) {
		return optionProduitPersistance.modifierOptionProduit(pOptionProduitValue);
	}

	@Override
	public OptionProduitValue rechercheOptionProduitById(Long pOptionProduitId) {
		return optionProduitPersistance.rechercheOptionProduitById(pOptionProduitId);
	}

	@Override
	public List<OptionProduitValue> listeOptionProduit() {
		return optionProduitPersistance.listeOptionProduit();
	}

}
