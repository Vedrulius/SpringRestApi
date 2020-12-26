package com.mihey.springrestapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "regions")
public class Region {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Writer> writers;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }

}