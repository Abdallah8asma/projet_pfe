package com.gpro.consulting.tiers.commun.rest.report.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ImpressionProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IUniteArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.rest.elementBase.impl.PrixClientRestImpl;
import com.gpro.consulting.tiers.commun.rest.report.utilities.ExcelUtils;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;
import com.gpro.consulting.tiers.commun.service.cache.IGestionnaireCacheService;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleProduitService;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.IBoutiqueService;
import com.gpro.consulting.tiers.commun.service.elementBase.IFamilleArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.IFamilleProduitService;
import com.gpro.consulting.tiers.commun.service.elementBase.IImpressionProduitService;
import com.gpro.consulting.tiers.commun.service.elementBase.IPrixClientService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitSerialisableService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.elementBase.ISousFamilleArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.ISousFamilleProduitService;
import com.gpro.consulting.tiers.commun.service.elementBase.IUniteArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.impl.BoutiqueServiceImpl;
import com.gpro.consulting.tiers.commun.service.elementBase.impl.FamilleProduitServiceImpl;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ITypePartieInteresseeService;

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
@RequestMapping("/fiches")
@SuppressWarnings("static-access")
public class GestionnaireFicheCommunRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireFicheCommunRestImpl.class);

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
	private IProduitSerialisableService produitSerialisableService;

	@Autowired
	private IPartieInteresseeService partieInteresseeService;

	@Autowired
	private IGroupeClientService groupeClientService;

	@Autowired
	private ITypePartieInteresseeService typePartieInteresseeService;

	@Autowired
	private IGestionnaireCacheService gestionnaireCacheService;

	@Autowired
	private IPrixClientService prixClientService;

	@Autowired
	private IFamilleProduitService familleProduitService;

	@Autowired
	private ISousFamilleProduitService sousFamilleProduitService;

	@Autowired
	private IBoutiqueService boutiqueService;
	
	@Autowired
	private IBaseInfoService baseInfoService;
	
	
	
	@Autowired
	private IFamilleArticleService familleArticleService;

	@Autowired
	private ISousFamilleArticleService sousFamilleArticleService;
	
	@Autowired
	private IArticleService articleService;
	
	
	
	@Autowired
	IUniteArticleService uniteArticleService;

     @Autowired
     IArticleProduitService articleProduitService;
     @Autowired
     IImpressionProduitService iImpressionProduitService;
	
	
	@RequestMapping(value = "/listArticle", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListArticleReport(

			@RequestBody RecherecheMulticritereArticleValue request
			

	) throws WriteException, IOException {
		
		BaseInfoValue baseInfo= baseInfoService.getClientActif();

		Date d = new Date();

		String dat = "" + dateFormat.format(d);
		
		excel_file_location = baseInfo.getExcelDirectory();


		// this.rapport=true;
		File f = new File(excel_file_location+"mp_list" + "-" + dat + ".xls");


		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		
		Equilibrageworkbook.createSheet("mp_list", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 30);
		sheet3.setColumnView(4, 20);
		sheet3.setColumnView(5, 20);
		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);

		/**************************************************************************/
		
	
		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}
		

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

	
		sheet3.addCell(new Label(2, 7, "    Liste des Matières Premières", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 7, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getRef()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getRef(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getDesignation()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Désignation :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getFamilleEntite()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Famille :", ExcelUtils.boldRed3));
			
			
			

			FamilleArticleValue famille = familleArticleService
					.rechercheFamilleArticleById(Long.parseLong(request.getFamilleEntite()));

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, famille.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getSousFamilleEntite()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Sous Famille :", ExcelUtils.boldRed3));

			SousFamilleArticleValue sousFamille = sousFamilleArticleService
					.rechercheSousFamilleArticleById(Long.parseLong(request.getSousFamilleEntite()));

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, sousFamille.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPrix_inf()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Min :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrix_inf().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPrix_sup()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Max :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrix_sup().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		

		ResultatRechecheArticleValue  result =  articleService.rechercherArticleMultiCritere(request);



		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Sous Famille", ExcelUtils.boldRed2));
	
		
		sheet3.addCell(new Label(5, i - 1, "Famille", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Unite 1", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Unite 2", ExcelUtils.boldRed2));

	

			for (ArticleValue element : result.getArticleValues()) {
				
				
				if(element.getUniteEntite() != null)
					element.setUniteDesignation(uniteArticleService.rechercheUniteArticleParId(element.getUniteEntite()).getDesignation());
				
				
				if(element.getUnite2Entite() != null)
					element.setUnite2Designation(uniteArticleService.rechercheUniteArticleParId(element.getUnite2Entite()).getDesignation());
				
		
				
				
		    	Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(element.getSousFamilleArtEntite(), element.getSiteEntite());
		    	//SousFamille, Famille, Type
		    	element.setSousFamilleArtEntiteDesignation(mapA.get("sousFamille"));
		    	element.setFamilleArticleDesignation(mapA.get("famille"));
				element.setTypeArticleDesignation(mapA.get("type"));
				  
				//Site
				element.setSiteEntiteDesignation(mapA.get("site"));

		
				if (element.getRef() != null) {

					sheet3.addCell(new Label(2, i, element.getRef() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getDesignation() != null) {

					sheet3.addCell(new Label(3, i, element.getDesignation() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getSousFamilleArtEntiteDesignation() != null) {

					sheet3.addCell(new Label(4, i, element.getSousFamilleArtEntiteDesignation() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getFamilleArticleDesignation() != null) {

					sheet3.addCell(new Label(5, i, element.getFamilleArticleDesignation() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

				}
				
				

				if (element.getUniteDesignation() != null) {

					sheet3.addCell(new Label(6, i, element.getUniteDesignation() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getUnite2Designation() != null) {

					sheet3.addCell(new Label(7, i, element.getUnite2Designation() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

				}
				

				i++;

			}
		


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
		String filename;
	
			filename = "mp-list" + dateFormat1 + ".xls";
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


	@RequestMapping(value = "/listPrixClient", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListPrixClientReport(

			@RequestBody RechercheMulticritereProduitValue request

	) throws WriteException, IOException {
		
		BaseInfoValue baseInfo= baseInfoService.getClientActif();

		Date d = new Date();

		String dat = "" + dateFormat.format(d);
		
		excel_file_location = baseInfo.getExcelDirectory();


		// this.rapport=true;
		File f;
		if (request.getFamillePiId() == 1)
			f = new File(excel_file_location+"Prix_Client" + "-" + dat + ".xls");
		else
			f = new File(excel_file_location+"Prix_Fournisseur" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		if (request.getFamillePiId() == 1)
			Equilibrageworkbook.createSheet("Prix_Client", 0);
		else
			Equilibrageworkbook.createSheet("Prix_Fournisseur", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 10);

		/**************************************************************************/
		
	
		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}
		

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

		if (request.getFamillePiId() == 1)
			sheet3.addCell(new Label(2, 7, "    Liste des Prix Client", ExcelUtils.boldTitre));
		else
			sheet3.addCell(new Label(2, 7, "    Liste des Prix Fournisseurs", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 11, 8);

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
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getDesignation()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Désignation :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getFamille()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Famille :", ExcelUtils.boldRed3));

			FamilleProduitValue famille = familleProduitService
					.rechercheFamilleProduitById(Long.parseLong(request.getFamille()));

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, famille.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getSousfamille()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Sous Famille :", ExcelUtils.boldRed3));

			SousFamilleProduitValue sousFamille = sousFamilleProduitService
					.rechercheSousFamilleProduitById(Long.parseLong(request.getSousfamille()));

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, sousFamille.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPrix_inf()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Min :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrix_inf().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPrix_sup()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Max :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrix_sup().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPartieInteressee()))

		{
			numLigneCritRech++;
			if (request.getFamillePiId() == 1)
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			else
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));

			PartieInteresseValue partieInteresse = partieInteresseeService
					.getById(Long.parseLong(request.getPartieInteressee()));

			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getGroupeClientId()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));

			GroupeClientValue grp = new GroupeClientValue();
			grp.setId(request.getGroupeClientId());
			GroupeClientValue groupe = groupeClientService.rechercheGroupeClientParId(grp);

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, groupe.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

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

		// request.setOptimized(this.checkForOptimization(request));

		RecherchePrixClientValue recherchePrixClientValue = new RecherchePrixClientValue();
		recherchePrixClientValue.setIdClient(Long.parseLong(request.getPartieInteressee()));
		recherchePrixClientValue.setFamillePartieInteressee(request.getFamillePiId());

		if (isNotEmty(request.getReference())) {
		

			recherchePrixClientValue.setIdProduit(produitService.rechercheProduitParReference(request.getReference()).getId());

		}

		List<PrixClientValue> listPrixClient = prixClientService
				.rechchercheMultiCriterePrixClient(recherchePrixClientValue);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i - 1, 4, i - 1);
		sheet3.addCell(new Label(5, i - 1, "Sous Famille", ExcelUtils.boldRed2));
		sheet3.mergeCells(5, i - 1, 6, i - 1);
		if (request.getFamillePiId() == 1)
			sheet3.addCell(new Label(7, i - 1, "Client", ExcelUtils.boldRed2));

		else
			sheet3.addCell(new Label(7, i - 1, "Fournisseur", ExcelUtils.boldRed2));

		sheet3.mergeCells(7, i - 1, 8, i - 1);
		sheet3.addCell(new Label(9, i - 1, "Prix", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Prix SP", ExcelUtils.boldRed2));

		if (listPrixClient != null && listPrixClient.size() > 0) {

			for (PrixClientValue element : listPrixClient) {

				if (element.getEbproduit() != null) {
					
					ProduitValue produit = produitService.rechercheProduitById(element.getEbproduit());

					sheet3.addCell(new Label(2, i, produit.getReference() + "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(3, i, produit.getDesignation() + "", ExcelUtils.boldRed));
					sheet3.mergeCells(3, i, 4, i);
					sheet3.addCell(new Label(5, i, produit.getSousFamilleDesignation() + "", ExcelUtils.boldRed));
					sheet3.mergeCells(5, i, 6, i);
					if (request.getFamillePiId() == 1)
						if (produit.getPrixVenteTTC() != null)
							sheet3.addCell(new Label(9, i, produit.getPrixVenteTTC() + "", ExcelUtils.boldRed));
						else
							sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

					else if (produit.getPrixVenteTTC() != null)
						sheet3.addCell(new Label(9, i, produit.getPrixAchat() + "", ExcelUtils.boldRed));
					else
						sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

				}

				if (element.getIdpi() != null) {

					PartieInteresseValue pi = partieInteresseeService.getById(element.getIdpi());

					sheet3.addCell(new Label(7, i, pi.getAbreviation() + "", ExcelUtils.boldRed));
					sheet3.mergeCells(7, i, 8, i);

				} else {
					sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
					sheet3.mergeCells(7, i, 8, i);

				}

				if (element.getRemise() != null) {

					sheet3.addCell(new Label(10, i, element.getRemise() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));

				}

				if (element.getPrixvente() != null) {

					sheet3.addCell(new Label(11, i, element.getPrixvente() + "", ExcelUtils.boldRed));

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

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream"));

		Date now = new Date();
		String dateFormat1 = dateFormat.format(now);
		String filename;
		if (request.getFamillePiId() == 1)
			filename = "Prix-Fournisseur" + dateFormat1 + ".xls";
		else
			filename = "Prix-Client" + dateFormat1 + ".xls";
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

	
	@RequestMapping(value = "/listPrixClientArticle", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListPrixClientReportArticle(

			@RequestBody RecherecheMulticritereArticleValue request

	) throws WriteException, IOException {
		
		BaseInfoValue baseInfo= baseInfoService.getClientActif();

		Date d = new Date();

		String dat = "" + dateFormat.format(d);
		
		excel_file_location = baseInfo.getExcelDirectory();	


		// this.rapport=true;
		File f;
		if (request.getFamillePiId()== 1)
			f = new File(excel_file_location+"Prix_Client" + "-" + dat + ".xls");
		else
			f = new File(excel_file_location+"Prix_Fournisseur" + "-" + dat + ".xls");

		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		// WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		if (request.getFamillePiId() == 1)
			Equilibrageworkbook.createSheet("Prix_Client", 0);
		else
			Equilibrageworkbook.createSheet("Prix_Fournisseur", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 12);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 10);

		/**************************************************************************/
		
	
		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}
		

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

		if (request.getFamillePiId() == 1)
			sheet3.addCell(new Label(2, 7, "    Liste des Prix Client", ExcelUtils.boldTitre));
		else
			sheet3.addCell(new Label(2, 7, "    Liste des Prix Fournisseurs", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 11, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getRef()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getRef(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getDesignation()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Désignation :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, request.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getFamilleEntite()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Famille :", ExcelUtils.boldRed3));
			

			FamilleArticleValue famille=familleArticleService.rechercheFamilleArticleById(Long.parseLong(request.getFamilleEntite()));
			
			//FamilleProduitValue famille = familleProduitService.rechercheFamilleProduitById(Long.parseLong(request.getFamille()));

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, famille.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getSousFamilleEntite()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Sous Famille :", ExcelUtils.boldRed3));

			
			SousFamilleArticleValue sousFamille=sousFamilleArticleService.rechercheSousFamilleArticleById(Long.parseLong(request.getSousFamilleEntite()));
			
			//SousFamilleProduitValue sousFamille = sousFamilleProduitService.rechercheSousFamilleProduitById(Long.parseLong(request.getSousfamille()));

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, sousFamille.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPrix_inf()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Min :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrix_inf().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPrix_sup()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Max :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getPrix_sup().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getPartieInteressee()))

		{
			numLigneCritRech++;
			if (request.getFamillePiId() == 1)
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			else
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :", ExcelUtils.boldRed3));

			PartieInteresseValue partieInteresse = partieInteresseeService
					.getById(Long.parseLong(request.getPartieInteressee()));

			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getGetGroupeClientId()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));

			GroupeClientValue grp = new GroupeClientValue();
			grp.setId(request.getGetGroupeClientId());
			GroupeClientValue groupe = groupeClientService.rechercheGroupeClientParId(grp);

			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, groupe.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

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

		// request.setOptimized(this.checkForOptimization(request));

		RecherchePrixClientValue recherchePrixClientValue = new RecherchePrixClientValue();
		recherchePrixClientValue.setIdClient(Long.parseLong(request.getPartieInteressee()));
		recherchePrixClientValue.setFamillePartieInteressee(request.getFamillePiId());

		if (isNotEmty(request.getRef())) {
		
			
			recherchePrixClientValue.setIdProduit(articleService.rechercheProduitParReference(request.getRef()).getId());

			//recherchePrixClientValue.setIdProduit(produitService.rechercheProduitParReference(request.getReference()).getId());

		}

		List<PrixClientValue> listPrixClient = prixClientService
				.rechchercheMultiCriterePrixClient(recherchePrixClientValue);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.mergeCells(3, i - 1, 4, i - 1);
		sheet3.addCell(new Label(5, i - 1, "Sous Famille", ExcelUtils.boldRed2));
		sheet3.mergeCells(5, i - 1, 6, i - 1);
		if (request.getFamillePiId() == 1)
			sheet3.addCell(new Label(7, i - 1, "Client", ExcelUtils.boldRed2));

		else
			sheet3.addCell(new Label(7, i - 1, "Fournisseur", ExcelUtils.boldRed2));

		sheet3.mergeCells(7, i - 1, 8, i - 1);
		sheet3.addCell(new Label(9, i - 1, "Prix", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Remise", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Prix SP", ExcelUtils.boldRed2));

		if (listPrixClient != null && listPrixClient.size() > 0) {

			for (PrixClientValue element : listPrixClient) {

				if (element.getEbproduit() != null) {
					ArticleValue articleValue=new ArticleValue();
					articleValue.setId(element.getEbproduit());
					
					ArticleValue produit=articleService.rechercheArticleParId(articleValue);
					
					//ProduitValue produit = produitService.rechercheProduitById(element.getEbproduit());

					sheet3.addCell(new Label(2, i, produit.getRef() + "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(3, i, produit.getDesignation() + "", ExcelUtils.boldRed));
					sheet3.mergeCells(3, i, 4, i);
					sheet3.addCell(new Label(5, i, produit.getSousFamilleArtEntiteDesignation() + "", ExcelUtils.boldRed));
					sheet3.mergeCells(5, i, 6, i);
					if (request.getFamillePiId() == 1)
						if (produit.getPuTTC()  != null)
							sheet3.addCell(new Label(9, i, produit.getPuTTC() + "", ExcelUtils.boldRed));
						else
							sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

					else if (produit.getPuTTC()  != null)
						sheet3.addCell(new Label(9, i, produit.getPu() + "", ExcelUtils.boldRed));
					else
						sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
					sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

				}

				if (element.getIdpi() != null) {

					PartieInteresseValue pi = partieInteresseeService.getById(element.getIdpi());

					sheet3.addCell(new Label(7, i, pi.getAbreviation() + "", ExcelUtils.boldRed));
					sheet3.mergeCells(7, i, 8, i);

				} else {
					sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
					sheet3.mergeCells(7, i, 8, i);

				}

				if (element.getRemise() != null) {

					sheet3.addCell(new Label(10, i, element.getRemise() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));

				}

				if (element.getPrixvente() != null) {

					sheet3.addCell(new Label(11, i, element.getPrixvente() + "", ExcelUtils.boldRed));

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

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream"));

		Date now = new Date();
		String dateFormat1 = dateFormat.format(now);
		String filename;
		if (request.getFamillePiId() == 1)
			filename = "Prix-Fournisseur" + dateFormat1 + ".xls";
		else
			filename = "Prix-Client" + dateFormat1 + ".xls";
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
	
	
	@RequestMapping(value = "/listproduit", method = RequestMethod.GET)
	public void generateListProduitListReport(

			@RequestParam("reference") String reference, @RequestParam("designation") String designation,
			/* @RequestParam("famille") String famille, */
			@RequestParam("sousfamille") String sousfamille, @RequestParam("partieInteressee") String partieInteressee,
			@RequestParam("etat") String etat, @RequestParam("site") String site,
			@RequestParam("prixInf") Double prixInf, @RequestParam("prixSup") Double prixSup,

			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);
		
		BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();

		// this.rapport=true;
		File f = new File(excel_file_location+"Article List" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Article-List", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 20);
		sheet3.setColumnView(2, 15);

		sheet3.setColumnView(3, 40);
		sheet3.setColumnView(4, 20);
		sheet3.setColumnView(5, 20);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 15);
		sheet3.setColumnView(9, 15);

		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */
		

		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}
		
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

		sheet3.addCell(new Label(2, 7, "   Liste des Articles", ExcelUtils.boldTitre));
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

		RechercheMulticritereProduitValue request = new RechercheMulticritereProduitValue();
		/*
		 * 
		 * 
		 * request.setPartieInteressee(partieInteressee); request.setEtat(etat);
		 * request.setSite(site); request.setPrix_inf(prixInf);
		 * request.setPrix_sup(prixSup);
		 * 
		 */
		if (isNotEmty(reference))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Article Réf. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, reference, ExcelUtils.boldRed3));

			request.setReference(reference);

		}

		if (isNotEmty(designation))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Article Dés. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, designation, ExcelUtils.boldRed3));

			request.setDesignation(designation);

		}

		if (isNotEmty(sousfamille))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Sous Famille. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, sousfamille, ExcelUtils.boldRed3));

			request.setSousfamille(sousfamille);

		}

		if (isNotEmty(partieInteressee))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, partieInteressee, ExcelUtils.boldRed3));

			request.setPartieInteressee(partieInteressee);

		}

		if (isNotEmty(prixInf))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix Du :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixInf + "", ExcelUtils.boldRed3));

			request.setPrix_inf(prixInf);

		}

		if (isNotEmty(prixSup))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prix A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, prixSup + "", ExcelUtils.boldRed3));

			request.setPrix_sup(prixSup);

		}

		ResultatRechecheProduitValue res = produitService.rechercherProduitMultiCritere(request);

		for (ProduitValue elementProduit : res.getProduitValues()) {

			Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(
					elementProduit.getSousFamilleId(), elementProduit.getSiteId(),
					elementProduit.getPartieIntersseId());
			// SousFamille, Famille

			elementProduit.setSousFamilleDesignation(mapA.get("sousFamille"));
			elementProduit.setFamilleDesignation(mapA.get("famille"));
			// Site
			elementProduit.setSiteEntiteDesignation(mapA.get("site"));
			// partieInteressee
			elementProduit.setPartieIntersseDesignation(mapA.get("partieInteressee"));

		}

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(4, i - 1, "Sous Famille", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Famille", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(6, i - 1, "Prix Vente HT", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Prix Achat", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Prix Vente TTC", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(9, i - 1, "Quantite", ExcelUtils.boldRed2));

		Double quantiteTotale = 0d;

		for (ProduitValue element : res.getProduitValues()) {

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getDesignation() != null) {
				sheet3.addCell(new Label(3, i, element.getDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			if (element.getSousFamilleDesignation() != null) {
				sheet3.addCell(new Label(4, i, element.getSousFamilleDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}

			if (element.getFamilleDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getFamilleDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

			}

			// prix unitaire
			if (element.getPrixUnitaire() != null) {
				sheet3.addCell(new Label(6, i, convertisseur(element.getPrixUnitaire(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// prix achat

			if (element.getPrixAchat() != null) {
				sheet3.addCell(new Label(7, i, convertisseur(element.getPrixAchat(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// prix vente

			if (element.getPrixVenteTTC() != null) {
				sheet3.addCell(new Label(8, i, convertisseur(element.getPrixVenteTTC(), 4) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(9, i, convertisseur(element.getQuantite(), 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;

		sheet3.addCell(new Label(2, i, "Nbr Ligne", ExcelUtils.boldRed2));
		// sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, res.getProduitValues().size() + "", ExcelUtils.boldRed2));

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

	@RequestMapping(value = "/listpi", method = RequestMethod.GET)
	public void generateListPIreport(

			@RequestParam("vReference") String vReference, @RequestParam("vRaisonSociale") String vRaisonSociale,
			@RequestParam("vCategoriePartieInteressee") String vCategoriePartieInteressee,
			@RequestParam("groupeClientId") String groupeClientId,
			@RequestParam("vTypePartieInteressee") String vTypePartieInteressee, @RequestParam("actif") String actif,
			@RequestParam("vFamillePartieInteressee") Long vFamillePartieInteressee,
			@RequestParam("nature") String nature,

			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		File f;
		// this.rapport=true;
		
	    BaseInfoValue baseInfo= baseInfoService.getClientActif();
		
		excel_file_location = baseInfo.getExcelDirectory();
		
		if (vFamillePartieInteressee.equals(1l))

			f = new File(excel_file_location+"Liste des Clients" + "-" + dat + ".xls");

		else
			f = new File(excel_file_location+"Liste des Fournisseurs" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("List", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 20);
		sheet3.setColumnView(2, 10);

		sheet3.setColumnView(3, 30);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 15);

		sheet3.setColumnView(9, 20);
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 30);
		sheet3.setColumnView(12, 15);

		sheet3.setColumnView(13, 30);
		sheet3.setColumnView(14, 15);

		/**************************************************************************/

		/*
		 * WritableImage image = new WritableImage(2, 1, // column, row 1, 6, // width,
		 * height in terms of number of cells new
		 * File("C:/ERP/logos_clients/logo.png")); // Supports only 'png' images
		 * 
		 * sheet3.addImage(image);
		 * 
		 */
		

		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}
		

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

		if (vFamillePartieInteressee.equals(1l)) {
			sheet3.addCell(new Label(2, 7, "   Liste des Clients", ExcelUtils.boldTitre));
		}

		else {
			sheet3.addCell(new Label(2, 7, "   Liste des Fournisseurs", ExcelUtils.boldTitre));
		}

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

		RechercheMulticriterePartieInteresseeValue request = new RechercheMulticriterePartieInteresseeValue();

		request.setvFamillePartieInteressee(vFamillePartieInteressee.toString());
		/*
		 * 
		 * 
		 * request.setPartieInteressee(partieInteressee); request.setEtat(etat);
		 * request.setSite(site); request.setPrix_inf(prixInf);
		 * request.setPrix_sup(prixSup);
		 * 
		 */
		
		if (isNotEmty(nature))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Nature. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, nature, ExcelUtils.boldRed3));

			request.setNature(nature);

		}
		
		
		if (isNotEmty(vReference))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, vReference, ExcelUtils.boldRed3));

			request.setvReference(vReference);

		}

		if (isNotEmty(vRaisonSociale))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Raison Sociale. :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, vRaisonSociale, ExcelUtils.boldRed3));

			request.setvRaisonSociale(vRaisonSociale);

		}

		if (isNotEmty(vCategoriePartieInteressee))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Categorie :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, vCategoriePartieInteressee, ExcelUtils.boldRed3));

			request.setvCategoriePartieInteressee(vCategoriePartieInteressee);

		}

		if (isNotEmty(groupeClientId))

		{

			String groupeDesignation = "";

			GroupeClientValue groupeClient = groupeClientService
					.rechercheGroupeClientParId(new GroupeClientValue(Long.parseLong(groupeClientId)));

			if (groupeClient != null && groupeClient.getDesignation() != null) {

				numLigneCritRech++;
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Groupe :", ExcelUtils.boldRed3));
				sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, groupeClient.getDesignation(),
						ExcelUtils.boldRed3));

				request.setGroupeClientId(groupeClient.getId());

			}

		}

		if (isNotEmty(vTypePartieInteressee))

		{
			TypeValue typePi = new TypeValue();
			typePi.setId(Long.parseLong(vTypePartieInteressee));

			TypeValue type = typePartieInteresseeService.rechercheTypePartieInteresseeParId(typePi);

			if (type != null && type.getDesignation() != null) {

				numLigneCritRech++;
				sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type :", ExcelUtils.boldRed3));
				sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, type.getDesignation() + "",
						ExcelUtils.boldRed3));

				request.setvTypePartieInteressee(vTypePartieInteressee);
			}

		}

		if (isNotEmty(actif))

		{

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Actif :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, actif, ExcelUtils.boldRed3));

			request.setActif(actif);

		}

		ResultatRechechePartieInteresseeValue res = partieInteresseeService
				.rechercherPartieInteresseMultiCritere(request);

		for (PartieInteresseValue partieInteressee : res.getPartieInteresseValues()) {
			// Famille, Categorie, Type PI
			Map<String, String> mapA = gestionnaireCacheService.rechercherPartieInteresseeParId(
					partieInteressee.getFamillePartieInteressee(), partieInteressee.getCategoriePartieInteressee(),
					partieInteressee.getTypePartieInteressee());

			partieInteressee.setFamillePartieInteresseeDesignation(mapA.get("famillePi"));
			partieInteressee.setCategoriePartieInteresseeDesignation(mapA.get("categoriePi"));
			partieInteressee.setTypePartieInteresseeDesignation(mapA.get("typePi"));

			if (partieInteressee.getGroupeClientId() != null) {
				partieInteressee.setGroupeClientDesignation(groupeClientService
						.rechercheGroupeClientParId(new GroupeClientValue(partieInteressee.getGroupeClientId()))
						.getDesignation());
			}

		}

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Réference", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Raison Sociale", ExcelUtils.boldRed2));
		//sheet3.addCell(new Label(4, i - 1, "Abreviation", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(4, i - 1, "Catégorie", ExcelUtils.boldRed2));
		//sheet3.addCell(new Label(5, i - 1, "Groupe", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(5, i - 1, "Type", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Tel Mobile", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "fix", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Fax", ExcelUtils.boldRed2));

		sheet3.addCell(new Label(9, i - 1, "Mat.Fiscale", ExcelUtils.boldRed2));
		//sheet3.addCell(new Label(11, i - 1, "Registre.Comm", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Code Postale", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Adresse", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(12, i - 1, "Nature", ExcelUtils.boldRed2));
		
		Double quantiteTotale = 0d;

		for (PartieInteresseValue element : res.getPartieInteresseValues()) {

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			if (element.getRaisonSociale() != null) {
				sheet3.addCell(new Label(3, i, element.getRaisonSociale() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

			}

			/*
			 * if (element.getAbreviation() != null) { sheet3.addCell(new Label(4, i,
			 * element.getAbreviation() + "", ExcelUtils.boldRed));
			 * 
			 * } else { sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			 * 
			 * }
			 */

			if (element.getCategoriePartieInteresseeDesignation() != null) {
				sheet3.addCell(
						new Label(4, i, element.getCategoriePartieInteresseeDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

			}

			/*if (element.getGroupeClientDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getGroupeClientDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

			}*/

			if (element.getTypePartieInteresseeDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getTypePartieInteresseeDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

			}
			
			
			
			
			
			
			if (element.getTelephoneMobile() != null) {
				sheet3.addCell(new Label(6, i, element.getTelephoneMobile() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

			}
			
			
			
			
			

			if (element.getTelephone() != null) {
				sheet3.addCell(new Label(7, i, element.getTelephone() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

			}

			if (element.getFax() != null) {
				sheet3.addCell(new Label(8, i, element.getFax() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

			}

			// mat

			if (element.getMatriculeFiscal() != null) {
				sheet3.addCell(new Label(9, i, element.getMatriculeFiscal() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

			}

			/*
			 * if (element.getRegimeCommercial() != null) { sheet3.addCell(new Label(11, i,
			 * element.getRegimeCommercial() + "", ExcelUtils.boldRed));
			 * 
			 * } else { sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
			 * 
			 * }
			 */

			if (element.getCodeDouane() != null) {
				sheet3.addCell(new Label(10, i, element.getCodeDouane() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));

			}

			if (element.getAdresse() != null) {
				sheet3.addCell(new Label(11, i, element.getAdresse() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));

			}

			if (element.getNature() != null) {
				sheet3.addCell(new Label(12, i, element.getNature() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(12, i, "", ExcelUtils.boldRed));

			}

			i++;

		}

		i++;

		sheet3.addCell(new Label(2, i, "Nbr Ligne", ExcelUtils.boldRed2));
		// sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(3, i, res.getPartieInteresseValues().size() + "", ExcelUtils.boldRed2));

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

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	
	
	
	
	
	/*
	 * @RequestMapping(value = "/listProduitSerialisable", method =
	 * RequestMethod.POST) public ResponseEntity<byte[]>
	 * generateListPoduitSerialisableReport(
	 * 
	 * @RequestBody RechercheMulticritereProduitSerialisableValue request
	 * 
	 * ) throws WriteException, IOException {
	 * 
	 * Date d = new Date();
	 * 
	 * String dat = "" + dateFormat.format(d);
	 * 
	 * // this.rapport=true; File f = new File("Produit_Sérialisable_" + "-" + dat +
	 * ".xls");
	 * 
	 * ByteArrayOutputStream fileOut = new ByteArrayOutputStream(); WritableWorkbook
	 * Equilibrageworkbook = Workbook.createWorkbook(fileOut);
	 * 
	 * // WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
	 * 
	 * Equilibrageworkbook.createSheet("Produit_Sérialisable_", 0); WritableSheet
	 * sheet3 = Equilibrageworkbook.getSheet(0);
	 * 
	 * sheet3.setColumnView(0, 7); sheet3.setColumnView(1, 7);
	 * sheet3.setColumnView(2, 25);
	 * 
	 * sheet3.setColumnView(3, 12); sheet3.setColumnView(4, 30);
	 * sheet3.setColumnView(5, 10);
	 * 
	 *//**************************************************************************/
	/*
	 * 
	 * // Nom du rapport et la date
	 * 
	 * ExcelUtils.init();
	 * 
	 * // Nom du rapport et la date
	 * 
	 * 
	 * sheet3.addCell(new Label(2, 7, "    Liste des Produits Sérialisables",
	 * ExcelUtils.boldTitre));
	 * 
	 * sheet3.mergeCells(2, 7, 11, 8);
	 * 
	 * // Critaire de recherche int numColCritRech = 2; int numLigneCritRech = 14;
	 * 
	 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
	 * "Critère de recherche", ExcelUtils.boldRed5)); sheet3.addCell( new
	 * Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d),
	 * ExcelUtils.boldRed3)); sheet3.mergeCells(numColCritRech + 1,
	 * numLigneCritRech, numColCritRech + 2, numLigneCritRech); numLigneCritRech++;
	 * 
	 * if (isNotEmty(request.getProduitId()))
	 * 
	 * { numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
	 * 
	 * sheet3.addCell( new Label(numColCritRech + 1, numLigneCritRech,
	 * produitService.rechercheProduitById(request.getProduitId()).getReference(),
	 * ExcelUtils.boldRed3)); sheet3.mergeCells(numColCritRech + 1,
	 * numLigneCritRech,numColCritRech + 2, numLigneCritRech);
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getFournisseurId()))
	 * 
	 * { numLigneCritRech++;
	 * 
	 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Fournisseur :",
	 * ExcelUtils.boldRed3));
	 * 
	 * PartieInteresseValue partieInteresse=
	 * partieInteresseeService.getById(request.getFournisseurId());
	 * 
	 * sheet3.addCell( new Label(numColCritRech + 1, numLigneCritRech,
	 * partieInteresse.getAbreviation(), ExcelUtils.boldRed3));
	 * sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2,
	 * numLigneCritRech);
	 * 
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getDateAchatDe()))
	 * 
	 * {
	 * 
	 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Date Achat De :", ExcelUtils.boldRed3));
	 * sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
	 * dateFormat2.format(request.getDateAchatDe().getTime()),
	 * ExcelUtils.boldRed3));
	 * 
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getDateAchatA()))
	 * 
	 * {
	 * 
	 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Date Achat A :", ExcelUtils.boldRed3)); sheet3.addCell(new
	 * Label(numColCritRech + 1, numLigneCritRech,
	 * dateFormat2.format(request.getDateAchatA().getTime()), ExcelUtils.boldRed3));
	 * 
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getDateVenteDe()))
	 * 
	 * {
	 * 
	 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Date Vente De :", ExcelUtils.boldRed3));
	 * sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
	 * dateFormat2.format(request.getDateVenteDe().getTime()),
	 * ExcelUtils.boldRed3));
	 * 
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getDateVenteA()))
	 * 
	 * {
	 * 
	 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Date Vente A :", ExcelUtils.boldRed3)); sheet3.addCell(new
	 * Label(numColCritRech + 1, numLigneCritRech,
	 * dateFormat2.format(request.getDateVenteA().getTime()), ExcelUtils.boldRed3));
	 * 
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getDateFinGarantieDe()))
	 * 
	 * {
	 * 
	 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Date F.Garantie De :", ExcelUtils.boldRed3));
	 * sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
	 * dateFormat2.format(request.getDateFinGarantieDe().getTime()),
	 * ExcelUtils.boldRed3));
	 * 
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getDateFinGarantieA()))
	 * 
	 * {
	 * 
	 * numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Date F.Garantie A :", ExcelUtils.boldRed3));
	 * sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
	 * dateFormat2.format(request.getDateFinGarantieA().getTime()),
	 * ExcelUtils.boldRed3));
	 * 
	 * 
	 * }
	 * 
	 * if (isNotEmty(request.getNumSerie()))
	 * 
	 * { numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Num. Série :", ExcelUtils.boldRed3));
	 * 
	 * sheet3.addCell( new Label(numColCritRech + 1, numLigneCritRech,
	 * request.getNumSerie(), ExcelUtils.boldRed3));
	 * sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2,
	 * numLigneCritRech);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if (isNotEmty(request.getPartieInteressee()))
	 * 
	 * { numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Client :", ExcelUtils.boldRed3));
	 * 
	 * 
	 * 
	 * sheet3.addCell( new Label(numColCritRech + 1,
	 * numLigneCritRech,partieInteresseeService.getById(request.getPartieInteressee(
	 * )).getAbreviation() , ExcelUtils.boldRed3)); sheet3.mergeCells(numColCritRech
	 * + 1, numLigneCritRech,numColCritRech + 2, numLigneCritRech);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * if (isNotEmty(request.getPartieInteressee()))
	 * 
	 * { numLigneCritRech++; sheet3.addCell(new Label(numColCritRech,
	 * numLigneCritRech, "Autres critère :", ExcelUtils.boldRed3));
	 * 
	 * 
	 * 
	 * sheet3.addCell( new Label(numColCritRech + 1,
	 * numLigneCritRech,request.getCritereSpeciale() , ExcelUtils.boldRed3));
	 * sheet3.mergeCells(numColCritRech + 1, numLigneCritRech,numColCritRech + 2,
	 * numLigneCritRech);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
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
	 * 
	 * 
	 * //request.setOptimized(this.checkForOptimization(request));
	 * 
	 * 
	 * 
	 * ResultatRechecheProduitSerialisableValue resultProduitSerialisable =
	 * produitSerialisableService.rechercherProduitSerialisableMultiCritere(request)
	 * ;
	 * 
	 * int i = numLigneCritRech + 4;
	 * 
	 * sheet3.addCell(new Label(2, i - 1, "Boutique", ExcelUtils.boldRed2));
	 * sheet3.addCell(new Label(3, i - 1, "Magasin", ExcelUtils.boldRed2));
	 * sheet3.mergeCells(3, i - 1, 4, i - 1); sheet3.addCell(new Label(5, i - 1,
	 * "Réference", ExcelUtils.boldRed2)); sheet3.mergeCells(5, i - 1, 6, i - 1);
	 * 
	 * sheet3.addCell(new Label(7, i - 1, "N° Série", ExcelUtils.boldRed2));
	 * 
	 * sheet3.mergeCells(7, i - 1, 8, i - 1); sheet3.addCell(new Label(9, i - 1,
	 * "N° BR", ExcelUtils.boldRed2)); sheet3.addCell(new Label(10, i - 1,
	 * "Date Achat", ExcelUtils.boldRed2)); sheet3.addCell(new Label(11, i - 1,
	 * "Client", ExcelUtils.boldRed2)); sheet3.addCell(new Label(12, i - 1, "N° BL",
	 * ExcelUtils.boldRed2)); sheet3.addCell(new Label(13, i - 1, "Date Vente",
	 * ExcelUtils.boldRed2));
	 * 
	 * sheet3.addCell(new Label(14, i - 1, "N° Facture", ExcelUtils.boldRed2));
	 * sheet3.addCell(new Label(15, i - 1, "Date Facture", ExcelUtils.boldRed2));
	 * sheet3.addCell(new Label(16, i - 1, "Prix Vente", ExcelUtils.boldRed2));
	 * sheet3.addCell(new Label(17, i - 1, "Date F.Garantie", ExcelUtils.boldRed2));
	 * 
	 * sheet3.addCell(new Label(18, i - 1, "N° Avoir", ExcelUtils.boldRed2));
	 * sheet3.addCell(new Label(19, i - 1, "Historique BT. Sortie",
	 * ExcelUtils.boldRed2)); sheet3.addCell(new Label(20, i - 1,
	 * "Historique BT. Réception", ExcelUtils.boldRed2));
	 * 
	 * 
	 * 
	 * if(resultProduitSerialisable.getProduitSerialisableValues()!=null &&
	 * resultProduitSerialisable.getProduitSerialisableValues().size()>0) {
	 * 
	 * 
	 * for (ProduitSerialisableValue element :
	 * resultProduitSerialisable.getProduitSerialisableValues()) {
	 * 
	 * if(element.getBoutiqueId()!=null) {
	 * 
	 * BoutiqueValue boutique=new BoutiqueValue();
	 * boutique.setId(element.getBoutiqueId());
	 * 
	 * sheet3.addCell(new Label(2, i,
	 * boutiqueService.rechercheBoutiqueParId(boutique).getAbreviation() + "",
	 * ExcelUtils.boldRed));
	 * 
	 * 
	 * 
	 * } else { sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
	 * 
	 * 
	 * }
	 * 
	 * if(element.getMagasinId()!=null) {
	 * 
	 * Magasin boutique=new BoutiqueValue();
	 * boutique.setId(element.getBoutiqueId());
	 * 
	 * sheet3.addCell(new Label(2, i,
	 * boutiqueService.rechercheBoutiqueParId(boutique).getAbreviation() + "",
	 * ExcelUtils.boldRed));
	 * 
	 * 
	 * 
	 * } else { sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
	 * 
	 * 
	 * }
	 * 
	 * if(element.getEbproduit() != null) { ProduitValue
	 * produit=produitService.rechercheProduitById(element.getEbproduit());
	 * 
	 * 
	 * sheet3.addCell(new Label(2, i, produit.getReference() + "",
	 * ExcelUtils.boldRed)); sheet3.addCell(new Label(3, i, produit.getDesignation()
	 * + "", ExcelUtils.boldRed)); sheet3.mergeCells(3, i , 4, i );
	 * sheet3.addCell(new Label(5, i, produit.getSousFamilleDesignation() + "",
	 * ExcelUtils.boldRed)); sheet3.mergeCells(5, i, 6, i); if
	 * (request.getFamillePiId() == 1) if(produit.getPrixVenteTTC()!= null)
	 * sheet3.addCell(new Label(9, i, produit.getPrixVenteTTC() + "",
	 * ExcelUtils.boldRed)); else sheet3.addCell(new Label(9, i, "",
	 * ExcelUtils.boldRed));
	 * 
	 * else if(produit.getPrixVenteTTC()!= null) sheet3.addCell(new Label(9, i,
	 * produit.getPrixAchat() + "", ExcelUtils.boldRed)); else sheet3.addCell(new
	 * Label(9, i, "", ExcelUtils.boldRed));
	 * 
	 * } else { sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
	 * sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed)); sheet3.addCell(new
	 * Label(5, i, "", ExcelUtils.boldRed)); sheet3.addCell(new Label(9, i, "",
	 * ExcelUtils.boldRed));
	 * 
	 * 
	 * }
	 * 
	 * if(element.getIdpi()!=null) {
	 * 
	 * PartieInteresseValue pi=partieInteresseeService.getById(element.getIdpi());
	 * 
	 * sheet3.addCell(new Label(7, i, pi.getAbreviation() + "",
	 * ExcelUtils.boldRed)); sheet3.mergeCells(7, i , 8, i );
	 * 
	 * 
	 * } else { sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
	 * sheet3.mergeCells(7, i , 8, i);
	 * 
	 * }
	 * 
	 * if(element.getRemise()!=null) {
	 * 
	 * 
	 * sheet3.addCell(new Label(10, i, element.getRemise() + "",
	 * ExcelUtils.boldRed));
	 * 
	 * } else { sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
	 * 
	 * }
	 * 
	 * if(element.getPrixvente()!=null) {
	 * 
	 * sheet3.addCell(new Label(11, i, element.getPrixvente() + "",
	 * ExcelUtils.boldRed));
	 * 
	 * } else { sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
	 * 
	 * }
	 * 
	 * i++;
	 * 
	 * } } i++; i++;
	 * 
	 * 
	 * sheet3.addCell(new Label(7, i, "Totale", ExcelUtils.boldRed2));
	 * sheet3.mergeCells(7, i, 9, i);
	 * 
	 * i++;
	 * 
	 * 
	 *//*******************************************
		 * BAS DU PAGE
		 ****************************************/
	/*
	 * 
	 * int numColBasDuPage = 2; int numLigneBasDuPage = i + 2;
	 * 
	 * Equilibrageworkbook.write(); Equilibrageworkbook.close();
	 * 
	 *//******************************************************************************************/
	/*
	 * HttpHeaders headers = new HttpHeaders(); headers.setContentType(new
	 * MediaType("application", "octet-stream"));
	 * 
	 * Date now = new Date(); String dateFormat1 = dateFormat.format(now); String
	 * filename; if (request.getFamillePiId() == 1) filename = "Prix-Fournisseur" +
	 * dateFormat1 + ".xls"; else filename = "Prix-Client" + dateFormat1 + ".xls";
	 * // String filename = "sortie-stock_" + dateFormat1 ;
	 * headers.setContentDispositionFormData(filename, filename);
	 * headers.setCacheControl("must-revalidate, post-check=0, pre-check=0"); return
	 * new ResponseEntity<>(fileOut.toByteArray(), headers, HttpStatus.OK);
	 *//******************************************************************************************/

	/*
	
	*//****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************//*
											 * 
											 * 
											 * // context.getExternalContext().getResponse();
											 * response.setHeader("Content-disposition", "attachment; filename=" +
											 * f.getName()); // System.out.println("nom du fichier" + f.getName());
											 * response.setContentType("application/vnd.ms-excel"); int bufferSize = 64
											 * * 1024;
											 * 
											 * 
											 * }
											 */
	@RequestMapping(value = "/listArticleProduit", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateListArticleProduitReport(

			@RequestBody RechercheMulticritereArticleProduitValue request
			

	) throws WriteException, IOException {
		
		BaseInfoValue baseInfo= baseInfoService.getClientActif();

		Date d = new Date();

		String dat = "" + dateFormat.format(d);
		
		excel_file_location = baseInfo.getExcelDirectory();


		// this.rapport=true;
		File f = new File(excel_file_location+"Article_Produit" + "-" + dat + ".xls");


		ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(fileOut);

		
		Equilibrageworkbook.createSheet("Article_Produit", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 25);

		sheet3.setColumnView(3, 30);
		sheet3.setColumnView(4, 20);
		sheet3.setColumnView(5, 20);
		sheet3.setColumnView(6, 20);
		sheet3.setColumnView(7, 20);
		sheet3.setColumnView(8, 20);
		sheet3.setColumnView(9, 20);
		sheet3.setColumnView(10, 20);

		/**************************************************************************/
		
	
		
		if(baseInfo.getLogoPNG()!=null) {
			WritableImage image = new WritableImage(2, 1, 1, 6, new File(baseInfo.getLogoPNG()));
			sheet3.addImage(image);
		}
		

		// Nom du rapport et la date

		ExcelUtils.init();

		// Nom du rapport et la date

	
		sheet3.addCell(new Label(2, 7, "    Liste des Articles du Produit", ExcelUtils.boldTitre));

		sheet3.mergeCells(2, 7, 7, 8);

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", ExcelUtils.boldRed5));
		sheet3.addCell(
				new Label(numColCritRech + 1, numLigneCritRech, "" + dateTimeFormat2.format(d), ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);
		numLigneCritRech++;

		if (isNotEmty(request.getArticleId()))

		{
			ProduitValue produitValue= produitService.rechercheProduitById(request.getArticleId());
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Réf :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produitValue.getReference(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getArticleId()))

		{			ProduitValue produitValue= produitService.rechercheProduitById(request.getArticleId());

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Désignation :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produitValue.getDesignation() ,ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getSousFamilleArticleId()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Famille :", ExcelUtils.boldRed3));
			
			
			
			SousFamilleArticleValue sousFamilleArticleValue= sousFamilleArticleService.rechercheSousFamilleArticleById(request.getSousFamilleArticleId());


			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, sousFamilleArticleValue.getDesignation(), ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		
		if (isNotEmty(request.getDimension()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Dimension :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getDimension().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}

		if (isNotEmty(request.getGrammage()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Grammage:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getGrammage().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getQteDe()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Quantité De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getQteDe().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getQteA()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "quantité A:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getQteA().toString(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getInfoMatiere()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Info Matiere:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getInfoMatiere(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getProduitSemiFini()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "ProduitSemiFini:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, request.getProduitSemiFini(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		if (isNotEmty(request.getImpressionProduitId()))

		{
						ImpressionProduitValue impressionProduitValue= iImpressionProduitService.rechercheImpressionProduitParId(request.getImpressionProduitId());

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "ImpressionProduit:", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, impressionProduitValue.getDesignation(),
					ExcelUtils.boldRed3));
			sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 2, numLigneCritRech);

		}
		 request.setOptimized(RechercheMulticritereArticleProduitValue.checkForOptimization(request));

		ResultatRechecheArticleProduitValue  result =  articleProduitService.rechercherProduitArticleMultiCritere(request);



		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Sous Famille", ExcelUtils.boldRed2));
	
		sheet3.addCell(new Label(5, i - 1, "Dimension", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Grammage", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Quantité", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Info Matiere", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(9, i - 1, "ProduitSemiFini", ExcelUtils.boldRed2));
		sheet3.addCell(new Label(10, i - 1, "ImpressionProduit", ExcelUtils.boldRed2));
	

			for (ArticleProduitValue element : result.getList()) {
				
				
				
		
				if (element.getReferenceArticle() != null) {

					sheet3.addCell(new Label(2, i, element.getReferenceArticle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getDesignationArticle() != null) {

					sheet3.addCell(new Label(3, i, element.getDesignationArticle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getReferenceFamilleArticle() != null) {

					sheet3.addCell(new Label(4, i, element.getReferenceFamilleArticle() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getDimension() != null) {

					sheet3.addCell(new Label(5, i, element.getDimension() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));

				}
				
				

				if (element.getGrammage() != null) {

					sheet3.addCell(new Label(6, i, element.getGrammage() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));

				}
				
				
				if (element.getQte() != null) {

					sheet3.addCell(new Label(7, i, element.getQte() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));

				}
				if (element.getInfoMatiere() != null) {

					sheet3.addCell(new Label(8, i, element.getInfoMatiere() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));

				}
				if (element.getProduitSemiFini() != null) {

					sheet3.addCell(new Label(9, i, element.getProduitSemiFini() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));

				}
				if (element.getImpressionProduitId() != null) {

					sheet3.addCell(new Label(10, i, element.getImpressionDesignation() + "", ExcelUtils.boldRed));

				} else {
					sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));

				}
				i++;

			}
		


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
		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "octet-stream"));

		Date now = new Date();
		String dateFormat1 = dateFormat.format(now);
		String filename;
	
			filename = "mp-list" + dateFormat1 + ".xls";
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

