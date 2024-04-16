package com.gpro.consulting.tiers.logistique.rest.gc.report.impl.gcReporting.situation;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.service.gc.report.reporting.IGestionnaireReportGcReportingService;

/**
 * @author Ameni Berrich
 *
 */
@SuppressWarnings("static-access")
@Controller
@RequestMapping("/reporting")
public class SituationReport extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(SituationReport.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private IGestionnaireReportGcReportingService gestionnaireReportGcReportingService;

	@RequestMapping(value = "/situation", method = RequestMethod.GET)
	public void generateListSituationReport(
			@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("regiontId") Long regionId,
			@RequestParam("deviseId") Long deviseId,
			@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("soldeMin") Double soldeMin,
			@RequestParam("soldeMax") Double soldeMax,
			@RequestParam("solde") Long solde,

			HttpServletResponse response) throws JRException, IOException {

		logger.info("Generate a {} Report Situation List", type);

		RechercheMulticritereSituationReportingValue request = new RechercheMulticritereSituationReportingValue();
		request.setDateMax(stringToCalendar(dateMax));
		request.setDateMin(stringToCalendar(dateMin));
		request.setRegionId(regionId);
		request.setPartieIntId(partieIntId);
		request.setSoldeMax(soldeMax);
		request.setSoldeMin(soldeMin);
		request.setDeviseId(deviseId);
		SituationReportingReportListValue report = gestionnaireReportGcReportingService
				.getListSituationReport(request,solde);

		this.download(type, report.getReportStream(), report.getParams(),
				report.getFileName(), report.getjRBeanCollectionDataSource(),
				response);

	}

	
	@RequestMapping(value = "/situationAchat", method = RequestMethod.GET)
	public void generateListSituationAchatReport(
			@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("regiontId") Long regionId,
			@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("soldeMin") Double soldeMin,
			@RequestParam("soldeMax") Double soldeMax,
			@RequestParam("solde") Long solde,

			HttpServletResponse response) throws JRException, IOException {

		logger.info("Generate a {} Report Situation List", type);

		RechercheMulticritereSituationReportingValue request = new RechercheMulticritereSituationReportingValue();
		request.setDateMax(stringToCalendar(dateMax));
		request.setDateMin(stringToCalendar(dateMin));
		request.setRegionId(regionId);
		request.setPartieIntId(partieIntId);
		request.setSoldeMax(soldeMax);
		request.setSoldeMin(soldeMin);
		SituationReportingReportListValue report = gestionnaireReportGcReportingService
				.getListSituationAchatReport(request,solde);

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
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}

	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value);

	}

}