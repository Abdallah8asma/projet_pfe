package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.RechercheMulticritereFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;

/**
 * FicheSuiveuse Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public interface IFicheSuiveusePersistance {

	public ResultatRechecheFicheSuiveuseValue rechercherMultiCritere(
			RechercheMulticritereFicheSuiveuseValue request);

	public String create(FicheSuiveuseValue ficheSuiveuseValue);

	public FicheSuiveuseValue getById(Long id);

	public String update(FicheSuiveuseValue ficheSuiveuseValue);

	public void delete(Long id);

	public List<FicheSuiveuseValue> getAll();

	/**
	 * @param referenceMise
	 * @return
	 */
	public FicheSuiveuseValue rechercheFSByRefMise(String referenceMise);


}
