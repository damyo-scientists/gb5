package com.game.gb5.dto;

import com.game.gb5.model.Character;
import com.game.gb5.model.CharacterStatus;
import com.game.gb5.model.HittingPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterDto extends BaseDto<Character> {
    @NotNull
    private String name;
    private int grade;
    private int acquisitionCoefficient;
    private int cumulativeAcquisitionCoefficient;
    private int backNumber;
    private HittingPosition hittingPosition;
    private List<Float> hittingInclination;
    @NotNull
    private CharacterStatus characterStatus;

    public CharacterDto(Long id, String code, String name, int grade, int acquisitionCoefficient, int cumulativeAcquisitionCoefficient, int backNumber,
                        HittingPosition hittingPosition, List<Float> hittingInclination, CharacterStatus characterStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.grade = grade;
        this.acquisitionCoefficient = acquisitionCoefficient;
        this.cumulativeAcquisitionCoefficient = cumulativeAcquisitionCoefficient;
        this.backNumber = backNumber;
        this.hittingPosition = hittingPosition;
        this.hittingInclination = hittingInclination;
        this.characterStatus = characterStatus;
    }

    public Character toEntity() {
        Character character = new Character(id, code, name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient,
                backNumber, hittingPosition, hittingInclination, characterStatus);
        if (createdDate != null) {
            character.setCreatedDate(createdDate);
        }
        return character;
    }
}
