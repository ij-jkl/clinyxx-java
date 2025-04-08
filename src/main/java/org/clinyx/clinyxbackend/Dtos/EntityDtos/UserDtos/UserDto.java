package org.clinyx.clinyxbackend.Dtos.EntityDtos.UserDtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long idUser;

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate registrationDate;
    private Set<Long> roleIds;
}