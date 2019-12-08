package com.game.gb5.scouting.strategy;

import com.game.gb5.entity.character.Character;
import com.game.gb5.scouting.ScouterStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultPickCharacterStrategy implements PickCharacterStrategy {
	private final int TOTAL = 100;
	
	@Override
	public List<Integer> pickGradeList(List<Character> characterList, ScouterStatus scouterStatus) {
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
			// TODO 2019-11-27 여기까지했음
			gradeList.set(new Random().nextInt(cnt), 1);
		}
		return gradeList;
	}
	
	private int pickGrade(List<Integer> gradeAcquisitionRate) {
		int rangeStart = 0;
		int rangeEnd;
		int rand = new Random().nextInt(TOTAL);
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
	
	public Character pickCharacter(List<Character> characterList, ScouterStatus scouterStatus, int selectedGrade) {
		int rangeStart = 0;
		int sum = 0;
		List<Character> filteredList = characterList.stream().filter(character -> character.getRate() == selectedGrade + 1).collect(Collectors.toList());
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
