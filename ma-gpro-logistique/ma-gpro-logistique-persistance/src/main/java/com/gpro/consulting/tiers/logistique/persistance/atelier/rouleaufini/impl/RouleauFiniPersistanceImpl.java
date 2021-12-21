package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.impl;

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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.erp.socle.j2ee.mt.socle.utilities.StringUtils;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.RechercheMulticritereLogistiqueReportingtValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.RouleauFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.utilities.RouleauFiniPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BonMouvementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MouvementEntite;

/**
 * Implementation of {@link IRouleauFiniPersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
@Component
public class RouleauFiniPersistanceImpl extends AbstractPersistance implements IRouleauFiniPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(RouleauFiniPersistanceImpl.class);
	
	private String PERCENT = "%"; 
	
	private String PREDICATE_BARRECODE = "codeBarre";
	private String PREDICATE_EMPLACEMENT = "emplacement";
	private String PREDICATE_REFERECE_PRODUIT = "reference";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_PARTIEINTERRESSEE = "partieInteresseeId";
	private String PREDICATE_ENTREPOT = "entrepot";
	private String PREDICATE_REF_MISE = "referenceMise";
	private String PREDICATE_POID = "poids";
	private String PREDICATE_METRAGE = "metrage";
	private String PREDICATE_CR_QUALITE = "choix";
	private String PREDICATE_LAIZE = "laize";
	private String PREDICATE_INFO_MODIF = "infoModif";
	private String PREDICATE_BON_SORTIE = "bonSortie";
	private String PREDICATE_DATE_INTRODUCTION = "dateIntroduction";
	private String PREDICATE_DATE_SORTIE = "dateSortie";
	private String PREDICATE_FINI = "fini";
	private String PREDICATE_BON_INVENTAIRE = "bonInventaire";
	private String PREDICATE_DATE_INVENTAIRE = "dateInventaire";
	private String PREDICATE_ID = "id";
	private String PREDICATE_PRODUIT_ID = "produitId";
	
	private String PREDICATE_PRODUCTION = "production";
	
	private String PREDICATE_TYPE_OF = "typeOf";
	
	private String PREDICATE_USERNAME = "responsable";
	
	private String PREDICATE_numberOfBox = "numberOfBox";
	

	@PersistenceContext
	private EntityManager entityManager;
	  
	@Autowired
	private RouleauFiniPersistanceUtilities persistanceUtilities;

	@SuppressWarnings("unchecked")
	@Override
	public ResultatRechecheRouleauFiniValue rechercherMultiCritere(RechercheMulticritereRouleauFiniValue request) {
		
		//logger.info("RouleauFiniPersistanceImpl --rechercherMultiCritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
		
		// Set request.codeBarre on whereClause if not empty or null
		if (estNonVide(request.getCodeBarre())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_BARRECODE), request.getCodeBarre()));
		}
		
		// Set request.emplacement on whereClause if not empty or null
		if (estNonVide(request.getEmplacement())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_EMPLACEMENT), request.getEmplacement()));
		}
		
		// Set request.reference on whereClause if not empty or null
		if (estNonVide(request.getReference())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), request.getReference()));
		}
		
		// Set request.partieInteresseeId on whereClause if not null
	    if (request.getPartieInteresseeId() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINTERRESSEE), request.getPartieInteresseeId()));
	    }
	    
		// Set request.produitId on whereClause if not null
	    if (request.getProduitId() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
	    }
		
		// Set request.refMise on whereClause if not null
	    if (estNonVide(request.getRefMise())) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REF_MISE), request.getRefMise()));
	    }	    
	    
		// Cherche only the RouleauFini list that not exist on any BonDeSortie,
	    //so the bonSortie colum should be null
	    whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE)));
	    
	    
		// Set request.infoModif on whereClause if not null
		if (estNonVide(request.getInfoModif())) {
			Expression<String> path = root.get(PREDICATE_INFO_MODIF);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getInfoModif().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		
		
		// Set request.refMise on whereClause if not null
	    if (request.getMetrage() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_METRAGE), request.getMetrage()));
	    }	  
	    
	    
	 // Set request.refMise on whereClause if not null
	    if (request.getNumberOfBox() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_numberOfBox), request.getNumberOfBox()));
	    }	
		
		
	    if (request.getIds() != null && request.getIds().size()>0 ) {
	    	
	    	whereClause.add(root.get("id").in(request.getIds() ));
	 	    
		    //  vWhereClause.add(vBuilder.in(vRootEntiteStock.get(id)).value( pRechercheMulticritereEntiteStockValue.getIds())) ;
		    		
	    }
	    
	    
	    
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
		List < RouleauFiniEntity > resultatEntite = null;
	    		

		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(1040).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}
	    
	    
	    List<RouleauFiniValue> list = new ArrayList<RouleauFiniValue>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	    	RouleauFiniValue dto = persistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheRouleauFiniValue resultat = new ResultatRechecheRouleauFiniValue();
		Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));
	    //System.out.println("---Persist---resultat----"+resultat);
	    return resultat;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResultatRechecheRouleauFiniValue rechercherMultiCritereStandard(RechercheMulticritereRouleauFiniStandardValue request) {
		
		//logger.info("RouleauFiniPersistanceImpl --rechercherMultiCritereStandard");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
		
		// Set request.numMise on whereClause if not empty or null
		if (estNonVide(request.getNumMise())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REF_MISE), request.getNumMise()));
		}
		
		// Set request.partieInteresseeId on whereClause if not null
	    if (request.getPartieInteresseeId() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINTERRESSEE), request.getPartieInteresseeId()));
	    }
	    
//	    List<Predicate> orPredicateProduit = new ArrayList<Predicate>();;
	    
		// Set request.produitId on whereClause if not null
	    if (request.getProduitId() != null) {
//	    	orPredicateProduit.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
	    }
	    
		// Set request.idProduitReference on whereClause if not empty or null
		// recherche produit par choix de reference 
		if (estNonVide(request.getIdProduitReference())) {
//			orPredicateProduit.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getIdProduitReference()));
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getIdProduitReference()));
		}
	    
		
		
		// Set request.poidMin on whereClause if not null
	    if (request.getPoidMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_POID), request.getPoidMin()));
	    }
	    
		// Set request.poidMax on whereClause if not null
	    if (request.getPoidMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_POID), request.getPoidMax()));
	    }
	    
		// Set request.metrageMin on whereClause if not null
	    if (request.getMetrageMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMin()));
	    }
	    
		// Set request.metrageMax on whereClause if not null
	    if (request.getMetrageMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMax()));
	    }
		// Set request.laizeMin on whereClause if not null
	    if (request.getLaizeMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_LAIZE), request.getLaizeMin()));
	    }
	    
		// Set request.laizeMax on whereClause if not null
	    if (request.getLaizeMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_LAIZE), request.getLaizeMax()));
	    }
	    
		// Set request.crQalite on whereClause if not null
	    if (request.getCrQalite() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CR_QUALITE), request.getCrQalite()));
	    }
	    
		// Set request.entrepot on whereClause if not null
	    if (request.getEntrepot() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ENTREPOT).get("id"), request.getEntrepot()));
	    }
	    
		// Set request.emplacement on whereClause if not empty or null
		if (estNonVide(request.getEmplacement())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_EMPLACEMENT), request.getEmplacement()));
		}
		
		
		// Set request.emplacement on whereClause if not empty or null
				if (estNonVide(request.getUsername())) {
					whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_USERNAME), request.getUsername()));
				}

				if (request.getDateProductionDe() != null && !request.getDateProductionDe().equals("")) {
					whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_INTRODUCTION),
							request.getDateProductionDe()));
				}
				if (request.getDateProductionA() != null && !request.getDateProductionA().equals("")) {
					whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_INTRODUCTION),
							request.getDateProductionA()));
				}
				
				

				// Set request.emplacement on whereClause if not empty or null
				if (estNonVide(request.getTypeOf())) {
					whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE_OF), request.getTypeOf()));
				}
		
		/** Set request.inBonDeSortie on whereClause if not empty or null.
			if inBonDeSortie = oui  --> cherche only RouleauFini that have a BONSORTIE.
			if inBonDeSortie = non  --> cherche only RouleauFini that haven't a BONSORTIE.
			if inBonDeSortie = tous --> cherche on all RouleauFini.
		 */
		if (estNonVide(request.getInBonDeSortie())) {
			
			switch (request.getInBonDeSortie()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.isNotNull(root.get(PREDICATE_BON_SORTIE)));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE)));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
		
		/** Set request.fini on whereClause if not empty or null.
			if fini = oui  --> cherche only RouleauFini that FINI = true.
			if fini = non  --> cherche only RouleauFini that FINI = false.
			if fini = tous --> cherche on all RouleauFini.
		 */
		if (estNonVide(request.getFini())) {
			Expression<Boolean> expression = root.get(PREDICATE_FINI);
			switch (request.getFini()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.isTrue(expression));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.isFalse(expression));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
		
		// Set request.reference on whereClause if not empty or null
		if (estNonVide(request.getReference())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFERENCE), request.getReference()));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	   
	    
	    
		List < RouleauFiniEntity > resultatEntite = null;
		

		if (request.isOptimized()) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(1040).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}

	    List<RouleauFiniValue> list = new ArrayList<RouleauFiniValue>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	    	RouleauFiniValue dto = persistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheRouleauFiniValue resultat = new ResultatRechecheRouleauFiniValue();
		Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public String createRouleauFini(RouleauFiniValue request) {

		RouleauFiniEntity entity = (RouleauFiniEntity) this.creer(persistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void deleteRouleauFini(Long id) {
		
		this.supprimerEntite(RouleauFiniEntity.class, id.longValue());
	}
	
	@Override
	public String updateRouleauFini(RouleauFiniValue request) {
		
		RouleauFiniEntity entity = (RouleauFiniEntity) this.modifier(persistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public RouleauFiniValue getRouleauFiniById(Long id) {
		
		RouleauFiniEntity entity = this.rechercherParId(id, RouleauFiniEntity.class);

	    return persistanceUtilities.toValue(entity);
	}
	
	  /** 
	   * Recherche Multicritère utilisée pour la création d'inventaire
	   * 
	   *  (non-Javadoc)
	   * @see com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance#rechercherMultiCritereRouleauFiniStandard(com.gpro.consulting.tiers.logistique.coordination.rouleaufini.value.CritereRechercheRouleauStandardValue)
	   */
	  @Override
	  public List < RouleauFiniValue > rechercherMultiCritereRouleauFiniStandard(
	    CritereRechercheRouleauStandardValue pCritereRechercheRouleauStandard) {
		  
		  
	    /** Création de la squelette de la requete */
	    CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

	    CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
	    
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    
	    Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
	    
	    /** Ajout des critères de recherche */
	    
	    // Client
	    if (pCritereRechercheRouleauStandard.getClient()!=null) {
	      whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIEINTERRESSEE), pCritereRechercheRouleauStandard.getClient()));
	    }
	    // Entrepot
	    if(pCritereRechercheRouleauStandard.getEntrepot()!=null){
	      whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ENTREPOT), pCritereRechercheRouleauStandard.getEntrepot()));
	    }
	    // Emplacement
	    if(!StringUtils.isStringEmptyOrNull(pCritereRechercheRouleauStandard.getEmplacement())){
	      whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_EMPLACEMENT), pCritereRechercheRouleauStandard.getEmplacement()));
	    }
	    
	    // use 'dateEtat' criteria at whereClause if not null
	    /** (dateIntroduction <= dateEtat) AND ( dateSortie = null OR dateSortie >= dateEtat)
	      predicates: predicate1 and (predicate21 or predicate22) 
		 		predicate1 :	dateIntroduction <= dateEtat
	     		predicate21:	dateSortie = null
		 		predicate22: 	dateSortie >= dateEtat
		 */
	    if(pCritereRechercheRouleauStandard.getDateEtat()!=null){
	    	
	    	Predicate predicate1  = criteriaBuilder.lessThanOrEqualTo(
	    			root.<Calendar>get(PREDICATE_DATE_INTRODUCTION), 
	    			pCritereRechercheRouleauStandard.getDateEtat());
	    	
	    	Predicate predicate21 = criteriaBuilder.isNull(root.get(PREDICATE_DATE_SORTIE));
	    	Predicate predicate221 = criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE));
	    	Predicate predicate22 = criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Calendar>get(PREDICATE_DATE_SORTIE), 
	    			pCritereRechercheRouleauStandard.getDateEtat());
	    	
	    	Predicate predicate210 =  criteriaBuilder.and(predicate21,predicate221);
		      
	    	
	    	whereClause.add(predicate1);

	    	whereClause.add(criteriaBuilder.or(predicate210, predicate22));
	    }
  
		/** Set request.fini on whereClause if not empty or null.
		if fini = oui  --> cherche only RouleauFini that FINI = true.
		if fini = non  --> cherche only RouleauFini that FINI = false.
		if fini = tous --> cherche on all RouleauFini.
		 */
		if (estNonVide(pCritereRechercheRouleauStandard.getFini())) {
			Expression<Boolean> expression = root.get(PREDICATE_FINI);
			switch (pCritereRechercheRouleauStandard.getFini()) {
				case IConstanteLogistique.YES:
					whereClause.add(criteriaBuilder.isTrue(expression));
					break;
				case IConstanteLogistique.NO:
					whereClause.add(criteriaBuilder.isFalse(expression));
					break;
				case IConstanteLogistique.ALL:
					break;
				default:
					break;
			}
		}
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List < RouleauFiniEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<RouleauFiniValue> listRouleau = new ArrayList<RouleauFiniValue>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	      RouleauFiniValue vElement = persistanceUtilities.toValue(entity);
	      listRouleau.add(vElement);
	    }
	    
