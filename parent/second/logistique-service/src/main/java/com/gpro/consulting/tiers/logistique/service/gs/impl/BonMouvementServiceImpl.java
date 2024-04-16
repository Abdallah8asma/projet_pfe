package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonMouvementDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IBonMouvementService;


// TODO: Auto-generated Javadoc
/**
 * The Class BonMouvementServiceImpl.
 */
@Service
@Transactional
public class BonMouvementServiceImpl implements IBonMouvementService{

	@Autowired
	IBonMouvementDomaine bonMouvementDomaine;
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.service.IBonMouvementService#rechercherBonMouvementMultiCritere(com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereBonMouvementStockValue)
	 */
	@Override
	public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(
			RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue) {
		return bonMouvementDomaine.rechercherBonMouvementMultiCritere(pRechercheMulticritereMouvementStockValue);
	}
	
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.service.IBonMouvementService#creerBonMouvement(com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue)
	 */
	@Override
	public String creerBonMouvement(
			BonMouvementStockValue pBonMouvementStockValue) {
	  return bonMouvementDomaine.creerBonMouvement(pBonMouvementStockValue);
	}

	/********modifier bon mouvement********/
	@Override
	public String modifierBonMouvement(BonMouvementStockValue pBonMouvement) {
		return bonMouvementDomaine.modifierBonMouvement(pBonMouvement);
	}

	/*******list bon mouvement *****/
	@Override
	public List<BonMouvementStockValue> listeBonMouvement() {
		return bonMouvementDomaine.listeBonMouvement();
	}
    /******supprimer bon mouvement*****/
	@Override
	public void supprimerBonMouvement(Long pId) {
		
		//System.out.println("---Service supprimerBonMouvementStock--idSupp:"+pId);

		bonMouvementDomaine.supprimerBonMouvement(pId);
		
	}

	/**********recherche par type bon mouvement*********/
	@Override
	public List<BonMouvementStockValue> getByTypeBonMouvement(String type) {
		return bonMouvementDomaine.getByTypeBonMouvement(type);
	}

	/********recherche par id******/
	@Override
	public BonMouvementStockValue rechercheBonMouvementParId(
			Long pBonMouvementId) {
		return bonMouvementDomaine.rechercheBonMouvementParId(pBonMouvementId);
	}


	//Added on 09/11/2016, by Zeineb Medimagh
	@Override
	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String type) {
		return bonMouvementDomaine.getListeNumerosParType(type);
	}



	// Added on 01/02/2017, by Hajer Amri
	/*******
	 * get BonMouvement par numero
	 *********/
	//rechercheBonMouvementParNum
	@Override
	public BonMouvementStockValue rechercheBonMouvementParNum(String numero){
		 
		return bonMouvementDomaine.rechercheBonMouvementParNum(numero);
	}
	
	
//	@Override
//	public BonMouvementStockValue getBonMouvementParNum(String numero){
//		 
//		return bonMouvementDomaine.getBonMouvementParNum(numero);
//	}


//	@Override
//	public List<MouvementOfVue> getListeMouvementsSortie(Long bonMouvementId) {
//		return bonMouvementDomaine.getListeMouvementsSortie(bonMouvementId);
//	} 
//	
	

}
