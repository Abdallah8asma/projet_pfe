package com.gpro.consulting.tiers.logistique.persistance.gc.guichet.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.IBonReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.GuichetBonReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetMensuelPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity.GuichetMensuelEntity;
@Component
public class GuichetMensuelPersistanceImpl extends AbstractPersistance implements IGuichetMensuelPersistance{
   
	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;

	  /**
	   * Constructeur de la classe.
	   */
	 
	  
	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public Long getNextNumBonLivraisonReference() {

	    // Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonLivraisonCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNumBonLivraison = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNumBonLivraison;
	  }
	 

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public Long getNextNumBonSortieReference() {

	    // Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonSortieCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNumBonSortie = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNumBonSortie;
	  }
	  
	  
	  
	  
	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public Long modifierGuichetBonLivraisonMensuel(final GuichetMensuelValue pGuichetValeur) {
	    // Convertir la valeur du guichet en une entité.
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
		  // Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonLivraisonCourante(pGuichetValeur.getNumReferenceBonLivraisonCourant());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
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
	  public GuichetMensuelEntity rechercherGuichetMensuel(final GuichetMensuelValue pGuichetValeur) {
		  GuichetMensuelEntity vGuichetEntite = 
	      this.entityManager.find(GuichetMensuelEntity.class, pGuichetValeur.getId(), 
	        LockModeType.PESSIMISTIC_WRITE);
		  
		  return vGuichetEntite;
	  }
	  

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public Long modifierGuichetBonSortieMensuel(final GuichetMensuelValue pGuichetValeur) {
	    // Convertir la valeur du guichet en une entité.
		    GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
		  // Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonSortieCourante(pGuichetValeur.getNumReferenceBonSortieCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	  }
}
