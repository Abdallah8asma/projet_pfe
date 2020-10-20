package com.gpro.consulting.tiers.commun.persistance.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IRegionPersistance {
	
	public String creer(RegionValue pRegionValue);

	public RegionValue getById(Long pId);
	
	public void supprimer(Long pId);

	public String modifier(RegionValue pRegionValue);
	
	public List<RegionValue> listeRegion();
}
