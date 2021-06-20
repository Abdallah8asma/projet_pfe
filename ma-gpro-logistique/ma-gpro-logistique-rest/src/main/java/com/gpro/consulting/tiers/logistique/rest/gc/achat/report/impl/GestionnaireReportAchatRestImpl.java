package com.gpro.consulting.tiers.logistique.rest.gc.achat.report.impl;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.RechercheMulticritereFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value.BonCommandeReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value.EcheancierReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.service.gc.achat.report.IGestionnaireReportAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;

import net.sf.jasperreports.engine.JRException;

/**
 * Gestionnaire de reporting pour la Gestion Commerciale
 * 
 * @author Samer Hassen
 * @since 03.04.20
 *
 */
@SuppressWarnings("static-access")
@Controller
@RequestMapping("/reportAchat")
public class GestionnaireReportAchatRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportAchatRestImpl.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private IGestionnaireReportAchatService gestionnaireReportAchatService;

	@Autowired
	private IMarcheService marcheService;

	@Autowired
	private IModePaiementService modePaiementService;


	@RequestMapping(value = "/bonCommande", method = RequestMethod.GET)
	public void genererBonCommandeReport(@RequestParam("id") Long id,@RequestParam("typerapport") String typerapport,
			@RequestParam(value = "avecPrix", required = false, defaultValue = "oui") String avecPrix,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report BonCommande", type);

		BonCommandeReportValue bonCommandeReport = gestionnaireReportAchatService.getBonCommandeParIdReport(id,typerapport, avecPrix);
		
		
		//

		this.download(type, bonCommandeReport.getReportStream(), bonCommandeReport.getParams(),
				bonCommandeReport.getFileName(), bonCommandeReport.getJRBeanCollectionDataSource(), response);
	}
	
	
	
	@RequestMapping(value = "/bonReceptionAchat", method = RequestMethod.GET)
	public void genererBonReceptionAchatReport(@RequestParam("id") Long id,
			@RequestParam(value = "avecPrix", required = false, defaultValue = "oui") String avecPrix,
			@RequestParam("typeRapport") Long typerapport,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report BonReception", type);

		BReceptionReportValue bonReceptionReport = gestionnaireReportAchatService.getBonReceptionParIdReport(id, avecPrix,typerapport);

		this.download(type, bonReceptionReport.getReportStream(), bonReceptionReport.getParams(),
				bonReceptionReport.getFileName(), bonReceptionReport.getJRBeanCollectionDataSource(), response);
	}
	
	
	
	@RequestMapping(value = "/facture", method = RequestMethod.GET)
	public void generateFactureVenteReport(@RequestParam("id") Long id,@RequestParam("typerapport") Long typerapport, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report FactureVente", type);

		FactureReportValue factureReport = gestionnaireReportAchatService.getFactureReportValue(id,typerapport);
		
		
		this.download(type, factureReport.getReportStream(), factureReport.getParams(), factureReport.getFileName(),
				factureReport.getJRBeanCollectionDataSource(), response);

	}
	
	@RequestMapping(value = "/reglement", method = RequestMethod.GET)
	public void getReglementAchatReportParRef(@RequestParam("id") Long id,
			@RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a Reglement", type);

		ReglementReportParRefValue reglementReportParRefValue = gestionnaireReportAchatService.getReglementReportParRef(id);

		this.download(type, reglementReportParRefValue.getReportStream(), reglementReportParRefValue.getParams(),
				reglementReportParRefValue.getFileName(), reglementReportParRefValue.getjRBeanCollectionDataSource(), response);
	}
	
	
	@RequestMapping(value = "/reglementList", method = RequestMethod.GET)
	public void getReglementAchatListReport(@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("dateReglementDu") String dateReglementDu,
			@RequestParam("dateReglementAu") String dateReglementAu,
			@RequestParam("montantMin") Double montantMin,
			@RequestParam("montantMax") Double montantMax,
			@RequestParam("idDepot") Long idDepot,
			
			@RequestParam("declarerRech") String declarerRech,
			@RequestParam("hasElementReglement") String hasElementReglement,
			@RequestParam("hasDetailReglement") String hasDetailReglement,
			
			@RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		RechercheMulticritereReglementAchatValue request = new RechercheMulticritereReglementAchatValue();

		request.setPartieIntId(partieIntId);
		request.setDateReglementMin(stringToCalendar(dateReglementDu));
		request.setDateReglementMax(stringToCalendar(dateReglementAu));
		request.setMontantMin(montantMin);
		request.setMontantMax(montantMax);
		request.setIdDepot(idDepot);
		
		request.setDeclarerRech(declarerRech);
		request.setHasElementReglement(hasElementReglement);
		request.setHasDetailReglement(hasDetailReglement);
		
		//logger.info("Generate a Reglement", type);

		ReglementReportValue reglementReportValue = gestionnaireReportAchatService.getReglementReport(request);

		this.download(type, reglementReportValue.getReportStream(), reglementReportValue.getParams(),
				reglementReportValue.getFileName(), reglementReportValue.getjRBeanCollectionDataSource(), response);
	}
	
	@RequestMapping(value = "/listEcheance", method = RequestMethod.GET)
	public void generateListDetailReglementAchatReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("reference") String reference,
			@RequestParam("referenceDetReglement") String referenceDetReglement,
			@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("dateReglementAu") String dateReglementAu,
			@RequestParam("dateReglementDu") String dateReglementDu,
			@RequestParam("dateEcheanceDu") String dateEcheanceDu,
			@RequestParam("dateEcheanceAu") String dateEcheanceAu,
			
			@RequestParam("dateDepotBanqueDe") String dateDepotBanqueDe,
			@RequestParam("dateDepotBanqueA") String dateDepotBanqueA,
			
			
			@RequestParam("numPiece") String numPiece,
			@RequestParam("regle") Boolean regle, @RequestParam("typeReglementId") Long typeReglementId,
			@RequestParam("avecTerme") Boolean avecTerme, @RequestParam("nomRapport") String nomRapport,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report FactureVente List", type);

		RechercheMulticritereDetailReglementAchatValue request = new RechercheMulticritereDetailReglementAchatValue();
		request.setDateReglementAu(stringToCalendar(dateReglementAu));
		request.setDateReglementDu(stringToCalendar(dateReglementDu));
		request.setDateEcheanceDu(stringToCalendar(dateEcheanceDu));
		request.setDateEcheanceAu(stringToCalendar(dateEcheanceAu));
		request.setNumPiece(numPiece);
		request.setPartieIntId(partieIntId);
		request.setReference(reference);
		request.setRegle(regle);
		request.setTypeReglementId(typeReglementId);
		request.setAvecTerme(avecTerme);
		request.setNomRapport(nomRapport);
		request.setReferenceDetReglement(referenceDetReglement);

		
		request.setDateDepotBanqueDe(stringToCalendar(dateDepotBanqueDe));
		
		request.setDateDepotBanqueA(stringToCalendar(dateDepotBanqueA));
		
		
		EcheancierReportListValue report = gestionnaireReportAchatService.getListEcheanceReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}
	
	@RequestMapping(value = "/ficheFournisseur", method = RequestMethod.GET)
	public void generateFicheFournisseurReport(@RequestParam("type") String type, @RequestParam("clientId") Long clientId,
			@RequestParam("dateMin") String dateMin, @RequestParam("dateMax") String dateMax,
			@RequestParam("typeRapport") String typeRapport, HttpServletResponse response)
			throws JRException, IOException {

		//logger.info("Generate a {} Report FciheClient", type);

		RechercheMulticritereFicheFournisseurValue request = new RechercheMulticritereFicheFournisseurValue();
		request.setClientId(clientId);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		request.setTypeRapport(typeRapport);

		FicheClientReportValue report = gestionnaireReportAchatService.getFicheFournisseurReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}
	
	
	@RequestMapping(value = "/situation", method = RequestMethod.GET)
	public void generateListSituationReport(
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
		SituationReportingReportListValue report = gestionnaireReportAchatService
				.getListSituationReport(request,solde);

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
