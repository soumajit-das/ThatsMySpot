package com.thatsmyspot.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UpdateUserDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
