package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.impl;

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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ElementRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.IBonReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.BonReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.utilities.BonReceptionPersistanceUtilities;

/**
 * Implémentation des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */

@Component
public class BonReceptionPersistanceImpl extends AbstractPersistance implements IBonReceptionPersistance {
	
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_PRODUIT = "produit";

  /** EntityManager. */
  @PersistenceContext
  private EntityManager entityManager;

  /** Utilitaire de persistance */
  @Autowired
  private BonReceptionPersistanceUtilities vPersistanceUtilities;

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(BonReceptionPersistanceImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public String creerBonReception(BonReceptionValue pBonReceptionValue) {
    BonReceptionEntity vBonReceptionEntity = (BonReceptionEntity) this.creer(vPersistanceUtilities.toEntity(pBonReceptionValue));

    return vBonReceptionEntity.getId().toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void supprimerBonReception(Long pId) {
    this.supprimerEntite(BonReceptionEntity.class, pId.longValue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String modifierBonReception(BonReceptionValue pBonReception) {
    BonReceptionEntity vBonReceptionEntity = (BonReceptionEntity) this.modifier(vPersistanceUtilities.toEntity(pBonReception));

    return vBonReceptionEntity.getId().toString();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BonReceptionValue rechercheBonReceptionParId(BonReceptionValue pBonReception) {

    BonReceptionEntity vBonReceptionEntity = this.rechercherParId(pBonReception.getId().longValue(), BonReceptionEntity.class);

    BonReceptionValue vBonReceptionValueResultat = vPersistanceUtilities.toValue(vBonReceptionEntity);
    return vBonReceptionValueResultat;
  }
  
 
  /**
   * {@inheritDoc}
   */
  @Override
  public ResultatRechecheBonReceptionValue rechercherBonReceptionMultiCritere(
    RechercheMulticritereBonReceptionValue pRechercheBonReceptionMulitCritere) {

    /** Création du Criteria */
    CriteriaBuilder vCriteriaBuilder = this.entityManager.getCriteriaBuilder();

    /** entity principale : Bon Reception **/
    CriteriaQuery < BonReceptionEntity > vCriteriaQuery = vCriteriaBuilder.createQuery(BonReceptionEntity.class);

    Root < BonReceptionEntity > vBonReceptionRoot = vCriteriaQuery.from(BonReceptionEntity.class);

    /** Liste des Prédicats : Date introduction et Client */
    List < Predicate > vWhereClause = new ArrayList < Predicate >();

    // Critère Client
    if (pRechercheBonReceptionMulitCritere.getClient() != null) {
      vWhereClause.add(vCriteriaBuilder.equal(vBonReceptionRoot.get("partieInteressee"),
        pRechercheBonReceptionMulitCritere.getClient()));
    }

    // Critere Produit
	if (pRechercheBonReceptionMulitCritere.getProduitId() != null) {
		vWhereClause.add(vCriteriaBuilder.equal(vBonReceptionRoot.get(PREDICATE_PRODUIT),
				pRechercheBonReceptionMulitCritere.getProduitId()));
	}
    // Critère Date introduction
    /** execute query and do something with result **/
    if (pRechercheBonReceptionMulitCritere.getDateIntroduction() != null) {
      Expression < Calendar > dateIntroduction = vBonReceptionRoot.get("dateIntroduction");
      vWhereClause.add(vCriteriaBuilder.equal(dateIntroduction, pRechercheBonReceptionMulitCritere.getDateIntroduction()));
    }
    /** Lancer la requete */
    vCriteriaQuery.select(vBonReceptionRoot).where(vWhereClause.toArray(new Predicate[] {}));

    /** Récupération du résultat de la base */
    List < BonReceptionEntity > listeResultatRechercheBonReception = this.entityManager.createQuery(vCriteriaQuery)
      .getResultList();

    /** Conversion de la liste en valeur */
    List < ElementRechecheBonReceptionValue > vListeResultat = new ArrayList < ElementRechecheBonReceptionValue >();

    for (BonReceptionEntity vBonReceptionEntite : listeResultatRechercheBonReception) {
      ElementRechecheBonReceptionValue vEv = vPersistanceUtilities.ResultatRechecheBonReceptionValue(vBonReceptionEntite);
      vListeResultat.add(vEv);
      
    }
    
    /** Construction de l'objet de retour de la recherche **/
    ResultatRechecheBonReceptionValue vResultatRechecheBonReceptionValue = new ResultatRechecheBonReceptionValue();
    Collections.sort(vListeResultat);
    vResultatRechecheBonReceptionValue.setListeElementRechecheBonReceptionValeur(new TreeSet<>(vListeResultat));
   
    return vResultatRechecheBonReceptionValue;

  }

  /**
   * Accesseur en lecture de l'attribut <code>entityManager</code>.
   * 
   * @return EntityManager L'attribut entityManager à lire.
   */
  public EntityManager getEntityManager() {
    return entityManager;
  }

  @Override
  public BonReceptionValue rechercheBonReceptionParId(Long id) {
	  BonReceptionEntity vBonReceptionEntity = this.rechercherParId(id, BonReceptionEntity.class);

	  BonReceptionValue vBonReceptionValueResultat = vPersistanceUtilities.toValue(vBonReceptionEntity);
    return vBonReceptionValueResultat;
  }

  
	@Override
	public BonReceptionValue rechercheBonReceptionByReference(String reference) {
		//LOGGER.info("BonReceptionPersistanceImpl --rechercheBonReceptionByReference");
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BonReceptionEntity> criteriaQuery = criteriaBuilder.createQuery(BonReceptionEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<BonReceptionEntity> root = criteriaQuery.from(BonReceptionEntity.class);
		
		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), reference));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <BonReceptionEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    BonReceptionEntity entity = null;
	    if (resultatEntite!= null){
	    	if (resultatEntite.size() > 0){
	    		entity = resultatEntite.get(0);
	    		BonReceptionValue dto = vPersistanceUtilities.toValue(entity);
	    		return dto;
	    	}
	    	
	    }
	    
		return null;
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	@Override
	public List<BonReceptionValue> getAll() {

		List<BonReceptionEntity> listEntity = this.lister(BonReceptionEntity.class);
		
		List<BonReceptionValue> listDTO = new ArrayList<BonReceptionValue>();
		
		for(BonReceptionEntity entity : listEntity){
			
			listDTO.add(vPersistanceUtilities.toValue(entity));
		}
		
		return listDTO;
	}
  
}
