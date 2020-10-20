package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;
import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.IConstante;


/**
 * The Class ArticleValue.
 *
 * @author $Ghazi
 */

public class ArticleCacheValue {

	private Long id;

	/** The ref. */
	
	private String ref;

	/** The dedignation. */
	private String designation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	

}
