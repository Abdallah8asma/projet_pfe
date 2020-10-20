package com.gpro.consulting.tiers.logistique.service.gc.guichet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.typeReglement.ITypeReglementDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.guichet.IGuichetAnnuelService;
import com.gpro.consulting.tiers.logistique.service.gc.typeReglement.ITypeReglementService;

/**
 * @author 
 *
 */
@Service
@Transactional
public class GuichetAnnuelServiceImpl implements IGuichetAnnuelService{
	
	@Autowired
	IGuichetAnnuelDomaine guichetAnnuelDomaine;
	
	/*@Override
	public List<GuichetAnnuelValue> getAll() {
		
		return guichetAnnuelDomaine.getAll();
	}*/

	@Override
	public GuichetAnnuelValue getById(Long id) {
		return guichetAnnuelDomaine.getById(id);
	}
	
	  @Override
      public String updateGuichetAnnuel(GuichetAnnuelValue guichetAnnuelValue) {
        
        return guichetAnnuelDomaine.modifierGuichetAnnuel(guichetAnnuelValue);
      }

	

	@Override
	public Long modifierGuichetAvoirAnnuel(GuichetAnnuelValue value) {
		return guichetAnnuelDomaine.modifierGuichetAvoirAnnuel(value);
	}
	
	@Override
	public Long modifierGuichetFactureAnnuel(GuichetAnnuelValue value) {
		return guichetAnnuelDomaine.modifierGuichetFactureAnnuel(value);
	}

	@Override
	public Long modifierGuichetReglementAnnuel(GuichetAnnuelValue value) {
		return guichetAnnuelDomaine.modifierGuichetReglementAnnuel(value);
	}
	
	
}
