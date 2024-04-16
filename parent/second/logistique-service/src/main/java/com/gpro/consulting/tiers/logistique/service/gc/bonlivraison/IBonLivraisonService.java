package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ValiderBLPassagerValue;

/**
 * BonLivraison Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonLivraisonService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public LivraisonVenteValue getBonLivraisonById(Long id);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonLivraison(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(
			List<String> refBonLivraisonList, Long factureVenteId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getListBonLivraisonRef();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteVue> getListBonLivraisonRefByClient(Long idClient);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteValue> getAll();
	
	//Added on 11/10/2016 by Zeineb Medimagh
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListTraitFaconElementValue getTraitementFaconElementList(
			List<String> refBonLivraisonList, Long factureVenteId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteFactureVue> getAllListBonLivraisonRefByClient(Long idClient);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteVue> getListBonLivraisonRefByClientAndDeclare(Long idClient);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementListForPassager(ValiderBLPassagerValue request);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceByType(String type, Calendar instance, boolean b);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementListByOF(List<String> refBonLivraisonList, Long factureVenteId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public LivraisonVenteValue getByReference(String reference);
	
}
