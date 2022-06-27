package com.gpro.consulting.tiers.logistique.rest.report.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MoldsValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl.PartieInteresseePersistanceImpl;
import com.gpro.consulting.tiers.commun.service.elementBase.IMoldsService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ElementRechechePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineOFValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ElementRechecheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.inventaire.value.InventaireReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ElementResultatRechecheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportProductValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities.DetLivraisonVenteValidateComparator;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.utilities.MisePersistanceUtilities;
import com.gpro.consulting.tiers.logistique.rest.report.utilities.ExcelUtils;
import com.gpro.consulting.tiers.logistique.service.atelier.bonsortiefini.IBonSortieFiniService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService;
import com.gpro.consulting.tiers.logistique.service.atelier.prepMoule.IPrepMouleService;
import com.gpro.consulting.tiers.logistique.service.atelier.report.IGestionnaireReportService;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IRouleauFiniService;
import com.gpro.consulting.tiers.logistique.service.gc.report.IGestionnaireReportGcService;
import com.gpro.consulting.tiers.logistique.service.gl.machine.IMachineService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
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
@RequestMapping("/fichesLogistique")
@SuppressWarnings("static-access")
public class GestionnaireFicheLogistiqueRestImpl extends AbstractGestionnaireDownloadImpl {
	
	private static final String SOUS_FAMILLE_PRODUIT_INJECTED = "INJECTED";
	private static final String SOUS_FAMILLE_PRODUIT_ASSEMBLED = "Assembled";
	private static final String SOUS_FAMILLE_PRODUIT_PAINTED = "PAINTED";
	private static final String SOUS_FAMILLE_PRODUIT_SPRAYED = "SPRAYED";

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireFicheLogistiqueRestImpl.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_CALENDAR_FORMAT = "EEE MMM dd yyyy";

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	SimpleDateFormat dateTimeFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Autowired
	private IMiseService miseService;

	@Autowired
	private IRouleauFiniService rouleauFiniService;

	@Autowired
	private IProduitService produitService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	@Autowired
	private IGestionnaireReportGcService gestionnaireReportGcService;

	@Autowired
	private IBonSortieFiniService bonSortieFiniService;
	
	@Autowired
	private IMachineService machineService;
	
	@Autowired
	IPrepMouleService prepMouleService;
	
	
	@Autowired
	IMoldsService moldsService;

	@Autowired
	IGestionnaireReportService gestionnaireReportService;
	

	@Autowired
	private IPartieInteresseeService partieInteresseeService;
	
	@Autowired
	private IProduitPersistance produitPersistance;
	
	@Autowired
	private MisePersistanceUtilities vMisePersistanceUtilities;
	/*
	 * @Autowired IBaseInfoService baseInfoService;
	 */

	/*
	 * @Autowired private IGestionnaireReportGpaoPlanningService
	 * gestionnaireReportGpaoPlanningService;
	 */

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/suivi-of", method = RequestMethod.GET)
	public void generateProductionReportExel(

			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "num", required = false) String num,
	

			@RequestParam(value = "dateLivDe", required = false) String dateLivDe,
			@RequestParam(value = "dateLivA", required = false) String dateLivA,

			@RequestParam(value = "dateIntroDe", required = false) String dateIntroDe,
			@RequestParam(value = "dateIntroA", required = false) String dateIntroA,

			@RequestParam(value = "dateDebutProdDe", required = false) String dateDebutProdDe,
			@RequestParam(value = "dateDebutProdA", required = false) String dateDebutProdA,

			@RequestParam(value = "dateFinProdDe", required = false) String dateFinProdDe,
			@RequestParam(value = "dateFinProdA", required = false) String dateFinProdA,

			@RequestParam(value = "etatProduced", required = false) String etatProduced,
			@RequestParam(value = "etatShipped", required = false) String etatShipped,
			
			@RequestParam(value = "machine", required = false) String machine,
			
			
			@RequestParam(value = "client", required = false) String client,
			@RequestParam(value = "produitId", required = false) String produitId,

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("suivi-production" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("suivi-production", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		
		
		sheet3.setColumnView(2, 22);// type
		sheet3.setColumnView(3, 22);// Machine

		sheet3.setColumnView(4, 15);// Reference

		sheet3.setColumnView(5, 30); // Designation

		sheet3.setColumnView(6, 15);// num of

		sheet3.setColumnView(7, 20); // date intro

		sheet3.setColumnView(8, 20); // date liv

		sheet3.setColumnView(9, 15); // qte demande

		sheet3.setColumnView(10, 15); // quantite produit

		sheet3.setColumnView(11, 15); // quantite restante

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "Rapport Suivie O.F", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7,12, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche ", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "Le " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

		RechercheMulticritereMiseValue request = new RechercheMulticritereMiseValue();

		if (isNotEmty(state))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Statut :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, state, ExcelUtils.boldRed3));

