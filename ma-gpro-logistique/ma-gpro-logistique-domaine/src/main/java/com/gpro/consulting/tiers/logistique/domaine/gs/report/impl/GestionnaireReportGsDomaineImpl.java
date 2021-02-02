package com.gpro.consulting.tiers.logistique.domaine.gs.report.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.report.value.ColisValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.BonMouvementStockReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatGlobalMapValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatMouvementElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatMouvementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockDetailleElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockDetailleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockGlobalElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockGlobalReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatStockReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryDetailleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryElementDetailleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryElementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockHistoryReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.MouvementStockReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonMouvementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.report.IGestionnaireReportGsDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.report.utilities.EtatMouvementElementReportComparator;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IMisePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IEntiteStockPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMagasinPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMouvementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IRaisonMouvementPersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementation of {@link IGestionnaireReportGsDomaine}
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */

@Component
public class GestionnaireReportGsDomaineImpl implements IGestionnaireReportGsDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGsDomaineImpl.class);
	
	private static final String REPORT_NAME_STOCKHISTORY = "mouvement stock history";
	private static final String REPORT_NAME_ETATSTOCK = "etat stock";
	private static final String REPORT_NAME_ETATMOUVEMENT = "etat mouvement";
	private static final String REPORT_NAME_ETATNONMOUVEMENT = "etat non mouvemente";
	private static final String REPORT_NAME_BON_MOUVENELENT = "bon de mouvement OF";
	private static final String REPORT_NAME_BON_MVT_STOK = "bon de mouvement Stock";
	
	private static final String ARTICLE_TYPE_1 = "1";	//fourniture
	private static final String ARTICLE_TYPE_2 = "2";	//tissu
	private static final String ARTICLE_TYPE_3 = "3";	//fileacoudre

	private static final Long ARTICLETYPE_1 = 1L;	//fourniture
	private static final Long ARTICLETYPE_2 = 2L;	//tissu
	private static final Long ARTICLETYPE_3 = 3L;	//fileacoudre
	
	private static final String ENTREE = "ENTREE";
	private static final String SORTIE = "SORTIE";
	
	
	
	@Autowired
	private IMouvementPersistance mouvementPersistance;
	
	@Autowired
	private IEntiteStockPersistance entiteStockPersistance;
	
	@Autowired
	private IArticlePersistance articlePersistance;
	
	@Autowired
	private IBonMouvementDomaine bonMouvementDomaine;
	
