package com.salud.equipoT.entidad;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.salud.equipoT.enums.Rol;

import lombok.Data;

@Entity
@Data
@Table(name = "doctor")
public class Doctor{
    
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "email", unique=true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name ="rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Column(name = "matricula", unique=true)
    private Long matricula;
    @Column()
    private LocalDate alta;
   
    @OneToMany
    private List<DiaAtencion> diasAtencion;
    
    private String horarioInicio;
    private String horarioFin;
    private Double precioConsulta;
    private Double puntuacion;
    private String observaciones;
    private Boolean activo;
    
    @OneToOne
    @JoinColumn(name = "idespecializacion" , referencedColumnName = "id")
    private Especializacion especializacion;

    @OneToMany()
    private List<Turno> turnosCreados;

    @OneToMany
    private List<Consulta> historialConsultas;
       
    @OneToOne
    @JoinColumn(name = "idimagen" , referencedColumnName = "id")
    private Imagen imagen;

    @PrePersist
    protected void onCreate() {
        alta = LocalDate.now(); 
    }

 
}