package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IBoutiquePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereMagasinValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IMagasinDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMagasinPersistance;

@Component
public class MagasinDomaineImpl implements IMagasinDomaine{
	
	@Autowired
	IMagasinPersistance magasinPersistance;
	
	
	@Autowired
	IBoutiquePersistance boutiquePersistance;

	@Override
	public String creerMagasin(MagasinValue pMagasinValue) {
		
		
		if(pMagasinValue.getBoutiqueId() != null) {
			
			
			BoutiqueValue boutique = boutiquePersistance.rechercheBoutiqueParId(pMagasinValue.getBoutiqueId() );
			
			if(boutique != null  && boutique.getSocieteId() != null) {
				
				pMagasinValue.setSocieteId(boutique.getSocieteId());
				return magasinPersistance.creerMagasin(pMagasinValue);
			}
			else
			{
				return IConstanteCommerciale.CODE_ERROR_CREATION_MAGASIN_INVALIDE_BOUTIQUE;
				
			}
			

		
			
			
			
		}else
		{
			return IConstanteCommerciale.CODE_ERROR_CREATION_MAGASIN_INVALIDE_BOUTIQUE;
			
		}
			

		
	
	}

	@Override
	public void supprimerMagasin(Long pId) {
		magasinPersistance.supprimerMagasin(pId);
		
	}

	@Override
	public String modifierMagasin(MagasinValue pMagasinValue) {
		
		
         if(pMagasinValue.getBoutiqueId() != null) {
			
			
			BoutiqueValue boutique = boutiquePersistance.rechercheBoutiqueParId(pMagasinValue.getBoutiqueId() );
			
			if(boutique != null && boutique.getSocieteId() != null) {
				
				pMagasinValue.setSocieteId(boutique.getSocieteId());
				
				return magasinPersistance.modifierMagasin(pMagasinValue);
				
			}else
			{
				
				return IConstanteCommerciale.CODE_ERROR_EDITION_MAGASIN_INVALIDE_BOUTIQUE;
				
			}
				
		
		
         
			
		}else
			
			return IConstanteCommerciale.CODE_ERROR_EDITION_MAGASIN_INVALIDE_BOUTIQUE;
		
		
		
		

	}

	@Override
	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue) {
		return magasinPersistance.rechercheMagasinParId(pMagasinValue);
	}

	@Override
	public List<MagasinValue> listeMagasin() {
		return magasinPersistance.listeMagasin();
	}

	@Override
	public List<MagasinValue> listeDepot() {
		return magasinPersistance.listeDepot();
	}

	@Override
	public List<MagasinValue> listePDV() {
		return magasinPersistance.listePDV();
	}

	@Override
	public List<MagasinValue> rechercheMulticritere(RechercheMulticritereMagasinValue pMagasinValue) {
		// TODO Auto-generated method stub
		return magasinPersistance.rechercheMulticritere(pMagasinValue);
	}
	
	
}
