package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

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

import com.gpro.consulting.tiers.commun.coordination.IConstante;

	/**
	* Classe: REMISE
	* @author: $SAMER
	* 
	*/
@Entity
@Table(name = IConstante.TABLE_EB_PACKAGE)
public class PackageEntite implements Serializable {




		/**
		 * 
		 */
		private static final long serialVersionUID = -5362907590797042258L;


		@Id
		@SequenceGenerator(name="PACKAGE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_PACKAGE,allocationSize=1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="PACKAGE_ID_GENERATOR")
		private Long id;
		
		
		/** The  reference. */
		@Column(name = "reference")
		private String reference;
		
		/** The  nom. */
		@Column(name = "nom")
		private String nom;
		
		
		
		/** The date debut. */
		@Column(name = "date_debut")
		private Calendar dateDebut;
		
		/** The date Fin. */
		@Column(name = "date_fin")
		private Calendar dateFin;
		
		
		/** The  client_id. */
		@Column(name = "client_id")
		private Long clientId;
		
		/** The  groupe_id. */
		@Column(name = "groupe_id")
		private Long groupeId;

		
		
		/** The boutique_id. */
		@Column(name = "boutique_id")
		private Long boutiqueId;
		
		
		
		
		@OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, orphanRemoval=true)
		private Set<DetailsPackageEntite> listDetPackage;
		
		


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

		
		
	

		public Set<DetailsPackageEntite> getListDetPackage() {
			return listDetPackage;
		}





		public void setListDetPackage(Set<DetailsPackageEntite> listDetPackage) {
			this.listDetPackage = listDetPackage;
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





		public String getNom() {
			return nom;
		}





		public void setNom(String nom) {
			this.nom = nom;
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





		public Long getClientId() {
			return clientId;
		}





		public void setClientId(Long clientId) {
			this.clientId = clientId;
		}





		public Long getGroupeId() {
			return groupeId;
		}





		public void setGroupeId(Long groupeId) {
			this.groupeId = groupeId;
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

