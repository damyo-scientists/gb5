package com.game.gb5.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreatedDate
	@GeneratedValue
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@GeneratedValue
	@Column(name = "updated_date", nullable = false)
	private LocalDateTime updatedDate;
	
	
	@PrePersist
	protected void onCreate() {
		updatedDate = createdDate = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedDate = LocalDateTime.now();
	}
}
