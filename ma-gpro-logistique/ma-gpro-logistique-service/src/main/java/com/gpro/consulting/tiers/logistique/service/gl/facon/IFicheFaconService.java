package com.gpro.consulting.tiers.logistique.service.gl.facon;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.RechercheMulticritereFicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.ResultatRechecheFicheFaconValue;

/**
 * Fiche façon Service interface
 * 
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public interface IFicheFaconService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(FicheFaconValue ficheFaconValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheFaconValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(FicheFaconValue ficheFaconValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<FicheFaconValue> getAll();
	
	
	//Recherche multicritère
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFicheFaconValue rechercherMultiCritere(
				RechercheMulticritereFicheFaconValue request);

}
