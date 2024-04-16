package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;

/**
 * Element Reglement Domaine interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */
public interface IElementReglementAchatDomaine {

	
	public List<ElementReglementAchatValue> getByRefernceFacture(String refernceFacture);
	

	public ResultatRechecheElementReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);

	public Boolean existElementReglementByReferenceBR(String reference);
	
	
	public Boolean existElementReglementByReferenceFacture(String reference);
	
	
	public List<ElementReglementAchatValue> getByRefFactureExact(String refernceFacture);
	
	
	public List<ElementReglementAchatValue> getByRefBLExact(String refernceFacture);
	
	
	public void deleteElementReglementById(Long id);


	public Double getSumMontantPayerByReferenceFacture(String reference);
	
	public Double getSumMontantPayerByReferenceBL(String reference);

}
