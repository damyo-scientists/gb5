package com.game.gb5.service.character;

import com.game.gb5.dto.CharacterDto;
import com.game.gb5.model.character.Character;
import com.game.gb5.model.character.CharacterStatus;
import com.game.gb5.model.character.HittingPosition;
import com.game.gb5.repository.character.CharacterRepository;
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

    public Character create(String code, String name, int grade, int acquisitionCoefficient,
                            int cumulativeAcquisitionCoefficient, int backNumber,
                            HittingPosition hittingPosition, List<Double> hittingInclination, CharacterStatus characterStatus) {
        Character character = new Character(0L, code, name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient, backNumber, hittingPosition, hittingInclination, characterStatus);
        return characterRepository.save(character);
    }

    public Character create(Character character) {
        return characterRepository.save(character);
    }

    public Character create(String name, int grade, int acquisitionCoefficient,
                            int cumulativeAcquisitionCoefficient, int backNumber,
                            HittingPosition hittingPosition, List<Double> hittingInclination, CharacterStatus characterStatus) {
        Character character = new Character(name, grade, acquisitionCoefficient, cumulativeAcquisitionCoefficient, backNumber, hittingPosition, hittingInclination, characterStatus);
        character.getCharacterStatus().setCharacter(character);
        return characterRepository.save(character);
    }

    public Character create(CharacterDto characterDto) {
        return this.create(characterDto.getName(), characterDto.getGrade(), 0, 0,
                (int) (long) characterDto.getId(), characterDto.getHittingPosition(), characterDto.getHittingInclination(),
                characterDto.getCharacterStatus());
    }

    public Optional<Character> getById(long characterId) {
        return characterRepository.findById(characterId);
    }

    public Optional<Character> getByCode(String code) {
        return characterRepository.findByCode(code);
    }

    @Async
    public CompletableFuture<List<Character>> importData(List<CharacterDto> characterDtos) {
        List<Character> characters = characterDtos.stream().map(dto -> {
            Optional<Character> character = getByCode(dto.getCode());
            character.ifPresent(value -> {
                dto.setId(value.getId());
                dto.setCreatedDate(value.getCreatedDate());
                dto.setCharacterStatus(value.getCharacterStatus());
            });
            return dto.toEntity();
        }).collect(Collectors.toList());
        return CompletableFuture.completedFuture(characterRepository.saveAll(characters));
    }
}
