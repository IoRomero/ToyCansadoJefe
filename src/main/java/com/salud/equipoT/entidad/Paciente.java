package com.salud.equipoT.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "paciente")
public class Paciente {

    
    @Id
    @Column(name = "id")
    private Long dni;
    private String nombre;
    private String email;
    private String password;
    @ManyToOne()
    @JoinColumn(name = "obra_social_id", referencedColumnName = "id")
    private ObraSocial obraSocial;
    @OneToOne()
    private HistoriaClinica historiaClinica;
    @Enumerated(EnumType.STRING)
    private Rol rol;



    
}