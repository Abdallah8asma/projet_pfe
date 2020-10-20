package com.gpro.consulting.tiers.commun.persistance.elementBase;
import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
/**
 * The Interface ISousFamilleProduitPersistance.
 * @author med
 */
public interface ISousFamilleProduitPersistance {
	
	/**
	 * Creer sous famille produit.
	 *
	 * @param pSousFamilleProduitValue the sous famille produit value
	 * @return the string
	 */
	public  String creerSousFamilleProduit(SousFamilleProduitValue pSousFamilleProduitValue);
	
	/**
	 * Supprimer sous famille produit.
	 *
	 * @param pSousFamilleProduitId the sous famille produit id
	 */
	public  void supprimerSousFamilleProduit(Long pSousFamilleProduitId);
	
	/**
	 * Modifier sous famille produit.
	 *
	 * @param pSousFamilleProduitValue the sous famille produit value
	 * @return the string
	 */
	public String modifierSousFamilleProduit(SousFamilleProduitValue pSousFamilleProduitValue);
	
	/**
	 * Recherche sous famille produit by id.
	 *
	 * @param pSousFamilleProduitId the sous famille produit id
	 * @return the sous famille produit value
	 */
	public SousFamilleProduitValue rechercheSousFamilleProduitById(Long pSousFamilleProduitId);
	
	/**
	 * Liste sous famille produit.
	 *
	 * @return the list
	 */
	public List<SousFamilleProduitValue> listeSousFamilleProduit();
}
