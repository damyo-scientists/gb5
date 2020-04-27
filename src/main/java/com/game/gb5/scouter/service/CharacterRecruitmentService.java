package com.game.gb5.scouter.service;

import com.game.gb5.character.model.Character;
import com.game.gb5.inventory.model.item.CharacterPiece;
import com.game.gb5.player.model.Player;
import com.game.gb5.scouter.model.report.ReportCharacter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterRecruitmentService {
	public List<Character> recruitCharacters(Player player, List<ReportCharacter> recruitCharacterList) {
		List<Character> playerCharacters = player.getCharacters();
		List<Character> recruitedCharacter = new ArrayList<>();
		for (ReportCharacter reportCharacter : recruitCharacterList) {
			if (playerCharacters.contains(reportCharacter.getTargetCharacter())) {
				CharacterPiece characterPiece = new CharacterPiece(reportCharacter.getTargetCharacter());
				Integer countBefore = player.getInventory().getCharacterPieces().get(characterPiece);
				player.getInventory().getCharacterPieces().put(characterPiece, countBefore + 1);
			} else {
				playerCharacters.add(reportCharacter.getTargetCharacter());
				recruitedCharacter.add(reportCharacter.getTargetCharacter());
			}
		}
		return recruitedCharacter;
	}
}
