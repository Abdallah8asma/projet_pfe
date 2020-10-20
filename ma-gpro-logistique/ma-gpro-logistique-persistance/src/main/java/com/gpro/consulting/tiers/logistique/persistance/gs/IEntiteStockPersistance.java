package com.gpro.consulting.tiers.logistique.persistance.gs;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechercheEntiteStockMouvementValue;



public interface IEntiteStockPersistance {

	/****************recherche entite stock par id****************/
  	public EntiteStockValue rechercheEntiteStockParId(Long pEntiteStockId);

	/******** recherche entite stock  for  Mouvement *******/
	public ResultatRechercheEntiteStockMouvementValue rechercherEntiteStockMouvement(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue);
	
	/******** recherche multi critere entite stock *******/
	public ResultatRechecheEntiteStockStockValue rechercherEntiteStockMultiCritere(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue);

	/******* creer entite stock ******/
	public String creerEntiteStock(EntiteStockValue pEntiteStockValue);

	/******** modifier entite stock ***********/
	public String modifierEntiteStock(EntiteStockValue pEntiteStockValue);

	/********* recherche entite stock by article and magasin *********/
	public EntiteStockValue rechercheEntiteStockByArticleMagasin(
			Long IdArticle, Long IdMagsin);

	/******* list entite stock *********/
	public List<EntiteStockValue> listeEntiteStock();
	
	/*******supprimer enti stock*****/
	public void supprimerEntiteStock(Long pId);
	
	
	public List<EntiteStockValue> getByArticleIdMagasinId(Long articleId, Long magsinId);

	public EntiteStockValue rechercheEntiteStockByLotMagasin(Long IdArticle,
			String referenceLot, Long IdMagsin);

	public EntiteStockValue rechercheEntiteStockByLotDateMagasin(Long IdArticle,
			String referenceLot, Long IdMagsin, Calendar date);
}
