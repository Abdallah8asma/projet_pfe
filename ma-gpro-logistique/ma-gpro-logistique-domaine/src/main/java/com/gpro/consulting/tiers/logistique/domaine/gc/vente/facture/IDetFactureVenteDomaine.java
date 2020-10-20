package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;

/**
 * DetFacture Vente Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface IDetFactureVenteDomaine {

	public String create(DetFactureVenteValue detFactureVenteValue);

	public DetFactureVenteValue getById(Long id);

	public String update(DetFactureVenteValue detFactureVenteValue);

	public void delete(Long id);

}
