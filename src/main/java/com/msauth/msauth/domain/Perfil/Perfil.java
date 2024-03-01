package com.msauth.msauth.domain.Perfil;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "perfis")
@Getter
@Setter
@NoArgsConstructor
public class Perfil {
    @Id
    @GeneratedValue
    private Integer id;
    private String role;
}
