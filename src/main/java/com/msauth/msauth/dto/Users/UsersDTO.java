package com.msauth.msauth.dto.Users;

import com.msauth.msauth.domain.Perfil.Perfil;

import java.util.Set;

public record UsersDTO(String email, String password, Boolean isActive, Set<Perfil> perfis) {
}

