package com.gpro.consulting.tiers.logistique.domaine.fn.report.reporting;

import java.io.IOException;

import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnChiffreAffaire.ChiffreAffaireFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnClient.ClientFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnExpEch.ExpeditionEchantillionFnReportingReportListValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IGestionnaireReportFnReportingDomaine {
	
	public ClientFnReportingReportListValue getListClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException;
	
	public ChiffreAffaireFnReportingReportListValue getListChiffreAffaireGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException;
	
	public ExpeditionEchantillionFnReportingReportListValue getListExpeditionEchantillonGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException;
	
}
