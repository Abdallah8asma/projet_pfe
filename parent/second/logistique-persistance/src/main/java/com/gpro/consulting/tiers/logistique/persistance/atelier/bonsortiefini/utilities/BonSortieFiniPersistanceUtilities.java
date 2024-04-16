package com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.entity.BonSortieFiniEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.EntrepotEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity.RouleauFiniEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Component
public class BonSortieFiniPersistanceUtilities {

	
	public BonSortieFiniEntity toEntity(BonSortieFiniValue request) {
		
		BonSortieFiniEntity bonSortieFiniEntity = new BonSortieFiniEntity();
		
		if(request.getId() != null){
			bonSortieFiniEntity.setId(request.getId());
		}
		bonSortieFiniEntity.setDateSortie(request.getDateSortie());
		bonSortieFiniEntity.setMetrageTotal(request.getMetrageTotal());
		bonSortieFiniEntity.setNbColis(request.getNbColis());
		bonSortieFiniEntity.setObservation(request.getObservation());
		bonSortieFiniEntity.setPartieInt(request.getPartieInt());
		bonSortieFiniEntity.setReference(request.getReference());
		bonSortieFiniEntity.setType(request.getType());
		bonSortieFiniEntity.setPoidsEcru(request.getPoidsEcru());
		bonSortieFiniEntity.setPoidsFini(request.getPoidsFini());
		
	    if(request.getListeRouleauFini()!= null){
	    	
		     Set <RouleauFiniEntity> listeRouleauFini = new HashSet<RouleauFiniEntity>();
		     
		     for (RouleauFiniValue rouleauFiniValue : request.getListeRouleauFini()) {
		    	 RouleauFiniEntity rouleauFiniEntity = toEntity(rouleauFiniValue, bonSortieFiniEntity);
		    	 listeRouleauFini.add(rouleauFiniEntity);
		    }
		     
		     bonSortieFiniEntity.setListeRouleauFini(listeRouleauFini);
		}
		
		return bonSortieFiniEntity;
	}

	private RouleauFiniEntity toEntity(RouleauFiniValue rouleauFiniValue,
			BonSortieFiniEntity bonSortieFiniEntity) {
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
	    rouleauFiniEntity.setBonSortie(bonSortieFiniEntity);
	    
	    rouleauFiniEntity.setDateSortie(rouleauFiniValue.getDateSortie());
	    rouleauFiniEntity.setDateIntroduction(rouleauFiniValue.getDateIntroduction());
	    rouleauFiniEntity.setMetrageModif(rouleauFiniValue.isMetrageModif());
	    rouleauFiniEntity.setInfoModif(rouleauFiniValue.getInfoModif());
	    rouleauFiniEntity.setFini(rouleauFiniValue.isFini());
	    rouleauFiniEntity.setInfo(rouleauFiniValue.getInfo());
	    
	    
		return rouleauFiniEntity;
	}

	public BonSortieFiniValue toValue(BonSortieFiniEntity bonSortieFiniEntity) {
		
		BonSortieFiniValue bonSortieFiniValue = new BonSortieFiniValue();
		
		bonSortieFiniValue.setId(bonSortieFiniEntity.getId());
		bonSortieFiniValue.setDateSortie(bonSortieFiniEntity.getDateSortie());
		bonSortieFiniValue.setMetrageTotal(bonSortieFiniEntity.getMetrageTotal());
		bonSortieFiniValue.setNbColis(bonSortieFiniEntity.getNbColis());
		bonSortieFiniValue.setObservation(bonSortieFiniEntity.getObservation());
		bonSortieFiniValue.setPartieInt(bonSortieFiniEntity.getPartieInt());
		bonSortieFiniValue.setReference(bonSortieFiniEntity.getReference());
		bonSortieFiniValue.setType(bonSortieFiniEntity.getType());
		bonSortieFiniValue.setPoidsEcru(bonSortieFiniEntity.getPoidsEcru());
		bonSortieFiniValue.setPoidsFini(bonSortieFiniEntity.getPoidsFini());
		
		
	    if(bonSortieFiniEntity.getListeRouleauFini()!= null){
	    	
		     List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();
		     
		     for (RouleauFiniEntity rouleauFiniEntity : bonSortieFiniEntity.getListeRouleauFini()) {
		    	 RouleauFiniValue rouleauFiniValue = toValue(rouleauFiniEntity);
		    	 listeRouleauFini.add(rouleauFiniValue);
		    }
		     
		     bonSortieFiniValue.setListeRouleauFini(listeRouleauFini);
		}
		
		return bonSortieFiniValue;
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

	public List<BonSortieFiniValue> toValue(List<BonSortieFiniEntity> listEntity) {
		List<BonSortieFiniValue> list = new ArrayList<BonSortieFiniValue>();
		
		for(BonSortieFiniEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
	
	
	public boolean checkForOptimization(
			RechercheMulticritereBonSortieFiniValue request) {

		return isNullOrEmpty(request.getDateSortieMax())
				&& isNullOrEmpty(request.getDateSortieMin())
				&& isNullOrEmpty(request.getPartieInt())
				&& isNullOrEmpty(request.getReference())
	
				
			
				
		
			;

	}
	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
}