//	    System.out.println("----listRouleau"+listRouleau);
	    return listRouleau;

	  }

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public RouleauFiniValue rechercheRouleauFiniParId(Long id) {
		RouleauFiniEntity rouleauFiniEntity = this.rechercherParId(id, RouleauFiniEntity.class);

		RouleauFiniValue rouleauFiniValueResultat = persistanceUtilities.toValue(rouleauFiniEntity);
	    return rouleauFiniValueResultat;
	}

	@Override
	public List<RouleauFiniValue> getRouleauFiniListByBarreCodeList(List<String> barreCodeList, Long id) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

	    CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
	    
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    
	    Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
	    
	    /** Ajout des criteres de recherche par BarreCode*/
	    
	    // BarreCode is the reference on the db
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(barreCodeList));
	    
		// Set bonSortieIid on whereClause if not null
		if (id != null) {
			
	    	Predicate predicate1 = criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE));
	    	Predicate predicate2 = criteriaBuilder.equal(root.get(PREDICATE_BON_SORTIE), id);
		      
	    	whereClause.add(criteriaBuilder.or(predicate1, predicate2));
		}else{
			
		    // Retrive only RouleauFiniEntity list, that haven't BON_SORTIE
		    whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE)));
		}
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List < RouleauFiniEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<RouleauFiniValue> listRouleau = new ArrayList<RouleauFiniValue>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	      RouleauFiniValue value = persistanceUtilities.toValue(entity);
	      listRouleau.add(value);
	    }
	    
	    return listRouleau;
	    
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultatRechecheRouleauFiniValue getRouleauFiniByInfoModif(String infoModif) {
		
		//logger.info("getRouleauFiniByInfoModif");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
		
		Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
		
		// Set infoModif on whereClause if not null

		if (infoModif != null) {
			Expression<String> path = root.get(PREDICATE_INFO_MODIF);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + infoModif.toUpperCase() + PERCENT);
			criteriaQuery.where(criteriaBuilder.and(predicate));
		}
		
	    List < RouleauFiniEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<RouleauFiniValue> list = new ArrayList<RouleauFiniValue>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	    	RouleauFiniValue dto = persistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheRouleauFiniValue resultat = new ResultatRechecheRouleauFiniValue();
		Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public List<String> getListRefMiseRouleauNonSortie() {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

	    CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
	    
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    
	    Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
	   	    
	    // Set bonSortieIid on whereClause if null
		whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE)));
		criteriaQuery.multiselect(root.get(PREDICATE_REF_MISE)).distinct(true).where(whereClause.toArray(new Predicate[] {}));
	    List < RouleauFiniEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<String> listRefMiseRouleauxNonSortie = new ArrayList<String>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	      listRefMiseRouleauxNonSortie.add(entity.getReferenceMise());
	    }
	    
	    return listRefMiseRouleauxNonSortie;
	}

	@Override
	public List<String> getListCodeBarreByRefMiseAndIdBSisNull(String refMise) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

	    CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
	    
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    
	    Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
	   	    
	    
	    // Set referenceMise on whereClause if equal to refMise & != Null
	    if(refMise != null)
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REF_MISE), refMise));
		
	    // Set bonSortieIid on whereClause if null
		whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE)));
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List < RouleauFiniEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<String> listCodeBarreFromMise = new ArrayList<String>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	    	listCodeBarreFromMise.add(entity.getReference());
	    }
	    
	    return listCodeBarreFromMise;
	}

	@Override
	public List<RouleauFiniValue> rechercherMultiCritereReporting(
			RechercheMulticritereLogistiqueReportingtValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

	    CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
	    
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    
	    Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
	    
	 // Set request.dateFactureMin on whereClause if not null
	    if (request.getDateMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_INTRODUCTION), request.getDateMin()));
	    }
	    
		// Set request.dateFactureMax on whereClause if not null
	    if (request.getDateMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_INTRODUCTION), request.getDateMax()));
	    }
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List < RouleauFiniEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<RouleauFiniValue> listRouleau = new ArrayList<RouleauFiniValue>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	      RouleauFiniValue value = persistanceUtilities.toValue(entity);
	      listRouleau.add(value);
	    }
	    
	    return listRouleau;
	}

	@Override
	public List<RouleauFiniValue> getAll() {

		List<RouleauFiniEntity> listEntity = this.lister(RouleauFiniEntity.class);
		
		List<RouleauFiniValue> listDTO = new ArrayList<RouleauFiniValue>();
		
		for(RouleauFiniEntity entity : listEntity){
			
			listDTO.add(persistanceUtilities.toValue(entity));
		}
		
		return listDTO;

	}
	
	// Added 18/12/2016 By Ghazi Atroussi
	@Override
	public List<RouleauFiniValue> getRouleauFiniInventaireListByBarreCodeList(List<String> barreCodeList, Long id) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

	    CriteriaQuery<RouleauFiniEntity> criteriaQuery = criteriaBuilder.createQuery(RouleauFiniEntity.class);
	    
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    
	    Root<RouleauFiniEntity> root = criteriaQuery.from(RouleauFiniEntity.class);
	    
	    /** Ajout des criteres de recherche par BarreCode*/
	    
	    // BarreCode is the reference on the db
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(barreCodeList));
	    
		// Set bonSortieIid on whereClause if not null
		if (id != null) {
			
	    	Predicate predicate1 = criteriaBuilder.isNull(root.get(PREDICATE_BON_INVENTAIRE));
	    	Predicate predicate2 = criteriaBuilder.equal(root.get(PREDICATE_BON_INVENTAIRE), id);
		      
	    	whereClause.add(criteriaBuilder.or(predicate1, predicate2));
		}else{
			
		    // Retrive only RouleauFiniEntity list, that haven't BON_INVENTAIRE
		    whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_INVENTAIRE)));
		}
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List < RouleauFiniEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<RouleauFiniValue> listRouleau = new ArrayList<RouleauFiniValue>();
	    
	    for (RouleauFiniEntity entity : resultatEntite) {
	      RouleauFiniValue value = persistanceUtilities.toValue(entity);
	      listRouleau.add(value);
	    }
	    
	    return listRouleau;
	    
	}

	@Override
	public Double getQteExpedierByMiseRef(String refMise) {
		String requete ="select SUM(r.metrage) from RouleauFiniEntity r where r.referenceMise =:referenceMise and r.bonSortie is not null)";
		 
		Double result =  (Double) entityManager.createQuery(requete)
				    .setParameter("referenceMise", refMise).getSingleResult();
		 
		 if(result != null)
		      return result;
		 
		 
		 return new Double(0) ;
	}

	@Override
	public Long getNbrColisExpedierByMiseRef(String refMise) {
		String requete ="select count(*) from RouleauFiniEntity r where r.referenceMise =:referenceMise and r.bonSortie is not null)";
		 
		Long result =  (Long) entityManager.createQuery(requete)
				    .setParameter("referenceMise", refMise).getSingleResult();
		 
		 if(result != null)
		      return result;
		 
		 
		 return new Long(0) ;
	}
	
	


}
