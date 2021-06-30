package com.gpro.consulting.tiers.logistique.rest.gc.report.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.RechercheMulticritereFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value.BonCommandeReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value.EcheancierReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.fichegroupeclient.value.FicheGroupeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.soldeClient.value.SoldeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService;

import net.sf.jasperreports.engine.JRException;

/**
 * Gestionnaire de reporting pour la Gestion Commerciale
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */
@SuppressWarnings("static-access")
@Controller
@RequestMapping("/reportgc")
public class GestionnaireReportGcRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGcRestImpl.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private IGestionnaireReportGcService gestionnaireReportGcService;

	@Autowired
	private IMarcheService marcheService;

	@Autowired
	private IModePaiementService modePaiementService;

	@RequestMapping(value = "/bonlivraison", method = RequestMethod.GET)
	public void genererBonLivraisonReport(@RequestParam("id") Long id,
			@RequestParam(value = "avecPrix", required = false, defaultValue = "oui") String avecPrix,
			@RequestParam("typerapport") Long typerapport,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report BonLivraison", typerapport);
		

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService.getBonLivraisonReportValue(id,avecPrix,typerapport);

		if (bonLivraisonReport.getMarcheId() != null) {

			Map<Long, MarcheValue> mapMarcheById = new HashMap<Long, MarcheValue>();
			List<MarcheValue> marcheList = marcheService.getAll();

			for (MarcheValue marche : marcheList) {
				Long key = marche.getId();
				if (mapMarcheById.get(key) == null) {
					mapMarcheById.put(marche.getId(), marche);
				}            
			}

			MarcheValue marche = mapMarcheById.get(bonLivraisonReport.getMarcheId());

			if (marche != null) {
				bonLivraisonReport.setMarche(marche.getDesignation());
			}
		}

		if (bonLivraisonReport.getModepaiementId() != null) {

			Map<Long, ModePaiementValue> mapModePaiementById = new HashMap<Long, ModePaiementValue>();
			List<ModePaiementValue> modePaiementList = modePaiementService.getAll();

			for (ModePaiementValue marche : modePaiementList) {
				Long key = marche.getId();
				if (mapModePaiementById.get(key) == null) {
					mapModePaiementById.put(marche.getId(), marche);
				}
			}

			ModePaiementValue modePaiement = mapModePaiementById.get(bonLivraisonReport.getModepaiementId());

			if (modePaiement != null) {
				bonLivraisonReport.setModepaiement(modePaiement.getDesignation());
			}
		}
      
        
        
		this.download(type, bonLivraisonReport.getReportStream(), bonLivraisonReport.getParams(),
				bonLivraisonReport.getFileName(), bonLivraisonReport.getJRBeanCollectionDataSource(), response);
	}

	@RequestMapping(value = "/facture", method = RequestMethod.GET)
	public void generateFactureVenteReport(@RequestParam("id") Long id,@RequestParam("typerapport") Long typerapport, 
			@RequestParam("avecObservation") boolean avecObservation ,
			@RequestParam("type") String type,
			 
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report FactureVente", type);

		FactureReportValue factureReport = gestionnaireReportGcService.getFactureReportValue(id,typerapport,avecObservation);
		
		
		this.download(type, factureReport.getReportStream(), factureReport.getParams(), factureReport.getFileName(),
				factureReport.getJRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/listfacture", method = RequestMethod.GET)
	public void generateListFactureVenteReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("referenceFacture") String referenceFacture, @RequestParam("referenceBl") String referenceBl,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateFactureMin") String dateFactureMin,
			@RequestParam("dateFactureMax") String dateFactureMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("typeFacture") String typeFacture,
			@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("devise") Long devise,
			
			@RequestParam("dateEcheanceDe") String dateEcheanceDe,
			@RequestParam("dateEcheanceA") String dateEcheanceA,
			@RequestParam("declarerecherche") String declarerecherche,
			
			
			
			HttpServletResponse response)
			throws JRException, IOException {

		//logger.info("Generate a {} Report FactureVente List");
		RechercheMulticritereFactureValue request = new RechercheMulticritereFactureValue();
	    request.setDateFactureMax(stringToCalendar(dateFactureMax));
		request.setDateFactureMin(stringToCalendar(dateFactureMin));
		request.setMetrageMax(metrageMax);
		request.setMetrageMin(metrageMin);
		request.setPartieIntId(partieIntId);
		request.setPrixMax(prixMax);
		request.setPrixMin(prixMin);
		request.setReferenceBl(referenceBl);
		request.setReferenceFacture(referenceFacture);
		request.setType(typeFacture);
		request.setNatureLivraison(natureLivraison);
		
		request.setGroupeClientId(groupeClientId);
		
		request.setDevise(devise);
		
		request.setDateEcheanceDe(stringToCalendar(dateEcheanceDe));
		request.setDateEcheanceA(stringToCalendar(dateEcheanceA));
		
		request.setDeclarerecherche(declarerecherche);
		
		
		
		request.setOptimized(this.checkForOptimization(request));
		
		if (request.getDateFactureMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateFactureMin(date);
		}

		FactureReportListValue report = gestionnaireReportGcService.getListFactureReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/listbonlivraison", method = RequestMethod.GET)
	public void generateListBonlivraisonReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereBonLivraisonValue
			// request,
			@RequestParam("referenceBl") String referenceBl, @RequestParam("referenceBs") String referenceBs,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateLivraisonMin") String dateLivraisonMin,
			@RequestParam("dateLivraisonMax") String dateLivraisonMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("avecFacture") String avecFacture,@RequestParam("stock") Boolean stock,@RequestParam("idDepot") Long idDepot ,
			@RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("numof") String numof,
			@RequestParam("declare") String declare,
			
			HttpServletResponse response)
			throws JRException, IOException {

		//logger.info("Generate a {} Report Bonlivraison List", type);

		RechercheMulticritereBonLivraisonValue request = new RechercheMulticritereBonLivraisonValue();
		request.setReferenceBl(referenceBl);
		request.setReferenceBs(referenceBs);
		request.setPartieIntId(partieIntId);
		request.setDateLivraisonMin(stringToCalendar(dateLivraisonMin));
		request.setDateLivraisonMax(stringToCalendar(dateLivraisonMax));
		request.setMetrageMax(metrageMax);
		request.setMetrageMin(metrageMin);
		request.setPrixMax(prixMax);
		request.setPrixMin(prixMin);
		request.setNatureLivraison(natureLivraison);
		request.setAvecFacture(avecFacture);
		request.setStock(stock);
		request.setIdDepot(idDepot);
		
		request.setGroupeClientId(groupeClientId);
		
		request.setDeclare(declare);
		
		request.setNumOF(numof);
		request.setOptimized(this.checkForOptimization(request));
		
		if (request.getDateLivraisonMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateLivraisonMin(date);
		}

		BonLivraisonReportListValue report = gestionnaireReportGcService.getListBonlivraisonReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/listEcheance", method = RequestMethod.GET)
	public void generateListDetailReglementReport(@RequestParam("type") String type,
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
			@RequestParam("avecTerme") Boolean avecTerme,
			
			@RequestParam("declarerRech") String declarerRech,
			
			
			@RequestParam("nomRapport") String nomRapport,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report FactureVente List", type);

		RechercheMulticritereDetailReglementValue request = new RechercheMulticritereDetailReglementValue();
		
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
		
		
		request.setDeclarerRech(declarerRech);

		EcheancierReportListValue report = gestionnaireReportGcService.getListEcheanceReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/ficheClient", method = RequestMethod.GET)
	public void generateFicheClientReport(@RequestParam("type") String type, @RequestParam("clientId") Long clientId,
			@RequestParam("dateMin") String dateMin, @RequestParam("dateMax") String dateMax,
			@RequestParam("typeRapport") String typeRapport,
			@RequestParam("declarerRech") String declarerRech,
			
			HttpServletResponse response)
			throws JRException, IOException {

		//logger.info("Generate a {} Report FciheClient", type);

		RechercheMulticritereFicheClientValue request = new RechercheMulticritereFicheClientValue();
		request.setClientId(clientId);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		request.setTypeRapport(typeRapport);
		
		request.setDeclarerRech(declarerRech);

		FicheClientReportValue report = gestionnaireReportGcService.getFicheClientReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}
	
	
	@RequestMapping(value = "/ficheGroupe", method = RequestMethod.GET)
	public void generateFicheGroupeReport(@RequestParam("type") String type, @RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("dateMin") String dateMin, @RequestParam("dateMax") String dateMax,
			@RequestParam("typeRapport") String typeRapport, HttpServletResponse response)
			throws JRException, IOException {

		//logger.info("Generate a {} Report FciheClient", type);

		RechercheMulticritereFicheGroupeClientValue request = new RechercheMulticritereFicheGroupeClientValue();
		request.setGroupeClientId(groupeClientId);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		request.setTypeRapport(typeRapport);

		FicheGroupeClientReportValue report = gestionnaireReportGcService.getFicheGroupeClientReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/soldeClient", method = RequestMethod.GET)
	public void generateFicheClientReport(@RequestParam("type") String type, @RequestParam("clientId") Long clientId,
			@RequestParam("bloque") Boolean bloque, HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report soldeClient", type);

		RechercheMulticritereSoldeClientValue request = new RechercheMulticritereSoldeClientValue();
		request.setPartieIntId(clientId);
		request.setBloque(bloque);

		SoldeClientReportValue report = gestionnaireReportGcService.getSoldeClientReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	/***************************
	 * Bon commande report
	 ******************************/

	// Added on 18/11/2016, by Zeineb Medimagh

	@RequestMapping(value = "/bonCommande", method = RequestMethod.GET)
	public void genererBonCommandeReport(@RequestParam("id") Long id,@RequestParam("typerapport") String typerapport,
			@RequestParam(value = "avecPrix", required = false, defaultValue = "oui") String avecPrix,
			@RequestParam("avecEntete") String avecEntete,
			@RequestParam("type") String type,
			@RequestParam("numrapport") Long numrapport,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report BonCommande", type);

		BonCommandeReportValue bonCommandeReport = gestionnaireReportGcService.getBonCommandeParIdReport(id,numrapport,typerapport,avecPrix,avecEntete);
		
		
		//

		this.download(type, bonCommandeReport.getReportStream(), bonCommandeReport.getParams(),
				bonCommandeReport.getFileName(), bonCommandeReport.getJRBeanCollectionDataSource(), response);
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

	/*************************** List Reglement ******************************/
	// Added on 30/01/2017, by Hajer Amri

	@RequestMapping(value = "/reglementList", method = RequestMethod.GET)
	public void getReglementReport(@RequestParam("partieIntId") Long partieIntId,
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

		RechercheMulticritereReglementValue request = new RechercheMulticritereReglementValue();

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

		ReglementReportValue reglementReportValue = gestionnaireReportGcService.getReglementReport(request);

		this.download(type, reglementReportValue.getReportStream(), reglementReportValue.getParams(),
				reglementReportValue.getFileName(), reglementReportValue.getjRBeanCollectionDataSource(), response);
	}
	
	/*************************** Reglement ******************************/
	// Added on 31/01/2017, by Hajer Amri

	@RequestMapping(value = "/reglement", method = RequestMethod.GET)
	public void getReglementReportParRef(@RequestParam("id") Long id,
			@RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a Reglement", type);

		ReglementReportParRefValue reglementReportParRefValue = gestionnaireReportGcService.getReglementReportParRef(id);

		this.download(type, reglementReportParRefValue.getReportStream(), reglementReportParRefValue.getParams(),
				reglementReportParRefValue.getFileName(), reglementReportParRefValue.getjRBeanCollectionDataSource(), response);
	}
	
	
	/***************************
	 * Bon reception report
	 ******************************/

	// Added on 23/03/2017, by Hajer AMRI

	@RequestMapping(value = "/bonReception", method = RequestMethod.GET)
	public void genererBonRecReporeptiont(@RequestParam("id") Long id,
			@RequestParam(value = "avecPrix", required = false, defaultValue = "oui") String avecPrix,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//logger.info("Generate a {} Report BonReception", type);

		BReceptionReportValue bonReceptionReport = gestionnaireReportGcService.getBonReceptionParIdReport(id, avecPrix);

		this.download(type, bonReceptionReport.getReportStream(), bonReceptionReport.getParams(),
				bonReceptionReport.getFileName(), bonReceptionReport.getJRBeanCollectionDataSource(), response);
	}
	
	
	public boolean checkForOptimization(RechercheMulticritereBonLivraisonValue request) {

		return

		isNullOrEmpty(request.getDateLivraisonMin())
		&& isNullOrEmpty(request.getDateLivraisonMax())
		&& isNullOrEmpty(request.getPartieIntId())
		&& isNullOrEmpty(request.getGroupeClientId());

	}
	
	public boolean checkForOptimization(RechercheMulticritereFactureValue request) {

		return

		isNullOrEmpty(request.getDateFactureMin())
		&& isNullOrEmpty(request.getDateFactureMax())
		&& isNullOrEmpty(request.getPartieIntId())		
		&& isNullOrEmpty(request.getGroupeClientId());

	}
	
	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

}
