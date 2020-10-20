package com.gpro.consulting.tiers.logistique.domaine.gl.facon.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.ITraitementFaconDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.IFicheFaconPersistance;

/**
 *  
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class FicheFaconDomaineImpl  extends AbstractPersistance implements IFicheFaconDomaine {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheFaconDomaineImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IFicheFaconPersistance ficheFaconPersistance;
	
	@Autowired
	private ITraitementFaconDomaine traitementFaconDomaine;
	
	@Override
	public String create(FicheFaconValue FicheFaconValue) {
		
		if(FicheFaconValue.getListeTraitementsFiche()!=null){
			for (TraitementFicheValue traitementFicheValue : FicheFaconValue.getListeTraitementsFiche()) {
				TraitementFaconValue traitementFaconValue = traitementFaconDomaine.getById(traitementFicheValue.getTraitementId());
				traitementFicheValue.setPu(traitementFaconValue.getPu());
			}
		}
		return ficheFaconPersistance.create(FicheFaconValue);
	}

	@Override
	public FicheFaconValue getById(Long id) {
		return ficheFaconPersistance.getById(id);
	}

	@Override
	public String update(FicheFaconValue FicheFaconValue) {
		
		if(FicheFaconValue.getListeTraitementsFiche()!=null){
			for (TraitementFicheValue traitementFicheValue : FicheFaconValue.getListeTraitementsFiche()) {
				TraitementFaconValue traitementFaconValue = traitementFaconDomaine.getById(traitementFicheValue.getTraitementId());
				traitementFicheValue.setPu(traitementFaconValue.getPu());
			}
		}
		
	    return ficheFaconPersistance.update(FicheFaconValue);
	}

	@Override
	public void delete(Long id) {
		ficheFaconPersistance.delete(id);
	}

	@Override
	public List<FicheFaconValue> getAll() {
		
		return ficheFaconPersistance.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ResultatRechecheFicheFaconValue rechercherMultiCritere(RechercheMulticritereFicheFaconValue request) {
		return ficheFaconPersistance.rechercherMultiCritere(request);
	}

	@Override
	public List<FicheFaconValue> getFicheByRefBonReception(String refBonReception) {
		return ficheFaconPersistance.getFicheByRefBonReception(refBonReception);
	}

}
