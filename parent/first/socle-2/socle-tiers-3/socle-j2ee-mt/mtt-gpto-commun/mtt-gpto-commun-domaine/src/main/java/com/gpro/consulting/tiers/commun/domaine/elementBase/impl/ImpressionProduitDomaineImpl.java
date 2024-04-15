package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IImpressionProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IImpressionProduitPersistance;


/**
 * The Class ImpressionProduitDomaineImpl.
 * @author mohamed
 */
@Component
public class ImpressionProduitDomaineImpl implements IImpressionProduitDomaine{
	
	@Autowired
	IImpressionProduitPersistance  impressionProduitPersistance ;
	
	/* ajouter unite */
	@Override
	public String creerImpressionProduit(ImpressionProduitValue pImpressionProduitValue) {
		return impressionProduitPersistance.creerImpressionProduit(pImpressionProduitValue);
	}

	/* supprimer unite
	 */
	@Override
	public void supprimerImpressionProduit(Long pImpressionProduitId) {
	 impressionProduitPersistance.supprimerImpressionProduit(pImpressionProduitId);
	}

	/* modifier unite
	 */
	@Override
	public String modifieruniteArticle(ImpressionProduitValue pImpressionProduitValue) {
		return impressionProduitPersistance.modifierImpressionProduit(pImpressionProduitValue);
	}

	@Override
	public ImpressionProduitValue rechercheImpressionProduitById(Long pImpressionProduitId) {
		return impressionProduitPersistance.rechercheImpressionProduitById(pImpressionProduitId);
	}

	@Override
	public List<ImpressionProduitValue> listeImpressionProduit() {
		return impressionProduitPersistance.listeImpressionProduit();
	}

}
