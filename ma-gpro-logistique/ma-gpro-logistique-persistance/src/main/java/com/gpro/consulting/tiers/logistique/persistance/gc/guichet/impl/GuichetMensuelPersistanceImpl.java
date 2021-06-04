package com.gpro.consulting.tiers.logistique.persistance.gc.guichet.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.RechercheMulticritereGuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetMensuelPersistance;
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
	public Long getNextNumfactureAvoirReference(Calendar c) {
	    int vAnneeCourante = c.get(Calendar.YEAR);
	    int vMoisCourant=(c.get(Calendar.MONTH)+1);
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
	public Long getNextNumfactureReference(Calendar c) {
	    int vAnneeCourante = c.get(Calendar.YEAR);
	    int vMoisCourant=(c.get(Calendar.MONTH)+1);
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
	public Long getNextNumBonReceptionReference(Calendar c) {
	    int vAnneeCourante = c.get(Calendar.YEAR);
	    int vMoisCourant=(c.get(Calendar.MONTH)+1);
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
	public String getPrefixBonReception(Calendar c) {
	    int vAnneeCourante = c.get(Calendar.YEAR);
	    int vMoisCourant=(c.get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.prefixeBonReception from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    String vNextNumBonReception= (String) vResult;
	 
	    return vNextNumBonReception;
	}


	@Override
	public String getPrefixFacture(Calendar c) {
	    int vAnneeCourante = c.get(Calendar.YEAR);
	    int vMoisCourant=(c.get(Calendar.MONTH)+1);
	       Query vQuery = this.entityManager.createQuery(
	      "select g.prefixeFacture from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

	    Object vResult = vQuery.getSingleResult();
	    String vNextNumBonReception= (String) vResult;
	 
	    return vNextNumBonReception;
	}
	

	@Override
	public String getPrefixFactureAvoir(Calendar c) {
	    int vAnneeCourante = c.get(Calendar.YEAR);
	    int vMoisCourant=(c.get(Calendar.MONTH)+1);
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
	public Long getNextNumBonReceptionReferenceNonDeclarer(Calendar c) {
		   int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceBonReceptionNonDeclarerCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixBonReceptionNonDeclarer(Calendar c) {
		   int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeBonReceptionNonDeclarer from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
		    
	}


	@Override
	public Long getNextNumfactureAchatReferenceNondeclarer(Calendar c) {
		   int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceFactureAchatNDCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
		
	}


	@Override
	public String getPrefixFactureAchatNondeclarer(Calendar c) {
		   int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeFactureAchatND from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}





	@Override
	public Long modifierGuichetFactureAchatNonDeclarerMensuel(GuichetMensuelValue pGuichetValeur) {
	
		  GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceFactureAchatNDCourante(pGuichetValeur.getNumReferenceFactureAchatNonDeclarerCourante());   
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumReglementAchat(Calendar c) {
		  int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceReglementAchatCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixReglementAchat(Calendar c) {
		 int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeReglementAchat from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetReglementAchatMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceReglementAchatCourante(pGuichetValeur.getNumReferenceReglementAchatCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumDetReglementAchat(Calendar c) {
		  int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceDetReglementAchatCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixDetReglementAchat(Calendar c) {
		 int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeDetReglementAchat from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetDetReglementAchatMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceDetReglementAchatCourante(pGuichetValeur.getNumReferenceDetReglementAchatCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumDetReglement(Calendar c) {
		  int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceDetReglementCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixDetReglement(Calendar c) {
		 int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeDetReglement from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetDetReglementMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceDetReglementCourante(pGuichetValeur.getNumReferenceDetReglementCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public GuichetMensuelValue getByAnneeAndMois(Long year, Long month) {
		
		if(year != null && month != null) {
			
		
			  Query vQuery = this.entityManager.createQuery(
				      "select g from GuichetMensuelEntity g where g.annee =" + year + " and g.mois="+month);

				    Object vResult = vQuery.getSingleResult();
				    GuichetMensuelEntity vGuichetMesuel = (GuichetMensuelEntity) vResult;
				
				    if(vGuichetMesuel != null)
				    	return GuichetPersistanceUtilities.toMensuelValue(vGuichetMesuel);
				    
				     	 
		}
			
    
	    return null;
	}


	@Override
	public GuichetMensuelValue getById(Long id) {
		GuichetMensuelEntity guichetMensuelValue = this.rechercherParId(id, GuichetMensuelEntity.class);

	    return GuichetPersistanceUtilities.toMensuelValue(guichetMensuelValue);
	}


	@Override
	public String update(GuichetMensuelValue guichetMensuelValue) {
		
		GuichetMensuelEntity entity = (GuichetMensuelEntity) this.modifier(GuichetPersistanceUtilities.toMensuelEntity(guichetMensuelValue));

	    return entity.getId().toString();
	}


	@Override
	public String create(GuichetMensuelValue guichetMensuelValue) {
		GuichetMensuelEntity entity = (GuichetMensuelEntity) this.creer(GuichetPersistanceUtilities.toMensuelEntity(guichetMensuelValue));

	    return entity.getId().toString();
	}


	@Override
	public void deleteById(Long id) {
		
		this.supprimerEntite(GuichetMensuelEntity.class, id);
		
	}


	@Override
	public List<GuichetMensuelValue> rechercheMultiCritere(RechercheMulticritereGuichetMensuelValue request) {
		//logger.info("rechercherMultiCritere");
		
				CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
				
				CriteriaQuery<GuichetMensuelEntity> criteriaQuery = criteriaBuilder.createQuery(GuichetMensuelEntity.class);
				List<Predicate> whereClause = new ArrayList<Predicate>();
				
				Root<GuichetMensuelEntity> root = criteriaQuery.from(GuichetMensuelEntity.class);
				
			
					//crtitere
				
				  // Set nature
			    if (request.getAnnee() != null ) {
					whereClause.add(criteriaBuilder.equal(root.get("annee"), request.getAnnee()));
				}
			    
				
			    if (request.getMois() != null ) {
					whereClause.add(criteriaBuilder.equal(root.get("mois"), request.getMois()));
				}
			 	
			 	criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			 	
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
				
				
				  List <GuichetMensuelEntity> resultatEntite = null;

				// If criterias are empty
				/*if (request.isOptimized()) {
					resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();

				} else {
					resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
				}*/
				
				resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			    List<GuichetMensuelValue> list = new ArrayList<GuichetMensuelValue>();
			    
			    for (GuichetMensuelEntity entity : resultatEntite) {
			    	GuichetMensuelValue dto = GuichetPersistanceUtilities.toMensuelValue(entity);
			    	list.add(dto);
			    }

			/*    ResultatRechecheFactureValue resultat = new ResultatRechecheFactureValue();
			    Collections.sort(list);
			    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
			    resultat.setList(new TreeSet<>(list));*/

			    return list;
	}


	@Override
	public Long getNextNumReglementInverseAchat(Calendar c) {
		  int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceReglementInverseAchatCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixReglementInverseAchat(Calendar c) {
		 int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeReglementInverseAchat from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetReglementInverseAchatMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceReglementInverseAchatCourante(pGuichetValeur.getNumReferenceReglementInverseAchatCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumDetReglementInverseAchat(Calendar c) {
		  int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceDetReglementInverseAchatCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixDetReglementInverseAchat(Calendar c) {
		 int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeDetReglementInverseAchat from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetDetReglementInverseAchatMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceDetReglementInverseAchatCourante(pGuichetValeur.getNumReferenceDetReglementInverseAchatCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public Long getNextNumDetReglementInverse(Calendar c) {
		 int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceDetReglementInverseCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public String getPrefixDetReglementInverse(Calendar c) {
		 int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeDetReglementInverse from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetDetReglementInverseMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceDetReglementInverseCourante(pGuichetValeur.getNumReferenceDetReglementInverseCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
	}


	@Override
	public String getPrefixReglementInverse(Calendar c) {
		 int vAnneeCourante =c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.prefixeReglementInverse from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    String vNextNumBonReception= (String) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long getNextNumReglementInverse(Calendar c) {
		  int vAnneeCourante = c.get(Calendar.YEAR);
		    int vMoisCourant=(c.get(Calendar.MONTH)+1);
		       Query vQuery = this.entityManager.createQuery(
		      "select g.numReferenceReglementInverseCourante from GuichetMensuelEntity g where g.annee =" + vAnneeCourante + " and g.mois="+vMoisCourant);

		    Object vResult = vQuery.getSingleResult();
		    Long vNextNumBonReception= (Long) vResult;
		 
		    return vNextNumBonReception;
	}


	@Override
	public Long modifierGuichetReglementInverseMensuel(GuichetMensuelValue pGuichetValeur) {
		   GuichetMensuelEntity vGuichetEntite = rechercherGuichetMensuel(pGuichetValeur);
			
		    vGuichetEntite.setNumReferenceReglementInverseCourante(pGuichetValeur.getNumReferenceReglementInverseCourante()); 
		    this.entityManager.merge(vGuichetEntite);
		    this.entityManager.flush();
		    return vGuichetEntite.getId();
		
	}

}
