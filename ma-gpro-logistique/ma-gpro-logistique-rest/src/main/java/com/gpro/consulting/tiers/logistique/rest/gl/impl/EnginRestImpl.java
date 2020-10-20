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

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.EnginValue;
import com.gpro.consulting.tiers.logistique.service.gl.suivi.IEnginService;

/**
 * Engin Controller
 * 
 * @author ELAraichi Oussama
 * @since 06/04/2018
 *
 */

@RestController
@RequestMapping("/engin")
public class EnginRestImpl {
	
private static final Logger logger = LoggerFactory.getLogger(MachineRestImpl.class);
	
	@Autowired
	private IEnginService enginService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<EnginValue> getAll() {
		  
		//logger.info("getAllEngin: Delegating request id to service layer.");
		
		return enginService.getAll();
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public EnginValue getById(@PathVariable Long id) {
		  
		//logger.info("getEnginById: Delegating request id {} to service layer.", id);
		
		return enginService.getById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody EnginValue enginValue) {
		
		//logger.info("createControle: Delegating request to Service layer.");
		
		return enginService.create(enginValue);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody EnginValue enginValue) {
	    
		//logger.info("Updateengin: Delegating request to service layer.");
		
		return enginService.update(enginValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("deleteengin: Delegating request id {} to service layer.", id);
		  
		enginService.delete(id);
	}

}
