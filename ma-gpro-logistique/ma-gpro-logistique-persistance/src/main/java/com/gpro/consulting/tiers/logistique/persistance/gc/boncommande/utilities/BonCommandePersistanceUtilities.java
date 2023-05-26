package com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.TaxeCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.CommandeVenteEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.ProduitCommandeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.entitie.TaxeCommandeEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie.DetFactureVenteEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Zeineb
 * @since 16/11/2016
 *
 */

@Component
public class BonCommandePersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(BonCommandePersistanceUtilities.class);

	/************************** toEntityCommandeVente **********************************/

	public static CommandeVenteEntity toEntity(CommandeVenteValue value) {

		CommandeVenteEntity entity = new CommandeVenteEntity();

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
		
		entity.setLivre(value.getLivre());
		
		entity.setReglementId(value.getReglementId());
		
		entity.setBoutiqueId(value.getBoutiqueId());
		
		entity.setDevise(value.getDevise());
		entity.setMontantConverti(value.getMontantConverti());
		entity.setTauxConversion(value.getTauxConversion());
		

		if (value.getListProduitCommandes() != null) {

			List<ProduitCommandeEntity> listProduitCommandes = new ArrayList<ProduitCommandeEntity>();

			for (ProduitCommandeValue produitCommandeValue : value.getListProduitCommandes()) {

				ProduitCommandeEntity produitCommandeEntity = toEntity(produitCommandeValue);
				produitCommandeEntity.setCommandeVente(entity);
				listProduitCommandes.add(produitCommandeEntity);
			}

			entity.setListProduitCommandes(listProduitCommandes);
		}

		if (value.getListProduitTaxes() != null) {

			List<TaxeCommandeEntity> listProduitTaxes = new ArrayList<TaxeCommandeEntity>();

			for (TaxeCommandeValue taxeCommandeValue : value.getListProduitTaxes()) {

				TaxeCommandeEntity taxeCommandeEntity = toEntity(taxeCommandeValue);
				taxeCommandeEntity.setCommandeVente(entity);
				// TaxeCommandeEntity.setCommandeVente(entity);
				listProduitTaxes.add(taxeCommandeEntity);
			}

			entity.setListTaxeCommande(listProduitTaxes);
		}

		return entity;
	}

	/************************** toValueCommandeVente **********************************/

	public static CommandeVenteValue toValue(CommandeVenteEntity entity) {

		CommandeVenteValue value = new CommandeVenteValue();

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
		
		
		value.setLivre(entity.getLivre());
		
		value.setReglementId(entity.getReglementId());
		
		value.setBoutiqueId(entity.getBoutiqueId());
		
		
		value.setDevise(entity.getDevise());
		value.setMontantConverti(entity.getMontantConverti());
	    value.setTauxConversion(entity.getTauxConversion());

		/**********/
		if (entity.getListProduitCommandes() != null) {

			List<ProduitCommandeValue> listProduitCommandes = new ArrayList<ProduitCommandeValue>();

			for (ProduitCommandeEntity produitCommandeEntity : entity.getListProduitCommandes()) {

				ProduitCommandeValue produitCommandeValue = toValue(produitCommandeEntity);
				listProduitCommandes.add(produitCommandeValue);
			}

			value.setListProduitCommandes(listProduitCommandes);
		}

		/**********/
		if (entity.getListTaxeCommande() != null) {

			List<TaxeCommandeValue> listTaxeCommandes = new ArrayList<TaxeCommandeValue>();

			for (TaxeCommandeEntity taxeCommandeEntity : entity.getListTaxeCommande()) {

				TaxeCommandeValue taxeCommandeValue = toValue(taxeCommandeEntity);
				listTaxeCommandes.add(taxeCommandeValue);
			}

			value.setListProduitTaxes(listTaxeCommandes);
		}

		//logger.info("PI tovalue" + value.getPartieIntersseId());

		return value;
	}

	/************************** toEntityProduitCommande **********************************/

	public static ProduitCommandeEntity toEntity(ProduitCommandeValue value) {

		ProduitCommandeEntity entity = new ProduitCommandeEntity();

		entity.setId(value.getId());
		entity.setPrixUnitaire(value.getPrixUnitaire());
		entity.setQuantite(value.getQuantite());
		entity.setQuantiteLivree(value.getQuantiteLivree());
		entity.setDateLivraison(value.getDateLivraison());
		entity.setProduit(value.getProduitId());
		
		entity.setRemise(value.getRemise());
		entity.setTaxeId(value.getTaxeId());
	

		return entity;
	}

	/************************** toValueProduitCommande **********************************/

	public static ProduitCommandeValue toValue(ProduitCommandeEntity entity) {

		ProduitCommandeValue value = new ProduitCommandeValue();

		if (entity.getId() != null) {
			value.setId(entity.getId());
		}

		value.setPrixUnitaire(entity.getPrixUnitaire());
		value.setQuantite(entity.getQuantite());
		value.setQuantiteLivree(entity.getQuantiteLivree());
		value.setDateLivraison(entity.getDateLivraison());

		value.setCommandeVenteId(entity.getCommandeVente().getId());
		value.setProduitId(entity.getProduit());
		
		value.setRemise(entity.getRemise());
		value.setTaxeId(entity.getTaxeId());

		return value;
	}

	public static TaxeCommandeEntity toEntity(TaxeCommandeValue dto) {

		TaxeCommandeEntity entity = new TaxeCommandeEntity();

		entity.setId(dto.getId());
		entity.setMontant(dto.getMontant());
		entity.setPourcentage(dto.getPourcentage());

		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setTaxeId(dto.getTaxeId());

		/*
		 * if(dto.getCommandeVenteId() != null){ CommandeVenteEntity
		 * commandeVenteEntity = new CommandeVenteEntity();
		 * commandeVenteEntity.setId(dto.getCommandeVenteId());
		 * entity.setCommandeVente(commandeVenteEntity); }
		 */

		return entity;
	}

	public static TaxeCommandeValue toValue(TaxeCommandeEntity entity) {

		TaxeCommandeValue dto = new TaxeCommandeValue();

		dto.setId(entity.getId());
		dto.setMontant(entity.getMontant());
		dto.setPourcentage(entity.getPourcentage());
		// dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setTaxeId(entity.getTaxeId());

		if (entity.getCommandeVente() != null) {
			dto.setCommandeVenteId(entity.getCommandeVente().getId());
		}

		return dto;
	}
	public static   ProduitCommandeValue toValueEnrichi(ProduitCommandeEntity entity) 
	{
		ProduitCommandeValue produitCommandeValue=toValue(entity);
		if(entity.getCommandeVente() != null)
		{
			produitCommandeValue.setPartieIntersseId(entity.getCommandeVente().getPartieIntersseId());
			produitCommandeValue.setDateCommande(entity.getCommandeVente().getDateIntroduction());
			produitCommandeValue.setReferenceCommande(entity.getCommandeVente().getReference());
			produitCommandeValue.setMontantTaxe(entity.getCommandeVente().getMontantTaxe());
			produitCommandeValue.setMontantRemise(entity.getCommandeVente().getMontantRemise());
			produitCommandeValue.setMontantHTTotal(entity.getCommandeVente().getMontantTTC());
			produitCommandeValue.setDeviseId(entity.getCommandeVente().getDevise());
		
		
			
			
		    
		}
		return produitCommandeValue;
		
			
		
	}
}
