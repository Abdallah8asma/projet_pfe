package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;


public interface IEmplacementDomaine {
	
	  public String creerEmplacement(EmplacementValue pEmplacementValue);

	  public void supprimerEmplacement(Long pId);
	
	  public String modifierEmplacement(EmplacementValue pEmplacementValue);

	  public EmplacementValue rechercheEmplacementValueParId(EmplacementValue pEmplacementValue);

	  public List < EmplacementValue > listeEmplacementValue();
	  

}
