package com.game.gb5.service;

import com.game.gb5.entity.character.GameCharacter;
import com.game.gb5.player.model.entity.player.Player;
import com.game.gb5.entity.scouting.report.ReportCharacter;

import java.util.ArrayList;
import java.util.List;

public class CharacterRecruitmentService {
	
	public List<GameCharacter> recruitCharacters(Player player, List<ReportCharacter> recruitCharacterList) {
		List<GameCharacter> playerCharacters = player.getCharacters();
		List<GameCharacter> recruitedCharacter = new ArrayList<>();
		for (ReportCharacter reportCharacter : recruitCharacterList) {
			if (playerCharacters.contains(reportCharacter.getTargetCharacter())) {
				// 조각화
			} else {
				playerCharacters.add(reportCharacter.getTargetCharacter());
				recruitedCharacter.add(reportCharacter.getTargetCharacter());
			}
		}
		return recruitedCharacter;
	}
}
