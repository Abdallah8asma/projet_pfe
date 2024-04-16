package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;

public interface IRaisonFactureDomaine {


	  public String creerRaisonFacture(RaisonFactureValue pRaisonFacture);

	  public void supprimerRaisonFacture(Long pId);
	
	  public String modifierRaisonFacture(RaisonFactureValue pRaisonFactureValue);

	  public RaisonFactureValue rechercheRaisonFactureParId(RaisonFactureValue pRaisonFactureValue);

	  public List < RaisonFactureValue > listeRaisonFacture();
	  
}
