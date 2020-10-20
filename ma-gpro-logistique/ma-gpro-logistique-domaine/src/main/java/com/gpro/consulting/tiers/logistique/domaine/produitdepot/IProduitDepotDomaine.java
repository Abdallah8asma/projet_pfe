package com.gpro.consulting.tiers.logistique.domaine.produitdepot;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;

public interface IProduitDepotDomaine {

	/**
	 * Liste ProduitDepot.
	 *
	 * @return the list
	 */
	public List<ProduitDepotValue> listProduitDepot();

	/** recherche multicriteres produitId,depotid */

	public ResultatRechercheProduitDepotValue rechercherMultiCritereProduitDepot(
			RechercheMulticritereProduitDepotValue prRechercheMulticritereProduitDepotValue);

	public String creer(ProduitDepotValue pProduitDepotValue);

	public String modifier(ProduitDepotValue pProduitDepotValue);

	public ProduitDepotValue getProduitDepotById(Long pProduitId, Long pDepotId);

}
