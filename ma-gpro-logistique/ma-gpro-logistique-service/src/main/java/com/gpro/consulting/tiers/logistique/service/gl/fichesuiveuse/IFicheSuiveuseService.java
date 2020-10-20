package com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.RechercheMulticritereFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseCoutValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;

/**
 * FicheSuiveuse Service Interface
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public interface IFicheSuiveuseService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFicheSuiveuseValue rechercherMultiCritere(
			RechercheMulticritereFicheSuiveuseValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(FicheSuiveuseValue ficheSuiveuseValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSuiveuseValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(FicheSuiveuseValue ficheSuiveuseValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<FicheSuiveuseValue> getAll();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFicheSuiveuseCoutValue rechercherMultiCritereAvecCout(
			RechercheMulticritereFicheSuiveuseValue request);

}
