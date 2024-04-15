package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.GrosseurEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IRegionPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.RegionEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class RegionPersistanceImpl extends AbstractPersistance implements
		IRegionPersistance {

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager;

	
	
	/** Logger */
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RegionPersistanceImpl.class);
	
	@Override
	public String creer(RegionValue pRegionValue) {
		
		RegionEntite entite = (RegionEntite) this.creer(PersistanceUtilities.toEntity(pRegionValue));
			   
		return entite.getId().toString();
	}

	@Override
	public void supprimer(Long pId) {
		this.supprimerEntite(RegionEntite.class, pId.longValue());

	}

	@Override
	public String modifier(RegionValue pRegionValue) {
		RegionEntite entite = (RegionEntite) this.modifier(PersistanceUtilities.toEntity(pRegionValue));
		   
		return entite.getId().toString();
	}

	@Override
	public List<RegionValue> listeRegion() {
		List<RegionEntite> vListeEntite = this.lister(RegionEntite.class);
		List<RegionValue> vListValues = new ArrayList<RegionValue>();
		for (RegionEntite vEntite : vListeEntite) {
			vListValues.add(PersistanceUtilities.toValue(vEntite));
		}
		return vListValues;
	}

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public RegionValue getById(Long pId) {
		RegionEntite pRegionEntite=(RegionEntite) this.rechercherParId(pId, RegionEntite.class);
		
		if(pRegionEntite != null) {
			RegionValue pRegionValueResult=PersistanceUtilities.toValue(pRegionEntite);
			return pRegionValueResult;
		}
		else 
			return null;
	
	}

}
