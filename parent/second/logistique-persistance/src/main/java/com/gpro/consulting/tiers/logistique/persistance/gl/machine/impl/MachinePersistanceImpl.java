package com.gpro.consulting.tiers.logistique.persistance.gl.machine.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.MachineEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.machine.IMachinePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.machine.utilities.MachinePersistanceUtilities;

/**
 * @author Wahid Gazzah
 * @since 31/03/2016
 */

@Component
public class MachinePersistanceImpl extends AbstractPersistance implements IMachinePersistance{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	MachinePersistanceUtilities machinePersistanceUtilities;

	@Override
	public MachineValue getById(Long id) {
		
		MachineEntity entity = this.rechercherParId(id, MachineEntity.class);

	    return machinePersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<MachineValue> getAll() {
		
		List<MachineEntity> listEntity = this.lister(MachineEntity.class);
		
		return machinePersistanceUtilities.listToValue(listEntity);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String create(MachineValue request) {
		MachineEntity entity = (MachineEntity) this.creer(machinePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public String update(MachineValue request) {
		MachineEntity entity = (MachineEntity) this.modifier(machinePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(MachineEntity.class, id.longValue());
		
	}
	
}