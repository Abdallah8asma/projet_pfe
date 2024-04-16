package com.gpro.consulting.tiers.logistique.domaine.gl.report;

import java.io.IOException;

import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.ClientProduitLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FicheFaconReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FiniFaconLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.RechercheMulticritereLogistiqueReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.SousFamilleLogistiqueReportingReportListValue;

/**
 * GestionnaireReportGlDomaine Interface
 * 
 * @author Zeineb Medimagh
 * @since 19/10/2016
 *
 */
public interface IGestionnaireReportLogistiqueDomaine {

	public ClientProduitLogistiqueReportingReportListValue getListClientProduitLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException;
	
	public SousFamilleLogistiqueReportingReportListValue getListSousFamillesLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException;
	
	public FiniFaconLogistiqueReportingReportListValue getListFiniFaconLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException;
	
	public FicheFaconReportValue getFicheFaconLogistiqueReport(Long id) throws IOException;
}
