package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.RechercheMulticritereLogistiqueReportingtValue;

/**
 * RouleauFini Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public interface IRouleauFiniPersistance {
	
	public ResultatRechecheRouleauFiniValue rechercherMultiCritere(RechercheMulticritereRouleauFiniValue request);
	
	public ResultatRechecheRouleauFiniValue rechercherMultiCritereStandard(RechercheMulticritereRouleauFiniStandardValue request);

	public String createRouleauFini(RouleauFiniValue request);

	public void deleteRouleauFini(Long id);
	
	public RouleauFiniValue rechercheRouleauFiniParId(Long id);

	public String updateRouleauFini(RouleauFiniValue request);
	
	public RouleauFiniValue getRouleauFiniById(Long id);
	
	public List<RouleauFiniValue> getRouleauFiniListByBarreCodeList(List<String> barreCodeList, Long id);

	public List<RouleauFiniValue> rechercherMultiCritereRouleauFiniStandard(CritereRechercheRouleauStandardValue pCritereRechercheRouleauStandard);

	public ResultatRechecheRouleauFiniValue getRouleauFiniByInfoModif(String infoModif);

	public List<String> getListRefMiseRouleauNonSortie();
	
	public List<String> getListCodeBarreByRefMiseAndIdBSisNull(String refMise);
	
	public List<RouleauFiniValue> rechercherMultiCritereReporting(RechercheMulticritereLogistiqueReportingtValue request);
	
	public List<RouleauFiniValue> getAll();

	List<RouleauFiniValue> getRouleauFiniInventaireListByBarreCodeList(
			List<String> barreCodeList, Long id);

	public Double getQteExpedierByMiseRef(String refMise);

	public Long getNbrColisExpedierByMiseRef(String refMise);
}
