package com.mihey.springrestapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Size(min = 6)
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
