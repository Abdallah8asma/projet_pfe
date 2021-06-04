package com.gpro.consulting.tiers.logistique.domaine.gc.achat.report;

import java.io.IOException;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.RechercheMulticritereFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
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
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;

/**
 * GestionnaireReportGcDomaine Interface
 * 
 * @author Samer Hassen
 * @since 
 *
 */
public interface IGestionnaireReportAchatDomaine {

	public BonLivraisonReportValue getBonLivraisonParId(Long id, String avecPrix, Long typerapport) throws IOException;

	public FactureReportValue getFactureReportValue(Long id,Long typerapport) throws IOException;

	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException;

	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException;

	public EcheancierReportListValue getListEcheanceReport(RechercheMulticritereDetailReglementAchatValue request)
			throws IOException;

	public FicheClientReportValue getFicheClientReport(RechercheMulticritereFicheClientValue request)
			throws IOException;

	public SoldeClientReportValue getSoldeClientReport(RechercheMulticritereSoldeClientValue request)
			throws IOException;

	// Added on 18/11/2016, by Zeineb Medimagh

	public BonCommandeReportValue getBonCommandeParIdReport(Long id,String typerapport, String avecPrix) throws IOException;

	// Added on 30/01/2017, by Hajer AMRI
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException;

	/**
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException;
	


	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix,Long typeRapport) throws IOException;

	public FicheGroupeClientReportValue getFicheGroupeClientReport(RechercheMulticritereFicheGroupeClientValue request)	throws IOException;

	public FactureAchatReportValue getFactureAchatReportValue(Long id, Long typerapport)  throws IOException;

	public ReglementReportValue getReglementReport(RechercheMulticritereReglementAchatValue request) throws IOException;

	public FicheClientReportValue getFicheFournisseurReport(RechercheMulticritereFicheFournisseurValue request)throws IOException;

	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request, Long solde) 	throws IOException;

	public BLReportElementRecapValue getDepenseBRbyMonth(RechercheMulticritereBonReceptionAchatValue request);

	public EcheancierReportListValue getListEcheanceInverseReport(
			RechercheMulticritereDetailReglementAchatValue request) throws IOException;

}
