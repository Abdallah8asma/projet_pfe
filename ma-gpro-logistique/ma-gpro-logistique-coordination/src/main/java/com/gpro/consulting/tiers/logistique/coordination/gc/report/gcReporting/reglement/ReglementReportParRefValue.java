package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Hajer Amri
 * @since 30/01/2017
 *
 */
public class ReglementReportParRefValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String clientAbreviation;
	private Calendar date;
	private String reference;
	private Double mntBLRegle;
	private Double totalBLNonRegle;
	private Double mntFactRegle;
	private Double totalFactNonRegle;
	private Double montant ;


	private List<ReglementReportParRefElementDetailsValue> listDetailsReportReglement = new ArrayList<ReglementReportParRefElementDetailsValue>();
	private List<ReglementReportParRefElementEltReglementValue> listElementReportReglement = new ArrayList<ReglementReportParRefElementEltReglementValue>();

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

	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getMntBLRegle() {
		return mntBLRegle;
	}

	public void setMntBLRegle(Double mntBLRegle) {
		this.mntBLRegle = mntBLRegle;
	}

	public Double getMntFactRegle() {
		return mntFactRegle;
	}

	public void setMntFactRegle(Double mntFactRegle) {
		this.mntFactRegle = mntFactRegle;
	}

	public Double getTotalBLNonRegle() {
		return totalBLNonRegle;
	}

	public void setTotalBLNonRegle(Double totalBLNonRegle) {
		this.totalBLNonRegle = totalBLNonRegle;
	}

	public Double getTotalFactNonRegle() {
		return totalFactNonRegle;
	}

	public void setTotalFactNonRegle(Double totalFactNonRegle) {
		this.totalFactNonRegle = totalFactNonRegle;
	}

	public List<ReglementReportParRefElementDetailsValue> getListDetailsReportReglement() {
		return listDetailsReportReglement;
	}

	public void setListDetailsReportReglement(
			List<ReglementReportParRefElementDetailsValue> listDetailsReportReglement) {
		this.listDetailsReportReglement = listDetailsReportReglement;
	}

	public List<ReglementReportParRefElementEltReglementValue> getListElementReportReglement() {
		return listElementReportReglement;
	}

	public void setListElementReportReglement(
			List<ReglementReportParRefElementEltReglementValue> listElementReportReglement) {
		this.listElementReportReglement = listElementReportReglement;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}
	
	

}
