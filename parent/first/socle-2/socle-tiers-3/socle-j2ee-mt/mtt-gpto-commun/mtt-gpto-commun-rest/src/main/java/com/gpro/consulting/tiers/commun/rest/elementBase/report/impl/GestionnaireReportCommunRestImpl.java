package com.gpro.consulting.tiers.commun.rest.elementBase.report.impl;

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
import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.service.cache.IGestionnaireCacheService;
import com.gpro.consulting.tiers.commun.service.elementBase.report.IGestionnaireReportCommunService;

/**
 * Gestionnaire de reporting
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */

@Controller
@RequestMapping("/reportCommun")
public class GestionnaireReportCommunRestImpl extends AbstractGestionnaireDownloadImpl{
	
	@Autowired
	private IGestionnaireReportCommunService gestionnaireReportCommunService;
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportCommunRestImpl.class);
	
	/** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireCacheService gestionnaireCacheService;
	  
	/**************/
	@RequestMapping(value="/listproduitspecial", method = RequestMethod.GET)
	public void generateListProduitReportSpecial(@RequestParam("type") String type,

			@RequestParam("reference") String reference,
			@RequestParam("designation") String designation,
			@RequestParam("sousfamille") String sousfamille,
			@RequestParam("partieInteressee") String partieInteressee,
			@RequestParam("etat") String etat,
			@RequestParam("site") String site,
			@RequestParam("prixInf") Double prixInf,
			@RequestParam("prixSup") Double prixSup,
			HttpServletResponse response ) throws JRException, IOException {
		
		
		//System.out.println("inn resport prix special..");
		//logger.info("Generate a {} Report Produit List",type);
		
		RechercheMulticritereProduitValue request = new RechercheMulticritereProduitValue();
		request.setReference(reference);
		request.setDesignation(designation); 

		request.setSousfamille(sousfamille);
		request.setPartieInteressee(partieInteressee);
		request.setEtat(etat);
		request.setSite(site);
		request.setPrix_inf(prixInf);
		request.setPrix_sup(prixSup);
		
		ProduitsReportListValue report = gestionnaireReportCommunService.getListProduitsReportSpecial(request);
		
		//Traitement : transformation de l'Id a sa propre Designation

        for(ProduitReportElementValue elementProduit : report.getProductList()){
        	
      	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(elementProduit.getSousfamilleId(), elementProduit.getSiteId(), elementProduit.getPartieInteresseeId());
      	  	//SousFamille, Famille
	      	elementProduit.setSousfamille(mapA.get("sousFamille"));
	      	elementProduit.setFamille(mapA.get("famille"));
	      	//Site
	      	elementProduit.setSite(mapA.get("site"));
	  		  //partieInteressee
	      	elementProduit.setPartieInteressee(mapA.get("partieInteressee"));
	    		  
  	    }
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	
	
	//
	@RequestMapping(value="/listproduit", method = RequestMethod.GET)
	public void generateListProduitReport(@RequestParam("type") String type,
//			@RequestParam("request") RechercheMulticritereProduitValue request,
			@RequestParam("reference") String reference,
			@RequestParam("designation") String designation,
			/*@RequestParam("famille") String famille,*/
			@RequestParam("sousfamille") String sousfamille,
			@RequestParam("partieInteressee") String partieInteressee,
			@RequestParam("etat") String etat,
			@RequestParam("site") String site,
			@RequestParam("prixInf") Double prixInf,
			@RequestParam("prixSup") Double prixSup,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} Report Produit List",type);
		
		RechercheMulticritereProduitValue request = new RechercheMulticritereProduitValue();
		request.setReference(reference);
		request.setDesignation(designation); 
//		request.setFamille(famille);
		request.setSousfamille(sousfamille);
		request.setPartieInteressee(partieInteressee);
		request.setEtat(etat);
		request.setSite(site);
		request.setPrix_inf(prixInf);
		request.setPrix_sup(prixSup);
		
		ProduitsReportListValue report = gestionnaireReportCommunService.getListProduitsReport(request);
		
		//Traitement : transformation de l'Id a sa propre Designation

        for(ProduitReportElementValue elementProduit : report.getProductList()){
        	
      	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(elementProduit.getSousfamilleId(), elementProduit.getSiteId(), elementProduit.getPartieInteresseeId());
      	  	//SousFamille, Famille
	      	elementProduit.setSousfamille(mapA.get("sousFamille"));
	      	elementProduit.setFamille(mapA.get("famille"));
	      	//Site
	      	elementProduit.setSite(mapA.get("site"));
	  		  //partieInteressee
	      	elementProduit.setPartieInteressee(mapA.get("partieInteressee"));
	    		  
  	    }
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	
	@RequestMapping(value="/listarticle", method = RequestMethod.GET)
	public void generateListArticleReport(@RequestParam("type") String type,
//			@RequestParam("request") RechercheMulticritereProduitValue request,
		//	@RequestParam("reference") String reference,
			//@RequestParam("designation") String designation,
			@RequestParam("famille") String famille,
		//	@RequestParam("sousfamille") String sousfamille,
			
			
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report Produit List",type);
		
		RecherecheMulticritereArticleValue request = new RecherecheMulticritereArticleValue();
		//request.setRef(reference);
		//request.setDesignation(designation); 
    	request.setFamilleEntite(famille);
	//	request.setSousFamilleEntite(sousfamille);
		
		
		
    	FicheColisReportValue report = gestionnaireReportCommunService.getListArticleReport(request);
		
	
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	

	/**
	 * @return the gestionnaireReportCommunService
	 */
	public IGestionnaireReportCommunService getGestionnaireReportCommunService() {
		return gestionnaireReportCommunService;
	}

	/**
	 * @param gestionnaireReportCommunService the gestionnaireReportCommunService to set
	 */
	public void setGestionnaireReportCommunService(
			IGestionnaireReportCommunService gestionnaireReportCommunService) {
		this.gestionnaireReportCommunService = gestionnaireReportCommunService;
	}

}
