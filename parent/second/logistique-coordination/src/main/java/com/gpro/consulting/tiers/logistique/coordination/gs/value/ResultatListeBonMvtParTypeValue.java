package com.gpro.consulting.tiers.logistique.coordination.gs.value;

/**
 * 
 * @author Zeineb
 * @since 09/11/2016
 *
 */

public class ResultatListeBonMvtParTypeValue implements Comparable<ResultatListeBonMvtParTypeValue> {

	/** The Id **/
	private Long id;
	
	/** The ref. */

	private String numero;
	
	/**
	 * idOF
	 */
	private Long idOF;
	
	public ResultatListeBonMvtParTypeValue(Long id, String numero, Long idOF) {
		super();
		this.id = id;
		this.numero = numero;
		this.idOF = idOF;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Long getIdOF() {
		return idOF;
	}

	public void setIdOF(Long idOF) {
		this.idOF = idOF;
	}

	@Override
	public int compareTo(ResultatListeBonMvtParTypeValue o) {

		ResultatListeBonMvtParTypeValue element = (ResultatListeBonMvtParTypeValue)o;
		return element.getNumero().compareTo(numero);
	}

}
