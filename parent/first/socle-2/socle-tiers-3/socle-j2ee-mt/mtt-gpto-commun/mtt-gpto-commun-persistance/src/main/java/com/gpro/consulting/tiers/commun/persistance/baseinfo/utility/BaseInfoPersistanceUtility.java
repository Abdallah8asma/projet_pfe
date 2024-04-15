package com.gpro.consulting.tiers.commun.persistance.baseinfo.utility;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.entity.BaseInfoEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */

public class BaseInfoPersistanceUtility {

	private static BaseInfoPersistanceUtility instance = new BaseInfoPersistanceUtility();

	public static BaseInfoPersistanceUtility getInstance() {
		
		return instance;
	}

	public BaseInfoValue toValue(BaseInfoEntity entity) {

		BaseInfoValue dto = new BaseInfoValue();

		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setValeur(entity.getValeur());
		dto.setUnite(entity.getUnite());

		return dto;
	}

	public BaseInfoEntity toEntity(BaseInfoValue dto) {

		BaseInfoEntity entity = new BaseInfoEntity();

		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		entity.setValeur(dto.getValeur());
		entity.setUnite(dto.getUnite());

		return entity;
	}
}
