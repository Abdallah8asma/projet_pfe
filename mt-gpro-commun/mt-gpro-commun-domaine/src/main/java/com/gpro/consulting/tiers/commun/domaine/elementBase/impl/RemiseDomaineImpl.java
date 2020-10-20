package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereRemiseValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RemiseValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IRemiseDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IRemisePersistance;

@Component
public class RemiseDomaineImpl implements IRemiseDomaine{

	@Autowired
	IRemisePersistance ebRemisePersistance;
	
	/************************ Creation Remise ***************************/
	@Override
	public String creerRemise(RemiseValue pRemiseValue) {
		return ebRemisePersistance.creerRemise(pRemiseValue);
	}

	/*********************** suppression Remise **************************/
	@Override
	public void supprimerRemise(Long pId) {
		ebRemisePersistance.supprimerRemise(pId);
	}

	/************************ Modifier Remise ****************************/
	@Override
	public String modifierRemise(RemiseValue pRemiseValue) {
		return ebRemisePersistance.modifierRemise(pRemiseValue);
	}

	/************************ Rechercher Remise ***************************/
	@Override
	public RemiseValue rechercheRemiseParId(RemiseValue pRemiseValue) {
		return ebRemisePersistance.rechercheRemiseParId(pRemiseValue);
	}

	/************************ Liste des Remise ***************************/
	@Override
	public List<RemiseValue> listeRemise() {
		return ebRemisePersistance.listeRemise();
	}

	@Override
	public List<RemiseValue> rechercheMulticritere(RechercheMulticritereRemiseValue request) {
		// TODO Auto-generated method stub
		return ebRemisePersistance.rechercheMulticritere(request);
	}

}
