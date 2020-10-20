package com.gpro.consulting.tiers.logistique.service.gl.facon.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.RechercheMulticritereFicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.facon.IFicheFaconService;

/**
 *  
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class FicheFaconServiceImpl  extends AbstractPersistance implements IFicheFaconService {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheFaconServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IFicheFaconDomaine ficheFaconDomaine;
	
	@Override
	public String create(FicheFaconValue FicheFaconValue) {
		return ficheFaconDomaine.create(FicheFaconValue);
	}

	@Override
	public FicheFaconValue getById(Long id) {
		return ficheFaconDomaine.getById(id);
	}

	@Override
	public String update(FicheFaconValue FicheFaconValue) {
	    return ficheFaconDomaine.update(FicheFaconValue);
	}

	@Override
	public void delete(Long id) {
		ficheFaconDomaine.delete(id);
	}

	@Override
	public List<FicheFaconValue> getAll() {
		
		return ficheFaconDomaine.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ResultatRechecheFicheFaconValue rechercherMultiCritere(RechercheMulticritereFicheFaconValue request) {
		return ficheFaconDomaine.rechercherMultiCritere(request);
	}

}
