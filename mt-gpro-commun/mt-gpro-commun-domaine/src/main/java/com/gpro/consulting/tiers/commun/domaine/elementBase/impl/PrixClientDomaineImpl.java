package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPrixClientDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;

//TODO: Auto-generated Javadoc
/**
* The Class PrixClientDomaineImpl.
* @author El Araichi Oussama
*/

@Component
public class PrixClientDomaineImpl implements IPrixClientDomaine {
	
	/** The PrixClient persistance. */
	@Autowired
	IPrixClientPersistance prixClientPersistance;


	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;

	
	
	@Override
	public String creerPrixClient(PrixClientValue pPrixClientValue) {
		// TODO Auto-generated method stub
		return prixClientPersistance.creerPrixClient(pPrixClientValue);
	}

	@Override
	public void supprimerPrixClient(Long pPrixClientID) {
		// TODO Auto-generated method stub
		prixClientPersistance.supprimerPrixClient(pPrixClientID);
		
	}

	@Override
	public String modifierPrixClient(PrixClientValue prixClientValue) {
		// TODO Auto-generated method stub
		return prixClientPersistance.modifierPrixClient(prixClientValue);
	}

	@Override
	public PrixClientValue recherchePrixClientById(Long pPrixClientID) {
		// TODO Auto-generated method stub
		return prixClientPersistance.recherchePrixClientById(pPrixClientID);
	}

	@Override
	public List<PrixClientValue> listPrixClientValue() {
		// TODO Auto-generated method stub
		return prixClientPersistance.listePrixClient();
	}

	@Override
	public String creerPrixClientListe(List<ProduitValue> pProduitValue) {
		// TODO Auto-generated method stub
		if (pProduitValue!=null && pProduitValue.size()>0)
		{
			Long idGroupeClient=pProduitValue.get(0).getGroupeClientId();

			Long famillePartieInteressee=partieInteresseePersistance.getPartieInteresseById(pProduitValue.get(0).getPartieIntersseId()).getFamillePartieInteressee();
		   
		    if (idGroupeClient!=null) {
		    	
		    	RechercheMulticriterePartieInteresseeValue vRecherchePi=new RechercheMulticriterePartieInteresseeValue();
		    	vRecherchePi.setGroupeClientId(idGroupeClient);
		    	
		    	ResultatRechechePartieInteresseeValue result=partieInteresseePersistance.rechercherPartieInteresseMultiCritere(vRecherchePi); 
		    	
		    	if (result.getNombreResultaRechercher()>0) {
		    		
		    		for (PartieInteresseValue pi:result.getPartieInteresseValues()) {
		    			
		    			for (ProduitValue produit:pProduitValue ){
		    				// TODO  RECHERCHE MC PRIX CLIENT 
		    				if(produit.getPrixSpecial()!=null && produit.getPrixSpecial()>0){
		    				RecherchePrixClientValue vRecherchePrixClientMulitCritere =new RecherchePrixClientValue();
		    				vRecherchePrixClientMulitCritere.setIdClient(pi.getId());
		    				vRecherchePrixClientMulitCritere.setIdProduit(produit.getId());
		    				PrixClientValue vPrixClient=this.rechercherPrixClientValue(vRecherchePrixClientMulitCritere);
		    				
		    				
		    				
		    				
		    				//TODO  SI RECHERCHE RETOUNRE VALUE ALORS VprixClient=value 
		    				if (vPrixClient==null)
		    				{
		    				  vPrixClient=new PrixClientValue();	
		    				vPrixClient.setPrixvente(produit.getPrixSpecial());
		    				vPrixClient.setEbproduit(produit.getId());
		    				vPrixClient.setIdpi(pi.getId());
		    				
		    				vPrixClient.setFamillePartieInteressee(famillePartieInteressee);
		    				vPrixClient.setRemise(produit.getRemise());
		    				
		    				prixClientPersistance.creerPrixClient(vPrixClient);
		    				
		    				}
		    				else {
		    					vPrixClient.setPrixvente(produit.getPrixSpecial());
		    					vPrixClient.setRemise(produit.getRemise());
		    					prixClientPersistance.modifierPrixClient(vPrixClient);
		    				}
		    				
		    			}
		    			
		    			}
		    	       		
		    			
		    			
		    		}
		    		
		    		
		    		
		    		
		    	}
		    	
		    	
		    	
		    	
		    	
		    }
			
		    else {		
		for (ProduitValue produit:pProduitValue ){
			// TODO  RECHERCHE MC PRIX CLIENT 
			if(produit.getPrixSpecial()!=null && produit.getPrixSpecial()>0){
			RecherchePrixClientValue vRecherchePrixClientMulitCritere =new RecherchePrixClientValue();
			vRecherchePrixClientMulitCritere.setIdClient(produit.getPartieIntersseId());
			vRecherchePrixClientMulitCritere.setIdProduit(produit.getId());
			PrixClientValue vPrixClient=this.rechercherPrixClientValue(vRecherchePrixClientMulitCritere);
			
			
			
			
			//TODO  SI RECHERCHE RETOUNRE VALUE ALORS VprixClient=value 
			if (vPrixClient==null)
			{
			  vPrixClient=new PrixClientValue();	
			vPrixClient.setPrixvente(produit.getPrixSpecial());
			vPrixClient.setEbproduit(produit.getId());
			vPrixClient.setIdpi(produit.getPartieIntersseId());
			
			vPrixClient.setFamillePartieInteressee(famillePartieInteressee);
			vPrixClient.setRemise(produit.getRemise());
			
			prixClientPersistance.creerPrixClient(vPrixClient);
			
			}
			else {
				vPrixClient.setPrixvente(produit.getPrixSpecial());
				vPrixClient.setRemise(produit.getRemise());
				prixClientPersistance.modifierPrixClient(vPrixClient);
			}
			
		}
		
		}
		    }
		}
		return "persisted";
	}

