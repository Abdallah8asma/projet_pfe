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
	* Classe: Couleur
	* @author: $AMENI
	* 
	*/
@Entity
@Table(name = IConstante.TABLE_EB_UTILS)
public class UtilsEntite implements Serializable {



		/**
		 * 
		 */
		private static final long serialVersionUID = -920937903598080722L;

		@Id
		@SequenceGenerator(name="UTILS_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_UTILS,allocationSize=1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="UTILS_ID_GENERATOR")
		private Long id;
		
		/** The designation. */
		@Column(name = "designation")
		private String designation;
		
		/** The description. */
		@Column(name = "description")
		private String description;
				
		
		/** The type. */
		@Column(name = "type")
		private String type;

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
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
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
		

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		/************* Equals & ToString *****************/
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}
		
		@Override
		public String toString() {
			return super.toString();
		}	
}

