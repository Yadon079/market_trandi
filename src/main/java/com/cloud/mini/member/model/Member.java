package com.cloud.mini.member.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {
		@Id
	  	String id;
		String pwd;
		String name;
		String nickname;
		String phone;
		String gender;
		int age;
		String location;
		String profile;
		int authority=1;  // 1 일반회원 2 블랙리스트 
		int role; //관리자 0 / 고객 1 / 상인 2 
}
