package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DocumentFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DocumentFactureVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.DetFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.DocumentFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.FactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie.TaxeFactureAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.MarcheEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.ModePaiementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.TaxeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DetFactureVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DocumentFactureVenteEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 *
 */

@Component
public class FactureAchatPersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(FactureAchatPersistanceUtilities.class);

	public FactureAchatVue toVue(FactureAchatEntity entity) {

		FactureAchatVue dto = new FactureAchatVue();

		dto.setReferenceFacture(entity.getReference());
		return dto;
	}

	public FactureAchatValue toValue(FactureAchatEntity entity) {

		FactureAchatValue dto = new FactureAchatValue();

		dto.setId(entity.getId());
		dto.setReference(entity.getReference());
		dto.setDate(entity.getDate());
		dto.setMontantHTaxe(entity.getMontantHTaxe());
		dto.setMontantTTC(entity.getMontantTTC());
		dto.setMontantTaxe(entity.getMontantTaxe());
		dto.setMontantRemise(entity.getMontantRemise());
		dto.setObservations(entity.getObservations());
		dto.setInfoLivraison(entity.getInfoLivraison());
		dto.setPartieIntId(entity.getPartieIntId());
		// dto.setBlSuppression(entity.isBlSuppression());
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
		
		dto.setNature(entity.getNature());
		dto.setRaison(entity.getRaison());
		
		dto.setDateIntroduction(entity.getDateIntroduction());
		dto.setBoutiqueId(entity.getBoutiqueId());
		
		dto.setIdDepot(entity.getIdDepot());

		dto.setDeclarer(entity.isDeclarer());
		
		dto.setForcerCalculMontant(entity.isForcerCalculMontant());
		
		dto.setRefexterne(entity.getRefexterne());
		
		if (entity.getListDetFactureAchat() != null) {
			List<DetFactureAchatValue> list = new ArrayList<DetFactureAchatValue>();
			for (DetFactureAchatEntity detFactureAchatEntity : entity.getListDetFactureAchat()) {
				DetFactureAchatValue detFactureAchatValue = toValue(detFactureAchatEntity);
				list.add(detFactureAchatValue);
			}
			dto.setListDetFactureAchat(list);
		}

		if (entity.getListTaxeFacture() != null) {
			List<TaxeFactureAchatValue> list = new ArrayList<TaxeFactureAchatValue>();
			for (TaxeFactureAchatEntity taxeFactureEntity : entity.getListTaxeFacture()) {
				TaxeFactureAchatValue TaxeFactureAchatValue = toValue(taxeFactureEntity);
				list.add(TaxeFactureAchatValue);
			}
			dto.setListTaxeFacture(list);
		}
		
		/*** Liste Document livraison vente */
		if (entity.getDocumentFactureAchat() != null) {
			Set<DocumentFactureAchatValue> vListeDocuments = new HashSet<DocumentFactureAchatValue>();
			for (DocumentFactureAchatEntity vDocumentEntity : entity
					.getDocumentFactureAchat()) {
				DocumentFactureAchatValue vDe = toValue(vDocumentEntity);
				vListeDocuments.add(vDe);
			}
			dto.setListDocFactureAchat(vListeDocuments);
		}

		dto.setModepaiementId(entity.getModepaiementId());

		return dto;
	}

	public FactureAchatEntity toEntity(FactureAchatValue dto) {

		FactureAchatEntity entity = new FactureAchatEntity();

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
		// entity.setBlSuppression(dto.isBlSuppression());
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
		
		entity.setNature(dto.getNature());
		entity.setRaison(dto.getRaison());
		
		entity.setDateIntroduction(dto.getDateIntroduction());
		entity.setBoutiqueId(dto.getBoutiqueId());
		
		entity.setIdDepot(dto.getIdDepot());
		entity.setDeclarer(dto.isDeclarer());
		
		entity.setForcerCalculMontant(dto.isForcerCalculMontant());
		
		entity.setRefexterne(dto.getRefexterne());
		
		if (dto.getListDetFactureAchat() != null) {
			Set<DetFactureAchatEntity> list = new HashSet<DetFactureAchatEntity>();
			for (DetFactureAchatValue detFactureAchatValue : dto.getListDetFactureAchat()) {
				DetFactureAchatEntity detFactureAchatEntity = toEntity(detFactureAchatValue);
				detFactureAchatEntity.setFactureAchat(entity);
				list.add(detFactureAchatEntity);
			}
			entity.setListDetFactureAchat(list);
		}

		if (dto.getListTaxeFacture() != null) {
			Set<TaxeFactureAchatEntity> list = new HashSet<TaxeFactureAchatEntity>();
			for (TaxeFactureAchatValue TaxeFactureAchatValue : dto.getListTaxeFacture()) {
				TaxeFactureAchatEntity taxeFactureEntity = toEntity(TaxeFactureAchatValue);
				taxeFactureEntity.setFactureAchat(entity);
				list.add(taxeFactureEntity);
			}
			entity.setListTaxeFacture(list);
		}
		
		
		/*** Liste Document facture Achat */
		if (dto.getListDocFactureAchat() != null) {
			Set<DocumentFactureAchatEntity> vListeDocuments = new HashSet<DocumentFactureAchatEntity>();
			for (DocumentFactureAchatValue vDocumentValue : dto.getListDocFactureAchat())
				 {
				DocumentFactureAchatEntity vDe = toEntity(vDocumentValue);
				vDe.setFactureAchat(entity);
				vListeDocuments.add(vDe);
			}
			entity.setDocumentFactureAchat(vListeDocuments);
		}


		entity.setModepaiementId(dto.getModepaiementId());

		return entity;
	}

	public DetFactureAchatEntity toEntity(DetFactureAchatValue dto) {

		DetFactureAchatEntity entity = new DetFactureAchatEntity();

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
		
		
		entity.setSerialisable(dto.isSerialisable());
		entity.setNumeroSeries(dto.getNumeroSeries());
		entity.setTaxeId(dto.getTaxeId());
		entity.setDesignation(dto.getProduitDesignation());
		entity.setReferenceArticle(dto.getProduitReference());
	
		
		if (dto.getFactureAchatId() != null) {
			FactureAchatEntity FactureAchatEntity = new FactureAchatEntity();
			FactureAchatEntity.setId(dto.getFactureAchatId());
			entity.setFactureAchat(FactureAchatEntity);
		}

		return entity;
	}

	public  static    DetFactureAchatValue toValue(DetFactureAchatEntity entity) {

		DetFactureAchatValue dto = new DetFactureAchatValue();

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
		
		dto.setSerialisable(entity.isSerialisable());
		dto.setNumeroSeries(entity.getNumeroSeries());
		dto.setTaxeId(entity.getTaxeId());
		
		dto.setProduitDesignation(entity.getDesignation());;
		dto.setProduitReference(entity.getReferenceArticle());
		
		if (entity.getFactureAchat() != null) {
			dto.setFactureAchatId(entity.getFactureAchat().getId());
		}

		return dto;
	}
	public static DetFactureAchatValue toValueEnrichi(DetFactureAchatEntity entity) 
	{
		DetFactureAchatValue det=toValue(entity);
		if(entity.getFactureAchat() != null)
		{
			det.setDateIntroduction(entity.getFactureAchat().getDate());
		
			det.setFactureReference(entity.getFactureAchat().getReference());
			det.setInfoLivraison(entity.getFactureAchat().getInfoLivraison());
			det.setReferenceBlExterne(entity.getFactureAchat().getRefexterne());
			det.setPartieIntId(entity.getFactureAchat().getPartieIntId());
			det.setPrixTotalHT(entity.getFactureAchat().getMontantTTC());
			
		    
		}
		return det;
		
			
		
	}

	public TaxeFactureAchatEntity toEntity(TaxeFactureAchatValue dto) {

		TaxeFactureAchatEntity entity = new TaxeFactureAchatEntity();

		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setPourcentage(dto.getPourcentage());
		// entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setTaxeId(dto.getTaxeId());

		if (dto.getFactureAchatId() != null) {
			FactureAchatEntity FactureAchatEntity = new FactureAchatEntity();
			FactureAchatEntity.setId(dto.getFactureAchatId());
			entity.setFactureAchat(FactureAchatEntity);
		}

		return entity;
	}

	public TaxeFactureAchatValue toValue(TaxeFactureAchatEntity entity) {

		TaxeFactureAchatValue dto = new TaxeFactureAchatValue();

		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setPourcentage(entity.getPourcentage());
		// dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setTaxeId(entity.getTaxeId());

		if (entity.getFactureAchat() != null) {
			dto.setFactureAchatId(entity.getFactureAchat().getId());
		}

		return dto;
	}

	public TaxeEntity toEntity(TaxeValue dto) {

		TaxeEntity entity = new TaxeEntity();

		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		// entity.setBlSuppression(dto.isBlSuppression());
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
		// dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());

		return dto;
	}

	public List<TaxeValue> toValue(List<TaxeEntity> listEntity) {

		List<TaxeValue> list = new ArrayList<TaxeValue>();

		for (TaxeEntity entity : listEntity) {
			list.add(toValue(entity));
		}

		return list;
	}

	public List<FactureAchatValue> listFactureAchatEntitytoValue(List<FactureAchatEntity> listEntity) {

		List<FactureAchatValue> list = new ArrayList<FactureAchatValue>();

		for (FactureAchatEntity entity : listEntity) {
			list.add(toValue(entity));
		}

		return list;
	}

	public ModePaiementValue toValue(ModePaiementEntity entity) {

		ModePaiementValue dto = new ModePaiementValue();
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		// dto.setBlSuppression(entity.isBlSuppression());
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
		// dto.setBlSuppression(entity.isBlSuppression());
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
		// entity.setBlSuppression(dto.isBlSuppression());
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
		// entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());

		return entity;
	}

	public List<ModePaiementValue> listModePaiementEntityToValue(List<ModePaiementEntity> listEntity) {

		List<ModePaiementValue> list = new ArrayList<ModePaiementValue>();

		for (ModePaiementEntity entity : listEntity) {
			list.add(toValue(entity));
		}

		return list;
	}

	public List<MarcheValue> listMarcheEntityToValue(List<MarcheEntity> listEntity) {

		List<MarcheValue> list = new ArrayList<MarcheValue>();

		for (MarcheEntity entity : listEntity) {
			list.add(toValue(entity));
		}

		return list;
	}
	
	
	/** Document factureAchatEntite to Document factureAchatValue **/
	public static DocumentFactureAchatValue toValue(
			DocumentFactureAchatEntity pDocumentProduitEntity) {
		DocumentFactureAchatValue documentProduitValue = new DocumentFactureAchatValue();
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
	public static DocumentFactureAchatEntity toEntity(
			DocumentFactureAchatValue pDocumentProduitValue) {
		DocumentFactureAchatEntity documentProduitEntity = new DocumentFactureAchatEntity();
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
