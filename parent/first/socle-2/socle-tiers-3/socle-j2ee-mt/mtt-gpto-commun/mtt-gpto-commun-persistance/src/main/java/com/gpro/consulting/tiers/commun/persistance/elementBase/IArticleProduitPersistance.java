package com.gpro.consulting.tiers.commun.persistance.elementBase;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;

public interface IArticleProduitPersistance {
	public ResultatRechecheArticleProduitValue rechercherMultiCritere(RechercheMulticritereArticleProduitValue request);


}
