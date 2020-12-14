package com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value;

import java.util.Calendar;

public class BonSortieFiniOptimizedValue {

	    private Long id;
	   
	    private String reference;
	    
		private Calendar dateSortie;
		
		private boolean enCours;
		
		
	    private String type;
	     
	     
	     
		
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isEnCours() {
			return enCours;
		}
		public void setEnCours(boolean enCours) {
			this.enCours = enCours;
		}
		public Calendar getDateSortie() {
			return dateSortie;
		}
		public void setDateSortie(Calendar dateSortie) {
			this.dateSortie = dateSortie;
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
	    
	    
	    
	
}
