package com.gpro.consulting.tiers.logistique.coordination.gc.chart.value;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ResultBestElementValue
		implements Comparable<ResultBestElementValue> {

	private Long id;

	private String abreviation;
	
	
	private Double quantite;
	
	private Double montantTTC;

	private Double montantHTaxe;
	
	
	private Object idProd;
	
	private Object qte;
	
	private Object montant;
	
	private Object designation;
	
	private String mois;
	
	private Long nbTotal;
	
	
	
	private Double quantiteActuelle;
	
	private Double quantiteEntree;
	
	
	private Double quantiteSortie;
	
	
	
	
	private Long nbTotalLastMonth;
	
	private Long nbTotalThisMonth;
	
	
	private String moisDernier;
	
	private String ceMois;
	
	
	

	public String getMoisDernier() {
		return moisDernier;
	}





	public void setMoisDernier(String moisDernier) {
		this.moisDernier = moisDernier;
	}





	public String getCeMois() {
		return ceMois;
	}





	public void setCeMois(String ceMois) {
		this.ceMois = ceMois;
	}





	public Long getNbTotalLastMonth() {
		return nbTotalLastMonth;
	}





	public void setNbTotalLastMonth(Long nbTotalLastMonth) {
		this.nbTotalLastMonth = nbTotalLastMonth;
	}





	public Long getNbTotalThisMonth() {
		return nbTotalThisMonth;
	}





	public void setNbTotalThisMonth(Long nbTotalThisMonth) {
		this.nbTotalThisMonth = nbTotalThisMonth;
	}





	public Double getQuantiteActuelle() {
		return quantiteActuelle;
	}





	public void setQuantiteActuelle(Double quantiteActuelle) {
		this.quantiteActuelle = quantiteActuelle;
	}





	public Double getQuantiteEntree() {
		return quantiteEntree;
	}





	public void setQuantiteEntree(Double quantiteEntree) {
		this.quantiteEntree = quantiteEntree;
	}





	public Double getQuantiteSortie() {
		return quantiteSortie;
	}





	public void setQuantiteSortie(Double quantiteSortie) {
		this.quantiteSortie = quantiteSortie;
	}





	public Long getNbTotal() {
		return nbTotal;
	}





	public void setNbTotal(Long nbTotal) {
		this.nbTotal = nbTotal;
	}





	public Object getIdProd() {
		return idProd;
	}





	public void setIdProd(Object idProd) {
		this.idProd = idProd;
	}





	public Object getQte() {
		return qte;
	}





	public void setQte(Object qte) {
		this.qte = qte;
	}





	public Object getDesignation() {
		return designation;
	}





	public void setDesignation(Object designation) {
		this.designation = designation;
	}





	public String getMois() {
		return mois;
	}





	public void setMois(String mois) {
		this.mois = mois;
	}





	public Object getMontant() {
		return montant;
	}





	public void setMontant(Object montant) {
		this.montant = montant;
	}





	public Double getMontantTTC() {
		return montantTTC;
	}





	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}





	public Double getMontantHTaxe() {
		return montantHTaxe;
	}





	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getAbreviation() {
		return abreviation;
	}





	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}





	public Double getQuantite() {
		return quantite;
	}





	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}





	@Override
	public int compareTo(ResultBestElementValue o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
}
