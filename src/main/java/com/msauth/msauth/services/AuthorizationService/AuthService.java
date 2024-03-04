package com.msauth.msauth.services.AuthorizationService;


import com.msauth.msauth.domain.Perfil.Perfil;
import com.msauth.msauth.domain.User.Users;
import com.msauth.msauth.dto.Users.UsersDTO;
import com.msauth.msauth.infra.repository.PerfilRepository.PerfilRepository;
import com.msauth.msauth.infra.repository.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public Users cadastrarUsuario(UsersDTO usersDTO, Set<String> perfilRoles) throws Exception  {
        Users novoUsuario = new Users();
        var encryptPassword = new BCryptPasswordEncoder().encode(usersDTO.password());
        if(usersDTO.email() != null && usersDTO.password() != null && usersDTO.name() != null){
            novoUsuario.setEmail(usersDTO.email());
            novoUsuario.setAbout(usersDTO.about());
            novoUsuario.setName(usersDTO.name());
            novoUsuario.setCidade(usersDTO.cidade());
            novoUsuario.setPassword(encryptPassword);
            novoUsuario.setIsActive(true);
            novoUsuario.setCreatedAt(new Date());
            Set<Perfil> perfis = perfilRepository.findByRoleIn(perfilRoles);
            novoUsuario.setPerfis(perfis);
            return userRepository.save(novoUsuario);
        }
        throw new RuntimeException("Favor prencher todos os campos");
    }

    public List<Users> getAll(){
       return userRepository.findAll();
    }

    public Users getOne(String email) throws Exception{
        UserDetails optionalUsers = userRepository.findByemail(email);
        if(optionalUsers != null){
            return (Users) optionalUsers;
        }
        throw new RuntimeException("Usuario não localizado!");
    }
    public void update(String email, UsersDTO data) throws Exception{
        Optional<Users> optionalUsers = userRepository.findByEmailOptional(email);
        if(optionalUsers.isPresent()){
            Users users = optionalUsers.get();
            users.updateUser(data);
            userRepository.save(users);
        }else{
            throw new Exception("Usuario não localizado");
        }
    }


}
