package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IRegionService;

/**
 * @author Ameni Berrich
 *
 */
@RestController
@RequestMapping("/region")
public class RegionRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(RegionRestImpl.class);
	
	@Autowired
	private IRegionService regionService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<RegionValue> getAll() {
		  
		//logger.info("getAll: Delegating request to service layer.");
		  
		return regionService.listeRegion();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody RegionValue regionValue) {
		
		//logger.info("create: Delegating request to Service layer.");
		
		return regionService.creer(regionValue);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestBody RegionValue regionValue) {
	    
		//logger.info("Update: Delegating request to service layer.");
		
		return regionService.modifier(regionValue);
	}
	
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("delete: Delegating request id {} to service layer.", id);
		  
		regionService.supprimer(id);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public RegionValue getById(@PathVariable Long id) {
		  
		//logger.info("Delegating request id: {} to service layer.", id);
		
		return regionService.getById(id);
	}
}
