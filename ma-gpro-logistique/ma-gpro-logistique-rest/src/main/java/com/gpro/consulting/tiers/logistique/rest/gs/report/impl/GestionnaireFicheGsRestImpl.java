package com.gpro.consulting.tiers.logistique.rest.gs.report.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.DetBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonMouvementDomaine;
import com.gpro.consulting.tiers.logistique.rest.report.utilities.ExcelUtils;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IMarcheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IElementReglementService;
import com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService;
import com.gpro.consulting.tiers.logistique.service.gs.IBonInventaireService;
import com.gpro.consulting.tiers.logistique.service.gs.IBonMouvementService;
import com.gpro.consulting.tiers.logistique.service.gs.IBonStockService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;
import com.gpro.consulting.tiers.logistique.service.gs.report.IGestionnaireReportGsService;
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
@RequestMapping("/fichesGS")
@SuppressWarnings("static-access")
public class GestionnaireFicheGsRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireFicheGsRestImpl.class);

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
	private IGroupeClientService groupeClientService;

	@Autowired
	private IProduitDepotService produitDepotService;

	@Autowired
	private IMagasinService magasinService;
	

	@Autowired
	private IBonStockService bonStockService;
	
	@Autowired
	private IBonInventaireService bonInventaireService;
	
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@Autowired
	private IBaseInfoService baseInfoService;
	@Autowired
	private IBonMouvementService      bonMouvementService;
	
	@Autowired
	private IMiseService miseServise;
	
	@RequestMapping(value = "/listBonStock", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListBonStockReport(
			
			@RequestBody  RechercheMulticritereBonStockValue request
							
		) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
        BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Sortie_Stock" + "-" + dat + ".xls");

		
	       ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
	        WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);
		
	//	WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Sortie_Stock", 0);
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

		sheet3.addCell(new Label(2, 7, "    Liste des Bons de sortie", ExcelUtils.boldTitre));
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

		



		if (isNotEmty(request.getReferenceBl()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getReferenceBl(), ExcelUtils.boldRed3));

	
		}

		

		if (isNotEmty(request.getPartieIntId()))

		{

			PartieInteresseValue pi = new PartieInteresseValue();
			pi.setId(request.getPartieIntId());

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.recherchePartieInteresseeParId(pi).getAbreviation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		
		}

		if (isNotEmty(request.getDateLivraisonMin()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonMin().getTime()), ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getDateLivraisonMax()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateLivraisonMax().getTime()), ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getMetrageMin()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getMetrageMin() + "", ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getMetrageMax()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getMetrageMax() + "", ExcelUtils.boldRed3));

		
		}

		if (isNotEmty(request.getPrixMin()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrixMin() + "", ExcelUtils.boldRed3));


		}

		if (isNotEmty(request.getPrixMax()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrixMax() + "", ExcelUtils.boldRed3));

		
		}

	

		request.setOptimized(this.checkForOptimization(request));

	

		ResultatRechecheBonStockValue report = bonStockService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Date Intro.", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(5, i - 1, "Date", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(6, i - 1, "Réf. Avoir", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Réf. Facture", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Réf. BL", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(9, i - 1, "Quantite", ExcelUtils.boldRed2));
		
		sheet3.addCell(new Label(10, i - 1, "Montant TTC", ExcelUtils.boldRed2));



		Double mantantTtcTotale = 0d;
		Double remiseTotale = 0d;
		Double quantiteTotale = 0d;
		
		Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();

		for (BonStockValue element : report.getList()) {
			
			PartieInteresseValue client = mapClientById.get(element.getPartieIntId());


			if (client != null) {
				element.setPartieIntAbreviation(client.getAbreviation());
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
			
			

			// date concer

			if (element.getDateConcernee() != null) {
				sheet3.addCell(
						new Label(5, i, dateFormat2.format(element.getDateConcernee().getTime()) + "", ExcelUtils.boldRed));
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
			
			
			if (element.getRefBR() != null) {
				sheet3.addCell(new Label(8, i, element.getRefBR() + "", ExcelUtils.boldRed));

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
			

			// Mantant TTC

			if (element.getMontantTTC() != null) {
				sheet3.addCell(new Label(10, i, convertisseur(element.getMontantTTC(), 4) + "", ExcelUtils.boldRed));

				mantantTtcTotale += element.getMontantTTC();
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}

		
		

			i++;

		}

		i++;
		i++;

	/*	sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
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

		sheet3.addCell(new Label(7, i, "Montant Remise Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(remiseTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));
		
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
        String filename = "sortie-stock_" + dateFormat1 + ".xls";
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
	
	
	
	
	
	@RequestMapping(value = "/listBonInventaire", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListBonInventaReport(
			
			@RequestBody  RechercheMulticritereBonInventaireValue request
							
		) throws WriteException, IOException {


		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		   BaseInfoValue baseInfo= baseInfoService.getClientActif();
			
			excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Bon_Inventaire" + "-" + dat + ".xls");

		
	       ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
	        WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);
		
	//	WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Bon_Inventaire", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 10);

		
		/**************************************************************************/

		

		// Nom du rapport et la date

		ExcelUtils.init();

	
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "    Liste des Bons d'inventaires", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 5, 8);
		

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
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getReference(), ExcelUtils.boldRed3));

	
		}

		

		

		if (isNotEmty(request.getDateDe()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateDe().getTime()), ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getDateA()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, dateFormat2.format(request.getDateA().getTime()), ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getMetrageMin()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getMetrageMin() + "", ExcelUtils.boldRed3));

	
		}

		if (isNotEmty(request.getMetrageMax()))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantite A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getMetrageMax() + "", ExcelUtils.boldRed3));

		
		}
		
		if (isNotEmty(request.getIdDepot()))

		{
			MagasinValue magasin=new MagasinValue();
			magasin.setId(request.getIdDepot());
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, magasinService.rechercheMagasinParId(magasin).getDesignation(), ExcelUtils.boldRed3));

	
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

		 

		request.setOptimized(this.checkForOptimization(request));

	

		ResultatRechecheBonInventaireValue report = bonInventaireService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Magasin", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Observations", ExcelUtils.boldRed2));
		
		
		 



		
		

		for (BonInventaireValue element : report.getList()) {
			
			  
			

			 

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));

			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(3, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			
			

			 

		 
			
			if (element.getIdDepot() != null) {
				MagasinValue magasinValue=new MagasinValue();
				magasinValue.setId(element.getIdDepot());
				MagasinValue depot = magasinService.rechercheMagasinParId(magasinValue);
				sheet3.addCell(new Label(4, i, depot.getDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}
			 
			
			
			if (element.getObservations() != null) {
				sheet3.addCell(new Label(5, i, element.getObservations() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

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

	@RequestMapping(value = "/bonInventaire", method = RequestMethod.GET)
	public void genererBonLivraisonReport(@RequestParam("id") Long id,
			HttpServletResponse response) throws WriteException, IOException {

		 

		BonInventaireValue bonInventaireValue = bonInventaireService.getBonInventaireById(id);

		 

		 

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("Inventaire_Detaille_" + "-" + dat + ".xls");

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

		 
		// Nom du rapport et la date

		ExcelUtils.init();

		 
		// Nom du rapport et la date

		sheet3.addCell(
				new Label(2, 7, "    Inventaire : " + bonInventaireValue.getReference(), ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		
		sheet3.addCell(new Label(2, 10, "Date :", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 10, 3, 10);

		sheet3.addCell(
				new Label(4, 10, dateFormat2.format(bonInventaireValue.getDate().getTime()), ExcelUtils.boldRed));
	 
		sheet3.addCell(new Label(2, 11, "Magasin :", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 11, 3 ,11);
		MagasinValue magasin=new MagasinValue();
		magasin.setId(bonInventaireValue.getIdDepot());
	 
		sheet3.addCell(new Label(4, 11, magasinService.rechercheMagasinParId(magasin).getDesignation(), ExcelUtils.boldRed));

		sheet3.addCell(new Label(2, 12, "Observations :" + "", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 12, 3 ,12);
		sheet3.addCell(new Label(4, 12, bonInventaireValue.getObservations(), ExcelUtils.boldRed));
		sheet3.mergeCells(4, 12, 9, 12);
	

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 1;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(3, i - 1, "Article", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i - 1, 5, i - 1);

		sheet3.addCell(new Label(6, i - 1, "Sous Famille", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Nº Série", ExcelUtils.boldRed2));
		sheet3.mergeCells(8, i - 1, 9, i - 1);
		for (DetBonInventaireValue element : bonInventaireValue.getListDetBonInventaire()) {

			if (element.getProduitId() != null) {
				
				ProduitValue produit=produitService.rechercheProduitById(element.getProduitId());
				sheet3.addCell(new Label(2, i, produit.getReference() + "", ExcelUtils.boldRed));
				
				sheet3.addCell(new Label(3, i, produit.getDesignation() + "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i, 5, i);
				sheet3.addCell(new Label(6, i, produit.getSousFamilleDesignation() + "", ExcelUtils.boldRed));

				 
			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(3, i, 5, i);
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			 

			 
			 

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(7, i, element.getQuantite() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "0", ExcelUtils.boldRed));
			}

			 

			if (element.getNumeroSeries() != null) {
				sheet3.addCell(
						new Label(8, i, element.getNumeroSeries() + "",
								ExcelUtils.boldRed));
				sheet3.mergeCells(8, i, 9, i);

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				sheet3.mergeCells(8, i, 9, i);
			}

			 

			i++;

		}

		i++;
		i++;

		/*
		 * sheet3.addCell(new Label(2, i, "Taxes", ExcelUtils.boldRed2));
		 * sheet3.addCell(new Label(3, i, "Taux", ExcelUtils.boldRed2));
		 * sheet3.addCell(new Label(4, i, "Assiette", ExcelUtils.boldRed2));
		 * sheet3.addCell(new Label(5, i, "Montant", ExcelUtils.boldRed2));
		 * 
		 * sheet3.addCell(new Label(7, i, "Montant Remise", ExcelUtils.boldRed2));
		 * sheet3.addCell(new Label(8, i, "Montant Taxes", ExcelUtils.boldRed2));
		 * sheet3.addCell(new Label(9, i, "Montant HT", ExcelUtils.boldRed2));
		 * 
		 * if (bonLivraisonReport.getMontantRemiseTotal() != null) { sheet3.addCell(new
		 * Label(7, i + 1, convertisseur(bonLivraisonReport.getMontantRemiseTotal(), 3)
		 * + "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(7, i + 1, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getMontantTaxes() != null) { sheet3.addCell(new
		 * Label(8, i + 1, convertisseur(bonLivraisonReport.getMontantTaxes(), 3) + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(8, i + 1, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getMontantHTTotal() != null) { sheet3.addCell(new
		 * Label(9, i + 1, convertisseur(bonLivraisonReport.getMontantHTTotal(), 3) +
		 * "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(9, i + 1, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getExistFodec()) { i++;
		 * 
		 * if (bonLivraisonReport.getTaxeFodec() != null) { sheet3.addCell( new Label(2,
		 * i, bonLivraisonReport.getTaxeFodec().toUpperCase() + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getTauxFodec() != null) { sheet3.addCell(new Label(3,
		 * i, bonLivraisonReport.getTauxFodec().toString().concat(" %") + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getAssietteFodec() != null) { sheet3.addCell(new
		 * Label(4, i, bonLivraisonReport.getAssietteFodec() + "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getMontantTaxeFodec() != null) { sheet3.addCell(new
		 * Label(5, i, bonLivraisonReport.getMontantTaxeFodec() + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed)); }
		 * 
		 * }
		 * 
		 * if (bonLivraisonReport.getExistTVA7()) { i++;
		 * 
		 * if (bonLivraisonReport.getTaxeTVA7() != null) { sheet3.addCell( new Label(2,
		 * i, bonLivraisonReport.getTaxeTVA7().toUpperCase() + "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getTauxTVA7() != null) { sheet3.addCell(new Label(3,
		 * i, bonLivraisonReport.getTauxTVA7().toString().concat(" %") + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getAssietteTVA7() != null) { sheet3.addCell(new
		 * Label(4, i, bonLivraisonReport.getAssietteTVA7() + "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getMontantTaxeTVA7() != null) { sheet3.addCell(new
		 * Label(5, i, bonLivraisonReport.getMontantTaxeTVA7() + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed)); }
		 * 
		 * }
		 * 
		 * if (bonLivraisonReport.getExistTVA13()) { i++;
		 * 
		 * if (bonLivraisonReport.getTaxeTVA13() != null) { sheet3.addCell( new Label(2,
		 * i, bonLivraisonReport.getTaxeTVA13().toUpperCase() + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getTauxTVA13() != null) { sheet3.addCell(new Label(3,
		 * i, bonLivraisonReport.getTauxTVA13().toString().concat(" %") + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getAssietteTVA13() != null) { sheet3.addCell(new
		 * Label(4, i, bonLivraisonReport.getAssietteTVA13() + "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getMontantTaxeTVA13() != null) { sheet3.addCell(new
		 * Label(5, i, bonLivraisonReport.getMontantTaxeTVA13() + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed)); }
		 * 
		 * }
		 * 
		 * if (bonLivraisonReport.getExistTVA()) { i++;
		 * 
		 * if (bonLivraisonReport.getTaxeTVA() != null) { sheet3.addCell(new Label(2, i,
		 * bonLivraisonReport.getTaxeTVA().toUpperCase() + "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getTauxTVA() != null) { sheet3.addCell(new Label(3, i,
		 * bonLivraisonReport.getTauxTVA().toString().concat(" %") + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getAssietteTVA() != null) { sheet3.addCell(new
		 * Label(4, i, bonLivraisonReport.getAssietteTVA() + "", ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed)); }
		 * 
		 * if (bonLivraisonReport.getMontantTaxeTVA() != null) { sheet3.addCell(new
		 * Label(5, i, bonLivraisonReport.getMontantTaxeTVA() + "",
		 * ExcelUtils.boldRed));
		 * 
		 * } else { sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed)); }
		 * 
		 * }
		 */

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

		/*
		 * sheet3.addCell(new Label(7, i + 1, "Montant TTC", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(7, i + 1, 8, i + 1); sheet3.addCell(new Label(9, i + 1,
		 * convertisseur(bonLivraisonReport.getMontantHTTotal(), 3) + " €",
		 * ExcelUtils.boldRed2));
		 */

		/*
		 * sheet3.addCell(new Label(2, i + 3,
		 * "Arrêté le présent Bon de livraison à la somme de : ",
		 * ExcelUtils.ARIAL_10_NO_BOLD_LEFT)); sheet3.mergeCells(2, i + 3, 4, i + 3);
		 */

		/*
		 * sheet3.addCell(new Label(2, i + 3,
		 * "Arrêté le présent Bon de livraison à la somme de : ",
		 * ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER)); sheet3.mergeCells(2, i + 3, 9, i +
		 * 3);
		 * 
		 * sheet3.addCell(new Label(2, i + 4,
		 * bonLivraisonReport.getMontantTTCToWords().toUpperCase(),
		 * ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER)); sheet3.mergeCells(2, i + 4, 9, i +
		 * 4);
		 */

		// sheet3.addCell(new Label(2, i + 5, "Valeur en votre aimable règlement net 15
		// jours date facture ", ExcelUtils.ARIAL_10_NO_BOLD_LEFT));
		// sheet3.mergeCells(2, i + 5, 7, i + 5);

		/*
		 * sheet3.addCell(new Label(2, i + 6, "Signature Client" + "",
		 * ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER)); sheet3.mergeCells(2, i + 6, 4, i +
		 * 6);
		 * 
		 * sheet3.addCell(new Label(2, i + 7, "", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(2, i + 7, 4, i + 9);
		 */

		// sheet3.addCell(new Label(2, i + 6, "Montant Réglé" + "",
		// ExcelUtils.boldRed3));
		// sheet3.mergeCells(2, i + 6, 3, i + 6);

		/*
		 * sheet3.addCell(new Label(7, i + 6, "Signature Responsable",
		 * ExcelUtils.ARIAL_10_BOLD_CENTER_BORDER)); sheet3.mergeCells(7, i + 6, 9, i +
		 * 6);
		 * 
		 * sheet3.addCell(new Label(7, i + 7, "", ExcelUtils.boldRed2));
		 * sheet3.mergeCells(7, i + 7, 9, i + 9);
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
	
	
	public boolean checkForOptimization(RechercheMulticritereBonStockValue request) {

		return

		isNullOrEmpty(request.getDateLivraisonMin()) && isNullOrEmpty(request.getDateLivraisonMax())
				&& isNullOrEmpty(request.getPartieIntId()) && isNullOrEmpty(request.getGroupeClientId());

	}
	
	
	public boolean checkForOptimization(RechercheMulticritereBonInventaireValue request) {

		return

		isNullOrEmpty(request.getDateDe()) && isNullOrEmpty(request.getDateA())
				&& isNullOrEmpty(request.getIdDepot()) && isNullOrEmpty(request.getReference());

	}


	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

	

	@RequestMapping(value = "/sortiestock", method = RequestMethod.GET)
	public void generateListSortieStockReport(
			@RequestParam("id") Long id,
			@RequestParam("type") String type,HttpServletResponse response
							
		) throws WriteException, IOException {


		BonMouvementStockValue bonMouvementStockValue = bonMouvementService.rechercheBonMouvementParId(id);
		
		Date d = new Date();

		String dat = "" + dateFormat.format(d);


		   BaseInfoValue baseInfo= baseInfoService.getClientActif();

		   
			excel_file_location = baseInfo.getExcelDirectory();

			// this.rapport=true;
			File f = new File(excel_file_location+"Bon de sortie" + "-" + dat + ".xls");

			WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
			Equilibrageworkbook.createSheet("Sortie-List", 0);
			WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 30);
		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
	

		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}

		// Nom du rapport et la date

		ExcelUtils.init();

	
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, " Bon de sortie ", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);

		int numColCritRech = 2;
		int numLigneCritRech = 14;
		
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		
		
		

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Reference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Code Article", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Article ", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Quantite ", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "OF ", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "PU ", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Montant ", ExcelUtils.boldRed2));
		
		
		
			


			for (MouvementStockValue element : bonMouvementStockValue.getMouvements()) {
				
				if( bonMouvementStockValue.getDate()!=null) {
					sheet3.addCell(new Label(2, i, dateFormat2.format( bonMouvementStockValue.getDate().getTime())+ "", ExcelUtils.boldRed));

					} else {
						sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));

					}
						if( bonMouvementStockValue.getNumero()!=null) {
							sheet3.addCell(new Label(3, i, bonMouvementStockValue.getNumero() + "", ExcelUtils.boldRed));

							} else {
								sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

							}


					


				if (element.getReferenceArticle() != null) {
					sheet3.addCell(
							new Label(4, i,element.getReferenceArticle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
				}
				
				
			
				
				
				if (element.getDesignationArticle() != null) {
				
					sheet3.addCell(
							new Label(5, i,element.getDesignationArticle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				}
				
				if (element.getQuantiteReelle() != null) {
					
					sheet3.addCell(
							new Label(6, i,element.getQuantiteReelle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
				}
				
				if( bonMouvementStockValue.getOfId()!=null) {
					
					MiseValue mise=miseServise.rechercheMiseParId(bonMouvementStockValue.getOfId());
					
					sheet3.addCell(new Label(7, i, mise.getReference() + "", ExcelUtils.boldRed));

					} else {
						sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

					}

		      if (element.getPrixUnitaire() != null) {
					
					sheet3.addCell(
							new Label(8, i,element.getPrixUnitaire() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				}
				
		      if (element.getPrixTotal()!=null) {
					
						sheet3.addCell(
								new Label(9, i,element.getPrixTotal()+ "", ExcelUtils.boldRed));

					} else {
						sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
					}
					
		

		      
		     
				i++;

		
			
		}
		
		

	
		i++;
		i++;

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();


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
		
		
	}


	
	

	@RequestMapping(value = "/entreestock", method = RequestMethod.GET)
	public void generateListEntreeStockReport(
			@RequestParam("id") Long id,
			@RequestParam("type") String type,HttpServletResponse response
							
		) throws WriteException, IOException {


		BonMouvementStockValue bonMouvementStockValue = bonMouvementService.rechercheBonMouvementParId(id);
		
		Date d = new Date();

		String dat = "" + dateFormat.format(d);


		   BaseInfoValue baseInfo= baseInfoService.getClientActif();

		   
			excel_file_location = baseInfo.getExcelDirectory();

			// this.rapport=true;
			File f = new File(excel_file_location+"Bon D'entree" + "-" + dat + ".xls");

			WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
			Equilibrageworkbook.createSheet("Entree-List", 0);
			WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 30);
		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		
	

		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}

		// Nom du rapport et la date

		ExcelUtils.init();

	
		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, " Bon d'entree ", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);

		int numColCritRech = 2;
		int numLigneCritRech = 14;
		
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		
		
		

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Reference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Code Article", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Article ", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Quantite ", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "PU ", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Montant ", ExcelUtils.boldRed2));
		
		
		
			


			for (MouvementStockValue element : bonMouvementStockValue.getMouvements()) {
				
				if( bonMouvementStockValue.getDate()!=null) {
					sheet3.addCell(new Label(2, i, dateFormat2.format( bonMouvementStockValue.getDate().getTime())+ "", ExcelUtils.boldRed));

					} else {
						sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));

					}
						if( bonMouvementStockValue.getNumero()!=null) {
							sheet3.addCell(new Label(3, i, bonMouvementStockValue.getNumero() + "", ExcelUtils.boldRed));

							} else {
								sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

							}


					


				if (element.getReferenceArticle() != null) {
					sheet3.addCell(
							new Label(4, i,element.getReferenceArticle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
				}
				
				
			
				
				
				if (element.getDesignationArticle() != null) {
				
					sheet3.addCell(
							new Label(5, i,element.getDesignationArticle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
				}
				
				if (element.getQuantiteReelle() != null) {
					
					sheet3.addCell(
							new Label(6, i,element.getQuantiteReelle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
				}

		      if (element.getPrixUnitaire() != null) {
					
					sheet3.addCell(
							new Label(7, i,element.getPrixUnitaire() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
				}
				
		      if (element.getPrixTotal()!=null) {
					
						sheet3.addCell(
								new Label(8, i,element.getPrixTotal()+ "", ExcelUtils.boldRed));

					} else {
						sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
					}
					
		

		      
		     
				i++;

		
			
		}
		
		

	
		i++;
		i++;

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();


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
		
		
	}

	
	
	
	
}