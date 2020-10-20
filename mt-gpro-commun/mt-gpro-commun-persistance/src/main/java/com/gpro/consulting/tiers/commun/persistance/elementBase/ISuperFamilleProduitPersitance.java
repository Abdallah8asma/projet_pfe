package com.gpro.consulting.tiers.commun.persistance.elementBase;
import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SuperFamilleProduitValue;
/**
 * The Interface ISuperFamilleProduitPersitance.
 * @author med
 */
public interface ISuperFamilleProduitPersitance {
	
	/**
	 * Creer SuperFamille produit.
	 *
	 * @param pSuperFamilleProduitValue the SuperFamille produit value
	 * @return the string
	 */
	public  String creerSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue);
	
	/**
	 * Supprimer sous SuperFamille produit.
	 *
	 * @param pSuperFamilleProduitId the SuperFamille produit id
	 */
	public  void supprimerSuperFamilleProduit(Long pSuperFamilleProduitId);
	
	/**
	 * Modifier SuperFamille produit.
	 *
	 * @param pSuperFamilleProduitValue the SuperFamille produit value
	 * @return the string
	 */
	public String modifierSuperFamilleProduit(SuperFamilleProduitValue pSuperFamilleProduitValue);
	
	/**
	 * Recherche SuperFamille produit by id.
	 *
	 * @param pSuperFamilleProduitId the SuperFamille produit id
	 * @return the SuperFamille produit value
	 */
	public SuperFamilleProduitValue rechercheSuperFamilleProduitById(Long pSuperFamilleProduitId);
	
	/**
	 * Liste SuperFamille produit.
	 *
	 * @return the list
	 */
	public List<SuperFamilleProduitValue> listeSuperFamilleProduit();
}
