package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.GrosseurEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MatiereArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MetrageEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class ArticlePersistanceImpl extends AbstractPersistance implements IArticlePersistance{

	 /** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;

	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticlePersistanceImpl.class);
      private String pu="pu";
      private String ref="ref";
      private String designation="designation";
      private String sousFamilleArtEntite="sousFamilleArtEntite";
      private String familleArticle="familleArticle";
      private String id="id";
      private String typeArticle="typeArticle";


      
      private String dimension="dimension";
      private String grammage="grammage";
      private String dimensionPapier="dimensionPapier";
      
      private String nbrCouleur="nbrCouleur";
      private String fichier="fichier";
      private String nbrPose="nbrPose"; 
      
      private String produitId="produitId"; 
      private String  piEntite="piEntite";
      
	@Override
	public String creerArticle(ArticleValue pArticleValue) {
		// TODO Auto-generated method stub
		 MetrageEntite vMetrage=new MetrageEntite();
		 MatiereArticleEntite vMatiere=new MatiereArticleEntite();
		 GrosseurEntite vGrosseur=new GrosseurEntite();
		 SousFamilleArticleEntity vSFm=new SousFamilleArticleEntity();

		 if(pArticleValue.getMetrageEntite() !=null){
			  vMetrage=this.rechercherParId(pArticleValue.getMetrageEntite(), MetrageEntite.class);

		}
		if(pArticleValue.getMatiereEntite() !=null){
		 vMatiere=this.rechercherParId(pArticleValue.getMatiereEntite(), MatiereArticleEntite.class);

		}
		
		if(pArticleValue.getGrosseurEntite() !=null){
			 vGrosseur=this.rechercherParId(pArticleValue.getGrosseurEntite(), GrosseurEntite.class);

		}
		if(pArticleValue.getSousFamilleArtEntite()!=null){
			vSFm=this.rechercherParId(pArticleValue.getSousFamilleArtEntite(), SousFamilleArticleEntity.class);
		}
			  	
		    ArticleEntite vArticleEntite = (ArticleEntite) this.creer(PersistanceUtilities.toArticleEntity(pArticleValue, vSFm, vMetrage, vGrosseur, vMatiere));
		
		    if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Persister l'article d'ID=" + vArticleEntite.getDesignation().toString() + " est en cours.");
		    }	     
		return vArticleEntite.getId().toString();
	}
	

	@Override
	public void supprimerArticle(Long pId) {
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Suppression de l'article d'ID=" + pId.toString() + " est en cours.");
		    }
		    this.supprimerEntite(ArticleEntite.class, pId.longValue());

		
	}

	@Override
	public String modifierArticle(ArticleValue pArticleValue) {
		MetrageEntite vMetrage=new MetrageEntite();
		 MatiereArticleEntite vMatiere=new MatiereArticleEntite();
		 GrosseurEntite vGrosseur=new GrosseurEntite();
		 
		if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Modification de l'article  d'ID=" + pArticleValue.getId().toString() + " est en cours.");
		    }
		  SousFamilleArticleEntity vSFm=this.rechercherParId(pArticleValue.getSousFamilleArtEntite(), SousFamilleArticleEntity.class);
		  
		  if(pArticleValue.getMetrageEntite() !=null){
			  vMetrage=this.rechercherParId(pArticleValue.getMetrageEntite(), MetrageEntite.class);

		}
		if(pArticleValue.getMatiereEntite() !=null){
		 vMatiere=this.rechercherParId(pArticleValue.getMatiereEntite(), MatiereArticleEntite.class);

		}
		
		if(pArticleValue.getGrosseurEntite() !=null){
			 vGrosseur=this.rechercherParId(pArticleValue.getGrosseurEntite(), GrosseurEntite.class);

		}

		    ArticleEntite vArticleEntite = (ArticleEntite) this.modifier(PersistanceUtilities.toArticleEntity(pArticleValue, vSFm, vMetrage, vGrosseur, vMatiere));

		    return vArticleEntite.getId().toString();

	}

	@Override
	public ArticleValue rechercheArticleParId(ArticleValue pArticleValue) {
		 if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Recherche de l'article d'ID=" + pArticleValue.getId().toString() + " est en cours.");
		    }
		    ArticleEntite pArticleEntite = this.rechercherParId(pArticleValue.getId().longValue(),
		      ArticleEntite.class);

		    ArticleValue vArticleValueResult = PersistanceUtilities.toArticleValue(pArticleEntite);

		return vArticleValueResult;
	}

	@Override
	public List<ArticleValue> listeArticle() {
		 List < ArticleEntite > vListeArticleEntite = this.lister(ArticleEntite.class);
		    List < ArticleValue > vListArticleValues = new ArrayList < ArticleValue >();
		    for (ArticleEntite vArticleEntite : vListeArticleEntite) {
		      vListArticleValues.add(PersistanceUtilities.toArticleValue(vArticleEntite));
		    }
		    return vListArticleValues;
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
	    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < ArticleEntite > vCriteriaQuery = vBuilder.createQuery(ArticleEntite.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();

	    /** create liste resyltat ***/

	    /************ entity jointure *****************/
	    Root < ArticleEntite > vRootArticle = vCriteriaQuery.from(ArticleEntite.class);

	    /***************** Predicate *************/
	    if (estNonVide(pRechercheArticleMulitCritere.getRef())) {
	      vWhereClause.add(vBuilder.equal(vRootArticle.get(ref),
	    		  pRechercheArticleMulitCritere.getRef()));
	    }

	    if (estNonVide(pRechercheArticleMulitCritere.getDesignation())) {
	      vWhereClause.add(vBuilder.equal(vRootArticle.get(designation),
	    		  pRechercheArticleMulitCritere.getDesignation()));
	    }

	   if (estNonVide(pRechercheArticleMulitCritere.getFamilleEntite())) {
		  	  Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousFm = vRootArticle.join(sousFamilleArtEntite);
		  	  Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSFmFamille = jointureArtSousFm.join(familleArticle);
	      vWhereClause.add(vBuilder.equal(jointureSFmFamille.get(id),
	    		  pRechercheArticleMulitCritere.getFamilleEntite()));
	    }

	    if (estNonVide(pRechercheArticleMulitCritere.getSousFamilleEntite())) {
		  	  Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousFm = vRootArticle.join(sousFamilleArtEntite);
	      vWhereClause.add(vBuilder.equal(jointureArtSousFm.get(id),
	    		  pRechercheArticleMulitCritere.getSousFamilleEntite()));
	    }
	    if (estNonVide(pRechercheArticleMulitCritere.getTypeEntite())) {
	    	  Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousFm = vRootArticle.join(sousFamilleArtEntite);
		  	  Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSFmFamille = jointureArtSousFm.join(familleArticle);
		  	  Join<FamilleArticleEntity, TypeArticleEntity> jointureSousFmType= jointureSFmFamille.join(typeArticle);
	      vWhereClause.add(vBuilder.equal(jointureSousFmType.get(id),
	    		  pRechercheArticleMulitCritere.getTypeEntite()));
	    }
	    if (estNonVide(pRechercheArticleMulitCritere.getSite())) {
	      vWhereClause.add(vBuilder.equal(vRootArticle.get("siteEntite"), pRechercheArticleMulitCritere.getSite()));
	    }
	   
	      if( pRechercheArticleMulitCritere.getPrix_inf()!=null ){
	  	    Expression<Double> rootValeur =vRootArticle.get(pu);
	        vWhereClause.add(vBuilder.ge(rootValeur, pRechercheArticleMulitCritere.getPrix_inf()));
	      }
	      if( pRechercheArticleMulitCritere.getPrix_sup()!=null ){
		  	    Expression<Double> rootValeur =vRootArticle.get(pu);
		        vWhereClause.add(vBuilder.le(rootValeur, pRechercheArticleMulitCritere.getPrix_sup()));
		      }
	      
	      
	     
		    if (estNonVide(pRechercheArticleMulitCritere.getDimension())) {
			      vWhereClause.add(vBuilder.equal(vRootArticle.get(dimension),
			    		  pRechercheArticleMulitCritere.getDimension()));
			    }

		    if (estNonVide(pRechercheArticleMulitCritere.getGrammage())) {
			      vWhereClause.add(vBuilder.equal(vRootArticle.get(grammage),
			    		  pRechercheArticleMulitCritere.getGrammage()));
			    }
	    
		    if (estNonVide(pRechercheArticleMulitCritere.getDimensionPapier())) {
			      vWhereClause.add(vBuilder.equal(vRootArticle.get(dimensionPapier),
			    		  pRechercheArticleMulitCritere.getDimensionPapier()));
			    }
		    if (estNonVide(pRechercheArticleMulitCritere.getNbrCouleur())) {
			      vWhereClause.add(vBuilder.equal(vRootArticle.get(nbrCouleur),
			    		  pRechercheArticleMulitCritere.getNbrCouleur()));
			    }
		    
		    if (estNonVide(pRechercheArticleMulitCritere.getFichier())) {
			      vWhereClause.add(vBuilder.equal(vRootArticle.get(fichier),
			    		  pRechercheArticleMulitCritere.getFichier()));
			    }
		    
		    if (pRechercheArticleMulitCritere.getProduitId()!=null) {
			      vWhereClause.add(vBuilder.equal(vRootArticle.get(produitId),
			    		  pRechercheArticleMulitCritere.getProduitId()));
			    }
		    
		    if (pRechercheArticleMulitCritere.getPiEntite()!=null) {
			      vWhereClause.add(vBuilder.equal(vRootArticle.get(piEntite),
			    		  pRechercheArticleMulitCritere.getPiEntite()));
			    }
		    
		    
	    /** execute query and do something with result **/

	    vCriteriaQuery.select(vRootArticle).where(vWhereClause.toArray(new Predicate[] {}));
	    List < ArticleEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

	    /** Conversion de la liste en valeur */
	    List < ArticleValue > vListeResultat = new ArrayList < ArticleValue >();

	    for (ArticleEntite vArticleEntite : resultatEntite) {
	      ArticleValue vPv = PersistanceUtilities.toArticleValue(vArticleEntite);
	      vListeResultat.add(vPv);
	    }
	    
	    


	    /** retour de list de recherche et le nombre de resultat **/
	    ResultatRechecheArticleValue vResultatRechecheArticleValue = new ResultatRechecheArticleValue();

	    vResultatRechecheArticleValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

	    Collections.sort(vListeResultat);
	    vResultatRechecheArticleValue.setArticleValues(new TreeSet<>(vListeResultat));

	    return vResultatRechecheArticleValue;
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	private boolean estNonVide(String val) {
	    return val != null && !"".equals(val);
	}


	@Override
	public List<ArticleCacheValue> listeArticleCache() {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<ArticleEntite> query = vBuilder
				.createQuery(ArticleEntite.class);
		Root<ArticleEntite> vRootArticleCache = query
				.from(ArticleEntite.class);
		
		query.multiselect(vRootArticleCache.get("id"),
				vRootArticleCache.get("ref"),vRootArticleCache.get("designation"));
		
       //LOGGER.info("this article  persistance");
       
		List<ArticleEntite> results = this.entityManager.createQuery(
				query).getResultList();
          
		/** Conversion de la liste en valeur */
		List<ArticleCacheValue> vListeResultat = new ArrayList<ArticleCacheValue>();
		
		for (ArticleEntite vArticleEntite : results) {
			ArticleCacheValue vArticleCache = new ArticleCacheValue();
			vArticleCache.setId(vArticleEntite.getId());
			vArticleCache.setDesignation(vArticleEntite.getDesignation());
			vArticleCache.setRef(vArticleEntite.getRef());
			 //LOGGER.info("designation= : "+vArticleEntite.getDesignation());
			vListeResultat.add(vArticleCache);
		}
		return vListeResultat;

	}


	@Override
	public ArticleValue getArticleParId(Long id) {
		
		ArticleEntite entity = this.rechercherParId(id, ArticleEntite.class);
		ArticleValue dto = PersistanceUtilities.toArticleValue(entity);

		return dto;
	}


	@Override
	public ArticleValue rechercheProduitParReference(String reference) {
	
			  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
			    /** entity principale **/
			    CriteriaQuery < ArticleEntite > vCriteriaQuery = vBuilder.createQuery(ArticleEntite.class);
			    List < Predicate > vWhereClause = new ArrayList < Predicate >();

			    /** create liste resultat ***/

			    /************ entity jointure *****************/
			    Root < ArticleEntite > vRootProduit = vCriteriaQuery.from(ArticleEntite.class);

			    /***************** Predicate *************/
			    
			    
			  
			    
			    
			    
			   
			    
			    
			    
			    if (ref!=null) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(reference),
			    		 ref));
			    }
	  
			  
			    /** execute query and do something with result **/

			    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
			    List < ArticleEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

			    /** Conversion de la liste en valeur */
			    List < ArticleValue > vListeResultat = new ArrayList < ArticleValue >();

			    for (ArticleEntite vProduitEntite : resultatEntite) {
			    	ArticleValue vPv = PersistanceUtilities.toArticleValue(vProduitEntite);
			      vListeResultat.add(vPv);
			    }

			   

			    if(vListeResultat!=null && vListeResultat.size()>0)
			    	return vListeResultat.get(0);
			  
					return null;

		}
	
	


}
