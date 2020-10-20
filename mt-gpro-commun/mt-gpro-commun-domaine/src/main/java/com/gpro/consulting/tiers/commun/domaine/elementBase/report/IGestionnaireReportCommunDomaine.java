package com.gpro.consulting.tiers.commun.domaine.elementBase.report;

import java.io.IOException;

import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;

/**
 * GestionnaireReportCommunDomaine Interface
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
public interface IGestionnaireReportCommunDomaine {

	public ProduitsReportListValue getListProduitsReport(
			RechercheMulticritereProduitValue request) throws IOException;
	
	public ProduitsReportListValue getListProduitsReportSpecial(
			RechercheMulticritereProduitValue request) throws IOException;

}
