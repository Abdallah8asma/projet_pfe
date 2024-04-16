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

import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ControleValue;
import com.gpro.consulting.tiers.logistique.service.gl.fichesuiveuse.IControleService;

/**
 * Controle Controller
 * 
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */

@RestController
@RequestMapping("/controle")
public class ControleRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ControleRestImpl.class);
	
	@Autowired
	private IControleService controlerService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody ControleValue controleValue) {
		
		//logger.info("createControle: Delegating request to Service layer.");
		
		return controlerService.create(controleValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public ControleValue getById(@PathVariable Long id) {
		  
		//logger.info("getControleById: Delegating request id {} to service layer.", id);
		
		return controlerService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody ControleValue controleValue) {
	    
		//logger.info("UpdateControle: Delegating request to service layer.");
		
		return controlerService.update(controleValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("deleteControle: Delegating request id {} to service layer.", id);
		  
		controlerService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<ControleValue> getAll() {
		  
		//logger.info("getAllControle: Delegating request id to service layer.");
		
		return controlerService.getAll();
	}

}
