package com.gpro.consulting.tiers.logistique.persistance.caisse.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.persistance.caisse.entity.MouvementCaisseEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Hamdi Etteieb
 * @since 16/08/2018
 *
 */

@Component
public class MouvementCaissePersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(MouvementCaissePersistanceUtilities.class);

	public MouvementCaisseValue toValue(MouvementCaisseEntity entity) {

		MouvementCaisseValue dto = new MouvementCaisseValue();

		dto.setId(entity.getId());
		dto.setDate(entity.getDate());
		dto.setDestinataire(entity.getDestinataire());
		dto.setCaisseId(entity.getCaisseId());
		dto.setTypeMouvement(entity.getTypeMouvement());
		dto.setMontantMouvement(entity.getMontantMouvement());
		return dto;
	}

	public MouvementCaisseEntity toEntity(MouvementCaisseValue dto) {

		MouvementCaisseEntity entity = new MouvementCaisseEntity();

		entity.setId(dto.getId());
		entity.setDate(dto.getDate());
		entity.setCaisseId(dto.getCaisseId());
		entity.setDestinataire(dto.getDestinataire());
		entity.setTypeMouvement(dto.getTypeMouvement());
		entity.setMontantMouvement(dto.getMontantMouvement());
		return entity;
	}

	/**
	 * To value.
	 *
	 * @param listEntity
	 *            the list entity
	 * @return the list
	 */
	public List<MouvementCaisseValue> toValue(List<MouvementCaisseEntity> listEntity) {
		List<MouvementCaisseValue> list = new ArrayList<MouvementCaisseValue>();

		for (MouvementCaisseEntity entity : listEntity) {
			list.add(toValue(entity));
		}

		return list;
	}

}
