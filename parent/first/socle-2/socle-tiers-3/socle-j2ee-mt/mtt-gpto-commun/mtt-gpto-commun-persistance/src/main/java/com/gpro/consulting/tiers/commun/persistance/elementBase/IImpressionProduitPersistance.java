package com.gpro.consulting.tiers.commun.persistance.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;


/**
 * The Interface IUntieArticle.
 * @author $mohamed
 */

public interface IImpressionProduitPersistance {
	/**
	 * ajouter    unite Article*.
	 *
	 * @return the string
	 */
	/** create ImpressionProduit */
	public  String creerImpressionProduit(ImpressionProduitValue pImpressionProduitValue);
	/**
	 * supprimer  unite Article*.
	 *
	 */
	public  void supprimerImpressionProduit(Long pImpressionProduitValueId);
	/**
	 * modifier unite Article*.
	 *
	 * @return the string
	 */
	public String modifierImpressionProduit(ImpressionProduitValue pImpressionProduitValue);
	/**
	 *  recherche by id unite Article*.
	 *
	 */
	public ImpressionProduitValue  rechercheImpressionProduitById(Long  pImpressionProduitValueId);
	/**
	 * afficher  liste unite   Article*.
	 *
	 * @return the list
	 */
	public List<ImpressionProduitValue> listeImpressionProduit();

}