//	@Autowired
//	private IOrdreFabricationPersistance ordreFabricationPersistance;
	
	@Autowired
	private IProduitPersistance produitPersistance;
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	IMagasinPersistance magasinPersistance;
	
	@Autowired
	IRaisonMouvementPersistance raisonMouvementPersistance;
	
	
	@Autowired
    private IMisePersistance misePersistance;

	@Override
	public MouvementStockHistoryReportValue getHistoryReport(RechercheMulticritereMouvementValue critere) throws IOException {
		
		logger.info("Get Mouvement Stock History Report");
		
		MouvementStockHistoryReportValue report = new MouvementStockHistoryReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_STOCKHISTORY);
		report.setReportStream(new FileInputStream("C://ERP/Lib/MouvementStock/mouvement_stock_report.jrxml"));
				
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		
		if(critere != null){
			if(critere.getType() != null){
				
				switch (critere.getType()) {
					case ARTICLE_TYPE_1:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/MouvementStock/Basique/mouvement_stock_fourniture_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_2:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/MouvementStock/Basique/mouvement_stock_tissu_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_3:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/MouvementStock/Basique/mouvement_stock_fileacoudre_sub_report.jasper");
						break;
				}
				
			}
		}
		
		report.setParams(params);
		
		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherMouvementMultiCritere(critere);
		
		report.setHistorique(critere.getHistorique());
		report.setType(critere.getType());
		
		if(result != null){
			enrichmentHistoryReportReport(report, result);
			
		}
		
		ArrayList<MouvementStockHistoryReportValue> dataList = new ArrayList<MouvementStockHistoryReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}

	private void enrichmentHistoryReportReport(
			MouvementStockHistoryReportValue report,
			ResultatRechecheMouvementValue result) {
		
		Set<MouvementStockHistoryElementReportValue> elementsList = report.getElementsList();
		
		report.setTypeRapport("Rapport Basique");
		
		for(MouvementStockValue mouvementStock: result.getMouvementStock()){
			
			MouvementStockHistoryElementReportValue elementReport = new MouvementStockHistoryElementReportValue();
			
			if(mouvementStock.getBonMouvement() != null){
				elementReport.setNumero(mouvementStock.getBonMouvement().getNumero());
				elementReport.setClientId(mouvementStock.getBonMouvement().getPartieintId());
				elementReport.setDate(mouvementStock.getBonMouvement().getDate());
				elementReport.setResponsable(mouvementStock.getBonMouvement().getResponsable());
				elementReport.setValeur(mouvementStock.getBonMouvement().getValeur());
				
				
				if(mouvementStock.getBonMouvement().getOfId() != null)
				elementReport.setNumeroOF(misePersistance.rechercheMiseParId(mouvementStock.getBonMouvement().getOfId()).getReference());
				
				
				
			}
			
			elementReport.setId(mouvementStock.getId());
			elementReport.setReferenceArticle(mouvementStock.getReferenceArticle());
			elementReport.setDesignationArticle(mouvementStock.getDesignationArticle());
			elementReport.setFamilleArticle(mouvementStock.getFamilleArticle());
			elementReport.setQuantiteReelle(mouvementStock.getQuantiteReelle());
			elementReport.setPoidsReel(mouvementStock.getPoidsReel());
			elementReport.setPrixUnitaire(mouvementStock.getPrixUnitaire());
			elementReport.setMagasinId(mouvementStock.getIdMagasin());
			elementReport.setEmplacement(mouvementStock.getEmplacement());
			elementReport.setNbRouleauxReel(mouvementStock.getNbRouleauxReel());
			
			elementsList.add(elementReport);
		}
		
		report.setElementsList(elementsList);
		
	}

	
	@Override
	public MouvementStockHistoryDetailleReportValue getHistoryReportDetaille(RechercheMulticritereMouvementValue critere) throws IOException {
		
		logger.info("Get Mouvement Stock History Report");
		
		MouvementStockHistoryDetailleReportValue report = new MouvementStockHistoryDetailleReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_STOCKHISTORY);
		report.setReportStream(new FileInputStream("C://ERP/Lib/MouvementStock/mouvement_stock_report.jrxml"));
				
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		
		if(critere != null){
			if(critere.getType() != null){
				
				switch (critere.getType()) {
					case ARTICLE_TYPE_1:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/MouvementStock/Detaille/mouvement_stock_fourniture_sub_detaille_report.jasper");
						break;
						
					case ARTICLE_TYPE_2:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/MouvementStock/Detaille/mouvement_stock_tissu_sub_detaille_report.jasper");
						break;
						
					case ARTICLE_TYPE_3:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/MouvementStock/Detaille/mouvement_stock_fileacoudre_sub_detaille_report.jasper");
						break;
				}
				
			}
		}
		
		report.setParams(params);
		
		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherMouvementMultiCritere(critere);
		
		report.setHistorique(critere.getHistorique());
		report.setType(critere.getType());
		
		if(result != null){
			enrichmentHistoryReportDetaille(report, result);
			
		}
		
		ArrayList<MouvementStockHistoryDetailleReportValue> dataList = new ArrayList<MouvementStockHistoryDetailleReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}
	
	
	private void enrichmentHistoryReportDetaille(MouvementStockHistoryDetailleReportValue report,
			ResultatRechecheMouvementValue result) {
		
		Set<MouvementStockHistoryElementDetailleReportValue> elementsList = report.getElementsList();
		
		report.setTypeRapport("Rapport Détaillée");
		
		for(MouvementStockValue mouvementStock: result.getMouvementStock()){
			
			MouvementStockHistoryElementDetailleReportValue elementReport = new MouvementStockHistoryElementDetailleReportValue();
			
			if(mouvementStock.getBonMouvement() != null){
				elementReport.setNumero(mouvementStock.getBonMouvement().getNumero());
				elementReport.setClientId(mouvementStock.getBonMouvement().getPartieintId());
				elementReport.setDate(mouvementStock.getBonMouvement().getDate());
				elementReport.setResponsable(mouvementStock.getBonMouvement().getResponsable());
				elementReport.setValeur(mouvementStock.getBonMouvement().getValeur());
			}
			
			elementReport.setId(mouvementStock.getId());
			elementReport.setReferenceArticle(mouvementStock.getReferenceArticle());
			elementReport.setDesignationArticle(mouvementStock.getDesignationArticle());
			elementReport.setFamilleArticle(mouvementStock.getFamilleArticle());
			elementReport.setQuantiteReelle(mouvementStock.getQuantiteReelle());
			elementReport.setPoidsReel(mouvementStock.getPoidsReel());
			elementReport.setPrixUnitaire(mouvementStock.getPrixUnitaire());
			elementReport.setMagasinId(mouvementStock.getIdMagasin());
			elementReport.setEmplacement(mouvementStock.getEmplacement());
			elementReport.setNbRouleauxReel(mouvementStock.getNbRouleauxReel());
			
			elementReport.setSousFamille(mouvementStock.getSousFamilleArticle());
			elementReport.setRefLot(mouvementStock.getLot());
			
			if(mouvementStock.getIdArticle()!=null){
				ArticleValue article = articlePersistance.getArticleParId(mouvementStock.getIdArticle());
//				
//				if(article != null){
//					elementReport.setCouleurArticle(article.getCouleur());
//					elementReport.setCodeFournisseur(article.getCodeFournisseur());
//					elementReport.setProducteur(article.getProducteur());
//				}
//				
			}
			
			
			elementsList.add(elementReport);
		}
	}

	@Override
	public EtatStockReportValue getEtatReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException {
		
		logger.info("Get Etat Stock Report");
		
		EtatStockReportValue report = new EtatStockReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATSTOCK);
		report.setReportStream(new FileInputStream("C://ERP/Lib/EtatStock/etat_stock_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PATH_LOGO", "/report/logo_enfavet.jpg");
		
		if(critere != null){
			if(critere.getTypeArticle() != null){
				
				switch (critere.getTypeArticle()) {
					case ARTICLE_TYPE_1:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatStock/Basique/etat_stock_fourniture_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_2:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatStock/Basique/etat_stock_tissu_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_3:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatStock/Basique/etat_stock_fileacoudre_sub_report.jasper");
						break;
				}
				
			}
		}
		
		report.setParams(params);
		report.setType(critere.getTypeArticle());
		report.setTypeRapport(typeRapport);
		
		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(critere);
		
		if(result != null){
			enrichmentEtatReport(report, result);
		}
		
		ArrayList<EtatStockReportValue> dataList = new ArrayList<EtatStockReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}

	@Override
	public EtatStockReportValue getEtatNonMouvementesReport(RechercheMulticritereEntiteStockValue critere) throws IOException {
		
		logger.info("Get Etat Stock non mouvementes Report");
		
		EtatStockReportValue report = new EtatStockReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATNONMOUVEMENT);
		report.setReportStream(new FileInputStream("C://ERP/Lib/EtatNonMouvemente/etat_stock_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if(critere != null){
			
			critere.setMouvement(IConstante.NO);
			
			if(critere.getTypeArticle() != null){
				
				switch (critere.getTypeArticle()) {
					case ARTICLE_TYPE_1:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatNonMouvemente/etat_fourniture_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_2:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatNonMouvemente/etat_tissu_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_3:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatNonMouvemente/etat_fileacoudre_sub_report.jasper");
						break;
				}
				
			}
		}
		
		report.setParams(params);
		
		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(critere);
		
		if(result != null){
			enrichmentEtatReport(report, result);
		}
		
		ArrayList<EtatStockReportValue> dataList = new ArrayList<EtatStockReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}

	private void enrichmentEtatReport(EtatStockReportValue report, ResultatRechecheEntiteStockStockValue result) {
		
		Set<EtatStockElementReportValue> elementsList = report.getElementsList();
		
		for(EntiteStockValue entiteStock: result.getEntiteStock()){
			
			EtatStockElementReportValue elementReport = new EtatStockElementReportValue();
			
			elementReport.setId(entiteStock.getId());
			elementReport.setDesignationMagasin(entiteStock.getDesignationMagasin());
			elementReport.setEmplacement(entiteStock.getEmplacement());
			elementReport.setFamilleArticle(entiteStock.getFamilleArticle());
			elementReport.setFinconeActuel(entiteStock.getFinconeActuel());
			elementReport.setLibelleArticle(entiteStock.getLibelleArticle());
			elementReport.setOA(entiteStock.getOA());
			elementReport.setPmp(entiteStock.getPmp());
			elementReport.setPrixTotal(entiteStock.getPrixTotal());
			elementReport.setPrixUnitaire(entiteStock.getPrixUnitaire());
			elementReport.setQteActuelle(entiteStock.getQteActuelle());
			elementReport.setReferenceArticle(entiteStock.getReferenceArticle());
			
			elementsList.add(elementReport);
		}
		
		report.setElementsList(elementsList);
		
	}

	@Override
	public EtatMouvementReportValue getEtatMouvementReport(RequestRechecheMouvementValue request) throws IOException {
		
		logger.info("Get EtatMouvement Report");
		
		EtatMouvementReportValue report = new EtatMouvementReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATMOUVEMENT);
		report.setReportStream(new FileInputStream("C://ERP/Lib/EtatMouvement/etat_mouvement_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ETATMOUVEMENT_PATH", "C://ERP/Lib/EtatMouvement/etat_mouvement_sub_report.jasper");
		report.setParams(params);

		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherEtatMouvement(request);
		
		if(request.getArticleId() != null){
			ArticleValue article = articlePersistance.getArticleParId(request.getArticleId());
			
			if(article != null){
				report.setDesignationArticle(article.getDesignation());
				report.setReferenceArticle(article.getRef());
			}
		}
		
		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());
		
		if(result != null){
			enrichmentEtatMouvementReport(report, result);
		}
		
		Collections.sort(report.getElementsList(), new EtatMouvementElementReportComparator());
		
		ArrayList<EtatMouvementReportValue> dataList = new ArrayList<EtatMouvementReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentEtatMouvementReport(EtatMouvementReportValue report,
			ResultatRechecheMouvementValue result) {
		
		List<EtatMouvementElementReportValue> elementsList = report.getElementsList();
		
		for(MouvementStockValue entiteStock: result.getMouvementStock()){
			
			EtatMouvementElementReportValue elementReport = new EtatMouvementElementReportValue();
			
			elementReport.setId(entiteStock.getId());
			elementReport.setQuantiteReelle(entiteStock.getQuantiteReelle());
			elementReport.setTypeEntree(entiteStock.getType());
			
			if(entiteStock.getBonMouvement() != null){
				elementReport.setBonMouvementStockDate(entiteStock.getBonMouvement().getDate());
				elementReport.setBonMouvementStockNumero(entiteStock.getBonMouvement().getNumero());
				elementReport.setBonMouvementStockRaison(entiteStock.getBonMouvement().getRaisonMouvementDesignation());
			}
			
			elementsList.add(elementReport);
		}
		
		report.setElementsList(elementsList);
		
	}
