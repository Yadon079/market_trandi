package com.cloud.mini.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String cateCode;

    private String name;

    private String desc;

    private String itemImg;

    private int stock;

    private int price;

}

/*
* 물품
번호(PK)
점주 번호(FK)
이름
썸네일 이미지의 저장된 이름(FK)
소개글 내용
소개 이미지의 저장된 이름(FK)
수량(판매단위)
가격
* */