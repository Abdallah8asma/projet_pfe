package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IRegionService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creer(RegionValue pRegionValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public RegionValue getById(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimer(Long pId);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifier(RegionValue pRegionValue);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RegionValue> listeRegion();
}
