package com.gpro.consulting.tiers.logistique.service.gc.chart.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
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
import com.gpro.consulting.tiers.logistique.domaine.gc.chart.IChartGcDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.chart.IChartGcService;


@Service
public class ChartGcServiceImpl implements IChartGcService {

	@Autowired
	private IChartGcDomaine chartGcDomaine;
	
	@Override
	public List<BLReportElementRecapValue> getChiffreAffaireBLbyMonth(
			RechercheMulticritereBonLivraisonValue request) {
		// TODO Auto-generated method stub
		return chartGcDomaine.getChiffreAffaireBLbyMonth(request);
	}

	@Override
	public List<FactureReportElementRecapValue> getChiffreAffaireFactureByMonth(
			RechercheMulticritereFactureValue request) {
		// TODO Auto-generated method stub
		return chartGcDomaine.getChiffreAffaireFactureByMonth(request);
	}

	@Override
	public List<RegelementReportElementRecapValue> getReglementByMonth(RechercheMulticritereReglementValue request) {

		return chartGcDomaine.getReglementByMonth(request);
	}

	@Override
	public List<ProduitValue> getTop10Article(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getTop10Article(request);

	}

	@Override
	public List<ResultBestElementValue> getTop10Client(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getTop10Client(request);

	}

	@Override
	public List<ResultBestElementValue> getChiffreAffairebyClient(RechercheMulticritereDetLivraisonValue request) {
		 
		return chartGcDomaine.getChiffreAffaireByClient(request);
	}

	@Override
	public List<ResultBestElementValue> getChiffreAffairebySousFamille(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getChiffreAffaireBySousFamille(request);

	}

	@Override
	public List<ResultBestElementValue> getChiffreAffairebyFamille(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getChiffreAffaireByFamille(request);
	}

	@Override
	public List<BLReportElementRecapValue> getDepenseBRbyMonth(RechercheMulticritereBonReceptionAchatValue request) {

		return chartGcDomaine.getDepenseBRbyMonth(request);

	}

	@Override
	public List<BLReportElementRecapValue> getDepenseFacturebyMonth(
			RechercheMulticritereFactureAchatValue request) {

		return chartGcDomaine.getDepenseFacturebyMonth(request);

	}

	@Override
	public List<BLReportElementRecapValue> getReglementAchatByMonth(RechercheMulticritereReglementValue request) {

		return chartGcDomaine.getReglementAchatByMonth(request);
	}

	@Override
	public List<ResultBestElementValue> getTop10ArticleAchat(RechercheMulticritereProduitReceptionAchatValue request) {

		return chartGcDomaine.getTop10ArticleAchat(request);

	}

	@Override
	public List<ResultBestElementValue> getTop3Fournisseur(RechercheMulticritereProduitReceptionAchatValue request) {

		return chartGcDomaine.getTop3Fournisseur(request);
	}

	@Override
	public List<ResultBestElementValue> getDepenseFournisseur(RechercheMulticritereProduitReceptionAchatValue request) {

		return chartGcDomaine.getDepenseFournisseur(request);
	}

	@Override
	public Map<String,ResultBestElementValue> getEtiquettes(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getEtiquettes(request);
	}
	
	@Override
	public ResultBestElementValue getEtiquetteMeilleurClient(RechercheMulticritereDetLivraisonValue request) {

		
		return chartGcDomaine.getEtiquetteMeilleurClient(request);
	}

	@Override
	public ResultBestElementValue getEtiquetteCAFacture(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getEtiquetteCAFacture(request);
	}

	@Override
	public ResultBestElementValue getEtiquetteReglement(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getEtiquetteReglement(request);
	}

	@Override
	public ResultBestElementValue getEtiquetteNbrClient(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getEtiquetteNbrClient(request);
	}

	@Override
	public ResultBestElementValue getEtiquetteNbrFournisseur(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getEtiquetteNbrFournisseur(request);
	}

	@Override
	public ResultBestElementValue getEtiquetteReglementEchus(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getEtiquetteReglementEchus(request);
	}
	
	@Override
	public List<ResultBestElementValue> getChiffreAffairebyGroupe(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getChiffreAffairebyGroupe(request);
	}

	@Override
	public List<ResultBestElementValue> getTop10Groupe(RechercheMulticritereDetLivraisonValue request) {

		return chartGcDomaine.getTop10Groupe(request);
	}

	@Override
	public List<FactureReportElementRecapValue> getDifferenceChiffreAffaireVenteAchatByMonth(
			RechercheMulticritereFactureValue request) {
		// TODO Auto-generated method stub
		return chartGcDomaine.getDifferenceChiffreAffaireVenteAchatByMonth(request);
	}

	@Override
	public List<ReglementChartValue> getReglementChart(RechercheMulticritereReglementValue request) {
		// TODO Auto-generated method stub
		return chartGcDomaine.getReglementChart(request);
	}
	
	
	
	

}
