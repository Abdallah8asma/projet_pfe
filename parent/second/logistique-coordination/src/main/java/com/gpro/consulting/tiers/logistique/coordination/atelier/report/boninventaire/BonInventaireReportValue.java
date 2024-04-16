package com.gpro.consulting.tiers.logistique.coordination.atelier.report.boninventaire;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.RouleauFiniReportGroupedByProduitValue;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Wahid Gazzah
 * @since 12/01/2016
 *
 */
public class BonInventaireReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String reference;
//	private String type; 
//	private String client;
	private Calendar dateSortie;
	private Integer nbColis;
	
	private List<RouleauFiniReportGroupedByProduitValue> listeRouleauFini;
	
	public InputStream getReportStream() {
		return reportStream;
	}
	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}
	public HashMap<String, Object> getParams() {
		return params;
	}
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}
	public void setJRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getClient() {
//		return client;
//	}
//	public void setClient(String client) {
//		this.client = client;
//	}
	public Calendar getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	public Integer getNbColis() {
		return nbColis;
	}
	public void setNbColis(Integer nbColis) {
		this.nbColis = nbColis;
	}
	public List<RouleauFiniReportGroupedByProduitValue> getListeRouleauFini() {
		return listeRouleauFini;
	}
	public void setListeRouleauFini(
			List<RouleauFiniReportGroupedByProduitValue> listeRouleauFini) {
		this.listeRouleauFini = listeRouleauFini;
	}

}
