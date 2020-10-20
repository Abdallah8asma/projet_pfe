package com.gpro.consulting.tiers.logistique.persistance.gl.facon.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity.FicheFaconEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity.TraitementFaconEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.entity.TraitementFicheEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */

@Component
public class FaconPersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(FaconPersistanceUtilities.class);
	
	/************************************ Fiche façon ****************************************/

	public static FicheFaconValue toValue(FicheFaconEntity entity) {
		
		FicheFaconValue value = new FicheFaconValue();
		
		value.setId(entity.getId());
		value.setProduitId(entity.getProduitId());
		value.setPartieIntId(entity.getPartieIntId());
		value.setObservations(entity.getObservations());
		value.setMethodeTeinture(entity.getMethodeTeinture());
		value.setRefBonReception(entity.getRefBonReception());
		value.setPoidsEcru(entity.getPoidsEcru());
		value.setPoidsFini(entity.getPoidsFini());
		
		
		if(entity.getListeTraitementsFiche()!= null){
	    	List<TraitementFicheValue> list = new ArrayList <TraitementFicheValue>();
	    	for (TraitementFicheEntity traitementFicheEntity : entity.getListeTraitementsFiche()) {
		    	 TraitementFicheValue traitementFicheValue = toValue(traitementFicheEntity);
		    	 list.add(traitementFicheValue);
		    }
	    	
	    	Collections.sort(list);
	    	value.setListeTraitementsFiche(list);
		}
		
		return value;
	}



	public static FicheFaconEntity toEntity(FicheFaconValue value) {
		
		FicheFaconEntity entity = new FicheFaconEntity();
		
		entity.setId(value.getId());
		entity.setProduitId(value.getProduitId());
		entity.setPartieIntId(value.getPartieIntId());
		entity.setObservations(value.getObservations());
		entity.setMethodeTeinture(value.getMethodeTeinture());
		entity.setRefBonReception(value.getRefBonReception());
		entity.setPoidsEcru(value.getPoidsEcru());
		entity.setPoidsFini(value.getPoidsFini());
		
		if(value.getListeTraitementsFiche()!= null){
	    	Set<TraitementFicheEntity> list = new HashSet <TraitementFicheEntity>();
	    	for (TraitementFicheValue traitementFicheValue : value.getListeTraitementsFiche()) {
	    		TraitementFicheEntity traitementFicheEntity = toEntity(traitementFicheValue);
		    	traitementFicheEntity.setFicheFacon(entity); 
	    		list.add(traitementFicheEntity);
		    }
	    	
	    	entity.setListeTraitementsFiche(list);
		}
		
		return entity;
	}
	
	/************************* Résultat recherche fiche façon *************************/
	
		public static ResultatRechecheFicheFaconElementValue toResultatRechecheFicheFaconElementValue(FicheFaconEntity entity) {
		
			ResultatRechecheFicheFaconElementValue value = new ResultatRechecheFicheFaconElementValue();
			
			value.setId(entity.getId());
			value.setProduitId(entity.getProduitId());
			value.setPartieIntId(entity.getPartieIntId());
			value.setObservations(entity.getObservations());
			value.setMethodeTeinture(entity.getMethodeTeinture());
			value.setRefBonReception(entity.getRefBonReception());
			
			
			if(entity.getListeTraitementsFiche()!= null){
		    	List<TraitementFicheValue> list = new ArrayList <TraitementFicheValue>();
		    	for (TraitementFicheEntity traitementFicheEntity : entity.getListeTraitementsFiche()) {
			    	 TraitementFicheValue traitementFicheValue = toValue(traitementFicheEntity);
			    	 list.add(traitementFicheValue);
			    }
		    	
		    	Collections.sort(list);
		    	value.setListeTraitementsFiche(list);
			}
			
			return value;
	}
	
	
	/*************************************** Traitement fiche *********************************/
	
	public static TraitementFicheValue toValue(TraitementFicheEntity entity) {
		
		TraitementFicheValue value = new TraitementFicheValue();
		
		value.setId(entity.getId());
		value.setPu(entity.getPu());
		value.setTraitementId(entity.getTraitementId());
		if(entity.getFicheFacon() != null){
			value.setFicheFaconId(entity.getFicheFacon().getId());
		}
		
		return value;
	}



	public static TraitementFicheEntity toEntity(TraitementFicheValue value) {
		
		TraitementFicheEntity entity = new TraitementFicheEntity();
		
		entity.setId(value.getId());
		entity.setPu(value.getPu());
		entity.setTraitementId(value.getTraitementId());
		
		/*if(value.getFicheFaconId() != null){
			FicheFaconEntity ficheFaconEntity = new FicheFaconEntity();
			ficheFaconEntity.setId(value.getFicheFaconId());
			entity.setFicheFacon(ficheFaconEntity);
		}*/
		
		return entity;
	}
	
	public static TraitementFicheEntity toEntity(TraitementFicheValue value, FicheFaconEntity ficheFaconEntity) {
		
		TraitementFicheEntity entity = new TraitementFicheEntity();
		
		entity.setId(value.getId());
		entity.setPu(value.getPu());
		entity.setTraitementId(value.getTraitementId());
		entity.setFicheFacon(ficheFaconEntity);
		
		return entity;
	}
	
	
/*************************************** Traitement facon *********************************/
	
	public static TraitementFaconValue toValue(TraitementFaconEntity entity) {
		
		TraitementFaconValue value = new TraitementFaconValue();
		
		value.setId(entity.getId());
		value.setDesignation(entity.getDesignation());
		value.setPu(entity.getPu());
		
		return value;
	}

	public static TraitementFaconEntity toEntity(TraitementFaconValue value) {
		
		TraitementFaconEntity entity = new TraitementFaconEntity();
		
		entity.setId(value.getId());
		entity.setPu(value.getPu());
		entity.setDesignation(value.getDesignation());

		return entity;
	}

	
	
}
