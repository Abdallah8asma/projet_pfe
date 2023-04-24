package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.DocumentProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.DocumentProduitEntity;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DocumentLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFnReportingVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetLivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DocumentLivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LivraisonVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.MarcheEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.ModePaiementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.TaxeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.TaxeLivraisonEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonLivraisonPersistanceUtilities {
	
	@Autowired
	IProduitPersistance produitPersistance;
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonPersistanceUtilities.class);
	
	public LivraisonVenteVue toVue(LivraisonVenteEntity entity) {
		
		LivraisonVenteVue dto = new LivraisonVenteVue();
		dto.setReferenceBL(entity.getReference());
		
		return dto;
	}
	
	public LivraisonVenteFnReportingVue toFnReportingVue(LivraisonVenteEntity entity) {
		
		LivraisonVenteFnReportingVue dto = new LivraisonVenteFnReportingVue();
		dto.setClientId(entity.getPartieIntId());
		// TODO: calcule du chiffre Affaire dans le domaine de LivVente
		return dto;
	}
	
	public LivraisonVenteValue toValue(LivraisonVenteEntity entity) {
		
		LivraisonVenteValue dto = new LivraisonVenteValue();
		
		if(entity.getId()!=null){
			dto.setId(entity.getId());
		}
		
		dto.setReference(entity.getReference());
		dto.setRefAvantChangement(entity.getReference());
		
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
		
		if(entity.getNatureLivraison() != null) {
			dto.setNatureLivraison(entity.getNatureLivraison().trim());
		}
	
		dto.setTotalPourcentageRemise(entity.getTotalPourcentageRemise());
		dto.setStock(entity.getStock());
		dto.setIdDepot(entity.getIdDepot());
		dto.setRefCommande(entity.getRefCommande());
		dto.setIdCamion(entity.getIdCamion());
		dto.setIdChauffeur(entity.getIdChauffeur());
	    dto.setIdRemorque(entity.getIdRemorque());
	    
	    dto.setModifier(entity.isModifier());
	    dto.setModepaiementId(entity.getModepaiementId());
	    dto.setMarcheId(entity.getMarcheId());
	    dto.setCamion(entity.getCamion());
	    dto.setChauffeur(entity.getChauffeur());
	    dto.setRemorque(entity.getRemorque());
	    
	    dto.setDescription(entity.getDescription());
	    dto.setTypePartieInteressee(entity.getTypePartieInteressee());
	    
	    dto.setDeclare(entity.getDeclare());
	    
	    dto.setGroupeClientId(entity.getGroupeClientId());
	    
	    
	    
	    dto.setEmailPassager(entity.getEmailPassager());
	    dto.setAdressePassager(entity.getAdressePassager());
	    dto.setNumTelPassager(entity.getNumTelPassager());

	    dto.setReglementId(entity.getReglementId());
	    dto.setBoutiqueId(entity.getBoutiqueId());
	    
	    dto.setDevise(entity.getDevise());
	    dto.setMontantConverti(entity.getMontantConverti());
	    dto.setTauxConversion(entity.getTauxConversion());
	    
	    dto.setReferenceBlManuel(entity.getReferenceBlManuel());
	    dto.setIdentifiantLivraison(entity.getIdentifiantLivraison());
		
		if(entity.getListDetLivraisonVente() != null){
	    	List<DetLivraisonVenteValue> list = new ArrayList <DetLivraisonVenteValue>();
		     for (DetLivraisonVenteEntity detLivraisonVenteEntity : entity.getListDetLivraisonVente()) {
		    	 DetLivraisonVenteValue detLivraisonVenteValue = toValue(detLivraisonVenteEntity);
		    	 list.add(detLivraisonVenteValue);
		    }
		     dto.setListDetLivraisonVente(list);
		}
	    
	    if(entity.getListTaxeLivraison() != null){
	    	List<TaxeLivraisonValue> list = new ArrayList <TaxeLivraisonValue>();
		     for (TaxeLivraisonEntity taxeLivraisonEntity : entity.getListTaxeLivraison()) {
		    	 TaxeLivraisonValue taxeLivraisonValue = toValue(taxeLivraisonEntity);
		    	 list.add(taxeLivraisonValue);
		    }
		     dto.setListTaxeLivraison(list);
		}
	    
		/*** Liste Document livraison vente */
		if (entity.getDocumentLivraisonVente() != null) {
			Set<DocumentLivraisonVenteValue> vListeDocuments = new HashSet<DocumentLivraisonVenteValue>();
			for (DocumentLivraisonVenteEntity vDocumentEntity : entity
					.getDocumentLivraisonVente()) {
				DocumentLivraisonVenteValue vDe = toValue(vDocumentEntity);
				vListeDocuments.add(vDe);
			}
			dto.setListDocLivraisonVente(vListeDocuments);
		}
	   
	  
		
		return dto;
	}



	public LivraisonVenteEntity toEntity(LivraisonVenteValue dto) {
		
		LivraisonVenteEntity entity = new LivraisonVenteEntity();
		
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
		
		
	    entity.setModifier(dto.isModifier());
	    entity.setModepaiementId(dto.getModepaiementId());
	    entity.setMarcheId(dto.getMarcheId());
		
		entity.setDescription(dto.getDescription());
		entity.setTypePartieInteressee(dto.getTypePartieInteressee());
		
		entity.setDeclare(dto.getDeclare());
		
		entity.setGroupeClientId(dto.getGroupeClientId());
		
		
		
		entity.setEmailPassager(dto.getEmailPassager());
		entity.setAdressePassager(dto.getAdressePassager());
		entity.setNumTelPassager(dto.getNumTelPassager());
		
		entity.setReglementId(dto.getReglementId());
		
		entity.setBoutiqueId(dto.getBoutiqueId());
		
		
		entity.setDevise(dto.getDevise());
		entity.setTauxConversion(dto.getTauxConversion());
		entity.setMontantConverti(dto.getMontantConverti());
		
		entity.setReferenceBlManuel(dto.getReferenceBlManuel());
		entity.setIdentifiantLivraison(dto.getIdentifiantLivraison());
		
	    if(dto.getListDetLivraisonVente() != null){
		     Set<DetLivraisonVenteEntity> list = new HashSet <DetLivraisonVenteEntity>();
		     for (DetLivraisonVenteValue detLivraisonVenteValue : dto.getListDetLivraisonVente()) {
		    	 DetLivraisonVenteEntity detLivraisonVenteEntity = toEntity(detLivraisonVenteValue);
		    	 detLivraisonVenteEntity.setLivraisonVente(entity);
		    	 list.add(detLivraisonVenteEntity);
		    }
		     entity.setListDetLivraisonVente(list);
		}
	    
	    if(dto.getListTaxeLivraison() != null){
		     Set<TaxeLivraisonEntity> list = new HashSet <TaxeLivraisonEntity>();
		     for (TaxeLivraisonValue taxeLivraisonValue : dto.getListTaxeLivraison()) {
		    	 TaxeLivraisonEntity taxeLivraisonEntity = toEntity(taxeLivraisonValue);
		    	 taxeLivraisonEntity.setLivraisonVente(entity);
		    	 list.add(taxeLivraisonEntity);
		    }
		     entity.setListTaxeLivraison(list);
		}
	    
	    
			/*** Liste Document livraison vente */
			if (dto.getListDocLivraisonVente() != null) {
				Set<DocumentLivraisonVenteEntity> vListeDocuments = new HashSet<DocumentLivraisonVenteEntity>();
				for (DocumentLivraisonVenteValue vDocumentValue : dto.getListDocLivraisonVente())
					 {
					DocumentLivraisonVenteEntity vDe = toEntity(vDocumentValue);
					vDe.setLivraisonVente(entity);
					vListeDocuments.add(vDe);
				}
				entity.setDocumentLivraisonVente(vListeDocuments);
			}
	
	    

		
		return entity;
	}

	/** Document livraisonVenteEntite to Document livraisonVenteValue **/
	public static DocumentLivraisonVenteValue toValue(
			DocumentLivraisonVenteEntity pDocumentProduitEntity) {
		DocumentLivraisonVenteValue documentProduitValue = new DocumentLivraisonVenteValue();
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
	public static DocumentLivraisonVenteEntity toEntity(
			DocumentLivraisonVenteValue pDocumentProduitValue) {
		DocumentLivraisonVenteEntity documentProduitEntity = new DocumentLivraisonVenteEntity();
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
	public DetLivraisonVenteEntity toEntity(DetLivraisonVenteValue dto) {
		
		DetLivraisonVenteEntity entity = new DetLivraisonVenteEntity();
		
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
		entity.setDescription(dto.getDescription());
		entity.setTaxeId(dto.getTaxeId());
		
		entity.setNumeroOF(dto.getNumeroOF());
		
		if(dto.getLivraisonVenteId() != null){
			LivraisonVenteEntity livraisonVenteEntity = new LivraisonVenteEntity();
			livraisonVenteEntity.setId(dto.getLivraisonVenteId());
			entity.setLivraisonVente(livraisonVenteEntity);
		}
		
		return entity;
	}

	public DetLivraisonVenteValue toValue(DetLivraisonVenteEntity entity) {
		
		DetLivraisonVenteValue dto = new DetLivraisonVenteValue();
		
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
		dto.setDescription(entity.getDescription());
		dto.setTaxeId(entity.getTaxeId());
		
		dto.setNumeroOF(entity.getNumeroOF());
		
		if (entity.getProduitId()!=null) {
			ProduitValue produitValue = produitPersistance.rechercheProduitById(entity.getProduitId());
			dto.setProduitDesignation(produitValue.getDesignation());
			dto.setProduitReference(produitValue.getReference());
		}
		
		if(entity.getLivraisonVente() != null){
			dto.setLivraisonVenteId(entity.getLivraisonVente().getId());
			dto.setDate(entity.getLivraisonVente().getDate());
			dto.setReferenceBL(entity.getLivraisonVente().getReference());
		}
		
		return dto;
	}

	public TaxeLivraisonEntity toEntity(TaxeLivraisonValue dto) {
		
		TaxeLivraisonEntity entity = new TaxeLivraisonEntity();
		
		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setPourcentage(dto.getPourcentage());
//		entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setTaxeId(dto.getTaxeId());
		
		
		if(dto.getLivraisonVenteId() != null){
			LivraisonVenteEntity livraisonVenteEntity = new LivraisonVenteEntity();
			livraisonVenteEntity.setId(dto.getLivraisonVenteId());
			entity.setLivraisonVente(livraisonVenteEntity);
		}
		
		return entity;
	}

	public TaxeLivraisonValue toValue(TaxeLivraisonEntity entity) {
		
		TaxeLivraisonValue dto = new TaxeLivraisonValue();
		
		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setPourcentage(entity.getPourcentage());
//		dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setTaxeId(entity.getTaxeId());
		
		if(entity.getLivraisonVente() != null){
			dto.setLivraisonVenteId(entity.getLivraisonVente().getId());
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
		entity.setTva(dto.getTva());
	    entity.setValeur(dto.getValeur());
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
		dto.setTva(entity.getTva());
		dto.setValeur(entity.getValeur());
		return dto;
	}

	public List<TaxeValue> toValue(List<TaxeEntity> listEntity) {

		List<TaxeValue> list = new ArrayList<TaxeValue>();
		
		for(TaxeEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public List<LivraisonVenteValue> listLivraisonVenteEntitytoValue(List<LivraisonVenteEntity> listEntity) {
		
		List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
		
		for(LivraisonVenteEntity entity : listEntity){
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
		dto.setPartieInteresseId(entity.getPartieInteresseId());
			
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
		
		//System.out.println("####   dto.getPartieInteresseId()  : "+dto.getPartieInteresseId());
		entity.setPartieInteresseId(dto.getPartieInteresseId());
			
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
public LivraisonVenteFactureVue toFactureVue(LivraisonVenteEntity entity) {
		
	LivraisonVenteFactureVue dto = new LivraisonVenteFactureVue();
		
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
		dto.setInfoProduit("");
		if(entity.getListDetLivraisonVente() != null){
	    	List<DetLivraisonVenteValue> list = new ArrayList <DetLivraisonVenteValue>();
		     for (DetLivraisonVenteEntity detLivraisonVenteEntity : entity.getListDetLivraisonVente()) {
		    	DetLivraisonVenteValue det=toValue(detLivraisonVenteEntity);
		    	//System.out.println("##### Avantttttt  det.getProduitDesignation():   "+det.getProduitDesignation()); 
		    	 if (!dto.getInfoProduit().contains(det.getProduitDesignation())){
		    	//	System.out.println("##### iiiiifffff  det.getProduitDesignation():   "+det.getProduitDesignation()); 
		    		String infoProduit =dto.getInfoProduit();
		    		
		    		infoProduit+="  +  ";
		    		infoProduit+=det.getProduitDesignation();
		    		dto.setInfoProduit(infoProduit);
		    		//System.out.println("##### INFOOOOO  det.getProduitDesignation():   "+infoProduit); 

		    		
		    	 }
		    }
		     //dto.setListDetLivraisonVente(list);
		}
	    
		//System.out.println("##### apressss  det.getProduitDesignation():   "+ dto.getInfoProduit());
	    
	    dto.setModifier(entity.isModifier());
	    dto.setModepaiementId(entity.getModepaiementId());
	    dto.setMarcheId(entity.getMarcheId());
	    dto.setCamion(entity.getCamion());
	    dto.setChauffeur(entity.getChauffeur());
	    dto.setRemorque(entity.getRemorque());
	   
	  
		
		return dto;
	}
}
