package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;


/**
 * DetFactureVente Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface IDetFactureVentePersistance {
	
	public String create(DetFactureVenteValue detFactureVenteValue);

	public DetFactureVenteValue getById(Long id);

	public String update(DetFactureVenteValue detFactureVenteValue);

	public void delete(Long id);

	public DetFactureVenteValue getByFactureVenteAndProduit(
			Long FactureVenteId, Long produitId, String choix);

}
