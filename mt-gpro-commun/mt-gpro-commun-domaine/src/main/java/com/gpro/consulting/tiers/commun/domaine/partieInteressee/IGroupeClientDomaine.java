package com.gpro.consulting.tiers.commun.domaine.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;


public interface IGroupeClientDomaine {
	/**************creer categorie partie interesse***************/
	public  String creerGroupeClient(GroupeClientValue pGroupeClientValue);
    
	/**********************supprimer categorie partie interesse*****************************/
	public  void supprimerGroupeClient(Long pGroupeClientValue);
	
	/**********************modifier categorie partie interesse*****************************/
	public String modifierGroupeClient(GroupeClientValue pGroupeClientValue);
    
	/**********************recherche  categorie partie interesse par Id*****************************/
	public GroupeClientValue rechercheGroupeClientParId(GroupeClientValue pGroupeClientValue);
	
	/**********************afficher  liste  categorie partie interesse *****************************/
	public List<GroupeClientValue> listeGroupeClient();
	
	
	
	
	
}
