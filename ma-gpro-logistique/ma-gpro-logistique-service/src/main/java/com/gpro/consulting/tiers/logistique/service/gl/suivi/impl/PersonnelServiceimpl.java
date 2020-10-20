package com.gpro.consulting.tiers.logistique.service.gl.suivi.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IPersonnelDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.suivi.IPersonnelService;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */

@Service
@Transactional
public class PersonnelServiceimpl implements IPersonnelService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonnelServiceimpl.class);

	@Autowired
	IPersonnelDomaine personnelDomaine;

	@Override
	public PersonnelValue getById(Long id) {
		
		//logger.info("getENGINById: Delegating request {} to Domaine layer."+id);
		return personnelDomaine.getById(id);
	}

	@Override
	public List<PersonnelValue> getAll() {
		
		//logger.info("getAll: Delegating request to Domaine layer.");
		return personnelDomaine.getAll();
	}

	@Override
	public String create(PersonnelValue personnelValue) {
		
		 //logger.info("create: Delegating request to Domaine layer.");
		 return personnelDomaine.create(personnelValue);
	}

	@Override
	public String update(PersonnelValue personnelValue) {
		
		//logger.info("update: Delegating request to Domaine layer.");
		return personnelDomaine.update(personnelValue);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("delete: Delegating request to Domaine layer.");
		personnelDomaine.delete(id);
	}

}
