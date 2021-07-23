package com.cloud.mini.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.board.model.Complain;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
}