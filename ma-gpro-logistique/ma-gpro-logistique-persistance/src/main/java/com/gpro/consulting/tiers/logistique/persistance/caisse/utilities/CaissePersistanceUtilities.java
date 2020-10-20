package com.gpro.consulting.tiers.logistique.persistance.caisse.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.persistance.caisse.entity.CaisseEntity;

// TODO: Auto-generated Javadoc
/**
 * Mapping Class from DTO to Entity, and from Entity to DTO.
 *
 * @author Hamdi Etteieb
 * @since 16/08/2018
 */

@Component
public class CaissePersistanceUtilities {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CaissePersistanceUtilities.class);

	/**
	 * To value.
	 *
	 * @param entity
	 *            the entity
	 * @return the caisse value
	 */
	public CaisseValue toValue(CaisseEntity entity) {

		CaisseValue dto = new CaisseValue();

		dto.setId(entity.getId());
		dto.setReference(entity.getReference());
		dto.setDate(entity.getDate());
		dto.setDateCloture(entity.getDateCloture());
		dto.setMagasinId(entity.getMagasinId());
		dto.setUserId(entity.getUserId());
		dto.setMontantCheque(entity.getMontantCheque());
		dto.setMontantEspeces(entity.getMontantEspeces());
		dto.setCloture(entity.isCloture());
		return dto;
	}

	/**
	 * To entity.
	 *
	 * @param dto
	 *            the dto
	 * @return the caisse entity
	 */
	public CaisseEntity toEntity(CaisseValue dto) {

		CaisseEntity entity = new CaisseEntity();

		entity.setId(dto.getId());
		entity.setReference(dto.getReference());
		entity.setDate(dto.getDate());
		entity.setDateCloture(dto.getDateCloture());
		entity.setMagasinId(dto.getMagasinId());
		entity.setUserId(dto.getUserId());
		entity.setMontantCheque(dto.getMontantCheque());
		entity.setMontantEspeces(dto.getMontantEspeces());
		entity.setCloture(dto.isCloture());
		return entity;
	}

	/**
	 * To value.
	 *
	 * @param listEntity
	 *            the list entity
	 * @return the list
	 */
	public List<CaisseValue> toValue(List<CaisseEntity> listEntity) {
		List<CaisseValue> list = new ArrayList<CaisseValue>();

		for (CaisseEntity entity : listEntity) {
			list.add(toValue(entity));
		}

		return list;
	}

}
