package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.BanqueValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IBanquePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IBanquePartieInteresseePersistance;

/**
 * The Class BanquePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class BanquePartieInteresseeDomaineImpl implements
		IBanquePartieInteresseeDomaine {

	@Autowired
	IBanquePartieInteresseePersistance categoriePartieInteresseePersistance;

	/*
	 * methode de creation cathegorie Partie Interessée
	 */
	@Override
	public String creerBanquePartieInteressee(BanqueValue pBanqueValue) {

		return categoriePartieInteresseePersistance
				.creerBanquePartieInteressee(pBanqueValue);
	}

	
	/*******************supprimer categorie partie interesse **********************/
	@Override
	public void supprimerBanquePartieInteressee(
			Long pBanqueValue) {
		categoriePartieInteresseePersistance.supprimerBanquePartieInteressee(pBanqueValue);
		
	}
	
	
	/*******************modifier categorie partie interesse **********************/
	@Override
	public String modifierBanquePartieInteressee(BanqueValue pBanqueValue) {
		return categoriePartieInteresseePersistance.modifierBanquePartieInteressee(pBanqueValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public BanqueValue rechercheBanquePartieInteresseeParId(BanqueValue pBanqueValue) {
		return categoriePartieInteresseePersistance.rechercheBanquePartieInteresseeParId(pBanqueValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public List<BanqueValue> listeBanquePartieInteressee() {
		return categoriePartieInteresseePersistance.listeBanquePartieInteressee();
	}	

}
