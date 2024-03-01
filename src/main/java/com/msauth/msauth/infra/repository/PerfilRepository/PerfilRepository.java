package com.msauth.msauth.infra.repository.PerfilRepository;

import com.msauth.msauth.domain.Perfil.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Set<Perfil> findByRoleIn(Set<String> perfilRoles);
}
