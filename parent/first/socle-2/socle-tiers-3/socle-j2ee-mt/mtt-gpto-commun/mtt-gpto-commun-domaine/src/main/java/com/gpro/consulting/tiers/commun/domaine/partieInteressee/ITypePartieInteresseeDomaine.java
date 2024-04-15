package com.gpro.consulting.tiers.commun.domaine.partieInteressee;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;


public interface ITypePartieInteresseeDomaine {
	/**************creer type partie interesse***************/
	public  String creerTypePartieInteressee(TypeValue pTypeValue);
    
	/**********************supprimer ty partie interesse*****************************/
	public  void supprimerTypePartieInteressee(TypeValue pTypeValue);
	
	/**********************modifier type partie interesse*****************************/
	public String modifierTypePartieInteressee(TypeValue pTypeValue);
    
	/**********************recherche  type partie interesse par Id*****************************/
	public TypeValue rechercheTypePartieInteresseeParId(TypeValue pTypeValue);
	
	/**********************afficher  liste  type partie interesse *****************************/
	public List<TypeValue> listetypePartieInteressee();
	
	

	
	
}
