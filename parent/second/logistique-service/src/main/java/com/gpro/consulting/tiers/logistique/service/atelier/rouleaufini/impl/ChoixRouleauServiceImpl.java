package com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IChoixRouleauDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IChoixRouleauService;

/**
 * implementation of {@link IChoixRouleauService}
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Service
@Transactional
public class ChoixRouleauServiceImpl implements IChoixRouleauService{
	
	private static final Logger logger = LoggerFactory.getLogger(ChoixRouleauServiceImpl.class);

	@Autowired
	private IChoixRouleauDomaine choixRouleauDomaine;
	
	
	@Override
	public ChoixRouleauValue getChoixRouleauById(Long id) {
		//logger.info("Trouver ChoixRouleauValue par id: "+id);
		
		return choixRouleauDomaine.getChoixRouleauById(id);
	}


	@Override
	public List<ChoixRouleauValue> listeChoixRouleau() {
		//logger.info("liste ChoixRouleauValue ");
		
		return choixRouleauDomaine.listeChoixRouleau();
	}

}
