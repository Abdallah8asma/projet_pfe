package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitCouleurValue;


public interface IProduitCouleurPersistance {
	
	/**
	 * Retourner liste des ProduitCouleur d'un produit 
	 * @param pIdProduit: id d'uzn produit
	 */
	List < ProduitCouleurValue > ListeProduitCouleur(Long pIdProduit);

}
