package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;


public interface IMouvementDomaine {

	/********************recherche multi critere mouvement ****************/
 	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue);
     
	/************** liste mouvement stock **************/
	public List<MouvementStockValue> listeMouvementStock();
}
