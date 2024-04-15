package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;

public interface ITypePartieInteresseeService {
  /************** creer type partie interesse ***************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerTypePartieInteressee(TypeValue pTypeValue);

  /********************** supprimer ty partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerTypePartieInteressee(TypeValue pTypeValue);

  /********************** modifier type partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierTypePartieInteressee(TypeValue pTypeValue);

  /********************** recherche type partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public TypeValue rechercheTypePartieInteresseeParId(TypeValue pTypeValue);

  /********************** afficher liste type partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < TypeValue > listetypePartieInteressee();

}
