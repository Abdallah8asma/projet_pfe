package com.gpro.consulting.tiers.commun.service.elementBase.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.report.IGestionnaireReportCommunDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.report.IGestionnaireReportCommunService;

/**
 * Implementation of {@link IGestionnaireReportCommunService}
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
@Service
@Transactional
public class GestionnaireReportCommunServiceImpl implements IGestionnaireReportCommunService{

	@Autowired
	private IGestionnaireReportCommunDomaine gestionnaireReportCommunDomaine;

	@Override
	public ProduitsReportListValue getListProduitsReport(
			RechercheMulticritereProduitValue request) throws IOException {
		
		return gestionnaireReportCommunDomaine.getListProduitsReport(request);
	}
	
	
	@Override
	public ProduitsReportListValue getListProduitsReportSpecial(
			RechercheMulticritereProduitValue request) throws IOException {
		
		return gestionnaireReportCommunDomaine.getListProduitsReportSpecial(request);
	}

	/**
	 * @return the gestionnaireReportCommunDomaine
	 */
	public IGestionnaireReportCommunDomaine getGestionnaireReportCommunDomaine() {
		return gestionnaireReportCommunDomaine;
	}

	/**
	 * @param gestionnaireReportCommunDomaine the gestionnaireReportCommunDomaine to set
	 */
	public void setGestionnaireReportCommunDomaine(
			IGestionnaireReportCommunDomaine gestionnaireReportCommunDomaine) {
		this.gestionnaireReportCommunDomaine = gestionnaireReportCommunDomaine;
	}
	
}
