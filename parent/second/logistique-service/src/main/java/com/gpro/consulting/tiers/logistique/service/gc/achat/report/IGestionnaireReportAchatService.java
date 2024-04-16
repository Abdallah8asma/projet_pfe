package com.gpro.consulting.tiers.logistique.service.gc.achat.report;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.RechercheMulticritereFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.report.facture.value.FactureAchatReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.RechercheMulticritereFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value.BonCommandeReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value.EcheancierReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.fichegroupeclient.value.FicheGroupeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.soldeClient.value.SoldeClientReportValue;
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
public interface IGestionnaireReportAchatService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonLivraisonReportValue getBonLivraisonReportValue(Long id, String avecPrix,Long typerapport) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureReportValue getFactureReportValue(Long id,Long typerapport) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EcheancierReportListValue getListEcheanceReport(RechercheMulticritereDetailReglementAchatValue request)
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
	public BonCommandeReportValue getBonCommandeParIdReport(Long id, String avecPrix, String typerapport) throws IOException;

	// Added on 30/01/2017, by Hajer AMRI
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException;

	// getReglementReportParRef
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException;
	
	// Added on 24/03/2017, by Hajer AMRI
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix, Long typeRapport) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureAchatReportValue getFactureAchatReportValue(Long id, Long typerapport)  throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementAchatValue request) throws IOException;

	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheClientReportValue getFicheFournisseurReport(RechercheMulticritereFicheFournisseurValue request) 	throws IOException;

	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request, Long solde) 	throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EcheancierReportListValue getListEcheanceInverseReport(RechercheMulticritereDetailReglementAchatValue request)
			throws IOException;


}
