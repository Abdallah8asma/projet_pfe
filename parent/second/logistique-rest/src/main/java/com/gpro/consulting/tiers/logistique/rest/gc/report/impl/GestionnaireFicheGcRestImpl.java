package com.gpro.consulting.tiers.logistique.rest.gc.report.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;
import com.gpro.consulting.tiers.commun.service.elementBase.IBoutiqueService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitSerialisableService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IDeviseService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IRegionService;
import com.gpro.consulting.tiers.commun.service.utils.IUtilsService;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value.EcheancierReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.mouvementproduit.MouvementProduitElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.mouvementproduit.MouvementProduitReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.rest.gc.achat.impl.DetFactureAchatRestImpl;
import com.gpro.consulting.tiers.logistique.rest.gc.vente.facture.impl.DetFactureVenteRestImpl;
import com.gpro.consulting.tiers.logistique.rest.report.utilities.ExcelUtils;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IDetFactureAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IProduitReceptionAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IBonCommandeService;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IProduitCommandeService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.ITaxeService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IDetailsReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IElementReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService;
import com.gpro.consulting.tiers.logistique.service.gc.report.reporting.IGestionnaireReportGcReportingService;
import com.gpro.consulting.tiers.logistique.service.gc.typeReglement.ITypeReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IDetFactureVenteService;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IFactureService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;
import com.gpro.consulting.tiers.logistique.service.produitdepot.IProduitDepotService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Gestionnaire de reporting
 * 
 * @author Samer Hassen
 * @since 24/07/2019
 *
 */

