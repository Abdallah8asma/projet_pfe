package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.utility;

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
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.TypeReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.entity.DetailsReglementInverseAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.entity.DocumentReglementInverseAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.entity.ElementReglementInverseAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.entity.ReglementInverseAchatEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

public class ReglementInverseAchatPersistanceUtilities {
	
	private ReglementInverseAchatPersistanceUtilities(){
		
	}

	public static ReglementInverseAchatEntity toEntity(ReglementAchatValue dto) {
		
		ReglementInverseAchatEntity entity = new ReglementInverseAchatEntity();
		
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
		     Set<DetailsReglementInverseAchatEntity> list = new HashSet <DetailsReglementInverseAchatEntity>();
		     for (DetailsReglementAchatValue detailsReglementAchatValue : dto.getListDetailsReglement()) {
		    	 DetailsReglementInverseAchatEntity detailsReglementInverseAchatEntity = toEntity(detailsReglementAchatValue);
		    	 detailsReglementInverseAchatEntity.setReglement(entity);
		    	 list.add(detailsReglementInverseAchatEntity);
		    }
		     entity.setListDetailsReglement(list);
		}
	    
	    if(dto.getListElementReglement() != null){
		     Set<ElementReglementInverseAchatEntity> list = new HashSet <ElementReglementInverseAchatEntity>();
		     for (ElementReglementAchatValue elementReglementAchatValue : dto.getListElementReglement()) {
		    	 ElementReglementInverseAchatEntity elementReglementInverseAchatEntity = toEntity(elementReglementAchatValue);
		    	 elementReglementInverseAchatEntity.setReglement(entity);
		    	 list.add(elementReglementInverseAchatEntity);
		    }
		     entity.setListElementReglement(list);
		}
	    
	    
		/*** Liste Document reglement  */
		if (dto.getListDocReglement() != null) {
			Set<DocumentReglementInverseAchatEntity> vListeDocuments = new HashSet<DocumentReglementInverseAchatEntity>();
			for (DocumentReglementAchatValue vDocumentValue : dto.getListDocReglement())
				 {
				DocumentReglementInverseAchatEntity vDe = toEntity(vDocumentValue);
				vDe.setReglement(entity);
				vListeDocuments.add(vDe);
			}
			entity.setDocumentReglement(vListeDocuments);
		}
		
		return entity;
	}

	public static ReglementAchatValue toValue(ReglementInverseAchatEntity entity) {
		
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
		     for (DetailsReglementInverseAchatEntity detailsReglementInverseAchatEntity : entity.getListDetailsReglement()) {
		    	 DetailsReglementAchatValue detailsReglementAchatValue = toValue(detailsReglementInverseAchatEntity);
		    	 list.add(detailsReglementAchatValue);
		    }
		     
		     Collections.sort(list);
		     dto.setListDetailsReglement(new TreeSet<>(list));
		}
	    
	    if(entity.getListElementReglement() != null){
	    	List<ElementReglementAchatValue> list = new ArrayList <ElementReglementAchatValue>();
		     for (ElementReglementInverseAchatEntity detailsReglementInverseAchatEntity : entity.getListElementReglement()) {
		    	 ElementReglementAchatValue elementReglementAchatValue = toValue(detailsReglementInverseAchatEntity);
		    	 list.add(elementReglementAchatValue);
		    }
		     
		     Collections.sort(list);
		     dto.setListElementReglement(new TreeSet<>(list));
		}
	    
	    
	    
		/*** Liste Document reglement  */
		if (entity.getDocumentReglement()!= null) {
			Set<DocumentReglementAchatValue> vListeDocuments = new HashSet<DocumentReglementAchatValue>();
			for (DocumentReglementInverseAchatEntity vDocumentEntity : entity
					.getDocumentReglement()) {
				DocumentReglementAchatValue vDe = toValue(vDocumentEntity);
				vListeDocuments.add(vDe);
			}
			dto.setListDocReglement(vListeDocuments);
		}
	    
		return dto;
	}

	public static List<ReglementAchatValue> listToValue(List<ReglementInverseAchatEntity> listEntity) {

		List<ReglementAchatValue> list = new ArrayList<ReglementAchatValue>();
		
		for(ReglementInverseAchatEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
	
	public static DetailsReglementInverseAchatEntity toEntity(DetailsReglementAchatValue dto) {
		
		DetailsReglementInverseAchatEntity entity = new DetailsReglementInverseAchatEntity();
		
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
			ReglementInverseAchatEntity reglement = new ReglementInverseAchatEntity();
			reglement.setId(dto.getReglementId());
			entity.setReglement(reglement);
		}
		
		
		return entity;
		
	}
	
	public static ElementReglementInverseAchatEntity toEntity(ElementReglementAchatValue dto) {
		
		ElementReglementInverseAchatEntity entity = new ElementReglementInverseAchatEntity();
		
		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setRefFacture(dto.getRefFacture());
		entity.setRefBL(dto.getRefBL());
		entity.setMontantDemande(dto.getMontantDemande());
		entity.setDateEcheance(dto.getDateEcheance());
		
		if(dto.getReglementId() != null){
			ReglementInverseAchatEntity reglement = new ReglementInverseAchatEntity();
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
	
	public static DetailsReglementAchatValue toValue(DetailsReglementInverseAchatEntity entity) {
		
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
			DetailsReglementInverseAchatEntity entity) {
		
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
	public static ElementReglementAchatValue toValue(ElementReglementInverseAchatEntity entity) {
		
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
			ReglementInverseAchatEntity entity) {
		
		ResultatRechecheReglementAchatElementValue dto = new ResultatRechecheReglementAchatElementValue();
		
		dto.setId(entity.getId());
		dto.setPartieIntId(entity.getPartieIntId());
		dto.setReference(entity.getReference());
		dto.setDate(entity.getDate());
		dto.setMontantTotal(entity.getMontantTotal());
		
		dto.setDeclarer(entity.isDeclarer());
		
		return dto;
	}



	public static ResultatRechecheElementReglementAchatElementValue toValueElementReglementAffichage(
			ElementReglementInverseAchatEntity entity) {
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
			DetailsReglementInverseAchatEntity entity) {

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
			DocumentReglementInverseAchatEntity pDocumentProduitEntity) {
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
	public static DocumentReglementInverseAchatEntity toEntity(
			DocumentReglementAchatValue pDocumentProduitValue) {
		DocumentReglementInverseAchatEntity documentProduitEntity = new DocumentReglementInverseAchatEntity();
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
