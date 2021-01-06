package com.mihey.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Random;

@Getter
@Setter
@ToString
@Entity
@Table(name = "codes")
public class Code {
    @Id
    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "code")
    private String code;
    @Column(name = "is_confirmed")
    private boolean isConfirmed = false;
    @Column(name = "created")
    private Timestamp created = new Timestamp(System.currentTimeMillis());
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Code() {
        this.code = createCode();
    }

    private String createCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100_000 - 10_000) + 10_000);
    }

}
