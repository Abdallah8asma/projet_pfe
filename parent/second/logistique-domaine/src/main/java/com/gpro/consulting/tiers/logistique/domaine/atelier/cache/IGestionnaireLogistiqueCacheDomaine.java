package com.gpro.consulting.tiers.logistique.domaine.atelier.cache;

import java.util.List;
import java.util.Map;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;

/**
 * @author Ameni
 *
 */
public interface IGestionnaireLogistiqueCacheDomaine {

	/**
	 * Méthode de récupératiuon de la liste des clients
	 * 
	 * @return List
	 */
	public List<PartieInteresseCacheValue> getListePartieInteressee();

	/**
	 * Méthode de récupératiuon de la liste des produits
	 * 
	 * @return List
	 */
	public List<ProduitValue> getListeProduit();
	
	/**
	 * Méthode de récupératiuon de la liste des sousFamilleProduit
	 * 
	 * @return List
	 */
	public List<SousFamilleProduitValue> getListeSousFamilleProduit();
	
	
	
	public Map<Long, PartieInteresseValue> mapClientById();
	
	public Map<Long, ProduitValue> mapProduitById();

	public Map<Long, GroupeClientValue> mapGroupePIById();

}
