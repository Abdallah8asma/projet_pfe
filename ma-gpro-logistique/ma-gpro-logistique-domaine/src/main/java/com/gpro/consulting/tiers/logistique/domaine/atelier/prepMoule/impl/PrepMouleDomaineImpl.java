package com.gpro.consulting.tiers.logistique.domaine.atelier.prepMoule.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.IPrepMoulePersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.prepMoule.IPrepMouleDomaine;


/**
 * Implémentation des méthodes CRUD du preparation moule
 * 
 * @author $Author: Samer Hassen $
 * @version $Revision: 0 $
 */

@Component
public class PrepMouleDomaineImpl implements IPrepMouleDomaine {

	@Autowired
	private IPrepMoulePersistance vPrepMoulePersitance;


	private static final Logger LOGGER = LoggerFactory.getLogger(PrepMouleDomaineImpl.class);

	@Override
		public String creerPrepMoule(PrepMouleValue dto) {

		return vPrepMoulePersitance.creerPrepMoule(dto);
	}

	@Override
	public void supprimerPrepMoule(Long pId) {
		vPrepMoulePersitance.supprimerPrepMoule(pId);
	}

	@Override
	public String modifierPrepMoule(PrepMouleValue dto) {


		return vPrepMoulePersitance.modifierPrepMoule(dto);
	}

	@Override
	public PrepMouleValue recherchePrepMouleParId(Long id) {

		return vPrepMoulePersitance.recherchePrepMouleParId(id);
	}



	@Override
	public ResultatRecherchePrepMouleValue rechercherPrepMouleMultiCritere(RechercheMulticriterePrepMouleValue request) {

		return vPrepMoulePersitance.rechercherPrepMouleMultiCritere(request);
	}

	@Override
	public List<PrepMouleValue> listerPrepMoule() {

		return vPrepMoulePersitance.listerPrepMoule();
	}



	@Override
	public List<PrepMouleValue> getPrepMouleByReference(String referencePrepMoule) {
		return vPrepMoulePersitance.getPrepMouleByReference(referencePrepMoule);
	}

	@Override
	public List<String> getListRefPrepMouleParRefBR(String referenceBR) {
		return vPrepMoulePersitance.getListRefPrepMouleParRefBR(referenceBR);
	}

	@Override
	public String listRefPrepMouleParRefBR(String referenceBR) {
		List<String> listRefPrepMoule = this.getListRefPrepMouleParRefBR(referenceBR);
		////// System.out.println("------listRefPrepMoule----------"+listRefPrepMoule);

		String concatinatedList = "";

		for (int i = 0; i < listRefPrepMoule.size(); i++) {
			String refPrepMoule = listRefPrepMoule.get(i);
			if (i != listRefPrepMoule.size() - 1) {
				concatinatedList += refPrepMoule + ",";
			} else {
				concatinatedList += refPrepMoule;
			}
		}

		////// System.out.println("------concatinatedList----------"+concatinatedList);
		return concatinatedList;
	}

	@Override
	public List<PrepMouleValue> getReferencePrepMoule() {
		// TODO Auto-generated method stub
		return vPrepMoulePersitance.getReferencePrepMoule();
	}

	@Override
	public PrepMouleValue recherchePrepMouleParReference(Long pId) {
		return vPrepMoulePersitance.recherchePrepMouleParReference(pId);
	}

}
