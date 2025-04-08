package org.clinyx.clinyxbackend.Services;

import org.clinyx.clinyxbackend.Dtos.UserCreateDto;
import org.clinyx.clinyxbackend.Dtos.UserDto;
import org.clinyx.clinyxbackend.Entities.RoleEntity;
import org.clinyx.clinyxbackend.Entities.UserEntity;
import org.clinyx.clinyxbackend.Exception.ResourceNotFoundException;
import org.clinyx.clinyxbackend.Interfaces.Repository.IRoleRepository;
import org.clinyx.clinyxbackend.Interfaces.Repository.IUserRepository;
import org.clinyx.clinyxbackend.Interfaces.Services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository _userRepository;
    private final IRoleRepository _roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository, ModelMapper modelMapper) {
        this._userRepository = userRepository;
        this._roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = _userRepository.findAll();
        return users.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return _userRepository.findById(id);
    }

    @Override
    public UserEntity createUser(UserCreateDto userCreateDto) {
        UserEntity userEntity = modelMapper.map(userCreateDto, UserEntity.class);

        // Fetch Role entities based on the IDs from the DTO
        Set<Long> roleIds = userCreateDto.getRoleIds();
        if (roleIds != null && !roleIds.isEmpty()) {
            Set<RoleEntity> roles = new HashSet<>(_roleRepository.findAllById(roleIds));
            if (roles.size() != roleIds.size()) {
                throw new ResourceNotFoundException("One or more role IDs not found.");
            }
            userEntity.setRoles(roles);
        }

        return _userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(Long id, UserCreateDto userCreateDto) {
        UserEntity existingUser = _userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        modelMapper.map(userCreateDto, existingUser);

        Set<Long> roleIds = userCreateDto.getRoleIds();
        if (roleIds != null && !roleIds.isEmpty()) {
            Set<RoleEntity> roles = new HashSet<>(_roleRepository.findAllById(roleIds));
            if (roles.size() != roleIds.size()) {
                throw new ResourceNotFoundException("One or more role IDs not found.");
            }
            existingUser.setRoles(roles);
        }

        return _userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        _userRepository.deleteById(id);
    }
}