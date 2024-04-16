package com.gpro.consulting.tiers.logistique.persistance.gs.impl;

import java.util.ArrayList;
import java.util.Calendar;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMouvementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BonMouvementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.EntiteStockEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MagasinEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MouvementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.utilities.PersistanceUtilitiesGs;


/**
 * The Class BonMouvementStockPersistanceImpl.
 */

@Component
public class MouvementPersistanceImpl extends AbstractPersistance implements IMouvementPersistance{

	private static final Logger logger = LoggerFactory.getLogger(MouvementPersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	  
	  	
  	private String historique="type";
	private String reference="referenceArticle" ;
	private String article="article";
	private String magasin="magasin";
	private String emplacement="emplacement";
	private String raison="raisonMouvementId";
	private String responsable="responsable";
	private String client="partieInteresseeId";
	private String fournisseur="partieInteresseeId";
	private String sousTraitant="partieInteresseeId";
	private String  entiteStock="entiteStock";
	private String bonMouvement="bonMouvement";
	private String  id="id";
	private String finConesReel ="finConesReel";
	private String finConeValue ="oui";
	private String date ="date";
	private String famille="familleArticle" ;
	private String sousFamille="sousFamilleArtEntite";
	private String refMise="numero" ;


	private static final String ATTRIBUTE_NAME_ID ="id";
	private String PREDICATE_DATEMOUVEMENT = "date";
	private String PREDICATE_OF_ID ="ofId"; 

		@Override
		public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(
				RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue) {
			
			CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < MouvementEntite > vCriteriaQuery = vBuilder.createQuery(MouvementEntite.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

            /**********entite de base **********/
		    Root < MouvementEntite > vRootMouvement = vCriteriaQuery.from(MouvementEntite.class);
		     /***********jointure ********/
		    
		    /*************jointure magasin entite ***********/
		    if (estNonVide(pRechercheMulticritereMouvementValue.getMagasin())) {
		    	   Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
				      Join<EntiteStockEntite, MagasinEntite> jointureEnStkMagasin = jointureMvtEnStk.join(magasin);
			      vWhereClause.add(vBuilder.equal(jointureEnStkMagasin.get(id), pRechercheMulticritereMouvementValue.getMagasin()));
			    }
			    
		    /***********jointure article entite********/
		    if (estNonVide(pRechercheMulticritereMouvementValue.getArticle())) {
		    	   Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
		    	   Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = jointureMvtEnStk.join(article);
		      vWhereClause.add(vBuilder.equal(jointureEnstkArt.get(id),
		    		  pRechercheMulticritereMouvementValue.getArticle()));
		    }
		    
		    if (estNonVide(pRechercheMulticritereMouvementValue.getRefArticle())) {
		    	   Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
		    	   Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = jointureMvtEnStk.join(article);
		      vWhereClause.add(vBuilder.equal(jointureEnstkArt.get(id),
		    		  pRechercheMulticritereMouvementValue.getRefArticle()));
		    }
		    
		    
		    //TODO : vérifier l'utilité
		    if (estNonVide(pRechercheMulticritereMouvementValue.getReference())) {
		    	   Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			      vWhereClause.add(vBuilder.equal(jointureMvtEnStk.get(reference),
			    		  pRechercheMulticritereMouvementValue.getReference()));
			    }
		    
		    /****************jointure partieInteresse entite*************/
		    if (estNonVide(pRechercheMulticritereMouvementValue.getClient())) {
		    	 Join<MouvementEntite, BonMouvementEntite> jointureMvtBonMvt = vRootMouvement.join(bonMouvement);
			      vWhereClause.add(vBuilder.equal(jointureMvtBonMvt.get(client),
			    		  pRechercheMulticritereMouvementValue.getClient()));
			    }
		    if (estNonVide(pRechercheMulticritereMouvementValue.getFournisseur())) {
		    	 Join<MouvementEntite, BonMouvementEntite> jointureMvtBonMvt = vRootMouvement.join(bonMouvement);
			      vWhereClause.add(vBuilder.equal(jointureMvtBonMvt.get(fournisseur),
			    		  pRechercheMulticritereMouvementValue.getFournisseur()));
			    }
		    
		    if (estNonVide(pRechercheMulticritereMouvementValue.getSousTraitant())) {
		    	 Join<MouvementEntite, BonMouvementEntite> jointureMvtBonMvt = vRootMouvement.join(bonMouvement);
			      vWhereClause.add(vBuilder.equal(jointureMvtBonMvt.get(sousTraitant),
			    		  pRechercheMulticritereMouvementValue.getSousTraitant()));
			    }
			    /****************************/
		    if (estNonVide(pRechercheMulticritereMouvementValue.getHistorique())) {
			      vWhereClause.add(vBuilder.equal(vRootMouvement.get(historique),
			    		  pRechercheMulticritereMouvementValue.getHistorique()));
			    }
		    
		    if (estNonVide(pRechercheMulticritereMouvementValue.getEmplacement())) {
			      vWhereClause.add(vBuilder.equal(vRootMouvement.get(emplacement),
			    		  pRechercheMulticritereMouvementValue.getEmplacement()));
			    }
			    
		    if (estNonVide(pRechercheMulticritereMouvementValue.getFamille())) {
		    	  Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
		    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = jointureMvtEnStk.join(article);
		    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
		    	   Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSouFmFamille = jointureArtSousF.join(famille);
			      vWhereClause.add(vBuilder.equal(jointureSouFmFamille.get(id),pRechercheMulticritereMouvementValue.getFamille()));
			    }
		    
		    if (estNonVide(pRechercheMulticritereMouvementValue.getSousFamille())) {
		    	  Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
		    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = jointureMvtEnStk.join(article);
		    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
			      vWhereClause.add(vBuilder.equal(jointureArtSousF.get(id),pRechercheMulticritereMouvementValue.getSousFamille()));
			    }
		    
			    
		    if (estNonVide(pRechercheMulticritereMouvementValue.getFinCone())) {
		    	 Expression<Long> nbrFinCone=vRootMouvement.get(finConesReel);
		    	if(pRechercheMulticritereMouvementValue.getFinCone()==finConeValue){
				      vWhereClause.add(vBuilder.gt(nbrFinCone,0L));
		    	}else{
				      vWhereClause.add(vBuilder.le(nbrFinCone,0L));
		    	}
		    	
			    }
			    
		    //type article
		     if (estNonVide(pRechercheMulticritereMouvementValue.getType())) {
		    	   Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
		    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = jointureMvtEnStk.join(article);
		    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
		    	   Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSouFmFamille = jointureArtSousF.join(famille);
		    	   Join<FamilleArticleEntity, TypeArticleEntity> jointureFmTypeArt = jointureSouFmFamille.join("typeArticle");
			      vWhereClause.add(vBuilder.equal(jointureFmTypeArt.get(id),Long .valueOf(pRechercheMulticritereMouvementValue.getType())));
			    }
		    
			    /*****jointure bonMouvement entite  *******/
		    /**************date bonMouvement******************/
		    if(pRechercheMulticritereMouvementValue.getDateDu()!=null){
			      Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
		    	  Expression<Calendar> dateMouvement =jointureMvtBmvt.get(date);
				  Calendar dateEntre = pRechercheMulticritereMouvementValue.getDateDu(); 
		    	 vWhereClause.add(vBuilder.greaterThanOrEqualTo(dateMouvement,dateEntre));
		    	
		    }
	
			if (pRechercheMulticritereMouvementValue.getDateA() != null) {
				Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement
						.join(bonMouvement);
				Expression<Calendar> dateMouvement = jointureMvtBmvt.get(date);
				Calendar dateSortie = pRechercheMulticritereMouvementValue
						.getDateA();
				vWhereClause.add(vBuilder.lessThanOrEqualTo(dateMouvement,
						dateSortie));
			}
	
			if (estNonVide(pRechercheMulticritereMouvementValue.getResponsable())) {
				Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
				vWhereClause.add(vBuilder.equal(jointureMvtBmvt.get(responsable),
						pRechercheMulticritereMouvementValue.getResponsable()));
			}
	
			if (estNonVide(pRechercheMulticritereMouvementValue.getRaison())) {
				Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
				vWhereClause.add(vBuilder.equal(jointureMvtBmvt.get(raison),
						pRechercheMulticritereMouvementValue.getRaison()));
			}
			
			//Hajer Amri 02/02/2017
			  /*************jointure MouvementEntite - bonMouvement entite (refMise) ************/
		    if(pRechercheMulticritereMouvementValue.getRefMise()!=null){
		    	
		    	System.out.println("----refMise---"+refMise);
			      Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
		    	 vWhereClause.add(vBuilder.equal(jointureMvtBmvt.get(refMise),
		    			 pRechercheMulticritereMouvementValue.getRefMise()));
		    }
		    
			/************** OF ******************/
	    if(pRechercheMulticritereMouvementValue.getOfId()!=null){
			      Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
		    	  Expression<Long> ofIdExpression =jointureMvtBmvt.get(PREDICATE_OF_ID);
				  Long ofId = pRechercheMulticritereMouvementValue.getOfId(); 
		    	 vWhereClause.add(vBuilder.equal(ofIdExpression,ofId));
		    	
		    }
	
		    
		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootMouvement).where(vWhereClause.toArray(new Predicate[] {}));
		    List < MouvementEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < MouvementStockValue > vListeResultat = new ArrayList < MouvementStockValue >();

		    for (MouvementEntite vMouvementEntite : resultatEntite) {
		    	MouvementStockValue vMe = PersistanceUtilitiesGs.toValueAffichage(vMouvementEntite);
		    	 vListeResultat.add(vMe);
		    }

		    /** retour de list de recherche et le nombre de resultat **/
		    ResultatRechecheMouvementValue vResultatRechecheMouvementValue = new ResultatRechecheMouvementValue();

		    vResultatRechecheMouvementValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

		    Collections.sort(vListeResultat);
		    vResultatRechecheMouvementValue.setMouvementStock(new TreeSet<>(vListeResultat));
		    //logger.info("nombre resultat =  "+vResultatRechecheMouvementValue.getNombreResultaRechercher());
		    return vResultatRechecheMouvementValue;
		  }
		
		/**
	 	 * Est non vide.
	 	 *
	 	 * @param val the val
	 	 * @return true, if successful
	 	 */
		  private boolean estNonVide(String val) {
		   
		    return val != null && !"".equals(val) && !"undefined".equals(val)  && !"null".equals(val);

		  }

	/******* liste mouvement stock ******/
	@Override
	public List<MouvementStockValue> listeMouvementStock() {

		List<MouvementEntite> vListMouvementStockEntite = this
				.lister(MouvementEntite.class);
		List<MouvementStockValue> vlistMouvementStockValue = new ArrayList<MouvementStockValue>();
		for (MouvementEntite vMouvementStockEntite : vListMouvementStockEntite) {
			vlistMouvementStockValue.add(PersistanceUtilitiesGs
					.toValue(vMouvementStockEntite));
		}
		
		Collections.sort(vlistMouvementStockValue);
		return vlistMouvementStockValue;
	}

		@Override
		public EntityManager getEntityManager() {
			return entityManager;
		}

		@Override
		public ResultatRechecheMouvementValue rechercherEtatMouvement(RequestRechecheMouvementValue request) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		    CriteriaQuery <MouvementEntite > criteriaQuery = criteriaBuilder.createQuery(MouvementEntite.class);
		    List <Predicate> whereClause = new ArrayList <Predicate>();

		    Root<MouvementEntite> root = criteriaQuery.from(MouvementEntite.class);
		    
		    if (request.getArticleId() != null) {
		    	
		    	Join<MouvementEntite, EntiteStockEntite> jointureMouvementEntiteStock = root.join(entiteStock);
		    	Join<EntiteStockEntite, ArticleEntite> jointureEntiteStockArticle = jointureMouvementEntiteStock.join(article);
		    	
		    	whereClause.add(criteriaBuilder.equal(jointureEntiteStockArticle.get(ATTRIBUTE_NAME_ID), request.getArticleId()));
		    }
		    
			// Set request.dateMin on whereClause if not null
		    if (request.getDateMin() != null) {
		    	Join<MouvementEntite, BonMouvementEntite> jointureMouvementBonMouvement = root.join(bonMouvement);
		    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointureMouvementBonMouvement.<Calendar>get(PREDICATE_DATEMOUVEMENT), request.getDateMin()));
		    }
			    
			// Set request.dateMax on whereClause if not null
		    if (request.getDateMax() != null) {
		    	Join<MouvementEntite, BonMouvementEntite> jointureMouvementBonMouvement = root.join(bonMouvement);
		    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointureMouvementBonMouvement.<Calendar>get(PREDICATE_DATEMOUVEMENT), request.getDateMax()));
		    }
		    
		    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <MouvementEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    List <MouvementStockValue> mouvementStockList = new ArrayList <MouvementStockValue>();

		    for (MouvementEntite entity : resultatEntite) {
		    	MouvementStockValue dto = PersistanceUtilitiesGs.toValueAffichage(entity);
		    	mouvementStockList.add(dto);
		    }

		    ResultatRechecheMouvementValue result = new ResultatRechecheMouvementValue();

		    result.setNombreResultaRechercher(Long.valueOf(mouvementStockList.size()));

		    result.setMouvementStock(new TreeSet<>(mouvementStockList));
		    return result;
		}

		
	

}
