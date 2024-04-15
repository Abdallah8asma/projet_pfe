package com.gpro.consulting.tiers.commun.domaine.elementBase;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;

public interface IArticleProduitDomaine {
	public ResultatRechecheArticleProduitValue rechercherMultiCritere(RechercheMulticritereArticleProduitValue request);


}
