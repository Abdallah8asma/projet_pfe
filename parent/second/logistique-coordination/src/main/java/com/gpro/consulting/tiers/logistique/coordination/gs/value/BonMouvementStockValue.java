package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class BonMouvementStockValue.
 */
public class BonMouvementStockValue implements Comparable<Object> {

	/** The id. */
	private Long id;

	/** The date. Date Reservation */
	private Calendar date;

	/** The description. */
	private String description;

	/** The gc blachat id. */
	private Long blachatId;

	/** The numero. BR */
	private String numero;

	/** The responsable. */
	private String responsable;

	/** The type. */
	private String type;

	/** The valeur. */
	private Double valeur;

	/** The raisonMouvementId. */
	private Long raisonMouvementId;

	/** The raisonMouvement designation. */
	private String raisonMouvementDesignation;

	private String etat;

	private Long partieintId;

	private String client;

	private Long ofId;

	private String numOF;

	private String refProduit;

	// bi-directional many-to-one association to GsMouvement
	/** The mouvements. */
	private Set<MouvementStockValue> mouvements;

	// Added on 10/11/2016, by Zeineb Medimagh
	// Rempli pour le bon de type sortie
	// Qui relie avec le bonReservation
	private String numBRSortie;
	

	/**
	 * @author Zeineb Medimagh
	 * @since 25/11/2016
	 */
	private List<MouvementStockSupprime> listeMouvementsSupprimes;

	/**
	 * @author Zeineb Medimagh
	 * @since 28/11/2016
	 */
	private Calendar dateReservation;

	/**
	 * @author Hajer Amri
	 * @since 30/01/2017
	 */
	/** The soustractionSortie. */
	private boolean soustractionSortie;

	/** The idMagasin. */
	private Long idMagasin;

	/** The origineFSuiveuse. */
	private boolean origineFSuiveuse;
	private String referenceBonReception;

	/**
	 * @return the partieintId
	 */
	public Long getPartieintId() {
		return partieintId;
	}

	/**
	 * @param partieintId
	 *            the partieintId to set
	 */
	public void setPartieintId(Long partieintId) {
		this.partieintId = partieintId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Set<MouvementStockValue> getMouvements() {
		return mouvements;
	}

	public void setMouvements(Set<MouvementStockValue> mouvements) {
		this.mouvements = mouvements;
	}

	public Long getBlachatId() {
		return blachatId;
	}

	public void setBlachatId(Long blachatId) {
		this.blachatId = blachatId;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the responsable.
	 *
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}

	/**
	 * Sets the responsable.
	 *
	 * @param responsable
	 *            the new responsable
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the valeur.
	 *
	 * @return the valeur
	 */
	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public Long getRaisonMouvementId() {
		return raisonMouvementId;
	}

	public void setRaisonMouvementId(Long raisonMouvementId) {
		this.raisonMouvementId = raisonMouvementId;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getRaisonMouvementDesignation() {
		return raisonMouvementDesignation;
	}

	public void setRaisonMouvementDesignation(String raisonMouvementDesignation) {
		this.raisonMouvementDesignation = raisonMouvementDesignation;
	}

	public Long getOfId() {
		return ofId;
	}

	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}

	public String getRefProduit() {
		return refProduit;
	}

	public void setRefProduit(String refProduit) {
		this.refProduit = refProduit;
	}

	public String getNumBRSortie() {
		return numBRSortie;
	}

	public void setNumBRSortie(String numBRSortie) {
		this.numBRSortie = numBRSortie;
	}

	public List<MouvementStockSupprime> getListeMouvementsSupprimes() {
		return listeMouvementsSupprimes;
	}

	public void setListeMouvementsSupprimes(List<MouvementStockSupprime> listeMouvementsSupprimes) {
		this.listeMouvementsSupprimes = listeMouvementsSupprimes;
	}

	public Calendar getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Calendar dateReservation) {
		this.dateReservation = dateReservation;
	}

	public boolean isSoustractionSortie() {
		return soustractionSortie;
	}

	public void setSoustractionSortie(boolean soustractionSortie) {
		this.soustractionSortie = soustractionSortie;
	}

	@Override
	public int compareTo(Object o) {
		BonMouvementStockValue element = (BonMouvementStockValue) o;
		
		return (element.getNumero().compareTo(numero));
		
		//return (element.getDate().compareTo(date));
	}

	public Long getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(Long idMagasin) {
		this.idMagasin = idMagasin;
	}

	public boolean isOrigineFSuiveuse() {
		return origineFSuiveuse;
	}

	public void setOrigineFSuiveuse(boolean origineFSuiveuse) {
		this.origineFSuiveuse = origineFSuiveuse;
	}

	public String getReferenceBonReception() {
		return referenceBonReception;
	}

	public void setReferenceBonReception(String referenceBonReception) {
		this.referenceBonReception = referenceBonReception;
	}

	@Override
	public String toString() {
		return "BonMouvementStockValue [id=" + id + ", date=" + date + ", description=" + description + ", blachatId="
				+ blachatId + ", numero=" + numero + ", responsable=" + responsable + ", type=" + type + ", valeur="
				+ valeur + ", raisonMouvementId=" + raisonMouvementId + ", raisonMouvementDesignation="
				+ raisonMouvementDesignation + ", etat=" + etat + ", partieintId=" + partieintId + ", client=" + client
				+ ", ofId=" + ofId + ", numOF=" + numOF + ", refProduit=" + refProduit + ", mouvements=" + mouvements
				+ ", numBRSortie=" + numBRSortie + ", listeMouvementsSupprimes=" + listeMouvementsSupprimes
				+ ", dateReservation=" + dateReservation + ", soustractionSortie=" + soustractionSortie + ", idMagasin="
				+ idMagasin + ", origineFSuiveuse=" + origineFSuiveuse + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	// @Override
	// public String toString() {
	// StringBuilder builder = new StringBuilder();
	// builder.append("BonMouvementStockValue [");
	// if (id != null)
	// builder.append("id=").append(id).append(", ");
	// if (date != null)
	// builder.append("date=").append(date).append(", ");
	// if (description != null)
	// builder.append("description=").append(description).append(", ");
	// if (blachatId != null)
	// builder.append("blachatId=").append(blachatId).append(", ");
	// if (numero != null)
	// builder.append("numero=").append(numero).append(", ");
	// if (responsable != null)
	// builder.append("responsable=").append(responsable).append(", ");
	// if (type != null)
	// builder.append("type=").append(type).append(", ");
	// if (valeur != null)
	// builder.append("valeur=").append(valeur).append(", ");
	// if (raisonMouvementId != null)
	// builder.append("raisonMouvementId=").append(raisonMouvementId)
	// .append(", ");
	// if (raisonMouvementDesignation != null)
	// builder.append("raisonMouvementDesignation=")
	// .append(raisonMouvementDesignation).append(", ");
	// if (etat != null)
	// builder.append("etat=").append(etat).append(", ");
	// if (partieintId != null)
	// builder.append("partieintId=").append(partieintId).append(", ");
	// if (mouvements != null)
	// builder.append("mouvements=").append(mouvements);
	// builder.append("]");
	// return builder.toString();
	// }

}
