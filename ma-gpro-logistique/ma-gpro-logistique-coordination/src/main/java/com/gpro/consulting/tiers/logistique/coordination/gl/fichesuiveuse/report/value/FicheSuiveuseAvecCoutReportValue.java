package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value;

import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Zeineb Medimagh
 * @since 22/09/2016
 */
public class FicheSuiveuseAvecCoutReportValue extends FicheSuiveuseReportValue {
	
	private Double coutRecette;
	private Double coutTraitement;
	private Double coutTotal;
	private Double coutUnitaire;
	private Double metrageTotal;
	private Double poidsTotal;
	
	public Double getCoutRecette() {
		return coutRecette;
	}
	public void setCoutRecette(Double coutRecette) {
		this.coutRecette = coutRecette;
	}
	public Double getCoutTraitement() {
		return coutTraitement;
	}
	public void setCoutTraitement(Double coutTraitement) {
		this.coutTraitement = coutTraitement;
	}
	public Double getCoutTotal() {
		return coutTotal;
	}
	public void setCoutTotal(Double coutTotal) {
		this.coutTotal = coutTotal;
	}
	public Double getCoutUnitaire() {
		return coutUnitaire;
	}
	public void setCoutUnitaire(Double coutUnitaire) {
		this.coutUnitaire = coutUnitaire;
	}
	public Double getMetrageTotal() {
		return metrageTotal;
	}
	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}
	public Double getPoidsTotal() {
		return poidsTotal;
	}
	public void setPoidsTotal(Double poidsTotal) {
		this.poidsTotal = poidsTotal;
	}
}
