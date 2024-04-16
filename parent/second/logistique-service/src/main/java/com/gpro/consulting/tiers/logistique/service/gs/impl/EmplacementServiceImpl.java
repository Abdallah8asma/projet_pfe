package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IEmplacementDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IEmplacementService;

// TODO: Auto-generated Javadoc
/**
 * The Class EmplacementServiceImpl.
 */
@Service
@Transactional
public class EmplacementServiceImpl   implements IEmplacementService{
    
    /** The emplacement domaine. */
    @Autowired
    IEmplacementDomaine emplacementDomaine;
	
	/*
	 * creer
	 */
	@Override
	public String creerEmplacement(EmplacementValue pEmplacementValue) {
	    return emplacementDomaine.creerEmplacement(pEmplacementValue);
	}

	/* (non-Javadoc)
	 * supprimer
	 */
	@Override
	public void supprimerEmplacement(Long pId) {
		emplacementDomaine.supprimerEmplacement(pId);
		
	}

	/* (non-Javadoc)
	 */
	@Override
	public String modifierEmplacement(EmplacementValue pEmplacementValue) {
		 return emplacementDomaine.modifierEmplacement(pEmplacementValue);
	}

	/* (non-Javadoc)
	 */
	@Override
	public EmplacementValue rechercheEmplacementValueParId(
			EmplacementValue pEmplacementValue) {
		   return emplacementDomaine.rechercheEmplacementValueParId(pEmplacementValue);
	}

	/* (non-Javadoc)
	 */
	@Override
	public List<EmplacementValue> listeEmplacementValue() {
		 return emplacementDomaine.listeEmplacementValue();
	}
	

}
