package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.Calendar;

/**
 * Critère de recherche du rouleau standard
 * @author rkhaskho
 *
 */
public class CritereRechercheRouleauStandardValue {

  /** Client */
  private Long client ;
  
  /** nombre Colis du */
  private Long nombreColieDu;
  
  /** nombre Colis a  */
  private Long nombreColieA;
  
  /** Entrepot */
  private Long entrepot;

  /** Emplacement */
  private String emplacement ;
  
  /** Metrage du */
  private Double metrageDu;
  
  /** Metrage a */
  private Double metrageA;
  
  /**
   * Return RouleauFini if dateIntroduction <= dateEtat AND (dateSortie = null OR dateSortie >= dateEtat)
   */
  private Calendar dateEtat;
  
  /** designationQuiContient*/
  private String designationQuiContient;
  
  /** referenceProduit*/
  private String referenceProduit;
  
  /** orderBy reference, metrage or prix, 
   *  see {@link IConstanteLogistique}
   */
  private String orderBy;
  
  private String fini; // oui, non , tous -- IConstant


/**
   * @return the client
   */
  public Long getClient() {
    return client;
  }

  /**
   * @param client the client to set
   */
  public void setClient(Long client) {
    this.client = client;
  }

  /**
   * @return the nombreColieDu
   */
  public Long getNombreColieDu() {
    return nombreColieDu;
  }

  /**
   * @param nombreColieDu the nombreColieDu to set
   */
  public void setNombreColieDu(Long nombreColieDu) {
    this.nombreColieDu = nombreColieDu;
  }

  /**
   * @return the nombreColieA
   */
  public Long getNombreColieA() {
    return nombreColieA;
  }

  /**
   * @param nombreColieA the nombreColieA to set
   */
  public void setNombreColieA(Long nombreColieA) {
    this.nombreColieA = nombreColieA;
  }



  /**
   * @return the entrepot
   */
  public Long getEntrepot() {
    return entrepot;
  }

  /**
   * @param entrepot the entrepot to set
   */
  public void setEntrepot(Long entrepot) {
    this.entrepot = entrepot;
  }

  /**
   * @return the emplacement
   */
  public String getEmplacement() {
    return emplacement;
  }

  /**
   * @param emplacement the emplacement to set
   */
  public void setEmplacement(String emplacement) {
    this.emplacement = emplacement;
  }

  /**
   * @return the metrageDu
   */
  public Double getMetrageDu() {
    return metrageDu;
  }

  /**
   * @param metrageDu the metrageDu to set
   */
  public void setMetrageDu(Double metrageDu) {
    this.metrageDu = metrageDu;
  }

  /**
   * @return the metrageA
   */
  public Double getMetrageA() {
    return metrageA;
  }

  /**
   * @param metrageA the metrageA to set
   */
  public void setMetrageA(Double metrageA) {
    this.metrageA = metrageA;
  }
  
  
  public String getDesignationQuiContient() {
	return designationQuiContient;
  }

  public void setDesignationQuiContient(String designationQuiContient) {
	  this.designationQuiContient = designationQuiContient;
  }
  
  public String getOrderBy() {
	return orderBy;
  }

  public void setOrderBy(String orderBy) {
	  this.orderBy = orderBy;
  }
  
  public Calendar getDateEtat() {
	return dateEtat;
  }

  public void setDateEtat(Calendar dateEtat) {
	  this.dateEtat = dateEtat;
  }
  
  	public String getFini() {
		return fini;
	}

	public void setFini(String fini) {
		this.fini = fini;
	}
	
  public String getReferenceProduit() {
		return referenceProduit;
	}

	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

/* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CritereRechercheRouleauStandardValue [");
    if (client != null) {
      builder.append("client=");
      builder.append(client);
      builder.append(", ");
    }
    builder.append("nombreColieDu=");
    builder.append(nombreColieDu);
    builder.append(", nombreColieA=");
    builder.append(nombreColieA);
    builder.append(", ");
    if (entrepot != null) {
      builder.append("entrepot=");
      builder.append(entrepot);
      builder.append(", ");
    }
    if (emplacement != null) {
      builder.append("emplacement=");
      builder.append(emplacement);
      builder.append(", ");
    }
    if (metrageDu != null) {
      builder.append("metrageDu=");
      builder.append(metrageDu);
      builder.append(", ");
    }
    if (metrageA != null) {
      builder.append("metrageA=");
      builder.append(metrageA);
    }
    builder.append("]");
    return builder.toString();
  }

  
}
