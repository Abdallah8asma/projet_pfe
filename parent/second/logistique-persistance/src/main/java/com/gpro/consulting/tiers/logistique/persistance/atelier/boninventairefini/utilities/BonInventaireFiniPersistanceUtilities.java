package com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.entity.BonInventaireFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.EntrepotEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.RouleauFiniEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Ghazi Atroussi
 * @since 18/12/2016
 *
 */
@Component
public class BonInventaireFiniPersistanceUtilities {

	
	public BonInventaireFiniEntity toEntity(BonInventaireFiniValue request) {
		
		BonInventaireFiniEntity BonInventaireFiniEntity = new BonInventaireFiniEntity();
		
		if(request.getId() != null){
			BonInventaireFiniEntity.setId(request.getId());
		}
		BonInventaireFiniEntity.setDateSortie(request.getDateSortie());
		BonInventaireFiniEntity.setMetrageTotal(request.getMetrageTotal());
		BonInventaireFiniEntity.setNbColis(request.getNbColis());
		BonInventaireFiniEntity.setObservation(request.getObservation());
		BonInventaireFiniEntity.setReference(request.getReference());
		
		
	    if(request.getListeRouleauFini()!= null){
	    	
		     Set <RouleauFiniEntity> listeRouleauFini = new HashSet<RouleauFiniEntity>();
		     
		     for (RouleauFiniValue rouleauFiniValue : request.getListeRouleauFini()) {
		    	 RouleauFiniEntity rouleauFiniEntity = toEntity(rouleauFiniValue, BonInventaireFiniEntity);
		    	 listeRouleauFini.add(rouleauFiniEntity);
		    }
		     
		     BonInventaireFiniEntity.setListeRouleauFini(listeRouleauFini);
		}
		
		return BonInventaireFiniEntity;
	}

	private RouleauFiniEntity toEntity(RouleauFiniValue rouleauFiniValue,
			BonInventaireFiniEntity BonInventaireFiniEntity) {
		RouleauFiniEntity rouleauFiniEntity = new RouleauFiniEntity();
		
		if(rouleauFiniValue.getId() != null){
			rouleauFiniEntity.setId(rouleauFiniValue.getId());
		}
	    rouleauFiniEntity.setBlSuppression(rouleauFiniValue.isBlSuppression());
	    rouleauFiniEntity.setChoix(rouleauFiniValue.getChoix());
	    rouleauFiniEntity.setCodeBarre(rouleauFiniValue.getCodeBarre());
	    rouleauFiniEntity.setDateCreation(rouleauFiniValue.getDateCreation());
	    rouleauFiniEntity.setDateModification(rouleauFiniValue.getDateModification());
	    rouleauFiniEntity.setDateSuppression(rouleauFiniValue.getDateSuppression());
	    rouleauFiniEntity.setEmplacement(rouleauFiniValue.getEmplacement());
	    rouleauFiniEntity.setMetrage(rouleauFiniValue.getMetrage());
	    rouleauFiniEntity.setPoids(rouleauFiniValue.getPoids());
	    rouleauFiniEntity.setReference(rouleauFiniValue.getReference());
	    rouleauFiniEntity.setReferenceMise(rouleauFiniValue.getReferenceMise());
	    rouleauFiniEntity.setVersion(rouleauFiniValue.getVersion());
	    rouleauFiniEntity.setLaize(rouleauFiniValue.getLaize());
	    
	    if(rouleauFiniValue.getEntrepot() != null){
		    EntrepotEntity entrepotEntity = new EntrepotEntity();
		    entrepotEntity.setId(rouleauFiniValue.getEntrepot());
		    rouleauFiniEntity.setEntrepot(entrepotEntity);
	    }
	    
	    rouleauFiniEntity.setProduitId(rouleauFiniValue.getProduitId());
	    rouleauFiniEntity.setPartieInteresseeId(rouleauFiniValue.getPartieInteresseeId());
	    
	    //enrichissement
	    rouleauFiniEntity.setBonInventaire(BonInventaireFiniEntity);
	    
	    rouleauFiniEntity.setDateSortie(rouleauFiniValue.getDateSortie());
	    rouleauFiniEntity.setDateIntroduction(rouleauFiniValue.getDateIntroduction());
	    rouleauFiniEntity.setMetrageModif(rouleauFiniValue.isMetrageModif());
	    rouleauFiniEntity.setInfoModif(rouleauFiniValue.getInfoModif());
	    rouleauFiniEntity.setFini(rouleauFiniValue.isFini());
	    rouleauFiniEntity.setInfo(rouleauFiniValue.getInfo());
	    
	    
		return rouleauFiniEntity;
	}

