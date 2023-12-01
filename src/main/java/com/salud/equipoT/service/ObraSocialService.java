package com.salud.equipoT.service;

import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.excepciones.MiException;
import com.salud.equipoT.repository.ObraSocialRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObraSocialService {
    
    @Autowired
    private ObraSocialRepository obraSocialRepository;

    @Transactional
    public void crearObraSocial(String nombre) throws MiException {
        validar(nombre);
        ObraSocial obraSocial = new ObraSocial();
        obraSocial.setNombre(nombre);
        obraSocialRepository.save(obraSocial);
    }

    public List<ObraSocial>listarObraSociales(){
        List<ObraSocial> obraSociales = new ArrayList();
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        obraSociales=obraSocialRepository.findAll(sort);

        return obraSociales;
    }

    @Transactional
    public void modificarObraSocial(Long id, String nombre) throws MiException{
        validar(nombre);
        obraSocialRepository.editarObraSocial(id, nombre);
        
    }

    public Optional<ObraSocial> obtenerObraSocialPorId(Long id) {
        return obraSocialRepository.findById(id);
    }

    
    public void eliminarObraSocial(Long id) {
        obraSocialRepository.deleteById(id);
    }

    public List<ObraSocial> buscarPorNombre(String nombre) {
        return obraSocialRepository.findByNombre(nombre);
    }

    /*public List<ObraSocial> buscarPorCoberturaGreaterThan(Long cobertura) {
        return obraSocialRepository.findByCoberturaGreaterThan(cobertura);
    }*/
 private void validar(String nombre) throws MiException{
        if(nombre.isEmpty()||nombre==null){
            throw new MiException("El nombre no puede ser nulo o estar vacio.");
        }
    }
       
}
