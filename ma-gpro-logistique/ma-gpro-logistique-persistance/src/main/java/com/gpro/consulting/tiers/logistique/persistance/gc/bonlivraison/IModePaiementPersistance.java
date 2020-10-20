package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;

/**
 * ModePaiement Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 19/02/2016
 *
 */
public interface IModePaiementPersistance {

	public String create(ModePaiementValue modePaiementValue);

	public ModePaiementValue getById(Long id);

	public String update(ModePaiementValue modePaiementValue);

	public void delete(Long id);

	public List<ModePaiementValue> getAll();
}
