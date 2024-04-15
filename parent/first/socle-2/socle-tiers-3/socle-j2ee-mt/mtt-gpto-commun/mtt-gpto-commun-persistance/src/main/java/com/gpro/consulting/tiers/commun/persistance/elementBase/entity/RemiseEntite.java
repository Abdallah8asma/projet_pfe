package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

	/**
	* Classe: REMISE
	* @author: $SAMER
	* 
	*/
@Entity
@Table(name = IConstante.TABLE_EB_REMISE)
public class RemiseEntite implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -710115226267174058L;


		@Id
		@SequenceGenerator(name="REMISE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_REMISE,allocationSize=1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="REMISE_ID_GENERATOR")
		private Long id;
		
		
		/** The date debut. */
		@Column(name = "date_debut")
		private Calendar dateDebut;
		
		/** The date Fin. */
		@Column(name = "date_fin")
		private Calendar dateFin;
		
		
		/** The  remise. */
		@Column(name = "remise")
		private Double remise;
		
		
		/** The type produit_id. */
		@Column(name = "produit_id")
		private Long produitId;
		
		
		/** The boutique_id. */
		@Column(name = "boutique_id")
		private Long boutiqueId;

		/** The bl suppression. */
		@Column(name = "bl_suppression")
		private boolean blSuppression;

		/** The date suppression. */
		@Column(name = "date_suppression")
		private Calendar dateSuppression;

		/** The date creation. */
		@Column(name = "date_creation")
		private Calendar dateCreation;

		/** The date modification. */
		@Column(name = "date_modification")
		private Calendar dateModification;

		/************* Getters & Setters *****************/
	

		/************* Equals & ToString *****************/
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Calendar getDateDebut() {
			return dateDebut;
		}

		public void setDateDebut(Calendar dateDebut) {
			this.dateDebut = dateDebut;
		}

		public Calendar getDateFin() {
			return dateFin;
		}

		public void setDateFin(Calendar dateFin) {
			this.dateFin = dateFin;
		}

		public Double getRemise() {
			return remise;
		}

		public void setRemise(Double remise) {
			this.remise = remise;
		}

		public Long getProduitId() {
			return produitId;
		}

		public void setProduitId(Long produitId) {
			this.produitId = produitId;
		}

		public Long getBoutiqueId() {
			return boutiqueId;
		}

		public void setBoutiqueId(Long boutiqueId) {
			this.boutiqueId = boutiqueId;
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

		@Override
		public String toString() {
			return super.toString();
		}	
}

