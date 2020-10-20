package com.gpro.consulting.tiers.logistique.domaine.produitdepot.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMagasinDomaine;
import com.gpro.consulting.tiers.logistique.domaine.produitdepot.IProduitDepotDomaine;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;

/**
 * The Class ProduitDomaineImpl.
 * *@author EL AraichiOussama
 */

@Component
public class ProduitDepotDomaineImpl implements IProduitDepotDomaine {

	/** The produitdepot persistance. */
	@Autowired
	IProduitDepotPersistance produitdepotPersistance;
	
	@Autowired
	private IProduitDomaine produitDomaine ;
	
	@Autowired
	private IMagasinDomaine magasinDomaine ;
	
	
	
	
	@Override
	public List<ProduitDepotValue> listProduitDepot() {
		// TODO Auto-generated method stub
		return produitdepotPersistance.listeProduitDepot() ;
	}



	@Override
	public ResultatRechercheProduitDepotValue rechercherMultiCritereProduitDepot(
			RechercheMulticritereProduitDepotValue request) {
		
		
		
		/*****************/

		ResultatRechercheProduitDepotValue result =produitdepotPersistance.rechercheMulticritere(request);
		Set<ProduitDepotValue> vListeDetails=new HashSet<ProduitDepotValue>();
		for (ProduitDepotValue det:result.getProduitdepotvalues()){
			
			 ProduitValue produitValue=produitDomaine.rechercheProduitById(det.getIdProduit());
			if(produitValue!=null){
				det.setDesignationProduit(produitValue.getDesignation());
				det.setReferenceProduit(produitValue.getReference());
				
			
				
			}
			//
			//det.setDesignationMagasin("");
			MagasinValue magasinRecherche=new MagasinValue();
			magasinRecherche.setId(det.getIdDepot());
			 MagasinValue magasinValue=magasinDomaine.rechercheMagasinParId(magasinRecherche);
			 if(magasinValue!=null){
					det.setDesignationMagasin(magasinValue.getDesignation());
								
			}
			
			
			vListeDetails.add(det);
		}
		
		 result.setProduitdepotvalues( vListeDetails);
		
		return result;
		
		/*****************/
		
		//return produitdepotPersistance.rechercheMulticritere(prRechercheMulticritereProduitDepotValue);
	}



	@Override
	public String creer(ProduitDepotValue pProduitDepotValue) {
		
		return produitdepotPersistance.create(pProduitDepotValue);
	}



	@Override
	public String modifier(ProduitDepotValue pProduitDepotValue) {
		// TODO Auto-generated method stub
		return produitdepotPersistance.modifier(pProduitDepotValue);
	}



	@Override
	public ProduitDepotValue getProduitDepotById(Long pProduitId, Long pDepotId) {
		// TODO Auto-generated method stub
		return produitdepotPersistance.getProduitDepotById(pProduitId, pDepotId);
	}

	

}