//MvtOF
	@Override
	public BonMouvementStockReportValue getBonMouvementStockById(Long id)
			throws IOException {
		
		logger.info("Get BonMouvement Report");
		
		BonMouvementStockReportValue report = new BonMouvementStockReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_BON_MOUVENELENT);
		report.setReportStream(new FileInputStream("C://ERP/Lib/BonMouvementStock/bon_mouvement_stock_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("SUBREPORT_BONMOUVEMENT_1_PATH", "C://ERP/Lib/BonMouvementStock/Reservation/bon_mouvement_stock_fourniture_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_2_PATH", "C://ERP/Lib/BonMouvementStock/Reservation/bon_mouvement_stock_tissu_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_3_PATH", "C://ERP/Lib/BonMouvementStock/Reservation/bon_mouvement_stock_fileacoudre_sub_report.jasper");
		
		params.put("SUBREPORT_BONMOUVEMENT_1_SORTIE_PATH", "C://ERP/Lib/BonMouvementStock/Sortie/bon_mouvement_stock_fourniture_sortie_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_2_SORTIE_PATH", "C://ERP/Lib/BonMouvementStock/Sortie/bon_mouvement_stock_tissu_sortie_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_3_SORTIE_PATH", "C://ERP/Lib/BonMouvementStock/Sortie/bon_mouvement_stock_fileacoudre_sortie_sub_report.jasper");
		
		report.setParams(params);
		
		BonMouvementStockValue bonMouvementStockValue = bonMouvementDomaine.rechercheBonMouvementParId(id);
		
		if(bonMouvementStockValue != null){
			
			enrichmentBonMouvementStockReport(report, bonMouvementStockValue);
			
		}
		
		ArrayList<BonMouvementStockReportValue> dataList = new ArrayList<BonMouvementStockReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}
//Enrichir MvtOF
	private void enrichmentBonMouvementStockReport(
			BonMouvementStockReportValue report,
			BonMouvementStockValue value) {
		
		report.setNumero(value.getNumero());
		report.setDate(value.getDate());
		report.setResponsable(value.getResponsable());
		
		report.setRaisonMouvementId(value.getRaisonMouvementId());
		report.setRaisonMouvementDesignation(value.getRaisonMouvementDesignation());
		
		report.setValeur(value.getValeur());
		report.setDescription(value.getDescription());
		report.setType(value.getType());
		report.setOfId(value.getOfId());
		
		
		
//		if(value.getOfId() != null){
//			
//			OrdreFabricationValue ordreFabrication = ordreFabricationPersistance.rechercheOrdreFabricationParId(value.getOfId());
//			
//			if(ordreFabrication != null){
//				
//				report.setOfNumero(ordreFabrication.getNumero());
//				
//				if(ordreFabrication.getProduitId() != null){
//					
//					ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());
//					
//					if(produit != null){
//						
//						report.setProduitReference(produit.getReference());
//						report.setProduitDesignation(produit.getDesignation());
//					}
//				}
//				
//				if(ordreFabrication.getPartieInterresId() != null){
//					
//					PartieInteresseValue client = partieInteresseePersistance.getById(ordreFabrication.getPartieInterresId());
//					
//					if(client != null){
//						
//						report.setClientAbreviation(client.getAbreviation());
//					}
//				}
//			}
//		}
		
		
		for(MouvementStockValue mouvementStockValue : value.getMouvements()){
			
			MouvementStockReportValue mouvementStockReport = new MouvementStockReportValue();
			
			mouvementStockReport.setId(mouvementStockValue.getId());
			mouvementStockReport.setReferenceArticle(mouvementStockValue.getReferenceArticle());
			mouvementStockReport.setDesignationArticle(mouvementStockValue.getDesignationArticle());
			mouvementStockReport.setFamilleArticle(mouvementStockValue.getFamilleArticle());
			
			if(mouvementStockValue.getIdMagasin() != null){
				MagasinValue vMagasin= new MagasinValue();
				vMagasin.setId(mouvementStockValue.getIdMagasin());
				MagasinValue magasin = magasinPersistance.rechercheMagasinParId(vMagasin);
				
	
				report.setMagasinDesignation(magasin.getDesignation());
			}
			
			mouvementStockReport.setQuantiteOF(mouvementStockValue.getQteOF());
			mouvementStockReport.setBesoinOF(mouvementStockValue.getBesoinOF());
			mouvementStockReport.setEmplacement(mouvementStockValue.getEmplacement());
			
			if(mouvementStockValue.getBesoinOF()!= null & mouvementStockValue.getQteOF()!=null){
				mouvementStockReport.setBu(mouvementStockValue.getBesoinOF()/mouvementStockValue.getQteOF());
			}
			
			mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
			mouvementStockReport.setReservationOF(mouvementStockValue.getReservationOF());
			mouvementStockReport.setNbRouleauxReel(mouvementStockValue.getNbRouleauxReel());
			
			logger.info("----- type rapport -----"+ value.getType());
			
			
				
		if(value.getType().equals("RESERVATION")){
				
				if(mouvementStockValue.getTypeArticle() != null){
					
					if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)){
						
						report.getElementsList1().add(mouvementStockReport);
						
					}
					if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)){
						
						
						report.getElementsList2().add(mouvementStockReport);
					}
					if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)){
						
						
						report.getElementsList3().add(mouvementStockReport);
					}
					
					if(mouvementStockValue.getBesoinOF()!= null & mouvementStockValue.getQteOF()!=null){
						mouvementStockReport.setBu(mouvementStockValue.getBesoinOF()/mouvementStockValue.getQteOF());
					}
				}
				
		}
		else if ( value.getType().equals("SORTIE")){
			
			report.setRefReservation(value.getNumBRSortie());
			
			BonMouvementStockValue bonMvtReservation = bonMouvementDomaine.rechercheBonMouvementParNum(value.getNumBRSortie());
			report.setDateReservation(bonMvtReservation.getDate());
			
			logger.info("mouvementStockValue-------------" + mouvementStockValue.toString());
					
				if(mouvementStockValue.getTypeArticle() != null){
					
					if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)){
						
						logger.info("fourniture--------------");
						
						report.getElementsList1Sortie().add(mouvementStockReport);
						
					}
					if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)){
						
						
						report.getElementsList2Sortie().add(mouvementStockReport);
					}
					if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)){
						
						
						report.getElementsList3Sortie().add(mouvementStockReport);
					}
					
				}
				
			}
		}
	
		logger.info("report.getElementsList1Sortie().length----"+ report.getElementsList1Sortie().size());
	}
		
