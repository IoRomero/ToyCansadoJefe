package com.salud.equipoT.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.salud.equipoT.entidad.Consulta;
import com.salud.equipoT.entidad.Imagen;
import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.enums.Rol;
import com.salud.equipoT.repository.ObraSocialRepository;
import com.salud.equipoT.repository.PacienteRepository;

@Service
public class PacienteService implements UserDetailsService{

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ObraSocialRepository obraSocialRepository;
    @Autowired
    private ImagenService imagenService;
    
    @Transactional
    public void crearPaciente(Long dni, String nombre, String email, String password,String password2, Long obraSocialId,MultipartFile imagen) throws Exception{
    
       /* 
        Paciente paciente = Paciente.builder()
        .dni(dni)
        .nombre(nombre)
        .email(email)
        .password(password)
        .obraSocial(obraSocialRepository.findById(obraSocialId).orElse(null))
        .imagen(imagen).build();
        */
        validar(nombre, email, password, password2);

        Paciente paciente = new Paciente();
        Imagen imagenPercistir = imagenService.guardar(imagen);
        if (obraSocialId != null) {

            paciente.setDni(dni);
            paciente.setNombre(nombre);
            paciente.setEmail(email);
            paciente.setPassword(new BCryptPasswordEncoder().encode(password));
            paciente.setObraSocial(obraSocialRepository.findById(obraSocialId).orElse(null));
            paciente.setRol(Rol.PACIENTE);
            paciente.setImagen(imagenPercistir);

        } else {
            paciente.setDni(dni);
            paciente.setNombre(nombre);
            paciente.setEmail(email);
            paciente.setPassword(new BCryptPasswordEncoder().encode(password));
            paciente.setObraSocial(null);
            paciente.setRol(Rol.PACIENTE);
            paciente.setImagen(imagenPercistir);

        }

        pacienteRepository.save(paciente);

    }
    @Transactional
    public void editarPaciente(String dni, String nombre, String email, String password, String obraSocial,Imagen imagen) {
            pacienteRepository.editarPaciente(dni, nombre, email, password, obraSocial,imagen);
        }

   

    @Transactional
    public void eliminarPaciente(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }

    public Paciente buscarPaciente(Long dni) {

        return pacienteRepository.findById(dni).orElse(null);
    }

    public List<Consulta> buscarHistoriaClinica(Long dni) {

        return pacienteRepository.findHistoriaClinica(dni);

    }

    public List<Paciente> listarPacientesObraSocial(ObraSocial obraSocial) {

        return pacienteRepository.findByObraSocial(obraSocial.getId());
    }
    
    public void validar(String nombre,String email,String password,String password2)throws Exception{

        if(nombre.isBlank()){
            throw   new IllegalArgumentException("el usuario debe tener nombre");
        }

        if (email.isBlank()) {
            throw new IllegalArgumentException("el usuario debe tener email");
        }

        if (password.isBlank() || password.length() <= 4) {
            throw new IllegalArgumentException("La contrase;a debe tener mas de 4 caracteres");
        }

        if (!password2.equals(password)){
            throw new IllegalArgumentException("Las contrase;as ingresadas deben ser iguales");
        }
    }

    public List<Paciente> listarPacientes(){
        List<Paciente> pacientes= new ArrayList();
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        pacientes = pacienteRepository.findAll(sort);
        return pacientes;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
    Paciente paciente = pacienteRepository.findByEmail(email);

    if (paciente != null){
        
        List<GrantedAuthority> permisos = new ArrayList();
        
        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ paciente.getRol().toString());

        permisos.add(p);

        return new User(paciente.getEmail(),paciente.getPassword(),permisos);
    }else{
        return null;
    }
    
    }
    
}   
