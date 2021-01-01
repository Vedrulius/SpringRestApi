package com.mihey.springrestapi.model.dto;

import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.model.Status;

public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private Role role;
    private Status status;
}
