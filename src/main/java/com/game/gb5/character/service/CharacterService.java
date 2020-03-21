package com.game.gb5.character.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.gb5.character.model.CharacterSet;
import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.model.HittingPosition;
import com.game.gb5.character.respository.CharacterRepository;
import com.game.gb5.game.model.Game;
import com.game.gb5.player.model.Player;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Service
public class CharacterService {
    private CharacterRepository characterRepository;

    public GameCharacter createCharacter(String name, int grade, int acquisitionCoefficient,
                                         int cumulativeAcquisitionCoefficient, int backNumber,
                                         HittingPosition hittingPosition, List<Float> hittingInclination, CharacterStatus characterStatus) {
        GameCharacter gameCharacter = new GameCharacter(name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient, backNumber, hittingPosition, hittingInclination, characterStatus);
        return characterRepository.save(gameCharacter);
    }
}
