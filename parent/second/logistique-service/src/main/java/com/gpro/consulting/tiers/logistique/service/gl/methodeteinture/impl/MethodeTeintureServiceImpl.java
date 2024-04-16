package com.gpro.consulting.tiers.logistique.service.gl.methodeteinture.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gl.methodeteinture.value.MethodeTeintureValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.methodeteinture.IMethodeTeintureDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.methodeteinture.IMethodeTeintureService;

/**
 *  
 * @author Zeineb Medimagh
 * @since 03/10/2016
 *
 */

@Component
public class MethodeTeintureServiceImpl  extends AbstractPersistance implements IMethodeTeintureService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodeTeintureServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IMethodeTeintureDomaine methodeTeintureDomaine;
	
	@Override
	public String create(MethodeTeintureValue MethodeTeintureValue) {
		return methodeTeintureDomaine.create(MethodeTeintureValue);
	}

	@Override
	public MethodeTeintureValue getById(Long id) {
		return methodeTeintureDomaine.getById(id);
	}

	@Override
	public String update(MethodeTeintureValue MethodeTeintureValue) {
	    return methodeTeintureDomaine.update(MethodeTeintureValue);
	}

	@Override
	public void delete(Long id) {
		methodeTeintureDomaine.delete(id);
	}

	@Override
	public List<MethodeTeintureValue> getAll() {
		
		return methodeTeintureDomaine.getAll();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
