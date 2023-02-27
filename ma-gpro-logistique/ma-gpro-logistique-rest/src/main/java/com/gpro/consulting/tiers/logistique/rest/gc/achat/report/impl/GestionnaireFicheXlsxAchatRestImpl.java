package com.gpro.consulting.tiers.logistique.rest.gc.achat.report.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IRegionService;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.rest.report.utilities.ExcelUtilsXlsx;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IDetFactureAchatService;

import jxl.write.WriteException;

@Controller
@RequestMapping("/fichesAchatXlsx")
@SuppressWarnings("static-access")
public class GestionnaireFicheXlsxAchatRestImpl  extends AbstractGestionnaireDownloadImpl {
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_CALENDAR_FORMAT = "EEE MMM dd yyyy";

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static final SimpleDateFormat dateTimeFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private String excel_file_location = "C:\\ERP\\Excel\\";

	
	@Autowired
	private IPartieInteresseeService partieInteresseeService;
	
	
	
	@Autowired
	private IBaseInfoService baseInfoService;
	
	
	@Autowired
	private IRegionService regionService;
	@Autowired
	private IDetFactureAchatService detFactureAchatService;
	@Autowired
	private IArticleService articleService;
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
		 
        Workbook workbook = new XSSFWorkbook(); 
        Sheet sheet3 = workbook.createSheet("Liste_Det_Facture_Achat");
	  
		sheet3.setColumnWidth(0, 7*256);
		sheet3.setColumnWidth(1, 7*256);
		sheet3.setColumnWidth(2, 20*256);
		sheet3.setColumnWidth(3, 20*256);
		sheet3.setColumnWidth(3, 20*256);
		sheet3.setColumnWidth(4, 30*256);
		sheet3.setColumnWidth(5, 20*256);
		sheet3.setColumnWidth(6, 20*256);
		sheet3.setColumnWidth(7, 20*256);
		sheet3.setColumnWidth(8, 20*256);
		sheet3.setColumnWidth(9, 20*256);
		sheet3.setColumnWidth(10, 20*256);
		sheet3.setColumnWidth(11, 20*256);
		sheet3.setColumnWidth(12, 20*256);
		sheet3.setColumnWidth(13, 20*256);
		
		/**************************************************************************/

		// Nom du rapport et la date

		ExcelUtilsXlsx.init(workbook);
			
		        if(baseInfo.getLogoPNG()!=null)
		        {
		        	InputStream inputStream = new FileInputStream(baseInfo.getLogoPNG());
					byte[] inputImageBytes = IOUtils.toByteArray(inputStream);
					int inputImagePicture = workbook.addPicture(inputImageBytes, Workbook.PICTURE_TYPE_PNG);
					XSSFDrawing drawing = (XSSFDrawing) sheet3.createDrawingPatriarch();
					XSSFClientAnchor ironManAnchor = new XSSFClientAnchor();
					ironManAnchor.setRow1(1);
					ironManAnchor.setRow2(5);
					ironManAnchor.setCol1(2);
					ironManAnchor.setCol2(3);
					drawing.createPicture(ironManAnchor, inputImagePicture);	
		        }
				

			
		

		Row   titreRows = sheet3.createRow(7);
		Cell cellule  = titreRows.createCell(2) ; 
			
		cellule.setCellValue("Liste_Det_Facture_Achat ");
		
		cellule.setCellStyle(ExcelUtilsXlsx.boldTitre);
	    sheet3.addMergedRegion(new CellRangeAddress(7,8,2,9));
	    Row   rowDate = sheet3.createRow(6);
	    Cell cellDate=rowDate.createCell(2);
		cellDate.setCellValue("Le"+dateFormat2.format(d));
		cellDate.setCellStyle(ExcelUtilsXlsx.boldRed3);
	    		 

		// Critaire de recherche
	    int numColCritRech = 2;
		int numLigneCritRech = 14;
		Row row=sheet3.createRow(numLigneCritRech);
		Cell cell1=  row.createCell(numColCritRech);
		cell1.setCellValue("Critère de recherche");
		cell1.setCellStyle(ExcelUtilsXlsx.boldRed5);
		
		numLigneCritRech++;
		if (isNotEmty(request.getFactureReference()))

		{
			numLigneCritRech++;
			Row row0=sheet3.createRow(numLigneCritRech);
			Cell cell2= row0.createCell(numColCritRech);
			cell2.setCellStyle(ExcelUtilsXlsx.boldRed3);
			cell2.setCellValue("Réf Facture :");
			Cell cell3=row0.createCell(numColCritRech + 1);
			cell3.setCellValue(request.getFactureReference());
			cell3.setCellStyle(ExcelUtilsXlsx.boldRed3);
			
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

		}
		if (isNotEmty(request.getPartieIntId()))

