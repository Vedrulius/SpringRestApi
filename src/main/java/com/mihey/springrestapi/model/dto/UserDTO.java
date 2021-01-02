package com.mihey.springrestapi.model.dto;

import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.model.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Role role;
    private Status status;
}
