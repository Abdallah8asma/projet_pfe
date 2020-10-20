package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MetrageValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IMetrageDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IMetrageService;

@Service
@Transactional
public class MetrageServiceImpl implements IMetrageService{

	@Autowired
	IMetrageDomaine ebMetrageDomaine;
	
	/************************ Creation Metrage *****************************/
	@Override
	public String creerMetrage(MetrageValue pMetrageValue) {
		return ebMetrageDomaine.creerMetrage(pMetrageValue);
	}
	
	/************************ suppression Metrage  ***************************/
	@Override
	public void supprimerMetrage(Long pId) {
		ebMetrageDomaine.supprimerMetrage(pId);
	}

	/************************ Modification Metrage ***************************/
	@Override
	public String modifierMetrage(MetrageValue pMetrageValue) {
		return ebMetrageDomaine.modifierMetrage(pMetrageValue);
	}

	/************************** Recherche Metrage *****************************/
	@Override
	public MetrageValue rechercheMetrageParId(MetrageValue pMetrageValue) {
		return ebMetrageDomaine.rechercheMetrageParId(pMetrageValue);
	}

	@Override
	public List<MetrageValue> listeMetrage() {
		return ebMetrageDomaine.listeMetrage();
	}
	
}
