package com.gpro.consulting.tiers.commun.service.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
//import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPrixClientDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IPrixClientService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PrixClientServiceImpl implements IPrixClientService {

	@Autowired
	IPrixClientDomaine prixclientDomaine;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerPrixClient(List <ProduitValue> pProduitValue) {

		return prixclientDomaine.creerPrixClientListe(pProduitValue);
	}

	@Override
	public void supprimerPrixClient(Long pId) {
		prixclientDomaine.supprimerPrixClient(pId);

	}

	@Override
	public String modifierPrixClient(PrixClientValue pPrixClientValue) {

		return prixclientDomaine.modifierPrixClient(pPrixClientValue);
	}

	@Override
	public PrixClientValue recherchePrixClientParId(Long pPrixClientID) {

		return prixclientDomaine.recherchePrixClientById(pPrixClientID);
	}

	@Override
	public List<PrixClientValue> listePrixClient() {

		return prixclientDomaine.listPrixClientValue();
	}
	
	@Override
	public PrixClientValue rechercherPrixClientValue(
			RecherchePrixClientValue pRecherchePrixClientMulitCritere) {
		
		return prixclientDomaine.rechercherPrixClientValue(pRecherchePrixClientMulitCritere);
	}

	@Override
	public List<PrixClientValue> rechchercheMultiCriterePrixClient(
			RecherchePrixClientValue pRecherchePrixClientMulitCritere) {

		return prixclientDomaine.rechchercheMultiCriterePrixClient(pRecherchePrixClientMulitCritere);
	}

	@Override
	public String creerPrixArticleClient(List<ArticleValue> pProduitValue) {
		// TODO Auto-generated method stub
		return prixclientDomaine.creerPrixArticleClient(pProduitValue);
	}
	
	

	/*@Override
	public ResultatRechechePrixClientValue rechercherPrixClientMultiCritere(
			RecherecheMulticriterePrixClientValue pRecherchePrixClientMulitCritere) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public ResultatRechechePrixClientValue rechercherPrixClientMultiCritere(
			RecherecheMulticriterePrixClientValue pRecherchePrixClientMulitCritere) {

		return prixclientDomaine
				.recherch(pRecherchePrixClientMulitCritere);
	}*/

	/*@Override
	public List<PrixClientCacheValue> listePrixClientCache() {
		return prixclientDomaine.listePrixClientCache();
	}*/

}
