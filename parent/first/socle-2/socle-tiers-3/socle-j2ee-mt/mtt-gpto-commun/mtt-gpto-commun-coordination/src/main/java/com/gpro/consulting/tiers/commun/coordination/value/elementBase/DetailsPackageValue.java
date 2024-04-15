package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

/**
	* Classe: REMISE
	* @author: $SAMER
	* 
	*/

public class DetailsPackageValue   {

		private Long id;
		

		private Double remise;
		

		private Long produitId;
		
		
	
		private String observations;
		
		
	
		private Long packageId;
		
		
		
		
	

		/************* Getters & Setters *****************/
	

		/************* Equals & ToString *****************/
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

	


		public Long getId() {
			return id;
		}




		public void setId(Long id) {
			this.id = id;
		}




		public Double getRemise() {
			return remise;
		}




		public void setRemise(Double remise) {
			this.remise = remise;
		}




		public Long getProduitId() {
			return produitId;
		}




		public void setProduitId(Long produitId) {
			this.produitId = produitId;
		}




		public String getObservations() {
			return observations;
		}




		public void setObservations(String observations) {
			this.observations = observations;
		}




	


		public Long getPackageId() {
			return packageId;
		}




		public void setPackageId(Long packageId) {
			this.packageId = packageId;
		}




		@Override
		public String toString() {
			return super.toString();
		}	
}

