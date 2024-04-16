package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;

/**
 * TaxeLivraison Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface ITaxeLivraisonDomaine {
	
	public String create(TaxeLivraisonValue taxeLivraisonValue);

	public TaxeLivraisonValue getById(Long id);

	public String update(TaxeLivraisonValue taxeLivraisonValue);

	public void delete(Long id);

}
