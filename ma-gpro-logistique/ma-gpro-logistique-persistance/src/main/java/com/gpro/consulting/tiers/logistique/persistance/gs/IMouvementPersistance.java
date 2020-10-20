package com.gpro.consulting.tiers.logistique.persistance.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;


public interface IMouvementPersistance {

	/**************** recherche multi critere mouvement stock *******************/
	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(
			RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue);

	/************** liste mouvement stock **************/
	public List<MouvementStockValue> listeMouvementStock();

	public ResultatRechecheMouvementValue rechercherEtatMouvement(RequestRechecheMouvementValue request);



}