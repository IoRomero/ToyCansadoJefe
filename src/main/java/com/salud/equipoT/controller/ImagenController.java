/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.salud.equipoT.controller;

import com.salud.equipoT.entidad.Doctor;
import com.salud.equipoT.entidad.Paciente;
import com.salud.equipoT.entidad.Usuario;
import com.salud.equipoT.service.DoctorService;
import com.salud.equipoT.service.PacienteService;
import com.salud.equipoT.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pablo A. Nista Casanova
 */
@Controller
@RequestMapping("/imagen")
public class ImagenController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable Long id ){
        Usuario usuario = usuarioService.buscarUsuario(id);
        byte[] imagen=usuario.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen,headers,HttpStatus.OK);
    }
   /* @GetMapping ("/perfil/{matricula}")
    public ResponseEntity<byte[]> imagenDoctor(@PathVariable Long matricula){
        Doctor doctor = doctorService.getOne(matricula);
        byte[] imagen = doctor.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.IMAGE_JPEG);
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }*/ 
}
