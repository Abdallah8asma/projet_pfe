package com.gpro.consulting.tiers.logistique.service.gs.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.gpro.consulting.tiers.logistique.domaine.gs.report.IGestionnaireReportGsDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.report.IGestionnaireReportGsService;

/**
 * Implementation of {@link IGestionnaireReportGsService}
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */

@Service
@Transactional
public class GestionnaireReportGsServiceImpl implements IGestionnaireReportGsService{
	
	@Autowired
	private IGestionnaireReportGsDomaine gestionnaireReportGsDomaine;
	
	@Override
	public MouvementStockHistoryReportValue getHistoryReport(RechercheMulticritereMouvementValue critere) throws IOException {
		
		return gestionnaireReportGsDomaine.getHistoryReport(critere);
	}

	@Override
	public EtatStockReportValue getEtatReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException {

		return gestionnaireReportGsDomaine.getEtatReport(critere, typeRapport);
	}
	
	@Override
	public EtatStockReportValue getEtatNonMouvementesReport(RechercheMulticritereEntiteStockValue critere) throws IOException {

		return gestionnaireReportGsDomaine.getEtatNonMouvementesReport(critere);
	}

	@Override
	public EtatMouvementReportValue getEtatMouvementReport(RequestRechecheMouvementValue request) throws IOException {
		
		return gestionnaireReportGsDomaine.getEtatMouvementReport(request);
	}

	@Override
	public BonMouvementStockReportValue getBonMouvementStockById(Long id) throws IOException {
		
		return gestionnaireReportGsDomaine.getBonMouvementStockById(id);
	}
	
	@Override
	public BonMouvementStockReportValue getBonMouvementStockEntreeSortieById(Long id) throws IOException {
		
		return gestionnaireReportGsDomaine.getBonMouvementStockEntreeSortieById(id);
	}

	@Override
	public EtatStockGlobalReportValue getEtatGlobalReport(RechercheMulticritereEntiteStockValue critere,
			String typeRapport) throws IOException {
		System.out.println("Generate a {} Report EtatStockGlobal service layer");
		return gestionnaireReportGsDomaine.getEtatGlobalReport(critere, typeRapport);
	}

	@Override
	public EtatStockDetailleReportValue getEtatDetailleReport(RechercheMulticritereEntiteStockValue critere,
			String typeRapport) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportGsDomaine.getEtatDetailleReport(critere, typeRapport);
	}

	@Override
	public MouvementStockHistoryDetailleReportValue getHistoryReportDetaille(
			RechercheMulticritereMouvementValue critere) throws IOException {
		return gestionnaireReportGsDomaine.getHistoryReportDetaille(critere);
	}

	@Override
	public FicheColisReportValue generateListEtatStockBarCodeReport(RechercheMulticritereEntiteStockValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportGsDomaine.generateListEtatStockBarCodeReport(request);
	}

	@Override
	public FicheColisReportValue generateListEtatStockBarCodeFromBEReport(Long id) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportGsDomaine.generateListEtatStockBarCodeFromBEReport(id);
	}


	
	
}
