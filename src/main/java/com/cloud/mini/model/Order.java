package com.cloud.mini.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String orderNum;

    private String deliAddress;

    private int totalPrice;

    @ManyToOne
    private User user;

}
