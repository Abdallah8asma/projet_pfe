package com.gpro.consulting.tiers.logistique.service.gc.guichet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.RechercheMulticritereGuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.guichet.IGuichetMensuelService;

/**
 * @author 
 *
 */
@Service
@Transactional
public class GuichetMensuelServiceImpl implements IGuichetMensuelService{
	
	@Autowired
	IGuichetMensuelDomaine guichetMensuelDomaine;

	@Override
	public GuichetMensuelValue getById(Long id) {
		// TODO Auto-generated method stub
		return guichetMensuelDomaine.getById(id);
	}

	@Override
	public String update(GuichetMensuelValue guichetMensuelValue) {
		// TODO Auto-generated method stub
		return guichetMensuelDomaine.update(guichetMensuelValue);
	}

	@Override
	public String create(GuichetMensuelValue guichetMensuelValue) {
		// TODO Auto-generated method stub
		return guichetMensuelDomaine.create(guichetMensuelValue);
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		
	    guichetMensuelDomaine.deleteById(id) ;
		
		
		return "OK";
	}

	@Override
	public List<GuichetMensuelValue> rechercheMultiCritere(RechercheMulticritereGuichetMensuelValue request) {
		// TODO Auto-generated method stub
		return guichetMensuelDomaine.rechercheMultiCritere(request);
	}
	
	
	
}
