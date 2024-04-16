package com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;

/**
 * Element Reglement Domaine interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */
public interface IElementReglementInverseDomaine {

	
	public List<ElementReglementValue> getByRefernceFacture(String refernceFacture);
	

	public ResultatRechecheElementReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);

	public Boolean existElementReglementByReferenceBL(String reference);
	
	
	public Boolean existElementReglementByReferenceFacture(String reference);
	
	
	public List<ElementReglementValue> getByRefFactureExact(String refernceFacture);
	
	
	public List<ElementReglementValue> getByRefBLExact(String refernceFacture);
	
	
	public void deleteElementReglementById(Long id);


	public Double getSumMontantPayerByReferenceFacture(String reference);


	public Double getSumMontantPayerByReferenceBL(String reference);
	

}
