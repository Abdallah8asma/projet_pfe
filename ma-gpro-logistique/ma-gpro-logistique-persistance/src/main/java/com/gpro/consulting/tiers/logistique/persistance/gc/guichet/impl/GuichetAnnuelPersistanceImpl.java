package com.gpro.consulting.tiers.logistique.persistance.gc.guichet.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetAnnuelPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity.GuichetAnnuelEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.utilities.GuichetPersistanceUtilities;
@Component
public class GuichetAnnuelPersistanceImpl extends AbstractPersistance implements IGuichetAnnuelPersistance{
   
	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  
	  @Autowired
		private GuichetPersistanceUtilities vPersistanceUtilities;

	  /**
	   * Constructeur de la classe.
	   */
	 
	  
	  
	  
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
	  public GuichetAnnuelEntity rechercherGuichetAnnuel(final GuichetAnnuelValue pGuichetValeur) {
		  GuichetAnnuelEntity vGuichetEntite = 
	     // this.entityManager.find(GuichetAnnuelEntity.class, pGuichetValeur.getId(), 
	      //  LockModeType.PESSIMISTIC_WRITE);
		  
		  this.entityManager.find(GuichetAnnuelEntity.class, pGuichetValeur.getId()
			        );
		  
		  
	    return vGuichetEntite;
	  }
	  

	  @Override
		public GuichetAnnuelValue getById(Long id) {
			
		  GuichetAnnuelEntity GuichetAnnuelEntity = this.rechercherParId(id, GuichetAnnuelEntity.class);

		  //System.out.println("==>GuichetAnnuelEntity: "+GuichetPersistanceUtilities.toValue(GuichetAnnuelEntity).getAnnee());
		    return GuichetPersistanceUtilities.toValue(GuichetAnnuelEntity);
		}
	  
	  @Override
      public String modifierGuichetAnnuel(GuichetAnnuelValue pGuichetAnnuelValue) {
		  //System.out.println("persistance--:"+pGuichetAnnuelValue.getNumReferenceFactureCourant());
		  GuichetAnnuelEntity vGuichetAnnuelEntity = (GuichetAnnuelEntity) this
            .modifier(vPersistanceUtilities.toEntity(pGuichetAnnuelValue));

        return vGuichetAnnuelEntity.getId().toString();

      }

	  
	  @Override
	public Long getNextNumBonCommandeReference() {
		// TODO Auto-generated method stub
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceCommandeCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
		
	}


