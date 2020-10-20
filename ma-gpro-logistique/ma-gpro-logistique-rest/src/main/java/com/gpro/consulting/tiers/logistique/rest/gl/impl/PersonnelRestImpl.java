package com.gpro.consulting.tiers.logistique.rest.gl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.PersonnelValue;
import com.gpro.consulting.tiers.logistique.service.gl.suivi.IPersonnelService;

/**
 * Engin Controller
 * 
 * @author ELAraichi Oussama
 * @since 06/04/2018
 *
 */

@RestController
@RequestMapping("/personnel")
public class PersonnelRestImpl {
	
private static final Logger logger = LoggerFactory.getLogger(MachineRestImpl.class);
	
	@Autowired
	private IPersonnelService personnelService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<PersonnelValue> getAll() {
		  
		//logger.info("getAllPersonnel: Delegating request id to service layer.");
		
		return personnelService.getAll();
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public PersonnelValue getById(@PathVariable Long id) {
		  
		//logger.info("getPersonnelById: Delegating request id {} to service layer.", id);
		
		return personnelService.getById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody PersonnelValue personnelValue) {
		
		//logger.info("createControle: Delegating request to Service layer.");
		
		return personnelService.create(personnelValue);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody PersonnelValue personnelValue) {
	    
		//logger.info("Updateengin: Delegating request to service layer.");
		
		return personnelService.update(personnelValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("deleteengin: Delegating request id {} to service layer.", id);
		  
		personnelService.delete(id);
	}


}
