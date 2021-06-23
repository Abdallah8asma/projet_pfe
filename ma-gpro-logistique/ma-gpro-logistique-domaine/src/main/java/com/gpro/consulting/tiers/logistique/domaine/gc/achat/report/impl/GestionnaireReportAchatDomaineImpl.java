package com.gpro.consulting.tiers.logistique.domaine.gc.achat.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IRegionPersistance;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommercialeReport;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.TaxeCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.RechercheMulticritereFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.ResultatRechecheFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.TaxeReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.report.facture.value.FactureAchatReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.RechercheMulticritereFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value.BonCommandeReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value.BonCommandeReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value.EcheancierReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.fichegroupeclient.value.FicheGroupeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefElementDetailsValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefElementEltReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportParRefValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement.ReglementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.ResultatRechecheSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValueComparator;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.reception.value.BReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.soldeClient.value.SoldeClientReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportTraitementFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.cache.IGestionnaireLogistiqueCacheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.fichefournisseur.IFicheFournisseurDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.report.IGestionnaireReportAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IModePaiementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.ficheclient.IFicheClientDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.IGestionnaireReportGcDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.uilities.FrenchNumberToWords;
import com.gpro.consulting.tiers.logistique.domaine.gc.reporting.situation.ISituationReportingDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.soldeClient.ISoldeClientDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.FactureReportProductComparator;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.ITraitementFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IEnginDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IPersonnelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IRemorqueDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMagasinDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.IBonCommandeAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier.IEcheancierFournisseurPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier.inverse.IEcheancierInverseFournisseurPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.ITypeReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.IReglementInverseAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IBonCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.IEcheancierPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.IReceptionPersistance;
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
public class GestionnaireReportAchatDomaineImpl implements IGestionnaireReportAchatDomaine {

	private static final String REPORT_NAME_BONLIVRAISON = "bon de Reception";
	private static final String REPORT_NAME_FACTURE = "facture";
	private static final String REPORT_NAME_FACTURE_LIST = "factures";
	private static final String REPORT_NAME_BONLIVRAISON_LIST = "bons de livraison";
	private static final String REPORT_NAME_ECHEANCIER_LIST = "Echeancier Fournisseur";
	private static final String REPORT_NAME_REGLEMENT = "Règlement";

	private static final Double ZERO = 0D;

	// default static taxeId values:
	// taxeId = 1 -> FODEC
	// taxeId = 2 -> TVA
	// taxeId = 3 -> TIMBRE
	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 2L;
	private static final Long TAXE_ID_TIMBRE = 3L;
	private static final Long TAXE_ID_TIMBRE_2 = 7L;
	private static final Long TAXE_ID_TIMBRE_3 = 8L;

	private static final String TAXE_TVA = "tva 19";
	private static final String TAXE_TVA7 = "tva 7";
	private static final String TAXE_TVA13 = "tva 13";

	private static final Long TAXE_ID_TVA7 = 4L;
	private static final Long TAXE_ID_TVA13 = 5L;

	private static final String TAXE_FODEC = "fodec";
	private static final String TAXE_TIMBRE = "timbre";
	private static final String TAXE_TIMBRE_2 = "timbre 2";
	private static final String TAXE_TIMBRE_3 = "timbre 3";

	private static final String DINARS = " dinars ";
	private static final String MILLIMES = " millimes";
	private static final String ET = " et ";

	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersitance;

	@Autowired
	private ITaxePersistance taxePersistance;

	@Autowired
	private IGestionnaireLogistiqueCacheDomaine gestionnaireLogistiqueCacheDomaine;

	@Autowired
	private IFacturePersistance facturePersistance;

	@Autowired
	private IEcheancierPersistance echeancierPersistance;

	@Autowired
	private IEcheancierFournisseurPersistance echeancierFournisseurPersistance;
	
	
	@Autowired
	private IEcheancierInverseFournisseurPersistance echeancierInverseFournisseurPersistance;

	@Autowired
	private ITypeReglementAchatPersistance typeReglementPersistance;

	@Autowired
	private IReglementAchatPersistance reglementAchatPersistance;
	
	@Autowired
	private IReglementInverseAchatPersistance reglementInverseAchatPersistance;

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
	private ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	@Autowired
	private IProduitService produitService;

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
	IReceptionAchatPersistance receptionAchatPersistance;

	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;

	@Autowired
	private IBonCommandeAchatPersistance bonCommandeAchatPersistance;

	@Autowired
	private IFicheFournisseurDomaine ficheFournisseurDomaine;

	@Autowired
	private ISituationReportingDomaine situationReportingDomaine;
	
	
	
	@Autowired
	private IArticleDomaine articleDomaine;
	@Autowired
	private ISousFamilleArticlePersistance sousFamilleArticlePersistance;

