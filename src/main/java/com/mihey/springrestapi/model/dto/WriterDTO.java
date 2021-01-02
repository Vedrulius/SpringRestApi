package com.mihey.springrestapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriterDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private RegionDTO region;
}
