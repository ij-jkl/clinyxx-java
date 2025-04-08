package org.clinyx.clinyxbackend.Services;

import org.clinyx.clinyxbackend.Dtos.EntityDtos.RoleDtos.RoleCreateDto;
import org.clinyx.clinyxbackend.Dtos.EntityDtos.RoleDtos.RoleDto;
import org.clinyx.clinyxbackend.Entities.RoleEntity;
import org.clinyx.clinyxbackend.Exception.ResourceNotFoundException;
import org.clinyx.clinyxbackend.Interfaces.Repository.IRoleRepository;
import org.clinyx.clinyxbackend.Interfaces.Services.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository _roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(IRoleRepository roleRepository, ModelMapper modelMapper) {
        this._roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<RoleEntity> roles = _roleRepository.findAll();
        return roles.stream()
                .map(roleEntity -> modelMapper.map(roleEntity, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleEntity> getRoleById(Long id) {
        return _roleRepository.findById(id);
    }

    @Override
    public RoleEntity createRole(RoleCreateDto roleCreateDto) {
        RoleEntity roleEntity = modelMapper.map(roleCreateDto, RoleEntity.class);
        return _roleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity updateRole(Long id, RoleCreateDto roleCreateDto) {
        RoleEntity existingRole = _roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));

        modelMapper.map(roleCreateDto, existingRole);
        return _roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id) {
        _roleRepository.deleteById(id);
    }
}