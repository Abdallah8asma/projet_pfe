package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;

/**
 * BonCommande Persistance interface
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 *
 */
public interface IReceptionAchatPersistance {

	public String create(ReceptionAchatValue bonCommandeValue);

	public ReceptionAchatValue getById(Long id);

	public String update(ReceptionAchatValue bonCommandeValue);

	public void delete(Long id);

	public List<ReceptionAchatValue> getAll();

	public ResultatRechecheBonReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionAchatValue request);

	public List<BonReceptionVue> getReferenceBRByFournisseurId(Long idFournisseur);

	public List<ReceptionAchatFactureVue> getListBRByFournisseurId(Long idFournisseur);

	public List<ReceptionAchatValue> getProduitElementList(List<String> refBonReceptionList);

	public List<ReceptionAchatValue> getByFournisseurId(Long clientId);
	
	public ReceptionAchatValue getByReference(String reference);

	public List<ReceptionAchatValue> getByGroupeFournisseurId(Long groupeClientId);

	public boolean existeBC(String reference);

	public  BLReportElementRecapValue getDepenseBRbyMonth(RechercheMulticritereBonReceptionAchatValue request);

	public boolean existeBL(String reference);

	public Double getSommeMontHT(RechercheMulticritereBonReceptionAchatValue requestBL);

	public List<BonReceptionVue> getReferenceBRByFournisseurIdDeclarer(Long idFournisseur);
}
