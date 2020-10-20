package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMagasinValue;


public interface IMagasinDomaine {

	  public String creerMagasin(MagasinValue pMagasinValue);

	  public void supprimerMagasin(Long pId);
	
	  public String modifierMagasin(MagasinValue pMagasinValue);

	  public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue);

	  public List < MagasinValue > listeMagasin();
	  public List<MagasinValue> listeDepot();

		public List<MagasinValue> listePDV();

		public List<MagasinValue> rechercheMulticritere(RechercheMulticritereMagasinValue pMagasinValue);
	  
}
