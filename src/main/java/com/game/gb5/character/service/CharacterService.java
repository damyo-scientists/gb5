package com.game.gb5.character.service;

import com.game.gb5.character.dto.CharacterDto;
import com.game.gb5.character.model.CharacterStatus;
import com.game.gb5.character.model.GameCharacter;
import com.game.gb5.character.model.HittingPosition;
import com.game.gb5.character.respository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    public GameCharacter create(CharacterDto characterDto) {
        return this.create(characterDto.getName(), characterDto.getGrade(), 0, 0,
                (int) (long) characterDto.getId(), characterDto.getHittingPosition(), characterDto.getHittingInclination(),
                characterDto.getCharacterStatus());
    }

    public Optional<GameCharacter> getById(long characterId) {
        return characterRepository.findById(characterId);
    }

    @Async
    public CompletableFuture<List<GameCharacter>> importData(List<CharacterDto> characterDtos) {
        List<GameCharacter> gameCharacters = characterDtos.stream().map(CharacterDto::toEntity).collect(Collectors.toList());
        return CompletableFuture.completedFuture(characterRepository.saveAll(gameCharacters));
    }

    public List<GameCharacter> importDataNonAsync(List<CharacterDto> characterDtos) {
        List<GameCharacter> gameCharacters = characterDtos.stream().map(CharacterDto::toEntity).collect(Collectors.toList());
        return characterRepository.saveAll(gameCharacters);
    }
}
