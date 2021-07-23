package com.cloud.mini.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.model.StoreStats;

public interface StoreStatsRepository extends JpaRepository<StoreStats, Long>{
	public List<StoreStats> findAllByStore(int store);
}
