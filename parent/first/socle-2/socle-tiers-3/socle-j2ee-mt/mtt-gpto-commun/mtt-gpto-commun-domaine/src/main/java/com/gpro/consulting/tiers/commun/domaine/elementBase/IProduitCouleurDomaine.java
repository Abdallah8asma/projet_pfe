package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitCouleurValue;


public interface IProduitCouleurDomaine {
	
	public List < ProduitCouleurValue > ListeProduitCouleur(Long pIdProduit);

}
