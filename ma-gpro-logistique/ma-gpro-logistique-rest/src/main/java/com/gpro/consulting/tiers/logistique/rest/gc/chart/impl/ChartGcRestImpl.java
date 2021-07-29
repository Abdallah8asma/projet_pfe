package com.gpro.consulting.tiers.logistique.rest.gc.chart.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RegelementReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.service.gc.chart.IChartGcService;
import com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService;

/**
 * 
 * 
 * @author Samer hassen
 * @since 
 * 
 */

@Controller
@RequestMapping("/chartGc")
public class ChartGcRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ChartGcRestImpl.class);

	
	
	@Autowired
	private IGestionnaireReportGcService gestionnaireReportGcService;


	@Autowired
	private IChartGcService chartGcService;
	
	
	/**************** WS Vente *******************/
	
	
	@RequestMapping(value = "/getReglementChart", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ReglementChartValue> getReglementChart(
			@RequestBody RechercheMulticritereReglementValue request) {
		
		
		return chartGcService.getReglementChart(request);
	}
	
	
	

	@RequestMapping(value = "/getChiffreAffaireBLbyMonth", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<BLReportElementRecapValue> rechercherMultiCritere(
			@RequestBody RechercheMulticritereBonLivraisonValue request) {
		
		
		return chartGcService.getChiffreAffaireBLbyMonth(request);
	}

	
	@RequestMapping(value = "/getChiffreAffaireFactureByMonth", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<FactureReportElementRecapValue> rechercherMultiCritere(
			@RequestBody RechercheMulticritereFactureValue request) {
		
		
		return chartGcService.getChiffreAffaireFactureByMonth(request);
	}
	
	@RequestMapping(value = "/getReglementByMonth", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<RegelementReportElementRecapValue> rechercherMultiCritereReglement(
			@RequestBody RechercheMulticritereReglementValue request) {
		
		
		return chartGcService.getReglementByMonth(request);
	}
	
	
	@RequestMapping(value = "/getTop10Article", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ProduitValue> rechercherMultiCritereTop10Article(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getTop10Article(request);
	}
	
	@RequestMapping(value = "/getTop10Client", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereTop10Client(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getTop10Client(request);
	}
	
	@RequestMapping(value = "/getTop10Groupe", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereTop10Groupe(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getTop10Groupe(request);
	}
	
	@RequestMapping(value = "/getChiffreAffaireByClient", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereCaByClient(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getChiffreAffairebyClient(request);
	}
	
	@RequestMapping(value = "/getChiffreAffaireByGroupe", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereCaByGroupe(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getChiffreAffairebyGroupe(request);
	}
	
	@RequestMapping(value = "/getChiffreAffaireBySousFamille", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereCaBySousFamille(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getChiffreAffairebySousFamille(request);
	}
	
	@RequestMapping(value = "/getChiffreAffaireByFamille", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereCaByFamille(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getChiffreAffairebyFamille(request);
	}
	
	
	
	
	
	/**************** WS Achat *****************/
	
	@RequestMapping(value = "/getDepenseBRbyMonth", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<BLReportElementRecapValue> rechercherMultiCritereDepenseBR(
			@RequestBody RechercheMulticritereBonReceptionAchatValue request) {
		
		
		return chartGcService.getDepenseBRbyMonth(request);
	}
	
	@RequestMapping(value = "/getDepenseFacturebyMonth", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<BLReportElementRecapValue> rechercherMultiCritereDepenseFacture(
			@RequestBody RechercheMulticritereFactureAchatValue request) {
		
		
		return chartGcService.getDepenseFacturebyMonth(request);
	}
	
	@RequestMapping(value = "/getReglementAchatByMonth", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<BLReportElementRecapValue> rechercherMultiCritereReglementAchat(
			@RequestBody RechercheMulticritereReglementValue request) {
		
		
		return chartGcService.getReglementAchatByMonth(request);
	}
	
	@RequestMapping(value = "/getTop10ArticleAchat", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereTop10ArticleAchat(
			@RequestBody RechercheMulticritereProduitReceptionAchatValue request) {
		
		
		return chartGcService.getTop10ArticleAchat(request);
	}
	
	
	@RequestMapping(value = "/getTop3Fournisseur", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereTop3Fournisseur(
			@RequestBody RechercheMulticritereProduitReceptionAchatValue request) {
		
		
		return chartGcService.getTop3Fournisseur(request);
	}
	
	
	@RequestMapping(value = "/getDepenseFournisseur", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<ResultBestElementValue> rechercherMultiCritereDepenseFournisseur(
			@RequestBody RechercheMulticritereProduitReceptionAchatValue request) {
		
		
		return chartGcService.getDepenseFournisseur(request);
	}
	
	
	
	/**************** WS Etiquette tableau de bord *********************/
	@RequestMapping(value = "/tableauDeBordEtiquettes", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String,ResultBestElementValue> rechercherMultiCritereEtiquettes(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getEtiquettes(request);
	}
	
	
	@RequestMapping(value = "/etuiquetteMeilleurClient", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBestElementValue rechercherMultiCritereEtiquetteMeilleurClient(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getEtiquetteMeilleurClient(request);
	}
	
	@RequestMapping(value = "/etuiquetteCAFacture", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBestElementValue rechercherMultiCritereEtiquetteCAFacture(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getEtiquetteCAFacture(request);
	}
	
	@RequestMapping(value = "/etuiquetteReglement", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBestElementValue rechercherMultiCritereEtiquetteReglement(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getEtiquetteReglement(request);
	}
	
	@RequestMapping(value = "/etuiquetteNbrClient", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBestElementValue rechercherMultiCritereEtiquetteNbrClient(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getEtiquetteNbrClient(request); 
	}
	@RequestMapping(value = "/etuiquetteNbrFournisseur", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBestElementValue rechercherMultiCritereEtiquetteNbrFournisseur(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getEtiquetteNbrFournisseur(request);
	}
	
	@RequestMapping(value = "/etuiquetteReglementEchus", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultBestElementValue rechercherMultiCritereEtiquetteReglementEchus(
			@RequestBody RechercheMulticritereDetLivraisonValue request) {
		
		
		return chartGcService.getEtiquetteReglementEchus(request);
	}
	
	
	

	
	@RequestMapping(value = "/getDifferenceChiffreAffaireVenteAchatByMonth", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<FactureReportElementRecapValue> getDifferenceChiffreAffaireVenteAchatByMonth(
			@RequestBody RechercheMulticritereFactureValue request) {
		
		
		return chartGcService.getDifferenceChiffreAffaireVenteAchatByMonth(request);
	}
	

	public boolean checkForOptimization(RechercheMulticritereBonLivraisonValue request) {

		return

		isNullOrEmpty(request.getDateLivraisonMin())
		&& isNullOrEmpty(request.getDateLivraisonMax())
		&& isNullOrEmpty(request.getPartieIntId())
		&& isNullOrEmpty(request.getGroupeClientId());

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

}
