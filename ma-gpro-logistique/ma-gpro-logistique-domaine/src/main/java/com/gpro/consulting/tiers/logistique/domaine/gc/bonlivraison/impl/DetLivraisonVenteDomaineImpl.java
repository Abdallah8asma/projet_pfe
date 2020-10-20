package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IBonLivraisonDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IDetLivraisonVenteDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMagasinDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IDetLivraisonVentePersistance;

/**
 * Implementation of {@link IDetLivraisonVenteDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class DetLivraisonVenteDomaineImpl implements IDetLivraisonVenteDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonDomaineImpl.class);
	
	@Autowired
	private IDetLivraisonVentePersistance detLivraisonVentePersistance;
	
	@Autowired
	private IProduitPersistance produitPersistance ;
	@Autowired
	private IBonLivraisonDomaine livraisonDomaine ;
	
	@Autowired
	private IPartieInteresseeDomaine partieInteresseeDomaine ;
	
	@Autowired
	private IMagasinDomaine magasinDomaine ;
	
	@Override
	public String create(DetLivraisonVenteValue bonLivraisonValue) {
		
		return detLivraisonVentePersistance.create(bonLivraisonValue);
	}

	@Override
	public DetLivraisonVenteValue getById(Long id) {
		
		return detLivraisonVentePersistance.getById(id);
	}

	@Override
	public String update(DetLivraisonVenteValue bonLivraisonValue) {
		
		return detLivraisonVentePersistance.update(bonLivraisonValue);
	}

	@Override
	public void delete(Long id) {
		
		detLivraisonVentePersistance.delete(id);
	} 
	
	@Override
	public ResultatRechecheDetBonLivraisonValue rechercherMultiCritereDetLivraison(
			RechercheMulticritereDetLivraisonValue request) {
		
		ResultatRechecheDetBonLivraisonValue result =detLivraisonVentePersistance.getResultatRechercheMcDetLivraisonValue(request);
		List<DetLivraisonVenteValue> vListeDetails=new ArrayList<DetLivraisonVenteValue>();
		for (DetLivraisonVenteValue det:result.getListDetailLivraison()){
			  LivraisonVenteValue livraisonVenteValue=livraisonDomaine.getBonLivraisonById(det.getLivraisonVenteId());
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
		
		 result.setListDetailLivraison(vListeDetails);
		
		return result;
	}

	

}
