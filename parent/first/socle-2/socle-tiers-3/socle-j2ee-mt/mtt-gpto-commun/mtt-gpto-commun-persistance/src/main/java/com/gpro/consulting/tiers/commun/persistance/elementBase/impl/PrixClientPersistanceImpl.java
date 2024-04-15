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

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.GrosseurEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MatiereArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.MetrageEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.PrixClientEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class PrixClientImpl.
 * @author El Araichi Oussama
 */
@Component
public class PrixClientPersistanceImpl extends AbstractPersistance implements IPrixClientPersistance {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
   	  private static Logger log =Logger.getLogger(PrixClientPersistanceImpl.class);
	 
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PrixClientPersistanceImpl.class);
			/**ajouter  PrixClient**/
	 
	  private String idpi="partieinteresse";
	  
	  private String ebproduitid = "ebproduitid";
	  
	  
	  private String famillePartieInteressee = "famillePartieInteressee";
	
	
	/*@Override
	public String creerPrixClient(PrixClientValue pPrixClientValue) {
		//PrixClientEntity vPrixClientEntity= (PrixClientEntity) this.creer(PersistanceUtilities.toEntity(pProduitValue, pSousFamille)(pPrixClientValue));
		 //PrixClientValue vPrixClientValueResult=PersistanceUtilities.toValue(vPrixClientEntity);
		 //return vPrixClientValueResult.getId().toString();	
		return null;
		 	
	}*/
	 @Override
		public String creerPrixClient(PrixClientValue pPrixClientValue) {
			// TODO Auto-generated method stub
			
			

						
			
				  	
			   PrixClientEntity vPrixClientEntite = (PrixClientEntity) this.creer(PersistanceUtilities.toEntity(pPrixClientValue));
			
			    if (LOGGER.isDebugEnabled()) {
			      //LOGGER.debug("Persister l'article d'ID=" + vPrixClientEntite.getId().toString() + " est en cours.");
			    }	     
			return vPrixClientEntite.getId().toString();
		}
		

	@Override
	public void supprimerPrixClient(Long pPrixClientID) {
		this.supprimerEntite(PrixClientEntity.class, pPrixClientID);	
		
	}
    
	
	/*@Override
	public String modifierPrixClient(
			PrixClientValue pPrixClientValue) {
		// PrixClientEntity vPrixClientEntity= (PrixClientEntity) this.modifier(PersistanceUtilities.toEntite(pPrixClientValue));
		// PrixClientValue vPrixClientValueResult=PersistanceUtilities.toValue(vPrixClientEntity);		
		// return vPrixClientValueResult.getId().toString();
		return null;
	}*/
	
	@Override
	public String modifierPrixClient(PrixClientValue pPrixClientValue) {
		
				
		    
		    PrixClientEntity vPrixClientEntite = (PrixClientEntity) this.modifier(PersistanceUtilities.toEntity(pPrixClientValue));
			

		    return vPrixClientEntite.getId().toString();

	}

	@Override
	public PrixClientValue recherchePrixClientById(Long pPrixClientId) {
		PrixClientEntity vPrixClientEntity= (PrixClientEntity) this.rechercherParId(pPrixClientId, PrixClientEntity.class);
		PrixClientValue vPrixClientValueResult=PersistanceUtilities.toValue(vPrixClientEntity);
		 return vPrixClientValueResult;
		
	}
	
	@Override
	public PrixClientValue rechcherchePrixClientMC(RecherchePrixClientValue pRecherchePrixClientValue) {
		
		 CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < PrixClientEntity > vCriteriaQuery = vBuilder.createQuery(PrixClientEntity.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resultat ***/

		    /************ entity jointure *****************/
		    Root < PrixClientEntity > vRootProduit = vCriteriaQuery.from(PrixClientEntity.class);

		    /***************** Predicate *************/
		    if (pRecherchePrixClientValue.getIdClient()!=null) {
		      vWhereClause.add(vBuilder.equal(vRootProduit.get(idpi),
		    		  pRecherchePrixClientValue.getIdClient()));
		    }

		    if (pRecherchePrixClientValue.getIdProduit()!=null) {
		      vWhereClause.add(vBuilder.equal(vRootProduit.get(ebproduitid),
		    		  pRecherchePrixClientValue.getIdProduit()));
		    }
           
		    
		    if (pRecherchePrixClientValue.getFamillePartieInteressee()!=null) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(famillePartieInteressee),
			    		  pRecherchePrixClientValue.getFamillePartieInteressee()));
			    }
		   		      
		  
		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
		    List < PrixClientEntity > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < PrixClientValue > vListeResultat = new ArrayList < PrixClientValue >();

		    for (PrixClientEntity vPrixClientEntite : resultatEntite) {
		    	PrixClientValue vPv = PersistanceUtilities.toValue(vPrixClientEntite);
		      vListeResultat.add(vPv);
		    }

		    
		    if(vListeResultat!=null && vListeResultat.size()>0)
		    	return vListeResultat.get(0);
		  
				return null;
	}
	
	@Override
	public List<PrixClientValue> rechchercheMultiCriterePrixClient(RecherchePrixClientValue pRecherchePrixClientValue) {
		
		 CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < PrixClientEntity > vCriteriaQuery = vBuilder.createQuery(PrixClientEntity.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resultat ***/

		    /************ entity jointure *****************/
		    Root < PrixClientEntity > vRootProduit = vCriteriaQuery.from(PrixClientEntity.class);

		    /***************** Predicate *************/
		    if (pRecherchePrixClientValue.getIdClient()!=null) {
		      vWhereClause.add(vBuilder.equal(vRootProduit.get(idpi),
		    		  pRecherchePrixClientValue.getIdClient()));
		    }

		    if (pRecherchePrixClientValue.getIdProduit()!=null) {
		      vWhereClause.add(vBuilder.equal(vRootProduit.get(ebproduitid),
		    		  pRecherchePrixClientValue.getIdProduit()));
		    }
           
		    
		    if (pRecherchePrixClientValue.getFamillePartieInteressee()!=null) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(famillePartieInteressee),
			    		  pRecherchePrixClientValue.getFamillePartieInteressee()));
			    }
		   		      
		  
		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
		    List < PrixClientEntity > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < PrixClientValue > vListeResultat = new ArrayList < PrixClientValue >();

		    for (PrixClientEntity vPrixClientEntite : resultatEntite) {
		    	PrixClientValue vPv = PersistanceUtilities.toValue(vPrixClientEntite);
		      vListeResultat.add(vPv);
		    }

		    
		    if(vListeResultat!=null && vListeResultat.size()>0)
		    	return vListeResultat;
		  
				return null;
	}
	
	

	@Override
	public List<PrixClientValue> listePrixClient() {
		java.util.List<PrixClientEntity> vPrixClientEntity = this.lister(PrixClientEntity.class);
		 if(!vPrixClientEntity.isEmpty()){
		 List <PrixClientValue> listePrixClient= PersistanceUtilities.tolistPrixClientValues(vPrixClientEntity);
		  //log.info("liste  du prix client non vide");
			return listePrixClient;
		 }else{
				//log.info("liste  du prix client est vide  ");
				return null;
		 }
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	

}
