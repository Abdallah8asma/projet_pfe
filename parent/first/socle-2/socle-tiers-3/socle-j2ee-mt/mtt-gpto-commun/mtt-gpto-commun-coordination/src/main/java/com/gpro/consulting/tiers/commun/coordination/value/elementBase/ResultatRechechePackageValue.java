package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechechePackageValue {

  private Long nombreResultaRechercher;

  private Set<PackageValue> list = new TreeSet<PackageValue>();

public Long getNombreResultaRechercher() {
	return nombreResultaRechercher;
}

public void setNombreResultaRechercher(Long nombreResultaRechercher) {
	this.nombreResultaRechercher = nombreResultaRechercher;
}

public Set<PackageValue> getList() {
	return list;
}

public void setList(Set<PackageValue> list) {
	this.list = list;
}


  
  
  
  
}
