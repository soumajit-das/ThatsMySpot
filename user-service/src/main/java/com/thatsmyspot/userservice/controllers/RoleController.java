package com.thatsmyspot.userservice.controllers;

import com.thatsmyspot.userservice.entities.Role;
import com.thatsmyspot.userservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Role> getRoleById(@PathVariable("id") String id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Role> getRoleByName(@PathVariable("name") String name) {
        return roleService.getRoleByName(name);
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Role> updateRole(@PathVariable("id") String id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRole(@PathVariable("id") String id) {
        return roleService.deleteRole(id);
    }
}
