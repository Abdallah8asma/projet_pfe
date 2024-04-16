package com.gpro.consulting.tiers.logistique.domaine.gc.echeancier.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl.PartieInteresseeDomaineImpl;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.echeancier.IEcheancierDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.echeancier.IEcheancierPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IElementReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.typeReglement.ITypeReglementPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class EcheancierDomaineImpl implements IEcheancierDomaine{

	@Autowired
	IEcheancierPersistance echeancierPersistance;
	
	@Autowired
	private ITypeReglementPersistance TypeReglementPersistance;
	
	@Autowired
	private IReglementPersistance reglementPersistance;
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private IElementReglementPersistance elementReglementPersistance;
	
	@Override
	public ResultatRechecheDetailReglementValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementValue request) {
		
		List<Long> reglementIds = new ArrayList<Long>();
		
		if(estNonVide(request.getRefBL()) || estNonVide(request.getRefFacture()) ) {
			
			RechercheMulticritereReglementValue reqElementReglement = new RechercheMulticritereReglementValue();
			
			reqElementReglement.setRefBL(request.getRefBL());
			reqElementReglement.setRefFacture(request.getRefFacture());
			
			
			reglementIds = elementReglementPersistance.getReglementIdDistinct(reqElementReglement);
			
			if(reglementIds.size()>0)request.setReglementsId(reglementIds);
		}
		
		ResultatRechecheDetailReglementValue resultatTrie = new ResultatRechecheDetailReglementValue();
		
		ResultatRechecheDetailReglementValue result = echeancierPersistance.rechercherMultiCritere(request);
		
		//Conversion Id/Designation
		for(ResultatRechecheDetailReglementElementValue element :result.getList()){
			
			/** typeReglement */
			if(element.getTypeReglementId() != null){
				TypeReglementValue typeReglement = TypeReglementPersistance.getById(element.getTypeReglementId());
				element.setTypeReglement(typeReglement.getDesignation());
			}		
			/** numReglement */
			if(element.getReglementId() != null){
				ReglementValue reglement = reglementPersistance.getById(element.getReglementId());
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
