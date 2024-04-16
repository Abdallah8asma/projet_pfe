package com.gpro.consulting.tiers.logistique.service.gc.report.reporting;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IGestionnaireReportGcReportingService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request,Long solde) throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SituationReportingReportListValue getListSituationAchatReport(
			RechercheMulticritereSituationReportingValue request,Long solde) throws IOException;
}
