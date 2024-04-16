package com.gpro.consulting.tiers.logistique.persistance.gl.facon;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;

/**
 * Traitement façon Persistance interface
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface ITraitementFaconPersistance {

	public String create(TraitementFaconValue traitementFaconValue);

	public TraitementFaconValue getById(Long id);

	public String update(TraitementFaconValue traitementFaconValue);

	public void delete(Long id);

	public List<TraitementFaconValue> getAll();
}
