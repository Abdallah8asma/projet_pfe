package com.gpro.consulting.tiers.logistique.service.gl.report;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseAvecCoutReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseReportValue;

/**
 * GL GestionnaireReport Service Interface
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */
public interface IGestionnaireReportGlService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSuiveuseReportValue getFicheSuiveuseReport(Long id, String avecRecette) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSuiveuseReportValue getFicheSuiveuseReportVide(Long id) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseDetCoutReport(Long id, String avecRecette, Double coutRecette, Double coutTraitement) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseGenCoutReport(Long id, String avecRecette, Double coutRecette, Double coutTraitement) throws IOException;
}
