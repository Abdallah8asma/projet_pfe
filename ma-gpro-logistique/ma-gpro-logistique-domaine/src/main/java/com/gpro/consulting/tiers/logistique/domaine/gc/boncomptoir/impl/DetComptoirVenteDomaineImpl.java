package com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereDetComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetBonComptoirValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir.IBonComptoirDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir.IDetComptoirVenteDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMagasinDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.IDetComptoirVentePersistance;

/**
 * Implementation of {@link IDetComptoirVenteDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class DetComptoirVenteDomaineImpl implements IDetComptoirVenteDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(BonComptoirDomaineImpl.class);
	
	@Autowired
	private IDetComptoirVentePersistance detComptoirVentePersistance;
	
	@Autowired
	private IProduitPersistance produitPersistance ;
	@Autowired
	private IBonComptoirDomaine livraisonDomaine ;
	
	@Autowired
	private IPartieInteresseeDomaine partieInteresseeDomaine ;
	
	@Autowired
	private IMagasinDomaine magasinDomaine ;
	
	@Override
	public String create(DetComptoirVenteValue bonComptoirValue) {
		
		return detComptoirVentePersistance.create(bonComptoirValue);
	}

	@Override
	public DetComptoirVenteValue getById(Long id) {
		
		return detComptoirVentePersistance.getById(id);
	}

	@Override
	public String update(DetComptoirVenteValue bonComptoirValue) {
		
		return detComptoirVentePersistance.update(bonComptoirValue);
	}

	@Override
	public void delete(Long id) {
		
		detComptoirVentePersistance.delete(id);
	} 
	
	@Override
	public ResultatRechecheDetBonComptoirValue rechercherMultiCritereDetComptoir(
			RechercheMulticritereDetComptoirValue request) {
		
		ResultatRechecheDetBonComptoirValue result =detComptoirVentePersistance.getResultatRechercheMcDetComptoirValue(request);
		List<DetComptoirVenteValue> vListeDetails=new ArrayList<DetComptoirVenteValue>();
		for (DetComptoirVenteValue det:result.getListDetailComptoir()){
			  ComptoirVenteValue livraisonVenteValue=livraisonDomaine.getBonComptoirById(det.getComptoirVenteId());
			if(livraisonVenteValue!=null){
				det.setReferenceBL(livraisonVenteValue.getReference());
				det.setDate(livraisonVenteValue.getDate());
				if(livraisonVenteValue.getPartieIntId()!=null){
					PartieInteresseValue client=partieInteresseeDomaine.getById(livraisonVenteValue.getPartieIntId());
					 if (client!=null){
						 det.setPartieInteressee(client.getRaisonSociale());
					 }
				}
				if(livraisonVenteValue.getIdDepot()!=null){
					 MagasinValue rechercheMagasin=new MagasinValue();
					 rechercheMagasin.setId(livraisonVenteValue.getIdDepot());
					
					 MagasinValue magasin=magasinDomaine.rechercheMagasinParId(rechercheMagasin);
					 
					 if(magasin!=null){
						 det.setDepotDesignation(magasin.getDesignation());
					 }
				}
				
			}
			vListeDetails.add(det);
		}
		
		 result.setListDetailComptoir(vListeDetails);
		
		return result;
	}

	

}
