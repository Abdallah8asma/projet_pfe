package com.gpro.consulting.tiers.logistique.domaine.gl.report;

import java.io.IOException;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseAvecCoutReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseReportValue;

/**
 * GestionnaireReportGlDomaine Interface
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */
public interface IGestionnaireReportGlDomaine {

	public FicheSuiveuseReportValue getFicheSuiveuseReport(Long id, String recette) throws IOException;
	
	public FicheSuiveuseReportValue getFicheSuiveuseReportVide(Long id) throws IOException;
	
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseDetCoutReport(Long id, String avecRecette, Double coutRecette, Double coutTraitement) throws IOException;
	
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseGenCoutReport(Long id, String avecRecette, Double coutRecette, Double coutTraitement) throws IOException;
	
}
