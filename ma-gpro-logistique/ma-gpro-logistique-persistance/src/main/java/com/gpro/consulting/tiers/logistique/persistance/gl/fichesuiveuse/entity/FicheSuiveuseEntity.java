package com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity;

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

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.TraitementMiseEntity;

/**
 * @author Wahid Gazzah
 * @since 23/03/2016
 *
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_GL_FICHESUIVEUSEMISE)
public class FicheSuiveuseEntity implements Serializable {
	
	private static final long serialVersionUID = 669811434041093587L;
	
	@Id
	@SequenceGenerator(name="FICHESUIVEUSEMISE_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_FICHESUIVEUSEMISE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FICHESUIVEUSEMISE_ID_GENERATOR")
    private Long id;
	
	@Column(name = "REFERENCE_MISE")
	private String referenceMise;
	
	@Column(name = "DATE")
	private Calendar date;
	
	@Column(name = "PI_PARTINT_ID")
	private Long partieIntId;
	
	@Column(name = "EB_PRODUIT_ID")
	private Long produitId;
	
	@Column(name = "TYPE_LIVRAISON")
	private String typeLivraison;
	
	@Column(name = "DATE_LIVRAISON")
	private Calendar dateLivraison;
	
	@Column(name = "DATE_LANCEMENT")
	private Calendar dateLancement;

	@Column(name = "CUISINIER")
	private String cuisinier;
	
	@Column(name = "CONDUCTEUR")
	private String conducteur;
	
	@Column(name = "POIDS")
	private Double poids;
	
	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "VOLUME")
	private Double volume;
	
	@Column(name = "LAIZE_DEM_FINI")
	private Double laizeDemFini;
	
	@Column(name = "LAIZE_SORTIE")
	private Double laizeSortie;
	
	@Column(name = "POIDS_SORTIE")
	private Double poidsSortie;
	
	@Column(name = "RAPPORT_BAIN")
	private String rapportBain;
	
	@Column(name = "METRAGE_SORTIE")
	private Double metrageSortie;
	
	@Column(name = "MARCHE_ID")
	private Long marcheId;
	
	@Column(name = "POIDS_PES")
	private Double poidsPES;
	
	@Column(name = "VOLUME_PES")
	private Double volumePES;
	
	@Column(name = "POIDS_COTON")
	private Double poidsCoton;
	
	@Column(name = "VOLUME_COTON")
	private Double volumeCoton;
	
	/**
	 * @author Hajer Amri
	 * @since 01/02/2017
	 */
	/** The soustractionEffectuee. */
	@Column(name = "soustraction_effectuee")
	private boolean soustractionEffectuee;

	
	@OneToMany(mappedBy = "ficheSuiveuse", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ElementControleEntity> listElementControle;
	
	@OneToMany(mappedBy = "ficheSuiveuse", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<TraitementMiseEntity> listeTraitementsMise;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferenceMise() {
		return referenceMise;
	}

	public void setReferenceMise(String referenceMise) {
		this.referenceMise = referenceMise;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getTypeLivraison() {
		return typeLivraison;
	}

	public void setTypeLivraison(String typeLivraison) {
		this.typeLivraison = typeLivraison;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Calendar getDateLancement() {
		return dateLancement;
	}

	public void setDateLancement(Calendar dateLancement) {
		this.dateLancement = dateLancement;
	}

	public String getCuisinier() {
		return cuisinier;
	}

	public void setCuisinier(String cuisinier) {
		this.cuisinier = cuisinier;
	}

	public String getConducteur() {
		return conducteur;
	}

	public void setConducteur(String conducteur) {
		this.conducteur = conducteur;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public Set<TraitementMiseEntity> getListeTraitementsMise() {
		return listeTraitementsMise;
	}

	public void setListeTraitementsMise(
			Set<TraitementMiseEntity> listeTraitementsMise) {
		this.listeTraitementsMise = listeTraitementsMise;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Set<ElementControleEntity> getListElementControle() {
		return listElementControle;
	}

	public void setListElementControle(
			Set<ElementControleEntity> listElementControle) {
		this.listElementControle = listElementControle;
	}

	public Double getLaizeDemFini() {
		return laizeDemFini;
	}

	public void setLaizeDemFini(Double laizeDemFini) {
		this.laizeDemFini = laizeDemFini;
	}

	public Double getLaizeSortie() {
		return laizeSortie;
	}

	public void setLaizeSortie(Double laizeSortie) {
		this.laizeSortie = laizeSortie;
	}

	public Double getPoidsSortie() {
		return poidsSortie;
	}

	public void setPoidsSortie(Double poidsSortie) {
		this.poidsSortie = poidsSortie;
	}

	public String getRapportBain() {
		return rapportBain;
	}

	public void setRapportBain(String rapportBain) {
		this.rapportBain = rapportBain;
	}

	public Double getMetrageSortie() {
		return metrageSortie;
	}

	public void setMetrageSortie(Double metrageSortie) {
		this.metrageSortie = metrageSortie;
	}

	public Long getMarcheId() {
		return marcheId;
	}

	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}

	public Double getPoidsPES() {
		return poidsPES;
	}

	public void setPoidsPES(Double poidsPES) {
		this.poidsPES = poidsPES;
	}

	public Double getVolumePES() {
		return volumePES;
	}

	public void setVolumePES(Double volumePES) {
		this.volumePES = volumePES;
	}

	public Double getPoidsCoton() {
		return poidsCoton;
	}

	public void setPoidsCoton(Double poidsCoton) {
		this.poidsCoton = poidsCoton;
	}

	public Double getVolumeCoton() {
		return volumeCoton;
	}

	public void setVolumeCoton(Double volumeCoton) {
		this.volumeCoton = volumeCoton;
	}

	public boolean isSoustractionEffectuee() {
		return soustractionEffectuee;
	}

	public void setSoustractionEffectuee(boolean soustractionEffectuee) {
		this.soustractionEffectuee = soustractionEffectuee;
	}

	@Override
	public String toString() {
		return "FicheSuiveuseEntity [id=" + id + ", referenceMise=" + referenceMise + ", date=" + date
				+ ", partieIntId=" + partieIntId + ", produitId=" + produitId + ", typeLivraison=" + typeLivraison
				+ ", dateLivraison=" + dateLivraison + ", dateLancement=" + dateLancement + ", cuisinier=" + cuisinier
				+ ", conducteur=" + conducteur + ", poids=" + poids + ", observations=" + observations + ", volume="
				+ volume + ", laizeDemFini=" + laizeDemFini + ", laizeSortie=" + laizeSortie + ", poidsSortie="
				+ poidsSortie + ", rapportBain=" + rapportBain + ", metrageSortie=" + metrageSortie + ", marcheId="
				+ marcheId + ", poidsPES=" + poidsPES + ", volumePES=" + volumePES + ", poidsCoton=" + poidsCoton
				+ ", volumeCoton=" + volumeCoton + ", soustractionEffectuee=" + soustractionEffectuee
				+ ", listElementControle=" + listElementControle + ", listeTraitementsMise=" + listeTraitementsMise
				+ "]";
	}
	
	
	
}
