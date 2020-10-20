package com.gpro.consulting.tiers.logistique.domaine.gl.facon;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.RechercheMulticritereFicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconValue;

/**
 * Fiche Facon Domaine interface
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface IFicheFaconDomaine {

	public String create(FicheFaconValue ficheFaconValue);

	public FicheFaconValue getById(Long id);

	public String update(FicheFaconValue ficheFaconValue);

	public void delete(Long id);

	public List<FicheFaconValue> getAll();
	
	//Recherche multicritère
	public ResultatRechecheFicheFaconValue rechercherMultiCritere(
				RechercheMulticritereFicheFaconValue request);

	public List<FicheFaconValue> getFicheByRefBonReception(String refBonReception);
}
