/*package com.salud.equipoT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salud.equipoT.entidad.Usuario;
import com.salud.equipoT.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailService {
    @Autowired
    private UsuarioRepository usuarioRepository;

     public Usuario buscarUsuario(Long id) {

        return usuarioRepository.findById(id).orElse(null);
    }
}
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Usuario usuario = usuarioRepository.findByEmail(email);

    if (usuario != null){
        List<GrantedAuthority> permisos = new ArrayList();
        
        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ usuario.getRol().toString());

        permisos.add(p);

        return new User(usuario.getEmail(), usuario.getPassword(),permisos);
    }else{
        return null;
    }
}

*/