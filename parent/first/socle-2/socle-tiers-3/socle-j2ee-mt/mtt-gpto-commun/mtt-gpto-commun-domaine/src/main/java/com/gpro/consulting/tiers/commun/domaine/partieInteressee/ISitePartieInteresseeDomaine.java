package com.gpro.consulting.tiers.commun.domaine.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface ISitePartieInteresseeDomaine.
 */
public interface ISitePartieInteresseeDomaine {

	/**
	 * ********************recherche  site partie interesse par Id****************************.
	 *
	 * @param pSiteValue the site value
	 * @return the site value
	 */
	public SiteValue rechercheSitePartieInteresseeParId(SiteValue pSiteValue);
	
	/**
	 * ********************afficher  liste  categorie partie interesse ****************************.
	 *
	 * @return the list
	 */
	public List<SiteValue> listeSitePartieInteressee();
	
	/**
	 * **************** ajouter site partie interesse ************.
	 *
	 * @param pSiteValue the site value
	 * @return the string
	 */
	public String creerSitePartieInteressee(SiteValue pSiteValue);

	/**
	 * ******************** supprimer site partie interesse ****************************.
	 *
	 * @param pSiteValue the site value
	 */
	public void supprimerSitePartieInteressee(SiteValue pSiteValue);

	/**
	 * ******************** modifier site partie interesse ****************************.
	 *
	 * @param pSiteValue the site value
	 * @return the string
	 */
	public String modifierSitePartieInteressee(SiteValue pSiteValue);

	
	
	
}
