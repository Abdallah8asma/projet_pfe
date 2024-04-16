package com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.RechercheMulticritereFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseCoutValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.fichesuiveuse.IFicheSuiveuseDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse.IFicheSuiveuseService;

/**
 * implementation of {@link IFicheSuiveuseService}
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
@Service
@Transactional
public class FicheSuiveuseServiceImpl implements IFicheSuiveuseService{
	
	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveuseServiceImpl.class);

	@Autowired
	IFicheSuiveuseDomaine ficheSuiveuseDomaine;
	
	@Override
	public ResultatRechecheFicheSuiveuseValue rechercherMultiCritere(
			RechercheMulticritereFicheSuiveuseValue request) {
		
		//logger.info("rechercheMulticritere: Delegating request to Domaine layer.");
		
		return ficheSuiveuseDomaine.rechercherMultiCritere(request);
	}

	@Override
	public String create(FicheSuiveuseValue ficheSuiveuseValue) {
		
		//logger.info("createFicheSuiveuse: Delegating request to Domaine layer.");
		
		return ficheSuiveuseDomaine.create(ficheSuiveuseValue);
	}

	@Override
	public FicheSuiveuseValue getById(Long id) {
		
		//logger.info("getFicheSuiveuseById: Delegating request id {} to Domaine layer.", id);
		
		return ficheSuiveuseDomaine.getById(id);
	}

	@Override
	public String update(FicheSuiveuseValue ficheSuiveuseValue) {
		
		//logger.info("updateFicheSuiveuse: Delegating request to Domaine layer.");
		
		return ficheSuiveuseDomaine.update(ficheSuiveuseValue);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("deleteFicheSuiveuse: Delegating request id {} to Domaine layer.", id);
		
		ficheSuiveuseDomaine.delete(id);
	}

	@Override
	public List<FicheSuiveuseValue> getAll() {
		
		//logger.info("getAll: Delegating request to Domaine layer.");
		
		return ficheSuiveuseDomaine.getAll();
	}

	@Override
	public ResultatRechecheFicheSuiveuseCoutValue rechercherMultiCritereAvecCout(
			RechercheMulticritereFicheSuiveuseValue request) {		
		ResultatRechecheFicheSuiveuseCoutValue result = ficheSuiveuseDomaine.rechercherMultiCritereAvecCout(request);
		//logger.info("\n ------ SERVICE : Resultat recherche -------- "+ result.toString() +"\n");
		return result;
	}

}
