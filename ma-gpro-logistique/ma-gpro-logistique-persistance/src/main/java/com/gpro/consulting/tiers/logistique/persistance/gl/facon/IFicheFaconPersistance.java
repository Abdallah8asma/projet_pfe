package com.gpro.consulting.tiers.logistique.persistance.gl.facon;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.RechercheMulticritereFicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconValue;

/**
 * FicheFacon Persistance interface
 * 
* @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface IFicheFaconPersistance {

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
