package com.gpro.consulting.tiers.logistique.service.gl.facon;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;

/**
 * Traitement fiche Service interface
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface ITraitementFicheService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TraitementFicheValue traitementFicheValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public TraitementFicheValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TraitementFicheValue traitementFicheValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<TraitementFicheValue> getAll();

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String setTraitementPU( Long id, Double nouveauPU, Long idFiche);
}
