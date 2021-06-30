package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DocumentReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DocumentReglementValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.DetailsReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.DocumentReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.ElementReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.ReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.TypeReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DetailsReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DocumentReglementEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

public class ReglementAchatPersistanceUtilities {
	
	private ReglementAchatPersistanceUtilities(){
		
	}

	public static ReglementAchatEntity toEntity(ReglementAchatValue dto) {
		
		ReglementAchatEntity entity = new ReglementAchatEntity();
		
		entity.setId(dto.getId());
		entity.setPartieIntId(dto.getPartieIntId());
		entity.setReference(dto.getReference());
		entity.setDate(dto.getDate());
		entity.setMontantTotal(dto.getMontantTotal());
		entity.setObservations(dto.getObservations());
		entity.setIdDepot(dto.getIdDepot());
		entity.setGroupeClientId(dto.getGroupeClientId());
		entity.setBoutiqueId(dto.getBoutiqueId());
		
		entity.setDeclarer(dto.isDeclarer());
		
		
	    if(dto.getListDetailsReglement() != null){
		     Set<DetailsReglementAchatEntity> list = new HashSet <DetailsReglementAchatEntity>();
		     for (DetailsReglementAchatValue detailsReglementAchatValue : dto.getListDetailsReglement()) {
		    	 DetailsReglementAchatEntity detailsReglementAchatEntity = toEntity(detailsReglementAchatValue);
		    	 detailsReglementAchatEntity.setReglement(entity);
		    	 list.add(detailsReglementAchatEntity);
		    }
		     entity.setListDetailsReglement(list);
		}
	    
	    if(dto.getListElementReglement() != null){
		     Set<ElementReglementAchatEntity> list = new HashSet <ElementReglementAchatEntity>();
		     for (ElementReglementAchatValue elementReglementAchatValue : dto.getListElementReglement()) {
		    	 ElementReglementAchatEntity elementReglementAchatEntity = toEntity(elementReglementAchatValue);
		    	 elementReglementAchatEntity.setReglement(entity);
		    	 list.add(elementReglementAchatEntity);
		    }
		     entity.setListElementReglement(list);
		}
	    
	    
		/*** Liste Document reglement  */
		if (dto.getListDocReglement() != null) {
			Set<DocumentReglementAchatEntity> vListeDocuments = new HashSet<DocumentReglementAchatEntity>();
			for (DocumentReglementAchatValue vDocumentValue : dto.getListDocReglement())
				 {
				DocumentReglementAchatEntity vDe = toEntity(vDocumentValue);
				vDe.setReglement(entity);
				vListeDocuments.add(vDe);
			}
			entity.setDocumentReglement(vListeDocuments);
		}
		
		return entity;
	}

	public static ReglementAchatValue toValue(ReglementAchatEntity entity) {
		
		ReglementAchatValue dto = new ReglementAchatValue();
		
		dto.setId(entity.getId());
		dto.setPartieIntId(entity.getPartieIntId());
		dto.setReference(entity.getReference());
		dto.setDate(entity.getDate());
		dto.setMontantTotal(entity.getMontantTotal());
		dto.setObservations(entity.getObservations());
		dto.setIdDepot(entity.getIdDepot());		
		dto.setGroupeClientId(entity.getGroupeClientId());
		dto.setBoutiqueId(entity.getBoutiqueId());
		
		dto.setDeclarer(entity.isDeclarer());
		
	    if(entity.getListDetailsReglement() != null){
	    	List<DetailsReglementAchatValue> list = new ArrayList <DetailsReglementAchatValue>();
		     for (DetailsReglementAchatEntity detailsReglementAchatEntity : entity.getListDetailsReglement()) {
		    	 DetailsReglementAchatValue detailsReglementAchatValue = toValue(detailsReglementAchatEntity);
		    	 list.add(detailsReglementAchatValue);
		    }
		     
		     Collections.sort(list);
		     dto.setListDetailsReglement(new TreeSet<>(list));
		}
	    
	    if(entity.getListElementReglement() != null){
	    	List<ElementReglementAchatValue> list = new ArrayList <ElementReglementAchatValue>();
		     for (ElementReglementAchatEntity detailsReglementAchatEntity : entity.getListElementReglement()) {
		    	 ElementReglementAchatValue elementReglementAchatValue = toValue(detailsReglementAchatEntity);
		    	 list.add(elementReglementAchatValue);
		    }
		     
		     Collections.sort(list);
		     dto.setListElementReglement(new TreeSet<>(list));
		}
	    
	    
	    
		/*** Liste Document reglement  */
		if (entity.getDocumentReglement()!= null) {
			Set<DocumentReglementAchatValue> vListeDocuments = new HashSet<DocumentReglementAchatValue>();
			for (DocumentReglementAchatEntity vDocumentEntity : entity
					.getDocumentReglement()) {
				DocumentReglementAchatValue vDe = toValue(vDocumentEntity);
				vListeDocuments.add(vDe);
			}
			dto.setListDocReglement(vListeDocuments);
		}
	    
		return dto;
	}

