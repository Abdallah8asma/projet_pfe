package com.gpro.consulting.tiers.logistique.service.finance.report.reporting;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnChiffreAffaire.ChiffreAffaireFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnClient.ClientFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnExpEch.ExpeditionEchantillionFnReportingReportListValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IGestionnaireReportFnReportingService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ClientFnReportingReportListValue getListClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ChiffreAffaireFnReportingReportListValue getListChiffreAffaireGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ExpeditionEchantillionFnReportingReportListValue getListExpeditionEchantillonGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException;
	
}
