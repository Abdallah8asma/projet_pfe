package com.gpro.consulting.tiers.logistique.domaine.gl.fichesuiveuse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.RechercheMulticritereFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseCoutValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;

/**
 * FicheSuiveuse Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */

public interface IFicheSuiveuseDomaine {

	public ResultatRechecheFicheSuiveuseValue rechercherMultiCritere(
			RechercheMulticritereFicheSuiveuseValue request);

	public String create(FicheSuiveuseValue ficheSuiveuseValue);

	public FicheSuiveuseValue getById(Long id);

	public String update(FicheSuiveuseValue ficheSuiveuseValue);

	public void delete(Long id);

	public List<FicheSuiveuseValue> getAll();
	
	public ResultatRechecheFicheSuiveuseCoutValue rechercherMultiCritereAvecCout(
			RechercheMulticritereFicheSuiveuseValue request);

	/**
	 * @param referenceMise
	 * Hajer Amri 03/02/2017
	 */
//	public FicheSuiveuseValue rechercheFSByRefMise(String referenceMise);

}
