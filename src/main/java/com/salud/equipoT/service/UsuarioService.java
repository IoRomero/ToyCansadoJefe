package com.salud.equipoT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Usuario;
import com.salud.equipoT.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

     public Usuario buscarUsuario(Long id) {

        return usuarioRepository.findById(id).orElse(null);
    }
}
