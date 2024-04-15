package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IRegionDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IRegionService;

/**
 * @author Ameni Berrich
 *
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class RegionServiceImpl implements IRegionService{

	@Autowired
	IRegionDomaine regionDomaine;
	
	@Override
	public String creer(RegionValue pRegionValue) {
		return regionDomaine.creer(pRegionValue);
	}

	@Override
	public void supprimer(Long pId) {
		regionDomaine.supprimer(pId);
	}

	@Override
	public String modifier(RegionValue pRegionValue) {
		return regionDomaine.modifier(pRegionValue);
	}

	@Override
	public List<RegionValue> listeRegion() {
		return regionDomaine.listeRegion();
	}

	@Override
	public RegionValue getById(Long id) {
		return regionDomaine.getById(id);
	}

}
