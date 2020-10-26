package com.game.gb5.model.deck;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.user.User;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Deck extends BaseEntity {

  public Deck(Long id, String code, Map<Position, DeckCharacter> deckCharacters, User user) {
    this.id = id;
    this.code = code;
    this.deckCharacters = deckCharacters;
    this.user = user;
  }

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @MapKeyEnumerated(EnumType.STRING)
  private Map<Position, DeckCharacter> deckCharacters = new HashMap<>();

  @ManyToOne
  private User user;
}
