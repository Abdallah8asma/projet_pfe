package com.gpro.consulting.tiers.logistique.persistance.gc.reception.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.entitie.ProduitReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.entitie.ReceptionVenteEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Hajer
 * @since 19/03/2017
 *
 */

@Component
public class ReceptionPersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(ReceptionPersistanceUtilities.class);
	
	
	/************************** toEntityCommandeVente **********************************/
	
	public static ReceptionVenteEntity toEntity(ReceptionVenteValue value) {
		
		ReceptionVenteEntity entity = new ReceptionVenteEntity();
		
		if(value.getId() != null){
			entity.setId(value.getId());
		}
		
		entity.setSiteId(value.getSiteId());
		entity.setReference(value.getReference());
		
		entity.setRefexterne(value.getRefexterne());
		entity.setRefexterne(value.getRefexterne());
		entity.setPrixTotal(value.getPrixTotal());
		entity.setDateIntroduction(value.getDateIntroduction());
		entity.setDateLivraison(value.getDateLivraison());
		entity.setObservations(value.getObservations());
		entity.setPartieIntersseId(value.getPartieIntersseId());
		entity.setBlSuppression(value.isBlSuppression());
		entity.setDateSuppression(value.getDateSuppression());
		entity.setDateModification(value.getDateModification());
		entity.setDateCreation(value.getDateCreation());
		entity.setQuantite(value.getQuantite());
		entity.setIdDepot(value.getIdDepot());
		
		if(value.getListProduitReceptions()!=null){
			
			List<ProduitReceptionEntity> listProduitReceptions = new ArrayList<ProduitReceptionEntity>();
			
			for (ProduitReceptionValue produitReceptionValue : value.getListProduitReceptions()) {
				
				ProduitReceptionEntity produitReceptionEntity = toEntity(produitReceptionValue);
				produitReceptionEntity.setReceptionVente(entity);
				listProduitReceptions.add(produitReceptionEntity);
			}
			
			entity.setListProduitReceptions(listProduitReceptions);
		}
		
		return entity;
	}
	
	/************************** toValueCommandeVente **********************************/
	
	public static ReceptionVenteValue toValue(ReceptionVenteEntity entity) {
		
		ReceptionVenteValue value = new ReceptionVenteValue();
		
		if(entity.getId()!=null){
			value.setId(entity.getId());
		}
		
		value.setSiteId(entity.getSiteId());
		value.setReference(entity.getReference());
		value.setRefexterne(entity.getRefexterne());
		value.setRefexterne(entity.getRefexterne());
		value.setPrixTotal(entity.getPrixTotal());
		value.setDateIntroduction(entity.getDateIntroduction());
		value.setDateLivraison(entity.getDateLivraison());
		value.setObservations(entity.getObservations());
		value.setPartieIntersseId(entity.getPartieIntersseId());
		value.setIdDepot(entity.getIdDepot());
		
		//logger.info("PI tovalue" + value.getPartieIntersseId());
		
		value.setBlSuppression(entity.isBlSuppression());
		value.setDateSuppression(entity.getDateSuppression());
		value.setDateModification(entity.getDateModification());
		value.setDateCreation(entity.getDateCreation());
		value.setQuantite(entity.getQuantite());
		
		if(entity.getListProduitReceptions()!=null){
			
		List<ProduitReceptionValue> listProduitReceptions = new ArrayList<ProduitReceptionValue>();
			
			for (ProduitReceptionEntity produitReceptionEntity : entity.getListProduitReceptions()) {
				
				ProduitReceptionValue produitReceptionValue = toValue(produitReceptionEntity);
				listProduitReceptions.add(produitReceptionValue);
			}
			
			value.setListProduitReceptions(listProduitReceptions);
		}
		
		//logger.info("PI tovalue" + value.getPartieIntersseId());
		
		return value;
	}

	
/************************** toEntityProduitCommande **********************************/
	
	public static ProduitReceptionEntity toEntity(ProduitReceptionValue value) {
		
		ProduitReceptionEntity entity = new ProduitReceptionEntity();
		
		entity.setId(value.getId());
		entity.setPrixUnitaire(value.getPrixUnitaire());
		entity.setQuantite(value.getQuantite());
		entity.setDateLivraison(value.getDateLivraison());
		entity.setProduit(value.getProduitId());
		
		return entity;
	}
	
	/************************** toValueProduitCommande **********************************/
	
	public static ProduitReceptionValue toValue(ProduitReceptionEntity entity) {
		
		ProduitReceptionValue value = new ProduitReceptionValue();
		
		if(entity.getId()!=null){
			value.setId(entity.getId());
		}
		
		value.setPrixUnitaire(entity.getPrixUnitaire());
		value.setQuantite(entity.getQuantite());
		value.setDateLivraison(entity.getDateLivraison());
		
		value.setCommandeVenteId(entity.getReceptionVente().getId());
		value.setProduitId(entity.getProduit());
		
		return value;
	}


	
}
