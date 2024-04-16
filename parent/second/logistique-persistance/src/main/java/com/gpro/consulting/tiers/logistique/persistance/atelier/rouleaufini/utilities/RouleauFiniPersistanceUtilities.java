package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.impl.ProduitPersistanceImpl;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.entity.BonInventaireFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.entity.BonSortieFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.ChoixRouleauEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.EntrepotEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.RouleauFiniEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */

@Component
public class RouleauFiniPersistanceUtilities {
	@Autowired
	private IProduitPersistance produitPersistance;
	
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RouleauFiniPersistanceUtilities.class);
	
	public EntrepotEntity toEntity(EntrepotValue dto) {

		EntrepotEntity entity = new EntrepotEntity();
		
		if(dto.getId()!=null){
			entity.setId(dto.getId());
		}
		entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDesignation(dto.getDesignation());
		entity.setPartintId(dto.getPartintId());
		entity.setVersion(dto.getVersion());
		
	    if(dto.getListRouleauFiniValue() != null){
		     Set<RouleauFiniEntity> list = new HashSet <RouleauFiniEntity>();
		     for (RouleauFiniValue rouleauFiniValue : dto.getListRouleauFiniValue()) {
		    	 RouleauFiniEntity rouleauFiniEntity = toEntity(rouleauFiniValue, entity);
		    	 list.add(rouleauFiniEntity);
		    }
		     entity.setRouleauFiniEntity(list);
		}
		
		return entity;
	}
	
	public RouleauFiniEntity toEntity(RouleauFiniValue value, EntrepotEntity pEntrepotEntity) {
		
		RouleauFiniEntity rouleauFiniEntity = new RouleauFiniEntity();
	    
		if (value.getId() != null) {
	    	rouleauFiniEntity.setId(value.getId());
	    }
	    rouleauFiniEntity.setBlSuppression(value.isBlSuppression());
	    rouleauFiniEntity.setChoix(value.getChoix());
	    rouleauFiniEntity.setCodeBarre(value.getCodeBarre());
	    rouleauFiniEntity.setDateCreation(value.getDateCreation());
	    rouleauFiniEntity.setDateModification(value.getDateModification());
	    rouleauFiniEntity.setDateSuppression(value.getDateSuppression());
	    rouleauFiniEntity.setEmplacement(value.getEmplacement());
	    rouleauFiniEntity.setEntrepot(pEntrepotEntity);
	    rouleauFiniEntity.setMetrage(value.getMetrage());
	    rouleauFiniEntity.setPartieInteresseeId(value.getPartieInteresseeId());
	    rouleauFiniEntity.setPoids(value.getPoids());
	    rouleauFiniEntity.setProduitId(value.getProduitId());
	    rouleauFiniEntity.setReference(value.getReference());
	    rouleauFiniEntity.setReferenceMise(value.getReferenceMise());
	    rouleauFiniEntity.setVersion(value.getVersion());
	    rouleauFiniEntity.setLaize(value.getLaize());
	    
		if(value.getBonSortie() != null){
			BonSortieFiniEntity bonSortieFiniEntity = new BonSortieFiniEntity();
			bonSortieFiniEntity.setId(value.getBonSortie());
			rouleauFiniEntity.setBonSortie(bonSortieFiniEntity);
		}
		rouleauFiniEntity.setDateSortie(value.getDateSortie());
		rouleauFiniEntity.setDateIntroduction(value.getDateIntroduction());
		rouleauFiniEntity.setMetrageModif(value.isMetrageModif());
		rouleauFiniEntity.setInfoModif(value.getInfoModif());
	    
	    rouleauFiniEntity.setFini(value.isFini());
	    
	    
	    rouleauFiniEntity.setUserId(value.getUserId());
	    rouleauFiniEntity.setTypeOf(value.getTypeOf());
	    rouleauFiniEntity.setResponsable(value.getResponsable());
		
	    rouleauFiniEntity.setDateSortieReel(value.getDateSortieReel());
		
	
		
	    rouleauFiniEntity.setUserIdExpedition(value.getUserIdExpedition());
	    rouleauFiniEntity.setResponsableExpedition(value.getResponsableExpedition());
	    rouleauFiniEntity.setPremierMetrage(value.getPremierMetrage());
	    
	    
	    rouleauFiniEntity.setNumberOfBox(value.getNumberOfBox());


	    return rouleauFiniEntity;
	}

	public EntrepotValue toValue(EntrepotEntity entity) {

		EntrepotValue dto = new EntrepotValue();
		
		dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDesignation(entity.getDesignation());
		dto.setId(entity.getId());
		dto.setPartintId(entity.getPartintId());
		dto.setVersion(entity.getVersion());
		
	    if (entity.getRouleauFiniEntity() != null) {
	    	
	    	List <RouleauFiniValue> listRouleauFiniValue = new ArrayList <RouleauFiniValue>();
	    	for (RouleauFiniEntity rouleauFiniEntite : entity.getRouleauFiniEntity()) {
	    		RouleauFiniValue rouleauFiniValue = toValue(rouleauFiniEntite);
	    		listRouleauFiniValue.add(rouleauFiniValue);
	    	}
	    	dto.setListRouleauFiniValue(listRouleauFiniValue);
	    }

		return dto;
	}
	
	
	public RouleauFiniValue toValue(RouleauFiniEntity entity) {
		
		RouleauFiniValue dto = new RouleauFiniValue();
		
		dto.setBlSuppression(entity.isBlSuppression());
		dto.setChoix(entity.getChoix());
		dto.setCodeBarre(entity.getCodeBarre());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setEmplacement(entity.getEmplacement());
		dto.setId(entity.getId());
		dto.setMetrage(entity.getMetrage());
		dto.setPartieInteresseeId(entity.getPartieInteresseeId());
		dto.setPoids(entity.getPoids());
		dto.setProduitId(entity.getProduitId());
		
		//reference Produit
		if(entity.getProduitId() != null){
			
			ProduitValue produitValue = produitPersistance.rechercheProduitById(entity.getProduitId());
			dto.setProduitReference(produitValue.getReference());
		}
		
		dto.setReference(entity.getReference());
		dto.setReferenceMise(entity.getReferenceMise());
		dto.setVersion(entity.getVersion());
		dto.setLaize(entity.getLaize());
		
		if(entity.getBonSortie() != null){
			dto.setBonSortie(entity.getBonSortie().getId());
		}
		
		dto.setDateSortie(entity.getDateSortie());
		dto.setDateIntroduction(entity.getDateIntroduction());
		dto.setMetrageModif(entity.getMetrageModif());
		dto.setInfoModif(entity.getInfoModif());
		
		dto.setFini(entity.isFini());
		
		
		dto.setMetrageAncien(entity.getMetrage());
		
		if(entity.getEntrepot() != null){
			dto.setEntrepot((entity.getEntrepot().getId()));
		}
		
		
		
		
		
		dto.setUserId(entity.getUserId());
		dto.setTypeOf(entity.getTypeOf());
		dto.setResponsable(entity.getResponsable());
		
		dto.setDateSortieReel(entity.getDateSortieReel());
		
	
		
		dto.setUserIdExpedition(entity.getUserIdExpedition());
		dto.setResponsableExpedition(entity.getResponsableExpedition());
		dto.setPremierMetrage(entity.getPremierMetrage());
		
		dto.setNumberOfBox(entity.getNumberOfBox());
		
		
		return dto;
	}
	
	public RouleauFiniEntity toEntity(RouleauFiniValue dto, BonSortieFiniEntity bonSortiFiniEntity) {

		RouleauFiniEntity entity = new RouleauFiniEntity();
		
		if(dto.getId() != null){
			entity.setId(dto.getId());
		}
		entity.setBlSuppression(dto.isBlSuppression());
		entity.setChoix(dto.getChoix());
		entity.setCodeBarre(dto.getCodeBarre());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setEmplacement(dto.getEmplacement());
		entity.setMetrage(dto.getMetrage());
		entity.setPartieInteresseeId(dto.getPartieInteresseeId());
		entity.setPoids(dto.getPoids());
		entity.setProduitId(dto.getProduitId());
		entity.setReference(dto.getReference());
		entity.setReferenceMise(dto.getReferenceMise());
		entity.setVersion(dto.getVersion());
		entity.setLaize(dto.getLaize());
		
		entity.setFini(dto.isFini());
		
		if(bonSortiFiniEntity != null){
			entity.setBonSortie(bonSortiFiniEntity);
		}
		entity.setDateSortie(dto.getDateSortie());
		entity.setDateIntroduction(dto.getDateIntroduction());
		entity.setMetrageModif(dto.isMetrageModif());
		entity.setInfoModif(dto.getInfoModif());
		
		
		entity.setUserId(dto.getUserId());
		entity.setTypeOf(dto.getTypeOf());
		entity.setResponsable(dto.getResponsable());
		
		entity.setDateSortieReel(dto.getDateSortieReel());
		
		entity.setUserIdExpedition(dto.getUserIdExpedition());
		entity.setResponsableExpedition(dto.getResponsableExpedition());
		entity.setPremierMetrage(dto.getPremierMetrage());
		
		entity.setNumberOfBox(dto.getNumberOfBox());
		
		return entity;
	}
	
	public RouleauFiniEntity toEntity(RouleauFiniValue dto) {

		RouleauFiniEntity entity = new RouleauFiniEntity();
		
		if(dto.getId() != null){
			entity.setId(dto.getId());
		}
		entity.setBlSuppression(dto.isBlSuppression());
		entity.setChoix(dto.getChoix());
		entity.setCodeBarre(dto.getCodeBarre());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setEmplacement(dto.getEmplacement());
		entity.setMetrage(dto.getMetrage());
		entity.setPartieInteresseeId(dto.getPartieInteresseeId());
		entity.setPoids(dto.getPoids());
		entity.setProduitId(dto.getProduitId());
		entity.setReference(dto.getReference());
		entity.setReferenceMise(dto.getReferenceMise());
		entity.setVersion(dto.getVersion());
		entity.setLaize(dto.getLaize());
		
		if(dto.getBonSortie() != null){
			BonSortieFiniEntity bonSortieFiniEntity = new BonSortieFiniEntity();
			bonSortieFiniEntity.setId(dto.getBonSortie());
			entity.setBonSortie(bonSortieFiniEntity);
		}
		entity.setDateSortie(dto.getDateSortie());
		entity.setDateIntroduction(dto.getDateIntroduction());
		entity.setMetrageModif(dto.isMetrageModif());
		entity.setInfoModif(dto.getInfoModif());
		
		entity.setFini(dto.isFini());
		
		if(dto.getBonSortie() != null){
			BonSortieFiniEntity bonSortieFiniEntity = new BonSortieFiniEntity();
			bonSortieFiniEntity.setId(dto.getBonSortie());
			entity.setBonSortie(bonSortieFiniEntity);
		}
		
		if(dto.getEntrepot() != null){
			EntrepotEntity entrepotEntity = new EntrepotEntity();
			entrepotEntity.setId(dto.getEntrepot());
			entity.setEntrepot(entrepotEntity);
		}
		if(dto.getBonInventaire() != null){
			BonInventaireFiniEntity bonInventaireFiniEntity = new BonInventaireFiniEntity();
			bonInventaireFiniEntity.setId(dto.getBonInventaire());
			entity.setBonInventaire(bonInventaireFiniEntity);
		}
		entity.setDateInventaire(dto.getDateInventaire());
		entity.setDateSortie(dto.getDateSortie());
		entity.setDateIntroduction(dto.getDateIntroduction());
		entity.setMetrageModif(dto.isMetrageModif());
		entity.setInfoModif(dto.getInfoModif());
		
		
		
		entity.setUserId(dto.getUserId());
		entity.setTypeOf(dto.getTypeOf());
		entity.setResponsable(dto.getResponsable());
		
		entity.setDateSortieReel(dto.getDateSortieReel());
		
		entity.setUserIdExpedition(dto.getUserIdExpedition());
		entity.setResponsableExpedition(dto.getResponsableExpedition());
		entity.setPremierMetrage(dto.getPremierMetrage());
	
		
		entity.setNumberOfBox(dto.getNumberOfBox());

		
		return entity;
	}

	public ChoixRouleauValue toValue(ChoixRouleauEntity entity) {
		ChoixRouleauValue dto = new ChoixRouleauValue();
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		
		return dto;
	}
	
	
	public boolean checkForOptimization(
			RechercheMulticritereRouleauFiniValue request) {

		return isNullOrEmpty(request.getIds())
				&& isNullOrEmpty(request.getRefMise())
				&& isNullOrEmpty(request.getPartieInteresseeId())
				&& isNullOrEmpty(request.getProduitId())
				&& isNullOrEmpty(request.getReference())
		
			;

	}
	
	public boolean checkForOptimization(
			RechercheMulticritereRouleauFiniStandardValue request) {

		return isNullOrEmpty(request.getDateProductionA())
				&& isNullOrEmpty(request.getDateProductionDe())
				&& isNullOrEmpty(request.getPartieInteresseeId())
				&& isNullOrEmpty(request.getProduitId())
				&& isNullOrEmpty(request.getReference())
				
			
				
		
			;

	}
	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
}
