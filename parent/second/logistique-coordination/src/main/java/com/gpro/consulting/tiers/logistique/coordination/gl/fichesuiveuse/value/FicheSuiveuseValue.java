package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;

/**
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public class FicheSuiveuseValue {

	private Long id;
	private String referenceMise;
	private Calendar date;
	private Long partieIntId;
	private Long produitId;
	private String typeLivraison;
	private Calendar dateLivraison;
	private Calendar dateLancement;
	private String cuisinier;
	private String conducteur;
	private Double poids;
	private String observations;
	private Double volume;

	private Double laizeDemFini; // --> dem
	private Double laizeSortie;
	private Double poidsSortie;
	private String rapportBain;
	private Double metrageSortie;
	private Long marcheId;

	private Double poidsPES;
	private Double volumePES;

	private Double poidsCoton;
	private Double volumeCoton;

	/**
	 * @author Hajer Amri
	 * @since 01/02/2017
	 */
	private boolean soustractionEffectuee;

	private Set<ElementControleValue> listElementControle;
	private List<TraitementMiseValue> listeTraitementsMise;

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

	public List<TraitementMiseValue> getListeTraitementsMise() {
		return listeTraitementsMise;
	}

	public void setListeTraitementsMise(List<TraitementMiseValue> listeTraitementsMise) {
		this.listeTraitementsMise = listeTraitementsMise;
	}

	public Set<ElementControleValue> getListElementControle() {
		return listElementControle;
	}

	public void setListElementControle(Set<ElementControleValue> listElementControle) {
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
		return "FicheSuiveuseValue [id=" + id + ", referenceMise=" + referenceMise + ", partieIntId=" + partieIntId
				+ ", produitId=" + produitId + ", typeLivraison=" + typeLivraison + ", cuisinier=" + cuisinier
				+ ", conducteur=" + conducteur + ", poids=" + poids + ", observations=" + observations + ", volume="
				+ volume + ", laizeDemFini=" + laizeDemFini + ", laizeSortie=" + laizeSortie + ", poidsSortie="
				+ poidsSortie + ", rapportBain=" + rapportBain + ", metrageSortie=" + metrageSortie + ", marcheId="
				+ marcheId + ", poidsPES=" + poidsPES + ", volumePES=" + volumePES + ", poidsCoton=" + poidsCoton
				+ ", volumeCoton=" + volumeCoton + ", soustractionEffectuee=" + soustractionEffectuee
				+ ", listElementControle=" + listElementControle
				+ "]";
	}

	
}
