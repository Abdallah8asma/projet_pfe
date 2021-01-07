package com.gpro.consulting.tiers.logistique.domaine.gc.guichet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
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

	
	@Override
	public Long getNextNumBonCommandeReference() {
		
		return this.guichetMensuelPersistance.getNextNumBonCommandeReference();
	}

	@Override
	public Long modifierGuichetBonCommandeMensuel(GuichetMensuelValue pGuichetValeur) {
	
		 return this.guichetMensuelPersistance.modifierGuichetBonCommandeMensuel(pGuichetValeur);
	}

	@Override
	public Long getNextNumBonReceptionReference() {
		return this.guichetMensuelPersistance.getNextNumBonReceptionReference();
	}

	@Override
	public Long modifierGuichetBonReceptionMensuel(GuichetMensuelValue pGuichetValeur) {
		 return this.guichetMensuelPersistance.modifierGuichetBonReceptionMensuel(pGuichetValeur);
	}

	@Override
	public Long getNextNumfactureReference() {
		return this.guichetMensuelPersistance.getNextNumfactureReference();
	}

	@Override
	public Long modifierGuichetFactureMensuel(GuichetMensuelValue pGuichetValeur) {
		return this.guichetMensuelPersistance.modifierGuichetFactureMensuel(pGuichetValeur);
	}

	@Override
	public Long getNextNumfactureAvoirReference() {
		return this.guichetMensuelPersistance.getNextNumfactureAvoirReference();
	}

	@Override
	public Long modifierGuichetFactureAvoirMensuel(GuichetMensuelValue pGuichetValeur) {
		return this.guichetMensuelPersistance.modifierGuichetFactureAvoirMensuel(pGuichetValeur);
	}
	
	@Override
	public GuichetMensuelValue getCurrentGuichetMensuel() {
		// TODO Auto-generated method stub
		return guichetMensuelPersistance.getCurrentGuichetMensuel();
	}
	@Override
public String getPrefix() {
		
		return this.guichetMensuelPersistance.getPrefix();
	}

	

	@Override
	public String getPrefixFacture() {
		return this.guichetMensuelPersistance.getPrefixFacture();
	}

	@Override
	public String getPrefixFactureAvoir() {
		return this.guichetMensuelPersistance.getPrefixFactureAvoir();
	}

	@Override
	public String getPrefixBonReception() {
		return this.guichetMensuelPersistance.getPrefixBonReception();
	}

	@Override
	public Long modifierGuichetBonReceptionNonDeclarerMensuel(GuichetMensuelValue vGuichetValeur) {
		 return this.guichetMensuelPersistance.modifierGuichetBonReceptionNonDeclarerMensuel(vGuichetValeur);
		
	}

	@Override
	public Long getNextNumBonReceptionReferenceNonDeclarer() {
		// TODO Auto-generated method stub
		 return this.guichetMensuelPersistance.getNextNumBonReceptionReferenceNonDeclarer();
	}

	@Override
	public String getPrefixBonReceptionNonDeclarer() {
		// TODO Auto-generated method stub
		 return this.guichetMensuelPersistance.getPrefixBonReceptionNonDeclarer();
	}
}
