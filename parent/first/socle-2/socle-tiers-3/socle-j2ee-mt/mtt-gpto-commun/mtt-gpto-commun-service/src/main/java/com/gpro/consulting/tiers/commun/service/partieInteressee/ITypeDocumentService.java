package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeDocumentValue;

public interface ITypeDocumentService {

  /********************** recherche type doc partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(TypeDocumentValue pTypeDocumentValue);

  /********************** afficher liste type doc partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < TypeDocumentValue > listeTypeDocumentPartieInteressee();
  

	/****************** ajouter type document partie interesse *************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	/********************** supprimer type document partie interesse *****************************/
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	/********************** modifier type document partie interesse *****************************/
	  @Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	
	

}
