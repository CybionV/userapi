package com.vma.userapi.controller;

import com.vma.userapi.dto.UserResponseDTO;
import com.vma.userapi.model.User;
import com.vma.userapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Api(value = "Mantencion de Usuarios", description = "Operaciones para el manejo de usuarios, creacion, actualizacion, eliminacion y consulta de usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Crea un nuevo usuario", response = User.class)
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            UserResponseDTO createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Error: " + e.getMessage()
            );
        }
    }
}