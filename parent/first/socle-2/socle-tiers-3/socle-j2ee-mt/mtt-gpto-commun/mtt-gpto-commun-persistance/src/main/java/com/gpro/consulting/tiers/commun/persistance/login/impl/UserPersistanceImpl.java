package com.gpro.consulting.tiers.commun.persistance.login.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.persistance.login.IUserPersistance;
import com.gpro.consulting.tiers.commun.persistance.login.entity.UserEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * Implementation of {@link IUserPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 03/06/2016
 *
 */
@Component
public class UserPersistanceImpl extends AbstractPersistance implements IUserPersistance{
	@Bean
	static BCryptPasswordEncoder getBCPE(){
		
		return new BCryptPasswordEncoder();
	}	
	private String PREDICATE_FIRSTNAME = "firstName";
	private String PREDICATE_LASTNAME = "lastName";
	private String PREDICATE_EMAIL = "email";
	private String PREDICATE_PONENUMBER = "phoneNumber";
	private String PREDICATE_USERNAME = "userName";
	private String PREDICATE_PASSWORD = "password";
	private String PERCENT = "%";
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ResultatRechecheUserValue rechercherMultiCritere(
			RechercheMulticritereUserValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		
		// Set request.firstName on whereClause if not empty or null
		if (estNonVide(request.getFirstName())) {
			Expression<String> path = root.get(PREDICATE_FIRSTNAME);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getFirstName().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.lastName on whereClause if not empty or null
		if (estNonVide(request.getLastName())) {
			Expression<String> path = root.get(PREDICATE_LASTNAME);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getLastName().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.email on whereClause if not empty or null
		if (estNonVide(request.getEmail())) {
			Expression<String> path = root.get(PREDICATE_EMAIL);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getEmail().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.phoneNumber on whereClause if not empty or null
		if (estNonVide(request.getPhoneNumber())) {
			Expression<String> path = root.get(PREDICATE_PONENUMBER);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getPhoneNumber().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		
		// Set request.userName on whereClause if not empty or null
		if (estNonVide(request.getUserName())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_USERNAME), request.getUserName()));
		}
		
		// Set request.password on whereClause if not empty or null
		if (estNonVide(request.getPassword())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PASSWORD), request.getPassword()));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <UserEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<UserValue> list = new ArrayList<UserValue>();
	    
	    for (UserEntity entity : resultatEntite) {
	    	UserValue dto = PersistanceUtilities.toUserValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheUserValue result = new ResultatRechecheUserValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}

	@Override
	public UserValue login(UserValue request) {
		
		UserValue result = null;
		
		// Set request.login and request.password on whereClause if not empty or null
		if (estNonVide(request.getUserName()) && estNonVide(request.getPassword()) ) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_USERNAME), request.getUserName()));
		//	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PASSWORD), getBCPE().encode(request.getPassword())));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <UserEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	if(resultatEntite.size() > 0){
		    		
		    		
		    		for(UserEntity userEntity :resultatEntite) {
		    			
		    			if(getBCPE().matches(request.getPassword(), userEntity.getPassword()))
		    				return result = PersistanceUtilities.toUserValue(resultatEntite.get(0));
		    			
		    		}
		    		
		    		
		    	}
		    }
		}
		
	    return result;
	}
	
	@Override
	public String create(UserValue request) {
		UserEntity entity = (UserEntity) this.creer(PersistanceUtilities.toUserEntity(request));
	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(UserEntity.class, id.longValue());
	}
	
	@Override
	public String update(UserValue request) {
		
		UserEntity entity = (UserEntity) this.modifier(PersistanceUtilities.toUserEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public UserValue getById(Long id) {
		
		UserEntity entity = this.rechercherParId(id, UserEntity.class);

	    return PersistanceUtilities.toUserValue(entity);
	}
	
	@Override
	public List<UserValue> getAll() {
		
		List<UserEntity> listEntity = this.lister(UserEntity.class);
		
		return PersistanceUtilities.listUserToValue(listEntity);
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
	
 
	@Override

	public UserValue findByUsername(String username) {
		// TODO Auto-generated method stub
		UserValue result = new UserValue();

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
		if (estNonVide(username)) {
			

			Expression<String> path = root.get(PREDICATE_USERNAME);
			Predicate predicate = criteriaBuilder.like(path, username );
			whereClause.add(criteriaBuilder.and(predicate));
		}
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

	    List <UserEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(resultatEntite != null){
	    	
	    	if(resultatEntite.size() > 0){
	    		
	    		result = PersistanceUtilities.toUserValue(resultatEntite.get(0));
	    	}
	    }
		return result;
	}

	
}
