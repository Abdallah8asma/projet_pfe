package com.gpro.consulting.tiers.logistique.domaine.gc.report;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.RechercheMulticritereFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RegelementReportElementRecapValue;
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
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;

/**
 * GestionnaireReportGcDomaine Interface
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */
public interface IGestionnaireReportGcDomaine {
	
	public BonLivraisonReportValue getBonLivraisonParId(Long id, String avecPrix) throws IOException;


	public BonLivraisonReportValue getBonLivraisonParId(Long id, String avecPrix, Long typerapport) throws IOException;

	public FactureReportValue getFactureReportValue(Long id,Long typerapport, boolean avecObservation) throws IOException;

	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException;

	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException;

	public EcheancierReportListValue getListEcheanceReport(RechercheMulticritereDetailReglementValue request)
			throws IOException;

	public FicheClientReportValue getFicheClientReport(RechercheMulticritereFicheClientValue request)
			throws IOException;

	public SoldeClientReportValue getSoldeClientReport(RechercheMulticritereSoldeClientValue request)
			throws IOException;

	// Added on 18/11/2016, by Zeineb Medimagh

	public BonCommandeReportValue getBonCommandeParIdReport(Long id,String typerapport, Long numrapport,String avecEntete, String avecPrix) throws IOException;

	// Added on 30/01/2017, by Hajer AMRI
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException;

	/**
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException;
	
	// Added on 24/03/2017, by Hajer AMRI

	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix) throws IOException;

	public FicheGroupeClientReportValue getFicheGroupeClientReport(RechercheMulticritereFicheGroupeClientValue request)	throws IOException;

	public MouvementProduitReportValue getMouvementProduitElementReport(Long produitId, Calendar dateMin,
			Calendar dateMax);

	public FactureReportElementRecapValue getFactureReportElementRecapValue(RechercheMulticritereFactureValue request);
	
	public BLReportElementRecapValue getBLReportElementRecapValue(RechercheMulticritereBonLivraisonValue request);
	
	
	public RegelementReportElementRecapValue getReglementReportValue(RechercheMulticritereReglementValue request);

	public List<ProduitValue> getTop10ArticleReportValue(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> getTop10ClientReportValue(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> getChiffreAffaireByClient(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> getChiffreAffaireBySousFamille(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> getChiffreAffaireByFamille(RechercheMulticritereDetLivraisonValue request);

	public ResultBestElementValue getReglementReportValueOptimise(RechercheMulticritereReglementValue rmReg);

	public List<ResultBestElementValue> getChiffreAffairebyGroupe(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> getTop10Groupe(RechercheMulticritereDetLivraisonValue request);


	public EcheancierReportListValue getListEcheanceInverseReport(RechercheMulticritereDetailReglementValue request)			throws IOException;



}
