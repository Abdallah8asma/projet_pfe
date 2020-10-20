package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereBoutiqueValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IBoutiquePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.BoutiqueEntite;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SocieteEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class BoutiquePersistanceImpl extends AbstractPersistance 
									implements IBoutiquePersistance{

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Logger*/
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BoutiquePersistanceImpl.class);

	
	/************************** Creation Boutique *****************************/
	@Override
	public String creerBoutique(BoutiqueValue pBoutiqueValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Creation du Boutique " + pBoutiqueValue.getDesignation() + " est en cours.");
		}
		BoutiqueEntite pBoutiqueEntite=(BoutiqueEntite) this.creer(PersistanceUtilities.toBoutiqueEntity(pBoutiqueValue));
		BoutiqueValue pBoutiqueValueResult=PersistanceUtilities.toBoutiqueValue(pBoutiqueEntite);
	return pBoutiqueValueResult.getId().toString();
	}
	
	/************************** Suppression Boutique *****************************/
	@Override
	public void supprimerBoutique(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Suppression du Boutique de l'ID=" + pId.longValue() + " est en cours.");
		}
		this.supprimerEntite(BoutiqueEntite.class, pId.longValue());
	}
	
	/************************ Modification Boutique ******************************/
	@Override
	public String modifierBoutique(BoutiqueValue pBoutiqueValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Modification du Boutique de l'ID=" + pBoutiqueValue.getId().toString() + " est en cours.");
		}
		BoutiqueEntite pBoutiqueEntite=(BoutiqueEntite) this.modifier(PersistanceUtilities.toBoutiqueEntity(pBoutiqueValue));
		BoutiqueValue pBoutiqueValueResult=PersistanceUtilities.toBoutiqueValue(pBoutiqueEntite);
	return pBoutiqueValueResult.getId().toString();
	}
	
	/************************ Recherche Boutique ******************************/
	@Override
	public BoutiqueValue rechercheBoutiqueParId(BoutiqueValue pBoutiqueValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Recherche du Boutique de l'ID=" + pBoutiqueValue.getId().toString() + " est en cours.");
		}
		BoutiqueEntite pBoutiqueEntite=(BoutiqueEntite) this.rechercherParId(pBoutiqueValue.getId().longValue(), BoutiqueEntite.class);
		BoutiqueValue pBoutiqueValueResult=PersistanceUtilities.toBoutiqueValue(pBoutiqueEntite);
	return pBoutiqueValueResult;
	
	}
	
	/************************ Liste Boutique ******************************/
	
	@Override
	public List<BoutiqueValue> listeBoutique() {
		List < BoutiqueEntite > vListeBoutiqueEntite = this.lister(BoutiqueEntite.class);
		List < BoutiqueValue > vListBoutiqueValues = new ArrayList < BoutiqueValue >();
			for (BoutiqueEntite vBoutiqueEntite : vListeBoutiqueEntite) {
			vListBoutiqueValues.add(PersistanceUtilities.toBoutiqueValue(vBoutiqueEntite));
			}
	return vListBoutiqueValues;
	}
	
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
	return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
	}

	@Override
	public List<BoutiqueValue> rechercheMulticritere(RechercheMulticritereBoutiqueValue requestBoutique) {
		 CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < BoutiqueEntite > vCriteriaQuery = vBuilder.createQuery(BoutiqueEntite.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < BoutiqueEntite > vRootParitieInteresse = vCriteriaQuery.from(BoutiqueEntite.class);

		 
		    
		    if (estNonVide(requestBoutique.getSocieteId())) {
      
		     	Join<BoutiqueEntite,SocieteEntite> jointure_boutique_societe = vRootParitieInteresse.join("societe");
				
		     	vWhereClause.add(vBuilder.equal(jointure_boutique_societe.get("id"), requestBoutique.getSocieteId()));
      
		    }
		    
		    
		    
		    
		    
		    if (estNonVide(requestBoutique.getReference())) {
		      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("reference"),
		        requestBoutique.getReference()));
		    }

		    if (estNonVide(requestBoutique.getRaisonSociale())) {
		      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("raisonSociale"),
		        requestBoutique.getRaisonSociale()));
		    }

		    if (estNonVide(requestBoutique.getActivite())) {
		      vWhereClause
		        .add(vBuilder.equal(vRootParitieInteresse.get("activite"), requestBoutique.getActivite()));
		    }

		    if (estNonVide(requestBoutique.getActif())) {
		      if (requestBoutique.getActif().equals(IConstante.OUI)) {
		        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), true));
		      } else if(requestBoutique.getActif().equals(IConstante.NON)) {
		        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), false));
		      }

		    }
		    
		    
		

		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootParitieInteresse).where(vWhereClause.toArray(new Predicate[] {}));
		    vCriteriaQuery.orderBy(vBuilder.desc(vRootParitieInteresse.get("id")));
		    
		    List < BoutiqueEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < BoutiqueValue > vListeResultat = new ArrayList < BoutiqueValue >();

		    for (BoutiqueEntite vPartieInteresseeEntite : resultatEntite) {
		      BoutiqueValue vPv = PersistanceUtilities.toBoutiqueValue(vPartieInteresseeEntite);
		      vListeResultat.add(vPv);
		    }


		    return vListeResultat;
	}

	 @SuppressWarnings("unused")
	  private boolean estNonVide(String val) {
	    return val != null && !"".equals(val);

	  }
	 
	 @SuppressWarnings("unused")
	  private boolean estNonVide(Long val) {
	    return val != null ;

	  }

	@Override
	public BoutiqueValue rechercheBoutiqueParId(Long id) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Recherche du Boutique de l'ID=" + pBoutiqueValue.getId().toString() + " est en cours.");
		}
		BoutiqueEntite pBoutiqueEntite=(BoutiqueEntite) this.rechercherParId(id.longValue(), BoutiqueEntite.class);
		
		if(pBoutiqueEntite == null) return null;
		BoutiqueValue pBoutiqueValueResult=PersistanceUtilities.toBoutiqueValue(pBoutiqueEntite);
	return pBoutiqueValueResult;
	}

}
