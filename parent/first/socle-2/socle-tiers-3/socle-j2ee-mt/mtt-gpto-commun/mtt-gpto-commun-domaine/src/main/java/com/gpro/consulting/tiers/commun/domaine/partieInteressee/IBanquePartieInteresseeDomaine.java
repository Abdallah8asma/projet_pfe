package com.gpro.consulting.tiers.commun.domaine.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.BanqueValue;


public interface IBanquePartieInteresseeDomaine {
	/**************creer categorie partie interesse***************/
	public  String creerBanquePartieInteressee(BanqueValue pBanqueValue);
    
	/**********************supprimer categorie partie interesse*****************************/
	public  void supprimerBanquePartieInteressee(Long pBanqueValue);
	
	/**********************modifier categorie partie interesse*****************************/
	public String modifierBanquePartieInteressee(BanqueValue pBanqueValue);
    
	/**********************recherche  categorie partie interesse par Id*****************************/
	public BanqueValue rechercheBanquePartieInteresseeParId(BanqueValue pBanqueValue);
	
	/**********************afficher  liste  categorie partie interesse *****************************/
	public List<BanqueValue> listeBanquePartieInteressee();
	
	
	
	
	
}