//MvtStock	
	public BonMouvementStockReportValue getBonMouvementStockEntreeSortieById(Long id)
			throws IOException {
		
		BonMouvementStockReportValue report = new BonMouvementStockReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_BON_MVT_STOK);
		
		BonMouvementStockValue bonMouvementStockValue = bonMouvementDomaine.rechercheBonMouvementParId(id);
		logger.info("----------- ***TYPE -----------" + bonMouvementStockValue.getType());
		switch (bonMouvementStockValue.getType()) {
	     case ENTREE:
	    	 logger.info("----------- ENTREE** -----------");
	    	 HashMap<String, Object> params = new HashMap<String, Object>();
			report.setReportStream(new FileInputStream("C://ERP/Lib/BonMvtStock/Entree/getById/bon_mouvement_stock_entree_report.jrxml"));
			
			params.put("SUBREPORT_Mvt_STOCK_ENTREE_1_PATH", "C://ERP/Lib/BonMvtStock/Entree/getById/bon_mouvement_stock_entree_fourniture_sub_report.jasper");
			params.put("SUBREPORT_Mvt_STOCK_ENTREE_2_PATH", "C://ERP/Lib/BonMvtStock/Entree/getById/bon_mouvement_stock_entree_tissu_sub_report.jasper");
			params.put("SUBREPORT_Mvt_STOCK_ENTREE_3_PATH", "C://ERP/Lib/BonMvtStock/Entree/getById/bon_mouvement_stock_entree_fileacoudre_sub_report.jasper");
			
			report.setParams(params);
			if(bonMouvementStockValue != null){
				
				enrichirBonMouvementStockEntreeReport(report, bonMouvementStockValue);
			}
			
			ArrayList<BonMouvementStockReportValue> dataList = new ArrayList<BonMouvementStockReportValue>();
			dataList.add(report);
	   
			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
			
			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
	    	break;
	     case SORTIE:
				
				logger.info("----------- SORTIE -----------");
				HashMap<String, Object> paramsSortie = new HashMap<String, Object>();
				report.setReportStream(new FileInputStream("C://ERP/Lib/BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_report.jrxml"));
				
				paramsSortie.put("SUBREPORT_Mvt_STOCK_SORTIE_1_PATH", "C://ERP/Lib/BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_fourniture_sub_report.jasper");
				paramsSortie.put("SUBREPORT_Mvt_STOCK_SORTIE_2_PATH", "C://ERP/Lib/BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_tissu_sub_report.jasper");
				paramsSortie.put("SUBREPORT_Mvt_STOCK_SORTIE_3_PATH", "C://ERP/Lib/BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_fileacoudre_sub_report.jasper");
				
				report.setParams(paramsSortie);
				if(bonMouvementStockValue != null){
					
					enrichirBonMouvementStockSortieReport(report, bonMouvementStockValue);
				}
				
				ArrayList<BonMouvementStockReportValue> dataListSortie = new ArrayList<BonMouvementStockReportValue>();
				dataListSortie.add(report);
		   
				JRBeanCollectionDataSource jRBeanCollectionDataSourceSortie = new JRBeanCollectionDataSource(dataListSortie);
				
				report.setJRBeanCollectionDataSource(jRBeanCollectionDataSourceSortie);
			break;
		}
		
		return report;
		
	}
	
