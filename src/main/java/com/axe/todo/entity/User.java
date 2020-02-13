package com.axe.todo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private Date createTime;
    private Date updateTime;

    public User(String name, String password) {
        this.id = null;
        this.password = password;
        this.name = name;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
