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
@Table(name = "users")
public class Writer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public Writer() {
    }

    public Writer(String firstName, String lastName, Region region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
    }
}
