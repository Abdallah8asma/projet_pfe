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

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;
import com.gpro.consulting.tiers.commun.service.partieInteressee.ITypePartieInteresseeService;
@Controller
@RequestMapping("/typePartieIntersse")
public class TypePartieIntersseRestImpl {
	
	@Autowired
	private ITypePartieInteresseeService typePartieInteresseeService;

	List<TypeValue> listeType=new ArrayList<>();
	//*************get Type By ID*************//*
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody TypeValue getTypePartieIntesse(@PathVariable Long id) {
		TypeValue pTypeValue=new TypeValue();
		pTypeValue.setId(id);
		return  typePartieInteresseeService.rechercheTypePartieInteresseeParId(pTypeValue);
	}
	
	
	@RequestMapping(value = "/string", method = RequestMethod.GET)
    public @ResponseBody String testStringTypePi(){
         return "ok";
    }
	//***********create Type***************//*
		@RequestMapping(value = "/createType", method = RequestMethod.POST , produces =  "application/json")
		public @ResponseBody String createType(@RequestBody  final TypeValue typeValue) {	
		          return typePartieInteresseeService.creerTypePartieInteressee(typeValue);
	    }
	 
		
		//***********delete Type***************//*
		@RequestMapping(value = "/deleteType:{id}", method = RequestMethod.DELETE, produces =  "application/json")
		public  @ResponseBody void deleteType(@PathVariable  Long  id){
			TypeValue idSupp=new TypeValue();
			idSupp.setId(id.longValue());
			typePartieInteresseeService.supprimerTypePartieInteressee(idSupp);
	    }
	 
		
		//***********modifier  Type***************//*
		  @RequestMapping(value = "/modifierType", method = RequestMethod.POST)
		public @ResponseBody String updateType(@RequestBody  final TypeValue typeValue){
			return typePartieInteresseeService.modifierTypePartieInteressee(typeValue);
			
	    }
	 
		//**********all type***********//*
		@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody List<TypeValue> viewAllType(){
	        return  typePartieInteresseeService.listetypePartieInteressee();
	    }
		
}
