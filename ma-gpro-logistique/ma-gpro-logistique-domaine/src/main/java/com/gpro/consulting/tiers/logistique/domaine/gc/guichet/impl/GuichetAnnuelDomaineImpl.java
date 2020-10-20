package com.gpro.consulting.tiers.logistique.domaine.gc.guichet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetAnnuelPersistance;
@Component
public class GuichetAnnuelDomaineImpl implements IGuichetAnnuelDomaine{

	/** Service Persisantce */
	@Autowired
	IGuichetAnnuelPersistance guichetAnnuelPersistance;
	


	
	@Override
	public Long getNextNumBonCommandeReference() {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.getNextNumBonCommandeReference();
	}

	@Override
	public Long getNextNumFactureReference() {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.getNextNumFactureReference();
	}

	@Override
	public Long getNextNumAvoirReference() {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.getNextNumAvoirReference();
	}

	@Override
	public Long getNextNumReglementReference() {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.getNextNumReglementReference();
	}

	@Override
	public Long modifierGuichetFactureAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.modifierGuichetFactureAnnuel(pGuichetValeur);
	}

	@Override
	public Long modifierGuichetAvoirAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.modifierGuichetAvoirAnnuel(pGuichetValeur);
	}

	@Override
	public Long modifierGuichetBonCommandeAnnuel(
			GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.modifierGuichetBonCommandeAnnuel(pGuichetValeur);
	}

	@Override
	public Long modifierGuichetReglementAnnuel(
			GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return this.guichetAnnuelPersistance.modifierGuichetReglementAnnuel(pGuichetValeur);
	}

	@Override
	public GuichetAnnuelValue getById(Long id) {
		// TODO Auto-generated method stub
		//System.out.println("Domain: "+id);
		return guichetAnnuelPersistance.getById(id);
		
	}
	
	  @Override
      public String modifierGuichetAnnuel(GuichetAnnuelValue pGuichetAnnuelValue) {
        
           
        return guichetAnnuelPersistance.modifierGuichetAnnuel(pGuichetAnnuelValue);
      }

	@Override
	public Long getNextNumBLReference() {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.getNextNumBLReference();
	}

	@Override
	public Long modifierGuichetBLAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBLAnnuel(pGuichetValeur);
	}

	@Override
	public Long getNextNumBonComptoirReference() {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.getNextNumBonComptoirReference();
	}

	@Override
	public Long modifierGuichetBonComptoirAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBonComptoirAnnuel(pGuichetValeur);
	}

	@Override
	public GuichetAnnuelValue getCurrentGuichetAnnuel() {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.getCurrentGuichetAnnuel();
	}

	@Override
	public Long modifierGuichetBRAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBRAnnuel(pGuichetValeur);
	}

	@Override
	public Long modifierGuichetFactureAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		return guichetAnnuelPersistance.modifierGuichetFactureAchatAnnuel(currentGuichetAnnuel);
		
	}

	@Override
	public Long getNextNumReglementAchatReference() {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.getNextNumReglementAchatReference();
	}

	@Override
	public Long modifierGuichetReglementAchatAnnuel(GuichetAnnuelValue vGuichetValeur) {
		return guichetAnnuelPersistance.modifierGuichetReglementAchatAnnuel(vGuichetValeur);
		
	}

	@Override
	public Long modifierGuichetBCAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		return guichetAnnuelPersistance.modifierGuichetBCAchatAnnuel(currentGuichetAnnuel);
		
	}

	@Override
	public Long modifierGuichetBCAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBCAnnuel(currentGuichetAnnuel);
	}

	@Override
	public Long modifierGuichetDevisAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetDevisAchatAnnuel(currentGuichetAnnuel);
	}

	@Override
	public Long modifierGuichetDevisAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetDevisAnnuel(currentGuichetAnnuel);
	}

	@Override
	public Long modifierGuichetFactureAvoirAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetFactureAvoirAchatAnnuel(currentGuichetAnnuel);
	}

	@Override
	public Long modifierGuichetBonStockAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBonStockAnnuel(currentGuichetAnnuel);
	}
	
	@Override
	public Long modifierGuichetBonInventaireAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBonInventaireAnnuel(currentGuichetAnnuel);
	}

	@Override
	public Long modifierGuichetBonTransfertReceptionAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		return guichetAnnuelPersistance.modifierGuichetBonTransfertReceptionAnnuel(currentGuichetAnnuel);
		
	}

	@Override
	public Long modifierGuichetBonTransfertSortieAnnuel(GuichetAnnuelValue currentGuichetAnnuel) {
		return guichetAnnuelPersistance.modifierGuichetBonTransfertSortieAnnuel(currentGuichetAnnuel);
		
	}
	
	

	
	
	

}
