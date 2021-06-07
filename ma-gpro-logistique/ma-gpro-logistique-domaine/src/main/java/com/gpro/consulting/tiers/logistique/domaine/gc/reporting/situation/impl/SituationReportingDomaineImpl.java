package com.gpro.consulting.tiers.logistique.domaine.gc.reporting.situation.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IRegionPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.ResultatRechecheSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reporting.situation.ISituationReportingDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier.IEcheancierFournisseurPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier.inverse.IEcheancierInverseFournisseurPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.IEcheancierPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.inverse.IEcheancierInversePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.ISoldeClientPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class SituationReportingDomaineImpl implements ISituationReportingDomaine {

//	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private ISoldeClientPersistance soldeClientPersistance;
	
	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;
	
	@Autowired
	private IEcheancierPersistance detailReglementPersistance;
	
	@Autowired
	private IEcheancierInversePersistance detailReglementInversePersistance;
	
	
	
	@Autowired
	private IRegionPersistance regionPersistance;
	
	@Autowired
	private IFacturePersistance facturePersistance;
	
	
	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;
	
	@Autowired
	private IReceptionAchatPersistance receptionAchatPersistance;
	
	@Autowired
	private IEcheancierFournisseurPersistance detailReglementAchatPersistance;
	
	
	@Autowired
	private IEcheancierInverseFournisseurPersistance detailReglementInverseAchatPersistance;
	
	private static final Logger logger = LoggerFactory.getLogger(SituationReportingDomaineImpl.class);
	
	@Override
	public ResultatRechecheSituationReportingValue rechercherMultiCritere(
			RechercheMulticritereSituationReportingValue request) {
		
		ResultatRechecheSituationReportingValue result = new ResultatRechecheSituationReportingValue();
		
		List<SituationReportingValue> listSituationReporting = new ArrayList <SituationReportingValue>();
		
		RechercheMulticritereSoldeClientValue requestSoldeClient = new RechercheMulticritereSoldeClientValue(
																	   request.getPartieIntId(),
																	   request.getDateMin(),
																	   request.getDateMax());
			
			requestSoldeClient.setPartieIntId(request.getPartieIntId());
			requestSoldeClient.setPartieIntFamilleId(IConstante.PI_FAMILLE_CLIENT);
			requestSoldeClient.setDeviseId(request.getDeviseId());
			ResultatRechecheSoldeClientValue resultatSoldeclient = soldeClientPersistance.rechercherMultiCritere(requestSoldeClient);
			
			
			
			
			
			//System.out.println("#####   resultatSoldeclient.getList() :  "+resultatSoldeclient.getList().size());
			
			
			for(SoldeClientValue soldeClientElement : resultatSoldeclient.getList()){
				
				
				/******** Remplir les elements du rapport *****************/
				
				SituationReportingValue situationReportingElement = new SituationReportingValue();
				
				if(soldeClientElement.getPartIntId() != null){
					PartieInteresseValue partieInt = partieInteresseePersistance.getById(soldeClientElement.getPartIntId());
					if(partieInt != null){
						situationReportingElement.setClientReference(partieInt.getReference());
						situationReportingElement.setClientAbreviation(partieInt.getAbreviation());
						if(partieInt.getRegionId() != null){
							RegionValue region = regionPersistance.getById(partieInt.getRegionId());
							if(region != null){
								situationReportingElement.setRegionDesignation(region.getDesignation());
								
								//System.out.println("------region.getDesignation()"+region.getDesignation());
							}
						}
					}
				}
				
				/** Solde Init **/
				situationReportingElement.setSoldeInit(soldeClientElement.getSoldeInitial());
				
				
				/** Chiffre d'affaire **/
				RechercheMulticritereBonLivraisonValue requestBL = new RechercheMulticritereBonLivraisonValue();
				requestBL.setPartieIntId(soldeClientElement.getPartIntId());
				
				//TODO : fix Date : if year == 2016
				Double regle_init = 0D;
				Double ca_Init = 0D;
				Double caTotal = 0D;
				Double caTotalFacturee = 0D;
				Double reglementTotal = 0D;
				
				Calendar calendar = Calendar.getInstance();
				if(request.getDateMin() != null){
					calendar.setTimeInMillis(request.getDateMin().getTimeInMillis());
					int mYear = calendar.get(Calendar.YEAR);
					//logger.debug("************  YEAR : "+mYear);
				// TODO pour année de debut d'exploitation
					if(mYear == 2016){
						
						requestBL.setDateLivraisonMin(soldeClientElement.getDateDemarrage());
						
						ca_Init = soldeClientElement.getChiffreAffaireTmp();
						regle_init = soldeClientElement.getReglementTmp();
						
						//System.out.println("-----------ca_Init -------"+ca_Init );
						//System.out.println("-----------regle_init-------"+regle_init);

						
					}else{
						requestBL.setDateLivraisonMin(soldeClientElement.getDateIntroduction());
					}
				}
				requestBL.setDateLivraisonMax(request.getDateMax());
				requestBL.setAvecFacture("nonFacture");
				
				//Somme des montantHT des factures
				Double caTotalFacture = 0D;
				caTotalFacture = facturePersistance.getSommeMontHT(soldeClientElement.getPartIntId(), request.getDateMin(), request.getDateMax());
				
				//Somme des montantHT des factures avoir
				Double caTotalFactureAvoir = 0D;
				caTotalFactureAvoir = facturePersistance.getSommeMontHTFactAvoir(soldeClientElement.getPartIntId(), request.getDateMin(), request.getDateMax());
				
				//Somme des montantHT des bon livraison
				Double caTotalBL =0D;
				caTotalBL = bonLivraisonPersistance.getSommeMontHT(requestBL);
				
				//Somme des montantHT des bon livraison facturés
				Double caBLFactures = 0D;
				//caBLFactures = bonLivraisonPersistance.getSommeMontHTFactures(requestBL);
				
				//TODO : Vérifier le calcul
				caTotal = (caTotalFacture - caTotalFactureAvoir) + (caTotalBL /*- caBLFactures*/);
				
				caTotalFacturee  = (caTotalFacture - caTotalFactureAvoir) ;
				
				//logger.info("Client ID: " + requestBL.getPartieIntId() + "\n caTotalBL=" + caTotalBL + "\n caBLFactures =" + caBLFactures + "\n caTotalFacture= " + caTotalFacture + "\n caTotalFactureAvoir =" + caTotalFactureAvoir +"\n caTotal =" + caTotal);
				
				caTotal += ca_Init;
				situationReportingElement.setChiffreDaffaire(caTotal);
				
				situationReportingElement.setChiffreDaffaireFacturee(caTotalFacturee);
				//System.out.println("-----------soldeActuel :.setChiffreDaffaire(caTotal)-------"+caTotal);

				
				/** Reglement **/
				RechercheMulticritereDetailReglementValue requestDetailReglement = new RechercheMulticritereDetailReglementValue();
				requestDetailReglement.setPartieIntId(soldeClientElement.getPartIntId());
				
				reglementTotal = detailReglementPersistance.getSommeMont(requestDetailReglement);
				
				reglementTotal += regle_init;
				situationReportingElement.setReglement(reglementTotal);
				
				/** Solde Actuel **/
				Double soldeActuel = 0D; Double soldeFacturee = 0D;
				Double vSoldeInitial=0D;
				if (soldeClientElement.getSoldeInitial()!=null)
				vSoldeInitial=soldeClientElement.getSoldeInitial();
                soldeActuel = (caTotal + vSoldeInitial-reglementTotal);
                
                soldeFacturee = caTotalFacture + vSoldeInitial - reglementTotal;
                
				//System.out.println("-----------soldeActuel :Solde Initial-------"+soldeClientElement.getSoldeInitial());
				//System.out.println("-----------soldeActuel :caTotal-------"+caTotal);
				//System.out.println("-----------soldeActuel :reglementTotal-------"+reglementTotal);
				//System.out.println("-----------soldeActuel :soldeActuel-------"+soldeActuel);
				//System.out.println("-----------soldeActuel :caTotalBL-------"+caTotalBL);

				
				//if (soldeActuel != 0.0) {
					
				
				situationReportingElement.setSoldeActuel(soldeActuel);
				
				situationReportingElement.setSoldeFacturee(soldeFacturee);
				
			//	}
				
				
				
				
				//payement en cours == (non regler et date echeance >= today)
				
				Double payementEncours = detailReglementPersistance.getMontantPayementsEnCours(requestDetailReglement);
				
				situationReportingElement.setPaiement(payementEncours);
				
				
				//Impayer == (non regler et date echeance < today)
				
		        Double impayee = detailReglementPersistance.getMontantImpaye(requestDetailReglement);
				
				situationReportingElement.setImpaye(impayee);
				
				
				
				
				//reglement inverse
				
			
				//Impayer == (non regler et date echeance < today)
				
		        Double impayeeInverse = detailReglementInversePersistance.getMontantImpaye(requestDetailReglement);
				
				situationReportingElement.setImpayeInverse(impayeeInverse);
				
				
				
				
				
				
				
				
				listSituationReporting.add(situationReportingElement);
				
			}
			
			//System.out.println("#######  listSituationReporting :  "+listSituationReporting.size());
		result.setListSituationReporting(listSituationReporting);
		
		return result;
	}

	@Override
	public ResultatRechecheSituationReportingValue rechercherMultiCritereAchat(
			RechercheMulticritereSituationReportingValue request) {
		ResultatRechecheSituationReportingValue result = new ResultatRechecheSituationReportingValue();
		
		List<SituationReportingValue> listSituationReporting = new ArrayList <SituationReportingValue>();
		
		RechercheMulticritereSoldeClientValue requestSoldeClient = new RechercheMulticritereSoldeClientValue(
																	   request.getPartieIntId(),
																	   request.getDateMin(),
																	   request.getDateMax());
			
			requestSoldeClient.setPartieIntId(request.getPartieIntId());
			requestSoldeClient.setPartieIntFamilleId(IConstante.PI_FAMILLE_FOURNISSEUR);
			ResultatRechecheSoldeClientValue resultatSoldeclient = soldeClientPersistance.rechercherMultiCritere(requestSoldeClient);
			
			
			
			
			
			//System.out.println("#####   resultatSoldeclient.getList() :  "+resultatSoldeclient.getList().size());
			
			
			for(SoldeClientValue soldeClientElement : resultatSoldeclient.getList()){
				
				
				//System.out.println("##########################soled client"+soldeClientElement.getSoldeInitial());
				
				
				/******** Remplir les elements du rapport *****************/
				
				SituationReportingValue situationReportingElement = new SituationReportingValue();
				
				if(soldeClientElement.getPartIntId() != null){
					PartieInteresseValue partieInt = partieInteresseePersistance.getById(soldeClientElement.getPartIntId());
					if(partieInt != null){
						situationReportingElement.setClientReference(partieInt.getReference());
						situationReportingElement.setClientAbreviation(partieInt.getAbreviation());
						if(partieInt.getRegionId() != null){
							RegionValue region = regionPersistance.getById(partieInt.getRegionId());
							if(region != null){
								situationReportingElement.setRegionDesignation(region.getDesignation());
								
								//System.out.println("------region.getDesignation()"+region.getDesignation());
							}
						}
					}
				}
				
				/** Solde Init **/
				situationReportingElement.setSoldeInit(soldeClientElement.getSoldeInitial());
				
				
				/** Chiffre d'affaire **/
				RechercheMulticritereBonReceptionAchatValue requestBL = new RechercheMulticritereBonReceptionAchatValue();
				requestBL.setPartieInteresseId(soldeClientElement.getPartIntId().toString());
				
				//TODO : fix Date : if year == 2016
				Double regle_init = 0D;
				Double ca_Init = 0D;
				Double caTotal = 0D;
				Double reglementTotal = 0D;
				
				Calendar calendar = Calendar.getInstance();
				if(request.getDateMin() != null){
					calendar.setTimeInMillis(request.getDateMin().getTimeInMillis());
					int mYear = calendar.get(Calendar.YEAR);
					//logger.debug("************  YEAR : "+mYear);
				// TODO pour année de debut d'exploitation
					if(mYear == 2016){
						
						//TODO A verifier si date intro ou date livraison
						requestBL.setDateLivraisonDu(soldeClientElement.getDateDemarrage());
						
						ca_Init = soldeClientElement.getChiffreAffaireTmp();
						regle_init = soldeClientElement.getReglementTmp();
						
						//System.out.println("-----------ca_Init -------"+ca_Init );
						//System.out.println("-----------regle_init-------"+regle_init);

						
					}else{
						requestBL.setDateLivraisonDu(soldeClientElement.getDateIntroduction());
					}
				}
				requestBL.setDateLivraisonDu(request.getDateMax());
				//TODO A verifier
				requestBL.setFacture("nonFacture");
				//requestBL.setAvecFacture("nonFacture");
				
				//Somme des montantHT des factures
				Double caTotalFacture = 0D;
				caTotalFacture = factureAchatPersistance.getSommeMontHT(soldeClientElement.getPartIntId(), request.getDateMin(), request.getDateMax());
				
				//Somme des montantHT des factures avoir
				Double caTotalFactureAvoir = 0D;
				caTotalFactureAvoir = factureAchatPersistance.getSommeMontHTFactAvoir(soldeClientElement.getPartIntId(), request.getDateMin(), request.getDateMax());
				
				//Somme des montantHT des bon livraison
				Double caTotalBL =0D;
				caTotalBL = receptionAchatPersistance.getSommeMontHT(requestBL);
				
				//Somme des montantHT des bon livraison facturés
				Double caBLFactures = 0D;
				//caBLFactures = bonLivraisonPersistance.getSommeMontHTFactures(requestBL);
				
				//TODO : Vérifier le calcul
				caTotal = (caTotalFacture - caTotalFactureAvoir) + (caTotalBL /*- caBLFactures*/);
				
				//logger.info("Client ID: " + requestBL.getPartieIntId() + "\n caTotalBL=" + caTotalBL + "\n caBLFactures =" + caBLFactures + "\n caTotalFacture= " + caTotalFacture + "\n caTotalFactureAvoir =" + caTotalFactureAvoir +"\n caTotal =" + caTotal);
				
				caTotal += ca_Init;
				situationReportingElement.setChiffreDaffaire(caTotal);
				//System.out.println("-----------soldeActuel :.setChiffreDaffaire(caTotal)-------"+caTotal);

				
				/** Reglement **/
				RechercheMulticritereDetailReglementAchatValue requestDetailReglement = new RechercheMulticritereDetailReglementAchatValue();
				requestDetailReglement.setPartieIntId(soldeClientElement.getPartIntId());
				
				reglementTotal = detailReglementAchatPersistance.getSommeMont(requestDetailReglement);
				
				reglementTotal += regle_init;
				situationReportingElement.setReglement(reglementTotal);
				
				/** Solde Actuel **/
				Double soldeActuel = 0D;
				Double vSoldeInitial=0D;
				if (soldeClientElement.getSoldeInitial()!=null)
				vSoldeInitial=soldeClientElement.getSoldeInitial();
                soldeActuel = (caTotal + vSoldeInitial-reglementTotal);
				//System.out.println("-----------soldeActuel :Solde Initial-------"+soldeClientElement.getSoldeInitial());
				//System.out.println("-----------soldeActuel :caTotal-------"+caTotal);
				//System.out.println("-----------soldeActuel :reglementTotal-------"+reglementTotal);
				//System.out.println("-----------soldeActuel :soldeActuel-------"+soldeActuel);
				//System.out.println("-----------soldeActuel :caTotalBL-------"+caTotalBL);

				
			//	if (soldeActuel != 0.0) {
					
				
				situationReportingElement.setSoldeActuel(soldeActuel);
				
			//	}
				
				
				//payement en cours == (non regler et date echeance >= today)
				
				Double payementEncours = detailReglementAchatPersistance.getMontantPayementsEnCours(requestDetailReglement);
				
				situationReportingElement.setPaiement(payementEncours);
				
				
				//Impayer == (non regler et date echeance < today)
				
		        Double impayee = detailReglementAchatPersistance.getMontantImpaye(requestDetailReglement);
				
				situationReportingElement.setImpaye(impayee);
				
			
				//reglement inverse
				
				
				//Impayer == (non regler et date echeance < today)
				
		        Double impayeeInverse = detailReglementInverseAchatPersistance.getMontantImpaye(requestDetailReglement);
				
				situationReportingElement.setImpayeInverse(impayeeInverse);
				
				
				
				listSituationReporting.add(situationReportingElement);
				
			}
			
			
		result.setListSituationReporting(listSituationReporting);
		
		return result;
	}


}
