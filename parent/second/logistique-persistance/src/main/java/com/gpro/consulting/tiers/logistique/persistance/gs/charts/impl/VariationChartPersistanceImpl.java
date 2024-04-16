package com.gpro.consulting.tiers.logistique.persistance.gs.charts.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.ResultatRechecheVariationChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value.VariationChartValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.IVariationChartPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.utilities.VariationChartPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.EntiteStockEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MouvementEntite;

/**
 * Implementation of {@link IVariationChartPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 26/04/2016
 *
 */

@Component
public class VariationChartPersistanceImpl extends AbstractPersistance implements IVariationChartPersistance{

	private String PREDICATE_TYPE = "type";
	private String PREDICATE_DATE = "date";
	private String TABLE_BON_MOUVEMENT = "bonMouvement";
	
	private String ID = "id";
	private String TYPE_ARTICLE = "typeArticle";
	private String FAMILLE = "familleArticle";
	private String ENTRE_STOK="entiteStock";
	private String ARTICLE="article";
	private String SOUS_FAMILLE="sousFamilleArtEntite";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	VariationChartPersistanceUtilities variationChartPersistanceUtilities;
	
	@Override
	public ResultatRechecheVariationChartValue rechercherMultiCritere(RechercheMulticritereVariationChartValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < MouvementEntite > criteriaQuery = criteriaBuilder.createQuery(MouvementEntite.class);
	    List < Predicate > whereClause = new ArrayList < Predicate >();
	    Root < MouvementEntite > rootMouvement = criteriaQuery.from(MouvementEntite.class);
	    
	    if (request.getSousFamilleId() != null) {
	    	Join<MouvementEntite, EntiteStockEntite> jointureMouvementEntiteStock = rootMouvement.join(ENTRE_STOK);
	    	Join<EntiteStockEntite, ArticleEntite> jointureEntiteStockArticle = jointureMouvementEntiteStock.join(ARTICLE);
	    	Join<ArticleEntite, SousFamilleArticleEntity> jointureArticleSouFamilleArticle = jointureEntiteStockArticle.join(SOUS_FAMILLE);
	    	whereClause.add(criteriaBuilder.equal(jointureArticleSouFamilleArticle.get(ID), request.getSousFamilleId()));
	    }
	    
	    if (request.getArticleId() != null) {
	    	   Join<MouvementEntite, EntiteStockEntite> jointureMouvementEntiteStock = rootMouvement.join(ENTRE_STOK);
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEntiteStockArticle = jointureMouvementEntiteStock.join(ARTICLE);
	    	   whereClause.add(criteriaBuilder.equal(jointureEntiteStockArticle.get(ID), request.getArticleId()));
	    }
	    
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
	    
		if(estNonVide(request.getTypeMouvement())){
			whereClause.add(criteriaBuilder.equal(rootMouvement.get(PREDICATE_TYPE), request.getTypeMouvement()));
		}
		
		if (estNonVide(request.getTypeArticle())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMouvementEntiteStock = rootMouvement.join(ENTRE_STOK);
			Join<EntiteStockEntite, ArticleEntite> jointureEntiteStockArticle = jointureMouvementEntiteStock.join(ARTICLE);
			Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEntiteStockArticle.join(SOUS_FAMILLE);
			Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureArticleSouFamilleArticle = jointureArtSousF.join(FAMILLE);
			Join<FamilleArticleEntity, TypeArticleEntity> jointureFamilleTypeArticle = jointureArticleSouFamilleArticle.join(TYPE_ARTICLE);
			whereClause.add(criteriaBuilder.equal(jointureFamilleTypeArticle.get(ID), request.getTypeArticle()));
		}

	    criteriaQuery.select(rootMouvement).where(whereClause.toArray(new Predicate[] {}));
	    List < MouvementEntite > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<VariationChartValue> listVariation = new ArrayList < VariationChartValue >();

	    for (MouvementEntite entity : resultatEntite) {
	    	VariationChartValue dto = variationChartPersistanceUtilities.toValue(entity);
	    	listVariation.add(dto);
	    }

	    ResultatRechecheVariationChartValue result = new ResultatRechecheVariationChartValue();

	    result.setNombreResultaRechercher(Long.valueOf(listVariation.size()));

	    result.setListVariation(listVariation);
	   
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
