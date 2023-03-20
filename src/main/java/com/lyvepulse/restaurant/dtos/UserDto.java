package com.lyvepulse.restaurant.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto { //Copy from the entity
    private String name;
    private String role;
    private String email;
}
