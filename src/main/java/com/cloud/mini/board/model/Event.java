package com.cloud.mini.board.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
		 name="EVENT_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="EVENT_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
public class Event {
	@Id
	@GeneratedValue(
			strategy=GenerationType.AUTO, //사용할 전략을 시퀀스로  선택
            generator="EVENT_SEQ_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설
            )
	 long id;
	
	String title;
	String content;
    String userId;
    long adminNum;
    
    @Temporal(TemporalType.DATE)
   	Date createDate;
}