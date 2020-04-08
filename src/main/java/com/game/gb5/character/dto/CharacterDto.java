package com.game.gb5.character.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.model.HittingPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CharacterDto {
    private Long id;
    private String code;
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

    public GameCharacter toEntity() {
        return new GameCharacter(id, code, name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient,
                backNumber, hittingPosition, hittingInclination, characterStatus);
    }
}
