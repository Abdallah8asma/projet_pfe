package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.domaine.gs.IGestionnaireCacheDomaine;

/**
 * 
 * @author $Author: mohamed $
 * @version $Revision: 0 $
 */

@Component
public class GestionnaireCacheDomaineImpl implements IGestionnaireCacheDomaine {
	
	@Autowired
	private IPartieInteresseeService partieInteresseService;

	@Override
	public List<PartieInteresseCacheValue> getListePartieInteressee() {
		return partieInteresseService.listePartieInteresseeCache();

	}
	

}
