package com.gpro.consulting.tiers.logistique.domaine.gc.achat.echeancier.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.echeancier.IEcheancierFournisseurDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.echeancier.IEcheancierFournisseurPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IElementReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.ITypeReglementAchatPersistance;

/**
 * @author Samer Hassen
 *
 */
@Component
public class EcheancierFournisseurDomaineImpl implements IEcheancierFournisseurDomaine{

	@Autowired
	IEcheancierFournisseurPersistance echeancierFournisseurPersistance;
	
	@Autowired
	private ITypeReglementAchatPersistance typeReglementAchatPersistance;
	
	@Autowired
	private IReglementAchatPersistance reglementAchatPersistance;
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private IElementReglementAchatPersistance elementReglementAchatPersistance;
	
	@Override
	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request) {
		
		List<Long> reglementIds = new ArrayList<Long>();
		
		if(estNonVide(request.getRefBL()) || estNonVide(request.getRefFacture()) ) {
			
			RechercheMulticritereReglementAchatValue reqElementReglement = new RechercheMulticritereReglementAchatValue();
			
			reqElementReglement.setRefBL(request.getRefBL());
			reqElementReglement.setRefFacture(request.getRefFacture());
			
			
			reglementIds = elementReglementAchatPersistance.getReglementIdDistinct(reqElementReglement);
			
			if(reglementIds.size()>0)request.setReglementsId(reglementIds);
		}
		
		ResultatRechecheDetailReglementAchatValue resultatTrie = new ResultatRechecheDetailReglementAchatValue();
		
		ResultatRechecheDetailReglementAchatValue result = echeancierFournisseurPersistance.rechercherMultiCritere(request);
		
		//Conversion Id/Designation
		for(ResultatRechecheDetailReglementAchatElementValue element :result.getList()){
			
			/** typeReglement */
			if(element.getTypeReglementId() != null){
				TypeReglementAchatValue typeReglement = typeReglementAchatPersistance.getById(element.getTypeReglementId());
				element.setTypeReglement(typeReglement.getDesignation());
			}		
			/** numReglement */
			if(element.getReglementId() != null){
				ReglementAchatValue reglement = reglementAchatPersistance.getById(element.getReglementId());
				element.setRefReglement(reglement.getReference());
				element.setDateReglement(reglement.getDate());
				if(reglement.getPartieIntId() != null){
					PartieInteresseValue partieIntersessee = partieInteresseePersistance.getById(reglement.getPartieIntId());
					element.setClientAbreviation(partieIntersessee.getAbreviation());
				}
			}
		}
		
		//Sort
		Collections.sort(result.getList());
		resultatTrie.setNombreResultaRechercher(Long.valueOf(result.getList().size()));
		resultatTrie.setList(result.getList());
		
		return resultatTrie;
	}

	
	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val) && !"undefined".equals(val);
	}



}
