package org.clinyx.clinyxbackend.Controllers;

import org.clinyx.clinyxbackend.Dtos.RoleDtos.RoleCreateDto;
import org.clinyx.clinyxbackend.Dtos.RoleDtos.RoleDto;
import org.clinyx.clinyxbackend.Entities.RoleEntity;
import org.clinyx.clinyxbackend.Interfaces.Services.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final IRoleService _roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(IRoleService roleService, ModelMapper modelMapper) {
        this._roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/get_all_roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(_roleService.getAllRoles());
    }

    @GetMapping("/get_role_by_{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id) {
        Optional<RoleEntity> role = _roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create_role")
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleCreateDto roleCreateDto) {
        RoleEntity createdRole = _roleService.createRole(roleCreateDto);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/update_role_by_{id}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable Long id, @RequestBody RoleCreateDto roleCreateDto) {
        try {
            RoleEntity updatedRole = _roleService.updateRole(id, roleCreateDto);
            return ResponseEntity.ok(updatedRole);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete_role_by_{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        _roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}