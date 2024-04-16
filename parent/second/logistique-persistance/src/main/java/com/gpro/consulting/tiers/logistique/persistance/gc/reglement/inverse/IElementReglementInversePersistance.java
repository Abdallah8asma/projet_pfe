package com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;

/**
 * Element Reglement Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 22/07/2016
 *
 */
public interface IElementReglementInversePersistance {

	public List<ElementReglementValue> getByRefernceFacture(String refernceFacture);
	
	

	public ResultatRechecheElementReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);



	public List<Long> getReglementIdDistinct(RechercheMulticritereReglementValue reqElementReglement);
	
	
	public Boolean existElementReglementByReferenceBL(String reference);
	
	
	public Boolean existElementReglementByReferenceFacture(String reference);



	public void deleteElementReglementById(Long id);



	public List<ElementReglementValue> getByRefBLExact(String refernceFacture);



	public List<ElementReglementValue> getByRefFactureExact(String refernceFacture);



	public Double getSumMontantPayerByReferenceFacture(String reference);



	public Double getSumMontantPayerByReferenceBL(String reference);

}
