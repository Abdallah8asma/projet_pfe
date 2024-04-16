package com.gpro.consulting.tiers.logistique.service.gc.report.impl;

import java.io.IOException;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.gpro.consulting.tiers.logistique.domaine.gc.report.IGestionnaireReportGcDomaine;
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
public class GestionnaireReportGcServiceImpl implements IGestionnaireReportGcService{
	
	@Autowired
	private IGestionnaireReportGcDomaine gestionnaireReportGcDomaine;
	
	@Override
	public BonLivraisonReportValue getBonLivraisonReportValue(Long id, String avecPrix, Long typerapport) throws IOException{
		
		return gestionnaireReportGcDomaine.getBonLivraisonParId(id, avecPrix ,typerapport);
		
	}

	@Override
	public FactureReportValue getFactureReportValue(Long id,Long typerapport,boolean avecObservation) throws IOException {
		
		return gestionnaireReportGcDomaine.getFactureReportValue(id,typerapport,avecObservation);
	}

	@Override
	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException {
		
		return gestionnaireReportGcDomaine.getListFactureReport(request);
	}

	@Override
	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException {
		
		return gestionnaireReportGcDomaine.getListBonlivraisonReport(request);
	}

	@Override
	public EcheancierReportListValue getListEcheanceReport(
			RechercheMulticritereDetailReglementValue request)
			throws IOException {
		return gestionnaireReportGcDomaine.getListEcheanceReport(request);
	}

	@Override
	public FicheClientReportValue getFicheClientReport(
			RechercheMulticritereFicheClientValue request) throws IOException {
		
		return gestionnaireReportGcDomaine.getFicheClientReport(request);
	}
	
	@Override
	public SoldeClientReportValue getSoldeClientReport(
			RechercheMulticritereSoldeClientValue request) throws IOException {
		
		return gestionnaireReportGcDomaine.getSoldeClientReport(request);
	}


	@Override
	public BonCommandeReportValue getBonCommandeParIdReport(Long id,Long numrapport,String typerapport ,String avecPrix,String avecEntete) throws IOException {
		return gestionnaireReportGcDomaine.getBonCommandeParIdReport(id,typerapport,numrapport, avecPrix,avecEntete);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService#getReglementReport(com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue)
	 */
	@Override
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException {

		return gestionnaireReportGcDomaine.getReglementReport(request);
	}
	
	//getReglementReportParRef
	@Override
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException {

		return gestionnaireReportGcDomaine.getReglementReportParRef(id);
	}
	
	//getBonReceptionParIdReport
	@Override
	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix) throws IOException {
		return gestionnaireReportGcDomaine.getBonReceptionParIdReport(id, avecPrix);
	}

	@Override
	public FicheGroupeClientReportValue getFicheGroupeClientReport(RechercheMulticritereFicheGroupeClientValue request)
			throws IOException {
		return gestionnaireReportGcDomaine.getFicheGroupeClientReport(request);
	}

	@Override
	public MouvementProduitReportValue getMouvementProduitElementReport(Long produitId, Calendar dateMin,
			Calendar dateMax) {
	
		return gestionnaireReportGcDomaine.getMouvementProduitElementReport(produitId,dateMin,dateMax);
	}

	@Override
	public FactureReportElementRecapValue getFactureReportElementRecapValue(RechercheMulticritereFactureValue request) {
		// TODO Auto-generated method stub
		return gestionnaireReportGcDomaine.getFactureReportElementRecapValue(request);
	}

	@Override
	public BonLivraisonReportValue getBonLivraisonReportValue(Long id, String avecPrix) throws IOException {
		return gestionnaireReportGcDomaine.getBonLivraisonParId(id, avecPrix);
		
	}

	@Override
	public EcheancierReportListValue getListEcheanceInverseReport(RechercheMulticritereDetailReglementValue request)
			throws IOException {
		return gestionnaireReportGcDomaine.getListEcheanceInverseReport(request);
	}
	

}
