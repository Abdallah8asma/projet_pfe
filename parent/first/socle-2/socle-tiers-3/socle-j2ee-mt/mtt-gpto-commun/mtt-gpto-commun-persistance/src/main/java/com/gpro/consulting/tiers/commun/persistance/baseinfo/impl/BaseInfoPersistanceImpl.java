package com.gpro.consulting.tiers.commun.persistance.baseinfo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.entity.BaseInfoEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * Implementation of {@link IBaseInfoPersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */
@Component
public class BaseInfoPersistanceImpl extends AbstractPersistance implements IBaseInfoPersistance {

	@PersistenceContext
	private EntityManager entityManager;

	private String PREDICATE_ACTIF="actif" ;
	
	@Override
	public String create(BaseInfoValue baseInfoValue) {

		BaseInfoEntity entity = (BaseInfoEntity) this
				.creer(PersistanceUtilities.toBaseInfoEntity(baseInfoValue));

		return entity.getId().toString();
	}

	@Override
	public BaseInfoValue getById(Long id) {

		BaseInfoEntity entity = this.rechercherParId(id, BaseInfoEntity.class);

		return PersistanceUtilities.toBaseInfoValue(entity);
	}

	@Override
	public String update(BaseInfoValue baseInfoValue) {

		BaseInfoEntity entity = (BaseInfoEntity) this
				.modifier(PersistanceUtilities.toBaseInfoEntity(baseInfoValue));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {

		this.supprimerEntite(BaseInfoEntity.class, id.longValue());
	}

	@Override
	public List<BaseInfoValue> getAll() {

		List<BaseInfoEntity> listEntity = this.lister(BaseInfoEntity.class);

		List<BaseInfoValue> result = new ArrayList<BaseInfoValue>();

		if (result != null) {

			for (BaseInfoEntity entity : listEntity) {

				result.add(PersistanceUtilities.toBaseInfoValue(entity));
			}
		}

		return result;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String getLogo() {
		
		BaseInfoValue value = this.getClientActif();
		
		if( value != null){
			return IConstante.LOGO_BASE_URL + value.getLogo();
		}
		
		return null;
	}

	@Override
	public BaseInfoValue getClientActif() {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < BaseInfoEntity > vCriteriaQuery = vBuilder.createQuery(BaseInfoEntity.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();

	    /** create liste resyltat ***/

	    Root < BaseInfoEntity > root = vCriteriaQuery.from(BaseInfoEntity.class);

	    vWhereClause.add(vBuilder.equal(root.get(PREDICATE_ACTIF),true));
	    
	    /** execute query and do something with result **/

	    vCriteriaQuery.select(root).where(vWhereClause.toArray(new Predicate[] {}));
	    BaseInfoEntity entity = this.entityManager.createQuery(vCriteriaQuery).getSingleResult();

	    /** Conversion de la liste en valeur */
	    BaseInfoValue finalResult = new BaseInfoValue();
	   
	    if(entity!=null){
	    	finalResult = PersistanceUtilities.toBaseInfoValue(entity);
	    }
	    
	    return finalResult;

	}

	@Override
	public Long getGuichetClient() {
		// TODO Auto-generated method stub
		return this.getClientActif().getGuichetClient()+1;
	}

	@Override
	public Long getGuichetFournisseur() {
		// TODO Auto-generated method stub
		return this.getClientActif().getGuichetFournisseur()+1;
	}

}
