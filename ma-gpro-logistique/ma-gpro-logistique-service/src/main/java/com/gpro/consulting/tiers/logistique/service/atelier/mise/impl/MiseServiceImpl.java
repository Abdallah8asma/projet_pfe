package com.gpro.consulting.tiers.logistique.service.atelier.mise.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ElementRechecheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService;

/**
 * Implementation des services du bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
@Service
@Transactional
public class MiseServiceImpl implements IMiseService {

	/** Service Domaine */
	@Autowired
	private IMiseDomaine vMiseDomaine;
	
	@Autowired
	private IRouleauFiniDomaine rouleauFiniDomaine;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerMise(MiseValue pMiseValue) {

		return vMiseDomaine.creerMise(pMiseValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerMise(Long pId) {
		vMiseDomaine.supprimerMise(pId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifierMise(MiseValue pMise) {
		return vMiseDomaine.modifierMise(pMise);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MiseValue rechercheMiseParId(Long pMise) {

		return vMiseDomaine.rechercheMiseParId(pMise);
	}

	@Override
	public ResultatRechercheMiseValue rechercherMiseMultiCritere(
			RechercheMulticritereMiseValue pRechercheMiseMulitCritere) {


		
		//System.out.println("inn rech mise multicritère..");

		ResultatRechercheMiseValue vResultatRecherche = vMiseDomaine.rechercherMiseMultiCritere(pRechercheMiseMulitCritere);

		// Traitement : transformation de l'Id a sa propre Designation
		for (ElementRechecheMiseValue elementRechecheMise : vResultatRecherche.getListeElementRechecheMiseValeur()) {

			// Client, Produit
			Map<String, String> mapA = gestionnaireLogistiqueCacheService.rechercherDesignationParId(
					elementRechecheMise.getAbreviationClient(), elementRechecheMise.getDesignationProduit(), null,
					null);
			// Client
			elementRechecheMise.setAbreviationClientDesignation(mapA.get("client"));
			// Produit (Tissu)
			elementRechecheMise.setReferenceProduit(mapA.get("produitRef"));
			
			
			
			
			
			elementRechecheMise.setQteExpedition(rouleauFiniDomaine.getQteExpedierByMiseRef(elementRechecheMise.getReference()));
			
			elementRechecheMise.setNbrColisExpedition(rouleauFiniDomaine.getNbrColisExpedierByMiseRef(elementRechecheMise.getReference()));
			

		}
		
		return vResultatRecherche;
	}

	@Override
	public List<MiseValue> listerMise() {
		// TODO Auto-generated method stub
		return vMiseDomaine.listerMise();
	}

	@Override
	public TraitementMiseValue getTraitementMiseById(Long id) {
		// TODO Auto-generated method stub
		return vMiseDomaine.getTraitementMiseById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService#
	 * listRefMiseParRefBR(java.lang.String)
	 */
	@Override
	public String listRefMiseParRefBR(String referenceBR) {

		return vMiseDomaine.listRefMiseParRefBR(referenceBR);
	}

	
	@Override
	public List<MiseValue> getReferenceMise() {
		// TODO Auto-generated method stub
		return vMiseDomaine.getReferenceMise();
	}
	
	@Override
	public MiseValue rechercheMiseParReference(String reference) {
		
		return vMiseDomaine.rechercheMiseParReference(reference);
	}
}
