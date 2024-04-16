package com.gpro.consulting.tiers.logistique.service.gs.report;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
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
 * GS GestionnaireReport Service Interface
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */
public interface IGestionnaireReportGsService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public MouvementStockHistoryReportValue getHistoryReport(RechercheMulticritereMouvementValue critere) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EtatStockReportValue getEtatReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EtatStockReportValue getEtatNonMouvementesReport(RechercheMulticritereEntiteStockValue critere) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EtatMouvementReportValue getEtatMouvementReport(RequestRechecheMouvementValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonMouvementStockReportValue getBonMouvementStockById(Long id) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonMouvementStockReportValue getBonMouvementStockEntreeSortieById(Long id)
			throws IOException;
	
	// Added on 30/10/2016 , by Zeineb Medimagh
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EtatStockGlobalReportValue getEtatGlobalReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EtatStockDetailleReportValue getEtatDetailleReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException;

	//Added on 15/11/2016 by Zeineb
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	public MouvementStockHistoryDetailleReportValue getHistoryReportDetaille(RechercheMulticritereMouvementValue critere) throws IOException;


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheColisReportValue generateListEtatStockBarCodeReport(
			RechercheMulticritereEntiteStockValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheColisReportValue generateListEtatStockBarCodeFromBEReport(Long id) throws IOException;

}
