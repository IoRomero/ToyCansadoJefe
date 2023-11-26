package com.salud.equipoT.service;

import com.salud.equipoT.entidad.Especializacion;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.repository.EspecializacionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EspecializacionService {
    @Autowired
    private EspecializacionRepository especializacionRepository;
    @Transactional
    public void crearEspecializacion (String nombre) throws MiException{
        validar(nombre);
        Especializacion especializacion = new Especializacion();
        especializacion.setNombre(nombre);
        especializacionRepository.save(especializacion);
    }
    public List<Especializacion> listarEspecializaciones(){
        List<Especializacion> especializaciones=new ArrayList();
        especializaciones = especializacionRepository.findAll();
        return especializaciones;
    }
    
    @Transactional
    public void modificarEspecializacion(String id, String nombre) throws MiException{
        validar(nombre);
        Optional<Especializacion> respuesta = especializacionRepository.findById(id);
        if (respuesta.isPresent()) {
            Especializacion especializacion = respuesta.get();
            especializacion.setNombre(nombre);
            especializacionRepository.save(especializacion);
        }
    }
    private void validar(String nombre) throws MiException{
        if (nombre.isEmpty()|| nombre == null) {
             throw new MiException("La Especialización no puede ser nulo o estar vacio.");
        }
    }
    public Especializacion getOne(String id){
        return especializacionRepository.findById(id).orElse(null);
    }
}
