package com.gpro.consulting.tiers.logistique.domaine.gl.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.ElementControleReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.ElementRecetteMiseReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseAvecCoutReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.FicheSuiveuseReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value.TraitementMiseReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ElementControleValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ElementRecetteMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.report.IGestionnaireReportGlDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.ITraitementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IMisePersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IMarchePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IControlePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IFicheSuiveusePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.machine.IMachinePersistance;

/**
 * Implementation of {@link IGestionnaireReportGlDomaine}
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */

@Component
public class GestionnaireReportGlDomaineImpl implements IGestionnaireReportGlDomaine{

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGlDomaineImpl.class);
	
	private static final String REPORT_NAME_FICHESUIVEUSE = "fiche suiveuse";
	private static final String REPORT_NAME_FICHESUIVEUSE_EMPTY = "fiche suiveuse vide";
	private static final String SEPARATOR = "@";
	private static final String TYPE_TINTURE_COTON = "Teinture - Coton";
	private static final String TYPE_TINTURE_PSE = "Teinture - PES";
	private static final String TYPE_FINISSAGE = "Finissage";
	private static final String TYPE_PREPARATION = "Preparation";
			
	@Autowired
	IFicheSuiveusePersistance ficheSuiveusePersistance;
	
	@Autowired
	IMachinePersistance machinePersistence;
	
	@Autowired
	IArticlePersistance articlePersistance;
	
	@Autowired
	IControlePersistance controlePersistance;
	
	@Autowired
	ITraitementPersistance traitementPersistance;
	
	@Autowired
	IMisePersistance misePersistance;
	
	@Autowired
	IMarchePersistance marchePersistance;
	
	@Autowired
	IRouleauFiniPersistance rouleauFiniPersistance;
	
	@Autowired
	IProduitPersistance produitPersistance;
	
	public FicheSuiveuseReportValue getFicheSuiveuseReport(Long id, String avecRecette) throws IOException {
		
		//logger.info("Generate a FicheSuiveuse PDF Report, Domain Layer");
		
		FicheSuiveuseReportValue report = new FicheSuiveuseReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_FICHESUIVEUSE);
		report.setReportStream(new FileInputStream("C://ERP/Lib/FicheSuiveuse/fiche_suiveuse_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 

		report.setParams(params);
		
		FicheSuiveuseValue ficheSuiveuseValue = ficheSuiveusePersistance.getById(id);
		
		// enrichissement du report
		enrichmentFicheSuiveuseReport(report, ficheSuiveuseValue, avecRecette);
		
		ArrayList<FicheSuiveuseReportValue> dataList = new ArrayList<FicheSuiveuseReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}

	
	@Override
	public FicheSuiveuseReportValue getFicheSuiveuseReportVide(Long id)
			throws IOException {
		
		//logger.info("Generate a FicheSuiveuse PDF Report, Domain Layer");
		
		FicheSuiveuseReportValue report = new FicheSuiveuseReportValue();
		
		// enrechissement des param du report
		report.setFileName(REPORT_NAME_FICHESUIVEUSE_EMPTY);
		report.setReportStream(new FileInputStream("C://ERP/Lib/FicheSuiveuse/fiche_suiveuse_empty_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 

		report.setParams(params);
		
		FicheSuiveuseValue ficheSuiveuseValue = ficheSuiveusePersistance.getById(id);
		
		// enrichissement du report
		enrichmentFicheSuiveuseReportVide(report, ficheSuiveuseValue);
		
		ArrayList<FicheSuiveuseReportValue> dataList = new ArrayList<FicheSuiveuseReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}
	
	
	private void enrichmentFicheSuiveuseEnteteReport(FicheSuiveuseReportValue report,
			FicheSuiveuseValue ficheSuiveuseValue) {
		
		report.setPoids(ficheSuiveuseValue.getPoids());
		report.setLaizeDemFini(ficheSuiveuseValue.getLaizeDemFini());
		report.setReferenceMise(ficheSuiveuseValue.getReferenceMise());
		
//		rest layer (referenceProduit, report.designation, report.dompositionProduit)
		report.setTypeLivraison(ficheSuiveuseValue.getTypeLivraison());
		report.setDateLancement(ficheSuiveuseValue.getDateLancement());
		report.setVolume(ficheSuiveuseValue.getVolume());
		report.setPoidsSortie(ficheSuiveuseValue.getPoidsSortie());
		report.setLaizeSortie(ficheSuiveuseValue.getLaizeSortie());
		report.setDateSortie(ficheSuiveuseValue.getDateLivraison());
		
		report.setClientId(ficheSuiveuseValue.getPartieIntId());
		report.setRapportBain(ficheSuiveuseValue.getRapportBain());
		report.setMetrageSortie(ficheSuiveuseValue.getMetrageSortie());
		
		ProduitValue produit = produitPersistance.rechercheProduitById(ficheSuiveuseValue.getProduitId());
		
		report.setProduitComposition(produit.getComposition());
		report.setProduitId(ficheSuiveuseValue.getProduitId());
		
		report.setPoidsPES(ficheSuiveuseValue.getPoidsPES());
		report.setPoidsCoton(ficheSuiveuseValue.getPoidsCoton());
		report.setVolumePES(ficheSuiveuseValue.getVolumePES());
		report.setVolumeCoton(ficheSuiveuseValue.getVolumeCoton());
		
		if(ficheSuiveuseValue.getMarcheId() != null){
			
			MarcheValue marche = marchePersistance.getById(ficheSuiveuseValue.getMarcheId());

			if(marche != null){
				
				report.setMarcheDesignation(marche.getDesignation());
			}
		}
		
		if(ficheSuiveuseValue.getReferenceMise() != null){
			List<MiseValue> listMise = misePersistance.getMiseByReference(ficheSuiveuseValue.getReferenceMise());
			
			if(listMise != null && listMise.size() > 0){
				report.setReferenceMise(listMise.get(0).getReference());
				report.setMetrageMise(listMise.get(0).getMetrage());
			}
		}
		
	}
	private void enrichmentFicheSuiveuseReport(FicheSuiveuseReportValue report,
			FicheSuiveuseValue ficheSuiveuseValue, String avecRecette) {
		
		Boolean showListElementRecetteMiseCoton = false;
		Boolean showListElementRecetteMisePES = false;
		Boolean showListElementRecetteMiseFinissage = false;
		Boolean showListElementRecetteMisePreparation = false;
		
		this.enrichmentFicheSuiveuseEnteteReport(report, ficheSuiveuseValue);
		
		List<ElementControleReportValue> listElementControleTintureCoton = new ArrayList <ElementControleReportValue>();
		List<ElementControleReportValue> listElementControleTinturePES = new ArrayList <ElementControleReportValue>();
		List<ElementControleReportValue> listElementControleFinissage = new ArrayList <ElementControleReportValue>();
		List<ElementControleReportValue> listElementControlePreparation = new ArrayList <ElementControleReportValue>();
		
		List<TraitementMiseReportValue> listTraitementsMiseTintureCoton = new ArrayList <TraitementMiseReportValue>();
		List<TraitementMiseReportValue> listTraitementsMiseTinturePES = new ArrayList <TraitementMiseReportValue>();
		List<TraitementMiseReportValue> listTraitementsMiseFinissage = new ArrayList <TraitementMiseReportValue>();
		List<TraitementMiseReportValue> listTraitementsMisePreparation = new ArrayList <TraitementMiseReportValue>();
		
		//group by TYPE
		
		for(ElementControleValue elementControle : ficheSuiveuseValue.getListElementControle()) {
			
			if(elementControle.getType() != null){
			
				ElementControleReportValue elementControleReport = new ElementControleReportValue();
	
				elementControleReport.setId(elementControle.getId());
				elementControleReport.setMethode(elementControle.getMethode());
				elementControleReport.setObservations(elementControle.getObservations());
				elementControleReport.setValeur(elementControle.getValeur());
				elementControleReport.setValeurCorrige(elementControle.getValeurCorrige());
				elementControleReport.setValeurTheorique(elementControle.getValeurTheorique());
				elementControleReport.setTemps(elementControle.getTemps());
				elementControleReport.setType(elementControle.getType());
				
				if(elementControle.getControleId() != null){
					ControleValue controle = controlePersistance.getById(elementControle.getControleId());
					if(controle != null){
						elementControleReport.setControleDesignation(controle.getDesignation());
					}
				}
				
			    switch(elementControle.getType()) {
			        case TYPE_TINTURE_COTON:
			        	listElementControleTintureCoton.add(elementControleReport);
			        	break;
			        	
					case TYPE_TINTURE_PSE:
						listElementControleTinturePES.add(elementControleReport);
						break;
						
					case TYPE_FINISSAGE:
						listElementControleFinissage.add(elementControleReport);
						break;
						
					case TYPE_PREPARATION:
						listElementControlePreparation.add(elementControleReport);
						break;
					
					default:
						break;
			    }
			}
			
		}
		
		for(TraitementMiseValue traitementMise : ficheSuiveuseValue.getListeTraitementsMise()) {
			
			List<ElementRecetteMiseReportValue> listElementRecetteMise = new ArrayList <ElementRecetteMiseReportValue>();
			
			if(traitementMise.getType() != null){
			
				TraitementMiseReportValue traitementMiseReport = new TraitementMiseReportValue();
				
			    if(avecRecette.equals(IConstante.OUI)){
			    	
					for(ElementRecetteMiseValue elementRecetteMise : traitementMise.getListElementRecetteMise()){
							
							ElementRecetteMiseReportValue element = new ElementRecetteMiseReportValue();
							
							element.setId(elementRecetteMise.getId());
							element.setObservations(elementRecetteMise.getObservations());
							element.setPoids(elementRecetteMise.getPoids());
							element.setPourcentage(elementRecetteMise.getPourcentage());
							element.setType(elementRecetteMise.getType());
							element.setRajout(elementRecetteMise.getRajout());
							element.setPh(elementRecetteMise.getPh());
							element.setOrder(elementRecetteMise.getOrder());
							element.setTemps(elementRecetteMise.getTemps());
							element.setTemperature(elementRecetteMise.getTemperature());
							
							if(elementRecetteMise.getArticleId() != null){
								ArticleValue article = articlePersistance.getArticleParId(elementRecetteMise.getArticleId());
								if(article != null){
									element.setArticleDesignation(article.getDesignation());
									element.setArticleReference(article.getRef());
									element.setPrixUnitaireRecette(article.getPu());
									if(article.getPu()!= null && element.getPoids()!=null){
										element.setPrixTotalRecette(article.getPu()* element.getPoids());
									}
									String famille = article.getFamilleArticleDesignation();
									
									if(famille != null){
										
										if(famille.length() > 2){
											
											//Output exmpl Form Famille column:
											//Colorant -> Col
											//Auxiliaire -> Aux
											
											element.setFamille(article.getFamilleArticleDesignation().substring(0,3));
										}else{
											
											element.setFamille(article.getFamilleArticleDesignation());
										}

									}
								}
							}
							
							listElementRecetteMise.add(element);
					}
					
					boolean showListElementMise = false;
					if(listElementRecetteMise.size() > 0)
						showListElementMise = true;
					
					if(traitementMise.getType() != null){
						switch(traitementMise.getType()) {
					        case TYPE_TINTURE_COTON:					        	
					        	if(showListElementMise)
					        		showListElementRecetteMiseCoton = true;
					        	break;
					        	
							case TYPE_TINTURE_PSE:								
								if(showListElementMise)
									showListElementRecetteMisePES = true;
								break;
								
							case TYPE_FINISSAGE:
								if(showListElementMise)
									showListElementRecetteMiseFinissage = true;
								break;
								
							case TYPE_PREPARATION:
								if(showListElementMise)
									showListElementRecetteMisePreparation = true;
								break;
							
							default:
								break;
					    }
					}
					
					// Added on 26/09/2016 by Zeineb Medimagh
					//Remplir le prixUnitaire et prixTotal d'un traitementMise
					if(traitementMise.getPu()!= null){
		        		traitementMiseReport.setPrixUnitaire(traitementMise.getPu());
		        		//TODO A verifier le poids
		        		traitementMiseReport.setPrixTotal(traitementMise.getPu()*ficheSuiveuseValue.getPoids());
		        	}
			    }

				Collections.sort(listElementRecetteMise);
				traitementMiseReport.setListElementRecetteMise(listElementRecetteMise);
				
				traitementMiseReport.setOrder(traitementMise.getOrder());
				traitementMiseReport.setType(traitementMise.getType());
				
				if(traitementMise.getObservations() != null){
					
					List<String> listObservations = new ArrayList<String>();
					String observationsSplitted[];
					
					observationsSplitted = traitementMise.getObservations().split(SEPARATOR);
					
					for(int index=0; index < observationsSplitted.length ;index++){
	
						listObservations.add(observationsSplitted[index]);
						
					}
					
					if(traitementMise.getObservations().length() > 0)
						traitementMiseReport.setShowObservations(true);
					
					traitementMiseReport.setObservations(listObservations);
				}
				
				if(traitementMise.getMachineId() != null){
					MachineValue machine = machinePersistence.getById(traitementMise.getMachineId());
					if(machine != null){
						traitementMiseReport.setMachineDesignation(machine.getDesignation());
					}
				}
				
				if(traitementMise.getTraitementId() != null){
					TraitementValue traitement = traitementPersistance.getTraitementParId(traitementMise.getTraitementId());
					if(traitement != null){
						traitementMiseReport.setTraitementDesignation(traitement.getDesignation());
					}
				}
				
						
				
			    switch(traitementMise.getType()) {
			        case TYPE_TINTURE_PSE:
			        	listTraitementsMiseTinturePES.add(traitementMiseReport);
			        	break;
			        case TYPE_TINTURE_COTON:
			        	listTraitementsMiseTintureCoton.add(traitementMiseReport);
			        	break;
					case TYPE_FINISSAGE:
						listTraitementsMiseFinissage.add(traitementMiseReport);
						break;
						
					case TYPE_PREPARATION:
						listTraitementsMisePreparation.add(traitementMiseReport);
						break;
						
					default:
						break;
			    }
			    
			}
	
		}
		
		Collections.sort(listTraitementsMiseTintureCoton);
		Collections.sort(listTraitementsMiseTinturePES);
		Collections.sort(listTraitementsMiseFinissage);
		Collections.sort(listTraitementsMisePreparation);
		
		Collections.sort(listElementControleTintureCoton);
		Collections.sort(listElementControleTinturePES);
		Collections.sort(listElementControleFinissage);
		Collections.sort(listElementControlePreparation);
		
		report.setListElementControleTintureCoton(listElementControleTintureCoton);
		report.setListElementControleTinturePES(listElementControleTinturePES);
		report.setListElementControleFinissage(listElementControleFinissage);
		report.setListElementControlePreparation(listElementControlePreparation);
		
		report.setListTraitementsMiseTintureCoton(listTraitementsMiseTintureCoton);
		report.setListTraitementsMiseTinturePES(listTraitementsMiseTinturePES);
		report.setListTraitementsMiseFinissage(listTraitementsMiseFinissage);
		report.setListTraitementsMisePreparation(listTraitementsMisePreparation);
		
		report.setShowListElementRecetteMiseCoton(showListElementRecetteMiseCoton);
		report.setShowListElementRecetteMisePES(showListElementRecetteMisePES);
		report.setShowListElementRecetteMiseFinissage(showListElementRecetteMiseFinissage);
		report.setShowListElementRecetteMisePreparation(showListElementRecetteMisePreparation);
	}

	
	private void enrichmentFicheSuiveuseReportVide(
			FicheSuiveuseReportValue report,
			FicheSuiveuseValue ficheSuiveuseValue) {
		
		report.setPoids(ficheSuiveuseValue.getPoids());
		report.setLaizeDemFini(ficheSuiveuseValue.getLaizeDemFini());
		report.setReferenceMise(ficheSuiveuseValue.getReferenceMise());
		
//		rest layer (referenceProduit, report.designation, report.dompositionProduit)
		report.setTypeLivraison(ficheSuiveuseValue.getTypeLivraison());
		report.setDateLancement(ficheSuiveuseValue.getDateLancement());
		report.setVolume(ficheSuiveuseValue.getVolume());
		report.setPoidsSortie(ficheSuiveuseValue.getPoidsSortie());
		report.setLaizeSortie(ficheSuiveuseValue.getLaizeSortie());
		report.setDateSortie(ficheSuiveuseValue.getDateLivraison());
		
		report.setClientId(ficheSuiveuseValue.getPartieIntId());
		report.setProduitId(ficheSuiveuseValue.getProduitId());
		report.setRapportBain(ficheSuiveuseValue.getRapportBain());
		report.setMetrageSortie(ficheSuiveuseValue.getMetrageSortie());
		
		if(ficheSuiveuseValue.getReferenceMise() != null){
			List<MiseValue> listMise = misePersistance.getMiseByReference(ficheSuiveuseValue.getReferenceMise());
			
			if(listMise != null && listMise.size() > 0){
				report.setReferenceMise(listMise.get(0).getReference());
				report.setMetrageMise(listMise.get(0).getMetrage());
			}
		}
		
		
		if(ficheSuiveuseValue.getMarcheId() != null){
			
			MarcheValue marche = marchePersistance.getById(ficheSuiveuseValue.getMarcheId());

			if(marche != null){
				
				report.setMarcheDesignation(marche.getDesignation());
			}
		}
		
		report.setShowListElementRecetteMiseCoton(false);
		report.setShowListElementRecetteMisePES(false);
		report.setShowListElementRecetteMiseFinissage(false);
		report.setShowListElementRecetteMisePreparation(false);
		
	}

	/************************* Rapport fiche suiveuse détaillée avec cout *****************************/

	@Override
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseDetCoutReport(Long id, String avecRecette,
			Double coutRecette, Double coutTraitement) throws IOException {
		//logger.info("Generate a FicheSuiveuseADetailleAvecCout PDF Report, Domain Layer");
		
		FicheSuiveuseAvecCoutReportValue report = new FicheSuiveuseAvecCoutReportValue();
		
		// enrichissement des param du report
		report.setFileName(REPORT_NAME_FICHESUIVEUSE);
		
		report.setReportStream(new FileInputStream("C://ERP/Lib/FicheSuiveuse/AvecCout/fiche_suiveuse_detaillee_cout_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 

		report.setParams(params);
		
		FicheSuiveuseValue ficheSuiveuseValue = ficheSuiveusePersistance.getById(id);
		
		// enrichissement du report
		enrichmentFicheSuiveuseDetAvecCoutReport(report, ficheSuiveuseValue, avecRecette, coutRecette, coutTraitement);
		
		ArrayList<FicheSuiveuseAvecCoutReportValue> dataList = new ArrayList<FicheSuiveuseAvecCoutReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}
	

	/************************* Rapport fiche suiveuse général avec cout *****************************/
	@Override
	public FicheSuiveuseAvecCoutReportValue getFicheSuiveuseGenCoutReport(Long id, String avecRecette,
			Double coutRecette, Double coutTraitement) throws IOException {

		//logger.info("Generate a FicheSuiveuseGeneraleAvecCout PDF Report, Domain Layer");

		FicheSuiveuseAvecCoutReportValue report = new FicheSuiveuseAvecCoutReportValue();
		
		// enrichissement des param du report
		report.setFileName(REPORT_NAME_FICHESUIVEUSE);
		
		report.setReportStream(new FileInputStream("C://ERP/Lib/FicheSuiveuse/AvecCout/fiche_suiveuse_generale_cout_report.jrxml"));
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 

		report.setParams(params);
		
		FicheSuiveuseValue ficheSuiveuseValue = ficheSuiveusePersistance.getById(id);
		
		// enrichissement du report
		enrichmentFicheSuiveuseGenAvecCoutReport(report, ficheSuiveuseValue, avecRecette, coutRecette, coutTraitement);
		
		ArrayList<FicheSuiveuseAvecCoutReportValue> dataList = new ArrayList<FicheSuiveuseAvecCoutReportValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		return report;
	}
	
	
	/************************** Enrichissement avec cout *************************************/
	

	private void enrichmentFicheSuiveuseDetAvecCoutReport(FicheSuiveuseAvecCoutReportValue report,
			FicheSuiveuseValue ficheSuiveuseValue, String avecRecette, Double coutRecette, Double coutTraitement) {

		//enrichissement du report complet
				this.enrichmentFicheSuiveuseReport(report, ficheSuiveuseValue, avecRecette);
		
		
		// Ajout enrichissement avec couts
			this.enrichissementDesCouts(report, coutRecette, coutTraitement, ficheSuiveuseValue);	
		
	}

	private void enrichmentFicheSuiveuseGenAvecCoutReport(FicheSuiveuseAvecCoutReportValue report,
			FicheSuiveuseValue ficheSuiveuseValue, String avecRecette, Double coutRecette, Double coutTraitement) {

		//enrichissement du report uniquement avec les informations d'entete
			this.enrichmentFicheSuiveuseEnteteReport(report, ficheSuiveuseValue);
		
		// Ajout enrichissement avec couts
			this.enrichissementDesCouts(report, coutRecette, coutTraitement, ficheSuiveuseValue);
	}
	
	
	private void enrichissementDesCouts(FicheSuiveuseAvecCoutReportValue report, Double coutRecette, Double coutTraitement, FicheSuiveuseValue ficheSuiveuseValue){
	
		Double metrageTotal= 0D;
		Double poidsTotal= 0D;
		Double coutUnitaire= 0D;
		
		List<RouleauFiniValue> listRouleauxFini = rouleauFiniPersistance.getAll();
		
		if(listRouleauxFini!=null){
			for (RouleauFiniValue rouleauFiniValue : listRouleauxFini) {
				
				if(rouleauFiniValue.getReferenceMise().equalsIgnoreCase(ficheSuiveuseValue.getReferenceMise())){
					
					if(rouleauFiniValue.getMetrage()!= null){
						metrageTotal+=rouleauFiniValue.getMetrage();
					}
					
					if(rouleauFiniValue.getPoids()!=null){
						poidsTotal+=rouleauFiniValue.getPoids();
					}
				}
			}
		}
		
		if(metrageTotal!=0D){
			coutUnitaire= (coutRecette+coutTraitement)/metrageTotal;
		}
		
		if(poidsTotal!=0D){
			coutUnitaire= (coutRecette+coutTraitement)/poidsTotal;
		}
		
		report.setMetrageTotal(metrageTotal);
		report.setPoidsTotal(poidsTotal);
		report.setCoutRecette(coutRecette);
		report.setCoutTraitement(coutTraitement);
		report.setCoutTotal(coutTraitement+coutRecette);
		report.setCoutUnitaire(coutUnitaire);
		
	}
}
