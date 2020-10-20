package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;

public interface IRaisonMouvementDomaine {


	  public String creerRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStock);

	  public void supprimerRaisonMouvementStock(Long pId);
	
	  public String modifierRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  public List < RaisonMouvementStockValue > listeRaisonMouvementStock();
	  
}
