package com.game.gb5.model.game.result;

import com.game.gb5.model.common.BaseEntity;
import com.game.gb5.model.game.type.InningType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InningResult extends BaseEntity {
    @Column
    private int inning;
    @Enumerated
    private InningType inningType;

    @OneToMany
    private List<BattingResult> battingResultList;
}
