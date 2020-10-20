package com.gpro.consulting.tiers.logistique.service.gl.facon;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;

/**
 * Traitement façon Service interface
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface ITraitementFaconService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(TraitementFaconValue traitementFaconValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public TraitementFaconValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(TraitementFaconValue traitementFaconValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<TraitementFaconValue> getAll();
	
}
