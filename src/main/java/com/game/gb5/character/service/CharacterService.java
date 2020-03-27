package com.game.gb5.character.service;

import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.model.HittingPosition;
import com.game.gb5.character.respository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    public GameCharacter create(String name, int grade, int acquisitionCoefficient,
                                int cumulativeAcquisitionCoefficient, int backNumber,
                                HittingPosition hittingPosition, List<Float> hittingInclination, CharacterStatus characterStatus) {
        GameCharacter gameCharacter = new GameCharacter(name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient, backNumber, hittingPosition, hittingInclination, characterStatus);
        return characterRepository.save(gameCharacter);
    }

    public Optional<GameCharacter> getById(long characterId) {
        return characterRepository.findById(characterId);
    }
}
