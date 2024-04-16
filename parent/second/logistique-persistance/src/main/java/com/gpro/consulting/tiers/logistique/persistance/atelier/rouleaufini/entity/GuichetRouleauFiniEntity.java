package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * @author rkhaskho
 *
 */
@Entity 
@Table(name = IConstanteLogistique.TABLE_GENERATEUR_ROULEAU)
public class GuichetRouleauFiniEntity implements Serializable {
	private static final long serialVersionUID = -4991838957614829573L;
	
	 /** L'id de la table: l'Année. */
	  @Id
	  private Long annee;

	  /** Numéro courant. */
	  @Column(name = "ref")
	  private Long numReferenceCourante;
      
	  /**prefixe .*/
	  @Column(name="prefixe")
	  
	  private Integer prefixe;
	  
	 /**
	 * @return the annee
	 */
	public Long getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(Long annee) {
		this.annee = annee;
	}

	/**
	 * @return the numReferenceCourante
	 */
	public Long getNumReferenceCourante() {
		return numReferenceCourante;
	}

	/**
	 * @param numReferenceCourante the numReferenceCourante to set
	 */
	public void setNumReferenceCourante(Long numReferenceCourante) {
		this.numReferenceCourante = numReferenceCourante;
	}

	/** Accesseur en lecture de l'attribut prefixe.
	 * @return the prefixe
	 */
	public Integer getPrefixe() {
		return prefixe;
	}

	/**
	 * @param prefixe the prefixe to set
	 */
	public void setPrefixe(Integer prefixe) {
		this.prefixe = prefixe;
	}
	  
	  
	  

}
