package com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.entitie.ComptoirVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.entitie.DetComptoirVenteEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonComptoirPersistanceUtilities {
	
	@Autowired
	IProduitPersistance produitPersistance;
	
	private static final Logger logger = LoggerFactory.getLogger(BonComptoirPersistanceUtilities.class);
	

	

	
	public ComptoirVenteValue toValue(ComptoirVenteEntity entity) {
		
		ComptoirVenteValue dto = new ComptoirVenteValue();
		
		if(entity.getId()!=null){
			dto.setId(entity.getId());
		}
		
		dto.setReference(entity.getReference());
		dto.setRefexterne(entity.getRefexterne());
		dto.setDate(entity.getDate());
		dto.setMontantHTaxe(entity.getMontantHTaxe());
		dto.setMontantTTC(entity.getMontantTTC());
		dto.setMontantTaxe(entity.getMontantTaxe());
		dto.setMontantRemise(entity.getMontantRemise());
		dto.setObservations(entity.getObservations());
		dto.setInfoSortie(entity.getInfoSortie());
		dto.setPartieIntId(entity.getPartieIntId());
		//dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setEtat(entity.getEtat());
		dto.setTransporteur(entity.getTransporteur());
		dto.setMetrageTotal(entity.getMetrageTotal());
		dto.setNatureLivraison(entity.getNatureLivraison().trim());
		dto.setTotalPourcentageRemise(entity.getTotalPourcentageRemise());
		dto.setStock(entity.getStock());
		dto.setIdDepot(entity.getIdDepot());
		dto.setRefCommande(entity.getRefCommande());
		dto.setIdCamion(entity.getIdCamion());
		dto.setIdChauffeur(entity.getIdChauffeur());
	    dto.setIdRemorque(entity.getIdRemorque());
	    dto.setDateClotue(entity.getDateClotue());
		
		if(entity.getListDetComptoirVente() != null){
	    	List<DetComptoirVenteValue> list = new ArrayList <DetComptoirVenteValue>();
		     for (DetComptoirVenteEntity detComptoirVenteEntity : entity.getListDetComptoirVente()) {
		    	 DetComptoirVenteValue detComptoirVenteValue = toValue(detComptoirVenteEntity);
		    	 list.add(detComptoirVenteValue);
		    }
		     dto.setListDetComptoirVente(list);
		}
	    

	    
	    dto.setModifier(entity.isModifier());
	    dto.setModepaiementId(entity.getModepaiementId());
	    dto.setMarcheId(entity.getMarcheId());
	    dto.setCamion(entity.getCamion());
	    dto.setChauffeur(entity.getChauffeur());
	    dto.setRemorque(entity.getRemorque());
	   
	  
		
		return dto;
	}

	public ComptoirVenteEntity toEntity(ComptoirVenteValue dto) {
		
		ComptoirVenteEntity entity = new ComptoirVenteEntity();
		
		entity.setId(dto.getId());
		entity.setReference(dto.getReference());
		entity.setRefexterne(dto.getRefexterne());
		entity.setDate(dto.getDate());
		entity.setMontantHTaxe(dto.getMontantHTaxe());
		entity.setMontantTTC(dto.getMontantTTC());
		entity.setMontantTaxe(dto.getMontantTaxe());
		entity.setMontantRemise(dto.getMontantRemise());
		entity.setObservations(dto.getObservations());
		entity.setInfoSortie(dto.getInfoSortie());
		entity.setPartieIntId(dto.getPartieIntId());
		//entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setEtat(dto.getEtat());
		entity.setTransporteur(dto.getTransporteur());
		entity.setMetrageTotal(dto.getMetrageTotal());
		entity.setNatureLivraison(dto.getNatureLivraison().trim());
		entity.setTotalPourcentageRemise(dto.getTotalPourcentageRemise());
		entity.setStock(dto.getStock());
		entity.setIdDepot(dto.getIdDepot());
		entity.setRefCommande(dto.getRefCommande());
		entity.setIdCamion(dto.getIdCamion());
		entity.setIdChauffeur(dto.getIdChauffeur());
		entity.setIdRemorque(dto.getIdRemorque());
		entity.setCamion(dto.getCamion());
		entity.setRemorque(dto.getRemorque());
		entity.setChauffeur(dto.getChauffeur());
		
		entity.setDateClotue(dto.getDateClotue());
		
	    if(dto.getListDetComptoirVente() != null){
		     Set<DetComptoirVenteEntity> list = new HashSet <DetComptoirVenteEntity>();
		     for (DetComptoirVenteValue detComptoirVenteValue : dto.getListDetComptoirVente()) {
		    	 DetComptoirVenteEntity detComptoirVenteEntity = toEntity(detComptoirVenteValue);
		    	 detComptoirVenteEntity.setComptoirVente(entity);
		    	 list.add(detComptoirVenteEntity);
		    }
		     entity.setListDetComptoirVente(list);
		}
	    
	  
	    
	    entity.setModifier(dto.isModifier());
	    entity.setModepaiementId(dto.getModepaiementId());
	    entity.setMarcheId(dto.getMarcheId());
		
		return entity;
	}

	public DetComptoirVenteEntity toEntity(DetComptoirVenteValue dto) {
		
		DetComptoirVenteEntity entity = new DetComptoirVenteEntity();
		
		entity.setId(dto.getId());
		entity.setProduitId(dto.getProduitId());
		entity.setQuantite(dto.getQuantite());
		entity.setUnite(dto.getUnite());
		entity.setNombreColis(dto.getNombreColis());
		entity.setRemise(dto.getRemise());
		entity.setChoix(dto.getChoix());
		entity.setTraitementFaconId(dto.getTraitementFaconId());
		entity.setPrixUnitaireHT(dto.getPrixUnitaireHT());
		entity.setPrixTotalHT(dto.getPrixTotalHT());
		entity.setFicheId(dto.getFicheId());
		entity.setUniteSupplementaire(dto.getUniteSupplementaire());
		entity.setQuantiteConversion(dto.getQuantiteConversion());
		entity.setConversion(dto.getConversion());
		entity.setPrixUnitaireHTConversion(dto.getPrixUnitaireHTConversion());
		entity.setTauxTva(dto.getTauxTVA());
		entity.setPrixTTC(dto.getPrixTTC());
		entity.setSerialisable(dto.isSerialisable());
		entity.setNumeroSeries(dto.getNumeroSeries());
		
		if(dto.getComptoirVenteId() != null){
			ComptoirVenteEntity livraisonVenteEntity = new ComptoirVenteEntity();
			livraisonVenteEntity.setId(dto.getComptoirVenteId());
			entity.setComptoirVente(livraisonVenteEntity);
		}
		
		return entity;
	}

	public DetComptoirVenteValue toValue(DetComptoirVenteEntity entity) {
		
		DetComptoirVenteValue dto = new DetComptoirVenteValue();
		
		dto.setId(entity.getId());
		dto.setProduitId(entity.getProduitId());
		dto.setQuantite(entity.getQuantite());
		dto.setUnite(entity.getUnite());
		dto.setNombreColis(entity.getNombreColis());
		dto.setRemise(entity.getRemise());
		dto.setChoix(entity.getChoix());
		dto.setTraitementFaconId(entity.getTraitementFaconId());
		dto.setPrixUnitaireHT(entity.getPrixUnitaireHT());
		dto.setPrixTotalHT(entity.getPrixTotalHT());
		dto.setFicheId(entity.getFicheId());
		dto.setUniteSupplementaire(entity.getUniteSupplementaire());
		dto.setQuantiteConversion(entity.getQuantiteConversion());
		dto.setPrixUnitaireHTConversion(entity.getPrixUnitaireHTConversion());
		dto.setConversion(entity.getConversion());
		dto.setTauxTVA(entity.getTauxTva());
		dto.setPrixTTC(entity.getPrixTTC());
		dto.setSerialisable(entity.isSerialisable());
		dto.setNumeroSeries(entity.getNumeroSeries());
		
		if (entity.getProduitId()!=null) {
			ProduitValue produitValue = produitPersistance.rechercheProduitById(entity.getProduitId());
			dto.setProduitDesignation(produitValue.getDesignation());
			dto.setProduitReference(produitValue.getReference());
		}
		
		if(entity.getComptoirVente() != null){
			dto.setComptoirVenteId(entity.getComptoirVente().getId());
		}
		
		return dto;
	}





	public List<ComptoirVenteValue> listComptoirVenteEntitytoValue(List<ComptoirVenteEntity> listEntity) {
		
		List<ComptoirVenteValue> list = new ArrayList<ComptoirVenteValue>();
		
		for(ComptoirVenteEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	



}
