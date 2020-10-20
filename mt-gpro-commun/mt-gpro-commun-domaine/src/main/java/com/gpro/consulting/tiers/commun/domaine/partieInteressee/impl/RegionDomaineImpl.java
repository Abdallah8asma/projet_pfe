package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IRegionDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IRegionPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class RegionDomaineImpl implements IRegionDomaine{

	@Autowired
	IRegionPersistance regionPersistance;
	
	@Override
	public String creer(RegionValue pRegionValue) {
	return regionPersistance.creer(pRegionValue);
	}

	@Override
	public void supprimer(Long pId) {
		regionPersistance.supprimer(pId);
		
	}

	@Override
	public String modifier(RegionValue pRegionValue) {
		return regionPersistance.modifier(pRegionValue);
	}

	@Override
	public List<RegionValue> listeRegion() {
		return regionPersistance.listeRegion();
	}

	@Override
	public RegionValue getById(Long pId) {
		return regionPersistance.getById(pId);
	}

}
