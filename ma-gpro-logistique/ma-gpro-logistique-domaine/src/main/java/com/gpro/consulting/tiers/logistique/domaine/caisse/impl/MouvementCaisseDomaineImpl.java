package com.gpro.consulting.tiers.logistique.domaine.caisse.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.domaine.caisse.IMouvementCaisseDomaine;
import com.gpro.consulting.tiers.logistique.persistance.caisse.ICaissePersistance;
import com.gpro.consulting.tiers.logistique.persistance.caisse.IMouvementCaissePersistance;

/**
 * Implementation of {@link MouvementCaisseDomaineImpl}.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */
@Component
public class MouvementCaisseDomaineImpl implements IMouvementCaisseDomaine {

	/** The mouvement caisse persistance. */
	@Autowired
	IMouvementCaissePersistance mouvementCaissePersistance;

	/** The caisse persistance. */
	@Autowired
	ICaissePersistance caissePersistance;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MouvementCaisseDomaineImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.
	 * IMouvementCaisseDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.caisse.value.MouvementCaisseValue)
	 */
	@Override
	public String create(MouvementCaisseValue mouvementCaisseValue) {
		CaisseValue caisseValue = caissePersistance.getById(mouvementCaisseValue.getCaisseId());
		if (mouvementCaisseValue.getTypeMouvement().equals("MOIN")) {
			caisseValue.setMontantEspeces(caisseValue.getMontantEspeces() - mouvementCaisseValue.getMontantMouvement());
			caissePersistance.update(caisseValue);
		} else {
			caisseValue.setMontantEspeces(caisseValue.getMontantEspeces() + mouvementCaisseValue.getMontantMouvement());
			caissePersistance.update(caisseValue);
		}
		return mouvementCaissePersistance.create(mouvementCaisseValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.
	 * IMouvementCaisseDomaine#getById(java.lang.Long)
	 */
	@Override
	public MouvementCaisseValue getById(Long id) {
		return mouvementCaissePersistance.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.
	 * IMouvementCaisseDomaine#rechercherMultiCritere(com.gpro.consulting.tiers.
	 * logistique.coordination.caisse.value.
	 * RechercheMulticritereMouvementCaisseValue)
	 */
	@Override
	public ResultatRechercheMouvementCaisseValue rechercherMultiCritere(
			RechercheMulticritereMouvementCaisseValue request) {
		return mouvementCaissePersistance.rechercheMutlicritere(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.
	 * IMouvementCaisseDomaine#update(com.gpro.consulting.tiers.logistique.
	 * coordination.caisse.value.MouvementCaisseValue)
	 */
	@Override
	public String update(MouvementCaisseValue mouvementCaisseValue) {
		CaisseValue caisseValue = caissePersistance.getById(mouvementCaisseValue.getCaisseId());
		if (mouvementCaisseValue.getTypeMouvement() == "MOIN") {
			caisseValue.setMontantEspeces(caisseValue.getMontantEspeces() - mouvementCaisseValue.getMontantMouvement());
			caissePersistance.update(caisseValue);
		} else {
			caisseValue.setMontantEspeces(caisseValue.getMontantEspeces() + mouvementCaisseValue.getMontantMouvement());
			caissePersistance.update(caisseValue);
		}
		return mouvementCaissePersistance.update(mouvementCaisseValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.
	 * IMouvementCaisseDomaine#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		mouvementCaissePersistance.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.caisse.
	 * IMouvementCaisseDomaine#getAll()
	 */
	@Override
	public List<MouvementCaisseValue> getAll() {
		return mouvementCaissePersistance.getAll();
	}

}
