package com.gpro.consulting.tiers.commun.domaine.elementBase.report.impl;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javassist.expr.NewArray;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.commun.domaine.elementBase.report.IGestionnaireReportCommunDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;

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
	
	@Autowired
	IProduitPersistance produitPersistance;
	
	@Autowired
	IProduitDomaine produitDomaine;
	
	
	

	
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
	
	

}
