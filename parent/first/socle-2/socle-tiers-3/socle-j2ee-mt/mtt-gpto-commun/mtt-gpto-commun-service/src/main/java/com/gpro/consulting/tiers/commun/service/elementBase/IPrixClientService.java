package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;


public interface IPrixClientService {
	/**
	   * Méthode de création d'un prix client 
	   * @param pArticleValue
	   * @return
	   */
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	  public String creerPrixClient(List <ProduitValue> pProduitValue);

	  /**
	   * Méthode de suppression d'un prix client par id.
	   * 
	   * @param long1
	   */
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	  public void supprimerPrixClient(Long long1);

	  /**
	   * Méthode de recheche d'un article par id retournant un objet Entité.
	   * 
	   * @param pArticleValue
	   * @return
	   */
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	  public String modifierPrixClient(PrixClientValue pPrixClientValue);

	  /**
	   * Méthode de recherche d'un article par id retournant un objet Valeur.
	   * 
	   * @param pArticleValue
	   * @return
	   */
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public PrixClientValue recherchePrixClientParId(Long pId);
	  /**
	   * Méthode de recherche des parties interessees retournant un objet Valeur.
	   * 
	   * @return
	   */
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public List < PrixClientValue > listePrixClient();

	  public PrixClientValue rechercherPrixClientValue(
			RecherchePrixClientValue pRecherchePrixClientMulitCritere);

	  /**
	   * Méthode de recherche des articles par critères
	   * 
	   * @return
	   */
	  /*@Transactional(readOnly = true, rollbackFor = Exception.class)
	  public ResultatRechechePrixClientValue rechercherPrixClientMultiCritere(
			  RecherecheMulticriterePrixClientValue pRecherchePrixClientMulitCritere);
	

		 /**
		 * @return the list article cache(designation,reference,id)
		 */
		//@Transactional(readOnly = true, rollbackFor = Exception.class)
		//public List<ArticleCacheValue> listeArticleCache();
	  
		public List<PrixClientValue> rechchercheMultiCriterePrixClient(
				RecherchePrixClientValue pRecherchePrixClientMulitCritere);
		
		
		  @Transactional(readOnly = false, rollbackFor = Exception.class)
	      public String creerPrixArticleClient(List<ArticleValue> pProduitValue);

	
}
