package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereMarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheMarcheValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IMarcheDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;

/**
 * implementation of {@link IMarcheService}
 * 
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

@Service
@Transactional
public class MarcheServiceImpl implements IMarcheService {
	
	private static final Logger logger = LoggerFactory.getLogger(MarcheServiceImpl.class);
	
	@Autowired
	private IMarcheDomaine marcheServiceDomaine;
	
	@Override
	public String create(MarcheValue marcheValue) {
		
		return marcheServiceDomaine.create(marcheValue);
	}

	@Override
	public MarcheValue getById(Long id) {
		
		return marcheServiceDomaine.getById(id);
	}

	@Override
	public String update(MarcheValue marcheValue) {
		
		return marcheServiceDomaine.update(marcheValue);
	}

	@Override
	public void delete(Long id) {
		
		marcheServiceDomaine.delete(id);
	}

	@Override
	public List<MarcheValue> getAll() {
		
		//logger.info("--------Delegating request to domaine layer.");
		
		return marcheServiceDomaine.getAll();
	}

	
	@Override
	public ResultatRechecheMarcheValue rechercherMultiCritere(RechercheMulticritereMarcheValue request) {
		return marcheServiceDomaine.rechercherMultiCritere(request);
	}
	
	
	@Override
	public List<MarcheValue> getListById(Long id) {
		
		return marcheServiceDomaine.getListById(id);
	}
	
}
