package com.gpro.consulting.tiers.logistique.rest.gs.report.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.BonMouvementStockReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatMouvementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockDetailleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockGlobalReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryDetailleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.service.gs.IGestionnaireGSCacheService;
import com.gpro.consulting.tiers.logistique.service.gs.report.IGestionnaireReportGsService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * GestionnaireReport Controller
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */

@Controller
@RequestMapping("/reportgs")
public class GestionnaireReportGsRestImpl extends AbstractGestionnaireDownloadImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGsRestImpl.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Autowired
	private IGestionnaireReportGsService gestionnaireReportGsService;
	
	@Autowired
	private IGestionnaireGSCacheService gestionnaireGSCacheService;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/mouvementStockHistory", method = RequestMethod.GET)
	public void generateMouvementStockHistoryReport(
			@RequestParam("historique") String historique,
			@RequestParam("articleType") String articleType,
			@RequestParam("ofId") Long ofId,
			@RequestParam("dateDu") String dateDu,
			@RequestParam("dateA") String dateA,
			@RequestParam("sousFamille") String sousFamille,
			@RequestParam("famille") String famille,
			@RequestParam("refArticle") String refArticle,
			@RequestParam("article") String article,
			
			
			
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report MouvementStockHistory",type);
		
		RechercheMulticritereMouvementValue critere = new RechercheMulticritereMouvementValue();
		critere.setHistorique(historique);
		critere.setType(articleType);
		critere.setOfId(ofId);
		critere.setDateA(stringToCalendar(dateA));
		critere.setDateDu(stringToCalendar(dateDu));
		critere.setFamille(famille);
		critere.setSousFamille(sousFamille);
		critere.setRefArticle(refArticle);
		critere.setArticle(article);
		
		
		
		
		MouvementStockHistoryReportValue report = gestionnaireReportGsService.getHistoryReport(critere);
		
		Map<Long, PartieInteresseCacheValue> mapClients = new HashMap<Long, PartieInteresseCacheValue>();
		Map<Long, MagasinValue> mapMagasins = new HashMap<Long, MagasinValue>();
		
		for(PartieInteresseCacheValue client : gestionnaireGSCacheService.getListeClient()){
			Long key = client.getId();
			if (mapClients.get(key) == null) {
				mapClients.put(key, client);
			}
		}
		
		for(MagasinValue magasin : gestionnaireGSCacheService.getListeMagasin()){
			Long key = magasin.getId();
			if (mapMagasins.get(key) == null) {
				mapMagasins.put(key, magasin);
			}
		}
		
		for(MouvementStockHistoryElementReportValue element : report.getElementsList()){
			
			if(element.getClientId() !=null){
				String client = (mapClients.containsKey(element.getClientId())) ? mapClients.get(element.getClientId()).getAbreviation() : null;
				element.setClient(client);
			}
			
			if(element.getMagasinId() != null){
				String designation = (mapMagasins.containsKey(element.getMagasinId())) ? mapMagasins.get(element.getMagasinId()).getDesignation() : null;
				element.setDesignationMagasin(designation);
			}
			
		}
		
		ArrayList<MouvementStockHistoryReportValue> dataList = new ArrayList<MouvementStockHistoryReportValue>();
		dataList.add(report);
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStock", method = RequestMethod.GET)
	public void generateetatStockReport(
			@RequestParam("articleType") String articleType,
			
			
			@RequestParam("familleArticle") String familleArticle,
			@RequestParam("article") String article,
			@RequestParam("magasin") String magasin,			
			@RequestParam("emplacement") String emplacement,
			@RequestParam("quantite") Double quantite,
			@RequestParam("dimensionArticle") String dimension,
			@RequestParam("grammageArticle") String grammage,
			@RequestParam("operateurQuantite") String operateurQuantite,
			@RequestParam("dateA") String dateA,
			@RequestParam("dateDu") String dateDu,
			@RequestParam("orderBy") String orderBy,
			
						
			
			@RequestParam("type") String type,
			@RequestParam("typeRapport") String typeRapport,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockHistory",type);
		
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		critere.setTypeArticle(articleType);
		
		critere.setArticle(article);
		
		critere.setFamilleArticle(familleArticle);
		
		critere.setMagasin(magasin);		
		
		critere.setEmplacement(emplacement);
		
		critere.setQuantite(quantite);
		critere.setDimensionArticle(dimension);
		critere.setGrammageArticle(grammage);
		critere.setDateA(stringToCalendar(dateA));
		critere.setDateDu(stringToCalendar(dateDu));
		critere.setOperateurQuantite(operateurQuantite);
		critere.setOrderBy(orderBy);
		
		EtatStockReportValue report = gestionnaireReportGsService.getEtatReport(critere, typeRapport);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStockNonMouvementes", method = RequestMethod.GET)
	public void generateEtatStockNonMouvementesReport(
			@RequestParam("articleType") String articleType,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockNonMouvementes",type);
		
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		critere.setTypeArticle(articleType);
		
		EtatStockReportValue report = gestionnaireReportGsService.getEtatNonMouvementesReport(critere);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatMouvement", method = RequestMethod.GET)
	public void generateEtatMouvementeReport(
			//@RequestParam("requestRechecheMouvementValue") RequestRechecheMouvementValue request,
			@RequestParam("id") Long id,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatMouvement",type);
		
		RequestRechecheMouvementValue request = new RequestRechecheMouvementValue();
		request.setArticleId(id);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		
		EtatMouvementReportValue report = gestionnaireReportGsService.getEtatMouvementReport(request);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/bonMouvementStockById", method = RequestMethod.GET)
	public void generateBonMouvementStockByIdReport(
			@RequestParam("id") Long id,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report for BonMouvementStock",type);
		
		BonMouvementStockReportValue report = gestionnaireReportGsService.getBonMouvementStockById(id);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/bonMouvementStockEntreeSortieById", method = RequestMethod.GET)
	public void generateBonMouvementStockEntreeSortieByIdReport(
			@RequestParam("id") Long id,
			@RequestParam("type") String type, 
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report for BonMouvementStock",type);
		
		BonMouvementStockReportValue report = gestionnaireReportGsService.getBonMouvementStockEntreeSortieById(id);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStockGlobal", method = RequestMethod.GET)
	public void generateetatStockGlobalReport(
		
			
        	@RequestParam("type") String type,		
			@RequestParam("articleType") String articleType,
	    	@RequestParam("familleArticle") String familleArticle,
			@RequestParam("article") String article,
			@RequestParam("magasin") String magasin,			
			@RequestParam("emplacement") String emplacement,
			@RequestParam("quantite") Double quantite,
			@RequestParam("dimensionArticle") String dimension,
			@RequestParam("grammageArticle") String grammage,
			@RequestParam("operateurQuantite") String operateurQuantite,
			@RequestParam("dateA") String dateA,
			@RequestParam("dateDu") String dateDu,
			
			
            @RequestParam("typeRapport") String typeRapport,
			
	
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockGlobal",type);
	//System.out.println("Generate a {} Report EtatStockGlobal rest layer");
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		critere.setTypeArticle(articleType);
		
		
	     critere.setArticle(article);
		
		critere.setFamilleArticle(familleArticle);
		
		critere.setMagasin(magasin);		
		
		critere.setEmplacement(emplacement);
		critere.setQuantite(quantite);
		critere.setDimensionArticle(dimension);
		critere.setGrammageArticle(grammage);
		critere.setDateA(stringToCalendar(dateA));
		critere.setDateDu(stringToCalendar(dateDu));
		critere.setOperateurQuantite(operateurQuantite);
		
		
		EtatStockGlobalReportValue report = gestionnaireReportGsService.getEtatGlobalReport(critere, typeRapport);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStockDetaille", method = RequestMethod.GET)
	public void generateetatStockDetailleReport(
			
			@RequestParam("type") String type,		
			@RequestParam("articleType") String articleType,
	    	@RequestParam("familleArticle") String familleArticle,
			@RequestParam("article") String article,
			@RequestParam("magasin") String magasin,			
			@RequestParam("emplacement") String emplacement,
			@RequestParam("quantite") Double quantite,
			@RequestParam("dimensionArticle") String dimension,
			@RequestParam("grammageArticle") String grammage,
			@RequestParam("operateurQuantite") String operateurQuantite,
			@RequestParam("dateA") String dateA,
			@RequestParam("dateDu") String dateDu,
			
            @RequestParam("typeRapport") String typeRapport,
            
            
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockGlobal",type);
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		critere.setTypeArticle(articleType);
		
		
	    critere.setArticle(article);
			
		critere.setFamilleArticle(familleArticle);
		
		critere.setMagasin(magasin);		
		
		critere.setEmplacement(emplacement);
		critere.setQuantite(quantite);
		critere.setDimensionArticle(dimension);
		critere.setGrammageArticle(grammage);
		critere.setDateA(stringToCalendar(dateA));
		critere.setDateDu(stringToCalendar(dateDu));
		critere.setOperateurQuantite(operateurQuantite);
		
		EtatStockDetailleReportValue report = gestionnaireReportGsService.getEtatDetailleReport(critere, typeRapport);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	//Added on 15/11/2016, by Zeineb
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/mouvementStockHistoryDetaille", method = RequestMethod.GET)
	public void generateMouvementStockHistoryReportDetaille(
			@RequestParam("historique") String historique,
			@RequestParam("articleType") String articleType,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report MouvementStockHistory",type);
		
		RechercheMulticritereMouvementValue critere = new RechercheMulticritereMouvementValue();
		critere.setHistorique(historique);
		critere.setType(articleType);
		
		MouvementStockHistoryDetailleReportValue report = gestionnaireReportGsService.getHistoryReportDetaille(critere);
		
		Map<Long, PartieInteresseCacheValue> mapClients = new HashMap<Long, PartieInteresseCacheValue>();
		Map<Long, MagasinValue> mapMagasins = new HashMap<Long, MagasinValue>();
		
		for(PartieInteresseCacheValue client : gestionnaireGSCacheService.getListeClient()){
			Long key = client.getId();
			if (mapClients.get(key) == null) {
				mapClients.put(key, client);
			}
		}
		
		for(MagasinValue magasin : gestionnaireGSCacheService.getListeMagasin()){
			Long key = magasin.getId();
			if (mapMagasins.get(key) == null) {
				mapMagasins.put(key, magasin);
			}
		}
		
		for(MouvementStockHistoryElementReportValue element : report.getElementsList()){
			
			if(element.getClientId() !=null){
				String client = (mapClients.containsKey(element.getClientId())) ? mapClients.get(element.getClientId()).getAbreviation() : null;
				element.setClient(client);
			}
			
			if(element.getMagasinId() != null){
				String designation = (mapMagasins.containsKey(element.getMagasinId())) ? mapMagasins.get(element.getMagasinId()).getDesignation() : null;
				element.setDesignationMagasin(designation);
			}
			
		}
		
		ArrayList<MouvementStockHistoryDetailleReportValue> dataList = new ArrayList<MouvementStockHistoryDetailleReportValue>();
		dataList.add(report);
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/listEtatStockBarCode", method = RequestMethod.GET)
	public void generateListEtatStockBarCodeReport(
			
			
			
			@RequestParam("typeArticle") String typeArticle,

			@RequestParam("familleArticle") String familleArticle,
			
			@RequestParam("article") String article,
			
			@RequestParam("magasin") String magasin,
			
			@RequestParam("numeroBonEntree") String numeroBonEntree,
			@RequestParam("quantite") Double quantite,
			@RequestParam("dimensionArticle") String dimension,
			@RequestParam("grammageArticle") String grammage,
			@RequestParam("dateA") String dateA,
			@RequestParam("dateDu") String dateDu,
			@RequestParam("operateurQuantite") String operateurQuantite,
			
			@RequestParam("type") String type,
	
			
			
			
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report Etat Stock  Bar Code List",type);
		
		RechercheMulticritereEntiteStockValue request = new RechercheMulticritereEntiteStockValue();
		
		request.setTypeArticle(typeArticle);
		request.setFamilleArticle(familleArticle);
		request.setArticle(article);
		request.setMagasin(magasin);
		request.setNumeroBonEntree(numeroBonEntree);
		request.setQuantite(quantite);
		request.setDimensionArticle(dimension);
		request.setGrammageArticle(grammage);
		request.setDateA(stringToCalendar(dateA));
		request.setDateDu(stringToCalendar(dateDu));
		request.setOperateurQuantite(operateurQuantite);
		
		
    	FicheColisReportValue report = gestionnaireReportGsService.generateListEtatStockBarCodeReport(request);
		
	
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	
	
	
	
	@RequestMapping(value="/listEtatStockBarCodeFromBE", method = RequestMethod.GET)
	public void generateListEtatStockBarCodeFromBEReport(
			
			
			
			@RequestParam("id") Long id,


			@RequestParam("type") String type,
	
			
			
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report Etat Stock  Bar Code List From BE",type);
		
		RechercheMulticritereEntiteStockValue request = new RechercheMulticritereEntiteStockValue();
		
	
		
		
		
    	FicheColisReportValue report = gestionnaireReportGsService.generateListEtatStockBarCodeFromBEReport(id);
		
	
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	
	
	
	private Calendar stringToCalendar(String dateString) {
		
		Calendar dateCalendar = null;
		
		if(isNotEmty(dateString)){
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));
				
			} catch (ParseException e) {
				logger.error("parse date exception: "+e.getMessage());
			}
		}
		
		return dateCalendar;
	}
	
	  private boolean isNotEmty(String value) {
		    return value != null && !"".equals(value);

		  }
}