@Controller
@RequestMapping("/fiches")
@SuppressWarnings("static-access")
public class GestionnaireFicheGcRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireFicheGcRestImpl.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_CALENDAR_FORMAT = "EEE MMM dd yyyy";

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static final SimpleDateFormat dateTimeFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private String excel_file_location = "C:\\ERP\\Excel\\";
	
	
	@Autowired
	private IProduitService produitService;

	@Autowired
	private IGestionnaireReportGcService gestionnaireReportGcService;

	@Autowired
	private IMarcheService marcheService;

	@Autowired
	private IModePaiementService modePaiementService;



	@Autowired
	private IPartieInteresseeService partieInteresseeService;

	@Autowired
	private IElementReglementService elementReglementService;
	
	@Autowired
	private IDetailsReglementService detailsReglementService;

	@Autowired
	private IGroupeClientService groupeClientService;

	@Autowired
	private IProduitDepotService produitDepotService;

	@Autowired
	private IMagasinService magasinService;

	@Autowired
	private IProduitReceptionAchatService produitReceptionAchatService;
	
	@Autowired
	private IUtilsService utilsService;
	
	@Autowired
	private IProduitSerialisableService produitSerialisableService;
	
	@Autowired
	private IBoutiqueService boutiqueService;
	
	
	@Autowired
	private IBonCommandeService bonCommandeService;
	
	
	@Autowired
	private IBaseInfoService baseInfoService;
	
	
	@Autowired
	private IFactureService factureService;
	
	
	@Autowired
	private ITaxeService taxeService;
	
	@Autowired
	private ITypeReglementService typeReglementService;
	
	

	@Autowired
	private IDeviseService deviseService;
	
	@Autowired
	private IGestionnaireReportGcReportingService gestionnaireReportGcReportingService;
	
	
	@Autowired
	private IRegionService regionService;
	@Autowired
	IDetFactureVenteService detFactureVenteService;
	@Autowired
	IDetFactureAchatService detFactureAchatService;
	@Autowired
	IProduitCommandeService produitCommandeService;
	
	
	@RequestMapping(value = "/listCommandesVente", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generatelistCommandesVenteReport(

			@RequestBody RechercheMulticritereBonCommandeValue request

	) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+"Liste_Commandes_Vente_" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		
			Equilibrageworkbook.createSheet("Liste_Commandes_Vente_", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);
		sheet3.setColumnView(3, 20);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 15);
		sheet3.setColumnView(9, 15);
		sheet3.setColumnView(10, 15);
		
		/**************************************************************************/

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

	 
			sheet3.addCell(new Label(2, 7, "    VENTE-Liste des Commandes", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 9, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getReference()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getReference(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		if (isNotEmty(request.getPartieInteresseId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(Long.parseLong(request.getPartieInteresseId()));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getLivre()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Livré :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getLivre(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		
		
		
		if (isNotEmty(request.getDateIntroductionDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date CMD De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateIntroductionDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateIntroductionDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date CMD A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateIntroductionDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateLivraisonDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateLivraisonA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		 
		
		
		
		if (isNotEmty(request.getQuantiteDu()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getQuantiteA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteA().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		
		if (isNotEmty(request.getCoutDu()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getCoutDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getCoutA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getCoutDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getDevise()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getDevise().toString(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}

		/*
		 * if (isNotEmty(request.getBoutiqueId()))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Boutique :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, request.getPrixMin() + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * 
		 * }
		 */

		//request.setOptimized(this.checkForOptimization(request));
		
		 
		
		ResultatRechecheBonCommandeValue result = bonCommandeService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date cmd", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date Liv.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Devise", ExcelUtils.boldRed2));




	  if(result.getListCommandeVente()!=null && result.getListCommandeVente().size()>0) {
	    	 
		
		for (CommandeVenteValue element : result.getListCommandeVente()) {
			
			if(element.getReference()!=null) {
				
				 
				sheet3.addCell(new Label(2, i, element.getReference()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getPartieIntersseAbbreviation()!=null) {
				
				 
				sheet3.addCell(new Label(3, i, element.getPartieIntersseAbbreviation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				 

			}
			
			if (element.getDateIntroduction() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDateIntroduction().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				
				
				
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
				 

			}

			if (element.getDateLivraison() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDateLivraison().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getQuantite()!=null) {
				
				 
				sheet3.addCell(new Label(6, i, element.getQuantite()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getMontantHTaxe()!=null) {
				
				 
				sheet3.addCell(new Label(7, i, element.getMontantHTaxe()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getMontantTaxe()!=null) {
				
				 
				sheet3.addCell(new Label(8, i, element.getMontantTaxe()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getMontantTTC()!=null) {
				
				 
				sheet3.addCell(new Label(9, i, element.getMontantTTC()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getDevise()!=null) {
				
				 
				sheet3.addCell(new Label(10, i, element.getDevise()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
				 

			}
			
			 
			








 

			i++;

		}
	}
		i++;
		i++;

		/*
		 * sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(7, i, 9, i);
		 * 
		 * i++;
		 */

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream"));

		Date now = new Date();
		String dateFormat1 = dateFormat.format(now);
		String filename = "Vente-Liste-commandes_" + dateFormat1 + ".xls";
		// String filename = "sortie-stock_" + dateFormat1 ;
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(fileOut.toByteArray(), headers, HttpStatus.OK);
		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		/*
		 * // context.getExternalContext().getResponse();
		 * response.setHeader("Content-disposition", "attachment; filename=" +
		 * f.getName()); // System.out.println("nom du fichier" + f.getName());
		 * response.setContentType("application/vnd.ms-excel"); int bufferSize = 64 *
		 * 1024;
		 */

	}

	
	
	
	
	
	@RequestMapping(value = "/listCommandesVenteDevise", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generatelistCommandesVenteReportdevise(

			@RequestBody RechercheMulticritereBonCommandeValue request

	) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+"Liste_Commandes_Vente_" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

			Equilibrageworkbook.createSheet("Liste_Commandes_Vente_", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);
		sheet3.setColumnView(3, 20);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 15);
		sheet3.setColumnView(9, 15);
		sheet3.setColumnView(10, 15);
		
		/**************************************************************************/


		ExcelUtils.init();

	

	 
			sheet3.addCell(new Label(2, 7, "    VENTE-Liste des Commandes", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 9, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getReference()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getReference(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		if (isNotEmty(request.getPartieInteresseId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(Long.parseLong(request.getPartieInteresseId()));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getLivre()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Livré :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getLivre(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		
		
		
		if (isNotEmty(request.getDateIntroductionDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date CMD De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateIntroductionDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateIntroductionDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date CMD A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateIntroductionDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateLivraisonDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateLivraisonA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		 
		
		
		
		if (isNotEmty(request.getQuantiteDu()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getQuantiteA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteA().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		
		if (isNotEmty(request.getCoutDu()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getCoutDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getCoutA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getCoutDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getDevise()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getDevise().toString(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}

		
		
		ResultatRechecheBonCommandeValue result = bonCommandeService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date cmd", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date Liv.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Devise", ExcelUtils.boldRed2));




	  if(result.getListCommandeVente()!=null && result.getListCommandeVente().size()>0) {
	    	 
		
		for (CommandeVenteValue element : result.getListCommandeVente()) {
			
			if(element.getReference()!=null) {
				
				 
				sheet3.addCell(new Label(2, i, element.getReference()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getPartieIntersseAbbreviation()!=null) {
				
				 
				sheet3.addCell(new Label(3, i, element.getPartieIntersseAbbreviation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				 

			}
			
			if (element.getDateIntroduction() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDateIntroduction().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				
				
				
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
				 

			}

			if (element.getDateLivraison() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDateLivraison().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getQuantite()!=null) {
				
				 
				sheet3.addCell(new Label(6, i, element.getQuantite()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getMontantHTaxe()!=null) {
				
				 
				sheet3.addCell(new Label(7, i,(double) Math.round(element.getMontantHTaxe() * 100) / 100 + "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getMontantTaxe()!=null) {
				
				 
				sheet3.addCell(new Label(8, i, (double) Math.round(element.getMontantTaxe() * 100) / 100 + "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getMontantTTC()!=null) {
				
				 
				sheet3.addCell(new Label(9, i, (double) Math.round(element.getMontantTTC() * 100) / 100 + "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getDevise()!=null) {
				
				 
				sheet3.addCell(new Label(10, i, element.getDevise()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
				 

			}
			
			 
			i++;

		}
	}
		i++;
		i++;


		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream"));

		Date now = new Date();
		String dateFormat1 = dateFormat.format(now);
		String filename = "Vente-Liste-commandes_" + dateFormat1 + ".xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(fileOut.toByteArray(), headers, HttpStatus.OK);
	

	}

	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/listProduitSerialisable", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListPoduitSerialisableReport(

			@RequestBody RechercheMulticritereProduitSerialisableValue request

	) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
			excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Produit_Sérialisable_" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		
			Equilibrageworkbook.createSheet("Produit_Sérialisable_", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(32, 18);
		sheet3.setColumnView(34, 20);

		/**************************************************************************/

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

	 
			sheet3.addCell(new Label(2, 7, "    Liste des Articles Sérialisables", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 20, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produitService.rechercheProduitById(request.getProduitId()).getReference(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		if (isNotEmty(request.getFournisseurId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(request.getFournisseurId());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getDateAchatDe()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Achat De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateAchatDe().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateAchatA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Achat A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateAchatA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateVenteDe()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Vente De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateVenteDe().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateVenteA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Vente A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateVenteA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateFinGarantieDe()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date F.Garantie De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateFinGarantieDe().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateFinGarantieA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date F.Garantie A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateFinGarantieA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getNumSerie()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Num. Série :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getNumSerie(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		
		
		if (isNotEmty(request.getPartieInteressee()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,partieInteresseeService.getById(request.getPartieInteressee()).getAbreviation() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		if (isNotEmty(request.getCritereSpeciale()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Autres critère :", ExcelUtils.boldRed3));
			
			 
			
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,getCritereSpecialProduitSerialisableForRepport(request.getCritereSpeciale()) , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		  

		/*
		 * if (isNotEmty(request.getBoutiqueId()))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Boutique :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, request.getPrixMin() + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * 
		 * }
		 */

		//request.setOptimized(this.checkForOptimization(request));
		
		 
		
		ResultatRechecheProduitSerialisableValue resultProduitSerialisable = produitSerialisableService.rechercherProduitSerialisableMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Boutique", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Magasin", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i - 1, 4, i - 1);
		sheet3.addCell(new Label(5, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.mergeCells(5, i - 1, 6, i - 1);
		sheet3.addCell(new Label(7, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i - 1, 9, i - 1);
		
		sheet3.addCell(new Label(10, i - 1, "N° Série", ExcelUtils.boldRed2));

		sheet3.mergeCells(10, i - 1, 11, i - 1);
		sheet3.addCell(new Label(12, i - 1, "N° BR", ExcelUtils.boldRed2));
		sheet3.mergeCells(12, i - 1, 13, i - 1);

		sheet3.addCell(new Label(14, i - 1, "Date Achat", ExcelUtils.boldRed2));
		sheet3.mergeCells(14, i - 1, 15, i - 1);

		sheet3.addCell(new Label(16, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.mergeCells(16, i - 1, 17, i - 1);

		sheet3.addCell(new Label(18, i - 1, "N° BL", ExcelUtils.boldRed2));
		sheet3.mergeCells(18, i - 1, 19, i - 1);

		sheet3.addCell(new Label(20, i - 1, "Date Vente", ExcelUtils.boldRed2));
		sheet3.mergeCells(20, i - 1, 21, i - 1);


		sheet3.addCell(new Label(22, i - 1, "N° Facture", ExcelUtils.boldRed2));
		sheet3.mergeCells(22, i - 1, 23, i - 1);

		sheet3.addCell(new Label(24, i - 1, "Date Facture", ExcelUtils.boldRed2));
		sheet3.mergeCells(24, i - 1, 25, i - 1);

		sheet3.addCell(new Label(26, i - 1, "Prix Vente", ExcelUtils.boldRed2));
		sheet3.mergeCells(26, i - 1, 27, i - 1);

		sheet3.addCell(new Label(28, i - 1, "Date F.Garantie", ExcelUtils.boldRed2));
		sheet3.mergeCells(28, i - 1, 29, i - 1);

		
		sheet3.addCell(new Label(30, i - 1, "N° Avoir", ExcelUtils.boldRed2));
		sheet3.mergeCells(30, i - 1, 31, i - 1);

		sheet3.addCell(new Label(32, i - 1, "Hist. BT. Sort.", ExcelUtils.boldRed2));
		sheet3.mergeCells(32, i - 1, 33, i - 1);

		sheet3.addCell(new Label(34, i - 1, "Hist. BT. Réc.", ExcelUtils.boldRed2));
		sheet3.mergeCells(34, i - 1, 35, i - 1);
		




	  if(resultProduitSerialisable.getProduitSerialisableValues()!=null && resultProduitSerialisable.getProduitSerialisableValues().size()>0) {
	    	 
		
		for (ProduitSerialisableValue element : resultProduitSerialisable.getProduitSerialisableValues()) {
			
			if(element.getBoutiqueId()!=null) {
				
				BoutiqueValue boutique=new BoutiqueValue();
				boutique.setId(element.getBoutiqueId());
				
				sheet3.addCell(new Label(2, i, boutiqueService.rechercheBoutiqueParId(boutique).getAbreviation() + "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getMagasinId()!=null) {
				
				MagasinValue magasin=new MagasinValue();
				magasin.setId(element.getMagasinId());
				
				sheet3.addCell(new Label(3, i, magasinService.rechercheMagasinParId(magasin).getDesignation() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i , 4, i );
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i , 4, i );

				 

			}
			
			if(element.getProduitId()!=null) {
				
				ProduitValue produit=produitService.rechercheProduitById(element.getProduitId());
				
				
				sheet3.addCell(new Label(5, i, produit.getReference() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(5, i , 6, i );

				sheet3.addCell(new Label(7, i, produit.getDesignation() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(7, i , 9, i );

				 
				

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(5, i , 6, i );

				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(7, i , 9, i );

				 

			}
			
			if(element.getNumSerie()!=null) {
				
				 
				sheet3.addCell(new Label(10, i, element.getNumSerie() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(10, i , 11, i );

				 
				

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(10, i , 11, i );

				 

			}

			if(element.getNumBonReception()!=null) {
				
				 
				sheet3.addCell(new Label(12, i, element.getNumBonReception() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(12, i , 13, i );

				 
				

			} else {
				sheet3.addCell(new Label(12, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(12, i , 13, i );

				 

			}
			
			if (element.getDateAchat() != null) {
				sheet3.addCell(
						new Label(14, i, dateFormat2.format(element.getDateAchat().getTime()) + "", ExcelUtils.boldRed));
				sheet3.mergeCells(14, i , 15, i );


			} else {
				sheet3.addCell(new Label(14, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(14, i , 15, i );

			}
			
			
			
			if (element.getPartieIntersseDesignation() != null) {
				sheet3.addCell(
						new Label(16, i, element.getPartieIntersseDesignation() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(16, i , 17, i );


			} else {
				sheet3.addCell(new Label(16, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(16, i , 17, i );

			}
			
			if (element.getNumBonLivraison() != null) {
				sheet3.addCell(
						new Label(18, i, element.getNumBonLivraison() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(18, i , 19, i );


			} else {
				sheet3.addCell(new Label(18, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(18, i , 19, i );

			}
			
			if (element.getDateVente() != null) {
				sheet3.addCell(
						new Label(20, i, dateFormat2.format(element.getDateVente().getTime()) + "", ExcelUtils.boldRed));
				sheet3.mergeCells(20, i , 21, i );


			} else {
				sheet3.addCell(new Label(20, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(20, i , 21, i );

			}
			
			
			if (element.getNumFacture() != null) {
				sheet3.addCell(
						new Label(22, i, element.getNumFacture() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(22, i , 23, i );


			} else {
				sheet3.addCell(new Label(22, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(22, i , 23, i );

			}
			
			if (element.getDateFacture() != null) {
				sheet3.addCell(
						new Label(24, i, dateFormat2.format(element.getDateFacture().getTime()) + "", ExcelUtils.boldRed));
				sheet3.mergeCells(24, i , 25, i );


			} else {
				sheet3.addCell(new Label(24, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(24, i , 25, i );

			}
			
			if (element.getPrixVente() != null) {
				sheet3.addCell(
						new Label(26, i, element.getPrixVente() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(26, i , 27, i );


			} else {
				sheet3.addCell(new Label(26, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(26, i , 27, i );

			}
			
			
			if (element.getDateFinGarantie() != null) {
				sheet3.addCell(
						new Label(28, i, dateFormat2.format(element.getDateFinGarantie().getTime()) + "", ExcelUtils.boldRed));
				sheet3.mergeCells(28, i , 29, i );


			} else {
				sheet3.addCell(new Label(28, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(28, i , 29, i );

			}
			
			if (element.getNumFactureAvoir() != null) {
				sheet3.addCell(
						new Label(30, i, element.getNumFactureAvoir() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(30, i , 31, i );


			} else {
				sheet3.addCell(new Label(30, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(30, i , 31, i );

			}
			
			if (element.getHistoriqueBTsortie() != null) {
				sheet3.addCell(
						new Label(32, i, element.getHistoriqueBTsortie() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(32, i , 33, i );


			} else {
				sheet3.addCell(new Label(32, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(32, i , 33, i );

			}
			
			if (element.getHistoriqueBTreception() != null) {
				sheet3.addCell(
						new Label(34, i, element.getHistoriqueBTreception() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(34, i , 35, i );


			} else {
				sheet3.addCell(new Label(34, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(34, i , 35, i );

			}
			








 

			i++;

		}
	}
		i++;
		i++;

		/*
		 * sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(7, i, 9, i);
		 * 
		 * i++;
		 */

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream"));

		Date now = new Date();
		String dateFormat1 = dateFormat.format(now);
		String filename = "Prosuits-Sérialisables_" + dateFormat1 + ".xls";
		// String filename = "sortie-stock_" + dateFormat1 ;
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(fileOut.toByteArray(), headers, HttpStatus.OK);
		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		/*
		 * // context.getExternalContext().getResponse();
		 * response.setHeader("Content-disposition", "attachment; filename=" +
		 * f.getName()); // System.out.println("nom du fichier" + f.getName());
		 * response.setContentType("application/vnd.ms-excel"); int bufferSize = 64 *
		 * 1024;
		 */

	}

	@RequestMapping(value = "/bonlivraison", method = RequestMethod.GET)
	public void genererBonLivraisonReport(@RequestParam("id") Long id,
			@RequestParam(value = "avecPrix", required = false, defaultValue = "oui") String avecPrix,
			@RequestParam("typerapport") Long typerapport, @RequestParam("type") String type,
			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService.getBonLivraisonReportValue(id,
				avecPrix, typerapport);

		if (bonLivraisonReport.getMarcheId() != null) {

			Map<Long, MarcheValue> mapMarcheById = new HashMap<Long, MarcheValue>();
			List<MarcheValue> marcheList = marcheService.getAll();

			for (MarcheValue marche : marcheList) {
				Long key = marche.getId();
				if (mapMarcheById.get(key) == null) {
					mapMarcheById.put(marche.getId(), marche);
				}
			}

			MarcheValue marche = mapMarcheById.get(bonLivraisonReport.getMarcheId());

			if (marche != null) {
				bonLivraisonReport.setMarche(marche.getDesignation());
			}
		}

		if (bonLivraisonReport.getModepaiementId() != null) {

			Map<Long, ModePaiementValue> mapModePaiementById = new HashMap<Long, ModePaiementValue>();
			List<ModePaiementValue> modePaiementList = modePaiementService.getAll();

			for (ModePaiementValue marche : modePaiementList) {
				Long key = marche.getId();
				if (mapModePaiementById.get(key) == null) {
					mapModePaiementById.put(marche.getId(), marche);
				}
			}

			ModePaiementValue modePaiement = mapModePaiementById.get(bonLivraisonReport.getModepaiementId());

			if (modePaiement != null) {
				bonLivraisonReport.setModepaiement(modePaiement.getDesignation());
			}
		}

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
				excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Bon_de_livraison" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("BL", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 12);

		sheet3.setColumnView(3, 10);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 10);

		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(
				new Label(2, 7, "    Bon de Livraison : " + bonLivraisonReport.getReference(), ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(), ExcelUtils.boldRed));
		sheet3.mergeCells(3, 10, 4, 10);

		sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		sheet3.mergeCells(3, 11, 4, 12);
		sheet3.addCell(new Label(3, 11, bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));

		sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		sheet3.mergeCells(7, 10, 8, 10);
		sheet3.addCell(new Label(9, 10, bonLivraisonReport.getReference(), ExcelUtils.boldRed));

		sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		sheet3.mergeCells(7, 11, 8, 11);
		// sheet3.addCell(new Label(9, 11, dateFormat2.format(d), ExcelUtils.boldRed));

		sheet3.addCell(
				new Label(9, 11, dateFormat2.format(bonLivraisonReport.getDateBl().getTime()), ExcelUtils.boldRed));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 1;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i - 1, 5, i - 1);

		sheet3.addCell(new Label(6, i - 1, "Quantite", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "PUHT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant HT", ExcelUtils.boldRed2));

		for (BonLivraisonReportProductValue element : bonLivraisonReport.getProductList()) {

			if (element.getProduitCode() != null) {
				sheet3.addCell(new Label(2, i, element.getProduitCode() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(3, i, element.getProduitDesignation() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i, 5, i);

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i, 5, i);
			}

			// QTE

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getQuantite(), 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// PUHT

			if (element.getPrixUHT() != null) {
				sheet3.addCell(new Label(7, i, convertisseur(element.getPrixUHT(), 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// remise

			if (element.getRemise() != null) {
				sheet3.addCell(
						new Label(8, i, convertisseur(element.getRemise(), 3).toString().toString().concat(" %") + "",
								ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, " 0 %", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHT() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMontantHT(), 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(2, i, "Taxes", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i, "Taux", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i, "Assiette", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i, "Montant", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(7, i, "Montant Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i, "Montant Taxes", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i, "Montant HT", ExcelUtils.boldRed2));

		if (bonLivraisonReport.getMontantRemiseTotal() != null) {
			sheet3.addCell(new Label(7, i + 1, convertisseur(bonLivraisonReport.getMontantRemiseTotal(), 3) + "",
					ExcelUtils.boldRed));

		} else {
			sheet3.addCell(new Label(7, i + 1, "", ExcelUtils.boldRed));
		}

		if (bonLivraisonReport.getMontantTaxes() != null) {
			sheet3.addCell(new Label(8, i + 1, convertisseur(bonLivraisonReport.getMontantTaxes(), 3) + "",
					ExcelUtils.boldRed));

		} else {
			sheet3.addCell(new Label(8, i + 1, "", ExcelUtils.boldRed));
		}

		if (bonLivraisonReport.getMontantHTTotal() != null) {
			sheet3.addCell(new Label(9, i + 1, convertisseur(bonLivraisonReport.getMontantHTTotal(), 3) + "",
					ExcelUtils.boldRed));

		} else {
			sheet3.addCell(new Label(9, i + 1, "", ExcelUtils.boldRed));
		}

		if (bonLivraisonReport.getExistFodec()) {
			i++;

			if (bonLivraisonReport.getTaxeFodec() != null) {
				sheet3.addCell(
						new Label(2, i, bonLivraisonReport.getTaxeFodec().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getTauxFodec() != null) {
				sheet3.addCell(new Label(3, i, bonLivraisonReport.getTauxFodec().toString().concat(" %") + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getAssietteFodec() != null) {
				sheet3.addCell(new Label(4, i, bonLivraisonReport.getAssietteFodec() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getMontantTaxeFodec() != null) {
				sheet3.addCell(new Label(5, i, bonLivraisonReport.getMontantTaxeFodec() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		if (bonLivraisonReport.getExistTVA7()) {
			i++;

			if (bonLivraisonReport.getTaxeTVA7() != null) {
				sheet3.addCell(
						new Label(2, i, bonLivraisonReport.getTaxeTVA7().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getTauxTVA7() != null) {
				sheet3.addCell(new Label(3, i, bonLivraisonReport.getTauxTVA7().toString().concat(" %") + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getAssietteTVA7() != null) {
				sheet3.addCell(new Label(4, i, bonLivraisonReport.getAssietteTVA7() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getMontantTaxeTVA7() != null) {
				sheet3.addCell(new Label(5, i, bonLivraisonReport.getMontantTaxeTVA7() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		if (bonLivraisonReport.getExistTVA13()) {
			i++;

			if (bonLivraisonReport.getTaxeTVA13() != null) {
				sheet3.addCell(
						new Label(2, i, bonLivraisonReport.getTaxeTVA13().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getTauxTVA13() != null) {
				sheet3.addCell(new Label(3, i, bonLivraisonReport.getTauxTVA13().toString().concat(" %") + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getAssietteTVA13() != null) {
				sheet3.addCell(new Label(4, i, bonLivraisonReport.getAssietteTVA13() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getMontantTaxeTVA13() != null) {
				sheet3.addCell(new Label(5, i, bonLivraisonReport.getMontantTaxeTVA13() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		if (bonLivraisonReport.getExistTVA()) {
			i++;

			if (bonLivraisonReport.getTaxeTVA() != null) {
				sheet3.addCell(new Label(2, i, bonLivraisonReport.getTaxeTVA().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getTauxTVA() != null) {
				sheet3.addCell(new Label(3, i, bonLivraisonReport.getTauxTVA().toString().concat(" %") + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getAssietteTVA() != null) {
				sheet3.addCell(new Label(4, i, bonLivraisonReport.getAssietteTVA() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (bonLivraisonReport.getMontantTaxeTVA() != null) {
				sheet3.addCell(new Label(5, i, bonLivraisonReport.getMontantTaxeTVA() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		i++;

		/*
		 * sheet3.addCell(new Label(2, i + 1, "TOTAUX", boldRed2)); sheet3.mergeCells(2,
		 * i + 1, 4, i + 1);
		 * 
		 * sheet3.addCell(new Label(5, i + 1, bonLivraisonReport.getQteTotaleCh1() + "",
		 * boldRed3)); sheet3.addCell(new Label(6, i + 1,
		 * bonLivraisonReport.getQteTotaleCh2() + "", boldRed3));
		 * 
		 * Long totalQte = bonLivraisonReport.getQteTotaleCh1() +
		 * bonLivraisonReport.getQteTotaleCh2(); sheet3.addCell(new Label(7, i + 1,
		 * totalQte + "", boldRed3));
		 * 
		 * 
		 */

		sheet3.addCell(new Label(7, i + 1, "Montant TTC", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i + 1, 8, i + 1);
		sheet3.addCell(new Label(9, i + 1, convertisseur(bonLivraisonReport.getMontantHTTotal(), 3) + " €",
				ExcelUtils.boldRed2));

		/*
		 * sheet3.addCell(new Label(2, i + 3,
		 * "Arrêté le présent Bon de livraison à la somme de : ",
		 * ExcelUtils.ARIAL_10_NO_BOLD_LEFT)); sheet3.mergeCells(2, i + 3, 4, i + 3);
		 */

		sheet3.addCell(new Label(2, i + 3, "Arrêté le présent Bon de livraison à la somme de : ",
				ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(2, i + 3, 9, i + 3);

		sheet3.addCell(new Label(2, i + 4, bonLivraisonReport.getMontantTTCToWords().toUpperCase(),
				ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(2, i + 4, 9, i + 4);

		// sheet3.addCell(new Label(2, i + 5, "Valeur en votre aimable règlement net 15
		// jours date facture ", ExcelUtils.ARIAL_10_NO_BOLD_LEFT));
		// sheet3.mergeCells(2, i + 5, 7, i + 5);

		sheet3.addCell(new Label(2, i + 6, "Signature Client" + "", ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(2, i + 6, 4, i + 6);

		sheet3.addCell(new Label(2, i + 7, "", ExcelUtils.boldRed2));
		sheet3.mergeCells(2, i + 7, 4, i + 9);

		// sheet3.addCell(new Label(2, i + 6, "Montant Réglé" + "",
		// ExcelUtils.boldRed3));
		// sheet3.mergeCells(2, i + 6, 3, i + 6);

		sheet3.addCell(new Label(7, i + 6, "Signature Responsable", ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(7, i + 6, 9, i + 6);

		sheet3.addCell(new Label(7, i + 7, "", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i + 7, 9, i + 9);

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}

	@RequestMapping(value = "/listbonlivraison", method = RequestMethod.GET)
	public void generateListBonlivraisonReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereBonLivraisonValue
			// request,
			@RequestParam("referenceBl") String referenceBl, @RequestParam("referenceBs") String referenceBs,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateLivraisonMin") String dateLivraisonMin,
			@RequestParam("dateLivraisonMax") String dateLivraisonMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, 
			//@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("avecFacture") String avecFacture,
			//@RequestParam("stock") Boolean stock,
			//@RequestParam("idDepot") Long idDepot, 
			//@RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("devise") Long devise,
			@RequestParam("declare") String declare,
			
			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+ "BL_LISTE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("BL-LIST", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 10);

		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		sheet3.setColumnView(10, 10);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des Bons de Livraisons", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereBonLivraisonValue request = new RechercheMulticritereBonLivraisonValue();


		request.setDeclare(declare);
		//request.setNatureLivraison(natureLivraison);

		//request.setStock(stock);
		//request.setIdDepot(idDepot);
       
	
		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReferenceBl(referenceBl);
			
		}

	/*	if (isNotEmty(groupeClientId))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(
					numColCritRech + 1, numLigneCritRech, groupeClientService
							.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}
*/
		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}

		if (isNotEmty(dateLivraisonMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMin, ExcelUtils.boldRed3));

			request.setDateLivraisonMin(stringToCalendar(dateLivraisonMin));
		}

		if (isNotEmty(dateLivraisonMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMax, ExcelUtils.boldRed3));

			request.setDateLivraisonMax(stringToCalendar(dateLivraisonMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setMetrageMax(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			request.setPrixMin(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setPrixMax(prixMax);
		}

		if (isNotEmty(avecFacture))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "A facturer :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, avecFacture + "", ExcelUtils.boldRed3));

			request.setAvecFacture(avecFacture);
		}
		if (isNotEmty(devise))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, devise.toString() +"", ExcelUtils.boldRed3));

			request.setDevise(devise);
			
		}
		request.setOptimized(this.checkForOptimization(request));

		if (request.getDateLivraisonMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateLivraisonMin(date);
		}

		BonLivraisonReportListValue report = gestionnaireReportGcService.getListBonlivraisonReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant TTC", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(7, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Remise", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(9, i - 1, "Montant Totale", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Devise", ExcelUtils.boldRed2));
		
		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;

		for (BonLivraisonReportElementValue element : report.getProductList()) {

			if (element.getPartieIntAbreviation() != null) {
				sheet3.addCell(new Label(2, i, element.getPartieIntAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getReference() != null) {
				sheet3.addCell(new Label(3, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell(new Label(5, i, convertisseur(element.getMontantHTaxe(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getMontantTTC(), 4) + "", ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new Label(7, i, convertisseur(element.getMontantTaxe(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// Mantat Remise

			if (element.getMontantRemise() != null) {
				sheet3.addCell(new Label(8, i, convertisseur(element.getMontantRemise(), 4) + "", ExcelUtils.boldRed));

				remiseTotale += element.getMontantRemise();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMetrageTotal(), 2) + "", ExcelUtils.boldRed));

				quantiteTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getDevise()!= null) {
				sheet3.addCell(new Label(10, i, element.getDevise() + "", ExcelUtils.boldRed));

			
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}
			
			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, report.getProductList().size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantTtcTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(remiseTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}
	
	
	@RequestMapping(value = "/listbonlivraisonDevise", method = RequestMethod.GET)
	public void generateListBonlivraisonReportDevise(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereBonLivraisonValue
			// request,
			@RequestParam("referenceBl") String referenceBl, @RequestParam("referenceBs") String referenceBs,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateLivraisonMin") String dateLivraisonMin,
			@RequestParam("dateLivraisonMax") String dateLivraisonMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, 
			//@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("avecFacture") String avecFacture,
			//@RequestParam("stock") Boolean stock,
			//@RequestParam("idDepot") Long idDepot, 
			//@RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("devise") Long devise,
			@RequestParam("declare") String declare,
			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+ "BL_LISTE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("BL-LIST", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 10);

		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		sheet3.setColumnView(10, 10);
		/**************************************************************************/


		// Nom du rapport et la date

		ExcelUtils.init();



		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des Bons de Livraisons", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);


		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
	

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereBonLivraisonValue request = new RechercheMulticritereBonLivraisonValue();


		//request.setNatureLivraison(natureLivraison);

		//request.setStock(stock);
		//request.setIdDepot(idDepot);
		
		request.setDeclare(declare);
       
	
		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReferenceBl(referenceBl);
			
		}

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}

		if (isNotEmty(dateLivraisonMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMin, ExcelUtils.boldRed3));

			request.setDateLivraisonMin(stringToCalendar(dateLivraisonMin));
		}

		if (isNotEmty(dateLivraisonMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMax, ExcelUtils.boldRed3));

			request.setDateLivraisonMax(stringToCalendar(dateLivraisonMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setMetrageMax(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			request.setPrixMin(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setPrixMax(prixMax);
		}

		if (isNotEmty(avecFacture))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "A facturer :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, avecFacture + "", ExcelUtils.boldRed3));

			request.setAvecFacture(avecFacture);
		}
		if (isNotEmty(devise))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, devise.toString() +"", ExcelUtils.boldRed3));

			request.setDevise(devise);
			
		}
		request.setOptimized(this.checkForOptimization(request));

		if (request.getDateLivraisonMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateLivraisonMin(date);
		}

		BonLivraisonReportListValue report = gestionnaireReportGcService.getListBonlivraisonReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant TTC", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(7, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Remise", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(9, i - 1, "Montant Totale", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Devise", ExcelUtils.boldRed2));
		
		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;

		for (BonLivraisonReportElementValue element : report.getProductList()) {

			if (element.getPartieIntAbreviation() != null) {
				sheet3.addCell(new Label(2, i, element.getPartieIntAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getReference() != null) {
				sheet3.addCell(new Label(3, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell(new Label(5, i, convertisseur(element.getMontantHTaxe(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(6, i, (double) Math.round(convertisseur(element.getMontantTTC(), 4) * 100) / 100 + "", ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new Label(7, i, (double) Math.round(convertisseur(element.getMontantTaxe(), 4) * 100) / 100 + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// Mantat Remise

			if (element.getMontantRemise() != null) {
				sheet3.addCell(new Label(8, i,(double) Math.round(convertisseur(element.getMontantRemise(), 4) * 100) / 100  + "", ExcelUtils.boldRed));

				remiseTotale += element.getMontantRemise();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMetrageTotal(), 2) + "", ExcelUtils.boldRed));

				quantiteTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getDevise()!= null) {
				sheet3.addCell(new Label(10, i, element.getDevise() + "", ExcelUtils.boldRed));

			
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}
			
			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, report.getProductList().size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(mantantTtcTotale, 4) * 100) / 100  + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i,  (double) Math.round(convertisseur(remiseTotale, 4) * 100) / 100   + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

	
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
	
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	
	
	

	@RequestMapping(value = "/facture", method = RequestMethod.GET)
	public void genererFactureReport(@RequestParam("id") Long id, @RequestParam("typerapport") Long typerapport,
			@RequestParam("type") String type, HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		FactureReportValue factureReport = gestionnaireReportGcService.getFactureReportValue(id, typerapport,true);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"facture" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("facture", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 12);

		sheet3.setColumnView(3, 10);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 10);

		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Bon de Livraison : " + factureReport.getReference(), ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		sheet3.addCell(new Label(3, 10, factureReport.getClient(), ExcelUtils.boldRed));
		sheet3.mergeCells(3, 10, 4, 10);

		sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		sheet3.mergeCells(3, 11, 4, 12);
		sheet3.addCell(new Label(3, 11, factureReport.getAdresse(), ExcelUtils.boldRed));

		sheet3.addCell(new Label(7, 10, "Facture N° :", ExcelUtils.boldRed3));
		sheet3.mergeCells(7, 10, 8, 10);
		sheet3.addCell(new Label(9, 10, factureReport.getReference(), ExcelUtils.boldRed));

		sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		sheet3.mergeCells(7, 11, 8, 11);
		// sheet3.addCell(new Label(9, 11, dateFormat2.format(d), ExcelUtils.boldRed));

		sheet3.addCell(new Label(9, 11, dateFormat2.format(factureReport.getDateBl().getTime()), ExcelUtils.boldRed));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 1;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i - 1, 5, i - 1);

		sheet3.addCell(new Label(6, i - 1, "Quantite", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "PUHT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant HT", ExcelUtils.boldRed2));

		for (FactureReportProductValue element : factureReport.getProductList()) {

			if (element.getProduitCode() != null) {
				sheet3.addCell(new Label(2, i, element.getProduitCode() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(3, i, element.getProduitDesignation() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i, 5, i);

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i, 5, i);
			}

			// QTE

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getQuantite(), 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// PUHT

			if (element.getPrixUHT() != null) {
				sheet3.addCell(new Label(7, i, convertisseur(element.getPrixUHT(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// remise

			if (element.getRemise() != null) {
				sheet3.addCell(
						new Label(8, i, convertisseur(element.getRemise(), 4).toString().toString().concat(" %") + "",
								ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, " 0 %", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHT() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMontantHT(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(2, i, "Taxes", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i, "Taux", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i, "Assiette", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i, "Montant", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(7, i, "Montant Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i, "Montant Taxes", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i, "Montant HT", ExcelUtils.boldRed2));

		if (factureReport.getMontantRemiseTotal() != null) {
			sheet3.addCell(new Label(7, i + 1, convertisseur(factureReport.getMontantRemiseTotal(), 4) + "",
					ExcelUtils.boldRed));

		} else {
			sheet3.addCell(new Label(7, i + 1, "", ExcelUtils.boldRed));
		}

		if (factureReport.getMontantTaxes() != null) {
			sheet3.addCell(
					new Label(8, i + 1, convertisseur(factureReport.getMontantTaxes(), 4) + "", ExcelUtils.boldRed));

		} else {
			sheet3.addCell(new Label(8, i + 1, "", ExcelUtils.boldRed));
		}

		if (factureReport.getMontantHTTotal() != null) {
			sheet3.addCell(
					new Label(9, i + 1, convertisseur(factureReport.getMontantHTTotal(), 4) + "", ExcelUtils.boldRed));

		} else {
			sheet3.addCell(new Label(9, i + 1, "", ExcelUtils.boldRed));
		}

		if (factureReport.getExistFodec()) {
			i++;

			if (factureReport.getTaxeFodec() != null) {
				sheet3.addCell(new Label(2, i, factureReport.getTaxeFodec().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getTauxFodec() != null) {
				sheet3.addCell(
						new Label(3, i, factureReport.getTauxFodec().toString().concat(" %") + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getAssietteFodec() != null) {
				sheet3.addCell(new Label(4, i, factureReport.getAssietteFodec() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getMontantTaxeFodec() != null) {
				sheet3.addCell(new Label(5, i, factureReport.getMontantTaxeFodec() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		if (factureReport.getExistTVA7()) {
			i++;

			if (factureReport.getTaxeTVA7() != null) {
				sheet3.addCell(new Label(2, i, factureReport.getTaxeTVA7().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getTauxTVA7() != null) {
				sheet3.addCell(
						new Label(3, i, factureReport.getTauxTVA7().toString().concat(" %") + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getAssietteTVA7() != null) {
				sheet3.addCell(new Label(4, i, factureReport.getAssietteTVA7() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getMontantTaxeTVA7() != null) {
				sheet3.addCell(new Label(5, i, factureReport.getMontantTaxeTVA7() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		if (factureReport.getExistTVA13()) {
			i++;

			if (factureReport.getTaxeTVA13() != null) {
				sheet3.addCell(new Label(2, i, factureReport.getTaxeTVA13().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getTauxTVA13() != null) {
				sheet3.addCell(
						new Label(3, i, factureReport.getTauxTVA13().toString().concat(" %") + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getAssietteTVA13() != null) {
				sheet3.addCell(new Label(4, i, factureReport.getAssietteTVA13() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getMontantTaxeTVA13() != null) {
				sheet3.addCell(new Label(5, i, factureReport.getMontantTaxeTVA13() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		if (factureReport.getExistTVA()) {
			i++;

			if (factureReport.getTaxeTVA() != null) {
				sheet3.addCell(new Label(2, i, factureReport.getTaxeTVA().toUpperCase() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getTauxTVA() != null) {
				sheet3.addCell(
						new Label(3, i, factureReport.getTauxTVA().toString().concat(" %") + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getAssietteTVA() != null) {
				sheet3.addCell(new Label(4, i, factureReport.getAssietteTVA() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (factureReport.getMontantTaxeTVA() != null) {
				sheet3.addCell(new Label(5, i, factureReport.getMontantTaxeTVA() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

		}

		i++;

		/*
		 * sheet3.addCell(new Label(2, i + 1, "TOTAUX", boldRed2)); sheet3.mergeCells(2,
		 * i + 1, 4, i + 1);
		 * 
		 * sheet3.addCell(new Label(5, i + 1, bonLivraisonReport.getQteTotaleCh1() + "",
		 * boldRed3)); sheet3.addCell(new Label(6, i + 1,
		 * bonLivraisonReport.getQteTotaleCh2() + "", boldRed3));
		 * 
		 * Long totalQte = bonLivraisonReport.getQteTotaleCh1() +
		 * bonLivraisonReport.getQteTotaleCh2(); sheet3.addCell(new Label(7, i + 1,
		 * totalQte + "", boldRed3));
		 * 
		 * 
		 */

		sheet3.addCell(new Label(7, i + 1, "Montant TTC", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i + 1, 8, i + 1);
		sheet3.addCell(
				new Label(9, i + 1, convertisseur(factureReport.getMontantHTTotal(), 4) + " €", ExcelUtils.boldRed2));

		/*
		 * sheet3.addCell(new Label(2, i + 3,
		 * "Arrêté le présent Bon de livraison à la somme de : ",
		 * ExcelUtils.ARIAL_10_NO_BOLD_LEFT)); sheet3.mergeCells(2, i + 3, 4, i + 3);
		 */

		sheet3.addCell(new Label(2, i + 3, "Arrêté le présent Bon de livraison à la somme de : ",
				ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(2, i + 3, 9, i + 3);

		sheet3.addCell(new Label(2, i + 4, factureReport.getMontantTTCToWords().toUpperCase(),
				ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(2, i + 4, 9, i + 4);

		// sheet3.addCell(new Label(2, i + 5, "Valeur en votre aimable règlement net 15
		// jours date facture ", ExcelUtils.ARIAL_10_NO_BOLD_LEFT));
		// sheet3.mergeCells(2, i + 5, 7, i + 5);

		sheet3.addCell(new Label(2, i + 6, "Signature Client" + "", ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(2, i + 6, 4, i + 6);

		sheet3.addCell(new Label(2, i + 7, "", ExcelUtils.boldRed2));
		sheet3.mergeCells(2, i + 7, 4, i + 9);

		// sheet3.addCell(new Label(2, i + 6, "Montant Réglé" + "",
		// ExcelUtils.boldRed3));
		// sheet3.mergeCells(2, i + 6, 3, i + 6);

		sheet3.addCell(new Label(7, i + 6, "Signature Responsable", ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER));
		sheet3.mergeCells(7, i + 6, 9, i + 6);

		sheet3.addCell(new Label(7, i + 7, "", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i + 7, 9, i + 9);

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}

	@RequestMapping(value = "/listfacture", method = RequestMethod.GET)
	public void generateListFactureVenteReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("referenceFacture") String referenceFacture, @RequestParam("referenceBl") String referenceBl,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateFactureMin") String dateFactureMin,
			@RequestParam("dateFactureMax") String dateFactureMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("typeFacture") String typeFacture,
			@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("devise") Long devise,
			
			@RequestParam("dateEcheanceDe") String dateEcheanceDe,
			@RequestParam("dateEcheanceA") String dateEcheanceA,
			@RequestParam("declarerecherche") String declarerecherche,
			
			
			HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"FACTURE_LISTE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("FACTURE-LIST", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 25);//groupe
		
		sheet3.setColumnView(4, 12);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(6, 15);

		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 15);
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 15);
		sheet3.setColumnView(12, 17);
		sheet3.setColumnView(13, 15);
		sheet3.setColumnView(14, 15);
		
		sheet3.setColumnView(15, 15);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des Factures", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 14, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereFactureValue request = new RechercheMulticritereFactureValue();

		request.setType(typeFacture);

		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReferenceBl(referenceBl);
		}

		if (isNotEmty(referenceFacture))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Facture :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceFacture, ExcelUtils.boldRed3));

			request.setReferenceFacture(referenceFacture);
		}
		if (isNotEmty(groupeClientId))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(
					numColCritRech + 1, numLigneCritRech, groupeClientService
							.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}

		if (isNotEmty(dateFactureMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMin, ExcelUtils.boldRed3));

			request.setDateFactureMin(stringToCalendar(dateFactureMin));
		}

		if (isNotEmty(dateFactureMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMax, ExcelUtils.boldRed3));

			request.setDateFactureMax(stringToCalendar(dateFactureMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setMetrageMax(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			request.setPrixMin(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setPrixMax(prixMax);
		}

		/*
		 * if (isNotEmty(typeFacture))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Type Facture :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, typeFacture + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * request.setType(typeFacture); }
		 * 
		 */

		if (isNotEmty(natureLivraison))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Nature Livraison :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, natureLivraison + "", ExcelUtils.boldRed3));

			request.setNatureLivraison(natureLivraison);
		}
		
		if (isNotEmty(devise))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, devise.toString(), ExcelUtils.boldRed3));

			request.setDevise(devise);
		}
		
		
		if (isNotEmty(dateEcheanceDe))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Echeance Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateEcheanceDe, ExcelUtils.boldRed3));

			request.setDateEcheanceDe(stringToCalendar(dateEcheanceDe));
		}

		if (isNotEmty(dateEcheanceA))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Echeance A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateEcheanceA, ExcelUtils.boldRed3));

			request.setDateEcheanceA(stringToCalendar(dateEcheanceA));
		}
		
		
		if (isNotEmty(declarerecherche))

		{

			/*numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Declaree :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, declarerecherche + "", ExcelUtils.boldRed3));
            */
			request.setDeclarerecherche(declarerecherche);
		}
		

		request.setOptimized(this.checkForOptimization(request));

	/*	if (request.getDateFactureMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateFactureMin(date);
		}*/

		ResultatRechecheFactureValue report = factureService.rechercherMultiCritereAvecDetail(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Groupe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant HT", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(7, i - 1, "FODEC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "TVA", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Timbre", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(10, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(12, i - 1, "Montant Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(13, i - 1, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(14, i - 1, "Montant Ouvert", ExcelUtils.boldRed2));		
		sheet3.addCell(new Label(15, i - 1, "Date Echeance", ExcelUtils.boldRed2));
		
		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;
		Double mantantOuvertTotale = 0d;
		
		List<TaxeValue> listeTaxes = taxeService.getAll();
		for (FactureVenteValue element : report.getList()) {
			
			
			if (element.getPartieIntId()!= null) {
				
				sheet3.addCell(new Label(2, i, partieInteresseeService.getById(element.getPartieIntId()).getAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

		
			if (element.getGroupeClientId() != null) {
				
				GroupeClientValue gr = new GroupeClientValue();
				gr.setId(element.getGroupeClientId());
				
			
				sheet3.addCell(new Label(3, i,	groupeClientService.rechercheGroupeClientParId(gr).getDesignation()+ "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getReference() != null) {
				sheet3.addCell(new Label(4, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getMontantHTaxe(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			
			
			Double fodec = new Double(0);
			Double tva = new Double(0);
			Double timbre = new Double(0);
			//fodec
			
			
			
			if (element.getListTaxeFacture() != null) {
				
				
				for(TaxeFactureValue tf :element.getListTaxeFacture()) {
					
              			if(tf.getTaxeId() != null && tf.getMontant() != null) {
              				
              				 TaxeValue tx = rechercherTaxeById(tf.getTaxeId(),listeTaxes) ;
                   			
                   			if(tx.getDesignation().contains("TVA"))
                   				tva += tf.getMontant() ;
                   			
                   			if(tx.getDesignation().contains("FODEC"))
                   				fodec += tf.getMontant() ;
                   			
                   			if(tx.getDesignation().contains("TIMBRE"))
                   				timbre += tf.getMontant() ;
              				
              			}
					 
					
				}
				
			}
			
			
		
				sheet3.addCell(new Label(7, i, convertisseur(fodec, 4) + "", ExcelUtils.boldRed));


				sheet3.addCell(new Label(8, i, convertisseur(tva, 4) + "", ExcelUtils.boldRed));

		
			
			//timbre
	
			
			sheet3.addCell(new Label(9, i, convertisseur(timbre, 4) + "", ExcelUtils.boldRed));

	
	
			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(10, i, convertisseur(element.getMontantTTC(), 4) + "", ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}

			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new Label(11, i, convertisseur(element.getMontantTaxe(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
			}

			// Mantat Remise

			if (element.getMontantRemise() != null) {
				sheet3.addCell(new Label(12, i, convertisseur(element.getMontantRemise(), 4) + "", ExcelUtils.boldRed));

				remiseTotale += element.getMontantRemise();
			} else {
				sheet3.addCell(new Label(12, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(13, i, convertisseur(element.getMetrageTotal(), 3) + "", ExcelUtils.boldRed));

				quantiteTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(13, i, "", ExcelUtils.boldRed));
			}
			
			
		

		      Double montantPaye = elementReglementService.getSumMontantPayerByReferenceFacture(element.getReference());
		      
		      
		      Double montantAvoir = new Double(0);
		      
		      
		      
		      RechercheMulticritereFactureValue requestAvoir = new RechercheMulticritereFactureValue();
		      
		      requestAvoir.setType(IConstanteCommerciale.FACTURE_TYPE_AVOIR);
		      requestAvoir.setInfoLivraison(element.getReference());
		      ResultatRechecheFactureValue resultAvoir = factureService.rechercherMultiCritere(requestAvoir);
		      
		      
		      for(FactureVenteValue factureAvoir : resultAvoir.getList()) {
		    	  
		    	  montantAvoir += factureAvoir.getMontantTTC() ;
		      }
			
			  Double montantOuvert   = element.getMontantTTC() - montantAvoir - montantPaye;
		      
		      if(montantOuvert < 0 ) {
		    	  
		    	  montantOuvert = 0d;
		      }
		      
		      
		    	 
			
			
		  	if (montantOuvert != null) {
		  		
	         	sheet3.addCell(new Label(14, i, convertisseur(montantOuvert, 4) + "", ExcelUtils.boldRed));

				mantantOuvertTotale += montantOuvert;
			} 
		  	
		  	
			// date echeance

			if (element.getDateEcheance() != null) {
				sheet3.addCell(
						new Label(15, i, dateFormat2.format(element.getDateEcheance().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(15, i, "", ExcelUtils.boldRed));
			}
		

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, report.getList().size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantTtcTotale, 4) + "", ExcelUtils.boldRed2));
		
		

		i++;

		sheet3.addCell(new Label(7, i, "Montant Ouvert", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantOuvertTotale, 4) + "", ExcelUtils.boldRed2));

//		i++;
//
//		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
//		sheet3.mergeCells(7, i, 8, i);
//		sheet3.addCell(new Label(9, i, convertisseur(remiseTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}
	
	
	





	private TaxeValue rechercherTaxeById(Long taxeId, List<TaxeValue> listeTaxes) {
		
		for(TaxeValue tx : listeTaxes) {
			if(tx.getId().equals(taxeId))
				return tx;
		}
		
		return null;
	}






	@RequestMapping(value = "/listfactureDevise", method = RequestMethod.GET)
	public void generateListFactureVenteReportDevise(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("referenceFacture") String referenceFacture, @RequestParam("referenceBl") String referenceBl,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateFactureMin") String dateFactureMin,
			@RequestParam("dateFactureMax") String dateFactureMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("typeFacture") String typeFacture,
			@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("devise") Long devise,
			
			HttpServletResponse response)
			throws WriteException, IOException {



		Date d = new Date();

		String dat = "" + dateFormat.format(d);

	
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"FACTURE_LISTE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("FACTURE-LIST", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 25);//groupe
		
		sheet3.setColumnView(4, 12);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(6, 10);

		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		sheet3.setColumnView(10, 10);
		/**************************************************************************/

		
		// Nom du rapport et la date

		ExcelUtils.init();

	
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des Factures", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 10, 8);


		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereFactureValue request = new RechercheMulticritereFactureValue();

		request.setType(typeFacture);

		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReferenceBl(referenceBl);
		}

		if (isNotEmty(referenceFacture))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Facture :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceFacture, ExcelUtils.boldRed3));

			request.setReferenceFacture(referenceFacture);
		}
		if (isNotEmty(groupeClientId))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(
					numColCritRech + 1, numLigneCritRech, groupeClientService
							.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}

		if (isNotEmty(dateFactureMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMin, ExcelUtils.boldRed3));

			request.setDateFactureMin(stringToCalendar(dateFactureMin));
		}

		if (isNotEmty(dateFactureMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMax, ExcelUtils.boldRed3));

			request.setDateFactureMax(stringToCalendar(dateFactureMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setMetrageMax(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			request.setPrixMin(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setPrixMax(prixMax);
		}

		/*
		 * if (isNotEmty(typeFacture))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Type Facture :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, typeFacture + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * request.setType(typeFacture); }
		 * 
		 */

		if (isNotEmty(natureLivraison))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Nature Livraison :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, natureLivraison + "", ExcelUtils.boldRed3));

			request.setNatureLivraison(natureLivraison);
		}
		
		if (isNotEmty(devise))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, devise.toString(), ExcelUtils.boldRed3));

			request.setDevise(devise);
		}

		request.setOptimized(this.checkForOptimization(request));

		if (request.getDateFactureMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateFactureMin(date);
		}

		FactureReportListValue report = gestionnaireReportGcService.getListFactureReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Groupe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant TTC", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(8, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant Remise", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(10, i - 1, "Quantite Totale", ExcelUtils.boldRed2));
		
		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;

		for (FactureReportElementValue element : report.getProductList()) {

			if (element.getPartieIntAbreviation() != null) {
				sheet3.addCell(new Label(2, i, element.getPartieIntAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getGroupePiDesignation() != null) {
				sheet3.addCell(new Label(3, i, element.getGroupePiDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getReference() != null) {
				sheet3.addCell(new Label(4, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell(new Label(6, i, (double) Math.round(convertisseur(element.getMontantHTaxe(), 4) * 100) / 100 + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(7, i, (double) Math.round(convertisseur(element.getMontantTTC(), 4) * 100) / 100  + "", ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new Label(8, i,(double) Math.round(convertisseur(element.getMontantTaxe(), 4) * 100) / 100   + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// Mantat Remise

			if (element.getMontantRemise() != null) {
				sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(element.getMontantRemise(), 4) * 100) / 100 + "", ExcelUtils.boldRed));

				remiseTotale += element.getMontantRemise();
			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(10, i, (double) Math.round(convertisseur(element.getMetrageTotal(), 3) * 100) / 100 + "", ExcelUtils.boldRed));

				quantiteTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}
		

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, report.getProductList().size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(mantantTtcTotale, 4)* 100) / 100 + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(remiseTotale, 4)* 100) / 100 + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());

		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value = "/factureRecap", method = RequestMethod.GET)
	public void generateListFactureVenteRecapReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("referenceFacture") String referenceFacture, @RequestParam("referenceBl") String referenceBl,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateFactureMin") String dateFactureMin,
			@RequestParam("dateFactureMax") String dateFactureMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("typeFacture") String typeFacture,
			@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("groupeClientId") Long groupeClientId, HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
				excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"FACTURE_RECAP" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("FACTURE-RECAP", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 25);//groupe
		
		sheet3.setColumnView(4, 25);
		sheet3.setColumnView(5, 25);
		sheet3.setColumnView(6, 20);

		sheet3.setColumnView(7, 20);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		sheet3.setColumnView(10, 10);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Rapport Facture Recap", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 7, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereFactureValue request = new RechercheMulticritereFactureValue();

		request.setType(typeFacture);

		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReferenceBl(referenceBl);
		}

		if (isNotEmty(referenceFacture))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Facture :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceFacture, ExcelUtils.boldRed3));

			request.setReferenceFacture(referenceFacture);
		}
		if (isNotEmty(groupeClientId))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(
					numColCritRech + 1, numLigneCritRech, groupeClientService
							.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}

		if (isNotEmty(dateFactureMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMin, ExcelUtils.boldRed3));

			request.setDateFactureMin(stringToCalendar(dateFactureMin));
		}

		if (isNotEmty(dateFactureMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMax, ExcelUtils.boldRed3));

			request.setDateFactureMax(stringToCalendar(dateFactureMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setMetrageMax(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			request.setPrixMin(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setPrixMax(prixMax);
		}

		/*
		 * if (isNotEmty(typeFacture))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Type Facture :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, typeFacture + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * request.setType(typeFacture); }
		 * 
		 */

		if (isNotEmty(natureLivraison))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Nature Livraison :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, natureLivraison + "", ExcelUtils.boldRed3));

			request.setNatureLivraison(natureLivraison);
		}

		request.setOptimized(this.checkForOptimization(request));

		if (request.getDateFactureMin() == null) {

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, 2020);
			date.set(Calendar.MONTH, 0);
			date.set(Calendar.DAY_OF_MONTH, 1);

			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.MILLISECOND, 0);

			request.setDateFactureMin(date);
		}
		
		
		
		List<DateUtilsValue>  dateUtilsList = utilsService.getDateDebAndMaxGroupByMonth(new DateUtilsValue(request.getDateFactureMin(),request.getDateFactureMax()));

		


		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(3, i - 1, "Nbr facture", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Somme Mont. HTAXE", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Somme Mont. TTC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Date De", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Date A", ExcelUtils.boldRed2));
	

		for(DateUtilsValue dateUtils : dateUtilsList) {
			
			request.setDateFactureMin(dateUtils.getDateMin());
			request.setDateFactureMax(dateUtils.getDateMax());
			
			request.setType("Normal");
			
			FactureReportElementRecapValue element = gestionnaireReportGcService.getFactureReportElementRecapValue(request);

			
			if (element.getMois() != null) {
				sheet3.addCell(new Label(2, i, element.getMois() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			
			sheet3.addCell(new Label(3, i, element.getNbr() + "", ExcelUtils.boldRed));

			
			
			if (element.getMontantHTaxe() != null) {
				sheet3.addCell( new jxl.write.Number(4, i, convertisseur(element.getMontantHTaxe(), 4), ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getMontantTTC() != null) {
				sheet3.addCell( new jxl.write.Number(5, i, convertisseur(element.getMontantHTaxe(), 5), ExcelUtils.boldRed));


			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getDebut() != null) {
				sheet3.addCell(
						new Label(6, i, dateFormat2.format(element.getDebut().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getFin() != null) {
				sheet3.addCell(
						new Label(7, i, dateFormat2.format(element.getFin().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}
			
			
			i++;
		}
		
		i++;
		i++;
		i++;
		
		sheet3.addCell(new Label(3, i - 1, "Nbr Avoir", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Somme Mont. HTAXE", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Somme Mont. TTC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Date De", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Date A", ExcelUtils.boldRed2));
	

		for(DateUtilsValue dateUtils : dateUtilsList) {
			
			request.setDateFactureMin(dateUtils.getDateMin());
			request.setDateFactureMax(dateUtils.getDateMax());
			
			request.setType("Avoir");
			
			FactureReportElementRecapValue element = gestionnaireReportGcService.getFactureReportElementRecapValue(request);

			
			if (element.getMois() != null) {
				sheet3.addCell(new Label(2, i, element.getMois() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			
			sheet3.addCell(new Label(3, i, element.getNbr() + "", ExcelUtils.boldRed));

			
			
			if (element.getMontantHTaxe() != null) {
				sheet3.addCell( new jxl.write.Number(4, i, convertisseur(element.getMontantHTaxe(), 4), ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getMontantTTC() != null) {
				sheet3.addCell( new jxl.write.Number(5, i, convertisseur(element.getMontantHTaxe(), 5), ExcelUtils.boldRed));


			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getDebut() != null) {
				sheet3.addCell(
						new Label(6, i, dateFormat2.format(element.getDebut().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getFin() != null) {
				sheet3.addCell(
						new Label(7, i, dateFormat2.format(element.getFin().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}
			
			
			i++;
		}
	
	

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}

	@RequestMapping(value = "/bl-non-paye", method = RequestMethod.GET)
	public void generateListBonlivraisonNonPayeReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereBonLivraisonValue
			// request,
			@RequestParam("referenceBl") String referenceBl, @RequestParam("referenceBs") String referenceBs,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateLivraisonMin") String dateLivraisonMin,
			@RequestParam("dateLivraisonMax") String dateLivraisonMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("avecFacture") String avecFacture, @RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("idDepot") Long idDepot,
			@RequestParam("deviseId") Long deviseId,
			
			
			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
				excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"BL_LISTE_NON_PAYEE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("BL-LIST-NON-PAYEE", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 10);

		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des Bons de Livraisons Non Payées", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereBonLivraisonValue request = new RechercheMulticritereBonLivraisonValue();

		if (isNotEmty(referenceBs))
			request.setReferenceBs(referenceBs);

		if (isNotEmty(natureLivraison))
			request.setNatureLivraison(natureLivraison);

		if (isNotEmty(idDepot))
			request.setIdDepot(idDepot);

		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReferenceBl(referenceBl);
		}

		if (isNotEmty(groupeClientId))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(
					numColCritRech + 1, numLigneCritRech, groupeClientService
							.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}
		
		if (isNotEmty(deviseId))

		{

			DeviseValue devRech = new DeviseValue();
			devRech.setId(deviseId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					deviseService.rechercheDeviseParId(devRech).getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setDevise(deviseId);
		}

		if (isNotEmty(dateLivraisonMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMin, ExcelUtils.boldRed3));

			request.setDateLivraisonMin(stringToCalendar(dateLivraisonMin));
		}

		if (isNotEmty(dateLivraisonMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMax, ExcelUtils.boldRed3));

			request.setDateLivraisonMax(stringToCalendar(dateLivraisonMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setMetrageMax(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			request.setPrixMin(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setPrixMax(prixMax);
		}

		if (isNotEmty(avecFacture))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "A facturer :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, avecFacture + "", ExcelUtils.boldRed3));

			request.setAvecFacture(avecFacture);
		}

		BonLivraisonReportListValue report = gestionnaireReportGcService.getListBonlivraisonReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant TTC", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(7, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Remise", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(9, i - 1, "Quantite Totale", ExcelUtils.boldRed2));

		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;

		List<BonLivraisonReportElementValue> listBlNonPaye = new ArrayList<>();
		for (BonLivraisonReportElementValue element : report.getProductList()) {

			//if (!elementReglementService.existElementReglementByReferenceBL(element.getReference()))
			//	listBlNonPaye.add(element);
			
			
			Double montantPayee = elementReglementService.getSumMontantPayerByReferenceBL(element.getReference()) ;
			
			
			if(montantPayee < element.getMontantTTC() )
				listBlNonPaye.add(element);
		}

		for (BonLivraisonReportElementValue element : listBlNonPaye) {

			if (element.getPartieIntAbreviation() != null) {
				sheet3.addCell(new Label(2, i, element.getPartieIntAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getReference() != null) {
				sheet3.addCell(new Label(3, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell(new Label(5, i, convertisseur(element.getMontantHTaxe(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getMontantTTC(), 4) + "", ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new Label(7, i, convertisseur(element.getMontantTaxe(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// Mantat Remise

			if (element.getMontantRemise() != null) {
				sheet3.addCell(new Label(8, i, convertisseur(element.getMontantRemise(), 4) + "", ExcelUtils.boldRed));

				remiseTotale += element.getMontantRemise();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMetrageTotal(), 3) + "", ExcelUtils.boldRed));

				quantiteTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, listBlNonPaye.size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantTtcTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(remiseTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}

	@RequestMapping(value = "/facture-non-paye", method = RequestMethod.GET)
	public void generateListFactureVenteNonPayeReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("referenceFacture") String referenceFacture, @RequestParam("referenceBl") String referenceBl,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateFactureMin") String dateFactureMin,
			@RequestParam("dateFactureMax") String dateFactureMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("typeFacture") String typeFacture,
			@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("deviseId") Long deviseId,
			@RequestParam("declarerRech") String declarerRech,

			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
				excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"FACTURE_LISTE_NON_PAYEE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("FACTURE-LIST-NON-PAYEE", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 10);

		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		
		sheet3.setColumnView(10, 10);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des Factures Non Payées", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 10, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereFactureValue request = new RechercheMulticritereFactureValue();

		request.setType(typeFacture);

		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReferenceBl(referenceBl);
		}

		if (isNotEmty(referenceFacture))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Facture :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceFacture, ExcelUtils.boldRed3));

			request.setReferenceFacture(referenceFacture);
		}

		if (isNotEmty(groupeClientId))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(
					numColCritRech + 1, numLigneCritRech, groupeClientService
							.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}
		
		
		
		if (isNotEmty(deviseId))

		{

			DeviseValue devRech = new DeviseValue();
			devRech.setId(deviseId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					deviseService.rechercheDeviseParId(devRech).getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setDevise(deviseId);
		}

		if (isNotEmty(dateFactureMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMin, ExcelUtils.boldRed3));

			request.setDateFactureMin(stringToCalendar(dateFactureMin));
		}

		if (isNotEmty(dateFactureMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFactureMax, ExcelUtils.boldRed3));

			request.setDateFactureMax(stringToCalendar(dateFactureMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setMetrageMax(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			request.setPrixMin(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setPrixMax(prixMax);
		}

		/*
		 * if (isNotEmty(typeFacture))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Type Facture :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, typeFacture + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * request.setType(typeFacture); }
		 * 
		 */

		if (isNotEmty(natureLivraison))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Nature Livraison :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, natureLivraison + "", ExcelUtils.boldRed3));

			request.setNatureLivraison(natureLivraison);
		}
		
		
		if (isNotEmty(declarerRech))

		{

		/*	numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Declaree :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, declarerRech + "", ExcelUtils.boldRed3));
         */
			request.setDeclarerecherche(declarerRech);
	
		}
		
		

		FactureReportListValue report = gestionnaireReportGcService.getListFactureReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Réglé", ExcelUtils.boldRed2));

		
		sheet3.addCell(new Label(9, i - 1, "Devise", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(10, i - 1, "Reste", ExcelUtils.boldRed2));

		
		
//		sheet3.addCell(new Label(9, i - 1, "Quantite Totale", ExcelUtils.boldRed2));

		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;

		Double montantPayeeTotale = 0d;
		
		Double mantantResteTotale = 0d;
		List<FactureReportElementValue> listFactureNonPaye = new ArrayList<FactureReportElementValue>();
		for (FactureReportElementValue element : report.getProductList()) {

			/*if (!elementReglementService.existElementReglementByReferenceFacture(element.getReference()))
				listFactureNonPaye.add(element);
				*/
			
			
			Double montantPayee = elementReglementService.getSumMontantPayerByReferenceFacture(element.getReference()) ;
			
			
			
			element.setMetrageTotal(montantPayee);
			
			
			if((montantPayee < element.getMontantTTC()) && !hasFactureAvoir(element.getReference()) ) {
				
				listFactureNonPaye.add(element);
			}
			
		}
		
		
		
		Map<Long,Double> mapsMontantTTCdevise = new HashMap<Long, Double>();

		for (FactureReportElementValue element : listFactureNonPaye) {
			
			
		
			
			if(element.getDevise() != null) {
				
				if(mapsMontantTTCdevise.containsKey(element.getDevise())) {
					
					Double montant = mapsMontantTTCdevise.get(element.getDevise()) ;
					
					
					if(element.getDevise().equals(DeviseValue.DINAR) && element.getMontantTTC() != null) {
						
						montant += element.getMontantTTC()  ;
						
					}else
					{
						if(element.getMontantConverti() != null)
					      	montant += element.getMontantConverti()  ;
					}
					
					mapsMontantTTCdevise.put(element.getDevise(), montant);
					
					
				}else
					
				{
					
					if(element.getDevise().equals(DeviseValue.DINAR) && element.getMontantTTC() != null) {
				
						
						mapsMontantTTCdevise.put(element.getDevise(),  element.getMontantTTC());
						
					}else
					{
						mapsMontantTTCdevise.put(element.getDevise(),  element.getMontantConverti());
					}
					
				}
				
				
				
				
				
			}
			

			if (element.getPartieIntAbreviation() != null) {
				sheet3.addCell(new Label(2, i, element.getPartieIntAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getReference() != null) {
				sheet3.addCell(new Label(3, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell( new jxl.write.Number(5, i, convertisseur(element.getMontantHTaxe(), 3), ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new jxl.write.Number(6, i, convertisseur(element.getMontantTaxe(), 3), ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			
			
			
			// Mantant TTC
			if(element.getDevise() != null) {
				
				if(element.getDevise().equals(DeviseValue.DINAR)){
					
					if (element.getMontantTTC() != null) {
						sheet3.addCell(new jxl.write.Number(7, i, convertisseur(element.getMontantTTC(), 3), ExcelUtils.boldRed));

						mantantTtcTotale += element.getMontantTTC();
					} else {
						sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
					}

					
				}else
					
				{
					if (element.getMontantConverti() != null) {
						sheet3.addCell(new jxl.write.Number(7, i, convertisseur(element.getMontantConverti(), 3), ExcelUtils.boldRed));

						mantantTtcTotale += element.getMontantTTC();
					} else {
						sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
					}

					
				}
				
		
			}

	
		
			// montant reglee
			
			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new jxl.write.Number(8, i, convertisseur(element.getMetrageTotal(), 3) , ExcelUtils.boldRed));

				montantPayeeTotale+=element.getMetrageTotal();
		
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}
			
			String devis = "" ;
			
			if(element.getDevise() != null) {
				
				DeviseValue pDeviseRech = new DeviseValue(); pDeviseRech.setId(element.getDevise());
				
				DeviseValue deviseValue = deviseService.rechercheDeviseParId(pDeviseRech) ;
				
				if(deviseValue != null) devis = deviseValue.getDesignation();
			}
			
			
			if (devis != null) {
				sheet3.addCell(new Label(9, i, devis + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

			}
			
			
			
			// Mantant Reste

			if (element.getMetrageTotal() != null && element.getMontantTTC() != null) {
				
				Double reste = element.getMontantTTC() - element.getMetrageTotal();
				sheet3.addCell(new jxl.write.Number(10, i, convertisseur(reste, 3) , ExcelUtils.boldRed));

				mantantResteTotale += reste ;
				
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}

	/*		// Mantat Remise

			if (element.getMontantRemise() != null) {
				sheet3.addCell(new Label(8, i, convertisseur(element.getMontantRemise(), 3) + "", ExcelUtils.boldRed));

				remiseTotale += element.getMontantRemise();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMetrageTotal(), 3) + "", ExcelUtils.boldRed));

				quantiteTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}
			
			*/

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(4, i, "Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i, 7, i);

		i++;

		sheet3.addCell(new Label(4, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(4, i, 6, i);
		sheet3.addCell(new Label(7, i, listFactureNonPaye.size() + "", ExcelUtils.boldRed2));

		/*		i++;
		

       	sheet3.addCell(new Label(4, i, "Montant Réglé Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(4, i, 6, i);
		sheet3.addCell(new jxl.write.Number(7, i, convertisseur(montantPayeeTotale, 4), ExcelUtils.boldRed2));
*/
		i++;
		
	      for (Map.Entry<Long, Double> mapentry : mapsMontantTTCdevise.entrySet()) {
	         /*  System.out.println("clé: "+mapentry.getKey() 
	                              + " | valeur: " + mapentry.getValue());*/
	    
	           
	           DeviseValue devRech = new DeviseValue(); devRech.setId((Long)mapentry.getKey());
	           
	       	sheet3.addCell(new Label(4, i, "Montant TTC Total en "+deviseService.rechercheDeviseParId(devRech).getDesignation(), ExcelUtils.boldRed2));
			sheet3.mergeCells(4, i, 6, i);
			sheet3.addCell(new jxl.write.Number(7, i, convertisseur((Double)mapentry.getValue(), 4), ExcelUtils.boldRed2));

			i++;
	           
	           
	           
	        }
		

	

		

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}

	private boolean hasFactureAvoir(String reference) {
		
	
		RechercheMulticritereFactureValue requestAvoir = new RechercheMulticritereFactureValue();
		
		requestAvoir.setInfoLivraison(reference);
		
		ResultatRechecheFactureValue result = factureService.rechercherMultiCritere(requestAvoir) ; 
		
	  
		
		return (result.getList().size() > 0) ? true : false ;
		
	}






	@RequestMapping(value = "/produit-depot-list", method = RequestMethod.GET)
	public void generateListProduitDepotReport(

			@RequestParam("idDepot") Long idDepot, @RequestParam("idProduit") Long idProduit,

			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"STOCK-ARTICLE-MAGASIN" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("STOCK-ARTICLE-MAGASIN", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 20);
		sheet3.setColumnView(2, 35);

		sheet3.setColumnView(3, 30);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 10);

		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "   Etat du Stock Article par Magasin", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 6, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

		if (isNotEmty(idProduit))

		{

			ProduitValue produit = produitService.rechercheProduitById(idProduit);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Article Réf. :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produit.getReference(), ExcelUtils.boldRed3));

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Article Dés. :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produit.getDesignation(), ExcelUtils.boldRed3));

			request.setIdProduit(idProduit);
		}

		if (isNotEmty(idDepot))

		{
			MagasinValue mgValue = new MagasinValue();
			mgValue.setId(idDepot);
			mgValue = magasinService.rechercheMagasinParId(mgValue);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, mgValue.getDesignation(), ExcelUtils.boldRed3));

			request.setIdDepot(idDepot);
		}

		ResultatRechercheProduitDepotValue res = produitDepotService.rechercherProduitDepotMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Magasin", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Quantite", ExcelUtils.boldRed2));

		Double quantiteTotale = 0d;

		for (ProduitDepotValue element : res.getProduitdepotvalues()) {

			if (element.getReferenceProduit() != null) {
				sheet3.addCell(new Label(2, i, element.getReferenceProduit() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getDesignationProduit() != null) {
				sheet3.addCell(new Label(3, i, element.getDesignationProduit() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			if (element.getDesignationMagasin() != null) {
				sheet3.addCell(new Label(4, i, element.getDesignationMagasin() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(5, i, convertisseur(element.getQuantite(), 3) + "", ExcelUtils.boldRed));

				quantiteTotale += element.getQuantite();
			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;

		sheet3.addCell(new Label(2, i, "Nbr Ligne", ExcelUtils.boldRed2));
		// sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, res.getProduitdepotvalues().size() + "", ExcelUtils.boldRed2));

		i++;
		sheet3.addCell(new Label(2, i, "Qte Totale", ExcelUtils.boldRed2));
		// sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, quantiteTotale + "", ExcelUtils.boldRed2));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}

	@RequestMapping(value = "/mouvementProduit", method = RequestMethod.GET)
	public void generateMouvementProduitReport(@RequestParam("produitId") Long produitId,
			@RequestParam("dateMin") String dateMin, @RequestParam("dateMax") String dateMax,

			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
				excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Historique Article" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Historique_Article", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 15);

		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 17);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Historique  Article", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 6, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(produitId))

		{

			ProduitValue p = produitService.rechercheProduitById(produitId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Référence :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, p.getReference(), ExcelUtils.boldRed3));

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Désignation :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, p.getDesignation(), ExcelUtils.boldRed3));

		}

		if (isNotEmty(dateMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateMin, ExcelUtils.boldRed3));

		}

		if (isNotEmty(dateMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateMax, ExcelUtils.boldRed3));

		}

		MouvementProduitReportValue report = gestionnaireReportGcService.getMouvementProduitElementReport(produitId,
				stringToCalendar(dateMin), stringToCalendar(dateMax));

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Type", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "QTE Entree", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "QTE Sortie", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "QTE En Stock", ExcelUtils.boldRed2));

		Double quantiteTotale = 0d;

		for (MouvementProduitElementReportValue element : report.getMouvement()) {

			if (element.getTypeMouvement() != null) {
				sheet3.addCell(new Label(2, i, element.getTypeMouvement() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getReferenceMouvement() != null) {
				sheet3.addCell(new Label(3, i, element.getReferenceMouvement() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDateMouvement() != null) {
				sheet3.addCell(new Label(4, i, dateFormat2.format(element.getDateMouvement().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getQuantite() != null && element.getTypeMouvement().equals("Réception")) {

				sheet3.addCell(new Label(5, i, convertisseur(element.getQuantite(), 4) + "", ExcelUtils.boldRed));

				quantiteTotale -= element.getQuantite();

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			if (element.getQuantite() != null && element.getTypeMouvement().equals("Livraison")) {

				sheet3.addCell(new Label(6, i, convertisseur(element.getQuantite(), 4) + "", ExcelUtils.boldRed));

				quantiteTotale += element.getQuantite();

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			if (element.getQuantiteEnStock() != null) {

				sheet3.addCell(
						new Label(7, i, convertisseur(element.getQuantiteEnStock(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;
		/*
		 * i++;
		 * 
		 * sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(7, i, 9, i);
		 */

		i++;

		sheet3.addCell(new Label(2, i, "Nbr des Lignes : " + report.getMouvement().size(), ExcelUtils.boldRed5));
		// sheet3.mergeCells(7, i, 8, i);
		// sheet3.addCell(new Label(9, i, report.getMouvement().size() + "",
		// ExcelUtils.boldRed2));

		i++;

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Qte En Stock Avant le "+dateFormat2.format(stringToCalendar(dateMin).getTime()), ExcelUtils.boldRed3));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, report.getQteEnStock() + "", ExcelUtils.boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}
	
	

	
	@RequestMapping(value = "/listElementReglement", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListElementReglementReport(
			
			@RequestBody  RechercheMulticritereReglementValue request
							
		) throws WriteException, IOException {


		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+"detail_reglement_vente" + "-" + dat + ".xls");

		
	       ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
	        WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);
		
	//	WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Det reglement vente", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		

		

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 20);

		sheet3.setColumnView(3, 25);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 15);
		sheet3.setColumnView(9, 15);
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 15);
		sheet3.setColumnView(12, 15);
		sheet3.setColumnView(13, 15);
		
		/**************************************************************************/

		

		// Nom du rapport et la date

		ExcelUtils.init();

	
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des détailles Reglements Vente", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 13, 8);
		

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		
		if (isNotEmty(request.getPartieIntId()))

		{
			
		
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, 	partieInteresseeService.getById(request.getPartieIntId()).getAbreviation(), ExcelUtils.boldRed3));


		}


		if (isNotEmty(request.getReference()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getReference(), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getRefFacture()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Facture:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getRefFacture(), ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getRefBL()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BL:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getRefBL(), ExcelUtils.boldRed3));

	
		}

		

		if (isNotEmty(request.getDateReglementMin()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateReglementMin().getTime()), ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getDateReglementMax()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateReglementMax().getTime()), ExcelUtils.boldRed3));

	
		}

	

		if (isNotEmty(request.getMontantMin()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Montant du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getMontantMin() + "", ExcelUtils.boldRed3));

		
		}
		
		
		if (isNotEmty(request.getMontantMax()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Montant A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getMontantMax() + "", ExcelUtils.boldRed3));

		
		}
		
		if (isNotEmty(request.getIdDepot()))

		{
			MagasinValue magasin=new MagasinValue();
			magasin.setId(request.getIdDepot());
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, magasinService.rechercheMagasinParId(magasin).getDesignation(), ExcelUtils.boldRed3));

	
		}
		
		
		
		
		ResultatRechecheElementReglementValue result;
		
		
		if(estNonVide(request.getNumPiece()))  {
			
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Numero Piece :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getNumPiece() + "", ExcelUtils.boldRed3));

			
	         result = detailsReglementService.rechercherMultiCritere(request);
			
			
		}else
			
			
		{
			
			result = elementReglementService.rechercherMultiCritere(request);
		}

		/*
		 * if (isNotEmty(request.getBoutiqueId()))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Boutique :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, request.getPrixMin() + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * 
		 * }
		 */

		 

	//	request.setOptimized(this.checkForOptimization(request));

	


		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Montant Totale", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Ref BR", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Ref Facture", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant Demandée", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Num Piece", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(12, i - 1, "Date Echéance", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(13, i - 1, "Observations", ExcelUtils.boldRed2));
		
		
		 

		
		

		for (ResultatRechecheElementReglementElementValue element : result.getList()) {
			
			  
			

			 

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));

			}
			
			
			

			 

			if (element.getPartieIntAbreviation() != null) {
				sheet3.addCell(new Label(3, i, element.getPartieIntAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

		
		

			if (element.getMontantTotal() != null) {
				sheet3.addCell(new Label(4, i, convertisseur(element.getMontantTotal(), 4)  + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			

			if (element.getMontant() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getMontant(), 4)  + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

			}
			

		
			

			if (element.getRefBL() != null) {
				sheet3.addCell(new Label(7, i, element.getRefBL() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

			}
			 
			
			if (element.getRefFacture() != null) {
				sheet3.addCell(new Label(8, i, element.getRefFacture() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

			}
			 

			if (element.getMontantDemande() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMontantDemande(), 4)  + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

			}
			
			
			if (element.getNumPiece() != null) {
				sheet3.addCell(new Label(10, i, element.getNumPiece() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));

			}
			 
			

			// date

			if (element.getDateEmission() != null) {
				sheet3.addCell(
						new Label(11, i, dateFormat2.format(element.getDateEmission().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getDateEcheance() != null) {
				sheet3.addCell(
						new Label(12, i, dateFormat2.format(element.getDateEcheance().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(12, i, "", ExcelUtils.boldRed));
			}
			
			 
			
			
			if (element.getObservation() != null) {
				sheet3.addCell(new Label(13, i, element.getObservation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(13, i, "", ExcelUtils.boldRed));

			}
			
			 

			i++;

		}

		i++;
		i++;

	/*	sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;
*/
		 

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		

/******************************************************************************************/
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "octet-stream"));
        
        Date now = new Date();
        String dateFormat1 = dateFormat.format(now);
        String filename = "bon-inventaire_" + dateFormat1 + ".xls";
       // String filename = "sortie-stock_" + dateFormat1 ;
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(fileOut.toByteArray(), headers, HttpStatus.OK);
		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/
        
	/*	// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;*/

	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/listEcheance", method = RequestMethod.GET)
	public void generateListDetailReglementReport(
			
			@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("reference") String reference,
			@RequestParam("referenceDetReglement") String referenceDetReglement,
			@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("dateReglementAu") String dateReglementAu,
			@RequestParam("dateReglementDu") String dateReglementDu,
			@RequestParam("dateEcheanceDu") String dateEcheanceDu,
			@RequestParam("dateEcheanceAu") String dateEcheanceAu,
			
			@RequestParam("dateDepotBanqueDe") String dateDepotBanqueDe,
			@RequestParam("dateDepotBanqueA") String dateDepotBanqueA,
			
			@RequestParam("numPiece") String numPiece,
			@RequestParam("regle") Boolean regle, @RequestParam("typeReglementId") Long typeReglementId,
			@RequestParam("avecTerme") Boolean avecTerme,
			@RequestParam("declarerRech") String declarerRech,
			
			
			@RequestParam("nomRapport") String nomRapport,
			
			HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+nomRapport + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet(nomRapport, 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 25);//groupe
		
		sheet3.setColumnView(4, 16);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(6, 20);

		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 20);
		sheet3.setColumnView(9, 10);
		
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 15);
		sheet3.setColumnView(12, 15);
		sheet3.setColumnView(13, 15);
		
		
		
		sheet3.setColumnView(14, 15);
		sheet3.setColumnView(15, 15);
		sheet3.setColumnView(16, 17);
		sheet3.setColumnView(17, 15);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "   "+nomRapport, ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 17, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

RechercheMulticritereDetailReglementValue request = new RechercheMulticritereDetailReglementValue();
		

request.setAvecTerme(avecTerme);
request.setNomRapport(nomRapport);
		if (isNotEmty(dateReglementDu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Reg. Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateReglementDu, ExcelUtils.boldRed3));

			request.setDateReglementDu(stringToCalendar(dateReglementDu));
		}

		if (isNotEmty(dateReglementAu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Reg. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateReglementAu, ExcelUtils.boldRed3));

			request.setDateReglementAu(stringToCalendar(dateReglementAu));
		}
		
		
		//date echeance
		
		if (isNotEmty(dateEcheanceDu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Ech. Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateEcheanceDu, ExcelUtils.boldRed3));

			request.setDateEcheanceDu(stringToCalendar(dateEcheanceDu));
		}
		
		if (isNotEmty(dateEcheanceAu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Ech. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateEcheanceAu, ExcelUtils.boldRed3));

			request.setDateEcheanceAu(stringToCalendar(dateEcheanceAu));
		}

		
		
		if (isNotEmty(dateDepotBanqueDe))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "D.Dep. Banque. Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateDepotBanqueDe, ExcelUtils.boldRed3));

			request.setDateDepotBanqueDe(stringToCalendar(dateDepotBanqueDe));
		}
		
		
	
		
		if (isNotEmty(dateDepotBanqueA))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "D.Dep. Banque. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateDepotBanqueA, ExcelUtils.boldRed3));

			request.setDateDepotBanqueA(stringToCalendar(dateDepotBanqueA));
		}
		
		
	

		

		if (isNotEmty(numPiece))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "N° Piece :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, numPiece, ExcelUtils.boldRed3));

			request.setNumPiece(numPiece);
		}
		
		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}
		
		
		

		if (isNotEmty(reference))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Reg.:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, reference, ExcelUtils.boldRed3));

			request.setReference(reference);
		}
		

		if (isNotEmty(referenceDetReglement))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Det. Reg.:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceDetReglement, ExcelUtils.boldRed3));

			request.setReferenceDetReglement(referenceDetReglement);
		}
		
	
		if (regle != null)

		{
			
			String regleDesignation = "" ;
			
			if(regle == true) regleDesignation = "OUI";
			else
				regleDesignation = "NON";
				
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Reglée :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, regleDesignation, ExcelUtils.boldRed3));

			request.setRegle(regle);
		}

		

		if (typeReglementId != null)

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeReglementService.getById(typeReglementId).getDesignation(), ExcelUtils.boldRed3));

			request.setTypeReglementId(typeReglementId);
		}
	

		if (isNotEmty(declarerRech))

		{
			/*numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Declaree :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, declarerRech, ExcelUtils.boldRed3));
*/
			request.setDeclarerRech(declarerRech);
		}


		EcheancierReportListValue report = gestionnaireReportGcService.getListEcheanceReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Transaction", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Paiement", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Type", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Client", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(7, i - 1, "Region", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "N° Piece", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Banque", ExcelUtils.boldRed2));
		
		
	
		
		
		sheet3.addCell(new Label(10, i - 1, "Montant", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(11, i - 1, "Echéance", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(12, i - 1, "Réglé", ExcelUtils.boldRed2));
		
		
		sheet3.addCell(new Label(13, i - 1, "Observation", ExcelUtils.boldRed2));
		
	
		sheet3.addCell(new Label(14, i - 1, "Banque Soc.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(15, i - 1, "D.Depot Banque", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(16, i - 1, "Charge Banque", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(17, i - 1, "TVA Banque", ExcelUtils.boldRed2));
		

		
		Double mantantTtcTotale = 0d;

		Double tvaBanque = 0d;
		
		Double chargeBanque = 0d;
		
		for (ResultatRechecheDetailReglementElementValue element : report.getEcheancierList()) {
			
			
			// date

			if (element.getDateReglement() != null) {
				sheet3.addCell(
						new Label(2, i, dateFormat2.format(element.getDateReglement().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			// transaction
			

			if (element.getReferenceDetReglement() != null) {
				sheet3.addCell(new Label(3, i, element.getReferenceDetReglement() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}
			
			// reglement
			

						if (element.getRefReglement() != null) {
							sheet3.addCell(new Label(4, i, element.getRefReglement() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));


						}
						
						
						// type reglement
						

						if (element.getTypeReglement() != null) {
							sheet3.addCell(new Label(5, i, element.getTypeReglement() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

						}
						
						
		// client abreviation
						

						if (element.getClientAbreviation() != null) {
							sheet3.addCell(new Label(6, i, element.getClientAbreviation() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

						}
						
						// client region				
						if (element.getClientRegion() != null) {
							sheet3.addCell(new Label(7, i, element.getClientRegion() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

						}
										
						// num piece				
						if (element.getNumPiece()!= null) {
							sheet3.addCell(new Label(8, i, element.getNumPiece() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

						}			
			
			
						//  banque				
						if (element.getBanque()!= null) {
							sheet3.addCell(new Label(9, i, element.getBanque() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

						}		
						
						// Mantant

						if (element.getMontant() != null) {
							
							mantantTtcTotale += element.getMontant();
							sheet3.addCell(new jxl.write.Number(10, i, convertisseur(element.getMontant(), 4) , ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
						}		
						
						
 
						// date echeance

						if (element.getDateEcheance() != null) {
							sheet3.addCell(
									new Label(11, i, dateFormat2.format(element.getDateEcheance().getTime()) + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
						}
						
						//reglee
										
						if (element.getRegle()!= null && element.getRegle() == true ) {
							sheet3.addCell(new Label(12, i, element.getNumPiece() + "OUI", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(12, i, "NON", ExcelUtils.boldRed));

						}	
						
						
						//  Observation				
						if (element.getObservation()!= null) {
							sheet3.addCell(new Label(13, i, element.getObservation() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(13, i, "", ExcelUtils.boldRed));

						}	
						
						
						
						
	
						
						
						//  banque	 soc			
						if (element.getBanqueSociete()!= null) {
							sheet3.addCell(new Label(14, i, element.getBanqueSociete() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(14, i, "", ExcelUtils.boldRed));

						}	
			
						// date depot banque

						if (element.getDateDepotBanque() != null) {
							sheet3.addCell(
									new Label(15, i, dateFormat2.format(element.getDateDepotBanque().getTime()) + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(15, i, "", ExcelUtils.boldRed));
						}
						
		
						
						// Charge Banque

						if (element.getChargeBanque() != null) {
							
							chargeBanque += element.getChargeBanque();
							
						
							sheet3.addCell(new jxl.write.Number(16, i, convertisseur(element.getChargeBanque(), 4) , ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(16, i, "", ExcelUtils.boldRed));
						}
						
						
						// "TVA Banque"
						
						if (element.getTvaBanque() != null) {
							
							tvaBanque += element.getTvaBanque();
							
							
							sheet3.addCell(new jxl.write.Number(17, i, convertisseur(element.getTvaBanque(), 4) , ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(17, i, "", ExcelUtils.boldRed));
						}
						
						
						
						
						
						
						

		

			i++;

		}

		i++;
		
		
		sheet3.addCell(new jxl.write.Number(16, i, +convertisseur(chargeBanque, 4), ExcelUtils.boldRed2));
		sheet3.addCell(new jxl.write.Number(17, i, +convertisseur(tvaBanque, 4), ExcelUtils.boldRed2));
		
		
		
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Montant Total : "+convertisseur(mantantTtcTotale, 4), ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);
		//sheet3.addCell(new Label(9, i, report.getEcheancierList().size() + "", ExcelUtils.boldRed2));


		i++;

		sheet3.addCell(new Label(7, i, "Nombre de Ligne : "+report.getEcheancierList().size(), ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);
		//sheet3.addCell(new Label(9, i, report.getEcheancierList().size() + "", ExcelUtils.boldRed2));

	

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}
	
	
	
	@RequestMapping(value = "/situation", method = RequestMethod.GET)
	public void generateListSituationReport(
			
			
			@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("regiontId") Long regionId,
			@RequestParam("deviseId") Long deviseId,
			@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("soldeMin") Double soldeMin,
			@RequestParam("soldeMax") Double soldeMax,
			@RequestParam("solde") Long solde,
			
			HttpServletResponse response)
			throws WriteException, IOException {



		Date d = new Date();

		String dat = "" + dateFormat.format(d);

	
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"SITUATION_LISTE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("SITUATION_LISTE", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 25);//groupe
		
		sheet3.setColumnView(4, 12);
		sheet3.setColumnView(5, 15);
		
		sheet3.setColumnView(6, 15);
	 
		sheet3.setColumnView(7, 15);

		sheet3.setColumnView(8, 20);
		sheet3.setColumnView(9, 20);
		
		
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 15);
		sheet3.setColumnView(11, 15);
		sheet3.setColumnView(12, 15);
		/**************************************************************************/

		
		// Nom du rapport et la date

		ExcelUtils.init();

	
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Situation Reporting", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 11, 8);


		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

	
		RechercheMulticritereSituationReportingValue request = new RechercheMulticritereSituationReportingValue();
	
	

		if (isNotEmty(dateMin))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date de :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateMin, ExcelUtils.boldRed3));

			request.setDateMin(stringToCalendar(dateMin));
		}

		
		if (isNotEmty(dateMax))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateMax, ExcelUtils.boldRed3));

			request.setDateMax(stringToCalendar(dateMax));
		}
		
		
		// solde 

		if (isNotEmty(soldeMin))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Solde de :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, soldeMin +"", ExcelUtils.boldRed3));

			request.setSoldeMin(soldeMin);
		}

		

		if (isNotEmty(soldeMax))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Solde A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, soldeMax +"", ExcelUtils.boldRed3));

			request.setSoldeMax(soldeMin);
		}
		
		
		if (isNotEmty(regionId))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Region :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, regionService.getById(regionId).getDesignation(), ExcelUtils.boldRed3));

			request.setRegionId(regionId);
		}
		
		
		
		


		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}

	

	

		
		if (isNotEmty(deviseId))

		{
			DeviseValue rechDevise = new DeviseValue();rechDevise.setId(deviseId);
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, deviseService.rechercheDeviseParId(rechDevise).getDesignation(), ExcelUtils.boldRed3));

			request.setDeviseId(deviseId);
		}



		SituationReportingReportListValue report = gestionnaireReportGcReportingService
				.getListSituationReport(request,solde);
		
		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2)); 
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Solde Init", ExcelUtils.boldRed2));
		
		//sheet3.addCell(new Label(5, i - 1, "Chif. d'affaire", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(5, i - 1, "CA / Facture", ExcelUtils.boldRed2));
		
		
		sheet3.addCell(new Label(6, i - 1, "Réglement", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Paiement en cours", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(8, i - 1, "Impayés/Date", ExcelUtils.boldRed2));
	//	sheet3.addCell(new Label(10, i - 1, "Solde Actuel", ExcelUtils.boldRed2));
		
		
		sheet3.addCell(new Label(9, i - 1, "Impayés Inv.", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(10, i - 1, "Solde Facturé", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(11, i - 1, "Région", ExcelUtils.boldRed2));
		
		Double soldeInitial = 0d;
		Double chiffreAffaire = 0d;
		Double reglement = 0d;
		Double payementEnCours = 0d;
		Double impaye = 0d;
		Double soldeActuel = 0d;
		Double impayeInverse = 0d;
		Double chiffreAffaireFacturee = 0d;
		Double soldeFacturee = 0d;

		for (SituationReportingValue element : report.getListSituation()) {

			if (element.getClientReference() != null) {
				sheet3.addCell(new Label(2, i, element.getClientReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getClientAbreviation() != null) {
				sheet3.addCell(new Label(3, i, element.getClientAbreviation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getSoldeInit() != null) {
				
				soldeInitial += element.getSoldeInit() ;
				
				sheet3.addCell(new jxl.write.Number(4, i, (double)Math.round( element.getSoldeInit()*1000)/1000  , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}
			
		/*	if (element.getChiffreDaffaire() != null) {
				
				chiffreAffaire += element.getChiffreDaffaire();
				
				sheet3.addCell(new jxl.write.Number(5, i, element.getChiffreDaffaire() , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

			}*/
			
	     if (element.getChiffreDaffaireFacturee() != null) {
				
				chiffreAffaireFacturee += element.getChiffreDaffaireFacturee();
				
				sheet3.addCell(new jxl.write.Number(5, i,(double)Math.round(element.getChiffreDaffaireFacturee()*1000)/1000 , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

			}
			
			if (element.getReglement() != null) {
				
				reglement += element.getReglement();
				sheet3.addCell(new jxl.write.Number(6, i,(double)Math.round( element.getReglement()*1000)/1000 , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

			}
			
			if (element.getPaiement() != null) {
				
				payementEnCours += element.getPaiement();
				sheet3.addCell(new jxl.write.Number(7, i, (double)Math.round(element.getPaiement()*1000)/1000 , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

			}
			
			
			if (element.getImpaye() != null) {
				impaye += element.getImpaye();
				sheet3.addCell(new jxl.write.Number(8, i,(double)Math.round( element.getImpaye()*1000)/1000 , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

			}

		/*	if (element.getSoldeActuel() != null) {
				soldeActuel += element.getSoldeActuel();
				
				sheet3.addCell(new jxl.write.Number(10, i, element.getSoldeActuel() , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));

			}*/
			
			
			
			
			if (element.getImpayeInverse() != null) {
				impayeInverse += element.getImpayeInverse();
				sheet3.addCell(new jxl.write.Number(9, i,(double)Math.round( element.getImpayeInverse()*1000)/1000 , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

			}
			
			
			if (element.getSoldeFacturee() != null) {
				soldeFacturee += element.getSoldeFacturee();
				
				sheet3.addCell(new jxl.write.Number(10, i, (double)Math.round( element.getSoldeFacturee()*1000)/1000 , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));

			}
			
			if (element.getRegionDesignation()!= null) {
				sheet3.addCell(new Label(11, i, element.getRegionDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
			}
			

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, report.getListSituation().size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Solde Initial", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(soldeInitial* 1000) / 1000 + "", ExcelUtils.boldRed2));

	/*	i++;

		sheet3.addCell(new Label(7, i, "Chiffre d'affaire", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(chiffreAffaire, 4)* 100) / 100 + "", ExcelUtils.boldRed2));
*/
		i++;
		
		sheet3.addCell(new Label(7, i, "CA / Facture", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(chiffreAffaireFacturee* 1000) / 1000 + "", ExcelUtils.boldRed2));

		i++;
		
		

		sheet3.addCell(new Label(7, i, "Réglement", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(reglement* 1000) / 1000  + "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Paiement en cours", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(payementEnCours* 1000) / 1000  + "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Impayés/Date", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(impaye* 1000) / 1000  + "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Impayés Inv.", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(impayeInverse* 1000) / 1000  + "", ExcelUtils.boldRed2));
		
		
		/*i++;

		sheet3.addCell(new Label(7, i, "Solde Actuel", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(soldeActuel, 4)* 100) / 100  + "", ExcelUtils.boldRed2));
		*/
		
		i++;

		sheet3.addCell(new Label(7, i, "Solde Facturé", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(soldeFacturee * 1000) / 1000  + "", ExcelUtils.boldRed2));
		

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());

		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value = "/listEcheanceInverse", method = RequestMethod.GET)
	public void generateListDetailReglementInverseReport(
			
			@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("reference") String reference,
			@RequestParam("referenceDetReglement") String referenceDetReglement,
			@RequestParam("partieIntId") Long partieIntId,
			@RequestParam("dateReglementAu") String dateReglementAu,
			@RequestParam("dateReglementDu") String dateReglementDu,
			@RequestParam("dateEcheanceDu") String dateEcheanceDu,
			@RequestParam("dateEcheanceAu") String dateEcheanceAu, @RequestParam("numPiece") String numPiece,
			@RequestParam("regle") Boolean regle, @RequestParam("typeReglementId") Long typeReglementId,
			@RequestParam("avecTerme") Boolean avecTerme, @RequestParam("nomRapport") String nomRapport,
			
			HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+nomRapport + "-Inverse-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet(nomRapport, 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 25);//groupe
		
		sheet3.setColumnView(4, 16);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(6, 20);

		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 20);
		sheet3.setColumnView(9, 10);
		
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 15);
		sheet3.setColumnView(12, 15);
		sheet3.setColumnView(13, 15);
		
		
		
		sheet3.setColumnView(14, 15);
		sheet3.setColumnView(15, 15);
		sheet3.setColumnView(16, 17);
		sheet3.setColumnView(17, 15);
		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active

		/*
		 * BaseInfoValue baseInfoValue = baseInfoPersistance.getClientActif(); String
		 * clientDesigntion = "";
		 * 
		 * if(baseInfoValue != null && baseInfoValue.getDesignation()!=null)
		 * clientDesigntion = baseInfoValue.getDesignation();
		 * 
		 * 
		 * sheet3.addCell(new Label(3, 2, "   " + clientDesigntion,
		 * ExcelUtils.TAHOMA_12_BOLD_CENTER)); sheet3.mergeCells(3, 2, 8, 2);
		 * 
		 * sheet3.addCell(new Label(2, 3,
		 * "Sarl au Capital de 48000 DT --RC Monastir B160681997-- Code TVA 38492L--Code Douane310429S"
		 * + "", ExcelUtils.ARIAL_10_NO_BOLD_CENTER));
		 * 
		 * 
		 * sheet3.mergeCells(2, 3, 9, 3); sheet3.addCell(new Label(2, 4,
		 * "Z. I. Route de Sousse BP 61-- 5012  SAHLINE - TUNISIE -" + "" + "", fn7));
		 * sheet3.mergeCells(2, 4, 9, 4); sheet3.addCell(new Label(2, 5,
		 * "Tél : 216 (73) 525188 Fax : 216 (73) 525214 E-mail : prodction.desdor@planet.tn"
		 * + "" + "" + "", fn7)); sheet3.mergeCells(2, 5, 9, 5);
		 * 
		 */
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "   "+nomRapport+" Inverse", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 17, 8);
		/*
		 * // sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d),
		 * boldRed3));
		 * 
		 * sheet3.addCell(new Label(2, 10, "Adressé à :" + "", ExcelUtils.boldRed3));
		 * sheet3.addCell(new Label(3, 10, bonLivraisonReport.getClient(),
		 * ExcelUtils.boldRed)); sheet3.mergeCells(3, 10, 4, 10);
		 * 
		 * sheet3.addCell(new Label(2, 11, "Adresse  :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(3, 11, 4, 12); sheet3.addCell(new Label(3, 11,
		 * bonLivraisonReport.getAdresse(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 10, "BL N° :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 10, 8, 10); sheet3.addCell(new Label(9, 10,
		 * bonLivraisonReport.getReference(), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(7, 11, "Date :", ExcelUtils.boldRed3));
		 * sheet3.mergeCells(7, 11, 8, 11); //sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(d), ExcelUtils.boldRed));
		 * 
		 * sheet3.addCell(new Label(9, 11,
		 * dateFormat2.format(bonLivraisonReport.getDateBl().getTime()),
		 * ExcelUtils.boldRed));
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

RechercheMulticritereDetailReglementValue request = new RechercheMulticritereDetailReglementValue();
		

request.setAvecTerme(avecTerme);
request.setNomRapport(nomRapport);
		if (isNotEmty(dateReglementDu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Reg. Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateReglementDu, ExcelUtils.boldRed3));

			request.setDateReglementDu(stringToCalendar(dateReglementDu));
		}

		if (isNotEmty(dateReglementAu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Reg. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateReglementAu, ExcelUtils.boldRed3));

			request.setDateReglementAu(stringToCalendar(dateReglementAu));
		}
		
		
		//date echeance
		
		if (isNotEmty(dateEcheanceDu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Ech. Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateEcheanceDu, ExcelUtils.boldRed3));

			request.setDateEcheanceDu(stringToCalendar(dateEcheanceDu));
		}
		
		if (isNotEmty(dateEcheanceAu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Ech. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateEcheanceAu, ExcelUtils.boldRed3));

			request.setDateEcheanceAu(stringToCalendar(dateEcheanceAu));
		}


		

		if (isNotEmty(numPiece))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "N° Piece :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, numPiece, ExcelUtils.boldRed3));

			request.setNumPiece(numPiece);
		}
		
		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}
		
		
		

		if (isNotEmty(reference))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Reg.:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, reference, ExcelUtils.boldRed3));

			request.setReference(reference);
		}
		

		if (isNotEmty(referenceDetReglement))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Det. Reg.:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceDetReglement, ExcelUtils.boldRed3));

			request.setReferenceDetReglement(referenceDetReglement);
		}
		
	
		if (regle != null)

		{
			
			String regleDesignation = "" ;
			
			if(regle == true) regleDesignation = "OUI";
			else
				regleDesignation = "NON";
				
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Reglée :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, regleDesignation, ExcelUtils.boldRed3));

			request.setRegle(regle);
		}

		

		if (typeReglementId != null)

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeReglementService.getById(typeReglementId).getDesignation(), ExcelUtils.boldRed3));

			request.setTypeReglementId(typeReglementId);
		}
	



		EcheancierReportListValue report = gestionnaireReportGcService.getListEcheanceInverseReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Transaction", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Paiement", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Type", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Client", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(7, i - 1, "Region", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "N° Piece", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Banque", ExcelUtils.boldRed2));
		
		
	
		
		
		sheet3.addCell(new Label(10, i - 1, "Montant", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(11, i - 1, "Echéance", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(12, i - 1, "Réglé", ExcelUtils.boldRed2));
		
		
		sheet3.addCell(new Label(13, i - 1, "Observation", ExcelUtils.boldRed2));
		
	
		sheet3.addCell(new Label(14, i - 1, "Banque Soc.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(15, i - 1, "D.Depot Banque", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(16, i - 1, "Charge Banque", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(17, i - 1, "TVA Banque", ExcelUtils.boldRed2));
		

		
		Double mantantTtcTotale = 0d;

		

		for (ResultatRechecheDetailReglementElementValue element : report.getEcheancierList()) {
			
			
			// date

			if (element.getDateReglement() != null) {
				sheet3.addCell(
						new Label(2, i, dateFormat2.format(element.getDateReglement().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			// transaction
			

			if (element.getReferenceDetReglement() != null) {
				sheet3.addCell(new Label(3, i, element.getReferenceDetReglement() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}
			
			// reglement
			

						if (element.getRefReglement() != null) {
							sheet3.addCell(new Label(4, i, element.getRefReglement() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));


						}
						
						
						// type reglement
						

						if (element.getTypeReglement() != null) {
							sheet3.addCell(new Label(5, i, element.getTypeReglement() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

						}
						
						
		// client abreviation
						

						if (element.getClientAbreviation() != null) {
							sheet3.addCell(new Label(6, i, element.getClientAbreviation() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

						}
						
						// client region				
						if (element.getClientRegion() != null) {
							sheet3.addCell(new Label(7, i, element.getClientRegion() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

						}
										
						// num piece				
						if (element.getNumPiece()!= null) {
							sheet3.addCell(new Label(8, i, element.getNumPiece() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

						}			
			
			
						//  banque				
						if (element.getBanque()!= null) {
							sheet3.addCell(new Label(9, i, element.getBanque() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

						}		
						
						// Mantant

						if (element.getMontant() != null) {
							
							mantantTtcTotale += element.getMontant();
							sheet3.addCell(new jxl.write.Number(10, i, convertisseur(element.getMontant(), 4) , ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
						}		
						
						
 
						// date echeance

						if (element.getDateEcheance() != null) {
							sheet3.addCell(
									new Label(11, i, dateFormat2.format(element.getDateEcheance().getTime()) + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
						}
						
						//reglee
										
						if (element.getRegle()!= null && element.getRegle() == true ) {
							sheet3.addCell(new Label(12, i, element.getNumPiece() + "OUI", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(12, i, "NON", ExcelUtils.boldRed));

						}	
						
						
						//  Observation				
						if (element.getObservation()!= null) {
							sheet3.addCell(new Label(13, i, element.getObservation() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(13, i, "", ExcelUtils.boldRed));

						}	
						
						
						
						
	
						
						
						//  banque	 soc			
						if (element.getBanqueSociete()!= null) {
							sheet3.addCell(new Label(14, i, element.getBanqueSociete() + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(14, i, "", ExcelUtils.boldRed));

						}	
			
						// date depot banque

						if (element.getDateDepotBanque() != null) {
							sheet3.addCell(
									new Label(15, i, dateFormat2.format(element.getDateDepotBanque().getTime()) + "", ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(15, i, "", ExcelUtils.boldRed));
						}
						
		
						
						// Charge Banque

						if (element.getChargeBanque() != null) {
							
						
							sheet3.addCell(new jxl.write.Number(16, i, convertisseur(element.getChargeBanque(), 4) , ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(16, i, "", ExcelUtils.boldRed));
						}
						
						
						// "TVA Banque"
						
						if (element.getTvaBanque() != null) {
							
							
							sheet3.addCell(new jxl.write.Number(17, i, convertisseur(element.getTvaBanque(), 4) , ExcelUtils.boldRed));

						} else {
							sheet3.addCell(new Label(17, i, "", ExcelUtils.boldRed));
						}
						
						
						
						
						
						
						

		

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Montant Total : "+convertisseur(mantantTtcTotale, 4), ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);
		//sheet3.addCell(new Label(9, i, report.getEcheancierList().size() + "", ExcelUtils.boldRed2));


		i++;

		sheet3.addCell(new Label(7, i, "Nombre de Ligne : "+report.getEcheancierList().size(), ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);
		//sheet3.addCell(new Label(9, i, report.getEcheancierList().size() + "", ExcelUtils.boldRed2));

	

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		// System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

		/*
		 * this.download(type, bonLivraisonReport.getReportStream(),
		 * bonLivraisonReport.getParams(), bonLivraisonReport.getFileName(),
		 * bonLivraisonReport.getJRBeanCollectionDataSource(), response);
		 */
	}
	
	

	private Calendar stringToCalendar(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}

	private Calendar stringDateTimeToCalendar(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}

	private Calendar calendarStringToCalendarObject(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_CALENDAR_FORMAT, Locale.ENGLISH);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}

	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value) && !value.equals("undefined") && !value.equals("null");
	}

	private boolean isNotEmty(Object value) {

		return value != null && !"".equals(value) && !"".equals(value.toString()) && !value.equals("undefined")
				&& !value.equals("null");
	}

	public Double convertisseur(Double d, int nbchiffre) {

		Double result = d;
		if (d != null) {
			String number = d.toString();
			int point = number.lastIndexOf('.');
			point += nbchiffre;
			String subnumber = "";
			if (number.length() >= point) {
				subnumber = number.substring(0, point);
			} else
				return result;
			try {
				result = Double.parseDouble(subnumber);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public boolean checkForOptimization(RechercheMulticritereBonLivraisonValue request) {

		return

		isNullOrEmpty(request.getDateLivraisonMin()) && isNullOrEmpty(request.getDateLivraisonMax())
				&& isNullOrEmpty(request.getPartieIntId()) && isNullOrEmpty(request.getGroupeClientId());

	}

	public boolean checkForOptimization(RechercheMulticritereFactureValue request) {

		return

		isNullOrEmpty(request.getDateFactureMin()) && isNullOrEmpty(request.getDateFactureMax())
		&& isNullOrEmpty(request.getDateEcheanceDe()) && isNullOrEmpty(request.getDateEcheanceA())
				&& isNullOrEmpty(request.getPartieIntId()) && isNullOrEmpty(request.getGroupeClientId());

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val) ;
	}
	
	private String getCritereSpecialProduitSerialisableForRepport(String  SritereSpecial) {
		String cs = null;
		switch (SritereSpecial) {
		case "produit-non-vendue":
			cs= "Article En Stock";
			break;

		default:
			break;
		}
		return cs;
	}
	@RequestMapping(value = "/listDetfacturevente", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generatelistDetFactureVenteReport(

			@RequestBody RechercheMulticritereDetFactureVenteValue request

	) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+"Liste_Det_Facture_Vente_" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		
			Equilibrageworkbook.createSheet("Liste_Det_Facture_Vente_", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);
		sheet3.setColumnView(3, 20);

		sheet3.setColumnView(3, 20);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 20);
		sheet3.setColumnView(6, 20);
		sheet3.setColumnView(7, 20);
		sheet3.setColumnView(8, 20);
		sheet3.setColumnView(9, 20);
		sheet3.setColumnView(10, 20);
		sheet3.setColumnView(11, 20);
		sheet3.setColumnView(12, 20);
		sheet3.setColumnView(13, 20);
		
		/**************************************************************************/

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

	 
			sheet3.addCell(new Label(2, 7, " Liste_Det_Facture_Vente", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 9, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getReferenceFacture()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Facture :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getReferenceFacture(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		if (isNotEmty(request.getPartieIntId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(request.getPartieIntId());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getReferenceBl()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Bl :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getReferenceBl(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getRefCommande()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Bc :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getRefCommande(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		
		
		
		if (isNotEmty(request.getDateFactureMin()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Facture De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateFactureMin().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateFactureMax()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Facture A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateFactureMax().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateEcheanceDe()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "DateEcheanceDe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateEcheanceDe().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateEcheanceA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "DateEcheanceA :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateEcheanceA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Ref produit :", ExcelUtils.boldRed3));
			
			ProduitValue produitValue= produitService.rechercheProduitById(request.getProduitId());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produitValue.getReference(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Designation produit :", ExcelUtils.boldRed3));
			
			ProduitValue produitValue= produitService.rechercheProduitById(request.getProduitId());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produitValue.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		
		if (isNotEmty(request.getQuantiteA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteA().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getQuantiteDe()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteDe().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		
		if (isNotEmty(request.getPrixMin()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getPrixMin().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getPrixMax()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getPrixMax().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		

		/*
		 * if (isNotEmty(request.getBoutiqueId()))
		 * 
		 * {
		 * 
		 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
		 * numLigneCritRech, "Boutique :", ExcelUtils.boldRed3)); sheet3.addCell(new
		 * Label(numColCritRech + 1, numLigneCritRech, request.getPrixMin() + "",
		 * ExcelUtils.boldRed3));
		 * 
		 * 
		 * }
		 */

		request.setOptimized(RechercheMulticritereDetFactureVenteValue.checkForOptimization(request));
		
		 
		
		ResultatRechecheDetFactureVenteValue result = detFactureVenteService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Réference Bc", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Réference BL", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Date Facture", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Date Echance", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Ref.Produit", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "produitDesignation", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(11, i - 1, "prix", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(12, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		




	  if(result.getList()!=null && result.getList().size()>0) {
	    	 
		
		for (DetFactureVenteValue element : result.getList()) {
			
			if(element.getReferenceFacture()!=null) {
				
				 
				sheet3.addCell(new Label(2, i, element.getReferenceFacture()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
				 

			}
			if(element.getPartieIntId()!=null) {
				
				 
				sheet3.addCell(new Label(3, i, element.getClientAbreviation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getCommandeReference()!=null) {
				
				 
				sheet3.addCell(new Label(4, i, element.getCommandeReference()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
				 

			}
			if(element.getReferenceBl()!=null) {
				
				 
				sheet3.addCell(new Label(5, i, element.getReferenceBl()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if (element.getDateFacture() != null) {
				sheet3.addCell(
						new Label(6, i, dateFormat2.format(element.getDateFacture().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				
				
				
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
				 

			}

			if (element.getDateEcheance() != null) {
				sheet3.addCell(
						new Label(7, i, dateFormat2.format(element.getDateEcheance().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
				 

			}
			if(element.getProduitReference()!=null) {
				
				 
				sheet3.addCell(new Label(8, i, element.getProduitReference()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getProduitDesignation()!=null) {
				
				 
				sheet3.addCell(new Label(9, i, element.getProduitDesignation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getQuantite()!=null) {
				
				 
				sheet3.addCell(new Label(10, i, element.getQuantite()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getPrixUnitaireHT()!=null) {
				
				 
				sheet3.addCell(new Label(11, i, element.getPrixUnitaireHT()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getPrixTotalHT()!=null) {
				
				 
				sheet3.addCell(new Label(12, i, element.getPrixTotalHT()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(12, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			

			i++;

		}
	}
		i++;
		i++;

		/*
		 * sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(7, i, 9, i);
		 * 
		 * i++;
		 */

		

	
	/*******************************************
	 * BAS DU PAGE
	 ****************************************/


	int numColBasDuPage = 2;
	int numLigneBasDuPage = i + 2;

	sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
	sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "nombre des lignes ", ExcelUtils.boldRed5));
	sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
			result.getNombreResultaRechercher() + "", ExcelUtils.boldRed3));

	numLigneBasDuPage++;

	sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);

	numLigneBasDuPage++;

	/*******************************************
	 * BAS DU PAGE
	 ****************************************/

	// int numColBasDuPage = 2;
	// int numLigneBasDuPage = i + 2;

	Equilibrageworkbook.write();
	Equilibrageworkbook.close();

	/******************************************************************************************/
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(new MediaType("application", "octet-stream"));

	Date now = new Date();
	String dateFormat1 = dateFormat.format(now);
	String filename = "list_Det_Factues_ventes" + dateFormat1 + ".xls";
	// String filename = "sortie-stock_" + dateFormat1 ;
	headers.setContentDispositionFormData(filename, filename);
	headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	return new ResponseEntity<>(fileOut.toByteArray(), headers, HttpStatus.OK);
	/******************************************************************************************/

	/****************************
	 * Ouvrir le nouveau fichier généré
	 *******************************/

	/*
	 * // context.getExternalContext().getResponse();
	 * response.setHeader("Content-disposition", "attachment; filename=" +
	 * f.getName()); // System.out.println("nom du fichier" + f.getName());
	 * response.setContentType("application/vnd.ms-excel"); int bufferSize = 64 *
	 * 1024;
	 */
}
	@RequestMapping(value = "/listProduitCommandesVente", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generatelistProduitsCommandesVenteReport(

			@RequestBody RechercheMulticritereProduitBonCommandeValue request

	) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+"Liste_Produit_Commandes_Vente_" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
			Equilibrageworkbook.createSheet("Liste_Produit_Commandes_Vente_", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);
		sheet3.setColumnView(3, 20);

		sheet3.setColumnView(3, 20);
		sheet3.setColumnView(4, 20);
		sheet3.setColumnView(5, 20);
		sheet3.setColumnView(6, 20);
		sheet3.setColumnView(7, 20);
		sheet3.setColumnView(8, 20);
		sheet3.setColumnView(9, 20);
		sheet3.setColumnView(10, 20);
		
		/**************************************************************************/

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

	 
			sheet3.addCell(new Label(2, 7, "    VENTE-Liste-Produit des Commandes", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 9, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getReference()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getReference(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		if (isNotEmty(request.getPartieInteresseId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(Long.parseLong(request.getPartieInteresseId()));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getLivre()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Livré :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getLivre(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		
		
		
		if (isNotEmty(request.getDateIntroductionDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date CMD De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateIntroductionDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateIntroductionDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date CMD A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateIntroductionDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateLivraisonDu()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonDu().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateLivraisonA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getIdProduit()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Ref produit :", ExcelUtils.boldRed3));
			
			ProduitValue produitValue= produitService.rechercheProduitById(request.getIdProduit());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produitValue.getReference(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getIdProduit()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Designation produit :", ExcelUtils.boldRed3));
			
			ProduitValue produitValue= produitService.rechercheProduitById(request.getIdProduit());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produitValue.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		
		
		if (isNotEmty(request.getQuantiteDu()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getQuantiteA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteA().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		
		if (isNotEmty(request.getPrixUnitaireDe()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getPrixUnitaireDe().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getPrixUnitaireA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getPrixUnitaireA().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}

		if (isNotEmty(request.getCoutDu()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getCoutDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getCoutA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout A:", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getCoutDu().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getDevise()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getDevise().toString(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}

		

		request.setOptimized(RechercheMulticritereProduitBonCommandeValue.checkForOptimization(request));
		
		 
		
		ResultatRechecheProduitBonCommandeValue result = produitCommandeService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date cmd", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date Liv.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Ref.Produit", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "produitDesignation", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "prix", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		




	  if(result.getListProduitCommandeValues()!=null && result.getListProduitCommandeValues().size()>0) {
	    	 
		
		for (ProduitCommandeValue element : result.getListProduitCommandeValues()) {
			
			if(element.getReferenceCommande()!=null) {
				
				 
				sheet3.addCell(new Label(2, i, element.getReferenceCommande()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getPartieIntersseAbbreviation()!=null) {
				
				 
				sheet3.addCell(new Label(3, i, element.getPartieIntersseAbbreviation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				 

			}
			
			if (element.getDateCommande() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDateCommande().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				
				
				
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
				 

			}

			if (element.getDateLivraison() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDateLivraison().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				 

			}
			if(element.getProduitReference()!=null) {
				
				 
				sheet3.addCell(new Label(6, i, element.getProduitReference()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getProduitDesignation()!=null) {
				
				 
				sheet3.addCell(new Label(7, i, element.getProduitDesignation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getQuantite()!=null) {
				
				 
				sheet3.addCell(new Label(8, i, element.getQuantite()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getPrixUnitaire()!=null) {
				
				 
				sheet3.addCell(new Label(9, i, element.getPrixUnitaire()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getMontantHTTotal()!=null) {
				
				 
				sheet3.addCell(new Label(10, i, element.getMontantHTTotal()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			
			 
			








 

			i++;

		}
	}
		i++;
		i++;

		/*
		 * sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(7, i, 9, i);
		 * 
		 * i++;
		 */

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "nombre des lignes ", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
				result.getNombreResultaRechercher() + "", ExcelUtils.boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);

		numLigneBasDuPage++;

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		// int numColBasDuPage = 2;
		// int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream"));

		Date now = new Date();
		String dateFormat1 = dateFormat.format(now);
		String filename = "list_Det_Factues_ventes" + dateFormat1 + ".xls";
		
		// String filename = "sortie-stock_" + dateFormat1 ;
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(fileOut.toByteArray(), headers, HttpStatus.OK);
		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		/*
		 * // context.getExternalContext().getResponse();
		 * response.setHeader("Content-disposition", "attachment; filename=" +
		 * f.getName()); // System.out.println("nom du fichier" + f.getName());
		 * response.setContentType("application/vnd.ms-excel"); int bufferSize = 64 *
		 * 1024;
		 */
	}

	
	
	
}