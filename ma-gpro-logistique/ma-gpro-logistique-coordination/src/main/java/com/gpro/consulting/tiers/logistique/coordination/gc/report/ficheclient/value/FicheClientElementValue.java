package com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public class FicheClientElementValue {
//implements Comparable<FicheClientComparator>
	private Calendar date;
	private String libelle;
	private Double debit;
	private Double credit;
	private String referenceComparator;
	
	private Double balance;
	
	
	private String type;
	
	
	public static String TYPE_BL = "BL";
	public static String TYPE_FACTURE = "FACTURE";
	public static String TYPE_AVOIR = "AVOIR";
	public static String TYPE_REGLEMENT = "REGLEMENT";
	
	public static String TYPE_REGLEMENT_INVERSE = "REGLEMENT_INVERSE";

//	@Override
//	public int compareTo(FicheClientElementValue element) {
//
//		return (element.getDate().compareTo(date));
//	}
	


	public Double getBalance() {
		return balance;
	}



	public void setBalance(Double balance) {
		this.balance = balance;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getReferenceComparator() {
		return referenceComparator;
	}



	public void setReferenceComparator(String referenceComparator) {
		this.referenceComparator = referenceComparator;
	}



	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}



	
}
