package com.msauth.msauth.controller.PerfilController;


import com.msauth.msauth.domain.Perfil.Perfil;
import com.msauth.msauth.dto.Perfil.PerfilDTO;
import com.msauth.msauth.services.AuthorizationService.AuthService;
import com.msauth.msauth.services.PerfilService.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/public/perfil")
@RestController
public class PerfilController {


    @Autowired
    private PerfilService perfilService;


    @GetMapping
    public ResponseEntity listPerfis(){
        var list = perfilService.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity postPerfil(@RequestBody PerfilDTO dto){
        Perfil perfil = new Perfil(dto);
        perfilService.post(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfil);
    }






}
