package com.gpro.consulting.tiers.logistique.domaine.gc.report.reporting;

import java.io.IOException;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IGestionnaireReportGcReportingDomaine {

	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request, Long solde) throws IOException;

	public SituationReportingReportListValue getListSituationAchatReport (
			RechercheMulticritereSituationReportingValue request, Long solde) throws IOException;
}
