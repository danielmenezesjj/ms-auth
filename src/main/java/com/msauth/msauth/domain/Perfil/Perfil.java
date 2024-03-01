package com.msauth.msauth.domain.Perfil;


import com.msauth.msauth.dto.Perfil.PerfilDTO;
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



    public Perfil(PerfilDTO dto){
        this.role = dto.role();
    }

    public void updatePerfil(PerfilDTO dto){
       if(dto.role() != null){
           this.role = dto.role();
       }
    }



}
