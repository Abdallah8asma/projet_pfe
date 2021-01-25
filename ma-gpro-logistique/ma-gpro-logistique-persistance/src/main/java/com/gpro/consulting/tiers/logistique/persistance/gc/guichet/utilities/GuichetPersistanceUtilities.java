package com.gpro.consulting.tiers.logistique.persistance.gc.guichet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity.GuichetAnnuelEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity.GuichetMensuelEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class GuichetPersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(GuichetPersistanceUtilities.class);
	
	public GuichetAnnuelEntity toEntity(GuichetAnnuelValue dto) {
		
		GuichetAnnuelEntity entity = new GuichetAnnuelEntity();
		
		//entity.setId((long) 3);
		entity.setId(dto.getId());
		entity.setAnnee(dto.getAnnee());
		entity.setNumReferenceAvoirCourante(dto.getNumReferenceAvoirCourante());
		entity.setNumReferenceCommandeCourante(dto.getNumReferenceCommandeCourante());
		entity.setNumReferenceFactureCourante(dto.getNumReferenceFactureCourant());
		entity.setNumReferenceReglementCourante(dto.getNumReferenceReglementCourante());
		//System.out.println("to entity getNumReferenceAvoirCourante: " +dto.getNumReferenceAvoirCourante());
		
		entity.setNumReferenceBonLivraisonCourante(dto.getNumReferenceBonLivraisonCourante());
		
		entity.setNumReferenceBonComptoirCourante(dto.getNumReferenceBonComptoirCourante());
		
		
		entity.setNumReferenceBonReceptionCourante(dto.getNumReferenceBonReceptionCourante());
		entity.setNumReferenceFactureAchatCourante(dto.getNumReferenceFactureAchatCourante());
		entity.setNumReferenceReglementAchatCourante(dto.getNumReferenceReglementAchatCourante());
		
		
		entity.setPrefixeFacture(dto.getPrefixeFacture());
		entity.setPrefixeAvoir(dto.getPrefixeAvoir());
		
		
		entity.setPrefixeBcAchat(dto.getPrefixeBcAchat());
		
		entity.setPrefixeBL(dto.getPrefixeBL());
		entity.setPrefixeBonReception(dto.getPrefixeBonReception());
		
		
		entity.setPrefixeFactureAchat(dto.getPrefixeFactureAchat());
		
		entity.setPrefixeBC(dto.getPrefixeBC());
	
		entity.setNumReferenceCommandeAchatCourante(dto.getNumReferenceCommandeAchatCourante());
		
		entity.setNumReferenceDevisCourante(dto.getNumReferenceDevisCourante());
		entity.setNumReferenceDevisAchatCourante(dto.getNumReferenceDevisAchatCourante());
		
		entity.setPrefixeDevis(dto.getPrefixeDevis());
		entity.setPrefixeDevisAchat(dto.getPrefixeDevisAchat());
		
		entity.setNumFactureAvoirAchatCourante(dto.getNumFactureAvoirAchatCourante());
		entity.setPrefixeFactureAvoirAchat(dto.getPrefixeFactureAvoirAchat());
		
		
		entity.setNumBonStockCourante(dto.getNumBonStockCourante());
		entity.setPrefixeBonStock(dto.getPrefixeBonStock());
		
		entity.setPrefixeReglement(dto.getPrefixeReglement());
		entity.setPrefixeReglementAchat(dto.getPrefixeReglementAchat());
		
		entity.setNumBonInventaireCourante(dto.getNumBonInventaireCourante());
		entity.setPrefixeBonInventaire(dto.getPrefixeBonInventaire());
		
		entity.setNumBonTransfertReceptionCourante(dto.getNumBonTransfertReceptionCourante());
		entity.setNumBonTransfertSortieCourante(dto.getNumBonTransfertSortieCourante());
		entity.setPrefixeBonTransfertReception(dto.getPrefixeBonTransfertReception());
		entity.setPrefixeBonTransfertSortie(dto.getPrefixeBonTransfertSortie());
		
		
		entity.setNumBonMouvementEntre(dto.getNumBonMouvementEntre());
		entity.setNumBonMouvementSortie(dto.getNumBonMouvementSortie());
		
		entity.setNumReferenceBonLivraisonNDCourante(dto.getNumReferenceBonLivraisonNDCourante());
		entity.setPrefixeBLND(dto.getPrefixeBLND());
		entity.setNumReferenceFactureNDCourante(dto.getNumReferenceFactureNDCourante());
		entity.setPrefixeFAND(dto.getPrefixeFAND());
		return entity;
	}

	public static GuichetAnnuelValue toValue(GuichetAnnuelEntity entity) {
		
		GuichetAnnuelValue dto = new GuichetAnnuelValue();
		
		dto.setId(entity.getId());
		dto.setAnnee(entity.getAnnee());
		dto.setNumReferenceAvoirCourante(entity.getNumReferenceAvoirCourante());
		dto.setNumReferenceCommandeCourante(entity.getNumReferenceCommandeCourante());
		dto.setNumReferenceFactureCourant(entity.getNumReferenceFactureCourante());
		dto.setNumReferenceReglementCourante(entity.getNumReferenceReglementCourante());
		dto.setNumReferenceBonLivraisonCourante(entity.getNumReferenceBonLivraisonCourante());
		dto.setNumReferenceBonComptoirCourante(entity.getNumReferenceBonComptoirCourante());
		
		
		dto.setNumReferenceBonReceptionCourante(entity.getNumReferenceBonReceptionCourante());
		dto.setNumReferenceFactureAchatCourante(entity.getNumReferenceFactureAchatCourante());
		dto.setNumReferenceReglementAchatCourante(entity.getNumReferenceReglementAchatCourante());
		
		
		
		
		dto.setPrefixeFacture(entity.getPrefixeFacture());
		dto.setPrefixeAvoir(entity.getPrefixeAvoir());
		dto.setPrefixeBL(entity.getPrefixeBL());
		
		
	
		
		dto.setPrefixeBcAchat(entity.getPrefixeBcAchat());
		
	
		dto.setPrefixeBonReception(entity.getPrefixeBonReception());
		
		
		dto.setPrefixeFactureAchat(entity.getPrefixeFactureAchat());
		
		dto.setPrefixeBC(entity.getPrefixeBC());
	
		dto.setNumReferenceCommandeAchatCourante(entity.getNumReferenceCommandeAchatCourante());

		dto.setNumReferenceDevisCourante(entity.getNumReferenceDevisCourante());
		dto.setNumReferenceDevisAchatCourante(entity.getNumReferenceDevisAchatCourante());
		
		dto.setPrefixeDevis(entity.getPrefixeDevis());
		dto.setPrefixeDevisAchat(entity.getPrefixeDevisAchat());
		
		dto.setNumFactureAvoirAchatCourante(entity.getNumFactureAvoirAchatCourante());
		dto.setPrefixeFactureAvoirAchat(entity.getPrefixeFactureAvoirAchat());
		
		
		dto.setNumBonStockCourante(entity.getNumBonStockCourante());
		dto.setPrefixeBonStock(entity.getPrefixeBonStock());
		
		
		dto.setPrefixeReglement(entity.getPrefixeReglement());
		dto.setPrefixeReglementAchat(entity.getPrefixeReglementAchat());
		
		dto.setNumBonInventaireCourante(entity.getNumBonInventaireCourante());
		dto.setPrefixeBonInventaire(entity.getPrefixeBonInventaire());
		
		dto.setNumBonTransfertReceptionCourante(entity.getNumBonTransfertReceptionCourante());
		dto.setNumBonTransfertSortieCourante(entity.getNumBonTransfertSortieCourante());
		dto.setPrefixeBonTransfertReception(entity.getPrefixeBonTransfertReception());
		dto.setPrefixeBonTransfertSortie(entity.getPrefixeBonTransfertSortie());
		
		dto.setNumBonMouvementEntre(entity.getNumBonMouvementEntre());
		dto.setNumBonMouvementSortie(entity.getNumBonMouvementSortie());
		
		
		dto.setNumReferenceBonLivraisonNDCourante(entity.getNumReferenceBonLivraisonNDCourante());
		dto.setPrefixeBLND(entity.getPrefixeBLND());
		
		dto.setNumReferenceFactureNDCourante(entity.getNumReferenceFactureNDCourante());
		dto.setPrefixeFAND(entity.getPrefixeFAND());
		return dto;
	}
	
	
	public static GuichetMensuelValue toMensuelValue(GuichetMensuelEntity entity) {
		
		GuichetMensuelValue gmv = new GuichetMensuelValue();
		
		gmv.setId(entity.getId());		
		
		gmv.setAnnee(entity.getAnnee());
		gmv.setMois(entity.getMois());
		gmv.setNumReferenceAvoirCourante(entity.getNumReferenceAvoirCourante());
		gmv.setNumReferenceBonReceptionCourante(entity.getNumReferenceBonReceptionCourante());
		gmv.setNumReferenceFactureCourante(entity.getNumReferenceFactureCourante());
	
		gmv.setNumReferenceBonCommandeCourante(entity.getNumReferenceBonCommandeCourante());
		
		
		return gmv;
		
		
	}
	}
	
	
	
	

