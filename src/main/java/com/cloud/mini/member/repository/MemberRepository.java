package com.cloud.mini.member.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.mini.member.model.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
			public 	Member findByIdAndPwdAndRole(String id, String pwd, int Role);
			public Member findByRole(int Role);
			List<Member> findAll();
			public Member findAllById(String id);
			public Member findAllByPhone(String phone);
			public Member findAllByNickname(String nickname);
			public void deleteById(String id);

}
