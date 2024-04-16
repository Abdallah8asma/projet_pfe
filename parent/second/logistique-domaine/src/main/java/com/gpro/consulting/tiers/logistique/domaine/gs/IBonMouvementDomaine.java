package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonMouvementStockValue;


public interface IBonMouvementDomaine {
	
	/******** recherche multi critere  bonmouvement stock ***********/
 	public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue);
  	
 	/******** creer bonmouvement stock ***********/
 	public String creerBonMouvement(BonMouvementStockValue pBonMouvementStockValue);

	/******** modifier bonmouvement stock ***********/
	public String modifierBonMouvement(BonMouvementStockValue pBonMouvement);


	/******* list bonmouvement stock *********/
	public List<BonMouvementStockValue> listeBonMouvement();
	

	/*******supprimer bon mouvement*****/
	public void supprimerBonMouvement(Long pId);
	

	/******* get bonmouvement stock by type*********/
	public List<BonMouvementStockValue> getByTypeBonMouvement(String type);
	
	/****************recherche bon mouvement par id****************/
  	public BonMouvementStockValue rechercheBonMouvementParId(Long pBonMouvementId);
  	
  //Added on 09/11/2016, by Zeineb Medimagh
  	/******* get liste Numeros Bon Mouvement par type (Reservation / entree/ sortie)*********/
  	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String type);
  	
  	 //Added on 09/11/2016, by Zeineb Medimagh
  	//gpao
//  	/******* get List Mouvement OF Vue - sortie*********/
//  	public List<MouvementOfVue> getListeMouvementsSortie(Long bonMouvementId);
  	
  	/****************recherche bon mouvement par numero****************/
  	public BonMouvementStockValue rechercheBonMouvementParNum(String pBonMouvementNum);
  	
  //Added on 01/02/2017, by Hajer Amri
//	public BonMouvementStockValue getBonMouvementParNum(String numero);
}