	@Override
	public BonCommandeReportValue getBonCommandeParIdReport(Long id, String typerapport, String avecPrix)
			throws IOException {

		BonCommandeReportValue bonCommandeReport = new BonCommandeReportValue();
		CommandeAchatValue commandeVente = bonCommandeAchatPersistance.getById(id);

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

			bonCommandeReport.setReportStream(
					new FileInputStream("C://ERP/Lib/COM_Achat_BonCommande/bon_commande_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/COM_Achat_BonCommande/bon_commande_sub_report.jasper");

			bonCommandeReport.setParams(params);
		}
		if (typerapport.equals("Devis")) {
			// enrechissement des param du report Devis
			// System.out.println("inn Devis");
			bonCommandeReport
					.setReportStream(new FileInputStream("C://ERP/Lib/COM_Achat_BonCommande/devis_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/COM_Achat_BonCommande/devis_sub_report.jasper");

			bonCommandeReport.setParams(params);
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

	/**
	 * @param bonCommandeReport
	 * @param commandeVente
	 */
	private void enrichissementBonCommande(BonCommandeReportValue bonCommandeReport, CommandeAchatValue commandeVente,
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
		bonCommandeReport.setMontantRemiseTotal(commandeVente.getMontantRemiseTotal());
		bonCommandeReport.setMontantHTTotal(commandeVente.getPrixTotal());
		bonCommandeReport.setMontantTaxes(commandeVente.getMontantTaxe());
		// System.out.println("##### ===>report: mt taxes: " +
		// commandeVente.getMontantTaxe() + " MontantTTC: "+
		// commandeVente.getMontantTTC());

		/*******************/

		List<BonCommandeReportProductValue> listeProduitCommandes = new ArrayList<BonCommandeReportProductValue>();

	//	Map<Long, ArticleValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();
		//

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		for (ProduitCommandeAchatValue produitCommande : commandeVente.getListProduitCommandes()) {
			
			ArticleValue vArticle = articleDomaine.getArticleParId(produitCommande.getProduitId());
			
			

			String referenceProduit = vArticle.getRef();
			String designationProduit =vArticle.getDesignation();

			Long sousFamilleId = vArticle.getSousFamilleId();

			String sousFamilleProduit = null;
			if (sousFamilleId != null) {
				
				SousFamilleArticleValue sousFamille=sousFamilleArticlePersistance.rechercheSousFamilleArticleById(sousFamilleId);
				//SousFamilleProduitValue sousFamille = sousFamilleProduitPersistance.rechercheSousFamilleProduitById(sousFamilleId);
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

			bonCommandeReportElement
					.setTauxTvaArt(produitCommande.getTaxeId().intValue());

			// //// Ajout TVA /////////////
			Double totalProduitCommande = (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			//ProduitValue produitValue = produitService.rechercheProduitById(produitCommande.getProduitId());
			if (!produitTaxeMap.containsKey(produitCommande.getTaxeId())) {

				produitTaxeMap.put(produitCommande.getTaxeId(), totalProduitCommande);

			} else {
				Double assietteValue = produitTaxeMap.get(produitCommande.getTaxeId()) + totalProduitCommande;
				produitTaxeMap.put(produitCommande.getTaxeId(), assietteValue);

			}

			//

			listeProduitCommandes.add(bonCommandeReportElement);

		}

		if (avecPrix.equals("non")) {
			bonCommandeReport.setPrixTotal(0D);
		}

		Map<Long, TaxeCommandeAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeCommandeAchatValue>();

		for (TaxeCommandeAchatValue value : commandeVente.getListProduitTaxes()) {

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

		// //// FIN PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		// TVA Reparti

		/******************************/

		Collections.sort(listeProduitCommandes);
		bonCommandeReport.setListeProduitCommandes(listeProduitCommandes);

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
	public BReceptionReportValue getBonReceptionParIdReport(Long id, String avecPrix, Long typeRapport)
			throws IOException {

		BReceptionReportValue bonReceptionReport = new BReceptionReportValue();

		ReceptionAchatValue receptionVente = receptionAchatPersistance.getById(id);

		// enrechissement des param du report
		bonReceptionReport.setFileName(REPORT_NAME_BONLIVRAISON);

		// rapport sans en tete
		if (typeRapport == 1) {

			bonReceptionReport.setReportStream(
					new FileInputStream("C://ERP/Lib/COMMERCIAL_BonReception/bon_reception_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			params.put("SUBREPORT_INVENTAIRE_PATH",
					"C://ERP/Lib/COMMERCIAL_BonReception/bon_reception_sub_report.jasper");

			bonReceptionReport.setParams(params);
			
			// enrichissement du report
			enrichissementBonReceptionAchat(bonReceptionReport, receptionVente, avecPrix);
			
			

		}

		// rapport avec en tete
		if (typeRapport == 2) {

			bonReceptionReport.setReportStream(
					new FileInputStream("C://ERP/Lib/COMMERCIAL_BonReception/avecEnTete/bon_reception_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			params.put("SUBREPORT_INVENTAIRE_PATH",
					"C://ERP/Lib/COMMERCIAL_BonReception/avecEnTete/bon_reception_sub_report.jasper");

			bonReceptionReport.setParams(params);
			
			// enrichissement du report
			enrichissementBonReceptionAchat(bonReceptionReport, receptionVente, avecPrix);
			enrichirWithBaseInfoInformation(bonReceptionReport);
		}



		ArrayList<BReceptionReportValue> dataList = new ArrayList<BReceptionReportValue>();
		dataList.add(bonReceptionReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		bonReceptionReport.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonReceptionReport;
	}

	
	private void enrichirWithBaseInfoInformation(BReceptionReportValue bonReceptionReportValue) {

		BaseInfoValue baseInfo = baseInfoPersistance.getClientActif();

	/*	if (baseInfo.getPrefixBonLivraison() != null)
			bonLivraisonReportValue
					.setReference(baseInfo.getPrefixBonLivraison() + bonLivraisonReportValue.getReference());
					*/

		bonReceptionReportValue.setAdresseCompagnie(baseInfo.getAdresse());
		bonReceptionReportValue.setCodeTVACompagnie(baseInfo.getCodeTVA());
		bonReceptionReportValue.setVilleCompagnie(baseInfo.getVille());
		bonReceptionReportValue.setTelephoneFixCompagnie(baseInfo.getTelephoneFix());
		bonReceptionReportValue.setTelephoneMoblieCompagnie(baseInfo.getTelephoneMoblie());
		bonReceptionReportValue.setFaxCompagnie(baseInfo.getFax());
		bonReceptionReportValue.setEmailCompagnie(baseInfo.getEmail());
		bonReceptionReportValue.setMatriculeFiscalCompagnie(baseInfo.getMatriculeFiscal());

		bonReceptionReportValue.getParams().put("p_PathLogo", baseInfo.getLogo());
	}
	private void enrichissementBonReceptionAchat(BReceptionReportValue bonReceptionReport,
			ReceptionAchatValue receptionVente, String avecPrix) {

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

		bonReceptionReport.setExistFodec(false);
		bonReceptionReport.setExistTVA(false);
		bonReceptionReport.setExistTimbre(false);

		// Montants params
		bonReceptionReport.setMontantTTC(receptionVente.getMontantTTC());
		bonReceptionReport.setMontantRemiseTotal(receptionVente.getMontantRemise());
		bonReceptionReport.setMontantHTTotal(receptionVente.getMontantHTaxe());
		bonReceptionReport.setMontantTaxes(receptionVente.getMontantTaxe());

		List<BReceptionReportProductValue> listeProduitReceptions = new ArrayList<BReceptionReportProductValue>();

		//Map<Long, ProduitValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		
		

		for (ProduitReceptionAchatValue produitReception : receptionVente.getListProduitReceptions()) {
			
			ArticleValue vArticle = articleDomaine.getArticleParId(produitReception.getProduitId());

			Long produitId = produitReception.getProduitId();

			String referenceProduit = vArticle.getRef();
			String designationProduit = produitReception.getDesignation();

			Long sousFamilleId = vArticle.getSousFamilleId();

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

			// //// Ajout TVA /////////////

			// TODO GASH -10092020
			// Modification - Introduire Remise dans Assiette de TVA
			// Verification Round

			if (produitReception.getRemise() == null)
				produitReception.setRemise(0D);
			Double totalApresRemise = produitReception.getPrixTotalHT() * (1 - produitReception.getRemise() / 100);

			if (!produitTaxeMap.containsKey(produitReception.getTaxeId())) {
				produitTaxeMap.put(produitReception.getTaxeId(), totalApresRemise);

			} else {

				Double assietteValue = produitTaxeMap.get(produitReception.getTaxeId()) + totalApresRemise;
				produitTaxeMap.put(produitReception.getTaxeId(), assietteValue);

			}

			listeProduitReceptions.add(bonReceptionReportElement);

		}

		if (avecPrix.equals("non")) {
			bonReceptionReport.setPrixTotal(0D);
		}

		Map<Long, TaxeReceptionAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeReceptionAchatValue>();

		for (TaxeReceptionAchatValue value : receptionVente.getListTaxeReceptionAchat()) {

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
			bonReceptionReport.setExistFodec(true);

			// FODEC params
			bonReceptionReport.setTaxeFodec(TAXE_FODEC);
			bonReceptionReport.setTauxFodec(taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage());
			bonReceptionReport.setMontantTaxeFodec(taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getMontant());
			bonReceptionReport.setAssietteFodec(bonReceptionReport.getMontantHTTotal());
		}

		// TVA Reparti

		// //// PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA)) {
			bonReceptionReport.setExistTVA(true);

			// TVA params
			bonReceptionReport.setTaxeTVA(TAXE_TVA);
			bonReceptionReport.setTauxTVA(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getPourcentage());
			bonReceptionReport.setMontantTaxeTVA(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getMontant());

			bonReceptionReport.setAssietteTVA(produitTaxeMap.get(TAXE_ID_TVA));

		}
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA7)) {
			bonReceptionReport.setExistTVA7(true);

			// TVA params
			bonReceptionReport.setTaxeTVA7(TAXE_TVA7);
			bonReceptionReport.setTauxTVA7(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA7).getPourcentage());
			bonReceptionReport.setMontantTaxeTVA7(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA7).getMontant());

			bonReceptionReport.setAssietteTVA7(produitTaxeMap.get(TAXE_ID_TVA7));
		}
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA13)) {
			bonReceptionReport.setExistTVA13(true);

			// TVA params
			bonReceptionReport.setTaxeTVA13(TAXE_TVA13);
			bonReceptionReport.setTauxTVA13(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA13).getPourcentage());
			bonReceptionReport.setMontantTaxeTVA13(taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA13).getMontant());

			bonReceptionReport.setAssietteTVA13(produitTaxeMap.get(TAXE_ID_TVA13));
		}

		// //// FIN PARTIE DE TAXE ENTREPRISE COMMERCIALE ////////

		// TVA Reparti

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			bonReceptionReport.setExistTimbre(true);

			// TIMBRE params
			bonReceptionReport.setTaxeTimbre(TAXE_TIMBRE);
			bonReceptionReport.setMontantTaxeTimbre(taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant());
		}

		BigDecimal bigDecimal = new BigDecimal(receptionVente.getMontantTTC());
		BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

		int dinars = bigDecimal.intValue();
		int millimes = fraction.intValue();

		String montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
				+ FrenchNumberToWords.convert(millimes) + MILLIMES;

		bonReceptionReport.setMontantTTCToWords(montantTTCToWords);

		Collections.sort(listeProduitReceptions);
		bonReceptionReport.setListeProduitReceptions(listeProduitReceptions);

	}