	public static List<ReglementAchatValue> listToValue(List<ReglementAchatEntity> listEntity) {

		List<ReglementAchatValue> list = new ArrayList<ReglementAchatValue>();
		
		for(ReglementAchatEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
	
	public static DetailsReglementAchatEntity toEntity(DetailsReglementAchatValue dto) {
		
		DetailsReglementAchatEntity entity = new DetailsReglementAchatEntity();
		
		entity.setId(dto.getId());
		entity.setNumPiece(dto.getNumPiece());
		entity.setBanque(dto.getBanque());
		entity.setMontant(dto.getMontant());
		entity.setDateEmission(dto.getDateEmission());
		entity.setDateEcheance(dto.getDateEcheance());
		entity.setRefFacture(dto.getRefFacture());
		entity.setRegle(dto.getRegle());
		entity.setObservation(dto.getObservation());
		entity.setReference(dto.getReference());
		
		entity.setDateDepotBanque(dto.getDateDepotBanque());
		entity.setChargeBanque(dto.getChargeBanque());
		entity.setTvaBanque(dto.getTvaBanque());
		
		entity.setBanqueSociete(dto.getBanqueSociete());
		
		if(dto.getTypeReglementId() != null){
			TypeReglementAchatEntity typeReglement = new TypeReglementAchatEntity();
			typeReglement.setId(dto.getTypeReglementId());
			entity.setTypeReglement(typeReglement);
		}
		
		if(dto.getReglementId() != null){
			ReglementAchatEntity reglement = new ReglementAchatEntity();
			reglement.setId(dto.getReglementId());
			entity.setReglement(reglement);
		}
		
		
		return entity;
		
	}
	
	public static ElementReglementAchatEntity toEntity(ElementReglementAchatValue dto) {
		
		ElementReglementAchatEntity entity = new ElementReglementAchatEntity();
		
		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setRefFacture(dto.getRefFacture());
		entity.setRefBL(dto.getRefBL());
		entity.setMontantDemande(dto.getMontantDemande());
		entity.setDateEcheance(dto.getDateEcheance());
		
		if(dto.getReglementId() != null){
			ReglementAchatEntity reglement = new ReglementAchatEntity();
			reglement.setId(dto.getReglementId());
			entity.setReglement(reglement);
		}
		
		return entity;
	}
	
	public static TypeReglementAchatEntity toEntity(TypeReglementAchatValue dto) {
		
		TypeReglementAchatEntity entity = new TypeReglementAchatEntity();
		
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		entity.setaTerme(dto.getaTerme());
		entity.setPrefixe(dto.getPrefixe());
		
		entity.setRegle(dto.getRegle());
		
		
		return entity;
	}
	
	public static DetailsReglementAchatValue toValue(DetailsReglementAchatEntity entity) {
		
		DetailsReglementAchatValue dto = new DetailsReglementAchatValue();
		
		dto.setId(entity.getId());

		dto.setNumPiece(entity.getNumPiece());
		dto.setBanque(entity.getBanque());
		dto.setMontant(entity.getMontant());
		dto.setDateEmission(entity.getDateEmission());
		dto.setDateEcheance(entity.getDateEcheance());
		dto.setRefFacture(entity.getRefFacture());
		dto.setRegle(entity.getRegle());
		dto.setObservation(entity.getObservation());
		
		dto.setReference(entity.getReference());
		
		
		
		dto.setDateDepotBanque(entity.getDateDepotBanque());
		dto.setChargeBanque(entity.getChargeBanque());
		dto.setTvaBanque(entity.getTvaBanque());
		
		dto.setBanqueSociete(entity.getBanqueSociete());
		
		if(entity.getReglement() != null){
			dto.setReglementId(entity.getReglement().getId());
		}
		
		if(entity.getTypeReglement() != null){
			dto.setTypeReglementId(entity.getTypeReglement().getId());
		}
		
		return dto;
		
	}
	
	public static ResultatRechecheDetailReglementAchatElementValue toDetailResultatValue(
			DetailsReglementAchatEntity entity) {
		
		ResultatRechecheDetailReglementAchatElementValue dto = new ResultatRechecheDetailReglementAchatElementValue();
		dto.setId(entity.getId());
		dto.setNumPiece(entity.getNumPiece());
		dto.setBanque(entity.getBanque());
		dto.setMontant(entity.getMontant());
		dto.setDateEmission(entity.getDateEmission());
		dto.setDateEcheance(entity.getDateEcheance());
		dto.setRefFacture(entity.getRefFacture());
		dto.setRegle(entity.getRegle());
		dto.setObservation(entity.getObservation());
		
		dto.setReferenceDetReglement(entity.getReference());
		
		
		dto.setDateDepotBanque(entity.getDateDepotBanque());
		dto.setChargeBanque(entity.getChargeBanque());
		dto.setTvaBanque(entity.getTvaBanque());
		
		dto.setBanqueSociete(entity.getBanqueSociete());
		
		
		if(entity.getTypeReglement() != null){
			if(entity.getTypeReglement().getId() != null){
				dto.setTypeReglementId(entity.getTypeReglement().getId());
			}
		}
		if(entity.getReglement() != null){
			if(entity.getReglement().getId() != null){
				dto.setReglementId(entity.getReglement().getId());
			}
		}
			
		return dto;
	}
	public static ElementReglementAchatValue toValue(ElementReglementAchatEntity entity) {
		
		ElementReglementAchatValue dto = new ElementReglementAchatValue();
		
		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setRefFacture(entity.getRefFacture());
		dto.setRefBL(entity.getRefBL());
		dto.setMontantDemande(entity.getMontantDemande());
		dto.setDateEcheance(entity.getDateEcheance());
		
		if(entity.getReglement() != null){
			dto.setReglementId(entity.getReglement().getId());
		}
		
		return dto;
	}
	
	public static TypeReglementAchatValue toValue(TypeReglementAchatEntity entity) {
		
		TypeReglementAchatValue dto = new TypeReglementAchatValue();
		
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setaTerme(entity.getaTerme());
		
		dto.setPrefixe(entity.getPrefixe());
		
		dto.setRegle(entity.getRegle());
		
		return dto;
	}

	public static List<TypeReglementAchatValue> toListValue(List<TypeReglementAchatEntity> listEntity) {
		
		List<TypeReglementAchatValue> list = new ArrayList<TypeReglementAchatValue>();
		
		for(TypeReglementAchatEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
	
	public static ResultatRechecheReglementAchatElementValue toResultatRechecheReglementElementValue(
			ReglementAchatEntity entity) {
		
		ResultatRechecheReglementAchatElementValue dto = new ResultatRechecheReglementAchatElementValue();
		
		dto.setId(entity.getId());
		dto.setPartieIntId(entity.getPartieIntId());
		dto.setReference(entity.getReference());
		dto.setDate(entity.getDate());
		dto.setMontantTotal(entity.getMontantTotal());
		
		dto.setDeclarer(entity.isDeclarer());
		
		String referenceDetailReglement = "";
		
		for(DetailsReglementAchatEntity detail : entity.getListDetailsReglement()) {
			
			if(detail.getReference() != null)
				referenceDetailReglement += detail.getReference() + " ";
		}
		
		dto.setReferenceDetailReglement(referenceDetailReglement);
		
		return dto;
	}



	public static ResultatRechecheElementReglementAchatElementValue toValueElementReglementAffichage(
			ElementReglementAchatEntity entity) {
		ResultatRechecheElementReglementAchatElementValue dto = new ResultatRechecheElementReglementAchatElementValue();
		
		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setRefFacture(entity.getRefFacture());
		dto.setRefBL(entity.getRefBL());
		dto.setMontantDemande(entity.getMontantDemande());
		dto.setDateEcheance(entity.getDateEcheance());
		
		if(entity.getReglement() != null){
			
			dto.setReglementId(entity.getReglement().getId());
			dto.setReference(entity.getReglement().getReference());
			dto.setDate(entity.getReglement().getDate());
			dto.setMontantTotal(entity.getReglement().getMontantTotal());
			dto.setPartieIntId(entity.getReglement().getPartieIntId());
			
			dto.setGroupeClientId(entity.getReglement().getGroupeClientId());
			
			dto.setDeclarer(entity.getReglement().isDeclarer());
			
		}
		
		
		return dto;
	}

	public static ResultatRechecheElementReglementAchatElementValue toValueDetailsReglementAffichage(
			DetailsReglementAchatEntity entity) {

		ResultatRechecheElementReglementAchatElementValue dto = new ResultatRechecheElementReglementAchatElementValue();
		
		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setRefFacture(entity.getRefFacture());
		
		
		dto.setNumPiece(entity.getNumPiece());
		dto.setBanque(entity.getBanque());
		dto.setDateEmission(entity.getDateEmission());
		dto.setDateEcheance(entity.getDateEcheance());
		dto.setRegle(entity.getRegle());
		dto.setObservation(entity.getObservation());

		dto.setReferenceDetReglement(entity.getReference());
		
	
		
		
		dto.setDateDepotBanque(entity.getDateDepotBanque());
		dto.setChargeBanque(entity.getChargeBanque());
		dto.setTvaBanque(entity.getTvaBanque());
		dto.setBanqueSociete(entity.getBanqueSociete());
		
		
		
		if(entity.getReglement() != null){
			
			dto.setReglementId(entity.getReglement().getId());
			dto.setReference(entity.getReglement().getReference());
			dto.setDate(entity.getReglement().getDate());
			dto.setMontantTotal(entity.getReglement().getMontantTotal());
			dto.setPartieIntId(entity.getReglement().getPartieIntId());
			
			dto.setGroupeClientId(entity.getReglement().getGroupeClientId());
			
			dto.setDeclarer(entity.getReglement().isDeclarer());
			
		}
		
		
		return dto;
	}
	
	
	

	/** DocumentreglementEntite to DocumentReglementValue **/
	public static DocumentReglementAchatValue toValue(
			DocumentReglementAchatEntity pDocumentProduitEntity) {
		DocumentReglementAchatValue documentProduitValue = new DocumentReglementAchatValue();
		documentProduitValue.setId(pDocumentProduitEntity.getId());
		documentProduitValue.setPath(pDocumentProduitEntity.getPath());
		// documentProduitValue.setProduitId(pDocumentProduitEntity.getProduitId());
		documentProduitValue.setUidDocument(pDocumentProduitEntity
				.getUidDocument());
		documentProduitValue.setTypeDocumentId(pDocumentProduitEntity
				.getTypeDocumentId());
		return documentProduitValue;
	}

	/** DocumentLivraisonVenteValue to DocumentLivraisonVenteEntite **/
	public static DocumentReglementAchatEntity toEntity(
			DocumentReglementAchatValue pDocumentProduitValue) {
		DocumentReglementAchatEntity documentProduitEntity = new DocumentReglementAchatEntity();
		if (pDocumentProduitValue.getId() != null) {
			documentProduitEntity.setId(pDocumentProduitValue.getId());
		}
		documentProduitEntity.setPath(pDocumentProduitValue.getPath());
		// documentProduitEntity.setProduitId(pDocumentProduitValue.getProduitId());
		documentProduitEntity.setUidDocument(pDocumentProduitValue
				.getUidDocument());
		documentProduitEntity.setTypeDocumentId(pDocumentProduitValue
				.getTypeDocumentId());
		return documentProduitEntity;
	}

}
