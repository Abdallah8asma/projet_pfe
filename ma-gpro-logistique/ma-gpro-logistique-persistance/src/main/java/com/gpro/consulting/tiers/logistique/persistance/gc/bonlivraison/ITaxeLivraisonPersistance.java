package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;

/**
 * TaxeLivraison Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface ITaxeLivraisonPersistance {
	
	public String create(TaxeLivraisonValue taxeLivraisonValue);

	public TaxeLivraisonValue getById(Long id);

	public String update(TaxeLivraisonValue taxeLivraisonValue);

	public void delete(Long id);

	public List<TaxeLivraisonValue> getAllByLivraisonId(Long livraisonId);

}
