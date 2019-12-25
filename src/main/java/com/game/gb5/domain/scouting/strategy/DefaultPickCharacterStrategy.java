package com.game.gb5.domain.scouting.strategy;

import com.game.gb5.domain.character.CharacterStatusReport;
import com.game.gb5.domain.character.EmptyGameCharacter;
import com.game.gb5.domain.character.GameCharacter;
import com.game.gb5.domain.scouting.ScouterStatus;

import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultPickCharacterStrategy implements PickCharacterStrategy {
	private static final int GRADE_RATE_TOTAL = 100;
	private Random rand = new Random();
	
	public List<GameCharacter> pickCharacters(List<GameCharacter> characterList, ScouterStatus scouterStatus) {
		List<Integer> gradeList = pickGradeList(scouterStatus);
		return gradeList.stream().map(grade -> {
			GameCharacter pickedCharacter = pickCharacter(grade, characterList);
			return getDeflectedCharacter(pickedCharacter, scouterStatus);
		}).collect(Collectors.toList());
	}
	
	private GameCharacter getDeflectedCharacter(GameCharacter targetCharacter, ScouterStatus scouterStatus) {
		
		CharacterStatusReport characterStatusReport = new CharacterStatusReport();
		
		ReflectionUtils.doWithFields(targetCharacter.getCharacterStatus().getClass(), field -> {
			int firstRandomDeflection = (int) (rand.nextInt() * scouterStatus.getDeflection());
			int baseStatus = (int) ReflectionUtils.getField(field, targetCharacter.getCharacterStatus());
			int maxValue = baseStatus + firstRandomDeflection;
			if (maxValue > 100) {
				maxValue = 100;
			}
			
			int secondRandomDeflection = scouterStatus.getDeflection() - firstRandomDeflection + (int) (rand.nextInt() * scouterStatus.getDeflectionRandomizeValue());
			int minValue = baseStatus - secondRandomDeflection;
			
			if (minValue < 0) {
				minValue = 0;
			}
			
			ReflectionUtils.setField(field, characterStatusReport,
					characterStatusReport.toReflectedValue(minValue, maxValue));
		});
		
		targetCharacter.setCharacterStatusReport(characterStatusReport);
		return targetCharacter;
	}
	
	private GameCharacter pickCharacter(int grade, List<GameCharacter> characterList) {
		int sum = 0;
		List<GameCharacter> filteredList = characterList.stream().filter(character -> character.getGrade() == grade + 1).peek((a) -> System.out.println("hey" + a)).collect(Collectors.toList());
		
		for (GameCharacter character : filteredList) {
			sum += character.getAcquisitionCoefficient();
		}
		int rand = new Random().nextInt(sum);
		sum = 0;
		for (GameCharacter character : filteredList) {
			if (rand >= sum && rand < sum + character.getAcquisitionCoefficient()) {
				return character;
			}
			sum += character.getAcquisitionCoefficient();
		}
		return new EmptyGameCharacter();
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