//Enrichir MvtStock Entrée
	private void enrichirBonMouvementStockEntreeReport(
			BonMouvementStockReportValue report,
			BonMouvementStockValue value) {
		
		logger.info("----------- SubReport :ENTREE -----------");
		report.setNumero(value.getNumero());
		report.setDate(value.getDate());
		report.setResponsable(value.getResponsable());
		report.setRaisonMouvementId(value.getRaisonMouvementId());
		
		//RaisonId > RaisonDesignation
		if(value.getRaisonMouvementId() != null){
			RaisonMouvementStockValue pRaisonMouvementStockValue = new RaisonMouvementStockValue();
			pRaisonMouvementStockValue.setId(value.getRaisonMouvementId());
			RaisonMouvementStockValue vRaison = raisonMouvementPersistance.rechercheRaisonMouvementStockParId(pRaisonMouvementStockValue);
			if(vRaison != null)
			report.setRaisonMouvementDesignation(vRaison.getDesignation());
		}
		
		if(value.getPartieintId() != null){
			PartieInteresseValue pPIValue = partieInteresseePersistance.getPartieInteresseById(value.getPartieintId());
			if(pPIValue != null)
			report.setClientAbreviation(pPIValue.getAbreviation());
		}
		
		report.setValeur(value.getValeur());
		report.setDescription(value.getDescription());
		for(MouvementStockValue mouvementStockValue : value.getMouvements()){
			
			MouvementStockReportValue mouvementStockReport = new MouvementStockReportValue();
			
			mouvementStockReport.setId(mouvementStockValue.getId());
			mouvementStockReport.setReferenceArticle(mouvementStockValue.getReferenceArticle());
			mouvementStockReport.setDesignationArticle(mouvementStockValue.getDesignationArticle());
			mouvementStockReport.setFamilleArticle(mouvementStockValue.getFamilleArticle());
			mouvementStockReport.setQuantiteAct(mouvementStockValue.getQuantite());
			mouvementStockReport.setPrixUnitaire(mouvementStockValue.getPrixUnitaire());
			mouvementStockReport.setEmplacement(mouvementStockValue.getEmplacement());
			
			if(mouvementStockValue.getTypeArticle() != null){
				
				if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)){
					
					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					report.getElementsList1().add(mouvementStockReport);
					
				}
				if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)){
					
					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					mouvementStockReport.setNbRouleauxReel(mouvementStockValue.getNbRouleauxReel());
					
					report.getElementsList2().add(mouvementStockReport);
				}
				if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)){
					mouvementStockReport.setConesReel(mouvementStockValue.getConesReel());
					mouvementStockReport.setFinconesReel(mouvementStockValue.getFinconesReel());
					mouvementStockReport.setPoids(mouvementStockValue.getPoids());
					report.getElementsList3().add(mouvementStockReport);
				}
				
			}
			
			
		}
	
	
	}
	
