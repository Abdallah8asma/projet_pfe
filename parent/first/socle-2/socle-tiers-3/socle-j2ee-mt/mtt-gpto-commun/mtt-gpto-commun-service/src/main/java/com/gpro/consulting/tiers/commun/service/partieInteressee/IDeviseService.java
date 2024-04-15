package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;

public interface IDeviseService {
	
	/**************************ajouter Devise***************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public  String creerDevise(DeviseValue pDeviseValue);
	
	/**********************supprimer Devise*****************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public  void supprimerDevise(Long pId);
	
	/**********************modifier Devise *****************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierDevise(DeviseValue pDeviseValue);
	
	/**********************recherche  Devise****************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public DeviseValue rechercheDeviseParId(DeviseValue pDeviseValue);
	
	/******************afficher  liste  Devise**************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<DeviseValue> listeDevise();
	
}
