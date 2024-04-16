package com.msauth.msauth.domain.User;


import com.msauth.msauth.domain.Perfil.Perfil;
import com.msauth.msauth.dto.Users.UsersDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private Integer id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Boolean isActive;
    @NotNull
    private String name;

    private String cidade;

    private String about;

    private String imagem;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    @ManyToMany
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private Set<Perfil> perfis;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getRole()))
                .collect(Collectors.toList());
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public void updateUser(UsersDTO usersDTO){
        if(usersDTO.email() != null){
            this.email = usersDTO.email();
        }
        if(usersDTO.isActive() != null){
            this.isActive = usersDTO.isActive();
        }
        if(usersDTO.name() != null){
            this.name = usersDTO.name();
        }
        if(usersDTO.cidade() != null){
            this.cidade = usersDTO.cidade();
        }
        if(usersDTO.about() != null){
            this.about = usersDTO.about();
        }

    }



}


