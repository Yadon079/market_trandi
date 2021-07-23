package com.cloud.mini.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.board.model.User1;

public interface User1Repository extends JpaRepository<User1, Long> {
	public User1 findByEmailAndPwd(String email, String pwd);
}