package com.gpro.consulting.tiers.logistique.rest.gc.achat.report.impl;

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
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
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
import com.gpro.consulting.tiers.commun.service.baseinfo.impl.BaseInfoServiceImpl;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IRegionService;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.report.facture.value.FactureAchatReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value.EcheancierReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;
import com.gpro.consulting.tiers.logistique.rest.report.utilities.ExcelUtils;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande.IBonCommandeAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IDetFactureAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IFactureAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IProduitReceptionAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IReceptionAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.IDetailsReglementAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.IElementReglementAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.ITypeReglementAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.report.IGestionnaireReportAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IElementReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService;
import com.gpro.consulting.tiers.logistique.service.gc.report.reporting.IGestionnaireReportGcReportingService;
import com.gpro.consulting.tiers.logistique.service.gc.typeReglement.ITypeReglementService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;
import com.gpro.consulting.tiers.logistique.service.produitdepot.IProduitDepotService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableImage;
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
@RequestMapping("/fichesAchat")
@SuppressWarnings("static-access")
public class GestionnaireFicheAchatRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireFicheAchatRestImpl.class);

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
	private IBaseInfoPersistance baseInfoPersistance;

	@Autowired
	private IPartieInteresseeService partieInteresseeService;
	
	@Autowired
	private IElementReglementService elementReglementService;
	
	
	@Autowired
	private IGroupeClientService groupeClientService;
	
	@Autowired
	private IProduitDepotService produitDepotService;
	
	@Autowired
	private IMagasinService magasinService ;
	
	@Autowired 
	private IProduitReceptionAchatService produitReceptionAchatService;
	
	@Autowired
	private IReceptionAchatService receptionAchatService;
	
	@Autowired
	private IFactureAchatService factureAchatService;
	
	@Autowired
	private IElementReglementAchatService elementReglementAchatService;
	
	@Autowired
	private IDetailsReglementAchatService detailsReglementAchatService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@Autowired
	private IBonCommandeAchatService bonCommandeService;
	
	@Autowired
	private IBaseInfoService baseInfoService;
	
	@Autowired
	private ITypeReglementAchatService typeReglementAchatService;
	
	

	@Autowired
	private IGestionnaireReportAchatService gestionnaireReportAchatService;
	
	
	@Autowired
	private IGestionnaireReportGcReportingService gestionnaireReportGcReportingService;
	
	
	@Autowired
	private IRegionService regionService;
	@Autowired
	private IDetFactureAchatService detFactureAchatService;
	@Autowired
	private IArticleService articleService;
	
	@RequestMapping(value = "/listCommandesAchat", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generatelistCommandesAchatReport(

			@RequestBody RechercheMulticritereBonCommandeAchatValue request

	) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Liste_Commandes_Achat_" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		
			Equilibrageworkbook.createSheet("Liste_Commandes_Achat_", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);
		sheet3.setColumnView(3, 20);

		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 15);
		sheet3.setColumnView(9, 15);

		/**************************************************************************/

		
		WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
		sheet3.addImage(image);


		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

	 
			sheet3.addCell(new Label(2, 7, "    ACHAT-Liste des Commandes", ExcelUtils.boldTitre));

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
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(Long.parseLong(request.getPartieInteresseId()));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		if (isNotEmty(request.getReception()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réception :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getReception(), ExcelUtils.boldRed3));
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
		
		 
		
		ResultatRechecheBonCommandeAchatValue result = bonCommandeService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Fournisseur", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date cmd", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date Liv.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant TTC", ExcelUtils.boldRed2));





	  if(result.getListCommandeAchat()!=null && result.getListCommandeAchat().size()>0) {
	    	 
		
		for (CommandeAchatValue element : result.getListCommandeAchat()) {
			
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
		String filename = "ACHAT-Liste-commandes_" + dateFormat1 + ".xls";
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

	
	
	
	@RequestMapping(value = "/listReceptionAchat", method = RequestMethod.GET)
	public void generateListReceptionAchatReport(
	
			@RequestParam("reference") String reference,
			@RequestParam("partieInteresseId") Long fournisseur,
	
			@RequestParam("dateIntroductionDu") String dateIntroductionDu,
			@RequestParam("dateIntroductionA") String dateIntroductionA,
			
			@RequestParam("quantiteDu") Double quantiteDu,
			@RequestParam("quantiteA") Double quantiteA,
			
			@RequestParam("idDepot") Long idDepot,
			
			@RequestParam("coutDu") Double coutDu,
			@RequestParam("coutA") Double coutA,
			
			
			
			HttpServletResponse response)throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Reception-Achat" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Reception-Achat", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 15);

		sheet3.setColumnView(3, 15);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 15);
		
		sheet3.setColumnView(6, 20);
		sheet3.setColumnView(7, 30);
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

		sheet3.addCell(new Label(2, 7, "   Liste des bons de reception", ExcelUtils.boldTitre));
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
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);
		numLigneCritRech++;


		RechercheMulticritereBonReceptionAchatValue request = new RechercheMulticritereBonReceptionAchatValue();
	
		request.setType(IConstanteCommerciale.RECEPTION_ACHAT_TYPE_ACHAT);
		
		if (isNotEmty(reference))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, reference, ExcelUtils.boldRed3));
			
	

			request.setReference(reference);
		}
		
		if (isNotEmty(fournisseur))

		{
			
		
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, 	partieInteresseeService.getById(fournisseur).getAbreviation(), ExcelUtils.boldRed3));

			request.setPartieInteresseId(fournisseur.toString());
		}
		
	

	
		
		
		if (isNotEmty(dateIntroductionDu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateIntroductionDu, ExcelUtils.boldRed3));

			request.setDateIntroductionDu(stringToCalendar(dateIntroductionDu));
		}

		if (isNotEmty(dateIntroductionA))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateIntroductionA, ExcelUtils.boldRed3));

			request.setDateIntroductionA(stringToCalendar(dateIntroductionA));
		}
		
		if (isNotEmty(idDepot))

		{
			MagasinValue mgValue = new MagasinValue();
			mgValue.setId(idDepot);
			mgValue = magasinService.rechercheMagasinParId(mgValue);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, mgValue.getDesignation(), ExcelUtils.boldRed3));

			request.setIdDepot(idDepot);
		}
		
		
		if (isNotEmty(quantiteDu))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, quantiteDu+"", ExcelUtils.boldRed3));
			
	

			request.setQuantiteDu(quantiteDu);
		}
		
		
		if (isNotEmty(quantiteA))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, quantiteA+"", ExcelUtils.boldRed3));
			
	

			request.setQuantiteA(quantiteA);
		}
		
		
		if (isNotEmty(coutDu))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout Du. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, coutDu+"", ExcelUtils.boldRed3));
			
	

			request.setCoutDu(coutDu);
		}
		
		
		if (isNotEmty(coutA))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout A. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, coutA+"", ExcelUtils.boldRed3));
			
	

			request.setCoutA(coutA);
		}
		
	
		
		ResultatRechecheBonReceptionAchatValue res = receptionAchatService.rechercherMultiCritere(request);
		Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
		

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réf. Bon Récep.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Fournisseur", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date Intro", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(5, i - 1, "Quantite", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Prix HTAXE", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Prix TTC", ExcelUtils.boldRed2));
		
		
	
		Double quantiteTotale = 0d;
		
		Double prixTotale = 0d;
		
	

		for (ReceptionAchatValue element : res.getListReceptionAchat()) {

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			
			


			if (element.getPartieIntersseId() != null) {
				
				PartieInteresseValue client = mapClientById.get(element.getPartieIntersseId());
				

				if (client != null) {
					sheet3.addCell(new Label(3, i,client.getAbreviation() + "", ExcelUtils.boldRed));
				} else {
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				}
				
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			
			// date intro

			if (element.getDateIntroduction() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDateIntroduction().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}
			
			
	
		
		

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(5, i, convertisseur(element.getQuantite(), 3) + "", ExcelUtils.boldRed));
				
				quantiteTotale += element.getQuantite();
			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getPrixTotal() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getPrixTotal(), 3) + "", ExcelUtils.boldRed));
				
				 prixTotale += element.getPrixTotal();
			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(7, i, convertisseur(element.getMontantTTC(), 3) + "", ExcelUtils.boldRed));
				
				 prixTotale += element.getPrixTotal();
			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}
			
		

			i++;

		}

		i++;
		
		sheet3.addCell(new Label(2, i, "Nbr Ligne", ExcelUtils.boldRed2));
		//	sheet3.mergeCells(7, i, 8, i);
			sheet3.addCell(new Label(3, i, res.getListReceptionAchat().size()+ "", ExcelUtils.boldRed2));
		
			i++;
		sheet3.addCell(new Label(2, i, "Qte Totale", ExcelUtils.boldRed2));
	//	sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, quantiteTotale+ "", ExcelUtils.boldRed2));

		
		i++;
		sheet3.addCell(new Label(2, i, "Prix Totale HTAXE", ExcelUtils.boldRed2));
	//	sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, convertisseur(prixTotale, 3)+ "", ExcelUtils.boldRed2));


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



	@RequestMapping(value = "/reception-achat-detail-list", method = RequestMethod.GET)
	public void generateListReceptionAchatDetailReport(
	
			@RequestParam("reference") String reference,
			@RequestParam("partieInteresseId") Long fournisseur,
			@RequestParam("produitId") Long produitId,
			@RequestParam("dateIntroductionDu") String dateIntroductionDu,
			@RequestParam("dateIntroductionA") String dateIntroductionA,
			
			@RequestParam("quantiteDu") Double quantiteDu,
			@RequestParam("quantiteA") Double quantiteA,
			
			@RequestParam("idDepot") Long idDepot,
			
			@RequestParam("coutDu") Double coutDu,
			@RequestParam("coutA") Double coutA,
			
			
			
			HttpServletResponse response)throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Reception-Achat-Detail" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Reception-Achat-Detail", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 15);

		sheet3.setColumnView(3, 15);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 15);
		
		sheet3.setColumnView(6, 20);
		sheet3.setColumnView(7, 30);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		sheet3.setColumnView(10, 15);
		


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

		sheet3.addCell(new Label(2, 7, "  Liste Reception Achat Detail", ExcelUtils.boldTitre));
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
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);
		numLigneCritRech++;


		RechercheMulticritereProduitReceptionAchatValue request = new RechercheMulticritereProduitReceptionAchatValue();
	
	
		
		if (isNotEmty(reference))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, reference, ExcelUtils.boldRed3));
			
	

			request.setReference(reference);
		}
		
		if (isNotEmty(fournisseur))

		{
			
		
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, 	partieInteresseeService.getById(fournisseur).getAbreviation(), ExcelUtils.boldRed3));

			request.setPartieInteresseId(fournisseur.toString());
		}
		
	

		if (isNotEmty(produitId))

		{
			
		 
			ProduitValue produit = produitService.rechercheProduitById(produitId);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Article Réf. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, produit.getReference(), ExcelUtils.boldRed3));
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Article Dés. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, produit.getDesignation(), ExcelUtils.boldRed3));


			request.setProduitId(produitId);
		}
		
		
		if (isNotEmty(dateIntroductionDu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateIntroductionDu, ExcelUtils.boldRed3));

			request.setDateIntroductionDu(stringToCalendar(dateIntroductionDu));
		}

		if (isNotEmty(dateIntroductionA))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateIntroductionA, ExcelUtils.boldRed3));

			request.setDateIntroductionA(stringToCalendar(dateIntroductionA));
		}
		
		if (isNotEmty(idDepot))

		{
			MagasinValue mgValue = new MagasinValue();
			mgValue.setId(idDepot);
			mgValue = magasinService.rechercheMagasinParId(mgValue);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, mgValue.getDesignation(), ExcelUtils.boldRed3));

			request.setIdDepot(idDepot);
		}
		
		
		if (isNotEmty(quantiteDu))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, quantiteDu+"", ExcelUtils.boldRed3));
			
	

			request.setQuantiteDu(quantiteDu);
		}
		
		
		if (isNotEmty(quantiteA))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, quantiteA+"", ExcelUtils.boldRed3));
			
	

			request.setQuantiteA(quantiteA);
		}
		
		
		if (isNotEmty(coutDu))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout Du. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, coutDu+"", ExcelUtils.boldRed3));
			
	

			request.setCoutDu(coutDu);
		}
		
		
		if (isNotEmty(coutA))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout A. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, coutA+"", ExcelUtils.boldRed3));
			
	

			request.setCoutA(coutA);
		}
		
	
		
		ResultatRechecheProduitReceptionAchatValue res = produitReceptionAchatService.rechercherMultiCritere(request);

		

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réf. Bon Récep.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Fournisseur", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date Intro", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Date Recep.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Réf. Article", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Quantite", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Prix", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Prix Totale", ExcelUtils.boldRed2));
		
		
	
		Double quantiteTotale = 0d;
		Double prixTotale = 0d;
		
		Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

		for (ProduitReceptionAchatValue element : res.getListProduitReceptionAchat()) {

			if (element.getReceptionAchatValue().getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReceptionAchatValue().getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			String fournisseurDesignation = "";
			if (element.getReceptionAchatValue().getPartieIntersseId() != null) {
				
				PartieInteresseValue client = mapClientById.get(element.getReceptionAchatValue().getPartieIntersseId());

				if (client != null && client.getAbreviation()!=null) {
					fournisseurDesignation = client.getAbreviation();
				}
			}


	    	sheet3.addCell(new Label(3, i, fournisseurDesignation + "", ExcelUtils.boldRed));

		
			
			
			
			// date intro

			if (element.getReceptionAchatValue().getDateIntroduction() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getReceptionAchatValue().getDateIntroduction().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}
			
			
			// date reception

			if (element.getReceptionAchatValue().getDateLivraison() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getReceptionAchatValue().getDateLivraison().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// prod reference
			
			
			if (element.getArticleValue().getRef() != null) {
				sheet3.addCell(new Label(6, i, element.getArticleValue().getRef() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

			}
			
			//prod designation
			
			
			if (element.getArticleValue().getDesignation() != null) {
				sheet3.addCell(new Label(7, i, element.getArticleValue().getDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

			}
		

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(8, i, convertisseur(element.getQuantite(), 3) + "", ExcelUtils.boldRed));
				
				quantiteTotale += element.getQuantite();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getPrixUnitaire() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getPrixUnitaire(), 3) + "", ExcelUtils.boldRed));
				
				quantiteTotale += element.getQuantite();
			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}
			
			
			if (element.getPrixUnitaire() != null && element.getQuantite() != null) {
				Double totalePrice = element.getPrixUnitaire() * element.getQuantite();
				prixTotale += totalePrice;
				
				sheet3.addCell(new Label(10, i, convertisseur(totalePrice, 3) + "", ExcelUtils.boldRed));
				
		
				
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}

		

			i++;

		}

		i++;
		
		sheet3.addCell(new Label(2, i, "Nbr Ligne", ExcelUtils.boldRed2));
		//	sheet3.mergeCells(7, i, 8, i);
			sheet3.addCell(new Label(3, i, res.getListProduitReceptionAchat().size()+ "", ExcelUtils.boldRed2));
		
			i++;
		sheet3.addCell(new Label(2, i, "Qte Totale", ExcelUtils.boldRed2));
	//	sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, quantiteTotale+ "", ExcelUtils.boldRed2));
		
		i++;
		sheet3.addCell(new Label(2, i, "Prix Totale", ExcelUtils.boldRed2));
	//	sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, convertisseur(prixTotale, 3)+ "", ExcelUtils.boldRed2));

		


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
	public void generateListFactureAchatReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("referenceFacture") String referenceFacture, @RequestParam("referenceBl") String referenceBl,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateFactureMin") String dateFactureMin,
			@RequestParam("dateFactureMax") String dateFactureMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("typeFacture") String typeFacture,
			@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("groupeClientId") Long groupeClientId,
			HttpServletResponse response)throws WriteException, IOException {

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

		sheet3.addCell(new Label(2, 7, "    Liste des Factures", ExcelUtils.boldTitre));
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
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereFactureAchatValue request = new RechercheMulticritereFactureAchatValue();

	
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
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					groupeClientService.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);

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
			sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);

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

	/*	if (isNotEmty(typeFacture))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type Facture :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeFacture + "", ExcelUtils.boldRed3));

			request.setType(typeFacture);
		}
		
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
		
		
		ResultatRechecheFactureAchatValue resultatRech = factureAchatService.rechercherMultiCritere(request);

		//FactureReportListValue report = gestionnaireReportGcService.getListFactureReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Qte Totale", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		
		
		
		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;

		Map<Long, PartieInteresseValue>  mapsFournisseur= 	gestionnaireLogistiqueCacheService.mapClientById();
		
		for (FactureAchatValue element : resultatRech.getList()) {

			String abreviationFournisseur = "";
			if (element.getPartieIntId() != null) {
			
				PartieInteresseValue pi = mapsFournisseur.get(element.getPartieIntId());
				
                      if(pi != null && pi.getAbreviation() != null)
                    	  abreviationFournisseur=pi.getAbreviation();

			} 
			
			sheet3.addCell(new Label(2, i, abreviationFournisseur + "", ExcelUtils.boldRed));

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
			
			// quantite

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(5, i, convertisseur(element.getMetrageTotal(), 3) + "", ExcelUtils.boldRed));

			      quantiteTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getMontantHTaxe(), 4) + "", ExcelUtils.boldRed));

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


			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getMontantTTC(), 4) + "", ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
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
		sheet3.addCell(new Label(9, i, resultatRech.getList().size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantTtcTotale, 4) + "", ExcelUtils.boldRed2));
		
		i++;

		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(remiseTotale,4)+ "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale+ "", ExcelUtils.boldRed2));

		
		


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
	
	
	@RequestMapping(value = "/br-non-paye", method = RequestMethod.GET)
	public void generateListBonReceptionNonPayeReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereBonLivraisonValue
			// request,
			@RequestParam("referenceBl") String referenceBl, @RequestParam("referenceBs") String referenceBs,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateLivraisonMin") String dateLivraisonMin,
			@RequestParam("dateLivraisonMax") String dateLivraisonMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("avecFacture") String avecFacture, @RequestParam("groupeClientId") Long groupeClientId,
			@RequestParam("idDepot") Long idDepot, HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"BR_LISTE_NON_PAYEE" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("BR-LIST-NON-PAYEE", 0);
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

		sheet3.addCell(new Label(2, 7, "    Liste des Bons de réception Non Payées", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 8, 8);
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
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereBonReceptionAchatValue request = new RechercheMulticritereBonReceptionAchatValue();

		//if(isNotEmty(referenceBs))
	//	request.setReferenceBs(referenceBs);

	//	if(isNotEmty(natureLivraison))
		//request.setNatureLivraison(natureLivraison);

		if(isNotEmty(idDepot))
		request.setIdDepot(idDepot);

		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BR :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, referenceBl, ExcelUtils.boldRed3));

			request.setReference(referenceBl);
			
			//request.setReferenceBl(referenceBl);
		}
		
		
		/*if (isNotEmty(groupeClientId))

		{

		

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					groupeClientService.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}*/

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);

			request.setPartieInteresseId(partieIntId.toString());
		}

		if (isNotEmty(dateLivraisonMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMin, ExcelUtils.boldRed3));

			request.setDateLivraisonA(stringToCalendar(dateLivraisonMin));
		}

		if (isNotEmty(dateLivraisonMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateLivraisonMax, ExcelUtils.boldRed3));

			request.setDateLivraisonDu(stringToCalendar(dateLivraisonMax));
		}

		if (isNotEmty(metrageMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMax + "", ExcelUtils.boldRed3));

			request.setQuantiteA(metrageMax);
		}

		if (isNotEmty(metrageMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageMin + "", ExcelUtils.boldRed3));

			request.setQuantiteDu(metrageMin);
			//request.setMetrageMin(metrageMin);
		}

		if (isNotEmty(prixMin))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMin + "", ExcelUtils.boldRed3));

			
			request.setCoutDu(prixMin);
		}

		if (isNotEmty(prixMax))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixMax + "", ExcelUtils.boldRed3));

			request.setCoutA(prixMax);
		}

		if (isNotEmty(avecFacture))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "A facturer :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, avecFacture + "", ExcelUtils.boldRed3));

			request.setFacture(avecFacture);
		}

		ResultatRechecheBonReceptionAchatValue report = receptionAchatService.rechercherMultiCritere(request);
		
		//BonLivraisonReportListValue report = gestionnaireReportGcService.getListBonlivraisonReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant TTC", ExcelUtils.boldRed2));	
		sheet3.addCell(new Label(8, i - 1, "Montant réglé", ExcelUtils.boldRed2));

		//sheet3.addCell(new Label(9, i - 1, "Quantite Totale", ExcelUtils.boldRed2));
		
		
		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;
		
		
		List<ReceptionAchatValue> listBRNonPaye = new ArrayList<>();
		for (ReceptionAchatValue element : report.getListReceptionAchat()) {
			
			//if( !elementReglementAchatService.existElementReglementByReferenceBR(element.getReference()))
			//	listBRNonPaye.add(element);
			
			
	     	Double montantPayee = elementReglementAchatService.getSumMontantPayerByReferenceBL(element.getReference()) ;
			
			
			
				element.setMetrageTotal(montantPayee);
				
				
				if(montantPayee < element.getMontantTTC() )
					listBRNonPaye.add(element);
		}
		
		Map<Long, PartieInteresseValue>  mapsFournisseur= 	gestionnaireLogistiqueCacheService.mapClientById();
		
		for (ReceptionAchatValue element : listBRNonPaye) {
			
			
			
			String abreviationFournisseur = "";
			if (element.getPartieIntersseId() != null) {
			
				PartieInteresseValue pi = mapsFournisseur.get(element.getPartieIntersseId());
				
                      if(pi != null && pi.getAbreviation() != null)
                    	  abreviationFournisseur=pi.getAbreviation();

			} 
			
			sheet3.addCell(new Label(2, i, abreviationFournisseur + "", ExcelUtils.boldRed));
			
			


			if (element.getReference() != null) {
				sheet3.addCell(new Label(3, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDateLivraison() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDateLivraison().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// Mantant HT

			if (element.getMontantHTaxe() != null) {
				sheet3.addCell(new jxl.write.Number(5, i, convertisseur(element.getMontantHTaxe(), 4) , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new jxl.write.Number(6, i, convertisseur(element.getMontantTaxe(), 4) , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new jxl.write.Number(7, i, convertisseur(element.getMontantTTC(), 4) , ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

		
			// Reglee

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new Label(8, i, convertisseur(element.getMetrageTotal(), 4) + "", ExcelUtils.boldRed));

				remiseTotale += element.getMetrageTotal();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}


			// Mantat Remise

		/*	if (element.getMontantRemise() != null) {
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
			}*/

			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, listBRNonPaye.size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantTtcTotale, 4) + "", ExcelUtils.boldRed2));
		
		i++;

		sheet3.addCell(new Label(7, i, "Montant Réglé Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(remiseTotale, 4) + "", ExcelUtils.boldRed2));
		
	/*	i++;

		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(remiseTotale, 4) + "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale+ "", ExcelUtils.boldRed2));

		*/
		


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
	public void generateListFactureAchatNonPayeReport(@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("referenceFacture") String referenceFacture, @RequestParam("referenceBl") String referenceBl,
			@RequestParam("partieIntId") Long partieIntId, @RequestParam("dateFactureMin") String dateFactureMin,
			@RequestParam("dateFactureMax") String dateFactureMax, @RequestParam("metrageMin") Double metrageMin,
			@RequestParam("metrageMax") Double metrageMax, @RequestParam("prixMin") Double prixMin,
			@RequestParam("prixMax") Double prixMax, @RequestParam("typeFacture") String typeFacture,
			@RequestParam("natureLivraison") String natureLivraison,
			@RequestParam("groupeClientId") Long groupeClientId,
			
			HttpServletResponse response)throws WriteException, IOException {

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

		sheet3.setColumnView(3, 16);
		sheet3.setColumnView(4, 16);
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
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);
		numLigneCritRech++;

		RechercheMulticritereFactureAchatValue request = new RechercheMulticritereFactureAchatValue();

	
		request.setType(typeFacture);
	

		if (isNotEmty(referenceBl))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BR :", ExcelUtils.boldRed3));
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
		
	/*	if (isNotEmty(groupeClientId))

		{

		

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					groupeClientService.rechercheGroupeClientParId(new GroupeClientValue(groupeClientId)).getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);

			request.setGroupeClientId(groupeClientId);
		}*/

		if (isNotEmty(partieIntId))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(partieIntId);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);

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

	/*	if (isNotEmty(typeFacture))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type Facture :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeFacture + "", ExcelUtils.boldRed3));

			request.setType(typeFacture);
		}
		
	*/
		
		if (isNotEmty(natureLivraison))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Nature Livraison :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, natureLivraison + "", ExcelUtils.boldRed3));

			request.setNatureLivraison(natureLivraison);
		}

		ResultatRechecheFactureAchatValue report = factureAchatService.rechercherMultiCritere(request);
	//	FactureAchatReportListValue report = gestionnaireReportGcService.getListFactureAchatReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(4, i - 1, "Réf. Fourn.", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(5, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Montant HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Montant Taxe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant Réglé", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(10, i - 1, "Reste", ExcelUtils.boldRed2));


		//sheet3.addCell(new Label(8, i - 1, "Montant Remise", ExcelUtils.boldRed2));

		//sheet3.addCell(new Label(9, i - 1, "Quantite Totale", ExcelUtils.boldRed2));
		
		
		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;
		
		
		Double mantantRegleTotale = 0d;
		Double mantantResteTotale = 0d;

		
		Map<Long, PartieInteresseValue>  mapsFournisseur= 	gestionnaireLogistiqueCacheService.mapClientById();
		

		
		List<FactureAchatValue> listFactureNonPaye = new ArrayList<FactureAchatValue>();
		for (FactureAchatValue element : report.getList()) {
			
			//if( !elementReglementAchatService.existElementReglementByReferenceFacture(element.getReference()))
		      //		listFactureNonPaye.add(element);
			
			
     	Double montantPayee = elementReglementAchatService.getSumMontantPayerByReferenceFacture(element.getReference()) ;
			
			
			
			element.setMetrageTotal(montantPayee);
			
			
			if((montantPayee < element.getMontantTTC()) && !hasFactureAvoir(element.getReference()) ) {
				listFactureNonPaye.add(element);
			}
			
		}

		for (FactureAchatValue element : listFactureNonPaye) {
			
			
			String abreviationFournisseur = "";
			if (element.getPartieIntId() != null) {
			
				PartieInteresseValue pi = mapsFournisseur.get(element.getPartieIntId());
				
                      if(pi != null && pi.getAbreviation() != null)
                    	  abreviationFournisseur=pi.getAbreviation();

			} 
			
			sheet3.addCell(new Label(2, i, abreviationFournisseur + "", ExcelUtils.boldRed));

			

			if (element.getReference() != null) {
				sheet3.addCell(new Label(3, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}
			
			
			
			if (element.getRefexterne() != null) {
				sheet3.addCell(new Label(4, i, element.getRefexterne() + "", ExcelUtils.boldRed));

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
				sheet3.addCell(new jxl.write.Number(6, i, convertisseur(element.getMontantHTaxe(), 4) , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			
			// Mantant Taxe

			if (element.getMontantTaxe() != null) {
				sheet3.addCell(new jxl.write.Number(7, i, convertisseur(element.getMontantTaxe(), 4) , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new jxl.write.Number(8, i, convertisseur(element.getMontantTTC(), 4) , ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			
			// Mantant regle

			if (element.getMetrageTotal() != null) {
				sheet3.addCell(new jxl.write.Number(9, i, convertisseur(element.getMetrageTotal(), 3) , ExcelUtils.boldRed));

				mantantRegleTotale += element.getMetrageTotal();
				
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
		

			// Mantat Remise

		/*	if (element.getMontantRemise() != null) {
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

		sheet3.addCell(new Label(7, i, "Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 9, i);

		i++;

		sheet3.addCell(new Label(7, i, "Ligne", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, listFactureNonPaye.size() + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new jxl.write.Number(9, i, convertisseur(mantantTtcTotale, 3) , ExcelUtils.boldRed2));
		
		i++;
		
		sheet3.addCell(new Label(7, i, "Montant Reglé Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new jxl.write.Number(9, i, convertisseur(mantantRegleTotale, 3) , ExcelUtils.boldRed2));
		
		i++;
		

		sheet3.addCell(new Label(7, i, "Montant Reste Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new jxl.write.Number(9, i, convertisseur(mantantResteTotale, 3) , ExcelUtils.boldRed2));

		/*sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, remiseTotale+ "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale+ "", ExcelUtils.boldRed2));

		*/
		


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
	
	

	@RequestMapping(value = "/listReceptionRetour", method = RequestMethod.GET)
	public void generateListReceptionRetourReport(
	
			@RequestParam("reference") String reference,
			@RequestParam("partieInteresseId") Long fournisseur,
	
			@RequestParam("dateIntroductionDu") String dateIntroductionDu,
			@RequestParam("dateIntroductionA") String dateIntroductionA,
			
			@RequestParam("quantiteDu") Double quantiteDu,
			@RequestParam("quantiteA") Double quantiteA,
			
			@RequestParam("idDepot") Long idDepot,
			
			@RequestParam("coutDu") Double coutDu,
			@RequestParam("coutA") Double coutA,
			
			
			
			HttpServletResponse response)throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Stock-Retour" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Stock-Retour", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 15);

		sheet3.setColumnView(3, 15);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 15);
		
		sheet3.setColumnView(6, 20);
		sheet3.setColumnView(7, 30);
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

		sheet3.addCell(new Label(2, 7, "   Liste Stock Retour", ExcelUtils.boldTitre));
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
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech +1, numLigneCritRech, numColCritRech +2, numLigneCritRech);
		numLigneCritRech++;


		RechercheMulticritereBonReceptionAchatValue request = new RechercheMulticritereBonReceptionAchatValue();
	
		request.setType(IConstanteCommerciale.RECEPTION_ACHAT_TYPE_RETOUR);
		
		if (isNotEmty(reference))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, reference, ExcelUtils.boldRed3));
			
	

			request.setReference(reference);
		}
		
		if (isNotEmty(fournisseur))

		{
			
		
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, 	partieInteresseeService.getById(fournisseur).getAbreviation(), ExcelUtils.boldRed3));

			request.setPartieInteresseId(fournisseur.toString());
		}
		
	

	
		
		
		if (isNotEmty(dateIntroductionDu))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateIntroductionDu, ExcelUtils.boldRed3));

			request.setDateIntroductionDu(stringToCalendar(dateIntroductionDu));
		}

		if (isNotEmty(dateIntroductionA))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateIntroductionA, ExcelUtils.boldRed3));

			request.setDateIntroductionA(stringToCalendar(dateIntroductionA));
		}
		
		if (isNotEmty(idDepot))

		{
			MagasinValue mgValue = new MagasinValue();
			mgValue.setId(idDepot);
			mgValue = magasinService.rechercheMagasinParId(mgValue);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, mgValue.getDesignation(), ExcelUtils.boldRed3));

			request.setIdDepot(idDepot);
		}
		
		
		if (isNotEmty(quantiteDu))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, quantiteDu+"", ExcelUtils.boldRed3));
			
	

			request.setQuantiteDu(quantiteDu);
		}
		
		
		if (isNotEmty(quantiteA))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, quantiteA+"", ExcelUtils.boldRed3));
			
	

			request.setQuantiteA(quantiteA);
		}
		
		
		if (isNotEmty(coutDu))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout Du. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, coutDu+"", ExcelUtils.boldRed3));
			
	

			request.setCoutDu(coutDu);
		}
		
		
		if (isNotEmty(coutA))

		{
			
		 

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Cout A. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, coutA+"", ExcelUtils.boldRed3));
			
	

			request.setCoutA(coutA);
		}
		
	
		
		ResultatRechecheBonReceptionAchatValue res = receptionAchatService.rechercherMultiCritere(request);
		Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
		

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réf.", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date Intro", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(5, i - 1, "Date", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(6, i - 1, "Réf. Avoir", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Réf. Facture", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Réf. BL", ExcelUtils.boldRed2));

		
		sheet3.addCell(new Label(9, i - 1, "Quantite", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Prix HTAXE", ExcelUtils.boldRed2));
		
		
	
		Double quantiteTotale = 0d;
		
		Double prixTotale = 0d;
		
	

		for (ReceptionAchatValue element : res.getListReceptionAchat()) {

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			
			


			if (element.getPartieIntersseId() != null) {
				
				PartieInteresseValue client = mapClientById.get(element.getPartieIntersseId());
				

				if (client != null) {
					sheet3.addCell(new Label(3, i,client.getAbreviation() + "", ExcelUtils.boldRed));
				} else {
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				}
				
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			
			// date intro

			if (element.getDateIntroduction() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDateIntroduction().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}
			
		
			// date

			if (element.getDateLivraison() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDateLivraison().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
	
		
			if (element.getRefAvoirRetour() != null) {
				sheet3.addCell(new Label(6, i, element.getRefAvoirRetour() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getRefFacture() != null) {
				sheet3.addCell(new Label(7, i, element.getRefFacture() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getRefBL() != null) {
				sheet3.addCell(new Label(8, i, element.getRefBL() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}
		

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getQuantite(), 3) + "", ExcelUtils.boldRed));
				
				quantiteTotale += element.getQuantite();
			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getPrixTotal() != null) {
				sheet3.addCell(new Label(10, i, convertisseur(element.getPrixTotal(), 3) + "", ExcelUtils.boldRed));
				
				 prixTotale += element.getPrixTotal();
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}
			
		

			i++;

		}

		i++;
		
		sheet3.addCell(new Label(2, i, "Nbr Ligne", ExcelUtils.boldRed2));
		//	sheet3.mergeCells(7, i, 8, i);
			sheet3.addCell(new Label(3, i, res.getListReceptionAchat().size()+ "", ExcelUtils.boldRed2));
		
			i++;
		sheet3.addCell(new Label(2, i, "Qte Totale", ExcelUtils.boldRed2));
	//	sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, quantiteTotale+ "", ExcelUtils.boldRed2));

		
		i++;
		sheet3.addCell(new Label(2, i, "Prix Totale HTAXE", ExcelUtils.boldRed2));
	//	sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, convertisseur(prixTotale, 3)+ "", ExcelUtils.boldRed2));


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
			
			@RequestBody  RechercheMulticritereReglementAchatValue request
							
		) throws WriteException, IOException {


		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"detail_reglement_achat" + "-" + dat + ".xls");

		
	       ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
	        WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);
		
	//	WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Det reglement Achat", 0);
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

		sheet3.addCell(new Label(2, 7, "    Liste des détailles Reglements Achat", ExcelUtils.boldTitre));
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
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
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
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf BR:", ExcelUtils.boldRed3));
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
		
		
		
		
		ResultatRechecheElementReglementAchatValue result;
		
		
		if(estNonVide(request.getNumPiece()))  {
			
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Numero Piece :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getNumPiece() + "", ExcelUtils.boldRed3));

			
	         result = detailsReglementAchatService.rechercherMultiCritere(request);
			
			
		}else
			
			
		{
			
			result = elementReglementAchatService.rechercherMultiCritere(request);
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
		sheet3.addCell(new Label(3, i - 1, "Fournisseur", ExcelUtils.boldRed2));
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
		
		
		 

		
		

		for (ResultatRechecheElementReglementAchatElementValue element : result.getList()) {
			
			  
			

			 

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
			@RequestParam("avecTerme") Boolean avecTerme, @RequestParam("nomRapport") String nomRapport,
			
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
		sheet3.setColumnView(12, 17);
		sheet3.setColumnView(13, 15);
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
		sheet3.mergeCells(2, 7, 13, 8);
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

		RechercheMulticritereDetailReglementAchatValue request = new RechercheMulticritereDetailReglementAchatValue();
		

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
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeReglementAchatService.getById(typeReglementId).getDesignation(), ExcelUtils.boldRed3));

			request.setTypeReglementId(typeReglementId);
		}
	


		EcheancierReportListValue report = gestionnaireReportAchatService.getListEcheanceReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Transaction", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Paiement", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Type", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Fournisseur", ExcelUtils.boldRed2));
		
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
							sheet3.addCell(new Label(10, i, convertisseur(element.getMontant(), 4) + "", ExcelUtils.boldRed));

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
		File f = new File(excel_file_location+nomRapport +"-Inverse"+ "-" + dat + ".xls");

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
		sheet3.setColumnView(12, 17);
		sheet3.setColumnView(13, 15);
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

		sheet3.addCell(new Label(2, 7, "   "+nomRapport + " Inverse", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 13, 8);
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

		RechercheMulticritereDetailReglementAchatValue request = new RechercheMulticritereDetailReglementAchatValue();
		

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
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeReglementAchatService.getById(typeReglementId).getDesignation(), ExcelUtils.boldRed3));

			request.setTypeReglementId(typeReglementId);
		}
	


		EcheancierReportListValue report = gestionnaireReportAchatService.getListEcheanceInverseReport(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Transaction", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Paiement", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Type", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Fournisseur", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(7, i - 1, "Region", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "N° Piece", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Banque", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(10, i - 1, "Montant", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(11, i - 1, "Echéance", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(12, i - 1, "Réglé", ExcelUtils.boldRed2));
		
		
		sheet3.addCell(new Label(13, i - 1, "Observation", ExcelUtils.boldRed2));
		
		
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
							sheet3.addCell(new Label(10, i, convertisseur(element.getMontant(), 4) + "", ExcelUtils.boldRed));

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
	
	

	
	@RequestMapping(value = "/situationAchat", method = RequestMethod.GET)
	public void generateListSituationReport(
			
			
			@RequestParam("type") String type,
			// @RequestParam("request") RechercheMulticritereFactureValue
			// request,
			@RequestParam("regiontId") Long regionId,
			//@RequestParam("deviseId") Long deviseId,
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

		sheet3.setColumnView(7, 20);
		sheet3.setColumnView(8, 15);
		
		sheet3.setColumnView(9, 15);
		
		
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 15);
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
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

			request.setPartieIntId(partieIntId);
		}

	

	

		
	/*	if (isNotEmty(deviseId))

		{
			DeviseValue rechDevise = new DeviseValue();rechDevise.setId(deviseId);
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Devise :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, deviseService.rechercheDeviseParId(rechDevise).getDesignation(), ExcelUtils.boldRed3));

			request.setDeviseId(deviseId);
		}
		*/



		SituationReportingReportListValue report = gestionnaireReportGcReportingService
				.getListSituationAchatReport(request,solde);
		
		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2)); 
		sheet3.addCell(new Label(3, i - 1, "Fournisseur", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Solde Init", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Chif. d'affaire", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Réglement", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Paiement en cours", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(8, i - 1, "Impayés/Date", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(9, i - 1, "Impayés Inv.", ExcelUtils.boldRed2));
		
		
		sheet3.addCell(new Label(10, i - 1, "Solde Actuel", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(11, i - 1, "Région", ExcelUtils.boldRed2));
		
		Double soldeInitial = 0d;
		Double chiffreAffaire = 0d;
		Double reglement = 0d;
		Double payementEnCours = 0d;
		Double impaye = 0d;
		Double impayeInverse = 0d;
        Double soldeActuel = 0d;

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
				
				sheet3.addCell(new jxl.write.Number(4, i, element.getSoldeInit() , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}
			
			if (element.getChiffreDaffaire() != null) {
				
				chiffreAffaire += element.getChiffreDaffaire();
				
				sheet3.addCell(new jxl.write.Number(5, i, element.getChiffreDaffaire() , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

			}
			
			if (element.getReglement() != null) {
				
				reglement += element.getReglement();
				sheet3.addCell(new jxl.write.Number(6, i, element.getReglement() , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

			}
			
			if (element.getPaiement() != null) {
				
				payementEnCours += element.getPaiement();
				sheet3.addCell(new jxl.write.Number(7, i, element.getPaiement() , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

			}
			
			
			if (element.getImpaye() != null) {
				impaye += element.getImpaye();
				sheet3.addCell(new jxl.write.Number(8, i, element.getImpaye() , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

			}
			
			
			if (element.getImpayeInverse() != null) {
				impayeInverse += element.getImpayeInverse();
				sheet3.addCell(new jxl.write.Number(9, i,(double)Math.round( element.getImpayeInverse()*1000)/1000 , ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

			}
			
			

			if (element.getSoldeActuel() != null) {
				soldeActuel += element.getSoldeActuel();
				
				sheet3.addCell(new jxl.write.Number(10, i, element.getSoldeActuel() , ExcelUtils.boldRed));

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
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(soldeInitial, 4)* 100) / 100 + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Chiffre d'affaire", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(chiffreAffaire, 4)* 100) / 100 + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Réglement", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(reglement, 4)* 100) / 100  + "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Paiement en cours", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(payementEnCours, 4)* 100) / 100  + "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Impayés/Date", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(impaye, 4)* 100) / 100  + "", ExcelUtils.boldRed2));
		
		i++;

		sheet3.addCell(new Label(7, i, "Impayés Inv.", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(impayeInverse* 1000) / 1000  + "", ExcelUtils.boldRed2));
		
		
		i++;

		sheet3.addCell(new Label(7, i, "Solde Actuel", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, (double) Math.round(convertisseur(soldeActuel, 4)* 100) / 100  + "", ExcelUtils.boldRed2));
		

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
	
	private boolean hasFactureAvoir(String reference) {
		
		
		RechercheMulticritereFactureAchatValue requestAvoir = new RechercheMulticritereFactureAchatValue();
		
		requestAvoir.setInfoLivraison(reference);
		
		ResultatRechecheFactureAchatValue result = factureAchatService.rechercherMultiCritere(requestAvoir) ; 
		
	  
		
		return (result.getList().size() > 0) ? true : false ;
		
	}


	public boolean checkForOptimization(RechercheMulticritereFactureAchatValue request) {

		return

		isNullOrEmpty(request.getDateFactureMin())
		&& isNullOrEmpty(request.getDateFactureMax())
		&& isNullOrEmpty(request.getPartieIntId())		
		&& isNullOrEmpty(request.getGroupeClientId());

	}
	
	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val) ;
	}
	@RequestMapping(value = "/listDetfactureAchat", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generatelistDetFactureAchatReport(

			@RequestBody RechercheMulticritereDetFactureAchatValue request

	) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		File f = new File(excel_file_location+"Liste_Det_Facture_Achat_" + "-" + dat + ".xlsx");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
			Equilibrageworkbook.createSheet("Liste_Det_Facture_Achat__", 0);
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

	 
			sheet3.addCell(new Label(2, 7, " Liste_Det_Facture_Achat", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 9, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getFactureReference()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf Facture :", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getFactureReference(), ExcelUtils.boldRed3));
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
		
		if (isNotEmty(request.getInfoLivraison()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf.Bon.Rec:", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getInfoLivraison(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getReferenceBlExterne()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf.Bon.Rec.Externe:", ExcelUtils.boldRed3));
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getReferenceBlExterne(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);

		}
		
		
		
		
		if (isNotEmty(request.getDateFactureDe()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Facture De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateFactureDe().getTime()), ExcelUtils.boldRed3));

	
		}
		
		if (isNotEmty(request.getDateFactureA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Facture A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateFactureA().getTime()), ExcelUtils.boldRed3));

	
		}
		
		
		
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Ref Article :", ExcelUtils.boldRed3));
			
				ArticleValue articleValue= articleService.getArticleParId(request.getProduitId());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, articleValue.getRef(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			 
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Designation produit :", ExcelUtils.boldRed3));
			
			ArticleValue articleValue= articleService.getArticleParId(request.getProduitId());
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, articleValue.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		
		
		
		if (isNotEmty(request.getQuantiteDe()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité Du :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQuantiteDe().toString() , ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);


		}
		if (isNotEmty(request.getQunatiteA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité A :", ExcelUtils.boldRed3));
			
			 
			
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech,request.getQunatiteA().toString() , ExcelUtils.boldRed3));
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

		request.setOptimized(RechercheMulticritereDetFactureAchatValue.checkForOptimization(request));
		
		 
		
		ResultatRechecheDetFactureAchatValue result = detFactureAchatService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Réf.Bon.Rec", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Réf.Bon.Rec.Externe", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Date Facture", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Ref.Article", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "ArticleDesignation", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "prix", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Montant TTC", ExcelUtils.boldRed2));
		




	  if(result.getList()!=null && result.getList().size()>0) {
	    	 
		
		for (DetFactureAchatValue element : result.getList()) {
			
			if(element.getFactureReference()!=null) {
				
				 
				sheet3.addCell(new Label(2, i, element.getFactureReference()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
				 

			}
			if(element.getPartieIntId()!=null) {
				
				 
				sheet3.addCell(new Label(3, i, element.getClientAbreviation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getInfoLivraison()!=null) {
				
				 
				sheet3.addCell(new Label(4, i, element.getInfoLivraison()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
				 

			}
			if(element.getReferenceBlExterne()!=null) {
				
				 
				sheet3.addCell(new Label(5, i, element.getReferenceBlExterne()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if (element.getDateIntroduction() != null) {
				sheet3.addCell(
						new Label(6, i, dateFormat2.format(element.getDateIntroduction().getTime()) + "", ExcelUtils.boldRed));
				 


			} else {
				
				
				
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
				 

			}

			
			if(element.getProduitReference()!=null) {
				
				 
				sheet3.addCell(new Label(7, i, element.getProduitReference()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getProduitDesignation()!=null) {
				
				 
				sheet3.addCell(new Label(8, i, element.getProduitDesignation()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				 

			}
			
			
			if(element.getQuantite()!=null) {
				
				 
				sheet3.addCell(new Label(9, i, element.getQuantite()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getPrixUnitaireHT()!=null) {
				
				 
				sheet3.addCell(new Label(10, i, element.getPrixUnitaireHT()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
				 

			}
			
			if(element.getPrixTotalHT()!=null) {
				
				 
				sheet3.addCell(new Label(11, i, element.getPrixTotalHT()+ "", ExcelUtils.boldRed));
				 
				

			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
				 

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
	String filename = "list_Det_Factues_Achat" + dateFormat1 + ".xls";
	
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