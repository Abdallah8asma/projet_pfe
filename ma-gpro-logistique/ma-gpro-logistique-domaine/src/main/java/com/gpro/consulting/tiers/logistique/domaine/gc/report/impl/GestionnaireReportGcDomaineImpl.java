package com.gpro.consulting.tiers.logistique.domaine.gc.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.OptionArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IImpressionProduitDomaine;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.commun.domaine.elementBase.ISousFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IRegionPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommercialeReport;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.TaxeCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.ResultatRechecheFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.RechercheMulticritereFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.ResultatRechecheFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RegelementReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value.BonCommandeReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value.BonCommandeReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportTraitementFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value.EcheancierReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.fichegroupeclient.value.FicheGroupeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefElementDetailsValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefElementEltReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.mouvementproduit.MouvementProduitElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.mouvementproduit.MouvementProduitReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.soldeClient.value.SoldeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportTraitementFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonsortiefini.IBonSortieFiniDomain;
import com.gpro.consulting.tiers.logistique.domaine.atelier.cache.IGestionnaireLogistiqueCacheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IModePaiementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.ficheclient.IFicheClientDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.IGestionnaireReportGcDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.uilities.BonLivraisonReportProductComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.uilities.BonLivraisonReportProductOrdreComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.uilities.FrenchNumberToWords;
import com.gpro.consulting.tiers.logistique.domaine.gc.soldeClient.ISoldeClientDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.FactureReportProductComparatorByOrdre;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.ITraitementFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IEnginDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IPersonnelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IRemorqueDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMagasinDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IProduitReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IBonCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IDetLivraisonVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.IEcheancierPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.inverse.IEcheancierInversePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.IReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.IReglementInversePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.typeReglement.ITypeReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementation of {@link IGestionnaireReportGcDomaine}
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */

@Component
public class GestionnaireReportGcDomaineImpl implements IGestionnaireReportGcDomaine {

	private static final String REPORT_NAME_BONLIVRAISON = "bon de livraison";
	private static final String REPORT_NAME_FACTURE = "facture";
	private static final String REPORT_NAME_FACTURE_LIST = "factures";
	private static final String REPORT_NAME_BONLIVRAISON_LIST = "bons de livraison";
	private static final String REPORT_NAME_ECHEANCIER_LIST = "Echeancier Client";
	private static final String REPORT_NAME_REGLEMENT = "Règlement";

	private static final Double ZERO = 0D;

	// default static taxeId values:
	// taxeId = 1 -> FODEC
	// taxeId = 2 -> TVA
	// taxeId = 3 -> TIMBRE
	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 2L;
	private static final Long TAXE_ID_TIMBRE = 3L;
	private static final Long TAXE_ID_TVA7 = 4L;
	private static final Long TAXE_ID_TVA13 = 5L;

	private static final String TAXE_TVA = "tva 19";
	private static final String TAXE_TVA7 = "tva 7";
	private static final String TAXE_TVA13 = "tva 13";



	private static final String TAXE_FODEC = "fodec";
	private static final String TAXE_TIMBRE = "timbre";

	private static final String DINARS = " dinars ";
	private static final String MILLIMES = " millimes";
	private static final String ET = " et ";
	
	
	private static final String EURO = " euros ";
	private static final String CENTIMES = " centimes";
	
	private static final String DOLLAR = " dollars ";


	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersitance;

	@Autowired
	private IDetLivraisonVentePersistance detLivraisonVentePersistance;

	@Autowired
	private ITaxePersistance taxePersistance;

	@Autowired
	private IGestionnaireLogistiqueCacheDomaine gestionnaireLogistiqueCacheDomaine;

	@Autowired
	private IFacturePersistance facturePersistance;

	@Autowired
	private IEcheancierPersistance echeancierPersistance;
	
	@Autowired
	private IEcheancierInversePersistance echeancierInversePersistance;

	@Autowired
	private ITypeReglementPersistance typeReglementPersistance;

	@Autowired
	private IReglementPersistance reglementPersistance;
	
	@Autowired
	private IReglementInversePersistance reglementInversePersistance;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	private IRegionPersistance regionPersistance;

	@Autowired
	private IFicheClientDomaine ficheClientDomaine;

	@Autowired
	private ISoldeClientDomaine soldeClientDomaine;

	@Autowired
	private ITraitementFaconDomaine traitementFaconDomaine;

	@Autowired
	private IFicheFaconDomaine ficheFaconDomaine;

	@Autowired
	private IMiseDomaine miseDomaine;

	@Autowired
	private IModePaiementDomaine modePaiementDomaine;

	@Autowired
	private IBonCommandePersistance bonCommandePersistance;

	@Autowired
	IReceptionPersistance receptionPersistance;

	@Autowired
	IProduitReceptionAchatPersistance produitReceptionAchatPersistance;

	@Autowired
	private ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	@Autowired
	private IProduitDomaine produitDomaine;

	@Autowired
	private IEnginDomaine enginDomaine;

	@Autowired
	private IRemorqueDomaine remorqueDomaine;

	@Autowired
	private IPersonnelDomaine personnelDomaine;

	@Autowired
	private IMagasinDomaine magasinDomaine;

	@Autowired
	private IBaseInfoPersistance baseInfoPersistance;

	@Autowired
	private IProduitSerialisablePersistance produitSerialisablePersistance;

	@Autowired
	private IGroupeClientPersistance groupeClientPersistance;
	
	@Autowired
	private IBonSortieFiniDomain bonSortieFiniDomaine;
	
	
	
	@Autowired
	private ISousFamilleArticleDomaine sousFamilleArticleDomaine;

	@Autowired
	private IImpressionProduitDomaine impressionProduitDomaine;
	
	
	// Reception Achat

	/**
	 * @param bonReceptionReport
	 * @param receptionVente
	 */

	@Override
	public BonLivraisonReportValue getBonLivraisonParId(Long id, String avecPrix, Long typerapport) throws IOException {

		BonLivraisonReportValue bonLivraisonReport = new BonLivraisonReportValue();
		LivraisonVenteValue livraisonVente = bonLivraisonPersitance.getBonLivraisonById(id);
		//Collections.sort(livraisonVente.getListDetLivraisonVente(), new DetLivraisonVenteComparator());
		
		
		
		if(typerapport == 33 && livraisonVente.getInfoSortie() != null && livraisonVente.getInfoSortie().length() > 0)
		{
			
			//update detail Livraison par Produit to detailLivra
			
			
			String[] arrayStringBS = livraisonVente.getInfoSortie().split("-");
			
			List<String> refBonSortieList =new ArrayList<String>(Arrays.asList(arrayStringBS)) ;
			
		
			
			com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue prodElementList = bonSortieFiniDomaine.getProduitElementListByOF(refBonSortieList, livraisonVente.getId());
			
			if(prodElementList.getListDetLivraisonVente() != null && prodElementList.getListDetLivraisonVente().size() >0)
				livraisonVente.setListDetLivraisonVente(prodElementList.getListDetLivraisonVente());
				
			
		}
		
		
		
		
		
		

		// enrechissement des param du report

		bonLivraisonReport.setFileName(REPORT_NAME_BONLIVRAISON);

		if (typerapport == 2) {
			bonLivraisonReport
					.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonLivraison/bon_livraison_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (livraisonVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraison/bon_livraison_sub_report.jasper");
			} else if (livraisonVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraison/bon_livraison_facon_sub_report.jasper");
			}

			bonLivraisonReport.setParams(params);

		}

		// rapport avec enTete
		if (typerapport == 3 || typerapport == 33) {
			bonLivraisonReport.setReportStream(
					new FileInputStream("C://ERP/Lib/STIT_BonLivraison/avecEnTete/bon_livraison_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (livraisonVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraison/avecEnTete/bon_livraison_sub_report.jasper");
			} else if (livraisonVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraison/avecEnTete/bon_livraison_facon_sub_report.jasper");
			}

			bonLivraisonReport.setParams(params);

		}
		// 1 Standard
		// 10 avec conversion
		// 11 ttc
		// 12 ttc conversion

		if (typerapport == 1 || typerapport == 10) {

			bonLivraisonReport.setReportStream(
					new FileInputStream("C://ERP/Lib/STIT_BonLivraisonBas/bon_livraison_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (livraisonVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraisonBas/bon_livraison_sub_report.jasper");
			} else if (livraisonVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraisonBas/bon_livraison_facon_sub_report.jasper");
			}

			bonLivraisonReport.setParams(params);
		}

		if (typerapport == 11 || typerapport == 12) {

			bonLivraisonReport.setReportStream(
					new FileInputStream("C://ERP/Lib/STIT_BonLivraisonBasTTC/bon_livraison_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (livraisonVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraisonBasTTC/bon_livraison_sub_report.jasper");
			} else if (livraisonVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraisonBasTTC/bon_livraison_facon_sub_report.jasper");
			}

			bonLivraisonReport.setParams(params);
		}
		
		
		//rapport ticket de caisse
		
		if (typerapport ==4) {
			bonLivraisonReport.setReportStream(
					new FileInputStream("C://ERP/Lib/STIT_BonLivraison/ticket/bon_livraison_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (livraisonVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraison/ticket/bon_livraison_sub_report.jasper");
			} else if (livraisonVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_BonLivraison/avecEnTete/bon_livraison_facon_sub_report.jasper");
			}

			bonLivraisonReport.setParams(params);

		}

		if (avecPrix.equals(IConstanteLogistique.YES)) {

			if (typerapport == 10 || typerapport == 12) // Rapport with
														// conversion
				bonLivraisonReport.setConversion(true);
			if (typerapport == 11)
				bonLivraisonReport.setTtc(true);

			// enrichissement du report
			enrichmentBonLivraisonReport(bonLivraisonReport, livraisonVente);
			// enrichissement du report avec info reglement
			enrichirBonLivraisonReportWithReglementInfo(bonLivraisonReport,livraisonVente.getReglementId());
			// enrichissement du report avec des informations de baseInfo
			enrichirWithBaseInfoInformation(bonLivraisonReport);

			BigDecimal bigDecimal = new BigDecimal(livraisonVente.getMontantTTC());
			BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
			BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

			int dinars = bigDecimal.intValue();
			int millimes = fraction.intValue();

			String montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
					+ FrenchNumberToWords.convert(millimes) + MILLIMES;

			bonLivraisonReport.setMontantTTCToWords(montantTTCToWords);

		} else {
			// enrichissement du report
			enrichmentBonLivraisonReportWithOutPrix(bonLivraisonReport, livraisonVente);
			enrichirBonLivraisonReportWithReglementInfo(bonLivraisonReport,livraisonVente.getReglementId());
			enrichirWithBaseInfoInformation(bonLivraisonReport);

		}

		ArrayList<BonLivraisonReportValue> dataList = new ArrayList<BonLivraisonReportValue>();
		dataList.add(bonLivraisonReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		bonLivraisonReport.setJRBeanCollectionDataSourceProduct(jRBeanCollectionDataSource);

		return bonLivraisonReport;
	}

	/***************
	 * Enrichissement rapport bon de livraison without prix
	 **************/

	private void enrichmentBonLivraisonReportWithOutPrix(BonLivraisonReportValue bonLivraisonReportValue,
			LivraisonVenteValue livraisonVente) {

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		Long clientId = null;

		if (livraisonVente.getGroupeClientId() != null) {

			GroupeClientValue groupe = groupeClientPersistance
					.rechercheGroupeClientParId(new GroupeClientValue(livraisonVente.getGroupeClientId()));
			bonLivraisonReportValue.setGroupeClientDesignation(groupe.getDesignation());
		}

		if (livraisonVente.getPartieIntId() != null) {
			clientId = livraisonVente.getPartieIntId();
			bonLivraisonReportValue.setClientId(clientId);
		}

		
		/*
		if (clientIdMap.containsKey(clientId)) {
			bonLivraisonReportValue.setClient(clientIdMap.get(clientId).getRaisonSociale());
			bonLivraisonReportValue.setMatriculeFiscal(clientIdMap.get(clientId).getMatriculeFiscal());
			bonLivraisonReportValue.setAdresse(clientIdMap.get(clientId).getAdresse());
			bonLivraisonReportValue.setTelephone(clientIdMap.get(clientId).getTelephone());
			bonLivraisonReportValue.setFax(clientIdMap.get(clientId).getFax());
		}*/
		
		
		
		PartieInteresseValue pi = clientIdMap.get(livraisonVente.getPartieIntId()) ;
		
		
		if( pi.getPassager() != null && pi.getPassager() == true)
			bonLivraisonReportValue.setClient(livraisonVente.getTransporteur());
		else
			bonLivraisonReportValue.setClient(pi.getRaisonSociale());
		

	
		
		
		if(pi.getRegionId() != null) {
			
			
			RegionValue regionClient = regionPersistance.getById(pi.getRegionId()) ;
			
			if(regionClient != null)
				bonLivraisonReportValue.setVilleClient(regionClient.getDesignation());
			
		}
		
		bonLivraisonReportValue.setCodePostalClient(pi.getCodeDouane());


		bonLivraisonReportValue.setExistFodec(false);
		bonLivraisonReportValue.setExistTVA(false);
		bonLivraisonReportValue.setExistTimbre(false);

		bonLivraisonReportValue.setClientId(livraisonVente.getPartieIntId());
		bonLivraisonReportValue.setDateBl(livraisonVente.getDate());
		bonLivraisonReportValue.setReference(livraisonVente.getReference());
		bonLivraisonReportValue.setObservations(livraisonVente.getObservations());
		bonLivraisonReportValue.setIdentifiant(livraisonVente.getIdentifiantLivraison());

		List<BonLivraisonReportProductValue> productList = new ArrayList<BonLivraisonReportProductValue>();
		List<BonLivraisonReportTraitementFaconValue> traitementFaconList = new ArrayList<BonLivraisonReportTraitementFaconValue>();

		Map<Long, ProduitValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();
		

		for (DetLivraisonVenteValue detLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

			if (livraisonVente.getNatureLivraison().equals("FINI")) {
				BonLivraisonReportProductValue bonLivraisonReportProduct = new BonLivraisonReportProductValue();

				bonLivraisonReportProduct.setDetLivraisonVenteId(detLivraisonVente.getId());
				bonLivraisonReportProduct.setChoix(detLivraisonVente.getChoix());
				bonLivraisonReportProduct.setNombreColis(detLivraisonVente.getNombreColis());
				bonLivraisonReportProduct.setProduitId(detLivraisonVente.getProduitId());
				bonLivraisonReportProduct.setQuantite(detLivraisonVente.getQuantite());
				bonLivraisonReportProduct.setRemise(detLivraisonVente.getRemise());
				bonLivraisonReportProduct.setUnite(detLivraisonVente.getUnite());
				bonLivraisonReportProduct.setMise(detLivraisonVente.getNumeroOF());

				bonLivraisonReportProduct.setMontantTaxeTVA(detLivraisonVente.getMontantTaxeTVA());

				bonLivraisonReportProduct.setPrixUHT(ZERO);
				bonLivraisonReportProduct.setMontantHT(ZERO);

				Long produitId = detLivraisonVente.getProduitId();

				if (produitIdMap.containsKey(produitId)) {
					
					
					
                     String designationEnrichie = enrichirDesignationProductReportFromProduit(produitIdMap.get(produitId));
					bonLivraisonReportProduct.setProduitDesignation(designationEnrichie);
					
					bonLivraisonReportProduct.setProduitCode(produitIdMap.get(produitId).getReference());

				}

				productList.add(bonLivraisonReportProduct);
				Collections.sort(productList, new BonLivraisonReportProductComparator());
				bonLivraisonReportValue.setProductList(productList);

			} else if (livraisonVente.getNatureLivraison().equals("FACON")) {

				BonLivraisonReportTraitementFaconValue bonLivraisonReportTraitementFacon = new BonLivraisonReportTraitementFaconValue();

				bonLivraisonReportTraitementFacon.setDetLivraisonVenteId(detLivraisonVente.getId());
				bonLivraisonReportTraitementFacon.setNombreColis(detLivraisonVente.getNombreColis());
				bonLivraisonReportTraitementFacon.setQuantite(detLivraisonVente.getQuantite());
				bonLivraisonReportTraitementFacon.setRemise(detLivraisonVente.getRemise());
				bonLivraisonReportTraitementFacon.setUnite(detLivraisonVente.getUnite());
				bonLivraisonReportTraitementFacon.setPrixUHT(ZERO);
				bonLivraisonReportTraitementFacon.setMontantHT(ZERO);
				bonLivraisonReportTraitementFacon.setTraitementId(detLivraisonVente.getTraitementFaconId());

				// Remplir la liste des référencesMiseConcatinés par idFiche
				FicheFaconValue ficheFacon = ficheFaconDomaine.getById(detLivraisonVente.getFicheId());
				detLivraisonVente.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));
				bonLivraisonReportTraitementFacon.setRefMiseList(detLivraisonVente.getRefMiseList());

				TraitementFaconValue traitementFaconValue = traitementFaconDomaine
						.getById(detLivraisonVente.getTraitementFaconId());
				bonLivraisonReportTraitementFacon.setTraitementDesignation(traitementFaconValue.getDesignation());

				traitementFaconList.add(bonLivraisonReportTraitementFacon);
				Collections.sort(traitementFaconList);
				bonLivraisonReportValue.setTraitementFaconList(traitementFaconList);
			}
		}

