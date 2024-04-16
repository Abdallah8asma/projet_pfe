package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;



/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Entity
@Table(name = IConstante.TABLE_BON_STOCK)
public class BonStockEntity implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 538708998095365075L;

	@Id
	@SequenceGenerator(name = "BON_STOCK_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_BON_STOCK, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BON_STOCK_ID_GENERATOR")
	private Long id;

	@Column(name = "REFERENCE")
	private String reference;
	
	@Column(name = "REFEXTERNE")
	private String refexterne ;
	
	@Column(name = "REF_COMMANDE")
	private String refCommande ;



	@Column(name = "DATE")
	private Calendar date;

	@Column(name = "MONTANTHTAXE")
	private Double montantHTaxe;

	@Column(name = "MONTANTTTC")
	private Double montantTTC;

	@Column(name = "MONTANT_TAXE")
	private Double montantTaxe;

	@Column(name = "MONTANT_REMISE")
	private Double montantRemise;

	@Column(name = "OBSERVATIONS")
	private String observations;

	@Column(name = "INFO_SORTIE")
	private String infoSortie;// list des ref bonSorties | patterne:
								// ref1-ref2-ref3 ....

	@Column(name = "PI_PARTIEINT_ID")
	private Long partieIntId;

	@Column(name = "METRAGE_TOTAL")
	private Double metrageTotal;

	@Column(name = "ETAT")
	private String etat;

	@Column(name = "TRANSPORTEUR")
	private String transporteur;

	@OneToMany(mappedBy = "bonStock", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DetBonStockEntity> listDetBonStock;



	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression;

	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;

	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;

	@Column(name = "VERSION")
	private String version;

	

	// added on 18/02/2016, by Wahid Gazzah
	@Column(name = "MODIFIER")
	private Boolean modifier;

	// added on 19/02/2016, by Wahid Gazzah
	@Column(name = "GC_MODEPAIEMENT_ID")
	private Long modepaiementId;

	@Column(name = "GC_MARCHE_ID")
	private Long marcheId;

	// added on 05/10/2016, by Zeineb Medimagh
	@Column(name = "NATURE_LIVRAISON")
	private String natureLivraison;

	@Column(name = "TOTAL_POURCENTAGE_REMISE")
	private Double totalPourcentageRemise;
	
	// Added on 25/04/2018  By Ghazi Atroussi
	@Column(name = "STOCK")
	private Boolean stock;
	
	@Column(name = "ID_DEPOT")
	private Long idDepot;
	
	@Column(name = "ID_CAMION")
	private Long idCamion;
   
	@Column(name = "ID_REMORQUE")
	private Long idRemorque;
	
	@Column(name = "ID_CHAUFFEUR")
	private Long idChauffeur;
	
	@Column(name = "camion")
	private String camion ;
	
	@Column(name = "chauffeur")
	private String chauffeur ;
	
	@Column(name = "remorque")
	private String remorque ;
	

	@Column(name = "DESCRIPTION")
	private Boolean description;
	
	@Column(name = "type_partie_Interessee")
	private Long typePartieInteressee;
	
	
	
	@Column(name = "declare")
	private Boolean declare;
	
	
	
	/** The GroupeClient. */
	@Column(name = "groupe_client_id")
	private Long groupeClientId;
	
	
	@Column(name = "type")
	private String type ;
	
	
	/**  refAvoirRetour est la ref facture achat Avoir Retour utilise pour  stock sortie. */
	
	@Column(name = "REF_AVOIR_RETOUR")
	private String refAvoirRetour ;
	
	
	
	@Column(name = "REF_BR")
	private String refBR ;
	
	@Column(name = "REF_FACTURE")
	private String refFacture;
	
	
	
	@Column(name = "DATE_CONCERNEE")
	private Calendar dateConcernee;
	
	
	
	
	

	public Calendar getDateConcernee() {
		return dateConcernee;
	}

	public void setDateConcernee(Calendar dateConcernee) {
		this.dateConcernee = dateConcernee;
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

	public String getRefBR() {
		return refBR;
	}

	public void setRefBR(String refBR) {
		this.refBR = refBR;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Boolean getDeclare() {
		return declare;
	}

	public void setDeclare(Boolean declare) {
		this.declare = declare;
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

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}


	
	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Boolean getStock() {
		return stock;
	}

	public void setStock(Boolean stock) {
		this.stock = stock;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
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

	public String getInfoSortie() {
		return infoSortie;
	}

	public void setInfoSortie(String infoSortie) {
		this.infoSortie = infoSortie;
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

	public String getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}

	public Boolean isModifier() {
		return modifier;
	}

	public void setModifier(Boolean modifier) {
		this.modifier = modifier;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public Long getMarcheId() {
		return marcheId;
	}

	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}

	/** contructeur DropList & Cache */
	public BonStockEntity(String reference) {
		super();
		this.reference = reference;
	}

	/** constructeurgolbal */
	public BonStockEntity() {
		super();
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

	

	public Long getIdCamion() {
		return idCamion;
	}

	public void setIdCamion(Long idCamion) {
		this.idCamion = idCamion;
	}

	public Long getIdRemorque() {
		return idRemorque;
	}

	public void setIdRemorque(Long idRemorque) {
		this.idRemorque = idRemorque;
	}

	public Long getIdChauffeur() {
		return idChauffeur;
	}

	public void setIdChauffeur(Long idChauffeur) {
		this.idChauffeur = idChauffeur;
	}

	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	public String getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(String chauffeur) {
		this.chauffeur = chauffeur;
	}

	public String getRemorque() {
		return remorque;
	}

	public void setRemorque(String remorque) {
		this.remorque = remorque;
	}

	public Boolean getModifier() {
		return modifier;
	}

	public Set<DetBonStockEntity> getListDetBonStock() {
		return listDetBonStock;
	}

	public void setListDetBonStock(Set<DetBonStockEntity> listDetBonStock) {
		this.listDetBonStock = listDetBonStock;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	@Override
	public String toString() {
		return "BonStockEntity [id=" + id + ", reference=" + reference + ", refexterne=" + refexterne + ", refCommande="
				+ refCommande + ", date=" + date + ", montantHTaxe=" + montantHTaxe + ", montantTTC=" + montantTTC
				+ ", montantTaxe=" + montantTaxe + ", montantRemise=" + montantRemise + ", observations=" + observations
				+ ", infoSortie=" + infoSortie + ", partieIntId=" + partieIntId + ", metrageTotal=" + metrageTotal
				+ ", etat=" + etat + ", transporteur=" + transporteur + ", listDetBonStock=" + listDetBonStock
				+ ", blSuppression=" + blSuppression + ", dateSuppression=" + dateSuppression + ", dateCreation="
				+ dateCreation + ", dateModification=" + dateModification + ", version=" + version + ", modifier="
				+ modifier + ", modepaiementId=" + modepaiementId + ", marcheId=" + marcheId + ", natureLivraison="
				+ natureLivraison + ", totalPourcentageRemise=" + totalPourcentageRemise + ", stock=" + stock
				+ ", idDepot=" + idDepot + ", idCamion=" + idCamion + ", idRemorque=" + idRemorque + ", idChauffeur="
				+ idChauffeur + ", camion=" + camion + ", chauffeur=" + chauffeur + ", remorque=" + remorque
				+ ", description=" + description + ", typePartieInteressee=" + typePartieInteressee + ", declare="
				+ declare + ", groupeClientId=" + groupeClientId + "]";
	}



}
