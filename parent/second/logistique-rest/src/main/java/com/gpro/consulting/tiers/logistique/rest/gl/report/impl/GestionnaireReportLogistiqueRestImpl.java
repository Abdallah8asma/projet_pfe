package com.gpro.consulting.tiers.logistique.rest.gl.report.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.ClientProduitLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FicheFaconReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FiniFaconLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.RechercheMulticritereLogistiqueReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.SousFamilleLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.service.gl.report.IGestionnaireReportLogistiqueService;

import net.sf.jasperreports.engine.JRException;

/**
 * Gestionnaire de reporting pour la logistique
 * 
 * @author Zeineb Medimagh
 * @since 20/10/2016
 *
 */

@Controller
@RequestMapping("/reportLogistique")
public class GestionnaireReportLogistiqueRestImpl extends AbstractGestionnaireDownloadImpl{

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportLogistiqueRestImpl.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Autowired
	private IGestionnaireReportLogistiqueService gestionnaireReportLogistiqueService;
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/reportingClientProduit", method = RequestMethod.GET)
	public void reportingClientProduit(
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("typeRapport") String typeRapport,
			@RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report clientReporting List", type);

		RechercheMulticritereLogistiqueReportingtValue request = new RechercheMulticritereLogistiqueReportingtValue();
		request.setTypeRapport(typeRapport);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		
		ClientProduitLogistiqueReportingReportListValue report = gestionnaireReportLogistiqueService.getListClientProduitLogistiqueReport(request);

		this.download(type, report.getReportStream(), report.getParams(),
				report.getFileName(), report.getjRBeanCollectionDataSource(),
				response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/reportingSousFamille", method = RequestMethod.GET)
	public void reportingSousFamille(
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report clientReporting List", type);

		RechercheMulticritereLogistiqueReportingtValue request = new RechercheMulticritereLogistiqueReportingtValue();
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		
		SousFamilleLogistiqueReportingReportListValue report = gestionnaireReportLogistiqueService.getListSousFamillesLogistiqueReport(request);

		this.download(type, report.getReportStream(), report.getParams(),
				report.getFileName(), report.getjRBeanCollectionDataSource(),
				response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/reportingFiniFacon", method = RequestMethod.GET)
	public void reportingFiniFacon(
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report clientReporting List", type);

		RechercheMulticritereLogistiqueReportingtValue request = new RechercheMulticritereLogistiqueReportingtValue();
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		
		FiniFaconLogistiqueReportingReportListValue report = gestionnaireReportLogistiqueService.getListFiniFaconLogistiqueReport(request);
		this.download(type, report.getReportStream(), report.getParams(),
				report.getFileName(), report.getjRBeanCollectionDataSource(),
				response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/reportingFicheFacon", method = RequestMethod.GET)
	public void reportingFicheFacon(
			@RequestParam("id") Long id,
			@RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report clientReporting List", type);

		FicheFaconReportValue report = gestionnaireReportLogistiqueService.getFicheFaconLogistiqueReport(id);
		
		this.download(type, report.getReportStream(), report.getParams(),
				report.getFileName(), report.getjRBeanCollectionDataSource(),
				response);
	}
	
	private Calendar stringToCalendar(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				//logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}
	
	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value);

	}
}
