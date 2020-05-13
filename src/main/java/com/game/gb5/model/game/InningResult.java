package com.game.gb5.model.game;

import com.game.gb5.model.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class InningResult extends BaseEntity {
    @Column
    private int inning;

    @OneToMany
    private List<BattingResult> batterResultList;
}
