package com.gpro.consulting.tiers.logistique.persistance.produitdepot.utilities;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.entities.ProduitDepotEntity;

/**
 * Classe Utilitaire permettant de convertir des objets valeur en entité et des
 * objets entité en objets valeur
 * 
 * @author DELL
 *
 */

@Component
public class PersistanceUtilitiesProduitDepot {

	//private IGestionnaireDocument vGestionnaireDocument;

	/**
	 * Instanciation du gestionnaire de persistance
	 */
//	private static PersistanceUtilities instance = new PersistanceUtilities();

	/**
	 * Méthode permettant l'accés au Gestionnaire de persistance
	 * 
	 * @return
	 */
//	public static PersistanceUtilities getInstance() {
//		return instance;
//	}

	// added on 25 03 2018

	/**
	 * Converstion entité à value du ProduitDepot/*
	 * 
	 * @param
	 * @return
	 */
	public static ProduitDepotValue toValue(ProduitDepotEntity pProduitDepotEntity) {
		ProduitDepotValue vProduitDepotValue = new ProduitDepotValue();

		/** The idproduit,iddepot,quantite */

		vProduitDepotValue.setId(pProduitDepotEntity.getId());
		vProduitDepotValue.setIdProduit(pProduitDepotEntity.getIdProduit());
		vProduitDepotValue.setIdDepot(pProduitDepotEntity.getIdDepot());
		vProduitDepotValue.setQuantite(pProduitDepotEntity.getQuantite());
		
		vProduitDepotValue.setSeuilMin(pProduitDepotEntity.getSeuilMin());
		vProduitDepotValue.setSeuilMax(pProduitDepotEntity.getSeuilMax());

		return vProduitDepotValue;

	}

	/*********************/
	/**
	 * Converstion value à entity du Produitdepot/*
	 * 
	 * @param
	 * @return
	 */
	public static ProduitDepotEntity toEntity(ProduitDepotValue pProduitDepotValue) {
		ProduitDepotEntity vProduitDepotEntity = new ProduitDepotEntity();

		vProduitDepotEntity.setId(pProduitDepotValue.getId());

		/** The idproduit. */

		vProduitDepotEntity.setIdProduit(pProduitDepotValue.getIdProduit());

		/** The iddepot. */

		vProduitDepotEntity.setIdDepot(pProduitDepotValue.getIdDepot());

		/** The quantite . */

		vProduitDepotEntity.setQuantite(pProduitDepotValue.getQuantite());
		
		vProduitDepotEntity.setSeuilMin(pProduitDepotValue.getSeuilMin());
		vProduitDepotEntity.setSeuilMax(pProduitDepotValue.getSeuilMax());

		return vProduitDepotEntity;
	}

}
