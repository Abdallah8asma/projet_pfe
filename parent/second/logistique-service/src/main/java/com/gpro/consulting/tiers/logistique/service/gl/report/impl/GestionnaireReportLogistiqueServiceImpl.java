package com.gpro.consulting.tiers.logistique.service.gl.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.ClientProduitLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FicheFaconReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FiniFaconLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.RechercheMulticritereLogistiqueReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.SousFamilleLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.report.IGestionnaireReportLogistiqueDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.report.IGestionnaireReportGlService;
import com.gpro.consulting.tiers.logistique.service.gl.report.IGestionnaireReportLogistiqueService;

/**
 * Implementation of {@link IGestionnaireReportGlService}
 * 
 * @author Zeineb Medimagh
 * @since 20/10/2016
 *
 */

@Service
@Transactional
public class GestionnaireReportLogistiqueServiceImpl implements IGestionnaireReportLogistiqueService{

	@Autowired
	private IGestionnaireReportLogistiqueDomaine GestionnaireReportLogistiqueDomaine ;
	
	@Override
	public ClientProduitLogistiqueReportingReportListValue getListClientProduitLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException {
		return GestionnaireReportLogistiqueDomaine.getListClientProduitLogistiqueReport(request);
	}

	@Override
	public SousFamilleLogistiqueReportingReportListValue getListSousFamillesLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException {
		return GestionnaireReportLogistiqueDomaine.getListSousFamillesLogistiqueReport(request);
	}

	@Override
	public FiniFaconLogistiqueReportingReportListValue getListFiniFaconLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException {
		// TODO Auto-generated method stub
		return GestionnaireReportLogistiqueDomaine.getListFiniFaconLogistiqueReport(request);
	}

	@Override
	public FicheFaconReportValue getFicheFaconLogistiqueReport(Long id) throws IOException {

		return GestionnaireReportLogistiqueDomaine.getFicheFaconLogistiqueReport(id);
	}

	
}
