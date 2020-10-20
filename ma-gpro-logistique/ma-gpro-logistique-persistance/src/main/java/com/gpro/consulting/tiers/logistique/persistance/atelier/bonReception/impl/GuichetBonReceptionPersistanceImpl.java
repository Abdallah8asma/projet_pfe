package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.IGuichetBonReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.GuichetBonReceptionEntity;


/**
 * 
 * @author  ridha.khaskhoussy
 */

@Component
public class GuichetBonReceptionPersistanceImpl extends AbstractPersistance 
    implements IGuichetBonReceptionPersistance {

  /** EntityManager. */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Constructeur de la classe.
   */
  public GuichetBonReceptionPersistanceImpl() {
    // Constructeur vide
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Long getNextNumReference() {

    // Année courante
    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
    // Chercher le dernier numéro dans la base et le charger
    Query vQuery = this.entityManager.createQuery(
      "select g.numReferenceCourante from GuichetBonReceptionEntity g where g.annee =" + vAnneeCourante);

    Object vResult = vQuery.getSingleResult();
    Long vNextNumBonReception = (Long) vResult;
    // Format du Numéro du bon de reception : NNNNNNN
    return vNextNumBonReception;
  }
 
  /**
   * {@inheritDoc}
   */
  @Override
  public Long modifierGuichetBonReception(final GuichetBonReceptionValue pGuichetValeur) {
    // Convertir la valeur du guichet en une entité.
	  GuichetBonReceptionEntity vGuichetEntite = rechercherGuichetBonReception(pGuichetValeur);
    // Sauvegarde de l'instance dans la base
    vGuichetEntite.setNumReferenceCourante(pGuichetValeur.getNumReferenceCourant());
    this.entityManager.merge(vGuichetEntite);
    this.entityManager.flush();
    return vGuichetEntite.getAnnee();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityManager getEntityManager() {
    return this.entityManager;
  }

  /**
   * Méthode de recheche d'une entité GuichetBonReceptionEntity par année.
   * @param pGuichetValeur ke guichet à rechercher
   * @return GuichetBonReceptionEntity le guichet trouvé en base
   */
  public GuichetBonReceptionEntity rechercherGuichetBonReception(final GuichetBonReceptionValue pGuichetValeur) {
	  GuichetBonReceptionEntity vGuichetEntite = 
      this.entityManager.find(GuichetBonReceptionEntity.class, pGuichetValeur.getAnnee(), 
        LockModeType.PESSIMISTIC_WRITE);
    return vGuichetEntite;
  }
}
