package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;

/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
public interface IGestionnaireCacheDomaine {

	  /**
	   * Méthode de récupératiuon de la liste des fournisseurs et des clients
	 * @return
	 */
	public List < PartieInteresseCacheValue> getListePartieInteressee();
	  

}
