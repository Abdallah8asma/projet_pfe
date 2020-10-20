package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticriterePackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechechePackageValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IPackageService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;

@Controller
@RequestMapping("/package")
public class PackageRestImpl {

	@Autowired
	private IPackageService ebPackageService;
	
	@Autowired
	private IPartieInteresseeService partieInteresseeService;
	
	@Autowired
	private IGroupeClientService groupeClientService;
	
	
	/*************get Package By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody PackageValue getPackage(@PathVariable Long id) {
		PackageValue pPackageValue=new PackageValue();
		pPackageValue.setId(id);
		return  ebPackageService.recherchePackageParId(pPackageValue);
	}
	
	/**********all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PackageValue> viewAllType(){
		return ebPackageService.listePackage();
    }
	
	
	 @RequestMapping(value = "/creerPackage", method = RequestMethod.POST)
	  public @ResponseBody String creerPackage(@RequestBody PackageValue pPackageValue) {
	      //transformation et transcodage des referenciel 
		  return ebPackageService.creerPackage(pPackageValue);
	  }

	  @RequestMapping(value = "/modifierPackage", method = RequestMethod.POST)
	  public @ResponseBody String modifierPackage(@RequestBody PackageValue pPackageValue) {
	    return this.ebPackageService.modifierPackage(pPackageValue);
	  }

	  @RequestMapping(value = "/supprimerPackage:{id}", method = RequestMethod.DELETE)
	  public @ResponseBody String supprimerPackage(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  PackageValue color= new PackageValue();
		  color.setId(idSupp);
		  ebPackageService.supprimerPackage(idSupp);
		  
		  return "OK";
	  }
	  
	  @RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	  public @ResponseBody ResultatRechechePackageValue rechercheMulticritere(
	    @RequestBody final RechercheMulticriterePackageValue pRechercheMultiCritere) {

		  ResultatRechechePackageValue res = ebPackageService.rechercheMulticritere(pRechercheMultiCritere);
	   
		  for(PackageValue pack : res.getList()) {
			  
			  if(pack.getClientId() != null) pack.setClientAbreviation(partieInteresseeService.getById(pack.getClientId()).getAbreviation());
			  
			  
			  if(pack.getGroupeId() != null) pack.setGroupeAbreviation(groupeClientService.rechercheGroupeClientParId(new GroupeClientValue(pack.getGroupeId())).getDesignation());
		  }
		  
		  
		  return res;
	  }
	  
}
