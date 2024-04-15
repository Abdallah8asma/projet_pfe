package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UtilsValue;

public interface IUtilsEntiteService {
	/************************** ajouter Utils ***************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerUtils(UtilsValue pUtilsValue);

	/********************** supprimer Utils *****************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerUtils(Long pId);

	/********************** modifier Utils *****************************/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierUtils(UtilsValue pUtilsValue);

	/********************** recherche Utils ****************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UtilsValue rechercheUtilsParId(UtilsValue pUtilsValue);

	/****************** afficher liste Utils **************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<UtilsValue> listeUtils();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<UtilsValue> listeUtilsParType(String type);

}
