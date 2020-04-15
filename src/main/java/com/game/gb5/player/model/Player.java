package com.game.gb5.player.model;

import com.game.gb5.character.model.Character;
import com.game.gb5.common.model.BaseEntity;
import com.game.gb5.deck.model.Deck;
import com.game.gb5.inventory.model.inventory.Inventory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Player extends BaseEntity {
    public Player(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.inventory = new Inventory(this);
    }

    @Column
    private String userId;
    @Column
    private String userName;
    @Column
    private Date reportAcquisitionResetTime;
    @OneToOne(mappedBy = "player", orphanRemoval = true, cascade = CascadeType.ALL)
    private Inventory inventory;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "player_character_relation")
    private List<Character> characters;
    @OneToMany(mappedBy = "player")
    @OrderColumn(name = "number")
    private List<Deck> decks;
}
