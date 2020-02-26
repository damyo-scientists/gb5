package com.game.gb5.scouter.service;

import com.game.gb5.character.model.entity.CharacterReportStatus;
import com.game.gb5.character.model.entity.GameCharacter;
import com.game.gb5.scouter.model.entity.ScouterStatus;
import com.game.gb5.scouter.system.deflection.DeflectedValue;
import com.game.gb5.scouter.system.deflection.DeflectedValueMaker;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class CharacterReportStatusService {
	
	public CharacterReportStatus getDeflectedCharacterReport(GameCharacter targetCharacter, ScouterStatus scouterStatus, long seed) {
		CharacterReportStatus characterReportStatus = new CharacterReportStatus();
		DeflectedValueMaker deflectedValueMaker = new DeflectedValueMaker(seed);
		ReflectionUtils.doWithFields(targetCharacter.getCharacterStatus().getClass(), field -> {
			if (field.getType() == int.class) {
				field.setAccessible(true);
				int baseStatus = (int) ReflectionUtils.getField(field, targetCharacter.getCharacterStatus());
				
				DeflectedValue deflectedValue = deflectedValueMaker.changeBaseStatusToDeflectedValue(baseStatus, scouterStatus.getDeflection(), scouterStatus.getDeflectionRandomizeValue());
				
				try {
					ReflectionUtils.setField(CharacterReportStatus.class.getDeclaredField(field.getName()), characterReportStatus,
							deflectedValue.toString());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
		});
		
		return characterReportStatus;
	}
}
