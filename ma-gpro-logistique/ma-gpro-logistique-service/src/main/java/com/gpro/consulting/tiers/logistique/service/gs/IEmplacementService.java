package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface IEmplacementService.
 */
public interface IEmplacementService {
	
	  /**
  	 * Creer emplacement.
  	 *
  	 * @param pEmplacementValue the emplacement value
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String creerEmplacement(EmplacementValue pEmplacementValue);

	  /**
  	 * Supprimer emplacement.
  	 *
  	 * @param pId the id
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)  	public void supprimerEmplacement(Long pId);
	
	  /**
  	 * Modifier emplacement.
  	 *
  	 * @param pEmplacementValue the emplacement value
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String modifierEmplacement(EmplacementValue pEmplacementValue);

	  /**
  	 * Recherche emplacement value par id.
  	 *
  	 * @param pEmplacementValue the emplacement value
  	 * @return the emplacement value
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public EmplacementValue rechercheEmplacementValueParId(EmplacementValue pEmplacementValue);

	  /**
  	 * Liste emplacement value.
  	 *
  	 * @return the list
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public List < EmplacementValue > listeEmplacementValue();
	  

}
