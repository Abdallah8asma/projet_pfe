package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ISitePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ISitePartieInteresseeService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class SitePartieInteresseeServiceImpl implements ISitePartieInteresseeService {
	@Autowired
	ISitePartieInteresseeDomaine sitePartieInteresseeDomaine;

	@Override
	public SiteValue rechercheCategoriePartieInteresseeParId(SiteValue pSiteValue) {
		return sitePartieInteresseeDomaine.rechercheSitePartieInteresseeParId(pSiteValue);
	}

	@Override
	public List<SiteValue> listeSitePartieInteressee() {
		 return sitePartieInteresseeDomaine.listeSitePartieInteressee();
	}

	@Override
	public String creerSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteresseeDomaine.creerSitePartieInteressee(pSiteValue);
	}

	@Override
	public void supprimerSitePartieInteressee(SiteValue pSiteValue) {
		sitePartieInteresseeDomaine.supprimerSitePartieInteressee(pSiteValue);		
	}

	@Override
	public String modifierSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteresseeDomaine.modifierSitePartieInteressee(pSiteValue);
	}
  

}
