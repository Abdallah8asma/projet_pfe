package com.gpro.consulting.tiers.logistique.domaine.gs.report;

import java.io.IOException;

import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.BonMouvementStockReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatMouvementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockDetailleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockGlobalReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryDetailleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;

/**
 * GestionnaireReportGsDomaine Interface
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */
public interface IGestionnaireReportGsDomaine {
	
	public MouvementStockHistoryReportValue getHistoryReport(RechercheMulticritereMouvementValue critere) throws IOException;

	public EtatStockReportValue getEtatReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException;

	public EtatStockReportValue getEtatNonMouvementesReport(RechercheMulticritereEntiteStockValue critere) throws IOException;

	public EtatMouvementReportValue getEtatMouvementReport(RequestRechecheMouvementValue request) throws IOException;
//Mvt OF: TODO: change the name 
	public BonMouvementStockReportValue getBonMouvementStockById(Long id) throws IOException;
//Mvt Stock
	public BonMouvementStockReportValue getBonMouvementStockEntreeSortieById(Long id) throws IOException;
	
	//Added on 30/10/2016, by Zeineb MEDIMAGH
	
	public EtatStockGlobalReportValue getEtatGlobalReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException;
	
	public EtatStockDetailleReportValue getEtatDetailleReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException;
	
	//Added on 15/11/2016 by Zeineb
	
	public MouvementStockHistoryDetailleReportValue getHistoryReportDetaille(RechercheMulticritereMouvementValue critere) throws IOException;

	public FicheColisReportValue generateListEtatStockBarCodeReport(RechercheMulticritereEntiteStockValue request)throws IOException;

	public FicheColisReportValue generateListEtatStockBarCodeFromBEReport(Long id) throws IOException;

}
