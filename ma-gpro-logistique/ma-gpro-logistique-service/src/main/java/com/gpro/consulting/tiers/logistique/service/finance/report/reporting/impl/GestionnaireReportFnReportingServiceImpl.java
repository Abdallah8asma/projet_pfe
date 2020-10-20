package com.gpro.consulting.tiers.logistique.service.finance.report.reporting.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnChiffreAffaire.ChiffreAffaireFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnClient.ClientFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnExpEch.ExpeditionEchantillionFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.domaine.fn.report.reporting.IGestionnaireReportFnReportingDomaine;
import com.gpro.consulting.tiers.logistique.service.finance.report.reporting.IGestionnaireReportFnReportingService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class GestionnaireReportFnReportingServiceImpl implements IGestionnaireReportFnReportingService{

	@Autowired
	private IGestionnaireReportFnReportingDomaine gestionnaireReportFnReportingDomaine;
	
	@Override
	public ClientFnReportingReportListValue getListClientFnReport(
			RechercheMulticritereFnReportingtValue request)
			throws IOException {
		return gestionnaireReportFnReportingDomaine.getListClientFnReport(request);
	}

	@Override
	public ChiffreAffaireFnReportingReportListValue getListChiffreAffaireGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException {
		return gestionnaireReportFnReportingDomaine.getListChiffreAffaireGpByClientFnReport(request);
	}

	@Override
	public ExpeditionEchantillionFnReportingReportListValue getListExpeditionEchantillonGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException {
		return gestionnaireReportFnReportingDomaine.getListExpeditionEchantillonGpByClientFnReport(request);
	}

}
