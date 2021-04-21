package com.gpro.consulting.tiers.logistique.domaine.gc.guichet.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.RechercheMulticritereGuichetMensuelValue;
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
	public Long getNextNumBonReceptionReference(Calendar c) {
		return this.guichetMensuelPersistance.getNextNumBonReceptionReference(c);
	}

	@Override
	public Long modifierGuichetBonReceptionMensuel(GuichetMensuelValue pGuichetValeur) {
		 return this.guichetMensuelPersistance.modifierGuichetBonReceptionMensuel(pGuichetValeur);
	}

	@Override
	public Long getNextNumfactureReference(Calendar c) {
		return this.guichetMensuelPersistance.getNextNumfactureReference(c);
	}

	@Override
	public Long modifierGuichetFactureMensuel(GuichetMensuelValue pGuichetValeur) {
		return this.guichetMensuelPersistance.modifierGuichetFactureMensuel(pGuichetValeur);
	}

	@Override
	public Long getNextNumfactureAvoirReference(Calendar c) {
		return this.guichetMensuelPersistance.getNextNumfactureAvoirReference(c);
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
	public String getPrefixFacture( Calendar c) {
		return this.guichetMensuelPersistance.getPrefixFacture(c);
	}

	@Override
	public String getPrefixFactureAvoir(Calendar c) {
		return this.guichetMensuelPersistance.getPrefixFactureAvoir(c);
	}

	@Override
	public String getPrefixBonReception(Calendar c) {
		return this.guichetMensuelPersistance.getPrefixBonReception(c);
	}

	@Override
	public Long modifierGuichetBonReceptionNonDeclarerMensuel(GuichetMensuelValue vGuichetValeur) {
		 return this.guichetMensuelPersistance.modifierGuichetBonReceptionNonDeclarerMensuel(vGuichetValeur);
		
	}

	@Override
	public Long getNextNumBonReceptionReferenceNonDeclarer(Calendar c) {
		// TODO Auto-generated method stub
		 return this.guichetMensuelPersistance.getNextNumBonReceptionReferenceNonDeclarer(c);
	}

	@Override
	public String getPrefixBonReceptionNonDeclarer(Calendar c ) {
		// TODO Auto-generated method stub
		 return this.guichetMensuelPersistance.getPrefixBonReceptionNonDeclarer(c);
	}

	@Override
	public Long getNextNumfactureAchatReferenceNondeclarer(Calendar c) {
		 return this.guichetMensuelPersistance.getNextNumfactureAchatReferenceNondeclarer(c);
	}

	@Override
	public String getPrefixFactureAchatNondeclarer(Calendar c) {
		return this.guichetMensuelPersistance.getPrefixFactureAchatNondeclarer(c);
	}

	@Override
	public Long modifierGuichetFactureAchatNonDeclarerMensuel(GuichetMensuelValue vGuichetValeur) {
		 return this.guichetMensuelPersistance.modifierGuichetFactureAchatNonDeclarerMensuel(vGuichetValeur);
	}

	@Override
	public GuichetMensuelValue getByAnneeAndMois(Long year, Long month) {
		// TODO Auto-generated method stub
		return guichetMensuelPersistance.getByAnneeAndMois(year,month);
	}

	@Override
	public GuichetMensuelValue getById(Long id) {
		// TODO Auto-generated method stub
		return guichetMensuelPersistance.getById(id);
	}

	@Override
	public String update(GuichetMensuelValue guichetMensuelValue) {
		// TODO Auto-generated method stub
		return guichetMensuelPersistance.update(guichetMensuelValue);
	}

	@Override
	public String create(GuichetMensuelValue guichetMensuelValue) {
		// TODO Auto-generated method stub
		return guichetMensuelPersistance.create(guichetMensuelValue);
	}

	@Override
	public void deleteById(Long id) {
		
		guichetMensuelPersistance.deleteById(id);
		
	}

	@Override
	public List<GuichetMensuelValue> rechercheMultiCritere(RechercheMulticritereGuichetMensuelValue request) {
		// TODO Auto-generated method stub
		return guichetMensuelPersistance.rechercheMultiCritere(request);
	}

	


}
