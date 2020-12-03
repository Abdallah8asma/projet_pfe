package com.gpro.consulting.tiers.commun.domaine.elementBase.report.impl;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ColisValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.commun.domaine.elementBase.report.IGestionnaireReportCommunDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementation of {@link IGestionnaireReportCommunDomaine}
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */

@Component
public class GestionnaireReportCommunDomaineImpl implements IGestionnaireReportCommunDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportCommunDomaineImpl.class);
	
	private static final String REPORT_NAME_PRODUIT_LIST = "produits";
	
	
	private static final String REPORT_NAME_ARTICLE_LIST = "material";
	
	
	
	@Autowired
	IProduitPersistance produitPersistance;
	
	@Autowired
	IProduitDomaine produitDomaine;
	
	@Autowired
	IArticlePersistance articlePersistence;
	

	
	@Override
	public ProduitsReportListValue getListProduitsReport(RechercheMulticritereProduitValue request) throws IOException {
		
		ProduitsReportListValue produitsReportList = new ProduitsReportListValue();
		
		// enrechissement des param du report
		produitsReportList.setFileName(REPORT_NAME_PRODUIT_LIST);
		produitsReportList.setReportStream(new FileInputStream("C:/ERP/Lib/HIBA_ProduitList/produits_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_PRODUITS_PATH", "C:/ERP/Lib/HIBA_ProduitList/produits_sub_report.jasper");

		produitsReportList.setParams(params);
		
		ResultatRechecheProduitValue result = produitPersistance.rechercherProduitMultiCritere(request);
		
		Set<ProduitValue> produitsList = new TreeSet<ProduitValue>(result.getProduitValues());
		
		// enrichissement du report
		enrichmentProduitReportList(produitsReportList, produitsList, request);
		
		ArrayList<ProduitsReportListValue> dataList = new ArrayList<ProduitsReportListValue>();
		dataList.add(produitsReportList);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		produitsReportList.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return produitsReportList;
	}


	private void enrichmentProduitReportList(ProduitsReportListValue produitsReportList,
			Set<ProduitValue> produitsSet, RechercheMulticritereProduitValue request) {

		produitsReportList.setReference(request.getReference());
		produitsReportList.setDesignation(request.getDesignation());
		produitsReportList.setEtat(request.getEtat());
		produitsReportList.setSousfamille(request.getSousfamille());
		produitsReportList.setPartieInteressee(request.getPartieInteressee());
		produitsReportList.setPrixInf(request.getPrix_inf());
		produitsReportList.setPrixSup(request.getPrix_sup());
		
		List<ProduitReportElementValue> listeElementProduits = new ArrayList<ProduitReportElementValue>();
		
		for(ProduitValue produitValue : produitsSet){
			
			ProduitReportElementValue vProduitReportElementValue = new ProduitReportElementValue();
			vProduitReportElementValue.setDesignation(produitValue.getDesignation());
			vProduitReportElementValue.setEtat(produitValue.getEtat());
			//à partir de sousFamilleId on recupere la familleId
			vProduitReportElementValue.setFamilleId(produitValue.getSousFamilleId());
			vProduitReportElementValue.setPartieInteresseeId(produitValue.getPartieIntersseId());
			vProduitReportElementValue.setPrix(produitValue.getPrixUnitaire());
			vProduitReportElementValue.setReference(produitValue.getReference());
			vProduitReportElementValue.setSiteId(produitValue.getSiteId());
			vProduitReportElementValue.setSousfamilleId(produitValue.getSousFamilleId());
			vProduitReportElementValue.setQuantite(produitValue.getQuantite());
			listeElementProduits.add(vProduitReportElementValue);
		}

		produitsReportList.setProductList(listeElementProduits);
	}

	
	
	/**** Liste Produit avec Prix Special ****/
	@Override
	public ProduitsReportListValue getListProduitsReportSpecial(RechercheMulticritereProduitValue request) throws IOException {
		
		ProduitsReportListValue produitsReportList = new ProduitsReportListValue();
		
		// enrechissement des param du report
		produitsReportList.setFileName(REPORT_NAME_PRODUIT_LIST);
		produitsReportList.setReportStream(new FileInputStream("C:/ERP/Lib/STIT_ProduitListSpecial/produits_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_PRODUITS_PATH", "C:/ERP/Lib/STIT_ProduitListSpecial/produits_sub_report.jasper");

		produitsReportList.setParams(params);
		
		//ResultatRechecheProduitValue result = produitPersistance.rechercherProduitMultiCritere(request);
		
		//
		ResultatRechecheProduitValue result = produitDomaine.rechercherProduitMultiCritereClient(request);
		  
		//
		
		Set<ProduitValue> produitsList = new TreeSet<ProduitValue>(result.getProduitValues());
		
		// enrichissement du report
		enrichmentProduitReportListSpecial(produitsReportList, produitsList, request);
		
		ArrayList<ProduitsReportListValue> dataList = new ArrayList<ProduitsReportListValue>();
		dataList.add(produitsReportList);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		produitsReportList.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return produitsReportList;
	}


	private void enrichmentProduitReportListSpecial(ProduitsReportListValue produitsReportList,
			Set<ProduitValue> produitsSet, RechercheMulticritereProduitValue request) {

		produitsReportList.setReference(request.getReference());
		produitsReportList.setDesignation(request.getDesignation());
		produitsReportList.setEtat(request.getEtat());
		produitsReportList.setSousfamille(request.getSousfamille());
		produitsReportList.setPartieInteressee(request.getPartieInteressee());
		produitsReportList.setPrixInf(request.getPrix_inf());
		produitsReportList.setPrixSup(request.getPrix_sup());
		
		List<ProduitReportElementValue> listeElementProduits = new ArrayList<ProduitReportElementValue>();
		
		for(ProduitValue produitValue : produitsSet){
			
			ProduitReportElementValue vProduitReportElementValue = new ProduitReportElementValue();
			vProduitReportElementValue.setDesignation(produitValue.getDesignation());
			vProduitReportElementValue.setEtat(produitValue.getEtat());
			
			vProduitReportElementValue.setFamilleId(produitValue.getSousFamilleId());
			vProduitReportElementValue.setPartieInteresseeId(produitValue.getPartieIntersseId());
			vProduitReportElementValue.setPrix(produitValue.getPrixUnitaire());
			vProduitReportElementValue.setReference(produitValue.getReference());
			vProduitReportElementValue.setSiteId(produitValue.getSiteId());
			vProduitReportElementValue.setSousfamilleId(produitValue.getSousFamilleId());
			vProduitReportElementValue.setQuantite(produitValue.getQuantite());
			vProduitReportElementValue.setPrixspecial(produitValue.getPrixSpecial());
			
		//	System.out.println("produit:"+produitValue.getDesignation()+"  prix: "+produitValue.getPrixUnitaire()+"   prix special: "+produitValue.getPrixSpecial());
			
			
			listeElementProduits.add(vProduitReportElementValue);
		}

		produitsReportList.setProductList(listeElementProduits);
	}

	/**
	 * @return the produitPersistance
	 */
	public IProduitPersistance getProduitPersistance() {
		return produitPersistance;
	}


	/**
	 * @param produitPersistance the produitPersistance to set
	 */
	public void setProduitPersistance(IProduitPersistance produitPersistance) {
		this.produitPersistance = produitPersistance;
	}
	

	// Liste Article Report 
	
	
	
	@Override
	public ArticleReportListValue getListArticleReport(RecherecheMulticritereArticleValue request) throws IOException {
		
		ArticleReportListValue produitsReportList = new ArticleReportListValue();
		
		// enrechissement des param du report
		produitsReportList.setFileName(REPORT_NAME_PRODUIT_LIST);
		produitsReportList.setReportStream(new FileInputStream("C:/ERP/Lib/TP_Article/barecodeList2.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 
		//params.put("SUBREPORT_PRODUITS_PATH", "C:/ERP/Lib/TP_Article/barecodeList.jasper");

		produitsReportList.setParams(params);
		
		ResultatRechecheArticleValue result = articlePersistence.rechercherArticleMultiCritere(request);
		
		System.out.println("######   RESULT SIZE  :   "+result.getNombreResultaRechercher());
		
		Set<ArticleValue> produitsList = new TreeSet<ArticleValue>(result.getArticleValues());
		
		// enrichissement du report
		enrichmentArticleReportList(produitsReportList, produitsList, request);
		
		System.out.println("### PRODUIT REPORT LIST :   "+produitsReportList.getProductList().size());
		
		
		ArrayList<ArticleReportListValue> dataList = new ArrayList<ArticleReportListValue>();
		dataList.add(produitsReportList);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		produitsReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return produitsReportList;
	}
	
	
	private void enrichmentArticleReportList(ArticleReportListValue produitsReportList,
			Set<ArticleValue> produitsSet, RecherecheMulticritereArticleValue request) {
		
		
		
		
		List<ArticleReportElementValue> listeElementProduits = new ArrayList<ArticleReportElementValue>();
		
		for(ArticleValue produitValue : produitsSet){
			
			ArticleReportElementValue vProduitReportElementValue = new ArticleReportElementValue();
			vProduitReportElementValue.setReference(produitValue.getRef());
		
			
			listeElementProduits.add(vProduitReportElementValue);
		}
		

		produitsReportList.setProductList(listeElementProduits);
	}
	
	
	
	
	
	@Override
	public FicheColisReportValue getColisReport(
			RecherecheMulticritereArticleValue request ) throws IOException {
		// TODO Auto-generated method stub
		FicheColisReportValue report = new FicheColisReportValue();

		report.setFileName("article");
		report.setReportStream(new FileInputStream("C:/ERP/Lib/TP_Article/fiche_article.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		ResultatRechecheArticleValue result = articlePersistence.rechercherArticleMultiCritere(request);
		
		List<ColisValue> list = new ArrayList<ColisValue>();
			for (ArticleValue det :result.getArticleValues()){
        	    ColisValue detail=new ColisValue();
        	    detail.setProduitReference(det.getRef());
        	
        	
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
	public ProduitReportValue getProduitReport(Long id) throws IOException {
		
ProduitReportValue produitsReportList = new ProduitReportValue();
		
		// enrechissement des param du report
		produitsReportList.setFileName(REPORT_NAME_PRODUIT_LIST);
		produitsReportList.setReportStream(new FileInputStream("C:/ERP/Lib/TP_Produit/barecode.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 
		//params.put("SUBREPORT_PRODUITS_PATH", "C:/ERP/Lib/GPRO_ProduitList/produits_sub_report.jasper");

		produitsReportList.setParams(params);
		
		//ProduitValue produitRecherche =new ProduitValue();
	//	produitRecherche.setId(id);
		ProduitValue produit=produitPersistance.rechercheProduitById(id);
		produitsReportList.setCodeBarre(produit.getReference());
		produitsReportList.setDesignation(produit.getDesignation());
		
	//	System.out.println("### REMPLISSAGE ###########");
		
		
		ArrayList<ProduitReportValue> dataList = new ArrayList<ProduitReportValue>();
		dataList.add(produitsReportList);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		produitsReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
	//	System.out.println("#### TRT REPORT ########");
		
		return produitsReportList;
	}

	@Override
	public ArticleReportValue getArticleReport(Long id) throws IOException {
		
		ArticleReportValue produitsReportList = new ArticleReportValue();
		
		// enrechissement des param du report
		produitsReportList.setFileName(REPORT_NAME_ARTICLE_LIST);
		produitsReportList.setReportStream(new FileInputStream("C:/ERP/Lib/TP_Article/barecode.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 
		//params.put("SUBREPORT_PRODUITS_PATH", "C:/ERP/Lib/GPRO_ProduitList/produits_sub_report.jasper");

		produitsReportList.setParams(params);
		
		ArticleValue articleRecherche =new ArticleValue();
		articleRecherche.setId(id);
		ArticleValue article=articlePersistence.rechercheArticleParId(articleRecherche);
		produitsReportList.setCodeBarre(article.getRef());
		
		//System.out.println("### REMPLISSAGE ###########");
		
		
		ArrayList<ArticleReportValue> dataList = new ArrayList<ArticleReportValue>();
		dataList.add(produitsReportList);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		produitsReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		//System.out.println("#### TRT REPORT ########");
		
		return produitsReportList;
	}


}
