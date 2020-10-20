package com.gpro.consulting.tiers.logistique.service.gl.suivi.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IEnginDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.suivi.IEnginService;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */

@Service
@Transactional
public class EnginServiceimpl implements IEnginService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(EnginServiceimpl.class);

	@Autowired
	IEnginDomaine engindomaine;
	
	

	@Override
	public EnginValue getById(Long id) {
		
		//logger.info("getENGINById: Delegating request {} to Domaine layer."+id);
		
		return engindomaine.getById(id);
	}

	@Override
	public List<EnginValue> getAll() {
    
		 //logger.info("getAll: Delegating request to Domaine layer.");
		
		return engindomaine.getAll();
	}

	@Override
	public String create(EnginValue enginValue) {
           
           //logger.info("create: Delegating request to Domaine layer.");
		
		return engindomaine.create(enginValue);

	}

	@Override
	public String update(EnginValue enginValue) {
		
		//logger.info("update: Delegating request to Domaine layer.");
		
		return engindomaine.update(enginValue);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("delete: Delegating request to Domaine layer.");
		engindomaine.delete(id);
		
	}

}
