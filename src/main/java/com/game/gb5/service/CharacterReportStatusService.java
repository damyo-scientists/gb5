package com.game.gb5.service;

import com.game.gb5.domain.character.CharacterReportStatus;
import com.game.gb5.domain.character.GameCharacter;
import com.game.gb5.domain.scouting.ScouterStatus;
import com.game.gb5.domain.scouting.deflection.DeflectedValue;
import com.game.gb5.domain.scouting.deflection.DeflectedValueMaker;

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
