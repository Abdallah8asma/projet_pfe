package com.gpro.consulting.tiers.logistique.domaine.gc.reporting.situation;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.ResultatRechecheSituationReportingValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ISituationReportingDomaine {

	public ResultatRechecheSituationReportingValue rechercherMultiCritere(
			RechercheMulticritereSituationReportingValue request);
	
	
	public ResultatRechecheSituationReportingValue rechercherMultiCritereAchat(
			RechercheMulticritereSituationReportingValue request);
}
