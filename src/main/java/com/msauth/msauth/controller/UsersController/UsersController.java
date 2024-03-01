package com.msauth.msauth.controller.UsersController;


import com.msauth.msauth.domain.Perfil.Perfil;
import com.msauth.msauth.domain.User.Users;
import com.msauth.msauth.dto.Users.UsersDTO;
import com.msauth.msauth.services.AuthorizationService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/public/users")
@RestController
public class UsersController {


    @Autowired
    private AuthService authService;


    @PostMapping()
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsersDTO usersDTO) {
        // Supondo que você também receba a lista de roles associados ao usuário no DTO
        Set<String> perfilRoles = usersDTO.perfis().stream().map(Perfil::getRole).collect(Collectors.toSet());
        Users novoUsuario = authService.cadastrarUsuario(usersDTO, perfilRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity listUsers(){
        var list = authService.getAll();
        return ResponseEntity.ok(list);
    }






}
