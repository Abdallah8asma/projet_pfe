package com.gpro.consulting.tiers.logistique.service.gc.boncomptoir;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheBonComptoirValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;

/**
 * BonComptoir Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonComptoirService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonComptoirValue rechercherMultiCritere(
			RechercheMulticritereBonComptoirValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonComptoir(ComptoirVenteValue bonComptoirValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ComptoirVenteValue getBonComptoirById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonComptoir(ComptoirVenteValue bonComptoirValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonComptoir(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(
			List<String> refBonComptoirList, Long factureVenteId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getListBonComptoirRef();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteVue> getListBonComptoirRefByClient(Long idClient);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ComptoirVenteValue> getAll();
	
	//Added on 11/10/2016 by Zeineb Medimagh
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListTraitFaconElementValue getTraitementFaconElementList(
			List<String> refBonComptoirList, Long factureVenteId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteFactureVue> getAllListBonComptoirRefByClient(Long idClient);
	
}
