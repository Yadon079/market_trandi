package com.cloud.mini.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.board.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}