		bonLivraisonReportValue.setMarcheId(livraisonVente.getMarcheId());
		bonLivraisonReportValue.setModepaiementId(livraisonVente.getModepaiementId());

		Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();

		for (TaxeLivraisonValue value : livraisonVente.getListTaxeLivraison()) {
			taxeLivraisonIdTaxeMap.put(value.getTaxeId(), value);
		}

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			bonLivraisonReportValue.setExistFodec(true);

			// FODEC params
			bonLivraisonReportValue.setTaxeFodec(TAXE_FODEC);
			bonLivraisonReportValue.setTauxFodec(taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage());
		}

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA)) {
			bonLivraisonReportValue.setExistTVA(true);

			// TVA params
			bonLivraisonReportValue.setTaxeTVA(TAXE_TVA);
			bonLivraisonReportValue.setTauxTVA(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getPourcentage());

		}
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			bonLivraisonReportValue.setExistTimbre(true);

			// TIMBRE params
			bonLivraisonReportValue.setTaxeTimbre(TAXE_TIMBRE);
		}

		bonLivraisonReportValue.setMontantTaxeFodec(ZERO);
		bonLivraisonReportValue.setMontantTaxeTVA(ZERO);
		bonLivraisonReportValue.setMontantTaxeTimbre(ZERO);
		bonLivraisonReportValue.setAssietteFodec(ZERO);
		bonLivraisonReportValue.setAssietteTVA(ZERO);
		bonLivraisonReportValue.setMontantRemiseTotal(ZERO);
		bonLivraisonReportValue.setMontantTaxes(ZERO);
		bonLivraisonReportValue.setMontantHTTotal(ZERO);
		bonLivraisonReportValue.setMontantTTC(ZERO);
		bonLivraisonReportValue.setMontantTTCToWords("ZERO DINARS ET ZERO MILLIMES");

	}

	private void enrichirWithBaseInfoInformation(BonLivraisonReportValue bonLivraisonReportValue) {

		BaseInfoValue baseInfo = baseInfoPersistance.getClientActif();

		if (baseInfo.getPrefixBonLivraison() != null)
			bonLivraisonReportValue
					.setReference(baseInfo.getPrefixBonLivraison() + bonLivraisonReportValue.getReference());

		bonLivraisonReportValue.setAdresseCompagnie(baseInfo.getAdresse());
		bonLivraisonReportValue.setCodeTVACompagnie(baseInfo.getCodeTVA());
		bonLivraisonReportValue.setVilleCompagnie(baseInfo.getVille());
		bonLivraisonReportValue.setTelephoneFixCompagnie(baseInfo.getTelephoneFix());
		bonLivraisonReportValue.setTelephoneMoblieCompagnie(baseInfo.getTelephoneMoblie());
		bonLivraisonReportValue.setFaxCompagnie(baseInfo.getFax());
		bonLivraisonReportValue.setEmailCompagnie(baseInfo.getEmail());
		bonLivraisonReportValue.setMatriculeFiscalCompagnie(baseInfo.getMatriculeFiscal());

		bonLivraisonReportValue.getParams().put("p_PathLogo", baseInfo.getLogo());
	}

	/*************** Enrichissement rapport bon de livraison **************/

	private void enrichmentBonLivraisonReport(BonLivraisonReportValue bonLivraisonReportValue,
			LivraisonVenteValue livraisonVente) {

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		Long clientId = null;
	//	Long passager = 517L;

		if (livraisonVente.getGroupeClientId() != null) {

			GroupeClientValue groupe = groupeClientPersistance
					.rechercheGroupeClientParId(new GroupeClientValue(livraisonVente.getGroupeClientId()));
			bonLivraisonReportValue.setGroupeClientDesignation(groupe.getDesignation());
		}
		bonLivraisonReportValue.setClientId(clientId);
		////////////
	if (livraisonVente.getPartieIntId() != null /*&& !livraisonVente.getPartieIntId().equals(passager)*/) {
			clientId = livraisonVente.getPartieIntId();
			bonLivraisonReportValue.setClientId(clientId);
		}

		if (clientIdMap.containsKey(clientId) /*&& !livraisonVente.getPartieIntId().equals(passager)*/) {
			bonLivraisonReportValue.setClient(clientIdMap.get(clientId).getRaisonSociale());
			bonLivraisonReportValue.setMatriculeFiscal(clientIdMap.get(clientId).getMatriculeFiscal());
			bonLivraisonReportValue.setAdresse(clientIdMap.get(clientId).getAdresse());
			bonLivraisonReportValue.setTelephone(clientIdMap.get(clientId).getTelephone());
			bonLivraisonReportValue.setFax(clientIdMap.get(clientId).getFax());
			bonLivraisonReportValue.setGroupeClientDesignation(clientIdMap.get(clientId).getReference());
		}

		
		/*if (livraisonVente.getPartieIntId().equals(passager)) {

			if (livraisonVente.getTransporteur() != null)
				bonLivraisonReportValue.setClient(livraisonVente.getTransporteur());
			else
				bonLivraisonReportValue.setClient("PASSAGER");

		}*/
		
		/////////////////
		
		PartieInteresseValue pi = clientIdMap.get(livraisonVente.getPartieIntId()) ;
		
		
		if( pi.getPassager() != null && pi.getPassager() == true)
			bonLivraisonReportValue.setClient(livraisonVente.getTransporteur());
		else
			bonLivraisonReportValue.setClient(pi.getRaisonSociale());
		
		
		
		if(pi.getRegionId() != null) {
			
			
			RegionValue regionClient = regionPersistance.getById(pi.getRegionId()) ;
			
			if(regionClient != null)
				bonLivraisonReportValue.setVilleClient(regionClient.getDesignation());
			
		}
		
		bonLivraisonReportValue.setCodePostalClient(pi.getCodeDouane());

		
		

		bonLivraisonReportValue.setExistFodec(false);
		bonLivraisonReportValue.setExistTVA(false);
		bonLivraisonReportValue.setExistTimbre(false);

		bonLivraisonReportValue.setClientId(livraisonVente.getPartieIntId());
		bonLivraisonReportValue.setDateBl(livraisonVente.getDate());
		bonLivraisonReportValue.setReference(livraisonVente.getReference());
		bonLivraisonReportValue.setIdentifiant(livraisonVente.getIdentifiantLivraison());
		bonLivraisonReportValue.setObservations(livraisonVente.getObservations());

		// Ajouté le 25/04/2018

		if (livraisonVente.getIdCamion() != null) {
			EnginValue vEngin = enginDomaine.getById(livraisonVente.getIdCamion());

			if (vEngin != null && vEngin.getMarque() != null && vEngin.getImmatriculation() != null) {
				String camion = /* vEngin.getSerie()+"TU" */"" + vEngin.getImmatriculation();
				bonLivraisonReportValue.setCamion(camion);

			}

		} else {

			if (livraisonVente.getCamion() != null)
				bonLivraisonReportValue.setCamion(livraisonVente.getCamion());

		}

		if (livraisonVente.getIdChauffeur() != null) {
			PersonnelValue vPersonnel = personnelDomaine.getById(livraisonVente.getIdChauffeur());

			if (vPersonnel != null && vPersonnel.getNom() != null && vPersonnel.getPrenom() != null) {
				String chauffeur = vPersonnel.getNom() + "  " + vPersonnel.getPrenom();
				bonLivraisonReportValue.setChauffeur(chauffeur);

			}

		}

		else {
			if (livraisonVente.getChauffeur() != null)
				bonLivraisonReportValue.setChauffeur(livraisonVente.getChauffeur());
		}

		if (livraisonVente.getIdRemorque() != null) {
			RemorqueValue vRemorque = remorqueDomaine.getById(livraisonVente.getIdRemorque());

			if (vRemorque != null && vRemorque.getImmatriculation() != null) {
				String remorque = vRemorque.getImmatriculation();
				bonLivraisonReportValue.setRemorque(remorque);

			}

		} else {

			if (livraisonVente.getRemorque() != null)
				bonLivraisonReportValue.setRemorque(livraisonVente.getRemorque());

		}

		if (livraisonVente.getIdDepot() != null) {

			MagasinValue pMagasinRecherche = new MagasinValue();
			pMagasinRecherche.setId(livraisonVente.getIdDepot());
			MagasinValue magasin = magasinDomaine.rechercheMagasinParId(pMagasinRecherche);

			if (magasin != null && magasin.getDesignation() != null) {
				bonLivraisonReportValue.setMagasin(magasin.getDesignation());
			}

		}

		if (livraisonVente.getRefCommande() != null && !livraisonVente.getRefCommande().equals("")) {
			bonLivraisonReportValue.setCommande(livraisonVente.getRefCommande());
		}

		// Montants params
		bonLivraisonReportValue.setMontantTTC(livraisonVente.getMontantTTC());
		bonLivraisonReportValue.setMontantRemiseTotal(livraisonVente.getMontantRemise());
		bonLivraisonReportValue.setMontantHTTotal(livraisonVente.getMontantHTaxe());
		bonLivraisonReportValue.setMontantTaxes(livraisonVente.getMontantTaxe());

		List<BonLivraisonReportProductValue> productList = new ArrayList<BonLivraisonReportProductValue>();
		List<BonLivraisonReportTraitementFaconValue> traitementFaconList = new ArrayList<BonLivraisonReportTraitementFaconValue>();

		Map<Long, ProduitValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		for (DetLivraisonVenteValue detLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

			if (livraisonVente.getNatureLivraison().equals("FINI")) {
				BonLivraisonReportProductValue bonLivraisonReportProduct = new BonLivraisonReportProductValue();

				bonLivraisonReportProduct.setDetLivraisonVenteId(detLivraisonVente.getId());
				bonLivraisonReportProduct.setChoix(detLivraisonVente.getChoix());
				bonLivraisonReportProduct.setNombreColis(detLivraisonVente.getNombreColis());
				bonLivraisonReportProduct.setProduitId(detLivraisonVente.getProduitId());
				bonLivraisonReportProduct.setQuantite(detLivraisonVente.getQuantite());
				bonLivraisonReportProduct.setRemise(detLivraisonVente.getRemise());
				bonLivraisonReportProduct.setUnite(detLivraisonVente.getUnite());
				bonLivraisonReportProduct.setMise(detLivraisonVente.getNumeroOF());

				Long produitId = detLivraisonVente.getProduitId();
				
				bonLivraisonReportProduct.setFicheId(detLivraisonVente.getFicheId());

				if (produitIdMap.containsKey(produitId)) {
					// TODO
					detLivraisonVente.setPrixUnitaireHT(detLivraisonVente.getPrixUnitaireHT());

					/***** Rapport converti **********/
					if (bonLivraisonReportValue.getConversion() != null
							&& bonLivraisonReportValue.getConversion() == true) {
						if (detLivraisonVente.getQuantiteConversion() != null
								&& detLivraisonVente.getQuantiteConversion() != ZERO)
							bonLivraisonReportProduct.setQuantite(detLivraisonVente.getQuantiteConversion());
						if (detLivraisonVente.getUniteSupplementaire() != null && !detLivraisonVente.equals(""))
							bonLivraisonReportProduct.setUnite(detLivraisonVente.getUniteSupplementaire());
						if (detLivraisonVente.getPrixUnitaireHTConversion() != null)
							bonLivraisonReportProduct.setPrixUHT(detLivraisonVente.getPrixUnitaireHTConversion());

					}
					Double quantite = (detLivraisonVente.getQuantite() != null) ? detLivraisonVente.getQuantite()
							: ZERO;
					Double prixUHT = (detLivraisonVente.getPrixUnitaireHT() != null)
							? detLivraisonVente.getPrixUnitaireHT()
							: ZERO;

					Double montantHT = quantite * prixUHT;

					if (bonLivraisonReportValue.getTtc() != null && bonLivraisonReportValue.getTtc() == true) {

						//updated par samer afin d'utilser le taux tva enregistrer dans detail.
					
					// TaxeValue vTaxeValue = taxePersistance.getById(produitIdMap.get(produitId).getIdTaxe());
						TaxeValue vTaxeValue = taxePersistance.getById(detLivraisonVente.getTaxeId());
						if (vTaxeValue != null && vTaxeValue.getValeur() != null) {
							prixUHT = prixUHT * (1 + vTaxeValue.getValeur() / 100);
							montantHT = quantite * prixUHT;

						}
						
					 
				
						
						

					}

					bonLivraisonReportProduct.setPrixUHT(prixUHT);

					bonLivraisonReportProduct.setMontantHT(montantHT);
					
					
					
					String designationEnrichie = enrichirDesignationProductReportFromProduit(produitIdMap.get(produitId));
					
					
					
					
					
					if (detLivraisonVente.isSerialisable() && detLivraisonVente.getNumeroSeries()!= null) {
						/*String numeroSeriesProduitSerialisable = getNumeroSerieProduitSeriaisableByReferenceBonAndProduitId(
								livraisonVente.getReference(), produitId, IConstanteCommerciale.BON_LIVRAISON);*/
						
						String numeroSeriesProduitSerialisable = detLivraisonVente.getNumeroSeries();
						bonLivraisonReportProduct.setProduitDesignation(designationEnrichie
								+ "(" + numeroSeriesProduitSerialisable + ")");
					} else {
						bonLivraisonReportProduct.setProduitDesignation(designationEnrichie);
					}

					// enrichir designation produit avec description detaille
					if (livraisonVente.getDescription() != null && livraisonVente.getDescription() == true
							&& produitIdMap.get(produitId).getDescription() != null) {
						// System.out.println("INSIDE IF
						// "+produitIdMap.get(produitId).getDescription());

						bonLivraisonReportProduct
								.setProduitDesignation(bonLivraisonReportProduct.getProduitDesignation() + "\n"
										+ produitIdMap.get(produitId).getDescription());

					}

					if (detLivraisonVente.getDescription() != null) {
						bonLivraisonReportProduct
								.setProduitDesignation(bonLivraisonReportProduct.getProduitDesignation() + " "
										+ detLivraisonVente.getDescription());

					}

					bonLivraisonReportProduct.setProduitCode(produitIdMap.get(produitId).getReference());

					// ajouté 16/03/2018
					bonLivraisonReportProduct.setTauxTvaArt(produitIdMap.get(produitId).getIdTaxe().intValue());

					// System.out.println("##### TAUX TVA : " +
					// bonLivraisonReportProduct.getTauxTvaArt());

					// //// Ajout TVA /////////////
					
					
					if (detLivraisonVente.getRemise()==null)
						detLivraisonVente.setRemise(0D);
					
					
					
					if(detLivraisonVente.getPrixTotalHT() == null)
						detLivraisonVente.setPrixTotalHT(ZERO);
					
					Double totalApresRemise=detLivraisonVente.getPrixTotalHT()*(1-detLivraisonVente.getRemise()/100);
					
					
			
					if (!produitTaxeMap.containsKey(detLivraisonVente.getTaxeId())) {
						produitTaxeMap.put(detLivraisonVente.getTaxeId(), totalApresRemise);

					} else {
						Double assietteValue = produitTaxeMap.get(detLivraisonVente.getTaxeId())
								+ totalApresRemise;
						produitTaxeMap.put(detLivraisonVente.getTaxeId(), assietteValue);

					}

				}

				productList.add(bonLivraisonReportProduct);
				Collections.sort(productList, new BonLivraisonReportProductOrdreComparator());
			//	Collections.sort(productList, new BonLivraisonReportProductComparator());
				bonLivraisonReportValue.setProductList(productList);
				// System.out.println(" taille liste*/*/*/:" +
				// productList.size());

			}
			// Inutile
			else if (livraisonVente.getNatureLivraison().equals("FACON")) {

				BonLivraisonReportTraitementFaconValue bonLivraisonReportTraitementFacon = new BonLivraisonReportTraitementFaconValue();

				bonLivraisonReportTraitementFacon.setDetLivraisonVenteId(detLivraisonVente.getId());
				bonLivraisonReportTraitementFacon.setNombreColis(detLivraisonVente.getNombreColis());
				bonLivraisonReportTraitementFacon.setQuantite(detLivraisonVente.getQuantite());
				bonLivraisonReportTraitementFacon.setRemise(detLivraisonVente.getRemise());
				bonLivraisonReportTraitementFacon.setUnite(detLivraisonVente.getUnite());
				bonLivraisonReportTraitementFacon.setPrixUHT(ZERO);
				bonLivraisonReportTraitementFacon.setMontantHT(ZERO);
				bonLivraisonReportTraitementFacon.setTraitementId(detLivraisonVente.getTraitementFaconId());

				// Remplir la liste des référencesMiseConcatinés par idFiche
				FicheFaconValue ficheFacon = ficheFaconDomaine.getById(detLivraisonVente.getFicheId());
				detLivraisonVente.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));
				bonLivraisonReportTraitementFacon.setRefMiseList(detLivraisonVente.getRefMiseList());

				TraitementFaconValue traitementFaconValue = traitementFaconDomaine
						.getById(detLivraisonVente.getTraitementFaconId());
				bonLivraisonReportTraitementFacon.setTraitementDesignation(traitementFaconValue.getDesignation());

				detLivraisonVente.setPrixUnitaireHT(traitementFaconValue.getPu());

				Double quantite = (detLivraisonVente.getQuantite() != null) ? detLivraisonVente.getQuantite() : ZERO;
				Double prixUHT = (traitementFaconValue.getPu() != null) ? traitementFaconValue.getPu() : ZERO;

				Double montantHT = quantite * prixUHT;
				bonLivraisonReportTraitementFacon.setPrixUHT(prixUHT);
				bonLivraisonReportTraitementFacon.setMontantHT(montantHT);

				traitementFaconList.add(bonLivraisonReportTraitementFacon);
				Collections.sort(traitementFaconList);
				bonLivraisonReportValue.setTraitementFaconList(traitementFaconList);
			}
			// Inutile
		}

		bonLivraisonReportValue.setMarcheId(livraisonVente.getMarcheId());
		bonLivraisonReportValue.setModepaiementId(livraisonVente.getModepaiementId());

		Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();

		for (TaxeLivraisonValue value : livraisonVente.getListTaxeLivraison()) {

			Long key = value.getId();
			if (taxeLivraisonIdTaxeMap.get(key) == null) {
				taxeLivraisonIdTaxeMap.put(value.getTaxeId(), value);
			}

			taxeLivraisonIdTaxeMap.put(value.getTaxeId(), value);
		}

		// System.out.println("###### Liste from map #####");

		// System.out.println(" #### map size : "
		// + taxeLivraisonIdTaxeMap.size());

		// System.out.println("###### Liste from map #####");

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			bonLivraisonReportValue.setExistFodec(true);

			// FODEC params
			bonLivraisonReportValue.setTaxeFodec(TAXE_FODEC);
			bonLivraisonReportValue.setTauxFodec(taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage());
			bonLivraisonReportValue.setMontantTaxeFodec(taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getMontant());
			bonLivraisonReportValue.setAssietteFodec(bonLivraisonReportValue.getMontantHTTotal());
		}

		// TVA Reparti

		// //// PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA)) {
			bonLivraisonReportValue.setExistTVA(true);

			// TVA params
			bonLivraisonReportValue.setTaxeTVA(TAXE_TVA);
			bonLivraisonReportValue.setTauxTVA(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getPourcentage());
			bonLivraisonReportValue.setMontantTaxeTVA(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getMontant());

			bonLivraisonReportValue.setAssietteTVA(produitTaxeMap.get(TAXE_ID_TVA));

		}
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA7)) {
			bonLivraisonReportValue.setExistTVA7(true);

			// TVA params
			bonLivraisonReportValue.setTaxeTVA7(TAXE_TVA7);
			bonLivraisonReportValue.setTauxTVA7(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA7).getPourcentage());
			bonLivraisonReportValue.setMontantTaxeTVA7(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA7).getMontant());

			bonLivraisonReportValue.setAssietteTVA7(produitTaxeMap.get(TAXE_ID_TVA7));
		}
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA13)) {
			bonLivraisonReportValue.setExistTVA13(true);

			// TVA params
			bonLivraisonReportValue.setTaxeTVA13(TAXE_TVA13);
			bonLivraisonReportValue.setTauxTVA13(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA13).getPourcentage());
			bonLivraisonReportValue.setMontantTaxeTVA13(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA13).getMontant());

			bonLivraisonReportValue.setAssietteTVA13(produitTaxeMap.get(TAXE_ID_TVA13));
		}

		// //// FIN PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		// TVA Reparti

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			bonLivraisonReportValue.setExistTimbre(true);

			// TIMBRE params
			bonLivraisonReportValue.setTaxeTimbre(TAXE_TIMBRE);
			bonLivraisonReportValue.setMontantTaxeTimbre(taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant());
		}

	}

	private void enrichirBonLivraisonReportWithReglementInfo(BonLivraisonReportValue  livraisonVenteReport, Long reglementId) {
		
		if(reglementId != null) {
			
			
			ReglementValue reglement = reglementPersistance.getById(reglementId);
			
			if(reglement != null && reglement.getListDetailsReglement() != null && reglement.getListDetailsReglement().size()>0) {
				
				
				Double sommeMontantDetailReglement  =  getSommeMontantPayerDetailReglement(reglement.getListDetailsReglement()) ;
				
				livraisonVenteReport.setMontantRegler(sommeMontantDetailReglement);
				livraisonVenteReport.setMontantNonRegler(livraisonVenteReport.getMontantTTC() - livraisonVenteReport.getMontantRegler());

				
			}else
			{
				livraisonVenteReport.setMontantRegler(ZERO);
				livraisonVenteReport.setMontantNonRegler(livraisonVenteReport.getMontantTTC());
			}
			
			
			
		}else
		{
			livraisonVenteReport.setMontantRegler(ZERO);
			livraisonVenteReport.setMontantNonRegler(livraisonVenteReport.getMontantTTC());

			
		}
		
	}

	private Double getSommeMontantPayerDetailReglement(Set<DetailsReglementValue> listDetailsReglement) {
		
		Double somme = new Double(0);
		for(DetailsReglementValue detail :listDetailsReglement) {
			if(detail.getMontant() != null)
				somme += detail.getMontant();
			
		}
		return somme;
	}

	private String getNumeroSerieProduitSeriaisableByReferenceBonAndProduitId(String reference, Long produitId,
			String typeBon) {
		String series = "";
		RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere = new RechercheMulticritereProduitSerialisableValue();
		pRechercheProduitSerialisableMulitCritere.setProduitId(produitId);

		if (typeBon.equals(IConstanteCommerciale.BON_LIVRAISON)) {
			pRechercheProduitSerialisableMulitCritere.setNumBonLivraison(reference);
		} else if (typeBon.equals(IConstanteCommerciale.BON_FACTURE)) {
			pRechercheProduitSerialisableMulitCritere.setNumFacture(reference);
		}

		ResultatRechecheProduitSerialisableValue result = produitSerialisablePersistance
				.rechercherProduitSerialisableMultiCritere(pRechercheProduitSerialisableMulitCritere);

		for (ProduitSerialisableValue prodSerialisable : result.getProduitSerialisableValues()) {
			series += prodSerialisable.getNumSerie();
			series += "-";
		}

		if (series.length() > 0 && series.charAt(series.length() - 1) == '-') {
			series = series.substring(0, series.length() - 1);
		}

		return series;

	}

	/***************************************
	 * Rapport liste bons de livraison
	 *********************************************/

	@Override
	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException {

		// System.out.println("report etat stock: "+request.getStock());

		BonLivraisonReportListValue bonlivraisonReportList = new BonLivraisonReportListValue();

		// enrechissement des param du report
		bonlivraisonReportList.setFileName(REPORT_NAME_BONLIVRAISON_LIST);
		bonlivraisonReportList
				.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonLivraisonList/bon_livraisons_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonLivraisonList/bon_livraisons_sub_report.jasper");

		bonlivraisonReportList.setParams(params);

		ResultatRechecheBonLivraisonValue result = bonLivraisonPersitance.rechercherMultiCritere(request);

		List<LivraisonVenteValue> livraisonVenteList = new ArrayList<LivraisonVenteValue>(result.getList());

		// enrichissement du report
		enrichmentBonlivraisonReportList(bonlivraisonReportList, livraisonVenteList, request);

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		if (request.getPartieIntId() != null) {
			Long clientId = request.getPartieIntId();
			if (clientIdMap.containsKey(clientId)) {
				bonlivraisonReportList.setPartieInt(clientIdMap.get(clientId).getAbreviation());
			}
		}

		ArrayList<BonLivraisonReportListValue> dataList = new ArrayList<BonLivraisonReportListValue>();
		dataList.add(bonlivraisonReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		bonlivraisonReportList.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonlivraisonReportList;
	}

	/***************
	 * Enrichissement rapport liste bons de livraison
	 **************/

	private void enrichmentBonlivraisonReportList(BonLivraisonReportListValue bonlivraisonReportList,
			List<LivraisonVenteValue> livraisonVenteList, RechercheMulticritereBonLivraisonValue request) {

		List<BonLivraisonReportElementValue> productList = new ArrayList<BonLivraisonReportElementValue>();

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		for (LivraisonVenteValue livraisonVente : livraisonVenteList) {
			BonLivraisonReportElementValue bonLivraisonReportElement = new BonLivraisonReportElementValue();

			Long clientId = null;
			if (livraisonVente.getPartieIntId() != null) {
				clientId = livraisonVente.getPartieIntId();
				if (clientIdMap.containsKey(clientId)) {
					bonLivraisonReportElement.setPartieIntAbreviation(clientIdMap.get(clientId).getAbreviation());
				}
			}

			bonLivraisonReportElement.setReference(livraisonVente.getReference());
			bonLivraisonReportElement.setRefexterne(livraisonVente.getRefexterne());
			bonLivraisonReportElement.setDate(livraisonVente.getDate());
			bonLivraisonReportElement.setMontantHTaxe(livraisonVente.getMontantHTaxe());
			bonLivraisonReportElement.setMontantTTC(livraisonVente.getMontantTTC());
			bonLivraisonReportElement.setMontantTaxe(livraisonVente.getMontantTaxe());
			bonLivraisonReportElement.setMontantRemise(livraisonVente.getMontantRemise());
			bonLivraisonReportElement.setObservations(livraisonVente.getObservations());
			bonLivraisonReportElement.setInfoSortie(livraisonVente.getInfoSortie());
			bonLivraisonReportElement.setMetrageTotal(livraisonVente.getMetrageTotal());
			bonLivraisonReportElement.setTransporteur(livraisonVente.getTransporteur());
			bonLivraisonReportElement.setTransporteur(livraisonVente.getTransporteur());
			// bonLivraisonReportElement.setNatureLivraison(livraisonVente.getNatureLivraison());

			productList.add(bonLivraisonReportElement);
		}

		// System.out.println("--- productList---" + productList.size());
		bonlivraisonReportList.setProductList(productList);

		bonlivraisonReportList.setReferenceBl(request.getReferenceBl());
		bonlivraisonReportList.setReferenceBs(request.getReferenceBs());
		bonlivraisonReportList.setPartieIntId(request.getPartieIntId());
		bonlivraisonReportList.setDateLivraisonMin(request.getDateLivraisonMin());
		bonlivraisonReportList.setDateLivraisonMax(request.getDateLivraisonMax());
		bonlivraisonReportList.setMetrageMin(request.getMetrageMin());
		bonlivraisonReportList.setMetrageMax(request.getMetrageMax());
		bonlivraisonReportList.setPrixMin(request.getPrixMin());
		bonlivraisonReportList.setPrixMax(request.getPrixMax());
		bonlivraisonReportList.setEtat(request.getEtat());
		bonlivraisonReportList.setStock(request.getStock());
		bonlivraisonReportList.setNumOF(request.getNumOF());

		if (request.getAvecFacture() != null) {

			if (request.getAvecFacture().equals("ouiFacture")) {
				bonlivraisonReportList.setAvecFacture("Oui");
			} else if (request.getAvecFacture().equals("nonFacture")) {
				bonlivraisonReportList.setAvecFacture("Non");
			} else {
				bonlivraisonReportList.setAvecFacture("Tout Type");
			}

		}

	}

	/***************************************
	 * Rapport facture
	 *********************************************/

	@Override
	public FactureReportValue getFactureReportValue(Long id, Long typerapport,boolean avecObservation) throws IOException {

		// System.out.println("====> id: " + id + "---typerapport " + typerapport);
		FactureReportValue factureReport = new FactureReportValue();
		FactureVenteValue factureVente = facturePersistance.getFactureById(id);

		// enrechissement des param du report
		if (typerapport == 2) {
			// rapport sur imp matricielle
			factureReport.setFileName(REPORT_NAME_FACTURE);
			factureReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_FactureVente/sansEnTete/facture_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png\"");

			if (factureVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_FactureVente/sansEnTete/facture_sub_report.jasper");
			} else if (factureVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_FactureVente/sansEnTete/facture_facon_sub_report.jasper");
			}

			factureReport.setParams(params);
		} else if (typerapport == 1) {

			// Rapport Basique
			factureReport.setFileName(REPORT_NAME_FACTURE);
			factureReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_FactureVenteBas/facture_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");

			if (factureVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_FactureVenteBas/facture_sub_report.jasper");
			} else if (factureVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_FactureVenteBas/facture_facon_sub_report.jasper");
			}

			factureReport.setParams(params);

			// rapport avec enTete
		} else if (typerapport == 3 || typerapport == 4) {
			
			if(factureVente.getDevise() != null){
				
				
				if(factureVente.getDevise().equals(DeviseValue.DINAR)) {
					
					// rapport sur imp matricielle
					factureReport.setFileName(REPORT_NAME_FACTURE);
					factureReport.setReportStream(
							new FileInputStream("C://ERP/Lib/STIT_FactureVente/avecEnTete/facture_report.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("p_PathLogo","C:/ERP/logos_clients/logo_client.png");

					if (factureVente.getNatureLivraison().equals("FINI")) {
						params.put("SUBREPORT_INVENTAIRE_PATH",
								"C://ERP/Lib/STIT_FactureVente/avecEnTete/facture_sub_report.jasper");
					} else if (factureVente.getNatureLivraison().equals("FACON")) {
						params.put("SUBREPORT_INVENTAIRE_PATH",
								"C://ERP/Lib/STIT_FactureVente/avecEnTete/facture_facon_sub_report.jasper");
					}

					factureReport.setParams(params);
				}else
					if(factureVente.getDevise().equals(DeviseValue.EURO)) {
						
						// rapport sur imp matricielle
						factureReport.setFileName(REPORT_NAME_FACTURE);
						factureReport.setReportStream(
								new FileInputStream("C://ERP/Lib/STIT_FactureVente/avecEnTeteDeviseEuro/facture_report_Euro_Dollar.jrxml"));

						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("p_PathLogo","C:/ERP/logos_clients/logo_client.png");

						if (factureVente.getNatureLivraison().equals("FINI")) {
							params.put("SUBREPORT_INVENTAIRE_PATH",
									"C://ERP/Lib/STIT_FactureVente/avecEnTeteDeviseEuro/facture_sub_report_Euro_Dollar.jasper");
						} else if (factureVente.getNatureLivraison().equals("FACON")) {
							params.put("SUBREPORT_INVENTAIRE_PATH",
									"C://ERP/Lib/STIT_FactureVente/avecEnTeteDeviseEuro/facture_facon_sub_report_Euro_Dollar.jasper");
						}

						factureReport.setParams(params);
					}
					else
						if(factureVente.getDevise().equals(DeviseValue.DOLLAR)) {
							
							// rapport sur imp matricielle
							factureReport.setFileName(REPORT_NAME_FACTURE);
							factureReport.setReportStream(
									new FileInputStream("C://ERP/Lib/STIT_FactureVente/avecEnTeteDeviseDollar/facture_report_Euro_Dollar.jrxml"));

							HashMap<String, Object> params = new HashMap<String, Object>();
							params.put("p_PathLogo","C:/ERP/logos_clients/logo_client.png");

							if (factureVente.getNatureLivraison().equals("FINI")) {
								params.put("SUBREPORT_INVENTAIRE_PATH",
										"C://ERP/Lib/STIT_FactureVente/avecEnTeteDeviseDollar/facture_sub_report_Euro_Dollar.jasper");
							} else if (factureVente.getNatureLivraison().equals("FACON")) {
								params.put("SUBREPORT_INVENTAIRE_PATH",
										"C://ERP/Lib/STIT_FactureVente/avecEnTeteDeviseDollar/facture_facon_sub_report_Euro_Dollar.jasper");
							}

							factureReport.setParams(params);
						}
				
				
				
			}else
			{
				// rapport sur imp matricielle
				factureReport.setFileName(REPORT_NAME_FACTURE);
				factureReport.setReportStream(
						new FileInputStream("C://ERP/Lib/STIT_FactureVente/avecEnTete/facture_report.jrxml"));

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("p_PathLogo","C:/ERP/logos_clients/logo_client.png");

				if (factureVente.getNatureLivraison().equals("FINI")) {
					params.put("SUBREPORT_INVENTAIRE_PATH",
							"C://ERP/Lib/STIT_FactureVente/avecEnTete/facture_sub_report.jasper");
				} else if (factureVente.getNatureLivraison().equals("FACON")) {
					params.put("SUBREPORT_INVENTAIRE_PATH",
							"C://ERP/Lib/STIT_FactureVente/avecEnTete/facture_facon_sub_report.jasper");
				}

				factureReport.setParams(params);
			}



		}
		
   
		
		
		// enrichissement du report
		enrichmentFactureReport(factureReport, factureVente,avecObservation);
		enrichmentFactureReportWithBaseInformation(factureReport, factureVente);

	
		
		String montantTTCToWords = "";
		
		
		montantTTCToWords = getMontantTTCToWordsByMantantAndDevise(factureVente.getMontantTTC(),factureVente.getDevise() ) ;
		
	
	
		factureReport.setMontantTTCToWords(montantTTCToWords);
		
		
			

		ArrayList<FactureReportValue> dataList = new ArrayList<FactureReportValue>();
		dataList.add(factureReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		factureReport.setJRBeanCollectionDataSourceProduct(jRBeanCollectionDataSource);

		return factureReport;
	}

	private String getMontantTTCToWordsByMantantAndDevise(Double montantTTC, Long devise) {

		String montantTTCToWords = "";
		if (devise != null) {

			if (devise.equals(DeviseValue.DINAR)) {

				BigDecimal bigDecimal = new BigDecimal(montantTTC);
				BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
				BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

				int dinars = bigDecimal.intValue();
				int millimes = fraction.intValue();

				montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
						+ FrenchNumberToWords.convert(millimes) + MILLIMES;
			} else if (devise.equals(DeviseValue.EURO)) {

				BigDecimal bigDecimal = new BigDecimal(montantTTC);
				BigDecimal bigDecimalScalled = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(100));

				int euro = bigDecimal.intValue();
				int centimes = fraction.intValue();
				montantTTCToWords = FrenchNumberToWords.convert(euro) + EURO + ET
						+ FrenchNumberToWords.convert(centimes) + CENTIMES;

			} else if (devise.equals(DeviseValue.DOLLAR)) {

				BigDecimal bigDecimal = new BigDecimal(montantTTC);
				BigDecimal bigDecimalScalled = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(100));

				int dollar = bigDecimal.intValue();
				int centimes = fraction.intValue();
				
				montantTTCToWords = FrenchNumberToWords.convert(dollar) + dollar + ET
						+ FrenchNumberToWords.convert(centimes) + CENTIMES;

			}

		} else {
			BigDecimal bigDecimal = new BigDecimal(montantTTC);
			BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
			BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

			int dinars = bigDecimal.intValue();
			int millimes = fraction.intValue();

			montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
					+ FrenchNumberToWords.convert(millimes) + MILLIMES;

		}

		return montantTTCToWords;
	}

	private void enrichmentFactureReportWithBaseInformation(FactureReportValue factureReport,
			FactureVenteValue factureVente) {

		BaseInfoValue baseInfo = baseInfoPersistance.getClientActif();
		Map<Long, GroupeClientValue> groupePiIdMap = gestionnaireLogistiqueCacheDomaine.mapGroupePIById();

		if (factureVente.getType() != null) {
			if (factureVente.getType().equals(IConstanteCommerciale.FACTURE_TYPE_AVOIR)) {

				if (baseInfo.getPrefixAvoir() != null)
					factureReport.setReference(baseInfo.getPrefixAvoir() + factureReport.getReference());
			} else

			{
				if (factureVente.getType().equals(IConstanteCommerciale.FACTURE_TYPE_NORMAL))
					if (baseInfo.getPrefixFacture() != null)
						factureReport.setReference(baseInfo.getPrefixFacture() + factureReport.getReference());
			}
		}

		factureReport.setAdresseCompagnie(baseInfo.getAdresse());
		factureReport.setCodeTVACompagnie(baseInfo.getCodeTVA());
		factureReport.setVilleCompagnie(baseInfo.getVille());
		factureReport.setTelephoneFixCompagnie(baseInfo.getTelephoneFix());
		factureReport.setTelephoneMoblieCompagnie(baseInfo.getTelephoneMoblie());
		factureReport.setFaxCompagnie(baseInfo.getFax());
		factureReport.setEmailCompagnie(baseInfo.getEmail());
		factureReport.setMatriculeFiscalCompagnie(baseInfo.getMatriculeFiscal());
		

		factureReport.getParams().put("p_PathLogo", baseInfo.getLogo());

	}

	/***************************************
	 * Rapport liste des factures
	 *********************************************/

	@Override
	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException {

		FactureReportListValue factureReportList = new FactureReportListValue();

		// enrechissement des param du report
		factureReportList.setFileName(REPORT_NAME_FACTURE_LIST);
		factureReportList
				.setReportStream(new FileInputStream("C://ERP/Lib/STIT_FactureVenteList/factures_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_FactureVenteList/factures_sub_report.jasper");

		factureReportList.setParams(params);

		ResultatRechecheFactureValue result = facturePersistance.rechercherMultiCritereAvecDetail(request);

		List<FactureVenteValue> factureVenteList = new ArrayList<FactureVenteValue>(result.getList());

		// enrichissement du report
		enrichmentFactureReportList(factureReportList, factureVenteList, request);

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();
		

		

		if (request.getPartieIntId() != null) {
			Long clientId = request.getPartieIntId();
			if (clientIdMap.containsKey(clientId)) {
				factureReportList.setPartieInt(clientIdMap.get(clientId).getAbreviation());
			}
		}

		ArrayList<FactureReportListValue> dataList = new ArrayList<FactureReportListValue>();
		dataList.add(factureReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		factureReportList.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return factureReportList;
	}

	/********** Enrichissement rapport liste factures ********************/

	private void enrichmentFactureReportList(FactureReportListValue factureReportList,
			List<FactureVenteValue> factureVenteList, RechercheMulticritereFactureValue request) {

		List<FactureReportElementValue> productList = new ArrayList<FactureReportElementValue>();

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();
		
		Map<Long, GroupeClientValue> groupePiIdMap = gestionnaireLogistiqueCacheDomaine.mapGroupePIById();

		for (FactureVenteValue factureVente : factureVenteList) {
			FactureReportElementValue factureReportElement = new FactureReportElementValue();

			Long clientId = null;
			if (factureVente.getPartieIntId() != null) {
				clientId = factureVente.getPartieIntId();
				if (clientIdMap.containsKey(clientId)) {
					factureReportElement.setPartieIntAbreviation(clientIdMap.get(clientId).getAbreviation());
				}
			}
			
			if(factureVente.getGroupeClientId() != null) {
				factureReportElement.setGroupePiDesignation(groupePiIdMap.get(factureVente.getGroupeClientId()).getDesignation());
			}
			factureReportList.setType(factureVente.getType());
			factureReportElement.setReference(factureVente.getReference());
			factureReportElement.setDate(factureVente.getDate());
			factureReportElement.setMontantHTaxe(factureVente.getMontantHTaxe());
			factureReportElement.setMontantTTC(factureVente.getMontantTTC());
			factureReportElement.setMontantTaxe(factureVente.getMontantTaxe());
			factureReportElement.setMontantRemise(factureVente.getMontantRemise());
			factureReportElement.setObservations(factureVente.getObservations());
			factureReportElement.setInfoLivraison(factureVente.getInfoLivraison());
			factureReportElement.setMetrageTotal(factureVente.getMetrageTotal());
			factureReportElement.setEtat(factureVente.getEtat());

			factureReportElement.setPartieIntId(factureVente.getPartieIntId());
			factureReportElement.setModepaiementId(factureVente.getModepaiementId());

			factureReportElement.setModepaiement(factureVente.getModepaiement());
			factureReportElement.setMarche(factureVente.getMarche());
			factureReportElement.setNatureLivraison(factureVente.getNatureLivraison());
			
			factureReportElement.setDevise(factureVente.getDevise());
			
			factureReportElement.setMontantConverti(factureVente.getMontantConverti());

			productList.add(factureReportElement);
		}

		factureReportList.setProductList(productList);

		factureReportList.setReferenceBl(request.getReferenceBl());
		factureReportList.setReferenceFacture(request.getReferenceFacture());
		factureReportList.setPartieIntId(request.getPartieIntId());
		factureReportList.setDateFactureMin(request.getDateFactureMin());
		factureReportList.setDateFactureMax(request.getDateFactureMax());
		factureReportList.setMetrageMin(request.getMetrageMin());
		factureReportList.setMetrageMax(request.getMetrageMax());
		factureReportList.setPrixMin(request.getPrixMin());
		factureReportList.setPrixMax(request.getPrixMax());

	}

	/*************** Enrichissement rapport facture **************/

	private void enrichmentFactureReport(FactureReportValue reportValue, FactureVenteValue factureVente , boolean avecObservation) {

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		Long clientId = null;
		
		
		PartieInteresseValue pi = null;

		if (factureVente.getPartieIntId() != null) {
			clientId = factureVente.getPartieIntId();
			reportValue.setClientId(clientId);
			
			pi = clientIdMap.get(clientId) ;
		}

		if (pi != null ) {
			
			
			
			reportValue.setClient(pi.getRaisonSociale());
			reportValue.setMatriculeFiscal(pi.getMatriculeFiscal());
			reportValue.setAdresse(pi.getAdresse());
			reportValue.setTelephone(pi.getTelephone());
			reportValue.setFax(pi.getFax());
			
			
			reportValue.setGroupeClientDesignation(pi.getReference());
			
			
			if(pi.getRegionId() != null) {
				
				
				RegionValue regionClient = regionPersistance.getById(pi.getRegionId()) ;
				
				if(regionClient != null)
					reportValue.setVilleClient(regionClient.getDesignation());
				
			}
			
			reportValue.setCodePostalClient(pi.getCodeDouane());
			
		}
		
		
		
		
		


		
		
		
		reportValue.setType(factureVente.getType());
		reportValue.setExistFodec(false);
		reportValue.setExistTVA(false);
		reportValue.setExistTimbre(false);

		reportValue.setClientId(factureVente.getPartieIntId());
		reportValue.setDateBl(factureVente.getDate());
		reportValue.setReference(factureVente.getReference());
		reportValue.setRaison(factureVente.getRaison());
		// System.out.println("ref: " + factureVente.getReference());

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		// String dat = ""+dateFormat.format(d);

		// System.out.println("##### info : "+factureVente.getInfoLivraison());
		if (factureVente.getInfoLivraison() != null && !factureVente.getInfoLivraison().equals(""))
				 {
			
			 if(!factureVente.getInfoLivraison().contains("-")) {
				 
				 //SI IL existe une seul BL affectee a cette facture
				 
					LivraisonVenteValue livraisonVente = bonLivraisonPersitance.getByReference(factureVente.getInfoLivraison());

					String infoLivraison = factureVente.getInfoLivraison();

					if (livraisonVente != null) {
						if (livraisonVente.getDate() != null) {
							String date = "" + dateFormat.format(livraisonVente.getDate().getTime());
							infoLivraison += "  DU  " + date;

						}

					}

					reportValue.setInfoLivraison(infoLivraison);
			 } else {
				 
				 //Si plusieurs BLs sont affecté a cette facture
				 
				 reportValue.setInfoLivraison(factureVente.getInfoLivraison());
				 
			 }
			
		

		}
		
		
		if(avecObservation)
			reportValue.setObservations(factureVente.getObservations());
		//if(factureVente.getTypePartieInteressee() != null && factureVente.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {
			
		//}

		

		// Montants params
		reportValue.setMontantTTC(factureVente.getMontantTTC());
		reportValue.setMontantRemiseTotal(factureVente.getMontantRemise());
		reportValue.setMontantHTTotal(factureVente.getMontantHTaxe());
		reportValue.setMontantTaxes(factureVente.getMontantTaxe());
		
		reportValue.setRefCommande(factureVente.getRefCommande());
		reportValue.setIdentifiant(factureVente.getIdentifiant());

		List<FactureReportProductValue> productList = new ArrayList<FactureReportProductValue>();
		List<FactureReportTraitementFaconValue> traitementFaconList = new ArrayList<FactureReportTraitementFaconValue>();

		Map<Long, ProduitValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();
		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		//
		for (DetFactureVenteValue detFactureVente : factureVente.getListDetFactureVente()) {

			if (factureVente.getNatureLivraison().equals("FINI")) {

				// System.out.println("inn fact fini..");
				FactureReportProductValue factureReportProduct = new FactureReportProductValue();

				
				factureReportProduct.setFicheId(detFactureVente.getFicheId());
				factureReportProduct.setDetLivraisonVenteId(detFactureVente.getId());
				factureReportProduct.setChoix(detFactureVente.getChoix());
				factureReportProduct.setNombreColis(detFactureVente.getNombreColis());
				factureReportProduct.setProduitId(detFactureVente.getProduitId());
				factureReportProduct.setQuantite(detFactureVente.getQuantite());
				factureReportProduct.setRemise(detFactureVente.getRemise());
				factureReportProduct.setUnite(detFactureVente.getUnite());
				factureReportProduct.setMise(detFactureVente.getNumeroOF());

				// factureReportProduct.setMontantTaxeTVA(detFactureVente.getMontanTaxeTVA());
				// System.out.println("MontanTaxeTVA():
				// "+detFactureVente.getMontanTaxeTVA()+"
				// detFactureVente.getQuantite():
				// "+detFactureVente.getQuantite());

				Long produitId = detFactureVente.getProduitId();

				if (produitIdMap.containsKey(produitId)) {

					detFactureVente.setPrixUnitaireHT(detFactureVente.getPrixUnitaireHT());

					Double quantite = (detFactureVente.getQuantite() != null) ? detFactureVente.getQuantite() : ZERO;
					Double prixUHT = (detFactureVente.getPrixUnitaireHT() != null) ? detFactureVente.getPrixUnitaireHT()
							: ZERO;

					Double montantHT = quantite * prixUHT;

					factureReportProduct.setPrixUHT(prixUHT);
					factureReportProduct.setMontantHT(montantHT);

					
					if (detFactureVente.isSerialisable() && detFactureVente.getNumeroSeries()!= null) {
						//String numeroSeriesProduitSerialisable = getNumeroSerieProduitSeriaisableByReferenceBonAndProduitId(factureVente.getReference(), produitId, IConstanteCommerciale.BON_FACTURE);
						
						String numeroSeriesProduitSerialisable  = detFactureVente.getNumeroSeries(); 
						factureReportProduct.setProduitDesignation(produitIdMap.get(produitId).getDesignation() + "("
								+ numeroSeriesProduitSerialisable + ")");
					} else {
						factureReportProduct.setProduitDesignation(enrichirDesignationProductReportFromProduit(produitIdMap.get(produitId)));
					}

					//factureReportProduct.setProduitDesignation(produitIdMap.get(produitId).getDesignation());
					
					// enrichir designation produit avec description detaille
					if (factureVente.getDescription() != null && factureVente.getDescription() == true
							&& produitIdMap.get(produitId).getDescription() != null) {
						// System.out.println("INSIDE IF
						// "+produitIdMap.get(produitId).getDescription());

						factureReportProduct.setProduitDesignation(factureReportProduct.getProduitDesignation() + "\n"
								+ produitIdMap.get(produitId).getDescription());

					}

					if (detFactureVente.getDescription() != null) {
						factureReportProduct.setProduitDesignation(
								factureReportProduct.getProduitDesignation() + " " + detFactureVente.getDescription());

					}

					factureReportProduct.setProduitCode(produitIdMap.get(produitId).getReference());

					// ajouté 16/03/2018
					
					//modifier par samer le 24.09.20
					//factureReportProduct.setTauxTvaArt(produitIdMap.get(produitId).getIdTaxe().intValue());
					
					
					if(detFactureVente.getTaxeId() != null) {
						factureReportProduct.setTauxTvaArt(detFactureVente.getTaxeId().intValue());
					}
					
				

					// //// Ajout TVA /////////////
					
					if (detFactureVente.getRemise()==null)
					detFactureVente.setRemise(0D);
					Double totalApresRemise=detFactureVente.getPrixTotalHT()*(1-detFactureVente.getRemise()/100);
					
					
					
					
					
					if (!produitTaxeMap.containsKey(detFactureVente.getTaxeId())) {
						produitTaxeMap.put(detFactureVente.getTaxeId(), totalApresRemise);
						// ajouté 16/03/2018
						// produitTaxeMapArt.put(produitIdMap.get(produitId).getIdTaxe(),
						// produitIdMap.get(produitId).getIdTaxe());

					} else {
						//TODO ERROR TVA
						Double assietteValue = produitTaxeMap.get(detFactureVente.getTaxeId())+totalApresRemise;
						produitTaxeMap.put(detFactureVente.getTaxeId(), assietteValue);

					}

				}

				productList.add(factureReportProduct);
				//modifier par samer afin d'utiliser l'ordre de saisie de bl
				//Collections.sort(productList, new FactureReportProductComparator());
				
				Collections.sort(productList, new FactureReportProductComparatorByOrdre());

				reportValue.setProductList(productList);
			} else if (factureVente.getNatureLivraison().equals("FACON")) {

				FactureReportTraitementFaconValue factureReportTraitementFacon = new FactureReportTraitementFaconValue();

				factureReportTraitementFacon.setDetLivraisonVenteId(detFactureVente.getId());
				factureReportTraitementFacon.setNombreColis(detFactureVente.getNombreColis());
				factureReportTraitementFacon.setQuantite(detFactureVente.getQuantite());
				factureReportTraitementFacon.setRemise(detFactureVente.getRemise());
				factureReportTraitementFacon.setUnite(detFactureVente.getUnite());
				factureReportTraitementFacon.setPrixUHT(ZERO);
				factureReportTraitementFacon.setMontantHT(ZERO);
				factureReportTraitementFacon.setTraitementId(detFactureVente.getTraitementFaconId());

				// Remplir la liste des référencesMiseConcatinés par idFiche
				FicheFaconValue ficheFacon = ficheFaconDomaine.getById(detFactureVente.getFicheId());
				detFactureVente.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));
				factureReportTraitementFacon.setRefMiseList(detFactureVente.getRefMiseList());

				TraitementFaconValue traitementFaconValue = traitementFaconDomaine
						.getById(detFactureVente.getTraitementFaconId());
				factureReportTraitementFacon.setTraitementDesignation(traitementFaconValue.getDesignation());

				detFactureVente.setPrixUnitaireHT(traitementFaconValue.getPu());

				Double quantite = (detFactureVente.getQuantite() != null) ? detFactureVente.getQuantite() : ZERO;
				Double prixUHT = (traitementFaconValue.getPu() != null) ? traitementFaconValue.getPu() : ZERO;

				Double montantHT = quantite * prixUHT;
				factureReportTraitementFacon.setPrixUHT(prixUHT);
				factureReportTraitementFacon.setMontantHT(montantHT);

				traitementFaconList.add(factureReportTraitementFacon);
				Collections.sort(traitementFaconList);
				reportValue.setTraitementFaconList(traitementFaconList);
			}

		}

		ModePaiementValue modePaiement = null;

		if (factureVente.getModepaiementId() != null)
			modePaiement = modePaiementDomaine.getById(factureVente.getModepaiementId());

		if (modePaiement != null)
			reportValue.setModepaiement(modePaiement.getDesignation());

		Map<Long, TaxeFactureValue> taxeFactureIdTaxeMap = new HashMap<Long, TaxeFactureValue>();

		for (TaxeFactureValue value : factureVente.getListTaxeFacture()) {
			taxeFactureIdTaxeMap.put(value.getTaxeId(), value);
			Long key = value.getId();
			if (taxeFactureIdTaxeMap.get(key) == null) {
				taxeFactureIdTaxeMap.put(value.getTaxeId(), value);
			}

			taxeFactureIdTaxeMap.put(value.getTaxeId(), value);
		}
		// System.out.println("###### Liste from map facture #####");

		// System.out.println(" #### map size : " + taxeFactureIdTaxeMap.size());

		// System.out.println("###### Liste from map facture #####");

		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			reportValue.setExistFodec(true);

			// FODEC params
			reportValue.setTaxeFodec(TAXE_FODEC);
			reportValue.setTauxFodec(taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage());
			reportValue.setMontantTaxeFodec(taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getMontant());
			reportValue.setAssietteFodec(reportValue.getMontantHTTotal());
		}

		// TVA Reparti

		// //// PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TVA)) {
			reportValue.setExistTVA(true);

			// TVA params
			reportValue.setTaxeTVA(TAXE_TVA);
			reportValue.setTauxTVA(taxeFactureIdTaxeMap.get(TAXE_ID_TVA).getPourcentage());
			reportValue.setMontantTaxeTVA(taxeFactureIdTaxeMap.get(TAXE_ID_TVA).getMontant());
			// System.out.println("##### produitTaxeMap.get(TAXE_ID_TVA) :" +
			// taxeFactureIdTaxeMap.get(TAXE_ID_TVA).getMontant());

			
			if(reportValue.getMontantTaxeFodec() != null && produitTaxeMap.get(TAXE_ID_TVA) != null) {
				reportValue.setAssietteTVA(produitTaxeMap.get(TAXE_ID_TVA)+reportValue.getMontantTaxeFodec());
			}else
			{
				reportValue.setAssietteTVA(produitTaxeMap.get(TAXE_ID_TVA));
			}
			

		}
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TVA7)) {
			reportValue.setExistTVA7(true);
			// System.out.println("##### produitTaxeMap.get(TAXE_ID_TVA7) :" +
			// taxeFactureIdTaxeMap.get(TAXE_ID_TVA7).getMontant());

			// TVA params
			reportValue.setTaxeTVA7(TAXE_TVA7);
			reportValue.setTauxTVA7(taxeFactureIdTaxeMap.get(TAXE_ID_TVA7).getPourcentage());
			reportValue.setMontantTaxeTVA7(taxeFactureIdTaxeMap.get(TAXE_ID_TVA7).getMontant());

			reportValue.setAssietteTVA7(produitTaxeMap.get(TAXE_ID_TVA7));
			// System.out.println("*************Montant TVA7 :
			// "+taxeFactureIdTaxeMap.get(TAXE_ID_TVA7).getMontant());
		}

		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TVA13)) {
			reportValue.setExistTVA13(true);
			// System.out.println("##### produitTaxeMap.get(TAXE_ID_TVA13).getMontant :"+
			// taxeFactureIdTaxeMap.get(TAXE_ID_TVA13).getMontant());

			// TVA params
			reportValue.setTaxeTVA13(TAXE_TVA13);
			reportValue.setTauxTVA13(taxeFactureIdTaxeMap.get(TAXE_ID_TVA13).getPourcentage());
			reportValue.setMontantTaxeTVA13(taxeFactureIdTaxeMap.get(TAXE_ID_TVA13).getMontant());

			reportValue.setAssietteTVA13(produitTaxeMap.get(TAXE_ID_TVA13));

		}
		// //// FIN PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			reportValue.setExistTimbre(true);

			// TIMBRE params
			reportValue.setTaxeTimbre(TAXE_TIMBRE);
			reportValue.setMontantTaxeTimbre(taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant());
		}

	}

	private String enrichirDesignationProductReportFromProduit(ProduitValue produitValue) {
		
		  //(Designation : Matière,info Matiere,grammage,Impression,dimension,options)
		
		
		StringBuilder designationProductReport = new StringBuilder();
		
		
		designationProductReport.append(produitValue.getDesignation());
		designationProductReport.append(" : ");
		
		for(ArticleProduitValue matiere : produitValue.getArticleProduits()) {
			
			if(matiere.getSousFamilleArticleId() != null) {
				
				SousFamilleArticleValue sf = sousFamilleArticleDomaine.rechercheSousFamilleArticleById(matiere.getSousFamilleArticleId());
			
				if(estNonVide(sf.getDesignation()))
				designationProductReport.append(sf.getDesignation() + ", ");
						
			}
			
			if(estNonVide(matiere.getInfoMatiere()))
			designationProductReport.append(matiere.getInfoMatiere() + ", ");
			
			if(estNonVide(matiere.getGrammage()))
			designationProductReport.append(matiere.getGrammage() + ", ");
			
			if(matiere.getImpressionProduitId() != null)
				designationProductReport.append(impressionProduitDomaine.rechercheImpressionProduitById(matiere.getImpressionProduitId()).getDesignation() + ", ");
			
				
			/*if(estNonVide(matiere.getDimension()))
				designationProductReport.append(matiere.getDimension() + ", ");*/
			
			
			for(OptionArticleProduitValue opt : matiere.getOptionArticleProduits()) {
				
				if(estNonVide(opt.getDesignation()))
					designationProductReport.append(opt.getDesignation() + ", ");
			}
				
		}
		

		
		// TODO Auto-generated method stub
		return designationProductReport.toString();
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
	}

	/*****************************
	 * Rapport list echeancier
	 ************************************/

	@Override
	public EcheancierReportListValue getListEcheanceReport(RechercheMulticritereDetailReglementValue request)
			throws IOException {

		EcheancierReportListValue echeanceReportList = new EcheancierReportListValue();

		// enrechissement des param du report
		echeanceReportList.setFileName(REPORT_NAME_ECHEANCIER_LIST);
		echeanceReportList.setReportStream(
				new FileInputStream("C://ERP/Lib/EcheancierClient/ListeEcheancier/echeancier_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_ECHEANCIER_PATH",
				"C://ERP/Lib/EcheancierClient/ListeEcheancier/echeancier_sub_List_report.jasper");

		echeanceReportList.setParams(params);
		if (request != null) {
			ResultatRechecheDetailReglementValue result = echeancierPersistance.rechercherMultiCritere(request);
			if (result != null) {
				List<ResultatRechecheDetailReglementElementValue> echeancierList = new ArrayList<ResultatRechecheDetailReglementElementValue>(
						result.getList());
				if (echeancierList != null) {
					// enrichissement du report
					enrichissementEcheancierReportList(echeanceReportList, echeancierList, request);
				}
			}
		}
		ArrayList<EcheancierReportListValue> dataList = new ArrayList<EcheancierReportListValue>();
		dataList.add(echeanceReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		echeanceReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return echeanceReportList;
	}

	private void enrichissementEcheancierReportList(EcheancierReportListValue report,
			List<ResultatRechecheDetailReglementElementValue> echeancierList,
			RechercheMulticritereDetailReglementValue request) {

		report.setDateReglementAu(request.getDateReglementAu());
		report.setDateReglementDu(request.getDateReglementDu());
		report.setDateEcheanceDu(request.getDateEcheanceDu());
		report.setDateEcheanceAu(request.getDateEcheanceAu());
		report.setNumPiece(request.getNumPiece());
		report.setPartieIntId(request.getPartieIntId());
		report.setReference(request.getReference());
		report.setRegle(request.getRegle());
		report.setTypeReglementId(request.getTypeReglementId());
		report.setAvecTerme(request.getAvecTerme());
		report.setNomRapport(request.getNomRapport());

		if (request.getTypeReglementId() != null) {
			TypeReglementValue typeReglement = typeReglementPersistance.getById(request.getTypeReglementId());
			if (typeReglement != null) {

				report.setTypeReglement(typeReglement.getDesignation());
			}
		}

		if (request.getPartieIntId() != null) {
			PartieInteresseValue pi = partieInteresseePersistance.getById(request.getPartieIntId());
			if (pi != null) {

				report.setPartieIntAbreviation(pi.getAbreviation());
			}
		}

		List<ResultatRechecheDetailReglementElementValue> echeanceElementList = new ArrayList<ResultatRechecheDetailReglementElementValue>();
		for (ResultatRechecheDetailReglementElementValue echeancier : echeancierList) {

			ResultatRechecheDetailReglementElementValue reportElement = new ResultatRechecheDetailReglementElementValue();

			reportElement.setDateEcheance(echeancier.getDateEcheance());
			reportElement.setDateEmission(echeancier.getDateEmission());
			reportElement.setBanque(echeancier.getBanque());
			reportElement.setMontant(echeancier.getMontant());
			reportElement.setNumPiece(echeancier.getNumPiece());
			reportElement.setRefFacture(echeancier.getRefFacture());
			reportElement.setRegle(echeancier.getRegle());
			reportElement.setReglementId(echeancier.getReglementId());
			reportElement.setTypeReglementId(echeancier.getTypeReglementId());
			
			reportElement.setReferenceDetReglement(echeancier.getReferenceDetReglement());
			reportElement.setObservation(echeancier.getObservation());
			
			
			reportElement.setBanqueSociete(echeancier.getBanqueSociete());
			reportElement.setDateDepotBanque(echeancier.getDateDepotBanque());
			reportElement.setChargeBanque(echeancier.getChargeBanque());
			reportElement.setTvaBanque(echeancier.getTvaBanque());
		

			if (echeancier.getTypeReglementId() != null) {
				TypeReglementValue typeReglement = typeReglementPersistance.getById(echeancier.getTypeReglementId());
				if (typeReglement != null) {

					reportElement.setTypeReglement(typeReglement.getDesignation());
				}
			}

			if (echeancier.getReglementId() != null) {
				ReglementValue reglement = reglementPersistance.getById(echeancier.getReglementId());
				if (reglement != null) {

					reportElement.setRefReglement(reglement.getReference());
					reportElement.setDateReglement(reglement.getDate());
					if (reglement.getPartieIntId() != null) {
						PartieInteresseValue partieInterssee = partieInteresseePersistance
								.getById(reglement.getPartieIntId());
						if (partieInterssee != null) {
							reportElement.setClientAbreviation(partieInterssee.getAbreviation());
							if (partieInterssee.getRegionId() != null) {
								RegionValue region = regionPersistance.getById(partieInterssee.getRegionId());
								reportElement.setClientRegion(region.getDesignation());
							}

						}
					}
				}
			}

			echeanceElementList.add(reportElement);
		}
		report.setEcheancierList(echeanceElementList);

	}

	/******************** Rapport fiche client *****************************/

	@Override
	public FicheClientReportValue getFicheClientReport(RechercheMulticritereFicheClientValue request)
			throws IOException {

		FicheClientReportValue report = new FicheClientReportValue();

		if (request.getTypeRapport().equals("ficheClient")) {
			/** ficheClient **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_FICHE_CLIENT);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_FICHE_CLIENT));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheClientValue result = ficheClientDomaine.rechercherMultiCritere(request);

			enrichmentFicheClientReportList(report, result, request);

			ArrayList<FicheClientReportValue> dataList = new ArrayList<FicheClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		} else if (request.getTypeRapport().equals("releveClient")) {
			/** releveClient **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_RELEVE_CLIENT);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_RELEVE_CLIENT));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheClientValue result = ficheClientDomaine.rechercherMultiCritere(request);

			enrichmentFicheClientReportList(report, result, request);

			ArrayList<FicheClientReportValue> dataList = new ArrayList<FicheClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		}
		
		if (request.getTypeRapport().equals("ficheFactureClient")) {
			/** ficheClient **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_FICHE_CLIENT);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_FICHE_CLIENT));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheClientValue result = ficheClientDomaine.rechercherMultiCritere(request);

			enrichmentFicheClientReportList(report, result, request);

			ArrayList<FicheClientReportValue> dataList = new ArrayList<FicheClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		} else if (request.getTypeRapport().equals("releveFactureClient")) {
			/** releveClient **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_RELEVE_CLIENT);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_RELEVE_CLIENT));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheClientValue result = ficheClientDomaine.rechercherMultiCritere(request);

			enrichmentFicheClientReportList(report, result, request);

			ArrayList<FicheClientReportValue> dataList = new ArrayList<FicheClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		}

		return report;

	}

	/********************
	 * Enrichissement rapport fiche client
	 ***********************/
	private void enrichmentFicheClientReportList(FicheClientReportValue report, ResultatRechecheFicheClientValue result,
			RechercheMulticritereFicheClientValue request) {
		if (request != null) {

			report.setDateMax(request.getDateMax());
			report.setDateMin(request.getDateMin());

			if (request.getClientId() != null) {

				PartieInteresseValue client = partieInteresseePersistance.getById(request.getClientId());

				if (client != null) {

					report.setClientAbreviation(client.getAbreviation());
					report.setClientReference(client.getReference());
					report.setClientAdresse(client.getAdresse());
					report.setClientTelephone(client.getTelephone());
				}
			}
		}

		if (result != null) {

			report.setDebitTotal(result.getDebitTotal());
			report.setCreditTotal(result.getCreditTotal());
			report.setSoldeClient(result.getSoldeClient());
			// System.out.println("-----------Fiche client :Solde Client-------" +
			// result.getSoldeClient());

			report.setSoldeInitial(result.getSoldeInitial());
			// System.out.println("-----------Fiche client :Solde Initial-------" +
			// result.getSoldeInitial());

			if (result.getListElements() != null) {
				
				
				//TODO calcul balance
				
				Double soldeInitiale =ZERO;
				
				if(result.getSoldeInitial() != null)
					soldeInitiale = result.getSoldeInitial();
				
				
				for(FicheClientElementValue element : result.getListElements()) {
					
					if ((element.getType().equals(FicheClientElementValue.TYPE_BL)
							|| element.getType().equals(FicheClientElementValue.TYPE_FACTURE))

							&& element.getDebit() != null

					) {

						soldeInitiale += element.getDebit();
						
						element.setBalance(soldeInitiale);

					}else
						if ((element.getType().equals(FicheClientElementValue.TYPE_AVOIR)
								|| element.getType().equals(FicheClientElementValue.TYPE_REGLEMENT))

								&& element.getCredit()!= null

						) {

							soldeInitiale -= element.getCredit();
							element.setBalance(soldeInitiale);
						}
					
					
						else
							if ((element.getType().equals(FicheClientElementValue.TYPE_REGLEMENT_INVERSE) && element.getDebit() != null	)
								
								) {

								soldeInitiale += element.getDebit();
								
								element.setBalance(soldeInitiale);

							}
					
									

						
					
					
					report.getListElements().add(element);
					
				}
				
				
	          	//report.getListElements().addAll(result.getListElements());
			}
		}

	}

	/********************
	 * Enrichissement rapport fiche client
	 ***********************/
	private void enrichmentFicheGroupeClientReportList(FicheGroupeClientReportValue report,
			ResultatRechecheFicheGroupeClientValue result, RechercheMulticritereFicheGroupeClientValue request) {
		if (request != null) {

			report.setDateMax(request.getDateMax());
			report.setDateMin(request.getDateMin());

			if (request.getGroupeClientId() != null) {

				GroupeClientValue groupeClient = groupeClientPersistance
						.rechercheGroupeClientParId(new GroupeClientValue(request.getGroupeClientId()));

				if (groupeClient != null) {

					report.setGroupeClientDesignation(groupeClient.getDesignation());

					// report.setClientAbreviation(client.getAbreviation());
					// report.setClientReference(client.getReference());
					// report.setClientAdresse(client.getAdresse());
					// report.setClientTelephone(client.getTelephone());
				}
			}
		}

		if (result != null) {

			report.setDebitTotal(result.getDebitTotal());
			report.setCreditTotal(result.getCreditTotal());
			report.setSoldeClient(result.getSoldeClient());
			// System.out.println("-----------Fiche client :Solde Client-------" +
			// result.getSoldeClient());

			report.setSoldeInitial(result.getSoldeInitial());
			// System.out.println("-----------Fiche client :Solde Initial-------" +
			// result.getSoldeInitial());

			if (result.getListElements() != null) {

				report.getListElements().addAll(result.getListElements());
			}
		}

	}

	/***********************
	 * Rapport solde client
	 ******************************/

	@Override
	public SoldeClientReportValue getSoldeClientReport(RechercheMulticritereSoldeClientValue request)
			throws IOException {

		SoldeClientReportValue report = new SoldeClientReportValue();

		report.setFileName(IConstanteCommercialeReport.REPORT_NAME_SOLDE_CLIENT);
		report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_SOLDE_CLIENT));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);
		params.put("SUBREPORT_SITUATION_PATH", "C://ERP/Lib/STIT_SoldeClient/solde_client_sub_List_report.jasper");
		params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

		report.setParams(params);

		ResultatRechecheSoldeClientValue result = soldeClientDomaine.rechercherMultiCritere(request);

		enrichissementSoldeClientReportList(report, result, request);

		ArrayList<SoldeClientReportValue> dataList = new ArrayList<SoldeClientReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	/***********************
	 * Enrichissement rapport solde client
	 ******************************/

	private void enrichissementSoldeClientReportList(SoldeClientReportValue report,
			ResultatRechecheSoldeClientValue result, RechercheMulticritereSoldeClientValue request) {

		report.setBloque(request.getBloque());
		report.setPartieIntId(request.getPartieIntId());
		if (request.getPartieIntId() != null) {

			PartieInteresseValue client = partieInteresseePersistance.getById(request.getPartieIntId());

			if (client != null) {

				report.setClientAbreviation(client.getAbreviation());
				report.setClientReference(client.getReference());
			}
		}

		if (result.getList() != null) {

			List<SoldeClientValue> listSoldeClient = new ArrayList<SoldeClientValue>();

			for (SoldeClientValue soldeClient : result.getList()) {
				SoldeClientValue soldeClientElement = new SoldeClientValue();

				if (soldeClient.getPartIntId() != null) {

					PartieInteresseValue client = partieInteresseePersistance.getById(soldeClient.getPartIntId());

					if (client != null) {

						soldeClientElement.setPartieIntAbreviation(client.getAbreviation());
						soldeClientElement.setPartieIntReference(client.getReference());
					}
				}
				soldeClientElement.setSeuil(soldeClient.getSeuil());
				soldeClientElement.setBloque(soldeClient.getBloque());
				soldeClientElement.setDateIntroduction(soldeClient.getDateIntroduction());
				soldeClientElement.setSoldeInitial(soldeClient.getSoldeInitial());
				soldeClientElement.setObservation(soldeClient.getObservation());
				listSoldeClient.add(soldeClientElement);
			}
			report.getListSoldeClient().addAll(listSoldeClient);
		}

	}

	/***************************************
	 * Rapport bon de commande
	 *********************************************/

	// Added on 18/11/2016, by Zeineb Medimagh

	@Override
	public BonCommandeReportValue getBonCommandeParIdReport(Long id, String typerapport,Long numrapport, String avecPrix,String avecEntete)
			throws IOException {

		BonCommandeReportValue bonCommandeReport = new BonCommandeReportValue();
		CommandeVenteValue commandeVente = bonCommandePersistance.getById(id);

		bonCommandeReport.setFileName(REPORT_NAME_BONLIVRAISON);

		//
		BigDecimal bigDecimal = new BigDecimal(commandeVente.getMontantTTC());
		BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

		int dinars = bigDecimal.intValue();
		int millimes = fraction.intValue();

		String montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
				+ FrenchNumberToWords.convert(millimes) + MILLIMES;

		bonCommandeReport.setMontantTTCToWords(montantTTCToWords);
		//

		if (typerapport.equals("Commande")) {
			// enrechissement des param du report Commande
			
			
	       if(avecEntete.equals("non")) {
				
				if(numrapport==1) {
						   		bonCommandeReport
								.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/bon_commande_report.jrxml"));
					
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");
					
						params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/bon_commande_sub_report.jasper");
					
						bonCommandeReport.setParams(params);
				
				}
				else if(numrapport==2) {
					
			   		bonCommandeReport
					.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/SansEntete/bon_commande_report_Euro_Dollar.jrxml"));
		
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");
		
			params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/SansEntete/bon_commande_sub_report_Euro_Dollar.jasper");
		
			bonCommandeReport.setParams(params);
					
					
					
				}
				
			}
			
			
			if(avecEntete.equals("oui")) {
				
				if(numrapport==3) {
								bonCommandeReport
								.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/avecEnTete/bon_commande_report.jrxml"));
				
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");
				
						params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/avecEnTete/bon_commande_sub_report.jasper");
				
						bonCommandeReport.setParams(params);
				}
				
				
				if(numrapport==4) {
					bonCommandeReport
					.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/avecEnTete/devise/bon_commande_report_Euro_Dollar.jrxml"));
	
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");
	
			params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/avecEnTete/devise/bon_commande_sub_report_Euro_Dollar.jasper");
	
			bonCommandeReport.setParams(params);
	}
				
				
			}

	
		}
		if (typerapport.equals("Devis")) {
			
			
			
		       if(avecEntete.equals("non")) {
		    	   if(numrapport==1) {
		    		// enrechissement des param du report Devis
					// System.out.println("inn Devis");
					bonCommandeReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/devis_report.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");

					params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/devis_sub_report.jasper");

					bonCommandeReport.setParams(params);
		    	   
		    	   }
		    	   else if(numrapport==2) {
		    		   
		    			bonCommandeReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/SansEntete/devis_report_Euro_Dollar.jrxml"));

						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");

						params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/SansEntete/devis_sub_report_Euro_Dollar.jasper");

						bonCommandeReport.setParams(params);
		    		   
		    	   }
		       }
		       
		       if(avecEntete.equals("oui")) {
		    	   
		    	   if(numrapport==3) {
		    		// enrechissement des param du report Devis
					// System.out.println("inn Devis");
					bonCommandeReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/avecEnTete/devis_report.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");

					params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/avecEnTete/devis_sub_report.jasper");

					bonCommandeReport.setParams(params);
		    	   
		    	   }
		    	   else if(numrapport==4) {
		    			bonCommandeReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonCommande/avecEnTete/devise/devis_report_Euro_Dollar.jrxml"));

						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("p_PathLogo", "C:/ERP/logos_clients/logo_client.png");

						params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonCommande/avecEnTete/devise/devis_sub_report_Euro_Dollar.jasper");

						bonCommandeReport.setParams(params);   
		    		   
		    	   }
		       }
		
		}

		// enrichissement du report Commande
		enrichissementBonCommande(bonCommandeReport, commandeVente, avecPrix);

		enrichirWithBaseInfoInformation(bonCommandeReport);

		ArrayList<BonCommandeReportValue> dataList = new ArrayList<BonCommandeReportValue>();
		dataList.add(bonCommandeReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		bonCommandeReport.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonCommandeReport;

	}

	private void enrichirWithBaseInfoInformation(BonCommandeReportValue bonCommandeReport) {
		BaseInfoValue baseInfo = baseInfoPersistance.getClientActif();

		// if(baseInfo.getPrefixBonLivraison() != null)
		// bonCommandeReport.setReference(baseInfo.getPrefixBonLivraison()+bonLivraisonReportValue.getReference());

		bonCommandeReport.setAdresseCompagnie(baseInfo.getAdresse());
		bonCommandeReport.setCodeTVACompagnie(baseInfo.getCodeTVA());
		bonCommandeReport.setVilleCompagnie(baseInfo.getVille());
		bonCommandeReport.setTelephoneFixCompagnie(baseInfo.getTelephoneFix());
		bonCommandeReport.setTelephoneMoblieCompagnie(baseInfo.getTelephoneMoblie());
		bonCommandeReport.setFaxCompagnie(baseInfo.getFax());
		bonCommandeReport.setEmailCompagnie(baseInfo.getEmail());
		
		bonCommandeReport.setMatriculeFiscalCompagnie(baseInfo.getMatriculeFiscal());
		
		bonCommandeReport.getParams().put("p_PathLogo", baseInfo.getLogo());

	}

	/**
	 * @param bonCommandeReport
	 * @param commandeVente
	 */
	private void enrichissementBonCommande(BonCommandeReportValue bonCommandeReport, CommandeVenteValue commandeVente,
			String avecPrix) {

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();
		Long clientId = null;

		if (commandeVente.getPartieIntersseId() != null) {
			clientId = commandeVente.getPartieIntersseId();
		}

		if (clientIdMap.containsKey(clientId)) {
			bonCommandeReport.setClientRaisonSociale(clientIdMap.get(clientId).getRaisonSociale());
			bonCommandeReport.setClientMatriculeFiscal(clientIdMap.get(clientId).getMatriculeFiscal());
			bonCommandeReport.setClientAdresse(clientIdMap.get(clientId).getAdresse());
			bonCommandeReport.setClientTelephone(clientIdMap.get(clientId).getTelephone());
			bonCommandeReport.setClientFax(clientIdMap.get(clientId).getFax());
			bonCommandeReport.setClientAbbreviation(clientIdMap.get(clientId).getAbreviation());
			
		

		}

		bonCommandeReport.setDateCommande(commandeVente.getDateIntroduction());
		bonCommandeReport.setDateLivraison(commandeVente.getDateLivraison());
		bonCommandeReport.setReference(commandeVente.getReference());
		bonCommandeReport.setObservations(commandeVente.getObservations());
		bonCommandeReport.setPrixTotal(commandeVente.getPrixTotal());
		bonCommandeReport.setQuantiteTotale(commandeVente.getQuantite());
		

		/* initialisation des var boolean */
		bonCommandeReport.setExistFodec(false);
		bonCommandeReport.setExistTVA(false);
		bonCommandeReport.setExistTimbre(false);

		// Montants params inexistant dans la BD detboncommande !!
		bonCommandeReport.setMontantTTC(commandeVente.getMontantTTC());
		bonCommandeReport.setMontantRemiseTotal(commandeVente.getMontantRemise());
		bonCommandeReport.setMontantHTTotal(commandeVente.getPrixTotal());
		bonCommandeReport.setMontantTaxes(commandeVente.getMontantTaxe());
		// System.out.println("##### ===>report: mt taxes: " +
		// commandeVente.getMontantTaxe() + " MontantTTC: "+
		// commandeVente.getMontantTTC());

		/*******************/

		List<BonCommandeReportProductValue> listeProduitCommandes = new ArrayList<BonCommandeReportProductValue>();

		Map<Long, ProduitValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();
		//

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		for (ProduitCommandeValue produitCommande : commandeVente.getListProduitCommandes()) {

			String referenceProduit = produitIdMap.get(produitCommande.getProduitId()).getReference();
			String designationProduit = produitIdMap.get(produitCommande.getProduitId()).getDesignation();

			Long sousFamilleId = produitIdMap.get(produitCommande.getProduitId()).getSousFamilleId();

			String sousFamilleProduit = null;
			if (sousFamilleId != null) {
				SousFamilleProduitValue sousFamille = sousFamilleProduitPersistance
						.rechercheSousFamilleProduitById(sousFamilleId);
				sousFamilleProduit = sousFamille.getDesignation();
			}

			Double prixTotal = 0D;

			if (produitCommande.getQuantite() != null && produitCommande.getPrixUnitaire() != null
					&& avecPrix.equals("oui")) {
				prixTotal = produitCommande.getQuantite() * produitCommande.getPrixUnitaire();
			}

			BonCommandeReportProductValue bonCommandeReportElement = new BonCommandeReportProductValue(referenceProduit,
					designationProduit, sousFamilleProduit, produitCommande.getQuantite(),
					produitCommande.getPrixUnitaire(), prixTotal);

			if (avecPrix.equals("non")) {
				bonCommandeReportElement.setPu(0D);
			}

			// added 25 03 2018 ajout des autres champs dans le detail du
			// produit

			//bonCommandeReportElement.setTauxTvaArt(produitIdMap.get(produitCommande.getProduitId()).getIdTaxe().intValue());
			bonCommandeReportElement.setTauxTvaArt(produitCommande.getTaxeId().intValue());
					

			// //// Ajout TVA /////////////
			Double totalProduitCommande = (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			
			if (produitCommande.getRemise()==null)
				produitCommande.setRemise(0D);
			Double totalApresRemise=totalProduitCommande*(1-produitCommande.getRemise()/100);
			
			//ProduitValue produitValue = produitDomaine.rechercheProduitById(produitCommande.getProduitId());
			if (!produitTaxeMap.containsKey(produitCommande.getTaxeId())) {

				produitTaxeMap.put(produitCommande.getTaxeId(), totalApresRemise);

			} else {
				Double assietteValue = produitTaxeMap.get(produitCommande.getTaxeId()) + totalApresRemise;
				produitTaxeMap.put(produitCommande.getTaxeId(), assietteValue);

			}
			
			bonCommandeReportElement.setRemise(produitCommande.getRemise());
			
		listeProduitCommandes.add(bonCommandeReportElement);

		}

		if (avecPrix.equals("non")) {
			bonCommandeReport.setPrixTotal(0D);
		}

		Map<Long, TaxeCommandeValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeCommandeValue>();

		for (TaxeCommandeValue value : commandeVente.getListProduitTaxes()) {

			Long key = value.getId();
			if (taxeLivraisonIdTaxeMap.get(key) == null) {
				taxeLivraisonIdTaxeMap.put(value.getTaxeId(), value);
			}

			taxeLivraisonIdTaxeMap.put(value.getTaxeId(), value);
		}

		// System.out.println("###### Liste from map #####");

		// System.out.println(" #### map size : " + taxeLivraisonIdTaxeMap.size());

		// System.out.println("###### Liste from map #####");

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			bonCommandeReport.setExistFodec(true);

			// FODEC params
			bonCommandeReport.setTaxeFodec(TAXE_FODEC);
			bonCommandeReport.setTauxFodec(taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage());
			bonCommandeReport.setMontantTaxeFodec(taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getMontant());
			bonCommandeReport.setAssietteFodec(commandeVente.getMontantHTTotal());
		}

		// TVA Reparti

		// //// PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA)) {
			bonCommandeReport.setExistTVA(true);

			// TVA params
			bonCommandeReport.setTaxeTVA(TAXE_TVA);
			bonCommandeReport.setTauxTVA(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getPourcentage());
			bonCommandeReport.setMontantTaxeTVA(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getMontant());

			bonCommandeReport.setAssietteTVA(produitTaxeMap.get(TAXE_ID_TVA));

		}
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA7)) {
			bonCommandeReport.setExistTVA7(true);

			// TVA params
			bonCommandeReport.setTaxeTVA7(TAXE_TVA7);
			bonCommandeReport.setTauxTVA7(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA7).getPourcentage());
			bonCommandeReport.setMontantTaxeTVA7(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA7).getMontant());

			bonCommandeReport.setAssietteTVA7(produitTaxeMap.get(TAXE_ID_TVA7));
		}
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA13)) {
			bonCommandeReport.setExistTVA13(true);

			// TVA params
			bonCommandeReport.setTaxeTVA13(TAXE_TVA13);
			bonCommandeReport.setTauxTVA13(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA13).getPourcentage());
			bonCommandeReport.setMontantTaxeTVA13(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA13).getMontant());

			bonCommandeReport.setAssietteTVA13(produitTaxeMap.get(TAXE_ID_TVA13));
		}
		
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			bonCommandeReport.setExistTimbre(true);

			// TIMBRE params
			bonCommandeReport.setTaxeTimbre(TAXE_TIMBRE);
			bonCommandeReport.setMontantTaxeTimbre(taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant());
		}

		// //// FIN PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		// TVA Reparti

		/******************************/

		Collections.sort(listeProduitCommandes);
		bonCommandeReport.setListeProduitCommandes(listeProduitCommandes);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.report.
	 * IGestionnaireReportGcDomaine#getReglementReport(com.gpro.consulting.tiers
	 * .logistique.coordination.gc.reglement.value.
	 * RechercheMulticritereReglementValue)
	 */
	@Override
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException {

		ResultatRechecheReglementValue reglementValue = reglementPersistance.rechercherMultiCritere(request);

		ReglementReportValue report = new ReglementReportValue();

		report.setFileName(REPORT_NAME_REGLEMENT);
		report.setReportStream(new FileInputStream("C://ERP/Lib/STIT_Reglement/list/reglement_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");

		params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_Reglement/list/reglement_sub_report.jasper");

		report.setParams(params);

		// Enrichissement Reglement

		enrichissementReglementReport(report, reglementValue, request);

		ArrayList<ReglementReportValue> dataList = new ArrayList<ReglementReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	/**
	 * @param report
	 * @param reglementValue
	 */
	private void enrichissementReglementReport(ReglementReportValue report, ResultatRechecheReglementValue result,
			RechercheMulticritereReglementValue request) {

		// System.out.println("-------result.getList().length-----" +
		// result.getList().size());
		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		if (clientIdMap.containsKey(request.getPartieIntId())) {
			Long clientId = request.getPartieIntId();
			report.setClientAbreviation(clientIdMap.get(clientId).getAbreviation());
		}

		report.setDateMin(request.getDateReglementMin());
		report.setDateMax(request.getDateReglementMax());
		report.setMontantMin(request.getMontantMin());
		report.setMontantMax(request.getMontantMax());

		List<ReglementReportElementValue> listreglementReportElement = new ArrayList<ReglementReportElementValue>();

		for (ResultatRechecheReglementElementValue reglementValue : result.getList()) {

			// System.out.println("-------reglementValue-----" + reglementValue.toString());

			ReglementReportElementValue element = new ReglementReportElementValue();
			if (clientIdMap.containsKey(reglementValue.getPartieIntId())) {
				Long clientId = reglementValue.getPartieIntId();
				element.setClientAbreviation(clientIdMap.get(clientId).getAbreviation());
				element.setClientReference(clientIdMap.get(clientId).getReference());
			}

			element.setDate(reglementValue.getDate());
			element.setMontant(reglementValue.getMontantTotal());
			element.setRefReglement(reglementValue.getReference());

			listreglementReportElement.add(element);
		}

		// System.out.println("-------listreglementReportElement.length-----" +
		// listreglementReportElement.size());

		report.setListReglementReportElement(listreglementReportElement);

	}

	/**
	 * @param report
	 * @param getReglementReportParRef
	 */
	@Override
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException {

		ReglementValue reglementValue = reglementPersistance.getById(id);

		ReglementReportParRefValue report = new ReglementReportParRefValue();

		report.setFileName(REPORT_NAME_REGLEMENT);
		report.setReportStream(new FileInputStream("C://ERP/Lib/STIT_Reglement/detail/reglement_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");

		params.put("SUBREPORT_INVENTAIRE_PATH",
				"C://ERP/Lib/STIT_Reglement/detail/reglement_details_sub_report.jasper");

		params.put("SUBREPORT_ELEMENTS_PATH", "C://ERP/Lib/STIT_Reglement/detail/reglement_elements_sub_report.jasper");

		report.setParams(params);

		// Enrichissement Reglement

		enrichissementGetReglementReportParRef(report, reglementValue);

		ArrayList<ReglementReportParRefValue> dataList = new ArrayList<ReglementReportParRefValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementGetReglementReportParRef(ReglementReportParRefValue report,
			ReglementValue reglementValue) {

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		if (clientIdMap.containsKey(reglementValue.getPartieIntId())) {
			Long clientId = reglementValue.getPartieIntId();
			report.setClientAbreviation(clientIdMap.get(clientId).getAbreviation());
		}
		report.setDate(reglementValue.getDate());

		List<ReglementReportParRefElementDetailsValue> listDetailsReportReglement = new ArrayList<ReglementReportParRefElementDetailsValue>();
		List<ReglementReportParRefElementEltReglementValue> listElementsReportReglement = new ArrayList<ReglementReportParRefElementEltReglementValue>();

		if (reglementValue != null) {

			report.setReference(reglementValue.getReference());
			report.setMontant(reglementValue.getMontantTotal());
			for (DetailsReglementValue reglementValueElt : reglementValue.getListDetailsReglement()) {

				// System.out.println("-------reglementValueElt-----" +
				// reglementValueElt.toString());

				ReglementReportParRefElementDetailsValue element = new ReglementReportParRefElementDetailsValue();

				element.setBanque(reglementValueElt.getBanque());
				element.setNumPiece(reglementValueElt.getNumPiece());
				element.setMontant(reglementValueElt.getMontant());
				element.setDateEmission(reglementValueElt.getDateEmission());
				element.setDateEcheance(reglementValueElt.getDateEcheance());
				String regle;
				if (reglementValueElt.getRegle() != null && reglementValueElt.getRegle() == true ) {

					regle = "Oui";

				} else {

					regle = "Non";
				}
				element.setRegle(regle);

				TypeReglementValue typeReglementValue = typeReglementPersistance
						.getById(reglementValueElt.getTypeReglementId());
				element.setTypeReglement(typeReglementValue.getDesignation());

				listDetailsReportReglement.add(element);
			}

			for (ElementReglementValue reglementValueElt : reglementValue.getListElementReglement()) {

				// System.out.println("-------reglementValueElt-----" +
				// reglementValueElt.toString());

				ReglementReportParRefElementEltReglementValue element = new ReglementReportParRefElementEltReglementValue();

				// TODO : A vérifier refFacture ou refBL
				element.setReference(reglementValueElt.getRefBL());
				element.setMontantTotal(reglementValueElt.getMontantDemande());
				element.setDate(reglementValueElt.getDateEcheance());

				listElementsReportReglement.add(element);
			}
		}

		// System.out.println("-------listDetailsReportReglement.length-----" +
		// listDetailsReportReglement.size());

		// System.out.println("-------listElementsReportReglement.length-----" +
		// listElementsReportReglement.size());

		report.setListDetailsReportReglement(listDetailsReportReglement);
		report.setListElementReportReglement(listElementsReportReglement);

	}

	// Hajer AMRI on 24/03/2017
	// @Override
	// public BonReceptionReportValue getBonReceptionParIdReport(Long id, String
	// avecPrix) throws IOException {
	// // TODO Auto-generated method stub
	// return null;
	// }

	/***************************************
	 * Rapport bon de reception
	 *********************************************/

	// Added on 24/03/2017, by Hajer AMRI
	@Override
	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix) throws IOException {

		BReceptionReportValue bonReceptionReport = new BReceptionReportValue();

		ReceptionVenteValue receptionVente = receptionPersistance.getById(id);

		// enrechissement des param du report
		bonReceptionReport.setFileName(REPORT_NAME_BONLIVRAISON);
		bonReceptionReport
				.setReportStream(new FileInputStream("C://ERP/Lib/COMMERCIAL_BonReception/bon_reception_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");

		params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/COMMERCIAL_BonReception/bon_reception_sub_report.jasper");

		bonReceptionReport.setParams(params);

		// enrichissement du report
		enrichissementBonReception(bonReceptionReport, receptionVente, avecPrix);

		ArrayList<BReceptionReportValue> dataList = new ArrayList<BReceptionReportValue>();
		dataList.add(bonReceptionReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		bonReceptionReport.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonReceptionReport;
	}

	/**
	 * @param bonReceptionReport
	 * @param receptionVente
	 */
	private void enrichissementBonReception(BReceptionReportValue bonReceptionReport,
			ReceptionVenteValue receptionVente, String avecPrix) {

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();
		Long clientId = null;

		if (receptionVente.getPartieIntersseId() != null) {
			clientId = receptionVente.getPartieIntersseId();
		}

		if (clientIdMap.containsKey(clientId)) {
			bonReceptionReport.setClientRaisonSociale(clientIdMap.get(clientId).getRaisonSociale());
			bonReceptionReport.setClientMatriculeFiscal(clientIdMap.get(clientId).getMatriculeFiscal());
			bonReceptionReport.setClientAdresse(clientIdMap.get(clientId).getAdresse());
			bonReceptionReport.setClientTelephone(clientIdMap.get(clientId).getTelephone());
			bonReceptionReport.setClientFax(clientIdMap.get(clientId).getFax());
			bonReceptionReport.setClientAbbreviation(clientIdMap.get(clientId).getAbreviation());

		}

		bonReceptionReport.setDateReception(receptionVente.getDateIntroduction());
		bonReceptionReport.setDateLivraison(receptionVente.getDateLivraison());
		bonReceptionReport.setReference(receptionVente.getReference());
		bonReceptionReport.setObservations(receptionVente.getObservations());
		bonReceptionReport.setPrixTotal(receptionVente.getPrixTotal());
		bonReceptionReport.setQuantiteTotale(receptionVente.getQuantite());

		List<BReceptionReportProductValue> listeProduitReceptions = new ArrayList<BReceptionReportProductValue>();

		Map<Long, ProduitValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();

		for (ProduitReceptionValue produitReception : receptionVente.getListProduitReceptions()) {

			String referenceProduit = produitIdMap.get(produitReception.getProduitId()).getReference();
			String designationProduit = produitIdMap.get(produitReception.getProduitId()).getDesignation();

			Long sousFamilleId = produitIdMap.get(produitReception.getProduitId()).getSousFamilleId();

			String sousFamilleProduit = null;
			if (sousFamilleId != null) {
				SousFamilleProduitValue sousFamille = sousFamilleProduitPersistance
						.rechercheSousFamilleProduitById(sousFamilleId);
				sousFamilleProduit = sousFamille.getDesignation();
			}

			Double prixTotal = 0D;

			if (produitReception.getQuantite() != null && produitReception.getPrixUnitaire() != null
					&& avecPrix.equals("oui")) {
				prixTotal = produitReception.getQuantite() * produitReception.getPrixUnitaire();
			}

			BReceptionReportProductValue bonReceptionReportElement = new BReceptionReportProductValue(referenceProduit,
					designationProduit, sousFamilleProduit, produitReception.getQuantite(),
					produitReception.getPrixUnitaire(), prixTotal);

			if (avecPrix.equals("non")) {
				bonReceptionReportElement.setPu(0D);
			}

			listeProduitReceptions.add(bonReceptionReportElement);

		}

		if (avecPrix.equals("non")) {
			bonReceptionReport.setPrixTotal(0D);
		}

		Collections.sort(listeProduitReceptions);
		bonReceptionReport.setListeProduitReceptions(listeProduitReceptions);

	}

	@Override
	public FicheGroupeClientReportValue getFicheGroupeClientReport(RechercheMulticritereFicheGroupeClientValue request)
			throws IOException {
		FicheGroupeClientReportValue report = new FicheGroupeClientReportValue();

		if (request.getTypeRapport().equals("ficheGroupe")) {

			/** ficheGroupe **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_FICHE_GROUPE);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_FICHE_GROUPE));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheGroupeClientValue result = ficheClientDomaine
					.rechercherMultiCritereGroupeClient(request);

			enrichmentFicheGroupeClientReportList(report, result, request);

			ArrayList<FicheGroupeClientReportValue> dataList = new ArrayList<FicheGroupeClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		} else if (request.getTypeRapport().equals("releveGroupe")) {

			/** releveGroupe **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_RELEVE_GROUPE);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_RELEVE_GROUPE));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheGroupeClientValue result = ficheClientDomaine
					.rechercherMultiCritereGroupeClient(request);

			enrichmentFicheGroupeClientReportList(report, result, request);

			ArrayList<FicheGroupeClientReportValue> dataList = new ArrayList<FicheGroupeClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		}

		return report;
	}

	@Override
	public MouvementProduitReportValue getMouvementProduitElementReport(Long produitId, Calendar dateMin,
			Calendar dateMax) {
		MouvementProduitReportValue mouvementProduitReportValue = new MouvementProduitReportValue();

		ProduitValue prod = produitDomaine.rechercheProduitById(produitId);

		mouvementProduitReportValue.setProduitReference(prod.getReference());
		mouvementProduitReportValue.setProduitDesignation(prod.getDesignation());
		
		
		
		//Debut calcule de qte avant date Min 

		RechercheMulticritereDetLivraisonValue requestDetailLivraisonForCalculeQteBeforeDate = new RechercheMulticritereDetLivraisonValue();
		requestDetailLivraisonForCalculeQteBeforeDate.setIdProduit(produitId);
		requestDetailLivraisonForCalculeQteBeforeDate.setDateStrA(dateMin);

		Double sommeQteLivraisonBeforeDate = new Double(0);
		Double sommeQteReceptionBeforeDate = new Double(0);

		ResultatRechecheDetBonLivraisonValue resultRechRequestDetailLivraisonForCalculeQteBeforeDate = detLivraisonVentePersistance
				.getResultatRechercheMcDetLivraisonValue(requestDetailLivraisonForCalculeQteBeforeDate);

		for (DetLivraisonVenteValue detail : resultRechRequestDetailLivraisonForCalculeQteBeforeDate
				.getListDetailLivraison()) {

			sommeQteLivraisonBeforeDate += detail.getQuantite();

		}

		RechercheMulticritereProduitReceptionAchatValue requestProdRecForCalculeQteBeforeDate = new RechercheMulticritereProduitReceptionAchatValue();
		requestProdRecForCalculeQteBeforeDate.setProduitId(produitId);
		requestProdRecForCalculeQteBeforeDate.setDateIntroductionStrA(dateMin);

		ResultatRechecheProduitReceptionAchatValue resProdRecForCalculeQteBeforeDate = produitReceptionAchatPersistance
				.rechercherMultiCritere(requestProdRecForCalculeQteBeforeDate);

		for (ProduitReceptionAchatValue detail : resProdRecForCalculeQteBeforeDate.getListProduitReceptionAchat()) {

			sommeQteReceptionBeforeDate += detail.getQuantite();

		}
		
		//Fin calcule de qte avant date Min 

		RechercheMulticritereDetLivraisonValue request = new RechercheMulticritereDetLivraisonValue();

		request.setIdProduit(produitId);
		request.setDateDe(dateMin);
		request.setDateA(dateMax);

		ResultatRechecheDetBonLivraisonValue resultRechDetLiv = detLivraisonVentePersistance
				.getResultatRechercheMcDetLivraisonValue(request);

		RechercheMulticritereProduitReceptionAchatValue requestProdRec = new RechercheMulticritereProduitReceptionAchatValue();
		requestProdRec.setProduitId(produitId);
		requestProdRec.setDateIntroductionDu(dateMin);
		requestProdRec.setDateIntroductionA(dateMax);

		ResultatRechecheProduitReceptionAchatValue resProdRec = produitReceptionAchatPersistance
				.rechercherMultiCritere(requestProdRec);

		List<MouvementProduitElementReportValue> mouvements = new ArrayList<>();

		for (DetLivraisonVenteValue detail : resultRechDetLiv.getListDetailLivraison()) {

			MouvementProduitElementReportValue mv = new MouvementProduitElementReportValue();

			mv.setTypeMouvement("Livraison");
			mv.setReferenceMouvement(detail.getReferenceBL());
			mv.setQuantite(detail.getQuantite());
			mv.setDateMouvement(detail.getDate());

			mouvements.add(mv);

		}

		for (ProduitReceptionAchatValue detail : resProdRec.getListProduitReceptionAchat()) {

			MouvementProduitElementReportValue mv = new MouvementProduitElementReportValue();

			mv.setTypeMouvement("Réception");
			mv.setReferenceMouvement(detail.getReceptionAchatValue().getReference());
			mv.setQuantite(detail.getQuantite());
			mv.setDateMouvement(detail.getReceptionAchatValue().getDateIntroduction());

			mouvements.add(mv);

		}

		Collections.sort(mouvements);

		Double qteEnStock = sommeQteReceptionBeforeDate - sommeQteLivraisonBeforeDate;

		for (MouvementProduitElementReportValue detail : mouvements) {

			if (detail.getTypeMouvement().equals("Réception")) {

				qteEnStock += detail.getQuantite();

				detail.setQuantiteEnStock(qteEnStock);
			} else {

				qteEnStock -= detail.getQuantite();

				detail.setQuantiteEnStock(qteEnStock);

			}

		}

		mouvementProduitReportValue.setQteLivraison(sommeQteLivraisonBeforeDate);
		mouvementProduitReportValue.setQteReception(sommeQteReceptionBeforeDate);
		mouvementProduitReportValue.setQteEnStock(sommeQteReceptionBeforeDate-sommeQteLivraisonBeforeDate);

		mouvementProduitReportValue.setMouvement(mouvements);

		return mouvementProduitReportValue;
	}

	@Override
	public FactureReportElementRecapValue getFactureReportElementRecapValue(RechercheMulticritereFactureValue request) {

		FactureReportElementRecapValue recapFacture = new FactureReportElementRecapValue();
		
		Double montantTTC = new Double(0);
		Double montantHtaxe = new Double(0);

		ResultatRechecheFactureValue resRech = facturePersistance.rechercherMultiCritere(request);
		
		for(FactureVenteValue facture : resRech.getList()) {
			
			if(facture.getMontantTTC() != null && facture.getDevise() != null) {
				
				if(!facture.getDevise().equals(DeviseValue.DINAR)) {
					
					
					if(facture.getMontantConverti() != null)
						 montantTTC += facture.getMontantConverti();
					      
					
				}else
					
				{
					
					montantTTC += facture.getMontantTTC();
				}
				
				
				
			}
			
			
			if(facture.getMontantHTaxe() != null && facture.getDevise() != null) {
				
	
				
	     	if(!facture.getDevise().equals(DeviseValue.DINAR)) {
					
					
			  if(facture.getTauxConversion()  != null)
					
						montantHtaxe += facture.getMontantHTaxe() * facture.getTauxConversion() ;
					
				}else
					
				{
					montantHtaxe += facture.getMontantHTaxe()  ;
				}
				
				
				
				
			
			}
			
			
			
			
			
		}
		
		recapFacture.setNbr(resRech.getList().size());
		recapFacture.setMontantTTC(montantTTC);
		recapFacture.setMontantHTaxe(montantHtaxe);
		recapFacture.setDebut(request.getDateFactureMin());
		recapFacture.setFin(request.getDateFactureMax());
		recapFacture.setType(request.getType());
		recapFacture.setMois(request.getDateFactureMin().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));
		
		int month = request.getDateFactureMin().get(Calendar.MONTH)+ 1;
		
		int year = request.getDateFactureMin().get(Calendar.YEAR) ;
		
		recapFacture.setMonth(Long.valueOf(month));
		recapFacture.setYear(Long.valueOf(year));
		
		
		
		return recapFacture;
	}

	@Override
	public BLReportElementRecapValue getBLReportElementRecapValue(RechercheMulticritereBonLivraisonValue request) {

		BLReportElementRecapValue recapFacture = new BLReportElementRecapValue();
		
		Double montantTTC = new Double(0);
		Double montantHtaxe = new Double(0);

		ResultatRechecheBonLivraisonValue resRech = bonLivraisonPersitance.rechercherMultiCritere(request);
		
		for(LivraisonVenteValue facture : resRech.getList()) {
			
			if(facture.getMontantTTC() != null)
			montantTTC += facture.getMontantTTC();
			
			if(facture.getMontantHTaxe() != null)
			montantHtaxe +=  facture.getMontantHTaxe();
			
			
			
			
		}
		
		recapFacture.setNbr(resRech.getList().size());
		recapFacture.setMontantTTC(montantTTC);
		recapFacture.setMontantHTaxe(montantHtaxe);
		recapFacture.setDebut(request.getDateLivraisonMin());
		recapFacture.setFin(request.getDateLivraisonMax());
		//recapFacture.setType(request.getType());
		recapFacture.setMois(request.getDateLivraisonMin().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));
		return recapFacture;
	}

	@Override
	public RegelementReportElementRecapValue getReglementReportValue(RechercheMulticritereReglementValue request) {


		RegelementReportElementRecapValue result = new RegelementReportElementRecapValue();
		
		Double montantReglement = new Double(0);

		ResultatRechecheReglementValue resRech = reglementPersistance.rechercherMultiCritere(request);
		
		for(ResultatRechecheReglementElementValue regelement : resRech.getList()) {
			
			if(regelement.getMontantTotal() != null)
			montantReglement+=regelement.getMontantTotal();
			
			
			
			
			
		}
		
		result.setNbr(resRech.getList().size());
		result.setMontantRegement(montantReglement);
		result.setDebut(request.getDateReglementMin());
		result.setFin(request.getDateReglementMax());
		result.setMois(request.getDateReglementMin().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));
		return result;
	}

	@Override
	public List<ProduitValue> getTop10ArticleReportValue(RechercheMulticritereDetLivraisonValue request) {

		//List<ProduitValue> result = new ArrayList<ProduitValue>();
		List<ProduitValue> resRech = detLivraisonVentePersistance.rechercherTop10Article(request);

		
		return resRech;
	}

	@Override
	public List<ResultBestElementValue> getTop10ClientReportValue(RechercheMulticritereDetLivraisonValue request) {

		return detLivraisonVentePersistance.rechercherTop10Client(request);
	}

	@Override
	public List<ResultBestElementValue> getChiffreAffaireByClient(RechercheMulticritereDetLivraisonValue request) {

		return detLivraisonVentePersistance.rechercheChiffreAffaireByClient(request);
	}

	@Override
	public List<ResultBestElementValue> getChiffreAffaireBySousFamille(RechercheMulticritereDetLivraisonValue request) {

		return detLivraisonVentePersistance.rechercheChiffreAffaireByCSousFamille(request);
	}

	@Override
	public List<ResultBestElementValue> getChiffreAffaireByFamille(RechercheMulticritereDetLivraisonValue request) {

		return detLivraisonVentePersistance.rechercheChiffreAffaireByFamille(request);

	}
	
	@Override
	public ResultBestElementValue getReglementReportValueOptimise(RechercheMulticritereReglementValue request) {


		RegelementReportElementRecapValue result = new RegelementReportElementRecapValue();
		
		

		return reglementPersistance.getReglementByMonth(request);
		
		

}
	
	@Override
	public List<ResultBestElementValue> getChiffreAffairebyGroupe(RechercheMulticritereDetLivraisonValue request) {

		return detLivraisonVentePersistance.rechercheChiffreAffaireByGroupe(request);

	}

	@Override
	public List<ResultBestElementValue> getTop10Groupe(RechercheMulticritereDetLivraisonValue request) {

		return detLivraisonVentePersistance.rechercherTop10Groupe(request);
	}

	@Override
	public BonLivraisonReportValue getBonLivraisonParId(Long id, String avecPrix) throws IOException {
		BonLivraisonReportValue bonLivraisonReport = new BonLivraisonReportValue();
		LivraisonVenteValue livraisonVente = bonLivraisonPersitance.getBonLivraisonById(id);

		// enrechissement des param du report
		bonLivraisonReport.setFileName(REPORT_NAME_BONLIVRAISON);
		if (livraisonVente.getNatureLivraison().equals("FINI")) {
		bonLivraisonReport
				.setReportStream(new FileInputStream("C://ERP/Lib/GPRO_BonLivraison/bon_livraison_report.jrxml"));
		}
		else {
			bonLivraisonReport
			.setReportStream(new FileInputStream("C://ERP/Lib/GPRO_BonLivraison/bon_livraison_fac_report.jrxml"));
		}

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logoSTIT.png");

		if (livraisonVente.getNatureLivraison().equals("FINI")) {
			params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/GPRO_BonLivraison/bon_livraison_sub_report.jasper");
		} else if (livraisonVente.getNatureLivraison().equals("FACON")) {
			params.put("SUBREPORT_INVENTAIRE_PATH",
					"C://ERP/Lib/GPRO_BonLivraison/bon_livraison_facon_sub_report.jasper");
		}

		bonLivraisonReport.setParams(params);

		if (avecPrix.equals(IConstanteLogistique.YES)) {

			// enrichissement du report
			enrichmentBonLivraisonReport(bonLivraisonReport, livraisonVente);

			BigDecimal bigDecimal = new BigDecimal(livraisonVente.getMontantTTC());
			BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
			BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

			int dinars = bigDecimal.intValue();
			int millimes = fraction.intValue();

			String montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
					+ FrenchNumberToWords.convert(millimes) + MILLIMES;

			bonLivraisonReport.setMontantTTCToWords(montantTTCToWords);

		} else {
			// enrichissement du report
			enrichmentBonLivraisonReportWithOutPrix(bonLivraisonReport, livraisonVente);

		}

		ArrayList<BonLivraisonReportValue> dataList = new ArrayList<BonLivraisonReportValue>();
		dataList.add(bonLivraisonReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		bonLivraisonReport.setJRBeanCollectionDataSourceProduct(jRBeanCollectionDataSource);

		return bonLivraisonReport;
	}

	@Override
	public EcheancierReportListValue getListEcheanceInverseReport(RechercheMulticritereDetailReglementValue request)
			throws IOException {


		EcheancierReportListValue echeanceReportList = new EcheancierReportListValue();

		// enrechissement des param du report
		echeanceReportList.setFileName(REPORT_NAME_ECHEANCIER_LIST);
		echeanceReportList.setReportStream(
				new FileInputStream("C://ERP/Lib/EcheancierClient/ListeEcheancier/echeancier_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_ECHEANCIER_PATH",
				"C://ERP/Lib/EcheancierClient/ListeEcheancier/echeancier_sub_List_report.jasper");

		echeanceReportList.setParams(params);
		if (request != null) {
			ResultatRechecheDetailReglementValue result = echeancierInversePersistance.rechercherMultiCritere(request);
			if (result != null) {
				List<ResultatRechecheDetailReglementElementValue> echeancierList = new ArrayList<ResultatRechecheDetailReglementElementValue>(
						result.getList());
				if (echeancierList != null) {
					// enrichissement du report
					enrichissementEcheancierInverseReportList(echeanceReportList, echeancierList, request);
				}
			}
		}
		ArrayList<EcheancierReportListValue> dataList = new ArrayList<EcheancierReportListValue>();
		dataList.add(echeanceReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		echeanceReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return echeanceReportList;
	}
	
	
	private void enrichissementEcheancierInverseReportList(EcheancierReportListValue report,
			List<ResultatRechecheDetailReglementElementValue> echeancierList,
			RechercheMulticritereDetailReglementValue request) {

		report.setDateReglementAu(request.getDateReglementAu());
		report.setDateReglementDu(request.getDateReglementDu());
		report.setDateEcheanceDu(request.getDateEcheanceDu());
		report.setDateEcheanceAu(request.getDateEcheanceAu());
		report.setNumPiece(request.getNumPiece());
		report.setPartieIntId(request.getPartieIntId());
		report.setReference(request.getReference());
		report.setRegle(request.getRegle());
		report.setTypeReglementId(request.getTypeReglementId());
		report.setAvecTerme(request.getAvecTerme());
		report.setNomRapport(request.getNomRapport());

		if (request.getTypeReglementId() != null) {
			TypeReglementValue typeReglement = typeReglementPersistance.getById(request.getTypeReglementId());
			if (typeReglement != null) {

				report.setTypeReglement(typeReglement.getDesignation());
			}
		}

		if (request.getPartieIntId() != null) {
			PartieInteresseValue pi = partieInteresseePersistance.getById(request.getPartieIntId());
			if (pi != null) {

				report.setPartieIntAbreviation(pi.getAbreviation());
			}
		}

		List<ResultatRechecheDetailReglementElementValue> echeanceElementList = new ArrayList<ResultatRechecheDetailReglementElementValue>();
		for (ResultatRechecheDetailReglementElementValue echeancier : echeancierList) {

			ResultatRechecheDetailReglementElementValue reportElement = new ResultatRechecheDetailReglementElementValue();

			reportElement.setDateEcheance(echeancier.getDateEcheance());
			reportElement.setDateEmission(echeancier.getDateEmission());
			reportElement.setBanque(echeancier.getBanque());
			reportElement.setMontant(echeancier.getMontant());
			reportElement.setNumPiece(echeancier.getNumPiece());
			reportElement.setRefFacture(echeancier.getRefFacture());
			reportElement.setRegle(echeancier.getRegle());
			reportElement.setReglementId(echeancier.getReglementId());
			reportElement.setTypeReglementId(echeancier.getTypeReglementId());
			
			reportElement.setReferenceDetReglement(echeancier.getReferenceDetReglement());
			reportElement.setObservation(echeancier.getObservation());
			
			
			reportElement.setBanqueSociete(echeancier.getBanqueSociete());
			reportElement.setDateDepotBanque(echeancier.getDateDepotBanque());
			reportElement.setChargeBanque(echeancier.getChargeBanque());
			reportElement.setTvaBanque(echeancier.getTvaBanque());
		

			if (echeancier.getTypeReglementId() != null) {
				TypeReglementValue typeReglement = typeReglementPersistance.getById(echeancier.getTypeReglementId());
				if (typeReglement != null) {

					reportElement.setTypeReglement(typeReglement.getDesignation());
				}
			}

			if (echeancier.getReglementId() != null) {
				ReglementValue reglement = reglementInversePersistance.getById(echeancier.getReglementId());
				if (reglement != null) {

					reportElement.setRefReglement(reglement.getReference());
					reportElement.setDateReglement(reglement.getDate());
					if (reglement.getPartieIntId() != null) {
						PartieInteresseValue partieInterssee = partieInteresseePersistance
								.getById(reglement.getPartieIntId());
						if (partieInterssee != null) {
							reportElement.setClientAbreviation(partieInterssee.getAbreviation());
							if (partieInterssee.getRegionId() != null) {
								RegionValue region = regionPersistance.getById(partieInterssee.getRegionId());
								reportElement.setClientRegion(region.getDesignation());
							}

						}
					}
				}
			}

			echeanceElementList.add(reportElement);
		}
		report.setEcheancierList(echeanceElementList);

	}
}
