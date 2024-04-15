package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereSocieteValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISocieteDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.ISocieteService;

@Service
@Transactional
public class SocieteServiceImpl implements ISocieteService{

	@Autowired
	ISocieteDomaine ebSocieteDomaine;
	
	/************************ Creation Societe *****************************/
	@Override
	public String creerSociete(SocieteValue pSocieteValue) {
		return ebSocieteDomaine.creerSociete(pSocieteValue);
	}

	/************************ suppression Societe ***************************/
	@Override
	public void supprimerSociete(Long pId) {
		ebSocieteDomaine.supprimerSociete(pId);
	}

	/************************ Modification Societe ***************************/
	@Override
	public String modifierSociete(SocieteValue pSocieteValue) {
		return ebSocieteDomaine.modifierSociete(pSocieteValue);
	}

	/************************** Recherche Societe *****************************/
	@Override
	public SocieteValue rechercheSocieteParId(SocieteValue pSocieteValue) {
		return ebSocieteDomaine.rechercheSocieteParId(pSocieteValue);
	}
	
	/************************** Liste des Societes *****************************/
	@Override
	public List<SocieteValue> listeSociete() {
		return ebSocieteDomaine.listeSociete();
	}

	@Override
	public List<SocieteValue> rechercheMulticritere(RechercheMulticritereSocieteValue request) {
		// TODO Auto-generated method stub
		return ebSocieteDomaine.rechercheMulticritere(request);
	}

}