	public BonInventaireFiniValue toValue(BonInventaireFiniEntity BonInventaireFiniEntity) {
		
		BonInventaireFiniValue BonInventaireFiniValue = new BonInventaireFiniValue();
		
		BonInventaireFiniValue.setId(BonInventaireFiniEntity.getId());
		BonInventaireFiniValue.setDateSortie(BonInventaireFiniEntity.getDateSortie());
		BonInventaireFiniValue.setMetrageTotal(BonInventaireFiniEntity.getMetrageTotal());
		BonInventaireFiniValue.setNbColis(BonInventaireFiniEntity.getNbColis());
		BonInventaireFiniValue.setObservation(BonInventaireFiniEntity.getObservation());
		BonInventaireFiniValue.setReference(BonInventaireFiniEntity.getReference());
		
	    if(BonInventaireFiniEntity.getListeRouleauFini()!= null){
	    	
		     List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();
		     
		     for (RouleauFiniEntity rouleauFiniEntity : BonInventaireFiniEntity.getListeRouleauFini()) {
		    	 RouleauFiniValue rouleauFiniValue = toValue(rouleauFiniEntity);
		    	 listeRouleauFini.add(rouleauFiniValue);
		    }
		     
		     BonInventaireFiniValue.setListeRouleauFini(listeRouleauFini);
		}
		
		return BonInventaireFiniValue;
	}

	private RouleauFiniValue toValue(RouleauFiniEntity entity) {
		
		RouleauFiniValue dto = new RouleauFiniValue();
		
		dto.setId(entity.getId());
		dto.setChoix(entity.getChoix());
		dto.setCodeBarre(entity.getCodeBarre());
		dto.setEmplacement(entity.getEmplacement());
		dto.setMetrage(entity.getMetrage());
		dto.setPartieInteresseeId(entity.getPartieInteresseeId());
		dto.setPoids(entity.getPoids());
		dto.setProduitId(entity.getProduitId());
		dto.setReference(entity.getReference());
		dto.setReferenceMise(entity.getReferenceMise());
		dto.setLaize(entity.getLaize());
		
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateIntroduction(entity.getDateIntroduction());
		dto.setDateModification(entity.getDateModification());
		dto.setDateSortie(entity.getDateSortie());
		dto.setDateSuppression(entity.getDateSuppression());
		
		dto.setMetrageModif(entity.getMetrageModif());
		dto.setInfoModif(entity.getInfoModif());
		
		dto.setFini(entity.isFini());
		dto.setInfo(entity.getInfo());
		
		if(entity.getBonSortie() != null){
			dto.setBonSortie(entity.getBonSortie().getId());
		}
		
		if(entity.getEntrepot() != null){
			dto.setEntrepot(entity.getEntrepot().getId());
			dto.setEntrepotDesignation(entity.getEntrepot().getDesignation());
		}
		
		return dto;
	}

	public List<BonInventaireFiniValue> toValue(List<BonInventaireFiniEntity> listEntity) {
		List<BonInventaireFiniValue> list = new ArrayList<BonInventaireFiniValue>();
		
		for(BonInventaireFiniEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
}
