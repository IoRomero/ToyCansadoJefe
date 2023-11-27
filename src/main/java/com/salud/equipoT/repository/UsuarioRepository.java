package com.salud.equipoT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salud.equipoT.entidad.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


     Optional<Usuario> findById(Long id);
}
