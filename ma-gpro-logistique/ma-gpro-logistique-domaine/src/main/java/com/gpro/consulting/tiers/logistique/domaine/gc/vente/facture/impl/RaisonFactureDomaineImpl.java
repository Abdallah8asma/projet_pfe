package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RaisonFactureValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.IRaisonFactureDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IRaisonFacturePersistance;

@Component
public class RaisonFactureDomaineImpl implements IRaisonFactureDomaine{

	@Autowired
	IRaisonFacturePersistance raisonFacturePersistance;
	
	@Override
	public String creerRaisonFacture(
			RaisonFactureValue pRaisonFacture) {
		return raisonFacturePersistance.creerRaisonFacture(pRaisonFacture);
	}

	@Override
	public void supprimerRaisonFacture(Long pId) {
		raisonFacturePersistance.supprimerRaisonFacture(pId);		
	}

	@Override
	public String modifierRaisonFacture(
			RaisonFactureValue pRaisonFactureValue) {
		   return raisonFacturePersistance.modifierRaisonFacture(pRaisonFactureValue);
	}

	@Override
	public RaisonFactureValue rechercheRaisonFactureParId(
			RaisonFactureValue pRaisonFactureValue) {
		return raisonFacturePersistance.rechercheRaisonFactureParId(pRaisonFactureValue);
	}

	@Override
	public List<RaisonFactureValue> listeRaisonFacture() {
		   return raisonFacturePersistance.listeRaisonFacture();
	}
	

}
