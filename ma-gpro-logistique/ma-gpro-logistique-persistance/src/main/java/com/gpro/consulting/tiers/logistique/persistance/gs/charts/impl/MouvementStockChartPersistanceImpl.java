package com.gpro.consulting.tiers.logistique.persistance.gs.charts.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.MouvementStockChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.IMouvementStockChartPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.utilities.MouvementStockChartPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MouvementEntite;

/**
 * Implementation of {@link IMouvementStockChartPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 14/04/2016
 *
 */

@Component
public class MouvementStockChartPersistanceImpl extends AbstractPersistance implements IMouvementStockChartPersistance{

	private String PREDICATE_TYPE = "type";
	private String PREDICATE_DATE = "date";
	private String TABLE_BON_MOUVEMENT = "bonMouvement";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	MouvementStockChartPersistanceUtilities mouvementStockChartPersistanceUtilities;

	@Override
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(RechercheMulticritereMouvementChartValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < MouvementEntite > criteriaQuery = criteriaBuilder.createQuery(MouvementEntite.class);
	    List < Predicate > whereClause = new ArrayList < Predicate >();
	    Root < MouvementEntite > rootMouvement = criteriaQuery.from(MouvementEntite.class);
//	    
//	    if(request.getDateFrom() != null){
//	    	Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = rootMouvement.join(TABLE_BON_MOUVEMENT);
//	    	Expression<Calendar> dateMouvement =jointureMvtBmvt.get(PREDICATE_DATE);
//	    	Calendar dateEntre = request.getDateFrom(); 
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(dateMouvement,dateEntre));
//	    }
//
//		if (request.getDateTo() != null) {
//			Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = rootMouvement.join(TABLE_BON_MOUVEMENT);
//			Expression<Calendar> dateMouvement = jointureMvtBmvt.get(PREDICATE_DATE);
//			Calendar dateSortie = request.getDateTo();
//			whereClause.add(criteriaBuilder.lessThanOrEqualTo(dateMouvement, dateSortie));
//		}
	    
		if(estNonVide(request.getType())){
			whereClause.add(criteriaBuilder.equal(rootMouvement.get(PREDICATE_TYPE), request.getType()));
		}

	    criteriaQuery.select(rootMouvement).where(whereClause.toArray(new Predicate[] {}));
	    List < MouvementEntite > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<MouvementStockChartValue> listMouvementStockChart = new ArrayList < MouvementStockChartValue >();

	    for (MouvementEntite entity : resultatEntite) {
	    	MouvementStockChartValue dto = mouvementStockChartPersistanceUtilities.toValue(entity);
	    	listMouvementStockChart.add(dto);
	    }

	    ResultatRechecheMouvementChartValue result = new ResultatRechecheMouvementChartValue();

	    result.setNombreResultaRechercher(Long.valueOf(listMouvementStockChart.size()));

	    result.setListMouvementStock(listMouvementStockChart);
	    return result;
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