			request.setStatut(state);
		}

		if (isNotEmty(type))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, type, ExcelUtils.boldRed3));

			request.setType(type);
		}

	/*	if (isNotEmty(reference))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Reference :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, reference, ExcelUtils.boldRed3));

			request.setReferenceProduit(reference);

		}
		*/
		
		if (isNotEmty(produitId))

			
		{
			
			Long idProd = Long.parseLong(produitId);
			ProduitValue p = produitService.rechercheProduitById(idProd);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Reference :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, p.getReference(), ExcelUtils.boldRed3));

			request.setProduitId(idProd);

		}
		
		
		

		if (isNotEmty(client))

	
		{
			
			Long idClient = Long.parseLong(client);
			
			PartieInteresseValue pi = partieInteresseeService.getById(idClient);
			

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, pi.getAbreviation(), ExcelUtils.boldRed3));

			request.setClient(idClient);

		}
		
		

		if (isNotEmty(num))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "O.F :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, num, ExcelUtils.boldRed3));

			request.setReferenceMise(num);
		}
		
		if (isNotEmty(machine))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Machine :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, machine, ExcelUtils.boldRed3));

			request.setMachine(machine);
		}

		if (isNotEmty(etatProduced))

		{
			if (etatProduced != null && etatProduced.equals("PLUS"))
				etatProduced = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère Qte Prod. ", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatProduced, ExcelUtils.boldRed3));

			request.setEtatProduced(etatProduced);

		}

		if (isNotEmty(etatShipped))

		{
			if (etatShipped != null && etatShipped.equals("PLUS"))
				etatShipped = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Qte Expediée ", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatShipped, ExcelUtils.boldRed3));

			request.setEtatShipped(etatShipped);

		}

		if (isNotEmty(dateIntroDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Intro. De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateIntroDe).getTime()),
					ExcelUtils.boldRed3));

			request.setDateIntroductionDE(calendarStringToCalendarObject(dateIntroDe));

		}
		if (isNotEmty(dateIntroA)) {
			// System.out.println("#################### DateLivraison A " + dateA);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Intro. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateIntroA).getTime()),
					ExcelUtils.boldRed3));

			request.setDateIntroductionA(calendarStringToCalendarObject(dateIntroA));
		}

		if (isNotEmty(dateDebutProdDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Debut Prod. De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, new SimpleDateFormat("dd-MM-yyyy")
					.format(calendarStringToCalendarObject(dateDebutProdDe).getTime()), ExcelUtils.boldRed3));

			request.setDateDebutProductionDe(calendarStringToCalendarObject(dateDebutProdDe));

		}
		if (isNotEmty(dateDebutProdA)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Debut Prod. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateDebutProdA).getTime()),
					ExcelUtils.boldRed3));

			request.setDateDebutProductionA(calendarStringToCalendarObject(dateDebutProdA));

		}

		if (isNotEmty(dateFinProdDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Fin Prod. De :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateFinProdDe).getTime()),
					ExcelUtils.boldRed3));

			request.setDateFinProductionDe(calendarStringToCalendarObject(dateFinProdDe));

		}
		if (isNotEmty(dateFinProdA)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Fin Prod. A :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateFinProdA).getTime()),
					ExcelUtils.boldRed3));

			request.setDateFinProductionA(calendarStringToCalendarObject(dateFinProdA));

		}
		
		request.setOptimized(vMisePersistanceUtilities.checkForOptimization(request));

		ResultatRechercheMiseValue resultatRecherche = miseService.rechercherMiseMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Type", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Machine", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Designation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "O.F", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "Date Introd.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Date Fin Th.", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(9, i - 1, "Qte O.F", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(10, i - 1, "Qte Produite", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(11, i - 1, "Qte Restante", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(12, i - 1, "Qte Expédiée", ExcelUtils.arial_bold_s10_white_AUTO));
		

		Long sommeQuantite = 0l;
		Long sommeQuantiteProduced = 0l;
		Long sommeQuantiteRestante = 0l;

		Long sommeQuantiteExpedier = 0l;
		for (ElementRechecheMiseValue element : resultatRecherche.getListeElementRechecheMiseValeur()) {
			
			
			// type

			if (element.getTypeOF() != null) {
				sheet3.addCell(new Label(2, i, element.getTypeOF() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			// machine

			if (element.getMachine() != null) {
				sheet3.addCell(new Label(3, i, element.getMachine() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			// reference

			if (element.getReferenceProduit() != null) {
				sheet3.addCell(new Label(4, i, element.getReferenceProduit() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// designation of

			if (element.getDesignationProduitDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getDesignationProduitDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// numero of

			if (element.getReference() != null) {
				sheet3.addCell(new Label(6, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// Intro

			if (element.getDateIntroduction() != null) {

				sheet3.addCell(new Label(7, i, dateTimeFormat.format(element.getDateIntroduction().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// end date

			if (element.getDateFin() != null) {
				sheet3.addCell(new Label(8, i, dateTimeFormat.format(element.getDateFin().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(9, i, element.getQuantite().longValue() + "", ExcelUtils.boldRed));

				sommeQuantite += element.getQuantite().longValue();

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			// quantite produite

			if (element.getQteProduite() != null) {
				sheet3.addCell(new Label(10, i, element.getQteProduite() + "", ExcelUtils.boldRed));

				sommeQuantiteProduced += element.getQteProduite();
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}

			// qte restante

			if (element.getQteProduite() != null && element.getQuantite() != null) {

				Long qteRestante = element.getQuantite().longValue() - element.getQteProduite();
				sheet3.addCell(new Label(11, i, qteRestante + "", ExcelUtils.boldRed));

				sommeQuantiteRestante += qteRestante;
			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
			}
			
			
			

			// quantite expedier

			if (element.getQteExpedition() != null) {
				sheet3.addCell(new Label(12, i, element.getQteExpedition().longValue() + "", ExcelUtils.boldRed));

				sommeQuantiteExpedier += element.getQteExpedition().longValue();
			} else {
				sheet3.addCell(new Label(12, i, "", ExcelUtils.boldRed));
			}
			

			i++;

		}

		i++;

		sheet3.addCell(new Label(9, i, sommeQuantite + "", ExcelUtils.boldRed3));

		sheet3.addCell(new Label(10, i, sommeQuantiteProduced + "", ExcelUtils.boldRed3));

		sheet3.addCell(new Label(11, i, sommeQuantiteRestante + "", ExcelUtils.boldRed3));
		
		sheet3.addCell(new Label(12, i, sommeQuantiteExpedier + "", ExcelUtils.boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"Number of lines : " + resultatRecherche.getListeElementRechecheMiseValeur().size(),
				ExcelUtils.boldRed5));
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/op-mold", method = RequestMethod.GET)
	public void generateOpMoldReportExel(
			@RequestParam(value = "mouleId", required = false) Long mouleId,
			@RequestParam(value = "machine", required = false) String machine,
			@RequestParam(value = "emplacement", required = false) String emplacement,
			@RequestParam(value = "designation", required = false) String designation,
			
			@RequestParam(value = "datePrepDe", required = false) String datePrepDe,
			@RequestParam(value = "datePrepA", required = false) String datePrepA,

		
		

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("OP-Mold" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("OP-Mold", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		
		
		sheet3.setColumnView(2, 22); // Numero
		sheet3.setColumnView(3, 13); // Machine
		sheet3.setColumnView(4, 15);// Reference

		sheet3.setColumnView(5, 30); // Designation
		sheet3.setColumnView(6, 15);//emplacement
		sheet3.setColumnView(7, 20); // date intro
	

	

		
		

	

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "O.P Mold", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 7, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Search Criteria", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "On " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

		RechercheMulticriterePrepMouleValue request = new RechercheMulticriterePrepMouleValue();

		if (isNotEmty(mouleId))

		{
			MoldsValue molds = new MoldsValue();
			molds.setId(mouleId);
			MoldsValue m = moldsService.rechercheMoldsParId(molds);
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Moule :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, m.getReference(), ExcelUtils.boldRed3));

			request.setIdMoule(mouleId);
		}
		
		if (isNotEmty(machine))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Machine :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, machine, ExcelUtils.boldRed3));

			request.setMachine(machine);
		}
		
		if (isNotEmty(emplacement))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Emplacement :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, emplacement, ExcelUtils.boldRed3));

			request.setEmplacement(emplacement);
		}

		if (isNotEmty(designation))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Description :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, designation, ExcelUtils.boldRed3));

			request.setDesignation(designation);
		}

		if (isNotEmty(datePrepDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prep. Date From :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(datePrepDe).getTime()),
					ExcelUtils.boldRed3));

			request.setDatePrep(calendarStringToCalendarObject(datePrepDe));

		}
		if (isNotEmty(datePrepA)) {
			// System.out.println("#################### DateLivraison A " + dateA);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prep. Date To :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(datePrepA).getTime()),
					ExcelUtils.boldRed3));

			request.setDatePrepF(calendarStringToCalendarObject(datePrepA));
		}

		
		
		ResultatRecherchePrepMouleValue  resultatRecherche = prepMouleService.rechercherPrepMouleMultiCritere(request);



		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "N°", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Machine", ExcelUtils.arial_bold_s10_white_AUTO));
	
		sheet3.addCell(new Label(4, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Designation", ExcelUtils.arial_bold_s10_white_AUTO));
		
		sheet3.addCell(new Label(6, i - 1, "Location", ExcelUtils.arial_bold_s10_white_AUTO));
		
		sheet3.addCell(new Label(7, i - 1, "Prep. Date", ExcelUtils.arial_bold_s10_white_AUTO));

		



		for (ElementRechechePrepMouleValue element : resultatRecherche.getListeElementRechechePrepMouleValeur()) {
			
			
			
			// numero

			if (element.getIdPrepMoule() != null) {
							sheet3.addCell(new Label(2, i, element.getIdPrepMoule() + "", ExcelUtils.boldRed));

				} else {
							sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
						}
			
			
			// machine

			if (element.getMachine() != null) {
							sheet3.addCell(new Label(3, i, element.getMachine() + "", ExcelUtils.boldRed));

				} else {
							sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
						}

			// ref mold

			if (element.getReference() != null) {
				sheet3.addCell(new Label(4, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// designation

			if (element.getDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			// emplacement

			if (element.getEmplacement() != null) {
				sheet3.addCell(new Label(6, i, element.getEmplacement() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			

	

			// Intro

			if (element.getDatePrep() != null) {

				sheet3.addCell(new Label(7, i, dateTimeFormat.format(element.getDatePrep().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

		
			

			i++;

		}

		i++;

		//sheet3.addCell(new Label(7, i, sommeQuantite + "", ExcelUtils.boldRed3));

		//sheet3.addCell(new Label(8, i, sommeQuantiteProduced + "", ExcelUtils.boldRed3));

		//sheet3.addCell(new Label(9, i, sommeQuantiteRestante + "", ExcelUtils.boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"Number of lines : " + resultatRecherche.getListeElementRechechePrepMouleValeur().size(),
				ExcelUtils.boldRed5));
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/of", method = RequestMethod.GET)
	public void generateOFReportExel(

			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "num", required = false) String num,
		

			@RequestParam(value = "dateLivDe", required = false) String dateLivDe,
			@RequestParam(value = "dateLivA", required = false) String dateLivA,

			@RequestParam(value = "dateIntroDe", required = false) String dateIntroDe,
			@RequestParam(value = "dateIntroA", required = false) String dateIntroA,

			@RequestParam(value = "dateDebutProdDe", required = false) String dateDebutProdDe,
			@RequestParam(value = "dateDebutProdA", required = false) String dateDebutProdA,

			@RequestParam(value = "dateFinProdDe", required = false) String dateFinProdDe,
			@RequestParam(value = "dateFinProdA", required = false) String dateFinProdA,

			@RequestParam(value = "etatProduced", required = false) String etatProduced,
			@RequestParam(value = "etatShipped", required = false) String etatShipped,
			@RequestParam(value = "machine", required = false) String machine,
			
			@RequestParam(value = "client", required = false) String client,
			@RequestParam(value = "produitId", required = false) String produitId,

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("OF" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("OF", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		
		
		sheet3.setColumnView(2, 22); // Type OF
		sheet3.setColumnView(3, 10); // Machine
		
		
		sheet3.setColumnView(4, 15);// num of

		sheet3.setColumnView(5, 15);// Reference

		sheet3.setColumnView(6, 30); // Designation

	

		sheet3.setColumnView(7, 20); // date intro
		sheet3.setColumnView(8, 20); // end date

	

		sheet3.setColumnView(9, 15); // qte demande
		
		sheet3.setColumnView(10, 15); // qte by box

	

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/logo_client.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "O.F Report", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 10, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Search Criteria", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "On " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

		RechercheMulticritereMiseValue request = new RechercheMulticritereMiseValue();

		if (isNotEmty(state))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "State :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, state, ExcelUtils.boldRed3));

			request.setStatut(state);
		}

		if (isNotEmty(type))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, type, ExcelUtils.boldRed3));

			request.setType(type);
		}

		if (isNotEmty(produitId))

	
		{
			
			Long idProd = Long.parseLong(produitId);
			ProduitValue p = produitService.rechercheProduitById(idProd);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Reference :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, p.getReference(), ExcelUtils.boldRed3));

			request.setProduitId(idProd);

		}
		
		
		

		if (isNotEmty(client))

	
		{
			
			Long idClient = Long.parseLong(client);
			
			PartieInteresseValue pi = partieInteresseeService.getById(idClient);
			

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, pi.getAbreviation(), ExcelUtils.boldRed3));

			request.setClient(idClient);

		}

		if (isNotEmty(num))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "O.F :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, num, ExcelUtils.boldRed3));

			request.setReferenceMise(num);
		}
		
		
		if (isNotEmty(machine))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Machine :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, machine, ExcelUtils.boldRed3));

			request.setMachine(machine);
		}

		if (isNotEmty(etatProduced))

		{
			if (etatProduced != null && etatProduced.equals("PLUS"))
				etatProduced = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Criteria Produced Qte ", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatProduced, ExcelUtils.boldRed3));

			request.setEtatProduced(etatProduced);

		}

		if (isNotEmty(etatShipped))

		{
			if (etatShipped != null && etatShipped.equals("PLUS"))
				etatShipped = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Criteria Shipped Qte ", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatShipped, ExcelUtils.boldRed3));

			request.setEtatShipped(etatShipped);

		}

		if (isNotEmty(dateIntroDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Introd. Date From :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateIntroDe).getTime()),
					ExcelUtils.boldRed3));

			request.setDateIntroductionDE(calendarStringToCalendarObject(dateIntroDe));

		}
		if (isNotEmty(dateIntroA)) {
			// System.out.println("#################### DateLivraison A " + dateA);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Introd. Date To :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateIntroA).getTime()),
					ExcelUtils.boldRed3));

			request.setDateIntroductionA(calendarStringToCalendarObject(dateIntroA));
		}

		if (isNotEmty(dateDebutProdDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Begin Prod. Date From :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, new SimpleDateFormat("dd-MM-yyyy")
					.format(calendarStringToCalendarObject(dateDebutProdDe).getTime()), ExcelUtils.boldRed3));

			request.setDateDebutProductionDe(calendarStringToCalendarObject(dateDebutProdDe));

		}
		if (isNotEmty(dateDebutProdA)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Begin Prod. Date To :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateDebutProdA).getTime()),
					ExcelUtils.boldRed3));

			request.setDateDebutProductionA(calendarStringToCalendarObject(dateDebutProdA));

		}

		if (isNotEmty(dateFinProdDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "End Prod. Date From :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateFinProdDe).getTime()),
					ExcelUtils.boldRed3));

			request.setDateFinProductionDe(calendarStringToCalendarObject(dateFinProdDe));

		}
		if (isNotEmty(dateFinProdA)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "End Prod. Date To :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateFinProdA).getTime()),
					ExcelUtils.boldRed3));

			request.setDateFinProductionA(calendarStringToCalendarObject(dateFinProdA));

		}
		
		request.setOptimized(vMisePersistanceUtilities.checkForOptimization(request));
		

		ResultatRechercheMiseValue resultatRecherche = miseService.rechercherMiseMultiCritere(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Type", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Machine", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "O.F", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Designation", ExcelUtils.arial_bold_s10_white_AUTO));
		
		sheet3.addCell(new Label(7, i - 1, "Intro. Date", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Th. End Date ", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(9, i - 1, "Qty", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(10, i - 1, "Qty By Box", ExcelUtils.arial_bold_s10_white_AUTO));
		

		Long sommeQuantite = 0l;


		for (ElementRechecheMiseValue element : resultatRecherche.getListeElementRechecheMiseValeur()) {
			
			
			
			// type OF

			if (element.getTypeOF() != null) {
							sheet3.addCell(new Label(2, i, element.getTypeOF() + "", ExcelUtils.boldRed));

				} else {
							sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
						}
			
			
			// machine

			if (element.getMachine() != null) {
							sheet3.addCell(new Label(3, i, element.getMachine() + "", ExcelUtils.boldRed));

				} else {
							sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
						}

			// numero of

			if (element.getReference() != null) {
				sheet3.addCell(new Label(4, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// reference

			if (element.getReferenceProduit() != null) {
				sheet3.addCell(new Label(5, i, element.getReferenceProduit() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// designation of

			if (element.getDesignationProduitDesignation() != null) {
				sheet3.addCell(new Label(6, i, element.getDesignationProduitDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

	

			// Intro

			if (element.getDateIntroduction() != null) {

				sheet3.addCell(new Label(7, i, dateTimeFormat.format(element.getDateIntroduction().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// end date

			if (element.getDateFin() != null) {
				sheet3.addCell(new Label(8, i, dateTimeFormat.format(element.getDateFin().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(9, i, element.getQuantite() + "", ExcelUtils.boldRed));

				//sommeQuantite += element.getQuantite().longValue();

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			// quantite produite

			if (element.getPoidFini() != null) {
				sheet3.addCell(new Label(10, i, element.getPoidFini() + "", ExcelUtils.boldRed));

				//sommeQuantiteProduced += element.getQteProduite();
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}

			

			i++;

		}

		i++;

		//sheet3.addCell(new Label(7, i, sommeQuantite + "", ExcelUtils.boldRed3));

		//sheet3.addCell(new Label(8, i, sommeQuantiteProduced + "", ExcelUtils.boldRed3));

		//sheet3.addCell(new Label(9, i, sommeQuantiteRestante + "", ExcelUtils.boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"Number of lines : " + resultatRecherche.getListeElementRechecheMiseValeur().size(),
				ExcelUtils.boldRed5));
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/production-user", method = RequestMethod.GET)
	public void generateProductionUserReportExel(

			@RequestParam(value = "numMise", required = false) String numMise,
			@RequestParam(value = "idProduitReference", required = false) String idProduitReference,
			@RequestParam(value = "produitId", required = false) String produitId,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "dateProductionDe", required = false) String dateProductionDe,
			@RequestParam(value = "dateProductionA", required = false) String dateProductionA,
			@RequestParam(value = "typeOf", required = false) String typeOf,

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("suivi-production-user" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("suivi-production-user", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 22);// Reference

		sheet3.setColumnView(3, 30); // Designation

		sheet3.setColumnView(4, 20);// num of

		sheet3.setColumnView(5, 20); // date intro

		sheet3.setColumnView(6, 20); // date liv

		sheet3.setColumnView(7, 15); // qte demande

		sheet3.setColumnView(8, 15); // quantite produit

		sheet3.setColumnView(9, 15); // quantite restante

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "Production O.F By User ", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 8, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Search Criteria", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "On " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

		RechercheMulticritereRouleauFiniStandardValue request = new RechercheMulticritereRouleauFiniStandardValue();

		if (isNotEmty(username))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Username :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, username, ExcelUtils.boldRed3));

			request.setUsername(username);
		}

		if (isNotEmty(typeOf))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type OF :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeOf, ExcelUtils.boldRed3));

			request.setTypeOf(typeOf);
		}

		if (isNotEmty(produitId))

		{
			ProduitValue produit = produitService.rechercheProduitById(Long.parseLong(produitId));
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Designation :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produit.getDesignation(), ExcelUtils.boldRed3));

			request.setProduitId(Long.parseLong(produitId));
		}

		if (isNotEmty(idProduitReference))

		{
			ProduitValue produit = produitService.rechercheProduitById(Long.parseLong(idProduitReference));

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Reference :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produit.getReference(), ExcelUtils.boldRed3));

			request.setProduitId(Long.parseLong(idProduitReference));

		}

		if (isNotEmty(numMise))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "O.F :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, numMise, ExcelUtils.boldRed3));

			request.setNumMise(numMise);
		}

		if (isNotEmty(dateProductionDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			dateProductionDe = dateProductionDe.replace('T', ' ');

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prod. Date From :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.format(stringDateTimeToCalendar(dateProductionDe).getTime()), ExcelUtils.boldRed3));

			request.setDateProductionDe(stringDateTimeToCalendar(dateProductionDe));

		}
		if (isNotEmty(dateProductionA)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			dateProductionA = dateProductionA.replace('T', ' ');

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prod. Date To :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.format(stringDateTimeToCalendar(dateProductionA).getTime()), ExcelUtils.boldRed3));

			request.setDateProductionA(stringDateTimeToCalendar(dateProductionA));

		}

		ResultatRechecheRouleauFiniValue resultatRecherche = rouleauFiniService.rechercherMultiCritereStandard(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "O.F", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Article", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Article Desig.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Prod. Date", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "Prod. Qty", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "User", ExcelUtils.arial_bold_s10_white_AUTO));

		Long sommeQuantite = 0l;
		Long sommeQuantiteProduced = 0l;
		Long sommeQuantiteRestante = 0l;

		for (RouleauFiniValue elementRouleauFini : resultatRecherche.getList()) {

			// Client, Produit
			Map<String, String> mapA = gestionnaireLogistiqueCacheService.rechercherDesignationParId(
					elementRouleauFini.getPartieInteresseeId(), elementRouleauFini.getProduitId(),
					elementRouleauFini.getEntrepot(), elementRouleauFini.getChoix());

			// Client
			elementRouleauFini.setPartieInteresseeIdDesignation(mapA.get("client"));
			// Produit (Tissu): designation, reference
			elementRouleauFini.setProduitIdDesignation(mapA.get("produit"));
			elementRouleauFini.setProduitReference(mapA.get("produitRef"));
			// Entrepot
			elementRouleauFini.setEntrepotDesignation(mapA.get("entrepot"));
			// Choix
			elementRouleauFini.setChoixDesignation(mapA.get("choix"));

		}

		for (RouleauFiniValue element : resultatRecherche.getList()) {

			// reference

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			// of

			if (element.getReferenceMise() != null) {
				sheet3.addCell(new Label(3, i, element.getReferenceMise() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			// produit reference

			if (element.getProduitReference() != null) {

				sheet3.addCell(new Label(4, i, element.getProduitReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// produit designation

			if (element.getProduitIdDesignation() != null) {

				sheet3.addCell(new Label(5, i, element.getProduitIdDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Intro

			if (element.getDateIntroduction() != null) {

				sheet3.addCell(new Label(6, i, dateTimeFormat.format(element.getDateIntroduction().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrage() != null) {
				sheet3.addCell(new Label(7, i, element.getMetrage() + "", ExcelUtils.boldRed));

				sommeQuantite += element.getMetrage().longValue();

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// responsable

			if (element.getResponsable() != null) {

				sheet3.addCell(new Label(8, i, element.getResponsable() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;

		sheet3.addCell(new Label(7, i, sommeQuantite + "", ExcelUtils.boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"Number of lines : " + resultatRecherche.getList().size(), ExcelUtils.boldRed5));
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/production-user-price", method = RequestMethod.GET)
	public void generateProductionUserPriceReportExel(

			@RequestParam(value = "numMise", required = false) String numMise,
			@RequestParam(value = "idProduitReference", required = false) String idProduitReference,
			@RequestParam(value = "produitId", required = false) String produitId,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "dateProductionDe", required = false) String dateProductionDe,
			@RequestParam(value = "dateProductionA", required = false) String dateProductionA,
			@RequestParam(value = "typeOf", required = false) String typeOf,

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("suivi-production-user-price" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("suivi-production-user-price", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 22);// Reference

		sheet3.setColumnView(3, 30); // Designation

		sheet3.setColumnView(4, 20);// num of

		sheet3.setColumnView(5, 20); // date intro

		sheet3.setColumnView(6, 20); // date liv

		sheet3.setColumnView(7, 15); // qte demande

		sheet3.setColumnView(8, 15); // quantite produit

		sheet3.setColumnView(9, 15); // quantite restante

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "Production O.F By User ", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Search Criteria", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "On " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

		RechercheMulticritereRouleauFiniStandardValue request = new RechercheMulticritereRouleauFiniStandardValue();

		if (isNotEmty(username))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Username :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, username, ExcelUtils.boldRed3));

			request.setUsername(username);
		}

		if (isNotEmty(typeOf))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type OF :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, typeOf, ExcelUtils.boldRed3));

			request.setTypeOf(typeOf);
		}

		if (isNotEmty(produitId))

		{
			ProduitValue produit = produitService.rechercheProduitById(Long.parseLong(produitId));
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Designation :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produit.getDesignation(), ExcelUtils.boldRed3));

			request.setProduitId(Long.parseLong(produitId));
		}

		if (isNotEmty(idProduitReference))

		{
			ProduitValue produit = produitService.rechercheProduitById(Long.parseLong(idProduitReference));

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Reference :", ExcelUtils.boldRed3));
			sheet3.addCell(
					new Label(numColCritRech + 1, numLigneCritRech, produit.getReference(), ExcelUtils.boldRed3));

			request.setProduitId(Long.parseLong(idProduitReference));

		}

		if (isNotEmty(numMise))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "O.F :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, numMise, ExcelUtils.boldRed3));

			request.setNumMise(numMise);
		}

		if (isNotEmty(dateProductionDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			dateProductionDe = dateProductionDe.replace('T', ' ');

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prod. Date From :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.format(stringDateTimeToCalendar(dateProductionDe).getTime()), ExcelUtils.boldRed3));

			request.setDateProductionDe(stringDateTimeToCalendar(dateProductionDe));

		}
		if (isNotEmty(dateProductionA)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			dateProductionA = dateProductionA.replace('T', ' ');

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Prod. Date To :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.format(stringDateTimeToCalendar(dateProductionA).getTime()), ExcelUtils.boldRed3));

			request.setDateProductionA(stringDateTimeToCalendar(dateProductionA));

		}

		ResultatRechecheRouleauFiniValue resultatRecherche = rouleauFiniService.rechercherMultiCritereStandard(request);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "O.F", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Article", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Article Desig.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Prod. Date", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "Prod. Qty", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Price", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(9, i - 1, "User", ExcelUtils.arial_bold_s10_white_AUTO));

		Long sommeQuantite = 0l;
		Long sommeQuantiteProduced = 0l;
		Long sommeQuantiteRestante = 0l;

		for (RouleauFiniValue elementRouleauFini : resultatRecherche.getList()) {

			// Client, Produit
			Map<String, String> mapA = gestionnaireLogistiqueCacheService.rechercherDesignationParId(
					elementRouleauFini.getPartieInteresseeId(), elementRouleauFini.getProduitId(),
					elementRouleauFini.getEntrepot(), elementRouleauFini.getChoix());

			// Client
			elementRouleauFini.setPartieInteresseeIdDesignation(mapA.get("client"));
			// Produit (Tissu): designation, reference
			elementRouleauFini.setProduitIdDesignation(mapA.get("produit"));
			elementRouleauFini.setProduitReference(mapA.get("produitRef"));
			// Entrepot
			elementRouleauFini.setEntrepotDesignation(mapA.get("entrepot"));
			// Choix
			elementRouleauFini.setChoixDesignation(mapA.get("choix"));

		}

		Double prixTotalte = 0d;

		for (RouleauFiniValue element : resultatRecherche.getList()) {

			ProduitValue produit = produitService.rechercheProduitById(element.getProduitId());

			// reference

			if (element.getReference() != null) {
				sheet3.addCell(new Label(2, i, element.getReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			// of

			if (element.getReferenceMise() != null) {
				sheet3.addCell(new Label(3, i, element.getReferenceMise() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			// produit reference

			if (element.getProduitReference() != null) {

				sheet3.addCell(new Label(4, i, element.getProduitReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			// produit designation

			if (element.getProduitIdDesignation() != null) {

				sheet3.addCell(new Label(5, i, element.getProduitIdDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			// Intro

			if (element.getDateIntroduction() != null) {

				sheet3.addCell(new Label(6, i, dateTimeFormat.format(element.getDateIntroduction().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getMetrage() != null) {
				sheet3.addCell(new Label(7, i, element.getMetrage() + "", ExcelUtils.boldRed));

				sommeQuantite += element.getMetrage().longValue();

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// price
			if (element.getMetrage() != null && produit != null && produit.getPrixUnitaire() != null) {

				Double prixByQty = (produit.getPrixUnitaire() / 1000d) * element.getMetrage();

				sheet3.addCell(new Label(8, i, convertisseur(prixByQty, 3) + "", ExcelUtils.boldRed));

				prixTotalte += prixByQty;

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// responsable

			if (element.getResponsable() != null) {

				sheet3.addCell(new Label(9, i, element.getResponsable() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;

		sheet3.addCell(new Label(7, i, sommeQuantite + "", ExcelUtils.boldRed3));
		sheet3.addCell(new Label(8, i, convertisseur(prixTotalte, 3) + "", ExcelUtils.boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"Number of lines : " + resultatRecherche.getList().size(), ExcelUtils.boldRed5));
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/facture", method = RequestMethod.GET)
	public void generateFactureReportExel(

			@RequestParam(value = "id", required = false) Long id,

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("facture" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Facture", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 22);// Reference

		sheet3.setColumnView(3, 30); // Designation

		sheet3.setColumnView(4, 20);// num of

		sheet3.setColumnView(5, 20); // date intro

		sheet3.setColumnView(6, 20); // date liv

		sheet3.setColumnView(7, 15); // qte demande

		sheet3.setColumnView(8, 15); // quantite produit

		sheet3.setColumnView(9, 15); // quantite restante

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService.getBonLivraisonReportValue(id, "oui");

		sheet3.addCell(new Label(2, 7, "Facture " + bonLivraisonReport.getReference(), ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 8, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		// sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Search Criteria",
		// ExcelUtils.boldRed5));
		// sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "On " +
		// dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));
		numLigneCritRech++;

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Thermoplastics Tunisia", ExcelUtils.boldRed3));
		sheet3.addCell(new Label(numColCritRech + 4, numLigneCritRech, "Thermoplastics Malta", ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 4, numLigneCritRech, numColCritRech + 6, numLigneCritRech);

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Sarl Zone", ExcelUtils.boldRed3));
		sheet3.addCell(new Label(numColCritRech + 4, numLigneCritRech, "A16A, Marsa Industrial Estate,  Marsa MRS 3000",
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 4, numLigneCritRech, numColCritRech + 6, numLigneCritRech);

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Industrielle AFI 1100", ExcelUtils.boldRed3));
		sheet3.addCell(new Label(numColCritRech + 4, numLigneCritRech, "Phone: (356) 21251111 ", ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 4, numLigneCritRech, numColCritRech + 6, numLigneCritRech);

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Zaghouan", ExcelUtils.boldRed3));
		sheet3.addCell(new Label(numColCritRech + 4, numLigneCritRech, "Fax: (356) 21246598", ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 4, numLigneCritRech, numColCritRech + 6, numLigneCritRech);

		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Phone: 0021621545920  0021672680873",
				ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech, numLigneCritRech, numColCritRech + 1, numLigneCritRech);
		sheet3.addCell(
				new Label(numColCritRech + 4, numLigneCritRech, "E-mail:   thermo@maltanet.net", ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 4, numLigneCritRech, numColCritRech + 6, numLigneCritRech);

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech + 4, numLigneCritRech, "Web: www.thermo.com.mt", ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 4, numLigneCritRech, numColCritRech + 6, numLigneCritRech);

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech + 4, numLigneCritRech, "Vat no: 1266-6102", ExcelUtils.boldRed3));
		sheet3.mergeCells(numColCritRech + 4, numLigneCritRech, numColCritRech + 6, numLigneCritRech);

		if (isNotEmty(bonLivraisonReport.getClient()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, bonLivraisonReport.getClient(),
					ExcelUtils.boldRed3));

		}

		if (isNotEmty(bonLivraisonReport.getDateBl()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					dateFormat.format(bonLivraisonReport.getDateBl().getTime()), ExcelUtils.boldRed3));

		}

		numLigneCritRech++;
		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
				"we confirm that the above mentioned articles are for further industrial production at playmobil Malta LTD	",
				ExcelUtils.arial_bold_s9_white_black));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 4, numLigneCritRech);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "QUANTITY", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "NUMBER OF PACKAGES", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "HS CODE", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "ARTICLE NUMBER", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "DESCRIPTION", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(7, i - 1, "AMOUNT  €", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "WEIGHT (Kg)", ExcelUtils.arial_bold_s10_white_AUTO));

		Double sommeQuantite = 0d;
		Long sommeColis = 0l;
		Double sommeAmount = 0d;

		Double sommePoids = 0d;

		for (BonLivraisonReportProductValue element : bonLivraisonReport.getProductList()) {

			// reference

			if (element.getQuantite() != null) {
				sommeQuantite += element.getQuantite();
				sheet3.addCell(new Label(2, i, convertisseur(element.getQuantite(), 1) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			// designation of

			if (element.getNombreColis() != null) {
				sommeColis += element.getNombreColis();
				sheet3.addCell(new Label(3, i, element.getNombreColis() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			String hsCode = "950";
			ProduitValue produit = produitService.rechercheProduitById(element.getProduitId());

			if (produit != null && produit.getMoule() != null) {
				hsCode += produit.getMoule();

				sheet3.addCell(new Label(4, i, hsCode + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (element.getProduitCode() != null) {
				sheet3.addCell(new Label(5, i, element.getProduitCode() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(6, i, element.getProduitDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			if (element.getMontantHT() != null) {
				Double amount = (element.getMontantHT() * 70 / 100) / 1000;

				sommeAmount += amount;
				sheet3.addCell(new Label(7, i, convertisseur(amount, 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			if (element.getQuantite() != null && produit != null && produit.getNetWeight() != null
					&& produit.getCavity() != null) {
				Double poids = (element.getQuantite() * produit.getNetWeight()) / (produit.getCavity() * 1000);

				sommePoids += poids;

				sheet3.addCell(new Label(8, i, convertisseur(poids, 3) + "", ExcelUtils.boldRed));

			} else if (produit != null && produit.getArticleProduits() != null
					&& produit.getArticleProduits().size() > 0) {

				String refProdSemiFin = produit.getArticleProduits().iterator().next().getProduitSemiFini();

				if (refProdSemiFin != null) {
					ProduitValue produitSemiFin = produitService.rechercheProduitParReference(refProdSemiFin);

					if (produitSemiFin != null && element.getQuantite() != null && produitSemiFin.getNetWeight() != null
							&& produitSemiFin.getCavity() != null) {
						Double poids = (element.getQuantite() * produitSemiFin.getNetWeight())
								/ (produitSemiFin.getCavity() * 1000);

						sommePoids += poids;

						sheet3.addCell(new Label(8, i, convertisseur(poids, 3) + "", ExcelUtils.boldRed));

					} else {
						sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
					}

				}

				else {
					sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
				}

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;

		sheet3.addCell(new Label(1, i, "TOTAL", ExcelUtils.boldRed));

		sheet3.addCell(new Label(2, i, sommeQuantite + "", ExcelUtils.boldRed));

		sheet3.addCell(new Label(3, i, sommeColis + "", ExcelUtils.boldRed));

		sheet3.addCell(new Label(7, i, convertisseur(sommeAmount, 3) + "", ExcelUtils.boldRed));

		sheet3.addCell(new Label(8, i, convertisseur(sommePoids, 3) + "", ExcelUtils.boldRed));

		numLigneCritRech++;
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
				sommeColis + " Colis Comportant  des jouets en plastique pour enfants",
				ExcelUtils.arial_bold_s9_white_black));
		sheet3.mergeCells(numColCritRech + 1, numLigneCritRech, numColCritRech + 4, numLigneCritRech);

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1,
		 * numLigneBasDuPage); sheet3.addCell(new Label(numColBasDuPage,
		 * numLigneBasDuPage, "Number of lines : " +
		 * resultatRecherche.getListeElementRechecheMiseValeur().size(),
		 * ExcelUtils.boldRed5));
		 */
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/facture-material", method = RequestMethod.GET)
	public void generateFactureMaterialReportExel(

			@RequestParam(value = "id", required = false) Long id,

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("facture-material" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Material", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 22);// Reference

		sheet3.setColumnView(3, 20); // Designation

		sheet3.setColumnView(4, 30);// num of

		sheet3.setColumnView(5, 10); // date intro

		sheet3.setColumnView(6, 10); // date liv

		sheet3.setColumnView(7, 15); // qte demande

		sheet3.setColumnView(8, 15); // quantite produit

		sheet3.setColumnView(9, 15); // quantite restante
		
		
		sheet3.setColumnView(10, 15); // quantite restante
		sheet3.setColumnView(11, 20); // quantite restante
		sheet3.setColumnView(12, 15); // quantite restante
		
		sheet3.setColumnView(13, 15); // quantite restante
		sheet3.setColumnView(14, 15); // quantite restante
		sheet3.setColumnView(15, 15); // quantite restante
		sheet3.setColumnView(16, 15); // quantite restante

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService.getBonLivraisonReportValue(id, "oui");

		sheet3.addCell(new Label(2, 7, "Material " + bonLivraisonReport.getReference(), ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 8, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		// sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Search Criteria",
		// ExcelUtils.boldRed5));
		// sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "On " +
		// dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));
		numLigneCritRech++;

		if (isNotEmty(bonLivraisonReport.getClient()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, bonLivraisonReport.getClient(),
					ExcelUtils.boldRed3));

		}

		if (isNotEmty(bonLivraisonReport.getDateBl()))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					dateFormat.format(bonLivraisonReport.getDateBl().getTime()), ExcelUtils.boldRed3));

		}

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "QUANTITY", ExcelUtils.arial_bold_s10_white_AUTO)); // B
		sheet3.addCell(new Label(3, i - 1, "ARTICLE NUMBER", ExcelUtils.arial_bold_s10_white_AUTO)); // C
		sheet3.addCell(new Label(4, i - 1, "DESCRIPTION", ExcelUtils.arial_bold_s10_white_AUTO)); // D
		sheet3.addCell(new Label(5, i - 1, "Net Weight", ExcelUtils.arial_bold_s10_white_AUTO)); // E
		sheet3.addCell(new Label(6, i - 1, "Cavity", ExcelUtils.arial_bold_s10_white_AUTO)); // F
		sheet3.addCell(new Label(7, i - 1, "Net Weight For Each Part", ExcelUtils.arial_bold_s10_white_AUTO)); // G = E/F
		sheet3.addCell(new Label(8, i - 1, "FM Material", ExcelUtils.arial_bold_s10_white_AUTO)); // H
		sheet3.addCell(new Label(9, i - 1, "Master Batch/1000", ExcelUtils.arial_bold_s10_white_AUTO)); // I
		sheet3.addCell(new Label(10, i - 1, "Weight FM", ExcelUtils.arial_bold_s10_white_AUTO)); // J
		sheet3.addCell(new Label(11, i - 1, "Weight FM /Kg + %15 westage", ExcelUtils.arial_bold_s10_white_AUTO)); // K
		sheet3.addCell(new Label(12, i - 1, "Value", ExcelUtils.arial_bold_s10_white_AUTO)); // L
		sheet3.addCell(new Label(13, i - 1, "Prod.€", ExcelUtils.arial_bold_s10_white_AUTO)); // M
		sheet3.addCell(new Label(14, i - 1, "AMOUNT  €", ExcelUtils.arial_bold_s10_white_AUTO)); // N
		sheet3.addCell(new Label(15, i - 1, "Totatl part weight (Kg)", ExcelUtils.arial_bold_s10_white_AUTO)); // O
		sheet3.addCell(new Label(16, i - 1, "UNIT PRICE", ExcelUtils.arial_bold_s10_white_AUTO)); // P
		sheet3.addCell(new Label(17, i - 1, "FM UNIT PRICE", ExcelUtils.arial_bold_s10_white_AUTO)); // Q

		Double sommeQuantite = 0d;
		Long sommeColis = 0l;
		Double sommeAmount = 0d;

		Double sommePoids = 0d;

		// Double sommeNetWeight = 0d;

		for (BonLivraisonReportProductValue element : bonLivraisonReport.getProductList()) {

			ProduitValue produit = produitService.rechercheProduitById(element.getProduitId());

			//B- QTE

			if (element.getQuantite() != null) {
				sommeQuantite += element.getQuantite();
				sheet3.addCell(new Label(2, i, convertisseur(element.getQuantite(), 1) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}

			// ARTICLE NUMBER

			if (element.getProduitCode() != null) {
				sheet3.addCell(new Label(3, i, element.getProduitCode() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			// DESCRIPTION

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(4, i, element.getProduitDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			//E- NET WEIHT

			if (produit != null && produit.getNetWeight() != null) {

				sheet3.addCell(new Label(5, i, convertisseur(produit.getNetWeight(), 3) + "", ExcelUtils.boldRed));

			}

			else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			//F- CAVITY

			if (produit != null && produit.getCavity() != null) {

				sheet3.addCell(new Label(6, i, produit.getCavity() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			//G- NET_WEIGHT FOR EACH PART = NET-WEIGHT/CAVITY

			if (produit != null && produit.getNetWeight() != null && produit.getCavity() != null) {

				Double netWeihtForEachPart = produit.getNetWeight() / produit.getCavity().doubleValue();

				sheet3.addCell(new Label(7, i, convertisseur(netWeihtForEachPart, 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			//H- FM_MATERIAL 
			
			
			String fmProduits = getFmMaterials(produit);
			
			if (fmProduits != null) {


				sheet3.addCell(new Label(8, i, fmProduits + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// I Master Batch/1000 == (qte Colorant / 1000)
			
			Double qteColorant = getQteColorant(produit);
			
			if (qteColorant != null ) {

				Double masterBatch = qteColorant / 1000;
				


				sheet3.addCell(new Label(9, i, convertisseur(masterBatch, 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}
			
			
			//J- wight FM = (G-(I*G))*B
			
			

			if (produit != null && produit.getNetWeight() != null && produit.getCavity() != null 
					&& element.getQuantite() != null && qteColorant != null) {
                      //G
				Double netWeihtForEachPart = produit.getNetWeight() / produit.getCavity().doubleValue();
				
				      //I
				Double masterBatch = qteColorant / 1000;
				
				
				Double jWeightFM = (netWeihtForEachPart - (masterBatch*netWeihtForEachPart))*element.getQuantite();

				sheet3.addCell(new Label(10, i, convertisseur(jWeightFM, 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}
			
			
			//K- Weight FM /Kg + %15 westage
			
			
			if (produit != null && produit.getNetWeight() != null && produit.getCavity() != null 
					&& element.getQuantite() != null && qteColorant != null) {
                //G
				Double netWeihtForEachPart = produit.getNetWeight() / produit.getCavity().doubleValue();
				
				//I
				Double masterBatch = qteColorant / 1000;
				
				
				Double jWeightFM = (netWeihtForEachPart - (masterBatch*netWeihtForEachPart))*element.getQuantite();
				

				Double jWeightFMplus15Pourcent = jWeightFM*1.15;

				sheet3.addCell(new Label(11, i, convertisseur(jWeightFMplus15Pourcent, 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
			}
			
			
			
			//L- Value1   =  (K/1000) * Q   avec Q = prix materiel FM
			
			
			
			//M- Prod = N-L  ;
			
			
			
			//N (Amount)
			
			
			if (element.getMontantHT() != null) {
				Double amount = (element.getMontantHT() * 70 / 100) / 1000;

				sommeAmount += amount;
				sheet3.addCell(new Label(14, i, convertisseur(amount, 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(14, i, "", ExcelUtils.boldRed));
			}
			
			
			
			//O = G*B   
			
			
			if (produit != null && element.getQuantite() != null && produit.getNetWeight() != null
					&& produit.getCavity() != null) {
				Double poids = (element.getQuantite() * produit.getNetWeight())/ (produit.getCavity() * 1000);
						

				sommePoids += poids;

				sheet3.addCell(new Label(15, i, convertisseur(poids, 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(15, i, "", ExcelUtils.boldRed));
			}
			
			//P- Unit Price
			
			if (produit != null && produit.getPrixUnitaire() != null ) {
						

				sheet3.addCell(new Label(16, i, convertisseur(produit.getPrixUnitaire(), 3) + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(16, i, "", ExcelUtils.boldRed));
			}
			
			
			
			//Q- Unit Price Materiel
			
			




			i++;

		}

		i++;

		/*sheet3.addCell(new Label(2, i, sommeQuantite + "", ExcelUtils.boldRed3));

		sheet3.addCell(new Label(3, i, sommeColis + "", ExcelUtils.boldRed3));

		sheet3.addCell(new Label(7, i, sommeAmount + "", ExcelUtils.boldRed3));

		sheet3.addCell(new Label(8, i, convertisseur(sommePoids, 3) + "", ExcelUtils.boldRed3));
        */
		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1,
		 * numLigneBasDuPage); sheet3.addCell(new Label(numColBasDuPage,
		 * numLigneBasDuPage, "Number of lines : " +
		 * resultatRecherche.getListeElementRechecheMiseValeur().size(),
		 * ExcelUtils.boldRed5));
		 */
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}

	private Double getQteColorant(ProduitValue produit) {
		
		Double qte = new Double(0);
		
		for(ArticleProduitValue vArtProd : produit.getMaterialProduits()) {
			if(vArtProd.getReferenceArticle() != null 
				&& vArtProd.getQte() != null
				&& (vArtProd.getReferenceArticle().contains("FA") || vArtProd.getReferenceArticle().contains("FZ")) )
				qte+=vArtProd.getQte();
		}
		
		return qte;
		
		
	}

	private String getFmMaterials(ProduitValue produit) {
		
		
		String material = "";
		
		if(produit != null) {
			
			//if(produit.getSousFamilleDesignation().equals(SOUS_FAMILLE_PRODUIT_INJECTED) ) {
				
				for(ArticleProduitValue vArtProd :  produit.getMaterialProduits()) {
					
				//	if(vArtProd.getReferenceArticle() != null)
					//	System.out.println(b);
					
					if(vArtProd.getReferenceArticle() != null && vArtProd.getReferenceArticle().contains("FM"))
						material.concat(vArtProd.getReferenceArticle()+", ");
					
				}
			//}

		}
		
		if(material.length() > 0) {
			material = material.substring(0, material.length()-2);
		}
			
	
		
		return material;

	}

	@RequestMapping(value = "/bonlivraisonExc", method = RequestMethod.GET)
	public void genererBonLivraisonReportExcel(
			@RequestParam(value = "refBonSortieList", required = false) String refBonSortieList,
			@RequestParam(value = "idBonLivVente", required = false) Long idBonLivVente, HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService
				.getBonLivraisonReportValue(idBonLivVente, "oui");

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("Bon_de_livraison" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Sortie", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		WritableFont font7 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn7 = new WritableCellFormat(font7);
		fn7.setWrap(true);
		fn7.setAlignment(Alignment.CENTRE);

		WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat fn6 = new WritableCellFormat(font6);
		fn6.setWrap(true);
		fn6.setAlignment(Alignment.CENTRE);

		WritableFont font8 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn8 = new WritableCellFormat(font7);
		fn8.setWrap(true);
		fn8.setAlignment(Alignment.LEFT);

		WritableFont font9 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat fn9 = new WritableCellFormat(font6);
		fn9.setWrap(true);
		fn9.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 20);
		sheet3.setColumnView(3, 10);
		sheet3.setColumnView(4, 10);
		sheet3.setColumnView(5, 30);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active
		String clientDesigntion = "THERMOPLASTICS";
		/*
		 * List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		 * 
		 * for (BaseInfoValue baseInf : baseInfoValues) { if (baseInf.isActif()) {
		 * clientDesigntion = baseInf.getDesignation(); } }
		 */
		// sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		// sheet3.addCell(new Label(3, 2, " " + clientDesigntion, fn6));
		sheet3.mergeCells(3, 2, 8, 2);

		sheet3.addCell(new Label(2, 3, "" + "", fn7));
		sheet3.mergeCells(2, 3, 9, 3);
		sheet3.addCell(new Label(2, 4, "" + "" + "", fn7));
		sheet3.mergeCells(2, 4, 9, 4);
		sheet3.addCell(new Label(2, 5, "" + "" + "" + "", fn7));
		sheet3.mergeCells(2, 5, 9, 5);

		sheet3.addCell(new Label(2, 7, "Liste de colisage", boldTitre));
		sheet3.mergeCells(2, 7, 10, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "INVOICE N° : ", boldRed3));
		sheet3.addCell(new Label(3, 10, bonLivraisonReport.getReference(), boldRed));
		sheet3.addCell(new Label(2, 11, "INVOICE DATE :", boldRed3));
		sheet3.addCell(new Label(3, 11, dateFormat2.format(bonLivraisonReport.getDateBl().getTime()) + "", boldRed));
		sheet3.addCell(new Label(2, 12, "VENDOR'S NAME", boldRed3));
		sheet3.addCell(new Label(3, 12, bonLivraisonReport.getClient(), boldRed));
		sheet3.addCell(new Label(2, 13, "SHIP TO  :", boldRed3));
		sheet3.addCell(new Label(3, 13, bonLivraisonReport.getClient(), boldRed));

		sheet3.addCell(new Label(3, 14, bonLivraisonReport.getAdresse(), boldRed));
		sheet3.mergeCells(3, 14, 4, 15);

		sheet3.addCell(new Label(5, 10, "SHIPPED BY :", boldRed3));
		sheet3.mergeCells(5, 10, 8, 10);
		sheet3.addCell(new Label(9, 10, bonLivraisonReport.getMarche(), boldRed));

		sheet3.addCell(new Label(5, 11, "WEIGHT, NET :", boldRed3));
		sheet3.mergeCells(5, 11, 8, 11);
		sheet3.addCell(new Label(9, 11, bonLivraisonReport.getModepaiement(), boldRed));

		sheet3.addCell(new Label(5, 12, "WEIGHT, GROSS :", boldRed3));
		sheet3.mergeCells(5, 12, 8, 12);
		sheet3.addCell(new Label(9, 12, bonLivraisonReport.getObservations(), boldRed));

		sheet3.addCell(new Label(5, 13, "TOTAL BOXES :", boldRed3));
		;
		sheet3.mergeCells(5, 13, 8, 13);
		// sheet3.addCell(new Label(9, 13, "", boldRed));

		sheet3.addCell(new Label(5, 14, "TOTAL PALLETS :", boldRed3));
		sheet3.mergeCells(5, 14, 8, 14);
		// sheet3.addCell(new Label(9, 14, "", boldRed));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Palette", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "OF", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Référence", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Désignation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Couleur", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "Type", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(8, i - 1, "NB/Colis", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(9, i - 1, "Qte/Colis", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(10, i - 1, "Total Qty", ExcelUtils.arial_bold_s10_white_AUTO));

		Double sommeQte = 0d;
		Double sommeQteTotale = 0d;
		Long sommeNbrColis = 0l;

		String[] listPalette = refBonSortieList.split(",");

		List<String> listPaltteArrayList = new ArrayList<>();

		for (String p : listPalette) {
			listPaltteArrayList.add(p);

			// System.out.println("##################################### "+p);
		}

		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniService.getListByBonSortieList(listPaltteArrayList);

		Collections.sort(listeBonSortieFini);

		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {

			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

			for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

				rouleau.setPalette(bonSortie.getReference());
				listeRouleauFini.add(rouleau);
			}

			Map<Map<String, Double>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<String, Double>, List<RouleauFiniValue>>();

			for (RouleauFiniValue rouleau : listeRouleauFini) {
				String produitKey = rouleau.getReferenceMise();
				Double choixKey = rouleau.getMetrage();

				Map<String, Double> map = new HashMap<String, Double>();

				map.put(produitKey, choixKey);

				if (mapRouleau.get(map) == null) {

					mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
				}

				mapRouleau.get(map).add(rouleau);

			}

			ProduitValue produitValue = null;

			Iterator it = mapRouleau.entrySet().iterator();
			List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
			while (it.hasNext()) {

				Map.Entry<Map<String, Double>, List<RouleauFiniValue>> pair = (Map.Entry<Map<String, Double>, List<RouleauFiniValue>>) it
						.next();

				DetLivraisonVenteValue element = new DetLivraisonVenteValue();

				String produitId = null;
				Double choixId = null;

				Map<String, Double> produitIdchoixIdMap = pair.getKey();
				Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

				Map.Entry<String, Double> produitIdchoixIdPair = (Map.Entry<String, Double>) produitIdchoixIdIt.next();
				produitId = produitIdchoixIdPair.getKey();
				choixId = produitIdchoixIdPair.getValue();

				element.setNumeroOF(produitId);
				element.setPrixUnitaireHT(choixId);

				/*
				 * if (choixId != null) { ChoixRouleauValue choixRouleau =
				 * choixRouleauPersistance.getChoixRouleauById(choixId); if (choixRouleau !=
				 * null) element.setChoix(choixRouleau.getDesignation()); }
				 */
				Double sommeMetrage = 0d;
				Double sommePoids = 0d;
				for (RouleauFiniValue rouleau : pair.getValue()) {
					// Somme des metrages
					if (rouleau.getMetrage() != null) {
						sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
					}
					if (rouleau.getPoids() != null) {
						sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
					}
				}

				// Qte livree = La somme des metrage des rouleaux de produit
				element.setQuantite(sommeMetrage);

				// TODO changement pour turkqua
				/*
				 * if (sommePoids > ZERO) element.setQuantite(sommePoids);
				 */

				if (element.getNumeroOF() != null) {

					// 1- Ramener l'OF
					// 2- Ramener le produit

					// 3- remplir cracteristique du produit

					MiseValue mise = miseService.rechercheMiseParReference(element.getNumeroOF());

					if (mise != null) {

						element.setTypeProduit(mise.getTypeEtiquette());

						produitValue = produitService.rechercheProduitById(mise.getProduitId());
						if (produitValue != null) {

							element.setCouleurProduit(produitValue.getReferenceInterne());
							element.setProduitDesignation(produitValue.getDesignation());
							element.setProduitReference(produitValue.getReference());
							if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
								element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
							if (produitValue.getPrixUnitaire() != null)
								element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
						}
					}

				}

				element.setNombreColis(Long.valueOf(pair.getValue().size()));

				listDetLivraisonVente.add(element);

				it.remove();
			}

			if (listDetLivraisonVente.size() > 0) {
				Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
			}

			for (DetLivraisonVenteValue element : listDetLivraisonVente) {

				if (bonSortie.getReference() != null) {
					sheet3.addCell(new Label(2, i,
							bonSortie.getReference().substring(bonSortie.getReference().indexOf('/') + 1,
									bonSortie.getReference().length()) + "",
							boldRed));

				} else {
					sheet3.addCell(new Label(2, i, "", boldRed));
				}

				// of

				if (element.getNumeroOF() != null) {
					sheet3.addCell(new Label(3, i, element.getNumeroOF() + "", boldRed));

				} else {
					sheet3.addCell(new Label(3, i, "", boldRed));
				}

				if (element.getProduitReference() != null) {
					sheet3.addCell(new Label(4, i, element.getProduitReference() + "", boldRed));

				} else {
					sheet3.addCell(new Label(4, i, "", boldRed));
				}

				if (element.getProduitDesignation() != null) {
					sheet3.addCell(new Label(5, i, element.getProduitDesignation() + "", boldRed));

				} else {
					sheet3.addCell(new Label(5, i, "", boldRed));
				}

				// couleur

				if (element.getCouleurProduit() != null) {
					sheet3.addCell(new Label(6, i, element.getCouleurProduit() + "", boldRed));

				} else {
					sheet3.addCell(new Label(6, i, "", boldRed));
				}

				// type

				if (element.getTypeProduit() != null) {
					sheet3.addCell(new Label(7, i, element.getTypeProduit() + "", boldRed));

				} else {
					sheet3.addCell(new Label(7, i, "", boldRed));
				}

				/*
				 * if (element.getNombreColis() != null) {
				 * 
				 * if (element.getNombreColis() > 0) sheet3.addCell(new Label(8, i,
				 * element.getNombreColis() + "", boldRed)); else sheet3.addCell(new Label(8, i,
				 * "", boldRed));
				 * 
				 * sommeNbrColis += element.getNombreColis();
				 * 
				 * } else { sheet3.addCell(new Label(8, i, "", boldRed)); }
				 */

				if (element.getNombreColis() != null) {
					sheet3.addCell(new Label(8, i, element.getNombreColis() + "", boldRed));
					sommeNbrColis += element.getNombreColis();

				} else {
					sheet3.addCell(new Label(8, i, "", boldRed));
				}

				if (element.getPrixUnitaireHT() != null) {
					sheet3.addCell(new Label(9, i, convertisseur(element.getPrixUnitaireHT(), 3) + "", boldRed));
					sommeQte += element.getPrixUnitaireHT();

				} else {
					sheet3.addCell(new Label(9, i, "", boldRed));
				}

				if (element.getPrixUnitaireHT() != null && element.getNombreColis() != null) {
					Double totale = element.getPrixUnitaireHT() * element.getNombreColis();
					sheet3.addCell(new Label(10, i, convertisseur(totale, 3) + "", boldRed));
					sommeQteTotale += totale;

				} else {
					sheet3.addCell(new Label(10, i, "", boldRed));
				}

				i++;

			}

			i++;

		}

		sheet3.addCell(new Label(9, 13, sommeNbrColis + "", boldRed));

		sheet3.addCell(new Label(9, 14, listeBonSortieFini.size() + "", boldRed));

		// sheet3.addCell(new Label(2, i + 1, "TOTAUX", boldRed2));
		// sheet3.mergeCells(2, i + 1, 4, i + 1);
		sheet3.addCell(new Label(8, i, sommeNbrColis + "", boldRed3));
		sheet3.addCell(new Label(9, i, sommeQte + "", boldRed3));
		sheet3.addCell(new Label(10, i, sommeQteTotale + "", boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage,
		 * "La somme des quantités des commandes: ", boldRed5)); sheet3.addCell(new
		 * Label(numColBasDuPage+2, numLigneBasDuPage, sommeQte.toString(), boldRed3));
		 * 
		 * numLigneBasDuPage++; sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage, "Somme des minutes: ", boldRed5));
		 * sheet3.addCell(new Label(numColBasDuPage+2, numLigneBasDuPage,
		 * convertisseur(sommeTemps, 3).toString(), boldRed3));
		 * 
		 */

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

	@RequestMapping(value = "/bonlivraisonExc-without-of", method = RequestMethod.GET)
	public void genererBonLivraisonWithoutOFReportExcel(
			@RequestParam(value = "refBonSortieList", required = false) String refBonSortieList,
			@RequestParam(value = "idBonLivVente", required = false) Long idBonLivVente, HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService
				.getBonLivraisonReportValue(idBonLivVente, "oui");

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("Bon_de_livraison" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Sortie", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		WritableFont font7 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn7 = new WritableCellFormat(font7);
		fn7.setWrap(true);
		fn7.setAlignment(Alignment.CENTRE);

		WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat fn6 = new WritableCellFormat(font6);
		fn6.setWrap(true);
		fn6.setAlignment(Alignment.CENTRE);

		WritableFont font8 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn8 = new WritableCellFormat(font7);
		fn8.setWrap(true);
		fn8.setAlignment(Alignment.LEFT);

		WritableFont font9 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat fn9 = new WritableCellFormat(font6);
		fn9.setWrap(true);
		fn9.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 20);
		// sheet3.setColumnView(3, 10);
		sheet3.setColumnView(3, 10);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active
		String clientDesigntion = "THERMOPLASTICS";
		/*
		 * List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		 * 
		 * for (BaseInfoValue baseInf : baseInfoValues) { if (baseInf.isActif()) {
		 * clientDesigntion = baseInf.getDesignation(); } }
		 */
		// sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		// sheet3.addCell(new Label(3, 2, " " + clientDesigntion, fn6));
		sheet3.mergeCells(3, 2, 8, 2);

		sheet3.addCell(new Label(2, 3, "" + "", fn7));
		sheet3.mergeCells(2, 3, 9, 3);
		sheet3.addCell(new Label(2, 4, "" + "" + "", fn7));
		sheet3.mergeCells(2, 4, 9, 4);
		sheet3.addCell(new Label(2, 5, "" + "" + "" + "", fn7));
		sheet3.mergeCells(2, 5, 9, 5);

		sheet3.addCell(new Label(2, 7, "Liste de colisage", boldTitre));
		sheet3.mergeCells(2, 7, 10, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "INVOICE N° : ", boldRed3));
		sheet3.addCell(new Label(3, 10, bonLivraisonReport.getReference(), boldRed));
		sheet3.addCell(new Label(2, 11, "INVOICE DATE :", boldRed3));
		sheet3.addCell(new Label(3, 11, dateFormat2.format(bonLivraisonReport.getDateBl().getTime()) + "", boldRed));
		sheet3.addCell(new Label(2, 12, "VENDOR'S NAME", boldRed3));
		sheet3.addCell(new Label(3, 12, bonLivraisonReport.getClient(), boldRed));
		sheet3.addCell(new Label(2, 13, "SHIP TO  :", boldRed3));
		sheet3.addCell(new Label(3, 13, bonLivraisonReport.getClient(), boldRed));

		sheet3.addCell(new Label(3, 14, bonLivraisonReport.getAdresse(), boldRed));
		sheet3.mergeCells(3, 14, 4, 15);

		sheet3.addCell(new Label(5, 10, "SHIPPED BY :", boldRed3));
		sheet3.mergeCells(5, 10, 8, 10);
		sheet3.addCell(new Label(9, 10, bonLivraisonReport.getMarche(), boldRed));

		sheet3.addCell(new Label(5, 11, "WEIGHT, NET :", boldRed3));
		sheet3.mergeCells(5, 11, 8, 11);
		sheet3.addCell(new Label(9, 11, bonLivraisonReport.getModepaiement(), boldRed));

		sheet3.addCell(new Label(5, 12, "WEIGHT, GROSS :", boldRed3));
		sheet3.mergeCells(5, 12, 8, 12);
		sheet3.addCell(new Label(9, 12, bonLivraisonReport.getObservations(), boldRed));

		sheet3.addCell(new Label(5, 13, "TOTAL BOXES :", boldRed3));
		;
		sheet3.mergeCells(5, 13, 8, 13);
		// sheet3.addCell(new Label(9, 13, "", boldRed));

		sheet3.addCell(new Label(5, 14, "TOTAL PALLETS :", boldRed3));
		sheet3.mergeCells(5, 14, 8, 14);
		// sheet3.addCell(new Label(9, 14, "", boldRed));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Palette", ExcelUtils.arial_bold_s10_white_AUTO));
		// sheet3.addCell(new Label(3, i - 1, "OF",
		// ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Désignation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Couleur", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Type", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(7, i - 1, "NB/Colis", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Qte/Colis", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(9, i - 1, "Total Qty", ExcelUtils.arial_bold_s10_white_AUTO));

		Double sommeQte = 0d;
		Double sommeQteTotale = 0d;
		Long sommeNbrColis = 0l;

		String[] listPalette = refBonSortieList.split(",");

		List<String> listPaltteArrayList = new ArrayList<>();

		for (String p : listPalette) {
			listPaltteArrayList.add(p);

			// System.out.println("##################################### "+p);
		}

		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniService.getListByBonSortieList(listPaltteArrayList);

		Collections.sort(listeBonSortieFini);

		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {

			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

			for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

				rouleau.setPalette(bonSortie.getReference());
				listeRouleauFini.add(rouleau);
			}

			Map<Map<String, Double>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<String, Double>, List<RouleauFiniValue>>();

			for (RouleauFiniValue rouleau : listeRouleauFini) {
				String produitKey = rouleau.getReferenceMise();
				Double choixKey = rouleau.getMetrage();

				Map<String, Double> map = new HashMap<String, Double>();

				map.put(produitKey, choixKey);

				if (mapRouleau.get(map) == null) {

					mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
				}

				mapRouleau.get(map).add(rouleau);

			}

			ProduitValue produitValue = null;

			Iterator it = mapRouleau.entrySet().iterator();
			List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
			while (it.hasNext()) {

				Map.Entry<Map<String, Double>, List<RouleauFiniValue>> pair = (Map.Entry<Map<String, Double>, List<RouleauFiniValue>>) it
						.next();

				DetLivraisonVenteValue element = new DetLivraisonVenteValue();

				String produitId = null;
				Double choixId = null;

				Map<String, Double> produitIdchoixIdMap = pair.getKey();
				Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

				Map.Entry<String, Double> produitIdchoixIdPair = (Map.Entry<String, Double>) produitIdchoixIdIt.next();
				produitId = produitIdchoixIdPair.getKey();
				choixId = produitIdchoixIdPair.getValue();

				element.setNumeroOF(produitId);
				element.setPrixUnitaireHT(choixId);

				/*
				 * if (choixId != null) { ChoixRouleauValue choixRouleau =
				 * choixRouleauPersistance.getChoixRouleauById(choixId); if (choixRouleau !=
				 * null) element.setChoix(choixRouleau.getDesignation()); }
				 */
				Double sommeMetrage = 0d;
				Double sommePoids = 0d;
				for (RouleauFiniValue rouleau : pair.getValue()) {
					// Somme des metrages
					if (rouleau.getMetrage() != null) {
						sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
					}
					if (rouleau.getPoids() != null) {
						sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
					}
				}

				// Qte livree = La somme des metrage des rouleaux de produit
				element.setQuantite(sommeMetrage);

				// TODO changement pour turkqua
				/*
				 * if (sommePoids > ZERO) element.setQuantite(sommePoids);
				 */

				if (element.getNumeroOF() != null) {

					// 1- Ramener l'OF
					// 2- Ramener le produit

					// 3- remplir cracteristique du produit

					MiseValue mise = miseService.rechercheMiseParReference(element.getNumeroOF());

					if (mise != null) {

						element.setTypeProduit(mise.getTypeEtiquette());

						produitValue = produitService.rechercheProduitById(mise.getProduitId());
						if (produitValue != null) {

							element.setCouleurProduit(produitValue.getReferenceInterne());
							element.setProduitDesignation(produitValue.getDesignation());
							element.setProduitReference(produitValue.getReference());
							if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
								element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
							if (produitValue.getPrixUnitaire() != null)
								element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
						}
					}

				}

				element.setNombreColis(Long.valueOf(pair.getValue().size()));

				listDetLivraisonVente.add(element);

				it.remove();
			}

			if (listDetLivraisonVente.size() > 0) {
				Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
			}

			for (DetLivraisonVenteValue element : listDetLivraisonVente) {

				if (bonSortie.getReference() != null) {
					sheet3.addCell(new Label(2, i,
							bonSortie.getReference().substring(bonSortie.getReference().indexOf('/') + 1,
									bonSortie.getReference().length()) + "",
							boldRed));

				} else {
					sheet3.addCell(new Label(2, i, "", boldRed));
				}

				// of

				/*
				 * if (element.getNumeroOF() != null) { sheet3.addCell(new Label(3, i,
				 * element.getNumeroOF() + "", boldRed));
				 * 
				 * } else { sheet3.addCell(new Label(3, i, "", boldRed)); }
				 */

				if (element.getProduitReference() != null) {
					sheet3.addCell(new Label(3, i, element.getProduitReference() + "", boldRed));

				} else {
					sheet3.addCell(new Label(3, i, "", boldRed));
				}

				if (element.getProduitDesignation() != null) {
					sheet3.addCell(new Label(4, i, element.getProduitDesignation() + "", boldRed));

				} else {
					sheet3.addCell(new Label(4, i, "", boldRed));
				}

				// couleur

				if (element.getCouleurProduit() != null) {
					sheet3.addCell(new Label(5, i, element.getCouleurProduit() + "", boldRed));

				} else {
					sheet3.addCell(new Label(5, i, "", boldRed));
				}

				// type

				if (element.getTypeProduit() != null) {
					sheet3.addCell(new Label(6, i, element.getTypeProduit() + "", boldRed));

				} else {
					sheet3.addCell(new Label(6, i, "", boldRed));
				}

				/*
				 * if (element.getNombreColis() != null) {
				 * 
				 * if (element.getNombreColis() > 0) sheet3.addCell(new Label(8, i,
				 * element.getNombreColis() + "", boldRed)); else sheet3.addCell(new Label(8, i,
				 * "", boldRed));
				 * 
				 * sommeNbrColis += element.getNombreColis();
				 * 
				 * } else { sheet3.addCell(new Label(8, i, "", boldRed)); }
				 */

				if (element.getNombreColis() != null) {
					sheet3.addCell(new Label(7, i, element.getNombreColis() + "", boldRed));
					sommeNbrColis += element.getNombreColis();

				} else {
					sheet3.addCell(new Label(7, i, "", boldRed));
				}

				if (element.getPrixUnitaireHT() != null) {
					sheet3.addCell(new Label(8, i, element.getPrixUnitaireHT() + "", boldRed));
					sommeQte += element.getPrixUnitaireHT();

				} else {
					sheet3.addCell(new Label(8, i, "", boldRed));
				}

				if (element.getPrixUnitaireHT() != null && element.getNombreColis() != null) {
					Double totale = element.getPrixUnitaireHT() * element.getNombreColis();
					sheet3.addCell(new Label(9, i, convertisseur(totale, 3) + "", boldRed));
					sommeQteTotale += totale;

				} else {
					sheet3.addCell(new Label(9, i, "", boldRed));
				}

				i++;

			}

			i++;

		}

		sheet3.addCell(new Label(9, 13, sommeNbrColis + "", boldRed));

		sheet3.addCell(new Label(9, 14, listeBonSortieFini.size() + "", boldRed));

		// sheet3.addCell(new Label(2, i + 1, "TOTAUX", boldRed2));
		// sheet3.mergeCells(2, i + 1, 4, i + 1);
		sheet3.addCell(new Label(8, i, sommeNbrColis + "", boldRed3));
		sheet3.addCell(new Label(9, i, sommeQte + "", boldRed3));
		sheet3.addCell(new Label(10, i, sommeQteTotale + "", boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage,
		 * "La somme des quantités des commandes: ", boldRed5)); sheet3.addCell(new
		 * Label(numColBasDuPage+2, numLigneBasDuPage, sommeQte.toString(), boldRed3));
		 * 
		 * numLigneBasDuPage++; sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage, "Somme des minutes: ", boldRed5));
		 * sheet3.addCell(new Label(numColBasDuPage+2, numLigneBasDuPage,
		 * convertisseur(sommeTemps, 3).toString(), boldRed3));
		 * 
		 */

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
	
	
	
	
	
	@RequestMapping(value = "/bonlivraisonExc-by-article", method = RequestMethod.GET)
	public void genererBonLivraisonByArticleReportExcel(
			@RequestParam(value = "refBonSortieList", required = false) String refBonSortieList,
			@RequestParam(value = "idBonLivVente", required = false) Long idBonLivVente, HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService
				.getBonLivraisonReportValue(idBonLivVente, "oui");

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("Bon_de_livraison" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Sortie", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		WritableFont font7 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn7 = new WritableCellFormat(font7);
		fn7.setWrap(true);
		fn7.setAlignment(Alignment.CENTRE);

		WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat fn6 = new WritableCellFormat(font6);
		fn6.setWrap(true);
		fn6.setAlignment(Alignment.CENTRE);

		WritableFont font8 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn8 = new WritableCellFormat(font7);
		fn8.setWrap(true);
		fn8.setAlignment(Alignment.LEFT);

		WritableFont font9 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat fn9 = new WritableCellFormat(font6);
		fn9.setWrap(true);
		fn9.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 20);
		// sheet3.setColumnView(3, 10);
		sheet3.setColumnView(3, 10);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);
		sheet3.setColumnView(9, 10);
		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active
		String clientDesigntion = "THERMOPLASTICS";
		/*
		 * List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		 * 
		 * for (BaseInfoValue baseInf : baseInfoValues) { if (baseInf.isActif()) {
		 * clientDesigntion = baseInf.getDesignation(); } }
		 */
		// sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		// sheet3.addCell(new Label(3, 2, " " + clientDesigntion, fn6));
		sheet3.mergeCells(3, 2, 8, 2);

		sheet3.addCell(new Label(2, 3, "" + "", fn7));
		sheet3.mergeCells(2, 3, 9, 3);
		sheet3.addCell(new Label(2, 4, "" + "" + "", fn7));
		sheet3.mergeCells(2, 4, 9, 4);
		sheet3.addCell(new Label(2, 5, "" + "" + "" + "", fn7));
		sheet3.mergeCells(2, 5, 9, 5);

		sheet3.addCell(new Label(2, 7, "Liste de colisage", boldTitre));
		sheet3.mergeCells(2, 7, 10, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "INVOICE N° : ", boldRed3));
		sheet3.addCell(new Label(3, 10, bonLivraisonReport.getReference(), boldRed));
		sheet3.addCell(new Label(2, 11, "INVOICE DATE :", boldRed3));
		sheet3.addCell(new Label(3, 11, dateFormat2.format(bonLivraisonReport.getDateBl().getTime()) + "", boldRed));
		sheet3.addCell(new Label(2, 12, "VENDOR'S NAME", boldRed3));
		sheet3.addCell(new Label(3, 12, bonLivraisonReport.getClient(), boldRed));
		sheet3.addCell(new Label(2, 13, "SHIP TO  :", boldRed3));
		sheet3.addCell(new Label(3, 13, bonLivraisonReport.getClient(), boldRed));

		sheet3.addCell(new Label(3, 14, bonLivraisonReport.getAdresse(), boldRed));
		sheet3.mergeCells(3, 14, 4, 15);

		sheet3.addCell(new Label(5, 10, "SHIPPED BY :", boldRed3));
		sheet3.mergeCells(5, 10, 8, 10);
		sheet3.addCell(new Label(9, 10, bonLivraisonReport.getMarche(), boldRed));

		sheet3.addCell(new Label(5, 11, "WEIGHT, NET :", boldRed3));
		sheet3.mergeCells(5, 11, 8, 11);
		sheet3.addCell(new Label(9, 11, bonLivraisonReport.getModepaiement(), boldRed));

		sheet3.addCell(new Label(5, 12, "WEIGHT, GROSS :", boldRed3));
		sheet3.mergeCells(5, 12, 8, 12);
		sheet3.addCell(new Label(9, 12, bonLivraisonReport.getObservations(), boldRed));

		sheet3.addCell(new Label(5, 13, "TOTAL BOXES :", boldRed3));
		;
		sheet3.mergeCells(5, 13, 8, 13);
		// sheet3.addCell(new Label(9, 13, "", boldRed));

		sheet3.addCell(new Label(5, 14, "TOTAL PALLETS :", boldRed3));
		sheet3.mergeCells(5, 14, 8, 14);
		// sheet3.addCell(new Label(9, 14, "", boldRed));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Palette", ExcelUtils.arial_bold_s10_white_AUTO));
		// sheet3.addCell(new Label(3, i - 1, "OF",
		// ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Désignation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Couleur", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Type", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(7, i - 1, "NB/Colis", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Qte/Colis", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(9, i - 1, "Total Qty", ExcelUtils.arial_bold_s10_white_AUTO));

		Double sommeQte = 0d;
		Double sommeQteTotale = 0d;
		Long sommeNbrColis = 0l;

		String[] listPalette = refBonSortieList.split(",");

		List<String> listPaltteArrayList = new ArrayList<>();

		for (String p : listPalette) {
			listPaltteArrayList.add(p);

			// System.out.println("##################################### "+p);
		}

		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniService.getListByBonSortieList(listPaltteArrayList);

		Collections.sort(listeBonSortieFini);

		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		
		int nbrPaletteInitiale = 0;
		int nbrPalette = 0;
		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {
			
			nbrPaletteInitiale ++;

			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

			for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

				rouleau.setPalette(bonSortie.getReference());
				listeRouleauFini.add(rouleau);
			}

			Map<Map<Long, Double>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<Long, Double>, List<RouleauFiniValue>>();

			for (RouleauFiniValue rouleau : listeRouleauFini) {
				Long produitKey = rouleau.getProduitId();
				Double choixKey = rouleau.getMetrage();

				Map<Long, Double> map = new HashMap<Long, Double>();

				map.put(produitKey, choixKey);

				if (mapRouleau.get(map) == null) {

					mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
				}

				mapRouleau.get(map).add(rouleau);

			}

			ProduitValue produitValue = null;

			Iterator it = mapRouleau.entrySet().iterator();
			List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
			while (it.hasNext()) {

				Map.Entry<Map<Long, Double>, List<RouleauFiniValue>> pair = (Map.Entry<Map<Long, Double>, List<RouleauFiniValue>>) it
						.next();

				DetLivraisonVenteValue element = new DetLivraisonVenteValue();

				Long produitId = null;
				Double choixId = null;

				Map<Long, Double> produitIdchoixIdMap = pair.getKey();
				Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

				Map.Entry<Long, Double> produitIdchoixIdPair = (Map.Entry<Long, Double>) produitIdchoixIdIt.next();
				produitId = produitIdchoixIdPair.getKey();
				choixId = produitIdchoixIdPair.getValue();

				element.setProduitId(produitId);;
				element.setPrixUnitaireHT(choixId);

				/*
				 * if (choixId != null) { ChoixRouleauValue choixRouleau =
				 * choixRouleauPersistance.getChoixRouleauById(choixId); if (choixRouleau !=
				 * null) element.setChoix(choixRouleau.getDesignation()); }
				 */
				Double sommeMetrage = 0d;
				Double sommePoids = 0d;
				for (RouleauFiniValue rouleau : pair.getValue()) {
					// Somme des metrages
					if (rouleau.getMetrage() != null) {
						sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
					}
					if (rouleau.getPoids() != null) {
						sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
					}
				}

				// Qte livree = La somme des metrage des rouleaux de produit
				element.setQuantite(sommeMetrage);

				// TODO changement pour turkqua
				/*
				 * if (sommePoids > ZERO) element.setQuantite(sommePoids);
				 */

				if (element.getProduitId() != null) {

					// 1- Ramener l'OF
					// 2- Ramener le produit

					// 3- remplir cracteristique du produit

					/*MiseValue mise = miseService.rechercheMiseParReference(Long.parseLong(element.getNumeroOF()));

					if (mise != null) {

						element.setTypeProduit(mise.getTypeEtiquette());

						produitValue = produitService.rechercheProduitById(mise.getProduitId());
						if (produitValue != null) {

							element.setCouleurProduit(produitValue.getReferenceInterne());
							element.setProduitDesignation(produitValue.getDesignation());
							element.setProduitReference(produitValue.getReference());
							if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
								element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
							if (produitValue.getPrixUnitaire() != null)
								element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
						}
					}*/
					
					
					
					
					produitValue = produitService.rechercheProduitById(element.getProduitId());
					if (produitValue != null) {

						element.setCouleurProduit(produitValue.getReferenceInterne());
						element.setProduitDesignation(produitValue.getDesignation());
						element.setProduitReference(produitValue.getReference());
						if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
							element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
						if (produitValue.getPrixUnitaire() != null)
							element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
						
						
						element.setPalette(bonSortie.getReference());
						
					}
					
					

				}

				element.setNombreColis(Long.valueOf(pair.getValue().size()));

				listDetLivraisonVente.add(element);

				it.remove();
			}

			if (listDetLivraisonVente.size() > 0) {
				Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
			}


			
         String firstReference = "";
			
			if(listDetLivraisonVente.size() > 0)

			     firstReference = listDetLivraisonVente.get(0).getProduitReference();
			
		//	String firstReferencePalette = listDetLivraisonVente.get(0).getPalette();

			int indicefirstEnter = numLigneCritRech + 4;
			int cptPalette =0;
			
			for (DetLivraisonVenteValue element : listDetLivraisonVente) {

				if (bonSortie.getReference() != null) {
					sheet3.addCell(new Label(2, i,
							bonSortie.getReference().substring(bonSortie.getReference().indexOf('/') + 1,
									bonSortie.getReference().length()) + "",
							boldRed));

				} else {
					sheet3.addCell(new Label(2, i, "", boldRed));
				}

				// of

				/*
				 * if (element.getNumeroOF() != null) { sheet3.addCell(new Label(3, i,
				 * element.getNumeroOF() + "", boldRed));
				 * 
				 * } else { sheet3.addCell(new Label(3, i, "", boldRed)); }
				 */

				if (element.getProduitReference() != null) {
					
					
				
					
					if (element.getProduitReference().equals(firstReference) && (i > indicefirstEnter) && cptPalette>0 ) {
						
					

						sheet3.addCell(new Label(3, i, "-- -- -- -- --", boldRed));
					} else

					{
						firstReference = element.getProduitReference();
						sheet3.addCell(new Label(3, i, element.getProduitReference() + "", boldRed));
					}
					
					
					
					
					
				//	sheet3.addCell(new Label(3, i, element.getProduitReference() + "", boldRed));

				} else {
					sheet3.addCell(new Label(3, i, "", boldRed));
				}

				if (element.getProduitDesignation() != null) {
					sheet3.addCell(new Label(4, i, element.getProduitDesignation() + "", boldRed));

				} else {
					sheet3.addCell(new Label(4, i, "", boldRed));
				}

				// couleur

				if (element.getCouleurProduit() != null) {
					sheet3.addCell(new Label(5, i, element.getCouleurProduit() + "", boldRed));

				} else {
					sheet3.addCell(new Label(5, i, "", boldRed));
				}

				// type

				if (bonSortie.getType() != null) {
					sheet3.addCell(new Label(6, i, bonSortie.getType() + "", boldRed));

				} else {
					sheet3.addCell(new Label(6, i, "", boldRed));
				}

				/*
				 * if (element.getNombreColis() != null) {
				 * 
				 * if (element.getNombreColis() > 0) sheet3.addCell(new Label(8, i,
				 * element.getNombreColis() + "", boldRed)); else sheet3.addCell(new Label(8, i,
				 * "", boldRed));
				 * 
				 * sommeNbrColis += element.getNombreColis();
				 * 
				 * } else { sheet3.addCell(new Label(8, i, "", boldRed)); }
				 */

				if (element.getNombreColis() != null) {
					sheet3.addCell(new Label(7, i, element.getNombreColis() + "", boldRed));
					sommeNbrColis += element.getNombreColis();

				} else {
					sheet3.addCell(new Label(7, i, "", boldRed));
				}

				if (element.getPrixUnitaireHT() != null) {
					sheet3.addCell(new Label(8, i, convertisseur(element.getPrixUnitaireHT(), 0) + "", boldRed));
					sommeQte += element.getPrixUnitaireHT();

				} else {
					sheet3.addCell(new Label(8, i, "", boldRed));
				}

				if (element.getPrixUnitaireHT() != null && element.getNombreColis() != null) {
					Double totale = element.getPrixUnitaireHT() * element.getNombreColis();
					sheet3.addCell(new Label(9, i, convertisseur(totale, 0) + "", boldRed));
					sommeQteTotale += totale;

				} else {
					sheet3.addCell(new Label(9, i, "", boldRed));
				}

				i++;
				cptPalette++;

			}

			
			
			
			
			
		
			i++;
			
		

		}

		sheet3.addCell(new Label(9, 13, sommeNbrColis + "", boldRed));

		sheet3.addCell(new Label(9, 14, listeBonSortieFini.size() + "", boldRed));

		// sheet3.addCell(new Label(2, i + 1, "TOTAUX", boldRed2));
		// sheet3.mergeCells(2, i + 1, 4, i + 1);
		sheet3.addCell(new Label(7, i,sommeNbrColis  + "", boldRed3));
		//sheet3.addCell(new Label(8, i, sommeQte + "", boldRed3));
		sheet3.addCell(new Label(9, i, convertisseur(sommeQteTotale, 1) + "", boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage,
		 * "La somme des quantités des commandes: ", boldRed5)); sheet3.addCell(new
		 * Label(numColBasDuPage+2, numLigneBasDuPage, sommeQte.toString(), boldRed3));
		 * 
		 * numLigneBasDuPage++; sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage, "Somme des minutes: ", boldRed5));
		 * sheet3.addCell(new Label(numColBasDuPage+2, numLigneBasDuPage,
		 * convertisseur(sommeTemps, 3).toString(), boldRed3));
		 * 
		 */

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
	
	
	@RequestMapping(value = "/bonlivraisonExc-by-article-th", method = RequestMethod.GET)
	public void genererBonLivraisonByArticleThermoReportExcel(
			@RequestParam(value = "refBonSortieList", required = false) String refBonSortieList,
			@RequestParam(value = "idBonLivVente", required = false) Long idBonLivVente, HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		BonLivraisonReportValue bonLivraisonReport = gestionnaireReportGcService
				.getBonLivraisonReportValue(idBonLivVente, "oui");

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("Bon_de_livraison" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Sortie", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		WritableFont font7 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn7 = new WritableCellFormat(font7);
		fn7.setWrap(true);
		fn7.setAlignment(Alignment.CENTRE);

		WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat fn6 = new WritableCellFormat(font6);
		fn6.setWrap(true);
		fn6.setAlignment(Alignment.CENTRE);

		WritableFont font8 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn8 = new WritableCellFormat(font7);
		fn8.setWrap(true);
		fn8.setAlignment(Alignment.LEFT);

		WritableFont font9 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat fn9 = new WritableCellFormat(font6);
		fn9.setWrap(true);
		fn9.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 18);
		// sheet3.setColumnView(3, 10);
		sheet3.setColumnView(3, 14);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 13);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 15);
		sheet3.setColumnView(9, 15);
		sheet3.setColumnView(10, 15);
		sheet3.setColumnView(11, 20);
		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active
		String clientDesigntion = "THERMOPLASTICS";
		/*
		 * List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		 * 
		 * for (BaseInfoValue baseInf : baseInfoValues) { if (baseInf.isActif()) {
		 * clientDesigntion = baseInf.getDesignation(); } }
		 */
		// sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		// sheet3.addCell(new Label(3, 2, " " + clientDesigntion, fn6));
		sheet3.mergeCells(3, 2, 8, 2);

		sheet3.addCell(new Label(2, 3, "" + "", fn7));
		sheet3.mergeCells(2, 3, 9, 3);
		sheet3.addCell(new Label(2, 4, "" + "" + "", fn7));
		sheet3.mergeCells(2, 4, 9, 4);
		sheet3.addCell(new Label(2, 5, "" + "" + "" + "", fn7));
		sheet3.mergeCells(2, 5, 9, 5);

		sheet3.addCell(new Label(2, 7, "Liste de colisage", boldTitre));
		sheet3.mergeCells(2, 7, 10, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "INVOICE N° : ", boldRed3));
		sheet3.addCell(new Label(3, 10, bonLivraisonReport.getReference(), boldRed));
		sheet3.addCell(new Label(2, 11, "INVOICE DATE :", boldRed3));
		sheet3.addCell(new Label(3, 11, dateFormat2.format(bonLivraisonReport.getDateBl().getTime()) + "", boldRed));
		sheet3.addCell(new Label(2, 12, "VENDOR'S NAME", boldRed3));
		sheet3.addCell(new Label(3, 12, bonLivraisonReport.getClient(), boldRed));
		sheet3.addCell(new Label(2, 13, "SHIP TO  :", boldRed3));
		sheet3.addCell(new Label(3, 13, bonLivraisonReport.getClient(), boldRed));

		sheet3.addCell(new Label(3, 14, bonLivraisonReport.getAdresse(), boldRed));
		sheet3.mergeCells(3, 14, 4, 15);

		sheet3.addCell(new Label(5, 10, "SHIPPED BY :", boldRed3));
		sheet3.mergeCells(5, 10, 8, 10);
		sheet3.addCell(new Label(9, 10, bonLivraisonReport.getMarche(), boldRed));

		sheet3.addCell(new Label(5, 11, "WEIGHT, NET :", boldRed3));
		sheet3.mergeCells(5, 11, 8, 11);
		sheet3.addCell(new Label(9, 11, bonLivraisonReport.getModepaiement(), boldRed));

		sheet3.addCell(new Label(5, 12, "WEIGHT, GROSS :", boldRed3));
		sheet3.mergeCells(5, 12, 8, 12);
		sheet3.addCell(new Label(9, 12, bonLivraisonReport.getObservations(), boldRed));

		sheet3.addCell(new Label(5, 13, "TOTAL BOXES :", boldRed3));
		;
		sheet3.mergeCells(5, 13, 8, 13);
		// sheet3.addCell(new Label(9, 13, "", boldRed));

		sheet3.addCell(new Label(5, 14, "TOTAL PALLETS :", boldRed3));
		sheet3.mergeCells(5, 14, 8, 14);
		// sheet3.addCell(new Label(9, 14, "", boldRed));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 4;

		
		Calendar supDeliveryNoteRef = (Calendar)bonLivraisonReport.getDateBl().clone();
		supDeliveryNoteRef.add(Calendar.DATE, 7);
				
				
		String supDeliveryNoteRefFormatted = dateFormat2.format(supDeliveryNoteRef.getTime());
		
		sheet3.addCell(new Label(2, i - 1, "DELIVERY TYPE", ExcelUtils.arial_bold_s10_white_AUTO));//PRODUCTION
		sheet3.addCell(new Label(3, i - 1, "PALETTE NO.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "ITEM NO.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "PRICE REF.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "ORDER TYPE", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "SUP.FULL BINS", ExcelUtils.arial_bold_s10_white_AUTO)); //nbrColis  
		sheet3.addCell(new Label(8, i - 1, "SUP.BIN QTY", ExcelUtils.arial_bold_s10_white_AUTO)); //qteColis 
		sheet3.addCell(new Label(9, i - 1, "SUP.PART BIN QTY", ExcelUtils.arial_bold_s10_white_AUTO)); //rest 
		sheet3.addCell(new Label(10, i - 1, "PLASTIC BINS", ExcelUtils.arial_bold_s10_white_AUTO)); //0
		sheet3.addCell(new Label(11, i - 1, "SUP. DELIVERY NOTE REF", ExcelUtils.arial_bold_s10_white_AUTO)); //date bl +7
		
		// sheet3.addCell(new Label(3, i - 1, "OF",
		// ExcelUtils.arial_bold_s10_white_AUTO));
		
		/*
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Désignation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Couleur", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Qte/Colis", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(9, i - 1, "Total Qty", ExcelUtils.arial_bold_s10_white_AUTO));
          */
		Double sommeQte = 0d;
		Double sommeQteTotale = 0d;
		Long sommeNbrColis = 0l;

		String[] listPalette = refBonSortieList.split(",");

		List<String> listPaltteArrayList = new ArrayList<>();

		for (String p : listPalette) {
			listPaltteArrayList.add(p);

			// System.out.println("##################################### "+p);
		}

		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniService.getListByBonSortieList(listPaltteArrayList);

		Collections.sort(listeBonSortieFini);

		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		
		int nbrPaletteInitiale = 0;
		int nbrPalette = 0;
		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {
			
			nbrPaletteInitiale ++;

			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

			for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

				rouleau.setPalette(bonSortie.getReference());
				listeRouleauFini.add(rouleau);
			}

			Map<Map<Long, Double>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<Long, Double>, List<RouleauFiniValue>>();

			for (RouleauFiniValue rouleau : listeRouleauFini) {
				Long produitKey = rouleau.getProduitId();
				Double choixKey = rouleau.getMetrage();

				Map<Long, Double> map = new HashMap<Long, Double>();

				map.put(produitKey, choixKey);

				if (mapRouleau.get(map) == null) {

					mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
				}

				mapRouleau.get(map).add(rouleau);

			}

			ProduitValue produitValue = null;

			Iterator it = mapRouleau.entrySet().iterator();
			List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
			while (it.hasNext()) {

				Map.Entry<Map<Long, Double>, List<RouleauFiniValue>> pair = (Map.Entry<Map<Long, Double>, List<RouleauFiniValue>>) it
						.next();

				DetLivraisonVenteValue element = new DetLivraisonVenteValue();

				Long produitId = null;
				Double choixId = null;

				Map<Long, Double> produitIdchoixIdMap = pair.getKey();
				Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

				Map.Entry<Long, Double> produitIdchoixIdPair = (Map.Entry<Long, Double>) produitIdchoixIdIt.next();
				produitId = produitIdchoixIdPair.getKey();
				choixId = produitIdchoixIdPair.getValue();

				element.setProduitId(produitId);;
				element.setPrixUnitaireHT(choixId);

				/*
				 * if (choixId != null) { ChoixRouleauValue choixRouleau =
				 * choixRouleauPersistance.getChoixRouleauById(choixId); if (choixRouleau !=
				 * null) element.setChoix(choixRouleau.getDesignation()); }
				 */
				Double sommeMetrage = 0d;
				Double sommePoids = 0d;
				for (RouleauFiniValue rouleau : pair.getValue()) {
					// Somme des metrages
					if (rouleau.getMetrage() != null) {
						sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
					}
					if (rouleau.getPoids() != null) {
						sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
					}
				}

				// Qte livree = La somme des metrage des rouleaux de produit
				element.setQuantite(sommeMetrage);

				// TODO changement pour turkqua
				/*
				 * if (sommePoids > ZERO) element.setQuantite(sommePoids);
				 */

				if (element.getProduitId() != null) {

					// 1- Ramener l'OF
					// 2- Ramener le produit

					// 3- remplir cracteristique du produit

					/*MiseValue mise = miseService.rechercheMiseParReference(Long.parseLong(element.getNumeroOF()));

					if (mise != null) {

						element.setTypeProduit(mise.getTypeEtiquette());

						produitValue = produitService.rechercheProduitById(mise.getProduitId());
						if (produitValue != null) {

							element.setCouleurProduit(produitValue.getReferenceInterne());
							element.setProduitDesignation(produitValue.getDesignation());
							element.setProduitReference(produitValue.getReference());
							if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
								element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
							if (produitValue.getPrixUnitaire() != null)
								element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
						}
					}*/
					
					
					
					
					produitValue = produitService.rechercheProduitById(element.getProduitId());
					if (produitValue != null) {

						element.setCouleurProduit(produitValue.getReferenceInterne());
						element.setProduitDesignation(produitValue.getDesignation());
						element.setProduitReference(produitValue.getReference());
						if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
							element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
						if (produitValue.getPrixUnitaire() != null)
							element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
						
						
						element.setPalette(bonSortie.getReference());
						
						//affectation ref prix unitaire
						element.setNumeroOF(produitValue.getRefPrixUnitaire());
						
					}
					
					

				}

				element.setNombreColis(Long.valueOf(pair.getValue().size()));

				listDetLivraisonVente.add(element);

				it.remove();
			}

			if (listDetLivraisonVente.size() > 0) {
				Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
			}

			
			String firstReference = "";
			
			if(listDetLivraisonVente.size() > 0)

			     firstReference = listDetLivraisonVente.get(0).getProduitReference();
			
		//	String firstReferencePalette = listDetLivraisonVente.get(0).getPalette();

			int indicefirstEnter = numLigneCritRech + 4;
			int cptDetailLivraison =-1;
			
			
			listDetLivraisonVente = calucleRest(listDetLivraisonVente);
			
			
			for (DetLivraisonVenteValue element : listDetLivraisonVente) {
			

					sheet3.addCell(new Label(2, i, "PRODUCTION" , boldRed));
					
					sheet3.addCell(new Label(10, i, "0", boldRed));
					
					sheet3.addCell(new Label(11, i, supDeliveryNoteRefFormatted, boldRed));
					
					
					
					/*
					if (element.getProduitReference() != null) {

						if (element.getProduitReference().equals(firstReference) && (i > indicefirstEnter) && cptPalette>0 ) {

							sheet3.addCell(new Label(3, i, "-- -- -- -- --", boldRed));
						} else

						{
							firstReference = element.getProduitReference();
							sheet3.addCell(new Label(3, i, element.getProduitReference() + "", boldRed));
						}


					} else {
						sheet3.addCell(new Label(3, i, "", boldRed));
					}*/
					
				


					if (bonSortie.getReference() != null) {
						sheet3.addCell(new Label(3, i,
								bonSortie.getReference().substring(bonSortie.getReference().indexOf('/') + 1,
										bonSortie.getReference().length()) + "",
								boldRed));

					} else {
						sheet3.addCell(new Label(3, i, "", boldRed));
					}


					//ITEM NO.
	                 if (element.getProduitReference() != null) {

							sheet3.addCell(new Label(4, i, element.getProduitReference() , boldRed));

					} else {
						sheet3.addCell(new Label(4, i, "", boldRed));
					}
	                 
	                 //price Ref
					


					if (element.getNumeroOF() != null) {
						sheet3.addCell(new Label(5, i, element.getNumeroOF() + "", boldRed));

					} else {
						sheet3.addCell(new Label(5, i, "", boldRed));
					}
					
					// type

					if (bonSortie.getType() != null) {
						sheet3.addCell(new Label(6, i, bonSortie.getType() + "", boldRed));

					} else {
						sheet3.addCell(new Label(6, i, "", boldRed));
					}

				

					

					if (element.getNombreColis() != null) {
						sheet3.addCell(new Label(7, i, element.getNombreColis() + "", boldRed));
						sommeNbrColis += element.getNombreColis();

					} else {
						sheet3.addCell(new Label(7, i, "", boldRed));
					}
					
					
					//qteColis

					if (element.getPrixUnitaireHT() != null) {
						sheet3.addCell(new Label(8, i, convertisseur(element.getPrixUnitaireHT(), 0) + "", boldRed));
						sommeQte += element.getPrixUnitaireHT();

					} else {
						sheet3.addCell(new Label(8, i, "", boldRed));
					}
					
				

				   //REST
					
					if (element.getPrixTotalHT() != null) {
						sheet3.addCell(new Label(9, i, convertisseur(element.getPrixTotalHT(), 0) + "", boldRed));
						sommeQte += element.getPrixTotalHT();

					} else {
						sheet3.addCell(new Label(9, i, "0", boldRed));
					}
					
					
					
					

					i++;
			
					
					
				}
				
				
				
				
				
				
				

			

			
			
			
			
			
		
			i++;
			
		

		}

		/*
		sheet3.addCell(new Label(9, 13, sommeNbrColis + "", boldRed));

		sheet3.addCell(new Label(9, 14, listeBonSortieFini.size() + "", boldRed));

		// sheet3.addCell(new Label(2, i + 1, "TOTAUX", boldRed2));
		// sheet3.mergeCells(2, i + 1, 4, i + 1);
		sheet3.addCell(new Label(7, i,sommeNbrColis  + "", boldRed3));
		//sheet3.addCell(new Label(8, i, sommeQte + "", boldRed3));
		sheet3.addCell(new Label(9, i, convertisseur(sommeQteTotale, 1) + "", boldRed3));
		
		*/

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage,
		 * "La somme des quantités des commandes: ", boldRed5)); sheet3.addCell(new
		 * Label(numColBasDuPage+2, numLigneBasDuPage, sommeQte.toString(), boldRed3));
		 * 
		 * numLigneBasDuPage++; sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage, "Somme des minutes: ", boldRed5));
		 * sheet3.addCell(new Label(numColBasDuPage+2, numLigneBasDuPage,
		 * convertisseur(sommeTemps, 3).toString(), boldRed3));
		 * 
		 */

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
	

	private List<DetLivraisonVenteValue> calucleRest(List<DetLivraisonVenteValue> listDetLivraisonVente) {
		
		
		List<DetLivraisonVenteValue> list = new ArrayList<>();
		
		
		for(int i = 0 ;i<listDetLivraisonVente.size() ; i++) {
			
			
			
			
			DetLivraisonVenteValue detail = listDetLivraisonVente.get(i);
			
			detail.setPrixTotalHT(new Double(0));
			
			if(i+1 < listDetLivraisonVente.size() && detail.getProduitReference().equals(listDetLivraisonVente.get(i+1).getProduitReference())) {
				
				//rest
				detail.setPrixTotalHT(detail.getNombreColis() * detail.getPrixUnitaireHT());
				
				
				detail.setNombreColis(listDetLivraisonVente.get(i+1).getNombreColis());
				detail.setPrixUnitaireHT(listDetLivraisonVente.get(i+1).getPrixUnitaireHT());
				
				list.add(detail);
				
				i++;
				
				
			}else
				
			{
				list.add(detail);
				
			}
				
				
				
				
			
			
		}
		
		
		
		
		return list;
	}

	@RequestMapping(value = "/bonSortieExc-detail", method = RequestMethod.GET)
	public void genererBonSortieDetailReportExcel(
			@RequestParam(value = "idBonSortie", required = false) Long idBonSortie, HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("palette" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Palette", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		WritableFont font7 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn7 = new WritableCellFormat(font7);
		fn7.setWrap(true);
		fn7.setAlignment(Alignment.CENTRE);

		WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat fn6 = new WritableCellFormat(font6);
		fn6.setWrap(true);
		fn6.setAlignment(Alignment.CENTRE);

		WritableFont font8 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn8 = new WritableCellFormat(font7);
		fn8.setWrap(true);
		fn8.setAlignment(Alignment.LEFT);

		WritableFont font9 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat fn9 = new WritableCellFormat(font6);
		fn9.setWrap(true);
		fn9.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 20);
		sheet3.setColumnView(3, 10);
		sheet3.setColumnView(4, 30);
		sheet3.setColumnView(5, 15);
		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);
		sheet3.setColumnView(8, 10);

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active
		String clientDesigntion = "THERMOPLASTICS";
		/*
		 * List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		 * 
		 * for (BaseInfoValue baseInf : baseInfoValues) { if (baseInf.isActif()) {
		 * clientDesigntion = baseInf.getDesignation(); } }
		 */
		// sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		// sheet3.addCell(new Label(3, 2, " " + clientDesigntion, fn6));
		sheet3.mergeCells(3, 2, 8, 2);

		sheet3.addCell(new Label(2, 3, "" + "", fn7));
		sheet3.mergeCells(2, 3, 9, 3);
		sheet3.addCell(new Label(2, 4, "" + "" + "", fn7));
		sheet3.mergeCells(2, 4, 9, 4);
		sheet3.addCell(new Label(2, 5, "" + "" + "" + "", fn7));
		sheet3.mergeCells(2, 5, 9, 5);

		BonSortieFiniValue bonSortie = bonSortieFiniService.getBonSortieFiniById(idBonSortie);

		sheet3.addCell(new Label(2, 7, "Palette " + bonSortie.getReference(), boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "N° : ", boldRed3));
		sheet3.addCell(new Label(3, 10, bonSortie.getReference().substring(bonSortie.getReference().indexOf('/') + 1,
				bonSortie.getReference().length()) + "", boldRed));
		sheet3.addCell(new Label(2, 11, "Release Date :", boldRed3));
		sheet3.addCell(new Label(3, 11, dateFormat2.format(bonSortie.getDateSortie().getTime()) + "", boldRed));
		sheet3.addCell(new Label(2, 12, "Number of box", boldRed3));
		sheet3.addCell(new Label(3, 12, bonSortie.getNbColis() + "", boldRed));
		/*
		 * sheet3.addCell(new Label(2, 13, "SHIP TO  :", boldRed3)); sheet3.addCell(new
		 * Label(3, 13, "", boldRed));
		 * 
		 * sheet3.addCell(new Label(3, 14, "", boldRed)); sheet3.mergeCells(3, 14, 4,
		 * 15);
		 * 
		 * sheet3.addCell(new Label(5, 10, "SHIPPED BY :", boldRed3));
		 * sheet3.mergeCells(5, 10, 8, 10); sheet3.addCell(new Label(9, 10, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 11, "WEIGHT, NET :", boldRed3));
		 * sheet3.mergeCells(5, 11, 8, 11); sheet3.addCell(new Label(9, 11, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 12, "WEIGHT, GROSS :", boldRed3));
		 * sheet3.mergeCells(5, 12, 8, 12); sheet3.addCell(new Label(9, 12, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 13, "TOTAL BOXES :", boldRed3)); ;
		 * sheet3.mergeCells(5, 13, 8, 13); // sheet3.addCell(new Label(9, 13, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 14, "TOTAL PALLETS :", boldRed3));
		 * sheet3.mergeCells(5, 14, 8, 14); // sheet3.addCell(new Label(9, 14, "",
		 * boldRed));
		 * 
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "OF", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Référence", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Désignation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Couleur", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Type", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(7, i - 1, "NB/Colis", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Qte/Colis", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(9, i - 1, "Total Qty", ExcelUtils.arial_bold_s10_white_AUTO));

		Double sommeQte = 0d;
		Double sommeQteTotale = 0d;
		Long sommeNbrColis = 0l;

		List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

		for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

			rouleau.setPalette(bonSortie.getReference());
			listeRouleauFini.add(rouleau);
		}

		Map<Map<String, Double>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<String, Double>, List<RouleauFiniValue>>();

		for (RouleauFiniValue rouleau : listeRouleauFini) {
			String produitKey = rouleau.getReferenceMise();
			Double choixKey = rouleau.getMetrage();

			Map<String, Double> map = new HashMap<String, Double>();

			map.put(produitKey, choixKey);

			if (mapRouleau.get(map) == null) {

				mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
			}

			mapRouleau.get(map).add(rouleau);

		}

		ProduitValue produitValue = null;

		Iterator it = mapRouleau.entrySet().iterator();
		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<String, Double>, List<RouleauFiniValue>> pair = (Map.Entry<Map<String, Double>, List<RouleauFiniValue>>) it
					.next();

			DetLivraisonVenteValue element = new DetLivraisonVenteValue();

			String produitId = null;
			Double choixId = null;

			Map<String, Double> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<String, Double> produitIdchoixIdPair = (Map.Entry<String, Double>) produitIdchoixIdIt.next();
			produitId = produitIdchoixIdPair.getKey();
			choixId = produitIdchoixIdPair.getValue();

			element.setNumeroOF(produitId);
			element.setPrixUnitaireHT(choixId);

			/*
			 * if (choixId != null) { ChoixRouleauValue choixRouleau =
			 * choixRouleauPersistance.getChoixRouleauById(choixId); if (choixRouleau !=
			 * null) element.setChoix(choixRouleau.getDesignation()); }
			 */
			Double sommeMetrage = 0d;
			Double sommePoids = 0d;
			for (RouleauFiniValue rouleau : pair.getValue()) {
				// Somme des metrages
				if (rouleau.getMetrage() != null) {
					sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
				}
				if (rouleau.getPoids() != null) {
					sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
				}
			}

			// Qte livree = La somme des metrage des rouleaux de produit
			element.setQuantite(sommeMetrage);

			// TODO changement pour turkqua
			/*
			 * if (sommePoids > ZERO) element.setQuantite(sommePoids);
			 */

			if (element.getNumeroOF() != null) {

				// 1- Ramener l'OF
				// 2- Ramener le produit

				// 3- remplir cracteristique du produit

				MiseValue mise = miseService.rechercheMiseParReference(element.getNumeroOF());

				if (mise != null) {

					element.setTypeProduit(mise.getTypeEtiquette());

					produitValue = produitService.rechercheProduitById(mise.getProduitId());
					if (produitValue != null) {

						element.setCouleurProduit(produitValue.getReferenceInterne());
						element.setProduitDesignation(produitValue.getDesignation());
						element.setProduitReference(produitValue.getReference());
						if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
							element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
						if (produitValue.getPrixUnitaire() != null)
							element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
					}
				}

			}

			element.setNombreColis(Long.valueOf(pair.getValue().size()));

			listDetLivraisonVente.add(element);

			it.remove();
		}

		if (listDetLivraisonVente.size() > 0) {
			Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
		}

		for (DetLivraisonVenteValue element : listDetLivraisonVente) {

			// of

			if (element.getNumeroOF() != null) {
				sheet3.addCell(new Label(2, i, element.getNumeroOF() + "", boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", boldRed));
			}

			if (element.getProduitReference() != null) {
				sheet3.addCell(new Label(3, i, element.getProduitReference() + "", boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", boldRed));
			}

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(4, i, element.getProduitDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", boldRed));
			}

			// couleur

			if (element.getCouleurProduit() != null) {
				sheet3.addCell(new Label(5, i, element.getCouleurProduit() + "", boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", boldRed));
			}

			// type

			if (element.getTypeProduit() != null) {
				sheet3.addCell(new Label(6, i, element.getTypeProduit() + "", boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", boldRed));
			}

			/*
			 * if (element.getNombreColis() != null) {
			 * 
			 * if (element.getNombreColis() > 0) sheet3.addCell(new Label(8, i,
			 * element.getNombreColis() + "", boldRed)); else sheet3.addCell(new Label(8, i,
			 * "", boldRed));
			 * 
			 * sommeNbrColis += element.getNombreColis();
			 * 
			 * } else { sheet3.addCell(new Label(8, i, "", boldRed)); }
			 */

			if (element.getNombreColis() != null) {
				sheet3.addCell(new Label(7, i, element.getNombreColis() + "", boldRed));
				sommeNbrColis += element.getNombreColis();

			} else {
				sheet3.addCell(new Label(7, i, "", boldRed));
			}

			if (element.getPrixUnitaireHT() != null) {
				sheet3.addCell(new Label(8, i, element.getPrixUnitaireHT() + "", boldRed));
				sommeQte += element.getPrixUnitaireHT();

			} else {
				sheet3.addCell(new Label(8, i, "", boldRed));
			}

			if (element.getPrixUnitaireHT() != null && element.getNombreColis() != null) {
				Double totale = element.getPrixUnitaireHT() * element.getNombreColis();
				sheet3.addCell(new Label(9, i, convertisseur(totale, 3) + "", boldRed));
				sommeQteTotale += totale;

			} else {
				sheet3.addCell(new Label(9, i, "", boldRed));
			}

			i++;

		}

		sheet3.addCell(new Label(7, i, sommeNbrColis + "", boldRed));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage,
		 * "La somme des quantités des commandes: ", boldRed5)); sheet3.addCell(new
		 * Label(numColBasDuPage+2, numLigneBasDuPage, sommeQte.toString(), boldRed3));
		 * 
		 * numLigneBasDuPage++; sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage, "Somme des minutes: ", boldRed5));
		 * sheet3.addCell(new Label(numColBasDuPage+2, numLigneBasDuPage,
		 * convertisseur(sommeTemps, 3).toString(), boldRed3));
		 * 
		 */

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

	@RequestMapping(value = "/bonSortieExc", method = RequestMethod.GET)
	public void genererBonSortieReportExcel(@RequestParam(value = "idBonSortie", required = false) Long idBonSortie,
			HttpServletResponse response) throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("palette" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Palette", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		WritableFont font7 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn7 = new WritableCellFormat(font7);
		fn7.setWrap(true);
		fn7.setAlignment(Alignment.CENTRE);

		WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat fn6 = new WritableCellFormat(font6);
		fn6.setWrap(true);
		fn6.setAlignment(Alignment.CENTRE);

		WritableFont font8 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn8 = new WritableCellFormat(font7);
		fn8.setWrap(true);
		fn8.setAlignment(Alignment.LEFT);

		WritableFont font9 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat fn9 = new WritableCellFormat(font6);
		fn9.setWrap(true);
		fn9.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 20);
		sheet3.setColumnView(3, 30);
		sheet3.setColumnView(4, 15);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(6, 10);
		sheet3.setColumnView(7, 10);

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active
		String clientDesigntion = "THERMOPLASTICS";
		/*
		 * List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		 * 
		 * for (BaseInfoValue baseInf : baseInfoValues) { if (baseInf.isActif()) {
		 * clientDesigntion = baseInf.getDesignation(); } }
		 */
		// sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		// sheet3.addCell(new Label(3, 2, " " + clientDesigntion, fn6));
		sheet3.mergeCells(3, 2, 8, 2);

		sheet3.addCell(new Label(2, 3, "" + "", fn7));
		sheet3.mergeCells(2, 3, 9, 3);
		sheet3.addCell(new Label(2, 4, "" + "" + "", fn7));
		sheet3.mergeCells(2, 4, 9, 4);
		sheet3.addCell(new Label(2, 5, "" + "" + "" + "", fn7));
		sheet3.mergeCells(2, 5, 9, 5);

		BonSortieFiniValue bonSortie = bonSortieFiniService.getBonSortieFiniById(idBonSortie);

		sheet3.addCell(new Label(2, 7, "Palette " + bonSortie.getReference(), boldTitre));
		sheet3.mergeCells(2, 7, 8, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "N° : ", boldRed3));
		sheet3.addCell(new Label(3, 10, bonSortie.getReference().substring(bonSortie.getReference().indexOf('/') + 1,
				bonSortie.getReference().length()) + "", boldRed));
		sheet3.addCell(new Label(2, 11, "Release Date :", boldRed3));
		sheet3.addCell(new Label(3, 11, dateFormat2.format(bonSortie.getDateSortie().getTime()) + "", boldRed));
		sheet3.addCell(new Label(2, 12, "Number of box", boldRed3));
		sheet3.addCell(new Label(3, 12, bonSortie.getNbColis() + "", boldRed));
		/*
		 * sheet3.addCell(new Label(2, 13, "SHIP TO  :", boldRed3)); sheet3.addCell(new
		 * Label(3, 13, "", boldRed));
		 * 
		 * sheet3.addCell(new Label(3, 14, "", boldRed)); sheet3.mergeCells(3, 14, 4,
		 * 15);
		 * 
		 * sheet3.addCell(new Label(5, 10, "SHIPPED BY :", boldRed3));
		 * sheet3.mergeCells(5, 10, 8, 10); sheet3.addCell(new Label(9, 10, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 11, "WEIGHT, NET :", boldRed3));
		 * sheet3.mergeCells(5, 11, 8, 11); sheet3.addCell(new Label(9, 11, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 12, "WEIGHT, GROSS :", boldRed3));
		 * sheet3.mergeCells(5, 12, 8, 12); sheet3.addCell(new Label(9, 12, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 13, "TOTAL BOXES :", boldRed3)); ;
		 * sheet3.mergeCells(5, 13, 8, 13); // sheet3.addCell(new Label(9, 13, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 14, "TOTAL PALLETS :", boldRed3));
		 * sheet3.mergeCells(5, 14, 8, 14); // sheet3.addCell(new Label(9, 14, "",
		 * boldRed));
		 * 
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Couleur", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Type", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(6, i - 1, "NB/Colis", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "Qte/Colis", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(8, i - 1, "Total Qty", ExcelUtils.arial_bold_s10_white_AUTO));

		Double sommeQte = 0d;
		Double sommeQteTotale = 0d;
		Long sommeNbrColis = 0l;

		List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

		for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

			rouleau.setPalette(bonSortie.getReference());
			listeRouleauFini.add(rouleau);
		}

		Map<Map<String, Double>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<String, Double>, List<RouleauFiniValue>>();

		for (RouleauFiniValue rouleau : listeRouleauFini) {
			String produitKey = rouleau.getReferenceMise();
			Double choixKey = rouleau.getMetrage();

			Map<String, Double> map = new HashMap<String, Double>();

			map.put(produitKey, choixKey);

			if (mapRouleau.get(map) == null) {

				mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
			}

			mapRouleau.get(map).add(rouleau);

		}

		ProduitValue produitValue = null;

		Iterator it = mapRouleau.entrySet().iterator();
		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<String, Double>, List<RouleauFiniValue>> pair = (Map.Entry<Map<String, Double>, List<RouleauFiniValue>>) it
					.next();

			DetLivraisonVenteValue element = new DetLivraisonVenteValue();

			String produitId = null;
			Double choixId = null;

			Map<String, Double> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<String, Double> produitIdchoixIdPair = (Map.Entry<String, Double>) produitIdchoixIdIt.next();
			produitId = produitIdchoixIdPair.getKey();
			choixId = produitIdchoixIdPair.getValue();

			element.setNumeroOF(produitId);

			element.setPrixUnitaireHT(choixId);

			/*
			 * if (choixId != null) { ChoixRouleauValue choixRouleau =
			 * choixRouleauPersistance.getChoixRouleauById(choixId); if (choixRouleau !=
			 * null) element.setChoix(choixRouleau.getDesignation()); }
			 */
			Double sommeMetrage = 0d;
			Double sommePoids = 0d;
			for (RouleauFiniValue rouleau : pair.getValue()) {
				// Somme des metrages
				if (rouleau.getMetrage() != null) {
					sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
				}
				if (rouleau.getPoids() != null) {
					sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
				}
			}

			// Qte livree = La somme des metrage des rouleaux de produit
			element.setQuantite(sommeMetrage);

			// TODO changement pour turkqua
			/*
			 * if (sommePoids > ZERO) element.setQuantite(sommePoids);
			 */

			if (element.getNumeroOF() != null) {

				// 1- Ramener l'OF
				// 2- Ramener le produit

				// 3- remplir cracteristique du produit

				MiseValue mise = miseService.rechercheMiseParReference(element.getNumeroOF());

				if (mise != null) {

					element.setTypeProduit(mise.getTypeEtiquette());

					produitValue = produitService.rechercheProduitById(mise.getProduitId());
					if (produitValue != null) {

						element.setCouleurProduit(produitValue.getReferenceInterne());
						element.setProduitDesignation(produitValue.getDesignation());
						element.setProduitReference(produitValue.getReference());
						if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
							element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
						if (produitValue.getPrixUnitaire() != null)
							element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
					}
				}

			}

			element.setNombreColis(Long.valueOf(pair.getValue().size()));

			listDetLivraisonVente.add(element);

			it.remove();
		}

		if (listDetLivraisonVente.size() > 0) {
			Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
		}

		for (DetLivraisonVenteValue element : listDetLivraisonVente) {

			if (element.getProduitReference() != null) {

				sheet3.addCell(new Label(2, i, element.getProduitReference() + "", boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", boldRed));
			}

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(3, i, element.getProduitDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", boldRed));
			}

			// couleur

			if (element.getCouleurProduit() != null) {
				sheet3.addCell(new Label(4, i, element.getCouleurProduit() + "", boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", boldRed));
			}

			// type

			if (element.getTypeProduit() != null) {
				sheet3.addCell(new Label(5, i, element.getTypeProduit() + "", boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", boldRed));
			}

			/*
			 * if (element.getNombreColis() != null) {
			 * 
			 * if (element.getNombreColis() > 0) sheet3.addCell(new Label(8, i,
			 * element.getNombreColis() + "", boldRed)); else sheet3.addCell(new Label(8, i,
			 * "", boldRed));
			 * 
			 * sommeNbrColis += element.getNombreColis();
			 * 
			 * } else { sheet3.addCell(new Label(8, i, "", boldRed)); }
			 */

			if (element.getNombreColis() != null) {
				sheet3.addCell(new Label(6, i, element.getNombreColis() + "", boldRed));
				sommeNbrColis += element.getNombreColis();

			} else {
				sheet3.addCell(new Label(6, i, "", boldRed));
			}

			if (element.getPrixUnitaireHT() != null) {
				sheet3.addCell(new Label(7, i, element.getPrixUnitaireHT() + "", boldRed));
				sommeQte += element.getPrixUnitaireHT();

			} else {
				sheet3.addCell(new Label(7, i, "", boldRed));
			}

			if (element.getPrixUnitaireHT() != null && element.getNombreColis() != null) {
				Double totale = element.getPrixUnitaireHT() * element.getNombreColis();
				sheet3.addCell(new Label(8, i, convertisseur(totale, 3) + "", boldRed));
				sommeQteTotale += totale;

			} else {
				sheet3.addCell(new Label(8, i, "", boldRed));
			}

			i++;

		}

		sheet3.addCell(new Label(6, i, sommeNbrColis + "", boldRed));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage,
		 * "La somme des quantités des commandes: ", boldRed5)); sheet3.addCell(new
		 * Label(numColBasDuPage+2, numLigneBasDuPage, sommeQte.toString(), boldRed3));
		 * 
		 * numLigneBasDuPage++; sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage, "Somme des minutes: ", boldRed5));
		 * sheet3.addCell(new Label(numColBasDuPage+2, numLigneBasDuPage,
		 * convertisseur(sommeTemps, 3).toString(), boldRed3));
		 * 
		 */

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

	@RequestMapping(value = "/bonSortieExc-by-article", method = RequestMethod.GET)
	public void genererBonSortieByArticleReportExcel(
			@RequestParam(value = "idBonSortie", required = false) Long idBonSortie, HttpServletResponse response)
			throws WriteException, IOException {

		// logger.info("Generate a {} Report BonLivraison", type);

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("palette" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Palette", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		WritableFont font7 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn7 = new WritableCellFormat(font7);
		fn7.setWrap(true);
		fn7.setAlignment(Alignment.CENTRE);

		WritableFont font6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
		WritableCellFormat fn6 = new WritableCellFormat(font6);
		fn6.setWrap(true);
		fn6.setAlignment(Alignment.CENTRE);

		WritableFont font8 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat fn8 = new WritableCellFormat(font7);
		fn8.setWrap(true);
		fn8.setAlignment(Alignment.LEFT);

		WritableFont font9 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat fn9 = new WritableCellFormat(font6);
		fn9.setWrap(true);
		fn9.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 20);
		sheet3.setColumnView(3, 30);
		sheet3.setColumnView(4, 15);
		// sheet3.setColumnView(5, 10);
		sheet3.setColumnView(5, 10);
		sheet3.setColumnView(6, 10);

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		// Récuperation de Nom du client active
		String clientDesigntion = "THERMOPLASTICS";
		/*
		 * List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		 * 
		 * for (BaseInfoValue baseInf : baseInfoValues) { if (baseInf.isActif()) {
		 * clientDesigntion = baseInf.getDesignation(); } }
		 */
		// sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		// sheet3.addCell(new Label(3, 2, " " + clientDesigntion, fn6));
		sheet3.mergeCells(3, 2, 7, 2);

		sheet3.addCell(new Label(2, 3, "" + "", fn7));
		sheet3.mergeCells(2, 3, 9, 3);
		sheet3.addCell(new Label(2, 4, "" + "" + "", fn7));
		sheet3.mergeCells(2, 4, 9, 4);
		sheet3.addCell(new Label(2, 5, "" + "" + "" + "", fn7));
		sheet3.mergeCells(2, 5, 9, 5);

		BonSortieFiniValue bonSortie = bonSortieFiniService.getBonSortieFiniById(idBonSortie);

		sheet3.addCell(new Label(2, 7, "Palette " + bonSortie.getReference(), boldTitre));
		sheet3.mergeCells(2, 7, 7, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		sheet3.addCell(new Label(2, 10, "N° : ", boldRed3));
		sheet3.addCell(new Label(3, 10, bonSortie.getReference().substring(bonSortie.getReference().indexOf('/') + 1,
				bonSortie.getReference().length()) + "", boldRed));
		sheet3.addCell(new Label(2, 11, "Release Date :", boldRed3));
		sheet3.addCell(new Label(3, 11, dateFormat2.format(bonSortie.getDateSortie().getTime()) + "", boldRed));
		sheet3.addCell(new Label(2, 12, "Number of box", boldRed3));
		sheet3.addCell(new Label(3, 12, bonSortie.getNbColis() + "", boldRed));
		/*
		 * sheet3.addCell(new Label(2, 13, "SHIP TO  :", boldRed3)); sheet3.addCell(new
		 * Label(3, 13, "", boldRed));
		 * 
		 * sheet3.addCell(new Label(3, 14, "", boldRed)); sheet3.mergeCells(3, 14, 4,
		 * 15);
		 * 
		 * sheet3.addCell(new Label(5, 10, "SHIPPED BY :", boldRed3));
		 * sheet3.mergeCells(5, 10, 8, 10); sheet3.addCell(new Label(9, 10, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 11, "WEIGHT, NET :", boldRed3));
		 * sheet3.mergeCells(5, 11, 8, 11); sheet3.addCell(new Label(9, 11, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 12, "WEIGHT, GROSS :", boldRed3));
		 * sheet3.mergeCells(5, 12, 8, 12); sheet3.addCell(new Label(9, 12, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 13, "TOTAL BOXES :", boldRed3)); ;
		 * sheet3.mergeCells(5, 13, 8, 13); // sheet3.addCell(new Label(9, 13, "",
		 * boldRed));
		 * 
		 * sheet3.addCell(new Label(5, 14, "TOTAL PALLETS :", boldRed3));
		 * sheet3.mergeCells(5, 14, 8, 14); // sheet3.addCell(new Label(9, 14, "",
		 * boldRed));
		 * 
		 * 
		 */

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 14;
		/*
		 * sheet3.addCell(new Label(numColCritRech, numLigneCritRech,
		 * "Critère de recherche", boldRed5)); numLigneCritRech ++;
		 */

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Référence", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Désignation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Couleur", ExcelUtils.arial_bold_s10_white_AUTO));
		// sheet3.addCell(new Label(5, i - 1, "Type",
		// ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(5, i - 1, "NB/Colis", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Qte/Colis", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(7, i - 1, "Total Qty", ExcelUtils.arial_bold_s10_white_AUTO));

		Double sommeQte = 0d;
		Double sommeQteTotale = 0d;
		Long sommeNbrColis = 0l;

		List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

		for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

			rouleau.setPalette(bonSortie.getReference());
			listeRouleauFini.add(rouleau);
		}

		Map<Map<Long, Double>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<Long, Double>, List<RouleauFiniValue>>();

		for (RouleauFiniValue rouleau : listeRouleauFini) {
			Long produitKey = rouleau.getProduitId();
			Double choixKey = rouleau.getMetrage();

			Map<Long, Double> map = new HashMap<Long, Double>();

			map.put(produitKey, choixKey);

			if (mapRouleau.get(map) == null) {

				mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
			}

			mapRouleau.get(map).add(rouleau);

		}

		ProduitValue produitValue = null;

		Iterator it = mapRouleau.entrySet().iterator();
		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<Long, Double>, List<RouleauFiniValue>> pair = (Map.Entry<Map<Long, Double>, List<RouleauFiniValue>>) it
					.next();

			DetLivraisonVenteValue element = new DetLivraisonVenteValue();

			Long produitId = null;
			Double choixId = null;

			Map<Long, Double> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<Long, Double> produitIdchoixIdPair = (Map.Entry<Long, Double>) produitIdchoixIdIt.next();
			produitId = produitIdchoixIdPair.getKey();
			choixId = produitIdchoixIdPair.getValue();

			element.setProduitId(produitId);

			element.setPrixUnitaireHT(choixId);

			/*
			 * if (choixId != null) { ChoixRouleauValue choixRouleau =
			 * choixRouleauPersistance.getChoixRouleauById(choixId); if (choixRouleau !=
			 * null) element.setChoix(choixRouleau.getDesignation()); }
			 */
			Double sommeMetrage = 0d;
			Double sommePoids = 0d;
			for (RouleauFiniValue rouleau : pair.getValue()) {
				// Somme des metrages
				if (rouleau.getMetrage() != null) {
					sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
				}
				if (rouleau.getPoids() != null) {
					sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
				}
			}

			// Qte livree = La somme des metrage des rouleaux de produit
			element.setQuantite(sommeMetrage);

			// TODO changement pour turkqua
			/*
			 * if (sommePoids > ZERO) element.setQuantite(sommePoids);
			 */

			if (element.getProduitId() != null) {

				produitValue = produitService.rechercheProduitById(element.getProduitId());
				if (produitValue != null) {

					element.setCouleurProduit(produitValue.getReferenceInterne());
					element.setProduitDesignation(produitValue.getDesignation());
					element.setProduitReference(produitValue.getReference());
					if (element.getPrixUnitaireHT() == null || element.getPrixUnitaireHT() == 0)
						element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
					if (produitValue.getPrixUnitaire() != null)
						element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
				}

			}

			element.setNombreColis(Long.valueOf(pair.getValue().size()));

			listDetLivraisonVente.add(element);

			it.remove();
		}

		if (listDetLivraisonVente.size() > 0) {
			Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
		}

		String firstReference = listDetLivraisonVente.get(0).getProduitReference();

		int indicefirstEnter = numLigneCritRech + 4;

		for (DetLivraisonVenteValue element : listDetLivraisonVente) {

			if (element.getProduitReference() != null) {

				if (element.getProduitReference().equals(firstReference) && (i > indicefirstEnter)) {

					sheet3.addCell(new Label(2, i, "-- -- -- -- --", boldRed));
				} else

				{
					firstReference = element.getProduitReference();
					sheet3.addCell(new Label(2, i, element.getProduitReference() + "", boldRed));
				}

			} else {
				sheet3.addCell(new Label(2, i, "", boldRed));
			}

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(3, i, element.getProduitDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", boldRed));
			}

			// couleur

			if (element.getCouleurProduit() != null) {
				sheet3.addCell(new Label(4, i, element.getCouleurProduit() + "", boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", boldRed));
			}

			// type

			/*
			 * if (element.getTypeProduit() != null) { sheet3.addCell(new Label(5, i,
			 * element.getTypeProduit() + "", boldRed));
			 * 
			 * } else { sheet3.addCell(new Label(5, i, "", boldRed)); }
			 */

			/*
			 * if (element.getNombreColis() != null) {
			 * 
			 * if (element.getNombreColis() > 0) sheet3.addCell(new Label(8, i,
			 * element.getNombreColis() + "", boldRed)); else sheet3.addCell(new Label(8, i,
			 * "", boldRed));
			 * 
			 * sommeNbrColis += element.getNombreColis();
			 * 
			 * } else { sheet3.addCell(new Label(8, i, "", boldRed)); }
			 */

			if (element.getNombreColis() != null) {
				sheet3.addCell(new Label(5, i, element.getNombreColis() + "", boldRed));
				sommeNbrColis += element.getNombreColis();

			} else {
				sheet3.addCell(new Label(5, i, "", boldRed));
			}

			if (element.getPrixUnitaireHT() != null) {
				sheet3.addCell(new Label(6, i, element.getPrixUnitaireHT() + "", boldRed));
				sommeQte += element.getPrixUnitaireHT();

			} else {
				sheet3.addCell(new Label(6, i, "", boldRed));
			}

			if (element.getPrixUnitaireHT() != null && element.getNombreColis() != null) {
				Double totale = element.getPrixUnitaireHT() * element.getNombreColis();
				sheet3.addCell(new Label(7, i, convertisseur(totale, 3) + "", boldRed));
				sommeQteTotale += totale;

			} else {
				sheet3.addCell(new Label(7, i, "", boldRed));
			}

			i++;

		}

		sheet3.addCell(new Label(5, i, sommeNbrColis + "", boldRed));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		/*
		 * sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage,
		 * "La somme des quantités des commandes: ", boldRed5)); sheet3.addCell(new
		 * Label(numColBasDuPage+2, numLigneBasDuPage, sommeQte.toString(), boldRed3));
		 * 
		 * numLigneBasDuPage++; sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage,
		 * numColBasDuPage+1,numLigneBasDuPage); sheet3.addCell(new
		 * Label(numColBasDuPage, numLigneBasDuPage, "Somme des minutes: ", boldRed5));
		 * sheet3.addCell(new Label(numColBasDuPage+2, numLigneBasDuPage,
		 * convertisseur(sommeTemps, 3).toString(), boldRed3));
		 * 
		 */

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
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/machine-of", method = RequestMethod.GET)
	public void generateMachineOFReportExel(

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("Machine-OF" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Machine-OF", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);

		sheet3.setColumnView(2, 12);// machine

		sheet3.setColumnView(3, 20); // OF

		sheet3.setColumnView(4, 18);// refrence

		sheet3.setColumnView(5, 30); // designation

		sheet3.setColumnView(6, 15); // status

		sheet3.setColumnView(7, 30); // date deb

		sheet3.setColumnView(8, 30); // date fin

		sheet3.setColumnView(9, 15); // quantite restante
		
		sheet3.setColumnView(10, 15); // quantite restante
		
		sheet3.setColumnView(11, 15); // quantite restante

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/thermoplastics.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "Etat Machine", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7, 11, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Search Criteria", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "On " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

	


		 List<MachineOFValue> machineOFlist = machineService.getAllMachineOFvalue();

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Machine", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "O.F", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Designation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "Status", ExcelUtils.arial_bold_s10_white_AUTO));
		
		sheet3.addCell(new Label(7, i - 1, "Date Deb.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Date Fin.", ExcelUtils.arial_bold_s10_white_AUTO));

		sheet3.addCell(new Label(9, i - 1, "Qte Demande", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(10, i - 1, "Qte Produite", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(11, i - 1, "Qte Restante", ExcelUtils.arial_bold_s10_white_AUTO));
		

	//	Long sommeQuantite = 0l;
	//	Long sommeQuantiteProduced = 0l;
	//	Long sommeQuantiteRestante = 0l;

		for (MachineOFValue element : machineOFlist) {

			// Machine

			if (element.getDesignation() != null) {
				sheet3.addCell(new Label(2, i, element.getDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			// OF

			if (element.getNumeroOF() != null) {
				sheet3.addCell(new Label(3, i, element.getNumeroOF() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			
			// reference

			if (element.getProduitReference() != null) {
				sheet3.addCell(new Label(4, i, element.getProduitReference() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}
			
			
			// designation produit

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getProduitDesignation() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			
			// statut

			if (element.getStatus() != null) {
				sheet3.addCell(new Label(6, i, element.getStatus() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			

	
			// date deb

			if (element.getDateDebut() != null) {

				sheet3.addCell(new Label(7, i, dateTimeFormat.format(element.getDateDebut().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}

			// end date

			if (element.getDateFin() != null) {
				sheet3.addCell(new Label(8, i, dateTimeFormat.format(element.getDateFin().getTime()) + "",
						ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}

			// quantite

			if (element.getQteOF() != null) {
				sheet3.addCell(new Label(9, i, element.getQteOF() + "", ExcelUtils.boldRed));

				//sommeQuantite += element.getQteOF().longValue();

			} else {
				sheet3.addCell(new Label(9, i, "", ExcelUtils.boldRed));
			}

			// quantite produite

			if (element.getQteProduite() != null) {
				sheet3.addCell(new Label(10, i, element.getQteProduite() + "", ExcelUtils.boldRed));

			//	sommeQuantiteProduced += element.getQteProduite();
			} else {
				sheet3.addCell(new Label(10, i, "", ExcelUtils.boldRed));
			}

			// qte restante

			if (element.getQteProduite() != null && element.getQteOF() != null) {

				Long qteRestante = element.getQteOF().longValue() - element.getQteProduite();
				sheet3.addCell(new Label(11, i, qteRestante + "", ExcelUtils.boldRed));

				//sommeQuantiteRestante += qteRestante;
			} else {
				sheet3.addCell(new Label(11, i, "", ExcelUtils.boldRed));
			}

			i++;

		}

		i++;

	//	sheet3.addCell(new Label(7, i, sommeQuantite + "", ExcelUtils.boldRed3));

	//	sheet3.addCell(new Label(8, i, sommeQuantiteProduced + "", ExcelUtils.boldRed3));

	//	sheet3.addCell(new Label(9, i, sommeQuantiteRestante + "", ExcelUtils.boldRed3));

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

	//	sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		//sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,"Number of lines : " + resultatRecherche.getListeElementRechecheMiseValeur().size(),	ExcelUtils.boldRed5));
			
			
		/*
		 * sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
		 * resultatRecherche.getListeElementRechecheMiseValeur().size() + "",
		 * ExcelUtils.boldRed3));
		 */
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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
		// System.out.println("#####################################" + dateDe);
	}

	
	
	
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/inventaireByOF", method = RequestMethod.GET)
	public void generateInventaireByOFReportExel(
	
	      @RequestParam(value ="client",required = false) Long client, 
			
			@RequestParam(value ="nombreColieDu",required = false) Long nombreColieDu,
			
			@RequestParam(value ="nombreColieA",required = false) Long nombreColieA, 
			//@RequestParam("entrepot") Long entrepot,
			//@RequestParam("emplacement") String emplacement, 
			@RequestParam(value ="metrageDu",required = false) Double metrageDu,
			@RequestParam(value ="metrageA",required = false) Double metrageA,
			@RequestParam("dateEtat") String dateEtat,
			@RequestParam(value ="designationQuiContient",required = false) String designationQuiContient,

			// Hajer Amri 06/02/2017
			@RequestParam(value ="referenceProduit",required = false) String referenceProduit,

			@RequestParam(value ="fini",required = false) String fini, 
			@RequestParam(value ="orderBy",required = false) String orderBy,
			@RequestParam(value ="typeOf",required = false) String typeOf,
			@RequestParam(value ="type",required = false) String type, HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("suivi-production" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("suivi-production", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		
		
		sheet3.setColumnView(2, 22);
		sheet3.setColumnView(3, 22);

		sheet3.setColumnView(4, 15);

		sheet3.setColumnView(5, 30); 

		sheet3.setColumnView(6, 15);

		sheet3.setColumnView(7, 20); 

		sheet3.setColumnView(8, 20); 

		sheet3.setColumnView(9, 15); 

		sheet3.setColumnView(10, 15);

		sheet3.setColumnView(11, 15); 

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/logo_client.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "inventaire", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7,12, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche ", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "Le " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

	
		CritereRechercheRouleauStandardValue critereRechercheRouleauStandard = new CritereRechercheRouleauStandardValue();
		critereRechercheRouleauStandard.setClient(client);
		critereRechercheRouleauStandard.setDateEtat(stringToCalendar(dateEtat));
		critereRechercheRouleauStandard.setDesignationQuiContient(designationQuiContient);
		////critereRechercheRouleauStandard.setEmplacement(emplacement);
	////	critereRechercheRouleauStandard.setEntrepot(entrepot);
		critereRechercheRouleauStandard.setMetrageA(metrageA);
		critereRechercheRouleauStandard.setMetrageDu(metrageDu);
		critereRechercheRouleauStandard.setNombreColieA(nombreColieA);
		critereRechercheRouleauStandard.setNombreColieDu(nombreColieDu);
		critereRechercheRouleauStandard.setOrderBy(orderBy);
		//critereRechercheRouleauStandard.setFini(fini);
		critereRechercheRouleauStandard.setReferenceProduit(referenceProduit);
		

		if (isNotEmty(client))

		{
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(client);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation() +"", ExcelUtils.boldRed3));

	
		}

	/*	if (isNotEmty(type))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, type, ExcelUtils.boldRed3));

		}*/

		if (isNotEmty(designationQuiContient))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "designationQuiContient :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, designationQuiContient, ExcelUtils.boldRed3));

		}

		if (isNotEmty(metrageA))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "metrageA :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageA+"", ExcelUtils.boldRed3));

		
		}
		
		if (isNotEmty(metrageDu))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "metrageDu :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageDu+"", ExcelUtils.boldRed3));

		}

		if (isNotEmty(nombreColieA))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "nombreColieA. ", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, nombreColieA+"", ExcelUtils.boldRed3));

		}

		if (isNotEmty(nombreColieDu))

		{
		
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "nombreColieDu", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, nombreColieDu+"", ExcelUtils.boldRed3));


		}

		if (isNotEmty(dateEtat)) {
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					dateEtat,
					ExcelUtils.boldRed3));

		}


	
	
		InventaireReportValue vInventaireReport = gestionnaireReportService
				.getInventaireByOFReportValue(critereRechercheRouleauStandard);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "OF", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Designation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "Quantite", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "nombreColies", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "PUHT.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Prix Total.", ExcelUtils.arial_bold_s10_white_AUTO));
		
		Double mantantTtcTotale = 0d;
		Long NbreColis = 0l;
		Double quantiteTotale = 0d;
		for (ElementResultatRechecheRouleauStandardValue element : vInventaireReport.getElementsList()  ) {
			
		
			if (element.getReferenceMise() != null) {
				sheet3.addCell(new Label(2, i, element.getReferenceMise() + "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getReferenceProduit() != null) {
				sheet3.addCell(new Label(3, i, element.getReferenceProduit()+ "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getDesignation() != null) {
				sheet3.addCell(new Label(4, i, element.getDesignation()+ "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (element.getMetrage() != null) {
				sheet3.addCell(new Label(5, i, element.getMetrage()+ "", ExcelUtils.boldRed));
				
				quantiteTotale+= element.getMetrage();
			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}

			if (element.getNombreColis() != null) {
				sheet3.addCell(new Label(6, i, element.getNombreColis()+ "", ExcelUtils.boldRed));

				
				 NbreColis+=element.getNombreColis();
			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}

			if (element.getPrixUnitaire() != null) {
				sheet3.addCell(new jxl.write.Number(7, i, element.getPrixUnitaire(), ExcelUtils.boldRed));
			
			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}
	
			
			
			if (element.getPrixTotal() != null) {
				sheet3.addCell(new jxl.write.Number(8, i, convertisseur(element.getPrixTotal(), 4), ExcelUtils.boldRed));
				mantantTtcTotale += element.getPrixTotal();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}
		
			
			i++;

		}

		i++;
		i++;


		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantTtcTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Nbr Colis", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i,NbreColis+"" , ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));


		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"Number of lines : " + vInventaireReport.getElementsList().size(),
				ExcelUtils.boldRed5));
	
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/inventaire", method = RequestMethod.GET)
	public void generateInventaireReportExel(
	
	      @RequestParam(value ="client",required = false) Long client, 
			
			@RequestParam(value ="nombreColieDu",required = false) Long nombreColieDu,
			
			@RequestParam(value ="nombreColieA",required = false) Long nombreColieA, 
			//@RequestParam("entrepot") Long entrepot,
			//@RequestParam("emplacement") String emplacement, 
			@RequestParam(value ="metrageDu",required = false) Double metrageDu,
			@RequestParam(value ="metrageA",required = false) Double metrageA,
			@RequestParam("dateEtat") String dateEtat,
			@RequestParam(value ="designationQuiContient",required = false) String designationQuiContient,

			// Hajer Amri 06/02/2017
			@RequestParam(value ="referenceProduit",required = false) String referenceProduit,

			@RequestParam(value ="fini",required = false) String fini, 
			@RequestParam(value ="orderBy",required = false) String orderBy,
			@RequestParam(value ="typeOf",required = false) String typeOf,
			@RequestParam(value ="type",required = false) String type, HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();

		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("suivi-production" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("suivi-production", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		
		
		sheet3.setColumnView(2, 22);
		sheet3.setColumnView(3, 22);

		sheet3.setColumnView(4, 15);

		sheet3.setColumnView(5, 30); 

		sheet3.setColumnView(6, 15);

		sheet3.setColumnView(7, 20); 

		sheet3.setColumnView(8, 20); 

		sheet3.setColumnView(9, 15); 

		sheet3.setColumnView(10, 15);

		sheet3.setColumnView(11, 15); 

		/**************************************************************************/

		WritableImage image = new WritableImage(2, 1, // column, row
				1, 6, // width, height in terms of number of cells
				new File("C:/ERP/logos_clients/logo_client.png")); // Supports only 'png' images

		sheet3.addImage(image);

		// Nom du rapport et la date

		ExcelUtils.init();

		sheet3.addCell(new Label(2, 7, "inventaire", ExcelUtils.boldTitre));
		sheet3.mergeCells(2, 7,12, 8);
		// sheet3.addCell(new Label(2, 6, "Le : " + dateTimeFormat2.format(d),
		// ExcelUtils.boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche ", ExcelUtils.boldRed5));
		sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "Le " + dateTimeFormat2.format(d),
				ExcelUtils.boldRed3));
		numLigneCritRech++;

	
		CritereRechercheRouleauStandardValue critereRechercheRouleauStandard = new CritereRechercheRouleauStandardValue();
		critereRechercheRouleauStandard.setClient(client);
		critereRechercheRouleauStandard.setDateEtat(stringToCalendar(dateEtat));
		critereRechercheRouleauStandard.setDesignationQuiContient(designationQuiContient);
		////critereRechercheRouleauStandard.setEmplacement(emplacement);
	////	critereRechercheRouleauStandard.setEntrepot(entrepot);
		critereRechercheRouleauStandard.setMetrageA(metrageA);
		critereRechercheRouleauStandard.setMetrageDu(metrageDu);
		critereRechercheRouleauStandard.setNombreColieA(nombreColieA);
		critereRechercheRouleauStandard.setNombreColieDu(nombreColieDu);
		critereRechercheRouleauStandard.setOrderBy(orderBy);
		//critereRechercheRouleauStandard.setFini(fini);
		critereRechercheRouleauStandard.setReferenceProduit(referenceProduit);
		

		if (isNotEmty(client))

		{
			PartieInteresseValue partieInteresse= partieInteresseeService.getById(client);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, partieInteresse.getAbreviation() +"", ExcelUtils.boldRed3));

	
		}

		/*if (isNotEmty(type))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Type :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, type, ExcelUtils.boldRed3));

		}*/

		if (isNotEmty(designationQuiContient))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "designationQuiContient :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, designationQuiContient, ExcelUtils.boldRed3));

		}

		if (isNotEmty(metrageA))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "metrageA :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageA+"", ExcelUtils.boldRed3));

		
		}
		
		if (isNotEmty(metrageDu))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "metrageDu :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, metrageDu+"", ExcelUtils.boldRed3));

		}

		if (isNotEmty(nombreColieA))

		{
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "nombreColieA. ", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, nombreColieA+"", ExcelUtils.boldRed3));

		}

		if (isNotEmty(nombreColieDu))

		{
		
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "nombreColieDu", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, nombreColieDu+"", ExcelUtils.boldRed3));


		}

		if (isNotEmty(dateEtat)) {
			
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date :", ExcelUtils.boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					dateEtat,ExcelUtils.boldRed3));
					

		}


	

		InventaireReportValue vInventaireReport = gestionnaireReportService
				.getInventaireReportValue(critereRechercheRouleauStandard);

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Reference", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(3, i - 1, "Designation", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(4, i - 1, "Quantite", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(5, i - 1, "nombreColies", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(6, i - 1, "OF", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(7, i - 1, "PUHT.", ExcelUtils.arial_bold_s10_white_AUTO));
		sheet3.addCell(new Label(8, i - 1, "Prix Total.", ExcelUtils.arial_bold_s10_white_AUTO));
		
		Double mantantTtcTotale = 0d;
		Long NbreColis = 0l;
		Double quantiteTotale = 0d;
		for (ElementResultatRechecheRouleauStandardValue element : vInventaireReport.getElementsList()  ) {
			
		

			if (element.getReferenceProduit() != null) {
				sheet3.addCell(new Label(2, i, element.getReferenceProduit()+ "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getDesignation() != null) {
				sheet3.addCell(new Label(3, i, element.getDesignation()+ "", ExcelUtils.boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", ExcelUtils.boldRed));
			}

			if (element.getMetrage() != null) {
				sheet3.addCell(new Label(4, i, element.getMetrage()+ "", ExcelUtils.boldRed));
				
				quantiteTotale+= element.getMetrage();
			} else {
				sheet3.addCell(new Label(4, i, "", ExcelUtils.boldRed));
			}

			if (element.getNombreColis() != null) {
				sheet3.addCell(new Label(5, i, element.getNombreColis()+ "", ExcelUtils.boldRed));

				
				 NbreColis+=element.getNombreColis();
			} else {
				sheet3.addCell(new Label(5, i, "", ExcelUtils.boldRed));
			}
			
			if (element.getNombreMise() != null) {
				sheet3.addCell(new Label(6, i, element.getNombreMise()+ "", ExcelUtils.boldRed));

			
			} else {
				sheet3.addCell(new Label(6, i, "", ExcelUtils.boldRed));
			}
			

			if (element.getPrixUnitaire() != null) {
				sheet3.addCell(new jxl.write.Number(7, i, element.getPrixUnitaire(), ExcelUtils.boldRed));
			
			} else {
				sheet3.addCell(new Label(7, i, "", ExcelUtils.boldRed));
			}
	
			
			
			if (element.getPrixTotal() != null) {
				sheet3.addCell(new jxl.write.Number(8, i,convertisseur(element.getPrixTotal(), 4) , ExcelUtils.boldRed));
				mantantTtcTotale += element.getPrixTotal();
			} else {
				sheet3.addCell(new Label(8, i, "", ExcelUtils.boldRed));
			}
		
			
			i++;

		}

		i++;
		i++;

		sheet3.addCell(new Label(7, i, "Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i,  "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Montant TTC Total", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, convertisseur(mantantTtcTotale, 4) + "", ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Nbr Colis", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i,NbreColis+"" , ExcelUtils.boldRed2));

		i++;

		sheet3.addCell(new Label(7, i, "Quantite Totale", ExcelUtils.boldRed2));
		sheet3.mergeCells(7, i, 8, i);
		sheet3.addCell(new Label(9, i, quantiteTotale + "", ExcelUtils.boldRed2));
  
		i++;
		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"Number of lines : " + vInventaireReport.getElementsList().size(),
				ExcelUtils.boldRed5));
	
		numLigneBasDuPage++;

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
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

}