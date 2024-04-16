package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.GuichetRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IGuichetRouleauFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.GuichetRouleauFiniEntity;


/**
 * 
 * @author  ridha.khaskhoussy
 */

@Component
public class GuichetRouleauFiniPersistanceImpl extends AbstractPersistance 
    implements IGuichetRouleauFiniPersistance {

  /** EntityManager. */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Constructeur de la classe.
   */
  public GuichetRouleauFiniPersistanceImpl() {
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
      "select g.numReferenceCourante from GuichetRouleauFiniEntity g where g.annee =" + vAnneeCourante);

    Object vResult = vQuery.getSingleResult();
    Long vNextNumBonReception = (Long) vResult;
    // Format du Numéro du bon de reception : NNNNNNN
    return vNextNumBonReception;
  }
 
  /**
   * {@inheritDoc}
   */
  @Override
  public Long modifierGuichetRouleauFini(final GuichetRouleauFiniValue pGuichetValeur) {
    // Convertir la valeur du guichet en une entité.
	  GuichetRouleauFiniEntity vGuichetEntite = rechercherGuichetRouleauFini(pGuichetValeur);
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
   * Méthode de recheche d'une entité GuichetRouleauFiniEntity par année.
   * @param pGuichetValeur ke guichet à rechercher
   * @return GuichetBonReceptionEntity le guichet trouvé en base
   */
  public GuichetRouleauFiniEntity rechercherGuichetRouleauFini(final GuichetRouleauFiniValue pGuichetValeur) {
	  GuichetRouleauFiniEntity vGuichetEntite = 
      this.entityManager.find(GuichetRouleauFiniEntity.class, pGuichetValeur.getAnnee(), 
        LockModeType.PESSIMISTIC_WRITE);
    return vGuichetEntite;
  }

@Override
public Integer getPrefixe() {
	  // Année courante
    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
    // Chercher le dernier numéro dans la base et le charger
    Query vQuery = this.entityManager.createQuery(
      "select g.prefixe from GuichetRouleauFiniEntity g where g.annee =" + vAnneeCourante);

    Object vResult = vQuery.getSingleResult();
    Integer vNextNumBonReception = (Integer) vResult;
    // Format du Numéro du bon de reception : NNNNNNN
    return vNextNumBonReception;
}
}
