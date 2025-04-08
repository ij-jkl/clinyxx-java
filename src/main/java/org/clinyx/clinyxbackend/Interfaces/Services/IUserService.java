package org.clinyx.clinyxbackend.Interfaces.Services;

import org.clinyx.clinyxbackend.Dtos.UserCreateDto;
import org.clinyx.clinyxbackend.Dtos.UserDto;
import org.clinyx.clinyxbackend.Entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserDto> getAllUsers();
    Optional<UserEntity> getUserById(Long id);
    UserEntity createUser(UserCreateDto userCreateDto);
    UserEntity updateUser(Long id, UserCreateDto userCreateDto);
    void deleteUser(Long id);
}