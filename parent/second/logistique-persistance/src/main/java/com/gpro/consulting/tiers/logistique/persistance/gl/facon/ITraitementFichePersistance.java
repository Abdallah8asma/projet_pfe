package com.gpro.consulting.tiers.logistique.persistance.gl.facon;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;

/**
 * Traitement fiche Persistance interface
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface ITraitementFichePersistance {

	public String create(TraitementFicheValue traitementFicheValue);

	public TraitementFicheValue getById(Long id);

	public String update(TraitementFicheValue traitementFicheValue);

	public void delete(Long id);

	public List<TraitementFicheValue> getAll();
	
	public String setTraitementPU( Long id , Double nouveauPU, Long idFiche);

}
