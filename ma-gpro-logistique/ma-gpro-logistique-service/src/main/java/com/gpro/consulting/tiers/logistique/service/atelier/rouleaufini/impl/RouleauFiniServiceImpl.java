package com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IRouleauFiniService;

/**
 * implementation of {@link IRouleauFiniService}
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
@Service
@Transactional
public class RouleauFiniServiceImpl implements IRouleauFiniService{
	
	@Autowired
	private IRouleauFiniDomaine rouleauFiniDomaine;
	

	private static final Logger logger = LoggerFactory.getLogger(RouleauFiniServiceImpl.class);
	

	@Override
	public ResultatRechecheRouleauFiniValue rechercherMultiCritere(RechercheMulticritereRouleauFiniValue request) {
		//logger.info("get emp "+request.getEmplacement());
		return rouleauFiniDomaine.rechercherMultiCritere(request);
	}
	
	@Override
	public ResultatRechecheRouleauFiniValue rechercherMultiCritereStandard(RechercheMulticritereRouleauFiniStandardValue request) {
		return rouleauFiniDomaine.rechercherMultiCritereStandard(request);
	}

	@Override
	public RouleauFiniValue getRouleauFiniById(Long id) {
		
		return rouleauFiniDomaine.getRouleauFiniById(id);
	}

	@Override
	public String updateRouleauFini(RouleauFiniValue request) {
		
		return rouleauFiniDomaine.updateRouleauFini(request);
	}

	@Override
	public void deleteRouleauFini(Long id) {
		
		rouleauFiniDomaine.deleteRouleauFini(id);
	}

	@Override
	public String createRouleauFini(RouleauFiniValue request) {
		
		return rouleauFiniDomaine.createRouleauFini(request);
	}

	@Override
	public ResultatRechecheRouleauFiniValue getRouleauFiniByInfoModif(String infoModif) {
		
		return rouleauFiniDomaine.getRouleauFiniByInfoModif(infoModif);
	}

	@Override
	public List<String> getListRefMiseRouleauNonSortie() {
		return rouleauFiniDomaine.getListRefMiseRouleauNonSortie();
	}

	@Override
	public List<String> getListCodeBarreByRefMiseAndIdBSisNull(String refMise) {
		return rouleauFiniDomaine.getListCodeBarreByRefMiseAndIdBSisNull(refMise);
	}
	
}
