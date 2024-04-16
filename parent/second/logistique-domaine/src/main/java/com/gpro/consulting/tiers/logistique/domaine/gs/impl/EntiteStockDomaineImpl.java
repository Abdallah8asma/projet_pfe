package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechercheEntiteStockMouvementValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IEntiteStockDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gs.IEntiteStockPersistance;

@Component
public class EntiteStockDomaineImpl implements IEntiteStockDomaine{
	
	@Autowired
	IEntiteStockPersistance entiteStockPersistance;
	@Autowired
	IArticlePersistance articlePersistance;
	
	@Override
	public ResultatRechecheEntiteStockStockValue rechercherEntiteStockMultiCritere(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
			
		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(pRechercheMulticritereEntiteStockValue);
		
		if(result != null){
			for(EntiteStockValue value : result.getEntiteStock()){
				if(value.getQteActuelle()!=null && value.getPmp()!=null)
					value.setPrixTotal(value.getQteActuelle() * value.getPmp());
				if (value.getArticle() != null)
					value.setStockMin(articlePersistance.getArticleParId(value.getArticle()).getStockMin());
			}
		}
		
		return result;
	}
	/*****liste entite stock****/
	@Override
	public List<EntiteStockValue> listeEntiteStock() {
		  
		  return entiteStockPersistance.listeEntiteStock();
	}
     /**********recherche entite stock mouvement ******************/
	@Override
	public ResultatRechercheEntiteStockMouvementValue rechercherEntiteStockMouvement(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
		return entiteStockPersistance.rechercherEntiteStockMouvement(pRechercheMulticritereEntiteStockValue);
	}

	
	
	
	public IEntiteStockPersistance getEntiteStockPersistance() {
		return entiteStockPersistance;
	}
	public void setEntiteStockPersistance(
			IEntiteStockPersistance entiteStockPersistance) {
		this.entiteStockPersistance = entiteStockPersistance;
	}
	@Override
	public String creerEntiteStock(EntiteStockValue pEntiteStockValue) {
		return entiteStockPersistance.creerEntiteStock(pEntiteStockValue);
	}
	@Override
	public String modifierEntiteStock(EntiteStockValue pEntiteStockValue) {
		return entiteStockPersistance.modifierEntiteStock(pEntiteStockValue);
	}
	@Override
	public void supprimerEntiteStock(Long pId) {
      entiteStockPersistance.supprimerEntiteStock(pId);		
	}
	@Override
	public EntiteStockValue rechercheEntiteStockParId(Long pEntiteStockId) {
		return entiteStockPersistance.rechercheEntiteStockParId(pEntiteStockId);
	}
	
	
}
