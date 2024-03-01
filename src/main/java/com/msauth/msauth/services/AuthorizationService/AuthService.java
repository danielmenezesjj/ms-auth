package com.msauth.msauth.services.AuthorizationService;


import com.msauth.msauth.domain.Perfil.Perfil;
import com.msauth.msauth.domain.User.Users;
import com.msauth.msauth.dto.Users.UsersDTO;
import com.msauth.msauth.infra.repository.PerfilRepository.PerfilRepository;
import com.msauth.msauth.infra.repository.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfilRepository perfilRepository; // Supondo que você tenha um repositório para os perfis

    public Users cadastrarUsuario(UsersDTO usersDTO, Set<String> perfilRoles) {
        Users novoUsuario = new Users();
        var encryptPassword = new BCryptPasswordEncoder().encode(usersDTO.password());
        novoUsuario.setEmail(usersDTO.email());
        novoUsuario.setPassword(encryptPassword);
        novoUsuario.setIsActive(usersDTO.isActive());
        novoUsuario.setCreatedAt(new Date());

        // Define os perfis associados ao usuário
        Set<Perfil> perfis = perfilRepository.findByRoleIn(perfilRoles);
        novoUsuario.setPerfis(perfis);

        // Salva o usuário no banco de dados
        return userRepository.save(novoUsuario);
    }

    public List<Users> getAll(){
       return userRepository.findAll();
    }

}
