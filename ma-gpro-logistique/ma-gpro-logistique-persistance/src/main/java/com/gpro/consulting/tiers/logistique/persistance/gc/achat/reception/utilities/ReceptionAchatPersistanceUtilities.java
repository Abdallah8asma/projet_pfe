package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.DocumentReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.TaxeReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DocumentLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.DocumentReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.ProduitReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.ReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.entitie.TaxeReceptionAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DocumentLivraisonVenteEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Hamdi Etteieb
 * @since 16/09/2018
 *
 */

@Component
public class ReceptionAchatPersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(ReceptionAchatPersistanceUtilities.class);

	/************************** toEntityCommandeAchat **********************************/

	public static ReceptionAchatEntity toEntity(ReceptionAchatValue value) {

		ReceptionAchatEntity entity = new ReceptionAchatEntity();

		if (value.getId() != null) {
			entity.setId(value.getId());
		}

		entity.setSiteId(value.getSiteId());
		entity.setReference(value.getReference());

		entity.setRefexterne(value.getRefexterne());
		entity.setRefexterne(value.getRefexterne());
		entity.setPrixTotal(value.getPrixTotal());
		entity.setDateIntroduction(value.getDateIntroduction());
		entity.setDateLivraison(value.getDateLivraison());
		entity.setObservations(value.getObservations());
		entity.setPartieIntersseId(value.getPartieIntersseId());
		entity.setBlSuppression(value.isBlSuppression());
		entity.setDateSuppression(value.getDateSuppression());
		entity.setDateModification(value.getDateModification());
		entity.setDateCreation(value.getDateCreation());
		entity.setQuantite(value.getQuantite());
		entity.setIdDepot(value.getIdDepot());
		
		entity.setFacture(value.getFacture());
		
		entity.setModepaiementId(value.getModepaiementId());
		
		entity.setNatureLivraison(value.getNatureLivraison());
		entity.setTotalPourcentageRemise(value.getTotalPourcentageRemise());
		entity.setMontantHTaxe(value.getMontantHTaxe());
		entity.setMontantTaxe(value.getMontantTaxe());
		entity.setMontantRemise(value.getMontantRemise());
		entity.setMetrageTotal(value.getMetrageTotal());
		entity.setMontantTTC(value.getMontantTTC());
		
		entity.setRefCommande(value.getRefCommande());
		entity.setType(value.getType());
		entity.setRefAvoirRetour(value.getRefAvoirRetour());
		entity.setRefBL(value.getRefBL());
		entity.setRefFacture(value.getRefFacture());
		
		entity.setBoutiqueId(value.getBoutiqueId());
		entity.setBonMouvementEntreeId(value.getBonMouvementEntreeId());

		if (value.getListProduitReceptions() != null) {

			List<ProduitReceptionAchatEntity> listProduitReceptions = new ArrayList<ProduitReceptionAchatEntity>();

			for (ProduitReceptionAchatValue ProduitReceptionAchatValue : value.getListProduitReceptions()) {

				ProduitReceptionAchatEntity ProduitReceptionAchatEntity = toEntity(ProduitReceptionAchatValue);
				ProduitReceptionAchatEntity.setReceptionAchat(entity);
				listProduitReceptions.add(ProduitReceptionAchatEntity);
			}

			entity.setListProduitReceptions(listProduitReceptions);
		}
		
	    if(value.getListTaxeReceptionAchat() != null){
		     Set<TaxeReceptionAchatEntity> list = new HashSet <TaxeReceptionAchatEntity>();
		     for (TaxeReceptionAchatValue taxeLivraisonValue : value.getListTaxeReceptionAchat()) {
		    	 TaxeReceptionAchatEntity taxeLivraisonEntity = toEntity(taxeLivraisonValue);
		    	 taxeLivraisonEntity.setReceptionAchat(entity);
		    	 list.add(taxeLivraisonEntity);
		    }
		     entity.setListTaxeReceptionAchat(list);
		}
	    
	    
		/*** Liste Document reception Achat */
		if (value.getListDocReceptionAchat()!= null) {
			Set<DocumentReceptionAchatEntity> vListeDocuments = new HashSet<DocumentReceptionAchatEntity>();
			for (DocumentReceptionAchatValue vDocumentValue : value.getListDocReceptionAchat())
				 {
				DocumentReceptionAchatEntity vDe = toEntity(vDocumentValue);
				vDe.setReceptionAchat(entity);
				vListeDocuments.add(vDe);
			}
			entity.setDocumentReceptionAchat(vListeDocuments);
		}

	    
	    

		return entity;
	}

	/************************** toValueCommandeAchat **********************************/

	public static ReceptionAchatValue toValue(ReceptionAchatEntity entity) {

		ReceptionAchatValue value = new ReceptionAchatValue();

		if (entity.getId() != null) {
			value.setId(entity.getId());
		}

		value.setSiteId(entity.getSiteId());
		value.setReference(entity.getReference());
		value.setRefexterne(entity.getRefexterne());
		value.setRefexterne(entity.getRefexterne());
		value.setPrixTotal(entity.getPrixTotal());
		value.setDateIntroduction(entity.getDateIntroduction());
		value.setDateLivraison(entity.getDateLivraison());
		value.setObservations(entity.getObservations());
		value.setPartieIntersseId(entity.getPartieIntersseId());
		value.setIdDepot(entity.getIdDepot());

		//logger.info("PI tovalue" + value.getPartieIntersseId());

		value.setBlSuppression(entity.isBlSuppression());
		value.setDateSuppression(entity.getDateSuppression());
		value.setDateModification(entity.getDateModification());
		value.setDateCreation(entity.getDateCreation());
		value.setQuantite(entity.getQuantite());
		value.setFacture(entity.getFacture());
		value.setModepaiementId(entity.getModepaiementId());
		
		value.setNatureLivraison(entity.getNatureLivraison());
		value.setTotalPourcentageRemise(entity.getTotalPourcentageRemise());
		value.setMontantHTaxe(entity.getMontantHTaxe());
		value.setMontantTaxe(entity.getMontantTaxe());
		value.setMontantRemise(entity.getMontantRemise());
		value.setMetrageTotal(entity.getMetrageTotal());
		value.setMontantTTC(entity.getMontantTTC());
		
		value.setRefCommande(entity.getRefCommande());
		
		value.setType(entity.getType());
		value.setRefAvoirRetour(entity.getRefAvoirRetour());
		value.setRefBL(entity.getRefBL());
		value.setRefFacture(entity.getRefFacture());
		value.setBoutiqueId(entity.getBoutiqueId());
		value.setBonMouvementEntreeId(entity.getBonMouvementEntreeId());

		if (entity.getListProduitReceptions() != null) {

			List<ProduitReceptionAchatValue> listProduitReceptions = new ArrayList<ProduitReceptionAchatValue>();

			for (ProduitReceptionAchatEntity ProduitReceptionAchatEntity : entity.getListProduitReceptions()) {

				ProduitReceptionAchatValue ProduitReceptionAchatValue = toValue(ProduitReceptionAchatEntity);
				listProduitReceptions.add(ProduitReceptionAchatValue);
			}

			value.setListProduitReceptions(listProduitReceptions);
		}
		
		
	    if(entity.getListTaxeReceptionAchat() != null){
	    	List<TaxeReceptionAchatValue> list = new ArrayList <TaxeReceptionAchatValue>();
		     for (TaxeReceptionAchatEntity taxeReceptionAchatEntity : entity.getListTaxeReceptionAchat()) {
		    	 TaxeReceptionAchatValue taxeReceptionAchatValue = toValue(taxeReceptionAchatEntity);
		    	 list.add(taxeReceptionAchatValue);
		    }
		     value.setListTaxeReceptionAchat(list);
		}
	    
	    
		/*** Liste Document reception achat */
		if (entity.getDocumentReceptionAchat() != null) {
			Set<DocumentReceptionAchatValue> vListeDocuments = new HashSet<DocumentReceptionAchatValue>();
			for (DocumentReceptionAchatEntity vDocumentEntity : entity
					.getDocumentReceptionAchat()){
				DocumentReceptionAchatValue vDe = toValue(vDocumentEntity);
				vListeDocuments.add(vDe);
			}
					value.setListDocReceptionAchat(vListeDocuments);
		}
	   

		//logger.info("PI tovalue" + value.getPartieIntersseId());

		return value;
	}

	/************************** toEntityProduitCommande **********************************/

	public static ProduitReceptionAchatEntity toEntity(ProduitReceptionAchatValue value) {

		ProduitReceptionAchatEntity entity = new ProduitReceptionAchatEntity();

		entity.setId(value.getId());
		entity.setPrixUnitaire(value.getPrixUnitaire());
		entity.setQuantite(value.getQuantite());
		entity.setDateLivraison(value.getDateLivraison());
		entity.setProduit(value.getProduitId());
		entity.setSerialisable(value.isSerialisable());
		
		entity.setNumeroSeries(value.getNumeroSeries());
		
		entity.setPrixUnitaireHT(value.getPrixUnitaireHT());
		entity.setPrixTotalHT(value.getPrixTotalHT());
		entity.setRemise(value.getRemise());
		entity.setTaxeId(value.getTaxeId());
		entity.setReferenceArticle(value.getReferenceArticle());
		entity.setDesignation(value.getDesignation());
		
		entity.setUnite(value.getUnite());

		return entity;
	}

	/************************** toValueProduitCommande **********************************/

	public static ProduitReceptionAchatValue toValue(ProduitReceptionAchatEntity entity) {

		ProduitReceptionAchatValue value = new ProduitReceptionAchatValue();

		if (entity.getId() != null) {
			value.setId(entity.getId());
		}

		value.setPrixUnitaire(entity.getPrixUnitaire());
		value.setQuantite(entity.getQuantite());
		value.setDateLivraison(entity.getDateLivraison());

		value.setCommandeAchatId(entity.getReceptionAchat().getId());
		value.setProduitId(entity.getProduit());
		value.setSerialisable(entity.isSerialisable());
		value.setNumeroSeries(entity.getNumeroSeries());
		
		value.setPrixUnitaireHT(entity.getPrixUnitaireHT());
		value.setPrixTotalHT(entity.getPrixTotalHT());
		value.setRemise(entity.getRemise());
		value.setTaxeId(entity.getTaxeId());
		
		value.setReferenceArticle(entity.getReferenceArticle());
		value.setDesignation(entity.getDesignation());
		
		value.setUnite(entity.getUnite());
		
		/*
		if (entity.getProduit()!=null) {
			ProduitValue produitValue = produitPersistance.rechercheProduitById(entity.getProduit());
			value.setProduitDesignation(produitValue.getDesignation());
			value.setProduitReference(produitValue.getReference());
		}
		*/
	

		return value;
	}
	
	
	/************************** toValueProduitCommande  used in rechercher MC ProduitReceptionAchat **********************************/
	public static ProduitReceptionAchatValue toValueRecherche(ProduitReceptionAchatEntity entity) {

		ProduitReceptionAchatValue value = new ProduitReceptionAchatValue();

		if (entity.getId() != null) {
			value.setId(entity.getId());
		}

		value.setPrixUnitaire(entity.getPrixUnitaire());
		value.setQuantite(entity.getQuantite());
		value.setDateLivraison(entity.getDateLivraison());

		value.setCommandeAchatId(entity.getReceptionAchat().getId());
		value.setProduitId(entity.getProduit());
		value.setSerialisable(entity.isSerialisable());
		
		value.setNumeroSeries(entity.getNumeroSeries());
		
		value.setUnite(entity.getUnite());
		
		if(entity.getReceptionAchat() != null) {
			
			ReceptionAchatValue valueReceptionAchatValue = new ReceptionAchatValue();
			
			valueReceptionAchatValue.setSiteId(entity.getReceptionAchat().getSiteId());
			valueReceptionAchatValue.setReference(entity.getReceptionAchat().getReference());
			valueReceptionAchatValue.setRefexterne(entity.getReceptionAchat().getRefexterne());
			valueReceptionAchatValue.setRefexterne(entity.getReceptionAchat().getRefexterne());
			valueReceptionAchatValue.setPrixTotal(entity.getReceptionAchat().getPrixTotal());
			valueReceptionAchatValue.setDateIntroduction(entity.getReceptionAchat().getDateIntroduction());
			valueReceptionAchatValue.setDateLivraison(entity.getReceptionAchat().getDateLivraison());
			valueReceptionAchatValue.setObservations(entity.getReceptionAchat().getObservations());
			valueReceptionAchatValue.setPartieIntersseId(entity.getReceptionAchat().getPartieIntersseId());
			valueReceptionAchatValue.setIdDepot(entity.getReceptionAchat().getIdDepot());


			valueReceptionAchatValue.setBlSuppression(entity.getReceptionAchat().isBlSuppression());
			valueReceptionAchatValue.setDateSuppression(entity.getReceptionAchat().getDateSuppression());
			valueReceptionAchatValue.setDateModification(entity.getReceptionAchat().getDateModification());
			valueReceptionAchatValue.setDateCreation(entity.getReceptionAchat().getDateCreation());
			valueReceptionAchatValue.setQuantite(entity.getReceptionAchat().getQuantite());
			
			valueReceptionAchatValue.setRefAvoirRetour(entity.getReceptionAchat().getRefAvoirRetour());
			valueReceptionAchatValue.setRefBL(entity.getReceptionAchat().getRefBL());
			valueReceptionAchatValue.setRefFacture(entity.getReceptionAchat().getRefFacture());
			
			
			value.setReceptionAchatValue(valueReceptionAchatValue);
			
		}

		return value;
	}
	
	public BonReceptionVue toVue(ReceptionAchatEntity entity) {
		
		BonReceptionVue dto = new BonReceptionVue();
		dto.setReferenceBR(entity.getReference());
		
		return dto;
	}
	
	public ReceptionAchatFactureVue toFactureVue(ReceptionAchatEntity entity) {
		
		ReceptionAchatFactureVue dto = new ReceptionAchatFactureVue();
			
			if(entity.getId()!=null){
				dto.setId(entity.getId());
			}
			
			dto.setReference(entity.getReference());
			dto.setRefexterne(entity.getRefexterne());
	//		dto.setDate(entity.getDate());
		//	dto.setMontantHTaxe(entity.getMontantHTaxe());
	//		dto.setMontantTTC(entity.getMontantTTC());
	//		dto.setMontantTaxe(entity.getMontantTaxe());
		//	dto.setMontantRemise(entity.getMontantRemise());
			dto.setObservations(entity.getObservations());
		//	dto.setInfoSortie(entity.getInfoSortie());
		//	dto.setPartieIntId(entity.getPartieIntId());
			//dto.setBlSuppression(entity.isBlSuppression());
			dto.setDateSuppression(entity.getDateSuppression());
			dto.setDateCreation(entity.getDateCreation());
			dto.setDateModification(entity.getDateModification());
		//	dto.setVersion(entity.getVersion());
		//	dto.setEtat(entity.getEtat());
		//	dto.setTransporteur(entity.getTransporteur());
		//	dto.setMetrageTotal(entity.getMetrageTotal());
		//	dto.setNatureLivraison(entity.getNatureLivraison().trim());
		//	dto.setTotalPourcentageRemise(entity.getTotalPourcentageRemise());
		//	dto.setStock(entity.getStock());
			dto.setIdDepot(entity.getIdDepot());
		//	dto.setRefCommande(entity.getRefCommande());
		//	dto.setIdCamion(entity.getIdCamion());
		//	dto.setIdChauffeur(entity.getIdChauffeur());
		//    dto.setIdRemorque(entity.getIdRemorque());
			dto.setInfoProduit("");
			if(entity.getListProduitReceptions() != null){
		    	List<ProduitReceptionAchatValue> list = new ArrayList <ProduitReceptionAchatValue>();
			     for (ProduitReceptionAchatEntity detLivraisonVenteEntity : entity.getListProduitReceptions()) {
			    	 ProduitReceptionAchatValue det=toValue(detLivraisonVenteEntity);
			    	//System.out.println("##### Avantttttt  det.getProduitDesignation():   "+det.getProduitDesignation()); 
			    	
			    	 
			    	 /*if (!dto.getInfoProduit().contains(det.getProduitDesignation())){
			    
			    		String infoProduit =dto.getInfoProduit();
			    		
			    		infoProduit+="  +  ";
			    		infoProduit+=det.getProduitDesignation();
			    		dto.setInfoProduit(infoProduit);
		

			    		
			    	 }*/
			    }
			     //dto.setListDetLivraisonVente(list);
			}
		    
			//System.out.println("##### apressss  det.getProduitDesignation():   "+ dto.getInfoProduit());
		    
		 //   dto.setModifier(entity.isModifier());
		//    dto.setModepaiementId(entity.getModepaiementId());
		 //   dto.setMarcheId(entity.getMarcheId());
		 //   dto.setCamion(entity.getCamion());
		//    dto.setChauffeur(entity.getChauffeur());
		//    dto.setRemorque(entity.getRemorque());
		   
		  
			
			return dto;
		}
	
	public static TaxeReceptionAchatEntity toEntity(TaxeReceptionAchatValue dto) {
		
		TaxeReceptionAchatEntity entity = new TaxeReceptionAchatEntity();
		
		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setPourcentage(dto.getPourcentage());
//		entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setTaxeId(dto.getTaxeId());
		
		
		if(dto.getReceptionAchatId() != null){
			ReceptionAchatEntity receptionAchatEntity = new ReceptionAchatEntity();
			receptionAchatEntity.setId(dto.getReceptionAchatId());
			entity.setReceptionAchat(receptionAchatEntity);
		}
		
		return entity;
	}

	public static TaxeReceptionAchatValue toValue(TaxeReceptionAchatEntity entity) {
		
		TaxeReceptionAchatValue dto = new TaxeReceptionAchatValue();
		
		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setPourcentage(entity.getPourcentage());
//		dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setTaxeId(entity.getTaxeId());
		
		if(entity.getReceptionAchat() != null){
			dto.setReceptionAchatId(entity.getReceptionAchat().getId());
		}
		
		return dto;
	}
	
	
	// Documents Reception Achat 
	
	
	
	/** Document ReceptionAchatEntite to Document RecpetionAchatValue **/
	public static DocumentReceptionAchatValue toValue(
			DocumentReceptionAchatEntity pDocumentProduitEntity) {
		DocumentReceptionAchatValue documentProduitValue = new DocumentReceptionAchatValue();
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
	public static DocumentReceptionAchatEntity toEntity(
			DocumentReceptionAchatValue pDocumentProduitValue) {
		DocumentReceptionAchatEntity documentProduitEntity = new DocumentReceptionAchatEntity();
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
