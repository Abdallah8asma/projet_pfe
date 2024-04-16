package com.gpro.consulting.tiers.logistique.persistance.gs.transfert.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.DetBonTransfertValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.transfert.entite.BonTransfertEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.transfert.entite.DetBonTransfertEntity;

/**
 * The Class PersistanceTransfertUtilities.
 * 
 * @author samer
 */

@Component
public class PersistanceTransfertUtilities {

	/*****************Bon Transfert ************************/
	public static BonTransfertValue toValue(BonTransfertEntity pEntiteTransfertEntite) {
		BonTransfertValue entiteTransfert = new BonTransfertValue();
		entiteTransfert.setId(pEntiteTransfertEntite.getId());

		 
		entiteTransfert.setReference(pEntiteTransfertEntite.getReference());
	
		entiteTransfert.setDate(pEntiteTransfertEntite.getDate());
		entiteTransfert.setObservations(pEntiteTransfertEntite.getObservations());
		entiteTransfert.setMetrageTotal(pEntiteTransfertEntite.getMetrageTotal());
		entiteTransfert.setVersion(pEntiteTransfertEntite.getVersion());

		entiteTransfert.setType(pEntiteTransfertEntite.getType());
		entiteTransfert.setBoutiqueId(pEntiteTransfertEntite.getBoutiqueId());
		
		
		entiteTransfert.setReferenceSortie(pEntiteTransfertEntite.getReferenceSortie());
		entiteTransfert.setReferenceReception(pEntiteTransfertEntite.getReferenceReception());
		entiteTransfert.setStatus(pEntiteTransfertEntite.getStatus());
		entiteTransfert.setStatusAuto(pEntiteTransfertEntite.getStatusAuto());
		entiteTransfert.setIdDepotDestination(pEntiteTransfertEntite.getIdDepotDestination());
		entiteTransfert.setIdDepotOrigine(pEntiteTransfertEntite.getIdDepotOrigine());
		 
		if(pEntiteTransfertEntite.getListDetBonTransfert()!= null){
	    	List<DetBonTransfertValue> list = new ArrayList <DetBonTransfertValue>();
		     for (DetBonTransfertEntity detBonTransfertEntity : pEntiteTransfertEntite.getListDetBonTransfert()) {
		    	 DetBonTransfertValue detBonTransfertValue = toValue(detBonTransfertEntity);
		    	  
		    	 list.add(detBonTransfertValue);
		    }
		     entiteTransfert.setListDetBonTransfert(list);
		}
		
		
		return entiteTransfert;
	}
	
	public static BonTransfertEntity toEntity(BonTransfertValue pTransfertValue) {
		BonTransfertEntity pTransfertEntity = new BonTransfertEntity();
		pTransfertEntity.setId(pTransfertValue.getId());

		 
		pTransfertEntity.setReference(pTransfertValue.getReference());
		pTransfertEntity.setDate(pTransfertValue.getDate());
		pTransfertEntity.setObservations(pTransfertValue.getObservations());
		pTransfertEntity.setMetrageTotal(pTransfertValue.getMetrageTotal());
		pTransfertEntity.setVersion(pTransfertValue.getVersion());
		pTransfertEntity.setType(pTransfertValue.getType());
		pTransfertEntity.setBoutiqueId(pTransfertValue.getBoutiqueId());
		
		pTransfertEntity.setReferenceSortie(pTransfertValue.getReferenceSortie());
		pTransfertEntity.setReferenceReception(pTransfertValue.getReferenceReception());
		pTransfertEntity.setStatus(pTransfertValue.getStatus());
		pTransfertEntity.setStatusAuto(pTransfertValue.getStatusAuto());
		pTransfertEntity.setIdDepotDestination(pTransfertValue.getIdDepotDestination());
		pTransfertEntity.setIdDepotOrigine(pTransfertValue.getIdDepotOrigine());
		
		if(pTransfertValue.getListDetBonTransfert()!= null){
	    	Set<DetBonTransfertEntity> list = new HashSet<DetBonTransfertEntity>();
		     for (DetBonTransfertValue detBonTransfertValue : pTransfertValue.getListDetBonTransfert()) {
		    	 DetBonTransfertEntity detBonTransfertEntity = toEntity(detBonTransfertValue);
		    	 detBonTransfertEntity.setBonTransfert(pTransfertEntity);
		    	 list.add(detBonTransfertEntity);
		    }
		     pTransfertEntity.setListDetBonTransfert(list);
		}

		
		return pTransfertEntity;
	}
	
	
	public static DetBonTransfertValue toValue(DetBonTransfertEntity entity) {
		
		DetBonTransfertValue dto = new DetBonTransfertValue();
		
		dto.setId(entity.getId());
		dto.setProduitId(entity.getProduitId());
		dto.setQuantite(entity.getQuantite());
		dto.setSerialisable(entity.isSerialisable());
		dto.setNumeroSeries(entity.getNumeroSeries());
		dto.setDescription(entity.getDescription());
		
		
		dto.setQuantiteEnvoyer(entity.getQuantiteEnvoyer());
		dto.setQuantiteRecu(entity.getQuantiteRecu());
		
		
		if(entity.getBonTransfert() != null){
			
			BonTransfertValue bonTransfertValue = new BonTransfertValue();
			bonTransfertValue.setId(entity.getBonTransfert().getId());
			 
			dto.setBonTransfert(bonTransfertValue);
			
			
		}
		
		
		
		
		
		return dto;
	}
	
	public static DetBonTransfertEntity toEntity(DetBonTransfertValue value) {
		
		DetBonTransfertEntity entity = new DetBonTransfertEntity();
		
		entity.setId(value.getId());
		entity.setProduitId(value.getProduitId());
		entity.setQuantite(value.getQuantite());
		entity.setSerialisable(value.isSerialisable());
		entity.setNumeroSeries(value.getNumeroSeries());
		entity.setDescription(value.getDescription());
		
		entity.setQuantiteEnvoyer(value.getQuantiteEnvoyer());
		entity.setQuantiteRecu(value.getQuantiteRecu());
		
		if(value.getBonTransfert() != null){
			
			BonTransfertEntity bonTransfertEntity = new BonTransfertEntity();
			bonTransfertEntity.setId(value.getBonTransfert().getId());
			 
			entity.setBonTransfert(bonTransfertEntity);
		}
		
		
		
		
		
		return entity;
	}

}
