package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereRemiseValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RemiseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IRemisePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.RemiseEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

@Component
public class RemisePersistanceImpl extends AbstractPersistance 
									implements IRemisePersistance{

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Logger*/
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RemisePersistanceImpl.class);
	  
	  
	  
	  
	  private String PREDICAT_PRODUIT_ID = "produitId";
	  private String PREDICAT_BOUTIQUE_ID = "boutiqueId";
	  private String PREDICAT_DATE_DEBUT = "dateDebut";
	  private String PREDICAT_DATE_FIN = "dateFin";

	
	/************************** Creation Remise *****************************/
	@Override
	public String creerRemise(RemiseValue pRemiseValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Creation du Remise " + pRemiseValue.getDesignation() + " est en cours.");
		}
		RemiseEntite pRemiseEntite=(RemiseEntite) this.creer(PersistanceUtilities.toRemiseEntity(pRemiseValue));
		RemiseValue pRemiseValueResult=PersistanceUtilities.toRemiseValue(pRemiseEntite);
	return pRemiseValueResult.getId().toString();
	}
	
	/************************** Suppression Remise *****************************/
	@Override
	public void supprimerRemise(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Suppression du Remise de l'ID=" + pId.longValue() + " est en cours.");
		}
		this.supprimerEntite(RemiseEntite.class, pId.longValue());
	}
	
	/************************ Modification Remise ******************************/
	@Override
	public String modifierRemise(RemiseValue pRemiseValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Modification du Remise de l'ID=" + pRemiseValue.getId().toString() + " est en cours.");
		}
		RemiseEntite pRemiseEntite=(RemiseEntite) this.modifier(PersistanceUtilities.toRemiseEntity(pRemiseValue));
		RemiseValue pRemiseValueResult=PersistanceUtilities.toRemiseValue(pRemiseEntite);
	return pRemiseValueResult.getId().toString();
	}
	
	/************************ Recherche Remise ******************************/
	@Override
	public RemiseValue rechercheRemiseParId(RemiseValue pRemiseValue) {
		if (LOGGER.isDebugEnabled()) {
			//LOGGER.debug(" ** Recherche du Remise de l'ID=" + pRemiseValue.getId().toString() + " est en cours.");
		}
		RemiseEntite pRemiseEntite=(RemiseEntite) this.rechercherParId(pRemiseValue.getId().longValue(), RemiseEntite.class);
		RemiseValue pRemiseValueResult=PersistanceUtilities.toRemiseValue(pRemiseEntite);
	return pRemiseValueResult;
	
	}
	
	/************************ Liste Remise ******************************/
	
	@Override
	public List<RemiseValue> listeRemise() {
		List < RemiseEntite > vListeRemiseEntite = this.lister(RemiseEntite.class);
		List < RemiseValue > vListRemiseValues = new ArrayList < RemiseValue >();
			for (RemiseEntite vRemiseEntite : vListeRemiseEntite) {
			vListRemiseValues.add(PersistanceUtilities.toRemiseValue(vRemiseEntite));
			}
	return vListRemiseValues;
	}
	
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
	return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
	}

	@Override
	public List<RemiseValue> rechercheMulticritere(RechercheMulticritereRemiseValue requestRemise) {
		 CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < RemiseEntite > vCriteriaQuery = vBuilder.createQuery(RemiseEntite.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < RemiseEntite > vRootRemiseEntite = vCriteriaQuery.from(RemiseEntite.class);

		 
		    
		    
		    if (estNonVide(requestRemise.getProduitId())) {
		      vWhereClause.add(vBuilder.equal(vRootRemiseEntite.get(PREDICAT_PRODUIT_ID),
		        requestRemise.getProduitId()));
		    }

		    if (estNonVide(requestRemise.getBoutiqueId())) {
			      vWhereClause.add(vBuilder.equal(vRootRemiseEntite.get(PREDICAT_BOUTIQUE_ID),
			        requestRemise.getBoutiqueId()));
			    }
		    
		    
		    
		    //date debut
			
			if (requestRemise.getDateDebutDe() != null) {
				vWhereClause.add(vBuilder.greaterThanOrEqualTo(vRootRemiseEntite.<Calendar> get(PREDICAT_DATE_DEBUT),
						requestRemise.getDateDebutDe()));
			}
			
			
			if (requestRemise.getDateDebutA() != null) {
				vWhereClause.add(vBuilder.lessThanOrEqualTo(vRootRemiseEntite.<Calendar> get(PREDICAT_DATE_DEBUT),
						requestRemise.getDateDebutA()));
			}

			
			//date fin
			
			if (requestRemise.getDateFinDe() != null) {
				vWhereClause.add(vBuilder.greaterThanOrEqualTo(vRootRemiseEntite.<Calendar> get(PREDICAT_DATE_FIN),
						requestRemise.getDateFinDe()));
			}
			
			
			if (requestRemise.getDateFinA() != null) {
				vWhereClause.add(vBuilder.lessThanOrEqualTo(vRootRemiseEntite.<Calendar> get(PREDICAT_DATE_FIN),
						requestRemise.getDateFinA()));
			}


		    
		

		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootRemiseEntite).where(vWhereClause.toArray(new Predicate[] {}));
		    vCriteriaQuery.orderBy(vBuilder.desc(vRootRemiseEntite.get("id")));
		    
		    List < RemiseEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < RemiseValue > vListeResultat = new ArrayList < RemiseValue >();

		    for (RemiseEntite vPartieInteresseeEntite : resultatEntite) {
		      RemiseValue vPv = PersistanceUtilities.toRemiseValue(vPartieInteresseeEntite);
		      vListeResultat.add(vPv);
		    }


		    return vListeResultat;
	}

	 @SuppressWarnings("unused")
	  private boolean estNonVide(String val) {
	    return val != null && !"".equals(val) && !"undefined".equals(val);

	  }
	 
	 @SuppressWarnings("unused")
	  private boolean estNonVide(Long val) {
	    return val != null ;

	  }

}
