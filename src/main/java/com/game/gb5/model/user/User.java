package com.game.gb5.model.user;

import com.game.gb5.model.character.Character;
import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.deck.Deck;
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
public class User extends BaseEntity {
    public User(String userId, String userName) {
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
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Inventory inventory;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_character_relation")
    private List<Character> characters;
    @OneToMany(mappedBy = "user")
    @OrderColumn(name = "number")
    private List<Deck> decks;
}
