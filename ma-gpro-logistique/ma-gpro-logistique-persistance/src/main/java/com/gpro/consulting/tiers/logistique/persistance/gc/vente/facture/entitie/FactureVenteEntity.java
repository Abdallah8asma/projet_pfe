package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.entitie;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DocumentLivraisonVenteEntity;

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_FACTUREVENTE)
public class FactureVenteEntity implements Serializable {

	private static final long serialVersionUID = -9152678992387499947L;

	@Id
	@SequenceGenerator(name="FACTUREVENTE_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_CFV_SEQ, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACTUREVENTE_ID_GENERATOR")
    private Long id;
	
	@Column(name="PI_PARTIEINT_ID")
	private Long partieIntId;
	
	@Column(name="REFERENCE")
	private String reference;
	
	@Column(name="DATE")
	private Calendar date;
	
	@Column(name="MONTANTHTAXE")
	private Double montantHTaxe;
	
	@Column(name="MONTANTTTC")
	private Double montantTTC;
			
	@Column(name="MONTANT_TAXE")
	private Double montantTaxe;
					
	@Column(name="MONTANT_REMISE")
	private Double montantRemise;
	
	@Column(name="OBSERVATIONS")
	private String observations;
											
	@Column(name="INFO_LIVRAISON")
	private String infoLivraison;//list des ref bonLivraisons | patterne: ref1-ref2-ref3 ....
													
	@Column(name="METRAGE_TOTAL")
	private Double metrageTotal;
	
	@Column(name="ETAT")
	private String etat;
	
	@Column(name="NATURE_LIVRAISON")
	private String natureLivraison;
	
	@OneToMany(mappedBy = "factureVente", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DetFactureVenteEntity> listDetFactureVente;
	
	@OneToMany(mappedBy = "factureVente", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<TaxeFactureEntity> listTaxeFacture;
	

	/** *** many-to-one association to DocumentFactureVente. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factureVente", cascade = CascadeType.ALL,orphanRemoval=true)
	private Set<DocumentFactureVenteEntity> documentFactureVente;
															
	@Column(name="BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name="DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name="DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Calendar dateModification;
																							
	@Column(name="VERSION")
	private String version;
	
	@Column(name="MODEPAIEMENT_ID")
	private Long modepaiementId;

	@Column(name="ref_factures")
	private String refFactures;
	
	@Column(name="type")
	private String type;
	
	@Column(name="TOTAL_POURCENTAGE_REMISE")
	private Double totalPourcentageRemise;
	
	@Column(name="INFO_LIVRAISON_EXTERNE")
	private String infoLivraisonExterne ;
	
	
	//Added on 25/05/2018
	
	@Column(name="id_marche")
	private Long idMarche ;
	
	
	@Column(name = "DESCRIPTION")
	private Boolean description;
	
	@Column(name = "type_partie_Interessee")
	private Long typePartieInteressee;
	
	
	/** The GroupeClient. */
	@Column(name = "groupe_client_id")
	private Long groupeClientId;
	
	
	@Column(name="raison")
	private String raison;
	
	
	@Column(name="nature")
	private String nature;
	
	
	
	@Column(name = "GC_REGLEMENT_ID")
	private Long reglementId;
	
	
	
	
	
	@Column(name = "DATE_INTRODUCTION")
	private Calendar dateIntroduction;
	
	
	@Column(name = "boutique_id")
	private Long boutiqueId;
	
	@Column(name = "id_depot")
	private Long idDepot;
	
	
	
	@Column(name = "devise")
	private Long devise;
	
	
	@Column(name = "taux_conversion")
	private Double tauxConversion;
	 
	@Column(name = "montant_converti")
	private Double montantConverti;
	
	
	@Column(name = "declarer")
	private Boolean declarer;
	
	
	@Column(name = "force_calcul_montant")
	private boolean forcerCalculMontant;
	
	
	@Column(name="modalite_paiement")
	private String modalitePaiement;
	
	
	
	@Column(name="DATE_ECHEANCE")
	private Calendar dateEcheance;
	
	
	@Column(name="ref_commande")
	private String refCommande;
	
	
	@Column(name="identifiant")
	private String identifiant;
	
	
	
	
	
	
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public String getModalitePaiement() {
		return modalitePaiement;
	}

	public void setModalitePaiement(String modalitePaiement) {
		this.modalitePaiement = modalitePaiement;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public boolean isForcerCalculMontant() {
		return forcerCalculMontant;
	}

	public void setForcerCalculMontant(boolean forcerCalculMontant) {
		this.forcerCalculMontant = forcerCalculMontant;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

	public Boolean getDeclarer() {
		return declarer;
	}

	public void setDeclarer(Boolean declarer) {
		this.declarer = declarer;
	}

	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public Double getTauxConversion() {
		return tauxConversion;
	}

	public void setTauxConversion(Double tauxConversion) {
		this.tauxConversion = tauxConversion;
	}

	public Double getMontantConverti() {
		return montantConverti;
	}

	public void setMontantConverti(Double montantConverti) {
		this.montantConverti = montantConverti;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Long getReglementId() {
		return reglementId;
	}

	public void setReglementId(Long reglementId) {
		this.reglementId = reglementId;
	}

	public Set<DocumentFactureVenteEntity> getDocumentFactureVente() {
		return documentFactureVente;
	}

	public void setDocumentFactureVente(Set<DocumentFactureVenteEntity> documentFactureVente) {
		this.documentFactureVente = documentFactureVente;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Long getTypePartieInteressee() {
		return typePartieInteressee;
	}

	public void setTypePartieInteressee(Long typePartieInteressee) {
		this.typePartieInteressee = typePartieInteressee;
	}

	public Boolean getDescription() {
		return description;
	}

	public void setDescription(Boolean description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantHTaxe() {
		return montantHTaxe;
	}

	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Set<DetFactureVenteEntity> getListDetFactureVente() {
		return listDetFactureVente;
	}

	public void setListDetFactureVente(Set<DetFactureVenteEntity> listDetFactureVente) {
		this.listDetFactureVente = listDetFactureVente;
	}

	public Set<TaxeFactureEntity> getListTaxeFacture() {
		return listTaxeFacture;
	}

	public void setListTaxeFacture(Set<TaxeFactureEntity> listTaxeFacture) {
		this.listTaxeFacture = listTaxeFacture;
	}

	public Double getMetrageTotal() {
		return metrageTotal;
	}

	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public String getInfoLivraison() {
		return infoLivraison;
	}

	public void setInfoLivraison(String infoLivraison) {
		this.infoLivraison = infoLivraison;
	}

	public String getRefFactures() {
		return refFactures;
	}

	public void setRefFactures(String refFactures) {
		this.refFactures = refFactures;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public String getNatureLivraison() {
		return natureLivraison;
	}

	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}

	public Double getTotalPourcentageRemise() {
		return totalPourcentageRemise;
	}

	public void setTotalPourcentageRemise(Double totalPourcentageRemise) {
		this.totalPourcentageRemise = totalPourcentageRemise;
	}

	public String getInfoLivraisonExterne() {
		return infoLivraisonExterne;
	}

	public void setInfoLivraisonExterne(String infoLivraisonExterne) {
		this.infoLivraisonExterne = infoLivraisonExterne;
	}

	public Long getIdMarche() {
		return idMarche;
	}

	public void setIdMarche(Long idMarche) {
		this.idMarche = idMarche;
	}
	
}