		{
			numLigneCritRech++;
			Row row1=sheet3.createRow(numLigneCritRech);

			PartieInteresseValue partieInteresse= partieInteresseeService.getById(request.getPartieIntId());

			
			 Cell cell4= row1.createCell(numColCritRech);
			cell4.setCellValue("Client :");
			cell4.setCellStyle(ExcelUtilsXlsx.boldRed3);

			Cell cell5=row1.createCell(numColCritRech + 1);
			cell5.setCellValue( partieInteresse.getAbreviation());
			cell5.setCellStyle(ExcelUtilsXlsx.boldRed3);


			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		if (isNotEmty(request.getInfoLivraison()))

		{
			numLigneCritRech++;
			Row row2=sheet3.createRow(numLigneCritRech);

			 Cell cell6=row2.createCell(numColCritRech);
			cell6.setCellValue("Réf.Bon.Rec:");
			cell6.setCellStyle(ExcelUtilsXlsx.boldRed3);
			 Cell cell7=row2.createCell(numColCritRech + 1);
			 cell7.setCellValue(request.getInfoLivraison());
			 cell7.setCellStyle(ExcelUtilsXlsx.boldRed3);
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

		}
		if (isNotEmty(request.getReferenceBlExterne()))

		{
			numLigneCritRech++;
			Row row3=sheet3.createRow(numLigneCritRech);
          
			
			Cell cell8=row3.createCell(numColCritRech);
			cell8.setCellValue("Réf.Bon.Rec.Externe: :");
			cell8.setCellStyle(ExcelUtilsXlsx.boldRed3);
			 Cell cell9=row3.createCell(numColCritRech + 1);
			 cell9.setCellValue(request.getReferenceBlExterne());
			 cell9.setCellStyle(ExcelUtilsXlsx.boldRed3);			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

		}
		
		
		
		
		if (isNotEmty(request.getDateFactureDe()))

		{

			numLigneCritRech++;
			Row row4=sheet3.createRow(numLigneCritRech);

			Cell cell10=row4.createCell(numColCritRech);
			cell10.setCellValue("Date Facture De:");
			 cell10.setCellStyle(ExcelUtilsXlsx.boldRed3);			

			 Cell cell11=row4.createCell(numColCritRech + 1);
			 cell11.setCellValue(dateFormat2.format(request.getDateFactureDe().getTime()));
			 cell11.setCellStyle(ExcelUtilsXlsx.boldRed3);			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

	
		}
		
		if (isNotEmty(request.getDateFactureA()))

		{

			numLigneCritRech++;
			Row row5=sheet3.createRow(numLigneCritRech);
			
			Cell cell12=row5.createCell(numColCritRech);
			cell12.setCellValue("Date Facture A :");
			 cell12.setCellStyle(ExcelUtilsXlsx.boldRed3);			
			 Cell cell13=row5.createCell(numColCritRech + 1);
			cell13.setCellValue(dateFormat2.format(request.getDateFactureA().getTime()));
			 cell13.setCellStyle(ExcelUtilsXlsx.boldRed3);			

			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

	
		}
		
		
		
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			Row row6=sheet3.createRow(numLigneCritRech);

		Cell cell14=row6.createCell(numColCritRech);
		cell14.setCellValue("Ref Article :");
		cell14.setCellStyle(ExcelUtilsXlsx.boldRed3);			

		ArticleValue articleValue= articleService.getArticleParId(request.getProduitId());

			Cell cell15=row6.createCell(numColCritRech + 1);
			cell15.setCellValue(articleValue.getRef());
			cell15.setCellStyle(ExcelUtilsXlsx.boldRed3);			

			 
			
				
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			Row row7=sheet3.createRow(numLigneCritRech);

			 Cell cell16=row7.createCell(numColCritRech);
			cell16.setCellValue("Designation  Article :");
			cell16.setCellStyle(ExcelUtilsXlsx.boldRed3);			

			ArticleValue articleValue= articleService.getArticleParId(request.getProduitId());

			 Cell cell17=row7.createCell(numColCritRech + 1);
			 cell17.setCellValue(articleValue.getDesignation());
			cell16.setCellStyle(ExcelUtilsXlsx.boldRed3);			

			
				
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		
		
		if (isNotEmty(request.getQuantiteDe()))

		{
			numLigneCritRech++;
			Row row9=sheet3.createRow(numLigneCritRech);

		
	          Cell Cell18=row9.createCell(numColCritRech);
			  Cell18.setCellValue("Quantité Du :");
			  Cell18.setCellStyle(ExcelUtilsXlsx.boldRed3);
		
			  Cell Cell19= row9.createCell(numColCritRech + 1);
			  Cell19.setCellValue(request.getQuantiteDe());
			  Cell19.setCellStyle(ExcelUtilsXlsx.boldRed3);
			
			 
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		if (isNotEmty(request.getQunatiteA()))

		{
			numLigneCritRech++;
			Row row10=sheet3.createRow(numLigneCritRech);

			 Cell cell20=row10.createCell(numColCritRech);
			 cell20.setCellValue("Quantité A:");
			 cell20.setCellStyle(ExcelUtilsXlsx.boldRed3);
			 Cell cell21=row10.createCell(numColCritRech + 1);
			cell21.setCellValue(request.getQunatiteA());
			 cell21.setCellStyle(ExcelUtilsXlsx.boldRed3);

			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		
		
		if (isNotEmty(request.getPrixMin()))

		{
			numLigneCritRech++;
			Row row12=sheet3.createRow(numLigneCritRech);

			Cell cell22=row12.createCell(numColCritRech);
			cell22.setCellValue("Prix Du :");
			 cell22.setCellStyle(ExcelUtilsXlsx.boldRed3);
			Cell cell23=row12.createCell(numColCritRech + 1);
			cell23.setCellValue(request.getPrixMin().toString());
			 cell23.setCellStyle(ExcelUtilsXlsx.boldRed3);

			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		if (isNotEmty(request.getPrixMax()))

		{
			numLigneCritRech++;
			Row row13=sheet3.createRow(numLigneCritRech);

			Cell cell24=row13.createCell(numColCritRech);
			cell24.setCellValue("Prix A  :");
			 cell24.setCellStyle(ExcelUtilsXlsx.boldRed3);

			Cell cell25=row13.createCell(numColCritRech + 1);
			cell25.setCellValue(request.getPrixMax().toString());
			 cell25.setCellStyle(ExcelUtilsXlsx.boldRed3);

			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		
		 
		
        request.setOptimized(RechercheMulticritereDetFactureAchatValue.checkForOptimization(request));
		
		 
		
		ResultatRechecheDetFactureAchatValue result = detFactureAchatService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;
		 Row rowColones= sheet3.createRow(i-1);
		 Cell cell26=rowColones.createCell(2);
		 cell26 .setCellValue("Réference");
		 cell26.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell27=rowColones.createCell(3);
		 cell27.setCellValue("Client");
		 cell27.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell28= rowColones.createCell(4);
		 cell28.setCellValue("Réf.Bon.Rec");
		 cell28.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell29=rowColones.createCell(5);
		 cell29.setCellValue("Réf.Bon.Rec.Externe");
		 cell29.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell30=rowColones.createCell(6);
		 cell30.setCellValue("Date Facture");
		 cell30.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell31= rowColones.createCell(7);
		cell31.setCellValue("Ref.Article");
		 cell31.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell32= rowColones.createCell(8);
		 cell32.setCellValue("ArticleDesignation");
		 cell32.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell33=rowColones.createCell(9);
		 cell33 .setCellValue("Quantité");
		 cell33.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell34=rowColones.createCell(10);
		 cell34.setCellValue("prix");
		 cell34.setCellStyle(ExcelUtilsXlsx.boldRed2);
		 Cell cell35=rowColones.createCell(11);
		cell35.setCellValue("Montant TTC");
		 cell35.setCellStyle(ExcelUtilsXlsx.boldRed2);

		  if(result.getList()!=null && result.getList().size()>0) {
		    	 
			
			for (DetFactureAchatValue element : result.getList()) 
			{
				 Row rowDonne=sheet3.createRow(i);

					Cell cell36=rowDonne.createCell(2);

				if(element.getFactureReference()!=null) 
				{
					
					 
					cell36.setCellValue(element.getFactureReference());
					cell36.setCellStyle(ExcelUtilsXlsx.boldRed);
					 
					

				} 
				else 
				{
					cell36.setCellValue("");
					cell36.setCellStyle(ExcelUtilsXlsx.boldRed);

					 

				}
				Cell cell37=rowDonne.createCell(3);

				if(element.getPartieIntId()!=null) {
					
					 
					cell37.setCellValue(element.getClientAbreviation());
					cell37.setCellStyle(ExcelUtilsXlsx.boldRed);
					 
					

				} else {
					cell37.setCellValue("");
					cell37.setCellStyle(ExcelUtilsXlsx.boldRed);
					 

				}
				Cell cell38=rowDonne.createCell(4);

				if(element.getInfoLivraison()!=null) {
					
					 
					cell38.setCellValue(element.getInfoLivraison());
					cell38.setCellStyle(ExcelUtilsXlsx.boldRed);
					 
					

				} else {
					cell38.setCellValue("");
					cell38.setCellStyle(ExcelUtilsXlsx.boldRed);
					 

				}
				Cell cell39=rowDonne.createCell(5);

				if(element.getReferenceBlExterne()!=null) {
					
					cell39.setCellValue(element.getReferenceBlExterne());
					cell39.setCellStyle(ExcelUtilsXlsx.boldRed);


					 
					

				} else {
					cell39.setCellValue("");
					cell39.setCellStyle(ExcelUtilsXlsx.boldRed);
					 

				}
				
				Cell cell40=rowDonne.createCell(6);

				if (element.getDateIntroduction() != null) {
					cell40.setCellValue(dateFormat2.format(element.getDateIntroduction().getTime()));
					cell40.setCellStyle(ExcelUtilsXlsx.boldRed);

					 


				} else {
					
					
					
					cell40.setCellValue("");
					cell40.setCellStyle(ExcelUtilsXlsx.boldRed);

					
					 

				}

				Cell cell41=rowDonne.createCell(7);

				if(element.getProduitReference()!=null) {
					cell41.setCellValue(element.getProduitReference());
					cell41.setCellStyle(ExcelUtilsXlsx.boldRed);

					
					 
					 
					

				} else {
					cell41.setCellValue("");
					cell41.setCellStyle(ExcelUtilsXlsx.boldRed);


				}
				
				Cell cell42=rowDonne.createCell(8);

				if(element.getProduitDesignation()!=null) {
					
					cell42.setCellValue(element.getProduitDesignation());
					cell42.setCellStyle(ExcelUtilsXlsx.boldRed);

					 
					

				} else {
					cell42.setCellValue("");
					cell42.setCellStyle(ExcelUtilsXlsx.boldRed);

					 

				}
				
				Cell cell43=rowDonne.createCell(9);

				if(element.getQuantite()!=null) {
					
					cell43.setCellValue(element.getQuantite());

					cell43.setCellStyle(ExcelUtilsXlsx.boldRed);

					

				} else {
					cell43.setCellValue("");
					cell43.setCellStyle(ExcelUtilsXlsx.boldRed);

					 

				}
				Cell cell44=rowDonne.createCell(10);

				if(element.getPrixUnitaireHT()!=null) {
					
					 
					cell44.setCellValue(element.getPrixUnitaireHT());
					cell44.setCellStyle(ExcelUtilsXlsx.boldRed);

					

				} else {
					cell44.setCellValue("");
					cell44.setCellStyle(ExcelUtilsXlsx.boldRed);

					 

				}
				Cell cell45=rowDonne.createCell(11);

				if(element.getPrixTotalHT()!=null) {
					
					 
					cell45.setCellValue(element.getPrixTotalHT());
					cell45.setCellStyle(ExcelUtilsXlsx.boldRed);

					 
					

				} else {
					cell45.setCellValue("");
					cell45.setCellStyle(ExcelUtilsXlsx.boldRed);

					 

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


			 sheet3.addMergedRegion(new CellRangeAddress(numLigneBasDuPage, numLigneBasDuPage,numColBasDuPage, numColBasDuPage + 1));
			 Row rowNombrelignes= sheet3.createRow(numLigneBasDuPage);
			Cell cell46= rowNombrelignes.createCell(numColBasDuPage);
			cell46.setCellValue("nombre des lignes");
			cell46.setCellStyle(ExcelUtilsXlsx.boldRed5);

			Cell cell47=rowNombrelignes.createCell(numColBasDuPage + 2);
			cell47.setCellValue(result.getNombreResultaRechercher());
			cell47.setCellStyle(ExcelUtilsXlsx.boldRed3);

			 


			numLigneBasDuPage++;

			 sheet3.addMergedRegion(new CellRangeAddress(numLigneBasDuPage, numLigneBasDuPage,numColBasDuPage, numColBasDuPage + 1));

			numLigneBasDuPage++;

			/*******************************************
			 * BAS DU PAGE
			 ****************************************/

			

			 workbook.write(fileOut);
		       fileOut.close();

			  
			   workbook.close();

			/******************************************************************************************/
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "octet-stream"));

			Date now = new Date();
			String dateFormat1 = dateFormat.format(now);
			String filename = "list_Det_Factues_Achat" + dateFormat1 + ".xlsx";
			
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

	
	private boolean isNotEmty(Object value) {

		return value != null && !"".equals(value) && !"".equals(value.toString()) && !value.equals("undefined")
				&& !value.equals("null");
	}
}
