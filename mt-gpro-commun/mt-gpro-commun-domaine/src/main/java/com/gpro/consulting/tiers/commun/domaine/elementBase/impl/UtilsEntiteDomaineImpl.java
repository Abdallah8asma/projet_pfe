package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UtilsValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IUtilsEntiteDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IUtilsEntitePersistance;

@Component
public class UtilsEntiteDomaineImpl implements IUtilsEntiteDomaine{

	@Autowired
	IUtilsEntitePersistance ebUtilsPersistance;
	
	/************************ Creation Utils ***************************/
	@Override
	public String creerUtils(UtilsValue pUtilsValue) {
		return ebUtilsPersistance.creerUtils(pUtilsValue);
	}

	/*********************** suppression Utils **************************/
	@Override
	public void supprimerUtils(Long pId) {
		ebUtilsPersistance.supprimerUtils(pId);
	}

	/************************ Modifier Utils ****************************/
	@Override
	public String modifierUtils(UtilsValue pUtilsValue) {
		return ebUtilsPersistance.modifierUtils(pUtilsValue);
	}

	/************************ Rechercher Utils ***************************/
	@Override
	public UtilsValue rechercheUtilsParId(UtilsValue pUtilsValue) {
		return ebUtilsPersistance.rechercheUtilsParId(pUtilsValue);
	}

	/************************ Liste des Utils ***************************/
	@Override
	public List<UtilsValue> listeUtils() {
		return ebUtilsPersistance.listeUtils();
	}

	@Override
	public List<UtilsValue> listeUtilsParType(String type) {
		// TODO Auto-generated method stub
		return ebUtilsPersistance.listeUtilsParType(type);
	}

}
