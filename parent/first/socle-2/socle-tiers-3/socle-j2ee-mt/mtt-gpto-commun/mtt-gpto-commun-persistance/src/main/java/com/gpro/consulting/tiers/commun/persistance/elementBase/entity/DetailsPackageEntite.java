package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

	/**
	* Classe: REMISE
	* @author: $SAMER
	* 
	*/
@Entity
@Table(name = IConstante.TABLE_EB_DETAIL_PACKAGE)
public class DetailsPackageEntite implements Serializable {




		/**
		 * 
		 */
		private static final long serialVersionUID = -463865894016160416L;


		@Id
		@SequenceGenerator(name="DETAIL_PACKAGE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_DETAIL_PACKAGE,allocationSize=1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="DETAIL_PACKAGE_ID_GENERATOR")
		private Long id;
		
		
		
		/** The  remise. */
		@Column(name = "remise")
		private Double remise;
		
		
		/** The type produit_id. */
		@Column(name = "produit_id")
		private Long produitId;
		
		
		
		/** The  observations. */
		@Column(name = "observations")
		private String observations;
		
		
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name = "EB_PACKAGE_ID")
		private PackageEntite pack;
		
		
		
		
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



		public String getObservations() {
			return observations;
		}



		public void setObservations(String observations) {
			this.observations = observations;
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



		public PackageEntite getPack() {
			return pack;
		}



		public void setPack(PackageEntite pack) {
			this.pack = pack;
		}



		@Override
		public String toString() {
			return super.toString();
		}	
}

