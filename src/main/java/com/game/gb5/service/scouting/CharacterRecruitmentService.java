package com.game.gb5.service.scouting;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.item.CharacterPiece;
import com.game.gb5.model.user.User;
import com.game.gb5.model.report.ReportCharacter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterRecruitmentService {
	public List<Character> recruitCharacters(User user, List<ReportCharacter> recruitCharacterList) {
		List<Character> userCharacters = user.getCharacters();
		List<Character> recruitedCharacter = new ArrayList<>();
		for (ReportCharacter reportCharacter : recruitCharacterList) {
			if (userCharacters.contains(reportCharacter.getTargetCharacter())) {
				CharacterPiece characterPiece = new CharacterPiece(reportCharacter.getTargetCharacter());
				Integer countBefore = user.getInventory().getCharacterPieces().get(characterPiece);
				user.getInventory().getCharacterPieces().put(characterPiece, countBefore + 1);
			} else {
				userCharacters.add(reportCharacter.getTargetCharacter());
				recruitedCharacter.add(reportCharacter.getTargetCharacter());
			}
		}
		return recruitedCharacter;
	}
}
