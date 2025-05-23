package com.thatsmyspot.userservice.services;

import com.thatsmyspot.userservice.entities.Role;
import com.thatsmyspot.userservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleRepository.findAll());
    }

    public ResponseEntity<Role> getRoleById(String id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Role> getRoleByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Role> saveRole(Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleRepository.save(role));
    }

    public ResponseEntity<Role> updateRole(String id, Role role) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if (existingRole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Role existing = existingRole.get();
        existing.setName(role.getName());
        existing.setDescription(role.getDescription());
        return ResponseEntity.ok(roleRepository.save(existing));
    }

    public ResponseEntity<Void> deleteRole(String id) {
        roleRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
