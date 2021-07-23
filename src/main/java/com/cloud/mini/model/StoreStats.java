package com.cloud.mini.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class StoreStats {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private int store;			// 시장 종류
	private String name;		// 점포명
	private String products;	// 취급 상품
	
	public StoreStats() {
		
	}
}
