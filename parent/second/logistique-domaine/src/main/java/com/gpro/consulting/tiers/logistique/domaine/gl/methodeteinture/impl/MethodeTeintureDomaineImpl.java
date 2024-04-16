package com.gpro.consulting.tiers.logistique.domaine.gl.methodeteinture.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.methodeteinture.value.MethodeTeintureValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.methodeteinture.IMethodeTeintureDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.IMethodeTeinturePersistance;

/**
 *
 * @author Zeineb Medimagh
 * @since 03/10/2016
 *
 */

@Component
public class MethodeTeintureDomaineImpl  extends AbstractPersistance implements IMethodeTeintureDomaine {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IMethodeTeinturePersistance methodeTeinturePersistance;
	
	@Override
	public String create(MethodeTeintureValue MethodeTeintureValue) {
		return methodeTeinturePersistance.create(MethodeTeintureValue);
	}

	@Override
	public MethodeTeintureValue getById(Long id) {
		return methodeTeinturePersistance.getById(id);
	}

	@Override
	public String update(MethodeTeintureValue MethodeTeintureValue) {
	    return methodeTeinturePersistance.update(MethodeTeintureValue);
	}

	@Override
	public void delete(Long id) {
		methodeTeinturePersistance.delete(id);
	}

	@Override
	public List<MethodeTeintureValue> getAll() {
		
		return methodeTeinturePersistance.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


}
