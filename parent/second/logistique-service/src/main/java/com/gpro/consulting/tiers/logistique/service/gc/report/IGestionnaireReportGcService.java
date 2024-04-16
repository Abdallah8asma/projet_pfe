package com.gpro.consulting.tiers.logistique.service.gc.report;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.transaction.annotation.Transactional;

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
import com.gpro.consulting.tiers.logistique.coordination.gc.report.mouvementproduit.MouvementProduitReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.soldeClient.value.SoldeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;

/**
 * GC GestionnaireReport Service Interface
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */
public interface IGestionnaireReportGcService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonLivraisonReportValue getBonLivraisonReportValue(Long id, String avecPrix) throws IOException;
	
	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonLivraisonReportValue getBonLivraisonReportValue(Long id, String avecPrix,Long typerapport) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureReportValue getFactureReportValue(Long id,Long typerapport , boolean avecObservation) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EcheancierReportListValue getListEcheanceReport(RechercheMulticritereDetailReglementValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheClientReportValue getFicheClientReport(RechercheMulticritereFicheClientValue request)
			throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheGroupeClientReportValue getFicheGroupeClientReport(RechercheMulticritereFicheGroupeClientValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SoldeClientReportValue getSoldeClientReport(RechercheMulticritereSoldeClientValue request)
			throws IOException;

	// Added on 18/11/2016, by Zeineb Medimagh
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonCommandeReportValue getBonCommandeParIdReport(Long id,Long numrapport,String typerapport ,String avecPrix,String avecEntete) throws IOException;

	// Added on 30/01/2017, by Hajer AMRI
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException;

	// getReglementReportParRef
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException;
	
	// Added on 24/03/2017, by Hajer AMRI
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix) throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public MouvementProduitReportValue getMouvementProduitElementReport(Long produitId, Calendar  dateMin,Calendar dateMax) ;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureReportElementRecapValue getFactureReportElementRecapValue(RechercheMulticritereFactureValue request);



	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EcheancierReportListValue getListEcheanceInverseReport(RechercheMulticritereDetailReglementValue request)
			throws IOException;
	
}
