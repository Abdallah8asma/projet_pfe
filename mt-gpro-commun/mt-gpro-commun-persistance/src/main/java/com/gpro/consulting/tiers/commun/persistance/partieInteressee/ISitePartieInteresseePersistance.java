package com.gpro.consulting.tiers.commun.persistance.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;

public interface ISitePartieInteresseePersistance {
	/****************** ajouter site partie interesse *************/
	public String creerSitePartieInteressee(SiteValue pSiteValue);

	/********************** supprimer site partie interesse *****************************/
	public void supprimerSitePartieInteressee(SiteValue pSiteValue);

	/********************** modifier site partie interesse *****************************/
	public String modifierSitePartieInteressee(SiteValue pSiteValue);

	/********************** recherche site partie interesse par Id *****************************/
	public SiteValue rechercheSitePartieInteresseeParId(
			SiteValue pSiteValue);

	/********************** afficher liste site partie interesse *****************************/
	public List<SiteValue> listeSitePartieInteressee();

}
