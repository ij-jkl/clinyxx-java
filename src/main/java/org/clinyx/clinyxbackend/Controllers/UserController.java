package org.clinyx.clinyxbackend.Controllers;

import org.clinyx.clinyxbackend.Dtos.UserCreateDto;
import org.clinyx.clinyxbackend.Dtos.UserDto;
import org.clinyx.clinyxbackend.Entities.UserEntity;
import org.clinyx.clinyxbackend.Interfaces.Services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService _userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(IUserService userService, ModelMapper modelMapper) {
        this._userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get_all_users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(_userService.getAllUsers());
    }

    @GetMapping("/get_user_by_{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        Optional<UserEntity> user = _userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create_user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserCreateDto userCreateDto) {
        UserEntity createdUser = _userService.createUser(userCreateDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/update_user/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserCreateDto userCreateDto) {
        try {
            UserEntity updatedUser = _userService.updateUser(id, userCreateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        _userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}