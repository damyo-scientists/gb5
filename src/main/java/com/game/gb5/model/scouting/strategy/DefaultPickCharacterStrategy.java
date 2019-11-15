package com.game.gb5.model.scouting.strategy;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.scouting.ScouterStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultPickCharacterStrategy implements PickCharacterStrategy {
	@Override
	public Character pickCharacter(List<Character> characterList, ScouterStatus scouterStatus) {
		List<Integer> chanceOfRateSelected = scouterStatus.getChanceOfRateSelected();
		int rangeStart = 0;
		int rangeEnd;
		int total = 100;
		int rand = new Random().nextInt(total);
		int selectedRate = -1;
		for (int i = 0; i < chanceOfRateSelected.size(); i++) {
			rangeEnd = rangeStart + chanceOfRateSelected.get(i);
			if (rand > rangeStart && rand <= rangeEnd) {
				selectedRate = i;
				break;
			}
			rangeStart = rangeEnd;
		}
		final int finalSelectedRate = selectedRate;
		rangeStart = 0;
		int sum = 0;
		List<Character> filteredList = characterList.stream().filter(character -> character.getRate() == finalSelectedRate + 1).collect(Collectors.toList());
		List<CoefficientRange> coefList = new ArrayList<>();
		for (Character character : filteredList) {
			rangeEnd = rangeStart + character.getAcquisitionCoefficient();
			sum += character.getAcquisitionCoefficient();
			coefList.add(new CoefficientRange(character, rangeStart, rangeEnd));
		}
		rand = new Random().nextInt(sum);
		for (CoefficientRange coefRange : coefList) {
			if (coefRange.rangeStart > rand && rand <= coefRange.rangeEnd) {
				return coefRange.character;
			}
		}
		return null;
	}
	
	static class CoefficientRange {
		Character character;
		int rangeStart;
		int rangeEnd;
		
		CoefficientRange(Character character, int rangeStart, int rangeEnd) {
			this.character = character;
			this.rangeStart = rangeStart;
			this.rangeEnd = rangeEnd;
		}
	}
}
