package com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.methodeteinture.value.MethodeTeintureValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.IMethodeTeinturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.entity.MethodeTeintureEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.utilities.MethodeTeinturePersistanceUtilities;

/**
 *
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class MethodeTeinturePersistanceImpl  extends AbstractPersistance implements IMethodeTeinturePersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodeTeinturePersistanceImpl.class);
	
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_REF_BON_RECEPTION = "refBonReception";
	private String PERCENT = "%";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String create(MethodeTeintureValue request) {

		MethodeTeintureEntity entity = (MethodeTeintureEntity) this.creer(MethodeTeinturePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(MethodeTeintureEntity.class, id.longValue());
	}
	
	@Override
	public String update(MethodeTeintureValue request) {
		
		MethodeTeintureEntity entity = (MethodeTeintureEntity) this.modifier(MethodeTeinturePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public MethodeTeintureValue getById(Long id) {
		
		MethodeTeintureEntity entity = this.rechercherParId(id, MethodeTeintureEntity.class);

	    return MethodeTeinturePersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<MethodeTeintureValue> getAll() {
		
		List<MethodeTeintureEntity> listEntity = this.lister(MethodeTeintureEntity.class);
		
		List<MethodeTeintureValue> listDTO = new ArrayList<MethodeTeintureValue>();
		
		for(MethodeTeintureEntity entity : listEntity){
			
			listDTO.add(MethodeTeinturePersistanceUtilities.toValue(entity));
		}
		
		return listDTO;
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
