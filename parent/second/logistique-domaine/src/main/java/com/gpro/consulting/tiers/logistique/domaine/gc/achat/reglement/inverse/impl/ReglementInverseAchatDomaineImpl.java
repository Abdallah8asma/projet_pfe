package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.inverse.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.FactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.LivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.inverse.IReglementInverseAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IReglementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.ITypeReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.IReglementInverseAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetMensuelPersistance;

/**
 * implementation of {@link IReglementDomaine}
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@Component
public class ReglementInverseAchatDomaineImpl implements IReglementInverseAchatDomaine{

	private static final Logger logger = LoggerFactory.getLogger(ReglementInverseAchatDomaineImpl.class);
	
	private static final Double ZERO = 0D;
	private static final String SEPARATOR = "-";
	
	@Autowired
	private IReglementInverseAchatPersistance reglementAchatPersistance;
	
	@Autowired
	private IReceptionAchatPersistance bonReceptionAchatPersistance;
	
	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;
	
	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;
	
	@Autowired
	private IGuichetMensuelPersistance guichetierMensuelPersistance;
	
	@Autowired
	private ITypeReglementAchatPersistance typeReglementAchatPersistance;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String create(ReglementAchatValue reglement) {
		
		if(reglement != null){
			
			if(reglement.getDate() == null)
				reglement.setDate(Calendar.getInstance());
			
			if(reglement.getListDetailsReglement() != null){
				
				Double montantTotal = ZERO;
				
				
				
				for(DetailsReglementAchatValue details : reglement.getListDetailsReglement()){
					
					
					if(!estNonVide(details.getReference()) && details.getTypeReglementId() != null ) {
						
						details.setReference(getCurrentReferenceDetReglementMensuelByDate(reglement.getDate(), true,typeReglementAchatPersistance.getById(details.getTypeReglementId()).getPrefixe()));
					}
					
					
					montantTotal = montantTotal + (details.getMontant() != null ? details.getMontant() : ZERO);
				}
				
				reglement.setMontantTotal(montantTotal);
			}
			
			//if(reglement.getReference()==null || reglement.getReference().equals(""))
			//	reglement.setReference(this.getNumeroReglementAchat(reglement.getDate()));
			
			
			
			if ((reglement.getReference() != null && reglement.getReference().equals(""))
					|| reglement.getReference() == null) {

				reglement.setReference(this.getCurrentReferenceMensuelByDate(reglement.getDate(), true));

				// logger.info("----- auto reference ----------" +
				// bonReceptionValue.getReference());

			} else

			{
				if (reglement.getRefAvantChangement() != null
						&& reglement.getReference().equals(reglement.getRefAvantChangement())) {
					this.getCurrentReferenceMensuelByDate(reglement.getDate(), true);
				}

			}
			
			
			
			
		}
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementAchatPersistance.create(reglement);
	}

	@Override
	public ReglementAchatValue getById(Long id) {
		
		//logger.info("Delegating request id: {} to Persistance layer.",id);
		
		return reglementAchatPersistance.getById(id);
	}

	@Override
	public String update(ReglementAchatValue reglement) {
		
		if(reglement != null){
			
			if(reglement.getListDetailsReglement() != null){
				
				Double montantTotal = ZERO;
				
				for(DetailsReglementAchatValue details : reglement.getListDetailsReglement()){
					
					montantTotal = montantTotal + (details.getMontant() != null ? details.getMontant() : ZERO);
					
					
					
                      if(!estNonVide(details.getReference()) && details.getTypeReglementId() != null ) {
						
						details.setReference(getCurrentReferenceDetReglementMensuelByDate(reglement.getDate(), true,typeReglementAchatPersistance.getById(details.getTypeReglementId()).getPrefixe()));
					   
                      }
					
				}
				
				reglement.setMontantTotal(montantTotal);
			}
		}
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementAchatPersistance.update(reglement);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("Delegating request id: {} to Persistance layer.",id);
		
		reglementAchatPersistance.delete(id);
	}

	@Override
	public List<ReglementAchatValue> getAll() {
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementAchatPersistance.getAll();
	}

	@Override
	public ResultatRechecheReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request) {
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementAchatPersistance.rechercherMultiCritere(request);
	}

	@Override
	public ValidateReglementResultValue validateByFournisseurId(Long clientId) {
		
		ValidateReglementResultValue result = new ValidateReglementResultValue();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementAchatValue> listReglementByClientId = reglementAchatPersistance.getByFournisseurId(clientId);
		
		List<FactureAchatValue> listFactureByClientId = factureAchatPersistance.getByFournisseurId(clientId);
		List<ReceptionAchatValue> listBLByClientId = bonReceptionAchatPersistance.getByFournisseurId(clientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementAchatValue reglement : listReglementByClientId){
				
				for(ElementReglementAchatValue element: reglement.getListElementReglement()){
					
					if(element.getRefFacture() != null){
						
						if(mapFactureRefMontantRegle.containsKey(element.getRefFacture())){
							
							Double currentAmount = mapFactureRefMontantRegle.get(element.getRefFacture());
							
							if(currentAmount != null && element.getMontantDemande()!= null){
								
								currentAmount = currentAmount + element.getMontantDemande();
							}
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), currentAmount);
							
						}else{
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), element.getMontantDemande());
						}
					}
					
					if(element.getRefBL() != null){
						
						if(mapBLRefMontantRegle.containsKey(element.getRefBL())){
							
							Double currentAmount = mapBLRefMontantRegle.get(element.getRefBL());
							
							if(currentAmount != null && element.getMontantDemande()!= null){
								
								currentAmount = currentAmount + element.getMontantDemande();
							}
							
							mapBLRefMontantRegle.put(element.getRefBL(), currentAmount);
							
						}else{
							
							mapBLRefMontantRegle.put(element.getRefBL(), element.getMontantDemande());
						}
						
						refBLFromReglement.add(element.getRefBL());
					}
				}
			}
		}
		
		
		if(listBLByClientId.size() > 0 ){
			
			for(ReceptionAchatValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureAchatValue factureVente : listFactureByClientId){
				
				if(mapFactureRefMontantRegle.containsKey(factureVente.getReference())){
					
					if(factureVente.getMontantTTC() != null){
						
						factureMontantTotal = factureMontantTotal + factureVente.getMontantTTC();
						
						Double montantFactureRegle = mapFactureRefMontantRegle.get(factureVente.getReference());
						
						if(montantFactureRegle != null){
							
							factureMontantTotalRegle = factureMontantTotalRegle + montantFactureRegle;
							
							Double montantResteARegle = factureVente.getMontantTTC() - montantFactureRegle;
							
							if(montantResteARegle > 0){
								
								FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
								
								factureNonRegle.setMontantRegle(montantResteARegle);
								
								listFactureNonRegleVentre.add(factureNonRegle);
								
							}
						}	
					}
				}else{
					
					if(factureVente.getMontantTTC() != null){

						FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
						
						factureNonRegle.setMontantRegle(ZERO);
						
						listFactureNonRegleVentre.add(factureNonRegle);
						
					}
				}
				
				String refBLSplitted[];
				
				if(factureVente.getInfoLivraison() != null){
					
					refBLSplitted = factureVente.getInfoLivraison().split(SEPARATOR);
					
					for(int index=0; index < refBLSplitted.length ;index++){

						refBLFromFacture.add(refBLSplitted[index]);
						
					}	
					
				}
				
			}
			
		}
		
		//list des refBL nonRegle
		refBLFromBL.removeAll(refBLFromFacture);
		
		for(String refBLNonRergle : refBLFromBL){
			
			ReceptionAchatValue livraisonVente = bonReceptionAchatPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
						
						livraisonNonRegle.setMontantRegle(ZERO);
						
						listLivraisonNonRegle.add(livraisonNonRegle);
						
					}
				}
			}
		}
		
		result.setFactureMontantTotal(factureMontantTotal);
		result.setFactureMontantTotalRegle(factureMontantTotalRegle);
		result.setFactureMontantTotalNonRegle(factureMontantTotal - factureMontantTotalRegle);
		
		result.setBlMontantTotal(blMontantTotal);
		result.setBlMontantTotalRegle(blMontantTotalRegle);
		result.setBlMontantTotalNonRegle(blMontantTotal - blMontantTotalRegle);
		
		result.setListFactureNonRegle(new TreeSet<>(listFactureNonRegleVentre));
		result.setListLivraisonNonRegle(new TreeSet<>(listLivraisonNonRegle));
		
		return result;
	}

	private FactureNonRegleValue factureToFactureNonRegle(FactureAchatValue factureVente) {
		
		FactureNonRegleValue factureNonRegle = new FactureNonRegleValue();
		
		factureNonRegle.setId(factureVente.getId());
		factureNonRegle.setNumFacture(factureVente.getReference());
		factureNonRegle.setDate(factureVente.getDate());
		factureNonRegle.setMontantFacture(factureVente.getMontantTTC());
		
		return factureNonRegle;
	}

	private LivraisonNonRegleValue bRToBRNonRegle(ReceptionAchatValue livraisonVente) {

		LivraisonNonRegleValue bonLivraisonNonRegle = new LivraisonNonRegleValue();
		
		bonLivraisonNonRegle.setId(livraisonVente.getId());
		bonLivraisonNonRegle.setNumBL(livraisonVente.getReference());
		bonLivraisonNonRegle.setDate(livraisonVente.getDateLivraison());
		bonLivraisonNonRegle.setMontantBL(livraisonVente.getMontantTTC());
		
		return bonLivraisonNonRegle;
	}
	
	@Override
	public List<ReglementAchatValue> listeRefReglementCache() {
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementAchatPersistance.listeRefReglementCache();
	}
	
	@Override
	public List< RefFactureNonRegleValue> getRefFactureNonRegleByFournisseurId(Long clientId) {
		
		List< RefFactureNonRegleValue> resultatlistRefFactureNonRegle = new ArrayList< RefFactureNonRegleValue>();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementAchatValue> listReglementByClientId = reglementAchatPersistance.getByFournisseurId(clientId);
		
		List<FactureAchatValue> listFactureByClientId = factureAchatPersistance.getByFournisseurId(clientId);
		List<ReceptionAchatValue> listBLByClientId = bonReceptionAchatPersistance.getByFournisseurId(clientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementAchatValue reglement : listReglementByClientId){
				
				for(ElementReglementAchatValue element: reglement.getListElementReglement()){
					
					if(element.getRefFacture() != null){
						
						if(mapFactureRefMontantRegle.containsKey(element.getRefFacture())){
							
							Double currentAmount = mapFactureRefMontantRegle.get(element.getRefFacture());
							
							if(currentAmount != null && element.getMontantDemande()!= null){
								
								currentAmount = currentAmount + element.getMontantDemande();
							}
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), currentAmount);
							
						}else{
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), element.getMontantDemande());
						}
					}
					
					if(element.getRefBL() != null){
						
						if(mapBLRefMontantRegle.containsKey(element.getRefBL())){
							
							Double currentAmount = mapBLRefMontantRegle.get(element.getRefBL());
							
							if(currentAmount != null && element.getMontantDemande()!= null){
								
								currentAmount = currentAmount + element.getMontantDemande();
							}
							
							mapBLRefMontantRegle.put(element.getRefBL(), currentAmount);
							
						}else{
							
							mapBLRefMontantRegle.put(element.getRefBL(), element.getMontantDemande());
						}
						
						refBLFromReglement.add(element.getRefBL());
					}
				}
			}
		}
		
		
		if(listBLByClientId.size() > 0 ){
			
			for(ReceptionAchatValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureAchatValue factureVente : listFactureByClientId){
				
				if(mapFactureRefMontantRegle.containsKey(factureVente.getReference())){
					
					if(factureVente.getMontantTTC() != null){
						
						factureMontantTotal = factureMontantTotal + factureVente.getMontantTTC();
						
						Double montantFactureRegle = mapFactureRefMontantRegle.get(factureVente.getReference());
						
						if(montantFactureRegle != null){
							
							factureMontantTotalRegle = factureMontantTotalRegle + montantFactureRegle;
							
							Double montantResteARegle = factureVente.getMontantTTC() - montantFactureRegle;
							
							if(montantResteARegle > 0){
								
								FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
								
								factureNonRegle.setMontantRegle(montantResteARegle);
								
								listFactureNonRegleVentre.add(factureNonRegle);
								
							}
						}	
					}
				}else{
					
					if(factureVente.getMontantTTC() != null){

						FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
						
						factureNonRegle.setMontantRegle(ZERO);
						
						listFactureNonRegleVentre.add(factureNonRegle);
						
					}
				}
				
				String refBLSplitted[];
				
				if(factureVente.getInfoLivraison() != null){
					
					refBLSplitted = factureVente.getInfoLivraison().split(SEPARATOR);
					
					for(int index=0; index < refBLSplitted.length ;index++){

						refBLFromFacture.add(refBLSplitted[index]);
						
					}	
					
				}
				
			}
			
		}
		
		//list des refBL nonRegle
		refBLFromBL.removeAll(refBLFromFacture);
		
		for(String refBLNonRergle : refBLFromBL){
			
			ReceptionAchatValue livraisonVente = bonReceptionAchatPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
						
						livraisonNonRegle.setMontantRegle(ZERO);
						
						listLivraisonNonRegle.add(livraisonNonRegle);
						
					}
				}
			}
		}
		
		//DISCUSS: recuperation des RefFacture Non reglées
		if(listFactureNonRegleVentre != null){
			for(FactureNonRegleValue elementFactureNonReg : listFactureNonRegleVentre){
				RefFactureNonRegleValue factureNonRegle = new RefFactureNonRegleValue();
				
				factureNonRegle.setId(elementFactureNonReg.getId());
				factureNonRegle.setNumFacture(elementFactureNonReg.getNumFacture());
				
				factureNonRegle.setMontantFacture(elementFactureNonReg.getMontantFacture());
				factureNonRegle.setDate(elementFactureNonReg.getDate());
				
			
				resultatlistRefFactureNonRegle.add(factureNonRegle);
			}
			
			Collections.sort(resultatlistRefFactureNonRegle);
		}
		return resultatlistRefFactureNonRegle;
	}
	
	@Override
	public List< RefLivraisonNonRegleValue> getRefBLNonRegleByFournisseurId(Long clientId) {
		
		List< RefLivraisonNonRegleValue> resultatlistRefBLNonRegle = new ArrayList< RefLivraisonNonRegleValue>();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementAchatValue> listReglementByClientId = reglementAchatPersistance.getByFournisseurId(clientId);
		
		List<FactureAchatValue> listFactureByClientId = factureAchatPersistance.getByFournisseurId(clientId);
		List<ReceptionAchatValue> listBLByClientId = bonReceptionAchatPersistance.getByFournisseurId(clientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementAchatValue reglement : listReglementByClientId){
				
				for(ElementReglementAchatValue element: reglement.getListElementReglement()){
					
					if(element.getRefFacture() != null){
						
						if(mapFactureRefMontantRegle.containsKey(element.getRefFacture())){
							
							Double currentAmount = mapFactureRefMontantRegle.get(element.getRefFacture());
							
							if(currentAmount != null && element.getMontantDemande()!= null){
								
								currentAmount = currentAmount + element.getMontantDemande();
							}
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), currentAmount);
							
						}else{
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), element.getMontantDemande());
						}
					}
					
					if(element.getRefBL() != null){
						
						if(mapBLRefMontantRegle.containsKey(element.getRefBL())){
							
							Double currentAmount = mapBLRefMontantRegle.get(element.getRefBL());
							
							if(currentAmount != null && element.getMontantDemande()!= null){
								
								currentAmount = currentAmount + element.getMontantDemande();
							}
							
							mapBLRefMontantRegle.put(element.getRefBL(), currentAmount);
							
						}else{
							
							mapBLRefMontantRegle.put(element.getRefBL(), element.getMontantDemande());
						}
						
						refBLFromReglement.add(element.getRefBL());
					}
				}
			}
		}
		
		
		if(listBLByClientId.size() > 0 ){
			
			for(ReceptionAchatValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureAchatValue factureVente : listFactureByClientId){
				
				if(mapFactureRefMontantRegle.containsKey(factureVente.getReference())){
					
					if(factureVente.getMontantTTC() != null){
						
						factureMontantTotal = factureMontantTotal + factureVente.getMontantTTC();
						
						Double montantFactureRegle = mapFactureRefMontantRegle.get(factureVente.getReference());
						
						if(montantFactureRegle != null){
							
							factureMontantTotalRegle = factureMontantTotalRegle + montantFactureRegle;
							
							Double montantResteARegle = factureVente.getMontantTTC() - montantFactureRegle;
							
							if(montantResteARegle > 0){
								
								FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
								
								factureNonRegle.setMontantRegle(montantResteARegle);
								
								listFactureNonRegleVentre.add(factureNonRegle);
								
							}
						}	
					}
				}else{
					
					if(factureVente.getMontantTTC() != null){

						FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
						
						factureNonRegle.setMontantRegle(ZERO);
						
						listFactureNonRegleVentre.add(factureNonRegle);
						
					}
				}
				
				String refBLSplitted[];
				
				if(factureVente.getInfoLivraison() != null){
					
					refBLSplitted = factureVente.getInfoLivraison().split(SEPARATOR);
					
					for(int index=0; index < refBLSplitted.length ;index++){

						refBLFromFacture.add(refBLSplitted[index]);
						
					}	
					
				}
				
			}
			
		}
		
		//list des refBL nonRegle
		refBLFromBL.removeAll(refBLFromFacture);
		
		for(String refBLNonRergle : refBLFromBL){
			
			ReceptionAchatValue livraisonVente = bonReceptionAchatPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
						
						livraisonNonRegle.setMontantRegle(ZERO);
						
						listLivraisonNonRegle.add(livraisonNonRegle);
						
					}
				}
			}
		}
		
		//DISCUSS:  recuperation des RefBL Non reglées
		if(listLivraisonNonRegle != null){
			for(LivraisonNonRegleValue elementBLNonReg : listLivraisonNonRegle){
				RefLivraisonNonRegleValue blNonRegle = new RefLivraisonNonRegleValue();
				
				blNonRegle.setId(elementBLNonReg.getId());
				blNonRegle.setNumBL(elementBLNonReg.getNumBL());
				
				blNonRegle.setMontantBL(elementBLNonReg.getMontantBL());
				blNonRegle.setDate(elementBLNonReg.getDate());
				
				resultatlistRefBLNonRegle.add(blNonRegle);
				
			}
			Collections.sort(resultatlistRefBLNonRegle);
		}
		return resultatlistRefBLNonRegle;
				
	}
	
	private String getNumeroReglementAchat(final Calendar pDateBonFacture) {

		Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumReglementAchatReference();
		/** Année courante. */
		int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");
		vNumFacture.append(vAnneeCourante);
		vNumFacture.append(String.format("%06d", vNumGuichetFacture));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) +1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceReglementAchatCourante(new Long(
				vNumGuichetFacture + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine
				.modifierGuichetReglementAchatAnnuel(vGuichetValeur);
		return vNumFacture.toString();
	}

	@Override
	public ValidateReglementResultValue validateByGroupeFournisseurId(Long groupeClientId) {

		
		ValidateReglementResultValue result = new ValidateReglementResultValue();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementAchatValue> listReglementByClientId = reglementAchatPersistance.getByGroupeFournisseurId(groupeClientId);
		
		List<FactureAchatValue> listFactureByClientId = factureAchatPersistance.getByGroupeFournisseurId(groupeClientId);
		List<ReceptionAchatValue> listBLByClientId = bonReceptionAchatPersistance.getByGroupeFournisseurId(groupeClientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementAchatValue reglement : listReglementByClientId){
				
				for(ElementReglementAchatValue element: reglement.getListElementReglement()){
					
					if(element.getRefFacture() != null){
						
						if(mapFactureRefMontantRegle.containsKey(element.getRefFacture())){
							
							Double currentAmount = mapFactureRefMontantRegle.get(element.getRefFacture());
							
							if(currentAmount != null && element.getMontant()!= null){
								
								currentAmount = currentAmount + element.getMontant();
							}
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), currentAmount);
							
						}else{
							
							mapFactureRefMontantRegle.put(element.getRefFacture(), element.getMontant());
						}
					}
					
					if(element.getRefBL() != null){
						
						if(mapBLRefMontantRegle.containsKey(element.getRefBL())){
							
							Double currentAmount = mapBLRefMontantRegle.get(element.getRefBL());
							
							if(currentAmount != null && element.getMontant()!= null){
								
								currentAmount = currentAmount + element.getMontant();
							}
							
							mapBLRefMontantRegle.put(element.getRefBL(), currentAmount);
							
						}else{
							
							mapBLRefMontantRegle.put(element.getRefBL(), element.getMontant());
						}
						
						refBLFromReglement.add(element.getRefBL());
					}
				}
			}
		}
		
		
		if(listBLByClientId.size() > 0 ){
			
			for(ReceptionAchatValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureAchatValue factureVente : listFactureByClientId){
				
				if(mapFactureRefMontantRegle.containsKey(factureVente.getReference())){
					
					if(factureVente.getMontantTTC() != null){
						
						factureMontantTotal = factureMontantTotal + factureVente.getMontantTTC();
						
						Double montantFactureRegle = mapFactureRefMontantRegle.get(factureVente.getReference());
						
						if(montantFactureRegle != null){
							
							factureMontantTotalRegle = factureMontantTotalRegle + montantFactureRegle;
							
							Double montantResteARegle = factureVente.getMontantTTC() - montantFactureRegle;
							
							if(montantResteARegle > 0){
								
								FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
								
								factureNonRegle.setMontantRegle(montantFactureRegle);
								
								listFactureNonRegleVentre.add(factureNonRegle);
								
							}
						}	
					}
				}else{
					
					if(factureVente.getMontantTTC() != null){

						FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
						
						factureNonRegle.setMontantRegle(ZERO);
						
						listFactureNonRegleVentre.add(factureNonRegle);
						
					}
				}
				
				String refBLSplitted[];
				
				if(factureVente.getInfoLivraison() != null){
					
					refBLSplitted = factureVente.getInfoLivraison().split(SEPARATOR);
					
					for(int index=0; index < refBLSplitted.length ;index++){

						refBLFromFacture.add(refBLSplitted[index]);
						
					}	
					
				}
				
			}
			
		}
		
		//list des refBL nonRegle
		refBLFromBL.removeAll(refBLFromFacture);
		
		for(String refBLNonRergle : refBLFromBL){
			
			ReceptionAchatValue livraisonVente = bonReceptionAchatPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantBLRegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = bRToBRNonRegle(livraisonVente);
						
						livraisonNonRegle.setMontantRegle(ZERO);
						
						listLivraisonNonRegle.add(livraisonNonRegle);
						
					}
				}
			}
		}
		
		result.setFactureMontantTotal(factureMontantTotal);
		result.setFactureMontantTotalRegle(factureMontantTotalRegle);
		result.setFactureMontantTotalNonRegle(factureMontantTotal - factureMontantTotalRegle);
		
		result.setBlMontantTotal(blMontantTotal);
		result.setBlMontantTotalRegle(blMontantTotalRegle);
		result.setBlMontantTotalNonRegle(blMontantTotal - blMontantTotalRegle);
		
		result.setListFactureNonRegle(new TreeSet<>(listFactureNonRegleVentre));
		result.setListLivraisonNonRegle(new TreeSet<>(listLivraisonNonRegle));
		
		return result;
	}

	@Override
	public BLReportElementRecapValue getReglementAchatbyMonth(RechercheMulticritereReglementValue request) {
	
		Date dateDeb=new Date(request.getDateReglementMin().getTime().getYear(), request.getDateReglementMin().getTime().getMonth(), request.getDateReglementMin().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateReglementMax().getTime().getYear(), request.getDateReglementMax().getTime().getMonth(), request.getDateReglementMax().getTime().getDate());
		
		
		//TODO
		String where="";
		
		if (estNonVide(request.getBoutiqueId())) {
			where=" and reg.boutiqueId="+request.getBoutiqueId();
		}
		
		
		 Query vQuery = this.entityManager.createQuery(
			      "select sum(reg.montantTotal) as montantTotal, count(reg.id) as idDepot"
			      +" from ReglementAchatEntity reg "
			      + "where reg.date>= '"+dateDeb
			      +"' and reg.date<='"+dateA
			      +"'"+where );
		 
		 
		

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   	
			    	BLReportElementRecapValue resultat=new BLReportElementRecapValue();

			    	resultat.setMontantTTC((Double)vResult.get(0)[0]);

			    
			    	

			   
		return resultat;
	}
	
	
	 private boolean estNonVide(Long val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}
	 
	 
	 private boolean estNonVide(String val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}

	@Override
	public String getCurrentReference(Calendar instance, boolean increment) {
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long vNumGuichetFacture = currentGuichetAnnuel.getNumReferenceReglementAchatCourante();

		// Format du numero de la Bon Reception= AAAA-NN. /
		StringBuilder vNumFacture = new StringBuilder("");
		
		if (currentGuichetAnnuel.getPrefixeReglement()!= null)
			vNumFacture.append(currentGuichetAnnuel.getPrefixeReglementAchat());
		
		//vNumFacture.append(vAnneeCourante);
		vNumFacture.append(String.format("%06d", vNumGuichetFacture));
		// Inserer une nouvelle valeur dans Guichet BonReception. /
		//GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		
		currentGuichetAnnuel.setNumReferenceReglementAchatCourante(new Long(
				vNumGuichetFacture + 1L));
		// Modification de la valeur en base du numéro./
		
		if (increment)
		this.guichetAnnuelDomaine.modifierGuichetReglementAchatAnnuel(currentGuichetAnnuel);
			
		return vNumFacture.toString();
	}

	@Override
	public String getCurrentReferenceMensuelByDate(Calendar c, boolean increment) {
		Long vNumGuichetBonLiv = this.guichetierMensuelPersistance.getNextNumReglementInverseAchat(c); 
		String vNumGuichetPrefix=this.guichetierMensuelPersistance.getPrefixReglementInverseAchat(c);
		
		
		
		int vAnneeCourante = c.get(Calendar.YEAR);
		int moisActuel = c.get(Calendar.MONTH) + 1;

		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonLiv = new StringBuilder("");
		vNumBonLiv.append(vNumGuichetPrefix);
		
		//vNumBonLiv.append(vAnneeCourante);
		//vNumBonLiv.append(String.format("%02d", moisActuel));
		vNumBonLiv.append(String.format("%04d", vNumGuichetBonLiv));
		
		
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();
		/** idMensuel = (annuelcourante - 2016) + moisCourant */

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceReglementInverseAchatCourante(new Long(vNumGuichetBonLiv + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		      this.guichetierMensuelPersistance.modifierGuichetReglementInverseAchatMensuel(vGuichetValeur); 
		
		

		return vNumBonLiv.toString();
	}
	
	
	
	
	
	public String getCurrentReferenceDetReglementMensuelByDate(Calendar c, boolean increment, String prfixeTypeReglement) {
		Long vNumGuichetBonLiv = this.guichetierMensuelPersistance.getNextNumDetReglementInverseAchat(c); 
		String vNumGuichetPrefix=this.guichetierMensuelPersistance.getPrefixDetReglementInverseAchat(c);
		
		
		
		int vAnneeCourante = c.get(Calendar.YEAR);
		int moisActuel = c.get(Calendar.MONTH) + 1;

		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonLiv = new StringBuilder("");
		
		if(prfixeTypeReglement != null)
			vNumBonLiv.append(prfixeTypeReglement);
		
		if(vNumGuichetPrefix != null)
		vNumBonLiv.append(vNumGuichetPrefix);
		
		
		
		
		//vNumBonLiv.append(vAnneeCourante);
		//vNumBonLiv.append(String.format("%02d", moisActuel));
		vNumBonLiv.append(String.format("%04d", vNumGuichetBonLiv));
		
		
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();
		/** idMensuel = (annuelcourante - 2016) + moisCourant */

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceDetReglementInverseAchatCourante(new Long(vNumGuichetBonLiv + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		      this.guichetierMensuelPersistance.modifierGuichetDetReglementInverseAchatMensuel(vGuichetValeur); 
		
		

		return vNumBonLiv.toString();
	}


}
