package com.gpro.consulting.tiers.commun.rest.partieInteressee.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ISitePartieInteresseeService;
@Controller
@RequestMapping("/sitePartieIntersse")
public class SitePartieIntersseRestImpl {
	
	@Autowired
	private ISitePartieInteresseeService sitePartieInteresseeService;

	List<TypeValue> listeType=new ArrayList<>();
	//*************get Type By ID*************//*
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody SiteValue getSitePartieIntesse(@PathVariable Long id) {
		SiteValue pSiteValue=new SiteValue();
		pSiteValue.setId(id);
		return  sitePartieInteresseeService.rechercheCategoriePartieInteresseeParId(pSiteValue);
	}
	
	
	@RequestMapping(value = "/string", method = RequestMethod.GET)
    public @ResponseBody String testStringPersonne(){
	   return "";
    }
	//***********create Type***************//*
		@RequestMapping(value = "/createSite", method = RequestMethod.POST , produces =  "application/json")
		public @ResponseBody String createSite(@RequestBody  final SiteValue pSiteValue) {	
		          return sitePartieInteresseeService.creerSitePartieInteressee(pSiteValue);
	    }
	 
		
		//***********delete Type***************//*
		@RequestMapping(value = "/deleteSite:{id}", method = RequestMethod.DELETE, produces =  "application/json")
		public  @ResponseBody void deleteSite(@PathVariable  Long  id){;
			SiteValue idSupp=new SiteValue();
			idSupp.setId(id.longValue());
			sitePartieInteresseeService.supprimerSitePartieInteressee(idSupp);
	    }
	 
		
		//***********modifier  Type***************//*
		  @RequestMapping(value = "/modifierSite", method = RequestMethod.POST)
		public @ResponseBody String updateSite(@RequestBody  final SiteValue pSiteValue){
			return sitePartieInteresseeService.modifierSitePartieInteressee(pSiteValue);
			
	    }
	 
		//**********all type***********//*
		@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody List<SiteValue> viewAllSite(){
	        return  sitePartieInteresseeService.listeSitePartieInteressee();
	    }
		
}
