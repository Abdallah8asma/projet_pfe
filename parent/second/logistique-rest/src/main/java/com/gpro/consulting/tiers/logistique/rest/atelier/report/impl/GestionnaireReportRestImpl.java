package com.gpro.consulting.tiers.logistique.rest.atelier.report.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonReception.value.BonReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.boninventaire.BonInventaireReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.BonSortieFinieReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.BonSortieFinieReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.inventaire.value.InventaireReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.rouleaufini.value.EtiquetteRouleauFiniReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.service.atelier.report.IGestionnaireReportService;

/**
 * Gestionnaire de reporting
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
@SuppressWarnings("static-access")
@Controller
@RequestMapping("/report")
public class GestionnaireReportRestImpl extends AbstractGestionnaireDownloadImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportRestImpl.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	IGestionnaireReportService gestionnaireReportService;
  
	@RequestMapping(value = "/generer", method = RequestMethod.GET)
	public void generateReport(@RequestParam("id") Long id,@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} report, for bonReception id {}",type, id);
		
		/* Récupération du bon de la base */
		BonReceptionReportValue bonReceptionvalue = gestionnaireReportService.getBonReceptionReportParId(id);

		// Création du nom du fichier 
		this.download( type , bonReceptionvalue.getReportStream() ,bonReceptionvalue.getParams(), 
				bonReceptionvalue.getFileName(),bonReceptionvalue.getJRBeanCollectionDataSource(), response);
	}
	
	@RequestMapping(value="/etiquetteRouleau", method = RequestMethod.GET)
	public void genererEtiquetteRouleauReport(@RequestParam("id") Long id, 
				@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} EtiquetteRouleau, for bonReception id {}",type, id);
		
		/* Récupération du Etiquette de la base */
		EtiquetteRouleauFiniReportValue etiquette = gestionnaireReportService.getEtiquetteRouleauFiniReportParId(id);

		// Création du nom du fichier 
		this.download( type , etiquette.getReportStream() ,etiquette.getParams(), 
				etiquette.getFileName(),etiquette.getJRBeanCollectionDataSource(), response);
		
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/list-etiquette-rouleau", method = RequestMethod.GET)
	public void genererListEtiquetteRouleauReport(
			
			
			
			@RequestParam("reference") String reference,

			@RequestParam("refMise") String refMise,
			
			@RequestParam("produitId") String produitId,
			
			@RequestParam("numberOfBox") String numberOfBox,
			
			@RequestParam("metrage") String metrage,
			
			@RequestParam("ids") String ids,
			
			@RequestParam("type") String type,
	
			
			
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report genererListEtiquetteRouleauReport",type);
		
		RechercheMulticritereRouleauFiniValue request = new RechercheMulticritereRouleauFiniValue();
		
		
		request.setReference(reference);
		
		request.setRefMise(refMise);
		
		
		if(isNotEmty(ids)) {
			
			List<Long> idLong = new ArrayList<Long>();
			
			String[] idString =  ids.split(",") ;
			
			for(String id : idString) {
				idLong.add(Long.parseLong(id)) ;
			}
				
			
		
			request.setIds(idLong);
			
		}
		
			
	 
		
		if(isNotEmty(metrage)) 
			request.setMetrage(Double.parseDouble(metrage)) ;
		
	
		
		if(isNotEmty(numberOfBox)) 
			request.setNumberOfBox(Long.parseLong(numberOfBox)) ;
		
		
		if(isNotEmty(produitId))
			request.setProduitId(Long.parseLong(produitId));
		
		
		
		

		
    	FicheColisReportValue report = gestionnaireReportService.genererListEtiquetteRouleauReport(request);
		
	
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	
	@RequestMapping(value = "/inventaire", method = RequestMethod.GET)
	public void genererInventaireReport(
			// @RequestParam("critereRechercheRouleauStandard")
			// CritereRechercheRouleauStandardValue
			// critereRechercheRouleauStandard,
			@RequestParam("client") Long client, 
			
			@RequestParam("nombreColieDu") Long nombreColieDu,
			
			@RequestParam("nombreColieA") Long nombreColieA, 
			//@RequestParam("entrepot") Long entrepot,
			//@RequestParam("emplacement") String emplacement, 
			@RequestParam("metrageDu") Double metrageDu,
			@RequestParam("metrageA") Double metrageA, @RequestParam("dateEtat") String dateEtat,
			@RequestParam("designationQuiContient") String designationQuiContient,

			// Hajer Amri 06/02/2017
			@RequestParam("referenceProduit") String referenceProduit,

			@RequestParam("fini") String fini, @RequestParam("orderBy") String orderBy,
			@RequestParam("typeOf") String typeOf,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		logger.info("Generate a {} Report Inventaire", type);

		CritereRechercheRouleauStandardValue critereRechercheRouleauStandard = new CritereRechercheRouleauStandardValue();
		critereRechercheRouleauStandard.setClient(client);
		critereRechercheRouleauStandard.setDateEtat(stringToCalendar(dateEtat));
		critereRechercheRouleauStandard.setDesignationQuiContient(designationQuiContient);
		////critereRechercheRouleauStandard.setEmplacement(emplacement);
	////	critereRechercheRouleauStandard.setEntrepot(entrepot);
		critereRechercheRouleauStandard.setMetrageA(metrageA);
		critereRechercheRouleauStandard.setMetrageDu(metrageDu);
		critereRechercheRouleauStandard.setNombreColieA(nombreColieA);
		critereRechercheRouleauStandard.setNombreColieDu(nombreColieDu);
		critereRechercheRouleauStandard.setOrderBy(orderBy);
		//critereRechercheRouleauStandard.setFini(fini);
		critereRechercheRouleauStandard.setReferenceProduit(referenceProduit);
		
		
		
		
		//critereRechercheRouleauStandard.setTypeOf(typeOf);

		InventaireReportValue vInventaireReport = gestionnaireReportService
				.getInventaireReportValue(critereRechercheRouleauStandard);

		this.download(type, vInventaireReport.getReportStream(), vInventaireReport.getParams(),
				vInventaireReport.getFileName(), vInventaireReport.getJRBeanCollectionDataSource(), response);
	}
	
	
	
	
	
	@RequestMapping(value = "/inventaireByOF", method = RequestMethod.GET)
	public void genererInventaireByOFReport(
			// @RequestParam("critereRechercheRouleauStandard")
			// CritereRechercheRouleauStandardValue
			// critereRechercheRouleauStandard,
			@RequestParam("client") Long client, 
			
			@RequestParam("nombreColieDu") Long nombreColieDu,
			
			@RequestParam("nombreColieA") Long nombreColieA, 
			//@RequestParam("entrepot") Long entrepot,
			//@RequestParam("emplacement") String emplacement, 
			@RequestParam("metrageDu") Double metrageDu,
			@RequestParam("metrageA") Double metrageA, @RequestParam("dateEtat") String dateEtat,
			@RequestParam("designationQuiContient") String designationQuiContient,

			// Hajer Amri 06/02/2017
			@RequestParam("referenceProduit") String referenceProduit,

			@RequestParam("fini") String fini, @RequestParam("orderBy") String orderBy,
			@RequestParam("typeOf") String typeOf,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		logger.info("Generate a {} Report Inventaire", type);

		CritereRechercheRouleauStandardValue critereRechercheRouleauStandard = new CritereRechercheRouleauStandardValue();
		critereRechercheRouleauStandard.setClient(client);
		critereRechercheRouleauStandard.setDateEtat(stringToCalendar(dateEtat));
		critereRechercheRouleauStandard.setDesignationQuiContient(designationQuiContient);
		////critereRechercheRouleauStandard.setEmplacement(emplacement);
	////	critereRechercheRouleauStandard.setEntrepot(entrepot);
		critereRechercheRouleauStandard.setMetrageA(metrageA);
		critereRechercheRouleauStandard.setMetrageDu(metrageDu);
		critereRechercheRouleauStandard.setNombreColieA(nombreColieA);
		critereRechercheRouleauStandard.setNombreColieDu(nombreColieDu);
		critereRechercheRouleauStandard.setOrderBy(orderBy);
		//critereRechercheRouleauStandard.setFini(fini);
		critereRechercheRouleauStandard.setReferenceProduit(referenceProduit);
		
		
		
		
		//critereRechercheRouleauStandard.setTypeOf(typeOf);

		InventaireReportValue vInventaireReport = gestionnaireReportService
				.getInventaireByOFReportValue(critereRechercheRouleauStandard);

		this.download(type, vInventaireReport.getReportStream(), vInventaireReport.getParams(),
				vInventaireReport.getFileName(), vInventaireReport.getJRBeanCollectionDataSource(), response);
	}
	
	@RequestMapping(value="/bonsortiefini", method = RequestMethod.GET)
	public void genererBonsortieFinieReport(@RequestParam("id") Long id,
			@RequestParam("type") String type,
			@RequestParam(value ="avecMise", required = false, defaultValue = "oui") String avecMise,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} Report BonsortieFinie",type);
		
		BonSortieFinieReportValue bonsortieFinieReport = gestionnaireReportService.getBonsortieFinieReportValue(id, avecMise);
		

		this.download( type , bonsortieFinieReport.getReportStream() ,bonsortieFinieReport.getParams(), 
				bonsortieFinieReport.getFileName(),bonsortieFinieReport.getJRBeanCollectionDataSource(), response);
	}

	@RequestMapping(value="/listbonsortie", method = RequestMethod.GET)
	public void generateListBonSortieReport(@RequestParam("type") String type,
//			@RequestParam("request") RechercheMulticritereBonSortieFiniValue request,
			@RequestParam("reference") String reference,
			@RequestParam("dateSortieMin") String dateSortieMin,
			@RequestParam("dateSortieMax") String dateSortieMax,
			@RequestParam("typeBonSortie") String typeBonSortie,
			@RequestParam("partieInt") Long partieInt,
			@RequestParam("rempli") String rempli,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} Report BonSortie List",type);
		
		RechercheMulticritereBonSortieFiniValue request = new RechercheMulticritereBonSortieFiniValue();
		request.setDateSortieMax(stringToCalendar(dateSortieMax));
		request.setDateSortieMin(stringToCalendar(dateSortieMin));
		request.setRempli(rempli);
		request.setPartieInt(partieInt);
		request.setReference(reference);
		request.setType(typeBonSortie);
		
		BonSortieFinieReportListValue report = gestionnaireReportService.getListBonSortieReport(request);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	
	@RequestMapping(value="/bonInventaire", method = RequestMethod.GET)
	public void genererbonInventaireReport(@RequestParam("id") Long id,
			@RequestParam("type") String type,
			@RequestParam(value ="avecMise", required = false, defaultValue = "oui") String avecMise,
			HttpServletResponse response ) throws JRException, IOException {
		
		//logger.info("Generate a {} Report bonInventaire",type);
		
		BonInventaireReportValue bonInventaireReportValue = gestionnaireReportService.getBonInventaireReportValue(id, avecMise);
		

		this.download( type , bonInventaireReportValue.getReportStream() ,bonInventaireReportValue.getParams(), 
				bonInventaireReportValue.getFileName(),bonInventaireReportValue.getJRBeanCollectionDataSource(), response);
	}
	
	
	private Calendar stringToCalendar(String dateString) {
		
		Calendar dateCalendar = null;
		
		if(isNotEmty(dateString)){
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));
				
			} catch (ParseException e) {
				//logger.error("parse date exception: "+e.getMessage());
			}
		}
		
		return dateCalendar;
	}
	
	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value) && !"undefined".equals(value) && !"null".equals(value);

	}
	
}