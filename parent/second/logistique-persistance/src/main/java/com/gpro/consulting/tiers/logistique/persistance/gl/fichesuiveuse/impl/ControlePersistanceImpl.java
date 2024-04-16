package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IControlePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.ControleEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.utilities.FicheSuiveusePersistanceUtilities;

/**
 * Implementation of {@link IControlePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */

@Component
public class ControlePersistanceImpl extends AbstractPersistance implements IControlePersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveusePersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FicheSuiveusePersistanceUtilities ficheSuiveusePersistanceUtilities;
	
	@Override
	public String create(ControleValue request) {

		ControleEntity entity = (ControleEntity) this.creer(ficheSuiveusePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(ControleEntity.class, id.longValue());
	}
	
	@Override
	public String update(ControleValue request) {
		
		ControleEntity entity = (ControleEntity) this.modifier(ficheSuiveusePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public ControleValue getById(Long id) {
		
		ControleEntity entity = this.rechercherParId(id, ControleEntity.class);

	    return ficheSuiveusePersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<ControleValue> getAll() {
		
		List<ControleEntity> listEntity = this.lister(ControleEntity.class);
		
		return ficheSuiveusePersistanceUtilities.listToValue(listEntity);
	}



	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
