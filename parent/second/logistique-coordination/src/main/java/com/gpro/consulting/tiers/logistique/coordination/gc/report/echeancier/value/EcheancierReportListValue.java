package com.gpro.consulting.tiers.logistique.coordination.gc.report.echeancier.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementElementValue;

/**
 * @author Ameni Berrich
 *
 */
public class EcheancierReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Long partieIntId;
	private String reference;
	private Calendar dateReglementDu;
	private Calendar dateReglementAu;
	private Calendar dateEcheanceDu;
	private Calendar dateEcheanceAu;
	private Long typeReglementId;
	private String numPiece;
	private Boolean regle;
	
	private String partieIntAbreviation;
	private String typeReglement;
	
	private Boolean avecTerme;
	private String nomRapport;
	
	private List<ResultatRechecheDetailReglementElementValue> echeancierList = new ArrayList <ResultatRechecheDetailReglementElementValue>();

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

	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDateReglementDu() {
		return dateReglementDu;
	}

	public void setDateReglementDu(Calendar dateReglementDu) {
		this.dateReglementDu = dateReglementDu;
	}

	public Calendar getDateReglementAu() {
		return dateReglementAu;
	}

	public void setDateReglementAu(Calendar dateReglementAu) {
		this.dateReglementAu = dateReglementAu;
	}

	public Long getTypeReglementId() {
		return typeReglementId;
	}

	public void setTypeReglementId(Long typeReglementId) {
		this.typeReglementId = typeReglementId;
	}

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public Boolean getRegle() {
		return regle;
	}

	public void setRegle(Boolean regle) {
		this.regle = regle;
	}

	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	public String getTypeReglement() {
		return typeReglement;
	}

	public void setTypeReglement(String typeReglement) {
		this.typeReglement = typeReglement;
	}

	public List<ResultatRechecheDetailReglementElementValue> getEcheancierList() {
		return echeancierList;
	}

	public void setEcheancierList(
			List<ResultatRechecheDetailReglementElementValue> echeancierList) {
		this.echeancierList = echeancierList;
	}

	public Boolean getAvecTerme() {
		return avecTerme;
	}

	public void setAvecTerme(Boolean avecTerme) {
		this.avecTerme = avecTerme;
	}

	public String getNomRapport() {
		return nomRapport;
	}

	public void setNomRapport(String nomRapport) {
		this.nomRapport = nomRapport;
	}

	public Calendar getDateEcheanceDu() {
		return dateEcheanceDu;
	}

	public void setDateEcheanceDu(Calendar dateEcheanceDu) {
		this.dateEcheanceDu = dateEcheanceDu;
	}

	public Calendar getDateEcheanceAu() {
		return dateEcheanceAu;
	}

	public void setDateEcheanceAu(Calendar dateEcheanceAu) {
		this.dateEcheanceAu = dateEcheanceAu;
	}

}
