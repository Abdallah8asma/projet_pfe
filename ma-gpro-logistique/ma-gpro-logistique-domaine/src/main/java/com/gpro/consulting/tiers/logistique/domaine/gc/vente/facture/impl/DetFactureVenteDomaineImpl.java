package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.IDetFactureVenteDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;

/**
 * Implementation of {@link IDetFactureVenteDomaine}
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class DetFactureVenteDomaineImpl implements IDetFactureVenteDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(DetFactureVenteDomaineImpl.class);

	@Autowired
	private IDetFactureVentePersistance detFactureVentePersistance;
	@Autowired
	IProduitPersistance produitPersistance;
	
	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;
	
	@Override
	public String create(DetFactureVenteValue bonFactureValue) {
		
		return detFactureVentePersistance.create(bonFactureValue);
	}

	@Override
	public DetFactureVenteValue getById(Long id) {
		
		return detFactureVentePersistance.getById(id);
	}

	@Override
	public String update(DetFactureVenteValue bonFactureValue) {
		
		return detFactureVentePersistance.update(bonFactureValue);
	}

	@Override
	public void delete(Long id) {
		
		detFactureVentePersistance.delete(id);
	}

	@Override
	public ResultatRechecheDetFactureVenteValue rechercherMultiCritere(
			RechercheMulticritereDetFactureVenteValue request) {
		
		ResultatRechecheDetFactureVenteValue result = detFactureVentePersistance.rechercherMultiCritere(request);
	
		for(DetFactureVenteValue detFactureVenteValue : result.getList()){
			
			
			
			if(detFactureVenteValue.getProduitId()!=null)
			{
			ProduitValue produitValue=	produitPersistance.rechercheProduitById(detFactureVenteValue.getProduitId());
			detFactureVenteValue.setProduitDesignation(produitValue.getDesignation());
			detFactureVenteValue.setProduitReference(produitValue.getReference());
			
		
			}
			if(detFactureVenteValue.getPartieIntId()!=null)
			{
				PartieInteresseValue partieInteresseValue=partieInteresseePersistance.getPartieInteresseById(detFactureVenteValue.getPartieIntId());
				detFactureVenteValue.setClientAbreviation(partieInteresseValue.getAbreviation());
			}
		}
			
		
		return result;
	}

}