	@Override
	public PrixClientValue rechercherPrixClientValue(
			RecherchePrixClientValue pRecherchePrixClientMulitCritere) {
		
		return prixClientPersistance.rechcherchePrixClientMC(pRecherchePrixClientMulitCritere);
	}
	
	@Override
	public List<PrixClientValue> rechchercheMultiCriterePrixClient(
			RecherchePrixClientValue pRecherchePrixClientMulitCritere) {
		
		return prixClientPersistance.rechchercheMultiCriterePrixClient(pRecherchePrixClientMulitCritere);
	}

	@Override
	public String creerPrixArticleClient(List<ArticleValue> pProduitValue) {

		// TODO Auto-generated method stub
		if (pProduitValue!=null && pProduitValue.size()>0)
		{
			

			Long idGroupeClient=pProduitValue.get(0).getGroupeClientId();

			Long famillePartieInteressee=partieInteresseePersistance.getPartieInteresseById(pProduitValue.get(0).getPartieIntersseId()).getFamillePartieInteressee();
		   
		    if (idGroupeClient!=null) {
		    	
		    	RechercheMulticriterePartieInteresseeValue vRecherchePi=new RechercheMulticriterePartieInteresseeValue();
		    	vRecherchePi.setGroupeClientId(idGroupeClient);
		    	
		    	ResultatRechechePartieInteresseeValue result=partieInteresseePersistance.rechercherPartieInteresseMultiCritere(vRecherchePi); 
		    	
		    	if (result.getNombreResultaRechercher()>0) {
		    		
		    		for (PartieInteresseValue pi:result.getPartieInteresseValues()) {
		    			
		    			for (ArticleValue produit:pProduitValue ){
		    				// TODO  RECHERCHE MC PRIX CLIENT 
		    				if(produit.getPrixSpecial()!=null && produit.getPrixSpecial()>0){
		    				RecherchePrixClientValue vRecherchePrixClientMulitCritere =new RecherchePrixClientValue();
		    				vRecherchePrixClientMulitCritere.setIdClient(pi.getId());
		    				vRecherchePrixClientMulitCritere.setIdProduit(produit.getId());
		    				PrixClientValue vPrixClient=this.rechercherPrixClientValue(vRecherchePrixClientMulitCritere);
		    				
		    				
		    				
		    				
		    				//TODO  SI RECHERCHE RETOUNRE VALUE ALORS VprixClient=value 
		    				if (vPrixClient==null)
		    				{
		    				  vPrixClient=new PrixClientValue();	
		    				vPrixClient.setPrixvente(produit.getPrixSpecial());
		    				vPrixClient.setEbproduit(produit.getId());
		    				vPrixClient.setIdpi(pi.getId());
		    				
		    				vPrixClient.setFamillePartieInteressee(famillePartieInteressee);
		    				vPrixClient.setRemise(produit.getRemise());
		    				
		    				prixClientPersistance.creerPrixClient(vPrixClient);
		    				
		    				}
		    				else {
		    					vPrixClient.setPrixvente(produit.getPrixSpecial());
		    					vPrixClient.setRemise(produit.getRemise());
		    					prixClientPersistance.modifierPrixClient(vPrixClient);
		    				}
		    				
		    			}
		    			
		    			}
		    	       		
		    			
		    			
		    		}
		    		
		    		
		    		
		    		
		    	}
		    	
		    	
		    	
		    	
		    	
		    }
			
		    else {		
		for (ArticleValue produit:pProduitValue ){
			// TODO  RECHERCHE MC PRIX CLIENT 
			if(produit.getPrixSpecial()!=null && produit.getPrixSpecial()>0){
			RecherchePrixClientValue vRecherchePrixClientMulitCritere =new RecherchePrixClientValue();
			vRecherchePrixClientMulitCritere.setIdClient(produit.getPartieIntersseId());
			vRecherchePrixClientMulitCritere.setIdProduit(produit.getId());
			PrixClientValue vPrixClient=this.rechercherPrixClientValue(vRecherchePrixClientMulitCritere);
			
			
			
			
			//TODO  SI RECHERCHE RETOUNRE VALUE ALORS VprixClient=value 
			if (vPrixClient==null)
			{
			  vPrixClient=new PrixClientValue();	
			vPrixClient.setPrixvente(produit.getPrixSpecial());
			vPrixClient.setEbproduit(produit.getId());
			vPrixClient.setIdpi(produit.getPartieIntersseId());
			
			vPrixClient.setFamillePartieInteressee(famillePartieInteressee);
			vPrixClient.setRemise(produit.getRemise());
			
			prixClientPersistance.creerPrixClient(vPrixClient);
			
			}
			else {
				vPrixClient.setPrixvente(produit.getPrixSpecial());
				vPrixClient.setRemise(produit.getRemise());
				prixClientPersistance.modifierPrixClient(vPrixClient);
			}
			
		}
		
		}
		    }
		}
		return "persisted";
	}


	

	

	

}
