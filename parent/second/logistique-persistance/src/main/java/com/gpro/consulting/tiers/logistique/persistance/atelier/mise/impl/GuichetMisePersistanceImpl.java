package com.gpro.consulting.tiers.logistique.persistance.atelier.mise.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.GuichetMiseValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IGuichetMisePersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.GuichetMiseEntity;



/**
 * 
 * @author  ridha.khaskhoussy
 */
@Component
public class GuichetMisePersistanceImpl extends AbstractPersistance 
    implements IGuichetMisePersistance {

  /** EntityManager. */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Constructeur de la classe.
   */
  public GuichetMisePersistanceImpl() {
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
      "select g.numReferenceCourante from GuichetMiseEntity g where g.annee =" + vAnneeCourante);

    Object vResult = vQuery.getSingleResult();
    Long vNextNumMise = (Long) vResult;
    // Format du Numéro du bon de reception : NNNNNNN
    return vNextNumMise;
  }
 
  /**
   * {@inheritDoc}
   */
  @Override
  public Long modifierGuichetMise(final GuichetMiseValue pGuichetValeur) {
    // Convertir la valeur du guichet en une entité.
	  GuichetMiseEntity vGuichetEntite = rechercherGuichetMise(pGuichetValeur);
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
   * Méthode de recheche d'une entité GuichetMiseEntity par année.
   * @param pGuichetValeur ke guichet à rechercher
   * @return GuichetMiseEntity le guichet trouvé en base
   */
  public GuichetMiseEntity rechercherGuichetMise(final GuichetMiseValue pGuichetValeur) {
	  GuichetMiseEntity vGuichetEntite = 
      this.entityManager.find(GuichetMiseEntity.class, pGuichetValeur.getAnnee(), 
        LockModeType.PESSIMISTIC_WRITE);
    return vGuichetEntite;
  }
}
