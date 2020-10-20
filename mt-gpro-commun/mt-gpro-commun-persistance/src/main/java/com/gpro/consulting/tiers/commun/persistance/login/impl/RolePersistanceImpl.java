package com.gpro.consulting.tiers.commun.persistance.login.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.persistance.login.IRolePersistance;
import com.gpro.consulting.tiers.commun.persistance.login.entity.RoleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
/**
 * Implementation of {@link IRolePersistance} interface.
 *  
 * @author Nour

 *
 */
@Component
public class RolePersistanceImpl  extends AbstractPersistance implements IRolePersistance {

	private String PREDICATE_CODE = "code";
	private String PREDICATE_MODULE = "module";
	private String PREDICATE_DESIGNATION = "designation";
	private String PREDICATE_VERSION = "version";
	private String PERCENT = "%";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<RoleValue> rechercherMultiCritere(
			RoleValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<RoleEntity> criteriaQuery = criteriaBuilder.createQuery(RoleEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<RoleEntity> root = criteriaQuery.from(RoleEntity.class);
		
		// Set request.firstName on whereClause if not empty or null
		if (estNonVide(request.getDesignation())) {
			Expression<String> path = root.get(PREDICATE_DESIGNATION);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getDesignation().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		if (estNonVide(request.getModule())) {
			Expression<String> path = root.get(PREDICATE_MODULE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getModule().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		if (estNonVide(request.getCode())) {
			Expression<String> path = root.get(PREDICATE_CODE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getCode().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.lastName on whereClause if not empty or null
	//	if (estNonVide(request.getVersion())) {
		/*	Expression<String> path = root.get(PREDICATE_VERSION);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getVersion().toUpperCase() + PERCENT);
			*/
		//	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_VERSION), request.getVersion()));
	//	}
		
	
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <RoleEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<RoleValue> list = new ArrayList<RoleValue>();
	    
	    for (RoleEntity entity : resultatEntite) {
	    	RoleValue dto = PersistanceUtilities.toRoleValue(entity);
	    	list.add(dto);
	    }
	    

	    
		return list;
	}
	
	
	@Override
	public String create(RoleValue request) {

		//System.out.println("Designation Role : "+request.getDesignation() );
		
		RoleEntity entity = (RoleEntity) this.creer(PersistanceUtilities.toRoleEntity(request));

	    return entity.getId().toString();
	}
	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(RoleEntity.class, id.longValue());
	}
	
	@Override
	public String update(RoleValue request) {
		
		RoleEntity entity = (RoleEntity) this.modifier(PersistanceUtilities.toRoleEntity(request));

	    return entity.getId().toString();
	}
	
	@Override
	public List<RoleValue> getAll() {
		
		List<RoleEntity> listEntity = this.lister(RoleEntity.class);
		
		return PersistanceUtilities.listRoleToValue(listEntity);
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}


	@Override
	public RoleValue getById(Long id) {
		RoleEntity entity = this.rechercherParId(id, RoleEntity.class);

	    return PersistanceUtilities.toRoleValue(entity);
	}


	@Override
	public RoleValue findByRoleName(String roleName) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<RoleEntity> criteriaQuery = criteriaBuilder.createQuery(RoleEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<RoleEntity> root = criteriaQuery.from(RoleEntity.class);
		if (estNonVide(roleName)) {
			Expression<String> path = root.get(PREDICATE_DESIGNATION);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + roleName.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    RoleValue result= new RoleValue();
	    List <RoleEntity> res = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(res != null){
	    	
	    	if(res.size() > 0){
	    		
	    		result = PersistanceUtilities.toRoleValue(res.get(0));
	    	}
	    }
		return result;
	}


	@Override
	public RoleValue getByCode(String code) {

		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<RoleEntity> criteriaQuery = criteriaBuilder.createQuery(RoleEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<RoleEntity> root = criteriaQuery.from(RoleEntity.class);
		
		
		
		
		if (estNonVide(code)) {

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CODE), code));
		}
		

	
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <RoleEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(resultatEntite.size() > 0)
	    	return PersistanceUtilities.toRoleValue(resultatEntite.get(0));

	 
	    

	    
		return null;
	}
}
