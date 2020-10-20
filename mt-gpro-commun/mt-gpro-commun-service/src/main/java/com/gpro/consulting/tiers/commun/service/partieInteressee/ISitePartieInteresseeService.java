package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;

public interface ISitePartieInteresseeService {

  /********************** recherche categorie partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public SiteValue rechercheCategoriePartieInteresseeParId(SiteValue pSiteValue);

  /********************** afficher liste categorie partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < SiteValue > listeSitePartieInteressee();

	/**
	 * **************** ajouter site partie interesse ************.
	 *
	 * @param pSiteValue the site value
	 * @return the string
	 */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerSitePartieInteressee(SiteValue pSiteValue);

	/**
	 * ******************** supprimer site partie interesse ****************************.
	 *
	 * @param pSiteValue the site value
	 */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerSitePartieInteressee(SiteValue pSiteValue);

	/**
	 * ******************** modifier site partie interesse ****************************.
	 *
	 * @param pSiteValue the site value
	 * @return the string
	 */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierSitePartieInteressee(SiteValue pSiteValue);

	
	
}
