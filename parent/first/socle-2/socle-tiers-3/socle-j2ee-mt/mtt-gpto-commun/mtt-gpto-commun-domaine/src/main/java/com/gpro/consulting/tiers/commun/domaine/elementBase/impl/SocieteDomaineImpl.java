package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereSocieteValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISocieteDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISocietePersistance;

@Component
public class SocieteDomaineImpl implements ISocieteDomaine{

	@Autowired
	ISocietePersistance ebSocietePersistance;
	
	/************************ Creation Societe ***************************/
	@Override
	public String creerSociete(SocieteValue pSocieteValue) {
		return ebSocietePersistance.creerSociete(pSocieteValue);
	}

	/*********************** suppression Societe **************************/
	@Override
	public void supprimerSociete(Long pId) {
		ebSocietePersistance.supprimerSociete(pId);
	}

	/************************ Modifier Societe ****************************/
	@Override
	public String modifierSociete(SocieteValue pSocieteValue) {
		return ebSocietePersistance.modifierSociete(pSocieteValue);
	}

	/************************ Rechercher Societe ***************************/
	@Override
	public SocieteValue rechercheSocieteParId(SocieteValue pSocieteValue) {
		return ebSocietePersistance.rechercheSocieteParId(pSocieteValue);
	}

	/************************ Liste des Societe ***************************/
	@Override
	public List<SocieteValue> listeSociete() {
		return ebSocietePersistance.listeSociete();
	}

	@Override
	public List<SocieteValue> rechercheMulticritere(RechercheMulticritereSocieteValue request) {
		// TODO Auto-generated method stub
		return ebSocietePersistance.rechercheMulticritere(request);
	}

}
