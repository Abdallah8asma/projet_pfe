package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ArticleEntreeValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheArticleEntreeValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IArticleEntreeDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gs.IEntiteStockPersistance;

@Component
public class ArticleEntreeDomaineImpl implements IArticleEntreeDomaine{

	@Autowired
	IArticleService articleService;
	
	@Autowired
	IEntiteStockPersistance entiteStockPersistance;

	
	@Override
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere,Long idMagasin) {
	    
		ResultatRechecheArticleEntreeValue vResultFinal=new ResultatRechecheArticleEntreeValue();
		java.util.List <ArticleEntreeValue> list=new ArrayList<ArticleEntreeValue>();
		
		ResultatRechecheArticleValue vResult=articleService.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
		
		if(vResult!=null && vResult.getNombreResultaRechercher()>0)
		{   
            for (ArticleValue art:vResult.getArticleValues()){
            ArticleEntreeValue artEntree=new ArticleEntreeValue();
            artEntree.setId(art.getId());
			artEntree.setRef(art.getRef());
			artEntree.setDesignation(art.getDesignation());
			artEntree.setSousFamille(art.getSousFamilleArtEntiteDesignation());
			artEntree.setFamilleArticleDesignation(art.getFamilleArticleDesignation());
			artEntree.setPmp(art.getPmp());
			artEntree.setPu(art.getPu());
			//artEntree.setPrixTotal(prixTotal);
            //TODO a changer integration de Lots 
			
//			EntiteStockValue entiteStock=entiteStockPersistance.rechercheEntiteStockByArticleMagasin(art.getId(), idMagasin);
//			
//			if (entiteStock!=null){
//				artEntree.setQuantiteActuelle(entiteStock.getQteActuelle());
//				artEntree.setNombreConeAct(entiteStock.getConesActuel());
//				artEntree.setNombreFinConeAct(entiteStock.getConesActuel());
//				artEntree.setNombreRouleauxAct(entiteStock.getRouleauxActuel()); 
//			    artEntree.setEmplacement(entiteStock.getEmplacement());
//			    artEntree.setPoidsActuel(entiteStock.getPoidsActuel());
//			}
			
			list.add(artEntree);
			}
			
		}
		
		vResultFinal.setNombreResultaRechercher(new Long (list.size()));
		vResultFinal.setArticleEntree(list);
		return vResultFinal;
	}
	
	
	@Override
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritereFB(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
	    
		ResultatRechecheArticleEntreeValue vResultFinal=new ResultatRechecheArticleEntreeValue();
		java.util.List <ArticleEntreeValue> list=new ArrayList<ArticleEntreeValue>();
		ResultatRechecheArticleValue vResult=articleService.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
		
		if(vResult!=null && vResult.getNombreResultaRechercher()>0)
		{   
            for (ArticleValue art:vResult.getArticleValues()){
            ArticleEntreeValue artEntree=new ArticleEntreeValue();
            artEntree.setId(art.getId());
			artEntree.setRef(art.getRef());
			artEntree.setDesignation(art.getDesignation());
			artEntree.setSousFamille(art.getSousFamilleArtEntiteDesignation());
			artEntree.setFamilleArticleDesignation(art.getFamilleArticleDesignation());
			artEntree.setPmp(art.getPmp());
			artEntree.setPu(art.getPu());
			//artEntree.setPrixTotal(prixTotal);
            //TODO a changer integration de Lots 
			
			list.add(artEntree);
			}
			
		}
		
		vResultFinal.setNombreResultaRechercher(new Long (list.size()));
		vResultFinal.setArticleEntree(list);
		return vResultFinal;
	}


}
