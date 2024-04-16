package com.gpro.consulting.tiers.logistique.domaine.fn.report.reporting.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IRegionPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.vue.BonSortieFnReportingVue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.IConstanteFinanceReport;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnChiffreAffaire.ChiffreAffaireFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnClient.ClientFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnExpEch.ExpeditionEchantillionFnReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetailBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFnReportingVue;
import com.gpro.consulting.tiers.logistique.domaine.fn.report.reporting.IGestionnaireReportFnReportingDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IDetLivraisonVentePersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class GestionnaireReportFnReportingDomaineImpl implements IGestionnaireReportFnReportingDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportFnReportingDomaineImpl.class);
	
	@Autowired
	private IDetLivraisonVentePersistance detLivraisonVentePersistance;
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private IRegionPersistance regionPersistance;
	
	@Autowired
	private IProduitPersistance produitPersistance ;

	@Autowired
	private IBonLivraisonPersistance livraisonVenteDomaine;
	
	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;
	
	
	@Autowired
	private IPrixClientPersistance prixClientPersistance;

	
	// Fn_Reporting Client & Produit 
	@Override
	public ClientFnReportingReportListValue getListClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException {

		ClientFnReportingReportListValue report = new ClientFnReportingReportListValue();
		
		report.setFileName(IConstanteFinanceReport.REPORT_NAME_FN_CLIENT_REPORTING);
		report.setReportStream(new FileInputStream(IConstanteFinanceReport.PATH_JRXML_FN_REPORTING_CLIENT));

		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_FN_CLIENT_PATH", IConstanteFinanceReport.PATH_JASPER_FN_REPORTING_CLIENT);
		
		report.setParams(params);
		
		enrichissementClientReportList(report, request);

		ArrayList<ClientFnReportingReportListValue> dataList = new ArrayList<ClientFnReportingReportListValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return report;
	}

	private void enrichissementClientReportList(
			ClientFnReportingReportListValue report,
			RechercheMulticritereFnReportingtValue request) {

		report.setTypeRapport(request.getTypeRapport());
		
		/** Critères de recherche dependent du type du rapport : Client ou Produit */
		if(request.getTypeRapport() != null){
			if(request.getTypeRapport().equals("Produit")){
				/** Criteres par Produit */
				//report.setCritere("Produit");
				report.setCritere("Article");
				if(request.getProduitId() != null){
					ProduitValue produit = produitPersistance.rechercheProduitById(request.getProduitId());
					if(produit != null){
						report.setClientAbreviation(produit.getDesignation());
						report.setClientReference(produit.getReference());
					}
				}
			}else if(request.getTypeRapport().equals("Client")){
				/** Criteres par Client */
				report.setCritere("Client");
					if(request.getClientId() != null){
						PartieInteresseValue client = partieInteresseePersistance.getById(request.getClientId());
							if(client != null){
							report.setClientAbreviation(client.getAbreviation());
							report.setClientReference(client.getReference());
							
						}
					}
				}
		}
		
		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());
		
		/** Listes DetailLiv pour Client & Produit **/
		ResultatRechecheDetailBonLivraisonValue result = detLivraisonVentePersistance.getDetailLivraisonByFnReportingClientProduitDate(request);
		
		Map<Long, Double> mapMetrageByProduitId = new HashMap<Long, Double>();
		Map<Long, Double> mapMetrageByClientId = new HashMap<Long, Double>();
		
		Double metrageTotal = 0D;
		for(DetLivraisonVenteValue detailLiv : result.getListDetailLivraison()){
			if(detailLiv != null){
				if(detailLiv.getProduitId() != null){
					/** metrageTotal groupe By client */
					if(request.getTypeRapport().equals("Produit")){
						
						LivraisonVenteValue livraisonVente = livraisonVenteDomaine.getBonLivraisonById(detailLiv.getLivraisonVenteId());
						
						Long keyClient = null;
						
						if(livraisonVente != null){
							
							keyClient = livraisonVente.getPartieIntId();
						}
						
						/** calcule du metrage Total de chaque Client */
						if (!mapMetrageByClientId.containsKey(keyClient)) {
							mapMetrageByClientId.put(keyClient,detailLiv.getQuantite() );
						}else{
							metrageTotal = mapMetrageByClientId.get(keyClient);
							metrageTotal += detailLiv.getQuantite();
							
							mapMetrageByClientId.put(keyClient, metrageTotal);
						}
					}else {
						/** metrageTotal groupe By produit */
							
							Long keyProduit = detailLiv.getProduitId();
							
							/** calcule du metrage Total de chaque produit */
							if (!mapMetrageByProduitId.containsKey(keyProduit)) {
								mapMetrageByProduitId.put(keyProduit,detailLiv.getQuantite() );
							}else{
								metrageTotal = mapMetrageByProduitId.get(keyProduit);
								metrageTotal += detailLiv.getQuantite();
								
								mapMetrageByProduitId.put(keyProduit, metrageTotal);
							}
					}
				
				}
			}
		}
		List<DetLivraisonVenteValue> listDetailLivraison = new ArrayList<DetLivraisonVenteValue>();
		
		if(request.getTypeRapport().equals("Produit")){
			Iterator it = mapMetrageByClientId.entrySet().iterator();
			
			while (it.hasNext()) {
				Map.Entry<Long, Double> pair = (Entry<Long, Double>) it.next();
				
				if(pair.getValue() != null){
						
						DetLivraisonVenteValue detLivraison = new DetLivraisonVenteValue();
						Long clientId = pair.getKey();
						
						ProduitValue produit = produitPersistance.rechercheProduitById(request.getProduitId());
						
						/** Si PrixClient.prix existe alors on prendra cette valeur , dans le cas échéant on prendra Produit.prix */
						
						
						PrixClientValue prixClientValue = prixClientPersistance.rechcherchePrixClientMC(new RecherchePrixClientValue(clientId,request.getProduitId()));
						
						if (prixClientValue != null && prixClientValue.getPrixvente() != null) {
							
							
							detLivraison.setPrixUnitaireHT(prixClientValue.getPrixvente());
							detLivraison.setPrixTotalHT(prixClientValue.getPrixvente() * pair.getValue());
							
						}
						else
							
						{
							detLivraison.setPrixUnitaireHT(produit.getPrixUnitaire());
							
							
							if (produit.getPrixUnitaire()!=null && pair.getValue()!=null)
							detLivraison.setPrixTotalHT(produit.getPrixUnitaire() * pair.getValue());
						}
		
						
						
						
				
						
						PartieInteresseValue client = partieInteresseePersistance.getById(clientId);
						detLivraison.setProduitDesignation(client.getAbreviation());
						detLivraison.setProduitReference(client.getReference());
						detLivraison.setQuantite(pair.getValue());
						detLivraison.setTypeRapport("Produit");
						listDetailLivraison.add(detLivraison);
						
				}
			}
		}else{
			Iterator it = mapMetrageByProduitId.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Long, Double> pair = (Entry<Long, Double>) it.next();
				
				if(pair.getValue() != null){
						
						DetLivraisonVenteValue detLivraison = new DetLivraisonVenteValue();
						Long produitId = pair.getKey();
						
						ProduitValue produit = produitPersistance.rechercheProduitById(produitId);
						
						
						/** Si PrixClient.prix existe alors on prendra cette valeur , dans le cas échéant on prendra Produit.prix **/
						
					
						
						PrixClientValue prixClientValue = prixClientPersistance.rechcherchePrixClientMC(new RecherchePrixClientValue(request.getClientId(),produitId));
						
						if (prixClientValue != null && prixClientValue.getPrixvente() != null) {
							
							
							detLivraison.setPrixUnitaireHT(prixClientValue.getPrixvente());
							detLivraison.setPrixTotalHT(prixClientValue.getPrixvente() * pair.getValue());
							
						}
						else
							
						{
							detLivraison.setPrixUnitaireHT(produit.getPrixUnitaire());
							
							
							if (produit.getPrixUnitaire()!=null && pair.getValue()!=null)
							detLivraison.setPrixTotalHT(produit.getPrixUnitaire() * pair.getValue());
						}
		
						
						
						
						
						
						
						
						
						detLivraison.setProduitDesignation(produit.getDesignation());
						detLivraison.setProduitReference(produit.getReference());
						//detLivraison.setPrixUnitaireHT(produit.getPrixUnitaire());
						detLivraison.setQuantite(pair.getValue());
					//	if (produit.getPrixUnitaire()!=null && pair.getValue()!=null )
						//detLivraison.setPrixTotalHT(produit.getPrixUnitaire() * pair.getValue());
						detLivraison.setTypeRapport("Client");
						listDetailLivraison.add(detLivraison);
						
				}
			}
		}
	
		report.setListDetailLivraison(listDetailLivraison);
			
	}

	// Fn_Reporting Chiffre D'affaire
	@Override
	public ChiffreAffaireFnReportingReportListValue getListChiffreAffaireGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException {
		
		ChiffreAffaireFnReportingReportListValue report = new ChiffreAffaireFnReportingReportListValue();
		
		report.setFileName(IConstanteFinanceReport.REPORT_NAME_FN_CHIFFRE_AFFAIRE_REPORTING);
		report.setReportStream(new FileInputStream(IConstanteFinanceReport.PATH_JRXML_FN_REPORTING_CHIFFRE_AFFAIRE));

		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_FN_CHIFFRE_AFFAIRE_PATH", IConstanteFinanceReport.PATH_JASPER_FN_REPORTING_CHIFFRE_AFFAIRE);
		
		report.setParams(params);
		
		enrichissementChiffreAffaireReportList(report, request);

		ArrayList<ChiffreAffaireFnReportingReportListValue> dataList = new ArrayList<ChiffreAffaireFnReportingReportListValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return report;	
	}

	private void enrichissementChiffreAffaireReportList(
			ChiffreAffaireFnReportingReportListValue report,
			RechercheMulticritereFnReportingtValue request) {
		
		/** critere de recherche */
		if(request.getRegionId() != null){
			RegionValue region = regionPersistance.getById(request.getRegionId());
			if(region != null){
				report.setRegionDesignation(region.getDesignation());
			}
		}
		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());
		
		/** List */
		ResultatRechecheBonLivraisonValue result = livraisonVenteDomaine.getLivraisonByFnReportingRegionDate(request);
		List<LivraisonVenteFnReportingVue> listLiv = new ArrayList <LivraisonVenteFnReportingVue>();
		Map<Long, Double> mapMetrageByClientId = new HashMap<Long, Double>();
		
		for(LivraisonVenteValue livVueElement : result.getList()){
			Double chiffreAffaireTotal = 0D;
			/* group by ClientId */
			if(livVueElement.getPartieIntId() != null){
				Long keyClient = livVueElement.getPartieIntId();
				
				if (!mapMetrageByClientId.containsKey(keyClient)) {
					mapMetrageByClientId.put(keyClient,livVueElement.getMontantHTaxe() );
				}else{
					chiffreAffaireTotal = mapMetrageByClientId.get(keyClient);
					if(livVueElement.getMontantHTaxe() != null){
						chiffreAffaireTotal += livVueElement.getMontantHTaxe();
					}
					mapMetrageByClientId.put(keyClient, chiffreAffaireTotal);
				}
				
			}
		}
		Iterator it = mapMetrageByClientId.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<Long, Double> pair = (Entry<Long, Double>) it.next();
			
				
				LivraisonVenteFnReportingVue livraison = new LivraisonVenteFnReportingVue();
					Long produitId = pair.getKey();
					
					PartieInteresseValue client = partieInteresseePersistance.getById(produitId);
					livraison.setClientAbreviation(client.getAbreviation());
					livraison.setClientReference(client.getReference());
					livraison.setChiffreAffaire(pair.getValue());
					listLiv.add(livraison);
					
		}
		report.setListLivraison(listLiv);
		
	}

	@Override
	public ExpeditionEchantillionFnReportingReportListValue getListExpeditionEchantillonGpByClientFnReport(
			RechercheMulticritereFnReportingtValue request) throws IOException {

		ExpeditionEchantillionFnReportingReportListValue report = new ExpeditionEchantillionFnReportingReportListValue();
		
		report.setFileName(IConstanteFinanceReport.REPORT_NAME_FN_EXP_ECH_REPORTING);
		report.setReportStream(new FileInputStream(IConstanteFinanceReport.PATH_JRXML_FN_REPORTING_EXP_ECH));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logo_commercial.png");
		params.put("SUBREPORT_FN_EXP_ECH_PATH", IConstanteFinanceReport.PATH_JASPER_FN_REPORTING_EXP_ECH);
		
		report.setParams(params);
		
		enrichissementExpEchReportList(report, request);

		ArrayList<ExpeditionEchantillionFnReportingReportListValue> dataList = new ArrayList<ExpeditionEchantillionFnReportingReportListValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return report;	
	}

	private void enrichissementExpEchReportList(
			ExpeditionEchantillionFnReportingReportListValue report,
			RechercheMulticritereFnReportingtValue request) {
		
		/** critere de recherche */
		if(request.getRegionId() != null){
			RegionValue region = regionPersistance.getById(request.getRegionId());
			if(region != null){
				report.setRegionDesignation(region.getDesignation());
			}
		}
		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());
		
		/** List */
		ResultatRechecheBonSortieFiniValue result = bonSortieFiniPersistance.getBSByFnReportingRegionDate(request);
		
		Map<Long, BonSortieFnReportingVue> mapMetrageByClientId = new HashMap<Long, BonSortieFnReportingVue>();
		
		for(BonSortieFiniValue livElement : result.getList()){
			Double metrageTotalECH = 0D;
			Double metrageTotalVente = 0D;
			/* group by ClientId */
			// REVIEW 
			BonSortieFnReportingVue bsVue = new BonSortieFnReportingVue();
					if(livElement.getPartieInt() != null){
						Long keyClient = livElement.getPartieInt();
						
						if(livElement.getType() != null){
							if (! mapMetrageByClientId.containsKey(keyClient) ) {
								
									if(livElement.getType().equals("Expédition")){
										bsVue.setMetrageTotalVente(livElement.getMetrageTotal());
									}
									if(livElement.getType().equals("Echantillon")){
										bsVue.setMetrageTotalECH(livElement.getMetrageTotal());
									}
									//logger.debug("NEW : "+keyClient +" ===Type : "+livElement.getType()+" ===ECH  : "+bsVue.getMetrageTotalECH() +" ===VENTE : "+bsVue.getMetrageTotalVente());
									
									mapMetrageByClientId.put(keyClient,bsVue );
							}else{
								
								bsVue = mapMetrageByClientId.get(keyClient);
								if(bsVue != null){
									
									if(livElement.getType().equals("Expédition")){
										if(livElement.getMetrageTotal() != null){
											if(bsVue.getMetrageTotalVente() == null){
												bsVue.setMetrageTotalVente(0D);
											}
												metrageTotalVente = bsVue.getMetrageTotalVente();
											
												
													metrageTotalVente += livElement.getMetrageTotal();
												
											
										}
										//logger.debug("===== OLD : "+keyClient +" :  ===VENTE : "+metrageTotalVente);
										
										bsVue.setMetrageTotalVente(metrageTotalVente);
									
									}else if(livElement.getType().equals("Echantillon")){
										if(livElement.getMetrageTotal() != null){
											if(bsVue.getMetrageTotalECH() == null){
												bsVue.setMetrageTotalECH(0D);
											}
												metrageTotalECH  = bsVue.getMetrageTotalECH();
										
													metrageTotalECH += livElement.getMetrageTotal();
											
										}
										//logger.debug("===== OLD : "+keyClient +" :  ===ECH : "+metrageTotalECH);
										
										bsVue.setMetrageTotalECH(metrageTotalECH);
									}
									
									mapMetrageByClientId.put(keyClient, bsVue);
								}
								
							}
						}
					}
		}
		
		List<BonSortieFnReportingVue> listLiv = new ArrayList <BonSortieFnReportingVue>();
		
		Iterator it = mapMetrageByClientId.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<Long, BonSortieFnReportingVue> pair = (Entry<Long, BonSortieFnReportingVue>) it.next();
			if(pair != null){
				BonSortieFnReportingVue bonSortieVue = new BonSortieFnReportingVue();
				Long produitId = pair.getKey();
				
				PartieInteresseValue client = partieInteresseePersistance.getById(produitId);
				bonSortieVue.setClientAbreviation(client.getAbreviation());
				bonSortieVue.setClientReference(client.getReference());
				if(pair.getValue() != null){
					bonSortieVue.setMetrageTotalECH(pair.getValue().getMetrageTotalECH());
					bonSortieVue.setMetrageTotalVente(pair.getValue().getMetrageTotalVente());
					listLiv.add(bonSortieVue);
				}
			}
		}
		report.setListBonSortie(listLiv);
		
	}

	

	

}
