package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UtilsValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IUtilsEntiteDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IUtilsEntiteService;

@Service
@Transactional
public class UtilsEntiteServiceImpl implements IUtilsEntiteService{

	@Autowired
	IUtilsEntiteDomaine ebUtilsDomaine;
	
	/************************ Creation Utils *****************************/
	@Override
	public String creerUtils(UtilsValue pUtilsValue) {
		return ebUtilsDomaine.creerUtils(pUtilsValue);
	}

	/************************ suppression Utils ***************************/
	@Override
	public void supprimerUtils(Long pId) {
		ebUtilsDomaine.supprimerUtils(pId);
	}

	/************************ Modification Utils ***************************/
	@Override
	public String modifierUtils(UtilsValue pUtilsValue) {
		return ebUtilsDomaine.modifierUtils(pUtilsValue);
	}

	/************************** Recherche Utils *****************************/
	@Override
	public UtilsValue rechercheUtilsParId(UtilsValue pUtilsValue) {
		return ebUtilsDomaine.rechercheUtilsParId(pUtilsValue);
	}
	
	/************************** Liste des Utilss *****************************/
	@Override
	public List<UtilsValue> listeUtils() {
		return ebUtilsDomaine.listeUtils();
	}

	@Override
	public List<UtilsValue> listeUtilsParType(String type) {
		// TODO Auto-generated method stub
		return ebUtilsDomaine.listeUtilsParType(type);
	}

}
