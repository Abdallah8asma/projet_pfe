package com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.fichesuiveuse.IControleDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse.IControleService;

/**
 * implementation of {@link IControleService}
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */

@Service
@Transactional
public class ControleServiceImpl implements IControleService {
	
	private static final Logger logger = LoggerFactory.getLogger(ControleServiceImpl.class);

	@Autowired
	IControleDomaine controleDomaine;
	
	@Override
	public String create(ControleValue controleValue) {
		
		//logger.info("createControle: Delegating request to Domaine layer.");
		
		return controleDomaine.create(controleValue);
	}

	@Override
	public ControleValue getById(Long id) {
		
		//logger.info("getControleById: Delegating request {} to Domaine layer."+id);
		
		return controleDomaine.getById(id);
	}

	@Override
	public String update(ControleValue controleValue) {
		
		//logger.info("updateControle: Delegating request to Domaine layer.");
		
		return controleDomaine.update(controleValue);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("deleteControle: Delegating request {} to Domaine layer."+id);
		
		controleDomaine.delete(id);
	}

	@Override
	public List<ControleValue> getAll(){
		
		//logger.info("getAll: Delegating request to Domaine layer.");
		
		return controleDomaine.getAll();
	}
	
}
