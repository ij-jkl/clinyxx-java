package org.clinyx.clinyxbackend.Interfaces.Services;

import org.clinyx.clinyxbackend.Dtos.RoleDtos.RoleCreateDto;
import org.clinyx.clinyxbackend.Dtos.RoleDtos.RoleDto;
import org.clinyx.clinyxbackend.Entities.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<RoleDto> getAllRoles();
    Optional<RoleEntity> getRoleById(Long id);
    RoleEntity createRole(RoleCreateDto roleCreateDto);
    RoleEntity updateRole(Long id, RoleCreateDto roleCreateDto);
    void deleteRole(Long id);
}