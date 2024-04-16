package com.gpro.consulting.tiers.logistique.service.gl.suivi.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.suivi.IRemorqueDomaine;
import com.gpro.consulting.tiers.logistique.service.gl.suivi.IRemorqueService;

/**
 * @author ELAraichi Oussama
 * @since 06/04/2018
 */

@Service
@Transactional
public class RemorqueServiceimpl implements IRemorqueService {

	private static final Logger logger = LoggerFactory.getLogger(PersonnelServiceimpl.class);

	@Autowired
	IRemorqueDomaine remorqueDomaine;
	
	
	@Override
	public RemorqueValue getById(Long id) {
		
        //logger.info("getENGINById: Delegating request {} to Domaine layer."+id);
		
		return remorqueDomaine.getById(id);
	}

	@Override
	public List<RemorqueValue> getAll() {
		 
		//logger.info("getAll: Delegating request to Domaine layer.");
			
			return remorqueDomaine.getAll();
	}

	@Override
	public String create(RemorqueValue remorquelValue) {
         
		 //logger.info("create: Delegating request to Domaine layer.");
			
			return remorqueDomaine.create(remorquelValue);

	}

	@Override
	public String update(RemorqueValue remorquelValue) {
		
        //logger.info("update: Delegating request to Domaine layer.");
		
		return remorqueDomaine.update(remorquelValue);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("delete: Delegating request to Domaine layer.");
		remorqueDomaine.delete(id);
		
	}

}
