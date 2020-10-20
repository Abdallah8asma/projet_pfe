package com.gpro.consulting.tiers.logistique.service.gc.report.reporting.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.reporting.IGestionnaireReportGcReportingDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.report.reporting.IGestionnaireReportGcReportingService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class GestionnaireReportGcReportingServiceImpl implements IGestionnaireReportGcReportingService{


	@Autowired
	private IGestionnaireReportGcReportingDomaine gestionnaireReportGcReportingDomaine;
	
	
	@Override
	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request,Long solde)
			throws IOException {
		return gestionnaireReportGcReportingDomaine.getListSituationReport(request,solde);
	}


	@Override
	public SituationReportingReportListValue getListSituationAchatReport(
			RechercheMulticritereSituationReportingValue request, Long solde) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportGcReportingDomaine.getListSituationAchatReport(request,solde);
	}

}
