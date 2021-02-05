package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;

/**
 * RouleauFini Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public interface IRouleauFiniDomaine {

	public ResultatRechecheRouleauFiniValue rechercherMultiCritere(RechercheMulticritereRouleauFiniValue request);
	
	public ResultatRechecheRouleauFiniValue rechercherMultiCritereStandard(RechercheMulticritereRouleauFiniStandardValue request);

	public RouleauFiniValue getRouleauFiniById(Long id);

	public String updateRouleauFini(RouleauFiniValue request);

	public void deleteRouleauFini(Long id);

	public String createRouleauFini(RouleauFiniValue request);

	public ResultatRechecheRouleauStandardValue rechercherRouleauInventaire (CritereRechercheRouleauStandardValue pCritereRechecheRouleau);

	public ResultatRechecheRouleauFiniValue getRouleauFiniByInfoModif(String infoModif);
	
	public List<String> getListRefMiseRouleauNonSortie();
	
	public List<String> getListCodeBarreByRefMiseAndIdBSisNull(String refMise);

	public Double getQteExpedierByMiseRef(String refMise);

	public Long getNbrColisExpedierByMiseRef(String refMise);

	public ResultatRechecheRouleauStandardValue rechercherRouleauInventaireByOF(
			CritereRechercheRouleauStandardValue critereRechercheRouleauStandard);
}