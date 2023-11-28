package com.salud.equipoT.service;

import com.salud.equipoT.entidad.ObraSocial;
import com.salud.equipoT.repository.ObraSocialRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObraSocialService {
    
    @Autowired
    private ObraSocialRepository obraSocialRepository;

    @Transactional
    public void crearObraSocial(String nombre, Long cobertura) {
        ObraSocial obraSocial = new ObraSocial();
        obraSocial.setNombre(nombre);
        obraSocial.setCobertura(cobertura);
        obraSocialRepository.save(obraSocial);
    }

    public List<ObraSocial> obtenerTodasLasObrasSociales() {
        return obraSocialRepository.findAll();
    }

    public Optional<ObraSocial> obtenerObraSocialPorId(Long id) {
        return obraSocialRepository.findById(id);
    }

    public void guardarObraSocial(ObraSocial obraSocial) {
        obraSocialRepository.save(obraSocial);
    }

    public void eliminarObraSocial(Long id) {
        obraSocialRepository.deleteById(id);
    }

    public List<ObraSocial> buscarPorNombre(String nombre) {
        return obraSocialRepository.findByNombre(nombre);
    }

    public List<ObraSocial> buscarPorCoberturaGreaterThan(Long cobertura) {
        return obraSocialRepository.findByCoberturaGreaterThan(cobertura);
    }

       
}
