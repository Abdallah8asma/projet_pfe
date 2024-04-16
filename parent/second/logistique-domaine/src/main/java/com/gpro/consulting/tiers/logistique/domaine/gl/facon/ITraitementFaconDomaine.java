package com.gpro.consulting.tiers.logistique.domaine.gl.facon;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;

/**
 * Traitement fiche Domaine interface
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface ITraitementFaconDomaine {

	public String create(TraitementFaconValue traitementFaconValue);

	public TraitementFaconValue getById(Long id);

	public String update(TraitementFaconValue traitementFaconValue);

	public void delete(Long id);

	public List<TraitementFaconValue> getAll();

}
