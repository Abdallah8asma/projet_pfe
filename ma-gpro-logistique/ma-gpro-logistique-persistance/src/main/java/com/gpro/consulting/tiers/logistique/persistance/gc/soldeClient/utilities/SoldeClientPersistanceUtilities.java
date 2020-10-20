package com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.utilities;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.entite.SoldeClientEntity;

/**
 * @author Ameni Berrich
 *
 */
public class SoldeClientPersistanceUtilities {

	private SoldeClientPersistanceUtilities(){
		
	}
	
	public static SituationReportingValue toSituationReportingValue(SoldeClientEntity entity) {

		SituationReportingValue dto = new SituationReportingValue();

		dto.setId(entity.getId());
		dto.setPartieIntId(entity.getPartIntId());
		dto.setSoldeInit(null);

		return dto;
	}
	
	public static SoldeClientValue toValue(SoldeClientEntity entity) {

		SoldeClientValue dto = new SoldeClientValue();

		if(entity != null){
			
			dto.setId(entity.getId());
			dto.setBloque(entity.getBloque());
			dto.setDateIntroduction(entity.getDateIntroduction());
			dto.setObservation(entity.getObservation());
			dto.setPartIntId(entity.getPartIntId());
			dto.setSeuil(entity.getSeuil());
			dto.setSoldeInitial(entity.getSoldeInitial());
			dto.setChiffreAffaireTmp(entity.getChiffreAffaireTmp());
			dto.setDateDemarrage(entity.getDateDemarrage());
			dto.setReglementTmp(entity.getReglementTmp());
		}
		
		return dto;
	}
	
	public static SoldeClientEntity toEntity(SoldeClientValue dto) {

		SoldeClientEntity entity = new SoldeClientEntity();

		entity.setId(dto.getId());
		entity.setBloque(dto.getBloque());
		entity.setDateIntroduction(dto.getDateIntroduction());
		entity.setObservation(dto.getObservation());
		entity.setPartIntId(dto.getPartIntId());
		entity.setSeuil(dto.getSeuil());
		entity.setSoldeInitial(dto.getSoldeInitial());
		entity.setChiffreAffaireTmp(dto.getChiffreAffaireTmp());
		entity.setDateDemarrage(dto.getDateDemarrage());
		entity.setReglementTmp(dto.getReglementTmp());
		return entity;
	}
	
}
