package com.gpro.consulting.tiers.commun.domaine.elementBase;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitTailleValue;

public interface IProduitTailleDomaine {
	public List < ProduitTailleValue > ListeProduitTaille(Long pIdProduit);
}
