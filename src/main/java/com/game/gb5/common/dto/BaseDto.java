package com.game.gb5.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseDto<T> {
    protected Long id;
    protected String code;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;

    public abstract T toEntity();
}
