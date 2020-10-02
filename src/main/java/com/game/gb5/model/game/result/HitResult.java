package com.game.gb5.model.game.result;

import com.game.gb5.model.common.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HitResult extends BaseEntity {
    @Column
    double finalHittingProbability;
    @Column
    double hitCheckRandomValue;
    @Column
    boolean isHit;
}
