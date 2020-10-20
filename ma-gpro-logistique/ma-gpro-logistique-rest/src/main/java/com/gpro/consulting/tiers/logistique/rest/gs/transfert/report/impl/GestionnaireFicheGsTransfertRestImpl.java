package com.gpro.consulting.tiers.logistique.rest.gs.transfert.report.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.DetBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.rest.report.utilities.ExcelUtils;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;
import com.gpro.consulting.tiers.logistique.service.gs.transfert.IBonTransfertService;
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
@RequestMapping("/fichesGS_Transfert")
@SuppressWarnings("static-access")
public class GestionnaireFicheGsTransfertRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireFicheGsTransfertRestImpl.class);

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
	private IProduitDepotService produitDepotService;

	@Autowired
	private IMagasinService magasinService;
	
	@Autowired
	private IBonTransfertService bonTransfertService;
	
	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	
	@Autowired
	private IBaseInfoService baseInfoService;

	@RequestMapping(value = "/listBonTransfert", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListBonTransfertReport(
			
			@RequestBody  RechercheMulticritereBonTransfertValue request
							
		) throws WriteException, IOException {


		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		   BaseInfoValue baseInfo= baseInfoService.getClientActif();
			
			excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"List_Bon_Transfert" + "-" + dat + ".xls");

		
	       ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
	        WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);
		
	//	WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("List_Bon_Transfert", 0);
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

		sheet3.addCell(new Label(2, 7, "    Liste des Bons Transferts", ExcelUtils.boldTitre));
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
		
		if (isNotEmty(request.getIdDepotOrigine()))

		{
			MagasinValue magasin=new MagasinValue();
			magasin.setId(request.getIdDepotOrigine());
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin Origine :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, magasinService.rechercheMagasinParId(magasin).getDesignation(), ExcelUtils.boldRed3));

	
		}
		
		
		if (isNotEmty(request.getIdDepotDestination()))

		{
			MagasinValue magasin=new MagasinValue();
			magasin.setId(request.getIdDepotDestination());
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Magasin destination :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, magasinService.rechercheMagasinParId(magasin).getDesignation(), ExcelUtils.boldRed3));

	
		}



		request.setOptimized(this.checkForOptimization(request));

	

		ResultatRechecheBonTransfertValue report = bonTransfertService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));
		
		if(request.getType().equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_SORTIE))
			sheet3.addCell(new Label(3, i - 1, "Réf. Réception", ExcelUtils.boldRed2));
		else
		sheet3.addCell(new Label(3, i - 1, "Réf. Sortie", ExcelUtils.boldRed2));
		
		
		
		
	
		sheet3.addCell(new Label(4, i - 1, "Date", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Magasin Origine", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Magasin Destination", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Status", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Status Auto", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Observations", ExcelUtils.boldRed2));
		
		
		 


		for (BonTransfertValue element : report.getList()) {
			
			  
			

			 

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));

			}
			
			
			if(request.getType().equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_SORTIE)) {
				
				
				if (element.getReferenceReception() != null) {
					sheet3.addCell(new Label(3, i, element.getReferenceReception() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

				}
				
			}
			
			
			
			if(request.getType().equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_RECEPTION)) {
				
				
				if (element.getReferenceSortie() != null) {
					sheet3.addCell(new Label(3, i, element.getReferenceSortie() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

				}
				
			}

			// date

			if (element.getDate() != null) {
				sheet3.addCell(
						new Label(4, i, dateFormat2.format(element.getDate().getTime()) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}
			
			

			 

		 
			
			if (element.getIdDepotOrigine() != null) {
				MagasinValue magasinValue=new MagasinValue();
				magasinValue.setId(element.getIdDepotOrigine());
				MagasinValue depot = magasinService.rechercheMagasinParId(magasinValue);
				sheet3.addCell(new Label(5, i, depot.getDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

			}
			
			
			
			if (element.getIdDepotDestination() != null) {
				MagasinValue magasinValue=new MagasinValue();
				magasinValue.setId(element.getIdDepotDestination());
				MagasinValue depot = magasinService.rechercheMagasinParId(magasinValue);
				sheet3.addCell(new Label(6, i, depot.getDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

			}
			
			
			if (element.getStatus() != null) {
				sheet3.addCell(new Label(7, i, element.getStatus() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

			}
			 
			
			
	
			
			if (element.getStatusAuto() != null) {
				sheet3.addCell(new Label(8, i, element.getStatusAuto() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

			}
			
			
			if (element.getObservations() != null) {
				sheet3.addCell(new Label(9, i, element.getObservations() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

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

	@RequestMapping(value = "/bonTransfert", method = RequestMethod.GET)
	public void genererBonTransfertReport(@RequestParam("id") Long id,
			HttpServletResponse response) throws WriteException, IOException {

		 

		BonTransfertValue bonTransfertValue = bonTransfertService.getBonTransfertById(id);

		 

		 

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
	      BaseInfoValue baseInfo= baseInfoService.getClientActif();
			
			excel_file_location = baseInfo.getExcelDirectory();
		File f = new File(excel_file_location+"Bon_Transfert-"+bonTransfertValue.getType() + "-" + dat + ".xls");

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
				new Label(2, 7, "    Bon Transfert  "+bonTransfertValue.getType()+" : " + bonTransfertValue.getReference(), ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		
		sheet3.addCell(new Label(2, 10, "Date :", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 10, 3, 10);

		sheet3.addCell(
				new Label(4, 10, dateFormat2.format(bonTransfertValue.getDate().getTime()), ExcelUtils.boldRed));
		
		
	 
		sheet3.addCell(new Label(2, 11, "Magasin Origine:", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 11, 3 ,11);
		MagasinValue magasin=new MagasinValue();
		magasin.setId(bonTransfertValue.getIdDepotOrigine());
	 
		sheet3.addCell(new Label(4, 11, magasinService.rechercheMagasinParId(magasin).getDesignation(), ExcelUtils.boldRed));
		
		
		
		
		sheet3.addCell(new Label(2, 12, "Magasin Destination:", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 12, 3 ,12);
		MagasinValue magasinDestination=new MagasinValue();
		magasinDestination.setId(bonTransfertValue.getIdDepotDestination());
	 
		sheet3.addCell(new Label(4, 12, magasinService.rechercheMagasinParId(magasinDestination).getDesignation(), ExcelUtils.boldRed));
		
		
		
		sheet3.addCell(new Label(2, 13, "Status :" + "", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 13, 3 ,13);
		sheet3.addCell(new Label(4, 13, bonTransfertValue.getStatus(), ExcelUtils.boldRed));
		sheet3.mergeCells(4, 13, 9, 13);
		
		sheet3.addCell(new Label(2, 14, "Status Auto:" + "", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 14, 3 ,14);
		sheet3.addCell(new Label(4, 14, bonTransfertValue.getStatus(), ExcelUtils.boldRed));
		sheet3.mergeCells(4, 14, 9, 14);
		

		sheet3.addCell(new Label(2, 15, "Observations :" + "", ExcelUtils.boldRed3));
		sheet3.mergeCells(2, 15, 3 ,15);
		sheet3.addCell(new Label(4, 15, bonTransfertValue.getObservations(), ExcelUtils.boldRed));
		sheet3.mergeCells(4, 15, 9, 15);
	

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

	//	int i = numLigneCritRech + 1;
		int i =18;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(3, i - 1, "Article", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i - 1, 5, i - 1);

		sheet3.addCell(new Label(6, i - 1, "Sous Famille", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Nº Série", ExcelUtils.boldRed2));
		sheet3.mergeCells(8, i - 1, 9, i - 1);
		for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

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




		i++;

		

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
	
	
	public boolean checkForOptimization(RechercheMulticritereBonTransfertValue request) {

		return

		isNullOrEmpty(
				request.getDateDe()) 		        
		        && isNullOrEmpty(request.getDateA())
				&& isNullOrEmpty(request.getIdDepotOrigine()) 
				&& isNullOrEmpty(request.getIdDepotDestination())
				&& isNullOrEmpty(request.getReference())
				&& isNullOrEmpty(request.getReferenceSortie())
				&& isNullOrEmpty(request.getReferenceReception())
		        && isNullOrEmpty(request.getStatus())
		        && isNullOrEmpty(request.getStatusAuto());

	}
	




	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

}