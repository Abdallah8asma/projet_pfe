package com.gpro.consulting.tiers.logistique.domaine.gl.machine.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineOFValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ElementRechecheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.domaine.gl.machine.IMachineDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IMisePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.machine.IMachinePersistance;

/**
 * Implementation of {@link IMachineDomaine}
 *  
 * @author Wahid Gazzah
 * @since 30/03/2016
 * 
 */

@Component
public class MachineDomaineImpl implements IMachineDomaine{

	@Autowired
	private IMachinePersistance machinePersistance;
	
	
	
	@Autowired
	private IMisePersistance misePersistance;
	
	
	
	@Autowired
	private IProduitPersistance produitPersistance;

	@Override
	public MachineValue getById(Long id) {
		
		return machinePersistance.getById(id);
	}
	
	@Override
	public List<MachineValue> getAll() {
		
		return machinePersistance.getAll();
	}

	@Override
	public String create(MachineValue machineValue) {
		return machinePersistance.create(machineValue);
	}

	@Override
	public String update(MachineValue machineValue) {
		return machinePersistance.update(machineValue);
	}

	@Override
	public void delete(Long id) {
		machinePersistance.delete(id);		
	}
	
	@Override
	public List<MachineOFValue> getAllMachineOFvalue() {
		List<MachineOFValue> listeMachineOF =  new ArrayList<>();
		
		
		List<MachineValue> listMachine = machinePersistance.getAll();
		
		
		for(MachineValue machine : listMachine) {
			
			MachineOFValue machineOF = new MachineOFValue();
			
			machineOF.setDesignation(machine.getDesignation());
			machineOF.setVersion(machine.getVersion());
			
			
			
			
			ElementRechecheMiseValue ordreFabrication = getOFenCours(machine.getDesignation());
			
			
			if(ordreFabrication != null) {
				
				
				machineOF.setNumeroOF(ordreFabrication.getReference());
				
				machineOF.setStatus(ordreFabrication.getStatut());
				
				machineOF.setDateDebut(ordreFabrication.getDateIntroduction());
				machineOF.setDateFin(ordreFabrication.getDateFin());
				
				machineOF.setQteProduite(ordreFabrication.getQteProduite());
				
				machineOF.setQteOF(ordreFabrication.getQuantite());
				
				
				ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getDesignationProduit());
				
				if(produit != null)
				{
					machineOF.setProduitReference(produit.getReference());
					machineOF.setProduitDesignation(produit.getDesignation());
					
				}
				
				
				
			}
			
			listeMachineOF.add(machineOF);
			
		}
		
		
		return listeMachineOF;
	}

	private ElementRechecheMiseValue getOFenCours(String designationMachine) {
		RechercheMulticritereMiseValue pRechercheMiseMulitCritere = new RechercheMulticritereMiseValue();
		
		pRechercheMiseMulitCritere.setMachine(designationMachine);
		
		pRechercheMiseMulitCritere.setDateLivraisonDE(Calendar.getInstance());
		
		
		ResultatRechercheMiseValue  resultatRecherche = misePersistance.rechercherMiseMultiCritere(pRechercheMiseMulitCritere);
		
		 Set< ElementRechecheMiseValue > listeElementRechecheMiseValeur = resultatRecherche.getListeElementRechecheMiseValeur();
		
		if (listeElementRechecheMiseValeur.size() > 0) {
			
			ElementRechecheMiseValue elementMinDateFin = resultatRecherche.getListeElementRechecheMiseValeur().iterator().next();
			
			
			
			for(ElementRechecheMiseValue element :listeElementRechecheMiseValeur ) {
				
				
				if(element.getDateFin() != null 
				&& elementMinDateFin.getDateFin() != null
				&& element.getDateFin().before(elementMinDateFin.getDateFin()) )
					
					
					elementMinDateFin = element;
			}
			
			
			return elementMinDateFin;
		}
			
		
	
		
		return null;
	}

}