//Enrichir MvtStock Sortie
	private void enrichirBonMouvementStockSortieReport(
			BonMouvementStockReportValue report,
			BonMouvementStockValue value) {
		logger.info("----------- SubReport :SORTIE -----------");
		report.setNumero(value.getNumero());
		report.setDate(value.getDate());
		report.setResponsable(value.getResponsable());
		report.setRaisonMouvementId(value.getRaisonMouvementId());
		
		//RaisonId > RaisonDesignation
		if(value.getRaisonMouvementId() != null){
			RaisonMouvementStockValue pRaisonMouvementStockValue = new RaisonMouvementStockValue();
			pRaisonMouvementStockValue.setId(value.getRaisonMouvementId());
			RaisonMouvementStockValue vRaison = raisonMouvementPersistance.rechercheRaisonMouvementStockParId(pRaisonMouvementStockValue);
			if(vRaison != null)
			report.setRaisonMouvementDesignation(vRaison.getDesignation());
		}
		
		if(value.getPartieintId() != null){
			PartieInteresseValue pPIValue = partieInteresseePersistance.getPartieInteresseById(value.getPartieintId());
			if(pPIValue != null)
			report.setClientAbreviation(pPIValue.getAbreviation());
		}
		
		report.setValeur(value.getValeur());
		report.setDescription(value.getDescription());
		
		for(MouvementStockValue mouvementStockValue : value.getMouvements()){
			
			MouvementStockReportValue mouvementStockReport = new MouvementStockReportValue();
			
			mouvementStockReport.setId(mouvementStockValue.getId());
			mouvementStockReport.setReferenceArticle(mouvementStockValue.getReferenceArticle());
			mouvementStockReport.setDesignationArticle(mouvementStockValue.getDesignationArticle());
			mouvementStockReport.setFamilleArticle(mouvementStockValue.getFamilleArticle());
			mouvementStockReport.setQuantiteAct(mouvementStockValue.getQuantite());
			mouvementStockReport.setPrixUnitaire(mouvementStockValue.getPrixUnitaire());
			mouvementStockReport.setEmplacement(mouvementStockValue.getEmplacement());
			
			if(mouvementStockValue.getTypeArticle() != null){
				
				if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)){
					
					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					report.getElementsList1().add(mouvementStockReport);
					
				}
				if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)){
					
					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					mouvementStockReport.setNbRouleauxReel(mouvementStockValue.getNbRouleauxReel());
					
					report.getElementsList2().add(mouvementStockReport);
				}
				if(mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)){
					mouvementStockReport.setConesReel(mouvementStockValue.getConesReel());
					mouvementStockReport.setFinconesReel(mouvementStockValue.getFinconesReel());
					mouvementStockReport.setPoids(mouvementStockValue.getPoids());
					report.getElementsList3().add(mouvementStockReport);
				}
				
			}
			
			
		}
	
	}

	@Override
	public EtatStockGlobalReportValue getEtatGlobalReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException {

		logger.info("Get Etat Stock Global Report");
		System.out.println("Generate a {} Report EtatStockGlobal domaine layer");
		EtatStockGlobalReportValue report = new EtatStockGlobalReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATSTOCK);
		report.setReportStream(new FileInputStream("C://ERP/Lib/EtatStock/etat_stock_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PATH_LOGO", "/report/logo_enfavet.jpg");
		
		if(critere != null){
			if(critere.getTypeArticle() != null){
				
				switch (critere.getTypeArticle()) {
					case ARTICLE_TYPE_1:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatStock/Global/etat_stock_fourniture_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_2:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatStock/Global/etat_stock_tissu_sub_report.jasper");
						break;
				}
				
			}
		}
		
		report.setParams(params);
		report.setType(critere.getTypeArticle());
		report.setTypeRapport(typeRapport);
		
		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(critere);
		
		if(result != null){
			enrichissementEtatGlobalReport(report, result);
		}
		
		
		ArrayList<EtatStockGlobalReportValue> dataList = new ArrayList<EtatStockGlobalReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}

	private void enrichissementEtatGlobalReport1(EtatStockGlobalReportValue report, ResultatRechecheEntiteStockStockValue result) {
		
		Set<EtatStockGlobalElementReportValue> elementsList = report.getElementsList();
		
		for(EntiteStockValue entiteStock: result.getEntiteStock()){
			
			ArticleValue article = articlePersistance.getArticleParId(entiteStock.getArticle());
			
			EtatStockGlobalElementReportValue elementReport = new EtatStockGlobalElementReportValue();
			
			elementReport.setId(entiteStock.getId());
			elementReport.setDesignationMagasin(entiteStock.getDesignationMagasin());
			elementReport.setEmplacement(entiteStock.getEmplacement());
			elementReport.setFamilleArticle(entiteStock.getFamilleArticle());
			elementReport.setLibelleArticle(entiteStock.getLibelleArticle());
			elementReport.setPmp(entiteStock.getPmp());
			elementReport.setPrixTotal(entiteStock.getPrixTotal());
			elementReport.setPrixUnitaire(entiteStock.getPrixUnitaire());
			elementReport.setQteActuelle(entiteStock.getQteActuelle());
			elementReport.setReferenceArticle(entiteStock.getReferenceArticle());
			elementReport.setQteReservee(entiteStock.getQteReservee());
			elementReport.setRouleauActuel(entiteStock.getRouleauxActuel());
			elementReport.setRouleauReserve(entiteStock.getRouleauxReserve());
			
//			elementReport.setCouleur(article.getCouleur());
//			elementReport.setCodeFournisseur(article.getCodeFournisseur());
			elementReport.setProducteur(article.getProducteur());
			elementReport.setSousFamilleArticle(article.getSousFamilleArtEntiteDesignation());
			
			elementsList.add(elementReport);
		}
		
		report.setElementsList(elementsList);
		
	}
	
	
	  private void enrichissementEtatGlobalReport(EtatStockGlobalReportValue report, ResultatRechecheEntiteStockStockValue result)
	  {
	    Set<EtatStockGlobalElementReportValue> elementsList = report.getElementsList();
	    
	    Map<Long, EtatGlobalMapValue> mapByArticle = new HashMap();
	    for (EntiteStockValue entiteStock : result.getEntiteStock())
	    {
	      Double qteActuelleTotal = Double.valueOf(0.0D);
	      Double qteReserveeTotal = Double.valueOf(0.0D);
	      Long nbRouleauxActuelsTotal = Long.valueOf(0L);
	      Long nbRouleauxReservesTotal = Long.valueOf(0L);
	      
	      EtatGlobalMapValue values = new EtatGlobalMapValue();
	      if (mapByArticle.containsKey(entiteStock.getArticle()))
	      {
	        values = (EtatGlobalMapValue)mapByArticle.get(entiteStock.getArticle());
	        
	        qteActuelleTotal = values.getQteActuelle();
	        qteReserveeTotal = values.getQteReservee();
	        nbRouleauxActuelsTotal = values.getNbRouleauxActuels();
	        nbRouleauxReservesTotal = values.getNbRouleauxReserves();
	        if (entiteStock.getQteActuelle() != null)
	        {
	          qteActuelleTotal = Double.valueOf(qteActuelleTotal.doubleValue() + entiteStock.getQteActuelle().doubleValue());
	          values.setQteActuelle(qteActuelleTotal);
	        }
	        if (entiteStock.getQteReservee() != null)
	        {
	          qteReserveeTotal = Double.valueOf(qteReserveeTotal.doubleValue() + entiteStock.getQteReservee().doubleValue());
	          values.setQteReservee(qteReserveeTotal);
	        }
	        if (entiteStock.getRouleauxActuel() != null)
	        {
	          nbRouleauxActuelsTotal = Long.valueOf(nbRouleauxActuelsTotal.longValue() + entiteStock.getRouleauxActuel().longValue());
	          values.setNbRouleauxActuels(nbRouleauxActuelsTotal);
	        }
	        if (entiteStock.getRouleauxReserve() != null)
	        {
	          nbRouleauxReservesTotal = Long.valueOf(nbRouleauxReservesTotal.longValue() + entiteStock.getRouleauxReserve().longValue());
	          values.setNbRouleauxReserves(nbRouleauxReservesTotal);
	        }
	        
	        mapByArticle.remove(entiteStock.getArticle());
	        mapByArticle.put(entiteStock.getArticle(), values);
	      }
	      else
	      {
	        values.setId(entiteStock.getId());
	        
	        values.setQteActuelle(Double.valueOf(0.0D));
	        if (entiteStock.getQteActuelle() != null) {
	          values.setQteActuelle(entiteStock.getQteActuelle());
	        }
	        values.setQteReservee(Double.valueOf(0.0D));
	        if (entiteStock.getQteReservee() != null) {
	          values.setQteReservee(entiteStock.getQteReservee());
	        }
	        values.setNbRouleauxActuels(Long.valueOf(0L));
	        if (entiteStock.getRouleauxActuel() != null) {
	          values.setNbRouleauxActuels(entiteStock.getRouleauxActuel());
	        }
	        values.setNbRouleauxReserves(Long.valueOf(0L));
	        if (entiteStock.getRouleauxReserve() != null) {
	          values.setNbRouleauxReserves(entiteStock.getRouleauxReserve());
	        }
	        if (entiteStock.getArticle() != null)
	        {
	          values.setFamilleArticle(entiteStock.getFamilleArticle());
	          values.setLibelleArticle(entiteStock.getLibelleArticle());
	          values.setReferenceArticle(entiteStock.getReferenceArticle());
	          values.setPrixTotal(entiteStock.getPrixTotal());
	          values.setPrixUnitaire(entiteStock.getPrixUnitaire());
	          values.setIdArticle(entiteStock.getArticle());
	          mapByArticle.put(entiteStock.getArticle(), values);
	        }
	      }
	    }
	    Iterator it = mapByArticle.entrySet().iterator();
	    while (it.hasNext())
	    {
	      Map.Entry<Long, Map<Double, Long>> pair = (Map.Entry)it.next();
	      
	      Long currentArticle = (Long)pair.getKey();
	      
	      ArticleValue article = this.articlePersistance.getArticleParId(currentArticle);
	      
	      EtatStockGlobalElementReportValue elementReport = new EtatStockGlobalElementReportValue();
	      
	      EtatGlobalMapValue values = (EtatGlobalMapValue)mapByArticle.get(currentArticle);
	      elementReport.setId(values.getId());
	      elementReport.setQteActuelle(values.getQteActuelle());
	      elementReport.setQteReservee(values.getQteReservee());
	      
	      if (values.getQteActuelle() != null && values.getQteReservee() != null)
	      elementReport.setQteLibre(values.getQteActuelle()-values.getQteReservee());
	      
	      elementReport.setRouleauActuel(values.getNbRouleauxActuels());
	      elementReport.setRouleauReserve(values.getNbRouleauxReserves());
	      
	      elementReport.setFamilleArticle(values.getFamilleArticle());
	      elementReport.setLibelleArticle(values.getLibelleArticle());
	      elementReport.setPrixTotal(values.getPrixTotal());
	      elementReport.setPrixUnitaire(values.getPrixUnitaire());
	      elementReport.setReferenceArticle(values.getReferenceArticle());
	      elementReport.setArticleId(values.getIdArticle());
	      
	      elementReport.setCouleur(article.getCouleur());
	      elementReport.setCodeFournisseur(article.getCodeFournisseur());
	      elementReport.setProducteur(article.getProducteur());
	      elementReport.setSousFamilleArticle(article.getSousFamilleArtEntiteDesignation());
	      
	      elementsList.add(elementReport);
	    }
	    report.setElementsList(elementsList);
	  }
	
	@Override
	public EtatStockDetailleReportValue getEtatDetailleReport(RechercheMulticritereEntiteStockValue critere, String typeRapport) throws IOException {
		
		logger.info("Get Etat Stock Detaille Report");
		
		EtatStockDetailleReportValue report = new EtatStockDetailleReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATSTOCK);
		report.setReportStream(new FileInputStream("C://ERP/Lib/EtatStock/etat_stock_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PATH_LOGO", "/report/logo_enfavet.jpg");
		
		if(critere != null){
			if(critere.getTypeArticle() != null){
				
				switch (critere.getTypeArticle()) {
					case ARTICLE_TYPE_1:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatStock/Detaille/etat_stock_fourniture_sub_report.jasper");
						break;
						
					case ARTICLE_TYPE_2:
						params.put("SUBREPORT_STOCKHISTORYE_PATH", "C://ERP/Lib/EtatStock/Detaille/etat_stock_tissu_sub_report.jasper");
						break;
						
				}
				
			}
		}
		
		report.setParams(params);
		report.setType(critere.getTypeArticle());
		report.setTypeRapport("Détaillé");
		
		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(critere);
		
		if(result != null){
			enrichissementEtatDetailleReport(report, result);
		}
		
		ArrayList<EtatStockDetailleReportValue> dataList = new ArrayList<EtatStockDetailleReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}
		

	private void enrichissementEtatDetailleReport(EtatStockDetailleReportValue report, ResultatRechecheEntiteStockStockValue result) {
		
		Set<EtatStockDetailleElementReportValue> elementsList = report.getElementsList();
		
		for(EntiteStockValue entiteStock: result.getEntiteStock()){
			
			ArticleValue article = articlePersistance.getArticleParId(entiteStock.getArticle());
			
			EtatStockDetailleElementReportValue elementReport = new EtatStockDetailleElementReportValue();
			
			elementReport.setId(entiteStock.getId());
			elementReport.setDesignationMagasin(entiteStock.getDesignationMagasin());
			elementReport.setEmplacement(entiteStock.getEmplacement());
			elementReport.setFamilleArticle(entiteStock.getFamilleArticle());
			elementReport.setLibelleArticle(entiteStock.getLibelleArticle());
			elementReport.setPmp(entiteStock.getPmp());
			elementReport.setPrixTotal(entiteStock.getPrixTotal());
			elementReport.setPrixUnitaire(entiteStock.getPrixUnitaire());
			elementReport.setQteActuelle(entiteStock.getQteActuelle());
			elementReport.setReferenceArticle(entiteStock.getReferenceArticle());
			elementReport.setQteReservee(entiteStock.getQteReservee());
			elementReport.setRouleauActuel(entiteStock.getRouleauxActuel());
			elementReport.setRouleauReserve(entiteStock.getRouleauxReserve());
			elementReport.setRefLot(entiteStock.getReferenceLot());
			elementReport.setDateEntree(entiteStock.getDateEntree());
			
//			elementReport.setCouleur(article.getCouleur());
//			elementReport.setCodeFournisseur(article.getCodeFournisseur());
			elementReport.setProducteur(article.getProducteur());
			elementReport.setSousFamilleArticle(article.getSousFamilleArtEntiteDesignation());
			
			elementsList.add(elementReport);
		}
		
		report.setElementsList(elementsList);
		
	}

	@Override
	public FicheColisReportValue generateListEtatStockBarCodeReport(RechercheMulticritereEntiteStockValue request)
			throws IOException {		// TODO Auto-generated method stub
		FicheColisReportValue report = new FicheColisReportValue();

		report.setFileName("article");
		report.setReportStream(new FileInputStream("C:/ERP/Lib/COM_INDUSTRIEL/Stock_MP/Etat/Bar_Code/fiche_etat_bar_code.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(request);
		
		List<ColisValue> list = new ArrayList<ColisValue>();
			for (EntiteStockValue det :result.getEntiteStock()){
        	    ColisValue detail=new ColisValue();
        	    
        	    detail.setChoix(det.getNumeroBonEntree());
        	    
        	    detail.setProduitReference(det.getId().toString());
        	      	    
        	    detail.setCouleurDesignation(det.getReferenceArticle());
        	    detail.setProduitDesignation(det.getLibelleArticle());
        	    
        	    detail.setPoidsNet(det.getQteEntree());
        	    
        	    detail.setPoidsBrut(det.getQteActuelle());
        	    
        	    
        	    detail.setCarton(det.getReferenceLot());
        	    
        	    
	           //date Entree
        	    
        	    detail.setDateSortie(det.getDateEntree());
        	     	    
        	    //emplacement
        	    
        	    detail.setOrdreNumero(det.getEmplacement());
        	    
        	    
        	 
        	
        	list.add(detail);
        }
		
		
		report.setColisList(list);    
		ArrayList<FicheColisReportValue> dataList = new ArrayList<FicheColisReportValue>();
		dataList.add(report);


		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	@Override
	public FicheColisReportValue generateListEtatStockBarCodeFromBEReport(Long id) throws IOException {
		// TODO Auto-generated method stub
		FicheColisReportValue report = new FicheColisReportValue();

		report.setFileName("article");
		report.setReportStream(new FileInputStream("C:/ERP/Lib/COM_INDUSTRIEL/Stock_MP/Etat/Bar_Code/fiche_etat_bar_code.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		
		
		BonMouvementStockValue bm = bonMouvementDomaine.rechercheBonMouvementParId(id);
		
		List<Long> idsEntiteStock = new ArrayList<Long>();
		
		
		if(bm.getMouvements() != null) {
			
			
			
			for(MouvementStockValue mv :bm.getMouvements()) {
				
				idsEntiteStock.add(mv.getEntiteStock());
				
				
			}
			
			
		}
		
		RechercheMulticritereEntiteStockValue request = new RechercheMulticritereEntiteStockValue();
			
		request.setIds(idsEntiteStock);

		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(request);
		
		List<ColisValue> list = new ArrayList<ColisValue>();
			for (EntiteStockValue det :result.getEntiteStock()){
        	    ColisValue detail=new ColisValue();
        	    
        	    detail.setChoix(det.getNumeroBonEntree());
        	    
        	    detail.setProduitReference(det.getId().toString());
        	      	    
        	    detail.setCouleurDesignation(det.getReferenceArticle());
        	    detail.setProduitDesignation(det.getLibelleArticle());
        	    
        	    detail.setPoidsNet(det.getQteEntree());
        	    
        	    detail.setPoidsBrut(det.getQteActuelle());
        	    
        	 
        	    detail.setCarton(det.getReferenceLot());
        	    
        	    
        	    //date Entree
        	    
        	    detail.setDateSortie(det.getDateEntree());
        	     	    
        	    //emplacement
        	    
        	    detail.setOrdreNumero(det.getEmplacement());
        	    
        	
        	list.add(detail);
        }
		
		
		report.setColisList(list);    
		ArrayList<FicheColisReportValue> dataList = new ArrayList<FicheColisReportValue>();
		dataList.add(report);


		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	
	
}
