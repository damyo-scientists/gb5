package com.game.gb5.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class BaseDto<T> {
    protected Long id;
    protected String code;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;

    public void setIdAndCreatedDate(Long id, LocalDateTime createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public abstract T toEntity();
}