	@Override
	public EcheancierReportListValue getListEcheanceReport(RechercheMulticritereDetailReglementAchatValue request)
			throws IOException {
		EcheancierReportListValue echeanceReportList = new EcheancierReportListValue();

		// enrechissement des param du report
		echeanceReportList.setFileName(REPORT_NAME_ECHEANCIER_LIST);
		echeanceReportList.setReportStream(new FileInputStream(
				"C://ERP/Lib/COM_Echeancier_Fournisseur/ListeEcheancier/echeancier_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_ECHEANCIER_PATH",
				"C://ERP/Lib/COM_Echeancier_Fournisseur/ListeEcheancier/echeancier_sub_List_report.jasper");

		echeanceReportList.setParams(params);
		if (request != null) {
			ResultatRechecheDetailReglementAchatValue result = echeancierFournisseurPersistance
					.rechercherMultiCritere(request);
			if (result != null) {
				List<ResultatRechecheDetailReglementAchatElementValue> echeancierList = new ArrayList<ResultatRechecheDetailReglementAchatElementValue>(
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
			List<ResultatRechecheDetailReglementAchatElementValue> echeancierList,
			RechercheMulticritereDetailReglementAchatValue request) {

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
			TypeReglementAchatValue typeReglement = typeReglementPersistance.getById(request.getTypeReglementId());
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
		for (ResultatRechecheDetailReglementAchatElementValue echeancier : echeancierList) {

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
				TypeReglementAchatValue typeReglement = typeReglementPersistance.getById(echeancier.getTypeReglementId());
				if (typeReglement != null) {

					reportElement.setTypeReglement(typeReglement.getDesignation());
				}
			}

			if (echeancier.getReglementId() != null) {
				ReglementAchatValue reglement = reglementAchatPersistance.getById(echeancier.getReglementId());
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

	@Override
	public ReglementReportParRefValue getReglementReportParRef(Long id) throws IOException {
		ReglementAchatValue reglementValue = reglementAchatPersistance.getById(id);

		ReglementReportParRefValue report = new ReglementReportParRefValue();

		report.setFileName(REPORT_NAME_REGLEMENT);
		report.setReportStream(new FileInputStream("C://ERP/Lib/COM_Achat_Reglement/detail/reglement_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");

		params.put("SUBREPORT_INVENTAIRE_PATH",
				"C://ERP/Lib/COM_Achat_Reglement/detail/reglement_details_sub_report.jasper");

		params.put("SUBREPORT_ELEMENTS_PATH",
				"C://ERP/Lib/COM_Achat_Reglement/detail/reglement_elements_sub_report.jasper");

		report.setParams(params);

		// Enrichissement Reglement

		enrichissementGetReglementReportParRef(report, reglementValue);

		ArrayList<ReglementReportParRefValue> dataList = new ArrayList<ReglementReportParRefValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	@Override
	public FicheGroupeClientReportValue getFicheGroupeClientReport(RechercheMulticritereFicheGroupeClientValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FactureAchatReportValue getFactureAchatReportValue(Long id, Long typerapport) throws IOException {

		// System.out.println("====> id: " + id + "---typerapport " + typerapport);
		FactureAchatReportValue factureReport = new FactureAchatReportValue();
		FactureAchatValue factureVente = factureAchatPersistance.getFactureById(id);

		// enrechissement des param du report
		if (typerapport == 2) {
			// rapport sur imp matricielle
			factureReport.setFileName(REPORT_NAME_FACTURE);
			factureReport.setReportStream(new FileInputStream("C://ERP/Lib/COM_Achat_Facture/facture_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (factureVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/COM_Achat_Facture/facture_sub_report.jasper");
			} else if (factureVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/COM_Achat_Facture/facture_facon_sub_report.jasper");
			}

			factureReport.setParams(params);
		} else if (typerapport == 1) {

			// Rapport Basique
			factureReport.setFileName(REPORT_NAME_FACTURE);
			factureReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_FactureVenteBas/facture_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (factureVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_FactureVenteBas/facture_sub_report.jasper");
			} else if (factureVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/STIT_FactureVenteBas/facture_facon_sub_report.jasper");
			}

			factureReport.setParams(params);
		}
		// enrichissement du report
		// enrichmentFactureReport(factureReport, factureVente);
		// enrichmentFactureReportWithBaseInformation(factureReport,factureVente);

		BigDecimal bigDecimal = new BigDecimal(factureVente.getMontantTTC());
		BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

		int dinars = bigDecimal.intValue();
		int millimes = fraction.intValue();

		String montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
				+ FrenchNumberToWords.convert(millimes) + MILLIMES;

		factureReport.setMontantTTCToWords(montantTTCToWords);

		ArrayList<FactureAchatReportValue> dataList = new ArrayList<FactureAchatReportValue>();
		dataList.add(factureReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		factureReport.setJRBeanCollectionDataSourceProduct(jRBeanCollectionDataSource);

		return factureReport;
	}

	private void enrichissementGetReglementReportParRef(ReglementReportParRefValue report,
			ReglementAchatValue reglementValue) {

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
			for (DetailsReglementAchatValue reglementValueElt : reglementValue.getListDetailsReglement()) {

				// System.out.println("-------reglementValueElt-----" +
				// reglementValueElt.toString());

				ReglementReportParRefElementDetailsValue element = new ReglementReportParRefElementDetailsValue();

				element.setBanque(reglementValueElt.getBanque());
				element.setNumPiece(reglementValueElt.getNumPiece());
				element.setMontant(reglementValueElt.getMontant());
				element.setDateEmission(reglementValueElt.getDateEmission());
				element.setDateEcheance(reglementValueElt.getDateEcheance());
				String regle;
				if (reglementValueElt.getRegle()) {

					regle = "Oui";

				} else {

					regle = "Non";
				}
				element.setRegle(regle);

				TypeReglementAchatValue typeReglementValue = typeReglementPersistance
						.getById(reglementValueElt.getTypeReglementId());
				element.setTypeReglement(typeReglementValue.getDesignation());

				listDetailsReportReglement.add(element);
			}

			for (ElementReglementAchatValue reglementValueElt : reglementValue.getListElementReglement()) {

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

	@Override
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementAchatValue request)
			throws IOException {

		ResultatRechecheReglementAchatValue reglementValue = reglementAchatPersistance.rechercherMultiCritere(request);

		ReglementReportValue report = new ReglementReportValue();

		report.setFileName(REPORT_NAME_REGLEMENT);
		report.setReportStream(new FileInputStream("C://ERP/Lib/COM_Achat_Reglement/list/reglement_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");

		params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/COM_Achat_Reglement/list/reglement_sub_report.jasper");

		report.setParams(params);

		// Enrichissement Reglement

		enrichissementReglementReport(report, reglementValue, request);

		ArrayList<ReglementReportValue> dataList = new ArrayList<ReglementReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementReglementReport(ReglementReportValue report, ResultatRechecheReglementAchatValue result,
			RechercheMulticritereReglementAchatValue request) {

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

		for (ResultatRechecheReglementAchatElementValue reglementValue : result.getList()) {

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
		
		bonCommandeReport.getParams().put("p_PathLogo", baseInfo.getLogo());

	}

	@Override
	public BonLivraisonReportValue getBonLivraisonParId(Long id, String avecPrix, Long typerapport) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FactureReportValue getFactureReportValue(Long id, Long typerapport) throws IOException {

		// System.out.println("====> id: " + id + "---typerapport " + typerapport);
		FactureReportValue factureReport = new FactureReportValue();
		FactureAchatValue factureVente = factureAchatPersistance.getFactureById(id);

		// enrechissement des param du report
		if (typerapport == 2) {
			// rapport sur imp matricielle
			factureReport.setFileName(REPORT_NAME_FACTURE);
			factureReport.setReportStream(new FileInputStream("C://ERP/Lib/COM_Achat_Facture/facture_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (factureVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/COM_Achat_Facture/facture_sub_report.jasper");
			} else if (factureVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/COM_Achat_Facture/facture_facon_sub_report.jasper");
			}

			factureReport.setParams(params);

		} else if (typerapport == 3) {

			// rapport sur imp matricielle
			factureReport.setFileName(REPORT_NAME_FACTURE);
			factureReport.setReportStream(
					new FileInputStream("C://ERP/Lib/COM_Achat_Facture/avecEnTete/facture_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logo_commercial.png");

			if (factureVente.getNatureLivraison().equals("FINI")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/COM_Achat_Facture/avecEnTete/facture_sub_report.jasper");
			} else if (factureVente.getNatureLivraison().equals("FACON")) {
				params.put("SUBREPORT_INVENTAIRE_PATH",
						"C://ERP/Lib/COM_Achat_Facture/avecEnTete/facture_facon_sub_report.jasper");
			}

			factureReport.setParams(params);

		}
		// enrichissement du report
		enrichmentFactureReport(factureReport, factureVente);
		enrichmentFactureReportWithBaseInformation(factureReport, factureVente);

		BigDecimal bigDecimal = new BigDecimal(factureVente.getMontantTTC());
		BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(1000));

		int dinars = bigDecimal.intValue();
		int millimes = fraction.intValue();

		String montantTTCToWords = FrenchNumberToWords.convert(dinars) + DINARS + ET
				+ FrenchNumberToWords.convert(millimes) + MILLIMES;

		factureReport.setMontantTTCToWords(montantTTCToWords);

		ArrayList<FactureReportValue> dataList = new ArrayList<FactureReportValue>();
		dataList.add(factureReport);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		factureReport.setJRBeanCollectionDataSourceProduct(jRBeanCollectionDataSource);

		return factureReport;
	}

	/*************** Enrichissement rapport facture **************/

	private void enrichmentFactureReport(FactureReportValue reportValue, FactureAchatValue factureVente) {

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		Long clientId = null;

		if (factureVente.getPartieIntId() != null) {
			clientId = factureVente.getPartieIntId();
			reportValue.setClientId(clientId);
		}

		if (clientIdMap.containsKey(clientId)) {
			reportValue.setClient(clientIdMap.get(clientId).getRaisonSociale());
			reportValue.setMatriculeFiscal(clientIdMap.get(clientId).getMatriculeFiscal());
			reportValue.setAdresse(clientIdMap.get(clientId).getAdresse());
			reportValue.setTelephone(clientIdMap.get(clientId).getTelephone());
			reportValue.setFax(clientIdMap.get(clientId).getFax());
		}
		reportValue.setType(factureVente.getType());
		reportValue.setExistFodec(false);
		reportValue.setExistTVA(false);
		reportValue.setExistTimbre(false);

		reportValue.setClientId(factureVente.getPartieIntId());
		reportValue.setDateBl(factureVente.getDate());
		reportValue.setReference(factureVente.getReference());
		// reportValue.setRaison(factureVente.getRaison());

		// System.out.println("ref: " + factureVente.getReference());

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		// String dat = ""+dateFormat.format(d);

		// System.out.println("##### info : "+factureVente.getInfoLivraison());
		if (factureVente.getInfoLivraison() != null && !factureVente.getInfoLivraison().equals("")
				&& !factureVente.getInfoLivraison().contains("-")) {
			//LivraisonVenteValue livraisonVente = bonLivraisonPersitance.getByReference(factureVente.getInfoLivraison());
			ReceptionAchatValue livraisonVente =  receptionAchatPersistance.getByReference(factureVente.getInfoLivraison());

			String observation = factureVente.getInfoLivraison();

			if (livraisonVente != null) {
				if (livraisonVente.getDateLivraison() != null) {
					String date = "" + dateFormat.format(livraisonVente.getDateLivraison().getTime());
					observation += "  DU  " + date;

				}

			}

			reportValue.setObservations(observation);

		}

		// reportValue.setObservations(factureVente.getObservations());

		// Montants params
		reportValue.setMontantTTC(factureVente.getMontantTTC());
		reportValue.setMontantRemiseTotal(factureVente.getMontantRemise());
		reportValue.setMontantHTTotal(factureVente.getMontantHTaxe());
		reportValue.setMontantTaxes(factureVente.getMontantTaxe());

		List<FactureReportProductValue> productList = new ArrayList<FactureReportProductValue>();
		List<FactureReportTraitementFaconValue> traitementFaconList = new ArrayList<FactureReportTraitementFaconValue>();

		//Map<Long, ProduitValue> produitIdMap = gestionnaireLogistiqueCacheDomaine.mapProduitById();
		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		//
		for (DetFactureAchatValue detFactureVente : factureVente.getListDetFactureAchat()) {
			ArticleValue vArticle = articleDomaine.getArticleParId(detFactureVente.getProduitId());

			if (factureVente.getNatureLivraison().equals("FINI")) {

				// System.out.println("inn fact fini..");
				FactureReportProductValue factureReportProduct = new FactureReportProductValue();
				factureReportProduct.setProduitDesignation(detFactureVente.getProduitDesignation());
				factureReportProduct.setDetLivraisonVenteId(detFactureVente.getId());
				factureReportProduct.setChoix(detFactureVente.getChoix());
				factureReportProduct.setNombreColis(detFactureVente.getNombreColis());
				factureReportProduct.setProduitId(detFactureVente.getProduitId());
				factureReportProduct.setQuantite(detFactureVente.getQuantite());
				factureReportProduct.setRemise(detFactureVente.getRemise());
				factureReportProduct.setUnite(detFactureVente.getUnite());
		
				// factureReportProduct.setMontantTaxeTVA(detFactureVente.getMontanTaxeTVA());
				// System.out.println("MontanTaxeTVA():
				// "+detFactureVente.getMontanTaxeTVA()+"
				// detFactureVente.getQuantite():
				// "+detFactureVente.getQuantite());

				Long produitId = detFactureVente.getProduitId();

				if (vArticle!=null) {

					detFactureVente.setPrixUnitaireHT(detFactureVente.getPrixUnitaireHT());

					Double quantite = (detFactureVente.getQuantite() != null) ? detFactureVente.getQuantite() : ZERO;
					Double prixUHT = (detFactureVente.getPrixUnitaireHT() != null) ? detFactureVente.getPrixUnitaireHT()
							: ZERO;

					Double montantHT = quantite * prixUHT;

					factureReportProduct.setPrixUHT(prixUHT);
					factureReportProduct.setMontantHT(montantHT);

					/*
					 * if(detFactureVente.isSerialisable()) { String numeroSeriesProduitSerialisable
					 * = getNumeroSerieProduitSeriaisableByReferenceBonAndProduitId(factureVente.
					 * getReference(),produitId,IConstanteCommerciale.BON_FACTURE);
					 * factureReportProduct.setProduitDesignation(produitIdMap.get(produitId).
					 * getDesignation()+"("+numeroSeriesProduitSerialisable+")"); }else {
					 * factureReportProduct.setProduitDesignation(produitIdMap.get(produitId).
					 * getDesignation()); }
					 */

					

					// enrichir designation produit avec description detaille
					/*
					 * if(factureVente.getDescription() != null && factureVente.getDescription() ==
					 * true && produitIdMap.get(produitId).getDescription() != null ) { //
					 * System.out.println("INSIDE IF "+produitIdMap.get(produitId).getDescription())
					 * ;
					 * 
					 * factureReportProduct.setProduitDesignation(factureReportProduct.
					 * getProduitDesignation() + "\n"
					 * +produitIdMap.get(produitId).getDescription());
					 * 
					 * }
					 */

					/*
					 * if(detFactureVente.getDescription() != null) {
					 * factureReportProduct.setProduitDesignation(factureReportProduct.
					 * getProduitDesignation() + " " +detFactureVente.getDescription());
					 * 
					 * }
					 */

					factureReportProduct.setProduitCode(vArticle.getRef());
					


					// ajouté 16/03/2018
					factureReportProduct.setTauxTvaArt(detFactureVente.getTaxeId().intValue());

					// //// Ajout TVA /////////////

					if (detFactureVente.getRemise() == null)
						detFactureVente.setRemise(0D);
					Double totalApresRemise = detFactureVente.getPrixTotalHT()
							* (1 - detFactureVente.getRemise() / 100);

					if (!produitTaxeMap.containsKey(detFactureVente.getTaxeId())) {
						produitTaxeMap.put(detFactureVente.getTaxeId(), totalApresRemise);
						// ajouté 16/03/2018
						// produitTaxeMapArt.put(produitIdMap.get(produitId).getIdTaxe(),
						// produitIdMap.get(produitId).getIdTaxe());

					} else {
						Double assietteValue = produitTaxeMap.get(detFactureVente.getTaxeId())
								+ totalApresRemise;
						produitTaxeMap.put(detFactureVente.getTaxeId(), assietteValue);

					}

				}

				productList.add(factureReportProduct);
				Collections.sort(productList, new FactureReportProductComparator());

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

		Map<Long, TaxeFactureAchatValue> taxeFactureIdTaxeMap = new HashMap<Long, TaxeFactureAchatValue>();

		for (TaxeFactureAchatValue value : factureVente.getListTaxeFacture()) {
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

			reportValue.setAssietteTVA(produitTaxeMap.get(TAXE_ID_TVA));

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

		//TIMBRE 1
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			reportValue.setExistTimbre(true);

			// TIMBRE params
			reportValue.setTaxeTimbre(TAXE_TIMBRE);
			reportValue.setMontantTaxeTimbre(taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant());
		}
		
		
		//TIMBRE 2
		
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE_2)) {
			reportValue.setExistTimbre2(true);

			// TIMBRE params
			reportValue.setTaxeTimbre2(TAXE_TIMBRE_2);
			reportValue.setMontantTaxeTimbre2(taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_2).getMontant());
		}
		
		//TIMBRE 3
		
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE_3)) {
			reportValue.setExistTimbre3(true);

			// TIMBRE params
			reportValue.setTaxeTimbre3(TAXE_TIMBRE_3);
			reportValue.setMontantTaxeTimbre3(taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_3).getMontant());
		}

	}

	@Override
	public FactureReportListValue getListFactureReport(RechercheMulticritereFactureValue request) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BonLivraisonReportListValue getListBonlivraisonReport(RechercheMulticritereBonLivraisonValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FicheClientReportValue getFicheClientReport(RechercheMulticritereFicheClientValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoldeClientReportValue getSoldeClientReport(RechercheMulticritereSoldeClientValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReglementReportValue getReglementReport(RechercheMulticritereReglementValue request) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FicheClientReportValue getFicheFournisseurReport(RechercheMulticritereFicheFournisseurValue request)
			throws IOException {

		FicheClientReportValue report = new FicheClientReportValue();

		if (request.getTypeRapport().equals("ficheFournisseur")) {
			/** ficheClient **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_FICHE_FOURNISSEUR);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_FICHE_FOURNISSEUR));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheFournisseurValue result = ficheFournisseurDomaine.rechercherMultiCritere(request);

			enrichmentFicheFournisseurReportList(report, result, request);

			ArrayList<FicheClientReportValue> dataList = new ArrayList<FicheClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		} else if (request.getTypeRapport().equals("releveFournisseur")) {
			/** releveClient **/
			report.setFileName(IConstanteCommercialeReport.REPORT_NAME_RELEVE_FOURNISSEUR);
			report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_RELEVE_FOURNISSEUR));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);

			report.setParams(params);

			ResultatRechecheFicheFournisseurValue result = ficheFournisseurDomaine.rechercherMultiCritere(request);

			enrichmentFicheFournisseurReportList(report, result, request);

			ArrayList<FicheClientReportValue> dataList = new ArrayList<FicheClientReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		}

		return report;
	}

	/********************
	 * Enrichissement rapport fiche fournisseur
	 ***********************/
	private void enrichmentFicheFournisseurReportList(FicheClientReportValue report,
			ResultatRechecheFicheFournisseurValue result, RechercheMulticritereFicheFournisseurValue request) {
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

				report.getListElements().addAll(result.getListElements());
			}
		}

	}

	@Override
	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request, Long solde) throws IOException {

		SituationReportingReportListValue report = new SituationReportingReportListValue();

		report.setFileName(IConstanteCommercialeReport.REPORT_NAME_SITUATION_REPORTING);
		report.setReportStream(
				new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_COM_Achat_SITUATION_REPORTING));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);
		params.put("SUBREPORT_SITUATION_PATH", "C://ERP/Lib/COM_Achat_Situation/situation_sub_report.jasper");

		report.setParams(params);

		// TODO
		ResultatRechecheSituationReportingValue result = situationReportingDomaine.rechercherMultiCritereAchat(request);

		enrichmentSituationReportList(report, result, request, solde);

		ArrayList<SituationReportingReportListValue> dataList = new ArrayList<SituationReportingReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentSituationReportList(SituationReportingReportListValue report,
			ResultatRechecheSituationReportingValue result, RechercheMulticritereSituationReportingValue request,
			Long solde) {

		report.setPartieIntId(request.getPartieIntId());
		if (request.getPartieIntId() != null) {

			PartieInteresseValue client = partieInteresseePersistance.getById(request.getPartieIntId());

			if (client != null) {

				report.setClientAbreviation(client.getAbreviation());
				report.setClientReference(client.getReference());
				if (client.getRegionId() != null) {
					RegionValue region = regionPersistance.getById(client.getRegionId());
					if (region != null) {
						report.setRegionDesignation(region.getDesignation());
					}
				}
			}
		}

		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());

		report.setSoldeMin(request.getSoldeMin());
		report.setSoldeMax(request.getSoldeMax());

		if (solde == 2) {

			// soldeAcuel
			List<SituationReportingValue> listSituation = new ArrayList<SituationReportingValue>();

			if (listSituation != null && !listSituation.isEmpty()) {

				for (SituationReportingValue elt : result.getListSituationReporting()) {

					if (elt.getSoldeActuel() > 0.0) {

						listSituation.add(elt);
						Collections.sort(listSituation, new SituationReportingValueComparator());

					}

				}
			}

			report.getListSituation().addAll(listSituation);

		} else {

			report.getListSituation().addAll(result.getListSituationReporting());
		}
	}

	private void enrichmentFactureReportWithBaseInformation(FactureReportValue factureReport,
			FactureAchatValue factureVente) {

		BaseInfoValue baseInfo = baseInfoPersistance.getClientActif();

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

	@Override
	public BLReportElementRecapValue getDepenseBRbyMonth(RechercheMulticritereBonReceptionAchatValue request) {

		return receptionAchatPersistance.getDepenseBRbyMonth(request);
	}

	@Override
	public EcheancierReportListValue getListEcheanceInverseReport(
			RechercheMulticritereDetailReglementAchatValue request) throws IOException {

		EcheancierReportListValue echeanceReportList = new EcheancierReportListValue();

		// enrechissement des param du report
		echeanceReportList.setFileName(REPORT_NAME_ECHEANCIER_LIST);
		echeanceReportList.setReportStream(new FileInputStream(
				"C://ERP/Lib/COM_Echeancier_Fournisseur/ListeEcheancier/echeancier_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_ECHEANCIER_PATH",
				"C://ERP/Lib/COM_Echeancier_Fournisseur/ListeEcheancier/echeancier_sub_List_report.jasper");

		echeanceReportList.setParams(params);
		if (request != null) {
			ResultatRechecheDetailReglementAchatValue result = echeancierInverseFournisseurPersistance
					.rechercherMultiCritere(request);
			if (result != null) {
				List<ResultatRechecheDetailReglementAchatElementValue> echeancierList = new ArrayList<ResultatRechecheDetailReglementAchatElementValue>(
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
			List<ResultatRechecheDetailReglementAchatElementValue> echeancierList,
			RechercheMulticritereDetailReglementAchatValue request) {

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
			TypeReglementAchatValue typeReglement = typeReglementPersistance.getById(request.getTypeReglementId());
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
		for (ResultatRechecheDetailReglementAchatElementValue echeancier : echeancierList) {

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

			if (echeancier.getTypeReglementId() != null) {
				TypeReglementAchatValue typeReglement = typeReglementPersistance.getById(echeancier.getTypeReglementId());
				if (typeReglement != null) {

					reportElement.setTypeReglement(typeReglement.getDesignation());
				}
			}

			if (echeancier.getReglementId() != null) {
				ReglementAchatValue reglement = reglementInverseAchatPersistance.getById(echeancier.getReglementId());
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
