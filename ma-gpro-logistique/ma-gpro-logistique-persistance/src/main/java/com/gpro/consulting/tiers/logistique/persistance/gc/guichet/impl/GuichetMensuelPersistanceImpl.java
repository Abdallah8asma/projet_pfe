package com.gpro.consulting.tiers.logistique.persistance.gc.guichet.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.IBonReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.GuichetBonReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetMensuelPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity.GuichetAnnuelEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity.GuichetMensuelEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.utilities.GuichetPersistanceUtilities;
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


	@Override
	public Long getNextNumBonCommandeReference() {
	
	
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonCommandeCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNumBonCommande= (Long) vResult;
	 
	    return vNextNumBonCommande;
	}


	@Override
	public Long modifierGuichetBonCommandeMensuel(final GuichetMensuelValue pGuichetValeur) {

		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
	
	    vGuichetEntite.setNumReferenceBonCommandeCourante(pGuichetValeur.getNumReferenceBonCommandeCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}


	@Override
	public Long modifierGuichetFactureAvoirMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceAvoirCourante(pGuichetValeur.getNumReferenceAvoirCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumfactureAvoirReference() {
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceAvoirCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNumfactureAvoir= (Long) vResult;
	 
	    return vNextNumfactureAvoir;
	}


	@Override
	public Long modifierGuichetFactureMensuel(GuichetMensuelValue pGuichetValeur) {
		
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceFactureCourante(pGuichetValeur.getNumReferenceFactureCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumfactureReference() {
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceFactureCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNumfacture= (Long) vResult;
	 
	    return vNextNumfacture;
	}


	@Override
	public Long modifierGuichetBonReceptionMensuel(GuichetMensuelValue pGuichetValeur) {
		  GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceBonReceptionCourante(pGuichetValeur.getNumReferenceBonReceptionCourante());   
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumBonReceptionReference() {
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonReceptionCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNumBonReception= (Long) vResult;
	 
	    return vNextNumBonReception;
	}


	

	@Override
	public GuichetMensuelValue getCurrentGuichetMensuel() {
	int idCurrentYear = Calendar.getInstance().get(Calendar.YEAR) - 2016+1;
			
		
	    GuichetMensuelEntity GuichetMensuelEntity = this.rechercherParId(new Long(idCurrentYear), GuichetMensuelEntity.class);
	    return GuichetPersistanceUtilities.toMensuelValue(GuichetMensuelEntity);
	}


	@Override
	public String getPrefix() {
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.prefixeBC from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    String vNextNumBonReception= (String) vResult;
	 
	    return vNextNumBonReception;
	}
	
	
	
	@Override
	public String getPrefixBonReception() {
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.prefixeBonReception from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    String vNextNumBonReception= (String) vResult;
	 
	    return vNextNumBonReception;
	}


	@Override
	public String getPrefixFacture() {
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.prefixeFacture from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    String vNextNumBonReception= (String) vResult;
	 
	    return vNextNumBonReception;
	}
	

	@Override
	public String getPrefixFactureAvoir() {
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.prefixeAvoir from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    String vNextNumBonReception= (String) vResult;
	 
	    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetBonReceptionNonDeclarerMensuel(GuichetMensuelValue pGuichetValeur) {
		  GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceBonReceptionNonDeclarerCourante(pGuichetValeur.getNumReferenceBonReceptionNonDeclarerCourante());   
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumBonReceptionReferenceNonDeclarer() {
		   int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
		    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceBonReceptionNonDeclarerCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixBonReceptionNonDeclarer() {
		   int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
		    int vMoisCourant=(Calendar.getInstance().get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeBonReceptionNonDeclarer from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}
	
	
	
	
	
	
}
