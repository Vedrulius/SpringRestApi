package com.mihey.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30")
    @Column(name = "user_name")
    private String username;
    @Size(min = 4)
    @Column(name = "password")
    private String password;
    @Size(min = 12)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Writer writer;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Code code;

}
