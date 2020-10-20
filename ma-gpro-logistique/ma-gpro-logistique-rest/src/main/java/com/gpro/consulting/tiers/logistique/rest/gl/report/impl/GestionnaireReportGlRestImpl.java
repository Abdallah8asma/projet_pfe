package com.gpro.consulting.tiers.logistique.rest.gl.report.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseAvecCoutReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseReportValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gl.report.IGestionnaireReportGlService;

/**
 * Gestionnaire de reporting pour la GL
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */

@Controller
@RequestMapping("/reportgl")
public class GestionnaireReportGlRestImpl extends AbstractGestionnaireDownloadImpl{

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGlRestImpl.class);
	
	@Autowired
	private IGestionnaireReportGlService gestionnaireReportGlService;
	
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/ficheSuiveuse", method = RequestMethod.GET)
	public void generateFactureVenteReport(@RequestParam("id") Long id, @RequestParam("type") String type,
			@RequestParam(value ="avecRecette", required = false, defaultValue = "oui") String avecRecette,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} Report FicheSuiveuse",type);
		
		FicheSuiveuseReportValue report = gestionnaireReportGlService.getFicheSuiveuseReport(id, avecRecette);
		
		if(report.getClientId() != null){
			
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(report.getClientId());
			
			report.setClientAbreviation(client.getAbreviation());
			report.setClientReference(client.getReference());
		}
		
		if(report.getProduitId() != null){
			Map<Long, ProduitValue> mapPtoduitById = gestionnaireLogistiqueCacheService.mapPtoduitById();
			ProduitValue produit = mapPtoduitById.get(report.getProduitId());

			report.setProduitComposition(produit.getComposition());
			report.setProduitReference(produit.getReference());
			report.setProduitDesignation(produit.getDesignation());
			report.setProduitCodeCouleur(produit.getCodeCouleur());
			report.setProduitReferenceInterne(produit.getReferenceInterne());

		}
		
		report.setAvecRecette(avecRecette);

		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/ficheSuiveuseVide", method = RequestMethod.GET)
	public void generateFactureVenteReport(@RequestParam("id") Long id, @RequestParam("type") String type,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate an Empty {} Report FicheSuiveuse",type);

		FicheSuiveuseReportValue report = gestionnaireReportGlService.getFicheSuiveuseReportVide(id);
		
		if(report.getClientId() != null){
			
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(report.getClientId());
			
			report.setClientAbreviation(client.getAbreviation());
			report.setClientReference(client.getReference());
		}
		
		if(report.getProduitId() != null){
			
			Map<Long, ProduitValue> mapPtoduitById = gestionnaireLogistiqueCacheService.mapPtoduitById();
			ProduitValue produit = mapPtoduitById.get(report.getProduitId());

			report.setProduitComposition(produit.getComposition());
			report.setProduitReference(produit.getReference());
			report.setProduitDesignation(produit.getDesignation());
			report.setProduitCodeCouleur(produit.getCodeCouleur());
			report.setProduitReferenceInterne(produit.getReferenceInterne());
			
		}

		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	/**************************** Rapport fiche suiveuse détaillée avec cout *************************/
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/ficheSuiveuseDetailleAvecCout", method = RequestMethod.GET)
	public void ficheSuiveuseDetailleeAvecCout(@RequestParam("id") Long id, @RequestParam("type") String type,
			@RequestParam(value ="avecRecette", required = false, defaultValue = "oui") String avecRecette,
			@RequestParam("coutRecette") String coutRecette, @RequestParam("coutTraitement") String coutTraitement,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} Report FicheSuiveuse detaillée avec cout",type);

		Double coutRecetteParse= Double.parseDouble(coutRecette);
		Double coutTraitementParse= Double.parseDouble(coutTraitement);
		
		FicheSuiveuseAvecCoutReportValue report = gestionnaireReportGlService.getFicheSuiveuseDetCoutReport(id, avecRecette, coutRecetteParse, coutTraitementParse);
		
		if(report.getClientId() != null){
			
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(report.getClientId());
			
			report.setClientAbreviation(client.getAbreviation());
			report.setClientReference(client.getReference());
		}
		
		if(report.getProduitId() != null){
			Map<Long, ProduitValue> mapPtoduitById = gestionnaireLogistiqueCacheService.mapPtoduitById();
			ProduitValue produit = mapPtoduitById.get(report.getProduitId());

			report.setProduitComposition(produit.getComposition());
			report.setProduitReference(produit.getReference());
			report.setProduitDesignation(produit.getDesignation());
			report.setProduitCodeCouleur(produit.getCodeCouleur());
			report.setProduitReferenceInterne(produit.getReferenceInterne());

		}
		
		report.setAvecRecette(avecRecette);

		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	
/**************************** Rapport fiche suiveuse Generale avec cout *************************/
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/ficheSuiveuseGeneraleAvecCout", method = RequestMethod.GET)
	public void ficheSuiveuseGeneraleAvecCout(@RequestParam("id") Long id, @RequestParam("type") String type,
			@RequestParam(value ="avecRecette", required = false, defaultValue = "oui") String avecRecette,
			@RequestParam("coutRecette") String coutRecette, @RequestParam("coutTraitement") String coutTraitement,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} Report FicheSuiveuse generale avec cout",type);

		Double coutRecetteParse= Double.parseDouble(coutRecette);
		Double coutTraitementParse= Double.parseDouble(coutTraitement);
		
		FicheSuiveuseAvecCoutReportValue report = gestionnaireReportGlService.getFicheSuiveuseGenCoutReport(id, avecRecette, coutRecetteParse, coutTraitementParse);
		
		if(report.getClientId() != null){
			
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(report.getClientId());
			
			report.setClientAbreviation(client.getAbreviation());
			report.setClientReference(client.getReference());
		}
		
		if(report.getProduitId() != null){
			Map<Long, ProduitValue> mapPtoduitById = gestionnaireLogistiqueCacheService.mapPtoduitById();
			ProduitValue produit = mapPtoduitById.get(report.getProduitId());

			report.setProduitComposition(produit.getComposition());
			report.setProduitReference(produit.getReference());
			report.setProduitDesignation(produit.getDesignation());
			report.setProduitCodeCouleur(produit.getCodeCouleur());
			report.setProduitReferenceInterne(produit.getReferenceInterne());
		}
		
		report.setAvecRecette(avecRecette);

		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}

}
