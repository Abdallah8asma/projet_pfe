package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class PartieInteresseePersistanceImpl extends AbstractPersistance implements IPartieInteresseePersistance {

  /** EntityManager. */
  @PersistenceContext
  private EntityManager entityManager;

  private static final Logger LOGGER = LoggerFactory.getLogger(PartieInteresseePersistanceImpl.class);

  @Autowired
  private PersistanceUtilities vPersistanceUtilities;

  private String actifValue = "oui";
  
  private String boutiqueId="boutiqueId";
  private String deviseId="deviseId";
  
  private String dateIntroduction="dateIntroduction";

  /**
   * methode de creation Partie Interessée
   **/
  @Override
  public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue) {

    PartieInteresseEntite vPartieInteresseEntite = (PartieInteresseEntite) this.creer(vPersistanceUtilities
      .toPartieInteresseEntity(pPartieInteresseValue));
    if (LOGGER.isDebugEnabled()) {
      //LOGGER.debug("Persister la Partie Interessee d'ID=" + vPartieInteresseEntite.getRaisonSociale().toString()  + " est en cours.");
      
    }
    return vPartieInteresseEntite.getId().toString();
  }

  /**
   * supprimer partie interessee
   **/
  @Override
  public void supprimerPartieInteressee(Long pId) {
    if (LOGGER.isDebugEnabled()) {
      //LOGGER.debug("Suppression de la Partie Interessee d'ID=" + pId.longValue() + " est en cours.");
    }
    this.supprimerEntite(PartieInteresseEntite.class, pId.longValue());
  }

  /**
   * modifier partie interessee
   * **/
  @Override
  public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
    if (LOGGER.isDebugEnabled()) {
      //LOGGER.debug("Modification de la Partie Interessee d'ID=" + pPartieInteresseValue.getId().toString() + " est en cours.");
    }
    PartieInteresseEntite vPartieInteresseEntite = (PartieInteresseEntite) this.modifier(vPersistanceUtilities
      .toPartieInteresseEntity(pPartieInteresseValue));

    return vPartieInteresseEntite.getId().toString();
  }

  /**
   * recherche partie interessee Par Id
   * */

  @Override
  public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue) {
    if (LOGGER.isDebugEnabled()) {
      //LOGGER.debug("Recherche de la Partie Interessee d'ID=" + pPartieInteresseValue.getId().toString() + " est en cours.");
    }
    PartieInteresseEntite pPartieInteresseEntite = this.rechercherParId(pPartieInteresseValue.getId().longValue(),
      PartieInteresseEntite.class);

    PartieInteresseValue pPartieInteresseValueResult = vPersistanceUtilities.toPartieInteresseValue(pPartieInteresseEntite);
    return pPartieInteresseValueResult;
  }

  /**
   * liste partie interessee
   * */

  @Override
  public List < PartieInteresseValue > listePartieInteressee() {
    List < PartieInteresseEntite > vListePartieInterresseeEntite = this.lister(PartieInteresseEntite.class);
    List < PartieInteresseValue > vListPartieInteresseValues = new ArrayList < PartieInteresseValue >();
    
    for (PartieInteresseEntite vPartieInteresseEntite : vListePartieInterresseeEntite) {
      vListPartieInteresseValues.add(vPersistanceUtilities.toPartieInteresseValue(vPartieInteresseEntite));
      
    }
    
    return vListPartieInteresseValues;
  }

  /**
   * recherche Partie Intersse multi Critere
   * 
   * {@inheritDoc}
   */
  @Override
  public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere) {

    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
    /** entity principale **/
    CriteriaQuery < PartieInteresseEntite > vCriteriaQuery = vBuilder.createQuery(PartieInteresseEntite.class);
    List < Predicate > vWhereClause = new ArrayList < Predicate >();

    /** create liste resyltat ***/

    /************ entity jointure *****************/
    Root < PartieInteresseEntite > vRootParitieInteresse = vCriteriaQuery.from(PartieInteresseEntite.class);

    /***************** Predicate *************/
    
    
    
    if (pRecherchePartieInteresseMulitCritere.getBoutiqueId() != null ) {
	      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get(boutiqueId),
	    		  pRecherchePartieInteresseMulitCritere.getBoutiqueId()));
	    }
    
    
    if (pRecherchePartieInteresseMulitCritere.getGroupeClientId() != null) {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("groupeClientId"),
          pRecherchePartieInteresseMulitCritere.getGroupeClientId()));
      }

    
    
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvReference())) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("reference"),
        pRecherchePartieInteresseMulitCritere.getvReference()));
    }

    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvRaisonSociale())) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("raisonSociale"),
        pRecherchePartieInteresseMulitCritere.getvRaisonSociale()));
    }

    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvFamillePartieInteressee())) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("famillePartieInteressee"),
        pRecherchePartieInteresseMulitCritere.getvFamillePartieInteressee()));
    }

    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvCategoriePartieInteressee())) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("categoriePartieInteressee"),
        pRecherchePartieInteresseMulitCritere.getvCategoriePartieInteressee()));
    }
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvTypePartieInteressee())) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("typePartieInteressee"),
        pRecherchePartieInteresseMulitCritere.getvTypePartieInteressee()));
    }
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvActivite())) {
      vWhereClause
        .add(vBuilder.equal(vRootParitieInteresse.get("activite"), pRecherchePartieInteresseMulitCritere.getvActivite()));
    }

    if (estNonVide(pRecherchePartieInteresseMulitCritere.getActif())) {
      if (pRecherchePartieInteresseMulitCritere.getActif().equals(IConstante.OUI)) {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), true));
      } else if(pRecherchePartieInteresseMulitCritere.getActif().equals(IConstante.NON)) {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), false));
      }

    }
    
    
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getPassager())) {
        if (pRecherchePartieInteresseMulitCritere.getPassager().equals(IConstante.OUI)) {
          vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("passager"), true));
        } else if(pRecherchePartieInteresseMulitCritere.getPassager().equals(IConstante.NON)) {
          vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("passager"), false));
        }

      }
    
    
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getTelephoneMobile())) {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("telephoneMobile"),
          pRecherchePartieInteresseMulitCritere.getTelephoneMobile()));
      }

    
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getPayementTerme())) {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("payementTerme"),
          pRecherchePartieInteresseMulitCritere.getPayementTerme()));
      }
    
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getNature())) {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("nature"),
          pRecherchePartieInteresseMulitCritere.getNature()));
      }
    
    
    
    if (pRecherchePartieInteresseMulitCritere.getDeviseId() != null ) {
	      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get(deviseId),
	    		  pRecherchePartieInteresseMulitCritere.getDeviseId()));
	    }
    
    
    
	if (pRecherchePartieInteresseMulitCritere.getDateIntroductionDe() != null) {
		vWhereClause.add(vBuilder.greaterThanOrEqualTo(vRootParitieInteresse.<Calendar> get(dateIntroduction),
				pRecherchePartieInteresseMulitCritere.getDateIntroductionDe()));
	}

	if (pRecherchePartieInteresseMulitCritere.getDateIntroductionA() != null) {
		vWhereClause.add(vBuilder.lessThanOrEqualTo(vRootParitieInteresse.<Calendar> get(dateIntroduction),
				pRecherchePartieInteresseMulitCritere.getDateIntroductionA()));
	}
    
    /** execute query and do something with result **/

    vCriteriaQuery.select(vRootParitieInteresse).where(vWhereClause.toArray(new Predicate[] {}));
    vCriteriaQuery.orderBy(vBuilder.desc(vRootParitieInteresse.get("id")));
    
    List < PartieInteresseEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

    /** Conversion de la liste en valeur */
    List < PartieInteresseValue > vListeResultat = new ArrayList < PartieInteresseValue >();

    for (PartieInteresseEntite vPartieInteresseeEntite : resultatEntite) {
      PartieInteresseValue vPv = vPersistanceUtilities.toPartieInteresseValue(vPartieInteresseeEntite);
      vListeResultat.add(vPv);
    }

    /** retour de list de recherche et le nombre de resultat **/
    ResultatRechechePartieInteresseeValue vResultatRechechePartieInteresseeValue = new ResultatRechechePartieInteresseeValue();

    vResultatRechechePartieInteresseeValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

    vResultatRechechePartieInteresseeValue.setPartieInteresseValues(vListeResultat);

    return vResultatRechechePartieInteresseeValue;
  }

  @SuppressWarnings("unused")
  private boolean estNonVide(String val) {
    return val != null && !"".equals(val);

  }

	/******** liste PartieIntersseCache *********/
	@Override
	public List<PartieInteresseCacheValue> listePartieInteresseeCache() {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<PartieInteresseEntite> query = vBuilder
				.createQuery(PartieInteresseEntite.class);
		Root<PartieInteresseEntite> vRootParitieInteresseCache = query
				.from(PartieInteresseEntite.class);
		
		query.multiselect(vRootParitieInteresseCache.get("id"),
				vRootParitieInteresseCache.get("abreviation"),vRootParitieInteresseCache.get("famillePartieInteressee"));
		
       //LOGGER.info("this pi persistance");
       
		List<PartieInteresseEntite> results = this.entityManager.createQuery(
				query).getResultList();
		 //LOGGER.info("size = "+results.size());

		/** Conversion de la liste en valeur */
		List<PartieInteresseCacheValue> vListeResultat = new ArrayList<PartieInteresseCacheValue>();
		
		for (PartieInteresseEntite vPartieInteresseeEntite : results) {
			PartieInteresseCacheValue vPiCache = new PartieInteresseCacheValue();
			vPiCache.setId(vPartieInteresseeEntite.getId());
			vPiCache.setAbreviation(vPartieInteresseeEntite.getAbreviation());
			vPiCache.setFamillePartieInteressee(vPartieInteresseeEntite
					.getFamillePartieInteressee());
			vListeResultat.add(vPiCache);
		}
		return vListeResultat;
	}
  
  /*
   * get entity manager
   */
  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }

  /**
   * Accesseur en écriture de l'attribut <code>entityManager</code>.
   *
   * @param entityManager
   *          L'attribut entityManager à modifier.
   */
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vPersistanceUtilities</code>.
   * 
   * @return PersistanceUtilities L'attribut vPersistanceUtilities à lire.
   */
  public PersistanceUtilities getvPersistanceUtilities() {
    return vPersistanceUtilities;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vPersistanceUtilities</code>.
   *
   * @param vPersistanceUtilities
   *          L'attribut vPersistanceUtilities à modifier.
   */
  public void setvPersistanceUtilities(PersistanceUtilities vPersistanceUtilities) {
    this.vPersistanceUtilities = vPersistanceUtilities;
  }

  /**
   * Accesseur en lecture de l'attribut <code>actifValue</code>.
   * 
   * @return String L'attribut actifValue à lire.
   */
  public String getActifValue() {
    return actifValue;
  }

  /**
   * Accesseur en écriture de l'attribut <code>actifValue</code>.
   *
   * @param actifValue
   *          L'attribut actifValue à modifier.
   */
  public void setActifValue(String actifValue) {
    this.actifValue = actifValue;
  }


  @Override
  public PartieInteresseValue getPartieInteresseById(Long id) {

	    PartieInteresseEntite entity = this.rechercherParId(id, PartieInteresseEntite.class);

	    PartieInteresseValue dto = vPersistanceUtilities.toPartieInteresseValue(entity);
	    
	    return dto;
  }

	@Override
	public PartieInteresseValue getById(Long id) {
		
		PartieInteresseEntite entity = this.rechercherParId(id, PartieInteresseEntite.class);

	    return vPersistanceUtilities.toPartieInteresseValue(entity);
	}

	@Override
	public String rechercheAbreviationPartieInteresseeParId(Long long1) {
		 if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Recherche de la Partie Interessee d'ID=" + pPartieInteresseValue.getId().toString() + " est en cours.");
		    }
		    PartieInteresseEntite pPartieInteresseEntite = this.rechercherParId(long1.longValue(),
		      PartieInteresseEntite.class);

		    return pPartieInteresseEntite.getAbreviation();
	}

	@Override
	public Long nbPartieIntByFamille(Long famille,Long boutiqueId) {
		
	
		
			//TODO
				String where="";
				
				if (estNonVide(boutiqueId)) {
					where=" and par.boutiqueId="+boutiqueId;
				}
				
				
				
		 Query vQuery = this.entityManager.createQuery(
			      "select count(par.id), count(par.famillePartieInteressee)"
			      +" from PartieInteresseEntite par "
			      + "where par.famillePartieInteressee= "+famille
			      + where
				 );
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			    return (Long)vResult.get(0)[0];
	}

			    private boolean estNonVide(Long val) {
					return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
				}

				@Override
				public Long getCountRechercherPartieInteresseMultiCritere(
						RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere) {


				    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
				    /** entity principale **/
				    CriteriaQuery < Long > vCriteriaQuery = vBuilder.createQuery(Long.class);
				    List < Predicate > vWhereClause = new ArrayList < Predicate >();

				    /** create liste resyltat ***/

				    /************ entity jointure *****************/
				    Root < PartieInteresseEntite > vRootParitieInteresse = vCriteriaQuery.from(PartieInteresseEntite.class);

				    /***************** Predicate *************/
				    
				    
				    
				    if (pRecherchePartieInteresseMulitCritere.getBoutiqueId() != null ) {
					      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get(boutiqueId),
					    		  pRecherchePartieInteresseMulitCritere.getBoutiqueId()));
					    }
				    
				    
				    if (pRecherchePartieInteresseMulitCritere.getGroupeClientId() != null) {
				        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("groupeClientId"),
				          pRecherchePartieInteresseMulitCritere.getGroupeClientId()));
				      }

				    
				    
				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvReference())) {
				      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("reference"),
				        pRecherchePartieInteresseMulitCritere.getvReference()));
				    }

				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvRaisonSociale())) {
				      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("raisonSociale"),
				        pRecherchePartieInteresseMulitCritere.getvRaisonSociale()));
				    }

				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvFamillePartieInteressee())) {
				      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("famillePartieInteressee"),
				        pRecherchePartieInteresseMulitCritere.getvFamillePartieInteressee()));
				    }

				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvCategoriePartieInteressee())) {
				      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("categoriePartieInteressee"),
				        pRecherchePartieInteresseMulitCritere.getvCategoriePartieInteressee()));
				    }
				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvTypePartieInteressee())) {
				      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("typePartieInteressee"),
				        pRecherchePartieInteresseMulitCritere.getvTypePartieInteressee()));
				    }
				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvActivite())) {
				      vWhereClause
				        .add(vBuilder.equal(vRootParitieInteresse.get("activite"), pRecherchePartieInteresseMulitCritere.getvActivite()));
				    }

				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getActif())) {
				      if (pRecherchePartieInteresseMulitCritere.getActif().equals(IConstante.OUI)) {
				        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), true));
				      } else if(pRecherchePartieInteresseMulitCritere.getActif().equals(IConstante.NON)) {
				        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), false));
				      }

				    }
				    
				    
				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getPassager())) {
				        if (pRecherchePartieInteresseMulitCritere.getPassager().equals(IConstante.OUI)) {
				          vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("passager"), true));
				        } else if(pRecherchePartieInteresseMulitCritere.getPassager().equals(IConstante.NON)) {
				          vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("passager"), false));
				        }

				      }
				    
				    
				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getTelephoneMobile())) {
				        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("telephoneMobile"),
				          pRecherchePartieInteresseMulitCritere.getTelephoneMobile()));
				      }

				    
				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getPayementTerme())) {
				        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("payementTerme"),
				          pRecherchePartieInteresseMulitCritere.getPayementTerme()));
				      }
				    
				    if (estNonVide(pRecherchePartieInteresseMulitCritere.getNature())) {
				        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("nature"),
				          pRecherchePartieInteresseMulitCritere.getNature()));
				      }
				    
				    
				    
				    if (pRecherchePartieInteresseMulitCritere.getDeviseId() != null ) {
					      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get(deviseId),
					    		  pRecherchePartieInteresseMulitCritere.getDeviseId()));
					    }
				    
				    
				    
					if (pRecherchePartieInteresseMulitCritere.getDateIntroductionDe() != null) {
						vWhereClause.add(vBuilder.greaterThanOrEqualTo(vRootParitieInteresse.<Calendar> get(dateIntroduction),
								pRecherchePartieInteresseMulitCritere.getDateIntroductionDe()));
					}

					if (pRecherchePartieInteresseMulitCritere.getDateIntroductionA() != null) {
						vWhereClause.add(vBuilder.lessThanOrEqualTo(vRootParitieInteresse.<Calendar> get(dateIntroduction),
								pRecherchePartieInteresseMulitCritere.getDateIntroductionA()));
					}
				    
				    /** execute query and do something with result **/

			
				    
				    vCriteriaQuery.select(vBuilder.count(vRootParitieInteresse))
					.where(vWhereClause.toArray(new Predicate[] {}));
			Long sommeMont = this.entityManager.createQuery(vCriteriaQuery).getSingleResult();

			if (sommeMont != null) {
				return sommeMont;
			}
			return 0l;
			
			
				  }

				

}
