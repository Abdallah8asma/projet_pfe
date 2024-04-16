package com.gpro.consulting.tiers.logistique.service.gc.achat.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.report.IGestionnaireReportAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.report.IGestionnaireReportAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService;

/**
 * Implementation of {@link IGestionnaireReportGcService}
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */
@Service
@Transactional
public class GestionnaireReportAchatServiceImpl implements IGestionnaireReportAchatService{
	
	@Autowired
	private IGestionnaireReportAchatDomaine gestionnaireReportAchatDomaine;
	
	@Override
	public BonLivraisonReportValue getBonLivraisonReportValue(Long id, String avecPrix, Long typerapport) throws IOException{
		
		return gestionnaireReportAchatDomaine.getBonLivraisonParId(id, avecPrix ,typerapport);
		
	}

	@Override
	public FactureReportValue getFactureReportValue(Long id,Long typerapport) throws IOException {
		
		return gestionnaireReportAchatDomaine.getFactureReportValue(id,typerapport);
	}

	@Override
	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException {
		
		return gestionnaireReportAchatDomaine.getListFactureReport(request);
	}

	@Override
	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException {
		
		return gestionnaireReportAchatDomaine.getListBonlivraisonReport(request);
	}

	@Override
	public EcheancierReportListValue getListEcheanceReport(
			RechercheMulticritereDetailReglementAchatValue request)
			throws IOException {
		return gestionnaireReportAchatDomaine.getListEcheanceReport(request);
	}

	@Override
	public FicheClientReportValue getFicheClientReport(
			RechercheMulticritereFicheClientValue request) throws IOException {
		
		return gestionnaireReportAchatDomaine.getFicheClientReport(request);
	}
	
	@Override
	public SoldeClientReportValue getSoldeClientReport(
			RechercheMulticritereSoldeClientValue request) throws IOException {
		
		return gestionnaireReportAchatDomaine.getSoldeClientReport(request);
	}
	@Override
	public BonCommandeReportValue getBonCommandeParIdReport(Long id,String typerapport ,String avecPrix) throws IOException {
		return gestionnaireReportAchatDomaine.getBonCommandeParIdReport(id,typerapport, avecPrix);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService#getReglementReport(com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue)
	 */
	@Override
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException {

		return gestionnaireReportAchatDomaine.getReglementReport(request);
	}
	
	//getReglementReportParRef
	@Override
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException {

		return gestionnaireReportAchatDomaine.getReglementReportParRef(id);
	}
	
	//getBonReceptionParIdReport
	@Override
	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix,Long typeRapport) throws IOException {
		return gestionnaireReportAchatDomaine.getBonReceptionParIdReport(id, avecPrix,typeRapport);
	}

	@Override
	public FicheGroupeClientReportValue getFicheGroupeClientReport(RechercheMulticritereFicheGroupeClientValue request)
			throws IOException {
		return gestionnaireReportAchatDomaine.getFicheGroupeClientReport(request);
	}

	@Override
	public FactureAchatReportValue getFactureAchatReportValue(Long id, Long typerapport)  throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportAchatDomaine.getFactureAchatReportValue(id,typerapport);
	}

	@Override
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementAchatValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportAchatDomaine.getReglementReport(request);
	}

	@Override
	public FicheClientReportValue getFicheFournisseurReport(RechercheMulticritereFicheFournisseurValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportAchatDomaine.getFicheFournisseurReport(request);
	}

	@Override
	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request, Long solde) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportAchatDomaine.getListSituationReport(request,solde);
	}

	@Override
	public EcheancierReportListValue getListEcheanceInverseReport(
			RechercheMulticritereDetailReglementAchatValue request) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportAchatDomaine.getListEcheanceInverseReport(request);
	}
	

}
