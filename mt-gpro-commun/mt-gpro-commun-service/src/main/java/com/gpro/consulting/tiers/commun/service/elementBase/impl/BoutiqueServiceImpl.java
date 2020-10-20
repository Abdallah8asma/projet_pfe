package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereBoutiqueValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IBoutiqueDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IBoutiqueService;

@Service
@Transactional
public class BoutiqueServiceImpl implements IBoutiqueService{

	@Autowired
	IBoutiqueDomaine ebBoutiqueDomaine;
	
	/************************ Creation Boutique *****************************/
	@Override
	public String creerBoutique(BoutiqueValue pBoutiqueValue) {
		return ebBoutiqueDomaine.creerBoutique(pBoutiqueValue);
	}

	/************************ suppression Boutique ***************************/
	@Override
	public void supprimerBoutique(Long pId) {
		ebBoutiqueDomaine.supprimerBoutique(pId);
	}

	/************************ Modification Boutique ***************************/
	@Override
	public String modifierBoutique(BoutiqueValue pBoutiqueValue) {
		return ebBoutiqueDomaine.modifierBoutique(pBoutiqueValue);
	}

	/************************** Recherche Boutique *****************************/
	@Override
	public BoutiqueValue rechercheBoutiqueParId(BoutiqueValue pBoutiqueValue) {
		return ebBoutiqueDomaine.rechercheBoutiqueParId(pBoutiqueValue);
	}
	
	/************************** Liste des Boutiques *****************************/
	@Override
	public List<BoutiqueValue> listeBoutique() {
		return ebBoutiqueDomaine.listeBoutique();
	}

	@Override
	public List<BoutiqueValue> rechercheMulticritere(RechercheMulticritereBoutiqueValue request) {
		// TODO Auto-generated method stub
		return ebBoutiqueDomaine.rechercheMulticritere(request);
	}

}
