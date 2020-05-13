package com.game.gb5.service.scouting;

import com.game.gb5.model.character.CharacterReportStatus;
import com.game.gb5.model.character.Character;
import com.game.gb5.model.scouting.ScouterStatus;
import com.game.gb5.system.deflection.DeflectedValue;
import com.game.gb5.system.deflection.DeflectedValueMaker;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class CharacterReportStatusService {

	public CharacterReportStatus getDeflectedCharacterReport(Character targetCharacter, ScouterStatus scouterStatus, long seed) {
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
