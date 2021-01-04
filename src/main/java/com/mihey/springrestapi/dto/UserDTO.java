package com.mihey.springrestapi.dto;

import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.model.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String phoneNumber;
    private Role role = Role.USER;
    private Status status = Status.CONFIRMATION_REQUIRED;
}
