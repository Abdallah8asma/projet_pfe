package com.gpro.consulting.tiers.logistique.domaine.gc.chart;

import java.util.List;
import java.util.Map;

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

public interface IChartGcDomaine {

	List<BLReportElementRecapValue> getChiffreAffaireBLbyMonth(RechercheMulticritereBonLivraisonValue request);

	List<FactureReportElementRecapValue> getChiffreAffaireFactureByMonth(RechercheMulticritereFactureValue request);

	List<RegelementReportElementRecapValue> getReglementByMonth(RechercheMulticritereReglementValue request);

	List<ProduitValue> getTop10Article(RechercheMulticritereDetLivraisonValue request);

	List<ResultBestElementValue> getTop10Client(RechercheMulticritereDetLivraisonValue request);
	
	List<ResultBestElementValue> getChiffreAffaireByClient(RechercheMulticritereDetLivraisonValue request);

	List<ResultBestElementValue> getChiffreAffaireBySousFamille(RechercheMulticritereDetLivraisonValue request);

	List<ResultBestElementValue> getChiffreAffaireByFamille(RechercheMulticritereDetLivraisonValue request);

	List<BLReportElementRecapValue> getDepenseBRbyMonth(RechercheMulticritereBonReceptionAchatValue request);

	List<BLReportElementRecapValue> getDepenseFacturebyMonth(RechercheMulticritereFactureAchatValue request);

	List<BLReportElementRecapValue> getReglementAchatByMonth(RechercheMulticritereReglementValue request);

	List<ResultBestElementValue> getTop10ArticleAchat(RechercheMulticritereProduitReceptionAchatValue request);

	List<ResultBestElementValue> getTop3Fournisseur(RechercheMulticritereProduitReceptionAchatValue request);

	List<ResultBestElementValue> getDepenseFournisseur(RechercheMulticritereProduitReceptionAchatValue request);

	Map<String,ResultBestElementValue> getEtiquettes(RechercheMulticritereDetLivraisonValue request);
	
	ResultBestElementValue getEtiquetteMeilleurClient(RechercheMulticritereDetLivraisonValue request);

	ResultBestElementValue getEtiquetteCAFacture(RechercheMulticritereDetLivraisonValue request);

	ResultBestElementValue getEtiquetteReglement(RechercheMulticritereDetLivraisonValue request);

	ResultBestElementValue getEtiquetteNbrClient(RechercheMulticritereDetLivraisonValue request);

	ResultBestElementValue getEtiquetteNbrFournisseur(RechercheMulticritereDetLivraisonValue request);

	ResultBestElementValue getEtiquetteReglementEchus(RechercheMulticritereDetLivraisonValue request);

	List<ResultBestElementValue> getChiffreAffairebyGroupe(RechercheMulticritereDetLivraisonValue request);

	List<ResultBestElementValue> getTop10Groupe(RechercheMulticritereDetLivraisonValue request);

	List<FactureReportElementRecapValue> getDifferenceChiffreAffaireVenteAchatByMonth(
			RechercheMulticritereFactureValue request);

	List<ReglementChartValue> getReglementChart(RechercheMulticritereReglementValue request);


	


}
