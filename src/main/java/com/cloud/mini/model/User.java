package com.cloud.mini.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    private String nickname;

    private String name;

    private String phoneNumber;

    private String address;

    private int authRole;

}
