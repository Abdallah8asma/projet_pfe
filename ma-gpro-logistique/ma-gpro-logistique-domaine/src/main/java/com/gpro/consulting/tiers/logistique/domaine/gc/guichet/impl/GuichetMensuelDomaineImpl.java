package com.gpro.consulting.tiers.logistique.domaine.gc.guichet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetMensuelPersistance;
@Component
public class GuichetMensuelDomaineImpl implements IGuichetMensuelDomaine{

	/** Service Persisantce */
	@Autowired
	IGuichetMensuelPersistance guichetMensuelPersistance;

	@Override
	public Long getNextNumBonLivraisonReference() {
		// TODO Auto-generated method stub
		return this.guichetMensuelPersistance.getNextNumBonLivraisonReference();
	}

	@Override
	public Long getNextNumBonSortieReference() {
		// TODO Auto-generated method stub
		return this.guichetMensuelPersistance.getNextNumBonSortieReference();
	}

	@Override
	public Long modifierGuichetBonLivraisonMensuel(
			GuichetMensuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		 return this.guichetMensuelPersistance.modifierGuichetBonLivraisonMensuel(pGuichetValeur);
	}

	@Override
	public Long modifierGuichetBonSortieMensuel(
			GuichetMensuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return this.guichetMensuelPersistance.modifierGuichetBonSortieMensuel(pGuichetValeur);
	}

	@Override
	public Long getNextNumBonComptoirReference() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifierGuichetBonComptoirMensuel(GuichetMensuelValue vGuichetValeur) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
