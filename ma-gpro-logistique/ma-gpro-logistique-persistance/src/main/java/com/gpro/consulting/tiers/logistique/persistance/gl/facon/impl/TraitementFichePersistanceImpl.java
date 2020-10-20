package com.gpro.consulting.tiers.logistique.persistance.gl.facon.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IDetLivraisonVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.IFicheFaconPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.ITraitementFichePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity.FicheFaconEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity.TraitementFicheEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.utilities.FaconPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IFicheSuiveusePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.FicheSuiveuseEntity;

/**
 * Implementation of {@link IFicheSuiveusePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */

@Component
public class TraitementFichePersistanceImpl  extends AbstractPersistance implements ITraitementFichePersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(TraitementFichePersistanceImpl.class);
	
	private String PREDICATE_TRAITEMENTID = "traitementId";
	
	@Autowired
	private IFicheFaconPersistance ficheFaconPersistance;
	
	
	@Autowired
	private IDetLivraisonVentePersistance detLivraisonVentePersistance;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String create(TraitementFicheValue TraitementFicheValue) {
		TraitementFicheEntity entity = (TraitementFicheEntity) this.creer(FaconPersistanceUtilities.toEntity(TraitementFicheValue));
	    return entity.getId().toString();
	}

	@Override
	public TraitementFicheValue getById(Long id) {
		TraitementFicheEntity entity = this.rechercherParId(id, TraitementFicheEntity.class);

	    return FaconPersistanceUtilities.toValue(entity);
	}

	@Override
	public String update(TraitementFicheValue TraitementFicheValue) {
		TraitementFicheEntity entity = (TraitementFicheEntity) this.modifier(FaconPersistanceUtilities.toEntity(TraitementFicheValue));
	    return entity.getId().toString();
	}
	
	public String update(TraitementFicheValue TraitementFicheValue, FicheFaconEntity ficheFaconEntity) {
		TraitementFicheEntity entity = (TraitementFicheEntity) this.modifier(FaconPersistanceUtilities.toEntity(TraitementFicheValue, ficheFaconEntity));
		//System.out.println("--- Update : traitementFicheEntity ---" + entity.toString());
		
		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(TraitementFicheEntity.class, id.longValue());
	}

	@Override
	public List<TraitementFicheValue> getAll() {
		
		List<TraitementFicheEntity> listEntity = this.lister(TraitementFicheEntity.class);
		
		List<TraitementFicheValue> listValue = new ArrayList<TraitementFicheValue>();
		
		for(TraitementFicheEntity entity : listEntity){
			
			listValue.add(FaconPersistanceUtilities.toValue(entity));
		}
		
		return listValue;
	}

	@Override
	public String setTraitementPU(Long id , Double nouveauPU, Long idFiche) {
		
	
		FicheFaconValue ficheFaconValue = ficheFaconPersistance.getById(idFiche);
		FicheFaconEntity ficheFaconEntity = FaconPersistanceUtilities.toEntity(ficheFaconValue);
		
		TraitementFicheValue traitementFicheValue= this.getById(id);
		
		//Update dans detLivraison
		detLivraisonVentePersistance.setTraitementPU(traitementFicheValue.getTraitementId(), nouveauPU, idFiche);
				
		//Update dans traitementFiche
		traitementFicheValue.setPu(nouveauPU);		
		return this.update(traitementFicheValue, ficheFaconEntity);
	}
}
