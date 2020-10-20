package com.gpro.consulting.tiers.logistique.domaine.gc.soldeClient.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.RechercheMulticritereSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.ResultatRechecheSoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.soldeClient.ISoldeClientDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.ISoldeClientPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class SoldeClientDomaineImpl implements ISoldeClientDomaine{

	@Autowired
	private ISoldeClientPersistance soldeClientPersistance;
	//REVIEW
	//@Autowired
	//IGestionnaireCacheService gestionnaireCacheService;
	
	@Autowired
	private IPartieInteresseePersistance partieIntersseePersistnce;
	
	@Override
	public ResultatRechecheSoldeClientValue rechercherMultiCritere(
			RechercheMulticritereSoldeClientValue request) {
		
		ResultatRechecheSoldeClientValue resultat = new ResultatRechecheSoldeClientValue();
	    
		ResultatRechecheSoldeClientValue list = soldeClientPersistance.rechercherMultiCritere(request);
		//Sort
		Collections.sort(list.getList());
		//Affect
		resultat.setNombreResultaRechercher(Long.valueOf(list.getList().size()));
		resultat.setList(list.getList());
		
		return resultat;
	}

	@Override
	public SoldeClientValue getById(Long id) {
		return soldeClientPersistance.getById(id);
	}

	@Override
	public String create(SoldeClientValue soldeValue) {
		return soldeClientPersistance.create(soldeValue);
	}
	
	@Override
	public String update(SoldeClientValue soldeValue) {
		return soldeClientPersistance.update(soldeValue);
	}

	@Override
	public void delete(Long id) {
		soldeClientPersistance.delete(id);
	}
	
	@Override
	public Boolean updateSoldeClientForAllClients() {
		List<PartieInteresseValue> listClient = partieIntersseePersistnce.listePartieInteressee();
		String id = "";
		Boolean rslt = false;
		if(listClient != null){
			
			for(PartieInteresseValue client : listClient){
				
				if(client.getFamillePartieInteressee().equals(IConstante.PI_FAMILLE_CLIENT) && soldeClientPersistance.getByClientId(client.getId()) == null)
				{
					
					SoldeClientValue soldeValue = new SoldeClientValue();
					soldeValue.setBloque(false);
					soldeValue.setDateIntroduction(null);
					soldeValue.setPartIntId(client.getId());
					soldeValue.setSeuil(0l);
					soldeValue.setSoldeInitial(0d);
					soldeValue.setReglementTmp(0d);
					soldeValue.setChiffreAffaireTmp(0d);
					
					id = soldeClientPersistance.create(soldeValue);
					if(id != null){
						rslt = true;
					}else
						rslt = false;
					}
				}
			
			}
		
		return rslt;
		 
	}

	@Override
	public SoldeClientValue getByClientId(Long clientId) {
		
		return soldeClientPersistance.getByClientId(clientId);
	}

	
}
