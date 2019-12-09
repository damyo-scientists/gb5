package com.game.gb5.domain.scouting.strategy;

import com.game.gb5.domain.character.Character;
import com.game.gb5.domain.scouting.ScouterStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultPickCharacterStrategy implements PickCharacterStrategy {
	private final int GRADE_RATE_TOTAL = 100;
	
	public List<Character> pickCharacters(List<Character> characterList, ScouterStatus scouterStatus) {
		List<Integer> gradeList = pickGradeList(scouterStatus);
		List<Character> pickList = new ArrayList<>();
		gradeList.forEach(grade -> pickList.add(pickCharacter(grade, characterList)));
		return pickList;
	}
	
	private Character pickCharacter(int grade, List<Character> characterList) {
		int sum = 0;
		List<Character> filteredList = characterList.stream().filter(character -> character.getRate() == grade + 1).collect(Collectors.toList());
		
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
		return null;
	}
	
	private List<Integer> pickGradeList(ScouterStatus scouterStatus) {
		List<Integer> gradeAcquisitionRate = scouterStatus.getGradeAcquisitionRate();
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
