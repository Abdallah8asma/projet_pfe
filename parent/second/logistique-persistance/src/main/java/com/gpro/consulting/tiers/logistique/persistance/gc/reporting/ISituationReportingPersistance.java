package com.gpro.consulting.tiers.logistique.persistance.gc.reporting;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.ResultatRechecheSituationReportingValue;

/**
 * @author Ameni Berrich
 *
 */

public interface ISituationReportingPersistance {

	public ResultatRechecheSituationReportingValue rechercherMultiCritere(
			RechercheMulticritereSituationReportingValue request);
	
}
