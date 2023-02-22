package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DocumentLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DocumentFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DocumentLivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.MarcheEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.ModePaiementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.TaxeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DetFactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DocumentFactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.FactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.RaisonFactureEntite;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.TaxeFactureEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class FacturePersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(FacturePersistanceUtilities.class);
	
	public FactureVenteVue toVue(FactureVenteEntity entity) {
		
		FactureVenteVue dto = new FactureVenteVue();
		
		dto.setReferenceFacture(entity.getReference());
		return dto;
	}
	
	public FactureVenteValue toValue(FactureVenteEntity entity) {
		
		FactureVenteValue dto = new FactureVenteValue();
		
		dto.setId(entity.getId());
		dto.setReference(entity.getReference());
		dto.setRefAvantChangement(entity.getReference());
		dto.setDate(entity.getDate());
		dto.setMontantHTaxe(entity.getMontantHTaxe());
		dto.setMontantTTC(entity.getMontantTTC());
		dto.setMontantTaxe(entity.getMontantTaxe());
		dto.setMontantRemise(entity.getMontantRemise());
		dto.setObservations(entity.getObservations());
		dto.setInfoLivraison(entity.getInfoLivraison());
		dto.setPartieIntId(entity.getPartieIntId());
		//dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setEtat(entity.getEtat());
		dto.setMetrageTotal(entity.getMetrageTotal());
		dto.setType(entity.getType());
		dto.setNatureLivraison(entity.getNatureLivraison());
		dto.setTotalPourcentageRemise(entity.getTotalPourcentageRemise());
		dto.setInfoLivraisonExterne(entity.getInfoLivraisonExterne());
	    dto.setIdMarche(entity.getIdMarche());
	    dto.setDescription(entity.getDescription());
	    dto.setTypePartieInteressee(entity.getTypePartieInteressee());
	    
	    dto.setGroupeClientId(entity.getGroupeClientId());
	    dto.setRaison(entity.getRaison());
	    dto.setNature(entity.getNature());
	    
	    dto.setReglementId(entity.getReglementId());
	    
	    dto.setDateIntroduction(entity.getDateIntroduction());
	    
	    dto.setBoutiqueId(entity.getBoutiqueId());
	    
	    
	    dto.setIdDepot(entity.getIdDepot());
	    
	    
	    dto.setDevise(entity.getDevise());
	    dto.setTauxConversion(entity.getTauxConversion());
	    dto.setMontantConverti(entity.getMontantConverti());
	    dto.setDeclarer(entity.getDeclarer());
	    
	    dto.setForcerCalculMontant(entity.isForcerCalculMontant());
	    
	    
	    dto.setModalitePaiement(entity.getModalitePaiement());
	    dto.setDateEcheance(entity.getDateEcheance());
	    
	    dto.setRefCommande(entity.getRefCommande());
	    dto.setIdentifiant(entity.getIdentifiant());
	    
	    
		if(entity.getListDetFactureVente() != null){
	    	List<DetFactureVenteValue> list = new ArrayList <DetFactureVenteValue>();
		     for (DetFactureVenteEntity detFactureVenteEntity : entity.getListDetFactureVente()) {
		    	 DetFactureVenteValue detFactureVenteValue = toValue(detFactureVenteEntity);
		    	 list.add(detFactureVenteValue);
		    }
		     dto.setListDetFactureVente(list);
		}
	    
	    if(entity.getListTaxeFacture() != null){
	    	List<TaxeFactureValue> list = new ArrayList <TaxeFactureValue>();
		     for (TaxeFactureEntity taxeFactureEntity : entity.getListTaxeFacture()) {
		    	 TaxeFactureValue taxeFactureValue = toValue(taxeFactureEntity);
		    	 list.add(taxeFactureValue);
		    }
		     dto.setListTaxeFacture(list);
		}
	    
	    
		/*** Liste Document livraison vente */
		if (entity.getDocumentFactureVente() != null) {
			Set<DocumentFactureVenteValue> vListeDocuments = new HashSet<DocumentFactureVenteValue>();
			for (DocumentFactureVenteEntity vDocumentEntity : entity
					.getDocumentFactureVente()) {
				DocumentFactureVenteValue vDe = toValue(vDocumentEntity);
				vListeDocuments.add(vDe);
			}
			dto.setListDocFactureVente(vListeDocuments);
		}
	    
	    
	    dto.setModepaiementId(entity.getModepaiementId());
		
		return dto;
	}

	public FactureVenteEntity toEntity(FactureVenteValue dto) {
		
		FactureVenteEntity entity = new FactureVenteEntity();
		
		entity.setId(dto.getId());
		entity.setReference(dto.getReference());
		entity.setDate(dto.getDate());
		entity.setMontantHTaxe(dto.getMontantHTaxe());
		entity.setMontantTTC(dto.getMontantTTC());
		entity.setMontantTaxe(dto.getMontantTaxe());
		entity.setMontantRemise(dto.getMontantRemise());
		entity.setObservations(dto.getObservations());
		entity.setInfoLivraison(dto.getInfoLivraison());
		entity.setPartieIntId(dto.getPartieIntId());
		//entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setEtat(dto.getEtat());
		entity.setMetrageTotal(dto.getMetrageTotal());
		entity.setType(dto.getType());
		entity.setNatureLivraison(dto.getNatureLivraison());
		entity.setTotalPourcentageRemise(dto.getTotalPourcentageRemise());
		entity.setInfoLivraisonExterne(dto.getInfoLivraisonExterne());
	    entity.setIdMarche(dto.getIdMarche());
	    entity.setDescription(dto.getDescription());
	    entity.setTypePartieInteressee(dto.getTypePartieInteressee());
	    
	    entity.setGroupeClientId(dto.getGroupeClientId());
	    entity.setRaison(dto.getRaison());
	    entity.setNature(dto.getNature());
	    
	    entity.setReglementId(dto.getReglementId());
	    
	    entity.setDateIntroduction(dto.getDateIntroduction());
	    
	    entity.setBoutiqueId(dto.getBoutiqueId());
	    
	    
	    entity.setIdDepot(dto.getIdDepot());
	    
	    
	    entity.setDevise(dto.getDevise());
	    entity.setTauxConversion(dto.getTauxConversion());
	    entity.setMontantConverti(dto.getMontantConverti());
	    entity.setDeclarer(dto.getDeclarer());
	    
	    entity.setForcerCalculMontant(dto.isForcerCalculMontant());
	    
	    entity.setModalitePaiement(dto.getModalitePaiement());
	    entity.setDateEcheance(dto.getDateEcheance());
	    
	    entity.setRefCommande(dto.getRefCommande());
	    entity.setIdentifiant(dto.getIdentifiant());
	    
	    
		if(dto.getListDetFactureVente() != null){
		     Set<DetFactureVenteEntity> list = new HashSet <DetFactureVenteEntity>();
		     for (DetFactureVenteValue detFactureVenteValue : dto.getListDetFactureVente()) {
		    	 DetFactureVenteEntity detFactureVenteEntity = toEntity(detFactureVenteValue);
		    	 detFactureVenteEntity.setFactureVente(entity);
		    	 list.add(detFactureVenteEntity);
		    }
		     entity.setListDetFactureVente(list);
		}
	    
	    if(dto.getListTaxeFacture() != null){
		     Set<TaxeFactureEntity> list = new HashSet <TaxeFactureEntity>();
		     for (TaxeFactureValue taxeFactureValue : dto.getListTaxeFacture()) {
		    	 TaxeFactureEntity taxeFactureEntity = toEntity(taxeFactureValue);
		    	 taxeFactureEntity.setFactureVente(entity);
		    	 list.add(taxeFactureEntity);
		    }
		     entity.setListTaxeFacture(list);
		}
	    
		/*** Liste Document livraison vente */
		if (dto.getListDocFactureVente() != null) {
			Set<DocumentFactureVenteEntity> vListeDocuments = new HashSet<DocumentFactureVenteEntity>();
			for (DocumentFactureVenteValue vDocumentValue : dto.getListDocFactureVente())
				 {
				DocumentFactureVenteEntity vDe = toEntity(vDocumentValue);
				vDe.setFactureVente(entity);
				vListeDocuments.add(vDe);
			}
			entity.setDocumentFactureVente(vListeDocuments);
		}

	    
	    entity.setModepaiementId(dto.getModepaiementId());
		
		return entity;
	}
	
	/** Document factureVenteEntite to Document factureVenteValue **/
	public static DocumentFactureVenteValue toValue(
			DocumentFactureVenteEntity pDocumentProduitEntity) {
		DocumentFactureVenteValue documentProduitValue = new DocumentFactureVenteValue();
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
	public static DocumentFactureVenteEntity toEntity(
			DocumentFactureVenteValue pDocumentProduitValue) {
		DocumentFactureVenteEntity documentProduitEntity = new DocumentFactureVenteEntity();
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

	public DetFactureVenteEntity toEntity(DetFactureVenteValue dto) {
		
		DetFactureVenteEntity entity = new DetFactureVenteEntity();
		
		entity.setId(dto.getId());
		entity.setProduitId(dto.getProduitId());
		entity.setQuantite(dto.getQuantite());
		entity.setUnite(dto.getUnite());
		entity.setNombreColis(dto.getNombreColis());
		entity.setRemise(dto.getRemise());
		entity.setChoix(dto.getChoix());
		entity.setPrixUnitaireHT(dto.getPrixUnitaireHT());
		entity.setTraitementFaconId(dto.getTraitementFaconId());
		entity.setPrixTotalHT(dto.getPrixTotalHT());
		entity.setFicheId(dto.getFicheId());
		if(dto.getFactureVenteId() != null){
			FactureVenteEntity FactureVenteEntity = new FactureVenteEntity();
			FactureVenteEntity.setId(dto.getFactureVenteId());
			entity.setFactureVente(FactureVenteEntity);
		}
		
		
		entity.setSerialisable(dto.isSerialisable());
		entity.setNumeroSeries(dto.getNumeroSeries());
		
		entity.setDescription(dto.getDescription());
		
		entity.setTaxeId(dto.getTaxeId());
		
		entity.setNumeroOF(dto.getNumeroOF());
	
		
		return entity;
	}

	public DetFactureVenteValue toValue(DetFactureVenteEntity entity) {
		
		DetFactureVenteValue dto = new DetFactureVenteValue();
		
		dto.setId(entity.getId());
		dto.setProduitId(entity.getProduitId());
		dto.setQuantite(entity.getQuantite());
		dto.setUnite(entity.getUnite());
		dto.setNombreColis(entity.getNombreColis());
		dto.setRemise(entity.getRemise());
		dto.setChoix(entity.getChoix());
		dto.setPrixUnitaireHT(entity.getPrixUnitaireHT());
		dto.setTraitementFaconId(entity.getTraitementFaconId());
		dto.setPrixTotalHT(entity.getPrixTotalHT());
		dto.setFicheId(entity.getFicheId());
		if(entity.getFactureVente() != null){
			dto.setFactureVenteId(entity.getFactureVente().getId());
		}
		
		dto.setSerialisable(entity.isSerialisable());
		dto.setNumeroSeries(entity.getNumeroSeries());
		
		dto.setDescription(entity.getDescription());
		
		dto.setTaxeId(entity.getTaxeId());
	
			
		dto.setNumeroOF(entity.getNumeroOF());
		
		return dto;
	}

	public TaxeFactureEntity toEntity(TaxeFactureValue dto) {
		
		TaxeFactureEntity entity = new TaxeFactureEntity();
		
		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setPourcentage(dto.getPourcentage());
//		entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setTaxeId(dto.getTaxeId());
		
		if(dto.getFactureVenteId() != null){
			FactureVenteEntity FactureVenteEntity = new FactureVenteEntity();
			FactureVenteEntity.setId(dto.getFactureVenteId());
			entity.setFactureVente(FactureVenteEntity);
		}
		
		return entity;
	}

	public TaxeFactureValue toValue(TaxeFactureEntity entity) {
		
		TaxeFactureValue dto = new TaxeFactureValue();
		
		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setPourcentage(entity.getPourcentage());
//		dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setTaxeId(entity.getTaxeId());
		
		if(entity.getFactureVente() != null){
			dto.setFactureVenteId(entity.getFactureVente().getId());
		}
		
		return dto;
	}

	public TaxeEntity toEntity(TaxeValue dto) {
		
		TaxeEntity entity = new TaxeEntity();
		
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		//entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
	    
		return entity;
	}
	
	public TaxeValue toValue(TaxeEntity entity) {
		
		TaxeValue dto = new TaxeValue();
		
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		//dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		
		return dto;
	}

	public List<TaxeValue> toValue(List<TaxeEntity> listEntity) {

		List<TaxeValue> list = new ArrayList<TaxeValue>();
		
		for(TaxeEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public List<FactureVenteValue> listFactureVenteEntitytoValue(List<FactureVenteEntity> listEntity) {
		
		List<FactureVenteValue> list = new ArrayList<FactureVenteValue>();
		
		for(FactureVenteEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	
	public ModePaiementValue toValue(ModePaiementEntity entity) {
			
		ModePaiementValue dto = new ModePaiementValue();
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		//dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
			
		return dto;
	}
	
	public MarcheValue toValue(MarcheEntity entity) {
		
		MarcheValue dto = new MarcheValue();
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		//dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
			
		return dto;
	}
	
	public ModePaiementEntity toEntity(ModePaiementValue dto) {
		
		ModePaiementEntity entity = new ModePaiementEntity();
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		//entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
			
		return entity;
	}
	
	public MarcheEntity toEntity(MarcheValue dto) {
		
		MarcheEntity entity = new MarcheEntity();
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		//entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
			
		return entity;
	}

	

	public List<ModePaiementValue> listModePaiementEntityToValue(List<ModePaiementEntity> listEntity) {

		List<ModePaiementValue> list = new ArrayList<ModePaiementValue>();
		
		for(ModePaiementEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
	

	public List<MarcheValue> listMarcheEntityToValue(List<MarcheEntity> listEntity) {

		List<MarcheValue> list = new ArrayList<MarcheValue>();
		
		for(MarcheEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
	
	public static RaisonFactureValue toValue(RaisonFactureEntite pRaisonFactureEntite) {
		RaisonFactureValue raisonFactureValue = new RaisonFactureValue();
		raisonFactureValue.setId(pRaisonFactureEntite.getId());
		raisonFactureValue.setDesignation(pRaisonFactureEntite.getDesignation());
		return raisonFactureValue;
	}

	/**
	 * To entity.
	 *
	 * @param pRaisonFactureValue
	 *            the raison value
	 * @return the raison  entite
	 */
	public static RaisonFactureEntite toEntity(RaisonFactureValue pRaisonFactureValue) {
		RaisonFactureEntite raisonFactureEntite = new RaisonFactureEntite();
		if (pRaisonFactureValue.getId() != null) {
			raisonFactureEntite.setId(pRaisonFactureValue.getId());
		}
		raisonFactureEntite.setDesignation(pRaisonFactureValue.getDesignation());
		return raisonFactureEntite;
	}
	public  DetFactureVenteValue toValueEnrichi(DetFactureVenteEntity entity) 
	{
		DetFactureVenteValue det=toValue(entity);
		if(entity.getFactureVente() != null)
		{
			det.setDateFacture(entity.getFactureVente().getDateEcheance());
			det.setDateFacture(entity.getFactureVente().getDate());
			det.setReferenceFacture(entity.getFactureVente().getReference());
			det.setReferenceBl(entity.getFactureVente().getInfoLivraison());
			det.setCommandeReference(entity.getFactureVente().getRefCommande());
			det.setPartieIntId(entity.getFactureVente().getPartieIntId());
			det.setPrixTotalHT(entity.getFactureVente().getMontantTTC());
			
		    
		}
		return det;
		
			
		
	}

}
