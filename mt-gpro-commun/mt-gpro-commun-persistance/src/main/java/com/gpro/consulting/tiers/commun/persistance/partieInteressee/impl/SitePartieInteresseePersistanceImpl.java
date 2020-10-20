package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ISitePartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.SiteEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The ClassSitePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class SitePartieInteresseePersistanceImpl extends AbstractPersistance
		implements ISitePartieInteresseePersistance {
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SitePartieInteresseePersistanceImpl.class);

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * get entity manager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String creerSitePartieInteressee(SiteValue pSiteValue) {
		  SiteEntite siteEntite= (SiteEntite)this.creer(PersistanceUtilities.toEntite(pSiteValue));
		  SiteValue siteValueResult =PersistanceUtilities.toValue(siteEntite);
		  return siteValueResult.getId().toString();
	}

	@Override
	public void supprimerSitePartieInteressee(SiteValue pSiteValue) {
		this.supprimerEntite(SiteEntite.class, pSiteValue.getId().longValue());
	}

	@Override
	public String modifierSitePartieInteressee(SiteValue pSiteValue) {
		 SiteEntite siteEntite= (SiteEntite)this.modifier(PersistanceUtilities.toEntite(pSiteValue));
		  SiteValue siteValueResult =PersistanceUtilities.toValue(siteEntite);
		  return siteValueResult.getId().toString();
	}

	@Override
	public SiteValue rechercheSitePartieInteresseeParId(SiteValue pSiteValue) {
		if (LOGGER.isDebugEnabled()) {
		      //LOGGER.debug("Recherche de site en cours");
		    }
		    SiteEntite pSiteEntite = this.rechercherParId(pSiteValue.getId().longValue(),SiteEntite.class);
		    SiteValue vSiteValueResult = PersistanceUtilities.toValue(pSiteEntite);
		return vSiteValueResult;
	}

	@Override
	public List<SiteValue> listeSitePartieInteressee() {
		 List < SiteEntite > vListeSiteEntite = this.lister(SiteEntite.class);
		    List < SiteValue > vListSiteValues = new ArrayList < SiteValue >();
		    for (SiteEntite vSiteEntite : vListeSiteEntite) {
		    	vListSiteValues.add(PersistanceUtilities.toValue(vSiteEntite));
		    }
		    return vListSiteValues;
	}

	

}
