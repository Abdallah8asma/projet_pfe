package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereRemiseValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RemiseValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IRemiseDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IRemiseService;

@Service
@Transactional
public class RemiseServiceImpl implements IRemiseService{

	@Autowired
	IRemiseDomaine ebRemiseDomaine;
	
	/************************ Creation Remise *****************************/
	@Override
	public String creerRemise(RemiseValue pRemiseValue) {
		return ebRemiseDomaine.creerRemise(pRemiseValue);
	}

	/************************ suppression Remise ***************************/
	@Override
	public void supprimerRemise(Long pId) {
		ebRemiseDomaine.supprimerRemise(pId);
	}

	/************************ Modification Remise ***************************/
	@Override
	public String modifierRemise(RemiseValue pRemiseValue) {
		return ebRemiseDomaine.modifierRemise(pRemiseValue);
	}

	/************************** Recherche Remise *****************************/
	@Override
	public RemiseValue rechercheRemiseParId(RemiseValue pRemiseValue) {
		return ebRemiseDomaine.rechercheRemiseParId(pRemiseValue);
	}
	
	/************************** Liste des Remises *****************************/
	@Override
	public List<RemiseValue> listeRemise() {
		return ebRemiseDomaine.listeRemise();
	}

	@Override
	public List<RemiseValue> rechercheMulticritere(RechercheMulticritereRemiseValue request) {
		// TODO Auto-generated method stub
		return ebRemiseDomaine.rechercheMulticritere(request);
	}

}
