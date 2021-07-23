package com.cloud.mini.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.board.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}