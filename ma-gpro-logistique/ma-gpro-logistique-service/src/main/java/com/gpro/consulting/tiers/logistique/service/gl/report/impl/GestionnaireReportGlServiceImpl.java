package com.gpro.consulting.tiers.logistique.service.gl.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseAvecCoutReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseReportValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.report.IGestionnaireReportGlDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.report.IGestionnaireReportGlService;

/**
 * Implementation of {@link IGestionnaireReportGlService}
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */

@Service
@Transactional
public class GestionnaireReportGlServiceImpl implements IGestionnaireReportGlService{

	@Autowired
	private IGestionnaireReportGlDomaine gestionnaireReportGlDomaine;
	
	@Override
	public FicheSuiveuseReportValue getFicheSuiveuseReport(Long id, String avecRecette) throws IOException{
		
		return gestionnaireReportGlDomaine.getFicheSuiveuseReport(id, avecRecette);
	}
	
	@Override
	public FicheSuiveuseReportValue getFicheSuiveuseReportVide(Long id)
			throws IOException {
		
		return gestionnaireReportGlDomaine.getFicheSuiveuseReportVide(id);
	}

	@Override
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseDetCoutReport(Long id, String avecRecette,
			Double coutRecette, Double coutTraitement) throws IOException {
		return gestionnaireReportGlDomaine.getFicheSuiveuseDetCoutReport(id ,avecRecette, coutRecette, coutTraitement);
	}

	@Override
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseGenCoutReport(Long id, String avecRecette,
			Double coutRecette, Double coutTraitement) throws IOException {
		return gestionnaireReportGlDomaine.getFicheSuiveuseGenCoutReport(id, avecRecette, coutRecette, coutTraitement);
	}

}
