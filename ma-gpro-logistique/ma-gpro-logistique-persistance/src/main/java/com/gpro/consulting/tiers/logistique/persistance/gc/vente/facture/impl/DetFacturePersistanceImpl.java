package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.DetFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DetFactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.FactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.utilities.FacturePersistanceUtilities;


/**
 * Implementation of {@link IBonFacturePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class DetFacturePersistanceImpl extends AbstractPersistance implements IDetFactureVentePersistance{
	
	private String PREDICATE_NUMERO_OF = "numeroOF";
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_FACTURE_VENTE = "factureVente";
	private String PREDICATE_CHOIX = "choix";
	private String PREDICATE_REfernce ="reference";
	private String PREDICATE_ID_CLIENT ="partieIntId";
	private String PREDICATE_DATE_INTRO = "dateIntroduction";	
	private String PREDICATE_DATE= "date";
	private String PREDICATE_DATE_ECHEANCE = "dateEcheance";
	private String PREDICATE_INFO_LIVRAISON = "infoLivraison";
	private String PREDICATE_refCommande = "refCommande";
	private String PREDICATE_QUNATITE="quantite";
	private String PREDICATE_nombreColis="nombreColis";
	private String  PREDICATE_prixTotalHT="prixTotalHT";
	private String PREDICATE_prixUnitaireHT="prixUnitaireHT";


	@PersistenceContext
	private EntityManager entityManager;
	private int MAX_RESULTS =52;
	
	@Autowired
	private FacturePersistanceUtilities facturePersistanceUtilities;
	
	@Override
	public String create(DetFactureVenteValue detFactureVenteValue) {
		
		DetFactureVenteEntity entity = (DetFactureVenteEntity) this.creer(facturePersistanceUtilities.toEntity(detFactureVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public DetFactureVenteValue getById(Long id) {
		
		DetFactureVenteEntity detFactureVenteEntity = this.rechercherParId(id, DetFactureVenteEntity.class);

	    return facturePersistanceUtilities.toValue(detFactureVenteEntity);
	}

	@Override
	public String update(DetFactureVenteValue detFactureVenteValue) {
		
		DetFactureVenteEntity entity = (DetFactureVenteEntity) this.modifier(facturePersistanceUtilities.toEntity(detFactureVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetFactureVenteEntity.class, id);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public DetFactureVenteValue getByFactureVenteAndProduit(
			Long factureVenteId, Long produitId, String choix) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetFactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetFactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetFactureVenteEntity> root = criteriaQuery.from(DetFactureVenteEntity.class);

		// Set FactureVenteId on whereClause if not empty or null
		if (factureVenteId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FACTURE_VENTE), factureVenteId));
		}
		
		// Set produitId on whereClause if not null
		if (produitId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), produitId));
		}
		
		// Set choix on whereClause if not empty or null
		if (estNonVide(choix)) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHOIX), choix));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetFactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    if(resultatEntite.size() >0){
	    	return facturePersistanceUtilities.toValue(resultatEntite.get(0));
	    }
	    else return null;

	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	@Override
	public DetFactureVenteValue getByFactureVenteAndNumeroOF(Long factureVenteId, String numeroOF, String choix) {

		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetFactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetFactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetFactureVenteEntity> root = criteriaQuery.from(DetFactureVenteEntity.class);

		// Set FactureVenteId on whereClause if not empty or null
		if (factureVenteId != null &&  estNonVide(numeroOF) &&  estNonVide(choix)) {
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FACTURE_VENTE), factureVenteId));
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NUMERO_OF), numeroOF));
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHOIX), choix));
			
			
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <DetFactureVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		    if(resultatEntite.size() >0){
		    	return facturePersistanceUtilities.toValue(resultatEntite.get(0));
		    }
			
			
		}
		
	

	
	     return null;
	}

	@Override
	public ResultatRechecheDetFactureVenteValue rechercherMultiCritere(
			RechercheMulticritereDetFactureVenteValue request) {
	// TODO Auto-generated method stub
		

		//logger.info("rechercherMultiCritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<DetFactureVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetFactureVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<DetFactureVenteEntity> root = criteriaQuery.from(DetFactureVenteEntity.class);
		if(estNonVide(request.getReferenceFacture()))
		{
			Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");
			whereClause.add(criteriaBuilder.equal( jointFactureVente.get(PREDICATE_REfernce), request.getReferenceFacture()));

	    }
		if(request.getPartieIntId()!=null)
		{
			Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");
			whereClause.add(criteriaBuilder.equal(jointFactureVente.get( PREDICATE_ID_CLIENT), request.getPartieIntId()));
		}
		if (request.getDateEcheanceDe() != null) {
			Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");

			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointFactureVente.<Calendar> get(PREDICATE_DATE_ECHEANCE),
					request.getDateEcheanceDe()));
		}

		if (request.getDateEcheanceA() != null) {
			Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");

			whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointFactureVente.<Calendar> get(PREDICATE_DATE_ECHEANCE),
					request.getDateEcheanceA()));
		}
		 if (request.getDateFactureMin() != null) 
		 {
				Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");

		    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointFactureVente.<Calendar>get(PREDICATE_DATE), request.getDateFactureMin()));
		    }
		    
			// Set request.dateFactureMax on whereClause if not null
		    if (request.getDateFactureMax() != null) {
				Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");

		    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointFactureVente.<Calendar>get(PREDICATE_DATE), request.getDateFactureMax()));
		    }
		    if (request.getDateIntroductionDe() != null) 
		    {
				Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");

				whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointFactureVente.<Calendar> get(PREDICATE_DATE_INTRO),
						request.getDateIntroductionDe()));
			}

			if (request.getDateIntroductionA() != null) {
				Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");
				whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointFactureVente.<Calendar> get(PREDICATE_DATE_INTRO),
						request.getDateIntroductionA()));
			}
		if(estNonVide(request.getReferenceBl()))
			
		{
			Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");

			whereClause.add(criteriaBuilder.equal( jointFactureVente.get(PREDICATE_INFO_LIVRAISON), request.getReferenceBl()));

		}
		if(estNonVide(request.getRefCommande()))
		{
			Join<DetFactureVenteEntity,FactureVenteEntity> jointFactureVente = root.join("factureVente");

			whereClause.add(criteriaBuilder.equal( jointFactureVente.get(PREDICATE_refCommande), request.getRefCommande()));
			
		}
		if(request.getQuantiteDe()!=null)
		{
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_QUNATITE), request.getQuantiteDe()));

		}
		if(request.getQuantiteA()!=null)
		{
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_QUNATITE), request.getQuantiteA()));

		}
		if(request.getNombreColis()!=null)
				{
			whereClause.add(criteriaBuilder.equal( root.get(PREDICATE_nombreColis), request.getNombreColis()));

				}
		if(request.getPrixTotalHT()!=null)
		{
			whereClause.add(criteriaBuilder.equal( root.get(PREDICATE_prixTotalHT), request.getPrixTotalHT()));

		}
			if(request.getPrixMin()!=null)
			{
				whereClause.add(criteriaBuilder.greaterThanOrEqualTo( root.<Double>get(PREDICATE_prixUnitaireHT), request.getPrixMin()));

			}
			if(request.getPrixMax()!=null)
			{
				whereClause.add(criteriaBuilder.lessThanOrEqualTo( root.<Double>get(PREDICATE_prixUnitaireHT), request.getPrixMax()));

			}
			if(request.getProduitId()!=null)
			{
				whereClause.add(criteriaBuilder.equal( root.get(PREDICATE_PRODUIT), request.getProduitId()));

			}
		
			 criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
				
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
				
				
		        List<DetFactureVenteEntity> resultatEntite = null;
				
				if(request.isOptimized())
				{
					resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(MAX_RESULTS).getResultList();
				}
				
				else
					
				{
					resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
				}

				
				
				List<DetFactureVenteValue> list = new ArrayList<DetFactureVenteValue>();

				for (DetFactureVenteEntity entity : resultatEntite) {
					DetFactureVenteValue dto =facturePersistanceUtilities.toValueEnrichi(entity);
					list.add(dto);
				}
				
				

				ResultatRechecheDetFactureVenteValue result = new ResultatRechecheDetFactureVenteValue();
				result.setNombreResultaRechercher(Long.valueOf(list.size()));
				Collections.sort(list);
				result.setList(list);
			
				
				
			


				return result;
	
	
}
}