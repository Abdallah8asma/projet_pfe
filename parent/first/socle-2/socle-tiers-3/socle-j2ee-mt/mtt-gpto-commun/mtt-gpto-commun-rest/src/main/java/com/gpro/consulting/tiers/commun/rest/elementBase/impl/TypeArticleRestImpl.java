package com.gpro.consulting.tiers.commun.rest.elementBase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;
import com.gpro.consulting.tiers.commun.service.elementBase.ITypeArticleService;

@Controller
@RequestMapping("/typeArticle")
public class TypeArticleRestImpl {
	
	@Autowired
	ITypeArticleService typeArticleService ;
	
	/*************get type article  By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
	public @ResponseBody TypeArticleValue getTypeArtcile(@PathVariable Long id) {
		return  typeArticleService.rechercheTypeArticleById(id);
	}

	@RequestMapping(value = "/string", method = RequestMethod.GET)
    public @ResponseBody String testStringPersonne(){
         return "test";
    }
	
	/**********all type article***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TypeArticleValue> viewAllTypeArticle(){
		return typeArticleService.listeTypeArticle();
    }
	
}
