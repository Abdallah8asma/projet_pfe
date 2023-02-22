package com.gpro.consulting.tiers.logistique.rest.gc.achat.report.impl;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.gpro.consulting.tiers.logistique.rest.report.utilities.ExcelUtils;
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
		Row   titreRows = sheet3.createRow(8);
		
	

		Cell cellule  = titreRows.createCell(2) ; 
			
		cellule.setCellValue("hassen mahjoub ");
		
		cellule.setCellStyle(ExcelUtilsXlsx.boldTitre);
	
        
	    sheet3.addMergedRegion(new CellRangeAddress(8,9,2,7));
	    		 

		// Critaire de recherche
	    int numColCritRech = 2;
		int numLigneCritRech = 14;
		Row row=sheet3.createRow(numLigneCritRech);
		Cell cell1=  row.createCell(numColCritRech);
		cell1.setCellValue("Critère de recherche");
		row.createCell(numColCritRech+1).setCellValue(dateTimeFormat2.format(d));
		numLigneCritRech++;
		if (isNotEmty(request.getFactureReference()))

		{
			numLigneCritRech++;
			Row row0=sheet3.createRow(numLigneCritRech);
			row0.createCell(numColCritRech).setCellValue("Réf Facture :");
			row0.createCell(numColCritRech + 1).setCellValue(request.getFactureReference());
			
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

		}
		if (isNotEmty(request.getPartieIntId()))

		{
			numLigneCritRech++;
			Row row1=sheet3.createRow(numLigneCritRech);

			PartieInteresseValue partieInteresse= partieInteresseeService.getById(request.getPartieIntId());

			
			row1.createCell(numColCritRech).setCellValue("Client :");

			row1.createCell(numColCritRech + 1).setCellValue( partieInteresse.getAbreviation());
			
			
		
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		if (isNotEmty(request.getInfoLivraison()))

		{
			numLigneCritRech++;
			Row row2=sheet3.createRow(numLigneCritRech);

			row2.createCell(numColCritRech).setCellValue("Réf.Bon.Rec:");
			row2.createCell(numColCritRech + 1).setCellValue(request.getInfoLivraison());
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

		}
		if (isNotEmty(request.getReferenceBlExterne()))

		{
			numLigneCritRech++;
			Row row3=sheet3.createRow(numLigneCritRech);

			row3.createCell(numColCritRech).setCellValue("Réf.Bon.Rec.Externe: :");
			row3.createCell(numColCritRech + 1).setCellValue(request.getReferenceBlExterne());
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

		}
		
		
		
		
		if (isNotEmty(request.getDateFactureDe()))

		{

			numLigneCritRech++;
			Row row4=sheet3.createRow(numLigneCritRech);

			row4.createCell(numColCritRech).setCellValue("Date Facture De:");
			row4.createCell(numColCritRech + 1).setCellValue(dateFormat2.format(request.getDateFactureDe().getTime()));
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

	
		}
		
		if (isNotEmty(request.getDateFactureA()))

		{

			numLigneCritRech++;
			Row row5=sheet3.createRow(numLigneCritRech);
			row5.createCell(numColCritRech).setCellValue("Date Facture A :");
			row5.createCell(numColCritRech + 1).setCellValue(dateFormat2.format(request.getDateFactureA().getTime()));
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));

	
		}
		
		
		
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			Row row6=sheet3.createRow(numLigneCritRech);

			row6.createCell(numColCritRech).setCellValue("Ref Article :");
			ArticleValue articleValue= articleService.getArticleParId(request.getProduitId());

			row6.createCell(numColCritRech + 1).setCellValue(articleValue.getRef());
			 
			
				
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		if (isNotEmty(request.getProduitId()))

		{
			numLigneCritRech++;
			Row row7=sheet3.createRow(numLigneCritRech);

			row7.createCell(numColCritRech).setCellValue("Designation  Article :");
			ArticleValue articleValue= articleService.getArticleParId(request.getProduitId());

			row7.createCell(numColCritRech + 1).setCellValue(articleValue.getDesignation());
			 
			
				
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		
		
		if (isNotEmty(request.getQuantiteDe()))

		{
			numLigneCritRech++;
			Row row9=sheet3.createRow(numLigneCritRech);

		
			row9.createCell(numColCritRech).setCellValue("Quantité Du :");
			row9.createCell(numColCritRech + 1).setCellValue(request.getQuantiteDe());
			
			 
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		if (isNotEmty(request.getQunatiteA()))

		{
			numLigneCritRech++;
			Row row10=sheet3.createRow(numLigneCritRech);

			row10.createCell(numColCritRech).setCellValue("Quantité A:");
			row10.createCell(numColCritRech + 1).setCellValue(request.getQunatiteA());
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		
		
		if (isNotEmty(request.getPrixMin()))

		{
			numLigneCritRech++;
			Row row12=sheet3.createRow(numLigneCritRech);

			row12.createCell(numColCritRech).setCellValue("Prix Du :");
			row12.createCell(numColCritRech + 1).setCellValue(request.getPrixMin().toString());
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		if (isNotEmty(request.getPrixMax()))

		{
			numLigneCritRech++;
			Row row13=sheet3.createRow(numLigneCritRech);

			row13.createCell(numColCritRech).setCellValue("Prix A  :");
			row13.createCell(numColCritRech + 1).setCellValue(request.getPrixMax().toString());
			
			sheet3.addMergedRegion(new CellRangeAddress(numLigneCritRech,numLigneCritRech,numColCritRech + 1,numColCritRech + 2));


		}
		
		
		 
		
        request.setOptimized(RechercheMulticritereDetFactureAchatValue.checkForOptimization(request));
		
		 
		
		ResultatRechecheDetFactureAchatValue result = detFactureAchatService.rechercherMultiCritere(request);

		int i = numLigneCritRech + 4;
		 Row rowColones= sheet3.createRow(i-1);
		 rowColones.createCell(2).setCellValue("Réference");
		 rowColones.createCell(3).setCellValue("Client");
		 rowColones.createCell(4).setCellValue("Réf.Bon.Rec");
		 rowColones.createCell(5).setCellValue("Réf.Bon.Rec.Externe");
		 rowColones.createCell(6).setCellValue("Date Facture");
		 rowColones.createCell(7).setCellValue("Ref.Article");
		 rowColones.createCell(8).setCellValue("ArticleDesignation");
		 rowColones.createCell(9).setCellValue("Quantité");
		 rowColones.createCell(10).setCellValue("prix");
		 rowColones.createCell(11).setCellValue("Montant TTC");

		  if(result.getList()!=null && result.getList().size()>0) {
		    	 
			
			for (DetFactureAchatValue element : result.getList()) 
			{
				 Row rowDonne=sheet3.createRow(i);

				
				if(element.getFactureReference()!=null) 
				{
					
					 
				rowDonne.createCell(2).setCellValue(element.getFactureReference());
					 
					

				} 
				else 
				{
					rowDonne.createCell(2).setCellValue("");
					 

				}
				if(element.getPartieIntId()!=null) {
					
					 
					rowDonne.createCell(3).setCellValue(element.getClientAbreviation());
					 
					

				} else {
					rowDonne.createCell(3).setCellValue("");
					 

				}
				
				if(element.getInfoLivraison()!=null) {
					
					 
					rowDonne.createCell(4).setCellValue(element.getInfoLivraison());
					 
					

				} else {
					rowDonne.createCell(4).setCellValue("");
					 

				}
				if(element.getReferenceBlExterne()!=null) {
					
					rowDonne.createCell(5).setCellValue(element.getReferenceBlExterne());

					 
					

				} else {
					rowDonne.createCell(5).setCellValue("");
					 

				}
				
				
				if (element.getDateIntroduction() != null) {
					rowDonne.createCell(6).setCellValue(dateFormat2.format(element.getDateIntroduction().getTime()));

					 


				} else {
					
					
					
					rowDonne.createCell(6).setCellValue("");
					 

				}

				
				if(element.getProduitReference()!=null) {
					rowDonne.createCell(7).setCellValue(element.getProduitReference());

					
					 
					 
					

				} else {
					rowDonne.createCell(7).setCellValue("");
					 

				}
				
				
				if(element.getProduitDesignation()!=null) {
					
					rowDonne.createCell(8).setCellValue(element.getProduitDesignation());

					 
					

				} else {
					rowDonne.createCell(8).setCellValue("");
					 

				}
				
				
				if(element.getQuantite()!=null) {
					
					rowDonne.createCell(9).setCellValue(element.getQuantite());

					 
					

				} else {
					rowDonne.createCell(9).setCellValue("");
					 

				}
				
				if(element.getPrixUnitaireHT()!=null) {
					
					 
					rowDonne.createCell(10).setCellValue(element.getPrixUnitaireHT());
					 
					

				} else {
					rowDonne.createCell(10).setCellValue("");
					 

				}
				
				if(element.getPrixTotalHT()!=null) {
					
					 
				rowDonne.createCell(11).setCellValue(element.getPrixTotalHT());
					 
					

				} else {
					rowDonne.createCell(11).setCellValue("");
					 

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
//	sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);

			 sheet3.addMergedRegion(new CellRangeAddress(numLigneBasDuPage, numLigneBasDuPage,numColBasDuPage, numColBasDuPage + 1));
			 Row rowNombrelignes= sheet3.createRow(numLigneBasDuPage);
			 rowNombrelignes.createCell(numColBasDuPage).setCellValue("nombre des lignes");
			 rowNombrelignes.createCell(numColBasDuPage + 2).setCellValue(result.getNombreResultaRechercher());
			 


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
