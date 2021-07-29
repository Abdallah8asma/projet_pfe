package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Component
public class PartieInteresseeDomaineImpl implements IPartieInteresseeDomaine {

	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	IBaseInfoPersistance baseInfoPersistance;

	/*
	 * methode de creation Partie Interessée
	 */
	@Override
	public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
		
	

		if (pPartieInteresseValue.getReference() == null || pPartieInteresseValue.getReference().equals("")) {

			
			
			String ref = getCurrentReferenceByFamille(pPartieInteresseValue.getFamillePartieInteressee(),  true);
			pPartieInteresseValue.setReference(ref);

		}
				
		else			
			
		{
			
           if(pPartieInteresseValue.getRefAvantChangement() != null && pPartieInteresseValue.getReference().equals(pPartieInteresseValue.getRefAvantChangement())) {
        		 getCurrentReferenceByFamille(pPartieInteresseValue.getFamillePartieInteressee(),  true);
           }
        	 
			
		}
		
		
		/** creation solde client **/

		return partieInteresseePersistance.creerPartieInteressee(pPartieInteresseValue);
	}

	/**************** supprimer partie interessee *******************/
	@Override
	public void supprimerPartieInteressee(Long pId) {
		partieInteresseePersistance.supprimerPartieInteressee(pId);
	}

	/**************** modifier partie interessee *******************/
	@Override
	public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
		return partieInteresseePersistance.modifierPartieInteressee(pPartieInteresseValue);
	}

	/**************** recherche partie interessee Par Id *******************/

	@Override
	public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue) {
		return partieInteresseePersistance.recherchePartieInteresseeParId(pPartieInteresseValue);
	}

	/**************** liste partie interessee *******************/

	@Override
	public java.util.List<PartieInteresseValue> listePartieInteressee() {
		return partieInteresseePersistance.listePartieInteressee();
	}

	/**********************
	 * recherche multi critere partie interesse
	 ***************************/
	@Override
	public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
			RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere) {
		return partieInteresseePersistance.rechercherPartieInteresseMultiCritere(pRecherchePartieInteresseMulitCritere);
	}

	@Override
	public List<PartieInteresseCacheValue> listePartieInteresseeCache() {
		return partieInteresseePersistance.listePartieInteresseeCache();
	}

	@Override
	public PartieInteresseValue getById(Long id) {

		return partieInteresseePersistance.getById(id);
	}

	@Override
	public String getCurrentReferenceByFamille(Long famillePI, boolean increment) {

		BaseInfoValue baseInfo = baseInfoPersistance.getClientActif();

		//String reference = "";

		if (famillePI.equals(IConstante.PI_FAMILLE_CLIENT)) {

			Long numeroClient = baseInfo.getGuichetClient();

			baseInfo.setGuichetClient(numeroClient + 1l);

			StringBuilder vNumClient = new StringBuilder("");

			if (baseInfo.getPrefixClient() != null)
				vNumClient.append(baseInfo.getPrefixClient());

			if (numeroClient > 0 && numeroClient < 10) {
				vNumClient.append("000");
			} else if (numeroClient >= 10 && numeroClient < 100) {
				vNumClient.append("00");
			}

			else if (numeroClient >= 100 && numeroClient < 1000) {
				vNumClient.append("0");
			}

			vNumClient.append(numeroClient.toString());

			if (increment)
				baseInfoPersistance.update(baseInfo);

			return vNumClient.toString();

		}

		else

		if (famillePI.equals(IConstante.PI_FAMILLE_FOURNISSEUR)) {

			Long numeroFournisseur = baseInfo.getGuichetFournisseur();

			baseInfo.setGuichetFournisseur(numeroFournisseur + 1l);

			StringBuilder vNumFournisseur = new StringBuilder("");

			if (baseInfo.getPrefixFournisseur() != null)
				vNumFournisseur.append(baseInfo.getPrefixFournisseur());

			if (numeroFournisseur > 0 && numeroFournisseur < 10) {
				vNumFournisseur.append("000");
			} else if (numeroFournisseur >= 10 && numeroFournisseur < 100) {
				vNumFournisseur.append("00");
			}

			else if (numeroFournisseur >= 100 && numeroFournisseur < 1000) {
				vNumFournisseur.append("0");
			}

			vNumFournisseur.append(numeroFournisseur.toString());

			if (increment)
				baseInfoPersistance.update(baseInfo);

			return vNumFournisseur.toString();
		}
		
		
		return null;

	}

	@Override
	public Long nbPartieIntByFamille(Long famille,Long boutiqueId) {

		return partieInteresseePersistance.nbPartieIntByFamille(famille,boutiqueId);
	}

	@Override
	public Long getCountRechercherPartieInteresseMultiCritere(
			RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere) {
		// TODO Auto-generated method stub
		return partieInteresseePersistance.getCountRechercherPartieInteresseMultiCritere(pRecherchePartieInteresseMulitCritere);
	}

}
