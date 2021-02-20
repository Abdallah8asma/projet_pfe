package com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.TaxeCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.entitie.CommandeAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.entitie.ProduitCommandeAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.entitie.TaxeCommandeAchatEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Hamdi Etteieb
 * @since 16/09/2018
 *
 */

@Component
public class BonCommandeAchatPersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(BonCommandeAchatPersistanceUtilities.class);

	/************************** toEntityCommandeAchat **********************************/

	public static CommandeAchatEntity toEntity(CommandeAchatValue value) {

		CommandeAchatEntity entity = new CommandeAchatEntity();

		if (value.getId() != null) {
			entity.setId(value.getId());
		}

		entity.setSiteId(value.getSiteId());
		entity.setReference(value.getReference());
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
		entity.setType(value.getType());

		entity.setMontantHTaxe(value.getMontantHTaxe());
		entity.setMontantRemise(value.getMontantRemise());
		entity.setMontantTaxe(value.getMontantTaxe());
		entity.setMontantTTC(value.getMontantTTC());
		
		
		entity.setReception(value.getReception());
		entity.setBoutiqueId(value.getBoutiqueId());

		if (value.getListProduitCommandes() != null) {

			List<ProduitCommandeAchatEntity> listProduitCommandes = new ArrayList<ProduitCommandeAchatEntity>();

			for (ProduitCommandeAchatValue ProduitCommandeAchatValue : value.getListProduitCommandes()) {

				ProduitCommandeAchatEntity ProduitCommandeAchatEntity = toEntity(ProduitCommandeAchatValue);
				ProduitCommandeAchatEntity.setCommandeAchat(entity);
				listProduitCommandes.add(ProduitCommandeAchatEntity);
			}

			entity.setListProduitCommandes(listProduitCommandes);
		}

		if (value.getListProduitTaxes() != null) {

			List<TaxeCommandeAchatEntity> listProduitTaxes = new ArrayList<TaxeCommandeAchatEntity>();

			for (TaxeCommandeAchatValue TaxeCommandeAchatValue : value.getListProduitTaxes()) {

				TaxeCommandeAchatEntity TaxeCommandeAchatEntity = toEntity(TaxeCommandeAchatValue);
				TaxeCommandeAchatEntity.setCommandeAchat(entity);
				// TaxeCommandeAchatEntity.setCommandeAchat(entity);
				listProduitTaxes.add(TaxeCommandeAchatEntity);
			}

			entity.setListTaxeCommande(listProduitTaxes);
		}

		return entity;
	}

	/************************** toValueCommandeAchat **********************************/

	public static CommandeAchatValue toValue(CommandeAchatEntity entity) {

		CommandeAchatValue value = new CommandeAchatValue();

		if (entity.getId() != null) {
			value.setId(entity.getId());
		}

		value.setSiteId(entity.getSiteId());
		value.setReference(entity.getReference());
		value.setPrixTotal(entity.getPrixTotal());
		value.setDateIntroduction(entity.getDateIntroduction());
		value.setDateLivraison(entity.getDateLivraison());
		value.setObservations(entity.getObservations());
		value.setPartieIntersseId(entity.getPartieIntersseId());

		//logger.info("PI tovalue" + value.getPartieIntersseId());

		value.setBlSuppression(entity.isBlSuppression());
		value.setDateSuppression(entity.getDateSuppression());
		value.setDateModification(entity.getDateModification());
		value.setDateCreation(entity.getDateCreation());
		value.setQuantite(entity.getQuantite());
		value.setType(entity.getType());
		/*******/
		value.setMontantHTaxe(entity.getMontantHTaxe());
		value.setMontantRemise(entity.getMontantRemise());
		value.setMontantTaxe(entity.getMontantTaxe());
		value.setMontantTTC(entity.getMontantTTC());
		
		value.setReception(entity.getReception());
		value.setBoutiqueId(entity.getBoutiqueId());

		/**********/
		if (entity.getListProduitCommandes() != null) {

			List<ProduitCommandeAchatValue> listProduitCommandes = new ArrayList<ProduitCommandeAchatValue>();

			for (ProduitCommandeAchatEntity ProduitCommandeAchatEntity : entity.getListProduitCommandes()) {

				ProduitCommandeAchatValue ProduitCommandeAchatValue = toValue(ProduitCommandeAchatEntity);
				listProduitCommandes.add(ProduitCommandeAchatValue);
			}

			value.setListProduitCommandes(listProduitCommandes);
		}

		/**********/
		if (entity.getListTaxeCommande() != null) {

			List<TaxeCommandeAchatValue> listTaxeCommandes = new ArrayList<TaxeCommandeAchatValue>();

			for (TaxeCommandeAchatEntity TaxeCommandeAchatEntity : entity.getListTaxeCommande()) {

				TaxeCommandeAchatValue TaxeCommandeAchatValue = toValue(TaxeCommandeAchatEntity);
				listTaxeCommandes.add(TaxeCommandeAchatValue);
			}

			value.setListProduitTaxes(listTaxeCommandes);
		}

		//logger.info("PI tovalue" + value.getPartieIntersseId());

		return value;
	}

	/************************** toEntityProduitCommande **********************************/

	public static ProduitCommandeAchatEntity toEntity(ProduitCommandeAchatValue value) {

		ProduitCommandeAchatEntity entity = new ProduitCommandeAchatEntity();

		entity.setId(value.getId());
		entity.setPrixUnitaire(value.getPrixUnitaire());
		entity.setQuantite(value.getQuantite());
		entity.setDateLivraison(value.getDateLivraison());
		entity.setProduit(value.getProduitId());
		entity.setTaxeId(value.getTaxeId());
		
		entity.setUnite(value.getUnite());

		return entity;
	}

	/************************** toValueProduitCommande **********************************/

	public static ProduitCommandeAchatValue toValue(ProduitCommandeAchatEntity entity) {

		ProduitCommandeAchatValue value = new ProduitCommandeAchatValue();

		if (entity.getId() != null) {
			value.setId(entity.getId());
		}

		value.setPrixUnitaire(entity.getPrixUnitaire());
		value.setQuantite(entity.getQuantite());
		value.setDateLivraison(entity.getDateLivraison());

		value.setCommandeAchatId(entity.getCommandeAchat().getId());
		value.setProduitId(entity.getProduit());
		value.setTaxeId(entity.getTaxeId());
		
		value.setUnite(entity.getUnite());

		return value;
	}

	public static TaxeCommandeAchatEntity toEntity(TaxeCommandeAchatValue dto) {

		TaxeCommandeAchatEntity entity = new TaxeCommandeAchatEntity();

		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setPourcentage(dto.getPourcentage());

		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setTaxeId(dto.getTaxeId());

		/*
		 * if(dto.getCommandeAchatId() != null){ CommandeAchatEntity
		 * CommandeAchatEntity = new CommandeAchatEntity();
		 * CommandeAchatEntity.setId(dto.getCommandeAchatId());
		 * entity.setCommandeAchat(CommandeAchatEntity); }
		 */

		return entity;
	}

	public static TaxeCommandeAchatValue toValue(TaxeCommandeAchatEntity entity) {

		TaxeCommandeAchatValue dto = new TaxeCommandeAchatValue();

		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setPourcentage(entity.getPourcentage());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setTaxeId(entity.getTaxeId());

		if (entity.getCommandeAchat() != null) {
			dto.setCommandeAchatId(entity.getCommandeAchat().getId());
		}

		return dto;
	}

}
