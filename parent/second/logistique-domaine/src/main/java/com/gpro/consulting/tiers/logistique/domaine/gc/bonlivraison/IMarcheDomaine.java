package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereMarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheMarcheValue;

/**
 * 
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

public interface IMarcheDomaine {
	
	public String create(MarcheValue marcheValue);

	public MarcheValue getById(Long id);

	public String update(MarcheValue marcheValue);

	public void delete(Long id);

	public List<MarcheValue> getAll();
	
	public ResultatRechecheMarcheValue rechercherMultiCritere(
			RechercheMulticritereMarcheValue request);

	public List<MarcheValue> getListById(Long id);
	

}
