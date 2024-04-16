package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ElementControleValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ElementRecetteMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseElementValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.TraitementMiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.ControleEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.ElementControleEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.ElementRecetteMiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.FicheSuiveuseEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */

@Component
public class FicheSuiveusePersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveusePersistanceUtilities.class);

	public FicheSuiveuseValue toValue(FicheSuiveuseEntity entity) {
		
		FicheSuiveuseValue dto = new FicheSuiveuseValue();
		
		dto.setId(entity.getId());
		dto.setConducteur(entity.getConducteur());
		dto.setCuisinier(entity.getCuisinier());
		dto.setDate(entity.getDate());
		dto.setDateLancement(entity.getDateLancement());
		dto.setDateLivraison(entity.getDateLivraison());
		dto.setObservations(entity.getObservations());
		dto.setPartieIntId(entity.getPartieIntId());
		dto.setPoids(entity.getPoids());
		dto.setProduitId(entity.getProduitId());
		dto.setReferenceMise(entity.getReferenceMise());
		dto.setTypeLivraison(entity.getTypeLivraison());
		dto.setVolume(entity.getVolume());
		dto.setLaizeDemFini(entity.getLaizeDemFini());
		dto.setLaizeSortie(entity.getLaizeSortie());
		dto.setPoidsSortie(entity.getPoidsSortie());
		dto.setRapportBain(entity.getRapportBain());
		dto.setMetrageSortie(entity.getMetrageSortie());
		dto.setMarcheId(entity.getMarcheId());
		dto.setPoidsPES(entity.getPoidsPES());
		dto.setPoidsCoton(entity.getPoidsCoton());
		dto.setVolumePES(entity.getVolumePES());
		dto.setVolumeCoton(entity.getVolumeCoton());
		dto.setSoustractionEffectuee(entity.isSoustractionEffectuee());
		
		if(entity.getListElementControle() != null){
	    	Set<ElementControleValue> list = new HashSet <ElementControleValue>();
		     for (ElementControleEntity elementControleEntity : entity.getListElementControle()) {
		    	 ElementControleValue elementControleValue = toValue(elementControleEntity);
		    	 list.add(elementControleValue);
		    }
		     dto.setListElementControle(list);
		}
		
		if(entity.getListeTraitementsMise()!= null){
	    	List<TraitementMiseValue> list = new ArrayList <TraitementMiseValue>();
	    	for (TraitementMiseEntity traitementMiseEntity : entity.getListeTraitementsMise()) {
		    	 TraitementMiseValue traitementMiseValue = toValue(traitementMiseEntity);
		    	 list.add(traitementMiseValue);
		    }
	    	
	    	Collections.sort(list);
	    	dto.setListeTraitementsMise(list);
		}
		
		return dto;
	}

	public TraitementMiseValue toValue(TraitementMiseEntity entity) {
		
		TraitementMiseValue dto = new TraitementMiseValue();
		
		dto.setId(entity.getId());
		dto.setMachineId(entity.getMachineId());
		dto.setObservations(entity.getObservations());
		dto.setTraitementId(entity.getTraitementId());
		dto.setVersion(entity.getVersion());
		dto.setOrder(entity.getOrder());
		dto.setType(entity.getType());
		dto.setPu(entity.getPu());
		
		if(entity.getFicheSuiveuse() != null){
			dto.setFicheSuiveuseId(entity.getFicheSuiveuse().getId());
		}
		
		if(entity.getListElementRecetteMise() != null){
	    	Set<ElementRecetteMiseValue> list = new HashSet <ElementRecetteMiseValue>();
		     for (ElementRecetteMiseEntity elementRecetteMiseEntity : entity.getListElementRecetteMise()) {
		    	 ElementRecetteMiseValue elementRecetteMiseValue = toValue(elementRecetteMiseEntity);
		    	 list.add(elementRecetteMiseValue);
		    }
		     dto.setListElementRecetteMise(list);
		}
		
		return dto;
	}


	public ElementRecetteMiseValue toValue(ElementRecetteMiseEntity entity) {
		
		ElementRecetteMiseValue dto = new ElementRecetteMiseValue();
		
		dto.setId(entity.getId());
		dto.setArticleId(entity.getArticleId());
		dto.setObservations(entity.getObservations());
		dto.setPoids(entity.getPoids());
		dto.setPourcentage(entity.getPourcentage());
		dto.setType(entity.getType());
		dto.setRajout(entity.getRajout());
		dto.setTemps(entity.getTemps());
		dto.setTemperature(entity.getTemperature());
		dto.setMethode(entity.getMethode());
		dto.setOrder(entity.getOrder());
		dto.setPh(entity.getPh());
		
		if(entity.getTraitementMise() != null){
			dto.setTraitementMiseId(entity.getTraitementMise().getId());
		}
		
		return dto;
	}


	public ElementControleValue toValue(ElementControleEntity entity) {
		
		ElementControleValue dto = new ElementControleValue();
		
		dto.setId(entity.getId());
		dto.setMethode(entity.getMethode());
		dto.setObservations(entity.getObservations());
		dto.setValeur(entity.getValeur());
		dto.setValeurCorrige(entity.getValeurCorrige());
		dto.setValeurTheorique(entity.getValeurTheorique());
		dto.setControleId(entity.getControleId());
		dto.setTemps(entity.getTemps());
		dto.setType(entity.getType());
		
		if(entity.getFicheSuiveuse() != null){
			dto.setFicheSuiveuseId(entity.getFicheSuiveuse().getId());
		}
		
		return dto;
	}


	public ResultatRechecheFicheSuiveuseElementValue toResultatRechecheFicheSuiveuseElementValue(FicheSuiveuseEntity entity) {
		
		ResultatRechecheFicheSuiveuseElementValue dto = new ResultatRechecheFicheSuiveuseElementValue();
		dto.setId(entity.getId());
		dto.setPartieIntId(entity.getPartieIntId());
		dto.setDateLancement(entity.getDateLancement());
		dto.setDateLivraison(entity.getDateLivraison());
		dto.setLaizeFini(entity.getLaizeDemFini());
		dto.setReferenceMise(entity.getReferenceMise());
		dto.setPoidSortie(entity.getPoids());
		dto.setProduitId(entity.getProduitId());
		dto.setTypeLivraison(entity.getTypeLivraison());
		
		return dto;
	}


	public FicheSuiveuseEntity toEntity(FicheSuiveuseValue dto) {
		
		FicheSuiveuseEntity entity = new FicheSuiveuseEntity();
		
		entity.setId(dto.getId());
		entity.setConducteur(dto.getConducteur());
		entity.setCuisinier(dto.getCuisinier());
		entity.setDate(dto.getDate());
		entity.setDateLancement(dto.getDateLancement());
		entity.setDateLivraison(dto.getDateLivraison());
		entity.setObservations(dto.getObservations());
		entity.setPartieIntId(dto.getPartieIntId());
		entity.setPoids(dto.getPoids());
		entity.setProduitId(dto.getProduitId());
		entity.setReferenceMise(dto.getReferenceMise());
		entity.setTypeLivraison(dto.getTypeLivraison());
		entity.setVolume(dto.getVolume());
		entity.setLaizeDemFini(dto.getLaizeDemFini());
		entity.setLaizeSortie(dto.getLaizeSortie());
		entity.setPoidsSortie(dto.getPoidsSortie());
		entity.setRapportBain(dto.getRapportBain());
		entity.setMetrageSortie(dto.getMetrageSortie());
		entity.setMarcheId(dto.getMarcheId());
		entity.setPoidsPES(dto.getPoidsPES());
		entity.setPoidsCoton(dto.getPoidsCoton());
		entity.setVolumePES(dto.getVolumePES());
		entity.setVolumeCoton(dto.getVolumeCoton());
		entity.setSoustractionEffectuee(dto.isSoustractionEffectuee());
		
	    if(dto.getListElementControle() != null){
		     Set<ElementControleEntity> list = new HashSet <ElementControleEntity>();
		     for (ElementControleValue elementControleValue : dto.getListElementControle()) {
		    	 ElementControleEntity elementControleEntity = toEntity(elementControleValue);
		    	 elementControleEntity.setFicheSuiveuse(entity);
		    	 list.add(elementControleEntity);
		    }
		     entity.setListElementControle(list);
		}
	    
	    if(dto.getListeTraitementsMise() != null){
		     Set<TraitementMiseEntity> list = new HashSet <TraitementMiseEntity>();
		     for (TraitementMiseValue traitementMiseValue : dto.getListeTraitementsMise()) {
		    	 TraitementMiseEntity traitementMiseEntity = toEntity(traitementMiseValue);
		    	 traitementMiseEntity.setFicheSuiveuse(entity);
		    	 list.add(traitementMiseEntity);
		    }
		     entity.setListeTraitementsMise(list);
		}
		
		return entity;
	}

	public TraitementMiseEntity toEntity(TraitementMiseValue dto) {

		TraitementMiseEntity entity = new TraitementMiseEntity();
		
		entity.setId(dto.getId());
		entity.setMachineId(dto.getMachineId());
		entity.setObservations(dto.getObservations());
		entity.setTraitementId(dto.getTraitementId());
		entity.setVersion(dto.getVersion());
		entity.setOrder(dto.getOrder());
		entity.setType(dto.getType());
		entity.setPu(dto.getPu());
		if(dto.getFicheSuiveuseId() != null){
			FicheSuiveuseEntity ficheSuiveuseEntity = new FicheSuiveuseEntity();
			ficheSuiveuseEntity.setId(dto.getFicheSuiveuseId());
			entity.setFicheSuiveuse(ficheSuiveuseEntity);
		}
		
	    if(dto.getListElementRecetteMise() != null){
		     Set<ElementRecetteMiseEntity> list = new HashSet <ElementRecetteMiseEntity>();
		     for (ElementRecetteMiseValue elementRecetteMiseValue : dto.getListElementRecetteMise()) {
		    	 ElementRecetteMiseEntity elementRecetteMiseEntity = toEntity(elementRecetteMiseValue);
		    	 elementRecetteMiseEntity.setTraitementMise(entity);
		    	 list.add(elementRecetteMiseEntity);
		    }
		     entity.setListElementRecetteMise(list);
		}
		
		return entity;
	}

	public ElementRecetteMiseEntity toEntity(ElementRecetteMiseValue dto) {
		
		ElementRecetteMiseEntity entity = new ElementRecetteMiseEntity();
		
		entity.setId(dto.getId());
		entity.setArticleId(dto.getArticleId());
		entity.setObservations(dto.getObservations());
		entity.setPoids(dto.getPoids());
		entity.setPourcentage(dto.getPourcentage());
		entity.setType(dto.getType());
		entity.setRajout(dto.getRajout());
		entity.setTemps(dto.getTemps());
		entity.setTemperature(dto.getTemperature());
		entity.setMethode(dto.getMethode());
		entity.setOrder(dto.getOrder());
		entity.setPh(dto.getPh());
		
		if(dto.getTraitementMiseId() != null){
		TraitementMiseEntity traitementMiseEntity = new TraitementMiseEntity();
		traitementMiseEntity.setId(dto.getTraitementMiseId());
		entity.setTraitementMise(traitementMiseEntity);
	}
		
		return entity;
	}

	public ElementControleEntity toEntity(ElementControleValue dto) {

		ElementControleEntity entity = new ElementControleEntity();
		
		entity.setId(dto.getId());
		entity.setMethode(dto.getMethode());
		entity.setObservations(dto.getObservations());
		entity.setValeur(dto.getValeur());
		entity.setValeurCorrige(dto.getValeurCorrige());
		entity.setValeurTheorique(dto.getValeurTheorique());
		entity.setControleId(dto.getControleId());
		entity.setTemps(dto.getTemps());
		entity.setType(dto.getType());
		
		if(dto.getFicheSuiveuseId() != null){
			FicheSuiveuseEntity ficheSuiveuseEntity = new FicheSuiveuseEntity();
			ficheSuiveuseEntity.setId(dto.getFicheSuiveuseId());
			entity.setFicheSuiveuse(ficheSuiveuseEntity);
		}
		
		return entity;
	}
	
	public ControleEntity toEntity(ControleValue dto) {

		ControleEntity entity = new ControleEntity();
		
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		
		return entity;
	}
	
	public ControleValue toValue(ControleEntity entity) {
		
		ControleValue dto = new ControleValue();
		
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		
		return dto;
	}

	public List<ControleValue> listToValue(List<ControleEntity> listEntity) {
		
		List<ControleValue> list = new ArrayList<ControleValue>();
		
		for(ControleEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

}
