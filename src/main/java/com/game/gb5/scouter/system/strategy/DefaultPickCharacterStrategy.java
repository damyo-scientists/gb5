package com.game.gb5.scouter.system.strategy;

import com.game.gb5.character.model.EmptyCharacter;
import com.game.gb5.character.model.Character;
import com.game.gb5.scouter.model.ScouterStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.Getter;

public class DefaultPickCharacterStrategy implements PickCharacterStrategy {
	private static final int GRADE_RATE_TOTAL = 100;
	@Getter
	private Long seed;

	public DefaultPickCharacterStrategy(Long seed) {
		this.seed = seed;
	}

	public List<Character> pickCharacters(List<Character> characterList, ScouterStatus scouterStatus) {
		List<Integer> gradeList = pickGradeList(scouterStatus);
		return gradeList.stream().map(grade -> pickCharacter(grade, characterList))
				.collect(Collectors.toList());
	}

	private Character pickCharacter(int grade, List<Character> characterList) {
		int sum = 0;
		List<Character> filteredList = characterList.stream().filter(character -> character.getGrade() == grade + 1).collect(Collectors.toList());

		for (Character character : filteredList) {
			sum += character.getAcquisitionCoefficient();
		}
		int rand = new Random().nextInt(sum);
		sum = 0;
		for (Character character : filteredList) {
			if (rand >= sum && rand < sum + character.getAcquisitionCoefficient()) {
				return character;
			}
			sum += character.getAcquisitionCoefficient();
		}
		return new EmptyCharacter();
	}

	private List<Integer> pickGradeList(ScouterStatus scouterStatus) {
		List<Integer> gradeAcquisitionRate = scouterStatus.getGradeAcquisitionRates();
		int cnt = scouterStatus.getReportChracterSize();
		boolean isAllZero = true;
		List<Integer> gradeList = new ArrayList<>();

		for (int i = 0; i < cnt; i++) {
			int grade = pickGrade(gradeAcquisitionRate);
			if (grade != 0) {
				isAllZero = false;
			}
			gradeList.add(grade);
		}
		if (scouterStatus.isGiveMinimumGradeAssured() && isAllZero) {
			// isGiveMinimumGradeAssured 보정
			gradeList.set(new Random().nextInt(cnt), 1);
		}
		return gradeList;
	}

	private int pickGrade(List<Integer> gradeAcquisitionRate) {
		int rangeStart = 0;
		int rangeEnd;
		int rand = new Random().nextInt(GRADE_RATE_TOTAL);
		int selectedGrade = -1;
		for (int i = 0; i < gradeAcquisitionRate.size(); i++) {
			rangeEnd = rangeStart + gradeAcquisitionRate.get(i);
			if (rand > rangeStart && rand <= rangeEnd) {
				selectedGrade = i;
				break;
			}
			rangeStart = rangeEnd;
		}
		return selectedGrade;
	}
}
