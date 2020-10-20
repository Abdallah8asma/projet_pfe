package com.gpro.consulting.tiers.commun.domaine.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IGroupeClientDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;

/**
 * The Class GroupeClientImpl.
 * 
 * @author $mohamed
 */

@Component
public class GroupeClientDomaineImpl implements
		IGroupeClientDomaine {

	@Autowired
	IGroupeClientPersistance groupeClientPersistance;

	/*
	 * methode de creation cathegorie Partie Interessée
	 */
	@Override
	public String creerGroupeClient(GroupeClientValue pGroupeClientValue) {

		return groupeClientPersistance
				.creerGroupeClient(pGroupeClientValue);
	}

	
	/*******************supprimer categorie partie interesse **********************/
	@Override
	public void supprimerGroupeClient(
			Long pGroupeClientValue) {
		groupeClientPersistance.supprimerGroupeClient(pGroupeClientValue);
		
	}
	
	
	/*******************modifier categorie partie interesse **********************/
	@Override
	public String modifierGroupeClient(GroupeClientValue pGroupeClientValue) {
		return groupeClientPersistance.modifierGroupeClient(pGroupeClientValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public GroupeClientValue rechercheGroupeClientParId(GroupeClientValue pGroupeClientValue) {
		return groupeClientPersistance.rechercheGroupeClientParId(pGroupeClientValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public List<GroupeClientValue> listeGroupeClient() {
		return groupeClientPersistance.listeGroupeClient();
	}	

}
