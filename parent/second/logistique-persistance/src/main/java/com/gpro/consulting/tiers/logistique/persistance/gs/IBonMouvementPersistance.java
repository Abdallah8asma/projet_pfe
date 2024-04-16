package com.gpro.consulting.tiers.logistique.persistance.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonMouvementStockValue;

/**
 * The Interface IBonMouvementStockPersistance.
 */
public interface IBonMouvementPersistance {

	/**
	 * Rechercher produit multi critere.
	 *
	 * @param pRechercheMulticritereMouvementStockValue
	 *            the recherche multicritere mouvement stock value
	 * @return the resultat recheche bon mouvement stock value
	 */
	public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(
			RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue);

	/****************** methode creation bon mouvement ****************/
	public String creerBonMouvement(BonMouvementStockValue pBonMouvementStockValue);

	/******** modifier bonmouvement stock ***********/
	public String modifierBonMouvement(BonMouvementStockValue pBonMouvement);

	/******* list bonmouvement stock *********/
	public List<BonMouvementStockValue> listeBonMouvement();

	/**************** recherche bon mouvement par id ****************/
	public BonMouvementStockValue rechercheBonMouvementParId(Long pBonMouvementId);

	/******* get bonmouvement stock by type *********/
	public List<BonMouvementStockValue> getByTypeBonMouvement(String type);

	/******* supprimer bon mouvement *****/
	public void supprimerBonMouvement(Long pId);

	// Added on 09/11/2016, by Zeineb Medimagh
	/*******
	 * get liste Numeros Bon Mouvement par type (Reservation / entree/ sortie)
	 *********/
	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String type);

	/**************** recherche bon mouvement par numero ****************/
	public BonMouvementStockValue rechercheBonMouvementParNum(String pBonMouvementNum);

	// Added on 01/02/2017, by Hajer Amri
//	/**************** recherche bon mouvement par numero ****************/
//	public BonMouvementStockValue getBonMouvementParNum(String pBonMouvementNum);

}
