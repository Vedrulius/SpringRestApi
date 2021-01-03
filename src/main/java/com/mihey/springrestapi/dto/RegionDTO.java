package com.mihey.springrestapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class RegionDTO {
    @Id
    private Integer id;
    private String name;
}
