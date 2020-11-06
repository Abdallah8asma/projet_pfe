package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IImpressionProduitDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IImpressionProduitService;

@Service
@Transactional
public class ImpressionProduitServiceImpl  implements IImpressionProduitService{
	@Autowired
	IImpressionProduitDomaine impressionProduitDomaine;

	@Override
	public ImpressionProduitValue rechercheImpressionProduitParId(Long pImpressionProduitValue) {
		return impressionProduitDomaine.rechercheImpressionProduitById(pImpressionProduitValue);
	}

	@Override
	public List<ImpressionProduitValue> listeImpressionProduit() {
		return impressionProduitDomaine.listeImpressionProduit();
	}
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerImpressionProduit(ImpressionProduitValue pImpressionProduitValue) {
		return impressionProduitDomaine.creerImpressionProduit(pImpressionProduitValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerImpressionProduit(Long pId ) {
		impressionProduitDomaine.supprimerImpressionProduit(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierImpressionProduit(ImpressionProduitValue pImpressionProduitValue) {
		return impressionProduitDomaine.modifieruniteArticle(pImpressionProduitValue);
	}

}
