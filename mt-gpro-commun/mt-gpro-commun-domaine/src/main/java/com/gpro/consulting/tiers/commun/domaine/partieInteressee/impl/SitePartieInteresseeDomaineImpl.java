package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ISitePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ISitePartieInteresseePersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class SitePartieInteresseeDomaineImpl implements
		ISitePartieInteresseeDomaine {

	/** The site partie interesse persistance. */
	@Autowired
	ISitePartieInteresseePersistance sitePartieInteressePersistance;

	/* (non-Javadoc)
	 * rechercher par id site
	 */
	@Override
	public SiteValue rechercheSitePartieInteresseeParId(SiteValue pSiteValue) {
		return sitePartieInteressePersistance.rechercheSitePartieInteresseeParId(pSiteValue);
	}

	/* (non-Javadoc)
	 * lister site
	 */
	@Override
	public List<SiteValue> listeSitePartieInteressee() {
		return sitePartieInteressePersistance.listeSitePartieInteressee();
	}

	/* (non-Javadoc)
	 * creer site 
	 */
	@Override
	public String creerSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteressePersistance.creerSitePartieInteressee(pSiteValue);
	}

	/* (non-Javadoc)
	 * supprimer site 
	 */
	@Override
	public void supprimerSitePartieInteressee(SiteValue pSiteValue) {
		sitePartieInteressePersistance.supprimerSitePartieInteressee(pSiteValue);		
	}

	/* (non-Javadoc)
	 * modifier site
	 */
	@Override
	public String modifierSitePartieInteressee(SiteValue pSiteValue) {
		return sitePartieInteressePersistance.modifierSitePartieInteressee(pSiteValue);
	}

	
	

}
