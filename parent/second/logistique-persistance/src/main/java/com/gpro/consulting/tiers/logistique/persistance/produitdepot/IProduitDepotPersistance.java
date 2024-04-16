package com.gpro.consulting.tiers.logistique.persistance.produitdepot;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;

public interface IProduitDepotPersistance {
	
	
	/**
	 * Liste ProduitDepot.
	 *
	 * @return the list
	 */
	
	public  String create(ProduitDepotValue pProduitDepotValue);
	
	public String modifier(ProduitDepotValue pProduitDepotValue);
	
	public ProduitDepotValue getProduitDepotById(Long pProduitId, Long pDepotId);
	
	
	public ProduitDepotValue getByProduitAndDepot(Long pProduitId, Long pDepotId);
	
	public List<ProduitDepotValue> listeProduitDepot();

	public ResultatRechercheProduitDepotValue rechercheMulticritere(
			RechercheMulticritereProduitDepotValue request);
	
	
	

}
