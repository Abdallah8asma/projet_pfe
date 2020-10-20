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

import com.gpro.consulting.tiers.logistique.coordination.gl.suivi.value.RemorqueValue;
import com.gpro.consulting.tiers.logistique.service.gl.suivi.IRemorqueService;

/**
 * Engin Controller
 * 
 * @author ELAraichi Oussama
 * @since 06/04/2018
 *
 */

@RestController
@RequestMapping("/remorque")
public class RemorqueRestImpl {
	
private static final Logger logger = LoggerFactory.getLogger(MachineRestImpl.class);
	
	@Autowired
	private IRemorqueService remorqueService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	
	   public List<RemorqueValue> getAll() {
		  
		//logger.info("getAllremorque: Delegating request id to service layer.");
		
		return remorqueService.getAll();
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	
	
	   public RemorqueValue getById(@PathVariable Long id) {
		  
		//logger.info("getremorqueById: Delegating request id {} to service layer.", id);
		
		return remorqueService.getById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	   
	public String create(@RequestBody RemorqueValue remorqueValue) {
		
		//logger.info("createremorque: Delegating request to Service layer.");
		
		return remorqueService.create(remorqueValue);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	
	   public String update(@RequestBody RemorqueValue remorqueValue) {
	    
		//logger.info("Updateengin: Delegating request to service layer.");
		
		return remorqueService.update(remorqueValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("deleteengin: Delegating request id {} to service layer.", id);
		  
		remorqueService.delete(id);
	}


}
