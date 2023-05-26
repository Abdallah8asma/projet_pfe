
package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class ReceptionAchatValue implements Comparable<ReceptionAchatValue> {

	private Long id;

	private Long siteId;

	private String reference;

	// ajoute par samer 07.05.20 afin d'etre envoye from front
	private String refAvantChangement;

	private String refexterne;

	private Double prixTotal;

	private Calendar dateIntroduction;

	private Calendar dateLivraison;

	private String observations;

	private Long partieIntersseId;

	private boolean blSuppression;

	private Calendar dateSuppression;

	private Calendar dateModification;

	private Calendar dateCreation;

	private List<ProduitReceptionAchatValue> listProduitReceptions;
	private List<TaxeReceptionAchatValue> listTaxeReceptionAchat;
	private Set<DocumentReceptionAchatValue> listDocReceptionAchat = new HashSet<DocumentReceptionAchatValue>();

	private String PartieIntersseAbbreviation;

	private Double quantite;

	private Long idDepot;

	private Boolean facture;

	private Long modepaiementId;

	private List<ProduitReceptionAchatValue> ListSuppDetReceptionAchat;

	// Ajouté par samer 08.04.20

	private String natureLivraison;

	private Double totalPourcentageRemise;

	private Double montantHTaxe;
	private Double montantTaxe;
	private Double montantTTC;
	private Double montantRemise;
	private Double metrageTotal;

	private String refCommande;

	private String type;

	/**
	 * refAvoirRetour est la ref facture vente Avoir Retour utilise pour stock
	 * retour.
	 */

	private String refAvoirRetour;

	private String refBL;

	private String refFacture;

	private Long boutiqueId;
	private Long  bonMouvementEntreeId ;

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Set<DocumentReceptionAchatValue> getListDocReceptionAchat() {
		return listDocReceptionAchat;
	}

	public void setListDocReceptionAchat(Set<DocumentReceptionAchatValue> listDocReceptionAchat) {
		this.listDocReceptionAchat = listDocReceptionAchat;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getRefBL() {
		return refBL;
	}

	public void setRefBL(String refBL) {
		this.refBL = refBL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefAvoirRetour() {
		return refAvoirRetour;
	}

	public void setRefAvoirRetour(String refAvoirRetour) {
		this.refAvoirRetour = refAvoirRetour;
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public Double getMontantHTaxe() {
		return montantHTaxe;
	}

	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}

	public Double getMontantTaxe() {
		return montantTaxe;
	}

	public void setMontantTaxe(Double montantTaxe) {
		this.montantTaxe = montantTaxe;
	}

	public Double getMontantRemise() {
		return montantRemise;
	}

	public void setMontantRemise(Double montantRemise) {
		this.montantRemise = montantRemise;
	}

	public Double getMetrageTotal() {
		return metrageTotal;
	}

	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}

	public Double getTotalPourcentageRemise() {
		return totalPourcentageRemise;
	}

	public void setTotalPourcentageRemise(Double totalPourcentageRemise) {
		this.totalPourcentageRemise = totalPourcentageRemise;
	}

	public String getNatureLivraison() {
		return natureLivraison;
	}

	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public List<TaxeReceptionAchatValue> getListTaxeReceptionAchat() {
		return listTaxeReceptionAchat;
	}

	public void setListTaxeReceptionAchat(List<TaxeReceptionAchatValue> listTaxeReceptionAchat) {
		this.listTaxeReceptionAchat = listTaxeReceptionAchat;
	}

	public Boolean getFacture() {
		return facture;
	}

	public void setFacture(Boolean facture) {
		this.facture = facture;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getPartieIntersseAbbreviation() {
		return PartieIntersseAbbreviation;
	}

	public void setPartieIntersseAbbreviation(String partieIntersseAbbreviation) {
		PartieIntersseAbbreviation = partieIntersseAbbreviation;
	}

	public Double getQuantite() {
		return quantite;
	}

	public List<ProduitReceptionAchatValue> getListProduitReceptions() {
		return listProduitReceptions;
	}

	public void setListProduitReceptions(List<ProduitReceptionAchatValue> listProduitReceptions) {
		this.listProduitReceptions = listProduitReceptions;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	public List<ProduitReceptionAchatValue> getListSuppDetReceptionAchat() {
		return ListSuppDetReceptionAchat;
	}

	public void setListSuppDetReceptionAchat(List<ProduitReceptionAchatValue> listSuppDetReceptionAchat) {
		ListSuppDetReceptionAchat = listSuppDetReceptionAchat;
	}

     

	public Long getBonMouvementEntreeId() {
		return bonMouvementEntreeId;
	}

	public void setBonMouvementEntreeId(Long bonMouvementEntreeId) {
		this.bonMouvementEntreeId = bonMouvementEntreeId;
	}

	@Override
	public int compareTo(ReceptionAchatValue o) {
		ReceptionAchatValue commandeVenteValue = (ReceptionAchatValue) o;
		return (commandeVenteValue.getReference().compareTo(reference));
	}

}
