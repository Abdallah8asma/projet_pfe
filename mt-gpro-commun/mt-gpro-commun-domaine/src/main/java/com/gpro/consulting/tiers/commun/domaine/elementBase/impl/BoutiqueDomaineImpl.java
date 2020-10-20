package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereBoutiqueValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IBoutiqueDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IBoutiquePersistance;

@Component
public class BoutiqueDomaineImpl implements IBoutiqueDomaine{

	@Autowired
	IBoutiquePersistance ebBoutiquePersistance;
	
	/************************ Creation Boutique ***************************/
	@Override
	public String creerBoutique(BoutiqueValue pBoutiqueValue) {
		return ebBoutiquePersistance.creerBoutique(pBoutiqueValue);
	}

	/*********************** suppression Boutique **************************/
	@Override
	public void supprimerBoutique(Long pId) {
		ebBoutiquePersistance.supprimerBoutique(pId);
	}

	/************************ Modifier Boutique ****************************/
	@Override
	public String modifierBoutique(BoutiqueValue pBoutiqueValue) {
		return ebBoutiquePersistance.modifierBoutique(pBoutiqueValue);
	}

	/************************ Rechercher Boutique ***************************/
	@Override
	public BoutiqueValue rechercheBoutiqueParId(BoutiqueValue pBoutiqueValue) {
		return ebBoutiquePersistance.rechercheBoutiqueParId(pBoutiqueValue);
	}

	/************************ Liste des Boutique ***************************/
	@Override
	public List<BoutiqueValue> listeBoutique() {
		return ebBoutiquePersistance.listeBoutique();
	}

	@Override
	public List<BoutiqueValue> rechercheMulticritere(RechercheMulticritereBoutiqueValue request) {
		// TODO Auto-generated method stub
		return ebBoutiquePersistance.rechercheMulticritere(request);
	}

}
