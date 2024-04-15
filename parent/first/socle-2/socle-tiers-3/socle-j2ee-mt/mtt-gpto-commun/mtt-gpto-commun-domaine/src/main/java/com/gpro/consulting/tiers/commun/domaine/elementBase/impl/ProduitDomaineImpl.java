package com.gpro.consulting.tiers.commun.domaine.elementBase.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;

/**
 * The Class ProduitDomaineImpl. *@author med
 */

@Component
public class ProduitDomaineImpl implements IProduitDomaine {

	/** The produit persistance. */
	@Autowired
	IProduitPersistance produitPersistance;

	/** The produit persistance. */
	@Autowired
	IPrixClientPersistance prixClientPersistance;

	@Autowired
	IGroupeClientPersistance groupeClientPersistance;

	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;

	/*
	 * creer produit
	 */
	@Override
	public String creerProduit(ProduitValue pProduitValue) {
		return produitPersistance.creerProduit(pProduitValue);
	}

	/*
	 * supprimer produit
	 */
	@Override
	public void supprimerProduit(Long pProduitId) {
		produitPersistance.supprimerProduit(pProduitId);
	}

	/*
	 * modifier produit
	 */
	@Override
	public String modifierProduit(ProduitValue pProduitValue) {

		if (pProduitValue.getMaterialProduits() != null && pProduitValue.getMaterialProduits().size() > 0)
			pProduitValue.getArticleProduits().addAll(pProduitValue.getMaterialProduits());

		return produitPersistance.modifierProduit(pProduitValue);
	}

	/*
	 * recherche by id produit
	 */
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId) {
		ProduitValue vProduitValue = produitPersistance.rechercheProduitById(pProduitId);
		
		/*
		
		Set<ArticleProduitValue> listMaterial = new HashSet<ArticleProduitValue>();
		Set<ArticleProduitValue> listProduct = new HashSet<ArticleProduitValue>();
		if (vProduitValue.getArticleProduits() != null && vProduitValue.getArticleProduits().size() > 0)
			for (ArticleProduitValue articleProduit : vProduitValue.getArticleProduits()) {

				if (articleProduit.getReferenceArticle() != null && !articleProduit.getReferenceArticle().equals(""))
					listMaterial.add(articleProduit);
				else
					listProduct.add(articleProduit);

			}

		vProduitValue.setMaterialProduits(listMaterial);
		vProduitValue.setArticleProduits(listProduct);
		
		*/

		return vProduitValue;

	}

	/*
	 * liste produit
	 */
	@Override
	public List<ProduitValue> listeProduit() {
		return produitPersistance.listeProduit();
	}

	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritere(
			RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
		return produitPersistance.rechercherProduitMultiCritere(pRechercheProduitMulitCritere);
	}

	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritereClient(
			RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {

		Long piId = Long.parseLong(pRechercheProduitMulitCritere.getPartieInteressee());

		ResultatRechecheProduitValue result = null;

		if (piId != null) {

			PartieInteresseValue pi = partieInteresseePersistance.getPartieInteresseById(piId);

			result = produitPersistance.rechercherProduitMultiCritere(pRechercheProduitMulitCritere);
			Set<ProduitValue> produitList = new HashSet<ProduitValue>();

			// System.out.println("###########
			// pRechercheProduitMulitCritere.getPartieInteressee()) :
			// "+pRechercheProduitMulitCritere.getPartieInteressee());

			for (ProduitValue prod : result.getProduitValues()) {

				prod.setPartieIntersseId(new Long(pRechercheProduitMulitCritere.getPartieInteressee()));

				if (pi.getGroupeClientId() != null && pRechercheProduitMulitCritere.getGroupeClientId() != null
						&& pi.getGroupeClientId().equals(pRechercheProduitMulitCritere.getGroupeClientId())) {
					prod.setGroupeClientId(pi.getGroupeClientId());
				}

				RecherchePrixClientValue vRecherchePrixClientMulitCritere = new RecherchePrixClientValue();
				vRecherchePrixClientMulitCritere.setIdClient(prod.getPartieIntersseId());
				vRecherchePrixClientMulitCritere.setIdProduit(prod.getId());
				PrixClientValue vPrixClient = prixClientPersistance
						.rechcherchePrixClientMC(vRecherchePrixClientMulitCritere);

				if (vPrixClient != null) {
					prod.setPrixSpecial(vPrixClient.getPrixvente());
					prod.setRemise(vPrixClient.getRemise());
				}

				else {
					prod.setPrixSpecial(new Double(0));
					prod.setRemise(new Double(0));
				}

				produitList.add(prod);
			}

			result.setProduitValues(produitList);

		}

		return result;

	}

	@Override
	public ProduitValue rechercheProduitParReference(String reference) {
		ProduitValue produit = produitPersistance.rechercheProduitParReference(reference);

	/*	if (produit != null) {
			Set<ArticleProduitValue> listMaterial = new HashSet<ArticleProduitValue>();
			Set<ArticleProduitValue> listProduct = new HashSet<ArticleProduitValue>();
			if (produit.getArticleProduits() != null && produit.getArticleProduits().size() > 0)
				for (ArticleProduitValue articleProduit : produit.getArticleProduits()) {

					if (articleProduit.getReferenceArticle() != null
							&& !articleProduit.getReferenceArticle().equals(""))
						listMaterial.add(articleProduit);
					else
						listProduct.add(articleProduit);

				}

			produit.setMaterialProduits(listMaterial);
			produit.setArticleProduits(listProduct);

		}
		*/

		return produit;

	}

	@Override
	public Map<Long, ProduitValue> mapProduitById() {
		// logger.info("--retrive mapProduitById, domain cache layer");

		List<ProduitValue> produitList = produitPersistance.listeProduit();

		Map<Long, ProduitValue> map = new HashMap<Long, ProduitValue>();

		for (ProduitValue produit : produitList) {
			map.put(produit.getId(), produit);
		}

		return map;
	}

	@Override
	public List<ProduitValue> rechercheProduitFinance() {
		return produitPersistance.rechercheProduitFinance();
	}

	@Override
	public List<ProduitValue> rechercheProduitNonFinance() {
		// TODO Auto-generated method stub
		return produitPersistance.rechercheProduitNonFinance();
	}

}
