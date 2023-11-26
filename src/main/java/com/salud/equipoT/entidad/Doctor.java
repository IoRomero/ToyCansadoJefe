package com.salud.equipoT.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "doctor")
public class Doctor{
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long matricula;
@Column(name = "nombre")
private String nombre;
private String email;
private String password;
private Double puntuacion;
private Double costoConsulta;

@OneToOne
private Especializacion especializacion;

@OneToMany
private List<Disponibilidad> turnos;
}
