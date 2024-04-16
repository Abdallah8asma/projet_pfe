package com.gpro.consulting.tiers.logistique.domaine.gl.fichesuiveuse.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.fichesuiveuse.IControleDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IControlePersistance;

/**
 * implementation of {@link IControleDomaine}
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */

@Component
public class ControleDomaineImpl implements IControleDomaine {
	
	private static final Logger logger = LoggerFactory.getLogger(ControleDomaineImpl.class);
	
	@Autowired
	private IControlePersistance controlePersistance;
	
	@Override
	public String create(ControleValue controleValue) {
		
		return controlePersistance.create(controleValue);
	}

	@Override
	public ControleValue getById(Long id) {
		
		return controlePersistance.getById(id);
	}

	@Override
	public String update(ControleValue controleValue) {
		
		return controlePersistance.update(controleValue);
	}

	@Override
	public void delete(Long id) {
		
		controlePersistance.delete(id);
	}

	@Override
	public List<ControleValue> getAll() {
		
		return controlePersistance.getAll();
	}
}
