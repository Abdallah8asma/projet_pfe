package com.gpro.consulting.tiers.logistique.rest.fn.report.impl.FnReporting;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnChiffreAffaire.ChiffreAffaireFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnClient.ClientFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnExpEch.ExpeditionEchantillionFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.rest.gc.report.impl.gcReporting.situation.SituationReport;
import com.gpro.consulting.tiers.logistique.service.finance.report.reporting.IGestionnaireReportFnReportingService;

/**
 * @author Ameni Berrich
 *
 */
@SuppressWarnings("static-access")
@Controller
@RequestMapping("/reportFn")
public class GestionnaireReportFnReportingRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(SituationReport.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private IGestionnaireReportFnReportingService gestionnaireReportFnReportingService;

	//Fn_Reporting Client & Produit
	@RequestMapping(value = "/clientGrByProduitOrClient", method = RequestMethod.GET)
	public void generateListClientFnReport(
			@RequestParam("type") String type,
			@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("produitId") Long produitId,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("typeRapport") String typeRapport,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report clientReporting List", type);

		RechercheMulticritereFnReportingtValue request = new RechercheMulticritereFnReportingtValue();
		request.setClientId(partieIntId);
		request.setProduitId(produitId);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		request.setTypeRapport(typeRapport);
		
		ClientFnReportingReportListValue report = gestionnaireReportFnReportingService
				.getListClientFnReport(request);

		this.download(type, report.getReportStream(), report.getParams(),
				report.getFileName(), report.getjRBeanCollectionDataSource(),
				response);

	}

	// Fn_Reporting Chiffre Affaire
	@RequestMapping(value = "/chiffreAffaireGpByClient", method = RequestMethod.GET)
	public void generateListChiffreAffaireGpByClientFnReport(
			@RequestParam("type") String type,
			@RequestParam("regiontId") Long regiontId,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report clientReporting List", type);

		RechercheMulticritereFnReportingtValue request = new RechercheMulticritereFnReportingtValue();
		request.setRegionId(regiontId);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));

		ChiffreAffaireFnReportingReportListValue report = gestionnaireReportFnReportingService
				.getListChiffreAffaireGpByClientFnReport(request);

		this.download(type, report.getReportStream(), report.getParams(),
				report.getFileName(), report.getjRBeanCollectionDataSource(),
				response);

	}
	
	// Fn_Reporting Exp/Ech
		@RequestMapping(value = "/expeditionEchantillonGpByClient", method = RequestMethod.GET)
		public void generateListExpeditionEchantillonGpByClientFnReport(
				@RequestParam("type") String type,
				@RequestParam("regiontId") Long regiontId,
				@RequestParam("dateMin") String dateMin,
				@RequestParam("dateMax") String dateMax,
				HttpServletResponse response) throws JRException, IOException {

			//logger.info("Generate a {} Report clientReporting List", type);

			RechercheMulticritereFnReportingtValue request = new RechercheMulticritereFnReportingtValue();
			request.setRegionId(regiontId);
			request.setDateMin(stringToCalendar(dateMin));
			request.setDateMax(stringToCalendar(dateMax));

			ExpeditionEchantillionFnReportingReportListValue report = gestionnaireReportFnReportingService
					.getListExpeditionEchantillonGpByClientFnReport(request);

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
