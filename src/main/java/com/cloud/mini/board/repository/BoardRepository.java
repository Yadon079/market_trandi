package com.cloud.mini.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
}