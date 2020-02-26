package com.game.gb5.common.model;

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
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
public abstract class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(nullable = false)
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
