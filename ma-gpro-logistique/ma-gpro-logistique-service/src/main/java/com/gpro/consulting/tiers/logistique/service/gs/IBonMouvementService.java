package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonMouvementStockValue;

// TODO: Auto-generated Javadoc
/**
 * The Interface IBonMouvementService.
 */
public interface IBonMouvementService {

	/**
	 * Rechercher bon mouvement multi critere.
	 *
	 * @param pRechercheMulticritereMouvementStockValue
	 *            the recherche multicritere mouvement stock value
	 * @return the resultat recheche bon mouvement stock value
	 */
	// transaction methode
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(
			RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue);

	/**
	 * Creer bon mouvement.
	 *
	 * @param pBonMouvementStockValue
	 *            the bon mouvement stock value
	 * @return the string
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerBonMouvement(BonMouvementStockValue pBonMouvementStockValue);

	/******** modifier bonmouvement stock ***********/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierBonMouvement(BonMouvementStockValue pBonMouvement);

	/******* list bonmouvement stock *********/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<BonMouvementStockValue> listeBonMouvement();

	/******* supprimer bon mouvement *****/
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerBonMouvement(Long pId);

	/******* get bonmouvement stock by type *********/
	public List<BonMouvementStockValue> getByTypeBonMouvement(String type);

	/**************** recherche bon mouvement par id ****************/
	public BonMouvementStockValue rechercheBonMouvementParId(Long pBonMouvementId);

	
	// Added on 09/11/2016, by Zeineb Medimagh
	/*******
	 * get liste Numeros Bon Mouvement par type (Reservation / entree/ sortie)
	 *********/
	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String type);
	
//Added on 09/11/2016, by Zeineb Medimagh
//  	/******* get List Mouvement OF Vue - sortie*********/
//  	public List<MouvementOfVue> getListeMouvementsSortie(Long bonMouvementId);


		// Added on 01/02/2017, by Hajer Amri
		/*******
		 * rechercheBonMouvementParNum
		 *********/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonMouvementStockValue rechercheBonMouvementParNum(String numero);
		
}
