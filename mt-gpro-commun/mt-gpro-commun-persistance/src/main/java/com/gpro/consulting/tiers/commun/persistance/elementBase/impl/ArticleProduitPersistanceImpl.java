package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class ArticleProduitPersistanceImpl extends AbstractPersistance implements IArticleProduitPersistance  {
	@PersistenceContext
	private EntityManager entityManager;

	
	private String PREDICATE_produitSemiFini = "produitSemiFini";
	private String PREDICATE_grammage ="grammage";
	private String PREDICATE_DIMENSION ="dimension";
	private String PREDICATE_INTRO_MATIERE = "infoMatiere";	
	private String PREDICATE_QUNATITE="qte";
	private String PREDICATE_articleId="id";
	private String impressionProduitId="impressionProduitId";
	private String sousFamilleArticleId="sousFamilleArticleId";

	
	private int MAX_RESULTS =52;
	@Override
	public ResultatRechecheArticleProduitValue rechercherMultiCritere(
			RechercheMulticritereArticleProduitValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<ArticleProduitEntity> criteriaQuery = criteriaBuilder.createQuery(ArticleProduitEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<ArticleProduitEntity> root = criteriaQuery.from(ArticleProduitEntity.class);
		
		 if (estNonVide(request.getInfoMatiere() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_INTRO_MATIERE), request.getInfoMatiere()));
			}
		 if (estNonVide(request.getDimension() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_DIMENSION), request.getDimension()));
			}
		 if (estNonVide(request.getGrammage() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_grammage), request.getGrammage()));
			}
		    
		if(request.getQteDe()!=null)
		{
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_QUNATITE), request.getQteDe()));

		}
		if(request.getQteA()!=null)
		{
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_QUNATITE), request.getQteA()));

		}
	
         if(request.getArticleId()!=null)
			
		{
			Join<ArticleProduitEntity,ProduitEntity> jointArticleProduit = root.join("produit");

			whereClause.add(criteriaBuilder.equal( jointArticleProduit.get(PREDICATE_articleId), request.getArticleId()));

		}
         if (estNonVide(request.getProduitSemiFini() )) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_produitSemiFini), request.getProduitSemiFini()));
			}
         if (request.getImpressionProduitId() !=null) {
				whereClause.add(criteriaBuilder.equal(root.get(impressionProduitId), request.getImpressionProduitId()));
			}
         if(request.getSousFamilleArticleId()!=null)
         {
				whereClause.add(criteriaBuilder.equal(root.get(sousFamilleArticleId), request.getSousFamilleArticleId()));

         }
 
		

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
			
			
	        List<ArticleProduitEntity> resultatEntite = null;
			
			if(request.isOptimized())
			{
				resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();
			}
			
			else
				
			{
				resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
			}

			
			
			List<ArticleProduitValue> list = new ArrayList<ArticleProduitValue>();

			for (ArticleProduitEntity entity : resultatEntite) {
				ArticleProduitValue dto =PersistanceUtilities.toValueEnrichi(entity);
				list.add(dto);
			}
			
			

			ResultatRechecheArticleProduitValue result = new ResultatRechecheArticleProduitValue();
			result.setNombreResultaRechercher(Long.valueOf(list.size()));
			//Collections.sort(list);
			result.setList(list);
		
			
			
		


			return result;
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
	}

}
