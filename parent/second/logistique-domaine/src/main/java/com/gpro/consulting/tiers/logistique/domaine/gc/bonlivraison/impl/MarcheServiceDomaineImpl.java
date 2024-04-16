package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereMarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheMarcheValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IMarcheDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IMarchePersistance;

/**
 * Implementation of {@link IMarcheDomaine}
 * 
 * @author Wahid Gazzah
 * @since 19/02/2016
 *
 */

@Component
public class MarcheServiceDomaineImpl implements IMarcheDomaine{

	private static final Logger logger = LoggerFactory.getLogger(MarcheServiceDomaineImpl.class);
	
	@Autowired
	private IMarchePersistance marchePersistance;

	@Autowired
	private IPartieInteresseeDomaine partieInteresseDomaine;
	
	
	@Override
	public String create(MarcheValue MarcheValue) {
		
		return marchePersistance.create(MarcheValue);
	}

	@Override
	public MarcheValue getById(Long id) {
		
		return marchePersistance.getById(id);
	}

	@Override
	public String update(MarcheValue MarcheValue) {
		
		return marchePersistance.update(MarcheValue);
	}

	@Override
	public void delete(Long id) {
		
		marchePersistance.delete(id);
	}

	@Override
	public List<MarcheValue> getAll() {
		List<MarcheValue> list=marchePersistance.getAll();
		
		for(MarcheValue marcheValue : list){
		if(marcheValue != null){
			//logger.info("getAll: Delegating testttttttttttttt:56666666::  "+marcheValue.getPartieInteresseId());
		 PartieInteresseValue client = partieInteresseDomaine.getById(marcheValue.getPartieInteresseId());
		 //logger.info("getAll: Delegating testttttttttttttt:::  "+client.getAbreviation());
			if(client != null){
				marcheValue.setPartieIntAbreviation(client.getAbreviation());
			}
		}
	}
		
		
		return list;
	}
	
	@Override
	public ResultatRechecheMarcheValue rechercherMultiCritere(RechercheMulticritereMarcheValue request) {
		return marchePersistance.rechercherMultiCritere(request);
	}
	
	
	@Override
	public List<MarcheValue> getListById(Long id) {
		
		return marchePersistance.getListById(id);
	}
}
