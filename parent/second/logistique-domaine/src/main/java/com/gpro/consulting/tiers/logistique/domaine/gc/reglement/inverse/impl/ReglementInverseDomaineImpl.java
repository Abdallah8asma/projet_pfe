package com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.FactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.LivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IReglementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse.IReglementInverseDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IBonCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetMensuelPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.IReglementInversePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.typeReglement.ITypeReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;

/**
 * implementation of {@link IReglementDomaine}
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@Component
public class ReglementInverseDomaineImpl implements IReglementInverseDomaine{

	private static final Logger logger = LoggerFactory.getLogger(ReglementInverseDomaineImpl.class);
	
	private static final Double ZERO = 0D;
	private static final String SEPARATOR = "-";
	
	@Autowired
	private IReglementInversePersistance reglementPersistance;
	
	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;
	
	@Autowired
	private IFacturePersistance facturePersistance;
	
	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;
	
	
	@Autowired
	private IPartieInteresseeDomaine partieInteresseeDomaine;
	
	@Autowired
	private IBonCommandePersistance bonCommandePersistance;
	
	
	@Autowired
	private IGuichetMensuelPersistance guichetierMensuelPersistance;
	
	@Autowired
	private ITypeReglementPersistance typeReglementPersistance;
	
	@Override
	public String create(ReglementValue reglement) {
		
		if(reglement != null){
			
			if(reglement.getDate() == null)
				reglement.setDate(Calendar.getInstance());
			
			if(reglement.getListDetailsReglement() != null){
				
				Double montantTotal = ZERO;
				
				for(DetailsReglementValue details : reglement.getListDetailsReglement()){
					
					montantTotal = montantTotal + (details.getMontant() != null ? details.getMontant() : ZERO);
					
			        if(!estNonVide(details.getReference()) && details.getTypeReglementId() != null ) {
						
						details.setReference(getCurrentReferenceDetReglementMensuelByDate(reglement.getDate(), true,typeReglementPersistance.getById(details.getTypeReglementId()).getPrefixe()));
					}
				}
				
				reglement.setMontantTotal(montantTotal);
			}
			
			if(reglement.getReference()==null || reglement.getReference().equals("")) {
			//	reglement.setReference(this.getNumeroReglement(reglement.getDate()));
				reglement.setReference(getCurrentReferenceMensuelByDate(Calendar.getInstance(), true));
			}else if (reglement.getRefAvantChangement() != null
					&& reglement.getReference().equals(reglement.getRefAvantChangement())) {
				this.getCurrentReferenceMensuelByDate(reglement.getDate(), true);
			}
			

			
			
			if(reglement.getPartieIntId() != null)
				reglement.setGroupeClientId(partieInteresseeDomaine.getById(reglement.getPartieIntId()).getGroupeClientId());
				
		}
		
		//logger.info("Delegating request to Persistance layer.");
		
		String reglementId =  reglementPersistance.create(reglement);
		
		
	    if(reglement.getAjoutSpecial() != null && reglement.getAjoutSpecial().equals(IConstanteCommerciale.REGLEMENT_AJOUT_SPECIAL_BC) && reglement.getRefBC() != null) {
	    
	    	
	    	CommandeVenteValue commandeVente = bonCommandePersistance.getByReference(reglement.getRefBC());
	    	
	    	commandeVente.setReglementId(Long.parseLong(reglementId));
	    	
	    	bonCommandePersistance.update(commandeVente);
	    	
		}
	    
	    
		if(reglement.getAjoutSpecial() != null && reglement.getAjoutSpecial().equals(IConstanteCommerciale.REGLEMENT_AJOUT_SPECIAL_BL) && reglement.getListElementReglement().size()==1) {
			
		
			//set reglement id
			String referenceBL = reglement.getListElementReglement().iterator().next().getRefBL();
			
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(referenceBL);
		
			livraisonVente.setReglementId(Long.parseLong(reglementId));
			
			bonLivraisonPersistance.updateBonLivraison(livraisonVente);
			
			
			return reglementId;
		}
		
	    if(reglement.getAjoutSpecial() != null && reglement.getAjoutSpecial().equals(IConstanteCommerciale.REGLEMENT_AJOUT_SPECIAL_FACTURE) && reglement.getListElementReglement().size()==1) {
			
	    	//set reglement id
			String referenceFacture = reglement.getListElementReglement().iterator().next().getRefFacture();
			
			
			FactureVenteValue factureVenteValue = facturePersistance.getFactureByReference(referenceFacture);
		
			factureVenteValue.setReglementId(Long.parseLong(reglementId));
			
			facturePersistance.updateFacture(factureVenteValue);
			
			return reglementId;
		
	    }
		

	    
	
		
		
		
		return reglementId;
		
	}

	@Override
	public ReglementValue getById(Long id) {
		
		//logger.info("Delegating request id: {} to Persistance layer.",id);
		
		return reglementPersistance.getById(id);
	}

	@Override
	public String update(ReglementValue reglement) {
		
		if(reglement != null){
			
			if(reglement.getPartieIntId() != null)
				reglement.setGroupeClientId(partieInteresseeDomaine.getById(reglement.getPartieIntId()).getGroupeClientId());
			
			
			if(reglement.getListDetailsReglement() != null){
				
				Double montantTotal = ZERO;
				
				for(DetailsReglementValue details : reglement.getListDetailsReglement()){
					
					montantTotal = montantTotal + (details.getMontant() != null ? details.getMontant() : ZERO);
					
					   if(!estNonVide(details.getReference()) && details.getTypeReglementId() != null ) {
							
							details.setReference(getCurrentReferenceDetReglementMensuelByDate(reglement.getDate(), true,typeReglementPersistance.getById(details.getTypeReglementId()).getPrefixe()));
						}
				}
				
				reglement.setMontantTotal(montantTotal);
			}
		}
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementPersistance.update(reglement);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("Delegating request id: {} to Persistance layer.",id);
		
		ReglementValue reglement = reglementPersistance.getById(id);
		
		reglementPersistance.delete(id);
		
		
		if(reglement.getAjoutSpecial() != null && reglement.getAjoutSpecial().equals(IConstanteCommerciale.REGLEMENT_AJOUT_SPECIAL_BC)) {
			
			
			List<CommandeVenteValue> listeCommandes =	bonCommandePersistance.getByIdReglement(id);
			
			for(CommandeVenteValue commande :listeCommandes ) {
				
				commande.setReglementId(null);
				bonCommandePersistance.update(commande);
				
			}
			
			
		}
		
		
	if(reglement.getAjoutSpecial() != null && reglement.getAjoutSpecial().equals(IConstanteCommerciale.REGLEMENT_AJOUT_SPECIAL_BL)) {
			
			
			
		List<LivraisonVenteValue>  listLivraison = bonLivraisonPersistance.getByIdReglement(id);
		
		
		for(LivraisonVenteValue element :listLivraison ) {
			
			element.setReglementId(null);
			bonLivraisonPersistance.updateBonLivraison(element);
			
		}
		
		
		
			
		}
	
	
	if(reglement.getAjoutSpecial() != null && reglement.getAjoutSpecial().equals(IConstanteCommerciale.REGLEMENT_AJOUT_SPECIAL_FACTURE)) {
		
		
		List<FactureVenteValue>  listeFacture = facturePersistance.getByIdReglement(id);
		
		
		for(FactureVenteValue element :listeFacture ) {
			
			element.setReglementId(null);
			facturePersistance.updateFacture(element);
			
		}
		
		
		
	}
		
		
		
		
		
		
		
		
		
	}

	@Override
	public List<ReglementValue> getAll() {
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementPersistance.getAll();
	}

	@Override
	public ResultatRechecheReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request) {
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementPersistance.rechercherMultiCritere(request);
	}

	/*@Override
	public ValidateReglementResultValue validateByClientId(Long clientId) {
		
		ValidateReglementResultValue result = new ValidateReglementResultValue();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementValue> listReglementByClientId = reglementPersistance.getByClientId(clientId);
		
		List<FactureVenteValue> listFactureByClientId = facturePersistance.getByClientId(clientId);
		List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByClientId(clientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementValue reglement : listReglementByClientId){
				
				for(ElementReglementValue element: reglement.getListElementReglement()){
					
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
			
			for(LivraisonVenteValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureVenteValue factureVente : listFactureByClientId){
				
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
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantBLRegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
						
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
	
	*/
	
	
	@Override
	public ValidateReglementResultValue validateByClientId(Long clientId) {
		
		ValidateReglementResultValue result = new ValidateReglementResultValue();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementValue> listReglementByClientId = reglementPersistance.getByClientId(clientId);
		
		List<FactureVenteValue> listFactureByClientId = facturePersistance.getByClientId(clientId);
		List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByClientId(clientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementValue reglement : listReglementByClientId){
				
				for(ElementReglementValue element: reglement.getListElementReglement()){
					
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
			
			for(LivraisonVenteValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureVenteValue factureVente : listFactureByClientId){
				
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
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
						
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

	private FactureNonRegleValue factureToFactureNonRegle(FactureVenteValue factureVente) {
		
		FactureNonRegleValue factureNonRegle = new FactureNonRegleValue();
		
		factureNonRegle.setId(factureVente.getId());
		factureNonRegle.setNumFacture(factureVente.getReference());
		factureNonRegle.setDate(factureVente.getDate());
		factureNonRegle.setMontantFacture(factureVente.getMontantTTC());
		
		return factureNonRegle;
	}

	private LivraisonNonRegleValue blToBLNonRegle(LivraisonVenteValue livraisonVente) {

		LivraisonNonRegleValue bonLivraisonNonRegle = new LivraisonNonRegleValue();
		
		bonLivraisonNonRegle.setId(livraisonVente.getId());
		bonLivraisonNonRegle.setNumBL(livraisonVente.getReference());
		bonLivraisonNonRegle.setDate(livraisonVente.getDate());
		bonLivraisonNonRegle.setMontantBL(livraisonVente.getMontantTTC());
		
		return bonLivraisonNonRegle;
	}
	
	@Override
	public List<ReglementValue> listeRefReglementCache() {
		
		//logger.info("Delegating request to Persistance layer.");
		
		return reglementPersistance.listeRefReglementCache();
	}
	
	@Override
	public List< RefFactureNonRegleValue> getRefFactureNonRegleByClientId(Long clientId) {
		
		List< RefFactureNonRegleValue> resultatlistRefFactureNonRegle = new ArrayList< RefFactureNonRegleValue>();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementValue> listReglementByClientId = reglementPersistance.getByClientId(clientId);
		
		List<FactureVenteValue> listFactureByClientId = facturePersistance.getByClientId(clientId);
		List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByClientId(clientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementValue reglement : listReglementByClientId){
				
				for(ElementReglementValue element: reglement.getListElementReglement()){
					
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
			
			for(LivraisonVenteValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureVenteValue factureVente : listFactureByClientId){
				
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
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
						
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
	public List< RefLivraisonNonRegleValue> getRefBLNonRegleByClientId(Long clientId) {
		
		List< RefLivraisonNonRegleValue> resultatlistRefBLNonRegle = new ArrayList< RefLivraisonNonRegleValue>();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementValue> listReglementByClientId = reglementPersistance.getByClientId(clientId);
		
		List<FactureVenteValue> listFactureByClientId = facturePersistance.getByClientId(clientId);
		List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByClientId(clientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementValue reglement : listReglementByClientId){
				
				for(ElementReglementValue element: reglement.getListElementReglement()){
					
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
			
			for(LivraisonVenteValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureVenteValue factureVente : listFactureByClientId){
				
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
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
						
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
	
	private String getNumeroReglement(final Calendar pDateBonFacture) {

		Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumReglementReference();
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
		vGuichetValeur.setNumReferenceReglementCourante(new Long(
				vNumGuichetFacture + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine
				.modifierGuichetReglementAnnuel(vGuichetValeur);
		return vNumFacture.toString();
	}

	@Override
	public ValidateReglementResultValue validateByGroupeClientId(Long groupeClientId) {

		
		ValidateReglementResultValue result = new ValidateReglementResultValue();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementValue> listReglementByClientId = reglementPersistance.getByGroupeClientId(groupeClientId);
		
		List<FactureVenteValue> listFactureByClientId = facturePersistance.getByGroupeClientId(groupeClientId);
		List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByGroupeClientId(groupeClientId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementValue reglement : listReglementByClientId){
				
				for(ElementReglementValue element: reglement.getListElementReglement()){
					
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
			
			for(LivraisonVenteValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureVenteValue factureVente : listFactureByClientId){
				
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
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
						
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
	public List<RefFactureNonRegleValue> getRefFactureNonRegleByGroupeId(Long groupeId) {

		
		List< RefFactureNonRegleValue> resultatlistRefFactureNonRegle = new ArrayList< RefFactureNonRegleValue>();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
	//	List<ReglementValue> listReglementByClientId = reglementPersistance.getByClientId(clientId);
		
		List<ReglementValue> listReglementByClientId = reglementPersistance.getByGroupeClientId(groupeId);
		
	//	List<FactureVenteValue> listFactureByClientId = facturePersistance.getByClientId(clientId);
		
		List<FactureVenteValue> listFactureByClientId = facturePersistance.getByGroupeClientId(groupeId);
		
		//List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByClientId(clientId);
		
		List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByGroupeClientId(groupeId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementValue reglement : listReglementByClientId){
				
				for(ElementReglementValue element: reglement.getListElementReglement()){
					
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
			
			for(LivraisonVenteValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureVenteValue factureVente : listFactureByClientId){
				
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
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantTTC() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantTTC();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantTTC() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantResteARegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantTTC() != null){

						LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
						
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
	public List<RefLivraisonNonRegleValue> getRefBLNonRegleByGroupeId(Long groupeId) {
List< RefLivraisonNonRegleValue> resultatlistRefBLNonRegle = new ArrayList< RefLivraisonNonRegleValue>();
		
		List<FactureNonRegleValue> listFactureNonRegleVentre = new ArrayList<FactureNonRegleValue>();
		List<LivraisonNonRegleValue> listLivraisonNonRegle = new ArrayList<LivraisonNonRegleValue>();
		
		Double factureMontantTotal = ZERO;
		Double factureMontantTotalRegle = ZERO;
		
		Double blMontantTotal = ZERO;
		Double blMontantTotalRegle = ZERO;
		
		List<ReglementValue> listReglementByClientId = reglementPersistance.getByGroupeClientId(groupeId);
		
		List<FactureVenteValue> listFactureByClientId = facturePersistance.getByGroupeClientId(groupeId);
		List<LivraisonVenteValue> listBLByClientId = bonLivraisonPersistance.getByGroupeClientId(groupeId);
		
		Map<String, Double> mapFactureRefMontantRegle = new HashMap<String, Double>();
		Map<String, Double> mapBLRefMontantRegle = new HashMap<String, Double>();
		
		List<String> refBLFromFacture = new ArrayList<String>();
		List<String> refBLFromReglement = new ArrayList<String>();
		
		List<String> refBLFromBL = new ArrayList<String>();
		
		if(listReglementByClientId.size() > 0 ){
			
			for(ReglementValue reglement : listReglementByClientId){
				
				for(ElementReglementValue element: reglement.getListElementReglement()){
					
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
			
			for(LivraisonVenteValue livraisonVente : listBLByClientId){
				
				if(livraisonVente.getReference() != null){
					
					refBLFromBL.add(livraisonVente.getReference());
				}
			}
		}
		
		if(listFactureByClientId.size() > 0 ){
			
			for(FactureVenteValue factureVente : listFactureByClientId){
				
				if(mapFactureRefMontantRegle.containsKey(factureVente.getReference())){
					
					if(factureVente.getMontantHTaxe() != null){
						
						factureMontantTotal = factureMontantTotal + factureVente.getMontantHTaxe();
						
						Double montantFactureRegle = mapFactureRefMontantRegle.get(factureVente.getReference());
						
						if(montantFactureRegle != null){
							
							factureMontantTotalRegle = factureMontantTotalRegle + montantFactureRegle;
							
							Double montantResteARegle = factureVente.getMontantHTaxe() - montantFactureRegle;
							
							if(montantResteARegle > 0){
								
								FactureNonRegleValue factureNonRegle = factureToFactureNonRegle(factureVente);
								
								factureNonRegle.setMontantRegle(montantFactureRegle);
								
								listFactureNonRegleVentre.add(factureNonRegle);
								
							}
						}	
					}
				}else{
					
					if(factureVente.getMontantHTaxe() != null){

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
			
			LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getByReference(refBLNonRergle);
			
			if(livraisonVente != null){
				
				if(mapBLRefMontantRegle.containsKey(livraisonVente.getReference())){
					
					if(livraisonVente.getMontantHTaxe() != null){
						
						blMontantTotal = blMontantTotal + livraisonVente.getMontantHTaxe();
						
						Double montantBLRegle = mapBLRefMontantRegle.get(livraisonVente.getReference());
						
						if(montantBLRegle != null){
							
							blMontantTotalRegle = blMontantTotalRegle + montantBLRegle;
							
							Double montantResteARegle = livraisonVente.getMontantHTaxe() - montantBLRegle;
							
							if(montantResteARegle > 0){
								
								LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
								
								livraisonNonRegle.setMontantRegle(montantBLRegle);
								
								listLivraisonNonRegle.add(livraisonNonRegle);
								
							}
						}	
					}
				}else{
					
					if(livraisonVente.getMontantHTaxe() != null){

						LivraisonNonRegleValue livraisonNonRegle = blToBLNonRegle(livraisonVente);
						
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

	@Override
	public String getCurrentReference(Calendar instance, boolean increment) {
		
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel(instance);

		Long vNumGuichetFacture = currentGuichetAnnuel.getNumReferenceReglementInverseCourante();

		// Format du numero de la Bon Reception= AAAA-NN. /
		StringBuilder vNumFacture = new StringBuilder("");
		
		if (currentGuichetAnnuel.getPrefixeReglementInverse()!= null)
			vNumFacture.append(currentGuichetAnnuel.getPrefixeReglementInverse());
		
		//vNumFacture.append(vAnneeCourante);
		vNumFacture.append(String.format("%06d", vNumGuichetFacture));
		// Inserer une nouvelle valeur dans Guichet BonReception. /
		//GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		
		currentGuichetAnnuel.setNumReferenceReglementInverseCourante(new Long(
				vNumGuichetFacture + 1L));
		// Modification de la valeur en base du numéro./
		
		if (increment)
		this.guichetAnnuelDomaine.modifierGuichetReglementInverseAnnuel(currentGuichetAnnuel);
			
		return vNumFacture.toString();
	}
	
	public String getCurrentReferenceDetReglementMensuelByDate(Calendar c, boolean increment, String prfixeTypeReglement) {
		Long vNumGuichetBonLiv = this.guichetierMensuelPersistance.getNextNumDetReglementInverse(c); 
		String vNumGuichetPrefix=this.guichetierMensuelPersistance.getPrefixDetReglementInverse(c);
		
		
		
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

	//	Calendar cal = Calendar.getInstance();
		int anneActuelle = c.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceDetReglementInverseCourante(new Long(vNumGuichetBonLiv + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		      this.guichetierMensuelPersistance.modifierGuichetDetReglementInverseMensuel(vGuichetValeur); 
		
		

		return vNumBonLiv.toString();
	}
	 
	 private boolean estNonVide(String val) {
			return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
		}

	 @Override
		public String getCurrentReferenceMensuelByDate(Calendar c, boolean increment) {
			Long vNumGuichetBonLiv = this.guichetierMensuelPersistance.getNextNumReglementInverse(c); 
			String vNumGuichetPrefix=this.guichetierMensuelPersistance.getPrefixReglementInverse(c);
			
			
			
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
			vGuichetValeur.setNumReferenceReglementInverseCourante(new Long(vNumGuichetBonLiv + 1L));
			/** Modification de la valeur en base du numéro. */
			
			if(increment)
			      this.guichetierMensuelPersistance.modifierGuichetReglementInverseMensuel(vGuichetValeur); 
			
			

			return vNumBonLiv.toString();
		}
		
}
