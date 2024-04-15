package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.ITypePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ITypePartieInteresseeService;

/**
 * The Class TypePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class TypePartieInteresseeServiceImpl implements ITypePartieInteresseeService {
  @Autowired
  ITypePartieInteresseeDomaine typePartieInteresseeDomaine;

  @Override
  public String creerTypePartieInteressee(TypeValue pTypeValue) {
    return typePartieInteresseeDomaine.creerTypePartieInteressee(pTypeValue);
  }

  @Override
  public void supprimerTypePartieInteressee(TypeValue pTypeValue) {
	  typePartieInteresseeDomaine.supprimerTypePartieInteressee(pTypeValue);
  }

  @Override
  public String modifierTypePartieInteressee(TypeValue pTypeValue) {
    return typePartieInteresseeDomaine.modifierTypePartieInteressee(pTypeValue);
  }

  /************* recherche by id ******************/
  @Override
  public TypeValue rechercheTypePartieInteresseeParId(TypeValue pTypeValue) {
    return typePartieInteresseeDomaine.rechercheTypePartieInteresseeParId(pTypeValue);
  }

  @Override
  public List < TypeValue > listetypePartieInteressee() {
      return typePartieInteresseeDomaine.listetypePartieInteressee();
  }

}
