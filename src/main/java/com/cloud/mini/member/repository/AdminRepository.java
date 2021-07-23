package com.cloud.mini.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.member.model.Admin;
import com.cloud.mini.member.model.Member;

public interface AdminRepository extends JpaRepository<Admin, String>{
			List<Admin> findAll();
}
