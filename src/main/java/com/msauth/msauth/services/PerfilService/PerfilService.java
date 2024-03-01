package com.msauth.msauth.services.PerfilService;


import com.msauth.msauth.domain.Perfil.Perfil;
import com.msauth.msauth.infra.repository.PerfilRepository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {


    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> getAll(){
        return perfilRepository.findAll();
    }

    public Perfil post(Perfil perfil){
        return perfilRepository.save(perfil);
    }




}
