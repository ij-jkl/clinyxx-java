package org.clinyx.clinyxbackend.Dtos.UserDtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserCreateDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private Set<Long> roleIds;
}