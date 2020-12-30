package com.mihey.springrestapi.model.dto;

import javax.persistence.Id;

public class RegionDTO {
    @Id
    private Integer id;
    private String name;
}
