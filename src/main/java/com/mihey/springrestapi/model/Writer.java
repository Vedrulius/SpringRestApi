package com.mihey.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "writers")
public class Writer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "First Name should not be empty")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "Last Name should not be empty")
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private List<Post> posts;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}
