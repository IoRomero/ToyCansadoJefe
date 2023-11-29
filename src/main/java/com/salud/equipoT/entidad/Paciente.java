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

import com.salud.equipoT.enums.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "id")
    private Long dni;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "nombre")
    private String nombre;
     
   

    @Column(name ="rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @ManyToOne()
    @JoinColumn(name = "obra_social_id", referencedColumnName = "id")
    private ObraSocial obraSocial;
    @OneToOne()
    private HistoriaClinica historiaClinica;
    @OneToOne
    @JoinColumn(name = "idimagen" , referencedColumnName = "id")
    private Imagen imagen;
}