	@Override
	public Long getNextNumFactureReference() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceFactureCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}


	@Override
	public Long getNextNumAvoirReference() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceAvoirCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}


	@Override
	public Long getNextNumReglementReference() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceReglementCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}


	@Override
	public Long modifierGuichetFactureAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceFactureCourante(pGuichetValeur.getNumReferenceFactureCourant());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();

	}


	@Override
	public Long modifierGuichetAvoirAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceAvoirCourante(pGuichetValeur.getNumReferenceAvoirCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();


	}


	@Override
	public Long modifierGuichetBonCommandeAnnuel(
			GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceCommandeCourante(pGuichetValeur.getNumReferenceCommandeCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();


	}


	@Override
	public Long modifierGuichetReglementAnnuel(
			GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
				// Convertir la valeur du guichet en une entité.
				  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
				// Sauvegarde de l'instance dans la base
			    vGuichetEntite.setNumReferenceReglementCourante(pGuichetValeur.getNumReferenceReglementCourante());
			    this.entityManager.merge(vGuichetEntite);
			    this.entityManager.flush();
			    return vGuichetEntite.getId();
	}

	@Override
	public List<GuichetAnnuelValue> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNextNumBLReference() {
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonLivraisonCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}

	@Override
	public Long modifierGuichetBLAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonLivraisonCourante(pGuichetValeur.getNumReferenceBonLivraisonCourante());
	    
	    
	    
	   // this.entityManager.merge(vGuichetEntite);
	    //this.entityManager.flush();
	  ///  return vGuichetEntite.getId();
	    
	    
	    
	    return Long.parseLong(modifierGuichetAnnuel(pGuichetValeur));
	}

	@Override
	public Long getNextNumBonComptoirReference() {
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonComptoirCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}

	@Override
	public Long modifierGuichetBonComptoirAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonComptoirCourante(pGuichetValeur.getNumReferenceBonComptoirCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public GuichetAnnuelValue getCurrentGuichetAnnuel() {
		 
	
		int idCurrentYear = Calendar.getInstance().get(Calendar.YEAR) - 2016+1;
			
		
	    GuichetAnnuelEntity GuichetAnnuelEntity = this.rechercherParId(new Long(idCurrentYear), GuichetAnnuelEntity.class);

		  //System.out.println("==>GuichetAnnuelEntity: "+GuichetPersistanceUtilities.toValue(GuichetAnnuelEntity).getAnnee());
	    return GuichetPersistanceUtilities.toValue(GuichetAnnuelEntity);
	}

	@Override
	public Long modifierGuichetBRAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonReceptionCourante(pGuichetValeur.getNumReferenceBonReceptionCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetFactureAchatAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceFactureAchatCourante(pGuichetValeur.getNumReferenceFactureAchatCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long getNextNumReglementAchatReference() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceReglementAchatCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}

	@Override
	public Long modifierGuichetReglementAchatAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceReglementAchatCourante(pGuichetValeur.getNumReferenceReglementAchatCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBCAchatAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceCommandeAchatCourante(pGuichetValeur.getNumReferenceCommandeAchatCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBCAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceCommandeCourante(pGuichetValeur.getNumReferenceCommandeCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetDevisAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(currentGuichetAnnuel);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceDevisAchatCourante(currentGuichetAnnuel.getNumReferenceDevisAchatCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetDevisAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(currentGuichetAnnuel);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceDevisCourante(currentGuichetAnnuel.getNumReferenceDevisCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetFactureAvoirAchatAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumFactureAvoirAchatCourante(pGuichetValeur.getNumFactureAvoirAchatCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBonStockAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumBonStockCourante(pGuichetValeur.getNumBonStockCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBonInventaireAnnuel(GuichetAnnuelValue pGuichetValeur) {
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
			// Sauvegarde de l'instance dans la base
		    vGuichetEntite.setNumBonInventaireCourante(pGuichetValeur.getNumBonInventaireCourante());
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBonTransfertReceptionAnnuel(GuichetAnnuelValue pGuichetValeur) {
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
				// Sauvegarde de l'instance dans la base
			    vGuichetEntite.setNumBonTransfertReceptionCourante(pGuichetValeur.getNumBonTransfertReceptionCourante());
			    this.entityManager.merge(vGuichetEntite);
			    this.entityManager.flush();
			    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBonTransfertSortieAnnuel(GuichetAnnuelValue pGuichetValeur) {
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
				// Sauvegarde de l'instance dans la base
			    vGuichetEntite.setNumBonTransfertSortieCourante(pGuichetValeur.getNumBonTransfertSortieCourante());
			    this.entityManager.merge(vGuichetEntite);
			    this.entityManager.flush();
			    return vGuichetEntite.getId();
	}
	

	
	@Override
	public Long getNextNumBonMouvementEntre() {
		// TODO Auto-generated method stub
	
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numBonMouvementEntre from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}

	@Override
	public Long getNextNumBonMouvementSortie() {
		// TODO Auto-generated method stub
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	   
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numBonMouvementSortie from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}
	
	
	@Override
	public Long modifierGuichetBonMouvementEntreAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumBonMouvementEntre(pGuichetValeur.getNumBonMouvementEntre());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBonMouvementSortieAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumBonMouvementSortie(pGuichetValeur.getNumBonMouvementSortie());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetBLNDAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonLivraisonNDCourante(pGuichetValeur.getNumReferenceBonLivraisonNDCourante());
	    
	    
	    
	   // this.entityManager.merge(vGuichetEntite);
	    //this.entityManager.flush();
	  ///  return vGuichetEntite.getId();
	    
	    
	    
	    return Long.parseLong(modifierGuichetAnnuel(pGuichetValeur));
	}

	@Override
	public Long modifierGuichetFANDAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(currentGuichetAnnuel);
		
		    vGuichetEntite.setNumReferenceFactureNDCourante(currentGuichetAnnuel.getNumReferenceFactureNDCourante());  
		    this.entityManager.flush();
		    return vGuichetEntite.getId();

	}

	@Override
	public GuichetAnnuelValue getCurrentGuichetAnnuel(Calendar c) {
		
		int idCurrentYear = c.get(Calendar.YEAR) - 2016+1;
			
		
	    GuichetAnnuelEntity GuichetAnnuelEntity = this.rechercherParId(new Long(idCurrentYear), GuichetAnnuelEntity.class);

		  //System.out.println("==>GuichetAnnuelEntity: "+GuichetPersistanceUtilities.toValue(GuichetAnnuelEntity).getAnnee());
	    return GuichetPersistanceUtilities.toValue(GuichetAnnuelEntity);

	}

	@Override
	public Long modifierGuichetReglementInverseAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceReglementInverseCourante(pGuichetValeur.getNumReferenceReglementInverseCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

	@Override
	public Long modifierGuichetReglementNonDeclarerAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceReglementNonDeclarerCourante(pGuichetValeur.getNumReferenceReglementNonDeclarerCourante());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}
	
}
