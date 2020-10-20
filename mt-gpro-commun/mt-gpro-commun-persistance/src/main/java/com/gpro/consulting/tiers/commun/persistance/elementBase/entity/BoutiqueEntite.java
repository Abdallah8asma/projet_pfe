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
	* Classe: BOUTIQUE
	* @author: $SAMER
	* 
	*/
@Entity
@Table(name = IConstante.TABLE_EB_BOUTIQUE)
public class BoutiqueEntite implements Serializable {



		/**
		 * 
		 */
		private static final long serialVersionUID = -2767342044728374694L;

		@Id
		@SequenceGenerator(name="BOUTIQUE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_BOUTIQUE,allocationSize=1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="BOUTIQUE_ID_GENERATOR")
		private Long id;
		
	
		/** The ref. */
		@Column(name = "ref")
		private String reference;

		/** The raison sociale. */
		@Column(name = "raison_sociale")
		private String raisonSociale;

		/** The abreviation. */
		@Column(name = "abreviation")
		private String abreviation;

		/** The activite. */
		@Column(name = "activite")
		private String activite;

		/** The observation. */
		@Column(name = "observation")
		private String observation;

		/** The devise. */
		@Column(name = "devise")
		private String devise;

		/** The date introduction. */
		@Column(name = "date_introduction")
		private Calendar dateIntroduction;

		/** The matr fiscal. */
		@Column(name = "matr_fiscal")
		private String matrFiscal;

		/** The regime commercial. */
		@Column(name = "reg_com")
		private String regimeCommercial;

		/** The code douane. */
		@Column(name = "code_douane")
		private String codeDouane;

		/** The adresse. */
		@Column(name = "adresse")
		private String adresse;

		/** The email. */
		@Column(name = "email")
		private String email;

		/** The telephone. */
		@Column(name = "telephone")
		private String telephone;

		/** The fax. */
		@Column(name = "fax")
		private String fax;
		
		/** The type entite. */
		@Column(name = "pi_region_id")
		private Long regionId;

		/** The actif. */
		@Column(name = "actif")
		private Boolean actif;
		
		
		@Column(name = "objectif")
		private Double objectif;
		
		
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name = "EB_SOCIETE_ID")
		private SocieteEntite societe;
		
		

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
		
		
		
		
		
		
		
		
		public SocieteEntite getSociete() {
			return societe;
		}








		public void setSociete(SocieteEntite societe) {
			this.societe = societe;
		}








		public Double getObjectif() {
			return objectif;
		}

		public void setObjectif(Double objectif) {
			this.objectif = objectif;
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

		public String getRaisonSociale() {
			return raisonSociale;
		}

		public void setRaisonSociale(String raisonSociale) {
			this.raisonSociale = raisonSociale;
		}

		public String getAbreviation() {
			return abreviation;
		}

		public void setAbreviation(String abreviation) {
			this.abreviation = abreviation;
		}

		public String getActivite() {
			return activite;
		}

		public void setActivite(String activite) {
			this.activite = activite;
		}

		public String getObservation() {
			return observation;
		}

		public void setObservation(String observation) {
			this.observation = observation;
		}

		public String getDevise() {
			return devise;
		}

		public void setDevise(String devise) {
			this.devise = devise;
		}

		public Calendar getDateIntroduction() {
			return dateIntroduction;
		}

		public void setDateIntroduction(Calendar dateIntroduction) {
			this.dateIntroduction = dateIntroduction;
		}

		public String getMatrFiscal() {
			return matrFiscal;
		}

		public void setMatrFiscal(String matrFiscal) {
			this.matrFiscal = matrFiscal;
		}

		public String getRegimeCommercial() {
			return regimeCommercial;
		}

		public void setRegimeCommercial(String regimeCommercial) {
			this.regimeCommercial = regimeCommercial;
		}

		public String getCodeDouane() {
			return codeDouane;
		}

		public void setCodeDouane(String codeDouane) {
			this.codeDouane = codeDouane;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public Long getRegionId() {
			return regionId;
		}

		public void setRegionId(Long regionId) {
			this.regionId = regionId;
		}

		public Boolean getActif() {
			return actif;
		}

		public void setActif(Boolean actif) {
			this.actif = actif;
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

